package com.datadog.android.telemetry.internal;

import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.rum.internal.monitor.NoOpAdvancedRumMonitor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u001a\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rR\u0014\u0010\u0003\u001a\u00020\u00048@X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/telemetry/internal/Telemetry;", "", "()V", "rumMonitor", "Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "getRumMonitor$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "debug", "", "message", "", "error", "throwable", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Telemetry.kt */
public final class Telemetry {
    public final AdvancedRumMonitor getRumMonitor$dd_sdk_android_release() {
        RumMonitor rumMonitor = GlobalRum.get();
        AdvancedRumMonitor advancedRumMonitor = rumMonitor instanceof AdvancedRumMonitor ? (AdvancedRumMonitor) rumMonitor : null;
        return advancedRumMonitor == null ? new NoOpAdvancedRumMonitor() : advancedRumMonitor;
    }

    public static /* synthetic */ void error$default(Telemetry telemetry, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        telemetry.error(str, th);
    }

    public final void error(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        getRumMonitor$dd_sdk_android_release().sendErrorTelemetryEvent(str, th);
    }

    public final void debug(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        getRumMonitor$dd_sdk_android_release().sendDebugTelemetryEvent(str);
    }
}
