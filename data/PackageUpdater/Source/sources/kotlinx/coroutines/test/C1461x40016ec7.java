package kotlinx.coroutines.test;

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
import kotlinx.coroutines.YieldKt;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0003*\u00020\u0004H@"}, mo20735d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$backgroundWorkRunner$1", mo21449f = "TestBuilders.kt", mo21450i = {0}, mo21451l = {249}, mo21452m = "invokeSuspend", mo21453n = {"$this$launch"}, mo21454s = {"L$0"})
/* renamed from: kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$backgroundWorkRunner$1 */
/* compiled from: TestBuilders.kt */
final class C1461x40016ec7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TestCoroutineScheduler $scheduler;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1461x40016ec7(TestCoroutineScheduler testCoroutineScheduler, Continuation<? super C1461x40016ec7> continuation) {
        super(2, continuation);
        this.$scheduler = testCoroutineScheduler;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        C1461x40016ec7 testBuildersKt__TestBuildersKt$runTestCoroutine$backgroundWorkRunner$1 = new C1461x40016ec7(this.$scheduler, continuation);
        testBuildersKt__TestBuildersKt$runTestCoroutine$backgroundWorkRunner$1.L$0 = obj;
        return testBuildersKt__TestBuildersKt$runTestCoroutine$backgroundWorkRunner$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C1461x40016ec7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        final CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        do {
            this.$scheduler.tryRunNextTaskUnless$kotlinx_coroutines_test(new Function0<Boolean>() {
                public final Boolean invoke() {
                    return Boolean.valueOf(!CoroutineScopeKt.isActive(coroutineScope));
                }
            });
            this.L$0 = coroutineScope;
            this.label = 1;
        } while (YieldKt.yield(this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
