package com.datadog.android.core.internal.utils;

import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(mo20734d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\bø\u0001\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0000\u001a\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n*\u0004\u0018\u00010\rH\u0000\u001a\u001a\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n*\u00020\u000eH\u0000\u001a\u0010\u0010\u000f\u001a\u0004\u0018\u00010\f*\u0004\u0018\u00010\fH\u0000\u001a\u0010\u0010\u0010\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u0011H\u0000\u001a\f\u0010\u0010\u001a\u00020\r*\u00020\u0012H\u0000\u001a\u000e\u0010\u0013\u001a\u00020\r*\u0004\u0018\u00010\fH\u0000\u001a\u0014\u0010\u0014\u001a\u00020\r*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\nH\u0000\u001a\f\u0010\u0014\u001a\u00020\r*\u00020\u0015H\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0016"}, mo20735d2 = {"retryWithDelay", "", "block", "Lkotlin/Function0;", "times", "", "loopsDelayInNanos", "", "retryDelayNs", "asMap", "", "", "", "Lcom/google/gson/JsonElement;", "Lcom/google/gson/JsonObject;", "fromJsonElement", "toJsonArray", "", "Lorg/json/JSONArray;", "toJsonElement", "toJsonObject", "Lorg/json/JSONObject;", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: MiscUtils.kt */
public final class MiscUtilsKt {
    public static final boolean retryWithDelay(Function0<Boolean> function0, int i, long j) {
        Intrinsics.checkNotNullParameter(function0, "block");
        long nanoTime = System.nanoTime() - j;
        int i2 = 1;
        boolean z = false;
        while (i2 <= i && !z) {
            if (System.nanoTime() - nanoTime >= j) {
                try {
                    z = function0.invoke().booleanValue();
                    nanoTime = System.nanoTime();
                    i2++;
                } catch (Exception e) {
                    LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
                    return false;
                }
            }
        }
        return z;
    }

    public static final JsonElement toJsonElement(Object obj) {
        if (Intrinsics.areEqual(obj, MapUtilsKt.getNULL_MAP_VALUE())) {
            JsonNull jsonNull = JsonNull.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(jsonNull, "INSTANCE");
            return jsonNull;
        } else if (obj == null) {
            JsonNull jsonNull2 = JsonNull.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(jsonNull2, "INSTANCE");
            return jsonNull2;
        } else if (Intrinsics.areEqual(obj, (Object) JsonNull.INSTANCE)) {
            JsonNull jsonNull3 = JsonNull.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(jsonNull3, "INSTANCE");
            return jsonNull3;
        } else if (obj instanceof Boolean) {
            return new JsonPrimitive((Boolean) obj);
        } else {
            if (obj instanceof Integer) {
                return new JsonPrimitive((Number) obj);
            }
            if (obj instanceof Long) {
                return new JsonPrimitive((Number) obj);
            }
            if (obj instanceof Float) {
                return new JsonPrimitive((Number) obj);
            }
            if (obj instanceof Double) {
                return new JsonPrimitive((Number) obj);
            }
            if (obj instanceof String) {
                return new JsonPrimitive((String) obj);
            }
            if (obj instanceof Date) {
                return new JsonPrimitive((Number) Long.valueOf(((Date) obj).getTime()));
            }
            if (obj instanceof Iterable) {
                return toJsonArray((Iterable<?>) (Iterable) obj);
            }
            if (obj instanceof Map) {
                return toJsonObject((Map<?, ?>) (Map) obj);
            }
            if (obj instanceof JsonObject) {
                return (JsonElement) obj;
            }
            if (obj instanceof JsonArray) {
                return (JsonElement) obj;
            }
            if (obj instanceof JsonPrimitive) {
                return (JsonElement) obj;
            }
            if (obj instanceof JSONObject) {
                return toJsonObject((JSONObject) obj);
            }
            if (obj instanceof JSONArray) {
                return toJsonArray((JSONArray) obj);
            }
            return new JsonPrimitive(obj.toString());
        }
    }

    public static final Object fromJsonElement(Object obj) {
        if (obj instanceof JsonNull) {
            return null;
        }
        if (!(obj instanceof JsonPrimitive)) {
            return obj instanceof JsonObject ? asMap((JsonObject) obj) : obj;
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) obj;
        if (jsonPrimitive.isBoolean()) {
            return Boolean.valueOf(jsonPrimitive.getAsBoolean());
        }
        if (jsonPrimitive.isNumber()) {
            return jsonPrimitive.getAsNumber();
        }
        if (jsonPrimitive.isString()) {
            return jsonPrimitive.getAsString();
        }
        return obj;
    }

    public static final JsonElement toJsonArray(Iterable<?> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        JsonArray jsonArray = new JsonArray();
        for (Object jsonElement : iterable) {
            jsonArray.add(toJsonElement(jsonElement));
        }
        return jsonArray;
    }

    public static final JsonElement toJsonObject(Map<?, ?> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry next : map.entrySet()) {
            jsonObject.add(String.valueOf(next.getKey()), toJsonElement(next.getValue()));
        }
        return jsonObject;
    }

    public static final JsonElement toJsonObject(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        JsonObject jsonObject = new JsonObject();
        Iterator<String> keys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "keys()");
        while (keys.hasNext()) {
            String next = keys.next();
            jsonObject.add(next, toJsonElement(jSONObject.get(next)));
        }
        return jsonObject;
    }

    public static final JsonElement toJsonArray(JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        JsonArray jsonArray = new JsonArray();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            jsonArray.add(toJsonElement(jSONArray.get(i)));
        }
        return jsonArray;
    }

    public static final Map<String, Object> asMap(JsonObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "<this>");
        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        Intrinsics.checkNotNullExpressionValue(entrySet, "entrySet()");
        for (Map.Entry entry : entrySet) {
            Object key = entry.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "it.key");
            linkedHashMap.put(key, fromJsonElement(entry.getValue()));
        }
        return linkedHashMap;
    }

    public static final Map<String, Object> asMap(JsonElement jsonElement) {
        if (jsonElement instanceof JsonObject) {
            return asMap((JsonObject) jsonElement);
        }
        return MapsKt.emptyMap();
    }

    public static final boolean retryWithDelay(int i, long j, Function0<Boolean> function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        long nanoTime = System.nanoTime() - j;
        int i2 = 1;
        boolean z = false;
        while (i2 <= i && !z) {
            if (System.nanoTime() - nanoTime >= j) {
                try {
                    z = function0.invoke().booleanValue();
                    nanoTime = System.nanoTime();
                    i2++;
                } catch (Exception e) {
                    LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
                    return false;
                }
            }
        }
        return z;
    }
}
