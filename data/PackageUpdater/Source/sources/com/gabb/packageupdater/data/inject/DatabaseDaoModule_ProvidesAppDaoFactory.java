package com.gabb.packageupdater.data.inject;

import com.gabb.data.GabbDatabase;
import com.gabb.data.dao.AppDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DatabaseDaoModule_ProvidesAppDaoFactory implements Factory<AppDao> {
    private final Provider<GabbDatabase> dbProvider;

    public DatabaseDaoModule_ProvidesAppDaoFactory(Provider<GabbDatabase> provider) {
        this.dbProvider = provider;
    }

    public AppDao get() {
        return providesAppDao(this.dbProvider.get());
    }

    public static DatabaseDaoModule_ProvidesAppDaoFactory create(Provider<GabbDatabase> provider) {
        return new DatabaseDaoModule_ProvidesAppDaoFactory(provider);
    }

    public static AppDao providesAppDao(GabbDatabase gabbDatabase) {
        return (AppDao) Preconditions.checkNotNullFromProvides(DatabaseDaoModule.INSTANCE.providesAppDao(gabbDatabase));
    }
}
