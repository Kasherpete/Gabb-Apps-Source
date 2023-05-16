package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 176)
@DebugMetadata(mo21448c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", mo21449f = "Channels.common.kt", mo21450i = {0, 0}, mo21451l = {129}, mo21452m = "consumeEach", mo21453n = {"action", "channel$iv"}, mo21454s = {"L$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$consumeEach$3<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__Channels_commonKt$consumeEach$3(Continuation<? super ChannelsKt__Channels_commonKt$consumeEach$3> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt__Channels_commonKt.consumeEach((BroadcastChannel) null, (Function1) null, (Continuation<? super Unit>) this);
    }
}
