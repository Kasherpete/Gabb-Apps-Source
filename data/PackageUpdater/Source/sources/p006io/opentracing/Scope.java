package p006io.opentracing;

import java.io.Closeable;

/* renamed from: io.opentracing.Scope */
public interface Scope extends Closeable {
    void close();

    @Deprecated
    Span span();
}
