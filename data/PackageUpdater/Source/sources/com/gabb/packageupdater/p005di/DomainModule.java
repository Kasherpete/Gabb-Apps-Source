package com.gabb.packageupdater.p005di;

import android.content.Context;
import android.content.pm.PackageManager;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.packageupdater.domain.packagemanagement.PackageStateModifier;
import com.gabb.packageupdater.domain.packagemanagement.VersionChecker;
import com.gabb.packageupdater.model.UpdatedApps;
import com.gabb.packageupdater.repository.ApkCache;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\r"}, mo20735d2 = {"Lcom/gabb/packageupdater/di/DomainModule;", "", "()V", "provideApkCache", "Lcom/gabb/packageupdater/repository/ApkCache;", "context", "Landroid/content/Context;", "providePackageStateModifier", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageStateModifier;", "provideUpdatedApps", "Lcom/gabb/packageupdater/model/UpdatedApps;", "provideVersionChecker", "Lcom/gabb/packageupdater/domain/packagemanagement/VersionChecker;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Module
/* renamed from: com.gabb.packageupdater.di.DomainModule */
/* compiled from: DomainModule.kt */
public final class DomainModule {
    public static final DomainModule INSTANCE = new DomainModule();

    private DomainModule() {
    }

    @Singleton
    @Provides
    public final PackageStateModifier providePackageStateModifier(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        return new PackageStateModifier(packageManager);
    }

    @Singleton
    @Provides
    public final VersionChecker provideVersionChecker(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        return new VersionChecker(packageManager);
    }

    @Singleton
    @Provides
    public final ApkCache provideApkCache(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        return new ApkCache(context);
    }

    @Singleton
    @Provides
    public final UpdatedApps provideUpdatedApps() {
        return new UpdatedApps();
    }
}
