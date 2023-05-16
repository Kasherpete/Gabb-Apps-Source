package com.datadog.opentracing.propagation;

import java.util.Collections;
import java.util.Map;
import p006io.opentracing.SpanContext;

public class TagContext implements SpanContext {
    private final String origin;
    private final Map<String, String> tags;

    public String toSpanId() {
        return "";
    }

    public String toTraceId() {
        return "";
    }

    public TagContext(String str, Map<String, String> map) {
        this.origin = str;
        this.tags = map;
    }

    public String getOrigin() {
        return this.origin;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public Iterable<Map.Entry<String, String>> baggageItems() {
        return Collections.emptyList();
    }
}
