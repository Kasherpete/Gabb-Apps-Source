package com.gabb.packageupdater.work;

import androidx.hilt.work.WorkerAssistedFactory;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface SingleShotInstallWorker_AssistedFactory extends WorkerAssistedFactory<SingleShotInstallWorker> {
}
