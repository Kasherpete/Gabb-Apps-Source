package com.gabb.packageupdater.data.inject;

import android.content.Context;
import com.gabb.data.GabbDatabase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class RoomDatabaseModule_ProvideDatabaseFactory implements Factory<GabbDatabase> {
    private final Provider<Context> contextProvider;

    public RoomDatabaseModule_ProvideDatabaseFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public GabbDatabase get() {
        return provideDatabase(this.contextProvider.get());
    }

    public static RoomDatabaseModule_ProvideDatabaseFactory create(Provider<Context> provider) {
        return new RoomDatabaseModule_ProvideDatabaseFactory(provider);
    }

    public static GabbDatabase provideDatabase(Context context) {
        return (GabbDatabase) Preconditions.checkNotNullFromProvides(RoomDatabaseModule.INSTANCE.provideDatabase(context));
    }
}
