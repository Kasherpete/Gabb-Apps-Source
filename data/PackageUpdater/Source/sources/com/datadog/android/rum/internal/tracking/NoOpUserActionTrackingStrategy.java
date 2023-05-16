package com.datadog.android.rum.internal.tracking;

import android.content.Context;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.rum.internal.instrumentation.gestures.GesturesTracker;
import com.datadog.android.rum.internal.instrumentation.gestures.NoOpGesturesTracker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/tracking/NoOpUserActionTrackingStrategy;", "Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;", "()V", "getGesturesTracker", "Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesTracker;", "register", "", "context", "Landroid/content/Context;", "unregister", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpUserActionTrackingStrategy.kt */
public final class NoOpUserActionTrackingStrategy implements UserActionTrackingStrategy {
    public void register(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
    }

    public void unregister(Context context) {
    }

    public GesturesTracker getGesturesTracker() {
        return new NoOpGesturesTracker();
    }
}
