package com.gabb.packageupdater.work;

import com.gabb.data.entities.App;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.InstallerWorker", mo21449f = "InstallerWorker.kt", mo21450i = {0}, mo21451l = {106}, mo21452m = "removeApp", mo21453n = {"app"}, mo21454s = {"L$0"})
/* compiled from: InstallerWorker.kt */
final class InstallerWorker$removeApp$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ InstallerWorker<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallerWorker$removeApp$1(InstallerWorker<T> installerWorker, Continuation<? super InstallerWorker$removeApp$1> continuation) {
        super(continuation);
        this.this$0 = installerWorker;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.removeApp((App) null, this);
    }
}
