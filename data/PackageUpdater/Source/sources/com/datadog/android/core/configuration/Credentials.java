package com.datadog.android.core.configuration;

import com.datadog.android.rum.RumAttributes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J?\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001c"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Credentials;", "", "clientToken", "", "envName", "variant", "rumApplicationId", "serviceName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getClientToken", "()Ljava/lang/String;", "getEnvName", "getRumApplicationId", "getServiceName", "getVariant", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Credentials.kt */
public final class Credentials {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NO_VARIANT = "";
    private final String clientToken;
    private final String envName;
    private final String rumApplicationId;
    private final String serviceName;
    private final String variant;

    public static /* synthetic */ Credentials copy$default(Credentials credentials, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = credentials.clientToken;
        }
        if ((i & 2) != 0) {
            str2 = credentials.envName;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = credentials.variant;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = credentials.rumApplicationId;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = credentials.serviceName;
        }
        return credentials.copy(str, str6, str7, str8, str5);
    }

    public final String component1() {
        return this.clientToken;
    }

    public final String component2() {
        return this.envName;
    }

    public final String component3() {
        return this.variant;
    }

    public final String component4() {
        return this.rumApplicationId;
    }

    public final String component5() {
        return this.serviceName;
    }

    public final Credentials copy(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "clientToken");
        Intrinsics.checkNotNullParameter(str2, "envName");
        Intrinsics.checkNotNullParameter(str3, RumAttributes.VARIANT);
        return new Credentials(str, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Credentials)) {
            return false;
        }
        Credentials credentials = (Credentials) obj;
        return Intrinsics.areEqual((Object) this.clientToken, (Object) credentials.clientToken) && Intrinsics.areEqual((Object) this.envName, (Object) credentials.envName) && Intrinsics.areEqual((Object) this.variant, (Object) credentials.variant) && Intrinsics.areEqual((Object) this.rumApplicationId, (Object) credentials.rumApplicationId) && Intrinsics.areEqual((Object) this.serviceName, (Object) credentials.serviceName);
    }

    public int hashCode() {
        int hashCode = ((((this.clientToken.hashCode() * 31) + this.envName.hashCode()) * 31) + this.variant.hashCode()) * 31;
        String str = this.rumApplicationId;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.serviceName;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        String str = this.clientToken;
        String str2 = this.envName;
        String str3 = this.variant;
        String str4 = this.rumApplicationId;
        return "Credentials(clientToken=" + str + ", envName=" + str2 + ", variant=" + str3 + ", rumApplicationId=" + str4 + ", serviceName=" + this.serviceName + ")";
    }

    public Credentials(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "clientToken");
        Intrinsics.checkNotNullParameter(str2, "envName");
        Intrinsics.checkNotNullParameter(str3, RumAttributes.VARIANT);
        this.clientToken = str;
        this.envName = str2;
        this.variant = str3;
        this.rumApplicationId = str4;
        this.serviceName = str5;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Credentials(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? null : str5);
    }

    public final String getClientToken() {
        return this.clientToken;
    }

    public final String getEnvName() {
        return this.envName;
    }

    public final String getVariant() {
        return this.variant;
    }

    public final String getRumApplicationId() {
        return this.rumApplicationId;
    }

    public final String getServiceName() {
        return this.serviceName;
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Credentials$Companion;", "", "()V", "NO_VARIANT", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Credentials.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
