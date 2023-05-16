package com.datadog.trace.common.writer;

import com.datadog.opentracing.DDSpan;
import java.io.Closeable;
import java.util.List;

public interface Writer extends Closeable {
    void close();

    void incrementTraceCount();

    void start();

    void write(List<DDSpan> list);
}
