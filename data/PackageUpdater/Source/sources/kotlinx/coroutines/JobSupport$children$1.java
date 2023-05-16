package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lkotlinx/coroutines/Job;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.JobSupport$children$1", mo21449f = "JobSupport.kt", mo21450i = {1, 1, 1}, mo21451l = {952, 954}, mo21452m = "invokeSuspend", mo21453n = {"$this$sequence", "this_$iv", "cur$iv"}, mo21454s = {"L$0", "L$1", "L$2"})
/* compiled from: JobSupport.kt */
final class JobSupport$children$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Job>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ JobSupport this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    JobSupport$children$1(JobSupport jobSupport, Continuation<? super JobSupport$children$1> continuation) {
        super(2, continuation);
        this.this$0 = jobSupport;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        JobSupport$children$1 jobSupport$children$1 = new JobSupport$children$1(this.this$0, continuation);
        jobSupport$children$1.L$0 = obj;
        return jobSupport$children$1;
    }

    public final Object invoke(SequenceScope<? super Job> sequenceScope, Continuation<? super Unit> continuation) {
        return ((JobSupport$children$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x002a
            if (r1 == r3) goto L_0x0026
            if (r1 != r2) goto L_0x001e
            java.lang.Object r1 = r6.L$2
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            java.lang.Object r3 = r6.L$1
            kotlinx.coroutines.internal.LockFreeLinkedListHead r3 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r3
            java.lang.Object r4 = r6.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0081
        L_0x001e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0086
        L_0x002a:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            kotlinx.coroutines.JobSupport r1 = r6.this$0
            java.lang.Object r1 = r1.getState$kotlinx_coroutines_core()
            boolean r4 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r4 == 0) goto L_0x004b
            kotlinx.coroutines.ChildHandleNode r1 = (kotlinx.coroutines.ChildHandleNode) r1
            kotlinx.coroutines.ChildJob r1 = r1.childJob
            r2 = r6
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r6.label = r3
            java.lang.Object r6 = r7.yield(r1, r2)
            if (r6 != r0) goto L_0x0086
            return r0
        L_0x004b:
            boolean r3 = r1 instanceof kotlinx.coroutines.Incomplete
            if (r3 == 0) goto L_0x0086
            kotlinx.coroutines.Incomplete r1 = (kotlinx.coroutines.Incomplete) r1
            kotlinx.coroutines.NodeList r1 = r1.getList()
            if (r1 == 0) goto L_0x0086
            kotlinx.coroutines.internal.LockFreeLinkedListHead r1 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r1
            java.lang.Object r3 = r1.getNext()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            r4 = r7
            r5 = r3
            r3 = r1
            r1 = r5
        L_0x0063:
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r7 != 0) goto L_0x0086
            boolean r7 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r7 == 0) goto L_0x0081
            r7 = r1
            kotlinx.coroutines.ChildHandleNode r7 = (kotlinx.coroutines.ChildHandleNode) r7
            kotlinx.coroutines.ChildJob r7 = r7.childJob
            r6.L$0 = r4
            r6.L$1 = r3
            r6.L$2 = r1
            r6.label = r2
            java.lang.Object r7 = r4.yield(r7, r6)
            if (r7 != r0) goto L_0x0081
            return r0
        L_0x0081:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = r1.getNextNode()
            goto L_0x0063
        L_0x0086:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport$children$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
