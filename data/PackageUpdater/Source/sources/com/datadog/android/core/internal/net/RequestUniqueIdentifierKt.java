package com.datadog.android.core.internal.net;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.Util;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, mo20735d2 = {"identifyRequest", "", "request", "Lokhttp3/Request;", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RequestUniqueIdentifier.kt */
public final class RequestUniqueIdentifierKt {
    public static final String identifyRequest(Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        String method = request.method();
        HttpUrl url = request.url();
        RequestBody body = request.body();
        if (body == null || Intrinsics.areEqual((Object) body, (Object) Util.EMPTY_REQUEST)) {
            return method + "•" + url;
        }
        long contentLength = body.contentLength();
        return method + "•" + url + "•" + contentLength + "•" + body.contentType();
    }
}
