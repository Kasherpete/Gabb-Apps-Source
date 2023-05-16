package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.YieldContext;

@Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\u0012\u001a\u00020\u0005H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, mo20735d2 = {"Lkotlinx/coroutines/test/UnconfinedTestDispatcherImpl;", "Lkotlinx/coroutines/test/TestDispatcher;", "scheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "name", "", "(Lkotlinx/coroutines/test/TestCoroutineScheduler;Ljava/lang/String;)V", "getScheduler", "()Lkotlinx/coroutines/test/TestCoroutineScheduler;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "isDispatchNeeded", "", "toString", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineDispatchers.kt */
final class UnconfinedTestDispatcherImpl extends TestDispatcher {
    private final String name;
    private final TestCoroutineScheduler scheduler;

    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        return false;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ UnconfinedTestDispatcherImpl(TestCoroutineScheduler testCoroutineScheduler, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(testCoroutineScheduler, (i & 2) != 0 ? null : str);
    }

    public TestCoroutineScheduler getScheduler() {
        return this.scheduler;
    }

    public UnconfinedTestDispatcherImpl(TestCoroutineScheduler testCoroutineScheduler, String str) {
        this.scheduler = testCoroutineScheduler;
        this.name = str;
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        TestCoroutineSchedulerKt.checkSchedulerInContext(getScheduler(), coroutineContext);
        getScheduler().sendDispatchEvent$kotlinx_coroutines_test(coroutineContext);
        YieldContext yieldContext = (YieldContext) coroutineContext.get(YieldContext.Key);
        if (yieldContext != null) {
            yieldContext.dispatcherWasUnconfined = true;
            return;
        }
        throw new UnsupportedOperationException("Function UnconfinedTestCoroutineDispatcher.dispatch can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.name;
        if (str == null) {
            str = "UnconfinedTestDispatcher";
        }
        return sb.append(str).append("[scheduler=").append(getScheduler()).append(']').toString();
    }
}
