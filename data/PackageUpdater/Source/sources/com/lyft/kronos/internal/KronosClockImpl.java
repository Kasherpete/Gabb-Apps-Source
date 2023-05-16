package com.lyft.kronos.internal;

import com.lyft.kronos.Clock;
import com.lyft.kronos.KronosClock;
import com.lyft.kronos.KronosTime;
import com.lyft.kronos.internal.ntp.SntpService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000f\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo20735d2 = {"Lcom/lyft/kronos/internal/KronosClockImpl;", "Lcom/lyft/kronos/KronosClock;", "ntpService", "Lcom/lyft/kronos/internal/ntp/SntpService;", "fallbackClock", "Lcom/lyft/kronos/Clock;", "(Lcom/lyft/kronos/internal/ntp/SntpService;Lcom/lyft/kronos/Clock;)V", "getCurrentNtpTimeMs", "", "()Ljava/lang/Long;", "getCurrentTime", "Lcom/lyft/kronos/KronosTime;", "getElapsedTimeMs", "shutdown", "", "sync", "", "syncInBackground", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: KronosClockImpl.kt */
public final class KronosClockImpl implements KronosClock {
    private final Clock fallbackClock;
    private final SntpService ntpService;

    public KronosClockImpl(SntpService sntpService, Clock clock) {
        Intrinsics.checkNotNullParameter(sntpService, "ntpService");
        Intrinsics.checkNotNullParameter(clock, "fallbackClock");
        this.ntpService = sntpService;
        this.fallbackClock = clock;
    }

    public long getCurrentTimeMs() {
        return KronosClock.DefaultImpls.getCurrentTimeMs(this);
    }

    public boolean sync() {
        return this.ntpService.sync();
    }

    public void syncInBackground() {
        this.ntpService.syncInBackground();
    }

    public void shutdown() {
        this.ntpService.shutdown();
    }

    public long getElapsedTimeMs() {
        return this.fallbackClock.getElapsedTimeMs();
    }

    public KronosTime getCurrentTime() {
        KronosTime currentTime = this.ntpService.currentTime();
        return currentTime != null ? currentTime : new KronosTime(this.fallbackClock.getCurrentTimeMs(), (Long) null);
    }

    public Long getCurrentNtpTimeMs() {
        KronosTime currentTime = this.ntpService.currentTime();
        if (currentTime != null) {
            return Long.valueOf(currentTime.getPosixTimeMs());
        }
        return null;
    }
}
