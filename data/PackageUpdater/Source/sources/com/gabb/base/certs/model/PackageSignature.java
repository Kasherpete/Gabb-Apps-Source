package com.gabb.base.certs.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, mo20735d2 = {"Lcom/gabb/base/certs/model/PackageSignature;", "", "signature", "", "(Ljava/lang/String;)V", "getSignature", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "base"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PackageSignature.kt */
public final class PackageSignature {
    private final String signature;

    public static /* synthetic */ PackageSignature copy$default(PackageSignature packageSignature, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = packageSignature.signature;
        }
        return packageSignature.copy(str);
    }

    public final String component1() {
        return this.signature;
    }

    public final PackageSignature copy(String str) {
        Intrinsics.checkNotNullParameter(str, "signature");
        return new PackageSignature(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PackageSignature) && Intrinsics.areEqual((Object) this.signature, (Object) ((PackageSignature) obj).signature);
    }

    public int hashCode() {
        return this.signature.hashCode();
    }

    public String toString() {
        return "PackageSignature(signature=" + this.signature + ')';
    }

    public PackageSignature(String str) {
        Intrinsics.checkNotNullParameter(str, "signature");
        this.signature = str;
    }

    public final String getSignature() {
        return this.signature;
    }
}
