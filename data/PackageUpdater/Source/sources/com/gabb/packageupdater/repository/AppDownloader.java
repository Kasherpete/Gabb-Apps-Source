package com.gabb.packageupdater.repository;

import com.gabb.packageupdater.network.interfaces.FileDownloadApi;
import com.gabb.packageupdater.sdk.UpdateCallbacks;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Singleton
@Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0010\b\u0002\u0010\r\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000fJ9\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J;\u0010\u0017\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\bX\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, mo20735d2 = {"Lcom/gabb/packageupdater/repository/AppDownloader;", "Lkotlinx/coroutines/CoroutineScope;", "fileDownloadApi", "Lcom/gabb/packageupdater/network/interfaces/FileDownloadApi;", "apkCache", "Lcom/gabb/packageupdater/repository/ApkCache;", "(Lcom/gabb/packageupdater/network/interfaces/FileDownloadApi;Lcom/gabb/packageupdater/repository/ApkCache;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "clearCache", "", "packageName", "", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageName;", "download", "versionName", "url", "getCallbacks", "Lkotlin/Function0;", "Lcom/gabb/packageupdater/sdk/UpdateCallbacks;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadApk", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: AppDownloader.kt */
public final class AppDownloader implements CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    /* access modifiers changed from: private */
    public final ApkCache apkCache;
    /* access modifiers changed from: private */
    public final FileDownloadApi fileDownloadApi;

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Inject
    public AppDownloader(FileDownloadApi fileDownloadApi2, ApkCache apkCache2) {
        Intrinsics.checkNotNullParameter(fileDownloadApi2, "fileDownloadApi");
        Intrinsics.checkNotNullParameter(apkCache2, "apkCache");
        this.fileDownloadApi = fileDownloadApi2;
        this.apkCache = apkCache2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object downloadApk(java.lang.String r8, java.lang.String r9, java.lang.String r10, kotlin.jvm.functions.Function0<? extends com.gabb.packageupdater.sdk.UpdateCallbacks> r11, kotlin.coroutines.Continuation<? super java.lang.String> r12) {
        /*
            r7 = this;
            boolean r0 = r12 instanceof com.gabb.packageupdater.repository.AppDownloader$downloadApk$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            com.gabb.packageupdater.repository.AppDownloader$downloadApk$1 r0 = (com.gabb.packageupdater.repository.AppDownloader$downloadApk$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.repository.AppDownloader$downloadApk$1 r0 = new com.gabb.packageupdater.repository.AppDownloader$downloadApk$1
            r0.<init>(r7, r12)
        L_0x0019:
            r6 = r0
            java.lang.Object r12 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0033
            if (r1 != r2) goto L_0x002b
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x004f }
            goto L_0x0048
        L_0x002b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result$Companion r12 = kotlin.Result.Companion     // Catch:{ all -> 0x004f }
            r1 = r7
            com.gabb.packageupdater.repository.AppDownloader r1 = (com.gabb.packageupdater.repository.AppDownloader) r1     // Catch:{ all -> 0x004f }
            r6.label = r2     // Catch:{ all -> 0x004f }
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            java.lang.Object r12 = r1.download(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x004f }
            if (r12 != r0) goto L_0x0048
            return r0
        L_0x0048:
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x004f }
            java.lang.Object r7 = kotlin.Result.m176constructorimpl(r12)     // Catch:{ all -> 0x004f }
            goto L_0x005a
        L_0x004f:
            r7 = move-exception
            kotlin.Result$Companion r8 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m176constructorimpl(r7)
        L_0x005a:
            java.lang.Throwable r8 = kotlin.Result.m179exceptionOrNullimpl(r7)
            if (r8 == 0) goto L_0x0063
            r8.printStackTrace()
        L_0x0063:
            boolean r8 = kotlin.Result.m182isFailureimpl(r7)
            if (r8 == 0) goto L_0x006a
            r7 = 0
        L_0x006a:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppDownloader.downloadApk(java.lang.String, java.lang.String, java.lang.String, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ void clearCache$default(AppDownloader appDownloader, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        appDownloader.clearCache(str);
    }

    public final void clearCache(String str) {
        if (str != null) {
            this.apkCache.delete(str);
        } else {
            this.apkCache.deleteAll();
        }
    }

    /* access modifiers changed from: private */
    public final Object download(String str, String str2, String str3, Function0<? extends UpdateCallbacks> function0, Continuation<? super String> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new AppDownloader$download$2$1(this, str, str2, str3, function0, cancellableContinuationImpl, (Continuation<? super AppDownloader$download$2$1>) null), 3, (Object) null);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
