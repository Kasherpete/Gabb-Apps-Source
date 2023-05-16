package com.gabb.packageupdater.receiver;

import androidx.work.WorkManager;
import com.gabb.packageupdater.repository.AppRepository;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BootCompleteReceiver_MembersInjector implements MembersInjector<BootCompleteReceiver> {
    private final Provider<AppRepository> appRepositoryProvider;
    private final Provider<WorkManager> workManagerProvider;

    public BootCompleteReceiver_MembersInjector(Provider<WorkManager> provider, Provider<AppRepository> provider2) {
        this.workManagerProvider = provider;
        this.appRepositoryProvider = provider2;
    }

    public static MembersInjector<BootCompleteReceiver> create(Provider<WorkManager> provider, Provider<AppRepository> provider2) {
        return new BootCompleteReceiver_MembersInjector(provider, provider2);
    }

    public void injectMembers(BootCompleteReceiver bootCompleteReceiver) {
        injectWorkManager(bootCompleteReceiver, this.workManagerProvider.get());
        injectAppRepository(bootCompleteReceiver, this.appRepositoryProvider.get());
    }

    public static void injectWorkManager(BootCompleteReceiver bootCompleteReceiver, WorkManager workManager) {
        bootCompleteReceiver.workManager = workManager;
    }

    public static void injectAppRepository(BootCompleteReceiver bootCompleteReceiver, AppRepository appRepository) {
        bootCompleteReceiver.appRepository = appRepository;
    }
}
