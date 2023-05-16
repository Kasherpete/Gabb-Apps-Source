package com.gabb.packageupdater.work;

import com.gabb.data.entities.App;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.InstallerWorker", mo21449f = "InstallerWorker.kt", mo21450i = {0, 0, 1, 1, 2, 2, 2}, mo21451l = {120, 140, 143}, mo21452m = "updateApp", mo21453n = {"this", "app", "this", "app", "this", "app", "result"}, mo21454s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "Z$0"})
/* compiled from: InstallerWorker.kt */
final class InstallerWorker$updateApp$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ InstallerWorker<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallerWorker$updateApp$1(InstallerWorker<T> installerWorker, Continuation<? super InstallerWorker$updateApp$1> continuation) {
        super(continuation);
        this.this$0 = installerWorker;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateApp((App) null, this);
    }
}
