package com.datadog.android.tracing.internal.net;

import com.datadog.android.core.internal.net.DataOkHttpUploaderV2;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/tracing/internal/net/TracesOkHttpUploaderV2;", "Lcom/datadog/android/core/internal/net/DataOkHttpUploaderV2;", "endpoint", "", "clientToken", "source", "sdkVersion", "callFactory", "Lokhttp3/Call$Factory;", "androidInfoProvider", "Lcom/datadog/android/core/internal/system/AndroidInfoProvider;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lokhttp3/Call$Factory;Lcom/datadog/android/core/internal/system/AndroidInfoProvider;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TracesOkHttpUploaderV2.kt */
public class TracesOkHttpUploaderV2 extends DataOkHttpUploaderV2 {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TracesOkHttpUploaderV2(java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, okhttp3.Call.Factory r16, com.datadog.android.core.internal.system.AndroidInfoProvider r17) {
        /*
            r11 = this;
            r0 = r12
            java.lang.String r1 = "endpoint"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r1)
            java.lang.String r1 = "clientToken"
            r4 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r1)
            java.lang.String r1 = "source"
            r5 = r14
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r1)
            java.lang.String r1 = "sdkVersion"
            r6 = r15
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r1)
            java.lang.String r1 = "callFactory"
            r7 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            java.lang.String r1 = "androidInfoProvider"
            r9 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r1)
            com.datadog.android.core.internal.net.DataOkHttpUploaderV2$Companion r1 = com.datadog.android.core.internal.net.DataOkHttpUploaderV2.Companion
            com.datadog.android.core.internal.net.DataOkHttpUploaderV2$TrackType r2 = com.datadog.android.core.internal.net.DataOkHttpUploaderV2.TrackType.SPANS
            java.lang.String r3 = r1.buildUrl$dd_sdk_android_release(r12, r2)
            com.datadog.android.log.Logger r10 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            java.lang.String r8 = "text/plain;charset=UTF-8"
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.tracing.internal.net.TracesOkHttpUploaderV2.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, okhttp3.Call$Factory, com.datadog.android.core.internal.system.AndroidInfoProvider):void");
    }
}
