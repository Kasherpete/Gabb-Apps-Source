package com.gabb.packageupdater.work;

import androidx.work.ListenableWorker;
import com.gabb.data.entities.App;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.PeriodicUpdateWorker", mo21449f = "PeriodicUpdateWorker.kt", mo21450i = {0, 0, 1}, mo21451l = {44, 51}, mo21452m = "attemptWork", mo21453n = {"this", "withData", "this"}, mo21454s = {"L$0", "L$1", "L$0"})
/* compiled from: PeriodicUpdateWorker.kt */
final class PeriodicUpdateWorker$attemptWork$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PeriodicUpdateWorker this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PeriodicUpdateWorker$attemptWork$1(PeriodicUpdateWorker periodicUpdateWorker, Continuation<? super PeriodicUpdateWorker$attemptWork$1> continuation) {
        super(continuation);
        this.this$0 = periodicUpdateWorker;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.attemptWork((List<App>) null, (Continuation<? super ListenableWorker.Result>) this);
    }
}
