package com.datadog.android.rum.tracking;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0013\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/AcceptAllActivities;", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "Landroid/app/Activity;", "()V", "accept", "", "component", "equals", "other", "", "getViewName", "", "hashCode", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: AcceptAllActivities.kt */
public class AcceptAllActivities implements ComponentPredicate<Activity> {
    public boolean accept(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "component");
        return true;
    }

    public String getViewName(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "component");
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return Intrinsics.areEqual((Object) getClass(), (Object) obj == null ? null : obj.getClass());
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}
