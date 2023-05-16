package p006io.opentracing.tag;

import p006io.opentracing.Span;

/* renamed from: io.opentracing.tag.StringTag */
public class StringTag extends AbstractTag<String> {
    public StringTag(String str) {
        super(str);
    }

    public void set(Span span, String str) {
        span.setTag(this.key, str);
    }

    @Deprecated
    public void set(Span span, StringTag stringTag) {
        span.setTag(this.key, stringTag.key);
    }
}
