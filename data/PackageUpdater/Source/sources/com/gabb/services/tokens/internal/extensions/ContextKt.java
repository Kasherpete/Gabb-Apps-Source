package com.gabb.services.tokens.internal.extensions;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.gabb.services.tokens.GabbService;
import com.gabb.services.tokens.IAllowedAccessService;
import com.gabb.services.tokens.ITokenService;
import com.gabb.services.tokens.JWT;
import com.gabb.services.tokens.ServiceInitializationException;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(mo20734d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\b\u0006\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HHø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0001*\u00020\u0002HHø\u0001\u0000¢\u0006\u0002\u0010\f\u001a!\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0001*\u00020\u0002HHø\u0001\u0000¢\u0006\u0002\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, mo20735d2 = {"connectService", "Lcom/gabb/services/tokens/internal/extensions/Result;", "Landroid/os/IBinder;", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "onDisconnect", "Lkotlin/Function0;", "", "(Landroid/content/Context;Landroid/content/Intent;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccessToken", "Lcom/gabb/services/tokens/JWT;", "(Landroid/os/IBinder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllowedServices", "", "Lcom/gabb/services/tokens/GabbService;", "tokens_release"}, mo20736k = 2, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: context.kt */
public final class ContextKt {
    public static /* synthetic */ Object connectService$default(Context context, Intent intent, Function0 function0, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = ContextKt$connectService$2.INSTANCE;
        }
        InlineMarker.mark(0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        if (!context.bindService(intent, new ContextKt$connectService$3$connection$1(function0, cancellableContinuation), 1) && cancellableContinuation.isActive()) {
            ErrorResult errorResult = new ErrorResult(new ServiceInitializationException());
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.m176constructorimpl(errorResult));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }

    private static final Object connectService$$forInline(Context context, Intent intent, Function0<Unit> function0, Continuation<? super Result<IBinder>> continuation) {
        InlineMarker.mark(0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        if (!context.bindService(intent, new ContextKt$connectService$3$connection$1(function0, cancellableContinuation), 1) && cancellableContinuation.isActive()) {
            ErrorResult errorResult = new ErrorResult(new ServiceInitializationException());
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.m176constructorimpl(errorResult));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }

    public static final Object connectService(Context context, Intent intent, Function0<Unit> function0, Continuation<? super Result<IBinder>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        if (!context.bindService(intent, new ContextKt$connectService$3$connection$1(function0, cancellableContinuation), 1) && cancellableContinuation.isActive()) {
            ErrorResult errorResult = new ErrorResult(new ServiceInitializationException());
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.m176constructorimpl(errorResult));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    private static final Object getAccessToken$$forInline(IBinder iBinder, Continuation<? super Result<JWT>> continuation) {
        InlineMarker.mark(0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        ITokenService.Stub.asInterface(iBinder).fetchToken(new ContextKt$getAccessToken$2$1(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }

    public static final Object getAccessToken(IBinder iBinder, Continuation<? super Result<JWT>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        ITokenService.Stub.asInterface(iBinder).fetchToken(new ContextKt$getAccessToken$2$1(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    private static final Object getAllowedServices$$forInline(IBinder iBinder, Continuation<? super Result<List<GabbService>>> continuation) {
        InlineMarker.mark(0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        IAllowedAccessService.Stub.asInterface(iBinder).getCurrentServices(new ContextKt$getAllowedServices$2$1(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }

    public static final Object getAllowedServices(IBinder iBinder, Continuation<? super Result<List<GabbService>>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        IAllowedAccessService.Stub.asInterface(iBinder).getCurrentServices(new ContextKt$getAllowedServices$2$1(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
