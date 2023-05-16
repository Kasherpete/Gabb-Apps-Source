package com.gabb.packageupdater.api;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u0013\u0010\u001b\u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\u0019\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006%"}, mo20735d2 = {"Lcom/gabb/packageupdater/api/App;", "Landroid/os/Parcelable;", "appName", "", "versionCode", "", "version", "releasedAt", "enabled", "", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Z)V", "getAppName", "()Ljava/lang/String;", "getEnabled", "()Z", "getReleasedAt", "getVersion", "getVersionCode", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "api_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: App.kt */
public final class App implements Parcelable {
    public static final Parcelable.Creator<App> CREATOR = new Creator();
    private final String appName;
    private final boolean enabled;
    private final String releasedAt;
    private final String version;
    private final long versionCode;

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: App.kt */
    public static final class Creator implements Parcelable.Creator<App> {
        public final App createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new App(parcel.readString(), parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
        }

        public final App[] newArray(int i) {
            return new App[i];
        }
    }

    public static /* synthetic */ App copy$default(App app, String str, long j, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = app.appName;
        }
        if ((i & 2) != 0) {
            j = app.versionCode;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            str2 = app.version;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            str3 = app.releasedAt;
        }
        String str5 = str3;
        if ((i & 16) != 0) {
            z = app.enabled;
        }
        return app.copy(str, j2, str4, str5, z);
    }

    public final String component1() {
        return this.appName;
    }

    public final long component2() {
        return this.versionCode;
    }

    public final String component3() {
        return this.version;
    }

    public final String component4() {
        return this.releasedAt;
    }

    public final boolean component5() {
        return this.enabled;
    }

    public final App copy(String str, long j, String str2, String str3, boolean z) {
        Intrinsics.checkNotNullParameter(str, "appName");
        Intrinsics.checkNotNullParameter(str2, "version");
        Intrinsics.checkNotNullParameter(str3, "releasedAt");
        return new App(str, j, str2, str3, z);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof App)) {
            return false;
        }
        App app = (App) obj;
        return Intrinsics.areEqual((Object) this.appName, (Object) app.appName) && this.versionCode == app.versionCode && Intrinsics.areEqual((Object) this.version, (Object) app.version) && Intrinsics.areEqual((Object) this.releasedAt, (Object) app.releasedAt) && this.enabled == app.enabled;
    }

    public int hashCode() {
        int hashCode = ((((((this.appName.hashCode() * 31) + Long.hashCode(this.versionCode)) * 31) + this.version.hashCode()) * 31) + this.releasedAt.hashCode()) * 31;
        boolean z = this.enabled;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "App(appName=" + this.appName + ", versionCode=" + this.versionCode + ", version=" + this.version + ", releasedAt=" + this.releasedAt + ", enabled=" + this.enabled + ')';
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.appName);
        parcel.writeLong(this.versionCode);
        parcel.writeString(this.version);
        parcel.writeString(this.releasedAt);
        parcel.writeInt(this.enabled ? 1 : 0);
    }

    public App(String str, long j, String str2, String str3, boolean z) {
        Intrinsics.checkNotNullParameter(str, "appName");
        Intrinsics.checkNotNullParameter(str2, "version");
        Intrinsics.checkNotNullParameter(str3, "releasedAt");
        this.appName = str;
        this.versionCode = j;
        this.version = str2;
        this.releasedAt = str3;
        this.enabled = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ App(String str, long j, String str2, String str3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, str2, str3, (i & 16) != 0 ? true : z);
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

    public final String getReleasedAt() {
        return this.releasedAt;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }
}
