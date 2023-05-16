package com.gabb.packageupdater.p005di;

import dagger.internal.Factory;
import okhttp3.logging.HttpLoggingInterceptor;

/* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideHttpLoggingInterceptorFactory */
public final class NetworkModule_ProvideHttpLoggingInterceptorFactory implements Factory<HttpLoggingInterceptor> {
    public HttpLoggingInterceptor get() {
        return provideHttpLoggingInterceptor();
    }

    public static NetworkModule_ProvideHttpLoggingInterceptorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return NetworkModule.INSTANCE.provideHttpLoggingInterceptor();
    }

    /* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideHttpLoggingInterceptorFactory$InstanceHolder */
    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final NetworkModule_ProvideHttpLoggingInterceptorFactory INSTANCE = new NetworkModule_ProvideHttpLoggingInterceptorFactory();

        private InstanceHolder() {
        }
    }
}
