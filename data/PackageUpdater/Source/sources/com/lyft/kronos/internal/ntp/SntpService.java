package com.lyft.kronos.internal.ntp;

import com.lyft.kronos.KronosTime;
import kotlin.Metadata;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0007H&¨\u0006\u000b"}, mo20735d2 = {"Lcom/lyft/kronos/internal/ntp/SntpService;", "", "currentTime", "Lcom/lyft/kronos/KronosTime;", "currentTimeMs", "", "shutdown", "", "sync", "", "syncInBackground", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: SntpService.kt */
public interface SntpService {
    KronosTime currentTime();

    long currentTimeMs();

    void shutdown();

    boolean sync();

    void syncInBackground();

    @Metadata(mo20733bv = {1, 0, 3}, mo20736k = 3, mo20737mv = {1, 4, 0})
    /* compiled from: SntpService.kt */
    public static final class DefaultImpls {
        public static long currentTimeMs(SntpService sntpService) {
            KronosTime currentTime = sntpService.currentTime();
            if (currentTime != null) {
                return currentTime.getPosixTimeMs();
            }
            return 0;
        }
    }
}
