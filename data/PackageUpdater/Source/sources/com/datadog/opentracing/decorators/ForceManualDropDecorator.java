package com.datadog.opentracing.decorators;

import com.datadog.opentracing.DDSpanContext;
import com.datadog.trace.api.DDTags;

public class ForceManualDropDecorator extends AbstractDecorator {
    public ForceManualDropDecorator() {
        setMatchingTag(DDTags.MANUAL_DROP);
    }

    public boolean shouldSetTag(DDSpanContext dDSpanContext, String str, Object obj) {
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            dDSpanContext.setSamplingPriority(-1);
            return false;
        } else if (!(obj instanceof String) || !Boolean.parseBoolean((String) obj)) {
            return false;
        } else {
            dDSpanContext.setSamplingPriority(-1);
            return false;
        }
    }
}
