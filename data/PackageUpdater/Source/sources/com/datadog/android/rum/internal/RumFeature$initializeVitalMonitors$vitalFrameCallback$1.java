package com.datadog.android.rum.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, mo20735d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumFeature.kt */
final class RumFeature$initializeVitalMonitors$vitalFrameCallback$1 extends Lambda implements Function0<Boolean> {
    public static final RumFeature$initializeVitalMonitors$vitalFrameCallback$1 INSTANCE = new RumFeature$initializeVitalMonitors$vitalFrameCallback$1();

    RumFeature$initializeVitalMonitors$vitalFrameCallback$1() {
        super(0);
    }

    public final Boolean invoke() {
        return Boolean.valueOf(RumFeature.INSTANCE.isInitialized());
    }
}
