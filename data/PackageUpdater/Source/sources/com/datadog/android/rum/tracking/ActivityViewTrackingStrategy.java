package com.datadog.android.rum.tracking;

import android.app.Activity;
import android.os.Bundle;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.rum.internal.tracking.ViewLoadingTimer;
import com.datadog.android.rum.model.ViewEvent;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0013\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0002R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006&"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/ActivityViewTrackingStrategy;", "Lcom/datadog/android/rum/tracking/ActivityLifecycleTrackingStrategy;", "Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;", "trackExtras", "", "componentPredicate", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "Landroid/app/Activity;", "(ZLcom/datadog/android/rum/tracking/ComponentPredicate;)V", "getComponentPredicate$dd_sdk_android_release", "()Lcom/datadog/android/rum/tracking/ComponentPredicate;", "getTrackExtras$dd_sdk_android_release", "()Z", "viewLoadingTimer", "Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;", "getViewLoadingTimer$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;", "setViewLoadingTimer$dd_sdk_android_release", "(Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;)V", "equals", "other", "", "hashCode", "", "onActivityCreated", "", "activity", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityPostResumed", "onActivityResumed", "onActivityStarted", "resolveLoadingType", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "firstTimeLoading", "updateLoadingTime", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ActivityViewTrackingStrategy.kt */
public final class ActivityViewTrackingStrategy extends ActivityLifecycleTrackingStrategy implements ViewTrackingStrategy {
    private final ComponentPredicate<Activity> componentPredicate;
    private final boolean trackExtras;
    private ViewLoadingTimer viewLoadingTimer;

    public ActivityViewTrackingStrategy(boolean z) {
        this(z, (ComponentPredicate) null, 2, (DefaultConstructorMarker) null);
    }

    public final boolean getTrackExtras$dd_sdk_android_release() {
        return this.trackExtras;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ActivityViewTrackingStrategy(boolean z, ComponentPredicate componentPredicate2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? new AcceptAllActivities() : componentPredicate2);
    }

    public final ComponentPredicate<Activity> getComponentPredicate$dd_sdk_android_release() {
        return this.componentPredicate;
    }

    public ActivityViewTrackingStrategy(boolean z, ComponentPredicate<Activity> componentPredicate2) {
        Intrinsics.checkNotNullParameter(componentPredicate2, "componentPredicate");
        this.trackExtras = z;
        this.componentPredicate = componentPredicate2;
        this.viewLoadingTimer = new ViewLoadingTimer();
    }

    public final ViewLoadingTimer getViewLoadingTimer$dd_sdk_android_release() {
        return this.viewLoadingTimer;
    }

