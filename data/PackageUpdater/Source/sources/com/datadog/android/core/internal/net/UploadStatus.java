package com.datadog.android.core.internal.net;

import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J:\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019¨\u0006\u001a"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/UploadStatus;", "", "shouldRetry", "", "(Ljava/lang/String;IZ)V", "getShouldRetry", "()Z", "logStatus", "", "context", "", "byteSize", "", "logger", "Lcom/datadog/android/log/Logger;", "ignoreInfo", "sendToTelemetry", "requestId", "SUCCESS", "NETWORK_ERROR", "INVALID_TOKEN_ERROR", "HTTP_REDIRECTION", "HTTP_CLIENT_ERROR", "HTTP_SERVER_ERROR", "HTTP_CLIENT_RATE_LIMITING", "UNKNOWN_ERROR", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UploadStatus.kt */
public enum UploadStatus {
    SUCCESS(false),
    NETWORK_ERROR(true),
    INVALID_TOKEN_ERROR(false),
    HTTP_REDIRECTION(false),
    HTTP_CLIENT_ERROR(false),
    HTTP_SERVER_ERROR(true),
    HTTP_CLIENT_RATE_LIMITING(true),
    UNKNOWN_ERROR(false);
    
    private final boolean shouldRetry;

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: UploadStatus.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] iArr = new int[UploadStatus.values().length];
            iArr[UploadStatus.NETWORK_ERROR.ordinal()] = 1;
            iArr[UploadStatus.INVALID_TOKEN_ERROR.ordinal()] = 2;
            iArr[UploadStatus.HTTP_REDIRECTION.ordinal()] = 3;
            iArr[UploadStatus.HTTP_CLIENT_ERROR.ordinal()] = 4;
            iArr[UploadStatus.HTTP_CLIENT_RATE_LIMITING.ordinal()] = 5;
            iArr[UploadStatus.HTTP_SERVER_ERROR.ordinal()] = 6;
            iArr[UploadStatus.UNKNOWN_ERROR.ordinal()] = 7;
            iArr[UploadStatus.SUCCESS.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private UploadStatus(boolean z) {
        this.shouldRetry = z;
    }

    public final boolean getShouldRetry() {
        return this.shouldRetry;
    }

    public final void logStatus(String str, int i, Logger logger, boolean z, boolean z2, String str2) {
        String str3;
        Intrinsics.checkNotNullParameter(str, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(logger, "logger");
        if (str2 == null) {
            str3 = "Batch [" + i + " bytes] (" + str + ")";
        } else {
            str3 = "Batch " + str2 + " [" + i + " bytes] (" + str + ")";
        }
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                Logger.e$default(logger, str3 + " failed because of a network error; we will retry later.", (Throwable) null, (Map) null, 6, (Object) null);
                return;
            case 2:
                if (!z) {
                    Logger.e$default(logger, str3 + " failed because your token is invalid. Make sure that the provided token still exists and you're targeting the relevant Datadog site.", (Throwable) null, (Map) null, 6, (Object) null);
                    return;
                }
                return;
            case 3:
                Logger.w$default(logger, str3 + " failed because of a network redirection; the batch was dropped.", (Throwable) null, (Map) null, 6, (Object) null);
                return;
            case 4:
                String str4 = str3 + " failed because of a processing error or invalid data; the batch was dropped.";
                if (z2) {
                    LogUtilsKt.errorWithTelemetry$default(logger, str4, (Throwable) null, (Map) null, 6, (Object) null);
                    return;
                } else {
                    Logger.e$default(logger, str4, (Throwable) null, (Map) null, 6, (Object) null);
                    return;
                }
            case 5:
                String str5 = str3 + " failed because of a request error; we will retry later.";
                if (z2) {
                    LogUtilsKt.errorWithTelemetry$default(logger, str5, (Throwable) null, (Map) null, 6, (Object) null);
                    return;
                } else {
                    Logger.e$default(logger, str5, (Throwable) null, (Map) null, 6, (Object) null);
                    return;
                }
            case 6:
                Logger.e$default(logger, str3 + " failed because of a server processing error; we will retry later.", (Throwable) null, (Map) null, 6, (Object) null);
                return;
            case 7:
                Logger.e$default(logger, str3 + " failed because of an unknown error; the batch was dropped.", (Throwable) null, (Map) null, 6, (Object) null);
                return;
            case 8:
                if (!z) {
                    Logger.v$default(logger, str3 + " sent successfully.", (Throwable) null, (Map) null, 6, (Object) null);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
