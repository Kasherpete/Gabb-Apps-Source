package com.gabb.packageupdater.data.resultentities;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, mo20735d2 = {"Lcom/gabb/packageupdater/data/resultentities/AppInstallInfo;", "", "packageName", "", "uri", "Landroid/net/Uri;", "(Ljava/lang/String;Landroid/net/Uri;)V", "getPackageName", "()Ljava/lang/String;", "getUri", "()Landroid/net/Uri;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "data-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: AppInstallInfo.kt */
public final class AppInstallInfo {
    private final String packageName;
    private final Uri uri;

    public static /* synthetic */ AppInstallInfo copy$default(AppInstallInfo appInstallInfo, String str, Uri uri2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = appInstallInfo.packageName;
        }
        if ((i & 2) != 0) {
            uri2 = appInstallInfo.uri;
        }
        return appInstallInfo.copy(str, uri2);
    }

    public final String component1() {
        return this.packageName;
    }

    public final Uri component2() {
        return this.uri;
    }

    public final AppInstallInfo copy(String str, Uri uri2) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(uri2, "uri");
        return new AppInstallInfo(str, uri2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppInstallInfo)) {
            return false;
        }
        AppInstallInfo appInstallInfo = (AppInstallInfo) obj;
        return Intrinsics.areEqual((Object) this.packageName, (Object) appInstallInfo.packageName) && Intrinsics.areEqual((Object) this.uri, (Object) appInstallInfo.uri);
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + this.uri.hashCode();
    }

    public String toString() {
        return "AppInstallInfo(packageName=" + this.packageName + ", uri=" + this.uri + ')';
    }

    public AppInstallInfo(String str, Uri uri2) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(uri2, "uri");
        this.packageName = str;
        this.uri = uri2;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final Uri getUri() {
        return this.uri;
    }
}
