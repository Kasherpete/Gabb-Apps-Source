package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlinx.coroutines.scheduling.CoroutineScheduler;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¨\u0006\u0005"}, mo20735d2 = {"isSchedulerWorker", "", "thread", "Ljava/lang/Thread;", "mayNotBlock", "kotlinx-coroutines-core"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CoroutineScheduler.kt */
public final class CoroutineSchedulerKt {
    public static final boolean isSchedulerWorker(Thread thread) {
        return thread instanceof CoroutineScheduler.Worker;
    }

    public static final boolean mayNotBlock(Thread thread) {
        return (thread instanceof CoroutineScheduler.Worker) && ((CoroutineScheduler.Worker) thread).state == CoroutineScheduler.WorkerState.CPU_ACQUIRED;
    }
}
