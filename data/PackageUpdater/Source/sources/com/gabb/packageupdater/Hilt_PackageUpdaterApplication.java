package com.gabb.packageupdater;

import android.app.Application;
import dagger.hilt.android.internal.managers.ApplicationComponentManager;
import dagger.hilt.android.internal.managers.ComponentSupplier;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.internal.GeneratedComponentManagerHolder;
import dagger.hilt.internal.UnsafeCasts;

public abstract class Hilt_PackageUpdaterApplication extends Application implements GeneratedComponentManagerHolder {
    private final ApplicationComponentManager componentManager = new ApplicationComponentManager(new ComponentSupplier() {
        public Object get() {
            return DaggerPackageUpdaterApplication_HiltComponents_SingletonC.builder().applicationContextModule(new ApplicationContextModule(Hilt_PackageUpdaterApplication.this)).build();
        }
    });

    public final ApplicationComponentManager componentManager() {
        return this.componentManager;
    }

    public final Object generatedComponent() {
        return componentManager().generatedComponent();
    }

    public void onCreate() {
        ((PackageUpdaterApplication_GeneratedInjector) generatedComponent()).injectPackageUpdaterApplication((PackageUpdaterApplication) UnsafeCasts.unsafeCast(this));
        super.onCreate();
    }
}
