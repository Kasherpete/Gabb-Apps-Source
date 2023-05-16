package p006io.opentracing.util;

import p006io.opentracing.Scope;
import p006io.opentracing.Span;

/* renamed from: io.opentracing.util.ThreadLocalScope */
public class ThreadLocalScope implements Scope {
    private final boolean finishOnClose;
    private final ThreadLocalScopeManager scopeManager;
    private final ThreadLocalScope toRestore;
    private final Span wrapped;

    ThreadLocalScope(ThreadLocalScopeManager threadLocalScopeManager, Span span) {
        this(threadLocalScopeManager, span, false);
    }

    ThreadLocalScope(ThreadLocalScopeManager threadLocalScopeManager, Span span, boolean z) {
        this.scopeManager = threadLocalScopeManager;
        this.wrapped = span;
        this.finishOnClose = z;
        this.toRestore = threadLocalScopeManager.tlsScope.get();
        threadLocalScopeManager.tlsScope.set(this);
    }

    public void close() {
        if (this.scopeManager.tlsScope.get() == this) {
            if (this.finishOnClose) {
                this.wrapped.finish();
            }
            this.scopeManager.tlsScope.set(this.toRestore);
        }
    }

    public Span span() {
        return this.wrapped;
    }
}
