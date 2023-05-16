package com.gabb.packageupdater.repository;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ApkCache_Factory implements Factory<ApkCache> {
    private final Provider<Context> contextProvider;

    public ApkCache_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public ApkCache get() {
        return newInstance(this.contextProvider.get());
    }

    public static ApkCache_Factory create(Provider<Context> provider) {
        return new ApkCache_Factory(provider);
    }

    public static ApkCache newInstance(Context context) {
        return new ApkCache(context);
    }
}
