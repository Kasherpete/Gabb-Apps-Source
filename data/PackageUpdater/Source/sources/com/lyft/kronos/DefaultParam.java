package com.lyft.kronos;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/lyft/kronos/DefaultParam;", "", "()V", "CACHE_EXPIRATION_MS", "", "getCACHE_EXPIRATION_MS", "()J", "MAX_NTP_RESPONSE_TIME_MS", "getMAX_NTP_RESPONSE_TIME_MS", "MIN_WAIT_TIME_BETWEEN_SYNC_MS", "getMIN_WAIT_TIME_BETWEEN_SYNC_MS", "NTP_HOSTS", "", "", "getNTP_HOSTS", "()Ljava/util/List;", "TIMEOUT_MS", "getTIMEOUT_MS", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: DefaultParam.kt */
public final class DefaultParam {
    private static final long CACHE_EXPIRATION_MS = TimeUnit.MINUTES.toMillis(1);
    public static final DefaultParam INSTANCE = new DefaultParam();
    private static final long MAX_NTP_RESPONSE_TIME_MS = TimeUnit.SECONDS.toMillis(5);
    private static final long MIN_WAIT_TIME_BETWEEN_SYNC_MS = TimeUnit.MINUTES.toMillis(1);
    private static final List<String> NTP_HOSTS = CollectionsKt.listOf("0.pool.ntp.org", "1.pool.ntp.org", "2.pool.ntp.org", "3.pool.ntp.org");
    private static final long TIMEOUT_MS = TimeUnit.SECONDS.toMillis(6);

    private DefaultParam() {
    }

    public final List<String> getNTP_HOSTS() {
        return NTP_HOSTS;
    }

    public final long getCACHE_EXPIRATION_MS() {
        return CACHE_EXPIRATION_MS;
    }

    public final long getMIN_WAIT_TIME_BETWEEN_SYNC_MS() {
        return MIN_WAIT_TIME_BETWEEN_SYNC_MS;
    }

    public final long getTIMEOUT_MS() {
        return TIMEOUT_MS;
    }

    public final long getMAX_NTP_RESPONSE_TIME_MS() {
        return MAX_NTP_RESPONSE_TIME_MS;
    }
}
