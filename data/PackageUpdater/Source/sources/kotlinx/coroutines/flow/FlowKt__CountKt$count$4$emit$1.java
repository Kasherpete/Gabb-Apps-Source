package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.flow.FlowKt__CountKt$count$4", mo21449f = "Count.kt", mo21450i = {0}, mo21451l = {31}, mo21452m = "emit", mo21453n = {"this"}, mo21454s = {"L$0"})
/* compiled from: Count.kt */
final class FlowKt__CountKt$count$4$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__CountKt$count$4<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__CountKt$count$4$emit$1(FlowKt__CountKt$count$4<? super T> flowKt__CountKt$count$4, Continuation<? super FlowKt__CountKt$count$4$emit$1> continuation) {
        super(continuation);
        this.this$0 = flowKt__CountKt$count$4;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
