package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChannelFlowKt;

@Metadata(mo20734d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001a/\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a9\u0010\u000b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\f\u001a\u00020\rH@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a&\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0007\u001a\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, mo20735d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "Lkotlinx/coroutines/channels/BroadcastChannel;", "consumeAsFlow", "Lkotlinx/coroutines/channels/ReceiveChannel;", "emitAll", "", "Lkotlinx/coroutines/flow/FlowCollector;", "channel", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitAllImpl", "consume", "", "emitAllImpl$FlowKt__ChannelsKt", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "produceIn", "scope", "Lkotlinx/coroutines/CoroutineScope;", "receiveAsFlow", "kotlinx-coroutines-core"}, mo20736k = 5, mo20737mv = {1, 6, 0}, mo20739xi = 48, mo20740xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Channels.kt */
final /* synthetic */ class FlowKt__ChannelsKt {
    public static final <T> Object emitAll(FlowCollector<? super T> flowCollector, ReceiveChannel<? extends T> receiveChannel, Continuation<? super Unit> continuation) {
        Object emitAllImpl$FlowKt__ChannelsKt = emitAllImpl$FlowKt__ChannelsKt(flowCollector, receiveChannel, true, continuation);
        return emitAllImpl$FlowKt__ChannelsKt == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emitAllImpl$FlowKt__ChannelsKt : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0085 A[SYNTHETIC, Splitter:B:36:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0086 A[Catch:{ all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector<? super T> r7, kotlinx.coroutines.channels.ReceiveChannel<? extends T> r8, boolean r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0058
            if (r2 == r4) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            boolean r7 = r0.Z$0
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0056 }
        L_0x0036:
            r6 = r9
            r9 = r7
            r7 = r6
            goto L_0x005e
        L_0x003a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0042:
            boolean r7 = r0.Z$0
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0056 }
            kotlinx.coroutines.channels.ChannelResult r10 = (kotlinx.coroutines.channels.ChannelResult) r10     // Catch:{ all -> 0x0056 }
            java.lang.Object r10 = r10.m1633unboximpl()     // Catch:{ all -> 0x0056 }
            goto L_0x0070
        L_0x0056:
            r9 = move-exception
            goto L_0x009d
        L_0x0058:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.flow.FlowKt.ensureActive(r7)
        L_0x005e:
            r0.L$0 = r7     // Catch:{ all -> 0x0099 }
            r0.L$1 = r8     // Catch:{ all -> 0x0099 }
            r0.Z$0 = r9     // Catch:{ all -> 0x0099 }
            r0.label = r4     // Catch:{ all -> 0x0099 }
            java.lang.Object r10 = r8.m1640receiveCatchingJP2dKIU(r0)     // Catch:{ all -> 0x0099 }
            if (r10 != r1) goto L_0x006d
            return r1
        L_0x006d:
            r6 = r9
            r9 = r7
            r7 = r6
        L_0x0070:
            r2 = 0
            boolean r5 = kotlinx.coroutines.channels.ChannelResult.m1629isClosedimpl(r10)     // Catch:{ all -> 0x0056 }
            if (r5 == 0) goto L_0x0086
            java.lang.Throwable r9 = kotlinx.coroutines.channels.ChannelResult.m1625exceptionOrNullimpl(r10)     // Catch:{ all -> 0x0056 }
            if (r9 != 0) goto L_0x0085
            if (r7 == 0) goto L_0x0082
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r2)
        L_0x0082:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x0085:
            throw r9     // Catch:{ all -> 0x0056 }
        L_0x0086:
            java.lang.Object r10 = kotlinx.coroutines.channels.ChannelResult.m1627getOrThrowimpl(r10)     // Catch:{ all -> 0x0056 }
            r0.L$0 = r9     // Catch:{ all -> 0x0056 }
            r0.L$1 = r8     // Catch:{ all -> 0x0056 }
            r0.Z$0 = r7     // Catch:{ all -> 0x0056 }
            r0.label = r3     // Catch:{ all -> 0x0056 }
            java.lang.Object r10 = r9.emit(r10, r0)     // Catch:{ all -> 0x0056 }
            if (r10 != r1) goto L_0x0036
            return r1
        L_0x0099:
            r7 = move-exception
            r6 = r9
            r9 = r7
            r7 = r6
        L_0x009d:
            throw r9     // Catch:{ all -> 0x009e }
        L_0x009e:
            r10 = move-exception
            if (r7 == 0) goto L_0x00a4
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r9)
        L_0x00a4:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector, kotlinx.coroutines.channels.ReceiveChannel, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <T> Flow<T> receiveAsFlow(ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow<>(receiveChannel, false, (CoroutineContext) null, 0, (BufferOverflow) null, 28, (DefaultConstructorMarker) null);
    }

    public static final <T> Flow<T> consumeAsFlow(ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow<>(receiveChannel, true, (CoroutineContext) null, 0, (BufferOverflow) null, 28, (DefaultConstructorMarker) null);
    }

    public static final <T> ReceiveChannel<T> produceIn(Flow<? extends T> flow, CoroutineScope coroutineScope) {
        return ChannelFlowKt.asChannelFlow(flow).produceImpl(coroutineScope);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "'BroadcastChannel' is obsolete and all corresponding operators are deprecated in the favour of StateFlow and SharedFlow")
    public static final <T> Flow<T> asFlow(BroadcastChannel<T> broadcastChannel) {
        return new FlowKt__ChannelsKt$asFlow$$inlined$unsafeFlow$1(broadcastChannel);
    }
}
