package com.gabb.packageupdater;

import androidx.hilt.work.HiltWorkerFactory;
import androidx.work.WorkManager;
import com.gabb.packageupdater.notifications.Notifier;
import com.gabb.packageupdater.repository.AppRepository;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PackageUpdaterApplication_MembersInjector implements MembersInjector<PackageUpdaterApplication> {
    private final Provider<AppRepository> appRepositoryProvider;
    private final Provider<Notifier> notifierProvider;
    private final Provider<WorkManager> workManagerProvider;
    private final Provider<HiltWorkerFactory> workerFactoryProvider;

    public PackageUpdaterApplication_MembersInjector(Provider<HiltWorkerFactory> provider, Provider<WorkManager> provider2, Provider<Notifier> provider3, Provider<AppRepository> provider4) {
        this.workerFactoryProvider = provider;
        this.workManagerProvider = provider2;
        this.notifierProvider = provider3;
        this.appRepositoryProvider = provider4;
    }

    public static MembersInjector<PackageUpdaterApplication> create(Provider<HiltWorkerFactory> provider, Provider<WorkManager> provider2, Provider<Notifier> provider3, Provider<AppRepository> provider4) {
        return new PackageUpdaterApplication_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(PackageUpdaterApplication packageUpdaterApplication) {
        injectWorkerFactory(packageUpdaterApplication, this.workerFactoryProvider.get());
        injectWorkManager(packageUpdaterApplication, this.workManagerProvider.get());
        injectNotifier(packageUpdaterApplication, this.notifierProvider.get());
        injectAppRepository(packageUpdaterApplication, this.appRepositoryProvider.get());
    }

    public static void injectWorkerFactory(PackageUpdaterApplication packageUpdaterApplication, HiltWorkerFactory hiltWorkerFactory) {
        packageUpdaterApplication.workerFactory = hiltWorkerFactory;
    }

    public static void injectWorkManager(PackageUpdaterApplication packageUpdaterApplication, WorkManager workManager) {
        packageUpdaterApplication.workManager = workManager;
    }

    public static void injectNotifier(PackageUpdaterApplication packageUpdaterApplication, Notifier notifier) {
        packageUpdaterApplication.notifier = notifier;
    }

    public static void injectAppRepository(PackageUpdaterApplication packageUpdaterApplication, AppRepository appRepository) {
        packageUpdaterApplication.appRepository = appRepository;
    }
}
