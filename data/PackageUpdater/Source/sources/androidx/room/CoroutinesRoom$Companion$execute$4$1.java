package androidx.room;

import android.os.Build;
import android.os.CancellationSignal;
import androidx.sqlite.p004db.SupportSQLiteCompat;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, mo20735d2 = {"<anonymous>", "", "R", "it", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CoroutinesRoom.kt */
final class CoroutinesRoom$Companion$execute$4$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ CancellationSignal $cancellationSignal;
    final /* synthetic */ Job $job;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CoroutinesRoom$Companion$execute$4$1(CancellationSignal cancellationSignal, Job job) {
        super(1);
        this.$cancellationSignal = cancellationSignal;
        this.$job = job;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        if (Build.VERSION.SDK_INT >= 16) {
            SupportSQLiteCompat.Api16Impl.cancel(this.$cancellationSignal);
        }
        Job.DefaultImpls.cancel$default(this.$job, (CancellationException) null, 1, (Object) null);
    }
}
