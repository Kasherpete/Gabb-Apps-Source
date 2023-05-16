package androidx.lifecycle;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, mo20735d2 = {"<anonymous>", ""}, mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: DispatchQueue.kt */
final class DispatchQueue$dispatchAndEnqueue$1$1 implements Runnable {
    final /* synthetic */ Runnable $runnable;
    final /* synthetic */ DispatchQueue this$0;

    DispatchQueue$dispatchAndEnqueue$1$1(DispatchQueue dispatchQueue, Runnable runnable) {
        this.this$0 = dispatchQueue;
        this.$runnable = runnable;
    }

    public final void run() {
        this.this$0.enqueue(this.$runnable);
    }
}
