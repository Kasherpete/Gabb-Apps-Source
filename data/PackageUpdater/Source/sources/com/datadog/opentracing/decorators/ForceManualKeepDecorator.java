package com.datadog.opentracing.decorators;

import com.datadog.opentracing.DDSpanContext;
import com.datadog.trace.api.DDTags;

public class ForceManualKeepDecorator extends AbstractDecorator {
    public ForceManualKeepDecorator() {
        setMatchingTag(DDTags.MANUAL_KEEP);
    }

    public boolean shouldSetTag(DDSpanContext dDSpanContext, String str, Object obj) {
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            dDSpanContext.setSamplingPriority(2);
            return false;
        } else if (!(obj instanceof String) || !Boolean.parseBoolean((String) obj)) {
            return false;
        } else {
            dDSpanContext.setSamplingPriority(2);
            return false;
        }
    }
}
