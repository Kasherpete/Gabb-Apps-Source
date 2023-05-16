package com.gabb.services.tokens.internal.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0005HÆ\u0003J(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\r\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\bJ\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, mo20735d2 = {"Lcom/gabb/services/tokens/internal/extensions/Success;", "T", "Lcom/gabb/services/tokens/internal/extensions/Result;", "data", "responseModified", "", "(Ljava/lang/Object;Z)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getResponseModified", "()Z", "component1", "component2", "copy", "(Ljava/lang/Object;Z)Lcom/gabb/services/tokens/internal/extensions/Success;", "equals", "other", "", "get", "hashCode", "", "toString", "", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: Result.kt */
public final class Success<T> extends Result<T> {
    private final T data;
    private final boolean responseModified;

    public static /* synthetic */ Success copy$default(Success success, T t, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            t = success.data;
        }
        if ((i & 2) != 0) {
            z = success.responseModified;
        }
        return success.copy(t, z);
    }

    public final T component1() {
        return this.data;
    }

    public final boolean component2() {
        return this.responseModified;
    }

    public final Success<T> copy(T t, boolean z) {
        return new Success<>(t, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Success)) {
            return false;
        }
        Success success = (Success) obj;
        return Intrinsics.areEqual((Object) this.data, (Object) success.data) && this.responseModified == success.responseModified;
    }

    public int hashCode() {
        T t = this.data;
        int hashCode = (t == null ? 0 : t.hashCode()) * 31;
        boolean z = this.responseModified;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "Success(data=" + this.data + ", responseModified=" + this.responseModified + ')';
    }

    public Success(T t, boolean z) {
        super((DefaultConstructorMarker) null);
        this.data = t;
        this.responseModified = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Success(Object obj, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? true : z);
    }

    public final T getData() {
        return this.data;
    }

    public final boolean getResponseModified() {
        return this.responseModified;
    }

    public T get() {
        return this.data;
    }
}
