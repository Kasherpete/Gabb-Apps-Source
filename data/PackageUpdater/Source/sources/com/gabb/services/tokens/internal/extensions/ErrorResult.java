package com.gabb.services.tokens.internal.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, mo20735d2 = {"Lcom/gabb/services/tokens/internal/extensions/ErrorResult;", "T", "Lcom/gabb/services/tokens/internal/extensions/Result;", "throwable", "", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: Result.kt */
public final class ErrorResult<T> extends Result<T> {
    private final Throwable throwable;

    public static /* synthetic */ ErrorResult copy$default(ErrorResult errorResult, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            th = errorResult.throwable;
        }
        return errorResult.copy(th);
    }

    public final Throwable component1() {
        return this.throwable;
    }

    public final ErrorResult<T> copy(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
        return new ErrorResult<>(th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ErrorResult) && Intrinsics.areEqual((Object) this.throwable, (Object) ((ErrorResult) obj).throwable);
    }

    public int hashCode() {
        return this.throwable.hashCode();
    }

    public String toString() {
        return "ErrorResult(throwable=" + this.throwable + ')';
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ErrorResult(Throwable th) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(th, "throwable");
        this.throwable = th;
    }

    public final Throwable getThrowable() {
        return this.throwable;
    }
}
