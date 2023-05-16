package com.datadog.android.webview.internal.rum;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.webview.internal.WebViewEventConsumer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB/\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u000fH\u0002J\u001a\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00022\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R0\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000ej\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010`\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/rum/WebViewRumEventConsumer;", "Lcom/datadog/android/webview/internal/WebViewEventConsumer;", "Lcom/google/gson/JsonObject;", "dataWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "webViewRumEventMapper", "Lcom/datadog/android/webview/internal/rum/WebViewRumEventMapper;", "contextProvider", "Lcom/datadog/android/webview/internal/rum/WebViewRumEventContextProvider;", "(Lcom/datadog/android/core/internal/persistence/DataWriter;Lcom/datadog/android/core/internal/time/TimeProvider;Lcom/datadog/android/webview/internal/rum/WebViewRumEventMapper;Lcom/datadog/android/webview/internal/rum/WebViewRumEventContextProvider;)V", "offsets", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "getOffsets$dd_sdk_android_release", "()Ljava/util/LinkedHashMap;", "consume", "", "event", "getOffset", "viewId", "map", "rumContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "purgeOffsets", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WebViewRumEventConsumer.kt */
public final class WebViewRumEventConsumer implements WebViewEventConsumer<JsonObject> {
    public static final String ACTION_EVENT_TYPE = "action";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ERROR_EVENT_TYPE = "error";
    public static final String JSON_PARSING_ERROR_MESSAGE = "The bundled web RUM event could not be deserialized";
    public static final String LONG_TASK_EVENT_TYPE = "long_task";
    public static final int MAX_VIEW_TIME_OFFSETS_RETAIN = 3;
    public static final String RESOURCE_EVENT_TYPE = "resource";
    public static final String RUM_EVENT_TYPE = "rum";
    /* access modifiers changed from: private */
    public static final Set<String> RUM_EVENT_TYPES = SetsKt.setOf("view", "action", "resource", "long_task", "error", "rum");
    public static final String VIEW_EVENT_TYPE = "view";
    public static final String VIEW_ID_KEY_NAME = "id";
    public static final String VIEW_KEY_NAME = "view";
    private final WebViewRumEventContextProvider contextProvider;
    private final DataWriter<Object> dataWriter;
    private final LinkedHashMap<String, Long> offsets;
    private final TimeProvider timeProvider;
    private final WebViewRumEventMapper webViewRumEventMapper;

    public WebViewRumEventConsumer(DataWriter<Object> dataWriter2, TimeProvider timeProvider2, WebViewRumEventMapper webViewRumEventMapper2, WebViewRumEventContextProvider webViewRumEventContextProvider) {
        Intrinsics.checkNotNullParameter(dataWriter2, "dataWriter");
        Intrinsics.checkNotNullParameter(timeProvider2, "timeProvider");
        Intrinsics.checkNotNullParameter(webViewRumEventMapper2, "webViewRumEventMapper");
        Intrinsics.checkNotNullParameter(webViewRumEventContextProvider, "contextProvider");
        this.dataWriter = dataWriter2;
        this.timeProvider = timeProvider2;
        this.webViewRumEventMapper = webViewRumEventMapper2;
        this.contextProvider = webViewRumEventContextProvider;
        this.offsets = new LinkedHashMap<>();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WebViewRumEventConsumer(DataWriter dataWriter2, TimeProvider timeProvider2, WebViewRumEventMapper webViewRumEventMapper2, WebViewRumEventContextProvider webViewRumEventContextProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(dataWriter2, timeProvider2, (i & 4) != 0 ? new WebViewRumEventMapper() : webViewRumEventMapper2, (i & 8) != 0 ? new WebViewRumEventContextProvider() : webViewRumEventContextProvider);
    }

    public final LinkedHashMap<String, Long> getOffsets$dd_sdk_android_release() {
        return this.offsets;
    }

    public void consume(JsonObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "event");
        GlobalRum.INSTANCE.notifyIngestedWebViewEvent$dd_sdk_android_release();
        this.dataWriter.write(map(jsonObject, this.contextProvider.getRumContext()));
    }

    private final JsonObject map(JsonObject jsonObject, RumContext rumContext) {
        try {
            JsonElement jsonElement = jsonObject.get("view");
            long j = 0;
            if (jsonElement != null) {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                if (asJsonObject != null) {
                    JsonElement jsonElement2 = asJsonObject.get("id");
                    if (jsonElement2 != null) {
                        String asString = jsonElement2.getAsString();
                        if (asString != null) {
                            j = getOffset(asString);
                        }
                    }
                }
            }
            return this.webViewRumEventMapper.mapEvent(jsonObject, rumContext, j);
        } catch (ClassCastException e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e, (Map) null, 4, (Object) null);
            return jsonObject;
        } catch (NumberFormatException e2) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e2, (Map) null, 4, (Object) null);
            return jsonObject;
        } catch (IllegalStateException e3) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e3, (Map) null, 4, (Object) null);
            return jsonObject;
        } catch (UnsupportedOperationException e4) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), JSON_PARSING_ERROR_MESSAGE, e4, (Map) null, 4, (Object) null);
            return jsonObject;
        }
    }

    private final long getOffset(String str) {
        Long l = this.offsets.get(str);
        if (l == null) {
            l = Long.valueOf(this.timeProvider.getServerOffsetMillis());
            this.offsets.put(str, l);
        }
        purgeOffsets();
        return l.longValue();
    }

    private final void purgeOffsets() {
        while (this.offsets.entrySet().size() > 3) {
            try {
                Set<Map.Entry<String, Long>> entrySet = this.offsets.entrySet();
                Intrinsics.checkNotNullExpressionValue(entrySet, "offsets.entries");
                Object first = CollectionsKt.first(entrySet);
                Intrinsics.checkNotNullExpressionValue(first, "offsets.entries.first()");
                this.offsets.remove(((Map.Entry) first).getKey());
            } catch (NoSuchElementException e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Trying to remove from an empty map.", e, (Map) null, 4, (Object) null);
                return;
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/rum/WebViewRumEventConsumer$Companion;", "", "()V", "ACTION_EVENT_TYPE", "", "ERROR_EVENT_TYPE", "JSON_PARSING_ERROR_MESSAGE", "LONG_TASK_EVENT_TYPE", "MAX_VIEW_TIME_OFFSETS_RETAIN", "", "RESOURCE_EVENT_TYPE", "RUM_EVENT_TYPE", "RUM_EVENT_TYPES", "", "getRUM_EVENT_TYPES", "()Ljava/util/Set;", "VIEW_EVENT_TYPE", "VIEW_ID_KEY_NAME", "VIEW_KEY_NAME", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: WebViewRumEventConsumer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getRUM_EVENT_TYPES() {
            return WebViewRumEventConsumer.RUM_EVENT_TYPES;
        }
    }
}
