package com.gabb.packageupdater.p005di;

import android.content.Context;
import com.gabb.packageupdater.repository.ApkCache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.DomainModule_ProvideApkCacheFactory */
public final class DomainModule_ProvideApkCacheFactory implements Factory<ApkCache> {
    private final Provider<Context> contextProvider;

    public DomainModule_ProvideApkCacheFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public ApkCache get() {
        return provideApkCache(this.contextProvider.get());
    }

    public static DomainModule_ProvideApkCacheFactory create(Provider<Context> provider) {
        return new DomainModule_ProvideApkCacheFactory(provider);
    }

    public static ApkCache provideApkCache(Context context) {
        return (ApkCache) Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideApkCache(context));
    }
}
