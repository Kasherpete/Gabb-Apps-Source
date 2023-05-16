package com.lyft.kronos.internal.ntp;

import com.lyft.kronos.Clock;
import com.lyft.kronos.KronosTime;
import com.lyft.kronos.SyncListener;
import com.lyft.kronos.internal.ntp.SntpClient;
import com.lyft.kronos.internal.ntp.SntpService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001+B_\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012J\n\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020%H\u0016J\b\u0010'\u001a\u00020(H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\fH\u0002J\b\u0010*\u001a\u00020%H\u0016R\u000e\u0010\u0010\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u000e8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001c8BX\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0010\u0012\f\u0012\n \u001a*\u0004\u0018\u00010!0!0 X\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, mo20735d2 = {"Lcom/lyft/kronos/internal/ntp/SntpServiceImpl;", "Lcom/lyft/kronos/internal/ntp/SntpService;", "sntpClient", "Lcom/lyft/kronos/internal/ntp/SntpClient;", "deviceClock", "Lcom/lyft/kronos/Clock;", "responseCache", "Lcom/lyft/kronos/internal/ntp/SntpResponseCache;", "ntpSyncListener", "Lcom/lyft/kronos/SyncListener;", "ntpHosts", "", "", "requestTimeoutMs", "", "minWaitTimeBetweenSyncMs", "cacheExpirationMs", "maxNtpResponseTimeMs", "(Lcom/lyft/kronos/internal/ntp/SntpClient;Lcom/lyft/kronos/Clock;Lcom/lyft/kronos/internal/ntp/SntpResponseCache;Lcom/lyft/kronos/SyncListener;Ljava/util/List;JJJJ)V", "cacheSyncAge", "getCacheSyncAge", "()J", "cachedSyncTime", "Ljava/util/concurrent/atomic/AtomicLong;", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "response", "Lcom/lyft/kronos/internal/ntp/SntpClient$Response;", "getResponse", "()Lcom/lyft/kronos/internal/ntp/SntpClient$Response;", "state", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/lyft/kronos/internal/ntp/SntpServiceImpl$State;", "currentTime", "Lcom/lyft/kronos/KronosTime;", "ensureServiceIsRunning", "", "shutdown", "sync", "", "host", "syncInBackground", "State", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: SntpService.kt */
public final class SntpServiceImpl implements SntpService {
    private final long cacheExpirationMs;
    private final AtomicLong cachedSyncTime;
    private final Clock deviceClock;
    private final ExecutorService executor;
    private final long maxNtpResponseTimeMs;
    private final long minWaitTimeBetweenSyncMs;
    private final List<String> ntpHosts;
    private final SyncListener ntpSyncListener;
    private final long requestTimeoutMs;
    private final SntpResponseCache responseCache;
    private final SntpClient sntpClient;
    private final AtomicReference<State> state;

