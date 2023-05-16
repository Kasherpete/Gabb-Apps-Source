package com.datadog.android.rum.internal.monitor;

import com.datadog.android.rum.RumActionType;
import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumResourceKind;
import com.datadog.android.rum.internal.debug.RumDebugListener;
import com.datadog.android.rum.internal.domain.event.ResourceTiming;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.rum.model.ViewEvent;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J8\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J8\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0006H\u0016J.\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u00062\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0018\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020 H\u0016J\b\u0010\"\u001a\u00020\u0004H\u0016J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001a\u0010$\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010%\u001a\u00020\u0004H\u0016J\u0012\u0010&\u001a\u00020\u00042\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J6\u0010)\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J.\u0010,\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u00062\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J.\u0010-\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u00062\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016JG\u0010.\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u00010\u00132\u0006\u00102\u001a\u0002032\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016¢\u0006\u0002\u00104JW\u00105\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u00020\u00062\b\u00107\u001a\u0004\u0018\u00010\u00062\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016¢\u0006\u0002\u00108JM\u00105\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016¢\u0006\u0002\u00109J.\u0010:\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u00062\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001e\u0010:\u001a\u00020\u00042\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J&\u0010;\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u000e2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J \u0010<\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020>H\u0016J\u0010\u0010?\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0006H\u0016¨\u0006@"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/monitor/NoOpAdvancedRumMonitor;", "Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "()V", "addCrash", "", "message", "", "source", "Lcom/datadog/android/rum/RumErrorSource;", "throwable", "", "addError", "attributes", "", "", "addErrorWithStacktrace", "stacktrace", "addLongTask", "durationNs", "", "target", "addResourceTiming", "key", "timing", "Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "addTiming", "name", "addUserAction", "type", "Lcom/datadog/android/rum/RumActionType;", "eventDropped", "viewId", "Lcom/datadog/android/rum/internal/monitor/EventType;", "eventSent", "resetSession", "sendDebugTelemetryEvent", "sendErrorTelemetryEvent", "sendWebViewEvent", "setDebugListener", "listener", "Lcom/datadog/android/rum/internal/debug/RumDebugListener;", "startResource", "method", "url", "startUserAction", "startView", "stopResource", "statusCode", "", "size", "kind", "Lcom/datadog/android/rum/RumResourceKind;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Lcom/datadog/android/rum/RumResourceKind;Ljava/util/Map;)V", "stopResourceWithError", "stackTrace", "errorType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/Throwable;Ljava/util/Map;)V", "stopUserAction", "stopView", "updateViewLoadingTime", "loadingTimeInNs", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "waitForResourceTiming", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpAdvancedRumMonitor.kt */
public final class NoOpAdvancedRumMonitor implements AdvancedRumMonitor {
    public void addCrash(String str, RumErrorSource rumErrorSource, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(rumErrorSource, "source");
        Intrinsics.checkNotNullParameter(th, "throwable");
    }

    public void addError(String str, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(rumErrorSource, "source");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void addErrorWithStacktrace(String str, RumErrorSource rumErrorSource, String str2, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(rumErrorSource, "source");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void addLongTask(long j, String str) {
        Intrinsics.checkNotNullParameter(str, "target");
    }

    public void addResourceTiming(String str, ResourceTiming resourceTiming) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(resourceTiming, "timing");
    }

    public void addTiming(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
    }

    public void addUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void eventDropped(String str, EventType eventType) {
        Intrinsics.checkNotNullParameter(str, "viewId");
        Intrinsics.checkNotNullParameter(eventType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
    }

    public void eventSent(String str, EventType eventType) {
        Intrinsics.checkNotNullParameter(str, "viewId");
        Intrinsics.checkNotNullParameter(eventType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
    }

    public void resetSession() {
    }

    public void sendDebugTelemetryEvent(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
    }

    public void sendErrorTelemetryEvent(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
    }

    public void sendWebViewEvent() {
    }

    public void setDebugListener(RumDebugListener rumDebugListener) {
    }

    public void startResource(String str, String str2, String str3, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "method");
        Intrinsics.checkNotNullParameter(str3, "url");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void startUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void startView(Object obj, String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void stopResource(String str, Integer num, Long l, RumResourceKind rumResourceKind, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(rumResourceKind, "kind");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void stopResourceWithError(String str, Integer num, String str2, RumErrorSource rumErrorSource, String str3, String str4, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "message");
        Intrinsics.checkNotNullParameter(rumErrorSource, "source");
        Intrinsics.checkNotNullParameter(str3, "stackTrace");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void stopResourceWithError(String str, Integer num, String str2, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "message");
        Intrinsics.checkNotNullParameter(rumErrorSource, "source");
        Intrinsics.checkNotNullParameter(th, "throwable");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void stopUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void stopUserAction(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void stopView(Object obj, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(map, "attributes");
    }

    public void updateViewLoadingTime(Object obj, long j, ViewEvent.LoadingType loadingType) {
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(loadingType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
    }

    public void waitForResourceTiming(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
    }
}
