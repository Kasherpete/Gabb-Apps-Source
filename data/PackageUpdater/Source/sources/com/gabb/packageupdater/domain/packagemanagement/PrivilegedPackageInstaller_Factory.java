package com.gabb.packageupdater.domain.packagemanagement;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PrivilegedPackageInstaller_Factory implements Factory<PrivilegedPackageInstaller> {
    private final Provider<Context> contextProvider;

    public PrivilegedPackageInstaller_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public PrivilegedPackageInstaller get() {
        return newInstance(this.contextProvider.get());
    }

    public static PrivilegedPackageInstaller_Factory create(Provider<Context> provider) {
        return new PrivilegedPackageInstaller_Factory(provider);
    }

    public static PrivilegedPackageInstaller newInstance(Context context) {
        return new PrivilegedPackageInstaller(context);
    }
}
