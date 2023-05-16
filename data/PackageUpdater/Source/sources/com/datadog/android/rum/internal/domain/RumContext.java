package com.datadog.android.rum.internal.domain;

import com.datadog.android.rum.internal.domain.scope.RumViewScope;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 #2\u00020\u0001:\u0001#BS\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JW\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006$"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/RumContext;", "", "applicationId", "", "sessionId", "viewId", "viewName", "viewUrl", "actionId", "viewType", "Lcom/datadog/android/rum/internal/domain/scope/RumViewScope$RumViewType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/scope/RumViewScope$RumViewType;)V", "getActionId", "()Ljava/lang/String;", "getApplicationId", "getSessionId", "getViewId", "getViewName", "getViewType", "()Lcom/datadog/android/rum/internal/domain/scope/RumViewScope$RumViewType;", "getViewUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumContext.kt */
public final class RumContext {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String NULL_UUID;
    private final String actionId;
    private final String applicationId;
    private final String sessionId;
    private final String viewId;
    private final String viewName;
    private final RumViewScope.RumViewType viewType;
    private final String viewUrl;

    public RumContext() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (RumViewScope.RumViewType) null, 127, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ RumContext copy$default(RumContext rumContext, String str, String str2, String str3, String str4, String str5, String str6, RumViewScope.RumViewType rumViewType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rumContext.applicationId;
        }
        if ((i & 2) != 0) {
            str2 = rumContext.sessionId;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = rumContext.viewId;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = rumContext.viewName;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = rumContext.viewUrl;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = rumContext.actionId;
        }
        String str11 = str6;
        if ((i & 64) != 0) {
            rumViewType = rumContext.viewType;
        }
        return rumContext.copy(str, str7, str8, str9, str10, str11, rumViewType);
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

    public final String component4() {
        return this.viewName;
    }

    public final String component5() {
        return this.viewUrl;
    }

    public final String component6() {
        return this.actionId;
    }

    public final RumViewScope.RumViewType component7() {
        return this.viewType;
    }

    public final RumContext copy(String str, String str2, String str3, String str4, String str5, String str6, RumViewScope.RumViewType rumViewType) {
        Intrinsics.checkNotNullParameter(str, "applicationId");
        Intrinsics.checkNotNullParameter(str2, "sessionId");
        Intrinsics.checkNotNullParameter(rumViewType, "viewType");
        return new RumContext(str, str2, str3, str4, str5, str6, rumViewType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RumContext)) {
            return false;
        }
        RumContext rumContext = (RumContext) obj;
        return Intrinsics.areEqual((Object) this.applicationId, (Object) rumContext.applicationId) && Intrinsics.areEqual((Object) this.sessionId, (Object) rumContext.sessionId) && Intrinsics.areEqual((Object) this.viewId, (Object) rumContext.viewId) && Intrinsics.areEqual((Object) this.viewName, (Object) rumContext.viewName) && Intrinsics.areEqual((Object) this.viewUrl, (Object) rumContext.viewUrl) && Intrinsics.areEqual((Object) this.actionId, (Object) rumContext.actionId) && this.viewType == rumContext.viewType;
    }

    public int hashCode() {
        int hashCode = ((this.applicationId.hashCode() * 31) + this.sessionId.hashCode()) * 31;
        String str = this.viewId;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.viewName;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.viewUrl;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.actionId;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return ((hashCode4 + i) * 31) + this.viewType.hashCode();
    }

    public String toString() {
        String str = this.applicationId;
        String str2 = this.sessionId;
        String str3 = this.viewId;
        String str4 = this.viewName;
        String str5 = this.viewUrl;
        String str6 = this.actionId;
        return "RumContext(applicationId=" + str + ", sessionId=" + str2 + ", viewId=" + str3 + ", viewName=" + str4 + ", viewUrl=" + str5 + ", actionId=" + str6 + ", viewType=" + this.viewType + ")";
    }

    public RumContext(String str, String str2, String str3, String str4, String str5, String str6, RumViewScope.RumViewType rumViewType) {
        Intrinsics.checkNotNullParameter(str, "applicationId");
        Intrinsics.checkNotNullParameter(str2, "sessionId");
        Intrinsics.checkNotNullParameter(rumViewType, "viewType");
        this.applicationId = str;
        this.sessionId = str2;
        this.viewId = str3;
        this.viewName = str4;
        this.viewUrl = str5;
        this.actionId = str6;
        this.viewType = rumViewType;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ RumContext(java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, com.datadog.android.rum.internal.domain.scope.RumViewScope.RumViewType r12, int r13, kotlin.jvm.internal.DefaultConstructorMarker r14) {
        /*
            r5 = this;
            r14 = r13 & 1
            if (r14 == 0) goto L_0x0006
            java.lang.String r6 = NULL_UUID
        L_0x0006:
            r14 = r13 & 2
            if (r14 == 0) goto L_0x000c
            java.lang.String r7 = NULL_UUID
        L_0x000c:
            r14 = r7
            r7 = r13 & 4
            r0 = 0
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
            if (r7 == 0) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            r0 = r11
        L_0x0029:
            r7 = r13 & 64
            if (r7 == 0) goto L_0x002f
            com.datadog.android.rum.internal.domain.scope.RumViewScope$RumViewType r12 = com.datadog.android.rum.internal.domain.scope.RumViewScope.RumViewType.NONE
        L_0x002f:
            r4 = r12
            r7 = r5
            r8 = r6
            r9 = r14
            r10 = r1
            r11 = r2
            r12 = r3
            r13 = r0
            r14 = r4
            r7.<init>(r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.domain.RumContext.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.datadog.android.rum.internal.domain.scope.RumViewScope$RumViewType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
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

    public final String getViewName() {
        return this.viewName;
    }

    public final String getViewUrl() {
        return this.viewUrl;
    }

    public final String getActionId() {
        return this.actionId;
    }

    public final RumViewScope.RumViewType getViewType() {
        return this.viewType;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/RumContext$Companion;", "", "()V", "NULL_UUID", "", "getNULL_UUID", "()Ljava/lang/String;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumContext.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getNULL_UUID() {
            return RumContext.NULL_UUID;
        }
    }

    static {
        String uuid = new UUID(0, 0).toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "UUID(0, 0).toString()");
        NULL_UUID = uuid;
    }
}
