package com.datadog.android.plugin;

import android.content.Context;
import com.datadog.android.privacy.TrackingConsent;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/plugin/DatadogPluginConfig;", "", "context", "Landroid/content/Context;", "envName", "", "serviceName", "trackingConsent", "Lcom/datadog/android/privacy/TrackingConsent;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/datadog/android/privacy/TrackingConsent;)V", "getContext", "()Landroid/content/Context;", "getEnvName", "()Ljava/lang/String;", "getServiceName", "getTrackingConsent", "()Lcom/datadog/android/privacy/TrackingConsent;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogPluginConfig.kt */
public final class DatadogPluginConfig {
    private final Context context;
    private final String envName;
    private final String serviceName;
    private final TrackingConsent trackingConsent;

    public DatadogPluginConfig(Context context2, String str, String str2, TrackingConsent trackingConsent2) {
        Intrinsics.checkNotNullParameter(context2, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(str, "envName");
        Intrinsics.checkNotNullParameter(str2, "serviceName");
        Intrinsics.checkNotNullParameter(trackingConsent2, "trackingConsent");
        this.context = context2;
        this.envName = str;
        this.serviceName = str2;
        this.trackingConsent = trackingConsent2;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getEnvName() {
        return this.envName;
    }

    public final String getServiceName() {
        return this.serviceName;
    }

    public final TrackingConsent getTrackingConsent() {
        return this.trackingConsent;
    }
}
