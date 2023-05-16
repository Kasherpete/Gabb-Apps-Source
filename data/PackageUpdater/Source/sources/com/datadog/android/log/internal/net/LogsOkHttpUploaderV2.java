package com.datadog.android.log.internal.net;

import com.datadog.android.core.internal.net.DataOkHttpUploaderV2;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;

@Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\b\u0010\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u000fH\u0014¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/log/internal/net/LogsOkHttpUploaderV2;", "Lcom/datadog/android/core/internal/net/DataOkHttpUploaderV2;", "endpoint", "", "clientToken", "source", "sdkVersion", "callFactory", "Lokhttp3/Call$Factory;", "androidInfoProvider", "Lcom/datadog/android/core/internal/system/AndroidInfoProvider;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lokhttp3/Call$Factory;Lcom/datadog/android/core/internal/system/AndroidInfoProvider;Lcom/datadog/android/log/Logger;)V", "buildQueryParameters", "", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LogsOkHttpUploaderV2.kt */
public class LogsOkHttpUploaderV2 extends DataOkHttpUploaderV2 {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LogsOkHttpUploaderV2(java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, okhttp3.Call.Factory r16, com.datadog.android.core.internal.system.AndroidInfoProvider r17, com.datadog.android.log.Logger r18) {
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
            java.lang.String r1 = "internalLogger"
            r10 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r1)
            com.datadog.android.core.internal.net.DataOkHttpUploaderV2$Companion r1 = com.datadog.android.core.internal.net.DataOkHttpUploaderV2.Companion
            com.datadog.android.core.internal.net.DataOkHttpUploaderV2$TrackType r2 = com.datadog.android.core.internal.net.DataOkHttpUploaderV2.TrackType.LOGS
            java.lang.String r3 = r1.buildUrl$dd_sdk_android_release(r12, r2)
            java.lang.String r8 = "application/json"
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.log.internal.net.LogsOkHttpUploaderV2.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, okhttp3.Call$Factory, com.datadog.android.core.internal.system.AndroidInfoProvider, com.datadog.android.log.Logger):void");
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> buildQueryParameters() {
        return MapsKt.mapOf(TuplesKt.m78to("ddsource", getSource$dd_sdk_android_release()));
    }
}
