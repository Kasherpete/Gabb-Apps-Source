package com.gabb.services.tokens.internal.extensions;

import com.gabb.services.tokens.ITokenCallback;
import com.gabb.services.tokens.JWT;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo20734d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, mo20735d2 = {"com/gabb/services/tokens/internal/extensions/ContextKt$getAccessToken$2$1", "Lcom/gabb/services/tokens/ITokenCallback$Stub;", "handleResult", "", "tokenString", "", "returnCode", "", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: context.kt */
public final class ContextKt$getAccessToken$2$1 extends ITokenCallback.Stub {
    final /* synthetic */ CancellableContinuation<Result<JWT>> $cont;

    public ContextKt$getAccessToken$2$1(CancellableContinuation<? super Result<JWT>> cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public void handleResult(String str, int i) {
        if (str != null) {
            JWT jwt = new JWT(str);
            if (this.$cont.isActive()) {
                Success success = new Success(jwt, false, 2, (DefaultConstructorMarker) null);
                Result.Companion companion = Result.Companion;
                this.$cont.resumeWith(Result.m176constructorimpl(success));
            }
        } else if (this.$cont.isActive()) {
            ErrorResult errorResult = new ErrorResult(new Throwable("Unable to retrieve token"));
            Result.Companion companion2 = Result.Companion;
            this.$cont.resumeWith(Result.m176constructorimpl(errorResult));
        }
    }
}
