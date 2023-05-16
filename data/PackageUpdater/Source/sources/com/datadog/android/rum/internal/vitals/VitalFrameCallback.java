package com.datadog.android.rum.internal.vitals;

import android.view.Choreographer;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/VitalFrameCallback;", "Landroid/view/Choreographer$FrameCallback;", "observer", "Lcom/datadog/android/rum/internal/vitals/VitalObserver;", "keepRunning", "Lkotlin/Function0;", "", "(Lcom/datadog/android/rum/internal/vitals/VitalObserver;Lkotlin/jvm/functions/Function0;)V", "lastFrameTimestampNs", "", "getLastFrameTimestampNs$dd_sdk_android_release", "()J", "setLastFrameTimestampNs$dd_sdk_android_release", "(J)V", "doFrame", "", "frameTimeNanos", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: VitalFrameCallback.kt */
public final class VitalFrameCallback implements Choreographer.FrameCallback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final double MAX_FPS = 240.0d;
    private static final double MIN_FPS = 1.0d;
    /* access modifiers changed from: private */
    public static final double ONE_SECOND_NS = ((double) TimeUnit.SECONDS.toNanos(1));
    /* access modifiers changed from: private */
    public static final ClosedFloatingPointRange<Double> VALID_FPS_RANGE = RangesKt.rangeTo((double) MIN_FPS, (double) MAX_FPS);
    private final Function0<Boolean> keepRunning;
    private long lastFrameTimestampNs;
    private final VitalObserver observer;

    public VitalFrameCallback(VitalObserver vitalObserver, Function0<Boolean> function0) {
        Intrinsics.checkNotNullParameter(vitalObserver, "observer");
        Intrinsics.checkNotNullParameter(function0, "keepRunning");
        this.observer = vitalObserver;
        this.keepRunning = function0;
    }

    public final long getLastFrameTimestampNs$dd_sdk_android_release() {
        return this.lastFrameTimestampNs;
    }

    public final void setLastFrameTimestampNs$dd_sdk_android_release(long j) {
        this.lastFrameTimestampNs = j;
    }

    public void doFrame(long j) {
        long j2 = this.lastFrameTimestampNs;
        if (j2 != 0) {
            double d = (double) (j - j2);
            if (d > 0.0d) {
                double d2 = ONE_SECOND_NS / d;
                if (VALID_FPS_RANGE.contains(Double.valueOf(d2))) {
                    this.observer.onNewSample(d2);
                }
            }
        }
        this.lastFrameTimestampNs = j;
        if (this.keepRunning.invoke().booleanValue()) {
            try {
                Choreographer.getInstance().postFrameCallback(this);
            } catch (IllegalStateException e) {
                Logger.e$default(RuntimeUtilsKt.getSdkLogger(), "Unable to post VitalFrameCallback, thread doesn't have looper", e, (Map) null, 4, (Object) null);
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/VitalFrameCallback$Companion;", "", "()V", "MAX_FPS", "", "MIN_FPS", "ONE_SECOND_NS", "getONE_SECOND_NS", "()D", "VALID_FPS_RANGE", "Lkotlin/ranges/ClosedFloatingPointRange;", "getVALID_FPS_RANGE", "()Lkotlin/ranges/ClosedFloatingPointRange;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: VitalFrameCallback.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final double getONE_SECOND_NS() {
            return VitalFrameCallback.ONE_SECOND_NS;
        }

        public final ClosedFloatingPointRange<Double> getVALID_FPS_RANGE() {
            return VitalFrameCallback.VALID_FPS_RANGE;
        }
    }
}
