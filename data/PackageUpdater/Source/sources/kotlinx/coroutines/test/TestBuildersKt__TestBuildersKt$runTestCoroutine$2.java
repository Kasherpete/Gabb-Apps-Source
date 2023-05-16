package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AbstractCoroutine;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0003*\u0002H\u0002H@"}, mo20735d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/AbstractCoroutine;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$2", mo21449f = "TestBuilders.kt", mo21450i = {}, mo21451l = {212}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: TestBuilders.kt */
final class TestBuildersKt__TestBuildersKt$runTestCoroutine$2 extends SuspendLambda implements Function2<T, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<T, Continuation<? super Unit>, Object> $testBody;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestBuildersKt__TestBuildersKt$runTestCoroutine$2(Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super TestBuildersKt__TestBuildersKt$runTestCoroutine$2> continuation) {
        super(2, continuation);
        this.$testBody = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TestBuildersKt__TestBuildersKt$runTestCoroutine$2 testBuildersKt__TestBuildersKt$runTestCoroutine$2 = new TestBuildersKt__TestBuildersKt$runTestCoroutine$2(this.$testBody, continuation);
        testBuildersKt__TestBuildersKt$runTestCoroutine$2.L$0 = obj;
        return testBuildersKt__TestBuildersKt$runTestCoroutine$2;
    }

    public final Object invoke(T t, Continuation<? super Unit> continuation) {
        return ((TestBuildersKt__TestBuildersKt$runTestCoroutine$2) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2<T, Continuation<? super Unit>, Object> function2 = this.$testBody;
            this.label = 1;
            if (function2.invoke((AbstractCoroutine) this.L$0, this) == coroutine_suspended) {
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
