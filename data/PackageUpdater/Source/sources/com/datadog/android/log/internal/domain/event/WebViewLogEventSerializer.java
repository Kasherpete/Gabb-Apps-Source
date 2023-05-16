package com.datadog.android.log.internal.domain.event;

import com.datadog.android.core.internal.persistence.Serializer;
import com.google.gson.JsonObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/log/internal/domain/event/WebViewLogEventSerializer;", "Lcom/datadog/android/core/internal/persistence/Serializer;", "Lcom/google/gson/JsonObject;", "()V", "serialize", "", "model", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WebViewLogEventSerializer.kt */
public final class WebViewLogEventSerializer implements Serializer<JsonObject> {
    public String serialize(JsonObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "model");
        String jsonObject2 = jsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject2, "model.toString()");
        return jsonObject2;
    }
}
