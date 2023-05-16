package com.datadog.trace.common.writer;

import com.datadog.opentracing.DDSpan;
import java.util.List;

public class LoggingWriter implements Writer {
    public void close() {
    }

    public void incrementTraceCount() {
    }

    public void start() {
    }

    public String toString() {
        return "LoggingWriter { }";
    }

    public void write(List<DDSpan> list) {
    }
}
