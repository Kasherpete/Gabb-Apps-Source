package com.gabb.packageupdater.work;

import com.gabb.data.entities.App;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BA\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\r\u0010\u0018\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003JS\u0010\u001f\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\u0006\u0010$\u001a\u00020%J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006'"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/AppDownloadData;", "", "packageName", "", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageName;", "appName", "enabled", "", "versionCode", "", "versionName", "apkUrl", "releasedAt", "(Ljava/lang/String;Ljava/lang/String;ZJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getApkUrl", "()Ljava/lang/String;", "getAppName", "getEnabled", "()Z", "getPackageName", "getReleasedAt", "getVersionCode", "()J", "getVersionName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toApp", "Lcom/gabb/data/entities/App;", "toString", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SingleShotInstallWorker.kt */
public final class AppDownloadData {
    private final String apkUrl;
    private final String appName;
    private final boolean enabled;
    private final String packageName;
    private final String releasedAt;
    private final long versionCode;
    private final String versionName;

    public static /* synthetic */ AppDownloadData copy$default(AppDownloadData appDownloadData, String str, String str2, boolean z, long j, String str3, String str4, String str5, int i, Object obj) {
        AppDownloadData appDownloadData2 = appDownloadData;
        return appDownloadData.copy((i & 1) != 0 ? appDownloadData2.packageName : str, (i & 2) != 0 ? appDownloadData2.appName : str2, (i & 4) != 0 ? appDownloadData2.enabled : z, (i & 8) != 0 ? appDownloadData2.versionCode : j, (i & 16) != 0 ? appDownloadData2.versionName : str3, (i & 32) != 0 ? appDownloadData2.apkUrl : str4, (i & 64) != 0 ? appDownloadData2.releasedAt : str5);
    }

    public final String component1() {
        return this.packageName;
    }

    public final String component2() {
        return this.appName;
    }

    public final boolean component3() {
        return this.enabled;
    }

    public final long component4() {
        return this.versionCode;
    }

    public final String component5() {
        return this.versionName;
    }

    public final String component6() {
        return this.apkUrl;
    }

    public final String component7() {
        return this.releasedAt;
    }

    public final AppDownloadData copy(String str, String str2, boolean z, long j, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "appName");
        String str6 = str3;
        Intrinsics.checkNotNullParameter(str6, "versionName");
        String str7 = str4;
        Intrinsics.checkNotNullParameter(str7, "apkUrl");
        String str8 = str5;
        Intrinsics.checkNotNullParameter(str8, "releasedAt");
        return new AppDownloadData(str, str2, z, j, str6, str7, str8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppDownloadData)) {
            return false;
        }
        AppDownloadData appDownloadData = (AppDownloadData) obj;
        return Intrinsics.areEqual((Object) this.packageName, (Object) appDownloadData.packageName) && Intrinsics.areEqual((Object) this.appName, (Object) appDownloadData.appName) && this.enabled == appDownloadData.enabled && this.versionCode == appDownloadData.versionCode && Intrinsics.areEqual((Object) this.versionName, (Object) appDownloadData.versionName) && Intrinsics.areEqual((Object) this.apkUrl, (Object) appDownloadData.apkUrl) && Intrinsics.areEqual((Object) this.releasedAt, (Object) appDownloadData.releasedAt);
    }

    public int hashCode() {
        int hashCode = ((this.packageName.hashCode() * 31) + this.appName.hashCode()) * 31;
        boolean z = this.enabled;
        if (z) {
            z = true;
        }
        return ((((((((hashCode + (z ? 1 : 0)) * 31) + Long.hashCode(this.versionCode)) * 31) + this.versionName.hashCode()) * 31) + this.apkUrl.hashCode()) * 31) + this.releasedAt.hashCode();
    }

    public String toString() {
        return "AppDownloadData(packageName=" + this.packageName + ", appName=" + this.appName + ", enabled=" + this.enabled + ", versionCode=" + this.versionCode + ", versionName=" + this.versionName + ", apkUrl=" + this.apkUrl + ", releasedAt=" + this.releasedAt + ')';
    }

    public AppDownloadData(String str, String str2, boolean z, long j, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "appName");
        Intrinsics.checkNotNullParameter(str3, "versionName");
        Intrinsics.checkNotNullParameter(str4, "apkUrl");
        Intrinsics.checkNotNullParameter(str5, "releasedAt");
        this.packageName = str;
        this.appName = str2;
        this.enabled = z;
        this.versionCode = j;
        this.versionName = str3;
        this.apkUrl = str4;
        this.releasedAt = str5;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final long getVersionCode() {
        return this.versionCode;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final String getApkUrl() {
        return this.apkUrl;
    }

    public final String getReleasedAt() {
        return this.releasedAt;
    }

    public final App toApp() {
        return new App(this.packageName, this.appName, this.versionCode, this.versionName, this.enabled, (String) null, this.apkUrl, this.releasedAt);
    }
}
