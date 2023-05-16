package com.gabb.packageupdater.p005di;

import com.gabb.base.certs.CertificateManager;
import com.gabb.packageupdater.util.PackageCertificateManager;
import dagger.Binds;
import dagger.Module;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0007"}, mo20735d2 = {"Lcom/gabb/packageupdater/di/AppModuleBinds;", "", "()V", "providesCertificateManager", "Lcom/gabb/base/certs/CertificateManager;", "bind", "Lcom/gabb/packageupdater/util/PackageCertificateManager;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Module
/* renamed from: com.gabb.packageupdater.di.AppModuleBinds */
/* compiled from: AppModuleBinds.kt */
public abstract class AppModuleBinds {
    @Binds
    public abstract CertificateManager providesCertificateManager(PackageCertificateManager packageCertificateManager);
}
