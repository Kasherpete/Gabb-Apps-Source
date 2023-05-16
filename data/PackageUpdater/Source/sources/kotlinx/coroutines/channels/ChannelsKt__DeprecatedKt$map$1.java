package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@"}, mo20735d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1", mo21449f = "Deprecated.kt", mo21450i = {0, 0, 1, 1, 2, 2}, mo21451l = {487, 333, 333}, mo21452m = "invokeSuspend", mo21453n = {"$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv"}, mo21454s = {"L$0", "L$2", "L$0", "L$2", "L$0", "L$2"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$map$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel<E> $this_map;
    final /* synthetic */ Function2<E, Continuation<? super R>, Object> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$map$1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super ChannelsKt__DeprecatedKt$map$1> continuation) {
        super(2, continuation);
        this.$this_map = receiveChannel;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$map$1 channelsKt__DeprecatedKt$map$1 = new ChannelsKt__DeprecatedKt$map$1(this.$this_map, this.$transform, continuation);
        channelsKt__DeprecatedKt$map$1.L$0 = obj;
        return channelsKt__DeprecatedKt$map$1;
    }

    public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$map$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008e A[Catch:{ all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x0060
            if (r1 == r5) goto L_0x004c
            if (r1 == r4) goto L_0x002f
            if (r1 != r3) goto L_0x0027
            java.lang.Object r1 = r11.L$3
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r11.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r11.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x00ca }
            r12 = r8
            goto L_0x0072
        L_0x0027:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x002f:
            java.lang.Object r1 = r11.L$4
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            java.lang.Object r6 = r11.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r11.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r11.L$1
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r9 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x0048 }
            goto L_0x00aa
        L_0x0048:
            r11 = move-exception
            r6 = r7
            goto L_0x00cb
        L_0x004c:
            java.lang.Object r1 = r11.L$3
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r11.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r11.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x00ca }
            goto L_0x0086
        L_0x0060:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            kotlinx.coroutines.channels.ReceiveChannel<E> r6 = r11.$this_map
            kotlin.jvm.functions.Function2<E, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r1 = r11.$transform
            kotlinx.coroutines.channels.ChannelIterator r7 = r6.iterator()     // Catch:{ all -> 0x00ca }
            r10 = r7
            r7 = r1
            r1 = r10
        L_0x0072:
            r11.L$0 = r12     // Catch:{ all -> 0x00ca }
            r11.L$1 = r7     // Catch:{ all -> 0x00ca }
            r11.L$2 = r6     // Catch:{ all -> 0x00ca }
            r11.L$3 = r1     // Catch:{ all -> 0x00ca }
            r11.label = r5     // Catch:{ all -> 0x00ca }
            java.lang.Object r8 = r1.hasNext(r11)     // Catch:{ all -> 0x00ca }
            if (r8 != r0) goto L_0x0083
            return r0
        L_0x0083:
            r10 = r8
            r8 = r12
            r12 = r10
        L_0x0086:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ all -> 0x00ca }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x00ca }
            if (r12 == 0) goto L_0x00c2
            java.lang.Object r12 = r1.next()     // Catch:{ all -> 0x00ca }
            r11.L$0 = r8     // Catch:{ all -> 0x00ca }
            r11.L$1 = r7     // Catch:{ all -> 0x00ca }
            r11.L$2 = r6     // Catch:{ all -> 0x00ca }
            r11.L$3 = r1     // Catch:{ all -> 0x00ca }
            r11.L$4 = r8     // Catch:{ all -> 0x00ca }
            r11.label = r4     // Catch:{ all -> 0x00ca }
            java.lang.Object r12 = r7.invoke(r12, r11)     // Catch:{ all -> 0x00ca }
            if (r12 != r0) goto L_0x00a5
            return r0
        L_0x00a5:
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r1
            r1 = r9
        L_0x00aa:
            r11.L$0 = r9     // Catch:{ all -> 0x0048 }
            r11.L$1 = r8     // Catch:{ all -> 0x0048 }
            r11.L$2 = r7     // Catch:{ all -> 0x0048 }
            r11.L$3 = r6     // Catch:{ all -> 0x0048 }
            r11.L$4 = r2     // Catch:{ all -> 0x0048 }
            r11.label = r3     // Catch:{ all -> 0x0048 }
            java.lang.Object r12 = r1.send(r12, r11)     // Catch:{ all -> 0x0048 }
            if (r12 != r0) goto L_0x00bd
            return r0
        L_0x00bd:
            r1 = r6
            r6 = r7
            r7 = r8
            r12 = r9
            goto L_0x0072
        L_0x00c2:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00ca }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r2)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00ca:
            r11 = move-exception
        L_0x00cb:
            throw r11     // Catch:{ all -> 0x00cc }
        L_0x00cc:
            r12 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
