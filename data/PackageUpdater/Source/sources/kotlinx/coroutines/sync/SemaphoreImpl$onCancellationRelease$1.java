package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo20735d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Semaphore.kt */
final class SemaphoreImpl$onCancellationRelease$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ SemaphoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SemaphoreImpl$onCancellationRelease$1(SemaphoreImpl semaphoreImpl) {
        super(1);
        this.this$0 = semaphoreImpl;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.this$0.release();
    }
}
