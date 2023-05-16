package com.datadog.opentracing.decorators;

import com.datadog.opentracing.DDSpanContext;
import p006io.opentracing.tag.Tags;

public class PeerServiceDecorator extends AbstractDecorator {
    public PeerServiceDecorator() {
        setMatchingTag(Tags.PEER_SERVICE.getKey());
    }

    public boolean shouldSetTag(DDSpanContext dDSpanContext, String str, Object obj) {
        dDSpanContext.setServiceName(String.valueOf(obj));
        return false;
    }
}
