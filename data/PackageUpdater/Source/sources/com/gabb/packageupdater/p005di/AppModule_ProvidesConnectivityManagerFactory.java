package com.gabb.packageupdater.p005di;

import android.content.Context;
import android.net.ConnectivityManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.AppModule_ProvidesConnectivityManagerFactory */
public final class AppModule_ProvidesConnectivityManagerFactory implements Factory<ConnectivityManager> {
    private final Provider<Context> contextProvider;

    public AppModule_ProvidesConnectivityManagerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public ConnectivityManager get() {
        return providesConnectivityManager(this.contextProvider.get());
    }

    public static AppModule_ProvidesConnectivityManagerFactory create(Provider<Context> provider) {
        return new AppModule_ProvidesConnectivityManagerFactory(provider);
    }

    public static ConnectivityManager providesConnectivityManager(Context context) {
        return (ConnectivityManager) Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providesConnectivityManager(context));
    }
}
