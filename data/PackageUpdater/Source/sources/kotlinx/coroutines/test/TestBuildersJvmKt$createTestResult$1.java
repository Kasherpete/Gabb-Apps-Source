package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.test.TestBuildersJvmKt$createTestResult$1", mo21449f = "TestBuildersJvm.kt", mo21450i = {}, mo21451l = {13}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: TestBuildersJvm.kt */
final class TestBuildersJvmKt$createTestResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $testProcedure;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestBuildersJvmKt$createTestResult$1(Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super TestBuildersJvmKt$createTestResult$1> continuation) {
        super(2, continuation);
        this.$testProcedure = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TestBuildersJvmKt$createTestResult$1 testBuildersJvmKt$createTestResult$1 = new TestBuildersJvmKt$createTestResult$1(this.$testProcedure, continuation);
        testBuildersJvmKt$createTestResult$1.L$0 = obj;
        return testBuildersJvmKt$createTestResult$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TestBuildersJvmKt$createTestResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2<CoroutineScope, Continuation<? super Unit>, Object> function2 = this.$testProcedure;
            this.label = 1;
            if (function2.invoke((CoroutineScope) this.L$0, this) == coroutine_suspended) {
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
