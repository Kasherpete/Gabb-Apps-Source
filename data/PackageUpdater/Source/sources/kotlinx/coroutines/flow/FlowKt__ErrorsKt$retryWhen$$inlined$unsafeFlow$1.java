package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function4;

@Metadata(mo20734d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, mo20735d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SafeCollector.common.kt */
public final class FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 implements Flow<T> {
    final /* synthetic */ Function4 $predicate$inlined;
    final /* synthetic */ Flow $this_retryWhen$inlined;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0079 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.C13801
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.C13801) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1
            r0.<init>(r12, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x005b
            if (r2 == r4) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            long r12 = r0.J$0
            java.lang.Object r2 = r0.L$2
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 r6 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1) r6
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00a1
        L_0x003c:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0044:
            int r12 = r0.I$0
            long r5 = r0.J$0
            java.lang.Object r13 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r13 = (kotlinx.coroutines.flow.FlowCollector) r13
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 r2 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1) r2
            kotlin.ResultKt.throwOnFailure(r14)
            r9 = r14
            r14 = r12
            r10 = r5
            r5 = r13
            r6 = r2
            r12 = r10
            r2 = r9
            goto L_0x007e
        L_0x005b:
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = r0
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            r5 = 0
        L_0x0063:
            r14 = 0
            kotlinx.coroutines.flow.Flow r2 = r12.$this_retryWhen$inlined
            r0.L$0 = r12
            r0.L$1 = r13
            r7 = 0
            r0.L$2 = r7
            r0.J$0 = r5
            r0.I$0 = r14
            r0.label = r4
            java.lang.Object r2 = kotlinx.coroutines.flow.FlowKt.catchImpl(r2, r13, r0)
            if (r2 != r1) goto L_0x007a
            return r1
        L_0x007a:
            r9 = r5
            r6 = r12
            r5 = r13
            r12 = r9
        L_0x007e:
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            if (r2 == 0) goto L_0x00af
            kotlin.jvm.functions.Function4 r14 = r6.$predicate$inlined
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r12)
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r2
            r0.J$0 = r12
            r0.label = r3
            r8 = 6
            kotlin.jvm.internal.InlineMarker.mark((int) r8)
            java.lang.Object r14 = r14.invoke(r5, r2, r7, r0)
            r7 = 7
            kotlin.jvm.internal.InlineMarker.mark((int) r7)
            if (r14 != r1) goto L_0x00a1
            return r1
        L_0x00a1:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x00ae
            r7 = 1
            long r12 = r12 + r7
            r14 = r4
            goto L_0x00af
        L_0x00ae:
            throw r2
        L_0x00af:
            r9 = r12
            r13 = r5
            r12 = r6
            r5 = r9
            if (r14 != 0) goto L_0x0063
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(Flow flow, Function4 function4) {
        this.$this_retryWhen$inlined = flow;
        this.$predicate$inlined = function4;
    }
}
