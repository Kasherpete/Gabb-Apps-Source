package com.datadog.opentracing.decorators;

import com.datadog.opentracing.DDSpanContext;

public class ServiceNameDecorator extends AbstractDecorator {
    private final boolean setTag;

    public ServiceNameDecorator() {
        this("service.name", false);
    }

    public ServiceNameDecorator(String str, boolean z) {
        this.setTag = z;
        setMatchingTag(str);
    }

    public boolean shouldSetTag(DDSpanContext dDSpanContext, String str, Object obj) {
        dDSpanContext.setServiceName(String.valueOf(obj));
        return this.setTag;
    }
}
