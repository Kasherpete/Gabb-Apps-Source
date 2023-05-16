package com.datadog.android.core.internal.time;

import com.lyft.kronos.Clock;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/core/internal/time/KronosTimeProvider;", "Lcom/datadog/android/core/internal/time/TimeProvider;", "clock", "Lcom/lyft/kronos/Clock;", "(Lcom/lyft/kronos/Clock;)V", "getDeviceTimestamp", "", "getServerOffsetMillis", "getServerOffsetNanos", "getServerTimestamp", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: KronosTimeProvider.kt */
public final class KronosTimeProvider implements TimeProvider {
    private final Clock clock;

    public KronosTimeProvider(Clock clock2) {
        Intrinsics.checkNotNullParameter(clock2, "clock");
        this.clock = clock2;
    }

    public long getDeviceTimestamp() {
        return System.currentTimeMillis();
    }

    public long getServerTimestamp() {
        return this.clock.getCurrentTimeMs();
    }

    public long getServerOffsetMillis() {
        return this.clock.getCurrentTimeMs() - System.currentTimeMillis();
    }

    public long getServerOffsetNanos() {
        return TimeUnit.MILLISECONDS.toNanos(getServerOffsetMillis());
    }
}
