package com.gabb.packageupdater.network.download;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.ResponseBody;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/gabb/packageupdater/network/download/DownloadStatus;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.network.download.DownloadStatusKt$downloadWithProgress$2", mo21449f = "DownloadStatus.kt", mo21450i = {0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3}, mo21451l = {19, 44, 58, 60}, mo21452m = "invokeSuspend", mo21453n = {"$this$flow", "$this$flow", "deleteFile", "inputStream", "outputStream", "data", "totalBytes", "progressBytes", "$this$flow", "deleteFile", "deleteFile"}, mo21454s = {"L$0", "L$0", "L$1", "L$3", "L$5", "L$6", "J$0", "J$1", "L$0", "L$1", "L$0"})
/* compiled from: DownloadStatus.kt */
final class DownloadStatusKt$downloadWithProgress$2 extends SuspendLambda implements Function2<FlowCollector<? super DownloadStatus>, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $targetFile;
    final /* synthetic */ ResponseBody $this_downloadWithProgress;
    long J$0;
    long J$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DownloadStatusKt$downloadWithProgress$2(File file, ResponseBody responseBody, Continuation<? super DownloadStatusKt$downloadWithProgress$2> continuation) {
        super(2, continuation);
        this.$targetFile = file;
        this.$this_downloadWithProgress = responseBody;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DownloadStatusKt$downloadWithProgress$2 downloadStatusKt$downloadWithProgress$2 = new DownloadStatusKt$downloadWithProgress$2(this.$targetFile, this.$this_downloadWithProgress, continuation);
        downloadStatusKt$downloadWithProgress$2.L$0 = obj;
        return downloadStatusKt$downloadWithProgress$2;
    }

    public final Object invoke(FlowCollector<? super DownloadStatus> flowCollector, Continuation<? super Unit> continuation) {
        return ((DownloadStatusKt$downloadWithProgress$2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v49, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: kotlin.jvm.internal.Ref$BooleanRef} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v50, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: kotlinx.coroutines.flow.FlowCollector} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v54, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: kotlin.jvm.internal.Ref$BooleanRef} */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01c2, code lost:
        if (r2.element != false) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01c8, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0114, code lost:
        if (r4.element == false) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0116, code lost:
        r1.$targetFile.delete();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x01bf A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e3 A[Catch:{ all -> 0x0172 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e7 A[Catch:{ all -> 0x0172 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x012e A[Catch:{ all -> 0x0172 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0136 A[Catch:{ all -> 0x0172 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            r20 = this;
            r1 = r20
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r0 == 0) goto L_0x007e
            if (r0 == r6) goto L_0x0076
            if (r0 == r5) goto L_0x0044
            if (r0 == r4) goto L_0x002c
            if (r0 != r3) goto L_0x0024
            java.lang.Object r0 = r1.L$0
            r2 = r0
            kotlin.jvm.internal.Ref$BooleanRef r2 = (kotlin.jvm.internal.Ref.BooleanRef) r2
            kotlin.ResultKt.throwOnFailure(r21)     // Catch:{ all -> 0x0021 }
            goto L_0x01c0
        L_0x0021:
            r0 = move-exception
            goto L_0x01c9
        L_0x0024:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x002c:
            java.lang.Object r0 = r1.L$1
            r4 = r0
            kotlin.jvm.internal.Ref$BooleanRef r4 = (kotlin.jvm.internal.Ref.BooleanRef) r4
            java.lang.Object r0 = r1.L$0
            r5 = r0
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            kotlin.ResultKt.throwOnFailure(r21)     // Catch:{ Exception -> 0x003f, all -> 0x003b }
            goto L_0x0112
        L_0x003b:
            r0 = move-exception
            r2 = r4
            goto L_0x01c9
        L_0x003f:
            r0 = move-exception
            r3 = r2
            r2 = r4
            goto L_0x019d
        L_0x0044:
            long r8 = r1.J$1
            long r10 = r1.J$0
            java.lang.Object r0 = r1.L$6
            byte[] r0 = (byte[]) r0
            java.lang.Object r6 = r1.L$5
            java.io.FileOutputStream r6 = (java.io.FileOutputStream) r6
            java.lang.Object r12 = r1.L$4
            java.io.Closeable r12 = (java.io.Closeable) r12
            java.lang.Object r13 = r1.L$3
            java.io.InputStream r13 = (java.io.InputStream) r13
            java.lang.Object r14 = r1.L$2
            java.io.Closeable r14 = (java.io.Closeable) r14
            java.lang.Object r15 = r1.L$1
            kotlin.jvm.internal.Ref$BooleanRef r15 = (kotlin.jvm.internal.Ref.BooleanRef) r15
            java.lang.Object r3 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
            kotlin.ResultKt.throwOnFailure(r21)     // Catch:{ all -> 0x0072 }
            r7 = r6
            r6 = r3
            r3 = r2
            r2 = r15
            r18 = r8
            r8 = r5
            r4 = r18
            goto L_0x016a
        L_0x0072:
            r0 = move-exception
            r4 = r0
            goto L_0x017b
        L_0x0076:
            java.lang.Object r0 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            kotlin.ResultKt.throwOnFailure(r21)
            goto L_0x0095
        L_0x007e:
            kotlin.ResultKt.throwOnFailure(r21)
            java.lang.Object r0 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            com.gabb.packageupdater.network.download.DownloadStatus$Started r3 = com.gabb.packageupdater.network.download.DownloadStatus.Started.INSTANCE
            r8 = r1
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r1.L$0 = r0
            r1.label = r6
            java.lang.Object r3 = r0.emit(r3, r8)
            if (r3 != r2) goto L_0x0095
            return r2
        L_0x0095:
            r3 = r0
            kotlin.jvm.internal.Ref$BooleanRef r8 = new kotlin.jvm.internal.Ref$BooleanRef
            r8.<init>()
            r8.element = r6
            java.io.File r0 = r1.$targetFile
            java.io.File r0 = r0.getParentFile()
            if (r0 != 0) goto L_0x00a6
            goto L_0x00ad
        L_0x00a6:
            boolean r0 = r0.mkdirs()
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
        L_0x00ad:
            okhttp3.ResponseBody r0 = r1.$this_downloadWithProgress     // Catch:{ Exception -> 0x0199, all -> 0x0196 }
            java.io.InputStream r0 = r0.byteStream()     // Catch:{ Exception -> 0x0199, all -> 0x0196 }
            r14 = r0
            java.io.Closeable r14 = (java.io.Closeable) r14     // Catch:{ Exception -> 0x0199, all -> 0x0196 }
            java.io.File r0 = r1.$targetFile     // Catch:{ Exception -> 0x0199, all -> 0x0196 }
            okhttp3.ResponseBody r6 = r1.$this_downloadWithProgress     // Catch:{ Exception -> 0x0199, all -> 0x0196 }
            r9 = r14
            java.io.InputStream r9 = (java.io.InputStream) r9     // Catch:{ all -> 0x0188 }
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ all -> 0x0188 }
            r10.<init>(r0)     // Catch:{ all -> 0x0188 }
            r12 = r10
            java.io.Closeable r12 = (java.io.Closeable) r12     // Catch:{ all -> 0x0188 }
            r0 = r12
            java.io.FileOutputStream r0 = (java.io.FileOutputStream) r0     // Catch:{ all -> 0x0178 }
            long r10 = r6.contentLength()     // Catch:{ all -> 0x0178 }
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r6]     // Catch:{ all -> 0x0178 }
            r16 = 0
            r13 = r9
            r18 = r8
            r8 = r0
            r0 = r6
            r6 = r3
            r3 = r2
            r2 = r18
        L_0x00db:
            int r9 = r13.read(r0)     // Catch:{ all -> 0x0172 }
            r15 = -1
            r5 = 0
            if (r9 != r15) goto L_0x0136
            int r0 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r0 < 0) goto L_0x012e
            if (r0 > 0) goto L_0x0126
            r2.element = r5     // Catch:{ all -> 0x0172 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0172 }
            kotlin.p007io.CloseableKt.closeFinally(r12, r7)     // Catch:{ all -> 0x0121 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0121 }
            kotlin.p007io.CloseableKt.closeFinally(r14, r7)     // Catch:{ Exception -> 0x011d }
            com.gabb.packageupdater.network.download.DownloadStatus$Finished r0 = com.gabb.packageupdater.network.download.DownloadStatus.Finished.INSTANCE     // Catch:{ Exception -> 0x011d }
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch:{ Exception -> 0x011d }
            r1.L$0 = r6     // Catch:{ Exception -> 0x011d }
            r1.L$1 = r2     // Catch:{ Exception -> 0x011d }
            r1.L$2 = r7     // Catch:{ Exception -> 0x011d }
            r1.L$3 = r7     // Catch:{ Exception -> 0x011d }
            r1.L$4 = r7     // Catch:{ Exception -> 0x011d }
            r1.L$5 = r7     // Catch:{ Exception -> 0x011d }
            r1.L$6 = r7     // Catch:{ Exception -> 0x011d }
            r1.label = r4     // Catch:{ Exception -> 0x011d }
            java.lang.Object r0 = r6.emit(r0, r5)     // Catch:{ Exception -> 0x011d }
            if (r0 != r3) goto L_0x0111
            return r3
        L_0x0111:
            r4 = r2
        L_0x0112:
            boolean r0 = r4.element
            if (r0 == 0) goto L_0x01c6
        L_0x0116:
            java.io.File r0 = r1.$targetFile
            r0.delete()
            goto L_0x01c6
        L_0x011d:
            r0 = move-exception
            r5 = r6
            goto L_0x019d
        L_0x0121:
            r0 = move-exception
            r4 = r0
            r5 = r6
            goto L_0x018d
        L_0x0126:
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ all -> 0x0172 }
            java.lang.String r4 = "too many bytes"
            r0.<init>(r4)     // Catch:{ all -> 0x0172 }
            throw r0     // Catch:{ all -> 0x0172 }
        L_0x012e:
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ all -> 0x0172 }
            java.lang.String r4 = "missing bytes"
            r0.<init>(r4)     // Catch:{ all -> 0x0172 }
            throw r0     // Catch:{ all -> 0x0172 }
        L_0x0136:
            r8.getChannel()     // Catch:{ all -> 0x0172 }
            r8.write(r0, r5, r9)     // Catch:{ all -> 0x0172 }
            long r4 = (long) r9     // Catch:{ all -> 0x0172 }
            long r4 = r16 + r4
            com.gabb.packageupdater.network.download.DownloadStatus$Progress r9 = new com.gabb.packageupdater.network.download.DownloadStatus$Progress     // Catch:{ all -> 0x0172 }
            r15 = 100
            r21 = r8
            long r7 = (long) r15     // Catch:{ all -> 0x0172 }
            long r7 = r7 * r4
            long r7 = r7 / r10
            int r7 = (int) r7     // Catch:{ all -> 0x0172 }
            r9.<init>(r7)     // Catch:{ all -> 0x0172 }
            r1.L$0 = r6     // Catch:{ all -> 0x0172 }
            r1.L$1 = r2     // Catch:{ all -> 0x0172 }
            r1.L$2 = r14     // Catch:{ all -> 0x0172 }
            r1.L$3 = r13     // Catch:{ all -> 0x0172 }
            r1.L$4 = r12     // Catch:{ all -> 0x0172 }
            r7 = r21
            r1.L$5 = r7     // Catch:{ all -> 0x0172 }
            r1.L$6 = r0     // Catch:{ all -> 0x0172 }
            r1.J$0 = r10     // Catch:{ all -> 0x0172 }
            r1.J$1 = r4     // Catch:{ all -> 0x0172 }
            r8 = 2
            r1.label = r8     // Catch:{ all -> 0x0172 }
            java.lang.Object r9 = r6.emit(r9, r1)     // Catch:{ all -> 0x0172 }
            if (r9 != r3) goto L_0x016a
            return r3
        L_0x016a:
            r16 = r4
            r5 = r8
            r4 = 3
            r8 = r7
            r7 = 0
            goto L_0x00db
        L_0x0172:
            r0 = move-exception
            r4 = r0
            r15 = r2
            r2 = r3
            r3 = r6
            goto L_0x017b
        L_0x0178:
            r0 = move-exception
            r4 = r0
            r15 = r8
        L_0x017b:
            throw r4     // Catch:{ all -> 0x017c }
        L_0x017c:
            r0 = move-exception
            r5 = r0
            kotlin.p007io.CloseableKt.closeFinally(r12, r4)     // Catch:{ all -> 0x0182 }
            throw r5     // Catch:{ all -> 0x0182 }
        L_0x0182:
            r0 = move-exception
            r4 = r0
            r5 = r3
            r3 = r2
            r2 = r15
            goto L_0x018d
        L_0x0188:
            r0 = move-exception
            r4 = r0
            r5 = r3
            r3 = r2
            r2 = r8
        L_0x018d:
            throw r4     // Catch:{ all -> 0x018e }
        L_0x018e:
            r0 = move-exception
            r6 = r0
            kotlin.p007io.CloseableKt.closeFinally(r14, r4)     // Catch:{ Exception -> 0x0194 }
            throw r6     // Catch:{ Exception -> 0x0194 }
        L_0x0194:
            r0 = move-exception
            goto L_0x019d
        L_0x0196:
            r0 = move-exception
            r2 = r8
            goto L_0x01c9
        L_0x0199:
            r0 = move-exception
            r5 = r3
            r3 = r2
            r2 = r8
        L_0x019d:
            com.gabb.packageupdater.network.download.DownloadStatus$Error r4 = new com.gabb.packageupdater.network.download.DownloadStatus$Error     // Catch:{ all -> 0x0021 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x0021 }
            r4.<init>(r0)     // Catch:{ all -> 0x0021 }
            r0 = r1
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0     // Catch:{ all -> 0x0021 }
            r1.L$0 = r2     // Catch:{ all -> 0x0021 }
            r6 = 0
            r1.L$1 = r6     // Catch:{ all -> 0x0021 }
            r1.L$2 = r6     // Catch:{ all -> 0x0021 }
            r1.L$3 = r6     // Catch:{ all -> 0x0021 }
            r1.L$4 = r6     // Catch:{ all -> 0x0021 }
            r1.L$5 = r6     // Catch:{ all -> 0x0021 }
            r1.L$6 = r6     // Catch:{ all -> 0x0021 }
            r6 = 4
            r1.label = r6     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r5.emit(r4, r0)     // Catch:{ all -> 0x0021 }
            if (r0 != r3) goto L_0x01c0
            return r3
        L_0x01c0:
            boolean r0 = r2.element
            if (r0 == 0) goto L_0x01c6
            goto L_0x0116
        L_0x01c6:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01c9:
            boolean r2 = r2.element
            if (r2 == 0) goto L_0x01d2
            java.io.File r1 = r1.$targetFile
            r1.delete()
        L_0x01d2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.network.download.DownloadStatusKt$downloadWithProgress$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
