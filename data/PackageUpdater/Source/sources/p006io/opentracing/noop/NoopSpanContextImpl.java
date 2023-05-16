package p006io.opentracing.noop;

import java.util.Collections;
import java.util.Map;

/* renamed from: io.opentracing.noop.NoopSpanContextImpl */
/* compiled from: NoopSpanContext */
final class NoopSpanContextImpl implements NoopSpanContext {
    static final NoopSpanContextImpl INSTANCE = new NoopSpanContextImpl();

    public String toSpanId() {
        return "";
    }

    public String toTraceId() {
        return "";
    }

    NoopSpanContextImpl() {
    }

    public Iterable<Map.Entry<String, String>> baggageItems() {
        return Collections.emptyList();
    }

    public String toString() {
        Class<NoopSpanContext> cls = NoopSpanContext.class;
        return "NoopSpanContext";
    }
}
