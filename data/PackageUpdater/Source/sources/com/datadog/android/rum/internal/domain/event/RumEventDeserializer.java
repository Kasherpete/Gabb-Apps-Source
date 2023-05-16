package com.datadog.android.rum.internal.domain.event;

import com.datadog.android.core.internal.persistence.Deserializer;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.model.ActionEvent;
import com.datadog.android.rum.model.ErrorEvent;
import com.datadog.android.rum.model.LongTaskEvent;
import com.datadog.android.rum.model.ResourceEvent;
import com.datadog.android.rum.model.ViewEvent;
import com.datadog.android.telemetry.model.TelemetryDebugEvent;
import com.datadog.android.telemetry.model.TelemetryErrorEvent;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u0007\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/event/RumEventDeserializer;", "Lcom/datadog/android/core/internal/persistence/Deserializer;", "", "()V", "deserialize", "model", "", "parseEvent", "eventType", "modelAsJson", "Lcom/google/gson/JsonObject;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumEventDeserializer.kt */
public final class RumEventDeserializer implements Deserializer<Object> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DESERIALIZE_ERROR_MESSAGE_FORMAT = "Error while trying to deserialize the serialized RumEvent: %s";
    public static final String EVENT_TELEMETRY_KEY_NAME = "telemetry";
    public static final String EVENT_TELEMETRY_STATUS_KEY_NAME = "status";
    public static final String EVENT_TYPE_ACTION = "action";
    public static final String EVENT_TYPE_ERROR = "error";
    public static final String EVENT_TYPE_KEY_NAME = "type";
    public static final String EVENT_TYPE_LONG_TASK = "long_task";
    public static final String EVENT_TYPE_RESOURCE = "resource";
    public static final String EVENT_TYPE_TELEMETRY = "telemetry";
    public static final String EVENT_TYPE_VIEW = "view";
    public static final String TELEMETRY_TYPE_DEBUG = "debug";
    public static final String TELEMETRY_TYPE_ERROR = "error";

    public Object deserialize(String str) {
        Intrinsics.checkNotNullParameter(str, "model");
        try {
            JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
            JsonPrimitive asJsonPrimitive = asJsonObject.getAsJsonPrimitive(EVENT_TYPE_KEY_NAME);
            String asString = asJsonPrimitive == null ? null : asJsonPrimitive.getAsString();
            Intrinsics.checkNotNullExpressionValue(asJsonObject, "jsonObject");
            return parseEvent(asString, str, asJsonObject);
        } catch (JsonParseException e) {
            Logger sdkLogger = RuntimeUtilsKt.getSdkLogger();
            String format = String.format(Locale.US, DESERIALIZE_ERROR_MESSAGE_FORMAT, Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(sdkLogger, format, e, (Map) null, 4, (Object) null);
            return null;
        } catch (IllegalStateException e2) {
            Logger sdkLogger2 = RuntimeUtilsKt.getSdkLogger();
            String format2 = String.format(Locale.US, DESERIALIZE_ERROR_MESSAGE_FORMAT, Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(sdkLogger2, format2, e2, (Map) null, 4, (Object) null);
            return null;
        }
    }

    private final Object parseEvent(String str, String str2, JsonObject jsonObject) throws JsonParseException {
        if (str != null) {
            switch (str.hashCode()) {
                case -1422950858:
                    if (str.equals("action")) {
                        return ActionEvent.Companion.fromJson(str2);
                    }
                    break;
                case -341064690:
                    if (str.equals("resource")) {
                        return ResourceEvent.Companion.fromJson(str2);
                    }
                    break;
                case 3619493:
                    if (str.equals("view")) {
                        return ViewEvent.Companion.fromJson(str2);
                    }
                    break;
                case 96784904:
                    if (str.equals("error")) {
                        return ErrorEvent.Companion.fromJson(str2);
                    }
                    break;
                case 128111976:
                    if (str.equals("long_task")) {
                        return LongTaskEvent.Companion.fromJson(str2);
                    }
                    break;
                case 780346297:
                    if (str.equals("telemetry")) {
                        String asString = jsonObject.getAsJsonObject("telemetry").getAsJsonPrimitive("status").getAsString();
                        if (Intrinsics.areEqual((Object) asString, (Object) TELEMETRY_TYPE_DEBUG)) {
                            return TelemetryDebugEvent.Companion.fromJson(str2);
                        }
                        if (Intrinsics.areEqual((Object) asString, (Object) "error")) {
                            return TelemetryErrorEvent.Companion.fromJson(str2);
                        }
                        throw new JsonParseException("We could not deserialize the telemetry event with status: " + asString);
                    }
                    break;
            }
        }
        throw new JsonParseException("We could not deserialize the event with type: " + str);
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/event/RumEventDeserializer$Companion;", "", "()V", "DESERIALIZE_ERROR_MESSAGE_FORMAT", "", "EVENT_TELEMETRY_KEY_NAME", "EVENT_TELEMETRY_STATUS_KEY_NAME", "EVENT_TYPE_ACTION", "EVENT_TYPE_ERROR", "EVENT_TYPE_KEY_NAME", "EVENT_TYPE_LONG_TASK", "EVENT_TYPE_RESOURCE", "EVENT_TYPE_TELEMETRY", "EVENT_TYPE_VIEW", "TELEMETRY_TYPE_DEBUG", "TELEMETRY_TYPE_ERROR", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumEventDeserializer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
