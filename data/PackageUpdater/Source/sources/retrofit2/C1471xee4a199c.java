package retrofit2;

import retrofit2.DefaultCallAdapterFactory;

/* renamed from: retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda0 */
public final /* synthetic */ class C1471xee4a199c implements Runnable {
    public final /* synthetic */ DefaultCallAdapterFactory.ExecutorCallbackCall.C14701 f$0;
    public final /* synthetic */ Callback f$1;
    public final /* synthetic */ Throwable f$2;

    public /* synthetic */ C1471xee4a199c(DefaultCallAdapterFactory.ExecutorCallbackCall.C14701 r1, Callback callback, Throwable th) {
        this.f$0 = r1;
        this.f$1 = callback;
        this.f$2 = th;
    }

    public final void run() {
        this.f$0.mo24942x714e864(this.f$1, this.f$2);
    }
}
