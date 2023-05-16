package com.gabb.packageupdater.p005di;

import android.content.Context;
import android.util.Log;
import androidx.work.PeriodicWorkRequest;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.packageupdater.BuildConfig;
import com.gabb.packageupdater.network.interceptors.BaseUrlInterceptor;
import com.gabb.packageupdater.network.interceptors.VersionHeaderInterceptor;
import com.gabb.packageupdater.network.interfaces.FileDownloadApi;
import com.gabb.packageupdater.network.interfaces.StoreApi;
import com.gabb.services.tokens.TokenManager;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.test.TestBuildersKt;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.LoggingEventListener;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Metadata(mo20734d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\n\u0010\u0014\u001a\u0004\u0018\u00010\rH\u0007J\n\u0010\u0015\u001a\u0004\u0018\u00010\u000bH\u0007J&\u0010\u0016\u001a\u00020\u00172\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u0007J.\u0010\u0018\u001a\u00020\u00172\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0017H\u0007J\u0012\u0010\u001d\u001a\u00020\u00132\b\b\u0001\u0010\u001c\u001a\u00020\u0017H\u0007J\u0012\u0010\u001e\u001a\u00020\u001f2\b\b\u0001\u0010\u0012\u001a\u00020\u0013H\u0007J\u0012\u0010 \u001a\u00020!2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, mo20735d2 = {"Lcom/gabb/packageupdater/di/NetworkModule;", "", "()V", "TIMEOUT", "Ljava/time/Duration;", "kotlin.jvm.PlatformType", "TIMEOUT_CALL", "TIMEOUT_READ", "getDefaultOkHttpClientBuilder", "Lokhttp3/OkHttpClient$Builder;", "httpLoggingInterceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "loggingEventListener", "Lokhttp3/logging/LoggingEventListener$Factory;", "context", "Landroid/content/Context;", "provideFileDownloadApi", "Lcom/gabb/packageupdater/network/interfaces/FileDownloadApi;", "retrofit", "Lretrofit2/Retrofit;", "provideHttpEventListener", "provideHttpLoggingInterceptor", "provideOkHttpClient", "Lokhttp3/OkHttpClient;", "provideOkHttpClientBaseUrlChangeable", "baseUrlInterceptor", "Lcom/gabb/packageupdater/network/interceptors/BaseUrlInterceptor;", "provideRetrofit", "okHttpClient", "provideRetrofitBaseUrlChangeable", "provideStoreApi", "Lcom/gabb/packageupdater/network/interfaces/StoreApi;", "provideTokenManager", "Lcom/gabb/services/tokens/TokenManager;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Module
/* renamed from: com.gabb.packageupdater.di.NetworkModule */
/* compiled from: NetworkModule.kt */
public final class NetworkModule {
    public static final NetworkModule INSTANCE = new NetworkModule();
    private static final Duration TIMEOUT = Duration.ofMillis(TestBuildersKt.DEFAULT_DISPATCH_TIMEOUT_MS);
    private static final Duration TIMEOUT_CALL = Duration.ofMillis(PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
    private static final Duration TIMEOUT_READ = Duration.ofMillis(150000);

    @Singleton
    @Provides
    public final LoggingEventListener.Factory provideHttpEventListener() {
        return null;
    }

    private NetworkModule() {
    }

    @BaseUrlChangeable
    @Singleton
    @Provides
    public final Retrofit provideRetrofitBaseUrlChangeable(@BaseUrlChangeable OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Retrofit build = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n        .baseU…pClient)\n        .build()");
        return build;
    }

    @Singleton
    @Provides
    public final Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Retrofit build = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n        .baseU…pClient)\n        .build()");
        return build;
    }

    @Singleton
    @Provides
    public final HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        Log.d("network", "Adding interceptor");
        return null;
    }

    @Singleton
    @Provides
    public final TokenManager provideTokenManager(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        return new TokenManager(context);
    }

    @BaseUrlChangeable
    @Singleton
    @Provides
    public final OkHttpClient provideOkHttpClientBaseUrlChangeable(HttpLoggingInterceptor httpLoggingInterceptor, LoggingEventListener.Factory factory, BaseUrlInterceptor baseUrlInterceptor, @ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(baseUrlInterceptor, "baseUrlInterceptor");
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        OkHttpClient.Builder defaultOkHttpClientBuilder = getDefaultOkHttpClientBuilder(httpLoggingInterceptor, factory, context);
        defaultOkHttpClientBuilder.addInterceptor(baseUrlInterceptor);
        return defaultOkHttpClientBuilder.build();
    }

    @Singleton
    @Provides
    public final OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, LoggingEventListener.Factory factory, @ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        return getDefaultOkHttpClientBuilder(httpLoggingInterceptor, factory, context).build();
    }

    @Singleton
    @Provides
    public final FileDownloadApi provideFileDownloadApi(Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object create = retrofit.create(FileDownloadApi.class);
        Intrinsics.checkNotNullExpressionValue(create, "retrofit.create(FileDownloadApi::class.java)");
        return (FileDownloadApi) create;
    }

    @Singleton
    @Provides
    public final StoreApi provideStoreApi(@BaseUrlChangeable Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object create = retrofit.create(StoreApi.class);
        Intrinsics.checkNotNullExpressionValue(create, "retrofit.create(StoreApi::class.java)");
        return (StoreApi) create;
    }

    private final OkHttpClient.Builder getDefaultOkHttpClientBuilder(HttpLoggingInterceptor httpLoggingInterceptor, LoggingEventListener.Factory factory, @ApplicationContext Context context) {
        OkHttpClient.Builder followSslRedirects = new OkHttpClient.Builder().followRedirects(true).followSslRedirects(true);
        Duration duration = TIMEOUT_CALL;
        Intrinsics.checkNotNullExpressionValue(duration, "TIMEOUT_CALL");
        OkHttpClient.Builder callTimeout = followSslRedirects.callTimeout(duration);
        Duration duration2 = TIMEOUT;
        Intrinsics.checkNotNullExpressionValue(duration2, "TIMEOUT");
        OkHttpClient.Builder connectTimeout = callTimeout.connectTimeout(duration2);
        Duration duration3 = TIMEOUT_READ;
        Intrinsics.checkNotNullExpressionValue(duration3, "TIMEOUT_READ");
        OkHttpClient.Builder connectionPool = connectTimeout.readTimeout(duration3).connectionPool(new ConnectionPool(10, 2, TimeUnit.MINUTES));
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(15);
        OkHttpClient.Builder dispatcher2 = connectionPool.dispatcher(dispatcher);
        Log.d("network", "Adding interceptor");
        if (httpLoggingInterceptor != null) {
            dispatcher2.addInterceptor(httpLoggingInterceptor);
        }
        if (factory != null) {
            dispatcher2.eventListenerFactory(factory);
        }
        dispatcher2.addInterceptor(new VersionHeaderInterceptor());
        dispatcher2.addNetworkInterceptor(new C0907x2f30148b());
        return dispatcher2;
    }
}
