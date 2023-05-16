package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H@"}, mo20735d2 = {"<anonymous>", "", "E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2", mo21449f = "Deprecated.kt", mo21450i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, mo21451l = {487, 469, 471}, mo21452m = "invokeSuspend", mo21453n = {"$this$produce", "otherIterator", "$this$consume$iv$iv", "$this$produce", "otherIterator", "$this$consume$iv$iv", "element1", "$this$produce", "otherIterator", "$this$consume$iv$iv"}, mo21454s = {"L$0", "L$1", "L$3", "L$0", "L$1", "L$3", "L$5", "L$0", "L$1", "L$3"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$zip$2 extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel<R> $other;
    final /* synthetic */ ReceiveChannel<E> $this_zip;
    final /* synthetic */ Function2<E, R, V> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$zip$2(ReceiveChannel<? extends R> receiveChannel, ReceiveChannel<? extends E> receiveChannel2, Function2<? super E, ? super R, ? extends V> function2, Continuation<? super ChannelsKt__DeprecatedKt$zip$2> continuation) {
        super(2, continuation);
        this.$other = receiveChannel;
        this.$this_zip = receiveChannel2;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$zip$2 channelsKt__DeprecatedKt$zip$2 = new ChannelsKt__DeprecatedKt$zip$2(this.$other, this.$this_zip, this.$transform, continuation);
        channelsKt__DeprecatedKt$zip$2.L$0 = obj;
        return channelsKt__DeprecatedKt$zip$2;
    }

    public final Object invoke(ProducerScope<? super V> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$zip$2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a2 A[Catch:{ all -> 0x00f4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ca A[Catch:{ all -> 0x0050 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x006c
            if (r1 == r4) goto L_0x0054
            if (r1 == r3) goto L_0x0032
            if (r1 != r2) goto L_0x002a
            java.lang.Object r1 = r12.L$4
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r12.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r12.L$2
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x00f4 }
            goto L_0x0085
        L_0x002a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0032:
            java.lang.Object r1 = r12.L$5
            java.lang.Object r6 = r12.L$4
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r12.L$3
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r12.L$2
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r9 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r10 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0050 }
            r11 = r6
            r6 = r1
            r1 = r11
            goto L_0x00c2
        L_0x0050:
            r12 = move-exception
            r6 = r7
            goto L_0x00f5
        L_0x0054:
            java.lang.Object r1 = r12.L$4
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r12.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r12.L$2
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x00f4 }
            goto L_0x009a
        L_0x006c:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
            kotlinx.coroutines.channels.ReceiveChannel<R> r1 = r12.$other
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            kotlinx.coroutines.channels.ReceiveChannel<E> r6 = r12.$this_zip
            kotlin.jvm.functions.Function2<E, R, V> r7 = r12.$transform
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch:{ all -> 0x00f4 }
            r9 = r13
            r11 = r8
            r8 = r1
            r1 = r11
        L_0x0085:
            r12.L$0 = r9     // Catch:{ all -> 0x00f4 }
            r12.L$1 = r8     // Catch:{ all -> 0x00f4 }
            r12.L$2 = r7     // Catch:{ all -> 0x00f4 }
            r12.L$3 = r6     // Catch:{ all -> 0x00f4 }
            r12.L$4 = r1     // Catch:{ all -> 0x00f4 }
            r12.L$5 = r5     // Catch:{ all -> 0x00f4 }
            r12.label = r4     // Catch:{ all -> 0x00f4 }
            java.lang.Object r13 = r1.hasNext(r12)     // Catch:{ all -> 0x00f4 }
            if (r13 != r0) goto L_0x009a
            return r0
        L_0x009a:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x00f4 }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x00f4 }
            if (r13 == 0) goto L_0x00ec
            java.lang.Object r13 = r1.next()     // Catch:{ all -> 0x00f4 }
            r12.L$0 = r9     // Catch:{ all -> 0x00f4 }
            r12.L$1 = r8     // Catch:{ all -> 0x00f4 }
            r12.L$2 = r7     // Catch:{ all -> 0x00f4 }
            r12.L$3 = r6     // Catch:{ all -> 0x00f4 }
            r12.L$4 = r1     // Catch:{ all -> 0x00f4 }
            r12.L$5 = r13     // Catch:{ all -> 0x00f4 }
            r12.label = r3     // Catch:{ all -> 0x00f4 }
            java.lang.Object r10 = r8.hasNext(r12)     // Catch:{ all -> 0x00f4 }
            if (r10 != r0) goto L_0x00bb
            return r0
        L_0x00bb:
            r11 = r6
            r6 = r13
            r13 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r11
        L_0x00c2:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x0050 }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r13 == 0) goto L_0x00e7
            java.lang.Object r13 = r9.next()     // Catch:{ all -> 0x0050 }
            java.lang.Object r13 = r8.invoke(r6, r13)     // Catch:{ all -> 0x0050 }
            r12.L$0 = r10     // Catch:{ all -> 0x0050 }
            r12.L$1 = r9     // Catch:{ all -> 0x0050 }
            r12.L$2 = r8     // Catch:{ all -> 0x0050 }
            r12.L$3 = r7     // Catch:{ all -> 0x0050 }
            r12.L$4 = r1     // Catch:{ all -> 0x0050 }
            r12.L$5 = r5     // Catch:{ all -> 0x0050 }
            r12.label = r2     // Catch:{ all -> 0x0050 }
            java.lang.Object r13 = r10.send(r13, r12)     // Catch:{ all -> 0x0050 }
            if (r13 != r0) goto L_0x00e7
            return r0
        L_0x00e7:
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            goto L_0x0085
        L_0x00ec:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00f4 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r5)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x00f4:
            r12 = move-exception
        L_0x00f5:
            throw r12     // Catch:{ all -> 0x00f6 }
        L_0x00f6:
            r13 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