    public final void setViewLoadingTimer$dd_sdk_android_release(ViewLoadingTimer viewLoadingTimer2) {
        Intrinsics.checkNotNullParameter(viewLoadingTimer2, "<set-?>");
        this.viewLoadingTimer = viewLoadingTimer2;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityCreated(activity, bundle);
        if (this.componentPredicate.accept(activity)) {
            try {
                getViewLoadingTimer$dd_sdk_android_release().onCreated(activity);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityStarted(activity);
        if (this.componentPredicate.accept(activity)) {
            try {
                getViewLoadingTimer$dd_sdk_android_release().onStartLoading(activity);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0029 A[Catch:{ Exception -> 0x0051 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033 A[Catch:{ Exception -> 0x0051 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003c A[Catch:{ Exception -> 0x0051 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResumed(android.app.Activity r7) {
        /*
            r6 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            super.onActivityResumed(r7)
            com.datadog.android.rum.tracking.ComponentPredicate<android.app.Activity> r0 = r6.componentPredicate
            boolean r0 = r0.accept(r7)
            if (r0 == 0) goto L_0x0061
            com.datadog.android.rum.tracking.ComponentPredicate r0 = r6.getComponentPredicate$dd_sdk_android_release()     // Catch:{ Exception -> 0x0051 }
            java.lang.String r0 = r0.getViewName(r7)     // Catch:{ Exception -> 0x0051 }
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ Exception -> 0x0051 }
            if (r1 == 0) goto L_0x0026
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)     // Catch:{ Exception -> 0x0051 }
            if (r1 == 0) goto L_0x0024
            goto L_0x0026
        L_0x0024:
            r1 = 0
            goto L_0x0027
        L_0x0026:
            r1 = 1
        L_0x0027:
            if (r1 == 0) goto L_0x002d
            java.lang.String r0 = com.datadog.android.core.internal.utils.ViewUtilsKt.resolveViewUrl((java.lang.Object) r7)     // Catch:{ Exception -> 0x0051 }
        L_0x002d:
            boolean r1 = r6.getTrackExtras$dd_sdk_android_release()     // Catch:{ Exception -> 0x0051 }
            if (r1 == 0) goto L_0x003c
            android.content.Intent r1 = r7.getIntent()     // Catch:{ Exception -> 0x0051 }
            java.util.Map r1 = r6.convertToRumAttributes((android.content.Intent) r1)     // Catch:{ Exception -> 0x0051 }
            goto L_0x0040
        L_0x003c:
            java.util.Map r1 = kotlin.collections.MapsKt.emptyMap()     // Catch:{ Exception -> 0x0051 }
        L_0x0040:
            com.datadog.android.rum.GlobalRum r2 = com.datadog.android.rum.GlobalRum.INSTANCE     // Catch:{ Exception -> 0x0051 }
            com.datadog.android.rum.RumMonitor r2 = r2.getMonitor$dd_sdk_android_release()     // Catch:{ Exception -> 0x0051 }
            r2.startView(r7, r0, r1)     // Catch:{ Exception -> 0x0051 }
            com.datadog.android.rum.internal.tracking.ViewLoadingTimer r6 = r6.getViewLoadingTimer$dd_sdk_android_release()     // Catch:{ Exception -> 0x0051 }
            r6.onFinishedLoading(r7)     // Catch:{ Exception -> 0x0051 }
            goto L_0x0061
        L_0x0051:
            r6 = move-exception
            com.datadog.android.log.Logger r0 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            r2 = r6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = 0
            r4 = 4
            r5 = 0
            java.lang.String r1 = "Internal operation failed"
            com.datadog.android.log.internal.utils.LogUtilsKt.errorWithTelemetry$default(r0, r1, r2, r3, r4, r5)
        L_0x0061:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.tracking.ActivityViewTrackingStrategy.onActivityResumed(android.app.Activity):void");
    }

    public void onActivityPostResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.componentPredicate.accept(activity)) {
            try {
                getViewLoadingTimer$dd_sdk_android_release().onFinishedLoading(activity);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityPaused(activity);
        if (this.componentPredicate.accept(activity)) {
            try {
                updateLoadingTime(activity);
                RumMonitor.DefaultImpls.stopView$default(GlobalRum.INSTANCE.getMonitor$dd_sdk_android_release(), activity, (Map) null, 2, (Object) null);
                getViewLoadingTimer$dd_sdk_android_release().onPaused(activity);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityDestroyed(activity);
        if (this.componentPredicate.accept(activity)) {
            try {
                getViewLoadingTimer$dd_sdk_android_release().onDestroyed(activity);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj == null ? null : obj.getClass())) {
            return false;
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type com.datadog.android.rum.tracking.ActivityViewTrackingStrategy");
        ActivityViewTrackingStrategy activityViewTrackingStrategy = (ActivityViewTrackingStrategy) obj;
        return this.trackExtras == activityViewTrackingStrategy.trackExtras && Intrinsics.areEqual((Object) this.componentPredicate, (Object) activityViewTrackingStrategy.componentPredicate);
    }

    public int hashCode() {
        return (Boolean.hashCode(this.trackExtras) * 31) + this.componentPredicate.hashCode();
    }

    private final void updateLoadingTime(Activity activity) {
        Long loadingTime = this.viewLoadingTimer.getLoadingTime(activity);
        if (loadingTime != null) {
            long longValue = loadingTime.longValue();
            RumMonitor rumMonitor = GlobalRum.get();
            AdvancedRumMonitor advancedRumMonitor = rumMonitor instanceof AdvancedRumMonitor ? (AdvancedRumMonitor) rumMonitor : null;
            if (advancedRumMonitor != null) {
                advancedRumMonitor.updateViewLoadingTime(activity, longValue, resolveLoadingType(getViewLoadingTimer$dd_sdk_android_release().isFirstTimeLoading(activity)));
            }
        }
    }

    private final ViewEvent.LoadingType resolveLoadingType(boolean z) {
        if (z) {
            return ViewEvent.LoadingType.ACTIVITY_DISPLAY;
        }
        return ViewEvent.LoadingType.ACTIVITY_REDISPLAY;
    }
}
