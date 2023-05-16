package com.datadog.opentracing;

import com.datadog.trace.api.DDTags;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import p006io.opentracing.SpanContext;

public class DDSpanContext implements SpanContext {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Map<String, Number> EMPTY_METRICS = Collections.emptyMap();
    public static final String ORIGIN_KEY = "_dd.origin";
    public static final String PRIORITY_SAMPLING_KEY = "_sampling_priority_v1";
    public static final String SAMPLE_RATE_KEY = "_sample_rate";
    private final Map<String, String> baggageItems;
    private volatile boolean errorFlag;
    private final AtomicReference<Map<String, Number>> metrics = new AtomicReference<>();
    private volatile String operationName;
    private final String origin;
    private final BigInteger parentId;
    private volatile String resourceName;
    private boolean samplingPriorityLocked = false;
    private volatile String serviceName;
    private final Map<String, String> serviceNameMappings;
    private final BigInteger spanId;
    private volatile String spanType;
    private final Map<String, Object> tags;
    private final long threadId;
    private final String threadName;
    private final PendingTrace trace;
    private final BigInteger traceId;
    private final DDTracer tracer;

    public DDSpanContext(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, String str, String str2, String str3, int i, String str4, Map<String, String> map, boolean z, String str5, Map<String, Object> map2, PendingTrace pendingTrace, DDTracer dDTracer, Map<String, String> map3) {
        int i2 = i;
        String str6 = str4;
        Map<String, String> map4 = map;
        Map<String, Object> map5 = map2;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.tags = concurrentHashMap;
        String name = Thread.currentThread().getName();
        this.threadName = name;
        long id = Thread.currentThread().getId();
        this.threadId = id;
        this.tracer = dDTracer;
        this.trace = pendingTrace;
        this.traceId = bigInteger;
        this.spanId = bigInteger2;
        this.parentId = bigInteger3;
        if (map4 == null) {
            this.baggageItems = new ConcurrentHashMap(0);
        } else {
            this.baggageItems = new ConcurrentHashMap(map4);
        }
        if (map5 != null) {
            concurrentHashMap.putAll(map5);
        }
        this.serviceNameMappings = map3;
        String str7 = str;
        setServiceName(str);
        this.operationName = str2;
        this.resourceName = str3;
        this.errorFlag = z;
        this.spanType = str5;
        this.origin = str6;
        if (i2 != Integer.MIN_VALUE) {
            setSamplingPriority(i2);
        }
        if (str6 != null) {
            concurrentHashMap.put(ORIGIN_KEY, str6);
        }
        concurrentHashMap.put(DDTags.THREAD_NAME, name);
        concurrentHashMap.put(DDTags.THREAD_ID, Long.valueOf(id));
    }

    public BigInteger getTraceId() {
        return this.traceId;
    }

    public String toTraceId() {
        return this.traceId.toString();
    }

    public BigInteger getParentId() {
        return this.parentId;
    }

    public BigInteger getSpanId() {
        return this.spanId;
    }

    public String toSpanId() {
        return this.spanId.toString();
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String str) {
        if (this.serviceNameMappings.containsKey(str)) {
            this.serviceName = this.serviceNameMappings.get(str);
        } else {
            this.serviceName = str;
        }
    }

    public String getResourceName() {
        return isResourceNameSet() ? this.resourceName : this.operationName;
    }

    public boolean isResourceNameSet() {
        return this.resourceName != null && !this.resourceName.isEmpty();
    }

    public boolean hasResourceName() {
        return isResourceNameSet() || this.tags.containsKey(DDTags.RESOURCE_NAME);
    }

    public void setResourceName(String str) {
        this.resourceName = str;
    }

    public String getOperationName() {
        return this.operationName;
    }

    public void setOperationName(String str) {
        this.operationName = str;
    }

    public boolean getErrorFlag() {
        return this.errorFlag;
    }

    public void setErrorFlag(boolean z) {
        this.errorFlag = z;
    }

    public String getSpanType() {
        return this.spanType;
    }

    public void setSpanType(String str) {
        this.spanType = str;
    }

    public boolean setSamplingPriority(int i) {
        DDSpan rootSpan;
        if (i == Integer.MIN_VALUE) {
            return false;
        }
        PendingTrace pendingTrace = this.trace;
        if (pendingTrace != null && (rootSpan = pendingTrace.getRootSpan()) != null && rootSpan.context() != this) {
            return rootSpan.context().setSamplingPriority(i);
        }
        synchronized (this) {
            if (this.samplingPriorityLocked) {
                return false;
            }
            setMetric(PRIORITY_SAMPLING_KEY, Integer.valueOf(i));
            return true;
        }
    }

