package p006io.opentracing.noop;

import p006io.opentracing.Scope;
import p006io.opentracing.Span;
import p006io.opentracing.SpanContext;
import p006io.opentracing.Tracer;
import p006io.opentracing.noop.NoopScopeManager;
import p006io.opentracing.tag.Tag;

/* renamed from: io.opentracing.noop.NoopSpanBuilderImpl */
/* compiled from: NoopSpanBuilder */
final class NoopSpanBuilderImpl implements NoopSpanBuilder {
    public Tracer.SpanBuilder addReference(String str, SpanContext spanContext) {
        return this;
    }

    public Tracer.SpanBuilder asChildOf(Span span) {
        return this;
    }

    public Tracer.SpanBuilder asChildOf(SpanContext spanContext) {
        return this;
    }

    public Tracer.SpanBuilder ignoreActiveSpan() {
        return this;
    }

    public Tracer.SpanBuilder withStartTimestamp(long j) {
        return this;
    }

    public <T> Tracer.SpanBuilder withTag(Tag<T> tag, T t) {
        return this;
    }

    public Tracer.SpanBuilder withTag(String str, Number number) {
        return this;
    }

    public Tracer.SpanBuilder withTag(String str, String str2) {
        return this;
    }

    public Tracer.SpanBuilder withTag(String str, boolean z) {
        return this;
    }

    NoopSpanBuilderImpl() {
    }

    public Scope startActive(boolean z) {
        return NoopScopeManager.NoopScope.INSTANCE;
    }

    public Span start() {
        return startManual();
    }

    public Span startManual() {
        return NoopSpanImpl.INSTANCE;
    }

    public String toString() {
        Class<NoopSpanBuilder> cls = NoopSpanBuilder.class;
        return "NoopSpanBuilder";
    }
}
