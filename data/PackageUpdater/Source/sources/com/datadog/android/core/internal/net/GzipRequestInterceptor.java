package com.datadog.android.core.internal.net;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/GzipRequestInterceptor;", "Lokhttp3/Interceptor;", "()V", "gzip", "Lokhttp3/RequestBody;", "body", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: GzipRequestInterceptor.kt */
public final class GzipRequestInterceptor implements Interceptor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String ENCODING_GZIP = "gzip";
    private static final String HEADER_ENCODING = "Content-Encoding";

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Intrinsics.checkNotNullExpressionValue(request, "chain.request()");
        RequestBody body = request.body();
        if (body == null || request.header(HEADER_ENCODING) != null) {
            Response proceed = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(proceed, "{\n            chain.proc…riginalRequest)\n        }");
            return proceed;
        }
        try {
            request = request.newBuilder().header(HEADER_ENCODING, ENCODING_GZIP).method(request.method(), gzip(body)).build();
        } catch (Exception e) {
            LogUtilsKt.warningWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Unable to gzip request body", e, (Map) null, 4, (Object) null);
        }
        Response proceed2 = chain.proceed(request);
        Intrinsics.checkNotNullExpressionValue(proceed2, "{\n            val compre…pressedRequest)\n        }");
        return proceed2;
    }

    private final RequestBody gzip(RequestBody requestBody) {
        return new GzipRequestInterceptor$gzip$1(requestBody);
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/GzipRequestInterceptor$Companion;", "", "()V", "ENCODING_GZIP", "", "HEADER_ENCODING", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: GzipRequestInterceptor.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
