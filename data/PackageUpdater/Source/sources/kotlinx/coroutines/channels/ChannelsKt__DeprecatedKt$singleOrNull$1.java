package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", mo21449f = "Deprecated.kt", mo21450i = {0, 0, 1, 1}, mo21451l = {149, 152}, mo21452m = "singleOrNull", mo21453n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, mo21454s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$singleOrNull$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__DeprecatedKt$singleOrNull$1(Continuation<? super ChannelsKt__DeprecatedKt$singleOrNull$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt__DeprecatedKt.singleOrNull((ReceiveChannel) null, this);
    }
}
