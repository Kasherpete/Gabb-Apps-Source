package com.datadog.android.rum.internal.tracking;

import com.datadog.android.rum.internal.instrumentation.gestures.GesturesTracker;
import com.datadog.android.rum.tracking.TrackingStrategy;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;", "Lcom/datadog/android/rum/tracking/TrackingStrategy;", "getGesturesTracker", "Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesTracker;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UserActionTrackingStrategy.kt */
public interface UserActionTrackingStrategy extends TrackingStrategy {
    GesturesTracker getGesturesTracker();
}
