package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.channels.AbstractChannel", mo21449f = "AbstractChannel.kt", mo21450i = {}, mo21451l = {633}, mo21452m = "receiveCatching-JP2dKIU", mo21453n = {}, mo21454s = {})
/* compiled from: AbstractChannel.kt */
final class AbstractChannel$receiveCatching$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AbstractChannel<E> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AbstractChannel$receiveCatching$1(AbstractChannel<E> abstractChannel, Continuation<? super AbstractChannel$receiveCatching$1> continuation) {
        super(continuation);
        this.this$0 = abstractChannel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r1 = this.this$0.m1610receiveCatchingJP2dKIU(this);
        return r1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r1 : ChannelResult.m1621boximpl(r1);
    }
}
