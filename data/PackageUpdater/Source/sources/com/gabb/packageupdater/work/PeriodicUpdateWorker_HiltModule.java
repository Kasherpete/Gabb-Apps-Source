package com.gabb.packageupdater.work;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface PeriodicUpdateWorker_HiltModule {
    @Binds
    @StringKey("com.gabb.packageupdater.work.PeriodicUpdateWorker")
    @IntoMap
    WorkerAssistedFactory<? extends ListenableWorker> bind(PeriodicUpdateWorker_AssistedFactory periodicUpdateWorker_AssistedFactory);
}
