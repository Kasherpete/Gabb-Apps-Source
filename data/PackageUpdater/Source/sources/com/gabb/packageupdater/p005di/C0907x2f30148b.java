package com.gabb.packageupdater.p005di;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.internal.http.StatusLine;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, mo20735d2 = {"<anonymous>", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "intercept", "okhttp3/OkHttpClient$Builder$addNetworkInterceptor$2"}, mo20736k = 3, mo20737mv = {1, 6, 0})
/* renamed from: com.gabb.packageupdater.di.NetworkModule$getDefaultOkHttpClientBuilder$lambda-3$$inlined$-addNetworkInterceptor$1 */
/* compiled from: OkHttpClient.kt */
public final class C0907x2f30148b implements Interceptor {
    public final Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response proceed = chain.proceed(chain.request());
        if (proceed.code() != 301) {
            return proceed;
        }
        Log.d("network", "replacing 301 with 308");
        return proceed.newBuilder().code(StatusLine.HTTP_PERM_REDIRECT).build();
    }
}
