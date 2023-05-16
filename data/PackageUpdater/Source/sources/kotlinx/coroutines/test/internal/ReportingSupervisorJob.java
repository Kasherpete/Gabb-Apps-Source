package kotlinx.coroutines.test.internal;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, mo20735d2 = {"Lkotlinx/coroutines/test/internal/ReportingSupervisorJob;", "Lkotlinx/coroutines/JobImpl;", "parent", "Lkotlinx/coroutines/Job;", "onChildCancellation", "Lkotlin/Function1;", "", "", "(Lkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function1;)V", "getOnChildCancellation", "()Lkotlin/jvm/functions/Function1;", "childCancelled", "", "cause", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ReportingSupervisorJob.kt */
public final class ReportingSupervisorJob extends JobImpl {
    private final Function1<Throwable, Unit> onChildCancellation;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ReportingSupervisorJob(Job job, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : job, function1);
    }

    public final Function1<Throwable, Unit> getOnChildCancellation() {
        return this.onChildCancellation;
    }

    public ReportingSupervisorJob(Job job, Function1<? super Throwable, Unit> function1) {
        super(job);
        this.onChildCancellation = function1;
    }

    public boolean childCancelled(Throwable th) {
        try {
            this.onChildCancellation.invoke(th);
        } catch (Throwable th2) {
            ExceptionsKt.addSuppressed(th, th2);
            CoroutineExceptionHandlerKt.handleCoroutineException(this, th);
        }
        Unit unit = Unit.INSTANCE;
        return false;
    }
}
