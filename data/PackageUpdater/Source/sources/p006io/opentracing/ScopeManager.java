package p006io.opentracing;

/* renamed from: io.opentracing.ScopeManager */
public interface ScopeManager {
    Scope activate(Span span);

    @Deprecated
    Scope activate(Span span, boolean z);

    @Deprecated
    Scope active();

    Span activeSpan();
}
