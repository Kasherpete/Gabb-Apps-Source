package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.flow.FlowKt__CountKt", mo21449f = "Count.kt", mo21450i = {0}, mo21451l = {30}, mo21452m = "count", mo21453n = {"i"}, mo21454s = {"L$0"})
/* compiled from: Count.kt */
final class FlowKt__CountKt$count$3<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    FlowKt__CountKt$count$3(Continuation<? super FlowKt__CountKt$count$3> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.count((Flow) null, (Function2) null, this);
    }
}
