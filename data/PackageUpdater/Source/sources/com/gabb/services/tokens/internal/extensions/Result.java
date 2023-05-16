package com.gabb.services.tokens.internal.extensions;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007\b\u0004¢\u0006\u0002\u0010\u0003J\u000f\u0010\u0004\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0005J\u000b\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005\u0001\u0002\u0007\b¨\u0006\t"}, mo20735d2 = {"Lcom/gabb/services/tokens/internal/extensions/Result;", "T", "", "()V", "get", "()Ljava/lang/Object;", "getOrThrow", "Lcom/gabb/services/tokens/internal/extensions/Success;", "Lcom/gabb/services/tokens/internal/extensions/ErrorResult;", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: Result.kt */
public abstract class Result<T> {
    public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public T get() {
        return null;
    }

    private Result() {
    }

    public final T getOrThrow() {
        if (this instanceof Success) {
            return ((Success) this).get();
        }
        if (this instanceof ErrorResult) {
            throw ((ErrorResult) this).getThrowable();
        }
        throw new NoWhenBranchMatchedException();
    }
}
