package com.gabb.packageupdater.service;

import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.gabb.packageupdater.api.IUpdateCallback;
import com.gabb.packageupdater.api.IUpdateCallbackHandle;
import com.gabb.packageupdater.api.IUpdateService;
import com.gabb.packageupdater.work.PeriodicUpdateWorker;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(mo20734d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, mo20735d2 = {"com/gabb/packageupdater/service/UpdateService$onBind$1", "Lcom/gabb/packageupdater/api/IUpdateService$Stub;", "checkForUpdates", "Lcom/gabb/packageupdater/api/IUpdateCallbackHandle;", "callback", "Lcom/gabb/packageupdater/api/IUpdateCallback;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UpdateService.kt */
public final class UpdateService$onBind$1 extends IUpdateService.Stub {
    final /* synthetic */ UpdateService this$0;

    UpdateService$onBind$1(UpdateService updateService) {
        this.this$0 = updateService;
    }

    public IUpdateCallbackHandle checkForUpdates(IUpdateCallback iUpdateCallback) {
        Intrinsics.checkNotNullParameter(iUpdateCallback, "callback");
        this.this$0.getUpdatedApps().setApps(CollectionsKt.toMutableList(CollectionsKt.emptyList()));
        String stringPlus = Intrinsics.stringPlus("update_check_", UUID.randomUUID());
        WorkManager instance = WorkManager.getInstance(this.this$0.getApplicationContext());
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(applicationContext)");
        ExistingWorkPolicy existingWorkPolicy = ExistingWorkPolicy.REPLACE;
        OneTimeWorkRequest build = PeriodicUpdateWorker.Companion.build(stringPlus);
        UpdateService updateService = this.this$0;
        String simpleName = Reflection.getOrCreateKotlinClass(PeriodicUpdateWorker.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        return this.this$0.asCallbackHandle(updateService.enqueueAndDispatchStatusUpdates(build, instance, simpleName, existingWorkPolicy, iUpdateCallback));
    }
}
