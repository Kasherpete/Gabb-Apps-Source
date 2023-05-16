package com.datadog.android.log.internal.domain.event;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.event.EventMapper;
import com.datadog.android.log.Logger;
import com.datadog.android.log.model.LogEvent;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/log/internal/domain/event/LogEventMapperWrapper;", "Lcom/datadog/android/event/EventMapper;", "Lcom/datadog/android/log/model/LogEvent;", "wrappedEventMapper", "(Lcom/datadog/android/event/EventMapper;)V", "getWrappedEventMapper$dd_sdk_android_release", "()Lcom/datadog/android/event/EventMapper;", "map", "event", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LogEventMapperWrapper.kt */
public final class LogEventMapperWrapper implements EventMapper<LogEvent> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EVENT_NULL_WARNING_MESSAGE = "LogEventMapper: the returned mapped object was null. This event will be dropped: %s";
    public static final String NOT_SAME_EVENT_INSTANCE_WARNING_MESSAGE = "LogEventMapper: the returned mapped object was not the same instance as the original object. This event will be dropped: %s";
    private final EventMapper<LogEvent> wrappedEventMapper;

    public LogEventMapperWrapper(EventMapper<LogEvent> eventMapper) {
        Intrinsics.checkNotNullParameter(eventMapper, "wrappedEventMapper");
        this.wrappedEventMapper = eventMapper;
    }

    public final EventMapper<LogEvent> getWrappedEventMapper$dd_sdk_android_release() {
        return this.wrappedEventMapper;
    }

    public LogEvent map(LogEvent logEvent) {
        LogEvent logEvent2 = logEvent;
        Intrinsics.checkNotNullParameter(logEvent2, "event");
        LogEvent map = this.wrappedEventMapper.map(logEvent2);
        if (map == null) {
            Logger devLogger = RuntimeUtilsKt.getDevLogger();
            String format = String.format(Locale.US, EVENT_NULL_WARNING_MESSAGE, Arrays.copyOf(new Object[]{logEvent2}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            Logger.w$default(devLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
            return null;
        } else if (map == logEvent2) {
            return map;
        } else {
            Logger devLogger2 = RuntimeUtilsKt.getDevLogger();
            String format2 = String.format(Locale.US, NOT_SAME_EVENT_INSTANCE_WARNING_MESSAGE, Arrays.copyOf(new Object[]{logEvent2}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
            Logger.w$default(devLogger2, format2, (Throwable) null, (Map) null, 6, (Object) null);
            return null;
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo20735d2 = {"Lcom/datadog/android/log/internal/domain/event/LogEventMapperWrapper$Companion;", "", "()V", "EVENT_NULL_WARNING_MESSAGE", "", "NOT_SAME_EVENT_INSTANCE_WARNING_MESSAGE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LogEventMapperWrapper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
