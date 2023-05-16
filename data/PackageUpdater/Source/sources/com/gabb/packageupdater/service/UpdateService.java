package com.gabb.packageupdater.service;

import android.content.Intent;
import android.os.IBinder;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.gabb.packageupdater.api.IUpdateCallback;
import com.gabb.packageupdater.api.IUpdateCallbackHandle;
import com.gabb.packageupdater.model.UpdatedApps;
import com.gabb.packageupdater.repository.AppRepository;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u0000o\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0014\b\u0007\u0018\u00002\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J#\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002¢\u0006\u0002\u0010\u001aJ\f\u0010\u001b\u001a\u00020\u001c*\u00020\u001dH\u0002J,\u0010\u001e\u001a\u00020\u001d*\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\f\u0010&\u001a\u00020'*\u00020(H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006*"}, mo20735d2 = {"Lcom/gabb/packageupdater/service/UpdateService;", "Landroidx/lifecycle/LifecycleService;", "()V", "appRepository", "Lcom/gabb/packageupdater/repository/AppRepository;", "getAppRepository", "()Lcom/gabb/packageupdater/repository/AppRepository;", "setAppRepository", "(Lcom/gabb/packageupdater/repository/AppRepository;)V", "updatedApps", "Lcom/gabb/packageupdater/model/UpdatedApps;", "getUpdatedApps", "()Lcom/gabb/packageupdater/model/UpdatedApps;", "setUpdatedApps", "(Lcom/gabb/packageupdater/model/UpdatedApps;)V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "workInfoObserver", "com/gabb/packageupdater/service/UpdateService$workInfoObserver$1", "liveData", "Landroidx/lifecycle/LiveData;", "Landroidx/work/WorkInfo;", "callback", "Lcom/gabb/packageupdater/api/IUpdateCallback;", "(Landroidx/lifecycle/LiveData;Lcom/gabb/packageupdater/api/IUpdateCallback;)Lcom/gabb/packageupdater/service/UpdateService$workInfoObserver$1;", "asCallbackHandle", "Lcom/gabb/packageupdater/api/IUpdateCallbackHandle;", "Ljava/lang/Runnable;", "enqueueAndDispatchStatusUpdates", "Landroidx/work/OneTimeWorkRequest;", "workManager", "Landroidx/work/WorkManager;", "uniqueWorkName", "", "policy", "Landroidx/work/ExistingWorkPolicy;", "toInt", "", "Landroidx/work/WorkInfo$State;", "WorkDetails", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@AndroidEntryPoint
/* compiled from: UpdateService.kt */
public final class UpdateService extends Hilt_UpdateService {
    @Inject
    public AppRepository appRepository;
    @Inject
    public UpdatedApps updatedApps;

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: UpdateService.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WorkInfo.State.values().length];
            iArr[WorkInfo.State.ENQUEUED.ordinal()] = 1;
            iArr[WorkInfo.State.RUNNING.ordinal()] = 2;
            iArr[WorkInfo.State.SUCCEEDED.ordinal()] = 3;
            iArr[WorkInfo.State.FAILED.ordinal()] = 4;
            iArr[WorkInfo.State.BLOCKED.ordinal()] = 5;
            iArr[WorkInfo.State.CANCELLED.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final UpdatedApps getUpdatedApps() {
        UpdatedApps updatedApps2 = this.updatedApps;
        if (updatedApps2 != null) {
            return updatedApps2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("updatedApps");
        return null;
    }

    public final void setUpdatedApps(UpdatedApps updatedApps2) {
        Intrinsics.checkNotNullParameter(updatedApps2, "<set-?>");
        this.updatedApps = updatedApps2;
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

    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onBind(intent);
        return new UpdateService$onBind$1(this);
    }

    /* access modifiers changed from: private */
    public final Runnable enqueueAndDispatchStatusUpdates(OneTimeWorkRequest oneTimeWorkRequest, WorkManager workManager, String str, ExistingWorkPolicy existingWorkPolicy, IUpdateCallback iUpdateCallback) {
        LiveData<WorkInfo> workInfoByIdLiveData = workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.getId());
        Intrinsics.checkNotNullExpressionValue(workInfoByIdLiveData, "workManager.getWorkInfoByIdLiveData(id)");
        UpdateService$workInfoObserver$1 workInfoObserver = workInfoObserver(workInfoByIdLiveData, iUpdateCallback);
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new UpdateService$enqueueAndDispatchStatusUpdates$1(workInfoByIdLiveData, this, workInfoObserver, (Continuation<? super UpdateService$enqueueAndDispatchStatusUpdates$1>) null), 3, (Object) null);
        workManager.enqueueUniqueWork(str, existingWorkPolicy, oneTimeWorkRequest);
        return new UpdateService$$ExternalSyntheticLambda0(this, workInfoByIdLiveData, workInfoObserver);
    }

    /* access modifiers changed from: private */
    /* renamed from: enqueueAndDispatchStatusUpdates$lambda-0  reason: not valid java name */
    public static final void m170enqueueAndDispatchStatusUpdates$lambda0(UpdateService updateService, LiveData liveData, UpdateService$workInfoObserver$1 updateService$workInfoObserver$1) {
        Intrinsics.checkNotNullParameter(updateService, "this$0");
        Intrinsics.checkNotNullParameter(liveData, "$liveData");
        Intrinsics.checkNotNullParameter(updateService$workInfoObserver$1, "$observer");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(updateService), (CoroutineContext) null, (CoroutineStart) null, new UpdateService$enqueueAndDispatchStatusUpdates$2$1(liveData, updateService$workInfoObserver$1, (Continuation<? super UpdateService$enqueueAndDispatchStatusUpdates$2$1>) null), 3, (Object) null);
    }

    private final UpdateService$workInfoObserver$1 workInfoObserver(LiveData<WorkInfo> liveData, IUpdateCallback iUpdateCallback) {
        return new UpdateService$workInfoObserver$1(liveData, this, iUpdateCallback);
    }

    /* access modifiers changed from: private */
    public final IUpdateCallbackHandle asCallbackHandle(Runnable runnable) {
        return new UpdateService$asCallbackHandle$1(runnable);
    }

    /* access modifiers changed from: private */
    public final int toInt(WorkInfo.State state) {
        switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 5;
            case 5:
                return 3;
            case 6:
                return 4;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003HÆ\u0003J\u001b\u0010\t\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, mo20735d2 = {"Lcom/gabb/packageupdater/service/UpdateService$WorkDetails;", "", "worker", "Lkotlin/reflect/KClass;", "Landroidx/work/ListenableWorker;", "(Lkotlin/reflect/KClass;)V", "getWorker", "()Lkotlin/reflect/KClass;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: UpdateService.kt */
    public static final class WorkDetails {
        private final KClass<? extends ListenableWorker> worker;

        public static /* synthetic */ WorkDetails copy$default(WorkDetails workDetails, KClass<? extends ListenableWorker> kClass, int i, Object obj) {
            if ((i & 1) != 0) {
                kClass = workDetails.worker;
            }
            return workDetails.copy(kClass);
        }

        public final KClass<? extends ListenableWorker> component1() {
            return this.worker;
        }

        public final WorkDetails copy(KClass<? extends ListenableWorker> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "worker");
            return new WorkDetails(kClass);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof WorkDetails) && Intrinsics.areEqual((Object) this.worker, (Object) ((WorkDetails) obj).worker);
        }

        public int hashCode() {
            return this.worker.hashCode();
        }

        public String toString() {
            return "WorkDetails(worker=" + this.worker + ')';
        }

        public WorkDetails(KClass<? extends ListenableWorker> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "worker");
            this.worker = kClass;
        }

        public final KClass<? extends ListenableWorker> getWorker() {
            return this.worker;
        }
    }
}
