package com.gabb.packageupdater.service;

import com.gabb.packageupdater.api.IUpdateCallbackHandle;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo20735d2 = {"com/gabb/packageupdater/service/UpdateService$asCallbackHandle$1", "Lcom/gabb/packageupdater/api/IUpdateCallbackHandle$Stub;", "removeCallback", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UpdateService.kt */
public final class UpdateService$asCallbackHandle$1 extends IUpdateCallbackHandle.Stub {
    final /* synthetic */ Runnable $this_asCallbackHandle;

    UpdateService$asCallbackHandle$1(Runnable runnable) {
        this.$this_asCallbackHandle = runnable;
    }

    public void removeCallback() {
        this.$this_asCallbackHandle.run();
    }
}
