package com.datadog.opentracing.jfr;

import com.datadog.opentracing.DDSpanContext;

public interface DDScopeEventFactory {
    DDScopeEvent create(DDSpanContext dDSpanContext);
}
