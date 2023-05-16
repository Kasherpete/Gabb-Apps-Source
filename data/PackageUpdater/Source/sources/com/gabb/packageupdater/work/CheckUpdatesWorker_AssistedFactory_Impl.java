package com.gabb.packageupdater.work;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

public final class CheckUpdatesWorker_AssistedFactory_Impl implements CheckUpdatesWorker_AssistedFactory {
    private final CheckUpdatesWorker_Factory delegateFactory;

    CheckUpdatesWorker_AssistedFactory_Impl(CheckUpdatesWorker_Factory checkUpdatesWorker_Factory) {
        this.delegateFactory = checkUpdatesWorker_Factory;
    }

    public CheckUpdatesWorker create(Context context, WorkerParameters workerParameters) {
        return this.delegateFactory.get(context, workerParameters);
    }

    public static Provider<CheckUpdatesWorker_AssistedFactory> create(CheckUpdatesWorker_Factory checkUpdatesWorker_Factory) {
        return InstanceFactory.create(new CheckUpdatesWorker_AssistedFactory_Impl(checkUpdatesWorker_Factory));
    }
}
