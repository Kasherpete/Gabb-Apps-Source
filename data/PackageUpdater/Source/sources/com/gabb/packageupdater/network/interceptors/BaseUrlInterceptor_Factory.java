package com.gabb.packageupdater.network.interceptors;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BaseUrlInterceptor_Factory implements Factory<BaseUrlInterceptor> {
    private final Provider<Context> contextProvider;

    public BaseUrlInterceptor_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public BaseUrlInterceptor get() {
        return newInstance(this.contextProvider.get());
    }

    public static BaseUrlInterceptor_Factory create(Provider<Context> provider) {
        return new BaseUrlInterceptor_Factory(provider);
    }

    public static BaseUrlInterceptor newInstance(Context context) {
        return new BaseUrlInterceptor(context);
    }
}
