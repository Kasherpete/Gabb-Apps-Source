package com.datadog.android.core.internal.persistence;

import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(mo20734d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010\b¨\u0006\t"}, mo20735d2 = {"serializeToByteArray", "", "T", "", "Lcom/datadog/android/core/internal/persistence/Serializer;", "model", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Lcom/datadog/android/core/internal/persistence/Serializer;Ljava/lang/Object;Lcom/datadog/android/log/Logger;)[B", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Serializer.kt */
public final class SerializerKt {
    public static final <T> byte[] serializeToByteArray(Serializer<T> serializer, T t, Logger logger) {
        Intrinsics.checkNotNullParameter(serializer, "<this>");
        Intrinsics.checkNotNullParameter(t, "model");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        try {
            String serialize = serializer.serialize(t);
            if (serialize == null) {
                return null;
            }
            byte[] bytes = serialize.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            return bytes;
        } catch (Throwable th) {
            Throwable th2 = th;
            String format = String.format(Locale.US, "Error serializing %s model", Arrays.copyOf(new Object[]{t.getClass().getSimpleName()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger, format, th2, (Map) null, 4, (Object) null);
            return null;
        }
    }
}
