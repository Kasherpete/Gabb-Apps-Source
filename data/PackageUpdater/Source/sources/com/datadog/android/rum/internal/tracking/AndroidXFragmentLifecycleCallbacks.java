package com.datadog.android.rum.internal.tracking;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.internal.RumFeature;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.rum.model.ViewEvent;
import com.datadog.android.rum.tracking.ComponentPredicate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003BO\u0012 \u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00070\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\"\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J \u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\u0018\u0010$\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\u0018\u0010%\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\u0018\u0010&\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\u0010\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u0002H\u0016J\u0010\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\u0006H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u0002H\u0016R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R.\u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00070\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u00060"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/tracking/AndroidXFragmentLifecycleCallbacks;", "Lcom/datadog/android/rum/internal/tracking/FragmentLifecycleCallbacks;", "Landroidx/fragment/app/FragmentActivity;", "Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;", "argumentsProvider", "Lkotlin/Function1;", "Landroidx/fragment/app/Fragment;", "", "", "", "componentPredicate", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "viewLoadingTimer", "Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;", "rumMonitor", "Lcom/datadog/android/rum/RumMonitor;", "advancedRumMonitor", "Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "(Lkotlin/jvm/functions/Function1;Lcom/datadog/android/rum/tracking/ComponentPredicate;Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;Lcom/datadog/android/rum/RumMonitor;Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;)V", "getArgumentsProvider$dd_sdk_android_release", "()Lkotlin/jvm/functions/Function1;", "getViewLoadingTimer$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;", "setViewLoadingTimer$dd_sdk_android_release", "(Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;)V", "onFragmentActivityCreated", "", "fm", "Landroidx/fragment/app/FragmentManager;", "f", "savedInstanceState", "Landroid/os/Bundle;", "onFragmentAttached", "context", "Landroid/content/Context;", "onFragmentDestroyed", "onFragmentPaused", "onFragmentResumed", "onFragmentStarted", "register", "activity", "resolveKey", "fragment", "resolveLoadingType", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "firstTimeLoading", "", "unregister", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: AndroidXFragmentLifecycleCallbacks.kt */
public class AndroidXFragmentLifecycleCallbacks extends FragmentManager.FragmentLifecycleCallbacks implements FragmentLifecycleCallbacks<FragmentActivity> {
    private final AdvancedRumMonitor advancedRumMonitor;
    private final Function1<Fragment, Map<String, Object>> argumentsProvider;
    private final ComponentPredicate<Fragment> componentPredicate;
    private final RumMonitor rumMonitor;
    private ViewLoadingTimer viewLoadingTimer;

    public Object resolveKey(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        return fragment;
    }

    public final Function1<Fragment, Map<String, Object>> getArgumentsProvider$dd_sdk_android_release() {
        return this.argumentsProvider;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AndroidXFragmentLifecycleCallbacks(Function1 function1, ComponentPredicate componentPredicate2, ViewLoadingTimer viewLoadingTimer2, RumMonitor rumMonitor2, AdvancedRumMonitor advancedRumMonitor2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, componentPredicate2, (i & 4) != 0 ? new ViewLoadingTimer() : viewLoadingTimer2, rumMonitor2, advancedRumMonitor2);
    }

    public final ViewLoadingTimer getViewLoadingTimer$dd_sdk_android_release() {
        return this.viewLoadingTimer;
    }

    public final void setViewLoadingTimer$dd_sdk_android_release(ViewLoadingTimer viewLoadingTimer2) {
        Intrinsics.checkNotNullParameter(viewLoadingTimer2, "<set-?>");
        this.viewLoadingTimer = viewLoadingTimer2;
    }

    public AndroidXFragmentLifecycleCallbacks(Function1<? super Fragment, ? extends Map<String, ? extends Object>> function1, ComponentPredicate<Fragment> componentPredicate2, ViewLoadingTimer viewLoadingTimer2, RumMonitor rumMonitor2, AdvancedRumMonitor advancedRumMonitor2) {
        Intrinsics.checkNotNullParameter(function1, "argumentsProvider");
        Intrinsics.checkNotNullParameter(componentPredicate2, "componentPredicate");
        Intrinsics.checkNotNullParameter(viewLoadingTimer2, "viewLoadingTimer");
        Intrinsics.checkNotNullParameter(rumMonitor2, "rumMonitor");
        Intrinsics.checkNotNullParameter(advancedRumMonitor2, "advancedRumMonitor");
        this.argumentsProvider = function1;
        this.componentPredicate = componentPredicate2;
        this.viewLoadingTimer = viewLoadingTimer2;
        this.rumMonitor = rumMonitor2;
        this.advancedRumMonitor = advancedRumMonitor2;
    }

    public void register(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        fragmentActivity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(this, true);
    }

