package com.datadog.android.core.internal.utils;

import com.datadog.android.BuildConfig;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.logger.ConditionalLogHandler;
import com.datadog.android.log.internal.logger.InternalLogHandler;
import com.datadog.android.log.internal.logger.LogHandler;
import com.datadog.android.log.internal.logger.LogcatLogHandler;
import com.datadog.android.log.internal.logger.NoOpLogHandler;
import com.datadog.android.log.internal.logger.TelemetryLogHandler;
import com.datadog.android.telemetry.internal.Telemetry;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a\b\u0010\u0010\u001a\u00020\u0011H\u0000\u001a\b\u0010\u0012\u001a\u00020\u0006H\u0002\u001a\b\u0010\u0013\u001a\u00020\u0006H\u0000\u001a,\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u001e\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, mo20735d2 = {"DEV_LOG_PREFIX", "", "SDK_LOG_PREFIX", "WARN_DEPRECATED", "WARN_DEPRECATED_WITH_ALT", "devLogger", "Lcom/datadog/android/log/Logger;", "getDevLogger", "()Lcom/datadog/android/log/Logger;", "<set-?>", "sdkLogger", "getSdkLogger", "telemetry", "Lcom/datadog/android/telemetry/internal/Telemetry;", "getTelemetry", "()Lcom/datadog/android/telemetry/internal/Telemetry;", "buildDevLogHandler", "Lcom/datadog/android/log/internal/logger/ConditionalLogHandler;", "buildDevLogger", "buildSdkLogger", "warnDeprecated", "", "target", "deprecatedSince", "removedInVersion", "alternative", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RuntimeUtils.kt */
public final class RuntimeUtilsKt {
    public static final String DEV_LOG_PREFIX = "Datadog";
    public static final String SDK_LOG_PREFIX = "DD_LOG";
    public static final String WARN_DEPRECATED = "%s has been deprecated since version %s, and will be removed in version %s.";
    public static final String WARN_DEPRECATED_WITH_ALT = "%s has been deprecated since version %s, and will be removed in version %s. Please use %s instead";
    private static final Logger devLogger = buildDevLogger();
    private static Logger sdkLogger = buildSdkLogger();
    private static final Telemetry telemetry = new Telemetry();

    public static final Telemetry getTelemetry() {
        return telemetry;
    }

    public static final Logger getSdkLogger() {
        return sdkLogger;
    }

    public static final Logger buildSdkLogger() {
        LogHandler logHandler;
        Boolean bool = BuildConfig.LOGCAT_ENABLED;
        Intrinsics.checkNotNullExpressionValue(bool, "LOGCAT_ENABLED");
        if (bool.booleanValue()) {
            logHandler = new LogcatLogHandler(SDK_LOG_PREFIX, true);
        } else {
            logHandler = new NoOpLogHandler();
        }
        return new Logger(new InternalLogHandler(logHandler, new TelemetryLogHandler(telemetry)));
    }

    public static final Logger getDevLogger() {
        return devLogger;
    }

    private static final Logger buildDevLogger() {
        return new Logger(buildDevLogHandler());
    }

    public static final ConditionalLogHandler buildDevLogHandler() {
        return new ConditionalLogHandler(new LogcatLogHandler(DEV_LOG_PREFIX, false), RuntimeUtilsKt$buildDevLogHandler$1.INSTANCE);
    }

    public static /* synthetic */ void warnDeprecated$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 8) != 0) {
            str4 = null;
        }
        warnDeprecated(str, str2, str3, str4);
    }

    public static final void warnDeprecated(String str, String str2, String str3, String str4) {
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        Intrinsics.checkNotNullParameter(str, "target");
        Intrinsics.checkNotNullParameter(str2, "deprecatedSince");
        Intrinsics.checkNotNullParameter(str7, "removedInVersion");
        if (str4 == null) {
            Logger logger = devLogger;
            String format = String.format(Locale.US, WARN_DEPRECATED, Arrays.copyOf(new Object[]{str5, str6, str7}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            Logger.w$default(logger, format, (Throwable) null, (Map) null, 6, (Object) null);
            return;
        }
        Logger logger2 = devLogger;
        String format2 = String.format(Locale.US, WARN_DEPRECATED_WITH_ALT, Arrays.copyOf(new Object[]{str5, str6, str7, str4}, 4));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
        Logger.w$default(logger2, format2, (Throwable) null, (Map) null, 6, (Object) null);
    }
}
