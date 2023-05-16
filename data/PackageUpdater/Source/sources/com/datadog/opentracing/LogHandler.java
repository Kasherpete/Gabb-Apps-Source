package com.datadog.opentracing;

import java.util.Map;

public interface LogHandler {
    void log(long j, String str, DDSpan dDSpan);

    void log(long j, Map<String, ?> map, DDSpan dDSpan);

    void log(String str, DDSpan dDSpan);

    void log(Map<String, ?> map, DDSpan dDSpan);
}
