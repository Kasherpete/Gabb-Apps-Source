package com.datadog.android.webview.internal.rum;

import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.rum.model.ViewEvent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/rum/WebViewRumEventMapper;", "", "()V", "mapEvent", "Lcom/google/gson/JsonObject;", "event", "context", "Lcom/datadog/android/rum/internal/domain/RumContext;", "timeOffset", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WebViewRumEventMapper.kt */
public final class WebViewRumEventMapper {
    public static final String APPLICATION_KEY_NAME = "application";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DATE_KEY_NAME = "date";
    public static final String DD_KEY_NAME = "_dd";
    public static final String DD_SESSION_KEY_NAME = "session";
    public static final String ID_KEY_NAME = "id";
    public static final String SESSION_KEY_NAME = "session";
    public static final String SESSION_PLAN_KEY_NAME = "plan";

    public final JsonObject mapEvent(JsonObject jsonObject, RumContext rumContext, long j) throws ClassCastException, IllegalStateException, NumberFormatException {
        Intrinsics.checkNotNullParameter(jsonObject, "event");
        JsonElement jsonElement = jsonObject.get("date");
        if (jsonElement != null) {
            jsonObject.addProperty("date", (Number) Long.valueOf(jsonElement.getAsLong() + j));
        }
        JsonElement jsonElement2 = jsonObject.get(DD_KEY_NAME);
        JsonObject jsonObject2 = null;
        JsonObject asJsonObject = jsonElement2 == null ? null : jsonElement2.getAsJsonObject();
        if (asJsonObject != null) {
            JsonElement jsonElement3 = asJsonObject.get("session");
            JsonObject asJsonObject2 = jsonElement3 == null ? null : jsonElement3.getAsJsonObject();
            if (asJsonObject2 == null) {
                asJsonObject2 = new JsonObject();
            }
            asJsonObject2.addProperty(SESSION_PLAN_KEY_NAME, (Number) Integer.valueOf(ViewEvent.Plan.PLAN_1.toJson().getAsInt()));
            asJsonObject.add("session", asJsonObject2);
        }
        if (rumContext != null) {
            JsonObject asJsonObject3 = jsonObject.getAsJsonObject(APPLICATION_KEY_NAME);
            JsonObject asJsonObject4 = asJsonObject3 == null ? null : asJsonObject3.getAsJsonObject();
            if (asJsonObject4 == null) {
                asJsonObject4 = new JsonObject();
            }
            JsonObject asJsonObject5 = jsonObject.getAsJsonObject("session");
            if (asJsonObject5 != null) {
                jsonObject2 = asJsonObject5.getAsJsonObject();
            }
            if (jsonObject2 == null) {
                jsonObject2 = new JsonObject();
            }
            asJsonObject4.addProperty("id", rumContext.getApplicationId());
            jsonObject2.addProperty("id", rumContext.getSessionId());
            jsonObject.add(APPLICATION_KEY_NAME, asJsonObject4);
            jsonObject.add("session", jsonObject2);
        }
        return jsonObject;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/rum/WebViewRumEventMapper$Companion;", "", "()V", "APPLICATION_KEY_NAME", "", "DATE_KEY_NAME", "DD_KEY_NAME", "DD_SESSION_KEY_NAME", "ID_KEY_NAME", "SESSION_KEY_NAME", "SESSION_PLAN_KEY_NAME", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: WebViewRumEventMapper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
