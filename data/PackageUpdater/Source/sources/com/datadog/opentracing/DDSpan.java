package com.datadog.opentracing;

import com.datadog.trace.api.DDTags;
import com.datadog.trace.api.interceptor.MutableSpan;
import com.datadog.trace.common.util.Clock;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p006io.opentracing.Span;
import p006io.opentracing.tag.Tag;

public class DDSpan implements Span, MutableSpan {
    private final DDSpanContext context;
    private final AtomicLong durationNano;
    private final LogHandler logHandler;
    volatile WeakReference<DDSpan> ref;
    private final long startTimeMicro;
    private final long startTimeNano;

    DDSpan(long j, DDSpanContext dDSpanContext) {
        this(j, dDSpanContext, new DefaultLogHandler());
    }

    DDSpan(long j, DDSpanContext dDSpanContext, LogHandler logHandler2) {
        this.durationNano = new AtomicLong();
        this.context = dDSpanContext;
        this.logHandler = logHandler2;
        if (j <= 0) {
            this.startTimeMicro = Clock.currentMicroTime();
            this.startTimeNano = dDSpanContext.getTrace().getCurrentTimeNano();
        } else {
            this.startTimeMicro = j;
            this.startTimeNano = 0;
        }
        dDSpanContext.getTrace().registerSpan(this);
    }

    public boolean isFinished() {
        return this.durationNano.get() != 0;
    }

    private void finishAndAddToTrace(long j) {
        if (this.durationNano.compareAndSet(0, Math.max(1, j))) {
            this.context.getTrace().addSpan(this);
        }
    }

    public final void finish() {
        if (this.startTimeNano > 0) {
            finishAndAddToTrace(this.context.getTrace().getCurrentTimeNano() - this.startTimeNano);
        } else {
            finish(Clock.currentMicroTime());
        }
    }

    public final void finish(long j) {
        finishAndAddToTrace(TimeUnit.MICROSECONDS.toNanos(j - this.startTimeMicro));
    }

    public DDSpan setError(boolean z) {
        this.context.setErrorFlag(z);
        return this;
    }

    public final void drop() {
        this.context.getTrace().dropSpan(this);
    }

    public final boolean isRootSpan() {
        return BigInteger.ZERO.equals(this.context.getParentId());
    }

    @Deprecated
    public MutableSpan getRootSpan() {
        return getLocalRootSpan();
    }

    public MutableSpan getLocalRootSpan() {
        return context().getTrace().getRootSpan();
    }

    public void setErrorMeta(Throwable th) {
        setError(true);
        setTag(DDTags.ERROR_MSG, th.getMessage());
        setTag(DDTags.ERROR_TYPE, th.getClass().getName());
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        setTag("error.stack", stringWriter.toString());
    }

    public final DDSpan setTag(String str, String str2) {
        context().setTag(str, str2);
        return this;
    }

    public final DDSpan setTag(String str, boolean z) {
        context().setTag(str, Boolean.valueOf(z));
        return this;
    }

    public final DDSpan setTag(String str, Number number) {
        context().setTag(str, number);
        return this;
    }

    public <T> Span setTag(Tag<T> tag, T t) {
        context().setTag(tag.getKey(), t);
        return this;
    }

    public final DDSpanContext context() {
        return this.context;
    }

    public final String getBaggageItem(String str) {
        return this.context.getBaggageItem(str);
    }

    public final DDSpan setBaggageItem(String str, String str2) {
        this.context.setBaggageItem(str, str2);
        return this;
    }

    public final DDSpan setOperationName(String str) {
        context().setOperationName(str);
        return this;
    }

    public final DDSpan log(Map<String, ?> map) {
        this.logHandler.log(map, this);
        return this;
    }

    public final DDSpan log(long j, Map<String, ?> map) {
        this.logHandler.log(j, map, this);
        return this;
    }

    public final DDSpan log(String str) {
        this.logHandler.log(str, this);
        return this;
    }

    public final DDSpan log(long j, String str) {
        this.logHandler.log(j, str, this);
        return this;
    }

    public final DDSpan setServiceName(String str) {
        context().setServiceName(str);
        return this;
    }

    public final DDSpan setResourceName(String str) {
        context().setResourceName(str);
        return this;
    }

    public final DDSpan setSamplingPriority(int i) {
        context().setSamplingPriority(i);
        return this;
    }

    public final DDSpan setSpanType(String str) {
        context().setSpanType(str);
        return this;
    }

    public Map<String, String> getMeta() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : context().getBaggageItems().entrySet()) {
            hashMap.put((String) next.getKey(), (String) next.getValue());
        }
        for (Map.Entry next2 : getTags().entrySet()) {
            hashMap.put((String) next2.getKey(), String.valueOf(next2.getValue()));
        }
        return hashMap;
    }

    public Map<String, Number> getMetrics() {
        return this.context.getMetrics();
    }

    public long getStartTime() {
        long j = this.startTimeNano;
        return j > 0 ? j : TimeUnit.MICROSECONDS.toNanos(this.startTimeMicro);
    }

    public long getDurationNano() {
        return this.durationNano.get();
    }

    public String getServiceName() {
        return this.context.getServiceName();
    }

    public BigInteger getTraceId() {
        return this.context.getTraceId();
    }

    public BigInteger getSpanId() {
        return this.context.getSpanId();
    }

    public BigInteger getParentId() {
        return this.context.getParentId();
    }

    public String getResourceName() {
        return this.context.getResourceName();
    }

    public String getOperationName() {
        return this.context.getOperationName();
    }

    public Integer getSamplingPriority() {
        int samplingPriority = this.context.getSamplingPriority();
        if (samplingPriority == Integer.MIN_VALUE) {
            return null;
        }
        return Integer.valueOf(samplingPriority);
    }

    public String getSpanType() {
        return this.context.getSpanType();
    }

    public Map<String, Object> getTags() {
        return context().getTags();
    }

    public String getType() {
        return this.context.getSpanType();
    }

    public Boolean isError() {
        return Boolean.valueOf(this.context.getErrorFlag());
    }

    public int getError() {
        return this.context.getErrorFlag() ? 1 : 0;
    }

    public String toString() {
        return this.context.toString() + ", duration_ns=" + this.durationNano;
    }
}
