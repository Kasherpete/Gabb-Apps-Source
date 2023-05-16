package com.datadog.android.core.internal.system;

import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/internal/system/StaticAndroidInfoProvider;", "Lcom/datadog/android/core/internal/system/AndroidInfoProvider;", "()V", "getDeviceBuildId", "", "getDeviceModel", "getDeviceVersion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: StaticAndroidInfoProvider.kt */
public final class StaticAndroidInfoProvider implements AndroidInfoProvider {
    public static final StaticAndroidInfoProvider INSTANCE = new StaticAndroidInfoProvider();

    private StaticAndroidInfoProvider() {
    }

    public String getDeviceModel() {
        String str = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(str, "MODEL");
        return str;
    }

    public String getDeviceBuildId() {
        String str = Build.ID;
        Intrinsics.checkNotNullExpressionValue(str, "ID");
        return str;
    }

    public String getDeviceVersion() {
        String str = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(str, "RELEASE");
        return str;
    }
}
