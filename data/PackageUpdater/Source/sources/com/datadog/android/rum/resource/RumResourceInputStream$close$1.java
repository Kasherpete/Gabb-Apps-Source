package com.datadog.android.rum.resource;

import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.RumResourceKind;
import com.datadog.android.rum.internal.domain.event.ResourceTiming;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "", "Ljava/io/InputStream;", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumResourceInputStream.kt */
final class RumResourceInputStream$close$1 extends Lambda implements Function1<InputStream, Unit> {
    final /* synthetic */ RumResourceInputStream this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RumResourceInputStream$close$1(RumResourceInputStream rumResourceInputStream) {
        super(1);
        this.this$0 = rumResourceInputStream;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((InputStream) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "$this$callWithErrorTracking");
        inputStream.close();
        RumMonitor rumMonitor = GlobalRum.get();
        AdvancedRumMonitor advancedRumMonitor = rumMonitor instanceof AdvancedRumMonitor ? (AdvancedRumMonitor) rumMonitor : null;
        if (advancedRumMonitor != null) {
            String key$dd_sdk_android_release = this.this$0.getKey$dd_sdk_android_release();
            ResourceTiming resourceTiming = r4;
            ResourceTiming resourceTiming2 = new ResourceTiming(0, 0, 0, 0, 0, 0, 0, 0, this.this$0.firstByte - this.this$0.callStart, this.this$0.lastByte - this.this$0.firstByte, 255, (DefaultConstructorMarker) null);
            advancedRumMonitor.addResourceTiming(key$dd_sdk_android_release, resourceTiming);
        }
        rumMonitor.stopResource(this.this$0.getKey$dd_sdk_android_release(), (Integer) null, Long.valueOf(this.this$0.getSize$dd_sdk_android_release()), RumResourceKind.OTHER, MapsKt.emptyMap());
    }
}
