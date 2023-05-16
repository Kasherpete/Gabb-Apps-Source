package p006io.opentracing.util;

import java.util.concurrent.atomic.AtomicInteger;
import p006io.opentracing.ScopeManager;
import p006io.opentracing.Span;
import p006io.opentracing.util.AutoFinishScope;

@Deprecated
/* renamed from: io.opentracing.util.AutoFinishScopeManager */
public class AutoFinishScopeManager implements ScopeManager {
    final ThreadLocal<AutoFinishScope> tlsScope = new ThreadLocal<>();

    public AutoFinishScope activate(Span span, boolean z) {
        return new AutoFinishScope(this, new AtomicInteger(1), span);
    }

    public AutoFinishScope activate(Span span) {
        return new AutoFinishScope(this, new AtomicInteger(1), span);
    }

    public AutoFinishScope active() {
        return this.tlsScope.get();
    }

    public Span activeSpan() {
        AutoFinishScope autoFinishScope = this.tlsScope.get();
        if (autoFinishScope == null) {
            return null;
        }
        return autoFinishScope.span();
    }

    public AutoFinishScope.Continuation captureScope() {
        AutoFinishScope autoFinishScope = this.tlsScope.get();
        if (autoFinishScope == null) {
            return null;
        }
        return autoFinishScope.capture();
    }
}
