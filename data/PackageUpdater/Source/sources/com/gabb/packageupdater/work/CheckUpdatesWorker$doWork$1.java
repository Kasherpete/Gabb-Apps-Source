package com.gabb.packageupdater.work;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.CheckUpdatesWorker", mo21449f = "CheckUpdatesWorker.kt", mo21450i = {0}, mo21451l = {25}, mo21452m = "doWork", mo21453n = {"this"}, mo21454s = {"L$0"})
/* compiled from: CheckUpdatesWorker.kt */
final class CheckUpdatesWorker$doWork$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CheckUpdatesWorker this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CheckUpdatesWorker$doWork$1(CheckUpdatesWorker checkUpdatesWorker, Continuation<? super CheckUpdatesWorker$doWork$1> continuation) {
        super(continuation);
        this.this$0 = checkUpdatesWorker;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doWork(this);
    }
}
