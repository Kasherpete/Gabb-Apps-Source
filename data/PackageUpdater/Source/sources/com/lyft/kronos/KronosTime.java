package com.lyft.kronos;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ$\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, mo20735d2 = {"Lcom/lyft/kronos/KronosTime;", "", "posixTimeMs", "", "timeSinceLastNtpSyncMs", "(JLjava/lang/Long;)V", "getPosixTimeMs", "()J", "getTimeSinceLastNtpSyncMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "copy", "(JLjava/lang/Long;)Lcom/lyft/kronos/KronosTime;", "equals", "", "other", "hashCode", "", "toString", "", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: Clock.kt */
public final class KronosTime {
    private final long posixTimeMs;
    private final Long timeSinceLastNtpSyncMs;

    public static /* synthetic */ KronosTime copy$default(KronosTime kronosTime, long j, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            j = kronosTime.posixTimeMs;
        }
        if ((i & 2) != 0) {
            l = kronosTime.timeSinceLastNtpSyncMs;
        }
        return kronosTime.copy(j, l);
    }

    public final long component1() {
        return this.posixTimeMs;
    }

    public final Long component2() {
        return this.timeSinceLastNtpSyncMs;
    }

    public final KronosTime copy(long j, Long l) {
        return new KronosTime(j, l);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KronosTime)) {
            return false;
        }
        KronosTime kronosTime = (KronosTime) obj;
        return this.posixTimeMs == kronosTime.posixTimeMs && Intrinsics.areEqual((Object) this.timeSinceLastNtpSyncMs, (Object) kronosTime.timeSinceLastNtpSyncMs);
    }

    public int hashCode() {
        long j = this.posixTimeMs;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        Long l = this.timeSinceLastNtpSyncMs;
        return i + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "KronosTime(posixTimeMs=" + this.posixTimeMs + ", timeSinceLastNtpSyncMs=" + this.timeSinceLastNtpSyncMs + ")";
    }

    public KronosTime(long j, Long l) {
        this.posixTimeMs = j;
        this.timeSinceLastNtpSyncMs = l;
    }

    public final long getPosixTimeMs() {
        return this.posixTimeMs;
    }

    public final Long getTimeSinceLastNtpSyncMs() {
        return this.timeSinceLastNtpSyncMs;
    }
}
