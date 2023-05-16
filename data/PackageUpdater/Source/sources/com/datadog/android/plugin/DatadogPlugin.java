package com.datadog.android.plugin;

import com.datadog.android.privacy.TrackingConsentProviderCallback;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/plugin/DatadogPlugin;", "Lcom/datadog/android/privacy/TrackingConsentProviderCallback;", "onContextChanged", "", "context", "Lcom/datadog/android/plugin/DatadogContext;", "register", "config", "Lcom/datadog/android/plugin/DatadogPluginConfig;", "unregister", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogPlugin.kt */
public interface DatadogPlugin extends TrackingConsentProviderCallback {
    void onContextChanged(DatadogContext datadogContext);

    void register(DatadogPluginConfig datadogPluginConfig);

    void unregister();
}
