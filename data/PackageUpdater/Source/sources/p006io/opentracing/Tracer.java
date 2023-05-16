package p006io.opentracing;

import java.io.Closeable;
import p006io.opentracing.propagation.Format;
import p006io.opentracing.tag.Tag;

/* renamed from: io.opentracing.Tracer */
public interface Tracer extends Closeable {

    /* renamed from: io.opentracing.Tracer$SpanBuilder */
    public interface SpanBuilder {
        SpanBuilder addReference(String str, SpanContext spanContext);

        SpanBuilder asChildOf(Span span);

        SpanBuilder asChildOf(SpanContext spanContext);

        SpanBuilder ignoreActiveSpan();

        Span start();

        @Deprecated
        Scope startActive(boolean z);

        @Deprecated
        Span startManual();

        SpanBuilder withStartTimestamp(long j);

        <T> SpanBuilder withTag(Tag<T> tag, T t);

        SpanBuilder withTag(String str, Number number);

        SpanBuilder withTag(String str, String str2);

        SpanBuilder withTag(String str, boolean z);
    }

    Scope activateSpan(Span span);

    Span activeSpan();

    SpanBuilder buildSpan(String str);

    void close();

    <C> SpanContext extract(Format<C> format, C c);

    <C> void inject(SpanContext spanContext, Format<C> format, C c);

    ScopeManager scopeManager();
}
