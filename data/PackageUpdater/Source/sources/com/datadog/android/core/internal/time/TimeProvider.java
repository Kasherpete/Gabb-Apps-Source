package com.datadog.android.core.internal.time;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\ba\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/internal/time/TimeProvider;", "", "getDeviceTimestamp", "", "getServerOffsetMillis", "getServerOffsetNanos", "getServerTimestamp", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TimeProvider.kt */
public interface TimeProvider {
    long getDeviceTimestamp();

    long getServerOffsetMillis();

    long getServerOffsetNanos();

    long getServerTimestamp();
}
