package com.gabb.packageupdater.receiver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.WorkManager;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.packageupdater.repository.AppRepository;
import com.gabb.packageupdater.work.CheckUpdatesWorker;
import com.gabb.packageupdater.work.PeriodicUpdateWorker;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, mo20735d2 = {"Lcom/gabb/packageupdater/receiver/BootCompleteReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "appRepository", "Lcom/gabb/packageupdater/repository/AppRepository;", "getAppRepository", "()Lcom/gabb/packageupdater/repository/AppRepository;", "setAppRepository", "(Lcom/gabb/packageupdater/repository/AppRepository;)V", "workManager", "Landroidx/work/WorkManager;", "getWorkManager", "()Landroidx/work/WorkManager;", "setWorkManager", "(Landroidx/work/WorkManager;)V", "isFromUpgrade", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "onReceive", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@AndroidEntryPoint
/* compiled from: BootCompleteReceiver.kt */
public final class BootCompleteReceiver extends Hilt_BootCompleteReceiver {
    @Inject
    public AppRepository appRepository;
    @Inject
    public WorkManager workManager;

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

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        if (Intrinsics.areEqual((Object) intent == null ? null : intent.getAction(), (Object) "android.intent.action.BOOT_COMPLETED") || isFromUpgrade(context, intent)) {
            getWorkManager().enqueueUniquePeriodicWork(PeriodicUpdateWorker.UNIQUE_NAME, ExistingPeriodicWorkPolicy.REPLACE, PeriodicUpdateWorker.Companion.buildPeriodic());
            getWorkManager().enqueueUniquePeriodicWork(CheckUpdatesWorker.UNIQUE_NAME, ExistingPeriodicWorkPolicy.REPLACE, CheckUpdatesWorker.Companion.buildPeriodic());
            Log.i("updater", "Started monitoring updates");
        }
    }

    private final boolean isFromUpgrade(Context context, Intent intent) {
        String str = null;
        if (Intrinsics.areEqual((Object) intent == null ? null : intent.getAction(), (Object) "android.intent.action.MY_PACKAGE_REPLACED")) {
            String packageName = context.getPackageName();
            Uri data = intent.getData();
            if (data != null) {
                str = data.getSchemeSpecificPart();
            }
            if (packageName.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
