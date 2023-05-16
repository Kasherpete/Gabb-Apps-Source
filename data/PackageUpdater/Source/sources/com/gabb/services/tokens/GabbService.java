package com.gabb.services.tokens;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0019"}, mo20735d2 = {"Lcom/gabb/services/tokens/GabbService;", "Landroid/os/Parcelable;", "name", "", "sku", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getSku", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: GabbService.kt */
public final class GabbService implements Parcelable {
    public static final Parcelable.Creator<GabbService> CREATOR = new Creator();
    private final String name;
    private final String sku;

    @Metadata(mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
    /* compiled from: GabbService.kt */
    public static final class Creator implements Parcelable.Creator<GabbService> {
        public final GabbService createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new GabbService(parcel.readString(), parcel.readString());
        }

        public final GabbService[] newArray(int i) {
            return new GabbService[i];
        }
    }

    public static /* synthetic */ GabbService copy$default(GabbService gabbService, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gabbService.name;
        }
        if ((i & 2) != 0) {
            str2 = gabbService.sku;
        }
        return gabbService.copy(str, str2);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.sku;
    }

    public final GabbService copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "sku");
        return new GabbService(str, str2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GabbService)) {
            return false;
        }
        GabbService gabbService = (GabbService) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) gabbService.name) && Intrinsics.areEqual((Object) this.sku, (Object) gabbService.sku);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.sku.hashCode();
    }

    public String toString() {
        return "GabbService(name=" + this.name + ", sku=" + this.sku + ')';
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.name);
        parcel.writeString(this.sku);
    }

    public GabbService(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "sku");
        this.name = str;
        this.sku = str2;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSku() {
        return this.sku;
    }
}
