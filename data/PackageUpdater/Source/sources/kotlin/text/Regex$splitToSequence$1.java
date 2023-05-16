package kotlin.text;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlin.text.Regex$splitToSequence$1", mo21449f = "Regex.kt", mo21450i = {1, 1, 1}, mo21451l = {276, 284, 288}, mo21452m = "invokeSuspend", mo21453n = {"$this$sequence", "matcher", "splitCount"}, mo21454s = {"L$0", "L$1", "I$0"})
/* compiled from: Regex.kt */
final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $limit;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Regex this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i, Continuation<? super Regex$splitToSequence$1> continuation) {
        super(2, continuation);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$limit = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.this$0, this.$input, this.$limit, continuation);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
        return ((Regex$splitToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a2 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x0034
            if (r1 == r5) goto L_0x002f
            if (r1 == r4) goto L_0x001f
            if (r1 != r3) goto L_0x0017
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00a3
        L_0x0017:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x001f:
            int r1 = r9.I$0
            java.lang.Object r2 = r9.L$1
            java.util.regex.Matcher r2 = (java.util.regex.Matcher) r2
            java.lang.Object r6 = r9.L$0
            kotlin.sequences.SequenceScope r6 = (kotlin.sequences.SequenceScope) r6
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r1
            r1 = r2
            goto L_0x0074
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00b8
        L_0x0034:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlin.sequences.SequenceScope r10 = (kotlin.sequences.SequenceScope) r10
            kotlin.text.Regex r1 = r9.this$0
            java.util.regex.Pattern r1 = r1.nativePattern
            java.lang.CharSequence r6 = r9.$input
            java.util.regex.Matcher r1 = r1.matcher(r6)
            int r6 = r9.$limit
            if (r6 == r5) goto L_0x00a6
            boolean r6 = r1.find()
            if (r6 != 0) goto L_0x0052
            goto L_0x00a6
        L_0x0052:
            r6 = r10
            r10 = r2
        L_0x0054:
            java.lang.CharSequence r7 = r9.$input
            int r8 = r1.start()
            java.lang.CharSequence r2 = r7.subSequence(r2, r8)
            java.lang.String r2 = r2.toString()
            r7 = r9
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r9.L$0 = r6
            r9.L$1 = r1
            r9.I$0 = r10
            r9.label = r4
            java.lang.Object r2 = r6.yield(r2, r7)
            if (r2 != r0) goto L_0x0074
            return r0
        L_0x0074:
            int r2 = r1.end()
            int r10 = r10 + r5
            int r7 = r9.$limit
            int r7 = r7 - r5
            if (r10 == r7) goto L_0x0084
            boolean r7 = r1.find()
            if (r7 != 0) goto L_0x0054
        L_0x0084:
            java.lang.CharSequence r10 = r9.$input
            int r1 = r10.length()
            java.lang.CharSequence r10 = r10.subSequence(r2, r1)
            java.lang.String r10 = r10.toString()
            r1 = r9
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r2 = 0
            r9.L$0 = r2
            r9.L$1 = r2
            r9.label = r3
            java.lang.Object r9 = r6.yield(r10, r1)
            if (r9 != r0) goto L_0x00a3
            return r0
        L_0x00a3:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x00a6:
            java.lang.CharSequence r1 = r9.$input
            java.lang.String r1 = r1.toString()
            r2 = r9
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r9.label = r5
            java.lang.Object r9 = r10.yield(r1, r2)
            if (r9 != r0) goto L_0x00b8
            return r0
        L_0x00b8:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.Regex$splitToSequence$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
