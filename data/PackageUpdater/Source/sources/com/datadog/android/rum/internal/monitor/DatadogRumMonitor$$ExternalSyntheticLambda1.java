package com.datadog.android.rum.internal.monitor;

import com.datadog.android.rum.internal.domain.scope.RumRawEvent;

public final /* synthetic */ class DatadogRumMonitor$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DatadogRumMonitor f$0;
    public final /* synthetic */ RumRawEvent f$1;

    public /* synthetic */ DatadogRumMonitor$$ExternalSyntheticLambda1(DatadogRumMonitor datadogRumMonitor, RumRawEvent rumRawEvent) {
        this.f$0 = datadogRumMonitor;
        this.f$1 = rumRawEvent;
    }

    public final void run() {
        DatadogRumMonitor.m148handleEvent$lambda3(this.f$0, this.f$1);
    }
}
