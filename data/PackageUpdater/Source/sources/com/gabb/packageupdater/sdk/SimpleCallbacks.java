package com.gabb.packageupdater.sdk;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016¨\u0006\u000f"}, mo20735d2 = {"Lcom/gabb/packageupdater/sdk/SimpleCallbacks;", "Lcom/gabb/packageupdater/sdk/UpdateCallbacks;", "()V", "onCancelled", "", "onDownloadQueued", "onDownloadStarted", "onDownloading", "percent", "", "onError", "throwable", "", "onInstalling", "onSuccess", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SimpleCallbacks.kt */
public class SimpleCallbacks implements UpdateCallbacks {
    public void onCancelled() {
    }

    public void onDownloadQueued() {
    }

    public void onDownloadStarted() {
    }

    public void onDownloading(int i) {
    }

    public void onError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
    }

    public void onInstalling() {
    }

    public void onSuccess() {
    }
}
