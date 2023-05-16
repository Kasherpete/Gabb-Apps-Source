package com.datadog.android.core.configuration;

import com.datadog.android.core.configuration.Configuration;
import com.datadog.android.event.EventMapper;
import com.datadog.android.rum.internal.tracking.UserActionTrackingStrategy;
import com.datadog.android.rum.tracking.TrackingStrategy;
import com.datadog.android.rum.tracking.ViewTrackingStrategy;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Configuration.kt */
final class Configuration$Builder$sampleRumSessions$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ float $samplingRate;
    final /* synthetic */ Configuration.Builder this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Configuration$Builder$sampleRumSessions$1(Configuration.Builder builder, float f) {
        super(0);
        this.this$0 = builder;
        this.$samplingRate = f;
    }

    public final void invoke() {
        Configuration.Builder builder = this.this$0;
        builder.rumConfig = Configuration.Feature.RUM.copy$default(builder.rumConfig, (String) null, (List) null, this.$samplingRate, 0.0f, (UserActionTrackingStrategy) null, (ViewTrackingStrategy) null, (TrackingStrategy) null, (EventMapper) null, false, 507, (Object) null);
    }
}
