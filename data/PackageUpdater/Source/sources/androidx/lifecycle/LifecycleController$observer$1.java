package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, mo20735d2 = {"<anonymous>", "", "source", "Landroidx/lifecycle/LifecycleOwner;", "<anonymous parameter 1>", "Landroidx/lifecycle/Lifecycle$Event;"}, mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: LifecycleController.kt */
final class LifecycleController$observer$1 implements LifecycleEventObserver {
    final /* synthetic */ Job $parentJob;
    final /* synthetic */ LifecycleController this$0;

    LifecycleController$observer$1(LifecycleController lifecycleController, Job job) {
        this.this$0 = lifecycleController;
        this.$parentJob = job;
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
        Intrinsics.checkNotNullParameter(event, "$noName_1");
        if (lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            LifecycleController lifecycleController = this.this$0;
            Job.DefaultImpls.cancel$default(this.$parentJob, (CancellationException) null, 1, (Object) null);
            lifecycleController.finish();
        } else if (lifecycleOwner.getLifecycle().getCurrentState().compareTo(this.this$0.minState) < 0) {
            this.this$0.dispatchQueue.pause();
        } else {
            this.this$0.dispatchQueue.resume();
        }
    }
}
