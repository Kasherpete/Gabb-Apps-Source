package com.gabb.packageupdater.work;

import androidx.work.ListenableWorker;
import com.gabb.data.entities.App;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.SingleShotInstallWorker", mo21449f = "SingleShotInstallWorker.kt", mo21450i = {}, mo21451l = {63}, mo21452m = "attemptWork", mo21453n = {}, mo21454s = {})
/* compiled from: SingleShotInstallWorker.kt */
final class SingleShotInstallWorker$attemptWork$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SingleShotInstallWorker this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SingleShotInstallWorker$attemptWork$1(SingleShotInstallWorker singleShotInstallWorker, Continuation<? super SingleShotInstallWorker$attemptWork$1> continuation) {
        super(continuation);
        this.this$0 = singleShotInstallWorker;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.attemptWork((App) null, (Continuation<? super ListenableWorker.Result>) this);
    }
}
