package com.lyft.kronos.internal.ntp;

import com.lyft.kronos.internal.ntp.SntpClient;
import kotlin.Metadata;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, mo20735d2 = {"Lcom/lyft/kronos/internal/ntp/SntpResponseCache;", "", "clear", "", "get", "Lcom/lyft/kronos/internal/ntp/SntpClient$Response;", "update", "response", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: SntpResponseCache.kt */
public interface SntpResponseCache {
    void clear();

    SntpClient.Response get();

    void update(SntpClient.Response response);
}
