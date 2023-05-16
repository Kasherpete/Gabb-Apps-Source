package com.gabb.packageupdater.domain.packagemanagement;

import com.gabb.base.certs.CertificateManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PackageHandler_Factory implements Factory<PackageHandler> {
    private final Provider<CertificateManager> certsManagerProvider;
    private final Provider<PackageInstaller> installerProvider;
    private final Provider<PackageStateModifier> stateModifierProvider;
    private final Provider<PackageRemover> uninstallerProvider;
    private final Provider<VersionChecker> versionCheckerProvider;

    public PackageHandler_Factory(Provider<PackageInstaller> provider, Provider<PackageRemover> provider2, Provider<PackageStateModifier> provider3, Provider<CertificateManager> provider4, Provider<VersionChecker> provider5) {
        this.installerProvider = provider;
        this.uninstallerProvider = provider2;
        this.stateModifierProvider = provider3;
        this.certsManagerProvider = provider4;
        this.versionCheckerProvider = provider5;
    }

    public PackageHandler get() {
        return newInstance(this.installerProvider.get(), this.uninstallerProvider.get(), this.stateModifierProvider.get(), this.certsManagerProvider.get(), this.versionCheckerProvider.get());
    }

    public static PackageHandler_Factory create(Provider<PackageInstaller> provider, Provider<PackageRemover> provider2, Provider<PackageStateModifier> provider3, Provider<CertificateManager> provider4, Provider<VersionChecker> provider5) {
        return new PackageHandler_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static PackageHandler newInstance(PackageInstaller packageInstaller, PackageRemover packageRemover, PackageStateModifier packageStateModifier, CertificateManager certificateManager, VersionChecker versionChecker) {
        return new PackageHandler(packageInstaller, packageRemover, packageStateModifier, certificateManager, versionChecker);
    }
}
