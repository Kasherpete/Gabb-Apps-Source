package com.datadog.android.rum.internal.monitor;

import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.internal.debug.RumDebugListener;
import com.datadog.android.rum.internal.domain.event.ResourceTiming;
import com.datadog.android.rum.model.ViewEvent;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H&J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0018\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0017\u001a\u00020\u0003H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\b\u0010\u001a\u001a\u00020\u0003H&J\u0012\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH&J \u0010\u001e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020!H&J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0005H&¨\u0006#"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "Lcom/datadog/android/rum/RumMonitor;", "addCrash", "", "message", "", "source", "Lcom/datadog/android/rum/RumErrorSource;", "throwable", "", "addLongTask", "durationNs", "", "target", "addResourceTiming", "key", "timing", "Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "eventDropped", "viewId", "type", "Lcom/datadog/android/rum/internal/monitor/EventType;", "eventSent", "resetSession", "sendDebugTelemetryEvent", "sendErrorTelemetryEvent", "sendWebViewEvent", "setDebugListener", "listener", "Lcom/datadog/android/rum/internal/debug/RumDebugListener;", "updateViewLoadingTime", "", "loadingTimeInNs", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "waitForResourceTiming", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: AdvancedRumMonitor.kt */
public interface AdvancedRumMonitor extends RumMonitor {
    void addCrash(String str, RumErrorSource rumErrorSource, Throwable th);

    void addLongTask(long j, String str);

    void addResourceTiming(String str, ResourceTiming resourceTiming);

    void eventDropped(String str, EventType eventType);

    void eventSent(String str, EventType eventType);

    void resetSession();

    void sendDebugTelemetryEvent(String str);

    void sendErrorTelemetryEvent(String str, Throwable th);

    void sendWebViewEvent();

    void setDebugListener(RumDebugListener rumDebugListener);

    void updateViewLoadingTime(Object obj, long j, ViewEvent.LoadingType loadingType);

    void waitForResourceTiming(String str);
}
