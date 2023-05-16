package com.datadog.android.tracing;

import kotlin.Metadata;
import okhttp3.Request;
import okhttp3.Response;
import p006io.opentracing.Span;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bg\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/tracing/TracedRequestListener;", "", "onRequestIntercepted", "", "request", "Lokhttp3/Request;", "span", "Lio/opentracing/Span;", "response", "Lokhttp3/Response;", "throwable", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TracedRequestListener.kt */
public interface TracedRequestListener {
    void onRequestIntercepted(Request request, Span span, Response response, Throwable th);
}
