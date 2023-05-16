package com.datadog.android.rum.internal.domain.scope;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/Timing;", "", "startTime", "", "duration", "(JJ)V", "getDuration", "()J", "getStartTime", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ExternalResourceTimings.kt */
final class Timing {
    private final long duration;
    private final long startTime;

    public static /* synthetic */ Timing copy$default(Timing timing, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = timing.startTime;
        }
        if ((i & 2) != 0) {
            j2 = timing.duration;
        }
        return timing.copy(j, j2);
    }

    public final long component1() {
        return this.startTime;
    }

    public final long component2() {
        return this.duration;
    }

    public final Timing copy(long j, long j2) {
        return new Timing(j, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Timing)) {
            return false;
        }
        Timing timing = (Timing) obj;
        return this.startTime == timing.startTime && this.duration == timing.duration;
    }

    public int hashCode() {
        return (Long.hashCode(this.startTime) * 31) + Long.hashCode(this.duration);
    }

    public String toString() {
        long j = this.startTime;
        return "Timing(startTime=" + j + ", duration=" + this.duration + ")";
    }

    public Timing(long j, long j2) {
        this.startTime = j;
        this.duration = j2;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final long getStartTime() {
        return this.startTime;
    }
}
