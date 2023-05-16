package com.datadog.opentracing.scopemanager;

import com.datadog.trace.context.ScopeListener;
import p006io.opentracing.Span;

public class SimpleScope implements DDScope {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final int depth;
    private final boolean finishOnClose;
    private final ContextualScopeManager scopeManager;
    private final Span spanUnderScope;
    private final DDScope toRestore;

    public SimpleScope(ContextualScopeManager contextualScopeManager, Span span, boolean z) {
        int i;
        this.scopeManager = contextualScopeManager;
        this.spanUnderScope = span;
        this.finishOnClose = z;
        DDScope dDScope = ContextualScopeManager.tlsScope.get();
        this.toRestore = dDScope;
        ContextualScopeManager.tlsScope.set(this);
        if (dDScope == null) {
            i = 0;
        } else {
            i = dDScope.depth() + 1;
        }
        this.depth = i;
        for (ScopeListener afterScopeActivated : contextualScopeManager.scopeListeners) {
            afterScopeActivated.afterScopeActivated();
        }
    }

    public void close() {
        if (this.finishOnClose) {
            this.spanUnderScope.finish();
        }
        for (ScopeListener afterScopeClosed : this.scopeManager.scopeListeners) {
            afterScopeClosed.afterScopeClosed();
        }
        if (ContextualScopeManager.tlsScope.get() == this) {
            ContextualScopeManager.tlsScope.set(this.toRestore);
            if (this.toRestore != null) {
                for (ScopeListener afterScopeActivated : this.scopeManager.scopeListeners) {
                    afterScopeActivated.afterScopeActivated();
                }
            }
        }
    }

    public Span span() {
        return this.spanUnderScope;
    }

    public int depth() {
        return this.depth;
    }
}
