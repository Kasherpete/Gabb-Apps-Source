package kotlinx.coroutines.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.test.internal.ReportingSupervisorJob;

@Metadata(mo20734d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0018\u001a\u00020\u0002J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00170\u001aJ\u000e\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0017J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u0004\u0018\u00010\u0017R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, mo20735d2 = {"Lkotlinx/coroutines/test/TestScopeImpl;", "Lkotlinx/coroutines/AbstractCoroutine;", "", "Lkotlinx/coroutines/test/TestScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "backgroundScope", "Lkotlinx/coroutines/CoroutineScope;", "getBackgroundScope", "()Lkotlinx/coroutines/CoroutineScope;", "entered", "", "finished", "lock", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "testScheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "getTestScheduler", "()Lkotlinx/coroutines/test/TestCoroutineScheduler;", "uncaughtExceptions", "", "", "enter", "leave", "", "reportException", "throwable", "toString", "", "tryGetCompletionCause", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestScope.kt */
public final class TestScopeImpl extends AbstractCoroutine<Unit> implements TestScope {
    private final CoroutineScope backgroundScope = CoroutineScopeKt.CoroutineScope(getCoroutineContext().plus(BackgroundWork.INSTANCE).plus(new ReportingSupervisorJob((Job) null, new TestScopeImpl$backgroundScope$1(this), 1, (DefaultConstructorMarker) null)));
    private boolean entered;
    private boolean finished;
    private final Object lock = new Object();
    private final List<Throwable> uncaughtExceptions = new ArrayList();

    public TestScopeImpl(CoroutineContext coroutineContext) {
        super(coroutineContext, true, true);
    }

    public TestCoroutineScheduler getTestScheduler() {
        CoroutineContext.Element element = getContext().get(TestCoroutineScheduler.Key);
        Intrinsics.checkNotNull(element);
        return (TestCoroutineScheduler) element;
    }

    public CoroutineScope getBackgroundScope() {
        return this.backgroundScope;
    }

    public final void enter() {
        List<Throwable> list;
        synchronized (this.lock) {
            if (!this.entered) {
                this.entered = true;
                if (!this.finished) {
                    list = this.uncaughtExceptions;
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            } else {
                throw new IllegalStateException("Only a single call to `runTest` can be performed during one test.");
            }
        }
        if (!list.isEmpty()) {
            UncaughtExceptionsBeforeTest uncaughtExceptionsBeforeTest = new UncaughtExceptionsBeforeTest();
            for (Throwable addSuppressed : list) {
                ExceptionsKt.addSuppressed(uncaughtExceptionsBeforeTest, addSuppressed);
            }
            throw uncaughtExceptionsBeforeTest;
        }
    }

    public final List<Throwable> leave() {
        List<Throwable> list;
        synchronized (this.lock) {
            if (this.entered && !this.finished) {
                this.finished = true;
                list = this.uncaughtExceptions;
            } else {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        List<Job> list2 = SequencesKt.toList(SequencesKt.filter(getChildren(), TestScopeImpl$leave$activeJobs$1.INSTANCE));
        if (list.isEmpty()) {
            if (!list2.isEmpty()) {
                throw new UncompletedCoroutinesError("Active jobs found during the tear-down. Ensure that all coroutines are completed or cancelled by your test. The active jobs: " + list2);
            } else if (!TestCoroutineScheduler.isIdle$kotlinx_coroutines_test$default(getTestScheduler(), false, 1, (Object) null)) {
                throw new UncompletedCoroutinesError("Unfinished coroutines found during the tear-down. Ensure that all coroutines are completed or cancelled by your test.");
            }
        }
        return list;
    }

    public final void reportException(Throwable th) {
        synchronized (this.lock) {
            if (!this.finished) {
                Iterator<Throwable> it = this.uncaughtExceptions.iterator();
                while (it.hasNext()) {
                    Throwable next = it.next();
                    Throwable unwrapImpl = !DebugKt.getRECOVER_STACK_TRACES() ? th : StackTraceRecoveryKt.unwrapImpl(th);
                    if (DebugKt.getRECOVER_STACK_TRACES()) {
                        next = StackTraceRecoveryKt.unwrapImpl(next);
                    }
                    if (Intrinsics.areEqual((Object) unwrapImpl, (Object) next)) {
                        return;
                    }
                }
                this.uncaughtExceptions.add(th);
                if (this.entered) {
                    Unit unit = Unit.INSTANCE;
                    return;
                }
                UncaughtExceptionsBeforeTest uncaughtExceptionsBeforeTest = new UncaughtExceptionsBeforeTest();
                ExceptionsKt.addSuppressed(uncaughtExceptionsBeforeTest, th);
                throw uncaughtExceptionsBeforeTest;
            }
            throw th;
        }
    }

    public final Throwable tryGetCompletionCause() {
        return getCompletionCause();
    }

    public String toString() {
        return "TestScope[" + (this.finished ? "test ended" : this.entered ? "test started" : "test not started") + ']';
    }
}
