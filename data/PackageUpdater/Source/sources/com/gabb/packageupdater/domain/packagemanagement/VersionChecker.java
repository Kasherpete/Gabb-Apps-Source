package com.gabb.packageupdater.domain.packagemanagement;

import android.content.pm.PackageManager;
import android.util.Log;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/packagemanagement/VersionChecker;", "", "packageManager", "Landroid/content/pm/PackageManager;", "(Landroid/content/pm/PackageManager;)V", "isInstalled", "", "packageName", "", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageName;", "requiresNewVersion", "targetVersion", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: VersionChecker.kt */
public final class VersionChecker {
    private final PackageManager packageManager;

    @Inject
    public VersionChecker(PackageManager packageManager2) {
        Intrinsics.checkNotNullParameter(packageManager2, "packageManager");
        this.packageManager = packageManager2;
    }

    public final boolean isInstalled(String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(str, "packageName");
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m176constructorimpl(this.packageManager.getPackageInfo(str, 0));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m176constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m179exceptionOrNullimpl(obj) != null) {
            Log.d("updater", Intrinsics.stringPlus(str, " is not installed"));
        }
        if (Result.m182isFailureimpl(obj)) {
            obj = null;
        }
        if (obj != null) {
            return true;
        }
        return false;
    }

    public final boolean requiresNewVersion(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        try {
            long longVersionCode = this.packageManager.getPackageInfo(str, 0).getLongVersionCode();
            Log.d("VersionChecker", "packageName=" + str + ", version=" + longVersionCode + ", target=" + j);
            if (j > longVersionCode) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
