package com.datadog.opentracing;

import com.datadog.trace.api.DDTags;
import java.util.Map;
import p006io.opentracing.log.Fields;

public class DefaultLogHandler implements LogHandler {
    public void log(long j, String str, DDSpan dDSpan) {
    }

    public void log(String str, DDSpan dDSpan) {
    }

    public void log(Map<String, ?> map, DDSpan dDSpan) {
        extractError(map, dDSpan);
    }

    public void log(long j, Map<String, ?> map, DDSpan dDSpan) {
        extractError(map, dDSpan);
    }

    private void extractError(Map<String, ?> map, DDSpan dDSpan) {
        if (map.get(Fields.ERROR_OBJECT) instanceof Throwable) {
            dDSpan.setErrorMeta((Throwable) map.get(Fields.ERROR_OBJECT));
        } else if (map.get("message") instanceof String) {
            dDSpan.setTag(DDTags.ERROR_MSG, (String) map.get("message"));
        }
    }
}
