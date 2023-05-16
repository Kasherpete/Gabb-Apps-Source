package com.datadog.opentracing;

import com.datadog.exec.CommonTaskExecutor;
import com.datadog.opentracing.scopemanager.ContinuableScope;
import com.datadog.trace.common.util.Clock;
import java.io.Closeable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PendingTrace extends LinkedList<DDSpan> {
    private static final AtomicReference<SpanCleaner> SPAN_CLEANER = new AtomicReference<>();
    private final AtomicInteger completedSpanCount = new AtomicInteger(0);
    private final AtomicBoolean isWritten = new AtomicBoolean(false);
    private final AtomicInteger pendingReferenceCount = new AtomicInteger(0);
    private final ReferenceQueue referenceQueue = new ReferenceQueue();
    private final AtomicReference<WeakReference<DDSpan>> rootSpan = new AtomicReference<>();
    private final long startNanoTicks;
    private final long startTimeNano;
    private final BigInteger traceId;
    private final DDTracer tracer;
    private final Set<WeakReference<?>> weakReferences = Collections.newSetFromMap(new ConcurrentHashMap());

    PendingTrace(DDTracer dDTracer, BigInteger bigInteger) {
        this.tracer = dDTracer;
        this.traceId = bigInteger;
        this.startTimeNano = Clock.currentNanoTime();
        this.startNanoTicks = Clock.currentNanoTicks();
        addPendingTrace();
    }

    public long getCurrentTimeNano() {
        return this.startTimeNano + Math.max(0, Clock.currentNanoTicks() - this.startNanoTicks);
    }

    public void registerSpan(DDSpan dDSpan) {
        if (this.traceId != null && dDSpan.context() != null && this.traceId.equals(dDSpan.context().getTraceId())) {
            this.rootSpan.compareAndSet((Object) null, new WeakReference(dDSpan));
            synchronized (dDSpan) {
                if (dDSpan.ref == null) {
                    dDSpan.ref = new WeakReference<>(dDSpan, this.referenceQueue);
                    this.weakReferences.add(dDSpan.ref);
                    this.pendingReferenceCount.incrementAndGet();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void expireSpan(com.datadog.opentracing.DDSpan r3, boolean r4) {
        /*
            r2 = this;
            java.math.BigInteger r0 = r2.traceId
            if (r0 == 0) goto L_0x0042
            com.datadog.opentracing.DDSpanContext r0 = r3.context()
            if (r0 != 0) goto L_0x000b
            goto L_0x0042
        L_0x000b:
            java.math.BigInteger r0 = r2.traceId
            com.datadog.opentracing.DDSpanContext r1 = r3.context()
            java.math.BigInteger r1 = r1.getTraceId()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x001c
            return
        L_0x001c:
            monitor-enter(r3)
            java.lang.ref.WeakReference<com.datadog.opentracing.DDSpan> r0 = r3.ref     // Catch:{ all -> 0x003f }
            if (r0 != 0) goto L_0x0023
            monitor-exit(r3)     // Catch:{ all -> 0x003f }
            return
        L_0x0023:
            java.util.Set<java.lang.ref.WeakReference<?>> r0 = r2.weakReferences     // Catch:{ all -> 0x003f }
            java.lang.ref.WeakReference<com.datadog.opentracing.DDSpan> r1 = r3.ref     // Catch:{ all -> 0x003f }
            r0.remove(r1)     // Catch:{ all -> 0x003f }
            java.lang.ref.WeakReference<com.datadog.opentracing.DDSpan> r0 = r3.ref     // Catch:{ all -> 0x003f }
            r0.clear()     // Catch:{ all -> 0x003f }
            r0 = 0
            r3.ref = r0     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0038
            r2.expireReference()     // Catch:{ all -> 0x003f }
            goto L_0x003d
        L_0x0038:
            java.util.concurrent.atomic.AtomicInteger r2 = r2.pendingReferenceCount     // Catch:{ all -> 0x003f }
            r2.decrementAndGet()     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r3)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x003f }
            throw r2
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.opentracing.PendingTrace.expireSpan(com.datadog.opentracing.DDSpan, boolean):void");
    }

    public void dropSpan(DDSpan dDSpan) {
        expireSpan(dDSpan, false);
    }

    public void addSpan(DDSpan dDSpan) {
        if (dDSpan.getDurationNano() != 0 && this.traceId != null && dDSpan.context() != null && this.traceId.equals(dDSpan.getTraceId())) {
            if (!this.isWritten.get()) {
                addFirst(dDSpan);
            }
            expireSpan(dDSpan, true);
        }
    }

    public DDSpan getRootSpan() {
        WeakReference weakReference = this.rootSpan.get();
        if (weakReference == null) {
            return null;
        }
        return (DDSpan) weakReference.get();
    }

    public void registerContinuation(ContinuableScope.Continuation continuation) {
        synchronized (continuation) {
            if (continuation.ref == null) {
                continuation.ref = new WeakReference<>(continuation, this.referenceQueue);
                this.weakReferences.add(continuation.ref);
                this.pendingReferenceCount.incrementAndGet();
            }
        }
    }

    public void cancelContinuation(ContinuableScope.Continuation continuation) {
        synchronized (continuation) {
            if (continuation.ref != null) {
                this.weakReferences.remove(continuation.ref);
                continuation.ref.clear();
                continuation.ref = null;
                expireReference();
            }
        }
    }

    private void expireReference() {
        if (this.pendingReferenceCount.decrementAndGet() == 0) {
            write();
        } else if (this.tracer.getPartialFlushMinSpans() > 0 && size() > this.tracer.getPartialFlushMinSpans()) {
            synchronized (this) {
                if (size() > this.tracer.getPartialFlushMinSpans()) {
                    DDSpan rootSpan2 = getRootSpan();
                    ArrayList arrayList = new ArrayList(size());
                    Iterator it = iterator();
                    while (it.hasNext()) {
                        DDSpan dDSpan = (DDSpan) it.next();
                        if (dDSpan != rootSpan2) {
                            arrayList.add(dDSpan);
                            this.completedSpanCount.decrementAndGet();
                            it.remove();
                        }
                    }
                    this.tracer.write(arrayList);
                }
            }
        }
    }

    private synchronized void write() {
        if (this.isWritten.compareAndSet(false, true)) {
            removePendingTrace();
            if (!isEmpty()) {
                this.tracer.write(this);
            }
        }
    }

    public synchronized boolean clean() {
        boolean z;
        z = false;
        int i = 0;
        while (true) {
            Reference poll = this.referenceQueue.poll();
            if (poll != null) {
                this.weakReferences.remove(poll);
                if (this.isWritten.compareAndSet(false, true)) {
                    removePendingTrace();
                    this.tracer.incrementTraceCount();
                }
                i++;
                expireReference();
            } else if (i > 0) {
                z = true;
            }
        }
        return z;
    }

    public void addFirst(DDSpan dDSpan) {
        super.addFirst(dDSpan);
        this.completedSpanCount.incrementAndGet();
    }

    public int size() {
        return this.completedSpanCount.get();
    }

    private void addPendingTrace() {
        SpanCleaner spanCleaner = SPAN_CLEANER.get();
        if (spanCleaner != null) {
            spanCleaner.pendingTraces.add(this);
        }
    }

    private void removePendingTrace() {
        SpanCleaner spanCleaner = SPAN_CLEANER.get();
        if (spanCleaner != null) {
            spanCleaner.pendingTraces.remove(this);
        }
    }

    static void initialize() {
        SpanCleaner andSet = SPAN_CLEANER.getAndSet(new SpanCleaner());
        if (andSet != null) {
            andSet.close();
        }
    }

    static void close() {
        SpanCleaner andSet = SPAN_CLEANER.getAndSet((Object) null);
        if (andSet != null) {
            andSet.close();
        }
    }

    private static class SpanCleaner implements Runnable, Closeable {
        private static final long CLEAN_FREQUENCY = 1;
        /* access modifiers changed from: private */
        public final Set<PendingTrace> pendingTraces = Collections.newSetFromMap(new ConcurrentHashMap());

        public SpanCleaner() {
            CommonTaskExecutor.INSTANCE.scheduleAtFixedRate(SpanCleanerTask.INSTANCE, this, 0, CLEAN_FREQUENCY, TimeUnit.SECONDS, "Pending trace cleaner");
        }

        public void run() {
            for (PendingTrace clean : this.pendingTraces) {
                clean.clean();
            }
        }

        public void close() {
            run();
        }
    }

    private static class SpanCleanerTask implements CommonTaskExecutor.Task<SpanCleaner> {
        static final SpanCleanerTask INSTANCE = new SpanCleanerTask();

        private SpanCleanerTask() {
        }

        public void run(SpanCleaner spanCleaner) {
            spanCleaner.run();
        }
    }
}
