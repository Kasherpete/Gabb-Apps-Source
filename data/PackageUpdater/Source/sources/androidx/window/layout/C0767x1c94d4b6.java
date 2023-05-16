package androidx.window.layout;

import androidx.window.layout.SidecarWindowBackend;

/* renamed from: androidx.window.layout.SidecarWindowBackend$WindowLayoutChangeCallbackWrapper$$ExternalSyntheticLambda0 */
public final /* synthetic */ class C0767x1c94d4b6 implements Runnable {
    public final /* synthetic */ SidecarWindowBackend.WindowLayoutChangeCallbackWrapper f$0;
    public final /* synthetic */ WindowLayoutInfo f$1;

    public /* synthetic */ C0767x1c94d4b6(SidecarWindowBackend.WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper, WindowLayoutInfo windowLayoutInfo) {
        this.f$0 = windowLayoutChangeCallbackWrapper;
        this.f$1 = windowLayoutInfo;
    }

    public final void run() {
        SidecarWindowBackend.WindowLayoutChangeCallbackWrapper.m137accept$lambda0(this.f$0, this.f$1);
    }
}
