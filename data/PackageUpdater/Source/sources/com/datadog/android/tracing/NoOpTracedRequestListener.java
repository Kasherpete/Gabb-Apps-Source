package com.datadog.android.tracing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;
import okhttp3.Response;
import p006io.opentracing.Span;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/tracing/NoOpTracedRequestListener;", "Lcom/datadog/android/tracing/TracedRequestListener;", "()V", "onRequestIntercepted", "", "request", "Lokhttp3/Request;", "span", "Lio/opentracing/Span;", "response", "Lokhttp3/Response;", "throwable", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpTracedRequestListener.kt */
public final class NoOpTracedRequestListener implements TracedRequestListener {
    public void onRequestIntercepted(Request request, Span span, Response response, Throwable th) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(span, "span");
    }
}
