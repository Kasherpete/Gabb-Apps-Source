package com.datadog.trace.common.sampling;

import com.datadog.opentracing.DDSpan;
import java.util.regex.Pattern;

public abstract class SamplingRule {
    private final RateSampler sampler;

    public abstract boolean matches(DDSpan dDSpan);

    public SamplingRule(RateSampler rateSampler) {
        this.sampler = rateSampler;
    }

    public boolean sample(DDSpan dDSpan) {
        return this.sampler.sample(dDSpan);
    }

    public RateSampler getSampler() {
        return this.sampler;
    }

    public static class AlwaysMatchesSamplingRule extends SamplingRule {
        public boolean matches(DDSpan dDSpan) {
            return true;
        }

        public AlwaysMatchesSamplingRule(RateSampler rateSampler) {
            super(rateSampler);
        }
    }

    public static abstract class PatternMatchSamplingRule extends SamplingRule {
        private final Pattern pattern;

        /* access modifiers changed from: protected */
        public abstract String getRelevantString(DDSpan dDSpan);

        public PatternMatchSamplingRule(String str, RateSampler rateSampler) {
            super(rateSampler);
            this.pattern = Pattern.compile(str);
        }

        public boolean matches(DDSpan dDSpan) {
            String relevantString = getRelevantString(dDSpan);
            return relevantString != null && this.pattern.matcher(relevantString).matches();
        }
    }

    public static class ServiceSamplingRule extends PatternMatchSamplingRule {
        public ServiceSamplingRule(String str, RateSampler rateSampler) {
            super(str, rateSampler);
        }

        /* access modifiers changed from: protected */
        public String getRelevantString(DDSpan dDSpan) {
            return dDSpan.getServiceName();
        }
    }

    public static class OperationSamplingRule extends PatternMatchSamplingRule {
        public OperationSamplingRule(String str, RateSampler rateSampler) {
            super(str, rateSampler);
        }

        /* access modifiers changed from: protected */
        public String getRelevantString(DDSpan dDSpan) {
            return dDSpan.getOperationName();
        }
    }
}
