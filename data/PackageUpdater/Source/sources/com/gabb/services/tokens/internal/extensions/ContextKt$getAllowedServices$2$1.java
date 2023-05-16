package com.gabb.services.tokens.internal.extensions;

import com.gabb.services.tokens.GabbService;
import com.gabb.services.tokens.IAllowedAccessCallback;
import com.gabb.services.tokens.UnauthorizedException;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo20734d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016¨\u0006\u000b"}, mo20735d2 = {"com/gabb/services/tokens/internal/extensions/ContextKt$getAllowedServices$2$1", "Lcom/gabb/services/tokens/IAllowedAccessCallback$Stub;", "handleError", "", "returnError", "", "handleResult", "services", "", "Lcom/gabb/services/tokens/GabbService;", "handleUnauthorized", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: context.kt */
public final class ContextKt$getAllowedServices$2$1 extends IAllowedAccessCallback.Stub {
    final /* synthetic */ CancellableContinuation<Result<List<GabbService>>> $cont;

    public ContextKt$getAllowedServices$2$1(CancellableContinuation<? super Result<List<GabbService>>> cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public void handleResult(List<GabbService> list) {
        Intrinsics.checkNotNullParameter(list, "services");
        if (this.$cont.isActive()) {
            Success success = new Success(list, false, 2, (DefaultConstructorMarker) null);
            Result.Companion companion = Result.Companion;
            this.$cont.resumeWith(Result.m176constructorimpl(success));
        }
    }

    public void handleError(String str) {
        if (this.$cont.isActive()) {
            ErrorResult errorResult = new ErrorResult(new Throwable(str));
            Result.Companion companion = Result.Companion;
            this.$cont.resumeWith(Result.m176constructorimpl(errorResult));
        }
    }

    public void handleUnauthorized() {
        if (this.$cont.isActive()) {
            ErrorResult errorResult = new ErrorResult(new UnauthorizedException());
            Result.Companion companion = Result.Companion;
            this.$cont.resumeWith(Result.m176constructorimpl(errorResult));
        }
    }
}
