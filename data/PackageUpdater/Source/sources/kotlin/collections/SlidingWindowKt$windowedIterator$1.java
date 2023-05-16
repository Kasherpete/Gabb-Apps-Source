package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H@"}, mo20735d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", ""}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", mo21449f = "SlidingWindow.kt", mo21450i = {0, 0, 0, 2, 2, 3, 3}, mo21451l = {34, 40, 49, 55, 58}, mo21452m = "invokeSuspend", mo21453n = {"$this$iterator", "buffer", "gap", "$this$iterator", "buffer", "$this$iterator", "buffer"}, mo21454s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: SlidingWindow.kt */
final class SlidingWindowKt$windowedIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator<T> $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator<? extends T> it, boolean z, boolean z2, Continuation<? super SlidingWindowKt$windowedIterator$1> continuation) {
        super(2, continuation);
        this.$size = i;
        this.$step = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.L$0 = obj;
        return slidingWindowKt$windowedIterator$1;
    }

    public final Object invoke(SequenceScope<? super List<? extends T>> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SlidingWindowKt$windowedIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0093, code lost:
        r11.L$0 = r4;
        r11.L$1 = r3;
        r11.L$2 = r2;
        r11.I$0 = r8;
        r11.label = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a4, code lost:
        if (r4.yield(r3, r11) != r0) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a6, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010b, code lost:
        if (r11.$reuseBuffer == false) goto L_0x0111;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010d, code lost:
        r12 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0111, code lost:
        r12 = new java.util.ArrayList(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x011b, code lost:
        r11.L$0 = r8;
        r11.L$1 = r5;
        r11.L$2 = r1;
        r11.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x012a, code lost:
        if (r8.yield(r12, r11) != r0) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012c, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0141  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0057
            if (r1 == r6) goto L_0x0044
            if (r1 == r5) goto L_0x003f
            if (r1 == r4) goto L_0x002e
            if (r1 == r3) goto L_0x0021
            if (r1 != r2) goto L_0x0019
            goto L_0x003f
        L_0x0019:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0021:
            java.lang.Object r1 = r11.L$1
            kotlin.collections.RingBuffer r1 = (kotlin.collections.RingBuffer) r1
            java.lang.Object r4 = r11.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0165
        L_0x002e:
            java.lang.Object r1 = r11.L$2
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r5 = r11.L$1
            kotlin.collections.RingBuffer r5 = (kotlin.collections.RingBuffer) r5
            java.lang.Object r8 = r11.L$0
            kotlin.sequences.SequenceScope r8 = (kotlin.sequences.SequenceScope) r8
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x012d
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0187
        L_0x0044:
            int r1 = r11.I$0
            java.lang.Object r2 = r11.L$2
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r11.L$1
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            java.lang.Object r4 = r11.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r1
            goto L_0x00a7
        L_0x0057:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            kotlin.sequences.SequenceScope r12 = (kotlin.sequences.SequenceScope) r12
            int r1 = r11.$size
            r8 = 1024(0x400, float:1.435E-42)
            int r1 = kotlin.ranges.RangesKt.coerceAtMost((int) r1, (int) r8)
            int r8 = r11.$step
            int r9 = r11.$size
            int r8 = r8 - r9
            if (r8 < 0) goto L_0x00e1
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r1)
            r1 = 0
            java.util.Iterator<T> r3 = r11.$iterator
            r4 = r12
            r10 = r3
            r3 = r2
            r2 = r10
        L_0x0079:
            boolean r12 = r2.hasNext()
            if (r12 == 0) goto L_0x00b9
            java.lang.Object r12 = r2.next()
            if (r1 <= 0) goto L_0x0088
            int r1 = r1 + -1
            goto L_0x0079
        L_0x0088:
            r3.add(r12)
            int r12 = r3.size()
            int r9 = r11.$size
            if (r12 != r9) goto L_0x0079
            r12 = r11
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r11.L$0 = r4
            r11.L$1 = r3
            r11.L$2 = r2
            r11.I$0 = r8
            r11.label = r6
            java.lang.Object r12 = r4.yield(r3, r12)
            if (r12 != r0) goto L_0x00a7
            return r0
        L_0x00a7:
            boolean r12 = r11.$reuseBuffer
            if (r12 == 0) goto L_0x00af
            r3.clear()
            goto L_0x00b7
        L_0x00af:
            java.util.ArrayList r12 = new java.util.ArrayList
            int r1 = r11.$size
            r12.<init>(r1)
            r3 = r12
        L_0x00b7:
            r1 = r8
            goto L_0x0079
        L_0x00b9:
            r12 = r3
            java.util.Collection r12 = (java.util.Collection) r12
            boolean r12 = r12.isEmpty()
            r12 = r12 ^ r6
            if (r12 == 0) goto L_0x0187
            boolean r12 = r11.$partialWindows
            if (r12 != 0) goto L_0x00cf
            int r12 = r3.size()
            int r1 = r11.$size
            if (r12 != r1) goto L_0x0187
        L_0x00cf:
            r12 = r11
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r11.L$0 = r7
            r11.L$1 = r7
            r11.L$2 = r7
            r11.label = r5
            java.lang.Object r11 = r4.yield(r3, r12)
            if (r11 != r0) goto L_0x0187
            return r0
        L_0x00e1:
            kotlin.collections.RingBuffer r5 = new kotlin.collections.RingBuffer
            r5.<init>(r1)
            java.util.Iterator<T> r1 = r11.$iterator
            r8 = r12
        L_0x00e9:
            boolean r12 = r1.hasNext()
            if (r12 == 0) goto L_0x0133
            java.lang.Object r12 = r1.next()
            r5.add(r12)
            boolean r12 = r5.isFull()
            if (r12 == 0) goto L_0x00e9
            int r12 = r5.size()
            int r9 = r11.$size
            if (r12 >= r9) goto L_0x0109
            kotlin.collections.RingBuffer r5 = r5.expanded(r9)
            goto L_0x00e9
        L_0x0109:
            boolean r12 = r11.$reuseBuffer
            if (r12 == 0) goto L_0x0111
            r12 = r5
            java.util.List r12 = (java.util.List) r12
            goto L_0x011b
        L_0x0111:
            java.util.ArrayList r12 = new java.util.ArrayList
            r9 = r5
            java.util.Collection r9 = (java.util.Collection) r9
            r12.<init>(r9)
            java.util.List r12 = (java.util.List) r12
        L_0x011b:
            r9 = r11
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r11.L$0 = r8
            r11.L$1 = r5
            r11.L$2 = r1
            r11.label = r4
            java.lang.Object r12 = r8.yield(r12, r9)
            if (r12 != r0) goto L_0x012d
            return r0
        L_0x012d:
            int r12 = r11.$step
            r5.removeFirst(r12)
            goto L_0x00e9
        L_0x0133:
            boolean r12 = r11.$partialWindows
            if (r12 == 0) goto L_0x0187
            r1 = r5
            r4 = r8
        L_0x0139:
            int r12 = r1.size()
            int r5 = r11.$step
            if (r12 <= r5) goto L_0x016b
            boolean r12 = r11.$reuseBuffer
            if (r12 == 0) goto L_0x0149
            r12 = r1
            java.util.List r12 = (java.util.List) r12
            goto L_0x0153
        L_0x0149:
            java.util.ArrayList r12 = new java.util.ArrayList
            r5 = r1
            java.util.Collection r5 = (java.util.Collection) r5
            r12.<init>(r5)
            java.util.List r12 = (java.util.List) r12
        L_0x0153:
            r5 = r11
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r11.L$0 = r4
            r11.L$1 = r1
            r11.L$2 = r7
            r11.label = r3
            java.lang.Object r12 = r4.yield(r12, r5)
            if (r12 != r0) goto L_0x0165
            return r0
        L_0x0165:
            int r12 = r11.$step
            r1.removeFirst(r12)
            goto L_0x0139
        L_0x016b:
            r12 = r1
            java.util.Collection r12 = (java.util.Collection) r12
            boolean r12 = r12.isEmpty()
            r12 = r12 ^ r6
            if (r12 == 0) goto L_0x0187
            r12 = r11
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r11.L$0 = r7
            r11.L$1 = r7
            r11.L$2 = r7
            r11.label = r2
            java.lang.Object r11 = r4.yield(r1, r12)
            if (r11 != r0) goto L_0x0187
            return r0
        L_0x0187:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
