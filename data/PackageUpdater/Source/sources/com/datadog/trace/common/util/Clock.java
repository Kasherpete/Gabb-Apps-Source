package com.datadog.trace.common.util;

import java.util.concurrent.TimeUnit;

public class Clock {
    public static long currentNanoTicks() {
        return System.nanoTime();
    }

    public static long currentMicroTime() {
        return TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
    }

    public static long currentNanoTime() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }
}
