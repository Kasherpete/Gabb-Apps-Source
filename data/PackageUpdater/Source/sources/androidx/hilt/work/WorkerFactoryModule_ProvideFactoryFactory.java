package androidx.hilt.work;

import androidx.work.ListenableWorker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
import javax.inject.Provider;

public final class WorkerFactoryModule_ProvideFactoryFactory implements Factory<HiltWorkerFactory> {
    private final Provider<Map<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>>> workerFactoriesProvider;

    public WorkerFactoryModule_ProvideFactoryFactory(Provider<Map<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>>> provider) {
        this.workerFactoriesProvider = provider;
    }

    public HiltWorkerFactory get() {
        return provideFactory(this.workerFactoriesProvider.get());
    }

    public static WorkerFactoryModule_ProvideFactoryFactory create(Provider<Map<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>>> provider) {
        return new WorkerFactoryModule_ProvideFactoryFactory(provider);
    }

    public static HiltWorkerFactory provideFactory(Map<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>> map) {
        return (HiltWorkerFactory) Preconditions.checkNotNullFromProvides(WorkerFactoryModule.provideFactory(map));
    }
}
