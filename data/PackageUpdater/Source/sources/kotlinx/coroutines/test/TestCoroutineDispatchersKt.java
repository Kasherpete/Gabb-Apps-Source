package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlinx.coroutines.test.internal.TestMainDispatcher;

@Metadata(mo20734d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007\u001a \u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¨\u0006\u0007"}, mo20735d2 = {"StandardTestDispatcher", "Lkotlinx/coroutines/test/TestDispatcher;", "scheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "name", "", "UnconfinedTestDispatcher", "kotlinx-coroutines-test"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineDispatchers.kt */
public final class TestCoroutineDispatchersKt {
    public static /* synthetic */ TestDispatcher UnconfinedTestDispatcher$default(TestCoroutineScheduler testCoroutineScheduler, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            testCoroutineScheduler = null;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return UnconfinedTestDispatcher(testCoroutineScheduler, str);
    }

    public static final TestDispatcher UnconfinedTestDispatcher(TestCoroutineScheduler testCoroutineScheduler, String str) {
        if (testCoroutineScheduler == null && (testCoroutineScheduler = TestMainDispatcher.Companion.getCurrentTestScheduler$kotlinx_coroutines_test()) == null) {
            testCoroutineScheduler = new TestCoroutineScheduler();
        }
        return new UnconfinedTestDispatcherImpl(testCoroutineScheduler, str);
    }

    public static /* synthetic */ TestDispatcher StandardTestDispatcher$default(TestCoroutineScheduler testCoroutineScheduler, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            testCoroutineScheduler = null;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return StandardTestDispatcher(testCoroutineScheduler, str);
    }

    public static final TestDispatcher StandardTestDispatcher(TestCoroutineScheduler testCoroutineScheduler, String str) {
        if (testCoroutineScheduler == null && (testCoroutineScheduler = TestMainDispatcher.Companion.getCurrentTestScheduler$kotlinx_coroutines_test()) == null) {
            testCoroutineScheduler = new TestCoroutineScheduler();
        }
        return new StandardTestDispatcherImpl(testCoroutineScheduler, str);
    }
}
