package p006io.opentracing.tag;

import p006io.opentracing.Span;

/* renamed from: io.opentracing.tag.AbstractTag */
public abstract class AbstractTag<T> implements Tag<T> {
    protected final String key;

    public abstract void set(Span span, T t);

    public AbstractTag(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }
}
