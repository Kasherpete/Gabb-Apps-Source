package com.datadog.android.log.internal.utils;

import com.datadog.android.log.Logger;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\b\u0010\u0007\u001a\u00020\bH\u0000\u001a8\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010H\u0000\u001a8\u0010\u0012\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010H\u0000\u001a8\u0010\u0013\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo20735d2 = {"DEBUG_WITH_TELEMETRY_LEVEL", "", "ERROR_WITH_TELEMETRY_LEVEL", "ISO_8601", "", "TELEMETRY_LOG_FLAG", "WARN_WITH_TELEMETRY_LEVEL", "buildLogDateFormat", "Ljava/text/SimpleDateFormat;", "debugWithTelemetry", "", "Lcom/datadog/android/log/Logger;", "message", "throwable", "", "attributes", "", "", "errorWithTelemetry", "warningWithTelemetry", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LogUtils.kt */
public final class LogUtilsKt {
    public static final int DEBUG_WITH_TELEMETRY_LEVEL = 35;
    public static final int ERROR_WITH_TELEMETRY_LEVEL = 38;
    public static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final int TELEMETRY_LOG_FLAG = 32;
    public static final int WARN_WITH_TELEMETRY_LEVEL = 37;

    public static final SimpleDateFormat buildLogDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public static /* synthetic */ void errorWithTelemetry$default(Logger logger, String str, Throwable th, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        errorWithTelemetry(logger, str, th, map);
    }

    public static final void errorWithTelemetry(Logger logger, String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(logger, "<this>");
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        logger.log(38, str, th, map);
    }

    public static /* synthetic */ void warningWithTelemetry$default(Logger logger, String str, Throwable th, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        warningWithTelemetry(logger, str, th, map);
    }

    public static final void warningWithTelemetry(Logger logger, String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(logger, "<this>");
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        logger.log(37, str, th, map);
    }

    public static /* synthetic */ void debugWithTelemetry$default(Logger logger, String str, Throwable th, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        debugWithTelemetry(logger, str, th, map);
    }

    public static final void debugWithTelemetry(Logger logger, String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(logger, "<this>");
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        logger.log(35, str, th, map);
    }
}
