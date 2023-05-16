package com.datadog.android.core.internal.lifecycle;

import android.content.Context;
import com.datadog.android.core.internal.lifecycle.ProcessLifecycleMonitor;
import com.datadog.android.core.internal.net.info.NetworkInfoProvider;
import com.datadog.android.core.internal.utils.WorkManagerUtilsKt;
import com.datadog.android.core.model.NetworkInfo;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/core/internal/lifecycle/ProcessLifecycleCallback;", "Lcom/datadog/android/core/internal/lifecycle/ProcessLifecycleMonitor$Callback;", "networkInfoProvider", "Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;", "appContext", "Landroid/content/Context;", "(Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;Landroid/content/Context;)V", "contextWeakRef", "Ljava/lang/ref/Reference;", "getContextWeakRef$dd_sdk_android_release", "()Ljava/lang/ref/Reference;", "getNetworkInfoProvider", "()Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;", "onPaused", "", "onResumed", "onStarted", "onStopped", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ProcessLifecycleCallback.kt */
public final class ProcessLifecycleCallback implements ProcessLifecycleMonitor.Callback {
    private final Reference<Context> contextWeakRef;
    private final NetworkInfoProvider networkInfoProvider;

    public void onPaused() {
    }

    public void onResumed() {
    }

    public ProcessLifecycleCallback(NetworkInfoProvider networkInfoProvider2, Context context) {
        Intrinsics.checkNotNullParameter(networkInfoProvider2, "networkInfoProvider");
        Intrinsics.checkNotNullParameter(context, "appContext");
        this.networkInfoProvider = networkInfoProvider2;
        this.contextWeakRef = new WeakReference(context);
    }

    public final NetworkInfoProvider getNetworkInfoProvider() {
        return this.networkInfoProvider;
    }

    public final Reference<Context> getContextWeakRef$dd_sdk_android_release() {
        return this.contextWeakRef;
    }

    public void onStarted() {
        Context context = this.contextWeakRef.get();
        if (context != null) {
            WorkManagerUtilsKt.cancelUploadWorker(context);
        }
    }

    public void onStopped() {
        Context context;
        if ((this.networkInfoProvider.getLatestNetworkInfo().getConnectivity() == NetworkInfo.Connectivity.NETWORK_NOT_CONNECTED) && (context = this.contextWeakRef.get()) != null) {
            WorkManagerUtilsKt.triggerUploadWorker(context);
        }
    }
}
