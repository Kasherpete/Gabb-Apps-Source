package androidx.hilt.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;
import java.util.Map;
import javax.inject.Provider;

public final class HiltWorkerFactory extends WorkerFactory {
    private final Map<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mWorkerFactories;

    HiltWorkerFactory(Map<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>> map) {
        this.mWorkerFactories = map;
    }

    public ListenableWorker createWorker(Context context, String str, WorkerParameters workerParameters) {
        Provider provider = this.mWorkerFactories.get(str);
        if (provider == null) {
            return null;
        }
        return ((WorkerAssistedFactory) provider.get()).create(context, workerParameters);
    }
}
