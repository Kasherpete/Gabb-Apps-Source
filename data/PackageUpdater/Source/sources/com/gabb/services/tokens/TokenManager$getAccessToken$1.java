package com.gabb.services.tokens;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.services.tokens.TokenManager", mo21449f = "TokenManager.kt", mo21450i = {0, 0, 1}, mo21451l = {43, 68}, mo21452m = "getAccessToken", mo21453n = {"serviceIntent", "$this$connectService_u24default$iv", "$this$getAccessToken$iv"}, mo21454s = {"L$0", "L$1", "L$0"})
/* compiled from: TokenManager.kt */
final class TokenManager$getAccessToken$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TokenManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TokenManager$getAccessToken$1(TokenManager tokenManager, Continuation<? super TokenManager$getAccessToken$1> continuation) {
        super(continuation);
        this.this$0 = tokenManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getAccessToken(this);
    }
}
