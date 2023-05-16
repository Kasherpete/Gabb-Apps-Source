package com.gabb.packageupdater.repository;

import com.gabb.packageupdater.sdk.UpdateCallbacks;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.repository.AppDownloader$download$2$1", mo21449f = "AppDownloader.kt", mo21450i = {0, 1}, mo21451l = {50, 51, 52}, mo21452m = "invokeSuspend", mo21453n = {"target", "target"}, mo21454s = {"L$0", "L$0"})
/* compiled from: AppDownloader.kt */
final class AppDownloader$download$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CancellableContinuation<String> $cont;
    final /* synthetic */ Function0<UpdateCallbacks> $getCallbacks;
    final /* synthetic */ String $packageName;
    final /* synthetic */ String $url;
    final /* synthetic */ String $versionName;
    Object L$0;
    int label;
    final /* synthetic */ AppDownloader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AppDownloader$download$2$1(AppDownloader appDownloader, String str, String str2, String str3, Function0<? extends UpdateCallbacks> function0, CancellableContinuation<? super String> cancellableContinuation, Continuation<? super AppDownloader$download$2$1> continuation) {
        super(2, continuation);
        this.this$0 = appDownloader;
        this.$packageName = str;
        this.$versionName = str2;
        this.$url = str3;
        this.$getCallbacks = function0;
        this.$cont = cancellableContinuation;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppDownloader$download$2$1(this.this$0, this.$packageName, this.$versionName, this.$url, this.$getCallbacks, this.$cont, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppDownloader$download$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0082 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x002d
            if (r1 == r4) goto L_0x0025
            if (r1 == r3) goto L_0x001d
            if (r1 != r2) goto L_0x0015
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0083
        L_0x0015:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x001d:
            java.lang.Object r1 = r8.L$0
            java.io.File r1 = (java.io.File) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0067
        L_0x0025:
            java.lang.Object r1 = r8.L$0
            java.io.File r1 = (java.io.File) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0057
        L_0x002d:
            kotlin.ResultKt.throwOnFailure(r9)
            com.gabb.packageupdater.repository.AppDownloader r9 = r8.this$0
            com.gabb.packageupdater.repository.ApkCache r9 = r9.apkCache
            java.lang.String r1 = r8.$packageName
            java.lang.String r5 = r8.$versionName
            java.io.File r9 = r9.targetStorageLocation(r1, r5)
            com.gabb.packageupdater.repository.AppDownloader r1 = r8.this$0
            com.gabb.packageupdater.network.interfaces.FileDownloadApi r1 = r1.fileDownloadApi
            java.lang.String r5 = r8.$url
            r6 = r8
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r8.L$0 = r9
            r8.label = r4
            java.lang.Object r1 = r1.downloadApk(r5, r6)
            if (r1 != r0) goto L_0x0054
            return r0
        L_0x0054:
            r7 = r1
            r1 = r9
            r9 = r7
        L_0x0057:
            okhttp3.ResponseBody r9 = (okhttp3.ResponseBody) r9
            r4 = r8
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r8.L$0 = r1
            r8.label = r3
            java.lang.Object r9 = com.gabb.packageupdater.network.download.DownloadStatusKt.downloadWithProgress(r9, r1, r4)
            if (r9 != r0) goto L_0x0067
            return r0
        L_0x0067:
            kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
            com.gabb.packageupdater.repository.AppDownloader$download$2$1$1 r3 = new com.gabb.packageupdater.repository.AppDownloader$download$2$1$1
            kotlin.jvm.functions.Function0<com.gabb.packageupdater.sdk.UpdateCallbacks> r4 = r8.$getCallbacks
            kotlinx.coroutines.CancellableContinuation<java.lang.String> r5 = r8.$cont
            r3.<init>(r4, r5, r1)
            kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
            r1 = r8
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4 = 0
            r8.L$0 = r4
            r8.label = r2
            java.lang.Object r8 = r9.collect(r3, r1)
            if (r8 != r0) goto L_0x0083
            return r0
        L_0x0083:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppDownloader$download$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
