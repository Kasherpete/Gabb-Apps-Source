package com.datadog.trace.common.sampling;

import com.datadog.opentracing.DDSpan;

public interface PrioritySampler {
    void setSamplingPriority(DDSpan dDSpan);
}
