package com.datadog.android.rum.internal.ndk;

import com.datadog.android.core.internal.persistence.DataWriter;

public final /* synthetic */ class DatadogNdkCrashHandler$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DatadogNdkCrashHandler f$0;
    public final /* synthetic */ DataWriter f$1;
    public final /* synthetic */ DataWriter f$2;

    public /* synthetic */ DatadogNdkCrashHandler$$ExternalSyntheticLambda1(DatadogNdkCrashHandler datadogNdkCrashHandler, DataWriter dataWriter, DataWriter dataWriter2) {
        this.f$0 = datadogNdkCrashHandler;
        this.f$1 = dataWriter;
        this.f$2 = dataWriter2;
    }

    public final void run() {
        DatadogNdkCrashHandler.m151handleNdkCrash$lambda1(this.f$0, this.f$1, this.f$2);
    }
}
