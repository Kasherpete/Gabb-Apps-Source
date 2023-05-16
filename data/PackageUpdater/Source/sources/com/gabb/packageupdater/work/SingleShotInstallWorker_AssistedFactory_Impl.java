package com.gabb.packageupdater.work;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

public final class SingleShotInstallWorker_AssistedFactory_Impl implements SingleShotInstallWorker_AssistedFactory {
    private final SingleShotInstallWorker_Factory delegateFactory;

    SingleShotInstallWorker_AssistedFactory_Impl(SingleShotInstallWorker_Factory singleShotInstallWorker_Factory) {
        this.delegateFactory = singleShotInstallWorker_Factory;
    }

    public SingleShotInstallWorker create(Context context, WorkerParameters workerParameters) {
        return this.delegateFactory.get(context, workerParameters);
    }

    public static Provider<SingleShotInstallWorker_AssistedFactory> create(SingleShotInstallWorker_Factory singleShotInstallWorker_Factory) {
        return InstanceFactory.create(new SingleShotInstallWorker_AssistedFactory_Impl(singleShotInstallWorker_Factory));
    }
}
