package kotlinx.coroutines.test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

@Metadata(mo20734d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aC\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aC\u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aQ\u0010\r\u001a\u00060\u0001j\u0002`\u000e2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00122'\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00062'\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\f2'\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001aK\u0010\u0017\u001a\u00060\u0001j\u0002`\u000e*\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00102'\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, mo20735d2 = {"runBlockingTest", "", "context", "Lkotlin/coroutines/CoroutineContext;", "testBody", "Lkotlin/Function2;", "Lkotlinx/coroutines/test/TestCoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)V", "runBlockingTestOnTestScope", "Lkotlinx/coroutines/test/TestScope;", "runTestWithLegacyScope", "Lkotlinx/coroutines/test/TestResult;", "dispatchTimeoutMs", "", "(Lkotlin/coroutines/CoroutineContext;JLkotlin/jvm/functions/Function2;)V", "Lkotlinx/coroutines/test/TestCoroutineDispatcher;", "block", "(Lkotlinx/coroutines/test/TestCoroutineDispatcher;Lkotlin/jvm/functions/Function2;)V", "(Lkotlinx/coroutines/test/TestCoroutineScope;Lkotlin/jvm/functions/Function2;)V", "(Lkotlinx/coroutines/test/TestScope;Lkotlin/jvm/functions/Function2;)V", "runTest", "(Lkotlinx/coroutines/test/TestCoroutineScope;JLkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-test"}, mo20736k = 5, mo20737mv = {1, 6, 0}, mo20739xi = 48, mo20740xs = "kotlinx/coroutines/test/TestBuildersKt")
/* compiled from: TestBuildersDeprecated.kt */
final /* synthetic */ class TestBuildersKt__TestBuildersDeprecatedKt {
    public static /* synthetic */ void runBlockingTest$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        TestBuildersKt.runBlockingTest(coroutineContext, (Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers. Please see the migration guide for details: https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md")
    public static final void runBlockingTest(CoroutineContext coroutineContext, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestCoroutineScope createTestCoroutineScope = TestCoroutineScopeKt.createTestCoroutineScope(new TestCoroutineDispatcher((TestCoroutineScheduler) null, 1, (DefaultConstructorMarker) null).plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)).plus(coroutineContext));
        TestCoroutineScheduler testScheduler = createTestCoroutineScope.getTestScheduler();
        Deferred async$default = BuildersKt__Builders_commonKt.async$default(createTestCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C1452x1153aca9(function2, createTestCoroutineScope, (Continuation<? super C1452x1153aca9>) null), 3, (Object) null);
        testScheduler.advanceUntilIdle();
        Throwable completionExceptionOrNull = async$default.getCompletionExceptionOrNull();
        if (completionExceptionOrNull == null) {
            createTestCoroutineScope.cleanupTestCoroutines();
            return;
        }
        throw completionExceptionOrNull;
    }

    public static /* synthetic */ void runBlockingTestOnTestScope$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        TestBuildersKt.runBlockingTestOnTestScope(coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers.")
    public static final void runBlockingTestOnTestScope(CoroutineContext coroutineContext, Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Throwable th;
        List<Throwable> list;
        CoroutineContext plus = new TestCoroutineDispatcher((TestCoroutineScheduler) null, 1, (DefaultConstructorMarker) null).plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)).plus(coroutineContext);
        Set<Job> activeJobs = TestCoroutineScopeKt.activeJobs(plus);
        TestScopeImpl asSpecificImplementation = TestScopeKt.asSpecificImplementation(TestScopeKt.TestScope(plus));
        asSpecificImplementation.enter();
        asSpecificImplementation.start(CoroutineStart.UNDISPATCHED, asSpecificImplementation, new C1453x11884809(function2, asSpecificImplementation, (Continuation<? super C1453x11884809>) null));
        asSpecificImplementation.getTestScheduler().advanceUntilIdle();
        try {
            th = asSpecificImplementation.getCompletionExceptionOrNull();
        } catch (IllegalStateException unused) {
            th = null;
        }
        CoroutineScopeKt.cancel$default(asSpecificImplementation.getBackgroundScope(), (CancellationException) null, 1, (Object) null);
        asSpecificImplementation.getTestScheduler().advanceUntilIdleOr$kotlinx_coroutines_test(C1454x1188480a.INSTANCE);
        if (th != null) {
            try {
                list = asSpecificImplementation.leave();
            } catch (UncompletedCoroutinesError unused2) {
                list = CollectionsKt.emptyList();
            }
            TestBuildersKt.throwAll(CollectionsKt.plus(CollectionsKt.listOf(th), list));
            return;
        }
        TestBuildersKt.throwAll(asSpecificImplementation.leave());
        Set<T> minus = SetsKt.minus(TestCoroutineScopeKt.activeJobs(plus), activeJobs);
        if (!minus.isEmpty()) {
            throw new UncompletedCoroutinesError("Some jobs were not completed at the end of the test: " + minus);
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers. Please see the migration guide for details: https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md")
    public static final void runBlockingTest(TestCoroutineScope testCoroutineScope, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt.runBlockingTest(testCoroutineScope.getCoroutineContext(), function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers.")
    public static final void runBlockingTest(TestScope testScope, Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt.runBlockingTestOnTestScope(testScope.getCoroutineContext(), function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers. Please see the migration guide for details: https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md")
    public static final void runBlockingTest(TestCoroutineDispatcher testCoroutineDispatcher, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt.runBlockingTest((CoroutineContext) testCoroutineDispatcher, function2);
    }

    public static /* synthetic */ void runTestWithLegacyScope$default(CoroutineContext coroutineContext, long j, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            j = TestBuildersKt.DEFAULT_DISPATCH_TIMEOUT_MS;
        }
        TestBuildersKt.runTestWithLegacyScope(coroutineContext, j, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead.")
    public static final void runTestWithLegacyScope(CoroutineContext coroutineContext, long j, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (coroutineContext.get(RunningInRunTest.INSTANCE) == null) {
            TestBuildersJvmKt.createTestResult(new C1455xb1caf9e0(new TestBodyCoroutine(TestCoroutineScopeKt.createTestCoroutineScope(coroutineContext.plus(RunningInRunTest.INSTANCE))), j, function2, (Continuation<? super C1455xb1caf9e0>) null));
            return;
        }
        throw new IllegalStateException("Calls to `runTest` can't be nested. Please read the docs on `TestResult` for details.");
    }

    public static /* synthetic */ void runTest$default(TestCoroutineScope testCoroutineScope, long j, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = TestBuildersKt.DEFAULT_DISPATCH_TIMEOUT_MS;
        }
        TestBuildersKt.runTest(testCoroutineScope, j, (Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `TestScope.runTest` instead.")
    public static final void runTest(TestCoroutineScope testCoroutineScope, long j, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt.runTestWithLegacyScope(testCoroutineScope.getCoroutineContext(), j, function2);
    }
}
