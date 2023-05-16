package com.gabb.packageupdater.repository;

import com.gabb.packageupdater.sdk.UpdateCallbacks;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.repository.AppDownloader", mo21449f = "AppDownloader.kt", mo21450i = {}, mo21451l = {30}, mo21452m = "downloadApk", mo21453n = {}, mo21454s = {})
/* compiled from: AppDownloader.kt */
final class AppDownloader$downloadApk$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppDownloader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AppDownloader$downloadApk$1(AppDownloader appDownloader, Continuation<? super AppDownloader$downloadApk$1> continuation) {
        super(continuation);
        this.this$0 = appDownloader;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.downloadApk((String) null, (String) null, (String) null, (Function0<? extends UpdateCallbacks>) null, this);
    }
}
