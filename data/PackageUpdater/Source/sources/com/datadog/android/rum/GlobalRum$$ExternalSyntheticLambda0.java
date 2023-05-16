package com.datadog.android.rum;

import java.util.concurrent.Callable;

public final /* synthetic */ class GlobalRum$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ RumMonitor f$0;

    public /* synthetic */ GlobalRum$$ExternalSyntheticLambda0(RumMonitor rumMonitor) {
        this.f$0 = rumMonitor;
    }

    public final Object call() {
        return GlobalRum.m145registerIfAbsent$lambda0(this.f$0);
    }
}
