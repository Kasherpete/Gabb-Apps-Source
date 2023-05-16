package com.datadog.trace.context;

public interface ScopeListener {
    void afterScopeActivated();

    void afterScopeClosed();
}
