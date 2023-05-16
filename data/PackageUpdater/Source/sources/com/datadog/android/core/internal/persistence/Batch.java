package com.datadog.android.core.internal.persistence;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/Batch;", "", "id", "", "data", "", "(Ljava/lang/String;[B)V", "getData", "()[B", "getId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Batch.kt */
public final class Batch {
    private final byte[] data;

    /* renamed from: id */
    private final String f96id;

    public static /* synthetic */ Batch copy$default(Batch batch, String str, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            str = batch.f96id;
        }
        if ((i & 2) != 0) {
            bArr = batch.data;
        }
        return batch.copy(str, bArr);
    }

    public final String component1() {
        return this.f96id;
    }

    public final byte[] component2() {
        return this.data;
    }

    public final Batch copy(String str, byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bArr, MPDbAdapter.KEY_DATA);
        return new Batch(str, bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Batch)) {
            return false;
        }
        Batch batch = (Batch) obj;
        return Intrinsics.areEqual((Object) this.f96id, (Object) batch.f96id) && Intrinsics.areEqual((Object) this.data, (Object) batch.data);
    }

    public int hashCode() {
        return (this.f96id.hashCode() * 31) + Arrays.hashCode(this.data);
    }

    public String toString() {
        String str = this.f96id;
        return "Batch(id=" + str + ", data=" + Arrays.toString(this.data) + ")";
    }

    public Batch(String str, byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bArr, MPDbAdapter.KEY_DATA);
        this.f96id = str;
        this.data = bArr;
    }

    public final String getId() {
        return this.f96id;
    }

    public final byte[] getData() {
        return this.data;
    }
}
