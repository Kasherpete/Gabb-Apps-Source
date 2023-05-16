package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo20735d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/test/TestDispatchEvent;", "", "invoke", "(Lkotlinx/coroutines/test/TestDispatchEvent;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScheduler.kt */
final class TestCoroutineScheduler$isIdle$1$1 extends Lambda implements Function1<TestDispatchEvent<Object>, Boolean> {
    public static final TestCoroutineScheduler$isIdle$1$1 INSTANCE = new TestCoroutineScheduler$isIdle$1$1();

    TestCoroutineScheduler$isIdle$1$1() {
        super(1);
    }

    public final Boolean invoke(TestDispatchEvent<Object> testDispatchEvent) {
        return Boolean.valueOf(!testDispatchEvent.isCancelled.invoke().booleanValue());
    }
}
