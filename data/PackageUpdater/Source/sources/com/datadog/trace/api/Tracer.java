package com.datadog.trace.api;

import com.datadog.trace.api.interceptor.TraceInterceptor;
import com.datadog.trace.context.ScopeListener;

public interface Tracer {
    void addScopeListener(ScopeListener scopeListener);

    boolean addTraceInterceptor(TraceInterceptor traceInterceptor);

    String getSpanId();

    String getTraceId();
}
