package com.gabb.packageupdater.work;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

public final class PeriodicUpdateWorker_AssistedFactory_Impl implements PeriodicUpdateWorker_AssistedFactory {
    private final PeriodicUpdateWorker_Factory delegateFactory;

    PeriodicUpdateWorker_AssistedFactory_Impl(PeriodicUpdateWorker_Factory periodicUpdateWorker_Factory) {
        this.delegateFactory = periodicUpdateWorker_Factory;
    }

    public PeriodicUpdateWorker create(Context context, WorkerParameters workerParameters) {
        return this.delegateFactory.get(context, workerParameters);
    }

    public static Provider<PeriodicUpdateWorker_AssistedFactory> create(PeriodicUpdateWorker_Factory periodicUpdateWorker_Factory) {
        return InstanceFactory.create(new PeriodicUpdateWorker_AssistedFactory_Impl(periodicUpdateWorker_Factory));
    }
}
