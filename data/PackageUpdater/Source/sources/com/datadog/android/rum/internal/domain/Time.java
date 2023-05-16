package com.datadog.android.rum.internal.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/Time;", "", "timestamp", "", "nanoTime", "(JJ)V", "getNanoTime", "()J", "getTimestamp", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Time.kt */
public final class Time {
    private final long nanoTime;
    private final long timestamp;

    public Time() {
        this(0, 0, 3, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Time copy$default(Time time, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = time.timestamp;
        }
        if ((i & 2) != 0) {
            j2 = time.nanoTime;
        }
        return time.copy(j, j2);
    }

    public final long component1() {
        return this.timestamp;
    }

    public final long component2() {
        return this.nanoTime;
    }

    public final Time copy(long j, long j2) {
        return new Time(j, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Time)) {
            return false;
        }
        Time time = (Time) obj;
        return this.timestamp == time.timestamp && this.nanoTime == time.nanoTime;
    }

    public int hashCode() {
        return (Long.hashCode(this.timestamp) * 31) + Long.hashCode(this.nanoTime);
    }

    public String toString() {
        long j = this.timestamp;
        return "Time(timestamp=" + j + ", nanoTime=" + this.nanoTime + ")";
    }

    public Time(long j, long j2) {
        this.timestamp = j;
        this.nanoTime = j2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Time(long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? System.currentTimeMillis() : j, (i & 2) != 0 ? System.nanoTime() : j2);
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final long getNanoTime() {
        return this.nanoTime;
    }
}
