package kotlinx.coroutines.test;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;

@Metadata(mo20734d1 = {"\u0000>\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\b\u0010\n\u001a\u00020\u000bH\u0002\u001a\b\u0010\f\u001a\u00020\u000bH\u0002\u001a:\u0010\r\u001a\u00020\u000e\"\u0012\b\u0000\u0010\u000f*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u000f0\u0011*\b\u0012\u0004\u0012\u0002H\u000f0\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u00020\u000e0\u0014H\u0002¨\u0006\u0015"}, mo20735d2 = {"addClamping", "", "a", "b", "checkSchedulerInContext", "", "scheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "context", "Lkotlin/coroutines/CoroutineContext;", "currentTimeAheadOfEvents", "", "invalidSchedulerState", "none", "", "T", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "predicate", "Lkotlin/Function1;", "kotlinx-coroutines-test"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScheduler.kt */
public final class TestCoroutineSchedulerKt {
    /* access modifiers changed from: private */
    public static final long addClamping(long j, long j2) {
        long j3 = j + j2;
        if (j3 >= 0) {
            return j3;
        }
        return Long.MAX_VALUE;
    }

    /* access modifiers changed from: private */
    public static final Void currentTimeAheadOfEvents() {
        invalidSchedulerState();
        throw new KotlinNothingValueException();
    }

    private static final Void invalidSchedulerState() {
        throw new IllegalStateException("The test scheduler entered an invalid state. Please report this at https://github.com/Kotlin/kotlinx.coroutines/issues.");
    }

    public static final void checkSchedulerInContext(TestCoroutineScheduler testCoroutineScheduler, CoroutineContext coroutineContext) {
        TestCoroutineScheduler testCoroutineScheduler2 = (TestCoroutineScheduler) coroutineContext.get(TestCoroutineScheduler.Key);
        if (testCoroutineScheduler2 != null) {
            if (!(testCoroutineScheduler2 == testCoroutineScheduler)) {
                throw new IllegalStateException("Detected use of different schedulers. If you need to use several test coroutine dispatchers, create one `TestCoroutineScheduler` and pass it to each of them.".toString());
            }
        }
    }

    /* access modifiers changed from: private */
    public static final <T extends ThreadSafeHeapNode & Comparable<? super T>> boolean none(ThreadSafeHeap<T> threadSafeHeap, Function1<? super T, Boolean> function1) {
        return threadSafeHeap.find(function1) == null;
    }
}
