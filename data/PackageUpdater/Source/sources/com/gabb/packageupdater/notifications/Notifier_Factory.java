package com.gabb.packageupdater.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class Notifier_Factory implements Factory<Notifier> {
    private final Provider<Context> contextProvider;
    private final Provider<NotificationManager> notificationManagerProvider;
    private final Provider<PackageManager> packageManagerProvider;

    public Notifier_Factory(Provider<Context> provider, Provider<NotificationManager> provider2, Provider<PackageManager> provider3) {
        this.contextProvider = provider;
        this.notificationManagerProvider = provider2;
        this.packageManagerProvider = provider3;
    }

    public Notifier get() {
        return newInstance(this.contextProvider.get(), this.notificationManagerProvider.get(), this.packageManagerProvider.get());
    }

    public static Notifier_Factory create(Provider<Context> provider, Provider<NotificationManager> provider2, Provider<PackageManager> provider3) {
        return new Notifier_Factory(provider, provider2, provider3);
    }

    public static Notifier newInstance(Context context, NotificationManager notificationManager, PackageManager packageManager) {
        return new Notifier(context, notificationManager, packageManager);
    }
}
