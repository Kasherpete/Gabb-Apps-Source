package com.datadog.android.rum.internal.debug;

import java.util.List;

public final /* synthetic */ class UiRumDebugListener$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ UiRumDebugListener f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ UiRumDebugListener$$ExternalSyntheticLambda0(UiRumDebugListener uiRumDebugListener, List list) {
        this.f$0 = uiRumDebugListener;
        this.f$1 = list;
    }

    public final void run() {
        UiRumDebugListener.m146onReceiveRumActiveViews$lambda4$lambda3(this.f$0, this.f$1);
    }
}
