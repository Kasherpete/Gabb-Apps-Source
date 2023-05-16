package com.gabb.packageupdater.p005di;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.AppModule_ProvidesPackageManagerFactory */
public final class AppModule_ProvidesPackageManagerFactory implements Factory<PackageManager> {
    private final Provider<Context> contextProvider;

    public AppModule_ProvidesPackageManagerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public PackageManager get() {
        return providesPackageManager(this.contextProvider.get());
    }

    public static AppModule_ProvidesPackageManagerFactory create(Provider<Context> provider) {
        return new AppModule_ProvidesPackageManagerFactory(provider);
    }

    public static PackageManager providesPackageManager(Context context) {
        return (PackageManager) Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providesPackageManager(context));
    }
}
