package com.datadog.opentracing.propagation;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExtractedContext extends TagContext {
    private final Map<String, String> baggage;
    private final int samplingPriority;
    private final AtomicBoolean samplingPriorityLocked = new AtomicBoolean(false);
    private final BigInteger spanId;
    private final BigInteger traceId;

    public ExtractedContext(BigInteger bigInteger, BigInteger bigInteger2, int i, String str, Map<String, String> map, Map<String, String> map2) {
        super(str, map2);
        this.traceId = bigInteger;
        this.spanId = bigInteger2;
        this.samplingPriority = i;
        this.baggage = map;
    }

    public Iterable<Map.Entry<String, String>> baggageItems() {
        return this.baggage.entrySet();
    }

    public void lockSamplingPriority() {
        this.samplingPriorityLocked.set(true);
    }

    public BigInteger getTraceId() {
        return this.traceId;
    }

    public BigInteger getSpanId() {
        return this.spanId;
    }

    public int getSamplingPriority() {
        return this.samplingPriority;
    }

    public Map<String, String> getBaggage() {
        return this.baggage;
    }

    public boolean getSamplingPriorityLocked() {
        return this.samplingPriorityLocked.get();
    }
}
