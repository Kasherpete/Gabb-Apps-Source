package com.datadog.android.rum.internal.vitals;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/NoOpVitalMonitor;", "Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "()V", "getLastSample", "", "onNewSample", "", "value", "register", "listener", "Lcom/datadog/android/rum/internal/vitals/VitalListener;", "unregister", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpVitalMonitor.kt */
public final class NoOpVitalMonitor implements VitalMonitor {
    public double getLastSample() {
        return 0.0d;
    }

    public void onNewSample(double d) {
    }

    public void register(VitalListener vitalListener) {
        Intrinsics.checkNotNullParameter(vitalListener, "listener");
    }

    public void unregister(VitalListener vitalListener) {
        Intrinsics.checkNotNullParameter(vitalListener, "listener");
    }
}
