package com.datadog.android.rum.internal.instrumentation;

import android.app.Activity;
import android.os.Bundle;
import com.datadog.android.rum.internal.instrumentation.gestures.GesturesTracker;
import com.datadog.android.rum.internal.tracking.UserActionTrackingStrategy;
import com.datadog.android.rum.tracking.ActivityLifecycleTrackingStrategy;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/UserActionTrackingStrategyApi29;", "Lcom/datadog/android/rum/tracking/ActivityLifecycleTrackingStrategy;", "Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;", "gesturesTracker", "Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesTracker;", "(Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesTracker;)V", "getGesturesTracker$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesTracker;", "equals", "", "other", "", "getGesturesTracker", "hashCode", "", "onActivityPreCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UserActionTrackingStrategyApi29.kt */
public final class UserActionTrackingStrategyApi29 extends ActivityLifecycleTrackingStrategy implements UserActionTrackingStrategy {
    private final GesturesTracker gesturesTracker;

    public final GesturesTracker getGesturesTracker$dd_sdk_android_release() {
        return this.gesturesTracker;
    }

    public UserActionTrackingStrategyApi29(GesturesTracker gesturesTracker2) {
        Intrinsics.checkNotNullParameter(gesturesTracker2, "gesturesTracker");
        this.gesturesTracker = gesturesTracker2;
    }

    public GesturesTracker getGesturesTracker() {
        return this.gesturesTracker;
    }

    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.gesturesTracker.startTracking(activity.getWindow(), activity);
        super.onActivityPreCreated(activity, bundle);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj == null ? null : obj.getClass())) {
            return false;
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type com.datadog.android.rum.internal.instrumentation.UserActionTrackingStrategyApi29");
        return Intrinsics.areEqual((Object) this.gesturesTracker, (Object) ((UserActionTrackingStrategyApi29) obj).gesturesTracker);
    }

    public int hashCode() {
        return this.gesturesTracker.hashCode();
    }

    public String toString() {
        return "UserActionTrackingStrategyApi29(" + this.gesturesTracker + ")";
    }
}
