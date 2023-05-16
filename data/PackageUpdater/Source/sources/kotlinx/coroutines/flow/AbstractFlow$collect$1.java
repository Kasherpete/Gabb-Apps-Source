package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.flow.AbstractFlow", mo21449f = "Flow.kt", mo21450i = {0}, mo21451l = {230}, mo21452m = "collect", mo21453n = {"safeCollector"}, mo21454s = {"L$0"})
/* compiled from: Flow.kt */
final class AbstractFlow$collect$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AbstractFlow<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AbstractFlow$collect$1(AbstractFlow<T> abstractFlow, Continuation<? super AbstractFlow$collect$1> continuation) {
        super(continuation);
        this.this$0 = abstractFlow;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collect((FlowCollector) null, this);
    }
}
