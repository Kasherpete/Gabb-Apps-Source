package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@"}, mo20735d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1", mo21449f = "Deprecated.kt", mo21450i = {0, 0, 1, 1, 2, 2}, mo21451l = {344, 345, 345}, mo21452m = "invokeSuspend", mo21453n = {"$this$produce", "index", "$this$produce", "index", "$this$produce", "index"}, mo21454s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$mapIndexed$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel<E> $this_mapIndexed;
    final /* synthetic */ Function3<Integer, E, Continuation<? super R>, Object> $transform;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$mapIndexed$1(ReceiveChannel<? extends E> receiveChannel, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super ChannelsKt__DeprecatedKt$mapIndexed$1> continuation) {
        super(2, continuation);
        this.$this_mapIndexed = receiveChannel;
        this.$transform = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$mapIndexed$1 channelsKt__DeprecatedKt$mapIndexed$1 = new ChannelsKt__DeprecatedKt$mapIndexed$1(this.$this_mapIndexed, this.$transform, continuation);
        channelsKt__DeprecatedKt$mapIndexed$1.L$0 = obj;
        return channelsKt__DeprecatedKt$mapIndexed$1;
    }

    public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$mapIndexed$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0048
            if (r1 == r4) goto L_0x003a
            if (r1 == r3) goto L_0x0028
            if (r1 != r2) goto L_0x0020
            int r1 = r11.I$0
            java.lang.Object r5 = r11.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r6
            goto L_0x0056
        L_0x0020:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0028:
            int r1 = r11.I$0
            java.lang.Object r5 = r11.L$2
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            java.lang.Object r6 = r11.L$1
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0094
        L_0x003a:
            int r1 = r11.I$0
            java.lang.Object r5 = r11.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x006b
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            r1 = 0
            kotlinx.coroutines.channels.ReceiveChannel<E> r5 = r11.$this_mapIndexed
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
        L_0x0056:
            r6 = r11
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r11.L$0 = r12
            r11.L$1 = r5
            r11.I$0 = r1
            r11.label = r4
            java.lang.Object r6 = r5.hasNext(r6)
            if (r6 != r0) goto L_0x0068
            return r0
        L_0x0068:
            r10 = r6
            r6 = r12
            r12 = r10
        L_0x006b:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x00ac
            java.lang.Object r12 = r5.next()
            kotlin.jvm.functions.Function3<java.lang.Integer, E, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r7 = r11.$transform
            int r8 = r1 + 1
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            r11.L$0 = r6
            r11.L$1 = r5
            r11.L$2 = r6
            r11.I$0 = r8
            r11.label = r3
            java.lang.Object r12 = r7.invoke(r1, r12, r11)
            if (r12 != r0) goto L_0x0090
            return r0
        L_0x0090:
            r7 = r6
            r1 = r8
            r6 = r5
            r5 = r7
        L_0x0094:
            r8 = r11
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r11.L$0 = r7
            r11.L$1 = r6
            r9 = 0
            r11.L$2 = r9
            r11.I$0 = r1
            r11.label = r2
            java.lang.Object r12 = r5.send(r12, r8)
            if (r12 != r0) goto L_0x00a9
            return r0
        L_0x00a9:
            r5 = r6
            r12 = r7
            goto L_0x0056
        L_0x00ac:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
