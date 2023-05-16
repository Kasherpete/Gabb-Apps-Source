package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 176)
@DebugMetadata(mo21448c = "kotlinx.coroutines.sync.MutexKt", mo21449f = "Mutex.kt", mo21450i = {0, 0, 0}, mo21451l = {112}, mo21452m = "withLock", mo21453n = {"$this$withLock", "owner", "action"}, mo21454s = {"L$0", "L$1", "L$2"})
/* compiled from: Mutex.kt */
final class MutexKt$withLock$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    MutexKt$withLock$1(Continuation<? super MutexKt$withLock$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MutexKt.withLock((Mutex) null, (Object) null, (Function0) null, this);
    }
}
