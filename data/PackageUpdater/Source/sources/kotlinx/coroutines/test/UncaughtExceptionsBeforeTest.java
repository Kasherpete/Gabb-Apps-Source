package kotlinx.coroutines.test;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, mo20735d2 = {"Lkotlinx/coroutines/test/UncaughtExceptionsBeforeTest;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "()V", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestScope.kt */
public final class UncaughtExceptionsBeforeTest extends IllegalStateException {
    public UncaughtExceptionsBeforeTest() {
        super("There were uncaught exceptions in coroutines launched from TestScope before the test started. Please avoid this, as such exceptions are also reported in a platform-dependent manner so that they are not lost.");
    }
}
