package com.datadog.android.rum.internal.vitals;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\u0004H\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/AggregatingVitalMonitor;", "Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "()V", "lastKnownSample", "", "listeners", "", "Lcom/datadog/android/rum/internal/vitals/VitalListener;", "Lcom/datadog/android/rum/internal/vitals/VitalInfo;", "getLastSample", "notifyListener", "", "listener", "value", "notifyListeners", "onNewSample", "register", "unregister", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: AggregatingVitalMonitor.kt */
public final class AggregatingVitalMonitor implements VitalMonitor {
    private double lastKnownSample = Double.NaN;
    private final Map<VitalListener, VitalInfo> listeners = new LinkedHashMap();

    public void onNewSample(double d) {
        this.lastKnownSample = d;
        notifyListeners(d);
    }

    public double getLastSample() {
        return this.lastKnownSample;
    }

    public void register(VitalListener vitalListener) {
        Intrinsics.checkNotNullParameter(vitalListener, "listener");
        double d = this.lastKnownSample;
        synchronized (this.listeners) {
            this.listeners.put(vitalListener, VitalInfo.Companion.getEMPTY());
            Unit unit = Unit.INSTANCE;
        }
        if (!Double.isNaN(d)) {
            notifyListener(vitalListener, d);
        }
    }

    public void unregister(VitalListener vitalListener) {
        Intrinsics.checkNotNullParameter(vitalListener, "listener");
        synchronized (this.listeners) {
            VitalInfo remove = this.listeners.remove(vitalListener);
        }
    }

    private final void notifyListeners(double d) {
        synchronized (this.listeners) {
            for (VitalListener notifyListener : this.listeners.keySet()) {
                notifyListener(notifyListener, d);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void notifyListener(VitalListener vitalListener, double d) {
        VitalInfo vitalInfo = this.listeners.get(vitalListener);
        if (vitalInfo == null) {
            vitalInfo = VitalInfo.Companion.getEMPTY();
        }
        int sampleCount = vitalInfo.getSampleCount() + 1;
        VitalInfo vitalInfo2 = new VitalInfo(sampleCount, Math.min(d, vitalInfo.getMinValue()), Math.max(d, vitalInfo.getMaxValue()), ((((double) vitalInfo.getSampleCount()) * vitalInfo.getMeanValue()) + d) / ((double) sampleCount));
        vitalListener.onVitalUpdate(vitalInfo2);
        synchronized (this.listeners) {
            this.listeners.put(vitalListener, vitalInfo2);
            Unit unit = Unit.INSTANCE;
        }
    }
}
