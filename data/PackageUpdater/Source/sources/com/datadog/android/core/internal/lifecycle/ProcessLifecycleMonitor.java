package com.datadog.android.core.internal.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0018H\u0016J\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006!"}, mo20735d2 = {"Lcom/datadog/android/core/internal/lifecycle/ProcessLifecycleMonitor;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "callback", "Lcom/datadog/android/core/internal/lifecycle/ProcessLifecycleMonitor$Callback;", "(Lcom/datadog/android/core/internal/lifecycle/ProcessLifecycleMonitor$Callback;)V", "activitiesResumedCounter", "Ljava/util/concurrent/atomic/AtomicInteger;", "getActivitiesResumedCounter", "()Ljava/util/concurrent/atomic/AtomicInteger;", "activitiesStartedCounter", "getActivitiesStartedCounter", "getCallback", "()Lcom/datadog/android/core/internal/lifecycle/ProcessLifecycleMonitor$Callback;", "wasPaused", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getWasPaused", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "wasStopped", "getWasStopped", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "Callback", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ProcessLifecycleMonitor.kt */
public final class ProcessLifecycleMonitor implements Application.ActivityLifecycleCallbacks {
    private final AtomicInteger activitiesResumedCounter = new AtomicInteger(0);
    private final AtomicInteger activitiesStartedCounter = new AtomicInteger(0);
    private final Callback callback;
    private final AtomicBoolean wasPaused = new AtomicBoolean(true);
    private final AtomicBoolean wasStopped = new AtomicBoolean(true);

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/internal/lifecycle/ProcessLifecycleMonitor$Callback;", "", "onPaused", "", "onResumed", "onStarted", "onStopped", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ProcessLifecycleMonitor.kt */
    public interface Callback {
        void onPaused();

        void onResumed();

        void onStarted();

        void onStopped();
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(bundle, "outState");
    }

    public ProcessLifecycleMonitor(Callback callback2) {
        Intrinsics.checkNotNullParameter(callback2, "callback");
        this.callback = callback2;
    }

    public final Callback getCallback() {
        return this.callback;
    }

    public final AtomicInteger getActivitiesResumedCounter() {
        return this.activitiesResumedCounter;
    }

    public final AtomicInteger getActivitiesStartedCounter() {
        return this.activitiesStartedCounter;
    }

    public final AtomicBoolean getWasPaused() {
        return this.wasPaused;
    }

    public final AtomicBoolean getWasStopped() {
        return this.wasStopped;
    }

    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.activitiesResumedCounter.decrementAndGet() == 0 && !this.wasPaused.getAndSet(true)) {
            this.callback.onPaused();
        }
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.activitiesStartedCounter.incrementAndGet() == 1 && this.wasStopped.getAndSet(false)) {
            this.callback.onStarted();
        }
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.activitiesStartedCounter.decrementAndGet() == 0 && this.wasPaused.get()) {
            this.callback.onStopped();
            this.wasStopped.set(true);
        }
    }

    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.activitiesResumedCounter.incrementAndGet() == 1 && this.wasPaused.getAndSet(false)) {
            this.callback.onResumed();
        }
    }
}
