package com.datadog.trace.common.sampling;

import com.datadog.opentracing.DDSpan;
import com.datadog.opentracing.DDTracer;
import java.math.BigDecimal;
import java.math.BigInteger;

public class DeterministicSampler implements RateSampler {
    private static final BigInteger KNUTH_FACTOR = new BigInteger("1111111111111111111");
    private static final BigInteger MODULUS = new BigInteger("2").pow(64);
    private static final BigDecimal TRACE_ID_MAX_AS_BIG_DECIMAL = new BigDecimal(DDTracer.TRACE_ID_MAX);
    private final BigInteger cutoff;
    private final double rate;

    public DeterministicSampler(double d) {
        this.rate = d;
        this.cutoff = new BigDecimal(d).multiply(TRACE_ID_MAX_AS_BIG_DECIMAL).toBigInteger();
    }

    public boolean sample(DDSpan dDSpan) {
        double d = this.rate;
        return d == 1.0d || (d != 0.0d && dDSpan.getTraceId().multiply(KNUTH_FACTOR).mod(MODULUS).compareTo(this.cutoff) < 0);
    }

    public double getSampleRate() {
        return this.rate;
    }
}
