package com.datadog.android.core.internal.data.upload;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.datadog.android.Datadog;
import com.datadog.android.core.internal.net.DataUploader;
import com.datadog.android.core.internal.net.UploadStatus;
import com.datadog.android.core.internal.persistence.Batch;
import com.datadog.android.core.internal.persistence.DataReader;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.error.internal.CrashReportsFeature;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.LogsFeature;
import com.datadog.android.rum.internal.RumFeature;
import com.datadog.android.tracing.internal.TracingFeature;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/core/internal/data/upload/UploadWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "consumeBatch", "", "batch", "Lcom/datadog/android/core/internal/persistence/Batch;", "uploader", "Lcom/datadog/android/core/internal/net/DataUploader;", "doWork", "Landroidx/work/ListenableWorker$Result;", "uploadAllBatches", "", "reader", "Lcom/datadog/android/core/internal/persistence/DataReader;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UploadWorker.kt */
public final class UploadWorker extends Worker {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "UploadWorker";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UploadWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(workerParameters, "workerParams");
    }

    public ListenableWorker.Result doWork() {
        if (!Datadog.isInitialized()) {
            Logger.e$default(RuntimeUtilsKt.getDevLogger(), Datadog.MESSAGE_NOT_INITIALIZED, (Throwable) null, (Map) null, 6, (Object) null);
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkNotNullExpressionValue(success, "success()");
            return success;
        }
        uploadAllBatches(CrashReportsFeature.INSTANCE.getPersistenceStrategy$dd_sdk_android_release().getReader(), CrashReportsFeature.INSTANCE.getUploader$dd_sdk_android_release());
        uploadAllBatches(LogsFeature.INSTANCE.getPersistenceStrategy$dd_sdk_android_release().getReader(), LogsFeature.INSTANCE.getUploader$dd_sdk_android_release());
        uploadAllBatches(TracingFeature.INSTANCE.getPersistenceStrategy$dd_sdk_android_release().getReader(), TracingFeature.INSTANCE.getUploader$dd_sdk_android_release());
        uploadAllBatches(RumFeature.INSTANCE.getPersistenceStrategy$dd_sdk_android_release().getReader(), RumFeature.INSTANCE.getUploader$dd_sdk_android_release());
        ListenableWorker.Result success2 = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(success2, "success()");
        return success2;
    }

    private final void uploadAllBatches(DataReader dataReader, DataUploader dataUploader) {
        Batch lockAndReadNext;
        List<Batch> arrayList = new ArrayList<>();
        do {
            lockAndReadNext = dataReader.lockAndReadNext();
            if (lockAndReadNext != null) {
                if (consumeBatch(lockAndReadNext, dataUploader)) {
                    dataReader.drop(lockAndReadNext);
                    continue;
                } else {
                    arrayList.add(lockAndReadNext);
                    continue;
                }
            }
        } while (lockAndReadNext != null);
        for (Batch release : arrayList) {
            dataReader.release(release);
        }
    }

    private final boolean consumeBatch(Batch batch, DataUploader dataUploader) {
        UploadStatus upload = dataUploader.upload(batch.getData());
        String simpleName = dataUploader.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "uploader.javaClass.simpleName");
        UploadStatus uploadStatus = upload;
        UploadStatus.logStatus$default(uploadStatus, simpleName, batch.getData().length, RuntimeUtilsKt.getDevLogger(), false, false, (String) null, 32, (Object) null);
        String simpleName2 = dataUploader.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName2, "uploader.javaClass.simpleName");
        UploadStatus.logStatus$default(uploadStatus, simpleName2, batch.getData().length, RuntimeUtilsKt.getSdkLogger(), true, true, (String) null, 32, (Object) null);
        return upload == UploadStatus.SUCCESS;
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/core/internal/data/upload/UploadWorker$Companion;", "", "()V", "TAG", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: UploadWorker.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
