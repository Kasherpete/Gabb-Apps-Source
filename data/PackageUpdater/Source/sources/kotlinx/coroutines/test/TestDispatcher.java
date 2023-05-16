package kotlinx.coroutines.test;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;

@Metadata(mo20734d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0000¢\u0006\u0002\u0010\u0003J$\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0017J\u001e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u001aH\u0016R\u001a\u0010\u0004\u001a\u00020\u00058&X§\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, mo20735d2 = {"Lkotlinx/coroutines/test/TestDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "()V", "scheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "getScheduler$annotations", "getScheduler", "()Lkotlinx/coroutines/test/TestCoroutineScheduler;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "context", "Lkotlin/coroutines/CoroutineContext;", "processEvent", "", "time", "marker", "", "processEvent$kotlinx_coroutines_test", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestDispatcher.kt */
public abstract class TestDispatcher extends CoroutineDispatcher implements Delay {
    public static /* synthetic */ void getScheduler$annotations() {
    }

    public abstract TestCoroutineScheduler getScheduler();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long j, Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, j, continuation);
    }

    public final void processEvent$kotlinx_coroutines_test(long j, Object obj) {
        if (obj instanceof Runnable) {
            ((Runnable) obj).run();
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public void scheduleResumeAfterDelay(long j, CancellableContinuation<? super Unit> cancellableContinuation) {
        long j2 = j;
        getScheduler().registerEvent$kotlinx_coroutines_test(this, j2, new CancellableContinuationRunnable(cancellableContinuation, this), cancellableContinuation.getContext(), TestDispatcher$scheduleResumeAfterDelay$1.INSTANCE);
    }

    public DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        return getScheduler().registerEvent$kotlinx_coroutines_test(this, j, runnable, coroutineContext, TestDispatcher$invokeOnTimeout$1.INSTANCE);
    }
}
