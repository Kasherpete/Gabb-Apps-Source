package com.datadog.android.rum.internal.domain.event;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.telemetry.model.TelemetryErrorEvent;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Source;", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumEventSourceProvider.kt */
final class RumEventSourceProvider$telemetryErrorEventSource$2 extends Lambda implements Function0<TelemetryErrorEvent.Source> {
    final /* synthetic */ String $source;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RumEventSourceProvider$telemetryErrorEventSource$2(String str) {
        super(0);
        this.$source = str;
    }

    public final TelemetryErrorEvent.Source invoke() {
        try {
            return TelemetryErrorEvent.Source.Companion.fromJson(this.$source);
        } catch (NoSuchElementException e) {
            Logger devLogger = RuntimeUtilsKt.getDevLogger();
            String format = String.format(Locale.US, RumEventSourceProvider.UNKNOWN_SOURCE_WARNING_MESSAGE_FORMAT, Arrays.copyOf(new Object[]{this.$source}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            Logger.e$default(devLogger, format, e, (Map) null, 4, (Object) null);
            return null;
        }
    }
}
