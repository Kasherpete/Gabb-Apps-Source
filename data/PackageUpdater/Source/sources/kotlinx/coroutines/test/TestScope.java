package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u00020\u00018&X§\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\b8&X§\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u000b\u0001\u0001\f¨\u0006\r"}, mo20735d2 = {"Lkotlinx/coroutines/test/TestScope;", "Lkotlinx/coroutines/CoroutineScope;", "backgroundScope", "getBackgroundScope$annotations", "()V", "getBackgroundScope", "()Lkotlinx/coroutines/CoroutineScope;", "testScheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "getTestScheduler$annotations", "getTestScheduler", "()Lkotlinx/coroutines/test/TestCoroutineScheduler;", "Lkotlinx/coroutines/test/TestScopeImpl;", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestScope.kt */
public interface TestScope extends CoroutineScope {

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TestScope.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void getBackgroundScope$annotations() {
        }

        public static /* synthetic */ void getTestScheduler$annotations() {
        }
    }

    CoroutineScope getBackgroundScope();

    TestCoroutineScheduler getTestScheduler();
}
