package com.datadog.trace.api.sampling;

public class PrioritySampling {
    public static final int SAMPLER_DROP = 0;
    public static final int SAMPLER_KEEP = 1;
    public static final int UNSET = Integer.MIN_VALUE;
    public static final int USER_DROP = -1;
    public static final int USER_KEEP = 2;

    private PrioritySampling() {
    }
}
