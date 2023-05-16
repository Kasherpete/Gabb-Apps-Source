package com.gabb.packageupdater.domain.packagemanagement;

import android.content.pm.PackageManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class VersionChecker_Factory implements Factory<VersionChecker> {
    private final Provider<PackageManager> packageManagerProvider;

    public VersionChecker_Factory(Provider<PackageManager> provider) {
        this.packageManagerProvider = provider;
    }

    public VersionChecker get() {
        return newInstance(this.packageManagerProvider.get());
    }

    public static VersionChecker_Factory create(Provider<PackageManager> provider) {
        return new VersionChecker_Factory(provider);
    }

    public static VersionChecker newInstance(PackageManager packageManager) {
        return new VersionChecker(packageManager);
    }
}
