package com.datadog.android.log.internal.logger;

import com.datadog.android.telemetry.internal.Telemetry;
import com.datadog.trace.api.Config;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JU\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/log/internal/logger/TelemetryLogHandler;", "Lcom/datadog/android/log/internal/logger/LogHandler;", "telemetry", "Lcom/datadog/android/telemetry/internal/Telemetry;", "(Lcom/datadog/android/telemetry/internal/Telemetry;)V", "handleLog", "", "level", "", "message", "", "throwable", "", "attributes", "", "", "tags", "", "timestamp", "", "(ILjava/lang/String;Ljava/lang/Throwable;Ljava/util/Map;Ljava/util/Set;Ljava/lang/Long;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TelemetryLogHandler.kt */
public final class TelemetryLogHandler implements LogHandler {
    private final Telemetry telemetry;

    public TelemetryLogHandler(Telemetry telemetry2) {
        Intrinsics.checkNotNullParameter(telemetry2, "telemetry");
        this.telemetry = telemetry2;
    }

    public void handleLog(int i, String str, Throwable th, Map<String, ? extends Object> map, Set<String> set, Long l) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        Intrinsics.checkNotNullParameter(set, Config.TAGS);
        if (i == 5 || i == 6) {
            this.telemetry.error(str, th);
        } else {
            this.telemetry.debug(str);
        }
    }
}
