package com.datadog.android.rum.internal.domain;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, mo20735d2 = {"asTime", "Lcom/datadog/android/rum/internal/domain/Time;", "", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Time.kt */
public final class TimeKt {
    public static final Time asTime(long j) {
        Time time = new Time(0, 0, 3, (DefaultConstructorMarker) null);
        return new Time(j, TimeUnit.MILLISECONDS.toNanos(j - time.getTimestamp()) + time.getNanoTime());
    }
}
