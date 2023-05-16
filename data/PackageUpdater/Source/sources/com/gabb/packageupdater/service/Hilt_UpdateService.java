package com.gabb.packageupdater.service;

import androidx.lifecycle.LifecycleService;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.internal.GeneratedComponentManagerHolder;
import dagger.hilt.internal.UnsafeCasts;

public abstract class Hilt_UpdateService extends LifecycleService implements GeneratedComponentManagerHolder {
    private volatile ServiceComponentManager componentManager;
    private final Object componentManagerLock = new Object();
    private boolean injected = false;

    Hilt_UpdateService() {
    }

    public void onCreate() {
        inject();
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    public ServiceComponentManager createComponentManager() {
        return new ServiceComponentManager(this);
    }

    public final ServiceComponentManager componentManager() {
        if (this.componentManager == null) {
            synchronized (this.componentManagerLock) {
                if (this.componentManager == null) {
                    this.componentManager = createComponentManager();
                }
            }
        }
        return this.componentManager;
    }

    /* access modifiers changed from: protected */
    public void inject() {
        if (!this.injected) {
            this.injected = true;
            ((UpdateService_GeneratedInjector) generatedComponent()).injectUpdateService((UpdateService) UnsafeCasts.unsafeCast(this));
        }
    }

    public final Object generatedComponent() {
        return componentManager().generatedComponent();
    }
}
