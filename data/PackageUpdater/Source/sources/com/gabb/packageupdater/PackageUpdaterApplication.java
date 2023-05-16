package com.gabb.packageupdater;

import android.content.Context;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.work.Configuration;
import androidx.work.WorkManager;
import com.datadog.android.Datadog;
import com.datadog.android.DatadogSite;
import com.datadog.android.core.configuration.Configuration;
import com.datadog.android.core.configuration.Credentials;
import com.datadog.android.privacy.TrackingConsent;
import com.gabb.packageupdater.notifications.Notifier;
import com.gabb.packageupdater.repository.AppRepository;
import dagger.hilt.android.HiltAndroidApp;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001fH\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006!"}, mo20735d2 = {"Lcom/gabb/packageupdater/PackageUpdaterApplication;", "Landroid/app/Application;", "Landroidx/work/Configuration$Provider;", "()V", "appRepository", "Lcom/gabb/packageupdater/repository/AppRepository;", "getAppRepository", "()Lcom/gabb/packageupdater/repository/AppRepository;", "setAppRepository", "(Lcom/gabb/packageupdater/repository/AppRepository;)V", "notifier", "Lcom/gabb/packageupdater/notifications/Notifier;", "getNotifier", "()Lcom/gabb/packageupdater/notifications/Notifier;", "setNotifier", "(Lcom/gabb/packageupdater/notifications/Notifier;)V", "workManager", "Landroidx/work/WorkManager;", "getWorkManager", "()Landroidx/work/WorkManager;", "setWorkManager", "(Landroidx/work/WorkManager;)V", "workerFactory", "Landroidx/hilt/work/HiltWorkerFactory;", "getWorkerFactory", "()Landroidx/hilt/work/HiltWorkerFactory;", "setWorkerFactory", "(Landroidx/hilt/work/HiltWorkerFactory;)V", "getWorkManagerConfiguration", "Landroidx/work/Configuration;", "initializeDatadog", "", "onCreate", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@HiltAndroidApp
/* compiled from: PackageUpdaterApplication.kt */
public final class PackageUpdaterApplication extends Hilt_PackageUpdaterApplication implements Configuration.Provider {
    @Inject
    public AppRepository appRepository;
    @Inject
    public Notifier notifier;
    @Inject
    public WorkManager workManager;
    @Inject
    public HiltWorkerFactory workerFactory;

    public final HiltWorkerFactory getWorkerFactory() {
        HiltWorkerFactory hiltWorkerFactory = this.workerFactory;
        if (hiltWorkerFactory != null) {
            return hiltWorkerFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("workerFactory");
        return null;
    }

    public final void setWorkerFactory(HiltWorkerFactory hiltWorkerFactory) {
        Intrinsics.checkNotNullParameter(hiltWorkerFactory, "<set-?>");
        this.workerFactory = hiltWorkerFactory;
    }

    public final WorkManager getWorkManager() {
        WorkManager workManager2 = this.workManager;
        if (workManager2 != null) {
            return workManager2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("workManager");
        return null;
    }

    public final void setWorkManager(WorkManager workManager2) {
        Intrinsics.checkNotNullParameter(workManager2, "<set-?>");
        this.workManager = workManager2;
    }

    public final Notifier getNotifier() {
        Notifier notifier2 = this.notifier;
        if (notifier2 != null) {
            return notifier2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("notifier");
        return null;
    }

    public final void setNotifier(Notifier notifier2) {
        Intrinsics.checkNotNullParameter(notifier2, "<set-?>");
        this.notifier = notifier2;
    }

    public final AppRepository getAppRepository() {
        AppRepository appRepository2 = this.appRepository;
        if (appRepository2 != null) {
            return appRepository2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appRepository");
        return null;
    }

    public final void setAppRepository(AppRepository appRepository2) {
        Intrinsics.checkNotNullParameter(appRepository2, "<set-?>");
        this.appRepository = appRepository2;
    }

    public Configuration getWorkManagerConfiguration() {
        Configuration build = new Configuration.Builder().setWorkerFactory(getWorkerFactory()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…ory)\n            .build()");
        return build;
    }

    public void onCreate() {
        super.onCreate();
        initializeDatadog();
    }

    private final void initializeDatadog() {
        Context context = this;
        Datadog.initialize(context, new Credentials(BuildConfig.DATADOG_CLIENT_TOKEN, "release", "", BuildConfig.DATADOG_APPLICATION_ID, (String) null, 16, (DefaultConstructorMarker) null), new Configuration.Builder(true, false, true, false).useSite(DatadogSite.US1).build(), TrackingConsent.GRANTED);
    }
}
