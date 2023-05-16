package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, mo20735d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScheduler.kt */
final class TestCoroutineScheduler$advanceUntilIdle$1 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ TestCoroutineScheduler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestCoroutineScheduler$advanceUntilIdle$1(TestCoroutineScheduler testCoroutineScheduler) {
        super(0);
        this.this$0 = testCoroutineScheduler;
    }

    public final Boolean invoke() {
        return Boolean.valueOf(TestCoroutineSchedulerKt.none(this.this$0.events, C14631.INSTANCE));
    }
}
