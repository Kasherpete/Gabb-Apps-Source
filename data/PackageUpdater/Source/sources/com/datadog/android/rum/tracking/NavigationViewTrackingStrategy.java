package com.datadog.android.rum.tracking;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.ActivityKt;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumActionType;
import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.RumResourceKind;
import com.datadog.android.rum.internal.debug.RumDebugListener;
import com.datadog.android.rum.internal.domain.event.ResourceTiming;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.rum.internal.monitor.EventType;
import com.datadog.android.rum.internal.tracking.AndroidXFragmentLifecycleCallbacks;
import com.datadog.android.rum.model.ViewEvent;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0002\"#B'\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\"\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0006\u0010\u001e\u001a\u00020\u0014J\u0006\u0010\u001f\u001a\u00020\u0014J\u0018\u0010 \u001a\u0004\u0018\u00010\u001a*\u00020\u000e2\b\b\u0001\u0010!\u001a\u00020\u0005H\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/NavigationViewTrackingStrategy;", "Lcom/datadog/android/rum/tracking/ActivityLifecycleTrackingStrategy;", "Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;", "Landroidx/navigation/NavController$OnDestinationChangedListener;", "navigationViewId", "", "trackArguments", "", "componentPredicate", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "Landroidx/navigation/NavDestination;", "(IZLcom/datadog/android/rum/tracking/ComponentPredicate;)V", "lifecycleCallbackRefs", "Ljava/util/WeakHashMap;", "Landroid/app/Activity;", "Lcom/datadog/android/rum/tracking/NavigationViewTrackingStrategy$NavControllerFragmentLifecycleCallbacks;", "predicate", "Landroidx/fragment/app/Fragment;", "startedActivity", "onActivityPaused", "", "activity", "onActivityStarted", "onActivityStopped", "onDestinationChanged", "controller", "Landroidx/navigation/NavController;", "destination", "arguments", "Landroid/os/Bundle;", "startTracking", "stopTracking", "findNavControllerOrNull", "viewId", "AdvancedMonitorDecorator", "NavControllerFragmentLifecycleCallbacks", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NavigationViewTrackingStrategy.kt */
public final class NavigationViewTrackingStrategy extends ActivityLifecycleTrackingStrategy implements ViewTrackingStrategy, NavController.OnDestinationChangedListener {
    private final ComponentPredicate<NavDestination> componentPredicate;
    private WeakHashMap<Activity, NavControllerFragmentLifecycleCallbacks> lifecycleCallbackRefs;
    private final int navigationViewId;
    private final ComponentPredicate<Fragment> predicate;
    private Activity startedActivity;
    private final boolean trackArguments;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NavigationViewTrackingStrategy(int i, boolean z, ComponentPredicate componentPredicate2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, z, (i2 & 4) != 0 ? new AcceptAllNavDestinations() : componentPredicate2);
    }

    public NavigationViewTrackingStrategy(int i, boolean z, ComponentPredicate<NavDestination> componentPredicate2) {
        Intrinsics.checkNotNullParameter(componentPredicate2, "componentPredicate");
        this.navigationViewId = i;
        this.trackArguments = z;
        this.componentPredicate = componentPredicate2;
        this.lifecycleCallbackRefs = new WeakHashMap<>();
        this.predicate = new NavigationViewTrackingStrategy$predicate$1();
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityStarted(activity);
        this.startedActivity = activity;
        startTracking();
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityStopped(activity);
        stopTracking();
        this.startedActivity = null;
    }

    public void onActivityPaused(Activity activity) {
        NavDestination currentDestination;
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onActivityPaused(activity);
        NavController findNavControllerOrNull = findNavControllerOrNull(activity, this.navigationViewId);
        if (findNavControllerOrNull != null && (currentDestination = findNavControllerOrNull.getCurrentDestination()) != null) {
            RumMonitor.DefaultImpls.stopView$default(GlobalRum.get(), currentDestination, (Map) null, 2, (Object) null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036 A[Catch:{ Exception -> 0x0042 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestinationChanged(androidx.navigation.NavController r7, androidx.navigation.NavDestination r8, android.os.Bundle r9) {
        /*
            r6 = this;
            java.lang.String r0 = "controller"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r7 = "destination"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r7)
            com.datadog.android.rum.tracking.ComponentPredicate<androidx.navigation.NavDestination> r7 = r6.componentPredicate
            boolean r7 = r7.accept(r8)
            if (r7 == 0) goto L_0x0052
            boolean r7 = r6.trackArguments     // Catch:{ Exception -> 0x0042 }
            if (r7 == 0) goto L_0x001b
            java.util.Map r7 = r6.convertToRumAttributes((android.os.Bundle) r9)     // Catch:{ Exception -> 0x0042 }
            goto L_0x001f
        L_0x001b:
            java.util.Map r7 = kotlin.collections.MapsKt.emptyMap()     // Catch:{ Exception -> 0x0042 }
        L_0x001f:
            com.datadog.android.rum.tracking.ComponentPredicate<androidx.navigation.NavDestination> r6 = r6.componentPredicate     // Catch:{ Exception -> 0x0042 }
            java.lang.String r6 = r6.getViewName(r8)     // Catch:{ Exception -> 0x0042 }
            r9 = r6
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ Exception -> 0x0042 }
            if (r9 == 0) goto L_0x0033
            boolean r9 = kotlin.text.StringsKt.isBlank(r9)     // Catch:{ Exception -> 0x0042 }
            if (r9 == 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r9 = 0
            goto L_0x0034
        L_0x0033:
            r9 = 1
        L_0x0034:
            if (r9 == 0) goto L_0x003a
            java.lang.String r6 = com.datadog.android.core.internal.utils.ViewUtilsKt.resolveViewUrl((java.lang.Object) r8)     // Catch:{ Exception -> 0x0042 }
        L_0x003a:
            com.datadog.android.rum.RumMonitor r9 = com.datadog.android.rum.GlobalRum.get()     // Catch:{ Exception -> 0x0042 }
            r9.startView(r8, r6, r7)     // Catch:{ Exception -> 0x0042 }
            goto L_0x0052
        L_0x0042:
            r6 = move-exception
            com.datadog.android.log.Logger r0 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            r2 = r6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = 0
            r4 = 4
            r5 = 0
            java.lang.String r1 = "Internal operation failed"
            com.datadog.android.log.internal.utils.LogUtilsKt.errorWithTelemetry$default(r0, r1, r2, r3, r4, r5)
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.tracking.NavigationViewTrackingStrategy.onDestinationChanged(androidx.navigation.NavController, androidx.navigation.NavDestination, android.os.Bundle):void");
    }

    public final void startTracking() {
        NavController findNavControllerOrNull;
        Activity activity = this.startedActivity;
        if (activity != null && (findNavControllerOrNull = findNavControllerOrNull(activity, this.navigationViewId)) != null) {
            if (FragmentActivity.class.isAssignableFrom(activity.getClass())) {
                NavControllerFragmentLifecycleCallbacks navControllerFragmentLifecycleCallbacks = new NavControllerFragmentLifecycleCallbacks(findNavControllerOrNull, C0870x19361d4b.INSTANCE, this.predicate);
                Activity activity2 = this.startedActivity;
                Objects.requireNonNull(activity2, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                navControllerFragmentLifecycleCallbacks.register((FragmentActivity) activity2);
                this.lifecycleCallbackRefs.put(this.startedActivity, navControllerFragmentLifecycleCallbacks);
            }
            findNavControllerOrNull.addOnDestinationChangedListener(this);
        }
    }

    public final void stopTracking() {
        NavController findNavControllerOrNull;
        NavControllerFragmentLifecycleCallbacks remove;
        Activity activity = this.startedActivity;
        if (activity != null && (findNavControllerOrNull = findNavControllerOrNull(activity, this.navigationViewId)) != null) {
            findNavControllerOrNull.removeOnDestinationChangedListener(this);
            if (FragmentActivity.class.isAssignableFrom(activity.getClass()) && (remove = this.lifecycleCallbackRefs.remove(activity)) != null) {
                remove.unregister((FragmentActivity) activity);
            }
        }
    }

    private final NavController findNavControllerOrNull(Activity activity, int i) {
        try {
            return ActivityKt.findNavController(activity, i);
        } catch (IllegalArgumentException unused) {
            return null;
        } catch (IllegalStateException unused2) {
            return null;
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012 \u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00070\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/NavigationViewTrackingStrategy$NavControllerFragmentLifecycleCallbacks;", "Lcom/datadog/android/rum/internal/tracking/AndroidXFragmentLifecycleCallbacks;", "navController", "Landroidx/navigation/NavController;", "argumentsProvider", "Lkotlin/Function1;", "Landroidx/fragment/app/Fragment;", "", "", "", "componentPredicate", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "(Landroidx/navigation/NavController;Lkotlin/jvm/functions/Function1;Lcom/datadog/android/rum/tracking/ComponentPredicate;)V", "resolveKey", "fragment", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: NavigationViewTrackingStrategy.kt */
    public static final class NavControllerFragmentLifecycleCallbacks extends AndroidXFragmentLifecycleCallbacks {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final Object NO_DESTINATION_FOUND = new Object();
        private final NavController navController;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public NavControllerFragmentLifecycleCallbacks(androidx.navigation.NavController r10, kotlin.jvm.functions.Function1<? super androidx.fragment.app.Fragment, ? extends java.util.Map<java.lang.String, ? extends java.lang.Object>> r11, com.datadog.android.rum.tracking.ComponentPredicate<androidx.fragment.app.Fragment> r12) {
            /*
                r9 = this;
                java.lang.String r0 = "navController"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                java.lang.String r0 = "argumentsProvider"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                java.lang.String r0 = "componentPredicate"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
                com.datadog.android.rum.NoOpRumMonitor r0 = new com.datadog.android.rum.NoOpRumMonitor
                r0.<init>()
                r5 = r0
                com.datadog.android.rum.RumMonitor r5 = (com.datadog.android.rum.RumMonitor) r5
                com.datadog.android.rum.tracking.NavigationViewTrackingStrategy$AdvancedMonitorDecorator r0 = new com.datadog.android.rum.tracking.NavigationViewTrackingStrategy$AdvancedMonitorDecorator
                com.datadog.android.rum.RumMonitor r1 = com.datadog.android.rum.GlobalRum.get()
                boolean r2 = r1 instanceof com.datadog.android.rum.internal.monitor.AdvancedRumMonitor
                if (r2 == 0) goto L_0x0024
                com.datadog.android.rum.internal.monitor.AdvancedRumMonitor r1 = (com.datadog.android.rum.internal.monitor.AdvancedRumMonitor) r1
                goto L_0x0025
            L_0x0024:
                r1 = 0
            L_0x0025:
                if (r1 != 0) goto L_0x002e
                com.datadog.android.rum.internal.monitor.NoOpAdvancedRumMonitor r1 = new com.datadog.android.rum.internal.monitor.NoOpAdvancedRumMonitor
                r1.<init>()
                com.datadog.android.rum.internal.monitor.AdvancedRumMonitor r1 = (com.datadog.android.rum.internal.monitor.AdvancedRumMonitor) r1
            L_0x002e:
                r0.<init>(r1)
                r6 = r0
                com.datadog.android.rum.internal.monitor.AdvancedRumMonitor r6 = (com.datadog.android.rum.internal.monitor.AdvancedRumMonitor) r6
                r7 = 4
                r8 = 0
                r4 = 0
                r1 = r9
                r2 = r11
                r3 = r12
                r1.<init>(r2, r3, r4, r5, r6, r7, r8)
                r9.navController = r10
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.tracking.NavigationViewTrackingStrategy.NavControllerFragmentLifecycleCallbacks.<init>(androidx.navigation.NavController, kotlin.jvm.functions.Function1, com.datadog.android.rum.tracking.ComponentPredicate):void");
        }

        public Object resolveKey(Fragment fragment) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            NavDestination currentDestination = this.navController.getCurrentDestination();
            return currentDestination == null ? NO_DESTINATION_FOUND : currentDestination;
        }

        @Metadata(mo20734d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/NavigationViewTrackingStrategy$NavControllerFragmentLifecycleCallbacks$Companion;", "", "()V", "NO_DESTINATION_FOUND", "getNO_DESTINATION_FOUND", "()Ljava/lang/Object;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: NavigationViewTrackingStrategy.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Object getNO_DESTINATION_FOUND() {
                return NavControllerFragmentLifecycleCallbacks.NO_DESTINATION_FOUND;
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0001J9\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001J9\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001J\u0019\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H\u0001J\u0019\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0001J\u0011\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0007H\u0001J/\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u00072\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001J\u0019\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020!H\u0001J\u0019\u0010\"\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020!H\u0001J\t\u0010#\u001a\u00020\u0005H\u0001J\u0011\u0010$\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001J\u001b\u0010%\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0001J\t\u0010&\u001a\u00020\u0005H\u0001J\u0013\u0010'\u001a\u00020\u00052\b\u0010(\u001a\u0004\u0018\u00010)H\u0001J7\u0010*\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u00072\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001J/\u0010-\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u00072\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001J/\u0010.\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00072\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001JH\u0010/\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00072\b\u00100\u001a\u0004\u0018\u0001012\b\u00102\u001a\u0004\u0018\u00010\u00142\u0006\u00103\u001a\u0002042\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001¢\u0006\u0002\u00105JX\u00106\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00072\b\u00100\u001a\u0004\u0018\u0001012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u00107\u001a\u00020\u00072\b\u00108\u001a\u0004\u0018\u00010\u00072\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001¢\u0006\u0002\u00109JN\u00106\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00072\b\u00100\u001a\u0004\u0018\u0001012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001¢\u0006\u0002\u0010:J/\u0010;\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u00072\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001J\u001f\u0010;\u001a\u00020\u00052\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001J'\u0010<\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000f2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0001J \u0010=\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020?H\u0016J\u0011\u0010@\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0007H\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/NavigationViewTrackingStrategy$AdvancedMonitorDecorator;", "Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "advancedRumMonitor", "(Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;)V", "addCrash", "", "message", "", "source", "Lcom/datadog/android/rum/RumErrorSource;", "throwable", "", "addError", "attributes", "", "", "addErrorWithStacktrace", "stacktrace", "addLongTask", "durationNs", "", "target", "addResourceTiming", "key", "timing", "Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "addTiming", "name", "addUserAction", "type", "Lcom/datadog/android/rum/RumActionType;", "eventDropped", "viewId", "Lcom/datadog/android/rum/internal/monitor/EventType;", "eventSent", "resetSession", "sendDebugTelemetryEvent", "sendErrorTelemetryEvent", "sendWebViewEvent", "setDebugListener", "listener", "Lcom/datadog/android/rum/internal/debug/RumDebugListener;", "startResource", "method", "url", "startUserAction", "startView", "stopResource", "statusCode", "", "size", "kind", "Lcom/datadog/android/rum/RumResourceKind;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Lcom/datadog/android/rum/RumResourceKind;Ljava/util/Map;)V", "stopResourceWithError", "stackTrace", "errorType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/Throwable;Ljava/util/Map;)V", "stopUserAction", "stopView", "updateViewLoadingTime", "loadingTimeInNs", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "waitForResourceTiming", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: NavigationViewTrackingStrategy.kt */
    public static final class AdvancedMonitorDecorator implements AdvancedRumMonitor {
        private final AdvancedRumMonitor advancedRumMonitor;

        public void addCrash(String str, RumErrorSource rumErrorSource, Throwable th) {
            Intrinsics.checkNotNullParameter(str, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(th, "throwable");
            this.advancedRumMonitor.addCrash(str, rumErrorSource, th);
        }

        public void addError(String str, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(str, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.addError(str, rumErrorSource, th, map);
        }

        public void addErrorWithStacktrace(String str, RumErrorSource rumErrorSource, String str2, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(str, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.addErrorWithStacktrace(str, rumErrorSource, str2, map);
        }

        public void addLongTask(long j, String str) {
            Intrinsics.checkNotNullParameter(str, "target");
            this.advancedRumMonitor.addLongTask(j, str);
        }

        public void addResourceTiming(String str, ResourceTiming resourceTiming) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(resourceTiming, "timing");
            this.advancedRumMonitor.addResourceTiming(str, resourceTiming);
        }

        public void addTiming(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.advancedRumMonitor.addTiming(str);
        }

        public void addUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.addUserAction(rumActionType, str, map);
        }

        public void eventDropped(String str, EventType eventType) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(eventType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            this.advancedRumMonitor.eventDropped(str, eventType);
        }

        public void eventSent(String str, EventType eventType) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(eventType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            this.advancedRumMonitor.eventSent(str, eventType);
        }

        public void resetSession() {
            this.advancedRumMonitor.resetSession();
        }

        public void sendDebugTelemetryEvent(String str) {
            Intrinsics.checkNotNullParameter(str, "message");
            this.advancedRumMonitor.sendDebugTelemetryEvent(str);
        }

        public void sendErrorTelemetryEvent(String str, Throwable th) {
            Intrinsics.checkNotNullParameter(str, "message");
            this.advancedRumMonitor.sendErrorTelemetryEvent(str, th);
        }

        public void sendWebViewEvent() {
            this.advancedRumMonitor.sendWebViewEvent();
        }

        public void setDebugListener(RumDebugListener rumDebugListener) {
            this.advancedRumMonitor.setDebugListener(rumDebugListener);
        }

        public void startResource(String str, String str2, String str3, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "method");
            Intrinsics.checkNotNullParameter(str3, "url");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.startResource(str, str2, str3, map);
        }

        public void startUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.startUserAction(rumActionType, str, map);
        }

        public void startView(Object obj, String str, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(obj, "key");
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.startView(obj, str, map);
        }

        public void stopResource(String str, Integer num, Long l, RumResourceKind rumResourceKind, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(rumResourceKind, "kind");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.stopResource(str, num, l, rumResourceKind, map);
        }

        public void stopResourceWithError(String str, Integer num, String str2, RumErrorSource rumErrorSource, String str3, String str4, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(str3, "stackTrace");
            Map<String, ? extends Object> map2 = map;
            Intrinsics.checkNotNullParameter(map2, "attributes");
            this.advancedRumMonitor.stopResourceWithError(str, num, str2, rumErrorSource, str3, str4, map2);
        }

        public void stopResourceWithError(String str, Integer num, String str2, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(th, "throwable");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.stopResourceWithError(str, num, str2, rumErrorSource, th, map);
        }

        public void stopUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.stopUserAction(rumActionType, str, map);
        }

        @Deprecated(message = "This method is deprecated. Please use RumMonitor#stopUserAction(type, name, attributes) instead")
        public void stopUserAction(Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.stopUserAction(map);
        }

        public void stopView(Object obj, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(obj, "key");
            Intrinsics.checkNotNullParameter(map, "attributes");
            this.advancedRumMonitor.stopView(obj, map);
        }

        public void waitForResourceTiming(String str) {
            Intrinsics.checkNotNullParameter(str, "key");
            this.advancedRumMonitor.waitForResourceTiming(str);
        }

        public AdvancedMonitorDecorator(AdvancedRumMonitor advancedRumMonitor2) {
            Intrinsics.checkNotNullParameter(advancedRumMonitor2, "advancedRumMonitor");
            this.advancedRumMonitor = advancedRumMonitor2;
        }

        public void updateViewLoadingTime(Object obj, long j, ViewEvent.LoadingType loadingType) {
            Intrinsics.checkNotNullParameter(obj, "key");
            Intrinsics.checkNotNullParameter(loadingType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            if (!Intrinsics.areEqual(obj, NavControllerFragmentLifecycleCallbacks.Companion.getNO_DESTINATION_FOUND())) {
                this.advancedRumMonitor.updateViewLoadingTime(obj, j, loadingType);
            }
        }
    }
}
