package com.datadog.android.tracing;

import okhttp3.Request;
import p006io.opentracing.propagation.TextMapInject;

public final /* synthetic */ class TracingInterceptor$$ExternalSyntheticLambda0 implements TextMapInject {
    public final /* synthetic */ Request.Builder f$0;

    public /* synthetic */ TracingInterceptor$$ExternalSyntheticLambda0(Request.Builder builder) {
        this.f$0 = builder;
    }

    public final void put(String str, String str2) {
        TracingInterceptor.m153updateRequest$lambda2(this.f$0, str, str2);
    }
}
