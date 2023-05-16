package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Landroid/view/View;"}, mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
@DebugMetadata(mo21448c = "androidx.core.view.ViewGroupKt$descendants$1", mo21449f = "ViewGroup.kt", mo21450i = {0, 0, 0, 1, 1}, mo21451l = {97, 99}, mo21452m = "invokeSuspend", mo21453n = {"$this$sequence", "$this$forEach$iv", "child", "$this$sequence", "$this$forEach$iv"}, mo21454s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
/* compiled from: ViewGroup.kt */
final class ViewGroupKt$descendants$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super View>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ViewGroup $this_descendants;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ViewGroupKt$descendants$1(ViewGroup viewGroup, Continuation<? super ViewGroupKt$descendants$1> continuation) {
        super(2, continuation);
        this.$this_descendants = viewGroup;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ViewGroupKt$descendants$1 viewGroupKt$descendants$1 = new ViewGroupKt$descendants$1(this.$this_descendants, continuation);
        viewGroupKt$descendants$1.L$0 = obj;
        return viewGroupKt$descendants$1;
    }

    public final Object invoke(SequenceScope<? super View> sequenceScope, Continuation<? super Unit> continuation) {
        return ((ViewGroupKt$descendants$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0096, code lost:
        if (r4 < r5) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009a, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004a, code lost:
        if (r5 > 0) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x003c
            if (r1 == r3) goto L_0x0027
            if (r1 != r2) goto L_0x001f
            int r1 = r9.I$1
            int r4 = r9.I$0
            java.lang.Object r5 = r9.L$1
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            java.lang.Object r6 = r9.L$0
            kotlin.sequences.SequenceScope r6 = (kotlin.sequences.SequenceScope) r6
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x008f
        L_0x001f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0027:
            int r1 = r9.I$1
            int r4 = r9.I$0
            java.lang.Object r5 = r9.L$2
            android.view.View r5 = (android.view.View) r5
            java.lang.Object r6 = r9.L$1
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            java.lang.Object r7 = r9.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r7
            goto L_0x006f
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlin.sequences.SequenceScope r10 = (kotlin.sequences.SequenceScope) r10
            android.view.ViewGroup r1 = r9.$this_descendants
            r4 = 0
            int r5 = r1.getChildCount()
            if (r5 <= 0) goto L_0x0098
        L_0x004c:
            int r6 = r4 + 1
            android.view.View r4 = r1.getChildAt(r4)
            java.lang.String r7 = "getChildAt(index)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r7)
            r9.L$0 = r10
            r9.L$1 = r1
            r9.L$2 = r4
            r9.I$0 = r6
            r9.I$1 = r5
            r9.label = r3
            java.lang.Object r7 = r10.yield(r4, r9)
            if (r7 != r0) goto L_0x006a
            return r0
        L_0x006a:
            r8 = r6
            r6 = r1
            r1 = r5
            r5 = r4
            r4 = r8
        L_0x006f:
            boolean r7 = r5 instanceof android.view.ViewGroup
            if (r7 == 0) goto L_0x0094
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            kotlin.sequences.Sequence r5 = androidx.core.view.ViewGroupKt.getDescendants(r5)
            r9.L$0 = r10
            r9.L$1 = r6
            r7 = 0
            r9.L$2 = r7
            r9.I$0 = r4
            r9.I$1 = r1
            r9.label = r2
            java.lang.Object r5 = r10.yieldAll(r5, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r9)
            if (r5 != r0) goto L_0x008d
            return r0
        L_0x008d:
            r5 = r6
            r6 = r10
        L_0x008f:
            r10 = r6
            r8 = r5
            r5 = r1
            r1 = r8
            goto L_0x0096
        L_0x0094:
            r5 = r1
            r1 = r6
        L_0x0096:
            if (r4 < r5) goto L_0x004c
        L_0x0098:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.ViewGroupKt$descendants$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
