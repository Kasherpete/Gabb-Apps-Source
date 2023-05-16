package kotlinx.coroutines.test;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlinx.coroutines.AbstractCoroutine;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\n\u001a\u00020\u0002J\b\u0010\u000b\u001a\u00020\fH\u0017J\b\u0010\r\u001a\u0004\u0018\u00010\u000eR\u0014\u0010\u0006\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo20735d2 = {"Lkotlinx/coroutines/test/TestBodyCoroutine;", "Lkotlinx/coroutines/AbstractCoroutine;", "", "Lkotlinx/coroutines/test/TestCoroutineScope;", "testScope", "(Lkotlinx/coroutines/test/TestCoroutineScope;)V", "testScheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "getTestScheduler", "()Lkotlinx/coroutines/test/TestCoroutineScheduler;", "cleanup", "cleanupTestCoroutines", "", "tryGetCompletionCause", "", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestBuildersDeprecated.kt */
final class TestBodyCoroutine extends AbstractCoroutine<Unit> implements TestCoroutineScope {
    private final TestCoroutineScope testScope;

    public TestBodyCoroutine(TestCoroutineScope testCoroutineScope) {
        super(testCoroutineScope.getCoroutineContext(), true, true);
        this.testScope = testCoroutineScope;
    }

    public TestCoroutineScheduler getTestScheduler() {
        return this.testScope.getTestScheduler();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This deprecation is to prevent accidentally calling `cleanupTestCoroutines` in our own code.", replaceWith = @ReplaceWith(expression = "this.cleanup()", imports = {}))
    public Void cleanupTestCoroutines() {
        throw new UnsupportedOperationException("Calling `cleanupTestCoroutines` inside `runTest` is prohibited: it will be called at the end of the test in any case.");
    }

    public final void cleanup() {
        this.testScope.cleanupTestCoroutines();
    }

    public final Throwable tryGetCompletionCause() {
        return getCompletionCause();
    }
}
