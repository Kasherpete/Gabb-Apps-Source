package p006io.opentracing.noop;

import p006io.opentracing.Span;

/* renamed from: io.opentracing.noop.NoopSpan */
public interface NoopSpan extends Span {
    public static final NoopSpan INSTANCE = new NoopSpanImpl();
}
