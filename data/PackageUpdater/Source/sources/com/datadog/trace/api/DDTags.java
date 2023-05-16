package com.datadog.trace.api;

public class DDTags {
    public static final String ANALYTICS_SAMPLE_RATE = "_dd1.sr.eausr";
    public static final String DB_STATEMENT = "sql.query";
    public static final String ERROR_MSG = "error.msg";
    public static final String ERROR_STACK = "error.stack";
    public static final String ERROR_TYPE = "error.type";
    @Deprecated
    public static final String EVENT_SAMPLE_RATE = "_dd1.sr.eausr";
    public static final String HTTP_FRAGMENT = "http.fragment.string";
    public static final String HTTP_QUERY = "http.query.string";
    public static final String MANUAL_DROP = "manual.drop";
    public static final String MANUAL_KEEP = "manual.keep";
    public static final String RESOURCE_NAME = "resource.name";
    public static final String SERVICE_NAME = "service.name";
    public static final String SPAN_TYPE = "span.type";
    public static final String THREAD_ID = "thread.id";
    public static final String THREAD_NAME = "thread.name";
    public static final String USER_NAME = "user.principal";
}
