package androidx.work;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n"}, mo20735d2 = {"<anonymous>", "", "R"}, mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: ListenableFuture.kt */
public final class ListenableFutureKt$await$2$1 implements Runnable {
    final /* synthetic */ CancellableContinuation<R> $cancellableContinuation;
    final /* synthetic */ ListenableFuture<R> $this_await;

    public ListenableFutureKt$await$2$1(CancellableContinuation<? super R> cancellableContinuation, ListenableFuture<R> listenableFuture) {
        this.$cancellableContinuation = cancellableContinuation;
        this.$this_await = listenableFuture;
    }

    public final void run() {
        try {
            Object obj = this.$this_await.get();
            Result.Companion companion = Result.Companion;
            this.$cancellableContinuation.resumeWith(Result.m176constructorimpl(obj));
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause == null) {
                cause = th;
            }
            if (th instanceof CancellationException) {
                this.$cancellableContinuation.cancel(cause);
                return;
            }
            Result.Companion companion2 = Result.Companion;
            this.$cancellableContinuation.resumeWith(Result.m176constructorimpl(ResultKt.createFailure(cause)));
        }
    }
}
