package com.datadog.android.core.internal.thread;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, mo20735d2 = {"sleepSafe", "", "durationMs", "", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ThreadExt.kt */
public final class ThreadExtKt {
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean sleepSafe(long r7) {
        /*
            r0 = 0
            java.lang.Thread.sleep(r7)     // Catch:{ InterruptedException -> 0x0016, IllegalArgumentException -> 0x0005 }
            return r0
        L_0x0005:
            r7 = move-exception
            com.datadog.android.log.Logger r1 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            r3 = r7
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r4 = 0
            r5 = 4
            r6 = 0
            java.lang.String r2 = "Thread tried to sleep for a negative amount of time"
            com.datadog.android.log.Logger.w$default(r1, r2, r3, r4, r5, r6)
            return r0
        L_0x0016:
            java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch:{ SecurityException -> 0x001e }
            r7.interrupt()     // Catch:{ SecurityException -> 0x001e }
            goto L_0x002e
        L_0x001e:
            r7 = move-exception
            com.datadog.android.log.Logger r0 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            r2 = r7
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = 0
            r4 = 4
            r5 = 0
            java.lang.String r1 = "Thread was unable to set its own interrupted state"
            com.datadog.android.log.Logger.e$default(r0, r1, r2, r3, r4, r5)
        L_0x002e:
            r7 = 1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.internal.thread.ThreadExtKt.sleepSafe(long):boolean");
    }
}
