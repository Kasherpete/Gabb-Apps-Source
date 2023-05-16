package com.datadog.android.core.internal.persistence.file.advanced;

import java.util.List;

public final /* synthetic */ class ScheduledWriter$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ScheduledWriter f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ ScheduledWriter$$ExternalSyntheticLambda1(ScheduledWriter scheduledWriter, List list) {
        this.f$0 = scheduledWriter;
        this.f$1 = list;
    }

    public final void run() {
        ScheduledWriter.m143write$lambda1(this.f$0, this.f$1);
    }
}
