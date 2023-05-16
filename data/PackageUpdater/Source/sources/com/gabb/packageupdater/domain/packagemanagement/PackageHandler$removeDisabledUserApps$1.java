package com.gabb.packageupdater.domain.packagemanagement;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.domain.packagemanagement.PackageHandler", mo21449f = "PackageHandler.kt", mo21450i = {0}, mo21451l = {60}, mo21452m = "removeDisabledUserApps", mo21453n = {"this"}, mo21454s = {"L$0"})
/* compiled from: PackageHandler.kt */
final class PackageHandler$removeDisabledUserApps$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PackageHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PackageHandler$removeDisabledUserApps$1(PackageHandler packageHandler, Continuation<? super PackageHandler$removeDisabledUserApps$1> continuation) {
        super(continuation);
        this.this$0 = packageHandler;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.removeDisabledUserApps(this);
    }
}
