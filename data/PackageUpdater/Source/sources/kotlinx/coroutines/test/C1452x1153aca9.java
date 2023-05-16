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
@DebugMetadata(mo21448c = "kotlinx.coroutines.test.TestBuildersKt__TestBuildersDeprecatedKt$runBlockingTest$deferred$1", mo21449f = "TestBuildersDeprecated.kt", mo21450i = {}, mo21451l = {64}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* renamed from: kotlinx.coroutines.test.TestBuildersKt__TestBuildersDeprecatedKt$runBlockingTest$deferred$1 */
/* compiled from: TestBuildersDeprecated.kt */
final class C1452x1153aca9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TestCoroutineScope $scope;
    final /* synthetic */ Function2<TestCoroutineScope, Continuation<? super Unit>, Object> $testBody;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1452x1153aca9(Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, TestCoroutineScope testCoroutineScope, Continuation<? super C1452x1153aca9> continuation) {
        super(2, continuation);
        this.$testBody = function2;
        this.$scope = testCoroutineScope;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new C1452x1153aca9(this.$testBody, this.$scope, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C1452x1153aca9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2<TestCoroutineScope, Continuation<? super Unit>, Object> function2 = this.$testBody;
            TestCoroutineScope testCoroutineScope = this.$scope;
            this.label = 1;
            if (function2.invoke(testCoroutineScope, this) == coroutine_suspended) {
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
