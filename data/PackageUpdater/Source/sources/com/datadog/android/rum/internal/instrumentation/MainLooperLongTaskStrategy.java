package com.datadog.android.rum.internal.instrumentation;

import android.content.Context;
import android.os.Looper;
import android.util.Printer;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.rum.tracking.TrackingStrategy;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001c2\u00020\u00012\u00020\u0002:\u0001\u001cB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0002J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016J\u0012\u0010\u001b\u001a\u00020\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u000e\u0010\u0006\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/MainLooperLongTaskStrategy;", "Landroid/util/Printer;", "Lcom/datadog/android/rum/tracking/TrackingStrategy;", "thresholdMs", "", "(J)V", "startUptimeNs", "target", "", "getThresholdMs$dd_sdk_android_release", "()J", "thresholdNS", "detectLongTask", "", "message", "equals", "", "other", "", "hashCode", "", "println", "x", "register", "context", "Landroid/content/Context;", "toString", "unregister", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: MainLooperLongTaskStrategy.kt */
public final class MainLooperLongTaskStrategy implements Printer, TrackingStrategy {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PREFIX_END = "<<<<< Finished to ";
    private static final String PREFIX_START = ">>>>> Dispatching to ";
    private static final int PREFIX_START_LENGTH = 21;
    private long startUptimeNs;
    private String target = "";
    private final long thresholdMs;
    private final long thresholdNS;

    public MainLooperLongTaskStrategy(long j) {
        this.thresholdMs = j;
        this.thresholdNS = TimeUnit.MILLISECONDS.toNanos(j);
    }

    public final long getThresholdMs$dd_sdk_android_release() {
        return this.thresholdMs;
    }

    public void register(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Looper.getMainLooper().setMessageLogging(this);
    }

    public void unregister(Context context) {
        Looper.getMainLooper().setMessageLogging((Printer) null);
    }

    public void println(String str) {
        if (str != null) {
            detectLongTask(str);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj == null ? null : obj.getClass())) {
            return false;
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type com.datadog.android.rum.internal.instrumentation.MainLooperLongTaskStrategy");
        return this.thresholdMs == ((MainLooperLongTaskStrategy) obj).thresholdMs;
    }

    public int hashCode() {
        return Long.hashCode(this.thresholdMs);
    }

    public String toString() {
        return "MainLooperLongTaskStrategy(" + this.thresholdMs + ")";
    }

    /* JADX WARNING: type inference failed for: r7v3, types: [com.datadog.android.rum.RumMonitor] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void detectLongTask(java.lang.String r7) {
        /*
            r6 = this;
            long r0 = java.lang.System.nanoTime()
            java.lang.String r2 = ">>>>> Dispatching to "
            r3 = 0
            r4 = 2
            r5 = 0
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r7, r2, r3, r4, r5)
            if (r2 == 0) goto L_0x001f
            r2 = 21
            java.lang.String r7 = r7.substring(r2)
            java.lang.String r2 = "this as java.lang.String).substring(startIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            r6.target = r7
            r6.startUptimeNs = r0
            goto L_0x0043
        L_0x001f:
            java.lang.String r2 = "<<<<< Finished to "
            boolean r7 = kotlin.text.StringsKt.startsWith$default(r7, r2, r3, r4, r5)
            if (r7 == 0) goto L_0x0043
            long r2 = r6.startUptimeNs
            long r0 = r0 - r2
            long r2 = r6.thresholdNS
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 <= 0) goto L_0x0043
            com.datadog.android.rum.RumMonitor r7 = com.datadog.android.rum.GlobalRum.get()
            boolean r2 = r7 instanceof com.datadog.android.rum.internal.monitor.AdvancedRumMonitor
            if (r2 == 0) goto L_0x003b
            r5 = r7
            com.datadog.android.rum.internal.monitor.AdvancedRumMonitor r5 = (com.datadog.android.rum.internal.monitor.AdvancedRumMonitor) r5
        L_0x003b:
            if (r5 != 0) goto L_0x003e
            goto L_0x0043
        L_0x003e:
            java.lang.String r6 = r6.target
            r5.addLongTask(r0, r6)
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.instrumentation.MainLooperLongTaskStrategy.detectLongTask(java.lang.String):void");
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/MainLooperLongTaskStrategy$Companion;", "", "()V", "PREFIX_END", "", "PREFIX_START", "PREFIX_START_LENGTH", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: MainLooperLongTaskStrategy.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
