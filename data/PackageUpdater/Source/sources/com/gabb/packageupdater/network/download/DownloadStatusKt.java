package com.gabb.packageupdater.network.download;

import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.ResponseBody;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, mo20735d2 = {"downloadWithProgress", "Lkotlinx/coroutines/flow/Flow;", "Lcom/gabb/packageupdater/network/download/DownloadStatus;", "Lokhttp3/ResponseBody;", "targetFile", "Ljava/io/File;", "(Lokhttp3/ResponseBody;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DownloadStatus.kt */
public final class DownloadStatusKt {
    public static final Object downloadWithProgress(ResponseBody responseBody, File file, Continuation<? super Flow<? extends DownloadStatus>> continuation) {
        return FlowKt.distinctUntilChanged(FlowKt.flowOn(FlowKt.flow(new DownloadStatusKt$downloadWithProgress$2(file, responseBody, (Continuation<? super DownloadStatusKt$downloadWithProgress$2>) null)), Dispatchers.getIO()));
    }
}
