package com.datadog.android.rum.internal.vitals;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "Lcom/datadog/android/rum/internal/vitals/VitalObserver;", "getLastSample", "", "register", "", "listener", "Lcom/datadog/android/rum/internal/vitals/VitalListener;", "unregister", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: VitalMonitor.kt */
public interface VitalMonitor extends VitalObserver {
    double getLastSample();

    void register(VitalListener vitalListener);

    void unregister(VitalListener vitalListener);
}
