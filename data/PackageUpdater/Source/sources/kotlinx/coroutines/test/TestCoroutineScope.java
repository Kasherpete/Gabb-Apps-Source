package kotlinx.coroutines.test;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH'R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, mo20735d2 = {"Lkotlinx/coroutines/test/TestCoroutineScope;", "Lkotlinx/coroutines/CoroutineScope;", "testScheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "getTestScheduler$annotations", "()V", "getTestScheduler", "()Lkotlinx/coroutines/test/TestCoroutineScheduler;", "cleanupTestCoroutines", "", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Deprecated(level = DeprecationLevel.WARNING, message = "Use `TestScope` in combination with `runTest` instead.Please see the migration guide for details: https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md")
/* compiled from: TestCoroutineScope.kt */
public interface TestCoroutineScope extends CoroutineScope {

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TestCoroutineScope.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void getTestScheduler$annotations() {
        }
    }

    @Deprecated(message = "Please call `runTest`, which automatically performs the cleanup, instead of using this function.")
    void cleanupTestCoroutines();

    TestCoroutineScheduler getTestScheduler();
}
