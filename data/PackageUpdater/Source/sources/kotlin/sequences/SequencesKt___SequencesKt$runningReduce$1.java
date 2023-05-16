package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@"}, mo20735d2 = {"<anonymous>", "", "S", "T", "Lkotlin/sequences/SequenceScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlin.sequences.SequencesKt___SequencesKt$runningReduce$1", mo21449f = "_Sequences.kt", mo21450i = {0, 0, 0, 1, 1, 1}, mo21451l = {2173, 2176}, mo21452m = "invokeSuspend", mo21453n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator"}, mo21454s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$runningReduce$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<S, T, S> $operation;
    final /* synthetic */ Sequence<T> $this_runningReduce;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$runningReduce$1(Sequence<? extends T> sequence, Function2<? super S, ? super T, ? extends S> function2, Continuation<? super SequencesKt___SequencesKt$runningReduce$1> continuation) {
        super(2, continuation);
        this.$this_runningReduce = sequence;
        this.$operation = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt___SequencesKt$runningReduce$1 sequencesKt___SequencesKt$runningReduce$1 = new SequencesKt___SequencesKt$runningReduce$1(this.$this_runningReduce, this.$operation, continuation);
        sequencesKt___SequencesKt$runningReduce$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningReduce$1;
    }

    public final Object invoke(SequenceScope<? super S> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningReduce$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: kotlin.sequences.SequenceScope} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0025
            if (r1 == r3) goto L_0x0017
            if (r1 != r2) goto L_0x000f
            goto L_0x0017
        L_0x000f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0017:
            java.lang.Object r1 = r6.L$2
            java.lang.Object r3 = r6.L$1
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r6.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0050
        L_0x0025:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            r4 = r7
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.sequences.Sequence<T> r7 = r6.$this_runningReduce
            java.util.Iterator r7 = r7.iterator()
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0072
            java.lang.Object r1 = r7.next()
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6.L$0 = r4
            r6.L$1 = r7
            r6.L$2 = r1
            r6.label = r3
            java.lang.Object r3 = r4.yield(r1, r5)
            if (r3 != r0) goto L_0x004f
            return r0
        L_0x004f:
            r3 = r7
        L_0x0050:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x0072
            kotlin.jvm.functions.Function2<S, T, S> r7 = r6.$operation
            java.lang.Object r5 = r3.next()
            java.lang.Object r1 = r7.invoke(r1, r5)
            r7 = r6
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r6.L$0 = r4
            r6.L$1 = r3
            r6.L$2 = r1
            r6.label = r2
            java.lang.Object r7 = r4.yield(r1, r7)
            if (r7 != r0) goto L_0x0050
            return r0
        L_0x0072:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$runningReduce$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
