package com.gabb.packageupdater.repository;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.repository.AppRepository", mo21449f = "AppRepository.kt", mo21450i = {0}, mo21451l = {125}, mo21452m = "checkAppsNeedUpdate-IoAF18A", mo21453n = {"this"}, mo21454s = {"L$0"})
/* compiled from: AppRepository.kt */
final class AppRepository$checkAppsNeedUpdate$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AppRepository$checkAppsNeedUpdate$1(AppRepository appRepository, Continuation<? super AppRepository$checkAppsNeedUpdate$1> continuation) {
        super(continuation);
        this.this$0 = appRepository;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r1 = this.this$0.m169checkAppsNeedUpdateIoAF18A(this);
        return r1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r1 : Result.m175boximpl(r1);
    }
}