    public void unregister(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        fragmentActivity.getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this);
    }

    public void onFragmentAttached(FragmentManager fragmentManager, Fragment fragment, Context context) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fm");
        Intrinsics.checkNotNullParameter(fragment, "f");
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        super.onFragmentAttached(fragmentManager, fragment, context);
        if (this.componentPredicate.accept(fragment)) {
            try {
                getViewLoadingTimer$dd_sdk_android_release().onCreated(resolveKey(fragment));
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fm");
        Intrinsics.checkNotNullParameter(fragment, "f");
        super.onFragmentStarted(fragmentManager, fragment);
        if (this.componentPredicate.accept(fragment)) {
            try {
                getViewLoadingTimer$dd_sdk_android_release().onStartLoading(resolveKey(fragment));
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public void onFragmentActivityCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fm");
        Intrinsics.checkNotNullParameter(fragment, "f");
        super.onFragmentActivityCreated(fragmentManager, fragment, bundle);
        Context context = fragment.getContext();
        if ((fragment instanceof DialogFragment) && context != null) {
            Dialog dialog = ((DialogFragment) fragment).getDialog();
            RumFeature.INSTANCE.getActionTrackingStrategy$dd_sdk_android_release().getGesturesTracker().startTracking(dialog == null ? null : dialog.getWindow(), context);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037 A[Catch:{ Exception -> 0x006a }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0054 A[Catch:{ Exception -> 0x006a }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFragmentResumed(androidx.fragment.app.FragmentManager r7, androidx.fragment.app.Fragment r8) {
        /*
            r6 = this;
            java.lang.String r0 = "fm"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "f"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            super.onFragmentResumed(r7, r8)
            com.datadog.android.rum.tracking.ComponentPredicate<androidx.fragment.app.Fragment> r7 = r6.componentPredicate
            boolean r7 = r7.accept(r8)
            if (r7 == 0) goto L_0x007a
            java.lang.Object r7 = r6.resolveKey(r8)     // Catch:{ Exception -> 0x006a }
            com.datadog.android.rum.internal.tracking.ViewLoadingTimer r0 = r6.getViewLoadingTimer$dd_sdk_android_release()     // Catch:{ Exception -> 0x006a }
            r0.onFinishedLoading(r7)     // Catch:{ Exception -> 0x006a }
            com.datadog.android.rum.tracking.ComponentPredicate<androidx.fragment.app.Fragment> r0 = r6.componentPredicate     // Catch:{ Exception -> 0x006a }
            java.lang.String r0 = r0.getViewName(r8)     // Catch:{ Exception -> 0x006a }
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ Exception -> 0x006a }
            if (r1 == 0) goto L_0x0034
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)     // Catch:{ Exception -> 0x006a }
            if (r1 == 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r1 = 0
            goto L_0x0035
        L_0x0034:
            r1 = 1
        L_0x0035:
            if (r1 == 0) goto L_0x003b
            java.lang.String r0 = com.datadog.android.core.internal.utils.ViewUtilsKt.resolveViewUrl((java.lang.Object) r8)     // Catch:{ Exception -> 0x006a }
        L_0x003b:
            com.datadog.android.rum.RumMonitor r1 = r6.rumMonitor     // Catch:{ Exception -> 0x006a }
            kotlin.jvm.functions.Function1 r2 = r6.getArgumentsProvider$dd_sdk_android_release()     // Catch:{ Exception -> 0x006a }
            java.lang.Object r8 = r2.invoke(r8)     // Catch:{ Exception -> 0x006a }
            java.util.Map r8 = (java.util.Map) r8     // Catch:{ Exception -> 0x006a }
            r1.startView(r7, r0, r8)     // Catch:{ Exception -> 0x006a }
            com.datadog.android.rum.internal.tracking.ViewLoadingTimer r8 = r6.getViewLoadingTimer$dd_sdk_android_release()     // Catch:{ Exception -> 0x006a }
            java.lang.Long r8 = r8.getLoadingTime(r7)     // Catch:{ Exception -> 0x006a }
            if (r8 == 0) goto L_0x007a
            com.datadog.android.rum.internal.monitor.AdvancedRumMonitor r0 = r6.advancedRumMonitor     // Catch:{ Exception -> 0x006a }
            long r1 = r8.longValue()     // Catch:{ Exception -> 0x006a }
            com.datadog.android.rum.internal.tracking.ViewLoadingTimer r8 = r6.getViewLoadingTimer$dd_sdk_android_release()     // Catch:{ Exception -> 0x006a }
            boolean r8 = r8.isFirstTimeLoading(r7)     // Catch:{ Exception -> 0x006a }
            com.datadog.android.rum.model.ViewEvent$LoadingType r6 = r6.resolveLoadingType(r8)     // Catch:{ Exception -> 0x006a }
            r0.updateViewLoadingTime(r7, r1, r6)     // Catch:{ Exception -> 0x006a }
            goto L_0x007a
        L_0x006a:
            r6 = move-exception
            com.datadog.android.log.Logger r0 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            r2 = r6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = 0
            r4 = 4
            r5 = 0
            java.lang.String r1 = "Internal operation failed"
            com.datadog.android.log.internal.utils.LogUtilsKt.errorWithTelemetry$default(r0, r1, r2, r3, r4, r5)
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.tracking.AndroidXFragmentLifecycleCallbacks.onFragmentResumed(androidx.fragment.app.FragmentManager, androidx.fragment.app.Fragment):void");
    }

    public void onFragmentPaused(FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fm");
        Intrinsics.checkNotNullParameter(fragment, "f");
        super.onFragmentPaused(fragmentManager, fragment);
        if (this.componentPredicate.accept(fragment)) {
            try {
                Object resolveKey = resolveKey(fragment);
                RumMonitor.DefaultImpls.stopView$default(this.rumMonitor, resolveKey, (Map) null, 2, (Object) null);
                getViewLoadingTimer$dd_sdk_android_release().onPaused(resolveKey);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fm");
        Intrinsics.checkNotNullParameter(fragment, "f");
        super.onFragmentDestroyed(fragmentManager, fragment);
        if (this.componentPredicate.accept(fragment)) {
            try {
                getViewLoadingTimer$dd_sdk_android_release().onDestroyed(resolveKey(fragment));
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
}
