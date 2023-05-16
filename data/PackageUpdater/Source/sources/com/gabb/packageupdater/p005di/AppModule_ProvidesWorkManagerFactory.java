package com.gabb.packageupdater.p005di;

import android.content.Context;
import androidx.work.WorkManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.AppModule_ProvidesWorkManagerFactory */
public final class AppModule_ProvidesWorkManagerFactory implements Factory<WorkManager> {
    private final Provider<Context> contextProvider;

    public AppModule_ProvidesWorkManagerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public WorkManager get() {
        return providesWorkManager(this.contextProvider.get());
    }

    public static AppModule_ProvidesWorkManagerFactory create(Provider<Context> provider) {
        return new AppModule_ProvidesWorkManagerFactory(provider);
    }

    public static WorkManager providesWorkManager(Context context) {
        return (WorkManager) Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providesWorkManager(context));
    }
}