    @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, mo20735d2 = {"Lcom/lyft/kronos/internal/ntp/SntpServiceImpl$State;", "", "(Ljava/lang/String;I)V", "IDLE", "SYNCING", "STOPPED", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
    /* compiled from: SntpService.kt */
    private enum State {
        IDLE,
        SYNCING,
        STOPPED
    }

    public SntpServiceImpl(SntpClient sntpClient2, Clock clock, SntpResponseCache sntpResponseCache, SyncListener syncListener, List<String> list) {
        this(sntpClient2, clock, sntpResponseCache, syncListener, list, 0, 0, 0, 0, 480, (DefaultConstructorMarker) null);
    }

    public SntpServiceImpl(SntpClient sntpClient2, Clock clock, SntpResponseCache sntpResponseCache, SyncListener syncListener, List<String> list, long j) {
        this(sntpClient2, clock, sntpResponseCache, syncListener, list, j, 0, 0, 0, 448, (DefaultConstructorMarker) null);
    }

    public SntpServiceImpl(SntpClient sntpClient2, Clock clock, SntpResponseCache sntpResponseCache, SyncListener syncListener, List<String> list, long j, long j2) {
        this(sntpClient2, clock, sntpResponseCache, syncListener, list, j, j2, 0, 0, 384, (DefaultConstructorMarker) null);
    }

    public SntpServiceImpl(SntpClient sntpClient2, Clock clock, SntpResponseCache sntpResponseCache, SyncListener syncListener, List<String> list, long j, long j2, long j3) {
        this(sntpClient2, clock, sntpResponseCache, syncListener, list, j, j2, j3, 0, 256, (DefaultConstructorMarker) null);
    }

    public SntpServiceImpl(SntpClient sntpClient2, Clock clock, SntpResponseCache sntpResponseCache, SyncListener syncListener, List<String> list, long j, long j2, long j3, long j4) {
        Intrinsics.checkNotNullParameter(sntpClient2, "sntpClient");
        Intrinsics.checkNotNullParameter(clock, "deviceClock");
        Intrinsics.checkNotNullParameter(sntpResponseCache, "responseCache");
        Intrinsics.checkNotNullParameter(list, "ntpHosts");
        this.sntpClient = sntpClient2;
        this.deviceClock = clock;
        this.responseCache = sntpResponseCache;
        this.ntpSyncListener = syncListener;
        this.ntpHosts = list;
        this.requestTimeoutMs = j;
        this.minWaitTimeBetweenSyncMs = j2;
        this.cacheExpirationMs = j3;
        this.maxNtpResponseTimeMs = j4;
        this.state = new AtomicReference<>(State.IDLE);
        this.cachedSyncTime = new AtomicLong(0);
        this.executor = Executors.newSingleThreadExecutor(SntpServiceImpl$executor$1.INSTANCE);
    }

    public long currentTimeMs() {
        return SntpService.DefaultImpls.currentTimeMs(this);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SntpServiceImpl(com.lyft.kronos.internal.ntp.SntpClient r18, com.lyft.kronos.Clock r19, com.lyft.kronos.internal.ntp.SntpResponseCache r20, com.lyft.kronos.SyncListener r21, java.util.List r22, long r23, long r25, long r27, long r29, int r31, kotlin.jvm.internal.DefaultConstructorMarker r32) {
        /*
            r17 = this;
            r0 = r31
            r1 = r0 & 32
            if (r1 == 0) goto L_0x000e
            com.lyft.kronos.DefaultParam r1 = com.lyft.kronos.DefaultParam.INSTANCE
            long r1 = r1.getTIMEOUT_MS()
            r9 = r1
            goto L_0x0010
        L_0x000e:
            r9 = r23
        L_0x0010:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x001c
            com.lyft.kronos.DefaultParam r1 = com.lyft.kronos.DefaultParam.INSTANCE
            long r1 = r1.getMIN_WAIT_TIME_BETWEEN_SYNC_MS()
            r11 = r1
            goto L_0x001e
        L_0x001c:
            r11 = r25
        L_0x001e:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x002a
            com.lyft.kronos.DefaultParam r1 = com.lyft.kronos.DefaultParam.INSTANCE
            long r1 = r1.getCACHE_EXPIRATION_MS()
            r13 = r1
            goto L_0x002c
        L_0x002a:
            r13 = r27
        L_0x002c:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x0038
            com.lyft.kronos.DefaultParam r0 = com.lyft.kronos.DefaultParam.INSTANCE
            long r0 = r0.getMAX_NTP_RESPONSE_TIME_MS()
            r15 = r0
            goto L_0x003a
        L_0x0038:
            r15 = r29
        L_0x003a:
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r3.<init>(r4, r5, r6, r7, r8, r9, r11, r13, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lyft.kronos.internal.ntp.SntpServiceImpl.<init>(com.lyft.kronos.internal.ntp.SntpClient, com.lyft.kronos.Clock, com.lyft.kronos.internal.ntp.SntpResponseCache, com.lyft.kronos.SyncListener, java.util.List, long, long, long, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    private final SntpClient.Response getResponse() {
        SntpClient.Response response = this.responseCache.get();
        if (!(this.state.get() == State.IDLE && response != null && !response.isFromSameBoot())) {
            return response;
        }
        this.responseCache.clear();
        return null;
    }

    private final long getCacheSyncAge() {
        return this.deviceClock.getElapsedTimeMs() - this.cachedSyncTime.get();
    }

    public KronosTime currentTime() {
        ensureServiceIsRunning();
        SntpClient.Response response = getResponse();
        if (response != null) {
            long responseAge = response.getResponseAge();
            if (responseAge >= this.cacheExpirationMs && getCacheSyncAge() >= this.minWaitTimeBetweenSyncMs) {
                syncInBackground();
            }
            return new KronosTime(response.getCurrentTimeMs(), Long.valueOf(responseAge));
        } else if (getCacheSyncAge() < this.minWaitTimeBetweenSyncMs) {
            return null;
        } else {
            syncInBackground();
            return null;
        }
    }

    public void syncInBackground() {
        ensureServiceIsRunning();
        if (this.state.get() != State.SYNCING) {
            this.executor.submit(new SntpServiceImpl$syncInBackground$1(this));
        }
    }

    public void shutdown() {
        ensureServiceIsRunning();
        this.state.set(State.STOPPED);
        this.executor.shutdown();
    }

    public boolean sync() {
        ensureServiceIsRunning();
        for (String sync : this.ntpHosts) {
            if (sync(sync)) {
                return true;
            }
        }
        return false;
    }

    private final void ensureServiceIsRunning() {
        if (this.state.get() == State.STOPPED) {
            throw new IllegalStateException("Service already shutdown");
        }
    }

    private final boolean sync(String str) {
        if (this.state.getAndSet(State.SYNCING) == State.SYNCING) {
            return false;
        }
        long elapsedTimeMs = this.deviceClock.getElapsedTimeMs();
        SyncListener syncListener = this.ntpSyncListener;
        if (syncListener != null) {
            syncListener.onStartSync(str);
        }
        try {
            SntpClient.Response requestTime = this.sntpClient.requestTime(str, Long.valueOf(this.requestTimeoutMs));
            Intrinsics.checkNotNullExpressionValue(requestTime, "response");
            if (requestTime.getCurrentTimeMs() >= 0) {
                long elapsedTimeMs2 = this.deviceClock.getElapsedTimeMs() - elapsedTimeMs;
                if (elapsedTimeMs2 <= this.maxNtpResponseTimeMs) {
                    this.responseCache.update(requestTime);
                    long offsetMs = requestTime.getOffsetMs();
                    SyncListener syncListener2 = this.ntpSyncListener;
                    if (syncListener2 != null) {
                        syncListener2.onSuccess(offsetMs, elapsedTimeMs2);
                    }
                    return true;
                }
                throw new NTPSyncException("Ignoring response from " + str + " because the network latency (" + elapsedTimeMs2 + " ms) is longer than the required value (" + this.maxNtpResponseTimeMs + " ms");
            }
            throw new NTPSyncException("Invalid time " + requestTime.getCurrentTimeMs() + " received from " + str);
        } catch (Throwable th) {
            SyncListener syncListener3 = this.ntpSyncListener;
            if (syncListener3 != null) {
                syncListener3.onError(str, th);
            }
            return false;
        } finally {
            this.state.set(State.IDLE);
            this.cachedSyncTime.set(this.deviceClock.getElapsedTimeMs());
        }
    }
}
