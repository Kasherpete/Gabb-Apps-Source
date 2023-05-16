package p006io.opentracing.util;

import p006io.opentracing.Scope;
import p006io.opentracing.ScopeManager;
import p006io.opentracing.Span;

/* renamed from: io.opentracing.util.ThreadLocalScopeManager */
public class ThreadLocalScopeManager implements ScopeManager {
    final ThreadLocal<ThreadLocalScope> tlsScope = new ThreadLocal<>();

    public Scope activate(Span span, boolean z) {
        return new ThreadLocalScope(this, span, z);
    }

    public Scope activate(Span span) {
        return new ThreadLocalScope(this, span);
    }

    public Scope active() {
        return this.tlsScope.get();
    }

    public Span activeSpan() {
        Scope scope = this.tlsScope.get();
        if (scope == null) {
            return null;
        }
        return scope.span();
    }
}
