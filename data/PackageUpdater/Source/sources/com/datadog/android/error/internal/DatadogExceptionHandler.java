package com.datadog.android.error.internal;

import android.content.Context;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.model.NetworkInfo;
import com.datadog.android.core.model.UserInfo;
import com.datadog.android.log.internal.domain.LogGenerator;
import com.datadog.android.log.model.LogEvent;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010H\u0016R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, mo20735d2 = {"Lcom/datadog/android/error/internal/DatadogExceptionHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "logGenerator", "Lcom/datadog/android/log/internal/domain/LogGenerator;", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "Lcom/datadog/android/log/model/LogEvent;", "appContext", "Landroid/content/Context;", "(Lcom/datadog/android/log/internal/domain/LogGenerator;Lcom/datadog/android/core/internal/persistence/DataWriter;Landroid/content/Context;)V", "contextRef", "Ljava/lang/ref/WeakReference;", "previousHandler", "createCrashMessage", "", "throwable", "", "createLog", "thread", "Ljava/lang/Thread;", "register", "", "uncaughtException", "t", "e", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogExceptionHandler.kt */
public final class DatadogExceptionHandler implements Thread.UncaughtExceptionHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EXECUTOR_NOT_IDLED_WARNING_MESSAGE = "Datadog SDK is in an unexpected state due to an ongoing crash. Some events could be lost";
    public static final String LOGGER_NAME = "crash";
    public static final long MAX_WAIT_FOR_IDLE_TIME_IN_MS = 100;
    public static final String MESSAGE = "Application crash detected";
    private final WeakReference<Context> contextRef;
    private final LogGenerator logGenerator;
    private Thread.UncaughtExceptionHandler previousHandler;
    private final DataWriter<LogEvent> writer;

    public DatadogExceptionHandler(LogGenerator logGenerator2, DataWriter<LogEvent> dataWriter, Context context) {
        Intrinsics.checkNotNullParameter(logGenerator2, "logGenerator");
        Intrinsics.checkNotNullParameter(dataWriter, "writer");
        this.logGenerator = logGenerator2;
        this.writer = dataWriter;
        this.contextRef = new WeakReference<>(context);
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [java.util.concurrent.ExecutorService] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uncaughtException(java.lang.Thread r8, java.lang.Throwable r9) {
        /*
            r7 = this;
            java.lang.String r0 = "t"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "e"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            com.datadog.android.core.internal.persistence.DataWriter<com.datadog.android.log.model.LogEvent> r0 = r7.writer
            com.datadog.android.log.model.LogEvent r1 = r7.createLog(r8, r9)
            r0.write(r1)
            com.datadog.android.rum.RumMonitor r0 = com.datadog.android.rum.GlobalRum.get()
            boolean r1 = r0 instanceof com.datadog.android.rum.internal.monitor.AdvancedRumMonitor
            r2 = 0
            if (r1 == 0) goto L_0x001f
            com.datadog.android.rum.internal.monitor.AdvancedRumMonitor r0 = (com.datadog.android.rum.internal.monitor.AdvancedRumMonitor) r0
            goto L_0x0020
        L_0x001f:
            r0 = r2
        L_0x0020:
            if (r0 != 0) goto L_0x0023
            goto L_0x002c
        L_0x0023:
            java.lang.String r1 = r7.createCrashMessage(r9)
            com.datadog.android.rum.RumErrorSource r3 = com.datadog.android.rum.RumErrorSource.SOURCE
            r0.addCrash(r1, r3, r9)
        L_0x002c:
            com.datadog.android.core.internal.CoreFeature r0 = com.datadog.android.core.internal.CoreFeature.INSTANCE
            java.util.concurrent.ExecutorService r0 = r0.getPersistenceExecutorService$dd_sdk_android_release()
            boolean r1 = r0 instanceof java.util.concurrent.ThreadPoolExecutor
            if (r1 == 0) goto L_0x0039
            r2 = r0
            java.util.concurrent.ThreadPoolExecutor r2 = (java.util.concurrent.ThreadPoolExecutor) r2
        L_0x0039:
            if (r2 != 0) goto L_0x003d
            r0 = 1
            goto L_0x0043
        L_0x003d:
            r0 = 100
            boolean r0 = com.datadog.android.core.internal.thread.ThreadPoolExecutorExtKt.waitToIdle(r2, r0)
        L_0x0043:
            if (r0 != 0) goto L_0x0052
            com.datadog.android.log.Logger r1 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getDevLogger()
            r3 = 0
            r4 = 0
            r5 = 6
            r6 = 0
            java.lang.String r2 = "Datadog SDK is in an unexpected state due to an ongoing crash. Some events could be lost"
            com.datadog.android.log.Logger.w$default(r1, r2, r3, r4, r5, r6)
        L_0x0052:
            java.lang.ref.WeakReference<android.content.Context> r0 = r7.contextRef
            java.lang.Object r0 = r0.get()
            android.content.Context r0 = (android.content.Context) r0
            if (r0 != 0) goto L_0x005d
            goto L_0x0060
        L_0x005d:
            com.datadog.android.core.internal.utils.WorkManagerUtilsKt.triggerUploadWorker(r0)
        L_0x0060:
            java.lang.Thread$UncaughtExceptionHandler r7 = r7.previousHandler
            if (r7 != 0) goto L_0x0065
            goto L_0x0068
        L_0x0065:
            r7.uncaughtException(r8, r9)
        L_0x0068:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.error.internal.DatadogExceptionHandler.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }

    public final void register() {
        this.previousHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private final LogEvent createLog(Thread thread, Throwable th) {
        Throwable th2 = th;
        return LogGenerator.generateLog$default(this.logGenerator, 9, createCrashMessage(th2), th2, MapsKt.emptyMap(), SetsKt.emptySet(), System.currentTimeMillis(), thread.getName(), false, false, (UserInfo) null, (NetworkInfo) null, 1920, (Object) null);
    }

    private final String createCrashMessage(Throwable th) {
        String message = th.getMessage();
        CharSequence charSequence = message;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            return message;
        }
        String canonicalName = th.getClass().getCanonicalName();
        if (canonicalName == null) {
            canonicalName = th.getClass().getSimpleName();
        }
        return "Application crash detected: " + canonicalName;
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/error/internal/DatadogExceptionHandler$Companion;", "", "()V", "EXECUTOR_NOT_IDLED_WARNING_MESSAGE", "", "LOGGER_NAME", "MAX_WAIT_FOR_IDLE_TIME_IN_MS", "", "MESSAGE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DatadogExceptionHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
