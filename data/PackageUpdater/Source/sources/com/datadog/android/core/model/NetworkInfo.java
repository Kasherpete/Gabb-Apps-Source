package com.datadog.android.core.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\b\u0018\u0000 )2\u00020\u0001:\u0002)*BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J`\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\u0006\u0010&\u001a\u00020'J\t\u0010(\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0015\u0010\u000eR\u0015\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0016\u0010\u000eR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0017\u0010\u000e¨\u0006+"}, mo20735d2 = {"Lcom/datadog/android/core/model/NetworkInfo;", "", "connectivity", "Lcom/datadog/android/core/model/NetworkInfo$Connectivity;", "carrierName", "", "carrierId", "", "upKbps", "downKbps", "strength", "cellularTechnology", "(Lcom/datadog/android/core/model/NetworkInfo$Connectivity;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)V", "getCarrierId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCarrierName", "()Ljava/lang/String;", "getCellularTechnology", "getConnectivity", "()Lcom/datadog/android/core/model/NetworkInfo$Connectivity;", "getDownKbps", "getStrength", "getUpKbps", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Lcom/datadog/android/core/model/NetworkInfo$Connectivity;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)Lcom/datadog/android/core/model/NetworkInfo;", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "Connectivity", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NetworkInfo.kt */
public final class NetworkInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Long carrierId;
    private final String carrierName;
    private final String cellularTechnology;
    private final Connectivity connectivity;
    private final Long downKbps;
    private final Long strength;
    private final Long upKbps;

    public NetworkInfo() {
        this((Connectivity) null, (String) null, (Long) null, (Long) null, (Long) null, (Long) null, (String) null, 127, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ NetworkInfo copy$default(NetworkInfo networkInfo, Connectivity connectivity2, String str, Long l, Long l2, Long l3, Long l4, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            connectivity2 = networkInfo.connectivity;
        }
        if ((i & 2) != 0) {
            str = networkInfo.carrierName;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            l = networkInfo.carrierId;
        }
        Long l5 = l;
        if ((i & 8) != 0) {
            l2 = networkInfo.upKbps;
        }
        Long l6 = l2;
        if ((i & 16) != 0) {
            l3 = networkInfo.downKbps;
        }
        Long l7 = l3;
        if ((i & 32) != 0) {
            l4 = networkInfo.strength;
        }
        Long l8 = l4;
        if ((i & 64) != 0) {
            str2 = networkInfo.cellularTechnology;
        }
        return networkInfo.copy(connectivity2, str3, l5, l6, l7, l8, str2);
    }

    @JvmStatic
    public static final NetworkInfo fromJson(String str) throws JsonParseException {
        return Companion.fromJson(str);
    }

    public final Connectivity component1() {
        return this.connectivity;
    }

    public final String component2() {
        return this.carrierName;
    }

    public final Long component3() {
        return this.carrierId;
    }

    public final Long component4() {
        return this.upKbps;
    }

    public final Long component5() {
        return this.downKbps;
    }

    public final Long component6() {
        return this.strength;
    }

    public final String component7() {
        return this.cellularTechnology;
    }

    public final NetworkInfo copy(Connectivity connectivity2, String str, Long l, Long l2, Long l3, Long l4, String str2) {
        Intrinsics.checkNotNullParameter(connectivity2, "connectivity");
        return new NetworkInfo(connectivity2, str, l, l2, l3, l4, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetworkInfo)) {
            return false;
        }
        NetworkInfo networkInfo = (NetworkInfo) obj;
        return this.connectivity == networkInfo.connectivity && Intrinsics.areEqual((Object) this.carrierName, (Object) networkInfo.carrierName) && Intrinsics.areEqual((Object) this.carrierId, (Object) networkInfo.carrierId) && Intrinsics.areEqual((Object) this.upKbps, (Object) networkInfo.upKbps) && Intrinsics.areEqual((Object) this.downKbps, (Object) networkInfo.downKbps) && Intrinsics.areEqual((Object) this.strength, (Object) networkInfo.strength) && Intrinsics.areEqual((Object) this.cellularTechnology, (Object) networkInfo.cellularTechnology);
    }

    public int hashCode() {
        int hashCode = this.connectivity.hashCode() * 31;
        String str = this.carrierName;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.carrierId;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.upKbps;
        int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.downKbps;
        int hashCode5 = (hashCode4 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Long l4 = this.strength;
        int hashCode6 = (hashCode5 + (l4 == null ? 0 : l4.hashCode())) * 31;
        String str2 = this.cellularTechnology;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode6 + i;
    }

    public String toString() {
        Connectivity connectivity2 = this.connectivity;
        String str = this.carrierName;
        Long l = this.carrierId;
        Long l2 = this.upKbps;
        Long l3 = this.downKbps;
        Long l4 = this.strength;
        return "NetworkInfo(connectivity=" + connectivity2 + ", carrierName=" + str + ", carrierId=" + l + ", upKbps=" + l2 + ", downKbps=" + l3 + ", strength=" + l4 + ", cellularTechnology=" + this.cellularTechnology + ")";
    }

    public NetworkInfo(Connectivity connectivity2, String str, Long l, Long l2, Long l3, Long l4, String str2) {
        Intrinsics.checkNotNullParameter(connectivity2, "connectivity");
        this.connectivity = connectivity2;
        this.carrierName = str;
        this.carrierId = l;
        this.upKbps = l2;
        this.downKbps = l3;
        this.strength = l4;
        this.cellularTechnology = str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ NetworkInfo(com.datadog.android.core.model.NetworkInfo.Connectivity r6, java.lang.String r7, java.lang.Long r8, java.lang.Long r9, java.lang.Long r10, java.lang.Long r11, java.lang.String r12, int r13, kotlin.jvm.internal.DefaultConstructorMarker r14) {
        /*
            r5 = this;
            r14 = r13 & 1
            if (r14 == 0) goto L_0x0006
            com.datadog.android.core.model.NetworkInfo$Connectivity r6 = com.datadog.android.core.model.NetworkInfo.Connectivity.NETWORK_NOT_CONNECTED
        L_0x0006:
            r14 = r13 & 2
            r0 = 0
            if (r14 == 0) goto L_0x000d
            r14 = r0
            goto L_0x000e
        L_0x000d:
            r14 = r7
        L_0x000e:
            r7 = r13 & 4
            if (r7 == 0) goto L_0x0014
            r1 = r0
            goto L_0x0015
        L_0x0014:
            r1 = r8
        L_0x0015:
            r7 = r13 & 8
            if (r7 == 0) goto L_0x001b
            r2 = r0
            goto L_0x001c
        L_0x001b:
            r2 = r9
        L_0x001c:
            r7 = r13 & 16
            if (r7 == 0) goto L_0x0022
            r3 = r0
            goto L_0x0023
        L_0x0022:
            r3 = r10
        L_0x0023:
            r7 = r13 & 32
            if (r7 == 0) goto L_0x0029
            r4 = r0
            goto L_0x002a
        L_0x0029:
            r4 = r11
        L_0x002a:
            r7 = r13 & 64
            if (r7 == 0) goto L_0x002f
            goto L_0x0030
        L_0x002f:
            r0 = r12
        L_0x0030:
            r7 = r5
            r8 = r6
            r9 = r14
            r10 = r1
            r11 = r2
            r12 = r3
            r13 = r4
            r14 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.model.NetworkInfo.<init>(com.datadog.android.core.model.NetworkInfo$Connectivity, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Connectivity getConnectivity() {
        return this.connectivity;
    }

    public final String getCarrierName() {
        return this.carrierName;
    }

    public final Long getCarrierId() {
        return this.carrierId;
    }

    public final Long getUpKbps() {
        return this.upKbps;
    }

    public final Long getDownKbps() {
        return this.downKbps;
    }

    public final Long getStrength() {
        return this.strength;
    }

    public final String getCellularTechnology() {
        return this.cellularTechnology;
    }

    public final JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("connectivity", this.connectivity.toJson());
        String str = this.carrierName;
        if (str != null) {
            jsonObject.addProperty("carrier_name", str);
        }
        Long l = this.carrierId;
        if (l != null) {
            jsonObject.addProperty("carrier_id", (Number) Long.valueOf(l.longValue()));
        }
        Long l2 = this.upKbps;
        if (l2 != null) {
            jsonObject.addProperty("up_kbps", (Number) Long.valueOf(l2.longValue()));
        }
        Long l3 = this.downKbps;
        if (l3 != null) {
            jsonObject.addProperty("down_kbps", (Number) Long.valueOf(l3.longValue()));
        }
        Long l4 = this.strength;
        if (l4 != null) {
            jsonObject.addProperty("strength", (Number) Long.valueOf(l4.longValue()));
        }
        String str2 = this.cellularTechnology;
        if (str2 != null) {
            jsonObject.addProperty("cellular_technology", str2);
        }
        return jsonObject;
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/model/NetworkInfo$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/core/model/NetworkInfo;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: NetworkInfo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final NetworkInfo fromJson(String str) throws JsonParseException {
            Intrinsics.checkNotNullParameter(str, "serializedObject");
            try {
                JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                String asString = asJsonObject.get("connectivity").getAsString();
                Connectivity.Companion companion = Connectivity.Companion;
                Intrinsics.checkNotNullExpressionValue(asString, "it");
                Connectivity fromJson = companion.fromJson(asString);
                JsonElement jsonElement = asJsonObject.get("carrier_name");
                String asString2 = jsonElement == null ? null : jsonElement.getAsString();
                JsonElement jsonElement2 = asJsonObject.get("carrier_id");
                Long valueOf = jsonElement2 == null ? null : Long.valueOf(jsonElement2.getAsLong());
                JsonElement jsonElement3 = asJsonObject.get("up_kbps");
                Long valueOf2 = jsonElement3 == null ? null : Long.valueOf(jsonElement3.getAsLong());
                JsonElement jsonElement4 = asJsonObject.get("down_kbps");
                Long valueOf3 = jsonElement4 == null ? null : Long.valueOf(jsonElement4.getAsLong());
                JsonElement jsonElement5 = asJsonObject.get("strength");
                Long valueOf4 = jsonElement5 == null ? null : Long.valueOf(jsonElement5.getAsLong());
                JsonElement jsonElement6 = asJsonObject.get("cellular_technology");
                return new NetworkInfo(fromJson, asString2, valueOf, valueOf2, valueOf3, valueOf4, jsonElement6 == null ? null : jsonElement6.getAsString());
            } catch (IllegalStateException e) {
                throw new JsonParseException(e.getMessage());
            } catch (NumberFormatException e2) {
                throw new JsonParseException(e2.getMessage());
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/core/model/NetworkInfo$Connectivity;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "NETWORK_NOT_CONNECTED", "NETWORK_ETHERNET", "NETWORK_WIFI", "NETWORK_WIMAX", "NETWORK_BLUETOOTH", "NETWORK_2G", "NETWORK_3G", "NETWORK_4G", "NETWORK_5G", "NETWORK_MOBILE_OTHER", "NETWORK_CELLULAR", "NETWORK_OTHER", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: NetworkInfo.kt */
    public enum Connectivity {
        NETWORK_NOT_CONNECTED("network_not_connected"),
        NETWORK_ETHERNET("network_ethernet"),
        NETWORK_WIFI("network_wifi"),
        NETWORK_WIMAX("network_wimax"),
        NETWORK_BLUETOOTH("network_bluetooth"),
        NETWORK_2G("network_2G"),
        NETWORK_3G("network_3G"),
        NETWORK_4G("network_4G"),
        NETWORK_5G("network_5G"),
        NETWORK_MOBILE_OTHER("network_mobile_other"),
        NETWORK_CELLULAR("network_cellular"),
        NETWORK_OTHER("network_other");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final Connectivity fromJson(String str) {
            return Companion.fromJson(str);
        }

        private Connectivity(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/model/NetworkInfo$Connectivity$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/core/model/NetworkInfo$Connectivity;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: NetworkInfo.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Connectivity fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                Connectivity[] values = Connectivity.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    Connectivity connectivity = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) connectivity.jsonValue, (Object) str)) {
                        return connectivity;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }
}
