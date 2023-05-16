package com.datadog.opentracing.scopemanager;

import p006io.opentracing.Scope;
import p006io.opentracing.Span;

interface DDScope extends Scope {
    int depth();

    Span span();
}
