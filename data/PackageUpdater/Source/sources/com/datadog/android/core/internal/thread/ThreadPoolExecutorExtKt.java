package com.datadog.android.core.internal.thread;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo20735d2 = {"MAX_SLEEP_DURATION_IN_MS", "", "isIdle", "", "Ljava/util/concurrent/ThreadPoolExecutor;", "waitToIdle", "timeoutInMs", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ThreadPoolExecutorExt.kt */
public final class ThreadPoolExecutorExtKt {
    public static final long MAX_SLEEP_DURATION_IN_MS = 10;

    public static final boolean waitToIdle(ThreadPoolExecutor threadPoolExecutor, long j) {
        Intrinsics.checkNotNullParameter(threadPoolExecutor, "<this>");
        long nanoTime = System.nanoTime();
        long nanos = TimeUnit.MILLISECONDS.toNanos(j);
        long coerceIn = RangesKt.coerceIn(j, 0, 10);
        while (!isIdle(threadPoolExecutor)) {
            boolean sleepSafe = ThreadExtKt.sleepSafe(coerceIn);
            if (System.nanoTime() - nanoTime < nanos) {
                if (sleepSafe) {
                }
            }
            return isIdle(threadPoolExecutor);
        }
        return true;
    }

    public static final boolean isIdle(ThreadPoolExecutor threadPoolExecutor) {
        Intrinsics.checkNotNullParameter(threadPoolExecutor, "<this>");
        return threadPoolExecutor.getTaskCount() - threadPoolExecutor.getCompletedTaskCount() <= 0;
    }
}
