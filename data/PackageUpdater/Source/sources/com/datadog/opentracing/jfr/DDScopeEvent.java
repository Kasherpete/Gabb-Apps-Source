package com.datadog.opentracing.jfr;

public interface DDScopeEvent {
    void finish();

    void start();
}
