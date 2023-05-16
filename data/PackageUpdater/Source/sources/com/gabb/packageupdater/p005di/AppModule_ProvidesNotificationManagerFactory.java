package com.gabb.packageupdater.p005di;

import android.app.NotificationManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.AppModule_ProvidesNotificationManagerFactory */
public final class AppModule_ProvidesNotificationManagerFactory implements Factory<NotificationManager> {
    private final Provider<Context> contextProvider;

    public AppModule_ProvidesNotificationManagerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public NotificationManager get() {
        return providesNotificationManager(this.contextProvider.get());
    }

    public static AppModule_ProvidesNotificationManagerFactory create(Provider<Context> provider) {
        return new AppModule_ProvidesNotificationManagerFactory(provider);
    }

    public static NotificationManager providesNotificationManager(Context context) {
        return (NotificationManager) Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providesNotificationManager(context));
    }
}
