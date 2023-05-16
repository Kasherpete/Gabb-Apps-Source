package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H@"}, mo20735d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3", mo21449f = "Merge.kt", mo21450i = {}, mo21451l = {27}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: Merge.kt */
final class ChannelFlowTransformLatest$flowCollect$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector<R> $collector;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ChannelFlowTransformLatest<T, R> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelFlowTransformLatest$flowCollect$3(ChannelFlowTransformLatest<T, R> channelFlowTransformLatest, FlowCollector<? super R> flowCollector, Continuation<? super ChannelFlowTransformLatest$flowCollect$3> continuation) {
        super(2, continuation);
        this.this$0 = channelFlowTransformLatest;
        this.$collector = flowCollector;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelFlowTransformLatest$flowCollect$3 channelFlowTransformLatest$flowCollect$3 = new ChannelFlowTransformLatest$flowCollect$3(this.this$0, this.$collector, continuation);
        channelFlowTransformLatest$flowCollect$3.L$0 = obj;
        return channelFlowTransformLatest$flowCollect$3;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChannelFlowTransformLatest$flowCollect$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Flow flow = this.this$0.flow;
            final ChannelFlowTransformLatest<T, R> channelFlowTransformLatest = this.this$0;
            final FlowCollector<R> flowCollector = this.$collector;
            this.label = 1;
            if (flow.collect(new FlowCollector() {
                /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
                /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(final T r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1
                        if (r0 == 0) goto L_0x0014
                        r0 = r9
                        kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1 r0 = (kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L_0x0014
                        int r9 = r0.label
                        int r9 = r9 - r2
                        r0.label = r9
                        goto L_0x0019
                    L_0x0014:
                        kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1 r0 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1
                        r0.<init>(r7, r9)
                    L_0x0019:
                        java.lang.Object r9 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L_0x003f
                        if (r2 != r3) goto L_0x0037
                        java.lang.Object r7 = r0.L$2
                        kotlinx.coroutines.Job r7 = (kotlinx.coroutines.Job) r7
                        java.lang.Object r7 = r0.L$1
                        java.lang.Object r8 = r0.L$0
                        kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1 r8 = (kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3.C14381) r8
                        kotlin.ResultKt.throwOnFailure(r9)
                        r6 = r8
                        r8 = r7
                        r7 = r6
                        goto L_0x0063
                    L_0x0037:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L_0x003f:
                        kotlin.ResultKt.throwOnFailure(r9)
                        kotlin.jvm.internal.Ref$ObjectRef<kotlinx.coroutines.Job> r9 = r1
                        T r9 = r9.element
                        kotlinx.coroutines.Job r9 = (kotlinx.coroutines.Job) r9
                        if (r9 == 0) goto L_0x0063
                        kotlinx.coroutines.flow.internal.ChildCancelledException r2 = new kotlinx.coroutines.flow.internal.ChildCancelledException
                        r2.<init>()
                        java.util.concurrent.CancellationException r2 = (java.util.concurrent.CancellationException) r2
                        r9.cancel((java.util.concurrent.CancellationException) r2)
                        r0.L$0 = r7
                        r0.L$1 = r8
                        r0.L$2 = r9
                        r0.label = r3
                        java.lang.Object r9 = r9.join(r0)
                        if (r9 != r1) goto L_0x0063
                        return r1
                    L_0x0063:
                        kotlin.jvm.internal.Ref$ObjectRef<kotlinx.coroutines.Job> r9 = r1
                        kotlinx.coroutines.CoroutineScope r0 = r8
                        r1 = 0
                        kotlinx.coroutines.CoroutineStart r2 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
                        kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2 r3 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2
                        kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest<T, R> r4 = r5
                        kotlinx.coroutines.flow.FlowCollector<R> r7 = r6
                        r5 = 0
                        r3.<init>(r4, r7, r8, r5)
                        kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
                        r4 = 1
                        kotlinx.coroutines.Job r7 = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r0, r1, r2, r3, r4, r5)
                        r9.element = r7
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3.C14381.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
