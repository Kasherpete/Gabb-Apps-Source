package com.gabb.packageupdater.network.model;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b,\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003¢\u0006\u0002\u0010\u0014J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\rHÆ\u0003J\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0003HÆ\u0001J\u0013\u00107\u001a\u00020\u00072\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020:HÖ\u0001J\t\u0010;\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0016\u0010\u0013\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018¨\u0006<"}, mo20735d2 = {"Lcom/gabb/packageupdater/network/model/Release;", "", "appId", "", "appName", "bundleId", "enabled", "", "packageName", "versionCode", "versionName", "changelog", "apkSize", "", "fileName", "fileId", "fileUrl", "url", "releasedAt", "createdAt", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getApkSize", "()J", "getAppId", "()Ljava/lang/String;", "getAppName", "getBundleId", "getChangelog", "getCreatedAt", "getEnabled", "()Z", "getFileId", "getFileName", "getFileUrl", "getPackageName", "getReleasedAt", "getUrl", "getVersionCode", "getVersionName", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Release.kt */
public final class Release {
    @SerializedName("apk_size")
    private final long apkSize;
    @SerializedName("app_id")
    private final String appId;
    @SerializedName("app_name")
    private final String appName;
    @SerializedName("app_bundle_id")
    private final String bundleId;
    private final String changelog;
    @SerializedName("created-at")
    private final String createdAt;
    @SerializedName("enabled")
    private final boolean enabled;
    @SerializedName("apk_file_id")
    private final String fileId;
    @SerializedName("apk_file_name")
    private final String fileName;
    @SerializedName("apk_file_url")
    private final String fileUrl;
    @SerializedName("package_name")
    private final String packageName;
    @SerializedName("date_released")
    private final String releasedAt;
    private final String url;
    @SerializedName("version_code")
    private final String versionCode;
    @SerializedName("version_name")
    private final String versionName;

    public static /* synthetic */ Release copy$default(Release release, String str, String str2, String str3, boolean z, String str4, String str5, String str6, String str7, long j, String str8, String str9, String str10, String str11, String str12, String str13, int i, Object obj) {
        Release release2 = release;
        int i2 = i;
        return release.copy((i2 & 1) != 0 ? release2.appId : str, (i2 & 2) != 0 ? release2.appName : str2, (i2 & 4) != 0 ? release2.bundleId : str3, (i2 & 8) != 0 ? release2.enabled : z, (i2 & 16) != 0 ? release2.packageName : str4, (i2 & 32) != 0 ? release2.versionCode : str5, (i2 & 64) != 0 ? release2.versionName : str6, (i2 & 128) != 0 ? release2.changelog : str7, (i2 & 256) != 0 ? release2.apkSize : j, (i2 & 512) != 0 ? release2.fileName : str8, (i2 & 1024) != 0 ? release2.fileId : str9, (i2 & 2048) != 0 ? release2.fileUrl : str10, (i2 & 4096) != 0 ? release2.url : str11, (i2 & 8192) != 0 ? release2.releasedAt : str12, (i2 & 16384) != 0 ? release2.createdAt : str13);
    }

    public final String component1() {
        return this.appId;
    }

    public final String component10() {
        return this.fileName;
    }

    public final String component11() {
        return this.fileId;
    }

    public final String component12() {
        return this.fileUrl;
    }

    public final String component13() {
        return this.url;
    }

    public final String component14() {
        return this.releasedAt;
    }

    public final String component15() {
        return this.createdAt;
    }

    public final String component2() {
        return this.appName;
    }

    public final String component3() {
        return this.bundleId;
    }

    public final boolean component4() {
        return this.enabled;
    }

    public final String component5() {
        return this.packageName;
    }

    public final String component6() {
        return this.versionCode;
    }

    public final String component7() {
        return this.versionName;
    }

    public final String component8() {
        return this.changelog;
    }

    public final long component9() {
        return this.apkSize;
    }

