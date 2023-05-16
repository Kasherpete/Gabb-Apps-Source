package com.datadog.android.tracing.internal.data;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.opentracing.DDSpan;
import com.datadog.trace.common.writer.Writer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\u0018\u0010\f\u001a\u00020\t2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000eH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, mo20735d2 = {"Lcom/datadog/android/tracing/internal/data/TraceWriter;", "Lcom/datadog/trace/common/writer/Writer;", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "Lcom/datadog/opentracing/DDSpan;", "(Lcom/datadog/android/core/internal/persistence/DataWriter;)V", "getWriter", "()Lcom/datadog/android/core/internal/persistence/DataWriter;", "close", "", "incrementTraceCount", "start", "write", "trace", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TraceWriter.kt */
public final class TraceWriter implements Writer {
    private final DataWriter<DDSpan> writer;

    public void close() {
    }

    public void incrementTraceCount() {
    }

    public void start() {
    }

    public TraceWriter(DataWriter<DDSpan> dataWriter) {
        Intrinsics.checkNotNullParameter(dataWriter, "writer");
        this.writer = dataWriter;
    }

    public final DataWriter<DDSpan> getWriter() {
        return this.writer;
    }

    public void write(List<DDSpan> list) {
        if (list != null) {
            getWriter().write(list);
        }
    }
}
