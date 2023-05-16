package com.datadog.opentracing.jfr;

public final class DDNoopScopeEvent implements DDScopeEvent {
    public static final DDNoopScopeEvent INSTANCE = new DDNoopScopeEvent();

    public void finish() {
    }

    public void start() {
    }
}
