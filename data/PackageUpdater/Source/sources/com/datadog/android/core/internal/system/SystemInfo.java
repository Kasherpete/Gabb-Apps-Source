package com.datadog.android.core.internal.system;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0019B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, mo20735d2 = {"Lcom/datadog/android/core/internal/system/SystemInfo;", "", "batteryFullOrCharging", "", "batteryLevel", "", "powerSaveMode", "onExternalPowerSource", "(ZIZZ)V", "getBatteryFullOrCharging", "()Z", "getBatteryLevel", "()I", "getOnExternalPowerSource", "getPowerSaveMode", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "BatteryStatus", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SystemInfo.kt */
public final class SystemInfo {
    private final boolean batteryFullOrCharging;
    private final int batteryLevel;
    private final boolean onExternalPowerSource;
    private final boolean powerSaveMode;

    public SystemInfo() {
        this(false, 0, false, false, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ SystemInfo copy$default(SystemInfo systemInfo, boolean z, int i, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = systemInfo.batteryFullOrCharging;
        }
        if ((i2 & 2) != 0) {
            i = systemInfo.batteryLevel;
        }
        if ((i2 & 4) != 0) {
            z2 = systemInfo.powerSaveMode;
        }
        if ((i2 & 8) != 0) {
            z3 = systemInfo.onExternalPowerSource;
        }
        return systemInfo.copy(z, i, z2, z3);
    }

    public final boolean component1() {
        return this.batteryFullOrCharging;
    }

    public final int component2() {
        return this.batteryLevel;
    }

    public final boolean component3() {
        return this.powerSaveMode;
    }

    public final boolean component4() {
        return this.onExternalPowerSource;
    }

    public final SystemInfo copy(boolean z, int i, boolean z2, boolean z3) {
        return new SystemInfo(z, i, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SystemInfo)) {
            return false;
        }
        SystemInfo systemInfo = (SystemInfo) obj;
        return this.batteryFullOrCharging == systemInfo.batteryFullOrCharging && this.batteryLevel == systemInfo.batteryLevel && this.powerSaveMode == systemInfo.powerSaveMode && this.onExternalPowerSource == systemInfo.onExternalPowerSource;
    }

    public int hashCode() {
        boolean z = this.batteryFullOrCharging;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int hashCode = (((z ? 1 : 0) * true) + Integer.hashCode(this.batteryLevel)) * 31;
        boolean z3 = this.powerSaveMode;
        if (z3) {
            z3 = true;
        }
        int i = (hashCode + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.onExternalPowerSource;
        if (!z4) {
            z2 = z4;
        }
        return i + (z2 ? 1 : 0);
    }

    public String toString() {
        boolean z = this.batteryFullOrCharging;
        int i = this.batteryLevel;
        boolean z2 = this.powerSaveMode;
        return "SystemInfo(batteryFullOrCharging=" + z + ", batteryLevel=" + i + ", powerSaveMode=" + z2 + ", onExternalPowerSource=" + this.onExternalPowerSource + ")";
    }

    public SystemInfo(boolean z, int i, boolean z2, boolean z3) {
        this.batteryFullOrCharging = z;
        this.batteryLevel = i;
        this.powerSaveMode = z2;
        this.onExternalPowerSource = z3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SystemInfo(boolean z, int i, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? -1 : i, (i2 & 4) != 0 ? false : z2, (i2 & 8) != 0 ? false : z3);
    }

    public final boolean getBatteryFullOrCharging() {
        return this.batteryFullOrCharging;
    }

    public final int getBatteryLevel() {
        return this.batteryLevel;
    }

    public final boolean getPowerSaveMode() {
        return this.powerSaveMode;
    }

    public final boolean getOnExternalPowerSource() {
        return this.onExternalPowerSource;
    }

    @Metadata(mo20734d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/core/internal/system/SystemInfo$BatteryStatus;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "CHARGING", "DISCHARGING", "NOT_CHARGING", "FULL", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SystemInfo.kt */
    public enum BatteryStatus {
        UNKNOWN,
        CHARGING,
        DISCHARGING,
        NOT_CHARGING,
        FULL;
        
        public static final Companion Companion = null;

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/internal/system/SystemInfo$BatteryStatus$Companion;", "", "()V", "fromAndroidStatus", "Lcom/datadog/android/core/internal/system/SystemInfo$BatteryStatus;", "status", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: SystemInfo.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final BatteryStatus fromAndroidStatus(int i) {
                if (i == 2) {
                    return BatteryStatus.CHARGING;
                }
                if (i == 3) {
                    return BatteryStatus.DISCHARGING;
                }
                if (i == 4) {
                    return BatteryStatus.NOT_CHARGING;
                }
                if (i != 5) {
                    return BatteryStatus.UNKNOWN;
                }
                return BatteryStatus.FULL;
            }
        }
    }
}
