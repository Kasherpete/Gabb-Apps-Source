package com.gabb.packageupdater.work;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.InstallerWorker", mo21449f = "InstallerWorker.kt", mo21450i = {0, 1, 2, 3, 4}, mo21451l = {44, 45, 47, 58, 64}, mo21452m = "doWork$suspendImpl", mo21453n = {"this", "this", "this", "this", "this"}, mo21454s = {"L$0", "L$0", "L$0", "L$0", "L$0"})
/* compiled from: InstallerWorker.kt */
final class InstallerWorker$doWork$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ InstallerWorker<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallerWorker$doWork$1(InstallerWorker<T> installerWorker, Continuation<? super InstallerWorker$doWork$1> continuation) {
        super(continuation);
        this.this$0 = installerWorker;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return InstallerWorker.doWork$suspendImpl(this.this$0, this);
    }
}
