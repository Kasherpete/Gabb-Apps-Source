package p006io.opentracing.noop;

import p006io.opentracing.Scope;
import p006io.opentracing.Span;
import p006io.opentracing.noop.NoopScopeManager;

/* renamed from: io.opentracing.noop.NoopScopeManagerImpl */
/* compiled from: NoopScopeManager */
class NoopScopeManagerImpl implements NoopScopeManager {
    NoopScopeManagerImpl() {
    }

    public Scope activate(Span span, boolean z) {
        return NoopScopeManager.NoopScope.INSTANCE;
    }

    public Scope activate(Span span) {
        return NoopScopeManager.NoopScope.INSTANCE;
    }

    public Scope active() {
        return NoopScopeManager.NoopScope.INSTANCE;
    }

    public Span activeSpan() {
        return NoopSpan.INSTANCE;
    }

    /* renamed from: io.opentracing.noop.NoopScopeManagerImpl$NoopScopeImpl */
    /* compiled from: NoopScopeManager */
    static class NoopScopeImpl implements NoopScopeManager.NoopScope {
        public void close() {
        }

        NoopScopeImpl() {
        }

        public Span span() {
            return NoopSpan.INSTANCE;
        }
    }
}
