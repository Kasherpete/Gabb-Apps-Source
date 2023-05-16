package com.gabb.services.tokens;

import android.content.Context;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH@ø\u0001\u0000¢\u0006\u0002\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, mo20735d2 = {"Lcom/gabb/services/tokens/TokenManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getAccessToken", "Lcom/gabb/services/tokens/JWT;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllowedServices", "", "Lcom/gabb/services/tokens/GabbService;", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: TokenManager.kt */
public final class TokenManager {
    private final Context context;

    public TokenManager(Context context2) {
        Intrinsics.checkNotNullParameter(context2, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        this.context = context2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ef A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAccessToken(kotlin.coroutines.Continuation<? super com.gabb.services.tokens.JWT> r9) throws com.gabb.services.tokens.ServiceInitializationException {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.gabb.services.tokens.TokenManager$getAccessToken$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.gabb.services.tokens.TokenManager$getAccessToken$1 r0 = (com.gabb.services.tokens.TokenManager$getAccessToken$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.gabb.services.tokens.TokenManager$getAccessToken$1 r0 = new com.gabb.services.tokens.TokenManager$getAccessToken$1
            r0.<init>(r8, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r4) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r8 = r0.L$0
            android.os.IBinder r8 = (android.os.IBinder) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00f0
        L_0x0032:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003a:
            java.lang.Object r8 = r0.L$1
            android.content.Context r8 = (android.content.Context) r8
            java.lang.Object r8 = r0.L$0
            android.content.Intent r8 = (android.content.Intent) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00b4
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r9)
            android.content.Intent r9 = new android.content.Intent
            java.lang.String r2 = "com.gabb.services.GET_TOKEN"
            r9.<init>(r2)
            android.content.ComponentName r2 = new android.content.ComponentName
            android.content.Context r5 = r8.context
            r6 = 0
            java.lang.String r7 = "com.gabb.services"
            android.content.Context r5 = r5.createPackageContext(r7, r6)
            java.lang.String r6 = "com.gabb.services.impl.TokenService"
            r2.<init>(r5, r6)
            r9.setComponent(r2)
            android.content.Context r8 = r8.context
            r0.L$0 = r9
            r0.L$1 = r8
            r0.label = r4
            kotlinx.coroutines.CancellableContinuationImpl r2 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r2.<init>(r5, r4)
            r2.initCancellability()
            r5 = r2
            kotlinx.coroutines.CancellableContinuation r5 = (kotlinx.coroutines.CancellableContinuation) r5
            com.gabb.services.tokens.TokenManager$getAccessToken$$inlined$connectService$default$1 r6 = new com.gabb.services.tokens.TokenManager$getAccessToken$$inlined$connectService$default$1
            r6.<init>(r5)
            android.content.ServiceConnection r6 = (android.content.ServiceConnection) r6
            boolean r8 = r8.bindService(r9, r6, r4)
            if (r8 != 0) goto L_0x00a4
            boolean r8 = r5.isActive()
            if (r8 == 0) goto L_0x00a4
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            com.gabb.services.tokens.internal.extensions.ErrorResult r8 = new com.gabb.services.tokens.internal.extensions.ErrorResult
            com.gabb.services.tokens.ServiceInitializationException r9 = new com.gabb.services.tokens.ServiceInitializationException
            r9.<init>()
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            r8.<init>(r9)
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            java.lang.Object r8 = kotlin.Result.m176constructorimpl(r8)
            r5.resumeWith(r8)
        L_0x00a4:
            java.lang.Object r9 = r2.getResult()
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r9 != r8) goto L_0x00b1
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L_0x00b1:
            if (r9 != r1) goto L_0x00b4
            return r1
        L_0x00b4:
            com.gabb.services.tokens.internal.extensions.Result r9 = (com.gabb.services.tokens.internal.extensions.Result) r9
            java.lang.Object r8 = r9.getOrThrow()
            android.os.IBinder r8 = (android.os.IBinder) r8
            r0.L$0 = r8
            r9 = 0
            r0.L$1 = r9
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r9 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r9.<init>(r2, r4)
            r9.initCancellability()
            r2 = r9
            kotlinx.coroutines.CancellableContinuation r2 = (kotlinx.coroutines.CancellableContinuation) r2
            com.gabb.services.tokens.ITokenService r8 = com.gabb.services.tokens.ITokenService.Stub.asInterface(r8)
            com.gabb.services.tokens.internal.extensions.ContextKt$getAccessToken$2$1 r3 = new com.gabb.services.tokens.internal.extensions.ContextKt$getAccessToken$2$1
            r3.<init>(r2)
            com.gabb.services.tokens.ITokenCallback r3 = (com.gabb.services.tokens.ITokenCallback) r3
            r8.fetchToken(r3)
            java.lang.Object r9 = r9.getResult()
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r9 != r8) goto L_0x00ed
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L_0x00ed:
            if (r9 != r1) goto L_0x00f0
            return r1
        L_0x00f0:
            com.gabb.services.tokens.internal.extensions.Result r9 = (com.gabb.services.tokens.internal.extensions.Result) r9
            java.lang.Object r8 = r9.get()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.services.tokens.TokenManager.getAccessToken(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ef A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAllowedServices(kotlin.coroutines.Continuation<? super java.util.List<com.gabb.services.tokens.GabbService>> r9) throws com.gabb.services.tokens.UnauthorizedException, com.gabb.services.tokens.ServiceInitializationException {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.gabb.services.tokens.TokenManager$getAllowedServices$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.gabb.services.tokens.TokenManager$getAllowedServices$1 r0 = (com.gabb.services.tokens.TokenManager$getAllowedServices$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.gabb.services.tokens.TokenManager$getAllowedServices$1 r0 = new com.gabb.services.tokens.TokenManager$getAllowedServices$1
            r0.<init>(r8, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r4) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r8 = r0.L$0
            android.os.IBinder r8 = (android.os.IBinder) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00f0
        L_0x0032:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003a:
            java.lang.Object r8 = r0.L$1
            android.content.Context r8 = (android.content.Context) r8
            java.lang.Object r8 = r0.L$0
            android.content.Intent r8 = (android.content.Intent) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00b4
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r9)
            android.content.Intent r9 = new android.content.Intent
            java.lang.String r2 = "com.gabb.services.GET_ALLOWED_SERVICES"
            r9.<init>(r2)
            android.content.ComponentName r2 = new android.content.ComponentName
            android.content.Context r5 = r8.context
            r6 = 0
            java.lang.String r7 = "com.gabb.services"
            android.content.Context r5 = r5.createPackageContext(r7, r6)
            java.lang.String r6 = "com.gabb.services.impl.AllowedServicesService"
            r2.<init>(r5, r6)
            r9.setComponent(r2)
            android.content.Context r8 = r8.context
            r0.L$0 = r9
            r0.L$1 = r8
            r0.label = r4
            kotlinx.coroutines.CancellableContinuationImpl r2 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r2.<init>(r5, r4)
            r2.initCancellability()
            r5 = r2
            kotlinx.coroutines.CancellableContinuation r5 = (kotlinx.coroutines.CancellableContinuation) r5
            com.gabb.services.tokens.TokenManager$getAllowedServices$$inlined$connectService$default$1 r6 = new com.gabb.services.tokens.TokenManager$getAllowedServices$$inlined$connectService$default$1
            r6.<init>(r5)
            android.content.ServiceConnection r6 = (android.content.ServiceConnection) r6
            boolean r8 = r8.bindService(r9, r6, r4)
            if (r8 != 0) goto L_0x00a4
            boolean r8 = r5.isActive()
            if (r8 == 0) goto L_0x00a4
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            com.gabb.services.tokens.internal.extensions.ErrorResult r8 = new com.gabb.services.tokens.internal.extensions.ErrorResult
            com.gabb.services.tokens.ServiceInitializationException r9 = new com.gabb.services.tokens.ServiceInitializationException
            r9.<init>()
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            r8.<init>(r9)
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            java.lang.Object r8 = kotlin.Result.m176constructorimpl(r8)
            r5.resumeWith(r8)
        L_0x00a4:
            java.lang.Object r9 = r2.getResult()
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r9 != r8) goto L_0x00b1
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L_0x00b1:
            if (r9 != r1) goto L_0x00b4
            return r1
        L_0x00b4:
            com.gabb.services.tokens.internal.extensions.Result r9 = (com.gabb.services.tokens.internal.extensions.Result) r9
            java.lang.Object r8 = r9.getOrThrow()
            android.os.IBinder r8 = (android.os.IBinder) r8
            r0.L$0 = r8
            r9 = 0
            r0.L$1 = r9
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r9 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r9.<init>(r2, r4)
            r9.initCancellability()
            r2 = r9
            kotlinx.coroutines.CancellableContinuation r2 = (kotlinx.coroutines.CancellableContinuation) r2
            com.gabb.services.tokens.IAllowedAccessService r8 = com.gabb.services.tokens.IAllowedAccessService.Stub.asInterface(r8)
            com.gabb.services.tokens.internal.extensions.ContextKt$getAllowedServices$2$1 r3 = new com.gabb.services.tokens.internal.extensions.ContextKt$getAllowedServices$2$1
            r3.<init>(r2)
            com.gabb.services.tokens.IAllowedAccessCallback r3 = (com.gabb.services.tokens.IAllowedAccessCallback) r3
            r8.getCurrentServices(r3)
            java.lang.Object r9 = r9.getResult()
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r9 != r8) goto L_0x00ed
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L_0x00ed:
            if (r9 != r1) goto L_0x00f0
            return r1
        L_0x00f0:
            com.gabb.services.tokens.internal.extensions.Result r9 = (com.gabb.services.tokens.internal.extensions.Result) r9
            java.lang.Object r8 = r9.getOrThrow()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.services.tokens.TokenManager.getAllowedServices(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
