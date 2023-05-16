package com.gabb.packageupdater.p005di;

import dagger.internal.Factory;
import okhttp3.logging.LoggingEventListener;

/* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideHttpEventListenerFactory */
public final class NetworkModule_ProvideHttpEventListenerFactory implements Factory<LoggingEventListener.Factory> {
    public LoggingEventListener.Factory get() {
        return provideHttpEventListener();
    }

    public static NetworkModule_ProvideHttpEventListenerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LoggingEventListener.Factory provideHttpEventListener() {
        return NetworkModule.INSTANCE.provideHttpEventListener();
    }

    /* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideHttpEventListenerFactory$InstanceHolder */
    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final NetworkModule_ProvideHttpEventListenerFactory INSTANCE = new NetworkModule_ProvideHttpEventListenerFactory();

        private InstanceHolder() {
        }
    }
}
