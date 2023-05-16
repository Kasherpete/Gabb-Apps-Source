package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.time.AbstractLongTimeSource;
import kotlin.time.DurationUnit;

@Metadata(mo20734d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014¨\u0006\u0004"}, mo20735d2 = {"kotlinx/coroutines/test/TestCoroutineScheduler$timeSource$1", "Lkotlin/time/AbstractLongTimeSource;", "read", "", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScheduler.kt */
public final class TestCoroutineScheduler$timeSource$1 extends AbstractLongTimeSource {
    final /* synthetic */ TestCoroutineScheduler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestCoroutineScheduler$timeSource$1(TestCoroutineScheduler testCoroutineScheduler, DurationUnit durationUnit) {
        super(durationUnit);
        this.this$0 = testCoroutineScheduler;
    }

    /* access modifiers changed from: protected */
    public long read() {
        return this.this$0.getCurrentTime();
    }
}
