package com.gabb.packageupdater.domain.packagemanagement;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller", mo21449f = "PrivilegedPackageInstaller.kt", mo21450i = {0, 0}, mo21451l = {31}, mo21452m = "install-0E7RQCE", mo21453n = {"this", "packageName"}, mo21454s = {"L$0", "L$1"})
/* compiled from: PrivilegedPackageInstaller.kt */
final class PrivilegedPackageInstaller$install$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PrivilegedPackageInstaller this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PrivilegedPackageInstaller$install$1(PrivilegedPackageInstaller privilegedPackageInstaller, Continuation<? super PrivilegedPackageInstaller$install$1> continuation) {
        super(continuation);
        this.this$0 = privilegedPackageInstaller;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r1 = this.this$0.m167install0E7RQCE((String) null, (Uri) null, this);
        return r1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r1 : Result.m175boximpl(r1);
    }
}
