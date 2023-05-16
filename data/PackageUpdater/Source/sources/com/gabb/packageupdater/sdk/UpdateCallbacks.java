package com.gabb.packageupdater.sdk;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000e"}, mo20735d2 = {"Lcom/gabb/packageupdater/sdk/UpdateCallbacks;", "", "onCancelled", "", "onDownloadQueued", "onDownloadStarted", "onDownloading", "percent", "", "onError", "throwable", "", "onInstalling", "onSuccess", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UpdateCallbacks.kt */
public interface UpdateCallbacks {
    void onCancelled();

    void onDownloadQueued();

    void onDownloadStarted();

    void onDownloading(int i);

    void onError(Throwable th);

    void onInstalling();

    void onSuccess();
}
