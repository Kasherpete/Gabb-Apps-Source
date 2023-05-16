package com.datadog.opentracing.jfr;

import com.datadog.opentracing.DDSpanContext;

public final class DDNoopScopeEventFactory implements DDScopeEventFactory {
    public DDScopeEvent create(DDSpanContext dDSpanContext) {
        return DDNoopScopeEvent.INSTANCE;
    }
}
