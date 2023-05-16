package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Ref;
import kotlin.time.TimeSource;
import kotlinx.coroutines.CoroutineExceptionHandler;

@Metadata(mo20734d1 = {"\u0000,\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u001a\u0014\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0001H\u0007\u001a\f\u0010\u0012\u001a\u00020\u0010*\u00020\u0002H\u0007\u001a\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0002H\u0000\u001a\f\u0010\u0015\u001a\u00020\u0010*\u00020\u0002H\u0007\u001a\f\u0010\u0016\u001a\u00020\u000e*\u00020\u000eH\u0000\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001e\u0010\u0007\u001a\u00020\b*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, mo20735d2 = {"currentTime", "", "Lkotlinx/coroutines/test/TestScope;", "getCurrentTime$annotations", "(Lkotlinx/coroutines/test/TestScope;)V", "getCurrentTime", "(Lkotlinx/coroutines/test/TestScope;)J", "testTimeSource", "Lkotlin/time/TimeSource;", "getTestTimeSource$annotations", "getTestTimeSource", "(Lkotlinx/coroutines/test/TestScope;)Lkotlin/time/TimeSource;", "TestScope", "context", "Lkotlin/coroutines/CoroutineContext;", "advanceTimeBy", "", "delayTimeMillis", "advanceUntilIdle", "asSpecificImplementation", "Lkotlinx/coroutines/test/TestScopeImpl;", "runCurrent", "withDelaySkipping", "kotlinx-coroutines-test"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestScope.kt */
public final class TestScopeKt {
    public static /* synthetic */ void getCurrentTime$annotations(TestScope testScope) {
    }

    public static /* synthetic */ void getTestTimeSource$annotations(TestScope testScope) {
    }

    public static final long getCurrentTime(TestScope testScope) {
        return testScope.getTestScheduler().getCurrentTime();
    }

    public static final void advanceUntilIdle(TestScope testScope) {
        testScope.getTestScheduler().advanceUntilIdle();
    }

    public static final void runCurrent(TestScope testScope) {
        testScope.getTestScheduler().runCurrent();
    }

    public static final void advanceTimeBy(TestScope testScope, long j) {
        testScope.getTestScheduler().advanceTimeBy(j);
    }

    public static final TimeSource getTestTimeSource(TestScope testScope) {
        return testScope.getTestScheduler().getTimeSource();
    }

    public static /* synthetic */ TestScope TestScope$default(CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return TestScope(coroutineContext);
    }

    public static final TestScope TestScope(CoroutineContext coroutineContext) {
        CoroutineContext withDelaySkipping = withDelaySkipping(coroutineContext);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (((CoroutineExceptionHandler) withDelaySkipping.get(CoroutineExceptionHandler.Key)) == null) {
            T testScopeImpl = new TestScopeImpl(withDelaySkipping.plus(new TestScopeKt$TestScope$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, objectRef)));
            objectRef.element = testScopeImpl;
            return (TestScope) testScopeImpl;
        }
        throw new IllegalArgumentException("A CoroutineExceptionHandler was passed to TestScope. Please pass it as an argument to a `launch` or `async` block on an already-created scope if uncaught exceptions require special treatment.");
    }

    public static final CoroutineContext withDelaySkipping(CoroutineContext coroutineContext) {
        TestDispatcher testDispatcher;
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key);
        if (continuationInterceptor instanceof TestDispatcher) {
            TestCoroutineScheduler testCoroutineScheduler = (TestCoroutineScheduler) coroutineContext.get(TestCoroutineScheduler.Key);
            if (testCoroutineScheduler != null) {
                if (!(((TestDispatcher) continuationInterceptor).getScheduler() == testCoroutineScheduler)) {
                    throw new IllegalArgumentException(("Both a TestCoroutineScheduler " + testCoroutineScheduler + " and TestDispatcher " + continuationInterceptor + " linked to another scheduler were passed.").toString());
                }
            }
            testDispatcher = (TestDispatcher) continuationInterceptor;
        } else if (continuationInterceptor == null) {
            testDispatcher = TestCoroutineDispatchersKt.StandardTestDispatcher$default((TestCoroutineScheduler) coroutineContext.get(TestCoroutineScheduler.Key), (String) null, 2, (Object) null);
        } else {
            throw new IllegalArgumentException("Dispatcher must implement TestDispatcher: " + continuationInterceptor);
        }
        return coroutineContext.plus(testDispatcher).plus(testDispatcher.getScheduler());
    }

    public static final TestScopeImpl asSpecificImplementation(TestScope testScope) {
        if (testScope instanceof TestScopeImpl) {
            return (TestScopeImpl) testScope;
        }
        throw new NoWhenBranchMatchedException();
    }
}
