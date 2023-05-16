package com.datadog.android.core.internal.time;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.lyft.kronos.SyncListener;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/core/internal/time/LoggingSyncListener;", "Lcom/lyft/kronos/SyncListener;", "()V", "onError", "", "host", "", "throwable", "", "onStartSync", "onSuccess", "ticksDelta", "", "responseTimeMs", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LoggingSyncListener.kt */
public final class LoggingSyncListener implements SyncListener {
    public void onStartSync(String str) {
        Intrinsics.checkNotNullParameter(str, "host");
    }

    public void onSuccess(long j, long j2) {
    }

    public void onError(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(th, "throwable");
        String str2 = "Kronos onError @host:" + str;
        Map mapOf = MapsKt.mapOf(TuplesKt.m78to("kronos.sync.host", str));
        if (th instanceof IOException) {
            RuntimeUtilsKt.getSdkLogger().mo13085e(str2, th, mapOf);
        } else {
            LogUtilsKt.errorWithTelemetry(RuntimeUtilsKt.getSdkLogger(), str2, th, mapOf);
        }
    }
}
