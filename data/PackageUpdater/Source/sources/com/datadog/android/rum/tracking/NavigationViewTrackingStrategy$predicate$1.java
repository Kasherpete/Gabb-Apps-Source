package com.datadog.android.rum.tracking;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¨\u0006\b"}, mo20735d2 = {"com/datadog/android/rum/tracking/NavigationViewTrackingStrategy$predicate$1", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "Landroidx/fragment/app/Fragment;", "accept", "", "component", "getViewName", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NavigationViewTrackingStrategy.kt */
public final class NavigationViewTrackingStrategy$predicate$1 implements ComponentPredicate<Fragment> {
    public String getViewName(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "component");
        return null;
    }

    NavigationViewTrackingStrategy$predicate$1() {
    }

    public boolean accept(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "component");
        return !NavHostFragment.class.isAssignableFrom(fragment.getClass());
    }
}
