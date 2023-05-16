package com.datadog.android.rum.tracking;

import androidx.fragment.app.Fragment;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.rum.internal.monitor.NoOpAdvancedRumMonitor;
import com.datadog.android.rum.internal.tracking.AndroidXFragmentLifecycleCallbacks;
import com.datadog.android.rum.internal.tracking.ViewLoadingTimer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "Lcom/datadog/android/rum/internal/tracking/AndroidXFragmentLifecycleCallbacks;", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FragmentViewTrackingStrategy.kt */
final class FragmentViewTrackingStrategy$androidXLifecycleCallbacks$2 extends Lambda implements Function0<AndroidXFragmentLifecycleCallbacks> {
    final /* synthetic */ FragmentViewTrackingStrategy this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FragmentViewTrackingStrategy$androidXLifecycleCallbacks$2(FragmentViewTrackingStrategy fragmentViewTrackingStrategy) {
        super(0);
        this.this$0 = fragmentViewTrackingStrategy;
    }

    public final AndroidXFragmentLifecycleCallbacks invoke() {
        final FragmentViewTrackingStrategy fragmentViewTrackingStrategy = this.this$0;
        Function1 r1 = new Function1<Fragment, Map<String, ? extends Object>>() {
            public final Map<String, Object> invoke(Fragment fragment) {
                Intrinsics.checkNotNullParameter(fragment, "it");
                return fragmentViewTrackingStrategy.getTrackArguments$dd_sdk_android_release() ? fragmentViewTrackingStrategy.convertToRumAttributes(fragment.getArguments()) : MapsKt.emptyMap();
            }
        };
        ComponentPredicate<Fragment> supportFragmentComponentPredicate$dd_sdk_android_release = this.this$0.getSupportFragmentComponentPredicate$dd_sdk_android_release();
        RumMonitor rumMonitor = GlobalRum.get();
        RumMonitor rumMonitor2 = GlobalRum.get();
        AdvancedRumMonitor advancedRumMonitor = rumMonitor2 instanceof AdvancedRumMonitor ? (AdvancedRumMonitor) rumMonitor2 : null;
        if (advancedRumMonitor == null) {
            advancedRumMonitor = new NoOpAdvancedRumMonitor();
        }
        return new AndroidXFragmentLifecycleCallbacks(r1, supportFragmentComponentPredicate$dd_sdk_android_release, (ViewLoadingTimer) null, rumMonitor, advancedRumMonitor, 4, (DefaultConstructorMarker) null);
    }
}
