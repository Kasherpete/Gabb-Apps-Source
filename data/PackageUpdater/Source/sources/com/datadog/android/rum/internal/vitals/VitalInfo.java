package com.datadog.android.rum.internal.vitals;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/VitalInfo;", "", "sampleCount", "", "minValue", "", "maxValue", "meanValue", "(IDDD)V", "getMaxValue", "()D", "getMeanValue", "getMinValue", "getSampleCount", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: VitalInfo.kt */
public final class VitalInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final VitalInfo EMPTY = new VitalInfo(0, Double.MAX_VALUE, -1.7976931348623157E308d, 0.0d);
    private final double maxValue;
    private final double meanValue;
    private final double minValue;
    private final int sampleCount;

    public static /* synthetic */ VitalInfo copy$default(VitalInfo vitalInfo, int i, double d, double d2, double d3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = vitalInfo.sampleCount;
        }
        if ((i2 & 2) != 0) {
            d = vitalInfo.minValue;
        }
        double d4 = d;
        if ((i2 & 4) != 0) {
            d2 = vitalInfo.maxValue;
        }
        double d5 = d2;
        if ((i2 & 8) != 0) {
            d3 = vitalInfo.meanValue;
        }
        return vitalInfo.copy(i, d4, d5, d3);
    }

    public final int component1() {
        return this.sampleCount;
    }

    public final double component2() {
        return this.minValue;
    }

    public final double component3() {
        return this.maxValue;
    }

    public final double component4() {
        return this.meanValue;
    }

    public final VitalInfo copy(int i, double d, double d2, double d3) {
        return new VitalInfo(i, d, d2, d3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VitalInfo)) {
            return false;
        }
        VitalInfo vitalInfo = (VitalInfo) obj;
        return this.sampleCount == vitalInfo.sampleCount && Intrinsics.areEqual((Object) Double.valueOf(this.minValue), (Object) Double.valueOf(vitalInfo.minValue)) && Intrinsics.areEqual((Object) Double.valueOf(this.maxValue), (Object) Double.valueOf(vitalInfo.maxValue)) && Intrinsics.areEqual((Object) Double.valueOf(this.meanValue), (Object) Double.valueOf(vitalInfo.meanValue));
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.sampleCount) * 31) + Double.hashCode(this.minValue)) * 31) + Double.hashCode(this.maxValue)) * 31) + Double.hashCode(this.meanValue);
    }

    public String toString() {
        int i = this.sampleCount;
        double d = this.minValue;
        double d2 = this.maxValue;
        return "VitalInfo(sampleCount=" + i + ", minValue=" + d + ", maxValue=" + d2 + ", meanValue=" + this.meanValue + ")";
    }

    public VitalInfo(int i, double d, double d2, double d3) {
        this.sampleCount = i;
        this.minValue = d;
        this.maxValue = d2;
        this.meanValue = d3;
    }

    public final int getSampleCount() {
        return this.sampleCount;
    }

    public final double getMinValue() {
        return this.minValue;
    }

    public final double getMaxValue() {
        return this.maxValue;
    }

    public final double getMeanValue() {
        return this.meanValue;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/VitalInfo$Companion;", "", "()V", "EMPTY", "Lcom/datadog/android/rum/internal/vitals/VitalInfo;", "getEMPTY", "()Lcom/datadog/android/rum/internal/vitals/VitalInfo;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: VitalInfo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VitalInfo getEMPTY() {
            return VitalInfo.EMPTY;
        }
    }
}