    public final Release copy(String str, String str2, String str3, boolean z, String str4, String str5, String str6, String str7, long j, String str8, String str9, String str10, String str11, String str12, String str13) {
        String str14 = str;
        Intrinsics.checkNotNullParameter(str14, "appId");
        Intrinsics.checkNotNullParameter(str2, "appName");
        Intrinsics.checkNotNullParameter(str3, "bundleId");
        Intrinsics.checkNotNullParameter(str4, "packageName");
        Intrinsics.checkNotNullParameter(str5, "versionCode");
        Intrinsics.checkNotNullParameter(str6, "versionName");
        Intrinsics.checkNotNullParameter(str7, "changelog");
        Intrinsics.checkNotNullParameter(str8, "fileName");
        Intrinsics.checkNotNullParameter(str9, "fileId");
        Intrinsics.checkNotNullParameter(str10, "fileUrl");
        Intrinsics.checkNotNullParameter(str11, "url");
        Intrinsics.checkNotNullParameter(str12, "releasedAt");
        Intrinsics.checkNotNullParameter(str13, "createdAt");
        return new Release(str14, str2, str3, z, str4, str5, str6, str7, j, str8, str9, str10, str11, str12, str13);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Release)) {
            return false;
        }
        Release release = (Release) obj;
        return Intrinsics.areEqual((Object) this.appId, (Object) release.appId) && Intrinsics.areEqual((Object) this.appName, (Object) release.appName) && Intrinsics.areEqual((Object) this.bundleId, (Object) release.bundleId) && this.enabled == release.enabled && Intrinsics.areEqual((Object) this.packageName, (Object) release.packageName) && Intrinsics.areEqual((Object) this.versionCode, (Object) release.versionCode) && Intrinsics.areEqual((Object) this.versionName, (Object) release.versionName) && Intrinsics.areEqual((Object) this.changelog, (Object) release.changelog) && this.apkSize == release.apkSize && Intrinsics.areEqual((Object) this.fileName, (Object) release.fileName) && Intrinsics.areEqual((Object) this.fileId, (Object) release.fileId) && Intrinsics.areEqual((Object) this.fileUrl, (Object) release.fileUrl) && Intrinsics.areEqual((Object) this.url, (Object) release.url) && Intrinsics.areEqual((Object) this.releasedAt, (Object) release.releasedAt) && Intrinsics.areEqual((Object) this.createdAt, (Object) release.createdAt);
    }

    public int hashCode() {
        int hashCode = ((((this.appId.hashCode() * 31) + this.appName.hashCode()) * 31) + this.bundleId.hashCode()) * 31;
        boolean z = this.enabled;
        if (z) {
            z = true;
        }
        return ((((((((((((((((((((((hashCode + (z ? 1 : 0)) * 31) + this.packageName.hashCode()) * 31) + this.versionCode.hashCode()) * 31) + this.versionName.hashCode()) * 31) + this.changelog.hashCode()) * 31) + Long.hashCode(this.apkSize)) * 31) + this.fileName.hashCode()) * 31) + this.fileId.hashCode()) * 31) + this.fileUrl.hashCode()) * 31) + this.url.hashCode()) * 31) + this.releasedAt.hashCode()) * 31) + this.createdAt.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Release(appId=").append(this.appId).append(", appName=").append(this.appName).append(", bundleId=").append(this.bundleId).append(", enabled=").append(this.enabled).append(", packageName=").append(this.packageName).append(", versionCode=").append(this.versionCode).append(", versionName=").append(this.versionName).append(", changelog=").append(this.changelog).append(", apkSize=").append(this.apkSize).append(", fileName=").append(this.fileName).append(", fileId=").append(this.fileId).append(", fileUrl=");
        sb.append(this.fileUrl).append(", url=").append(this.url).append(", releasedAt=").append(this.releasedAt).append(", createdAt=").append(this.createdAt).append(')');
        return sb.toString();
    }

    public Release(String str, String str2, String str3, boolean z, String str4, String str5, String str6, String str7, long j, String str8, String str9, String str10, String str11, String str12, String str13) {
        String str14 = str;
        String str15 = str2;
        String str16 = str3;
        String str17 = str4;
        String str18 = str5;
        String str19 = str6;
        String str20 = str7;
        String str21 = str8;
        String str22 = str9;
        String str23 = str10;
        String str24 = str11;
        String str25 = str12;
        String str26 = str13;
        Intrinsics.checkNotNullParameter(str14, "appId");
        Intrinsics.checkNotNullParameter(str15, "appName");
        Intrinsics.checkNotNullParameter(str16, "bundleId");
        Intrinsics.checkNotNullParameter(str17, "packageName");
        Intrinsics.checkNotNullParameter(str18, "versionCode");
        Intrinsics.checkNotNullParameter(str19, "versionName");
        Intrinsics.checkNotNullParameter(str20, "changelog");
        Intrinsics.checkNotNullParameter(str21, "fileName");
        Intrinsics.checkNotNullParameter(str22, "fileId");
        Intrinsics.checkNotNullParameter(str23, "fileUrl");
        Intrinsics.checkNotNullParameter(str24, "url");
        Intrinsics.checkNotNullParameter(str25, "releasedAt");
        Intrinsics.checkNotNullParameter(str26, "createdAt");
        this.appId = str14;
        this.appName = str15;
        this.bundleId = str16;
        this.enabled = z;
        this.packageName = str17;
        this.versionCode = str18;
        this.versionName = str19;
        this.changelog = str20;
        this.apkSize = j;
        this.fileName = str21;
        this.fileId = str22;
        this.fileUrl = str23;
        this.url = str24;
        this.releasedAt = str25;
        this.createdAt = str26;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Release(String str, String str2, String str3, boolean z, String str4, String str5, String str6, String str7, long j, String str8, String str9, String str10, String str11, String str12, String str13, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, z, str4, str5, str6, (i & 128) != 0 ? "" : str7, j, str8, str9, str10, str11, str12, str13);
    }

    public final String getAppId() {
        return this.appId;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getBundleId() {
        return this.bundleId;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getVersionCode() {
        return this.versionCode;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final String getChangelog() {
        return this.changelog;
    }

    public final long getApkSize() {
        return this.apkSize;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final String getFileId() {
        return this.fileId;
    }

    public final String getFileUrl() {
        return this.fileUrl;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getReleasedAt() {
        return this.releasedAt;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }
}
