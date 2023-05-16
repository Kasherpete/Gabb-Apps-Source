package com.gabb.data.entities;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, mo20735d2 = {"Lcom/gabb/data/entities/AppContentUri;", "", "packageName", "", "contentUri", "(Ljava/lang/String;Ljava/lang/String;)V", "getContentUri", "()Ljava/lang/String;", "getPackageName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "data"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: AppContentUri.kt */
public final class AppContentUri {
    private final String contentUri;
    private final String packageName;

    public static /* synthetic */ AppContentUri copy$default(AppContentUri appContentUri, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = appContentUri.packageName;
        }
        if ((i & 2) != 0) {
            str2 = appContentUri.contentUri;
        }
        return appContentUri.copy(str, str2);
    }

    public final String component1() {
        return this.packageName;
    }

    public final String component2() {
        return this.contentUri;
    }

    public final AppContentUri copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "contentUri");
        return new AppContentUri(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppContentUri)) {
            return false;
        }
        AppContentUri appContentUri = (AppContentUri) obj;
        return Intrinsics.areEqual((Object) this.packageName, (Object) appContentUri.packageName) && Intrinsics.areEqual((Object) this.contentUri, (Object) appContentUri.contentUri);
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + this.contentUri.hashCode();
    }

    public String toString() {
        return "AppContentUri(packageName=" + this.packageName + ", contentUri=" + this.contentUri + ')';
    }

    public AppContentUri(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "contentUri");
        this.packageName = str;
        this.contentUri = str2;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getContentUri() {
        return this.contentUri;
    }
}
