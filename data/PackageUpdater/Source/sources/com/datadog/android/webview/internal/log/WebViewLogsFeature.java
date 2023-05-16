package com.datadog.android.webview.internal.log;

import android.content.Context;
import com.datadog.android.core.configuration.Configuration;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.SdkFeature;
import com.datadog.android.core.internal.net.DataUploader;
import com.datadog.android.core.internal.persistence.PersistenceStrategy;
import com.datadog.android.core.internal.system.StaticAndroidInfoProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.net.LogsOkHttpUploaderV2;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.google.gson.JsonObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/log/WebViewLogsFeature;", "Lcom/datadog/android/core/internal/SdkFeature;", "Lcom/google/gson/JsonObject;", "Lcom/datadog/android/core/configuration/Configuration$Feature$Logs;", "()V", "WEB_LOGS_FEATURE_NAME", "", "createPersistenceStrategy", "Lcom/datadog/android/core/internal/persistence/PersistenceStrategy;", "context", "Landroid/content/Context;", "configuration", "createUploader", "Lcom/datadog/android/core/internal/net/DataUploader;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WebViewLogsFeature.kt */
public final class WebViewLogsFeature extends SdkFeature<JsonObject, Configuration.Feature.Logs> {
    public static final WebViewLogsFeature INSTANCE = new WebViewLogsFeature();
    public static final String WEB_LOGS_FEATURE_NAME = "web-logs";

    private WebViewLogsFeature() {
    }

    public PersistenceStrategy<JsonObject> createPersistenceStrategy(Context context, Configuration.Feature.Logs logs) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(logs, "configuration");
        return new WebViewLogFilePersistenceStrategy(CoreFeature.INSTANCE.getTrackingConsentProvider$dd_sdk_android_release(), context, CoreFeature.INSTANCE.getPersistenceExecutorService$dd_sdk_android_release(), RuntimeUtilsKt.getSdkLogger(), CoreFeature.INSTANCE.getLocalDataEncryption$dd_sdk_android_release());
    }

    public DataUploader createUploader(Configuration.Feature.Logs logs) {
        Intrinsics.checkNotNullParameter(logs, "configuration");
        return new LogsOkHttpUploaderV2(logs.getEndpointUrl(), CoreFeature.INSTANCE.getClientToken$dd_sdk_android_release(), CoreFeature.INSTANCE.getSourceName$dd_sdk_android_release(), CoreFeature.INSTANCE.getSdkVersion$dd_sdk_android_release(), CoreFeature.INSTANCE.getOkHttpClient$dd_sdk_android_release(), StaticAndroidInfoProvider.INSTANCE, RuntimeUtilsKt.getSdkLogger());
    }
}
