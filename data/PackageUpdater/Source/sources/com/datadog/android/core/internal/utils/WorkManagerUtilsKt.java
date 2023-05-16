package com.datadog.android.core.internal.utils;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import com.datadog.android.core.internal.data.upload.UploadWorker;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\r"}, mo20735d2 = {"CANCEL_ERROR_MESSAGE", "", "DELAY_MS", "", "SETUP_ERROR_MESSAGE", "TAG_DATADOG_UPLOAD", "UPLOAD_WORKER_NAME", "UPLOAD_WORKER_WAS_SCHEDULED", "cancelUploadWorker", "", "context", "Landroid/content/Context;", "triggerUploadWorker", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WorkManagerUtils.kt */
public final class WorkManagerUtilsKt {
    public static final String CANCEL_ERROR_MESSAGE = "Error cancelling the UploadWorker";
    public static final long DELAY_MS = 5000;
    public static final String SETUP_ERROR_MESSAGE = "Error while trying to setup the UploadWorker";
    public static final String TAG_DATADOG_UPLOAD = "DatadogBackgroundUpload";
    public static final String UPLOAD_WORKER_NAME = "DatadogUploadWorker";
    public static final String UPLOAD_WORKER_WAS_SCHEDULED = "UploadWorker was scheduled.";

    public static final void cancelUploadWorker(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        try {
            WorkManager instance = WorkManager.getInstance(context);
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(context)");
            instance.cancelAllWorkByTag(TAG_DATADOG_UPLOAD);
        } catch (IllegalStateException e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), CANCEL_ERROR_MESSAGE, e, (Map) null, 4, (Object) null);
        }
    }

    public static final void triggerUploadWorker(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        try {
            WorkManager instance = WorkManager.getInstance(context);
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(context)");
            Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…TED)\n            .build()");
            WorkRequest build2 = ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(UploadWorker.class).setConstraints(build)).addTag(TAG_DATADOG_UPLOAD)).setInitialDelay(5000, TimeUnit.MILLISECONDS)).build();
            Intrinsics.checkNotNullExpressionValue(build2, "Builder(UploadWorker::cl…NDS)\n            .build()");
            instance.enqueueUniqueWork(UPLOAD_WORKER_NAME, ExistingWorkPolicy.REPLACE, (OneTimeWorkRequest) build2);
            Logger.i$default(RuntimeUtilsKt.getSdkLogger(), UPLOAD_WORKER_WAS_SCHEDULED, (Throwable) null, (Map) null, 6, (Object) null);
        } catch (Exception e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), SETUP_ERROR_MESSAGE, e, (Map) null, 4, (Object) null);
        }
    }
}
