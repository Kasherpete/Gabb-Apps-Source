package com.gabb.packageupdater.p005di;

import com.gabb.packageupdater.model.UpdatedApps;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* renamed from: com.gabb.packageupdater.di.DomainModule_ProvideUpdatedAppsFactory */
public final class DomainModule_ProvideUpdatedAppsFactory implements Factory<UpdatedApps> {
    public UpdatedApps get() {
        return provideUpdatedApps();
    }

    public static DomainModule_ProvideUpdatedAppsFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static UpdatedApps provideUpdatedApps() {
        return (UpdatedApps) Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideUpdatedApps());
    }

    /* renamed from: com.gabb.packageupdater.di.DomainModule_ProvideUpdatedAppsFactory$InstanceHolder */
    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final DomainModule_ProvideUpdatedAppsFactory INSTANCE = new DomainModule_ProvideUpdatedAppsFactory();

        private InstanceHolder() {
        }
    }
}
