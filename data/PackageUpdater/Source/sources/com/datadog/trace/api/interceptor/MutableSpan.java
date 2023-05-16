package com.datadog.trace.api.interceptor;

import java.util.Map;

public interface MutableSpan {
    void drop();

    long getDurationNano();

    MutableSpan getLocalRootSpan();

    String getOperationName();

    String getResourceName();

    @Deprecated
    MutableSpan getRootSpan();

    Integer getSamplingPriority();

    String getServiceName();

    String getSpanType();

    long getStartTime();

    Map<String, Object> getTags();

    Boolean isError();

    MutableSpan setError(boolean z);

    MutableSpan setOperationName(String str);

    MutableSpan setResourceName(String str);

    @Deprecated
    MutableSpan setSamplingPriority(int i);

    MutableSpan setServiceName(String str);

    MutableSpan setSpanType(String str);

    MutableSpan setTag(String str, Number number);

    MutableSpan setTag(String str, String str2);

    MutableSpan setTag(String str, boolean z);
}
