package kotlinx.coroutines.test;

import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0003H@"}, mo20735d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/AbstractCoroutine;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3", mo21449f = "TestBuilders.kt", mo21450i = {}, mo21451l = {}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: TestBuilders.kt */
final class TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<List<Throwable>> $cleanup;
    final /* synthetic */ T $coroutine;
    final /* synthetic */ long $dispatchTimeoutMs;
    final /* synthetic */ Function1<T, Throwable> $tryGetCompletionCause;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3(T t, long j, Function1<? super T, ? extends Throwable> function1, Function0<? extends List<? extends Throwable>> function0, Continuation<? super TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3> continuation) {
        super(1, continuation);
        this.$coroutine = t;
        this.$dispatchTimeoutMs = j;
        this.$tryGetCompletionCause = function1;
        this.$cleanup = function0;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3(this.$coroutine, this.$dispatchTimeoutMs, this.$tryGetCompletionCause, this.$cleanup, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        List<Throwable> list;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            T t = this.$coroutine;
            long j = this.$dispatchTimeoutMs;
            Function1<T, Throwable> function1 = this.$tryGetCompletionCause;
            try {
                list = this.$cleanup.invoke();
            } catch (UncompletedCoroutinesError unused) {
                list = CollectionsKt.emptyList();
            }
            List<Job> list2 = SequencesKt.toList(SequencesKt.filter(t.getChildren(), TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1.INSTANCE));
            Throwable invoke = t.isCancelled() ? function1.invoke(t) : null;
            String str = "After waiting for " + j + " ms";
            if (invoke == null) {
                str = str + ", the test coroutine is not completing";
            }
            if (!list2.isEmpty()) {
                str = str + ", there were active child jobs: " + list2;
            }
            if (invoke != null && list2.isEmpty()) {
                if (t.isCompleted()) {
                    return Unit.INSTANCE;
                }
                str = str + ", the test coroutine was not completed";
            }
            UncompletedCoroutinesError uncompletedCoroutinesError = new UncompletedCoroutinesError(str);
            if (invoke != null) {
                ExceptionsKt.addSuppressed(uncompletedCoroutinesError, invoke);
            }
            for (Throwable addSuppressed : list) {
                ExceptionsKt.addSuppressed(uncompletedCoroutinesError, addSuppressed);
            }
            throw uncompletedCoroutinesError;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