    public int getSamplingPriority() {
        DDSpan rootSpan = this.trace.getRootSpan();
        if (rootSpan != null && rootSpan.context() != this) {
            return rootSpan.context().getSamplingPriority();
        }
        Number number = getMetrics().get(PRIORITY_SAMPLING_KEY);
        if (number == null) {
            return Integer.MIN_VALUE;
        }
        return number.intValue();
    }

    public boolean lockSamplingPriority() {
        boolean z;
        DDSpan rootSpan = this.trace.getRootSpan();
        if (rootSpan != null && rootSpan.context() != this) {
            return rootSpan.context().lockSamplingPriority();
        }
        synchronized (this) {
            if (getMetrics().get(PRIORITY_SAMPLING_KEY) != null) {
                if (!this.samplingPriorityLocked) {
                    this.samplingPriorityLocked = true;
                }
            }
            z = this.samplingPriorityLocked;
        }
        return z;
    }

    public String getOrigin() {
        DDSpan rootSpan = this.trace.getRootSpan();
        if (rootSpan != null) {
            return rootSpan.context().origin;
        }
        return this.origin;
    }

    public void setBaggageItem(String str, String str2) {
        this.baggageItems.put(str, str2);
    }

    public String getBaggageItem(String str) {
        return this.baggageItems.get(str);
    }

    public Map<String, String> getBaggageItems() {
        return this.baggageItems;
    }

    public Iterable<Map.Entry<String, String>> baggageItems() {
        return this.baggageItems.entrySet();
    }

    public PendingTrace getTrace() {
        return this.trace;
    }

    @Deprecated
    public DDTracer getTracer() {
        return this.tracer;
    }

    public Map<String, Number> getMetrics() {
        Map<String, Number> map = this.metrics.get();
        return map == null ? EMPTY_METRICS : map;
    }

    public void setMetric(String str, Number number) {
        if (this.metrics.get() == null) {
            this.metrics.compareAndSet((Object) null, new ConcurrentHashMap());
        }
        if (number instanceof Float) {
            this.metrics.get().put(str, Double.valueOf(number.doubleValue()));
        } else {
            this.metrics.get().put(str, number);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0038, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setTag(java.lang.String r4, java.lang.Object r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r5 == 0) goto L_0x0039
            boolean r0 = r5 instanceof java.lang.String     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0011
            r0 = r5
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0040 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0011
            goto L_0x0039
        L_0x0011:
            r0 = 1
            com.datadog.opentracing.DDTracer r1 = r3.tracer     // Catch:{ all -> 0x0040 }
            java.util.List r1 = r1.getSpanContextDecorators(r4)     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x0030
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0040 }
        L_0x001e:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0040 }
            if (r2 == 0) goto L_0x0030
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0040 }
            com.datadog.opentracing.decorators.AbstractDecorator r2 = (com.datadog.opentracing.decorators.AbstractDecorator) r2     // Catch:{ all -> 0x0040 }
            boolean r2 = r2.shouldSetTag(r3, r4, r5)     // Catch:{ all -> 0x001e }
            r0 = r0 & r2
            goto L_0x001e
        L_0x0030:
            if (r0 == 0) goto L_0x0037
            java.util.Map<java.lang.String, java.lang.Object> r0 = r3.tags     // Catch:{ all -> 0x0040 }
            r0.put(r4, r5)     // Catch:{ all -> 0x0040 }
        L_0x0037:
            monitor-exit(r3)
            return
        L_0x0039:
            java.util.Map<java.lang.String, java.lang.Object> r5 = r3.tags     // Catch:{ all -> 0x0040 }
            r5.remove(r4)     // Catch:{ all -> 0x0040 }
            monitor-exit(r3)
            return
        L_0x0040:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.opentracing.DDSpanContext.setTag(java.lang.String, java.lang.Object):void");
    }

    public synchronized Map<String, Object> getTags() {
        return Collections.unmodifiableMap(this.tags);
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("DDSpan [ t_id=").append(this.traceId).append(", s_id=").append(this.spanId).append(", p_id=").append(this.parentId).append("] trace=").append(getServiceName()).append("/").append(getOperationName()).append("/").append(getResourceName()).append(" metrics=").append(new TreeMap(getMetrics()));
        if (this.errorFlag) {
            append.append(" *errored*");
        }
        append.append(" tags=").append(new TreeMap(this.tags));
        return append.toString();
    }
}
