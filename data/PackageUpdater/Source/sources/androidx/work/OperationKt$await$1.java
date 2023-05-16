package androidx.work;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
@DebugMetadata(mo21448c = "androidx.work.OperationKt", mo21449f = "Operation.kt", mo21450i = {0}, mo21451l = {39}, mo21452m = "await", mo21453n = {"$this$await$iv"}, mo21454s = {"L$0"})
/* compiled from: Operation.kt */
final class OperationKt$await$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    OperationKt$await$1(Continuation<? super OperationKt$await$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OperationKt.await((Operation) null, this);
    }
}
