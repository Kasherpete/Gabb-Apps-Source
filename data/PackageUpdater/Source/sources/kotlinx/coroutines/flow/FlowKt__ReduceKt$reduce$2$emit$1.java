package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$2", mo21449f = "Reduce.kt", mo21450i = {}, mo21451l = {25}, mo21452m = "emit", mo21453n = {}, mo21454s = {})
/* compiled from: Reduce.kt */
final class FlowKt__ReduceKt$reduce$2$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__ReduceKt$reduce$2<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__ReduceKt$reduce$2$emit$1(FlowKt__ReduceKt$reduce$2<? super T> flowKt__ReduceKt$reduce$2, Continuation<? super FlowKt__ReduceKt$reduce$2$emit$1> continuation) {
        super(continuation);
        this.this$0 = flowKt__ReduceKt$reduce$2;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
