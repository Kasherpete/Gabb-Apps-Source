package com.datadog.trace.common.sampling;

import com.datadog.opentracing.DDSpan;
import com.datadog.trace.api.Config;
import java.util.Properties;

public interface Sampler {
    boolean sample(DDSpan dDSpan);

    public static final class Builder {
        public static Sampler forConfig(Config config) {
            if (config == null) {
                return new AllSampler();
            }
            if (config.isPrioritySamplingEnabled()) {
                return new RateByServiceSampler();
            }
            return new AllSampler();
        }

        public static Sampler forConfig(Properties properties) {
            return forConfig(Config.get(properties));
        }

        private Builder() {
        }
    }
}
