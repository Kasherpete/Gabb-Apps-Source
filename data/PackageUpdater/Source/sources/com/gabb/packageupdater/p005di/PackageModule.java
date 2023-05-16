package com.gabb.packageupdater.p005di;

import com.gabb.packageupdater.domain.packagemanagement.PackageInstaller;
import com.gabb.packageupdater.domain.packagemanagement.PackageRemover;
import com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller;
import com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageRemover;
import dagger.Binds;
import dagger.Module;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH'¨\u0006\u000b"}, mo20735d2 = {"Lcom/gabb/packageupdater/di/PackageModule;", "", "()V", "bindPackageInstaller", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageInstaller;", "packageInstallerImpl", "Lcom/gabb/packageupdater/domain/packagemanagement/PrivilegedPackageInstaller;", "bindPackageRemover", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageRemover;", "packageRemoverImpl", "Lcom/gabb/packageupdater/domain/packagemanagement/PrivilegedPackageRemover;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Module
/* renamed from: com.gabb.packageupdater.di.PackageModule */
/* compiled from: PackageModule.kt */
public abstract class PackageModule {
    @Binds
    public abstract PackageInstaller bindPackageInstaller(PrivilegedPackageInstaller privilegedPackageInstaller);

    @Binds
    public abstract PackageRemover bindPackageRemover(PrivilegedPackageRemover privilegedPackageRemover);
}
