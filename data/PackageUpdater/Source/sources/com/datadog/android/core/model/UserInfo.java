package com.datadog.android.core.model;

import androidx.core.app.NotificationCompat;
import com.datadog.android.core.internal.utils.MiscUtilsKt;
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

@Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0003JE\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/core/model/UserInfo;", "", "id", "", "name", "email", "additionalProperties", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "getEmail", "()Ljava/lang/String;", "getId", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UserInfo.kt */
public final class UserInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String[] RESERVED_PROPERTIES = {"id", "name", NotificationCompat.CATEGORY_EMAIL};
    private final Map<String, Object> additionalProperties;
    private final String email;

    /* renamed from: id */
    private final String f97id;
    private final String name;

    public UserInfo() {
        this((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ UserInfo copy$default(UserInfo userInfo, String str, String str2, String str3, Map<String, Object> map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userInfo.f97id;
        }
        if ((i & 2) != 0) {
            str2 = userInfo.name;
        }
        if ((i & 4) != 0) {
            str3 = userInfo.email;
        }
        if ((i & 8) != 0) {
            map = userInfo.additionalProperties;
        }
        return userInfo.copy(str, str2, str3, map);
    }

    @JvmStatic
    public static final UserInfo fromJson(String str) throws JsonParseException {
        return Companion.fromJson(str);
    }

    public final String component1() {
        return this.f97id;
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

    public final UserInfo copy(String str, String str2, String str3, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "additionalProperties");
        return new UserInfo(str, str2, str3, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserInfo)) {
            return false;
        }
        UserInfo userInfo = (UserInfo) obj;
        return Intrinsics.areEqual((Object) this.f97id, (Object) userInfo.f97id) && Intrinsics.areEqual((Object) this.name, (Object) userInfo.name) && Intrinsics.areEqual((Object) this.email, (Object) userInfo.email) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) userInfo.additionalProperties);
    }

    public int hashCode() {
        String str = this.f97id;
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
        String str = this.f97id;
        String str2 = this.name;
        String str3 = this.email;
        return "UserInfo(id=" + str + ", name=" + str2 + ", email=" + str3 + ", additionalProperties=" + this.additionalProperties + ")";
    }

    public UserInfo(String str, String str2, String str3, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "additionalProperties");
        this.f97id = str;
        this.name = str2;
        this.email = str3;
        this.additionalProperties = map;
    }

    public final String getId() {
        return this.f97id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ UserInfo(String str, String str2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public final JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        String str = this.f97id;
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

    @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/core/model/UserInfo$Companion;", "", "()V", "RESERVED_PROPERTIES", "", "", "getRESERVED_PROPERTIES$dd_sdk_android_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "fromJson", "Lcom/datadog/android/core/model/UserInfo;", "serializedObject", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: UserInfo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String[] getRESERVED_PROPERTIES$dd_sdk_android_release() {
            return UserInfo.RESERVED_PROPERTIES;
        }

        @JvmStatic
        public final UserInfo fromJson(String str) throws JsonParseException {
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
                return new UserInfo(asString, asString2, str2, linkedHashMap);
            } catch (IllegalStateException e) {
                throw new JsonParseException(e.getMessage());
            } catch (NumberFormatException e2) {
                throw new JsonParseException(e2.getMessage());
            }
        }
    }
}
