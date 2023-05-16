package com.gabb.packageupdater.p005di;

import android.content.Context;
import com.gabb.packageupdater.network.interceptors.BaseUrlInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.LoggingEventListener;

/* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideOkHttpClientBaseUrlChangeableFactory */
public final class NetworkModule_ProvideOkHttpClientBaseUrlChangeableFactory implements Factory<OkHttpClient> {
    private final Provider<BaseUrlInterceptor> baseUrlInterceptorProvider;
    private final Provider<Context> contextProvider;
    private final Provider<HttpLoggingInterceptor> httpLoggingInterceptorProvider;
    private final Provider<LoggingEventListener.Factory> loggingEventListenerProvider;

    public NetworkModule_ProvideOkHttpClientBaseUrlChangeableFactory(Provider<HttpLoggingInterceptor> provider, Provider<LoggingEventListener.Factory> provider2, Provider<BaseUrlInterceptor> provider3, Provider<Context> provider4) {
        this.httpLoggingInterceptorProvider = provider;
        this.loggingEventListenerProvider = provider2;
        this.baseUrlInterceptorProvider = provider3;
        this.contextProvider = provider4;
    }

    public OkHttpClient get() {
        return provideOkHttpClientBaseUrlChangeable(this.httpLoggingInterceptorProvider.get(), this.loggingEventListenerProvider.get(), this.baseUrlInterceptorProvider.get(), this.contextProvider.get());
    }

    public static NetworkModule_ProvideOkHttpClientBaseUrlChangeableFactory create(Provider<HttpLoggingInterceptor> provider, Provider<LoggingEventListener.Factory> provider2, Provider<BaseUrlInterceptor> provider3, Provider<Context> provider4) {
        return new NetworkModule_ProvideOkHttpClientBaseUrlChangeableFactory(provider, provider2, provider3, provider4);
    }

    public static OkHttpClient provideOkHttpClientBaseUrlChangeable(HttpLoggingInterceptor httpLoggingInterceptor, LoggingEventListener.Factory factory, BaseUrlInterceptor baseUrlInterceptor, Context context) {
        return (OkHttpClient) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClientBaseUrlChangeable(httpLoggingInterceptor, factory, baseUrlInterceptor, context));
    }
}
