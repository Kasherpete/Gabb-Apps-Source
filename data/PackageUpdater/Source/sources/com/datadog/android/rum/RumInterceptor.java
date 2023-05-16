package com.datadog.android.rum;

import com.datadog.android.DatadogInterceptor;
import com.datadog.android.tracing.TracedRequestListener;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0003\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\t¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/rum/RumInterceptor;", "Lcom/datadog/android/DatadogInterceptor;", "firstPartyHosts", "", "", "rumResourceAttributesProvider", "Lcom/datadog/android/rum/RumResourceAttributesProvider;", "traceSamplingRate", "", "(Ljava/util/List;Lcom/datadog/android/rum/RumResourceAttributesProvider;F)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumInterceptor.kt */
public final class RumInterceptor extends DatadogInterceptor {
    public RumInterceptor() {
        this((List) null, (RumResourceAttributesProvider) null, 0.0f, 7, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RumInterceptor(List list, RumResourceAttributesProvider rumResourceAttributesProvider, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? new NoOpRumResourceAttributesProvider() : rumResourceAttributesProvider, (i & 4) != 0 ? 20.0f : f);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RumInterceptor(List<String> list, RumResourceAttributesProvider rumResourceAttributesProvider, float f) {
        super((List) list, (TracedRequestListener) null, rumResourceAttributesProvider, f, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "firstPartyHosts");
        Intrinsics.checkNotNullParameter(rumResourceAttributesProvider, "rumResourceAttributesProvider");
    }
}
