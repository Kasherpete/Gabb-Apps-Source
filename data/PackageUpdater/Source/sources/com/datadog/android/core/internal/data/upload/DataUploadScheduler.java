package com.datadog.android.core.internal.data.upload;

import com.datadog.android.core.configuration.UploadFrequency;
import com.datadog.android.core.internal.net.DataUploader;
import com.datadog.android.core.internal.net.info.NetworkInfoProvider;
import com.datadog.android.core.internal.persistence.DataReader;
import com.datadog.android.core.internal.system.SystemInfoProvider;
import com.datadog.android.core.internal.utils.ConcurrencyExtKt;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/core/internal/data/upload/DataUploadScheduler;", "Lcom/datadog/android/core/internal/data/upload/UploadScheduler;", "reader", "Lcom/datadog/android/core/internal/persistence/DataReader;", "dataUploader", "Lcom/datadog/android/core/internal/net/DataUploader;", "networkInfoProvider", "Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;", "systemInfoProvider", "Lcom/datadog/android/core/internal/system/SystemInfoProvider;", "uploadFrequency", "Lcom/datadog/android/core/configuration/UploadFrequency;", "scheduledThreadPoolExecutor", "Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "(Lcom/datadog/android/core/internal/persistence/DataReader;Lcom/datadog/android/core/internal/net/DataUploader;Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;Lcom/datadog/android/core/internal/system/SystemInfoProvider;Lcom/datadog/android/core/configuration/UploadFrequency;Ljava/util/concurrent/ScheduledThreadPoolExecutor;)V", "runnable", "Lcom/datadog/android/core/internal/data/upload/DataUploadRunnable;", "startScheduling", "", "stopScheduling", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DataUploadScheduler.kt */
public final class DataUploadScheduler implements UploadScheduler {
    private final DataUploadRunnable runnable;
    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public DataUploadScheduler(DataReader dataReader, DataUploader dataUploader, NetworkInfoProvider networkInfoProvider, SystemInfoProvider systemInfoProvider, UploadFrequency uploadFrequency, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor2) {
        Intrinsics.checkNotNullParameter(dataReader, "reader");
        Intrinsics.checkNotNullParameter(dataUploader, "dataUploader");
        Intrinsics.checkNotNullParameter(networkInfoProvider, "networkInfoProvider");
        Intrinsics.checkNotNullParameter(systemInfoProvider, "systemInfoProvider");
        Intrinsics.checkNotNullParameter(uploadFrequency, "uploadFrequency");
        Intrinsics.checkNotNullParameter(scheduledThreadPoolExecutor2, "scheduledThreadPoolExecutor");
        this.scheduledThreadPoolExecutor = scheduledThreadPoolExecutor2;
        this.runnable = new DataUploadRunnable(scheduledThreadPoolExecutor2, dataReader, dataUploader, networkInfoProvider, systemInfoProvider, uploadFrequency);
    }

    public void startScheduling() {
        ConcurrencyExtKt.scheduleSafe(this.scheduledThreadPoolExecutor, "Data upload", this.runnable.getCurrentDelayIntervalMs$dd_sdk_android_release(), TimeUnit.MILLISECONDS, this.runnable);
    }

    public void stopScheduling() {
        this.scheduledThreadPoolExecutor.remove(this.runnable);
    }
}
