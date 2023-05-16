package com.datadog.android.rum.internal.anr;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/anr/ANRException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "thread", "Ljava/lang/Thread;", "(Ljava/lang/Thread;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ANRException.kt */
public final class ANRException extends Exception {
    public ANRException(Thread thread) {
        Intrinsics.checkNotNullParameter(thread, "thread");
        setStackTrace(thread.getStackTrace());
    }
}
