package com.gabb.packageupdater.network.interceptors;

import android.content.Context;
import android.content.SharedPreferences;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.packageupdater.BuildConfig;
import com.gabb.packageupdater.receiver.SharedPrefsKey;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(mo20734d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, mo20735d2 = {"Lcom/gabb/packageupdater/network/interceptors/BaseUrlInterceptor;", "Lokhttp3/Interceptor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPrefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getSharedPrefs", "()Landroid/content/SharedPreferences;", "sharedPrefs$delegate", "Lkotlin/Lazy;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "isDefaultEnvironment", "", "updateBaseUrlToDev", "Lokhttp3/Request;", "url", "Lokhttp3/HttpUrl;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BaseUrlInterceptor.kt */
public final class BaseUrlInterceptor implements Interceptor {
    /* access modifiers changed from: private */
    public final Context context;
    private final Lazy sharedPrefs$delegate = LazyKt.lazy(new BaseUrlInterceptor$sharedPrefs$2(this));

    @Inject
    public BaseUrlInterceptor(@ApplicationContext Context context2) {
        Intrinsics.checkNotNullParameter(context2, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        this.context = context2;
    }

    private final SharedPreferences getSharedPrefs() {
        return (SharedPreferences) this.sharedPrefs$delegate.getValue();
    }

    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        if (isDefaultEnvironment()) {
            return chain.proceed(chain.request());
        }
        return chain.proceed(updateBaseUrlToDev(chain.request(), chain.request().url()));
    }

    private final boolean isDefaultEnvironment() {
        return getSharedPrefs().getString(SharedPrefsKey.ENVIRONMENT, (String) null) == null;
    }

    private final Request updateBaseUrlToDev(Request request, HttpUrl httpUrl) {
        HttpUrl.Builder newBuilder = httpUrl.newBuilder();
        newBuilder.host(BuildConfig.UPDATER_DEV_HOST);
        return request.newBuilder().url(newBuilder.build()).build();
    }
}
