package com.gabb.packageupdater.service;

import androidx.lifecycle.LiveData;

public final /* synthetic */ class UpdateService$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ UpdateService f$0;
    public final /* synthetic */ LiveData f$1;
    public final /* synthetic */ UpdateService$workInfoObserver$1 f$2;

    public /* synthetic */ UpdateService$$ExternalSyntheticLambda0(UpdateService updateService, LiveData liveData, UpdateService$workInfoObserver$1 updateService$workInfoObserver$1) {
        this.f$0 = updateService;
        this.f$1 = liveData;
        this.f$2 = updateService$workInfoObserver$1;
    }

    public final void run() {
        UpdateService.m170enqueueAndDispatchStatusUpdates$lambda0(this.f$0, this.f$1, this.f$2);
    }
}
