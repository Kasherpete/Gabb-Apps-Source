package com.datadog.android.log.internal.logger;

import com.datadog.trace.api.Config;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010\u0004JU\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u0018"}, mo20735d2 = {"Lcom/datadog/android/log/internal/logger/InternalLogHandler;", "Lcom/datadog/android/log/internal/logger/LogHandler;", "logcatLogHandler", "telemetryLogHandler", "(Lcom/datadog/android/log/internal/logger/LogHandler;Lcom/datadog/android/log/internal/logger/LogHandler;)V", "getLogcatLogHandler$dd_sdk_android_release", "()Lcom/datadog/android/log/internal/logger/LogHandler;", "getTelemetryLogHandler$dd_sdk_android_release", "handleLog", "", "level", "", "message", "", "throwable", "", "attributes", "", "", "tags", "", "timestamp", "", "(ILjava/lang/String;Ljava/lang/Throwable;Ljava/util/Map;Ljava/util/Set;Ljava/lang/Long;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: InternalLogHandler.kt */
public final class InternalLogHandler implements LogHandler {
    private final LogHandler logcatLogHandler;
    private final LogHandler telemetryLogHandler;

    public InternalLogHandler(LogHandler logHandler, LogHandler logHandler2) {
        Intrinsics.checkNotNullParameter(logHandler, "logcatLogHandler");
        Intrinsics.checkNotNullParameter(logHandler2, "telemetryLogHandler");
        this.logcatLogHandler = logHandler;
        this.telemetryLogHandler = logHandler2;
    }

    public final LogHandler getLogcatLogHandler$dd_sdk_android_release() {
        return this.logcatLogHandler;
    }

    public final LogHandler getTelemetryLogHandler$dd_sdk_android_release() {
        return this.telemetryLogHandler;
    }

    public void handleLog(int i, String str, Throwable th, Map<String, ? extends Object> map, Set<String> set, Long l) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        Intrinsics.checkNotNullParameter(set, Config.TAGS);
        int i2 = i & -33;
        this.logcatLogHandler.handleLog(i2, str, th, map, set, l);
        if ((i & 32) != 0) {
            this.telemetryLogHandler.handleLog(i2, str, th, map, set, l);
        }
    }
}
