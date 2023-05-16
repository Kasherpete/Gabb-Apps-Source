package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.TimeoutKt", mo21449f = "Timeout.kt", mo21450i = {0, 0, 0}, mo21451l = {100}, mo21452m = "withTimeoutOrNull", mo21453n = {"block", "coroutine", "timeMillis"}, mo21454s = {"L$0", "L$1", "J$0"})
/* compiled from: Timeout.kt */
final class TimeoutKt$withTimeoutOrNull$1<T> extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    TimeoutKt$withTimeoutOrNull$1(Continuation<? super TimeoutKt$withTimeoutOrNull$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TimeoutKt.withTimeoutOrNull(0, (Function2) null, this);
    }
}
