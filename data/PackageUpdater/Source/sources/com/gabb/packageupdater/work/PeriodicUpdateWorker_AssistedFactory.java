package com.gabb.packageupdater.work;

import androidx.hilt.work.WorkerAssistedFactory;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface PeriodicUpdateWorker_AssistedFactory extends WorkerAssistedFactory<PeriodicUpdateWorker> {
}
