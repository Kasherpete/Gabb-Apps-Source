package com.datadog.android.rum.tracking;

import android.app.Activity;
import android.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.datadog.android.rum.internal.tracking.FragmentLifecycleCallbacks;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B/\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0002\u0010\nJ\u0013\u0010\u001b\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0015H\u0016J\u0010\u0010#\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0015H\u0016R!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8BX\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\f8BX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0016\u0010\u000fR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006$"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/FragmentViewTrackingStrategy;", "Lcom/datadog/android/rum/tracking/ActivityLifecycleTrackingStrategy;", "Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;", "trackArguments", "", "supportFragmentComponentPredicate", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "Landroidx/fragment/app/Fragment;", "defaultFragmentComponentPredicate", "Landroid/app/Fragment;", "(ZLcom/datadog/android/rum/tracking/ComponentPredicate;Lcom/datadog/android/rum/tracking/ComponentPredicate;)V", "androidXLifecycleCallbacks", "Lcom/datadog/android/rum/internal/tracking/FragmentLifecycleCallbacks;", "Landroidx/fragment/app/FragmentActivity;", "getAndroidXLifecycleCallbacks", "()Lcom/datadog/android/rum/internal/tracking/FragmentLifecycleCallbacks;", "androidXLifecycleCallbacks$delegate", "Lkotlin/Lazy;", "getDefaultFragmentComponentPredicate$dd_sdk_android_release", "()Lcom/datadog/android/rum/tracking/ComponentPredicate;", "oreoLifecycleCallbacks", "Landroid/app/Activity;", "getOreoLifecycleCallbacks", "oreoLifecycleCallbacks$delegate", "getSupportFragmentComponentPredicate$dd_sdk_android_release", "getTrackArguments$dd_sdk_android_release", "()Z", "equals", "other", "", "hashCode", "", "onActivityStarted", "", "activity", "onActivityStopped", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FragmentViewTrackingStrategy.kt */
public final class FragmentViewTrackingStrategy extends ActivityLifecycleTrackingStrategy implements ViewTrackingStrategy {
    private final Lazy androidXLifecycleCallbacks$delegate;
    private final ComponentPredicate<Fragment> defaultFragmentComponentPredicate;
    private final Lazy oreoLifecycleCallbacks$delegate;
    private final ComponentPredicate<androidx.fragment.app.Fragment> supportFragmentComponentPredicate;
    private final boolean trackArguments;

    public FragmentViewTrackingStrategy(boolean z) {
        this(z, (ComponentPredicate) null, (ComponentPredicate) null, 6, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FragmentViewTrackingStrategy(boolean z, ComponentPredicate<androidx.fragment.app.Fragment> componentPredicate) {
        this(z, componentPredicate, (ComponentPredicate) null, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(componentPredicate, "supportFragmentComponentPredicate");
    }

    public final boolean getTrackArguments$dd_sdk_android_release() {
        return this.trackArguments;
    }

    public final ComponentPredicate<androidx.fragment.app.Fragment> getSupportFragmentComponentPredicate$dd_sdk_android_release() {
        return this.supportFragmentComponentPredicate;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FragmentViewTrackingStrategy(boolean z, ComponentPredicate componentPredicate, ComponentPredicate componentPredicate2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? new AcceptAllSupportFragments() : componentPredicate, (i & 4) != 0 ? new AcceptAllDefaultFragment() : componentPredicate2);
    }

    public final ComponentPredicate<Fragment> getDefaultFragmentComponentPredicate$dd_sdk_android_release() {
        return this.defaultFragmentComponentPredicate;
    }

    public FragmentViewTrackingStrategy(boolean z, ComponentPredicate<androidx.fragment.app.Fragment> componentPredicate, ComponentPredicate<Fragment> componentPredicate2) {
        Intrinsics.checkNotNullParameter(componentPredicate, "supportFragmentComponentPredicate");
        Intrinsics.checkNotNullParameter(componentPredicate2, "defaultFragmentComponentPredicate");
        this.trackArguments = z;
        this.supportFragmentComponentPredicate = componentPredicate;
        this.defaultFragmentComponentPredicate = componentPredicate2;
        this.androidXLifecycleCallbacks$delegate = LazyKt.lazy(new FragmentViewTrackingStrategy$androidXLifecycleCallbacks$2(this));
        this.oreoLifecycleCallbacks$delegate = LazyKt.lazy(new FragmentViewTrackingStrategy$oreoLifecycleCallbacks$2(this));
    }

    private final FragmentLifecycleCallbacks<FragmentActivity> getAndroidXLifecycleCallbacks() {
        return (FragmentLifecycleCallbacks) this.androidXLifecycleCallbacks$delegate.getValue();
    }

    private final FragmentLifecycleCallbacks<Activity> getOreoLifecycleCallbacks() {
        return (FragmentLifecycleCallbacks) this.oreoLifecycleCallbacks$delegate.getValue();
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityStarted(activity);
        if (FragmentActivity.class.isAssignableFrom(activity.getClass())) {
            getAndroidXLifecycleCallbacks().register((FragmentActivity) activity);
        } else {
            getOreoLifecycleCallbacks().register(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityStopped(activity);
        if (FragmentActivity.class.isAssignableFrom(activity.getClass())) {
            getAndroidXLifecycleCallbacks().unregister((FragmentActivity) activity);
        } else {
            getOreoLifecycleCallbacks().unregister(activity);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj == null ? null : obj.getClass())) {
            return false;
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type com.datadog.android.rum.tracking.FragmentViewTrackingStrategy");
        FragmentViewTrackingStrategy fragmentViewTrackingStrategy = (FragmentViewTrackingStrategy) obj;
        return this.trackArguments == fragmentViewTrackingStrategy.trackArguments && Intrinsics.areEqual((Object) this.supportFragmentComponentPredicate, (Object) fragmentViewTrackingStrategy.supportFragmentComponentPredicate) && Intrinsics.areEqual((Object) this.defaultFragmentComponentPredicate, (Object) fragmentViewTrackingStrategy.defaultFragmentComponentPredicate);
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.trackArguments) * 31) + this.supportFragmentComponentPredicate.hashCode()) * 31) + this.defaultFragmentComponentPredicate.hashCode();
    }
}
