package com.datadog.android.webview.internal.log;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.LogAttributes;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.webview.internal.WebViewEventConsumer;
import com.datadog.android.webview.internal.rum.WebViewRumEventContextProvider;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00172\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001:\u0001\u0017B#\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0002J\u001c\u0010\u0014\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0002J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0002R\u001b\u0010\f\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/log/WebViewLogEventConsumer;", "Lcom/datadog/android/webview/internal/WebViewEventConsumer;", "Lkotlin/Pair;", "Lcom/google/gson/JsonObject;", "", "userLogsWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "rumContextProvider", "Lcom/datadog/android/webview/internal/rum/WebViewRumEventContextProvider;", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "(Lcom/datadog/android/core/internal/persistence/DataWriter;Lcom/datadog/android/webview/internal/rum/WebViewRumEventContextProvider;Lcom/datadog/android/core/internal/time/TimeProvider;)V", "ddTags", "getDdTags", "()Ljava/lang/String;", "ddTags$delegate", "Lkotlin/Lazy;", "addDdTags", "", "event", "consume", "correctDate", "map", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WebViewLogEventConsumer.kt */
public final class WebViewLogEventConsumer implements WebViewEventConsumer<Pair<? extends JsonObject, ? extends String>> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DATE_KEY_NAME = "date";
    public static final String DDTAGS_KEY_NAME = "ddtags";
    public static final String DDTAGS_SEPARATOR = ",";
    public static final String INTERNAL_LOG_EVENT_TYPE = "internal_log";
    public static final String JSON_PARSING_ERROR_MESSAGE = "The bundled web log event could not be deserialized";
    /* access modifiers changed from: private */
    public static final Set<String> LOG_EVENT_TYPES = SetsKt.setOf("log", INTERNAL_LOG_EVENT_TYPE);
    public static final String USER_LOG_EVENT_TYPE = "log";
    private final Lazy ddTags$delegate = LazyKt.lazy(WebViewLogEventConsumer$ddTags$2.INSTANCE);
    private final WebViewRumEventContextProvider rumContextProvider;
    private final TimeProvider timeProvider;
    private final DataWriter<JsonObject> userLogsWriter;

    public WebViewLogEventConsumer(DataWriter<JsonObject> dataWriter, WebViewRumEventContextProvider webViewRumEventContextProvider, TimeProvider timeProvider2) {
        Intrinsics.checkNotNullParameter(dataWriter, "userLogsWriter");
        Intrinsics.checkNotNullParameter(webViewRumEventContextProvider, "rumContextProvider");
        Intrinsics.checkNotNullParameter(timeProvider2, "timeProvider");
        this.userLogsWriter = dataWriter;
        this.rumContextProvider = webViewRumEventContextProvider;
        this.timeProvider = timeProvider2;
    }

    private final String getDdTags() {
        return (String) this.ddTags$delegate.getValue();
    }

    public void consume(Pair<JsonObject, String> pair) {
        Intrinsics.checkNotNullParameter(pair, "event");
        JsonObject map = map(pair.getFirst());
        if (Intrinsics.areEqual((Object) pair.getSecond(), (Object) "log")) {
            this.userLogsWriter.write(map);
        }
    }

    private final JsonObject map(JsonObject jsonObject) {
        addDdTags(jsonObject);
        correctDate(jsonObject);
        RumContext rumContext = this.rumContextProvider.getRumContext();
        if (rumContext != null) {
            jsonObject.addProperty(LogAttributes.RUM_APPLICATION_ID, rumContext.getApplicationId());
            jsonObject.addProperty(LogAttributes.RUM_SESSION_ID, rumContext.getSessionId());
        }
        return jsonObject;
    }

    private final void correctDate(JsonObject jsonObject) {
        try {
            JsonElement jsonElement = jsonObject.get("date");
            if (jsonElement != null) {
                jsonObject.addProperty("date", (Number) Long.valueOf(jsonElement.getAsLong() + this.timeProvider.getServerOffsetMillis()));
            }
        } catch (ClassCastException e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e, (Map) null, 4, (Object) null);
        } catch (IllegalStateException e2) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e2, (Map) null, 4, (Object) null);
        } catch (NumberFormatException e3) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e3, (Map) null, 4, (Object) null);
        } catch (UnsupportedOperationException e4) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e4, (Map) null, 4, (Object) null);
        }
    }

    private final void addDdTags(JsonObject jsonObject) {
        String str = null;
        try {
            JsonElement jsonElement = jsonObject.get("ddtags");
            if (jsonElement != null) {
                str = jsonElement.getAsString();
            }
        } catch (ClassCastException e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e, (Map) null, 4, (Object) null);
        } catch (IllegalStateException e2) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e2, (Map) null, 4, (Object) null);
        } catch (UnsupportedOperationException e3) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e3, (Map) null, 4, (Object) null);
        }
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0) {
            jsonObject.addProperty("ddtags", getDdTags());
        } else {
            jsonObject.addProperty("ddtags", getDdTags() + DDTAGS_SEPARATOR + str);
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/log/WebViewLogEventConsumer$Companion;", "", "()V", "DATE_KEY_NAME", "", "DDTAGS_KEY_NAME", "DDTAGS_SEPARATOR", "INTERNAL_LOG_EVENT_TYPE", "JSON_PARSING_ERROR_MESSAGE", "LOG_EVENT_TYPES", "", "getLOG_EVENT_TYPES", "()Ljava/util/Set;", "USER_LOG_EVENT_TYPE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: WebViewLogEventConsumer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getLOG_EVENT_TYPES() {
            return WebViewLogEventConsumer.LOG_EVENT_TYPES;
        }
    }
}
