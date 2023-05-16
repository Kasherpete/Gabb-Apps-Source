package com.datadog.android.core.configuration;

import com.datadog.android.core.configuration.Configuration;
import com.datadog.android.event.EventMapper;
import com.datadog.android.log.model.LogEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Configuration.kt */
final class Configuration$Builder$setLogEventMapper$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ EventMapper<LogEvent> $eventMapper;
    final /* synthetic */ Configuration.Builder this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Configuration$Builder$setLogEventMapper$1(Configuration.Builder builder, EventMapper<LogEvent> eventMapper) {
        super(0);
        this.this$0 = builder;
        this.$eventMapper = eventMapper;
    }

    public final void invoke() {
        Configuration.Builder builder = this.this$0;
        builder.logsConfig = Configuration.Feature.Logs.copy$default(builder.logsConfig, (String) null, (List) null, this.$eventMapper, 3, (Object) null);
    }
}
