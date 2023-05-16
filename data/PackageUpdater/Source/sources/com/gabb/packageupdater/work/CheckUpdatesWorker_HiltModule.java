package com.gabb.packageupdater.work;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface CheckUpdatesWorker_HiltModule {
    @Binds
    @StringKey("com.gabb.packageupdater.work.CheckUpdatesWorker")
    @IntoMap
    WorkerAssistedFactory<? extends ListenableWorker> bind(CheckUpdatesWorker_AssistedFactory checkUpdatesWorker_AssistedFactory);
}
