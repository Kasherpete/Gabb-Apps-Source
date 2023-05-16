package com.gabb.packageupdater.service;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.WorkInfo;
import com.gabb.packageupdater.api.IUpdateCallback;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, mo20735d2 = {"com/gabb/packageupdater/service/UpdateService$workInfoObserver$1", "Landroidx/lifecycle/Observer;", "Landroidx/work/WorkInfo;", "onChanged", "", "workInfo", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UpdateService.kt */
public final class UpdateService$workInfoObserver$1 implements Observer<WorkInfo> {
    final /* synthetic */ IUpdateCallback $callback;
    final /* synthetic */ LiveData<WorkInfo> $liveData;
    final /* synthetic */ UpdateService this$0;

    UpdateService$workInfoObserver$1(LiveData<WorkInfo> liveData, UpdateService updateService, IUpdateCallback iUpdateCallback) {
        this.$liveData = liveData;
        this.this$0 = updateService;
        this.$callback = iUpdateCallback;
    }

    public void onChanged(WorkInfo workInfo) {
        Pair pair;
        if (workInfo == null) {
            Log.w("update", "work info null");
            return;
        }
        if (workInfo.getState().isFinished()) {
            Log.i("update", "work finished: clean up observer");
            this.$liveData.removeObserver(this);
            UpdateService updateService = this.this$0;
            WorkInfo.State state = workInfo.getState();
            Intrinsics.checkNotNullExpressionValue(state, "workInfo.state");
            pair = TuplesKt.m78to(Integer.valueOf(updateService.toInt(state)), true);
        } else {
            UpdateService updateService2 = this.this$0;
            WorkInfo.State state2 = workInfo.getState();
            Intrinsics.checkNotNullExpressionValue(state2, "workInfo.state");
            pair = TuplesKt.m78to(Integer.valueOf(updateService2.toInt(state2)), false);
        }
        this.$callback.onChange(((Number) pair.component1()).intValue(), ((Boolean) pair.component2()).booleanValue(), this.this$0.getUpdatedApps().getApps());
    }
}
