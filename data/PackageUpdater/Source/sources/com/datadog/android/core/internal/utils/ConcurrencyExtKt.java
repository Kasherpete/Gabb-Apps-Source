package com.datadog.android.core.internal.utils;

import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a2\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo20735d2 = {"ERROR_TASK_REJECTED", "", "executeSafe", "", "Ljava/util/concurrent/ExecutorService;", "operationName", "runnable", "Ljava/lang/Runnable;", "scheduleSafe", "Ljava/util/concurrent/ScheduledFuture;", "Ljava/util/concurrent/ScheduledExecutorService;", "delay", "", "unit", "Ljava/util/concurrent/TimeUnit;", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ConcurrencyExt.kt */
public final class ConcurrencyExtKt {
    public static final String ERROR_TASK_REJECTED = "Unable to schedule %s task on the executor";

    public static final void executeSafe(ExecutorService executorService, String str, Runnable runnable) {
        Intrinsics.checkNotNullParameter(executorService, "<this>");
        Intrinsics.checkNotNullParameter(str, "operationName");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        try {
            executorService.execute(runnable);
        } catch (RejectedExecutionException e) {
            Logger sdkLogger = RuntimeUtilsKt.getSdkLogger();
            String format = String.format(Locale.US, ERROR_TASK_REJECTED, Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(sdkLogger, format, e, (Map) null, 4, (Object) null);
        }
    }

    public static final ScheduledFuture<?> scheduleSafe(ScheduledExecutorService scheduledExecutorService, String str, long j, TimeUnit timeUnit, Runnable runnable) {
        Intrinsics.checkNotNullParameter(scheduledExecutorService, "<this>");
        Intrinsics.checkNotNullParameter(str, "operationName");
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        try {
            return scheduledExecutorService.schedule(runnable, j, timeUnit);
        } catch (RejectedExecutionException e) {
            Logger sdkLogger = RuntimeUtilsKt.getSdkLogger();
            String format = String.format(Locale.US, ERROR_TASK_REJECTED, Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(sdkLogger, format, e, (Map) null, 4, (Object) null);
            return null;
        }
    }
}
