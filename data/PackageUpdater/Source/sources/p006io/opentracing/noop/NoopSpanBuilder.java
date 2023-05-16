package p006io.opentracing.noop;

import p006io.opentracing.Tracer;

/* renamed from: io.opentracing.noop.NoopSpanBuilder */
public interface NoopSpanBuilder extends Tracer.SpanBuilder {
    public static final NoopSpanBuilder INSTANCE = new NoopSpanBuilderImpl();
}
