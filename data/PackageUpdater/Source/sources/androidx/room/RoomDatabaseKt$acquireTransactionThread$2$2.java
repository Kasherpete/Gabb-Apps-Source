package androidx.room;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "", "run"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RoomDatabase.kt */
final class RoomDatabaseKt$acquireTransactionThread$2$2 implements Runnable {
    final /* synthetic */ CancellableContinuation<ContinuationInterceptor> $continuation;
    final /* synthetic */ Job $controlJob;

    RoomDatabaseKt$acquireTransactionThread$2$2(CancellableContinuation<? super ContinuationInterceptor> cancellableContinuation, Job job) {
        this.$continuation = cancellableContinuation;
        this.$controlJob = job;
    }

    @Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    @DebugMetadata(mo21448c = "androidx.room.RoomDatabaseKt$acquireTransactionThread$2$2$1", mo21449f = "RoomDatabase.kt", mo21450i = {}, mo21451l = {124}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
    /* renamed from: androidx.room.RoomDatabaseKt$acquireTransactionThread$2$2$1 */
    /* compiled from: RoomDatabase.kt */
    static final class C06371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06371 r0 = new C06371(cancellableContinuation, job, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Result.Companion companion = Result.Companion;
                CoroutineContext.Element element = ((CoroutineScope) this.L$0).getCoroutineContext().get(ContinuationInterceptor.Key);
                Intrinsics.checkNotNull(element);
                cancellableContinuation.resumeWith(Result.m176constructorimpl(element));
                this.label = 1;
                if (job.join(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void run() {
        final CancellableContinuation<ContinuationInterceptor> cancellableContinuation = this.$continuation;
        final Job job = this.$controlJob;
        Object unused = BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new C06371((Continuation<? super C06371>) null), 1, (Object) null);
    }
}
