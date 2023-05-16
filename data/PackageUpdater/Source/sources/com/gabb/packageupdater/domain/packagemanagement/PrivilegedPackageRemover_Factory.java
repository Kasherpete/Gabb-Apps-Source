package com.gabb.packageupdater.domain.packagemanagement;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PrivilegedPackageRemover_Factory implements Factory<PrivilegedPackageRemover> {
    private final Provider<Context> contextProvider;

    public PrivilegedPackageRemover_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public PrivilegedPackageRemover get() {
        return newInstance(this.contextProvider.get());
    }

    public static PrivilegedPackageRemover_Factory create(Provider<Context> provider) {
        return new PrivilegedPackageRemover_Factory(provider);
    }

    public static PrivilegedPackageRemover newInstance(Context context) {
        return new PrivilegedPackageRemover(context);
    }
}
