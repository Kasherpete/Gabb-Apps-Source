package com.gabb.packageupdater.service;

import com.gabb.packageupdater.model.UpdatedApps;
import com.gabb.packageupdater.repository.AppRepository;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UpdateService_MembersInjector implements MembersInjector<UpdateService> {
    private final Provider<AppRepository> appRepositoryProvider;
    private final Provider<UpdatedApps> updatedAppsProvider;

    public UpdateService_MembersInjector(Provider<UpdatedApps> provider, Provider<AppRepository> provider2) {
        this.updatedAppsProvider = provider;
        this.appRepositoryProvider = provider2;
    }

    public static MembersInjector<UpdateService> create(Provider<UpdatedApps> provider, Provider<AppRepository> provider2) {
        return new UpdateService_MembersInjector(provider, provider2);
    }

    public void injectMembers(UpdateService updateService) {
        injectUpdatedApps(updateService, this.updatedAppsProvider.get());
        injectAppRepository(updateService, this.appRepositoryProvider.get());
    }

    public static void injectUpdatedApps(UpdateService updateService, UpdatedApps updatedApps) {
        updateService.updatedApps = updatedApps;
    }

    public static void injectAppRepository(UpdateService updateService, AppRepository appRepository) {
        updateService.appRepository = appRepository;
    }
}
