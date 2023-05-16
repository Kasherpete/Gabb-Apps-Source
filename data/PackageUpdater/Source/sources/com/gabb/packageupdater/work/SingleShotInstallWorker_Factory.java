package com.gabb.packageupdater.work;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.work.WorkerParameters;
import com.gabb.packageupdater.domain.packagemanagement.PackageHandler;
import com.gabb.packageupdater.model.UpdatedApps;
import com.gabb.packageupdater.notifications.Notifier;
import com.gabb.packageupdater.repository.AppRepository;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import javax.inject.Provider;

public final class SingleShotInstallWorker_Factory {
    private final Provider<ConnectivityManager> connectivityProvider;
    private final Provider<MixpanelAPI> mixpanelAPIProvider;
    private final Provider<Notifier> notifierProvider;
    private final Provider<PackageHandler> pacmanProvider;
    private final Provider<AppRepository> repositoryProvider;
    private final Provider<UpdatedApps> updatedAppsProvider;

    public SingleShotInstallWorker_Factory(Provider<ConnectivityManager> provider, Provider<AppRepository> provider2, Provider<PackageHandler> provider3, Provider<Notifier> provider4, Provider<UpdatedApps> provider5, Provider<MixpanelAPI> provider6) {
        this.connectivityProvider = provider;
        this.repositoryProvider = provider2;
        this.pacmanProvider = provider3;
        this.notifierProvider = provider4;
        this.updatedAppsProvider = provider5;
        this.mixpanelAPIProvider = provider6;
    }

    public SingleShotInstallWorker get(Context context, WorkerParameters workerParameters) {
        return newInstance(context, workerParameters, this.connectivityProvider.get(), this.repositoryProvider.get(), this.pacmanProvider.get(), this.notifierProvider.get(), this.updatedAppsProvider.get(), this.mixpanelAPIProvider.get());
    }

    public static SingleShotInstallWorker_Factory create(Provider<ConnectivityManager> provider, Provider<AppRepository> provider2, Provider<PackageHandler> provider3, Provider<Notifier> provider4, Provider<UpdatedApps> provider5, Provider<MixpanelAPI> provider6) {
        return new SingleShotInstallWorker_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static SingleShotInstallWorker newInstance(Context context, WorkerParameters workerParameters, ConnectivityManager connectivityManager, AppRepository appRepository, PackageHandler packageHandler, Notifier notifier, UpdatedApps updatedApps, MixpanelAPI mixpanelAPI) {
        return new SingleShotInstallWorker(context, workerParameters, connectivityManager, appRepository, packageHandler, notifier, updatedApps, mixpanelAPI);
    }
}
