package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0001H@"}, mo20735d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "it"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2", mo21449f = "TestBuilders.kt", mo21450i = {}, mo21451l = {}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: TestBuilders.kt */
final class TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
    int label;

    TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2(Continuation<? super TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2(continuation);
    }

    public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
        return ((TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
