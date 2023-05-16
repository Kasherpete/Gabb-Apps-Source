package kotlinx.coroutines.test;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo20735d2 = {"<anonymous>", "", "it", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestScope.kt */
final class TestScopeImpl$backgroundScope$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ TestScopeImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestScopeImpl$backgroundScope$1(TestScopeImpl testScopeImpl) {
        super(1);
        this.this$0 = testScopeImpl;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        if (!(th instanceof CancellationException)) {
            this.this$0.reportException(th);
        }
    }
}
