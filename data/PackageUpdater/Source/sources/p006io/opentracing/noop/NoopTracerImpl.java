package p006io.opentracing.noop;

import p006io.opentracing.Scope;
import p006io.opentracing.ScopeManager;
import p006io.opentracing.Span;
import p006io.opentracing.SpanContext;
import p006io.opentracing.Tracer;
import p006io.opentracing.noop.NoopScopeManager;
import p006io.opentracing.propagation.Format;

/* renamed from: io.opentracing.noop.NoopTracerImpl */
/* compiled from: NoopTracer */
final class NoopTracerImpl implements NoopTracer {
    static final NoopTracer INSTANCE = new NoopTracerImpl();

    public void close() {
    }

    public <C> void inject(SpanContext spanContext, Format<C> format, C c) {
    }

    NoopTracerImpl() {
    }

    public ScopeManager scopeManager() {
        return NoopScopeManager.INSTANCE;
    }

    public Span activeSpan() {
        return NoopSpanImpl.INSTANCE;
    }

    public Scope activateSpan(Span span) {
        return NoopScopeManager.NoopScope.INSTANCE;
    }

    public Tracer.SpanBuilder buildSpan(String str) {
        return NoopSpanBuilderImpl.INSTANCE;
    }

    public <C> SpanContext extract(Format<C> format, C c) {
        return NoopSpanContextImpl.INSTANCE;
    }

    public String toString() {
        Class<NoopTracer> cls = NoopTracer.class;
        return "NoopTracer";
    }
}
