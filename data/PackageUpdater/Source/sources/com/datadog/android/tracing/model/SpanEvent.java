package com.datadog.android.tracing.model;

import androidx.core.app.NotificationCompat;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.utils.MiscUtilsKt;
import com.datadog.android.log.LogAttributes;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.tracing.internal.domain.event.SpanEventSerializer;
import com.datadog.android.webview.internal.rum.WebViewRumEventMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import p006io.opentracing.tag.Tags;

@Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\b\u0018\u0000 ;2\u00020\u0001:\n:;<=>?@ABCB_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u000eHÆ\u0003J\t\u0010(\u001a\u00020\u0010HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\nHÆ\u0003J\t\u0010/\u001a\u00020\nHÆ\u0003J\t\u00100\u001a\u00020\nHÆ\u0003Jw\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010HÆ\u0001J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u000206HÖ\u0001J\u0006\u00107\u001a\u000208J\t\u00109\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0014\u0010$\u001a\u00020\u0003XD¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001a¨\u0006D"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent;", "", "traceId", "", "spanId", "parentId", "resource", "name", "service", "duration", "", "start", "error", "metrics", "Lcom/datadog/android/tracing/model/SpanEvent$Metrics;", "meta", "Lcom/datadog/android/tracing/model/SpanEvent$Meta;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJLcom/datadog/android/tracing/model/SpanEvent$Metrics;Lcom/datadog/android/tracing/model/SpanEvent$Meta;)V", "getDuration", "()J", "getError", "getMeta", "()Lcom/datadog/android/tracing/model/SpanEvent$Meta;", "getMetrics", "()Lcom/datadog/android/tracing/model/SpanEvent$Metrics;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getParentId", "getResource", "setResource", "getService", "getSpanId", "getStart", "getTraceId", "type", "getType", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Client", "Companion", "Dd", "Meta", "Metrics", "Network", "SimCarrier", "Span", "Tracer", "Usr", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SpanEvent.kt */
public final class SpanEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final long duration;
    private final long error;
    private final Meta meta;
    private final Metrics metrics;
    private String name;
    private final String parentId;
    private String resource;
    private final String service;
    private final String spanId;
    private final long start;
    private final String traceId;
    private final String type;

    public static /* synthetic */ SpanEvent copy$default(SpanEvent spanEvent, String str, String str2, String str3, String str4, String str5, String str6, long j, long j2, long j3, Metrics metrics2, Meta meta2, int i, Object obj) {
        SpanEvent spanEvent2 = spanEvent;
        int i2 = i;
        return spanEvent.copy((i2 & 1) != 0 ? spanEvent2.traceId : str, (i2 & 2) != 0 ? spanEvent2.spanId : str2, (i2 & 4) != 0 ? spanEvent2.parentId : str3, (i2 & 8) != 0 ? spanEvent2.resource : str4, (i2 & 16) != 0 ? spanEvent2.name : str5, (i2 & 32) != 0 ? spanEvent2.service : str6, (i2 & 64) != 0 ? spanEvent2.duration : j, (i2 & 128) != 0 ? spanEvent2.start : j2, (i2 & 256) != 0 ? spanEvent2.error : j3, (i2 & 512) != 0 ? spanEvent2.metrics : metrics2, (i2 & 1024) != 0 ? spanEvent2.meta : meta2);
    }

    @JvmStatic
    public static final SpanEvent fromJson(String str) throws JsonParseException {
        return Companion.fromJson(str);
    }

    public final String component1() {
        return this.traceId;
    }

    public final Metrics component10() {
        return this.metrics;
    }

    public final Meta component11() {
        return this.meta;
    }

    public final String component2() {
        return this.spanId;
    }

    public final String component3() {
        return this.parentId;
    }

    public final String component4() {
        return this.resource;
    }

    public final String component5() {
        return this.name;
    }

    public final String component6() {
        return this.service;
    }

    public final long component7() {
        return this.duration;
    }

    public final long component8() {
        return this.start;
    }

    public final long component9() {
        return this.error;
    }

    public final SpanEvent copy(String str, String str2, String str3, String str4, String str5, String str6, long j, long j2, long j3, Metrics metrics2, Meta meta2) {
        String str7 = str;
        Intrinsics.checkNotNullParameter(str7, "traceId");
        String str8 = str2;
        Intrinsics.checkNotNullParameter(str8, "spanId");
        String str9 = str3;
        Intrinsics.checkNotNullParameter(str9, "parentId");
        String str10 = str4;
        Intrinsics.checkNotNullParameter(str10, "resource");
        String str11 = str5;
        Intrinsics.checkNotNullParameter(str11, "name");
        String str12 = str6;
        Intrinsics.checkNotNullParameter(str12, "service");
        Metrics metrics3 = metrics2;
        Intrinsics.checkNotNullParameter(metrics3, SpanEventSerializer.METRICS_KEY_PREFIX);
        Meta meta3 = meta2;
        Intrinsics.checkNotNullParameter(meta3, "meta");
        return new SpanEvent(str7, str8, str9, str10, str11, str12, j, j2, j3, metrics3, meta3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpanEvent)) {
            return false;
        }
        SpanEvent spanEvent = (SpanEvent) obj;
        return Intrinsics.areEqual((Object) this.traceId, (Object) spanEvent.traceId) && Intrinsics.areEqual((Object) this.spanId, (Object) spanEvent.spanId) && Intrinsics.areEqual((Object) this.parentId, (Object) spanEvent.parentId) && Intrinsics.areEqual((Object) this.resource, (Object) spanEvent.resource) && Intrinsics.areEqual((Object) this.name, (Object) spanEvent.name) && Intrinsics.areEqual((Object) this.service, (Object) spanEvent.service) && this.duration == spanEvent.duration && this.start == spanEvent.start && this.error == spanEvent.error && Intrinsics.areEqual((Object) this.metrics, (Object) spanEvent.metrics) && Intrinsics.areEqual((Object) this.meta, (Object) spanEvent.meta);
    }

    public int hashCode() {
        return (((((((((((((((((((this.traceId.hashCode() * 31) + this.spanId.hashCode()) * 31) + this.parentId.hashCode()) * 31) + this.resource.hashCode()) * 31) + this.name.hashCode()) * 31) + this.service.hashCode()) * 31) + Long.hashCode(this.duration)) * 31) + Long.hashCode(this.start)) * 31) + Long.hashCode(this.error)) * 31) + this.metrics.hashCode()) * 31) + this.meta.hashCode();
    }

    public String toString() {
        String str = this.traceId;
        String str2 = this.spanId;
        String str3 = this.parentId;
        String str4 = this.resource;
        String str5 = this.name;
        String str6 = this.service;
        long j = this.duration;
        long j2 = this.start;
        long j3 = this.error;
        Metrics metrics2 = this.metrics;
        return "SpanEvent(traceId=" + str + ", spanId=" + str2 + ", parentId=" + str3 + ", resource=" + str4 + ", name=" + str5 + ", service=" + str6 + ", duration=" + j + ", start=" + j2 + ", error=" + j3 + ", metrics=" + metrics2 + ", meta=" + this.meta + ")";
    }

    public SpanEvent(String str, String str2, String str3, String str4, String str5, String str6, long j, long j2, long j3, Metrics metrics2, Meta meta2) {
        Intrinsics.checkNotNullParameter(str, "traceId");
        Intrinsics.checkNotNullParameter(str2, "spanId");
        Intrinsics.checkNotNullParameter(str3, "parentId");
        Intrinsics.checkNotNullParameter(str4, "resource");
        Intrinsics.checkNotNullParameter(str5, "name");
        Intrinsics.checkNotNullParameter(str6, "service");
        Intrinsics.checkNotNullParameter(metrics2, SpanEventSerializer.METRICS_KEY_PREFIX);
        Intrinsics.checkNotNullParameter(meta2, "meta");
        this.traceId = str;
        this.spanId = str2;
        this.parentId = str3;
        this.resource = str4;
        this.name = str5;
        this.service = str6;
        this.duration = j;
        this.start = j2;
        this.error = j3;
        this.metrics = metrics2;
        this.meta = meta2;
        this.type = "custom";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SpanEvent(String str, String str2, String str3, String str4, String str5, String str6, long j, long j2, long j3, Metrics metrics2, Meta meta2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, j, j2, (i & 256) != 0 ? 0 : j3, metrics2, meta2);
    }

    public final String getTraceId() {
        return this.traceId;
    }

    public final String getSpanId() {
        return this.spanId;
    }

    public final String getParentId() {
        return this.parentId;
    }

    public final String getResource() {
        return this.resource;
    }

    public final void setResource(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.resource = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getService() {
        return this.service;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final long getStart() {
        return this.start;
    }

    public final long getError() {
        return this.error;
    }

    public final Metrics getMetrics() {
        return this.metrics;
    }

    public final Meta getMeta() {
        return this.meta;
    }

    public final String getType() {
        return this.type;
    }

    public final JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("trace_id", this.traceId);
        jsonObject.addProperty("span_id", this.spanId);
        jsonObject.addProperty("parent_id", this.parentId);
        jsonObject.addProperty("resource", this.resource);
        jsonObject.addProperty("name", this.name);
        jsonObject.addProperty("service", this.service);
        jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
        jsonObject.addProperty("start", (Number) Long.valueOf(this.start));
        jsonObject.addProperty("error", (Number) Long.valueOf(this.error));
        jsonObject.addProperty(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type);
        jsonObject.add(SpanEventSerializer.METRICS_KEY_PREFIX, this.metrics.toJson());
        jsonObject.add("meta", this.meta.toJson());
        return jsonObject;
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/tracing/model/SpanEvent;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SpanEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SpanEvent fromJson(String str) throws JsonParseException {
            Intrinsics.checkNotNullParameter(str, "serializedObject");
            try {
                JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                String asString = asJsonObject.get("trace_id").getAsString();
                String asString2 = asJsonObject.get("span_id").getAsString();
                String asString3 = asJsonObject.get("parent_id").getAsString();
                String asString4 = asJsonObject.get("resource").getAsString();
                String asString5 = asJsonObject.get("name").getAsString();
                String asString6 = asJsonObject.get("service").getAsString();
                long asLong = asJsonObject.get(LogAttributes.DURATION).getAsLong();
                long asLong2 = asJsonObject.get("start").getAsLong();
                long asLong3 = asJsonObject.get("error").getAsLong();
                String jsonElement = asJsonObject.get(SpanEventSerializer.METRICS_KEY_PREFIX).toString();
                Metrics.Companion companion = Metrics.Companion;
                Intrinsics.checkNotNullExpressionValue(jsonElement, "it");
                Metrics fromJson = companion.fromJson(jsonElement);
                String jsonElement2 = asJsonObject.get("meta").toString();
                Meta.Companion companion2 = Meta.Companion;
                Intrinsics.checkNotNullExpressionValue(jsonElement2, "it");
                Meta fromJson2 = companion2.fromJson(jsonElement2);
                Intrinsics.checkNotNullExpressionValue(asString, "traceId");
                Intrinsics.checkNotNullExpressionValue(asString2, "spanId");
                Intrinsics.checkNotNullExpressionValue(asString3, "parentId");
                Intrinsics.checkNotNullExpressionValue(asString4, "resource");
                Intrinsics.checkNotNullExpressionValue(asString5, "name");
                Intrinsics.checkNotNullExpressionValue(asString6, "service");
                return new SpanEvent(asString, asString2, asString3, asString4, asString5, asString6, asLong, asLong2, asLong3, fromJson, fromJson2);
            } catch (IllegalStateException e) {
                throw new JsonParseException(e.getMessage());
            } catch (NumberFormatException e2) {
                throw new JsonParseException(e2.getMessage());
            }
        }
    }

    @Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0004\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0015\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J0\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Metrics;", "", "topLevel", "", "additionalProperties", "", "", "", "(Ljava/lang/Long;Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "getTopLevel", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "copy", "(Ljava/lang/Long;Ljava/util/Map;)Lcom/datadog/android/tracing/model/SpanEvent$Metrics;", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SpanEvent.kt */
    public static final class Metrics {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final String[] RESERVED_PROPERTIES = {"_top_level"};
        private final Map<String, Number> additionalProperties;
        private final Long topLevel;

        public Metrics() {
            this((Long) null, (Map) null, 3, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Metrics copy$default(Metrics metrics, Long l, Map<String, Number> map, int i, Object obj) {
            if ((i & 1) != 0) {
                l = metrics.topLevel;
            }
            if ((i & 2) != 0) {
                map = metrics.additionalProperties;
            }
            return metrics.copy(l, map);
        }

        @JvmStatic
        public static final Metrics fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final Long component1() {
            return this.topLevel;
        }

        public final Map<String, Number> component2() {
            return this.additionalProperties;
        }

        public final Metrics copy(Long l, Map<String, ? extends Number> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            return new Metrics(l, map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Metrics)) {
                return false;
            }
            Metrics metrics = (Metrics) obj;
            return Intrinsics.areEqual((Object) this.topLevel, (Object) metrics.topLevel) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) metrics.additionalProperties);
        }

        public int hashCode() {
            Long l = this.topLevel;
            return ((l == null ? 0 : l.hashCode()) * 31) + this.additionalProperties.hashCode();
        }

        public String toString() {
            Long l = this.topLevel;
            return "Metrics(topLevel=" + l + ", additionalProperties=" + this.additionalProperties + ")";
        }

        public Metrics(Long l, Map<String, ? extends Number> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            this.topLevel = l;
            this.additionalProperties = map;
        }

        public final Long getTopLevel() {
            return this.topLevel;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Metrics(Long l, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l, (i & 2) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, Number> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            Long l = this.topLevel;
            if (l != null) {
                jsonObject.addProperty("_top_level", (Number) Long.valueOf(l.longValue()));
            }
            for (Map.Entry next : this.additionalProperties.entrySet()) {
                String str = (String) next.getKey();
                Number number = (Number) next.getValue();
                if (!ArraysKt.contains((T[]) RESERVED_PROPERTIES, str)) {
                    jsonObject.addProperty(str, number);
                }
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Metrics$Companion;", "", "()V", "RESERVED_PROPERTIES", "", "", "getRESERVED_PROPERTIES$dd_sdk_android_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "fromJson", "Lcom/datadog/android/tracing/model/SpanEvent$Metrics;", "serializedObject", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: SpanEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final String[] getRESERVED_PROPERTIES$dd_sdk_android_release() {
                return Metrics.RESERVED_PROPERTIES;
            }

            @JvmStatic
            public final Metrics fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    JsonElement jsonElement = asJsonObject.get("_top_level");
                    Long valueOf = jsonElement == null ? null : Long.valueOf(jsonElement.getAsLong());
                    Map linkedHashMap = new LinkedHashMap();
                    for (Map.Entry next : asJsonObject.entrySet()) {
                        if (!ArraysKt.contains((T[]) getRESERVED_PROPERTIES$dd_sdk_android_release(), next.getKey())) {
                            Object key = next.getKey();
                            Intrinsics.checkNotNullExpressionValue(key, "entry.key");
                            Number asNumber = ((JsonElement) next.getValue()).getAsNumber();
                            Intrinsics.checkNotNullExpressionValue(asNumber, "entry.value.asNumber");
                            linkedHashMap.put(key, asNumber);
                        }
                    }
                    return new Metrics(valueOf, linkedHashMap);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 /2\u00020\u0001:\u0001/BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\u000bHÆ\u0003J\t\u0010$\u001a\u00020\rHÆ\u0003J\u0015\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0003J[\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\u0006\u0010,\u001a\u00020-J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u00060"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Meta;", "", "version", "", "dd", "Lcom/datadog/android/tracing/model/SpanEvent$Dd;", "span", "Lcom/datadog/android/tracing/model/SpanEvent$Span;", "tracer", "Lcom/datadog/android/tracing/model/SpanEvent$Tracer;", "usr", "Lcom/datadog/android/tracing/model/SpanEvent$Usr;", "network", "Lcom/datadog/android/tracing/model/SpanEvent$Network;", "additionalProperties", "", "(Ljava/lang/String;Lcom/datadog/android/tracing/model/SpanEvent$Dd;Lcom/datadog/android/tracing/model/SpanEvent$Span;Lcom/datadog/android/tracing/model/SpanEvent$Tracer;Lcom/datadog/android/tracing/model/SpanEvent$Usr;Lcom/datadog/android/tracing/model/SpanEvent$Network;Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "getDd", "()Lcom/datadog/android/tracing/model/SpanEvent$Dd;", "getNetwork", "()Lcom/datadog/android/tracing/model/SpanEvent$Network;", "getSpan", "()Lcom/datadog/android/tracing/model/SpanEvent$Span;", "getTracer", "()Lcom/datadog/android/tracing/model/SpanEvent$Tracer;", "getUsr", "()Lcom/datadog/android/tracing/model/SpanEvent$Usr;", "getVersion", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SpanEvent.kt */
    public static final class Meta {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final String[] RESERVED_PROPERTIES = {"version", WebViewRumEventMapper.DD_KEY_NAME, "span", "tracer", "usr", "network"};
        private final Map<String, String> additionalProperties;

        /* renamed from: dd */
        private final C0875Dd f149dd;
        private final Network network;
        private final Span span;
        private final Tracer tracer;
        private final Usr usr;
        private final String version;

        public static /* synthetic */ Meta copy$default(Meta meta, String str, C0875Dd dd, Span span2, Tracer tracer2, Usr usr2, Network network2, Map<String, String> map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = meta.version;
            }
            if ((i & 2) != 0) {
                dd = meta.f149dd;
            }
            C0875Dd dd2 = dd;
            if ((i & 4) != 0) {
                span2 = meta.span;
            }
            Span span3 = span2;
            if ((i & 8) != 0) {
                tracer2 = meta.tracer;
            }
            Tracer tracer3 = tracer2;
            if ((i & 16) != 0) {
                usr2 = meta.usr;
            }
            Usr usr3 = usr2;
            if ((i & 32) != 0) {
                network2 = meta.network;
            }
            Network network3 = network2;
            if ((i & 64) != 0) {
                map = meta.additionalProperties;
            }
            return meta.copy(str, dd2, span3, tracer3, usr3, network3, map);
        }

        @JvmStatic
        public static final Meta fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.version;
        }

        public final C0875Dd component2() {
            return this.f149dd;
        }

        public final Span component3() {
            return this.span;
        }

        public final Tracer component4() {
            return this.tracer;
        }

        public final Usr component5() {
            return this.usr;
        }

        public final Network component6() {
            return this.network;
        }

        public final Map<String, String> component7() {
            return this.additionalProperties;
        }

        public final Meta copy(String str, C0875Dd dd, Span span2, Tracer tracer2, Usr usr2, Network network2, Map<String, String> map) {
            Intrinsics.checkNotNullParameter(str, "version");
            Intrinsics.checkNotNullParameter(dd, "dd");
            Intrinsics.checkNotNullParameter(span2, "span");
            Intrinsics.checkNotNullParameter(tracer2, "tracer");
            Intrinsics.checkNotNullParameter(usr2, "usr");
            Intrinsics.checkNotNullParameter(network2, "network");
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            return new Meta(str, dd, span2, tracer2, usr2, network2, map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Meta)) {
                return false;
            }
            Meta meta = (Meta) obj;
            return Intrinsics.areEqual((Object) this.version, (Object) meta.version) && Intrinsics.areEqual((Object) this.f149dd, (Object) meta.f149dd) && Intrinsics.areEqual((Object) this.span, (Object) meta.span) && Intrinsics.areEqual((Object) this.tracer, (Object) meta.tracer) && Intrinsics.areEqual((Object) this.usr, (Object) meta.usr) && Intrinsics.areEqual((Object) this.network, (Object) meta.network) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) meta.additionalProperties);
        }

        public int hashCode() {
            return (((((((((((this.version.hashCode() * 31) + this.f149dd.hashCode()) * 31) + this.span.hashCode()) * 31) + this.tracer.hashCode()) * 31) + this.usr.hashCode()) * 31) + this.network.hashCode()) * 31) + this.additionalProperties.hashCode();
        }

        public String toString() {
            String str = this.version;
            C0875Dd dd = this.f149dd;
            Span span2 = this.span;
            Tracer tracer2 = this.tracer;
            Usr usr2 = this.usr;
            Network network2 = this.network;
            return "Meta(version=" + str + ", dd=" + dd + ", span=" + span2 + ", tracer=" + tracer2 + ", usr=" + usr2 + ", network=" + network2 + ", additionalProperties=" + this.additionalProperties + ")";
        }

        public Meta(String str, C0875Dd dd, Span span2, Tracer tracer2, Usr usr2, Network network2, Map<String, String> map) {
            Intrinsics.checkNotNullParameter(str, "version");
            Intrinsics.checkNotNullParameter(dd, "dd");
            Intrinsics.checkNotNullParameter(span2, "span");
            Intrinsics.checkNotNullParameter(tracer2, "tracer");
            Intrinsics.checkNotNullParameter(usr2, "usr");
            Intrinsics.checkNotNullParameter(network2, "network");
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            this.version = str;
            this.f149dd = dd;
            this.span = span2;
            this.tracer = tracer2;
            this.usr = usr2;
            this.network = network2;
            this.additionalProperties = map;
        }

        public final String getVersion() {
            return this.version;
        }

        public final C0875Dd getDd() {
            return this.f149dd;
        }

        public final Span getSpan() {
            return this.span;
        }

        public final Tracer getTracer() {
            return this.tracer;
        }

        public final Usr getUsr() {
            return this.usr;
        }

        public final Network getNetwork() {
            return this.network;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ Meta(java.lang.String r10, com.datadog.android.tracing.model.SpanEvent.C0875Dd r11, com.datadog.android.tracing.model.SpanEvent.Span r12, com.datadog.android.tracing.model.SpanEvent.Tracer r13, com.datadog.android.tracing.model.SpanEvent.Usr r14, com.datadog.android.tracing.model.SpanEvent.Network r15, java.util.Map r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
            /*
                r9 = this;
                r0 = r17 & 64
                if (r0 == 0) goto L_0x000a
                java.util.Map r0 = kotlin.collections.MapsKt.emptyMap()
                r8 = r0
                goto L_0x000c
            L_0x000a:
                r8 = r16
            L_0x000c:
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r12
                r5 = r13
                r6 = r14
                r7 = r15
                r1.<init>(r2, r3, r4, r5, r6, r7, r8)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.tracing.model.SpanEvent.Meta.<init>(java.lang.String, com.datadog.android.tracing.model.SpanEvent$Dd, com.datadog.android.tracing.model.SpanEvent$Span, com.datadog.android.tracing.model.SpanEvent$Tracer, com.datadog.android.tracing.model.SpanEvent$Usr, com.datadog.android.tracing.model.SpanEvent$Network, java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final Map<String, String> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("version", this.version);
            jsonObject.add(WebViewRumEventMapper.DD_KEY_NAME, this.f149dd.toJson());
            jsonObject.add("span", this.span.toJson());
            jsonObject.add("tracer", this.tracer.toJson());
            jsonObject.add("usr", this.usr.toJson());
            jsonObject.add("network", this.network.toJson());
            for (Map.Entry next : this.additionalProperties.entrySet()) {
                String str = (String) next.getKey();
                String str2 = (String) next.getValue();
                if (!ArraysKt.contains((T[]) RESERVED_PROPERTIES, str)) {
                    jsonObject.addProperty(str, str2);
                }
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Meta$Companion;", "", "()V", "RESERVED_PROPERTIES", "", "", "getRESERVED_PROPERTIES$dd_sdk_android_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "fromJson", "Lcom/datadog/android/tracing/model/SpanEvent$Meta;", "serializedObject", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: SpanEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final String[] getRESERVED_PROPERTIES$dd_sdk_android_release() {
                return Meta.RESERVED_PROPERTIES;
            }

            @JvmStatic
            public final Meta fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    String asString = asJsonObject.get("version").getAsString();
                    String jsonElement = asJsonObject.get(WebViewRumEventMapper.DD_KEY_NAME).toString();
                    C0875Dd.Companion companion = C0875Dd.Companion;
                    Intrinsics.checkNotNullExpressionValue(jsonElement, "it");
                    C0875Dd fromJson = companion.fromJson(jsonElement);
                    Span span = new Span();
                    String jsonElement2 = asJsonObject.get("tracer").toString();
                    Tracer.Companion companion2 = Tracer.Companion;
                    Intrinsics.checkNotNullExpressionValue(jsonElement2, "it");
                    Tracer fromJson2 = companion2.fromJson(jsonElement2);
                    String jsonElement3 = asJsonObject.get("usr").toString();
                    Usr.Companion companion3 = Usr.Companion;
                    Intrinsics.checkNotNullExpressionValue(jsonElement3, "it");
                    Usr fromJson3 = companion3.fromJson(jsonElement3);
                    String jsonElement4 = asJsonObject.get("network").toString();
                    Network.Companion companion4 = Network.Companion;
                    Intrinsics.checkNotNullExpressionValue(jsonElement4, "it");
                    Network fromJson4 = companion4.fromJson(jsonElement4);
                    Map linkedHashMap = new LinkedHashMap();
                    for (Map.Entry next : asJsonObject.entrySet()) {
                        if (!ArraysKt.contains((T[]) getRESERVED_PROPERTIES$dd_sdk_android_release(), next.getKey())) {
                            Object key = next.getKey();
                            Intrinsics.checkNotNullExpressionValue(key, "entry.key");
                            String asString2 = ((JsonElement) next.getValue()).getAsString();
                            Intrinsics.checkNotNullExpressionValue(asString2, "entry.value.asString");
                            linkedHashMap.put(key, asString2);
                        }
                    }
                    Intrinsics.checkNotNullExpressionValue(asString, "version");
                    return new Meta(asString, fromJson, span, fromJson2, fromJson3, fromJson4, linkedHashMap);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Dd;", "", "source", "", "(Ljava/lang/String;)V", "getSource", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* renamed from: com.datadog.android.tracing.model.SpanEvent$Dd */
    /* compiled from: SpanEvent.kt */
    public static final class C0875Dd {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String source;

        public C0875Dd() {
            this((String) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ C0875Dd copy$default(C0875Dd dd, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = dd.source;
            }
            return dd.copy(str);
        }

        @JvmStatic
        public static final C0875Dd fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.source;
        }

        public final C0875Dd copy(String str) {
            return new C0875Dd(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof C0875Dd) && Intrinsics.areEqual((Object) this.source, (Object) ((C0875Dd) obj).source);
        }

        public int hashCode() {
            String str = this.source;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "Dd(source=" + this.source + ")";
        }

        public C0875Dd(String str) {
            this.source = str;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ C0875Dd(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CoreFeature.DEFAULT_SOURCE_NAME : str);
        }

        public final String getSource() {
            return this.source;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            String str = this.source;
            if (str != null) {
                jsonObject.addProperty("source", str);
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Dd$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/tracing/model/SpanEvent$Dd;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* renamed from: com.datadog.android.tracing.model.SpanEvent$Dd$Companion */
        /* compiled from: SpanEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final C0875Dd fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonElement jsonElement = JsonParser.parseString(str).getAsJsonObject().get("source");
                    return new C0875Dd(jsonElement == null ? null : jsonElement.getAsString());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Span;", "", "()V", "kind", "", "getKind", "()Ljava/lang/String;", "toJson", "Lcom/google/gson/JsonElement;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SpanEvent.kt */
    public static final class Span {
        private final String kind = Tags.SPAN_KIND_CLIENT;

        public final String getKind() {
            return this.kind;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kind", this.kind);
            return jsonObject;
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Tracer;", "", "version", "", "(Ljava/lang/String;)V", "getVersion", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SpanEvent.kt */
    public static final class Tracer {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String version;

        public static /* synthetic */ Tracer copy$default(Tracer tracer, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tracer.version;
            }
            return tracer.copy(str);
        }

        @JvmStatic
        public static final Tracer fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.version;
        }

        public final Tracer copy(String str) {
            Intrinsics.checkNotNullParameter(str, "version");
            return new Tracer(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Tracer) && Intrinsics.areEqual((Object) this.version, (Object) ((Tracer) obj).version);
        }

        public int hashCode() {
            return this.version.hashCode();
        }

        public String toString() {
            return "Tracer(version=" + this.version + ")";
        }

        public Tracer(String str) {
            Intrinsics.checkNotNullParameter(str, "version");
            this.version = str;
        }

        public final String getVersion() {
            return this.version;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("version", this.version);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Tracer$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/tracing/model/SpanEvent$Tracer;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: SpanEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Tracer fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    String asString = JsonParser.parseString(str).getAsJsonObject().get("version").getAsString();
                    Intrinsics.checkNotNullExpressionValue(asString, "version");
                    return new Tracer(asString);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0003JE\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Usr;", "", "id", "", "name", "email", "additionalProperties", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "getEmail", "()Ljava/lang/String;", "getId", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SpanEvent.kt */
    public static final class Usr {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final String[] RESERVED_PROPERTIES = {"id", "name", NotificationCompat.CATEGORY_EMAIL};
        private final Map<String, Object> additionalProperties;
        private final String email;

        /* renamed from: id */
        private final String f151id;
        private final String name;

        public Usr() {
            this((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Usr copy$default(Usr usr, String str, String str2, String str3, Map<String, Object> map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = usr.f151id;
            }
            if ((i & 2) != 0) {
                str2 = usr.name;
            }
            if ((i & 4) != 0) {
                str3 = usr.email;
            }
            if ((i & 8) != 0) {
                map = usr.additionalProperties;
            }
            return usr.copy(str, str2, str3, map);
        }

        @JvmStatic
        public static final Usr fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f151id;
        }

        public final String component2() {
            return this.name;
        }

        public final String component3() {
            return this.email;
        }

        public final Map<String, Object> component4() {
            return this.additionalProperties;
        }

        public final Usr copy(String str, String str2, String str3, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            return new Usr(str, str2, str3, map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Usr)) {
                return false;
            }
            Usr usr = (Usr) obj;
            return Intrinsics.areEqual((Object) this.f151id, (Object) usr.f151id) && Intrinsics.areEqual((Object) this.name, (Object) usr.name) && Intrinsics.areEqual((Object) this.email, (Object) usr.email) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) usr.additionalProperties);
        }

        public int hashCode() {
            String str = this.f151id;
            int i = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.name;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.email;
            if (str3 != null) {
                i = str3.hashCode();
            }
            return ((hashCode2 + i) * 31) + this.additionalProperties.hashCode();
        }

        public String toString() {
            String str = this.f151id;
            String str2 = this.name;
            String str3 = this.email;
            return "Usr(id=" + str + ", name=" + str2 + ", email=" + str3 + ", additionalProperties=" + this.additionalProperties + ")";
        }

        public Usr(String str, String str2, String str3, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            this.f151id = str;
            this.name = str2;
            this.email = str3;
            this.additionalProperties = map;
        }

        public final String getId() {
            return this.f151id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Usr(String str, String str2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            String str = this.f151id;
            if (str != null) {
                jsonObject.addProperty("id", str);
            }
            String str2 = this.name;
            if (str2 != null) {
                jsonObject.addProperty("name", str2);
            }
            String str3 = this.email;
            if (str3 != null) {
                jsonObject.addProperty(NotificationCompat.CATEGORY_EMAIL, str3);
            }
            for (Map.Entry next : this.additionalProperties.entrySet()) {
                String str4 = (String) next.getKey();
                Object value = next.getValue();
                if (!ArraysKt.contains((T[]) RESERVED_PROPERTIES, str4)) {
                    jsonObject.add(str4, MiscUtilsKt.toJsonElement(value));
                }
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Usr$Companion;", "", "()V", "RESERVED_PROPERTIES", "", "", "getRESERVED_PROPERTIES$dd_sdk_android_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "fromJson", "Lcom/datadog/android/tracing/model/SpanEvent$Usr;", "serializedObject", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: SpanEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final String[] getRESERVED_PROPERTIES$dd_sdk_android_release() {
                return Usr.RESERVED_PROPERTIES;
            }

            @JvmStatic
            public final Usr fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    JsonElement jsonElement = asJsonObject.get("id");
                    String str2 = null;
                    String asString = jsonElement == null ? null : jsonElement.getAsString();
                    JsonElement jsonElement2 = asJsonObject.get("name");
                    String asString2 = jsonElement2 == null ? null : jsonElement2.getAsString();
                    JsonElement jsonElement3 = asJsonObject.get(NotificationCompat.CATEGORY_EMAIL);
                    if (jsonElement3 != null) {
                        str2 = jsonElement3.getAsString();
                    }
                    Map linkedHashMap = new LinkedHashMap();
                    for (Map.Entry next : asJsonObject.entrySet()) {
                        if (!ArraysKt.contains((T[]) getRESERVED_PROPERTIES$dd_sdk_android_release(), next.getKey())) {
                            Object key = next.getKey();
                            Intrinsics.checkNotNullExpressionValue(key, "entry.key");
                            linkedHashMap.put(key, next.getValue());
                        }
                    }
                    return new Usr(asString, asString2, str2, linkedHashMap);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Network;", "", "client", "Lcom/datadog/android/tracing/model/SpanEvent$Client;", "(Lcom/datadog/android/tracing/model/SpanEvent$Client;)V", "getClient", "()Lcom/datadog/android/tracing/model/SpanEvent$Client;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SpanEvent.kt */
    public static final class Network {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Client client;

        public static /* synthetic */ Network copy$default(Network network, Client client2, int i, Object obj) {
            if ((i & 1) != 0) {
                client2 = network.client;
            }
            return network.copy(client2);
        }

        @JvmStatic
        public static final Network fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final Client component1() {
            return this.client;
        }

        public final Network copy(Client client2) {
            Intrinsics.checkNotNullParameter(client2, Tags.SPAN_KIND_CLIENT);
            return new Network(client2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Network) && Intrinsics.areEqual((Object) this.client, (Object) ((Network) obj).client);
        }

        public int hashCode() {
            return this.client.hashCode();
        }

        public String toString() {
            return "Network(client=" + this.client + ")";
        }

        public Network(Client client2) {
            Intrinsics.checkNotNullParameter(client2, Tags.SPAN_KIND_CLIENT);
            this.client = client2;
        }

        public final Client getClient() {
            return this.client;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(Tags.SPAN_KIND_CLIENT, this.client.toJson());
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Network$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/tracing/model/SpanEvent$Network;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: SpanEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Network fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    String jsonElement = JsonParser.parseString(str).getAsJsonObject().get(Tags.SPAN_KIND_CLIENT).toString();
                    Client.Companion companion = Client.Companion;
                    Intrinsics.checkNotNullExpressionValue(jsonElement, "it");
                    return new Network(companion.fromJson(jsonElement));
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB=\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003JC\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\u0006\u0010\u001c\u001a\u00020\u001dJ\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006 "}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Client;", "", "simCarrier", "Lcom/datadog/android/tracing/model/SpanEvent$SimCarrier;", "signalStrength", "", "downlinkKbps", "uplinkKbps", "connectivity", "(Lcom/datadog/android/tracing/model/SpanEvent$SimCarrier;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getConnectivity", "()Ljava/lang/String;", "getDownlinkKbps", "getSignalStrength", "getSimCarrier", "()Lcom/datadog/android/tracing/model/SpanEvent$SimCarrier;", "getUplinkKbps", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SpanEvent.kt */
    public static final class Client {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String connectivity;
        private final String downlinkKbps;
        private final String signalStrength;
        private final SimCarrier simCarrier;
        private final String uplinkKbps;

        public static /* synthetic */ Client copy$default(Client client, SimCarrier simCarrier2, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                simCarrier2 = client.simCarrier;
            }
            if ((i & 2) != 0) {
                str = client.signalStrength;
            }
            String str5 = str;
            if ((i & 4) != 0) {
                str2 = client.downlinkKbps;
            }
            String str6 = str2;
            if ((i & 8) != 0) {
                str3 = client.uplinkKbps;
            }
            String str7 = str3;
            if ((i & 16) != 0) {
                str4 = client.connectivity;
            }
            return client.copy(simCarrier2, str5, str6, str7, str4);
        }

        @JvmStatic
        public static final Client fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final SimCarrier component1() {
            return this.simCarrier;
        }

        public final String component2() {
            return this.signalStrength;
        }

        public final String component3() {
            return this.downlinkKbps;
        }

        public final String component4() {
            return this.uplinkKbps;
        }

        public final String component5() {
            return this.connectivity;
        }

        public final Client copy(SimCarrier simCarrier2, String str, String str2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str4, "connectivity");
            return new Client(simCarrier2, str, str2, str3, str4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Client)) {
                return false;
            }
            Client client = (Client) obj;
            return Intrinsics.areEqual((Object) this.simCarrier, (Object) client.simCarrier) && Intrinsics.areEqual((Object) this.signalStrength, (Object) client.signalStrength) && Intrinsics.areEqual((Object) this.downlinkKbps, (Object) client.downlinkKbps) && Intrinsics.areEqual((Object) this.uplinkKbps, (Object) client.uplinkKbps) && Intrinsics.areEqual((Object) this.connectivity, (Object) client.connectivity);
        }

        public int hashCode() {
            SimCarrier simCarrier2 = this.simCarrier;
            int i = 0;
            int hashCode = (simCarrier2 == null ? 0 : simCarrier2.hashCode()) * 31;
            String str = this.signalStrength;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.downlinkKbps;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.uplinkKbps;
            if (str3 != null) {
                i = str3.hashCode();
            }
            return ((hashCode3 + i) * 31) + this.connectivity.hashCode();
        }

        public String toString() {
            SimCarrier simCarrier2 = this.simCarrier;
            String str = this.signalStrength;
            String str2 = this.downlinkKbps;
            String str3 = this.uplinkKbps;
            return "Client(simCarrier=" + simCarrier2 + ", signalStrength=" + str + ", downlinkKbps=" + str2 + ", uplinkKbps=" + str3 + ", connectivity=" + this.connectivity + ")";
        }

        public Client(SimCarrier simCarrier2, String str, String str2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str4, "connectivity");
            this.simCarrier = simCarrier2;
            this.signalStrength = str;
            this.downlinkKbps = str2;
            this.uplinkKbps = str3;
            this.connectivity = str4;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Client(SimCarrier simCarrier2, String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : simCarrier2, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, str4);
        }

        public final SimCarrier getSimCarrier() {
            return this.simCarrier;
        }

        public final String getSignalStrength() {
            return this.signalStrength;
        }

        public final String getDownlinkKbps() {
            return this.downlinkKbps;
        }

        public final String getUplinkKbps() {
            return this.uplinkKbps;
        }

        public final String getConnectivity() {
            return this.connectivity;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            SimCarrier simCarrier2 = this.simCarrier;
            if (simCarrier2 != null) {
                jsonObject.add("sim_carrier", simCarrier2.toJson());
            }
            String str = this.signalStrength;
            if (str != null) {
                jsonObject.addProperty("signal_strength", str);
            }
            String str2 = this.downlinkKbps;
            if (str2 != null) {
                jsonObject.addProperty("downlink_kbps", str2);
            }
            String str3 = this.uplinkKbps;
            if (str3 != null) {
                jsonObject.addProperty("uplink_kbps", str3);
            }
            jsonObject.addProperty("connectivity", this.connectivity);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$Client$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/tracing/model/SpanEvent$Client;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: SpanEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX WARNING: Removed duplicated region for block: B:11:0x0030 A[Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }] */
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0032 A[Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }] */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x003f A[Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }] */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x0041 A[Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }] */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x004e A[Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }] */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x004f A[Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }] */
            @kotlin.jvm.JvmStatic
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.datadog.android.tracing.model.SpanEvent.Client fromJson(java.lang.String r9) throws com.google.gson.JsonParseException {
                /*
                    r8 = this;
                    java.lang.String r8 = "connectivity"
                    java.lang.String r0 = "serializedObject"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                    com.google.gson.JsonElement r9 = com.google.gson.JsonParser.parseString(r9)     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    com.google.gson.JsonObject r9 = r9.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    java.lang.String r0 = "sim_carrier"
                    com.google.gson.JsonElement r0 = r9.get(r0)     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    r1 = 0
                    if (r0 != 0) goto L_0x001a
                L_0x0018:
                    r3 = r1
                    goto L_0x0028
                L_0x001a:
                    java.lang.String r0 = r0.toString()     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    if (r0 != 0) goto L_0x0021
                    goto L_0x0018
                L_0x0021:
                    com.datadog.android.tracing.model.SpanEvent$SimCarrier$Companion r2 = com.datadog.android.tracing.model.SpanEvent.SimCarrier.Companion     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    com.datadog.android.tracing.model.SpanEvent$SimCarrier r0 = r2.fromJson(r0)     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    r3 = r0
                L_0x0028:
                    java.lang.String r0 = "signal_strength"
                    com.google.gson.JsonElement r0 = r9.get(r0)     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    if (r0 != 0) goto L_0x0032
                    r4 = r1
                    goto L_0x0037
                L_0x0032:
                    java.lang.String r0 = r0.getAsString()     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    r4 = r0
                L_0x0037:
                    java.lang.String r0 = "downlink_kbps"
                    com.google.gson.JsonElement r0 = r9.get(r0)     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    if (r0 != 0) goto L_0x0041
                    r5 = r1
                    goto L_0x0046
                L_0x0041:
                    java.lang.String r0 = r0.getAsString()     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    r5 = r0
                L_0x0046:
                    java.lang.String r0 = "uplink_kbps"
                    com.google.gson.JsonElement r0 = r9.get(r0)     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    if (r0 != 0) goto L_0x004f
                    goto L_0x0053
                L_0x004f:
                    java.lang.String r1 = r0.getAsString()     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                L_0x0053:
                    r6 = r1
                    com.google.gson.JsonElement r9 = r9.get(r8)     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    java.lang.String r7 = r9.getAsString()     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    com.datadog.android.tracing.model.SpanEvent$Client r9 = new com.datadog.android.tracing.model.SpanEvent$Client     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    r2 = r9
                    r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ IllegalStateException -> 0x0071, NumberFormatException -> 0x0066 }
                    return r9
                L_0x0066:
                    r8 = move-exception
                    com.google.gson.JsonParseException r9 = new com.google.gson.JsonParseException
                    java.lang.String r8 = r8.getMessage()
                    r9.<init>((java.lang.String) r8)
                    throw r9
                L_0x0071:
                    r8 = move-exception
                    com.google.gson.JsonParseException r9 = new com.google.gson.JsonParseException
                    java.lang.String r8 = r8.getMessage()
                    r9.<init>((java.lang.String) r8)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.tracing.model.SpanEvent.Client.Companion.fromJson(java.lang.String):com.datadog.android.tracing.model.SpanEvent$Client");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$SimCarrier;", "", "id", "", "name", "(Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SpanEvent.kt */
    public static final class SimCarrier {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f150id;
        private final String name;

        public SimCarrier() {
            this((String) null, (String) null, 3, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ SimCarrier copy$default(SimCarrier simCarrier, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = simCarrier.f150id;
            }
            if ((i & 2) != 0) {
                str2 = simCarrier.name;
            }
            return simCarrier.copy(str, str2);
        }

        @JvmStatic
        public static final SimCarrier fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f150id;
        }

        public final String component2() {
            return this.name;
        }

        public final SimCarrier copy(String str, String str2) {
            return new SimCarrier(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SimCarrier)) {
                return false;
            }
            SimCarrier simCarrier = (SimCarrier) obj;
            return Intrinsics.areEqual((Object) this.f150id, (Object) simCarrier.f150id) && Intrinsics.areEqual((Object) this.name, (Object) simCarrier.name);
        }

        public int hashCode() {
            String str = this.f150id;
            int i = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.name;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            String str = this.f150id;
            return "SimCarrier(id=" + str + ", name=" + this.name + ")";
        }

        public SimCarrier(String str, String str2) {
            this.f150id = str;
            this.name = str2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SimCarrier(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        public final String getId() {
            return this.f150id;
        }

        public final String getName() {
            return this.name;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            String str = this.f150id;
            if (str != null) {
                jsonObject.addProperty("id", str);
            }
            String str2 = this.name;
            if (str2 != null) {
                jsonObject.addProperty("name", str2);
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/tracing/model/SpanEvent$SimCarrier$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/tracing/model/SpanEvent$SimCarrier;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: SpanEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final SimCarrier fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    JsonElement jsonElement = asJsonObject.get("id");
                    String str2 = null;
                    String asString = jsonElement == null ? null : jsonElement.getAsString();
                    JsonElement jsonElement2 = asJsonObject.get("name");
                    if (jsonElement2 != null) {
                        str2 = jsonElement2.getAsString();
                    }
                    return new SimCarrier(asString, str2);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }
}
