package com.gabb.packageupdater.p005di;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.LoggingEventListener;

/* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideOkHttpClientFactory */
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
    private final Provider<Context> contextProvider;
    private final Provider<HttpLoggingInterceptor> httpLoggingInterceptorProvider;
    private final Provider<LoggingEventListener.Factory> loggingEventListenerProvider;

    public NetworkModule_ProvideOkHttpClientFactory(Provider<HttpLoggingInterceptor> provider, Provider<LoggingEventListener.Factory> provider2, Provider<Context> provider3) {
        this.httpLoggingInterceptorProvider = provider;
        this.loggingEventListenerProvider = provider2;
        this.contextProvider = provider3;
    }

    public OkHttpClient get() {
        return provideOkHttpClient(this.httpLoggingInterceptorProvider.get(), this.loggingEventListenerProvider.get(), this.contextProvider.get());
    }

    public static NetworkModule_ProvideOkHttpClientFactory create(Provider<HttpLoggingInterceptor> provider, Provider<LoggingEventListener.Factory> provider2, Provider<Context> provider3) {
        return new NetworkModule_ProvideOkHttpClientFactory(provider, provider2, provider3);
    }

    public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, LoggingEventListener.Factory factory, Context context) {
        return (OkHttpClient) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClient(httpLoggingInterceptor, factory, context));
    }
}
