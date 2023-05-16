package com.gabb.packageupdater.domain.packagemanagement;

import android.content.pm.PackageManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PackageStateModifier_Factory implements Factory<PackageStateModifier> {
    private final Provider<PackageManager> packageManagerProvider;

    public PackageStateModifier_Factory(Provider<PackageManager> provider) {
        this.packageManagerProvider = provider;
    }

    public PackageStateModifier get() {
        return newInstance(this.packageManagerProvider.get());
    }

    public static PackageStateModifier_Factory create(Provider<PackageManager> provider) {
        return new PackageStateModifier_Factory(provider);
    }

    public static PackageStateModifier newInstance(PackageManager packageManager) {
        return new PackageStateModifier(packageManager);
    }
}
