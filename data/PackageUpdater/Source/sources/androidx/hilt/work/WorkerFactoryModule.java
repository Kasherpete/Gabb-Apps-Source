package androidx.hilt.work;

import androidx.work.ListenableWorker;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;
import java.util.Map;
import javax.inject.Provider;

@Module
abstract class WorkerFactoryModule {
    /* access modifiers changed from: package-private */
    @Multibinds
    public abstract Map<String, WorkerAssistedFactory<? extends ListenableWorker>> workerFactoriesMap();

    WorkerFactoryModule() {
    }

    @Provides
    static HiltWorkerFactory provideFactory(Map<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>> map) {
        return new HiltWorkerFactory(map);
    }
}
