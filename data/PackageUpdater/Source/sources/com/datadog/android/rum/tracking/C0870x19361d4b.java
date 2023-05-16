package com.datadog.android.rum.tracking;

import androidx.fragment.app.Fragment;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, mo20735d2 = {"<anonymous>", "", "", "", "it", "Landroidx/fragment/app/Fragment;", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* renamed from: com.datadog.android.rum.tracking.NavigationViewTrackingStrategy$startTracking$1$navControllerFragmentCallbacks$1 */
/* compiled from: NavigationViewTrackingStrategy.kt */
final class C0870x19361d4b extends Lambda implements Function1<Fragment, Map<String, ? extends Object>> {
    public static final C0870x19361d4b INSTANCE = new C0870x19361d4b();

    C0870x19361d4b() {
        super(1);
    }

    public final Map<String, Object> invoke(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "it");
        return MapsKt.emptyMap();
    }
}
