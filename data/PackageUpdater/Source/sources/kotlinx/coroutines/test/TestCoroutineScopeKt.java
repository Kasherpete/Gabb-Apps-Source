package kotlinx.coroutines.test;

import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(mo20734d1 = {"\u0000H\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a\u0012\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\tH\u0007\u001a\u0012\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\tH\u0007\u001a\u0012\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\tH\u0000\u001a\u0014\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0001H\u0007\u001a\f\u0010\u001e\u001a\u00020\u001c*\u00020\u0002H\u0007\u001a\f\u0010\u001f\u001a\u00020\u001c*\u00020\u0002H\u0007\u001a3\u0010\u001f\u001a\u00020\u001c*\u00020\u00022\u001c\u0010 \u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\"\u0012\u0006\u0012\u0004\u0018\u00010#0!H@ø\u0001\u0000¢\u0006\u0002\u0010$\u001a\f\u0010%\u001a\u00020\u001c*\u00020\u0002H\u0007\u001a\f\u0010&\u001a\u00020\u001c*\u00020\u0002H\u0007\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\t8Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u0018\u0010\f\u001a\u00020\b*\u00020\u00028BX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0014\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, mo20735d2 = {"currentTime", "", "Lkotlinx/coroutines/test/TestCoroutineScope;", "getCurrentTime$annotations", "(Lkotlinx/coroutines/test/TestCoroutineScope;)V", "getCurrentTime", "(Lkotlinx/coroutines/test/TestCoroutineScope;)J", "delayController", "Lkotlinx/coroutines/test/DelayController;", "Lkotlin/coroutines/CoroutineContext;", "getDelayController", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/test/DelayController;", "delayControllerForPausing", "getDelayControllerForPausing", "(Lkotlinx/coroutines/test/TestCoroutineScope;)Lkotlinx/coroutines/test/DelayController;", "uncaughtExceptions", "", "", "getUncaughtExceptions$annotations", "getUncaughtExceptions", "(Lkotlinx/coroutines/test/TestCoroutineScope;)Ljava/util/List;", "TestCoroutineScope", "context", "createTestCoroutineScope", "activeJobs", "", "Lkotlinx/coroutines/Job;", "advanceTimeBy", "", "delayTimeMillis", "advanceUntilIdle", "pauseDispatcher", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/test/TestCoroutineScope;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resumeDispatcher", "runCurrent", "kotlinx-coroutines-test"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScope.kt */
public final class TestCoroutineScopeKt {
    public static /* synthetic */ void getCurrentTime$annotations(TestCoroutineScope testCoroutineScope) {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This list is only populated if `UncaughtExceptionCaptor` is in the test context, and so can be easily misused. It is only present for backward compatibility and will be removed in the subsequent releases. If you need to check the list of exceptions, please consider creating your own `CoroutineExceptionHandler`.")
    public static /* synthetic */ void getUncaughtExceptions$annotations(TestCoroutineScope testCoroutineScope) {
    }

    public static final Set<Job> activeJobs(CoroutineContext coroutineContext) {
        CoroutineContext.Element element = coroutineContext.get(Job.Key);
        if (element != null) {
            return SequencesKt.toSet(SequencesKt.filter(((Job) element).getChildren(), TestCoroutineScopeKt$activeJobs$1.INSTANCE));
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public static /* synthetic */ TestCoroutineScope TestCoroutineScope$default(CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return TestCoroutineScope(coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This constructs a `TestCoroutineScope` with a deprecated `CoroutineDispatcher` by default. Please use `createTestCoroutineScope` instead.", replaceWith = @ReplaceWith(expression = "createTestCoroutineScope(TestCoroutineDispatcher() + TestCoroutineExceptionHandler() + context)", imports = {"kotlin.coroutines.EmptyCoroutineContext"}))
    public static final TestCoroutineScope TestCoroutineScope(CoroutineContext coroutineContext) {
        TestCoroutineScheduler testCoroutineScheduler = (TestCoroutineScheduler) coroutineContext.get(TestCoroutineScheduler.Key);
        if (testCoroutineScheduler == null) {
            testCoroutineScheduler = new TestCoroutineScheduler();
        }
        return createTestCoroutineScope(new TestCoroutineDispatcher(testCoroutineScheduler).plus(new TestCoroutineExceptionHandler()).plus(coroutineContext));
    }

    public static /* synthetic */ TestCoroutineScope createTestCoroutineScope$default(CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return createTestCoroutineScope(coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This function was introduced in order to help migrate from TestCoroutineScope to TestScope. Please use TestScope() construction instead, or just runTest(), without creating a scope.")
    public static final TestCoroutineScope createTestCoroutineScope(CoroutineContext coroutineContext) {
        CoroutineContext withDelaySkipping = TestScopeKt.withDelaySkipping(coroutineContext);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        C1464x5c477f01 testCoroutineScopeKt$createTestCoroutineScope$ownExceptionHandler$1 = new C1464x5c477f01(objectRef, CoroutineExceptionHandler.Key);
        CoroutineExceptionHandler coroutineExceptionHandler = (CoroutineExceptionHandler) withDelaySkipping.get(CoroutineExceptionHandler.Key);
        if (!(coroutineExceptionHandler instanceof UncaughtExceptionCaptor)) {
            if (coroutineExceptionHandler == null) {
                coroutineExceptionHandler = testCoroutineScopeKt$createTestCoroutineScope$ownExceptionHandler$1;
            } else if (coroutineExceptionHandler instanceof TestCoroutineScopeExceptionHandler) {
                coroutineExceptionHandler = testCoroutineScopeKt$createTestCoroutineScope$ownExceptionHandler$1;
            } else {
                throw new IllegalArgumentException("A CoroutineExceptionHandler was passed to TestCoroutineScope. Please pass it as an argument to a `launch` or `async` block on an already-created scope if uncaught exceptions require special treatment.");
            }
        }
        Job job = (Job) withDelaySkipping.get(Job.Key);
        if (job == null) {
            job = JobKt.Job$default((Job) null, 1, (Object) null);
        }
        T testCoroutineScopeImpl = new TestCoroutineScopeImpl(withDelaySkipping.plus(coroutineExceptionHandler).plus(job));
        objectRef.element = testCoroutineScopeImpl;
        return (TestCoroutineScope) testCoroutineScopeImpl;
    }

    private static final DelayController getDelayController(CoroutineContext coroutineContext) {
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key);
        if (continuationInterceptor instanceof DelayController) {
            return (DelayController) continuationInterceptor;
        }
        return null;
    }

    public static final long getCurrentTime(TestCoroutineScope testCoroutineScope) {
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) testCoroutineScope.getCoroutineContext().get(ContinuationInterceptor.Key);
        DelayController delayController = continuationInterceptor instanceof DelayController ? (DelayController) continuationInterceptor : null;
        return delayController != null ? delayController.getCurrentTime() : testCoroutineScope.getTestScheduler().getCurrentTime();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "The name of this function is misleading: it not only advances the time, but also runs the tasks scheduled *at* the ending moment.", replaceWith = @ReplaceWith(expression = "this.testScheduler.apply { advanceTimeBy(delayTimeMillis); runCurrent() }", imports = {}))
    public static final void advanceTimeBy(TestCoroutineScope testCoroutineScope, long j) {
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) testCoroutineScope.getCoroutineContext().get(ContinuationInterceptor.Key);
        DelayController delayController = continuationInterceptor instanceof DelayController ? (DelayController) continuationInterceptor : null;
        if (delayController == null) {
            testCoroutineScope.getTestScheduler().advanceTimeBy(j);
            testCoroutineScope.getTestScheduler().runCurrent();
            return;
        }
        delayController.advanceTimeBy(j);
    }

    public static final void advanceUntilIdle(TestCoroutineScope testCoroutineScope) {
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) testCoroutineScope.getCoroutineContext().get(ContinuationInterceptor.Key);
        DelayController delayController = continuationInterceptor instanceof DelayController ? (DelayController) continuationInterceptor : null;
        if (delayController != null) {
            delayController.advanceUntilIdle();
        } else {
            testCoroutineScope.getTestScheduler().advanceUntilIdle();
        }
    }

    public static final void runCurrent(TestCoroutineScope testCoroutineScope) {
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) testCoroutineScope.getCoroutineContext().get(ContinuationInterceptor.Key);
        Unit unit = null;
        DelayController delayController = continuationInterceptor instanceof DelayController ? (DelayController) continuationInterceptor : null;
        if (delayController != null) {
            delayController.runCurrent();
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            testCoroutineScope.getTestScheduler().runCurrent();
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "The test coroutine scope isn't able to pause its dispatchers in the general case. Only `TestCoroutineDispatcher` supports pausing; pause it directly, or use a dispatcher that is always \"paused\", like `StandardTestDispatcher`.", replaceWith = @ReplaceWith(expression = "(this.coroutineContext[ContinuationInterceptor]!! as DelayController).pauseDispatcher(block)", imports = {"kotlin.coroutines.ContinuationInterceptor"}))
    public static final Object pauseDispatcher(TestCoroutineScope testCoroutineScope, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super Unit> continuation) {
        Object pauseDispatcher = getDelayControllerForPausing(testCoroutineScope).pauseDispatcher(function1, continuation);
        return pauseDispatcher == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? pauseDispatcher : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "The test coroutine scope isn't able to pause its dispatchers in the general case. Only `TestCoroutineDispatcher` supports pausing; pause it directly, or use a dispatcher that is always \"paused\", like `StandardTestDispatcher`.", replaceWith = @ReplaceWith(expression = "(this.coroutineContext[ContinuationInterceptor]!! as DelayController).pauseDispatcher()", imports = {"kotlin.coroutines.ContinuationInterceptor"}))
    public static final void pauseDispatcher(TestCoroutineScope testCoroutineScope) {
        getDelayControllerForPausing(testCoroutineScope).pauseDispatcher();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "The test coroutine scope isn't able to pause its dispatchers in the general case. Only `TestCoroutineDispatcher` supports pausing; pause it directly, or use a dispatcher that is always \"paused\", like `StandardTestDispatcher`.", replaceWith = @ReplaceWith(expression = "(this.coroutineContext[ContinuationInterceptor]!! as DelayController).resumeDispatcher()", imports = {"kotlin.coroutines.ContinuationInterceptor"}))
    public static final void resumeDispatcher(TestCoroutineScope testCoroutineScope) {
        getDelayControllerForPausing(testCoroutineScope).resumeDispatcher();
    }

    public static final List<Throwable> getUncaughtExceptions(TestCoroutineScope testCoroutineScope) {
        List<Throwable> uncaughtExceptions;
        CoroutineContext.Element element = testCoroutineScope.getCoroutineContext().get(CoroutineExceptionHandler.Key);
        UncaughtExceptionCaptor uncaughtExceptionCaptor = element instanceof UncaughtExceptionCaptor ? (UncaughtExceptionCaptor) element : null;
        return (uncaughtExceptionCaptor == null || (uncaughtExceptions = uncaughtExceptionCaptor.getUncaughtExceptions()) == null) ? CollectionsKt.emptyList() : uncaughtExceptions;
    }

    private static final DelayController getDelayControllerForPausing(TestCoroutineScope testCoroutineScope) {
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) testCoroutineScope.getCoroutineContext().get(ContinuationInterceptor.Key);
        DelayController delayController = continuationInterceptor instanceof DelayController ? (DelayController) continuationInterceptor : null;
        if (delayController != null) {
            return delayController;
        }
        throw new IllegalStateException("This scope isn't able to pause its dispatchers");
    }
}
