package com.datadog.android.webview.internal;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.webview.internal.log.WebViewLogEventConsumer;
import com.datadog.android.webview.internal.rum.WebViewRumEventConsumer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB-\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00060\u0001¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0016R&\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00060\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000f"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/MixedWebViewEventConsumer;", "Lcom/datadog/android/webview/internal/WebViewEventConsumer;", "", "rumEventConsumer", "Lcom/google/gson/JsonObject;", "logsEventConsumer", "Lkotlin/Pair;", "(Lcom/datadog/android/webview/internal/WebViewEventConsumer;Lcom/datadog/android/webview/internal/WebViewEventConsumer;)V", "getLogsEventConsumer$dd_sdk_android_release", "()Lcom/datadog/android/webview/internal/WebViewEventConsumer;", "getRumEventConsumer$dd_sdk_android_release", "consume", "", "event", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: MixedWebViewEventConsumer.kt */
public final class MixedWebViewEventConsumer implements WebViewEventConsumer<String> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EVENT_KEY = "event";
    public static final String EVENT_TYPE_KEY = "eventType";
    public static final String LOG_EVENT_TYPE = "log";
    public static final String WEB_EVENT_MISSING_TYPE_ERROR_MESSAGE = "The web event: %s is missing the event type.";
    public static final String WEB_EVENT_MISSING_WRAPPED_EVENT = "The web event: %s is missing the wrapped event object.";
    public static final String WEB_EVENT_PARSING_ERROR_MESSAGE = "We could not deserialize the delegated browser event: %s.";
    public static final String WRONG_EVENT_TYPE_ERROR_MESSAGE = "The event type %s for the bundled web event is unknown.";
    private final WebViewEventConsumer<Pair<JsonObject, String>> logsEventConsumer;
    private final WebViewEventConsumer<JsonObject> rumEventConsumer;

    public MixedWebViewEventConsumer(WebViewEventConsumer<JsonObject> webViewEventConsumer, WebViewEventConsumer<Pair<JsonObject, String>> webViewEventConsumer2) {
        Intrinsics.checkNotNullParameter(webViewEventConsumer, "rumEventConsumer");
        Intrinsics.checkNotNullParameter(webViewEventConsumer2, "logsEventConsumer");
        this.rumEventConsumer = webViewEventConsumer;
        this.logsEventConsumer = webViewEventConsumer2;
    }

    public final WebViewEventConsumer<JsonObject> getRumEventConsumer$dd_sdk_android_release() {
        return this.rumEventConsumer;
    }

    public final WebViewEventConsumer<Pair<JsonObject, String>> getLogsEventConsumer$dd_sdk_android_release() {
        return this.logsEventConsumer;
    }

    public void consume(String str) {
        Intrinsics.checkNotNullParameter(str, "event");
        try {
            JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
            if (!asJsonObject.has(EVENT_TYPE_KEY)) {
                Logger sdkLogger = RuntimeUtilsKt.getSdkLogger();
                String format = String.format(Locale.US, WEB_EVENT_MISSING_TYPE_ERROR_MESSAGE, Arrays.copyOf(new Object[]{str}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
                LogUtilsKt.errorWithTelemetry$default(sdkLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
            } else if (!asJsonObject.has("event")) {
                Logger sdkLogger2 = RuntimeUtilsKt.getSdkLogger();
                String format2 = String.format(Locale.US, WEB_EVENT_MISSING_WRAPPED_EVENT, Arrays.copyOf(new Object[]{str}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
                LogUtilsKt.errorWithTelemetry$default(sdkLogger2, format2, (Throwable) null, (Map) null, 6, (Object) null);
            } else {
                String asString = asJsonObject.get(EVENT_TYPE_KEY).getAsString();
                JsonObject asJsonObject2 = asJsonObject.get("event").getAsJsonObject();
                if (WebViewLogEventConsumer.Companion.getLOG_EVENT_TYPES().contains(asString)) {
                    this.logsEventConsumer.consume(TuplesKt.m78to(asJsonObject2, asString));
                } else if (WebViewRumEventConsumer.Companion.getRUM_EVENT_TYPES().contains(asString)) {
                    WebViewEventConsumer<JsonObject> webViewEventConsumer = this.rumEventConsumer;
                    Intrinsics.checkNotNullExpressionValue(asJsonObject2, "wrappedEvent");
                    webViewEventConsumer.consume(asJsonObject2);
                } else {
                    Logger sdkLogger3 = RuntimeUtilsKt.getSdkLogger();
                    String format3 = String.format(Locale.US, WRONG_EVENT_TYPE_ERROR_MESSAGE, Arrays.copyOf(new Object[]{asString}, 1));
                    Intrinsics.checkNotNullExpressionValue(format3, "format(locale, this, *args)");
                    LogUtilsKt.errorWithTelemetry$default(sdkLogger3, format3, (Throwable) null, (Map) null, 6, (Object) null);
                }
            }
        } catch (JsonParseException e) {
            Logger sdkLogger4 = RuntimeUtilsKt.getSdkLogger();
            String format4 = String.format(Locale.US, WEB_EVENT_PARSING_ERROR_MESSAGE, Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format4, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(sdkLogger4, format4, e, (Map) null, 4, (Object) null);
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/MixedWebViewEventConsumer$Companion;", "", "()V", "EVENT_KEY", "", "EVENT_TYPE_KEY", "LOG_EVENT_TYPE", "WEB_EVENT_MISSING_TYPE_ERROR_MESSAGE", "WEB_EVENT_MISSING_WRAPPED_EVENT", "WEB_EVENT_PARSING_ERROR_MESSAGE", "WRONG_EVENT_TYPE_ERROR_MESSAGE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: MixedWebViewEventConsumer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
