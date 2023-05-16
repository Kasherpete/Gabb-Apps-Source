package kotlinx.coroutines.channels;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", mo21449f = "Deprecated.kt", mo21450i = {0, 0, 0, 1, 1, 1, 1}, mo21451l = {434, 436}, mo21452m = "minWith", mo21453n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "min"}, mo21454s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$minWith$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__DeprecatedKt$minWith$1(Continuation<? super ChannelsKt__DeprecatedKt$minWith$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt__DeprecatedKt.minWith((ReceiveChannel) null, (Comparator) null, this);
    }
}
