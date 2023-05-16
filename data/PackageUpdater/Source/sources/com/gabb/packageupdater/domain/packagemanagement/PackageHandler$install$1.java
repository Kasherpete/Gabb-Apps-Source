package com.gabb.packageupdater.domain.packagemanagement;

import com.gabb.packageupdater.data.resultentities.AppInstallInfo;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.domain.packagemanagement.PackageHandler", mo21449f = "PackageHandler.kt", mo21450i = {}, mo21451l = {43}, mo21452m = "install", mo21453n = {}, mo21454s = {})
/* compiled from: PackageHandler.kt */
final class PackageHandler$install$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PackageHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PackageHandler$install$1(PackageHandler packageHandler, Continuation<? super PackageHandler$install$1> continuation) {
        super(continuation);
        this.this$0 = packageHandler;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.install((AppInstallInfo) null, this);
    }
}
