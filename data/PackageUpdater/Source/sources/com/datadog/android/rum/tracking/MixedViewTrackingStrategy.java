package com.datadog.android.rum.tracking;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B?\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u0002\u0010\fB\u0017\b\u0000\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0016\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u0010!\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0007H\u0016R\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006%"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/MixedViewTrackingStrategy;", "Lcom/datadog/android/rum/tracking/ActivityLifecycleTrackingStrategy;", "Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;", "trackExtras", "", "componentPredicate", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "Landroid/app/Activity;", "supportFragmentComponentPredicate", "Landroidx/fragment/app/Fragment;", "defaultFragmentComponentPredicate", "Landroid/app/Fragment;", "(ZLcom/datadog/android/rum/tracking/ComponentPredicate;Lcom/datadog/android/rum/tracking/ComponentPredicate;Lcom/datadog/android/rum/tracking/ComponentPredicate;)V", "activityViewTrackingStrategy", "Lcom/datadog/android/rum/tracking/ActivityViewTrackingStrategy;", "fragmentViewTrackingStrategy", "Lcom/datadog/android/rum/tracking/FragmentViewTrackingStrategy;", "(Lcom/datadog/android/rum/tracking/ActivityViewTrackingStrategy;Lcom/datadog/android/rum/tracking/FragmentViewTrackingStrategy;)V", "getActivityViewTrackingStrategy$dd_sdk_android_release", "()Lcom/datadog/android/rum/tracking/ActivityViewTrackingStrategy;", "getFragmentViewTrackingStrategy$dd_sdk_android_release", "()Lcom/datadog/android/rum/tracking/FragmentViewTrackingStrategy;", "equals", "other", "", "hashCode", "", "onActivityCreated", "", "activity", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivityStarted", "onActivityStopped", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: MixedViewTrackingStrategy.kt */
public final class MixedViewTrackingStrategy extends ActivityLifecycleTrackingStrategy implements ViewTrackingStrategy {
    private final ActivityViewTrackingStrategy activityViewTrackingStrategy;
    private final FragmentViewTrackingStrategy fragmentViewTrackingStrategy;

    public MixedViewTrackingStrategy(boolean z) {
        this(z, (ComponentPredicate) null, (ComponentPredicate) null, (ComponentPredicate) null, 14, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MixedViewTrackingStrategy(boolean z, ComponentPredicate<Activity> componentPredicate) {
        this(z, componentPredicate, (ComponentPredicate) null, (ComponentPredicate) null, 12, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(componentPredicate, "componentPredicate");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MixedViewTrackingStrategy(boolean z, ComponentPredicate<Activity> componentPredicate, ComponentPredicate<Fragment> componentPredicate2) {
        this(z, componentPredicate, componentPredicate2, (ComponentPredicate) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(componentPredicate, "componentPredicate");
        Intrinsics.checkNotNullParameter(componentPredicate2, "supportFragmentComponentPredicate");
    }

    public final ActivityViewTrackingStrategy getActivityViewTrackingStrategy$dd_sdk_android_release() {
        return this.activityViewTrackingStrategy;
    }

    public final FragmentViewTrackingStrategy getFragmentViewTrackingStrategy$dd_sdk_android_release() {
        return this.fragmentViewTrackingStrategy;
    }

    public MixedViewTrackingStrategy(ActivityViewTrackingStrategy activityViewTrackingStrategy2, FragmentViewTrackingStrategy fragmentViewTrackingStrategy2) {
        Intrinsics.checkNotNullParameter(activityViewTrackingStrategy2, "activityViewTrackingStrategy");
        Intrinsics.checkNotNullParameter(fragmentViewTrackingStrategy2, "fragmentViewTrackingStrategy");
        this.activityViewTrackingStrategy = activityViewTrackingStrategy2;
        this.fragmentViewTrackingStrategy = fragmentViewTrackingStrategy2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MixedViewTrackingStrategy(boolean z, ComponentPredicate componentPredicate, ComponentPredicate componentPredicate2, ComponentPredicate componentPredicate3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? new AcceptAllActivities() : componentPredicate, (i & 4) != 0 ? new AcceptAllSupportFragments() : componentPredicate2, (i & 8) != 0 ? new AcceptAllDefaultFragment() : componentPredicate3);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MixedViewTrackingStrategy(boolean z, ComponentPredicate<Activity> componentPredicate, ComponentPredicate<Fragment> componentPredicate2, ComponentPredicate<android.app.Fragment> componentPredicate3) {
        this(new ActivityViewTrackingStrategy(z, componentPredicate), new FragmentViewTrackingStrategy(z, componentPredicate2, componentPredicate3));
        Intrinsics.checkNotNullParameter(componentPredicate, "componentPredicate");
        Intrinsics.checkNotNullParameter(componentPredicate2, "supportFragmentComponentPredicate");
        Intrinsics.checkNotNullParameter(componentPredicate3, "defaultFragmentComponentPredicate");
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityCreated(activity, bundle);
        this.activityViewTrackingStrategy.onActivityCreated(activity, bundle);
        this.fragmentViewTrackingStrategy.onActivityCreated(activity, bundle);
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityStarted(activity);
        this.activityViewTrackingStrategy.onActivityStarted(activity);
        this.fragmentViewTrackingStrategy.onActivityStarted(activity);
    }

    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityResumed(activity);
        this.activityViewTrackingStrategy.onActivityResumed(activity);
        this.fragmentViewTrackingStrategy.onActivityResumed(activity);
    }

    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityPaused(activity);
        this.activityViewTrackingStrategy.onActivityPaused(activity);
        this.fragmentViewTrackingStrategy.onActivityPaused(activity);
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityStopped(activity);
        this.activityViewTrackingStrategy.onActivityStopped(activity);
        this.fragmentViewTrackingStrategy.onActivityStopped(activity);
    }

    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityDestroyed(activity);
        this.activityViewTrackingStrategy.onActivityDestroyed(activity);
        this.fragmentViewTrackingStrategy.onActivityDestroyed(activity);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj == null ? null : obj.getClass())) {
            return false;
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type com.datadog.android.rum.tracking.MixedViewTrackingStrategy");
        MixedViewTrackingStrategy mixedViewTrackingStrategy = (MixedViewTrackingStrategy) obj;
        return Intrinsics.areEqual((Object) this.activityViewTrackingStrategy, (Object) mixedViewTrackingStrategy.activityViewTrackingStrategy) && Intrinsics.areEqual((Object) this.fragmentViewTrackingStrategy, (Object) mixedViewTrackingStrategy.fragmentViewTrackingStrategy);
    }

    public int hashCode() {
        return (this.activityViewTrackingStrategy.hashCode() * 31) + this.fragmentViewTrackingStrategy.hashCode();
    }
}
