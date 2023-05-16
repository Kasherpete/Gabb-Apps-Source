package com.datadog.android.core.internal.data.upload;

import com.datadog.android.core.configuration.UploadFrequency;
import com.datadog.android.core.internal.net.DataUploader;
import com.datadog.android.core.internal.net.info.NetworkInfoProvider;
import com.datadog.android.core.internal.persistence.Batch;
import com.datadog.android.core.internal.persistence.DataReader;
import com.datadog.android.core.internal.system.SystemInfo;
import com.datadog.android.core.internal.system.SystemInfoProvider;
import com.datadog.android.core.internal.utils.ConcurrencyExtKt;
import com.datadog.android.core.model.NetworkInfo;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001cH\u0002J\b\u0010 \u001a\u00020\u001cH\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0002J\b\u0010$\u001a\u00020\u001cH\u0016J\b\u0010%\u001a\u00020\u001cH\u0002R\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, mo20735d2 = {"Lcom/datadog/android/core/internal/data/upload/DataUploadRunnable;", "Lcom/datadog/android/core/internal/data/upload/UploadRunnable;", "threadPoolExecutor", "Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "reader", "Lcom/datadog/android/core/internal/persistence/DataReader;", "dataUploader", "Lcom/datadog/android/core/internal/net/DataUploader;", "networkInfoProvider", "Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;", "systemInfoProvider", "Lcom/datadog/android/core/internal/system/SystemInfoProvider;", "uploadFrequency", "Lcom/datadog/android/core/configuration/UploadFrequency;", "(Ljava/util/concurrent/ScheduledThreadPoolExecutor;Lcom/datadog/android/core/internal/persistence/DataReader;Lcom/datadog/android/core/internal/net/DataUploader;Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;Lcom/datadog/android/core/internal/system/SystemInfoProvider;Lcom/datadog/android/core/configuration/UploadFrequency;)V", "currentDelayIntervalMs", "", "getCurrentDelayIntervalMs$dd_sdk_android_release", "()J", "setCurrentDelayIntervalMs$dd_sdk_android_release", "(J)V", "maxDelayMs", "getMaxDelayMs$dd_sdk_android_release", "setMaxDelayMs$dd_sdk_android_release", "minDelayMs", "getMinDelayMs$dd_sdk_android_release", "setMinDelayMs$dd_sdk_android_release", "consumeBatch", "", "batch", "Lcom/datadog/android/core/internal/persistence/Batch;", "decreaseInterval", "increaseInterval", "isNetworkAvailable", "", "isSystemReady", "run", "scheduleNextUpload", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DataUploadRunnable.kt */
public final class DataUploadRunnable implements UploadRunnable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DECREASE_PERCENT = 90;
    public static final int DEFAULT_DELAY_FACTOR = 5;
    public static final int INCREASE_PERCENT = 110;
    public static final int LOW_BATTERY_THRESHOLD = 10;
    public static final int MAX_DELAY_FACTOR = 10;
    public static final int MIN_DELAY_FACTOR = 1;
    private long currentDelayIntervalMs;
    private final DataUploader dataUploader;
    private long maxDelayMs;
    private long minDelayMs;
    private final NetworkInfoProvider networkInfoProvider;
    private final DataReader reader;
    private final SystemInfoProvider systemInfoProvider;
    private final ScheduledThreadPoolExecutor threadPoolExecutor;

    public DataUploadRunnable(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, DataReader dataReader, DataUploader dataUploader2, NetworkInfoProvider networkInfoProvider2, SystemInfoProvider systemInfoProvider2, UploadFrequency uploadFrequency) {
        Intrinsics.checkNotNullParameter(scheduledThreadPoolExecutor, "threadPoolExecutor");
        Intrinsics.checkNotNullParameter(dataReader, "reader");
        Intrinsics.checkNotNullParameter(dataUploader2, "dataUploader");
        Intrinsics.checkNotNullParameter(networkInfoProvider2, "networkInfoProvider");
        Intrinsics.checkNotNullParameter(systemInfoProvider2, "systemInfoProvider");
        Intrinsics.checkNotNullParameter(uploadFrequency, "uploadFrequency");
        this.threadPoolExecutor = scheduledThreadPoolExecutor;
        this.reader = dataReader;
        this.dataUploader = dataUploader2;
        this.networkInfoProvider = networkInfoProvider2;
        this.systemInfoProvider = systemInfoProvider2;
        this.currentDelayIntervalMs = ((long) 5) * uploadFrequency.getBaseStepMs$dd_sdk_android_release();
        this.minDelayMs = uploadFrequency.getBaseStepMs$dd_sdk_android_release() * 1;
        this.maxDelayMs = ((long) 10) * uploadFrequency.getBaseStepMs$dd_sdk_android_release();
    }

    public final long getCurrentDelayIntervalMs$dd_sdk_android_release() {
        return this.currentDelayIntervalMs;
    }

    public final void setCurrentDelayIntervalMs$dd_sdk_android_release(long j) {
        this.currentDelayIntervalMs = j;
    }

    public final long getMinDelayMs$dd_sdk_android_release() {
        return this.minDelayMs;
    }

    public final void setMinDelayMs$dd_sdk_android_release(long j) {
        this.minDelayMs = j;
    }

    public final long getMaxDelayMs$dd_sdk_android_release() {
        return this.maxDelayMs;
    }

    public final void setMaxDelayMs$dd_sdk_android_release(long j) {
        this.maxDelayMs = j;
    }

    public void run() {
        Batch lockAndReadNext = (!isNetworkAvailable() || !isSystemReady()) ? null : this.reader.lockAndReadNext();
        if (lockAndReadNext != null) {
            consumeBatch(lockAndReadNext);
        } else {
            increaseInterval();
        }
        scheduleNextUpload();
    }

    private final boolean isNetworkAvailable() {
        return this.networkInfoProvider.getLatestNetworkInfo().getConnectivity() != NetworkInfo.Connectivity.NETWORK_NOT_CONNECTED;
    }

    private final boolean isSystemReady() {
        SystemInfo latestSystemInfo = this.systemInfoProvider.getLatestSystemInfo();
        if (!(latestSystemInfo.getBatteryFullOrCharging() || latestSystemInfo.getOnExternalPowerSource() || latestSystemInfo.getBatteryLevel() > 10) || latestSystemInfo.getPowerSaveMode()) {
            return false;
        }
        return true;
    }

    private final void scheduleNextUpload() {
        Runnable runnable = this;
        this.threadPoolExecutor.remove(runnable);
        ConcurrencyExtKt.scheduleSafe(this.threadPoolExecutor, "Data upload", this.currentDelayIntervalMs, TimeUnit.MILLISECONDS, runnable);
    }

    private final void consumeBatch(Batch batch) {
        if (this.dataUploader.upload(batch.getData()).getShouldRetry()) {
            this.reader.release(batch);
            increaseInterval();
            return;
        }
        this.reader.drop(batch);
        decreaseInterval();
    }

    private final void decreaseInterval() {
        this.currentDelayIntervalMs = Math.max(this.minDelayMs, (this.currentDelayIntervalMs * ((long) 90)) / ((long) 100));
    }

    private final void increaseInterval() {
        this.currentDelayIntervalMs = Math.min(this.maxDelayMs, (this.currentDelayIntervalMs * ((long) 110)) / ((long) 100));
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/core/internal/data/upload/DataUploadRunnable$Companion;", "", "()V", "DECREASE_PERCENT", "", "DEFAULT_DELAY_FACTOR", "INCREASE_PERCENT", "LOW_BATTERY_THRESHOLD", "MAX_DELAY_FACTOR", "MIN_DELAY_FACTOR", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DataUploadRunnable.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
