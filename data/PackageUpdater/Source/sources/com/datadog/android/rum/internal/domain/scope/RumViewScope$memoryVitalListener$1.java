package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.rum.internal.vitals.VitalInfo;
import com.datadog.android.rum.internal.vitals.VitalListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, mo20735d2 = {"com/datadog/android/rum/internal/domain/scope/RumViewScope$memoryVitalListener$1", "Lcom/datadog/android/rum/internal/vitals/VitalListener;", "onVitalUpdate", "", "info", "Lcom/datadog/android/rum/internal/vitals/VitalInfo;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumViewScope.kt */
public final class RumViewScope$memoryVitalListener$1 implements VitalListener {
    final /* synthetic */ RumViewScope this$0;

    RumViewScope$memoryVitalListener$1(RumViewScope rumViewScope) {
        this.this$0 = rumViewScope;
    }

    public void onVitalUpdate(VitalInfo vitalInfo) {
        Intrinsics.checkNotNullParameter(vitalInfo, "info");
        this.this$0.lastMemoryInfo = vitalInfo;
    }
}
