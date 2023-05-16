package androidx.hilt.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

public interface WorkerAssistedFactory<T extends ListenableWorker> {
    T create(Context context, WorkerParameters workerParameters);
}
