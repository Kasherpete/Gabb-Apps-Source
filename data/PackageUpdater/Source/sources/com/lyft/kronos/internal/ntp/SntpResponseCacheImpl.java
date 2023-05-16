package com.lyft.kronos.internal.ntp;

import com.lyft.kronos.Clock;
import com.lyft.kronos.SyncResponseCache;
import com.lyft.kronos.internal.ntp.SntpClient;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo20735d2 = {"Lcom/lyft/kronos/internal/ntp/SntpResponseCacheImpl;", "Lcom/lyft/kronos/internal/ntp/SntpResponseCache;", "syncResponseCache", "Lcom/lyft/kronos/SyncResponseCache;", "deviceClock", "Lcom/lyft/kronos/Clock;", "(Lcom/lyft/kronos/SyncResponseCache;Lcom/lyft/kronos/Clock;)V", "clear", "", "get", "Lcom/lyft/kronos/internal/ntp/SntpClient$Response;", "update", "response", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: SntpResponseCache.kt */
public final class SntpResponseCacheImpl implements SntpResponseCache {
    private final Clock deviceClock;
    private final SyncResponseCache syncResponseCache;

    public SntpResponseCacheImpl(SyncResponseCache syncResponseCache2, Clock clock) {
        Intrinsics.checkNotNullParameter(syncResponseCache2, "syncResponseCache");
        Intrinsics.checkNotNullParameter(clock, "deviceClock");
        this.syncResponseCache = syncResponseCache2;
        this.deviceClock = clock;
    }

    public SntpClient.Response get() {
        long currentTime = this.syncResponseCache.getCurrentTime();
        long elapsedTime = this.syncResponseCache.getElapsedTime();
        long currentOffset = this.syncResponseCache.getCurrentOffset();
        if (elapsedTime == 0) {
            return null;
        }
        return new SntpClient.Response(currentTime, elapsedTime, currentOffset, this.deviceClock);
    }

    public void update(SntpClient.Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        synchronized (this) {
            this.syncResponseCache.setCurrentTime(response.getDeviceCurrentTimestampMs());
            this.syncResponseCache.setElapsedTime(response.getDeviceElapsedTimestampMs());
            this.syncResponseCache.setCurrentOffset(response.getOffsetMs());
            Unit unit = Unit.INSTANCE;
        }
    }

    public void clear() {
        synchronized (this) {
            this.syncResponseCache.clear();
            Unit unit = Unit.INSTANCE;
        }
    }
}
