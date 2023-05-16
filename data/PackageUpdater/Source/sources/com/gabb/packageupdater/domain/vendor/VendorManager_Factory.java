package com.gabb.packageupdater.domain.vendor;

import dagger.internal.Factory;

public final class VendorManager_Factory implements Factory<VendorManager> {
    public VendorManager get() {
        return newInstance();
    }

    public static VendorManager_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static VendorManager newInstance() {
        return new VendorManager();
    }

    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final VendorManager_Factory INSTANCE = new VendorManager_Factory();

        private InstanceHolder() {
        }
    }
}
