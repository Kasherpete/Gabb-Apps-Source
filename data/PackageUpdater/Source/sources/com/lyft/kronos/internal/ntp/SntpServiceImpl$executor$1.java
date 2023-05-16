package com.lyft.kronos.internal.ntp;

import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0005"}, mo20735d2 = {"<anonymous>", "Ljava/lang/Thread;", "kotlin.jvm.PlatformType", "it", "Ljava/lang/Runnable;", "newThread"}, mo20736k = 3, mo20737mv = {1, 4, 0})
/* compiled from: SntpService.kt */
final class SntpServiceImpl$executor$1 implements ThreadFactory {
    public static final SntpServiceImpl$executor$1 INSTANCE = new SntpServiceImpl$executor$1();

    SntpServiceImpl$executor$1() {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "kronos-android");
    }
}
