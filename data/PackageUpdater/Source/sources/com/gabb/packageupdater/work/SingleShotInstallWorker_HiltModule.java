package com.gabb.packageupdater.work;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface SingleShotInstallWorker_HiltModule {
    @Binds
    @StringKey("com.gabb.packageupdater.work.SingleShotInstallWorker")
    @IntoMap
    WorkerAssistedFactory<? extends ListenableWorker> bind(SingleShotInstallWorker_AssistedFactory singleShotInstallWorker_AssistedFactory);
}
