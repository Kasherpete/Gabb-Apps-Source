package com.datadog.opentracing.decorators;

import com.datadog.opentracing.DDSpanContext;
import com.datadog.trace.api.Config;

public class ServletContextDecorator extends AbstractDecorator {
    public ServletContextDecorator() {
        setMatchingTag("servlet.context");
    }

    public boolean shouldSetTag(DDSpanContext dDSpanContext, String str, Object obj) {
        String trim = String.valueOf(obj).trim();
        if (!trim.equals("/") && (dDSpanContext.getServiceName().equals(Config.DEFAULT_SERVICE_NAME) || dDSpanContext.getServiceName().isEmpty())) {
            if (trim.startsWith("/") && trim.length() > 1) {
                trim = trim.substring(1);
            }
            if (!trim.isEmpty()) {
                dDSpanContext.setServiceName(trim);
            }
        }
        return true;
    }
}
