package com.datadog.android.rum.internal.vitals;

import com.datadog.android.core.internal.utils.ConcurrencyExtKt;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.internal.domain.scope.RumViewScope;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/VitalReaderRunnable;", "Ljava/lang/Runnable;", "reader", "Lcom/datadog/android/rum/internal/vitals/VitalReader;", "observer", "Lcom/datadog/android/rum/internal/vitals/VitalObserver;", "executor", "Ljava/util/concurrent/ScheduledExecutorService;", "periodMs", "", "(Lcom/datadog/android/rum/internal/vitals/VitalReader;Lcom/datadog/android/rum/internal/vitals/VitalObserver;Ljava/util/concurrent/ScheduledExecutorService;J)V", "getExecutor", "()Ljava/util/concurrent/ScheduledExecutorService;", "getObserver", "()Lcom/datadog/android/rum/internal/vitals/VitalObserver;", "getPeriodMs", "()J", "getReader", "()Lcom/datadog/android/rum/internal/vitals/VitalReader;", "run", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: VitalReaderRunnable.kt */
public final class VitalReaderRunnable implements Runnable {
    private final ScheduledExecutorService executor;
    private final VitalObserver observer;
    private final long periodMs;
    private final VitalReader reader;

    public VitalReaderRunnable(VitalReader vitalReader, VitalObserver vitalObserver, ScheduledExecutorService scheduledExecutorService, long j) {
        Intrinsics.checkNotNullParameter(vitalReader, "reader");
        Intrinsics.checkNotNullParameter(vitalObserver, "observer");
        Intrinsics.checkNotNullParameter(scheduledExecutorService, "executor");
        this.reader = vitalReader;
        this.observer = vitalObserver;
        this.executor = scheduledExecutorService;
        this.periodMs = j;
    }

    public final VitalReader getReader() {
        return this.reader;
    }

    public final VitalObserver getObserver() {
        return this.observer;
    }

    public final ScheduledExecutorService getExecutor() {
        return this.executor;
    }

    public final long getPeriodMs() {
        return this.periodMs;
    }

    public void run() {
        Double readVitalData;
        if (GlobalRum.INSTANCE.getRumContext$dd_sdk_android_release().getViewType() == RumViewScope.RumViewType.FOREGROUND && (readVitalData = this.reader.readVitalData()) != null) {
            this.observer.onNewSample(readVitalData.doubleValue());
        }
        ConcurrencyExtKt.scheduleSafe(this.executor, "Vitals monitoring", this.periodMs, TimeUnit.MILLISECONDS, this);
    }
}
