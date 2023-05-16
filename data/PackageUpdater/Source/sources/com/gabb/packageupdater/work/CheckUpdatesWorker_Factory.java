package com.gabb.packageupdater.work;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.gabb.packageupdater.notifications.Notifier;
import com.gabb.packageupdater.repository.AppRepository;
import javax.inject.Provider;

public final class CheckUpdatesWorker_Factory {
    private final Provider<AppRepository> appRepositoryProvider;
    private final Provider<Notifier> notifierProvider;

    public CheckUpdatesWorker_Factory(Provider<Notifier> provider, Provider<AppRepository> provider2) {
        this.notifierProvider = provider;
        this.appRepositoryProvider = provider2;
    }

    public CheckUpdatesWorker get(Context context, WorkerParameters workerParameters) {
        return newInstance(context, workerParameters, this.notifierProvider.get(), this.appRepositoryProvider.get());
    }

    public static CheckUpdatesWorker_Factory create(Provider<Notifier> provider, Provider<AppRepository> provider2) {
        return new CheckUpdatesWorker_Factory(provider, provider2);
    }

    public static CheckUpdatesWorker newInstance(Context context, WorkerParameters workerParameters, Notifier notifier, AppRepository appRepository) {
        return new CheckUpdatesWorker(context, workerParameters, notifier, appRepository);
    }
}
