package com.gabb.packageupdater.work;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.PeriodicUpdateWorker", mo21449f = "PeriodicUpdateWorker.kt", mo21450i = {0, 1}, mo21451l = {60, 62}, mo21452m = "downloadData", mo21453n = {"this", "apps"}, mo21454s = {"L$0", "L$0"})
/* compiled from: PeriodicUpdateWorker.kt */
final class PeriodicUpdateWorker$downloadData$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PeriodicUpdateWorker this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PeriodicUpdateWorker$downloadData$1(PeriodicUpdateWorker periodicUpdateWorker, Continuation<? super PeriodicUpdateWorker$downloadData$1> continuation) {
        super(continuation);
        this.this$0 = periodicUpdateWorker;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.downloadData(this);
    }
}
