package kotlinx.coroutines.test;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0004"}, mo20735d2 = {"cancellableRunnableIsCancelled", "", "runnable", "Lkotlinx/coroutines/test/CancellableContinuationRunnable;", "kotlinx-coroutines-test"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestDispatcher.kt */
public final class TestDispatcherKt {
    /* access modifiers changed from: private */
    public static final boolean cancellableRunnableIsCancelled(CancellableContinuationRunnable cancellableContinuationRunnable) {
        return !cancellableContinuationRunnable.continuation.isActive();
    }
}
