package com.datadog.android.rum.internal.net;

import com.datadog.android.core.internal.net.DataOkHttpUploaderV2;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\b\u0010\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00130\u0012H\u0014R\u001b\u0010\f\u001a\u00020\u00038BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/net/RumOkHttpUploaderV2;", "Lcom/datadog/android/core/internal/net/DataOkHttpUploaderV2;", "endpoint", "", "clientToken", "source", "sdkVersion", "callFactory", "Lokhttp3/Call$Factory;", "androidInfoProvider", "Lcom/datadog/android/core/internal/system/AndroidInfoProvider;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lokhttp3/Call$Factory;Lcom/datadog/android/core/internal/system/AndroidInfoProvider;)V", "tags", "getTags", "()Ljava/lang/String;", "tags$delegate", "Lkotlin/Lazy;", "buildQueryParameters", "", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumOkHttpUploaderV2.kt */
public class RumOkHttpUploaderV2 extends DataOkHttpUploaderV2 {
    private final Lazy tags$delegate;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RumOkHttpUploaderV2(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, okhttp3.Call.Factory r15, com.datadog.android.core.internal.system.AndroidInfoProvider r16) {
        /*
            r10 = this;
            r0 = r11
            r9 = r14
            java.lang.String r1 = "endpoint"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r1)
            java.lang.String r1 = "clientToken"
            r2 = r12
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r1)
            java.lang.String r1 = "source"
            r3 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r1)
            java.lang.String r1 = "sdkVersion"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r1)
            java.lang.String r1 = "callFactory"
            r5 = r15
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r1)
            java.lang.String r1 = "androidInfoProvider"
            r7 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            com.datadog.android.core.internal.net.DataOkHttpUploaderV2$Companion r1 = com.datadog.android.core.internal.net.DataOkHttpUploaderV2.Companion
            com.datadog.android.core.internal.net.DataOkHttpUploaderV2$TrackType r4 = com.datadog.android.core.internal.net.DataOkHttpUploaderV2.TrackType.RUM
            java.lang.String r1 = r1.buildUrl$dd_sdk_android_release(r11, r4)
            com.datadog.android.log.Logger r8 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            java.lang.String r6 = "text/plain;charset=UTF-8"
            r0 = r10
            r4 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            com.datadog.android.rum.internal.net.RumOkHttpUploaderV2$tags$2 r0 = new com.datadog.android.rum.internal.net.RumOkHttpUploaderV2$tags$2
            r0.<init>(r14)
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            kotlin.Lazy r0 = kotlin.LazyKt.lazy(r0)
            r1 = r10
            r1.tags$delegate = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.net.RumOkHttpUploaderV2.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, okhttp3.Call$Factory, com.datadog.android.core.internal.system.AndroidInfoProvider):void");
    }

    private final String getTags() {
        return (String) this.tags$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> buildQueryParameters() {
        return MapsKt.mapOf(TuplesKt.m78to("ddsource", getSource$dd_sdk_android_release()), TuplesKt.m78to("ddtags", getTags()));
    }
}
