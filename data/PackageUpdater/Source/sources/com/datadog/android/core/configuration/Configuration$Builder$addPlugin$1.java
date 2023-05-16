package com.datadog.android.core.configuration;

import com.datadog.android.core.configuration.Configuration;
import com.datadog.android.event.EventMapper;
import com.datadog.android.event.SpanEventMapper;
import com.datadog.android.plugin.DatadogPlugin;
import com.datadog.android.plugin.Feature;
import com.datadog.android.rum.internal.tracking.UserActionTrackingStrategy;
import com.datadog.android.rum.tracking.TrackingStrategy;
import com.datadog.android.rum.tracking.ViewTrackingStrategy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Configuration.kt */
final class Configuration$Builder$addPlugin$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Feature $feature;
    final /* synthetic */ DatadogPlugin $plugin;
    final /* synthetic */ Configuration.Builder this$0;

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Configuration.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Feature.values().length];
            iArr[Feature.RUM.ordinal()] = 1;
            iArr[Feature.TRACE.ordinal()] = 2;
            iArr[Feature.LOG.ordinal()] = 3;
            iArr[Feature.CRASH.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Configuration$Builder$addPlugin$1(Feature feature, Configuration.Builder builder, DatadogPlugin datadogPlugin) {
        super(0);
        this.$feature = feature;
        this.this$0 = builder;
        this.$plugin = datadogPlugin;
    }

    public final void invoke() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.$feature.ordinal()];
        if (i == 1) {
            Configuration.Builder builder = this.this$0;
            builder.rumConfig = Configuration.Feature.RUM.copy$default(builder.rumConfig, (String) null, CollectionsKt.plus(this.this$0.rumConfig.getPlugins(), this.$plugin), 0.0f, 0.0f, (UserActionTrackingStrategy) null, (ViewTrackingStrategy) null, (TrackingStrategy) null, (EventMapper) null, false, 509, (Object) null);
        } else if (i == 2) {
            Configuration.Builder builder2 = this.this$0;
            builder2.tracesConfig = Configuration.Feature.Tracing.copy$default(builder2.tracesConfig, (String) null, CollectionsKt.plus(this.this$0.tracesConfig.getPlugins(), this.$plugin), (SpanEventMapper) null, 5, (Object) null);
        } else if (i == 3) {
            Configuration.Builder builder3 = this.this$0;
            builder3.logsConfig = Configuration.Feature.Logs.copy$default(builder3.logsConfig, (String) null, CollectionsKt.plus(this.this$0.logsConfig.getPlugins(), this.$plugin), (EventMapper) null, 5, (Object) null);
        } else if (i == 4) {
            Configuration.Builder builder4 = this.this$0;
            builder4.crashReportConfig = Configuration.Feature.CrashReport.copy$default(builder4.crashReportConfig, (String) null, CollectionsKt.plus(this.this$0.crashReportConfig.getPlugins(), this.$plugin), 1, (Object) null);
        }
    }
}
