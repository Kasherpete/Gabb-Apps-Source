package com.datadog.android.core.internal.net;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import okio.Sink;

@Metadata(mo20734d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, mo20735d2 = {"com/datadog/android/core/internal/net/GzipRequestInterceptor$gzip$1", "Lokhttp3/RequestBody;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "writeTo", "", "sink", "Lokio/BufferedSink;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: GzipRequestInterceptor.kt */
public final class GzipRequestInterceptor$gzip$1 extends RequestBody {
    final /* synthetic */ RequestBody $body;

    public long contentLength() {
        return -1;
    }

    GzipRequestInterceptor$gzip$1(RequestBody requestBody) {
        this.$body = requestBody;
    }

    public MediaType contentType() {
        return this.$body.contentType();
    }

    public void writeTo(BufferedSink bufferedSink) {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        BufferedSink buffer = Okio.buffer((Sink) new GzipSink(bufferedSink));
        Intrinsics.checkNotNullExpressionValue(buffer, "buffer(GzipSink(sink))");
        this.$body.writeTo(buffer);
        buffer.close();
    }
}
