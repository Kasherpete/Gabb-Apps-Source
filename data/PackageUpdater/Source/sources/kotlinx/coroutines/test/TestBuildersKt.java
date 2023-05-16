package kotlinx.coroutines.test;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"kotlinx/coroutines/test/TestBuildersKt__TestBuildersDeprecatedKt", "kotlinx/coroutines/test/TestBuildersKt__TestBuildersKt"}, mo20736k = 4, mo20737mv = {1, 6, 0}, mo20739xi = 48)
public final class TestBuildersKt {
    public static final long DEFAULT_DISPATCH_TIMEOUT_MS = 60000;

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers. Please see the migration guide for details: https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md")
    public static final void runBlockingTest(CoroutineContext coroutineContext, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt__TestBuildersDeprecatedKt.runBlockingTest(coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers. Please see the migration guide for details: https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md")
    public static final void runBlockingTest(TestCoroutineDispatcher testCoroutineDispatcher, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt__TestBuildersDeprecatedKt.runBlockingTest(testCoroutineDispatcher, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers. Please see the migration guide for details: https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md")
    public static final void runBlockingTest(TestCoroutineScope testCoroutineScope, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt__TestBuildersDeprecatedKt.runBlockingTest(testCoroutineScope, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers.")
    public static final void runBlockingTest(TestScope testScope, Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt__TestBuildersDeprecatedKt.runBlockingTest(testScope, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead to support completing from other dispatchers.")
    public static final void runBlockingTestOnTestScope(CoroutineContext coroutineContext, Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt__TestBuildersDeprecatedKt.runBlockingTestOnTestScope(coroutineContext, function2);
    }

    public static final void runTest(CoroutineContext coroutineContext, long j, Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt__TestBuildersKt.runTest(coroutineContext, j, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `TestScope.runTest` instead.")
    public static final void runTest(TestCoroutineScope testCoroutineScope, long j, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt__TestBuildersDeprecatedKt.runTest(testCoroutineScope, j, function2);
    }

    public static final void runTest(TestScope testScope, long j, Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt__TestBuildersKt.runTest(testScope, j, function2);
    }

    public static final <T extends AbstractCoroutine<? super Unit>> Object runTestCoroutine(CoroutineScope coroutineScope, T t, long j, Function1<? super T, ? extends Throwable> function1, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, Function0<? extends List<? extends Throwable>> function0, Continuation<? super Unit> continuation) {
        return TestBuildersKt__TestBuildersKt.runTestCoroutine(coroutineScope, t, j, function1, function2, function0, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `runTest` instead.")
    public static final void runTestWithLegacyScope(CoroutineContext coroutineContext, long j, Function2<? super TestCoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestBuildersKt__TestBuildersDeprecatedKt.runTestWithLegacyScope(coroutineContext, j, function2);
    }

    public static final void throwAll(List<? extends Throwable> list) {
        TestBuildersKt__TestBuildersKt.throwAll(list);
    }
}
