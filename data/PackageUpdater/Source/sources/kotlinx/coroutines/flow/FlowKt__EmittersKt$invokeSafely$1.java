package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.flow.FlowKt__EmittersKt", mo21449f = "Emitters.kt", mo21450i = {0}, mo21451l = {216}, mo21452m = "invokeSafely$FlowKt__EmittersKt", mo21453n = {"cause"}, mo21454s = {"L$0"})
/* compiled from: Emitters.kt */
final class FlowKt__EmittersKt$invokeSafely$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    FlowKt__EmittersKt$invokeSafely$1(Continuation<? super FlowKt__EmittersKt$invokeSafely$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt((FlowCollector) null, (Function3) null, (Throwable) null, this);
    }
}
