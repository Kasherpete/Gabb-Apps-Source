package kotlinx.coroutines.test.internal;

import kotlin.Metadata;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, mo20735d2 = {"getTestMainDispatcher", "Lkotlinx/coroutines/test/internal/TestMainDispatcher;", "Lkotlinx/coroutines/Dispatchers;", "kotlinx-coroutines-test"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestMainDispatcherJvm.kt */
public final class TestMainDispatcherJvmKt {
    public static final TestMainDispatcher getTestMainDispatcher(Dispatchers dispatchers) {
        MainCoroutineDispatcher main = Dispatchers.getMain();
        if (main instanceof TestMainDispatcher) {
            return (TestMainDispatcher) main;
        }
        throw new IllegalArgumentException(("TestMainDispatcher is not set as main dispatcher, have " + main + " instead.").toString());
    }
}
