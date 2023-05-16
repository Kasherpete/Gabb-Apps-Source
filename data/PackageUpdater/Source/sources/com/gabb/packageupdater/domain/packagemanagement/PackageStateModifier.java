package com.gabb.packageupdater.domain.packagemanagement;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.gabb.data.entities.App;
import com.gabb.packageupdater.BuildConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/packagemanagement/PackageStateModifier;", "", "packageManager", "Landroid/content/pm/PackageManager;", "(Landroid/content/pm/PackageManager;)V", "getDisabledUserApps", "", "Landroid/content/pm/ApplicationInfo;", "getUserApps", "needsChangeState", "", "app", "Lcom/gabb/data/entities/App;", "updatePackageState", "", "packages", "Companion", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PackageStateModifier.kt */
public final class PackageStateModifier {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final List<String> exceptionList = CollectionsKt.listOf(BuildConfig.APPLICATION_ID, PrivilegedPackageRemover.PACKAGE_NAME, "com.samsung.android.knox.efota.plugin", "com.gabb.mygabb", "com.gabb.music");
    private final PackageManager packageManager;

    @Inject
    public PackageStateModifier(PackageManager packageManager2) {
        Intrinsics.checkNotNullParameter(packageManager2, "packageManager");
        this.packageManager = packageManager2;
    }

    public final void updatePackageState(List<App> list) {
        Intrinsics.checkNotNullParameter(list, "packages");
        for (App updatePackageState : list) {
            updatePackageState(updatePackageState);
        }
    }

    public final void updatePackageState(App app) {
        Intrinsics.checkNotNullParameter(app, "app");
        Log.d("state modifier", "changing package (" + app.getPackageName() + ") to " + app.getEnabled());
        this.packageManager.setApplicationEnabledSetting(app.getPackageName(), app.getEnabled() ? 1 : 2, 1);
    }

    public final boolean needsChangeState(App app) {
        Intrinsics.checkNotNullParameter(app, "app");
        try {
            if (!exceptionList.contains(app.getPackageName()) && this.packageManager.getApplicationInfo(app.getPackageName(), 0).enabled != app.getEnabled()) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public final List<ApplicationInfo> getUserApps() {
        boolean z;
        List<ApplicationInfo> installedApplications = this.packageManager.getInstalledApplications(1048576);
        Intrinsics.checkNotNullExpressionValue(installedApplications, "packageManager.getInstal…anager.MATCH_SYSTEM_ONLY)");
        List<ApplicationInfo> installedApplications2 = this.packageManager.getInstalledApplications(0);
        Intrinsics.checkNotNullExpressionValue(installedApplications2, "packageManager.getInstalledApplications(0)");
        Collection arrayList = new ArrayList();
        for (Object next : installedApplications2) {
            ApplicationInfo applicationInfo = (ApplicationInfo) next;
            Iterable iterable = installedApplications;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (Intrinsics.areEqual((Object) ((ApplicationInfo) it.next()).packageName, (Object) applicationInfo.packageName)) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            z = false;
            if (true ^ z) {
                arrayList.add(next);
            }
        }
        Collection arrayList2 = new ArrayList();
        for (Object next2 : (List) arrayList) {
            if (!exceptionList.contains(((ApplicationInfo) next2).packageName)) {
                arrayList2.add(next2);
            }
        }
        return (List) arrayList2;
    }

    public final List<ApplicationInfo> getDisabledUserApps() {
        Collection arrayList = new ArrayList();
        for (Object next : getUserApps()) {
            if (!((ApplicationInfo) next).enabled) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    @Metadata(mo20734d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/packagemanagement/PackageStateModifier$Companion;", "", "()V", "exceptionList", "", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: PackageStateModifier.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
