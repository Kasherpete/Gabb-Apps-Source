package com.datadog.trace.common.sampling;

import com.datadog.opentracing.DDSpan;

public class AllSampler extends AbstractSampler {
    public boolean doSample(DDSpan dDSpan) {
        return true;
    }

    public String toString() {
        return "AllSampler { sample=true }";
    }
}
