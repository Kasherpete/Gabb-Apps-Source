package com.gabb.packageupdater.work;

import com.gabb.data.entities.App;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.PeriodicUpdateWorker", mo21449f = "PeriodicUpdateWorker.kt", mo21450i = {}, mo21451l = {80}, mo21452m = "updateApps", mo21453n = {}, mo21454s = {})
/* compiled from: PeriodicUpdateWorker.kt */
final class PeriodicUpdateWorker$updateApps$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PeriodicUpdateWorker this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PeriodicUpdateWorker$updateApps$1(PeriodicUpdateWorker periodicUpdateWorker, Continuation<? super PeriodicUpdateWorker$updateApps$1> continuation) {
        super(continuation);
        this.this$0 = periodicUpdateWorker;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateApps((List<App>) null, this);
    }
}
