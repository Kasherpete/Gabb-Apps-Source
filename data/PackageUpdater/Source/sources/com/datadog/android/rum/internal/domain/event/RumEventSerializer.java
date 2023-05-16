package com.datadog.android.rum.internal.domain.event;

import com.datadog.android.core.internal.constraints.DataConstraints;
import com.datadog.android.core.internal.constraints.DatadogDataConstraints;
import com.datadog.android.core.internal.persistence.Serializer;
import com.datadog.android.rum.RumAttributes;
import com.datadog.android.rum.model.ActionEvent;
import com.datadog.android.rum.model.ErrorEvent;
import com.datadog.android.rum.model.LongTaskEvent;
import com.datadog.android.rum.model.ResourceEvent;
import com.datadog.android.rum.model.ViewEvent;
import com.datadog.android.telemetry.model.TelemetryDebugEvent;
import com.datadog.android.telemetry.model.TelemetryErrorEvent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0015H\u0002J,\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00172\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0017H\u0002J,\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00172\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/event/RumEventSerializer;", "Lcom/datadog/android/core/internal/persistence/Serializer;", "", "dataConstraints", "Lcom/datadog/android/core/internal/constraints/DataConstraints;", "(Lcom/datadog/android/core/internal/constraints/DataConstraints;)V", "extractKnownAttributes", "Lcom/google/gson/JsonObject;", "jsonObject", "serialize", "", "model", "serializeActionEvent", "Lcom/datadog/android/rum/model/ActionEvent;", "serializeErrorEvent", "Lcom/datadog/android/rum/model/ErrorEvent;", "serializeLongTaskEvent", "Lcom/datadog/android/rum/model/LongTaskEvent;", "serializeResourceEvent", "Lcom/datadog/android/rum/model/ResourceEvent;", "serializeViewEvent", "Lcom/datadog/android/rum/model/ViewEvent;", "validateContextAttributes", "", "attributes", "validateUserAttributes", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumEventSerializer.kt */
public final class RumEventSerializer implements Serializer<Object> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String GLOBAL_ATTRIBUTE_PREFIX = "context";
    public static final String USER_ATTRIBUTE_PREFIX = "usr";
    public static final String USER_EXTRA_GROUP_VERBOSE_NAME = "user extra information";
    /* access modifiers changed from: private */
    public static final Set<String> crossPlatformTransitAttributes = SetsKt.setOf(RumAttributes.INTERNAL_TIMESTAMP, RumAttributes.INTERNAL_ERROR_TYPE, RumAttributes.INTERNAL_ERROR_SOURCE_TYPE, RumAttributes.INTERNAL_ERROR_IS_CRASH);
    /* access modifiers changed from: private */
    public static final Set<String> ignoredAttributes = SetsKt.setOf(RumAttributes.INTERNAL_TIMESTAMP, RumAttributes.INTERNAL_ERROR_TYPE, RumAttributes.INTERNAL_ERROR_SOURCE_TYPE, RumAttributes.INTERNAL_ERROR_IS_CRASH);
    /* access modifiers changed from: private */
    public static final Set<String> knownAttributes = SetsKt.setOf(RumAttributes.ACTION_GESTURE_DIRECTION, RumAttributes.ACTION_GESTURE_FROM_STATE, RumAttributes.ACTION_GESTURE_TO_STATE, RumAttributes.ACTION_TARGET_PARENT_RESOURCE_ID, RumAttributes.ACTION_TARGET_PARENT_CLASSNAME, RumAttributes.ACTION_TARGET_PARENT_INDEX, RumAttributes.ACTION_TARGET_CLASS_NAME, RumAttributes.ACTION_TARGET_RESOURCE_ID, RumAttributes.ACTION_TARGET_TITLE, RumAttributes.ERROR_RESOURCE_METHOD, RumAttributes.ERROR_RESOURCE_STATUS_CODE, RumAttributes.ERROR_RESOURCE_URL);
    private final DataConstraints dataConstraints;

    public RumEventSerializer() {
        this((DataConstraints) null, 1, (DefaultConstructorMarker) null);
    }

    public RumEventSerializer(DataConstraints dataConstraints2) {
        Intrinsics.checkNotNullParameter(dataConstraints2, "dataConstraints");
        this.dataConstraints = dataConstraints2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RumEventSerializer(DataConstraints dataConstraints2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DatadogDataConstraints() : dataConstraints2);
    }

    public String serialize(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "model");
        if (obj instanceof ViewEvent) {
            return serializeViewEvent((ViewEvent) obj);
        }
        if (obj instanceof ErrorEvent) {
            return serializeErrorEvent((ErrorEvent) obj);
        }
        if (obj instanceof ActionEvent) {
            return serializeActionEvent((ActionEvent) obj);
        }
        if (obj instanceof ResourceEvent) {
            return serializeResourceEvent((ResourceEvent) obj);
        }
        if (obj instanceof LongTaskEvent) {
            return serializeLongTaskEvent((LongTaskEvent) obj);
        }
        if (obj instanceof TelemetryDebugEvent) {
            String jsonElement = ((TelemetryDebugEvent) obj).toJson().toString();
            Intrinsics.checkNotNullExpressionValue(jsonElement, "{\n                model.….toString()\n            }");
            return jsonElement;
        } else if (obj instanceof TelemetryErrorEvent) {
            String jsonElement2 = ((TelemetryErrorEvent) obj).toJson().toString();
            Intrinsics.checkNotNullExpressionValue(jsonElement2, "{\n                model.….toString()\n            }");
            return jsonElement2;
        } else if (obj instanceof JsonObject) {
            return obj.toString();
        } else {
            String jsonObject = new JsonObject().toString();
            Intrinsics.checkNotNullExpressionValue(jsonObject, "{\n                JsonOb….toString()\n            }");
            return jsonObject;
        }
    }

    private final String serializeViewEvent(ViewEvent viewEvent) {
        ViewEvent.Usr usr = viewEvent.getUsr();
        ViewEvent.CustomTimings customTimings = null;
        ViewEvent.Usr copy$default = usr == null ? null : ViewEvent.Usr.copy$default(usr, (String) null, (String) null, (String) null, validateUserAttributes(viewEvent.getUsr().getAdditionalProperties()), 7, (Object) null);
        ViewEvent.Context context = viewEvent.getContext();
        ViewEvent.Context copy = context == null ? null : context.copy(validateContextAttributes(viewEvent.getContext().getAdditionalProperties()));
        ViewEvent.View view = viewEvent.getView();
        ViewEvent.CustomTimings customTimings2 = viewEvent.getView().getCustomTimings();
        if (customTimings2 != null) {
            customTimings = customTimings2.copy(this.dataConstraints.validateTimings(viewEvent.getView().getCustomTimings().getAdditionalProperties()));
        }
        JsonObject asJsonObject = ViewEvent.copy$default(viewEvent, 0, (ViewEvent.Application) null, (String) null, (ViewEvent.ViewEventSession) null, (ViewEvent.Source) null, ViewEvent.View.copy$default(view, (String) null, (String) null, (String) null, (String) null, (Long) null, (ViewEvent.LoadingType) null, 0, (Long) null, (Long) null, (Long) null, (Long) null, (Number) null, (Long) null, (Long) null, (Long) null, (Long) null, customTimings, (Boolean) null, (Boolean) null, (ViewEvent.Action) null, (ViewEvent.Error) null, (ViewEvent.Crash) null, (ViewEvent.LongTask) null, (ViewEvent.FrozenFrame) null, (ViewEvent.Resource) null, (List) null, (Number) null, (Number) null, (Number) null, (Number) null, (Number) null, (Number) null, -65537, (Object) null), copy$default, (ViewEvent.Connectivity) null, (ViewEvent.Synthetics) null, (ViewEvent.CiTest) null, (ViewEvent.C0867Dd) null, copy, 1951, (Object) null).toJson().getAsJsonObject();
        Intrinsics.checkNotNullExpressionValue(asJsonObject, "sanitizedModel.toJson().asJsonObject");
        String jsonObject = extractKnownAttributes(asJsonObject).toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject, "extractKnownAttributes(s….asJsonObject).toString()");
        return jsonObject;
    }

    private final String serializeErrorEvent(ErrorEvent errorEvent) {
        ErrorEvent.Usr usr = errorEvent.getUsr();
        ErrorEvent.Context context = null;
        ErrorEvent.Usr copy$default = usr == null ? null : ErrorEvent.Usr.copy$default(usr, (String) null, (String) null, (String) null, validateUserAttributes(errorEvent.getUsr().getAdditionalProperties()), 7, (Object) null);
        ErrorEvent.Context context2 = errorEvent.getContext();
        if (context2 != null) {
            context = context2.copy(validateContextAttributes(errorEvent.getContext().getAdditionalProperties()));
        }
        JsonObject asJsonObject = ErrorEvent.copy$default(errorEvent, 0, (ErrorEvent.Application) null, (String) null, (ErrorEvent.ErrorEventSession) null, (ErrorEvent.ErrorEventSource) null, (ErrorEvent.View) null, copy$default, (ErrorEvent.Connectivity) null, (ErrorEvent.Synthetics) null, (ErrorEvent.CiTest) null, (ErrorEvent.C0864Dd) null, context, (ErrorEvent.Error) null, (ErrorEvent.Action) null, 14271, (Object) null).toJson().getAsJsonObject();
        Intrinsics.checkNotNullExpressionValue(asJsonObject, "sanitizedModel.toJson().asJsonObject");
        String jsonObject = extractKnownAttributes(asJsonObject).toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject, "extractKnownAttributes(s….asJsonObject).toString()");
        return jsonObject;
    }

    private final String serializeResourceEvent(ResourceEvent resourceEvent) {
        ResourceEvent.Usr usr = resourceEvent.getUsr();
        ResourceEvent.Context context = null;
        ResourceEvent.Usr copy$default = usr == null ? null : ResourceEvent.Usr.copy$default(usr, (String) null, (String) null, (String) null, validateUserAttributes(resourceEvent.getUsr().getAdditionalProperties()), 7, (Object) null);
        ResourceEvent.Context context2 = resourceEvent.getContext();
        if (context2 != null) {
            context = context2.copy(validateContextAttributes(resourceEvent.getContext().getAdditionalProperties()));
        }
        JsonObject asJsonObject = ResourceEvent.copy$default(resourceEvent, 0, (ResourceEvent.Application) null, (String) null, (ResourceEvent.ResourceEventSession) null, (ResourceEvent.Source) null, (ResourceEvent.View) null, copy$default, (ResourceEvent.Connectivity) null, (ResourceEvent.Synthetics) null, (ResourceEvent.CiTest) null, (ResourceEvent.C0866Dd) null, context, (ResourceEvent.Resource) null, (ResourceEvent.Action) null, 14271, (Object) null).toJson().getAsJsonObject();
        Intrinsics.checkNotNullExpressionValue(asJsonObject, "sanitizedModel.toJson().asJsonObject");
        String jsonObject = extractKnownAttributes(asJsonObject).toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject, "extractKnownAttributes(s….asJsonObject).toString()");
        return jsonObject;
    }

    private final String serializeActionEvent(ActionEvent actionEvent) {
        ActionEvent.Usr usr = actionEvent.getUsr();
        ActionEvent.Context context = null;
        ActionEvent.Usr copy$default = usr == null ? null : ActionEvent.Usr.copy$default(usr, (String) null, (String) null, (String) null, validateUserAttributes(actionEvent.getUsr().getAdditionalProperties()), 7, (Object) null);
        ActionEvent.Context context2 = actionEvent.getContext();
        if (context2 != null) {
            context = context2.copy(validateContextAttributes(actionEvent.getContext().getAdditionalProperties()));
        }
        JsonObject asJsonObject = ActionEvent.copy$default(actionEvent, 0, (ActionEvent.Application) null, (String) null, (ActionEvent.ActionEventSession) null, (ActionEvent.Source) null, (ActionEvent.View) null, copy$default, (ActionEvent.Connectivity) null, (ActionEvent.Synthetics) null, (ActionEvent.CiTest) null, (ActionEvent.C0863Dd) null, context, (ActionEvent.Action) null, 6079, (Object) null).toJson().getAsJsonObject();
        Intrinsics.checkNotNullExpressionValue(asJsonObject, "sanitizedModel.toJson().asJsonObject");
        String jsonObject = extractKnownAttributes(asJsonObject).toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject, "extractKnownAttributes(s….asJsonObject).toString()");
        return jsonObject;
    }

    private final String serializeLongTaskEvent(LongTaskEvent longTaskEvent) {
        LongTaskEvent.Usr usr = longTaskEvent.getUsr();
        LongTaskEvent.Context context = null;
        LongTaskEvent.Usr copy$default = usr == null ? null : LongTaskEvent.Usr.copy$default(usr, (String) null, (String) null, (String) null, validateUserAttributes(longTaskEvent.getUsr().getAdditionalProperties()), 7, (Object) null);
        LongTaskEvent.Context context2 = longTaskEvent.getContext();
        if (context2 != null) {
            context = context2.copy(validateContextAttributes(longTaskEvent.getContext().getAdditionalProperties()));
        }
        JsonObject asJsonObject = LongTaskEvent.copy$default(longTaskEvent, 0, (LongTaskEvent.Application) null, (String) null, (LongTaskEvent.LongTaskEventSession) null, (LongTaskEvent.Source) null, (LongTaskEvent.View) null, copy$default, (LongTaskEvent.Connectivity) null, (LongTaskEvent.Synthetics) null, (LongTaskEvent.CiTest) null, (LongTaskEvent.C0865Dd) null, context, (LongTaskEvent.LongTask) null, (LongTaskEvent.Action) null, 14271, (Object) null).toJson().getAsJsonObject();
        Intrinsics.checkNotNullExpressionValue(asJsonObject, "sanitizedModel.toJson().asJsonObject");
        String jsonObject = extractKnownAttributes(asJsonObject).toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject, "extractKnownAttributes(s….asJsonObject).toString()");
        return jsonObject;
    }

    private final Map<String, Object> validateContextAttributes(Map<String, ? extends Object> map) {
        DataConstraints dataConstraints2 = this.dataConstraints;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            if (!crossPlatformTransitAttributes.contains((String) next.getKey())) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        return DataConstraints.DefaultImpls.validateAttributes$default(dataConstraints2, linkedHashMap, GLOBAL_ATTRIBUTE_PREFIX, (String) null, ignoredAttributes, 4, (Object) null);
    }

    private final Map<String, Object> validateUserAttributes(Map<String, ? extends Object> map) {
        return this.dataConstraints.validateAttributes(map, "usr", "user extra information", ignoredAttributes);
    }

    private final JsonObject extractKnownAttributes(JsonObject jsonObject) {
        if (jsonObject.has(GLOBAL_ATTRIBUTE_PREFIX)) {
            JsonObject asJsonObject = jsonObject.getAsJsonObject(GLOBAL_ATTRIBUTE_PREFIX);
            Set<Map.Entry<String, JsonElement>> entrySet = asJsonObject.entrySet();
            Intrinsics.checkNotNullExpressionValue(entrySet, "contextObject\n                .entrySet()");
            Collection arrayList = new ArrayList();
            for (Object next : entrySet) {
                if (knownAttributes.contains(((Map.Entry) next).getKey())) {
                    arrayList.add(next);
                }
            }
            for (Map.Entry entry : (List) arrayList) {
                asJsonObject.remove((String) entry.getKey());
                jsonObject.add((String) entry.getKey(), (JsonElement) entry.getValue());
            }
        }
        return jsonObject;
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u000f"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/event/RumEventSerializer$Companion;", "", "()V", "GLOBAL_ATTRIBUTE_PREFIX", "", "USER_ATTRIBUTE_PREFIX", "USER_EXTRA_GROUP_VERBOSE_NAME", "crossPlatformTransitAttributes", "", "getCrossPlatformTransitAttributes$dd_sdk_android_release", "()Ljava/util/Set;", "ignoredAttributes", "getIgnoredAttributes$dd_sdk_android_release", "knownAttributes", "getKnownAttributes$dd_sdk_android_release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumEventSerializer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getKnownAttributes$dd_sdk_android_release() {
            return RumEventSerializer.knownAttributes;
        }

        public final Set<String> getIgnoredAttributes$dd_sdk_android_release() {
            return RumEventSerializer.ignoredAttributes;
        }

        public final Set<String> getCrossPlatformTransitAttributes$dd_sdk_android_release() {
            return RumEventSerializer.crossPlatformTransitAttributes;
        }
    }
}
