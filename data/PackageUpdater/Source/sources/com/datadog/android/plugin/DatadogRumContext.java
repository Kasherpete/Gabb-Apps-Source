package com.datadog.android.plugin;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/plugin/DatadogRumContext;", "", "applicationId", "", "sessionId", "viewId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getApplicationId", "()Ljava/lang/String;", "getSessionId", "getViewId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogRumContext.kt */
public final class DatadogRumContext {
    private final String applicationId;
    private final String sessionId;
    private final String viewId;

    public DatadogRumContext() {
        this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DatadogRumContext copy$default(DatadogRumContext datadogRumContext, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = datadogRumContext.applicationId;
        }
        if ((i & 2) != 0) {
            str2 = datadogRumContext.sessionId;
        }
        if ((i & 4) != 0) {
            str3 = datadogRumContext.viewId;
        }
        return datadogRumContext.copy(str, str2, str3);
    }

    public final String component1() {
        return this.applicationId;
    }

    public final String component2() {
        return this.sessionId;
    }

    public final String component3() {
        return this.viewId;
    }

    public final DatadogRumContext copy(String str, String str2, String str3) {
        return new DatadogRumContext(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DatadogRumContext)) {
            return false;
        }
        DatadogRumContext datadogRumContext = (DatadogRumContext) obj;
        return Intrinsics.areEqual((Object) this.applicationId, (Object) datadogRumContext.applicationId) && Intrinsics.areEqual((Object) this.sessionId, (Object) datadogRumContext.sessionId) && Intrinsics.areEqual((Object) this.viewId, (Object) datadogRumContext.viewId);
    }

    public int hashCode() {
        String str = this.applicationId;
        int i = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.sessionId;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.viewId;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        String str = this.applicationId;
        String str2 = this.sessionId;
        return "DatadogRumContext(applicationId=" + str + ", sessionId=" + str2 + ", viewId=" + this.viewId + ")";
    }

    public DatadogRumContext(String str, String str2, String str3) {
        this.applicationId = str;
        this.sessionId = str2;
        this.viewId = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DatadogRumContext(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getViewId() {
        return this.viewId;
    }
}
