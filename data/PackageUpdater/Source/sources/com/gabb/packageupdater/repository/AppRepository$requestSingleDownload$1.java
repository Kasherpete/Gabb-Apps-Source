package com.gabb.packageupdater.repository;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.repository.AppRepository", mo21449f = "AppRepository.kt", mo21450i = {0, 0, 1}, mo21451l = {44, 81}, mo21452m = "requestSingleDownload", mo21453n = {"this", "packageName", "installUri"}, mo21454s = {"L$0", "L$1", "L$0"})
/* compiled from: AppRepository.kt */
final class AppRepository$requestSingleDownload$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AppRepository$requestSingleDownload$1(AppRepository appRepository, Continuation<? super AppRepository$requestSingleDownload$1> continuation) {
        super(continuation);
        this.this$0 = appRepository;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.requestSingleDownload((String) null, (String) null, (String) null, (Function1<? super Integer, Unit>) null, this);
    }
}
