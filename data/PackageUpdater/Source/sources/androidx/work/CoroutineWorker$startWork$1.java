package androidx.work;

import androidx.work.ListenableWorker;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
@DebugMetadata(mo21448c = "androidx.work.CoroutineWorker$startWork$1", mo21449f = "CoroutineWorker.kt", mo21450i = {}, mo21451l = {68}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: CoroutineWorker.kt */
final class CoroutineWorker$startWork$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ CoroutineWorker this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CoroutineWorker$startWork$1(CoroutineWorker coroutineWorker, Continuation<? super CoroutineWorker$startWork$1> continuation) {
        super(2, continuation);
        this.this$0 = coroutineWorker;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CoroutineWorker$startWork$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CoroutineWorker$startWork$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.doWork(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                this.this$0.getFuture$work_runtime_ktx_release().setException(th);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.getFuture$work_runtime_ktx_release().set((ListenableWorker.Result) obj);
        return Unit.INSTANCE;
    }
}
