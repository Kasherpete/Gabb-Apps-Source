package com.gabb.data.entities;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J[\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006'"}, mo20735d2 = {"Lcom/gabb/data/entities/App;", "", "packageName", "", "appName", "versionCode", "", "version", "enabled", "", "uri", "downloadUrl", "releasedAt", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAppName", "()Ljava/lang/String;", "getDownloadUrl", "getEnabled", "()Z", "getPackageName", "getReleasedAt", "getUri", "getVersion", "getVersionCode", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "data"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: App.kt */
public final class App {
    private final String appName;
    private final String downloadUrl;
    private final boolean enabled;
    private final String packageName;
    private final String releasedAt;
    private final String uri;
    private final String version;
    private final long versionCode;

    public static /* synthetic */ App copy$default(App app, String str, String str2, long j, String str3, boolean z, String str4, String str5, String str6, int i, Object obj) {
        App app2 = app;
        int i2 = i;
        return app.copy((i2 & 1) != 0 ? app2.packageName : str, (i2 & 2) != 0 ? app2.appName : str2, (i2 & 4) != 0 ? app2.versionCode : j, (i2 & 8) != 0 ? app2.version : str3, (i2 & 16) != 0 ? app2.enabled : z, (i2 & 32) != 0 ? app2.uri : str4, (i2 & 64) != 0 ? app2.downloadUrl : str5, (i2 & 128) != 0 ? app2.releasedAt : str6);
    }

    public final String component1() {
        return this.packageName;
    }

    public final String component2() {
        return this.appName;
    }

    public final long component3() {
        return this.versionCode;
    }

    public final String component4() {
        return this.version;
    }

    public final boolean component5() {
        return this.enabled;
    }

    public final String component6() {
        return this.uri;
    }

    public final String component7() {
        return this.downloadUrl;
    }

    public final String component8() {
        return this.releasedAt;
    }

    public final App copy(String str, String str2, long j, String str3, boolean z, String str4, String str5, String str6) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "appName");
        String str7 = str3;
        Intrinsics.checkNotNullParameter(str7, "version");
        String str8 = str5;
        Intrinsics.checkNotNullParameter(str8, "downloadUrl");
        String str9 = str6;
        Intrinsics.checkNotNullParameter(str9, "releasedAt");
        return new App(str, str2, j, str7, z, str4, str8, str9);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof App)) {
            return false;
        }
        App app = (App) obj;
        return Intrinsics.areEqual((Object) this.packageName, (Object) app.packageName) && Intrinsics.areEqual((Object) this.appName, (Object) app.appName) && this.versionCode == app.versionCode && Intrinsics.areEqual((Object) this.version, (Object) app.version) && this.enabled == app.enabled && Intrinsics.areEqual((Object) this.uri, (Object) app.uri) && Intrinsics.areEqual((Object) this.downloadUrl, (Object) app.downloadUrl) && Intrinsics.areEqual((Object) this.releasedAt, (Object) app.releasedAt);
    }

    public int hashCode() {
        int hashCode = ((((((this.packageName.hashCode() * 31) + this.appName.hashCode()) * 31) + Long.hashCode(this.versionCode)) * 31) + this.version.hashCode()) * 31;
        boolean z = this.enabled;
        if (z) {
            z = true;
        }
        int i = (hashCode + (z ? 1 : 0)) * 31;
        String str = this.uri;
        return ((((i + (str == null ? 0 : str.hashCode())) * 31) + this.downloadUrl.hashCode()) * 31) + this.releasedAt.hashCode();
    }

    public String toString() {
        return "App(packageName=" + this.packageName + ", appName=" + this.appName + ", versionCode=" + this.versionCode + ", version=" + this.version + ", enabled=" + this.enabled + ", uri=" + this.uri + ", downloadUrl=" + this.downloadUrl + ", releasedAt=" + this.releasedAt + ')';
    }

    public App(String str, String str2, long j, String str3, boolean z, String str4, String str5, String str6) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "appName");
        Intrinsics.checkNotNullParameter(str3, "version");
        Intrinsics.checkNotNullParameter(str5, "downloadUrl");
        Intrinsics.checkNotNullParameter(str6, "releasedAt");
        this.packageName = str;
        this.appName = str2;
        this.versionCode = j;
        this.version = str3;
        this.enabled = z;
        this.uri = str4;
        this.downloadUrl = str5;
        this.releasedAt = str6;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final long getVersionCode() {
        return this.versionCode;
    }

    public final String getVersion() {
        return this.version;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final String getUri() {
        return this.uri;
    }

    public final String getDownloadUrl() {
        return this.downloadUrl;
    }

    public final String getReleasedAt() {
        return this.releasedAt;
    }
}
