package com.datadog.opentracing.decorators;

import com.datadog.opentracing.DDSpanContext;
import com.datadog.trace.api.DDSpanTypes;
import p006io.opentracing.tag.Tags;

@Deprecated
public class DBTypeDecorator extends AbstractDecorator {
    public DBTypeDecorator() {
        setMatchingTag(Tags.DB_TYPE.getKey());
        setReplacementTag("service.name");
    }

    public boolean shouldSetTag(DDSpanContext dDSpanContext, String str, Object obj) {
        if (!super.shouldSetTag(dDSpanContext, str, obj) && !"couchbase".equals(obj) && !DDSpanTypes.ELASTICSEARCH.equals(obj)) {
            if ("mongo".equals(obj)) {
                dDSpanContext.setSpanType(DDSpanTypes.MONGO);
            } else if (DDSpanTypes.CASSANDRA.equals(obj)) {
                dDSpanContext.setSpanType(DDSpanTypes.CASSANDRA);
            } else if (DDSpanTypes.MEMCACHED.equals(obj)) {
                dDSpanContext.setSpanType(DDSpanTypes.MEMCACHED);
            } else {
                dDSpanContext.setSpanType(DDSpanTypes.SQL);
            }
            dDSpanContext.setOperationName(String.valueOf(obj) + ".query");
        }
        return true;
    }
}
