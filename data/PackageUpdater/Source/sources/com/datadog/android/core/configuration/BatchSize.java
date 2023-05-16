package com.datadog.android.core.configuration;

import kotlin.Metadata;
import kotlinx.coroutines.test.TestBuildersKt;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/BatchSize;", "", "windowDurationMs", "", "(Ljava/lang/String;IJ)V", "getWindowDurationMs$dd_sdk_android_release", "()J", "SMALL", "MEDIUM", "LARGE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BatchSize.kt */
public enum BatchSize {
    SMALL(5000),
    MEDIUM(15000),
    LARGE(TestBuildersKt.DEFAULT_DISPATCH_TIMEOUT_MS);
    
    private final long windowDurationMs;

    private BatchSize(long j) {
        this.windowDurationMs = j;
    }

    public final long getWindowDurationMs$dd_sdk_android_release() {
        return this.windowDurationMs;
    }
}
