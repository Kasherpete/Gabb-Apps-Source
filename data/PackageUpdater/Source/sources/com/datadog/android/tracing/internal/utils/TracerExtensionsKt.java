package com.datadog.android.tracing.internal.utils;

import com.datadog.opentracing.DDSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import p006io.opentracing.Span;
import p006io.opentracing.Tracer;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¨\u0006\u0004"}, mo20735d2 = {"spanId", "", "Lio/opentracing/Tracer;", "traceId", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TracerExtensions.kt */
public final class TracerExtensionsKt {
    public static final String traceId(Tracer tracer) {
        Intrinsics.checkNotNullParameter(tracer, "<this>");
        Span activeSpan = tracer.activeSpan();
        if (activeSpan instanceof DDSpan) {
            return ((DDSpan) activeSpan).getTraceId().toString();
        }
        return null;
    }

    public static final String spanId(Tracer tracer) {
        Intrinsics.checkNotNullParameter(tracer, "<this>");
        Span activeSpan = tracer.activeSpan();
        if (activeSpan instanceof DDSpan) {
            return ((DDSpan) activeSpan).getSpanId().toString();
        }
        return null;
    }
}
