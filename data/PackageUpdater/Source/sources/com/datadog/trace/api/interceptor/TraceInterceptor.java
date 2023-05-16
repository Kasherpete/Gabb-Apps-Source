package com.datadog.trace.api.interceptor;

import java.util.Collection;

public interface TraceInterceptor {
    Collection<? extends MutableSpan> onTraceComplete(Collection<? extends MutableSpan> collection);

    int priority();
}
