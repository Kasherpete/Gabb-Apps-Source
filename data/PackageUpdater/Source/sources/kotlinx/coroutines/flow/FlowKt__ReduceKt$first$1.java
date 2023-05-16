package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", mo21449f = "Reduce.kt", mo21450i = {0, 0}, mo21451l = {183}, mo21452m = "first", mo21453n = {"result", "collector$iv"}, mo21454s = {"L$0", "L$1"})
/* compiled from: Reduce.kt */
final class FlowKt__ReduceKt$first$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    FlowKt__ReduceKt$first$1(Continuation<? super FlowKt__ReduceKt$first$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.first((Flow) null, this);
    }
}
