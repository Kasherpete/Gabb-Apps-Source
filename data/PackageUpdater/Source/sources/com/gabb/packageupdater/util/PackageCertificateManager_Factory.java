package com.gabb.packageupdater.util;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PackageCertificateManager_Factory implements Factory<PackageCertificateManager> {
    private final Provider<ContentResolver> contentResolverProvider;
    private final Provider<PackageManager> packageManagerProvider;

    public PackageCertificateManager_Factory(Provider<PackageManager> provider, Provider<ContentResolver> provider2) {
        this.packageManagerProvider = provider;
        this.contentResolverProvider = provider2;
    }

    public PackageCertificateManager get() {
        return newInstance(this.packageManagerProvider.get(), this.contentResolverProvider.get());
    }

    public static PackageCertificateManager_Factory create(Provider<PackageManager> provider, Provider<ContentResolver> provider2) {
        return new PackageCertificateManager_Factory(provider, provider2);
    }

    public static PackageCertificateManager newInstance(PackageManager packageManager, ContentResolver contentResolver) {
        return new PackageCertificateManager(packageManager, contentResolver);
    }
}
