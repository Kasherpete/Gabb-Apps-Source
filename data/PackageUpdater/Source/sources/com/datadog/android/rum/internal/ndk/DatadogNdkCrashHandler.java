package com.datadog.android.rum.internal.ndk;

import android.content.Context;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.persistence.Deserializer;
import com.datadog.android.core.internal.persistence.file.FileExtKt;
import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.core.internal.utils.ByteArrayExtKt;
import com.datadog.android.core.model.NetworkInfo;
import com.datadog.android.core.model.UserInfo;
import com.datadog.android.log.LogAttributes;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.domain.LogGenerator;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.log.model.LogEvent;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.rum.internal.domain.event.RumEventSourceProvider;
import com.datadog.android.rum.model.ErrorEvent;
import com.datadog.android.rum.model.ViewEvent;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p007io.FilesKt;
import kotlin.text.Charsets;

@Metadata(mo20734d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0004\b\u0000\u0018\u0000 I2\u00020\u0001:\u0001IBw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\t\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018¢\u0006\u0002\u0010\u0019J$\u0010-\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002\f\u00102\u001a\b\u0012\u0004\u0012\u00020\f00H\u0002J\b\u00103\u001a\u00020.H\u0002J\b\u00104\u001a\u00020.H\u0002J$\u00105\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002\f\u00102\u001a\b\u0012\u0004\u0012\u00020\f00H\u0016JL\u00106\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002\f\u00102\u001a\b\u0012\u0004\u0012\u00020\f002\b\u00107\u001a\u0004\u0018\u00010\n2\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00102\b\u0010;\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010<\u001a\u00020.H\u0016J\b\u0010=\u001a\u00020.H\u0002J\u001a\u0010>\u001a\u0004\u0018\u00010\u001b2\u0006\u0010?\u001a\u00020,2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J \u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\n2\u0006\u0010C\u001a\u000209H\u0002JN\u0010D\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002\u0006\u0010B\u001a\u00020\u001b2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0F2\u0006\u00107\u001a\u00020\n2\b\u0010;\u001a\u0004\u0018\u00010\u000e2\b\u0010:\u001a\u0004\u0018\u00010\u0010H\u0002J\u0010\u0010G\u001a\u0002092\u0006\u00108\u001a\u000209H\u0002J.\u0010H\u001a\u00020.2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\f002\u0006\u0010B\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\n2\u0006\u00108\u001a\u000209H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR\u001c\u0010#\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR\u001c\u0010&\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001d\"\u0004\b(\u0010\u001fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\tX\u0004¢\u0006\u0002\n\u0000¨\u0006J"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/ndk/DatadogNdkCrashHandler;", "Lcom/datadog/android/rum/internal/ndk/NdkCrashHandler;", "appContext", "Landroid/content/Context;", "dataPersistenceExecutorService", "Ljava/util/concurrent/ExecutorService;", "logGenerator", "Lcom/datadog/android/log/internal/domain/LogGenerator;", "ndkCrashLogDeserializer", "Lcom/datadog/android/core/internal/persistence/Deserializer;", "Lcom/datadog/android/rum/internal/ndk/NdkCrashLog;", "rumEventDeserializer", "", "networkInfoDeserializer", "Lcom/datadog/android/core/model/NetworkInfo;", "userInfoDeserializer", "Lcom/datadog/android/core/model/UserInfo;", "internalLogger", "Lcom/datadog/android/log/Logger;", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "fileHandler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "rumEventSourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "(Landroid/content/Context;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/log/internal/domain/LogGenerator;Lcom/datadog/android/core/internal/persistence/Deserializer;Lcom/datadog/android/core/internal/persistence/Deserializer;Lcom/datadog/android/core/internal/persistence/Deserializer;Lcom/datadog/android/core/internal/persistence/Deserializer;Lcom/datadog/android/log/Logger;Lcom/datadog/android/core/internal/time/TimeProvider;Lcom/datadog/android/core/internal/persistence/file/FileHandler;Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;)V", "lastSerializedNdkCrashLog", "", "getLastSerializedNdkCrashLog$dd_sdk_android_release", "()Ljava/lang/String;", "setLastSerializedNdkCrashLog$dd_sdk_android_release", "(Ljava/lang/String;)V", "lastSerializedNetworkInformation", "getLastSerializedNetworkInformation$dd_sdk_android_release", "setLastSerializedNetworkInformation$dd_sdk_android_release", "lastSerializedRumViewEvent", "getLastSerializedRumViewEvent$dd_sdk_android_release", "setLastSerializedRumViewEvent$dd_sdk_android_release", "lastSerializedUserInformation", "getLastSerializedUserInformation$dd_sdk_android_release", "setLastSerializedUserInformation$dd_sdk_android_release", "getLogGenerator$dd_sdk_android_release", "()Lcom/datadog/android/log/internal/domain/LogGenerator;", "ndkCrashDataDirectory", "Ljava/io/File;", "checkAndHandleNdkCrashReport", "", "logWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "Lcom/datadog/android/log/model/LogEvent;", "rumWriter", "clearAllReferences", "clearCrashLog", "handleNdkCrash", "handleNdkCrashLog", "ndkCrashLog", "lastViewEvent", "Lcom/datadog/android/rum/model/ViewEvent;", "lastUserInfo", "lastNetworkInfo", "prepareData", "readCrashData", "readFileContent", "file", "resolveErrorEventFromViewEvent", "Lcom/datadog/android/rum/model/ErrorEvent;", "errorLogMessage", "viewEvent", "sendCrashLogEvent", "logAttributes", "", "updateViewEvent", "updateViewEventAndSendError", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogNdkCrashHandler.kt */
public final class DatadogNdkCrashHandler implements NdkCrashHandler {
    public static final String CRASH_DATA_FILE_NAME = "crash_log";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ERROR_READ_NDK_DIR = "Error while trying to read the NDK crash directory";
    public static final String ERROR_TASK_REJECTED = "Unable to schedule operation on the executor";
    public static final String LOGGER_NAME = "ndk_crash";
    public static final String LOG_CRASH_MSG = "NDK crash detected with signal: %s";
    public static final String NDK_CRASH_REPORTS_FOLDER_NAME = "ndk_crash_reports_v2";
    private static final String NDK_CRASH_REPORTS_PENDING_FOLDER_NAME = "ndk_crash_reports_intermediary_v2";
    public static final String NETWORK_INFO_FILE_NAME = "network_information";
    public static final String RUM_VIEW_EVENT_FILE_NAME = "last_view_event";
    private static final int STORAGE_VERSION = 2;
    public static final String USER_INFO_FILE_NAME = "user_information";
    /* access modifiers changed from: private */
    public static final long VIEW_EVENT_AVAILABILITY_TIME_THRESHOLD = TimeUnit.HOURS.toMillis(4);
    private final ExecutorService dataPersistenceExecutorService;
    private final FileHandler fileHandler;
    private final Logger internalLogger;
    private String lastSerializedNdkCrashLog;
    private String lastSerializedNetworkInformation;
    private String lastSerializedRumViewEvent;
    private String lastSerializedUserInformation;
    private final LogGenerator logGenerator;
    private final File ndkCrashDataDirectory;
    private final Deserializer<NdkCrashLog> ndkCrashLogDeserializer;
    private final Deserializer<NetworkInfo> networkInfoDeserializer;
    private final Deserializer<Object> rumEventDeserializer;
    private final RumEventSourceProvider rumEventSourceProvider;
    private final TimeProvider timeProvider;
    private final Deserializer<UserInfo> userInfoDeserializer;

    public DatadogNdkCrashHandler(Context context, ExecutorService executorService, LogGenerator logGenerator2, Deserializer<NdkCrashLog> deserializer, Deserializer<Object> deserializer2, Deserializer<NetworkInfo> deserializer3, Deserializer<UserInfo> deserializer4, Logger logger, TimeProvider timeProvider2, FileHandler fileHandler2, RumEventSourceProvider rumEventSourceProvider2) {
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(executorService, "dataPersistenceExecutorService");
        Intrinsics.checkNotNullParameter(logGenerator2, "logGenerator");
        Intrinsics.checkNotNullParameter(deserializer, "ndkCrashLogDeserializer");
        Intrinsics.checkNotNullParameter(deserializer2, "rumEventDeserializer");
        Intrinsics.checkNotNullParameter(deserializer3, "networkInfoDeserializer");
        Intrinsics.checkNotNullParameter(deserializer4, "userInfoDeserializer");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        Intrinsics.checkNotNullParameter(timeProvider2, "timeProvider");
        Intrinsics.checkNotNullParameter(fileHandler2, "fileHandler");
        Intrinsics.checkNotNullParameter(rumEventSourceProvider2, "rumEventSourceProvider");
        this.dataPersistenceExecutorService = executorService;
        this.logGenerator = logGenerator2;
        this.ndkCrashLogDeserializer = deserializer;
        this.rumEventDeserializer = deserializer2;
        this.networkInfoDeserializer = deserializer3;
        this.userInfoDeserializer = deserializer4;
        this.internalLogger = logger;
        this.timeProvider = timeProvider2;
        this.fileHandler = fileHandler2;
        this.rumEventSourceProvider = rumEventSourceProvider2;
        this.ndkCrashDataDirectory = Companion.getNdkGrantedDir$dd_sdk_android_release(context);
    }

    public final LogGenerator getLogGenerator$dd_sdk_android_release() {
        return this.logGenerator;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DatadogNdkCrashHandler(Context context, ExecutorService executorService, LogGenerator logGenerator2, Deserializer deserializer, Deserializer deserializer2, Deserializer deserializer3, Deserializer deserializer4, Logger logger, TimeProvider timeProvider2, FileHandler fileHandler2, RumEventSourceProvider rumEventSourceProvider2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, executorService, logGenerator2, deserializer, deserializer2, deserializer3, deserializer4, logger, timeProvider2, fileHandler2, (i & 1024) != 0 ? new RumEventSourceProvider(CoreFeature.INSTANCE.getSourceName$dd_sdk_android_release()) : rumEventSourceProvider2);
    }

    public final String getLastSerializedRumViewEvent$dd_sdk_android_release() {
        return this.lastSerializedRumViewEvent;
    }

    public final void setLastSerializedRumViewEvent$dd_sdk_android_release(String str) {
        this.lastSerializedRumViewEvent = str;
    }

    public final String getLastSerializedUserInformation$dd_sdk_android_release() {
        return this.lastSerializedUserInformation;
    }

    public final void setLastSerializedUserInformation$dd_sdk_android_release(String str) {
        this.lastSerializedUserInformation = str;
    }

    public final String getLastSerializedNdkCrashLog$dd_sdk_android_release() {
        return this.lastSerializedNdkCrashLog;
    }

    public final void setLastSerializedNdkCrashLog$dd_sdk_android_release(String str) {
        this.lastSerializedNdkCrashLog = str;
    }

    public final String getLastSerializedNetworkInformation$dd_sdk_android_release() {
        return this.lastSerializedNetworkInformation;
    }

    public final void setLastSerializedNetworkInformation$dd_sdk_android_release(String str) {
        this.lastSerializedNetworkInformation = str;
    }

    public void prepareData() {
        try {
            this.dataPersistenceExecutorService.submit(new DatadogNdkCrashHandler$$ExternalSyntheticLambda0(this));
        } catch (RejectedExecutionException e) {
            LogUtilsKt.errorWithTelemetry$default(this.internalLogger, ERROR_TASK_REJECTED, e, (Map) null, 4, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: prepareData$lambda-0  reason: not valid java name */
    public static final void m152prepareData$lambda0(DatadogNdkCrashHandler datadogNdkCrashHandler) {
        Intrinsics.checkNotNullParameter(datadogNdkCrashHandler, "this$0");
        datadogNdkCrashHandler.readCrashData();
    }

    public void handleNdkCrash(DataWriter<LogEvent> dataWriter, DataWriter<Object> dataWriter2) {
        Intrinsics.checkNotNullParameter(dataWriter, "logWriter");
        Intrinsics.checkNotNullParameter(dataWriter2, "rumWriter");
        try {
            this.dataPersistenceExecutorService.submit(new DatadogNdkCrashHandler$$ExternalSyntheticLambda1(this, dataWriter, dataWriter2));
        } catch (RejectedExecutionException e) {
            LogUtilsKt.errorWithTelemetry$default(this.internalLogger, ERROR_TASK_REJECTED, e, (Map) null, 4, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: handleNdkCrash$lambda-1  reason: not valid java name */
    public static final void m151handleNdkCrash$lambda1(DatadogNdkCrashHandler datadogNdkCrashHandler, DataWriter dataWriter, DataWriter dataWriter2) {
        Intrinsics.checkNotNullParameter(datadogNdkCrashHandler, "this$0");
        Intrinsics.checkNotNullParameter(dataWriter, "$logWriter");
        Intrinsics.checkNotNullParameter(dataWriter2, "$rumWriter");
        datadogNdkCrashHandler.checkAndHandleNdkCrashReport(dataWriter, dataWriter2);
    }

    private final void readCrashData() {
        if (FileExtKt.existsSafe(this.ndkCrashDataDirectory)) {
            try {
                File[] listFilesSafe = FileExtKt.listFilesSafe(this.ndkCrashDataDirectory);
                if (listFilesSafe != null) {
                    int i = 0;
                    int length = listFilesSafe.length;
                    while (i < length) {
                        File file = listFilesSafe[i];
                        i++;
                        String name = file.getName();
                        if (name != null) {
                            switch (name.hashCode()) {
                                case -528983909:
                                    if (name.equals(NETWORK_INFO_FILE_NAME)) {
                                        setLastSerializedNetworkInformation$dd_sdk_android_release(readFileContent(file, this.fileHandler));
                                        break;
                                    } else {
                                        break;
                                    }
                                case 75377097:
                                    if (name.equals(RUM_VIEW_EVENT_FILE_NAME)) {
                                        setLastSerializedRumViewEvent$dd_sdk_android_release(readFileContent(file, this.fileHandler));
                                        break;
                                    } else {
                                        break;
                                    }
                                case 408381112:
                                    if (name.equals(USER_INFO_FILE_NAME)) {
                                        setLastSerializedUserInformation$dd_sdk_android_release(readFileContent(file, this.fileHandler));
                                        break;
                                    } else {
                                        break;
                                    }
                                case 1847397036:
                                    if (name.equals(CRASH_DATA_FILE_NAME)) {
                                        setLastSerializedNdkCrashLog$dd_sdk_android_release(FileExtKt.readTextSafe$default(file, (Charset) null, 1, (Object) null));
                                        break;
                                    } else {
                                        break;
                                    }
                            }
                        }
                    }
                }
            } catch (SecurityException e) {
                LogUtilsKt.errorWithTelemetry$default(this.internalLogger, ERROR_READ_NDK_DIR, e, (Map) null, 4, (Object) null);
            } catch (Throwable th) {
                clearCrashLog();
                throw th;
            }
            clearCrashLog();
        }
    }

    private final String readFileContent(File file, FileHandler fileHandler2) {
        List<byte[]> readData = fileHandler2.readData(file);
        if (readData.isEmpty()) {
            return null;
        }
        return new String(ByteArrayExtKt.join$default(readData, new byte[0], (byte[]) null, (byte[]) null, 6, (Object) null), Charsets.UTF_8);
    }

    private final void checkAndHandleNdkCrashReport(DataWriter<LogEvent> dataWriter, DataWriter<Object> dataWriter2) {
        ViewEvent viewEvent;
        UserInfo userInfo;
        NetworkInfo networkInfo;
        String str = this.lastSerializedRumViewEvent;
        String str2 = this.lastSerializedUserInformation;
        String str3 = this.lastSerializedNdkCrashLog;
        String str4 = this.lastSerializedNetworkInformation;
        if (str3 != null) {
            NdkCrashLog deserialize = this.ndkCrashLogDeserializer.deserialize(str3);
            if (str == null) {
                viewEvent = null;
            } else {
                Object deserialize2 = this.rumEventDeserializer.deserialize(str);
                viewEvent = deserialize2 instanceof ViewEvent ? (ViewEvent) deserialize2 : null;
            }
            if (str2 == null) {
                userInfo = null;
            } else {
                userInfo = this.userInfoDeserializer.deserialize(str2);
            }
            if (str4 == null) {
                networkInfo = null;
            } else {
                networkInfo = this.networkInfoDeserializer.deserialize(str4);
            }
            handleNdkCrashLog(dataWriter, dataWriter2, deserialize, viewEvent, userInfo, networkInfo);
        }
        clearAllReferences();
    }

    private final void clearAllReferences() {
        this.lastSerializedNdkCrashLog = null;
        this.lastSerializedNetworkInformation = null;
        this.lastSerializedRumViewEvent = null;
        this.lastSerializedUserInformation = null;
    }

    private final void handleNdkCrashLog(DataWriter<LogEvent> dataWriter, DataWriter<Object> dataWriter2, NdkCrashLog ndkCrashLog, ViewEvent viewEvent, UserInfo userInfo, NetworkInfo networkInfo) {
        Map map;
        if (ndkCrashLog != null) {
            String format = String.format(Locale.US, LOG_CRASH_MSG, Arrays.copyOf(new Object[]{ndkCrashLog.getSignalName()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            if (viewEvent != null) {
                Map mapOf = MapsKt.mapOf(TuplesKt.m78to(LogAttributes.RUM_SESSION_ID, viewEvent.getSession().getId()), TuplesKt.m78to(LogAttributes.RUM_APPLICATION_ID, viewEvent.getApplication().getId()), TuplesKt.m78to(LogAttributes.RUM_VIEW_ID, viewEvent.getView().getId()), TuplesKt.m78to("error.stack", ndkCrashLog.getStacktrace()));
                updateViewEventAndSendError(dataWriter2, format, ndkCrashLog, viewEvent);
                map = mapOf;
            } else {
                map = MapsKt.mapOf(TuplesKt.m78to("error.stack", ndkCrashLog.getStacktrace()));
            }
            sendCrashLogEvent(dataWriter, format, map, ndkCrashLog, networkInfo, userInfo);
        }
    }

    private final void updateViewEventAndSendError(DataWriter<Object> dataWriter, String str, NdkCrashLog ndkCrashLog, ViewEvent viewEvent) {
        dataWriter.write(resolveErrorEventFromViewEvent(str, ndkCrashLog, viewEvent));
        if (System.currentTimeMillis() - viewEvent.getDate() < VIEW_EVENT_AVAILABILITY_TIME_THRESHOLD) {
            dataWriter.write(updateViewEvent(viewEvent));
        }
    }

    private final void sendCrashLogEvent(DataWriter<LogEvent> dataWriter, String str, Map<String, String> map, NdkCrashLog ndkCrashLog, NetworkInfo networkInfo, UserInfo userInfo) {
        DataWriter<LogEvent> dataWriter2 = dataWriter;
        dataWriter2.write(LogGenerator.generateLog$default(this.logGenerator, 9, str, (Throwable) null, map, SetsKt.emptySet(), ndkCrashLog.getTimestamp(), (String) null, false, false, userInfo, networkInfo, 64, (Object) null));
    }

    private final ViewEvent updateViewEvent(ViewEvent viewEvent) {
        ViewEvent.Crash crash;
        ViewEvent.Crash crash2 = viewEvent.getView().getCrash();
        if (crash2 == null) {
            crash = null;
        } else {
            crash = crash2.copy(crash2.getCount() + 1);
        }
        if (crash == null) {
            crash = new ViewEvent.Crash(1);
        }
        return ViewEvent.copy$default(viewEvent, 0, (ViewEvent.Application) null, (String) null, (ViewEvent.ViewEventSession) null, (ViewEvent.Source) null, ViewEvent.View.copy$default(viewEvent.getView(), (String) null, (String) null, (String) null, (String) null, (Long) null, (ViewEvent.LoadingType) null, 0, (Long) null, (Long) null, (Long) null, (Long) null, (Number) null, (Long) null, (Long) null, (Long) null, (Long) null, (ViewEvent.CustomTimings) null, false, (Boolean) null, (ViewEvent.Action) null, (ViewEvent.Error) null, crash, (ViewEvent.LongTask) null, (ViewEvent.FrozenFrame) null, (ViewEvent.Resource) null, (List) null, (Number) null, (Number) null, (Number) null, (Number) null, (Number) null, (Number) null, -2228225, (Object) null), (ViewEvent.Usr) null, (ViewEvent.Connectivity) null, (ViewEvent.Synthetics) null, (ViewEvent.CiTest) null, ViewEvent.C0867Dd.copy$default(viewEvent.getDd(), (ViewEvent.DdSession) null, (String) null, viewEvent.getDd().getDocumentVersion() + 1, 3, (Object) null), (ViewEvent.Context) null, 3039, (Object) null);
    }

    private final ErrorEvent resolveErrorEventFromViewEvent(String str, NdkCrashLog ndkCrashLog, ViewEvent viewEvent) {
        ErrorEvent.Connectivity connectivity;
        ViewEvent.Connectivity connectivity2 = viewEvent.getConnectivity();
        if (connectivity2 == null) {
            connectivity = null;
        } else {
            ErrorEvent.Status valueOf = ErrorEvent.Status.valueOf(connectivity2.getStatus().name());
            Iterable<ViewEvent.Interface> interfaces = connectivity2.getInterfaces();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(interfaces, 10));
            for (ViewEvent.Interface name : interfaces) {
                arrayList.add(ErrorEvent.Interface.valueOf(name.name()));
            }
            List list = (List) arrayList;
            ViewEvent.Cellular cellular = connectivity2.getCellular();
            String technology = cellular == null ? null : cellular.getTechnology();
            ViewEvent.Cellular cellular2 = connectivity2.getCellular();
            connectivity = new ErrorEvent.Connectivity(valueOf, list, new ErrorEvent.Cellular(technology, cellular2 == null ? null : cellular2.getCarrierName()));
        }
        ViewEvent.Context context = viewEvent.getContext();
        Map<String, Object> additionalProperties = context == null ? null : context.getAdditionalProperties();
        if (additionalProperties == null) {
            additionalProperties = MapsKt.emptyMap();
        }
        ViewEvent.Usr usr = viewEvent.getUsr();
        Map<String, Object> additionalProperties2 = usr == null ? null : usr.getAdditionalProperties();
        if (additionalProperties2 == null) {
            additionalProperties2 = MapsKt.emptyMap();
        }
        long serverOffsetMillis = this.timeProvider.getServerOffsetMillis() + ndkCrashLog.getTimestamp();
        ErrorEvent.Application application = new ErrorEvent.Application(viewEvent.getApplication().getId());
        String service = viewEvent.getService();
        ErrorEvent.ErrorEventSession errorEventSession = new ErrorEvent.ErrorEventSession(viewEvent.getSession().getId(), ErrorEvent.ErrorEventSessionType.USER, (Boolean) null, 4, (DefaultConstructorMarker) null);
        ErrorEvent.ErrorEventSource errorEventSource = this.rumEventSourceProvider.getErrorEventSource();
        ErrorEvent.View view = new ErrorEvent.View(viewEvent.getView().getId(), viewEvent.getView().getReferrer(), viewEvent.getView().getUrl(), viewEvent.getView().getName(), (Boolean) null, 16, (DefaultConstructorMarker) null);
        ViewEvent.Usr usr2 = viewEvent.getUsr();
        String id = usr2 == null ? null : usr2.getId();
        ViewEvent.Usr usr3 = viewEvent.getUsr();
        String name2 = usr3 == null ? null : usr3.getName();
        ViewEvent.Usr usr4 = viewEvent.getUsr();
        ErrorEvent.Usr usr5 = new ErrorEvent.Usr(id, name2, usr4 == null ? null : usr4.getEmail(), additionalProperties2);
        ErrorEvent.C0864Dd dd = r0;
        ErrorEvent.C0864Dd dd2 = new ErrorEvent.C0864Dd(new ErrorEvent.DdSession(ErrorEvent.Plan.PLAN_1), (String) null, 2, (DefaultConstructorMarker) null);
        ErrorEvent.Context context2 = r0;
        ErrorEvent.Context context3 = new ErrorEvent.Context(additionalProperties);
        ErrorEvent.Error error = r21;
        ErrorEvent.Error error2 = new ErrorEvent.Error((String) null, str, ErrorEvent.ErrorSource.SOURCE, ndkCrashLog.getStacktrace(), true, ndkCrashLog.getSignalName(), (ErrorEvent.Handling) null, (String) null, ErrorEvent.SourceType.ANDROID, (ErrorEvent.Resource) null, 705, (DefaultConstructorMarker) null);
        return new ErrorEvent(serverOffsetMillis, application, service, errorEventSession, errorEventSource, view, usr5, connectivity, (ErrorEvent.Synthetics) null, (ErrorEvent.CiTest) null, dd, context2, error, (ErrorEvent.Action) null, 8960, (DefaultConstructorMarker) null);
    }

    private final void clearCrashLog() {
        if (FileExtKt.existsSafe(this.ndkCrashDataDirectory)) {
            try {
                File[] listFilesSafe = FileExtKt.listFilesSafe(this.ndkCrashDataDirectory);
                if (listFilesSafe != null) {
                    int i = 0;
                    int length = listFilesSafe.length;
                    while (i < length) {
                        File file = listFilesSafe[i];
                        i++;
                        FilesKt.deleteRecursively(file);
                    }
                }
            } catch (Throwable th) {
                Logger logger = this.internalLogger;
                LogUtilsKt.errorWithTelemetry$default(logger, "Unable to clear the NDK crash report file: " + this.ndkCrashDataDirectory.getAbsolutePath(), th, (Map) null, 4, (Object) null);
            }
        }
    }

    @Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u001aJ\u0015\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u001cJ\u0015\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u001eJ\u0015\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b J\u0015\u0010!\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\"J\u0015\u0010#\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b$R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006%"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/ndk/DatadogNdkCrashHandler$Companion;", "", "()V", "CRASH_DATA_FILE_NAME", "", "ERROR_READ_NDK_DIR", "ERROR_TASK_REJECTED", "LOGGER_NAME", "LOG_CRASH_MSG", "NDK_CRASH_REPORTS_FOLDER_NAME", "NDK_CRASH_REPORTS_PENDING_FOLDER_NAME", "NETWORK_INFO_FILE_NAME", "RUM_VIEW_EVENT_FILE_NAME", "STORAGE_VERSION", "", "USER_INFO_FILE_NAME", "VIEW_EVENT_AVAILABILITY_TIME_THRESHOLD", "", "getVIEW_EVENT_AVAILABILITY_TIME_THRESHOLD$dd_sdk_android_release", "()J", "getGrantedNetworkInfoFile", "Ljava/io/File;", "context", "Landroid/content/Context;", "getGrantedNetworkInfoFile$dd_sdk_android_release", "getGrantedUserInfoFile", "getGrantedUserInfoFile$dd_sdk_android_release", "getLastViewEventFile", "getLastViewEventFile$dd_sdk_android_release", "getNdkGrantedDir", "getNdkGrantedDir$dd_sdk_android_release", "getNdkPendingDir", "getNdkPendingDir$dd_sdk_android_release", "getPendingNetworkInfoFile", "getPendingNetworkInfoFile$dd_sdk_android_release", "getPendingUserInfoFile", "getPendingUserInfoFile$dd_sdk_android_release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DatadogNdkCrashHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long getVIEW_EVENT_AVAILABILITY_TIME_THRESHOLD$dd_sdk_android_release() {
            return DatadogNdkCrashHandler.VIEW_EVENT_AVAILABILITY_TIME_THRESHOLD;
        }

        public final File getNdkGrantedDir$dd_sdk_android_release(Context context) {
            Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
            return new File(context.getCacheDir(), DatadogNdkCrashHandler.NDK_CRASH_REPORTS_FOLDER_NAME);
        }

        public final File getNdkPendingDir$dd_sdk_android_release(Context context) {
            Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
            return new File(context.getCacheDir(), DatadogNdkCrashHandler.NDK_CRASH_REPORTS_PENDING_FOLDER_NAME);
        }

        public final File getLastViewEventFile$dd_sdk_android_release(Context context) {
            Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
            return new File(getNdkGrantedDir$dd_sdk_android_release(context), DatadogNdkCrashHandler.RUM_VIEW_EVENT_FILE_NAME);
        }

        public final File getPendingNetworkInfoFile$dd_sdk_android_release(Context context) {
            Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
            return new File(getNdkPendingDir$dd_sdk_android_release(context), DatadogNdkCrashHandler.NETWORK_INFO_FILE_NAME);
        }

        public final File getGrantedNetworkInfoFile$dd_sdk_android_release(Context context) {
            Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
            return new File(getNdkGrantedDir$dd_sdk_android_release(context), DatadogNdkCrashHandler.NETWORK_INFO_FILE_NAME);
        }

        public final File getPendingUserInfoFile$dd_sdk_android_release(Context context) {
            Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
            return new File(getNdkPendingDir$dd_sdk_android_release(context), DatadogNdkCrashHandler.USER_INFO_FILE_NAME);
        }

        public final File getGrantedUserInfoFile$dd_sdk_android_release(Context context) {
            Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
            return new File(getNdkGrantedDir$dd_sdk_android_release(context), DatadogNdkCrashHandler.USER_INFO_FILE_NAME);
        }
    }
}
