package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.test.internal.TestMainDispatcher;
import kotlinx.coroutines.test.internal.TestMainDispatcherJvmKt;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\u0014\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, mo20735d2 = {"resetMain", "", "Lkotlinx/coroutines/Dispatchers;", "setMain", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "kotlinx-coroutines-test"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestDispatchers.kt */
public final class TestDispatchers {
    public static final void setMain(Dispatchers dispatchers, CoroutineDispatcher coroutineDispatcher) {
        if (!(coroutineDispatcher instanceof TestMainDispatcher)) {
            TestMainDispatcherJvmKt.getTestMainDispatcher(dispatchers).setDispatcher(coroutineDispatcher);
            return;
        }
        throw new IllegalArgumentException("Dispatchers.setMain(Dispatchers.Main) is prohibited, probably Dispatchers.resetMain() should be used instead".toString());
    }

    public static final void resetMain(Dispatchers dispatchers) {
        TestMainDispatcherJvmKt.getTestMainDispatcher(dispatchers).resetDispatcher();
    }
}
