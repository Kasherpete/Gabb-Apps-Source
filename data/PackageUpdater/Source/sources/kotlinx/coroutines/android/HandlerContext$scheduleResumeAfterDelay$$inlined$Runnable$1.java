package kotlinx.coroutines.android;

import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo20735d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Runnable.kt */
public final class HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1 implements Runnable {
    final /* synthetic */ CancellableContinuation $continuation$inlined;
    final /* synthetic */ HandlerContext this$0;

    public HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1(CancellableContinuation cancellableContinuation, HandlerContext handlerContext) {
        this.$continuation$inlined = cancellableContinuation;
        this.this$0 = handlerContext;
    }

    public final void run() {
        this.$continuation$inlined.resumeUndispatched(this.this$0, Unit.INSTANCE);
    }
}
