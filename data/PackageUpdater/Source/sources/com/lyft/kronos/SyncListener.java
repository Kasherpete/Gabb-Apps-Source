package com.lyft.kronos;

import kotlin.Metadata;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&¨\u0006\r"}, mo20735d2 = {"Lcom/lyft/kronos/SyncListener;", "", "onError", "", "host", "", "throwable", "", "onStartSync", "onSuccess", "ticksDelta", "", "responseTimeMs", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: SyncListener.kt */
public interface SyncListener {
    void onError(String str, Throwable th);

    void onStartSync(String str);

    void onSuccess(long j, long j2);
}
