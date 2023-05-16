package p006io.opentracing.tag;

import p006io.opentracing.Span;

/* renamed from: io.opentracing.tag.Tag */
public interface Tag<T> {
    String getKey();

    void set(Span span, T t);
}
