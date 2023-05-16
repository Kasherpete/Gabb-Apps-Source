package com.datadog.trace.common.sampling;

import com.datadog.opentracing.DDSpan;
import java.util.Collections;
import java.util.Map;

public class RateByServiceSampler implements Sampler, PrioritySampler {
    private static final String DEFAULT_KEY = "service:,env:";
    private static final double DEFAULT_RATE = 1.0d;
    public static final String SAMPLING_AGENT_RATE = "_dd.agent_psr";
    private volatile Map<String, RateSampler> serviceRates = Collections.unmodifiableMap(Collections.singletonMap(DEFAULT_KEY, createRateSampler(DEFAULT_RATE)));

    public boolean sample(DDSpan dDSpan) {
        return true;
    }

    public void setSamplingPriority(DDSpan dDSpan) {
        boolean z;
        Map<String, RateSampler> map = this.serviceRates;
        RateSampler rateSampler = this.serviceRates.get("service:" + dDSpan.getServiceName() + ",env:" + getSpanEnv(dDSpan));
        if (rateSampler == null) {
            rateSampler = map.get(DEFAULT_KEY);
        }
        if (rateSampler.sample(dDSpan)) {
            z = dDSpan.context().setSamplingPriority(1);
        } else {
            z = dDSpan.context().setSamplingPriority(0);
        }
        if (z) {
            dDSpan.context().setMetric(SAMPLING_AGENT_RATE, Double.valueOf(rateSampler.getSampleRate()));
        }
    }

    private static String getSpanEnv(DDSpan dDSpan) {
        return dDSpan.getTags().get("env") == null ? "" : String.valueOf(dDSpan.getTags().get("env"));
    }

    private RateSampler createRateSampler(double d) {
        if (d < 0.0d || d > DEFAULT_RATE) {
            d = 1.0d;
        }
        return new DeterministicSampler(d);
    }
}
