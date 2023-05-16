package com.datadog.android.rum.internal;

import com.datadog.android.rum.RumSessionListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003\"\u00020\u0001¢\u0006\u0002\u0010\u0004J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/CombinedRumSessionListener;", "Lcom/datadog/android/rum/RumSessionListener;", "listeners", "", "([Lcom/datadog/android/rum/RumSessionListener;)V", "getListeners", "()[Lcom/datadog/android/rum/RumSessionListener;", "[Lcom/datadog/android/rum/RumSessionListener;", "onSessionStarted", "", "sessionId", "", "isDiscarded", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CombinedRumSessionListener.kt */
public final class CombinedRumSessionListener implements RumSessionListener {
    private final RumSessionListener[] listeners;

    public CombinedRumSessionListener(RumSessionListener... rumSessionListenerArr) {
        Intrinsics.checkNotNullParameter(rumSessionListenerArr, "listeners");
        this.listeners = rumSessionListenerArr;
    }

    public final RumSessionListener[] getListeners() {
        return this.listeners;
    }

    public void onSessionStarted(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        RumSessionListener[] rumSessionListenerArr = this.listeners;
        int length = rumSessionListenerArr.length;
        int i = 0;
        while (i < length) {
            RumSessionListener rumSessionListener = rumSessionListenerArr[i];
            i++;
            rumSessionListener.onSessionStarted(str, z);
        }
    }
}
