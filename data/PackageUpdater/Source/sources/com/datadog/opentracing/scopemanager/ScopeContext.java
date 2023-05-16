package com.datadog.opentracing.scopemanager;

import p006io.opentracing.ScopeManager;

@Deprecated
public interface ScopeContext extends ScopeManager {
    boolean inContext();
}
