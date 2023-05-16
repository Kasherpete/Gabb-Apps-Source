package kotlinx.coroutines.test;

import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTest$1$1", mo21449f = "TestBuilders.kt", mo21450i = {}, mo21451l = {167}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: TestBuilders.kt */
final class TestBuildersKt__TestBuildersKt$runTest$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $dispatchTimeoutMs;
    final /* synthetic */ TestScopeImpl $it;
    final /* synthetic */ Function2<TestScope, Continuation<? super Unit>, Object> $testBody;
    final /* synthetic */ TestScope $this_runTest;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestBuildersKt__TestBuildersKt$runTest$1$1(TestScopeImpl testScopeImpl, long j, Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object> function2, TestScope testScope, Continuation<? super TestBuildersKt__TestBuildersKt$runTest$1$1> continuation) {
        super(2, continuation);
        this.$it = testScopeImpl;
        this.$dispatchTimeoutMs = j;
        this.$testBody = function2;
        this.$this_runTest = testScope;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TestBuildersKt__TestBuildersKt$runTest$1$1 testBuildersKt__TestBuildersKt$runTest$1$1 = new TestBuildersKt__TestBuildersKt$runTest$1$1(this.$it, this.$dispatchTimeoutMs, this.$testBody, this.$this_runTest, continuation);
        testBuildersKt__TestBuildersKt$runTest$1$1.L$0 = obj;
        return testBuildersKt__TestBuildersKt$runTest$1$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TestBuildersKt__TestBuildersKt$runTest$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2<TestScope, Continuation<? super Unit>, Object> function2 = this.$testBody;
            final TestScope testScope = this.$this_runTest;
            final TestScopeImpl testScopeImpl = this.$it;
            this.label = 1;
            if (TestBuildersKt.runTestCoroutine((CoroutineScope) this.L$0, this.$it, this.$dispatchTimeoutMs, C14581.INSTANCE, function2, new Function0<List<? extends Throwable>>() {
                public final List<Throwable> invoke() {
                    CoroutineScopeKt.cancel$default(testScope.getBackgroundScope(), (CancellationException) null, 1, (Object) null);
                    testScope.getTestScheduler().advanceUntilIdleOr$kotlinx_coroutines_test(C14601.INSTANCE);
                    return testScopeImpl.leave();
                }
            }, this) == coroutine_suspended) {
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
