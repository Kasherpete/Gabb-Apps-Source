package com.datadog.android.rum.internal.tracking;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import com.datadog.android.core.internal.system.BuildSdkVersionProvider;
import com.datadog.android.core.internal.system.DefaultBuildSdkVersionProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.internal.RumFeature;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.rum.model.ViewEvent;
import com.datadog.android.rum.tracking.ComponentPredicate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 ,2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001,BY\u0012 \u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00070\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\"\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J$\u0010\u001f\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u001a\u0010\"\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0016J\u0018\u0010#\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0016J\u0018\u0010$\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0016J\u001a\u0010%\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0016J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\u0002H\u0016J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0016H\u0002J\u0010\u0010+\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\u0002H\u0016R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R(\u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00070\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/tracking/OreoFragmentLifecycleCallbacks;", "Lcom/datadog/android/rum/internal/tracking/FragmentLifecycleCallbacks;", "Landroid/app/Activity;", "Landroid/app/FragmentManager$FragmentLifecycleCallbacks;", "argumentsProvider", "Lkotlin/Function1;", "Landroid/app/Fragment;", "", "", "", "componentPredicate", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "viewLoadingTimer", "Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;", "rumMonitor", "Lcom/datadog/android/rum/RumMonitor;", "advancedRumMonitor", "Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "buildSdkVersionProvider", "Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;", "(Lkotlin/jvm/functions/Function1;Lcom/datadog/android/rum/tracking/ComponentPredicate;Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;Lcom/datadog/android/rum/RumMonitor;Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;)V", "isNotAViewFragment", "", "fragment", "onFragmentActivityCreated", "", "fm", "Landroid/app/FragmentManager;", "f", "savedInstanceState", "Landroid/os/Bundle;", "onFragmentAttached", "context", "Landroid/content/Context;", "onFragmentDestroyed", "onFragmentPaused", "onFragmentResumed", "onFragmentStarted", "register", "activity", "resolveLoadingType", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "firstTimeLoading", "unregister", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: OreoFragmentLifecycleCallbacks.kt */
public final class OreoFragmentLifecycleCallbacks extends FragmentManager.FragmentLifecycleCallbacks implements FragmentLifecycleCallbacks<Activity> {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Deprecated
    private static final String REPORT_FRAGMENT_NAME = "androidx.lifecycle.ReportFragment";
    private final AdvancedRumMonitor advancedRumMonitor;
    private final Function1<Fragment, Map<String, Object>> argumentsProvider;
    private final BuildSdkVersionProvider buildSdkVersionProvider;
    private final ComponentPredicate<Fragment> componentPredicate;
    private final RumMonitor rumMonitor;
    private final ViewLoadingTimer viewLoadingTimer;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OreoFragmentLifecycleCallbacks(Function1 function1, ComponentPredicate componentPredicate2, ViewLoadingTimer viewLoadingTimer2, RumMonitor rumMonitor2, AdvancedRumMonitor advancedRumMonitor2, BuildSdkVersionProvider buildSdkVersionProvider2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, componentPredicate2, (i & 4) != 0 ? new ViewLoadingTimer() : viewLoadingTimer2, rumMonitor2, advancedRumMonitor2, (i & 32) != 0 ? new DefaultBuildSdkVersionProvider() : buildSdkVersionProvider2);
    }

    public OreoFragmentLifecycleCallbacks(Function1<? super Fragment, ? extends Map<String, ? extends Object>> function1, ComponentPredicate<Fragment> componentPredicate2, ViewLoadingTimer viewLoadingTimer2, RumMonitor rumMonitor2, AdvancedRumMonitor advancedRumMonitor2, BuildSdkVersionProvider buildSdkVersionProvider2) {
        Intrinsics.checkNotNullParameter(function1, "argumentsProvider");
        Intrinsics.checkNotNullParameter(componentPredicate2, "componentPredicate");
        Intrinsics.checkNotNullParameter(viewLoadingTimer2, "viewLoadingTimer");
        Intrinsics.checkNotNullParameter(rumMonitor2, "rumMonitor");
        Intrinsics.checkNotNullParameter(advancedRumMonitor2, "advancedRumMonitor");
        Intrinsics.checkNotNullParameter(buildSdkVersionProvider2, "buildSdkVersionProvider");
        this.argumentsProvider = function1;
        this.componentPredicate = componentPredicate2;
        this.viewLoadingTimer = viewLoadingTimer2;
        this.rumMonitor = rumMonitor2;
        this.advancedRumMonitor = advancedRumMonitor2;
        this.buildSdkVersionProvider = buildSdkVersionProvider2;
    }

    public void register(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.buildSdkVersionProvider.version() >= 26) {
            activity.getFragmentManager().registerFragmentLifecycleCallbacks(this, true);
        }
    }

    public void unregister(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.buildSdkVersionProvider.version() >= 26) {
            activity.getFragmentManager().unregisterFragmentLifecycleCallbacks(this);
        }
    }

    public void onFragmentActivityCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fm");
        Intrinsics.checkNotNullParameter(fragment, "f");
        super.onFragmentActivityCreated(fragmentManager, fragment, bundle);
        if (!isNotAViewFragment(fragment)) {
            Context context = fragment.getContext();
            if ((fragment instanceof DialogFragment) && context != null) {
                Dialog dialog = ((DialogFragment) fragment).getDialog();
                RumFeature.INSTANCE.getActionTrackingStrategy$dd_sdk_android_release().getGesturesTracker().startTracking(dialog == null ? null : dialog.getWindow(), context);
            }
        }
    }

    public void onFragmentAttached(FragmentManager fragmentManager, Fragment fragment, Context context) {
        Intrinsics.checkNotNullParameter(fragment, "f");
        super.onFragmentAttached(fragmentManager, fragment, context);
        if (!isNotAViewFragment(fragment) && this.componentPredicate.accept(fragment)) {
            try {
                this.viewLoadingTimer.onCreated(fragment);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "f");
        super.onFragmentStarted(fragmentManager, fragment);
        if (!isNotAViewFragment(fragment) && this.componentPredicate.accept(fragment)) {
            try {
                this.viewLoadingTimer.onStartLoading(fragment);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033 A[Catch:{ Exception -> 0x0065 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051 A[Catch:{ Exception -> 0x0065 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFragmentResumed(android.app.FragmentManager r7, android.app.Fragment r8) {
        /*
            r6 = this;
            java.lang.String r0 = "fm"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "f"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            super.onFragmentResumed(r7, r8)
            boolean r7 = r6.isNotAViewFragment(r8)
            if (r7 == 0) goto L_0x0014
            return
        L_0x0014:
            com.datadog.android.rum.tracking.ComponentPredicate<android.app.Fragment> r7 = r6.componentPredicate
            boolean r7 = r7.accept(r8)
            if (r7 == 0) goto L_0x0075
            com.datadog.android.rum.tracking.ComponentPredicate<android.app.Fragment> r7 = r6.componentPredicate     // Catch:{ Exception -> 0x0065 }
            java.lang.String r7 = r7.getViewName(r8)     // Catch:{ Exception -> 0x0065 }
            r0 = r7
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ Exception -> 0x0065 }
            if (r0 == 0) goto L_0x0030
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)     // Catch:{ Exception -> 0x0065 }
            if (r0 == 0) goto L_0x002e
            goto L_0x0030
        L_0x002e:
            r0 = 0
            goto L_0x0031
        L_0x0030:
            r0 = 1
        L_0x0031:
            if (r0 == 0) goto L_0x0037
            java.lang.String r7 = com.datadog.android.core.internal.utils.ViewUtilsKt.resolveViewUrl((java.lang.Object) r8)     // Catch:{ Exception -> 0x0065 }
        L_0x0037:
            com.datadog.android.rum.internal.tracking.ViewLoadingTimer r0 = r6.viewLoadingTimer     // Catch:{ Exception -> 0x0065 }
            r0.onFinishedLoading(r8)     // Catch:{ Exception -> 0x0065 }
            com.datadog.android.rum.RumMonitor r0 = r6.rumMonitor     // Catch:{ Exception -> 0x0065 }
            kotlin.jvm.functions.Function1<android.app.Fragment, java.util.Map<java.lang.String, java.lang.Object>> r1 = r6.argumentsProvider     // Catch:{ Exception -> 0x0065 }
            java.lang.Object r1 = r1.invoke(r8)     // Catch:{ Exception -> 0x0065 }
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ Exception -> 0x0065 }
            r0.startView(r8, r7, r1)     // Catch:{ Exception -> 0x0065 }
            com.datadog.android.rum.internal.tracking.ViewLoadingTimer r7 = r6.viewLoadingTimer     // Catch:{ Exception -> 0x0065 }
            java.lang.Long r7 = r7.getLoadingTime(r8)     // Catch:{ Exception -> 0x0065 }
            if (r7 == 0) goto L_0x0075
            com.datadog.android.rum.internal.monitor.AdvancedRumMonitor r0 = r6.advancedRumMonitor     // Catch:{ Exception -> 0x0065 }
            long r1 = r7.longValue()     // Catch:{ Exception -> 0x0065 }
            com.datadog.android.rum.internal.tracking.ViewLoadingTimer r7 = r6.viewLoadingTimer     // Catch:{ Exception -> 0x0065 }
            boolean r7 = r7.isFirstTimeLoading(r8)     // Catch:{ Exception -> 0x0065 }
            com.datadog.android.rum.model.ViewEvent$LoadingType r6 = r6.resolveLoadingType(r7)     // Catch:{ Exception -> 0x0065 }
            r0.updateViewLoadingTime(r8, r1, r6)     // Catch:{ Exception -> 0x0065 }
            goto L_0x0075
        L_0x0065:
            r6 = move-exception
            com.datadog.android.log.Logger r0 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            r2 = r6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = 0
            r4 = 4
            r5 = 0
            java.lang.String r1 = "Internal operation failed"
            com.datadog.android.log.internal.utils.LogUtilsKt.errorWithTelemetry$default(r0, r1, r2, r3, r4, r5)
        L_0x0075:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.tracking.OreoFragmentLifecycleCallbacks.onFragmentResumed(android.app.FragmentManager, android.app.Fragment):void");
    }

    public void onFragmentPaused(FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fm");
        Intrinsics.checkNotNullParameter(fragment, "f");
        super.onFragmentPaused(fragmentManager, fragment);
        if (!isNotAViewFragment(fragment) && this.componentPredicate.accept(fragment)) {
            try {
                RumMonitor.DefaultImpls.stopView$default(this.rumMonitor, fragment, (Map) null, 2, (Object) null);
                this.viewLoadingTimer.onPaused(fragment);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "f");
        super.onFragmentDestroyed(fragmentManager, fragment);
        if (!isNotAViewFragment(fragment) && this.componentPredicate.accept(fragment)) {
            try {
                this.viewLoadingTimer.onDestroyed(fragment);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    private final ViewEvent.LoadingType resolveLoadingType(boolean z) {
        if (z) {
            return ViewEvent.LoadingType.FRAGMENT_DISPLAY;
        }
        return ViewEvent.LoadingType.FRAGMENT_REDISPLAY;
    }

    private final boolean isNotAViewFragment(Fragment fragment) {
        return Intrinsics.areEqual((Object) fragment.getClass().getName(), (Object) REPORT_FRAGMENT_NAME);
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/tracking/OreoFragmentLifecycleCallbacks$Companion;", "", "()V", "REPORT_FRAGMENT_NAME", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: OreoFragmentLifecycleCallbacks.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
