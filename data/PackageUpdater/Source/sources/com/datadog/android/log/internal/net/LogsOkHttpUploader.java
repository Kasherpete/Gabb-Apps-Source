package com.datadog.android.log.internal.net;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.net.DataOkHttpUploader;
import com.datadog.android.core.internal.system.AndroidInfoProvider;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.Call;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000bH\u0016¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/log/internal/net/LogsOkHttpUploader;", "Lcom/datadog/android/core/internal/net/DataOkHttpUploader;", "endpoint", "", "token", "callFactory", "Lokhttp3/Call$Factory;", "androidInfoProvider", "Lcom/datadog/android/core/internal/system/AndroidInfoProvider;", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/Call$Factory;Lcom/datadog/android/core/internal/system/AndroidInfoProvider;)V", "buildQueryParams", "", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LogsOkHttpUploader.kt */
public final class LogsOkHttpUploader extends DataOkHttpUploader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String UPLOAD_URL = "%s/v1/input/%s";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LogsOkHttpUploader(String str, String str2, Call.Factory factory, AndroidInfoProvider androidInfoProvider) {
        super(Companion.buildUrl(str, str2), factory, androidInfoProvider, (String) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "endpoint");
        Intrinsics.checkNotNullParameter(str2, MPDbAdapter.KEY_TOKEN);
        Intrinsics.checkNotNullParameter(factory, "callFactory");
        Intrinsics.checkNotNullParameter(androidInfoProvider, "androidInfoProvider");
    }

    public Map<String, Object> buildQueryParams() {
        return MapsKt.mutableMapOf(TuplesKt.m78to("ddsource", CoreFeature.INSTANCE.getSourceName$dd_sdk_android_release()));
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo20735d2 = {"Lcom/datadog/android/log/internal/net/LogsOkHttpUploader$Companion;", "", "()V", "UPLOAD_URL", "", "buildUrl", "endpoint", "token", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LogsOkHttpUploader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final String buildUrl(String str, String str2) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.US, "%s/v1/input/%s", Arrays.copyOf(new Object[]{str, str2}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            return format;
        }
    }
}
