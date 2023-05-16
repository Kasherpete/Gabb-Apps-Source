package p006io.opentracing;

import java.util.Map;

/* renamed from: io.opentracing.SpanContext */
public interface SpanContext {
    Iterable<Map.Entry<String, String>> baggageItems();

    String toSpanId();

    String toTraceId();
}
