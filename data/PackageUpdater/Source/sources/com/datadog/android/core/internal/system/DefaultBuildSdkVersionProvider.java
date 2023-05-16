package com.datadog.android.core.internal.system;

import android.os.Build;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/core/internal/system/DefaultBuildSdkVersionProvider;", "Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;", "()V", "version", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DefaultBuildSdkVersionProvider.kt */
public final class DefaultBuildSdkVersionProvider implements BuildSdkVersionProvider {
    public int version() {
        return Build.VERSION.SDK_INT;
    }
}
