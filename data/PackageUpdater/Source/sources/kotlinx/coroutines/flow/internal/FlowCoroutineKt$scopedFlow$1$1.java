package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@"}, mo20735d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.flow.internal.FlowCoroutineKt$scopedFlow$1$1", mo21449f = "FlowCoroutine.kt", mo21450i = {}, mo21451l = {51}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: FlowCoroutine.kt */
final class FlowCoroutineKt$scopedFlow$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<CoroutineScope, FlowCollector<? super R>, Continuation<? super Unit>, Object> $block;
    final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowCoroutineKt$scopedFlow$1$1(Function3<? super CoroutineScope, ? super FlowCollector<? super R>, ? super Continuation<? super Unit>, ? extends Object> function3, FlowCollector<? super R> flowCollector, Continuation<? super FlowCoroutineKt$scopedFlow$1$1> continuation) {
        super(2, continuation);
        this.$block = function3;
        this.$this_unsafeFlow = flowCollector;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowCoroutineKt$scopedFlow$1$1 flowCoroutineKt$scopedFlow$1$1 = new FlowCoroutineKt$scopedFlow$1$1(this.$block, this.$this_unsafeFlow, continuation);
        flowCoroutineKt$scopedFlow$1$1.L$0 = obj;
        return flowCoroutineKt$scopedFlow$1$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FlowCoroutineKt$scopedFlow$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function3<CoroutineScope, FlowCollector<? super R>, Continuation<? super Unit>, Object> function3 = this.$block;
            FlowCollector<R> flowCollector = this.$this_unsafeFlow;
            this.label = 1;
            if (function3.invoke((CoroutineScope) this.L$0, flowCollector, this) == coroutine_suspended) {
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
