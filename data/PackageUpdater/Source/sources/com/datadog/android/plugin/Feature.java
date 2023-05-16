package com.datadog.android.plugin;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/plugin/Feature;", "", "featureName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getFeatureName$dd_sdk_android_release", "()Ljava/lang/String;", "LOG", "CRASH", "TRACE", "RUM", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Feature.kt */
public enum Feature {
    LOG("Logging"),
    CRASH("Crash Reporting"),
    TRACE("Tracing"),
    RUM("RUM");
    
    private final String featureName;

    private Feature(String str) {
        this.featureName = str;
    }

    public final String getFeatureName$dd_sdk_android_release() {
        return this.featureName;
    }
}
