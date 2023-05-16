package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@"}, mo20735d2 = {"<anonymous>", "", "S", "T", "Lkotlin/sequences/SequenceScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlin.sequences.SequencesKt___SequencesKt$runningReduceIndexed$1", mo21449f = "_Sequences.kt", mo21450i = {0, 0, 0, 1, 1, 1, 1}, mo21451l = {2202, 2206}, mo21452m = "invokeSuspend", mo21453n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator", "index"}, mo21454s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "I$0"})
/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$runningReduceIndexed$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<Integer, S, T, S> $operation;
    final /* synthetic */ Sequence<T> $this_runningReduceIndexed;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$runningReduceIndexed$1(Sequence<? extends T> sequence, Function3<? super Integer, ? super S, ? super T, ? extends S> function3, Continuation<? super SequencesKt___SequencesKt$runningReduceIndexed$1> continuation) {
        super(2, continuation);
        this.$this_runningReduceIndexed = sequence;
        this.$operation = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt___SequencesKt$runningReduceIndexed$1 sequencesKt___SequencesKt$runningReduceIndexed$1 = new SequencesKt___SequencesKt$runningReduceIndexed$1(this.$this_runningReduceIndexed, this.$operation, continuation);
        sequencesKt___SequencesKt$runningReduceIndexed$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningReduceIndexed$1;
    }

    public final Object invoke(SequenceScope<? super S> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningReduceIndexed$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: kotlin.sequences.SequenceScope} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0037
            if (r1 == r3) goto L_0x0029
            if (r1 != r2) goto L_0x0021
            int r1 = r9.I$0
            java.lang.Object r3 = r9.L$2
            java.lang.Object r4 = r9.L$1
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r9.L$0
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r8 = r3
            r3 = r1
            r1 = r8
            goto L_0x0061
        L_0x0021:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0029:
            java.lang.Object r1 = r9.L$2
            java.lang.Object r4 = r9.L$1
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r9.L$0
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0061
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            r5 = r10
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            kotlin.sequences.Sequence<T> r10 = r9.$this_runningReduceIndexed
            java.util.Iterator r4 = r10.iterator()
            boolean r10 = r4.hasNext()
            if (r10 == 0) goto L_0x0093
            java.lang.Object r1 = r4.next()
            r10 = r9
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r9.L$0 = r5
            r9.L$1 = r4
            r9.L$2 = r1
            r9.label = r3
            java.lang.Object r10 = r5.yield(r1, r10)
            if (r10 != r0) goto L_0x0061
            return r0
        L_0x0061:
            boolean r10 = r4.hasNext()
            if (r10 == 0) goto L_0x0093
            kotlin.jvm.functions.Function3<java.lang.Integer, S, T, S> r10 = r9.$operation
            int r6 = r3 + 1
            if (r3 >= 0) goto L_0x0070
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x0070:
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            java.lang.Object r7 = r4.next()
            java.lang.Object r3 = r10.invoke(r3, r1, r7)
            r10 = r9
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r9.L$0 = r5
            r9.L$1 = r4
            r9.L$2 = r3
            r9.I$0 = r6
            r9.label = r2
            java.lang.Object r10 = r5.yield(r3, r10)
            if (r10 != r0) goto L_0x0090
            return r0
        L_0x0090:
            r1 = r3
            r3 = r6
            goto L_0x0061
        L_0x0093:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$runningReduceIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
