package com.datadog.android.event;

import com.datadog.android.tracing.model.SpanEvent;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/event/SpanEventMapper;", "Lcom/datadog/android/event/EventMapper;", "Lcom/datadog/android/tracing/model/SpanEvent;", "map", "event", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SpanEventMapper.kt */
public interface SpanEventMapper extends EventMapper<SpanEvent> {
    SpanEvent map(SpanEvent spanEvent);
}
