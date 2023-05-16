package com.datadog.opentracing.scopemanager;

import com.datadog.opentracing.DDSpan;
import com.datadog.opentracing.PendingTrace;
import com.datadog.opentracing.jfr.DDScopeEvent;
import com.datadog.opentracing.jfr.DDScopeEventFactory;
import com.datadog.trace.context.ScopeListener;
import com.datadog.trace.context.TraceScope;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ContinuableScope implements DDScope, TraceScope {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Continuation continuation;
    private final int depth;
    private final DDScopeEvent event;
    /* access modifiers changed from: private */
    public final DDScopeEventFactory eventFactory;
    /* access modifiers changed from: private */
    public final boolean finishOnClose;
    private final AtomicBoolean isAsyncPropagating;
    /* access modifiers changed from: private */
    public final AtomicInteger openCount;
    /* access modifiers changed from: private */
    public final ContextualScopeManager scopeManager;
    /* access modifiers changed from: private */
    public final DDSpan spanUnderScope;
    private final DDScope toRestore;

    ContinuableScope(ContextualScopeManager contextualScopeManager, DDSpan dDSpan, boolean z, DDScopeEventFactory dDScopeEventFactory) {
        this(contextualScopeManager, new AtomicInteger(1), (Continuation) null, dDSpan, z, dDScopeEventFactory);
    }

    private ContinuableScope(ContextualScopeManager contextualScopeManager, AtomicInteger atomicInteger, Continuation continuation2, DDSpan dDSpan, boolean z, DDScopeEventFactory dDScopeEventFactory) {
        int i = 0;
        this.isAsyncPropagating = new AtomicBoolean(false);
        this.scopeManager = contextualScopeManager;
        this.openCount = atomicInteger;
        this.continuation = continuation2;
        this.spanUnderScope = dDSpan;
        this.finishOnClose = z;
        this.eventFactory = dDScopeEventFactory;
        DDScopeEvent create = dDScopeEventFactory.create(dDSpan.context());
        this.event = create;
        create.start();
        DDScope dDScope = ContextualScopeManager.tlsScope.get();
        this.toRestore = dDScope;
        ContextualScopeManager.tlsScope.set(this);
        this.depth = dDScope != null ? dDScope.depth() + 1 : i;
        for (ScopeListener afterScopeActivated : contextualScopeManager.scopeListeners) {
            afterScopeActivated.afterScopeActivated();
        }
    }

    public void close() {
        this.event.finish();
        if (this.continuation != null) {
            this.spanUnderScope.context().getTrace().cancelContinuation(this.continuation);
        }
        if (this.openCount.decrementAndGet() == 0 && this.finishOnClose) {
            this.spanUnderScope.finish();
        }
        for (ScopeListener afterScopeClosed : this.scopeManager.scopeListeners) {
            afterScopeClosed.afterScopeClosed();
        }
        if (ContextualScopeManager.tlsScope.get() == this) {
            ContextualScopeManager.tlsScope.set(this.toRestore);
            if (this.toRestore != null) {
                for (ScopeListener afterScopeActivated : this.scopeManager.scopeListeners) {
                    afterScopeActivated.afterScopeActivated();
                }
            }
        }
    }

    public DDSpan span() {
        return this.spanUnderScope;
    }

    public int depth() {
        return this.depth;
    }

    public boolean isAsyncPropagating() {
        return this.isAsyncPropagating.get();
    }

    public void setAsyncPropagation(boolean z) {
        this.isAsyncPropagating.set(z);
    }

    public Continuation capture() {
        if (isAsyncPropagating()) {
            return new Continuation();
        }
        return null;
    }

    public String toString() {
        return super.toString() + "->" + this.spanUnderScope;
    }

    public class Continuation implements Closeable, TraceScope.Continuation {
        public WeakReference<Continuation> ref;
        private final PendingTrace trace;
        private final AtomicBoolean used;

        private Continuation() {
            this.used = new AtomicBoolean(false);
            ContinuableScope.this.openCount.incrementAndGet();
            PendingTrace trace2 = ContinuableScope.this.spanUnderScope.context().getTrace();
            this.trace = trace2;
            trace2.registerContinuation(this);
        }

        public ContinuableScope activate() {
            if (!this.used.compareAndSet(false, true)) {
                return new ContinuableScope(ContinuableScope.this.scopeManager, new AtomicInteger(1), (Continuation) null, ContinuableScope.this.spanUnderScope, ContinuableScope.this.finishOnClose, ContinuableScope.this.eventFactory);
            }
            return new ContinuableScope(ContinuableScope.this.scopeManager, ContinuableScope.this.openCount, this, ContinuableScope.this.spanUnderScope, ContinuableScope.this.finishOnClose, ContinuableScope.this.eventFactory);
        }

        public void close() {
            close(true);
        }

        public void close(boolean z) {
            if (this.used.compareAndSet(false, true)) {
                this.trace.cancelContinuation(this);
                if (z) {
                    ContinuableScope.this.close();
                } else if (ContinuableScope.this.openCount.decrementAndGet() == 0 && ContinuableScope.this.finishOnClose) {
                    ContinuableScope.this.spanUnderScope.finish();
                }
            }
        }
    }
}
