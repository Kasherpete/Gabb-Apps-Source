package p006io.opentracing.util;

import java.util.concurrent.atomic.AtomicInteger;
import p006io.opentracing.Scope;
import p006io.opentracing.Span;

@Deprecated
/* renamed from: io.opentracing.util.AutoFinishScope */
public class AutoFinishScope implements Scope {
    final AutoFinishScopeManager manager;
    final AtomicInteger refCount;
    private final AutoFinishScope toRestore;
    /* access modifiers changed from: private */
    public final Span wrapped;

    AutoFinishScope(AutoFinishScopeManager autoFinishScopeManager, AtomicInteger atomicInteger, Span span) {
        this.manager = autoFinishScopeManager;
        this.refCount = atomicInteger;
        this.wrapped = span;
        this.toRestore = autoFinishScopeManager.tlsScope.get();
        autoFinishScopeManager.tlsScope.set(this);
    }

    /* renamed from: io.opentracing.util.AutoFinishScope$Continuation */
    public class Continuation {
        public Continuation() {
            AutoFinishScope.this.refCount.incrementAndGet();
        }

        public AutoFinishScope activate() {
            return new AutoFinishScope(AutoFinishScope.this.manager, AutoFinishScope.this.refCount, AutoFinishScope.this.wrapped);
        }
    }

    public Continuation capture() {
        return new Continuation();
    }

    public void close() {
        if (this.manager.tlsScope.get() == this) {
            if (this.refCount.decrementAndGet() == 0) {
                this.wrapped.finish();
            }
            this.manager.tlsScope.set(this.toRestore);
        }
    }

    public Span span() {
        return this.wrapped;
    }
}
