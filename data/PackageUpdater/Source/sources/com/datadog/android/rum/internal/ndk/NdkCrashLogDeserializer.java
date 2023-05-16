package com.datadog.android.rum.internal.ndk;

import com.datadog.android.core.internal.persistence.Deserializer;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.google.gson.JsonParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/ndk/NdkCrashLogDeserializer;", "Lcom/datadog/android/core/internal/persistence/Deserializer;", "Lcom/datadog/android/rum/internal/ndk/NdkCrashLog;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Lcom/datadog/android/log/Logger;)V", "deserialize", "model", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NdkCrashLogDeserializer.kt */
public final class NdkCrashLogDeserializer implements Deserializer<NdkCrashLog> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DESERIALIZE_ERROR_MESSAGE_FORMAT = "Error while trying to deserialize the serialized NDK Crash info: %s";
    private final Logger internalLogger;

    public NdkCrashLogDeserializer(Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        this.internalLogger = logger;
    }

    public NdkCrashLog deserialize(String str) {
        Intrinsics.checkNotNullParameter(str, "model");
        try {
            return NdkCrashLog.Companion.fromJson$dd_sdk_android_release(str);
        } catch (JsonParseException e) {
            Logger logger = this.internalLogger;
            String format = String.format(Locale.US, DESERIALIZE_ERROR_MESSAGE_FORMAT, Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger, format, e, (Map) null, 4, (Object) null);
            return null;
        } catch (IllegalStateException e2) {
            Logger logger2 = this.internalLogger;
            String format2 = String.format(Locale.US, DESERIALIZE_ERROR_MESSAGE_FORMAT, Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger2, format2, e2, (Map) null, 4, (Object) null);
            return null;
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/ndk/NdkCrashLogDeserializer$Companion;", "", "()V", "DESERIALIZE_ERROR_MESSAGE_FORMAT", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: NdkCrashLogDeserializer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
