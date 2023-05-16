package com.datadog.android.rum;

import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016J8\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J.\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00062\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016J6\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016J.\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00062\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016J.\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00062\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016JG\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016¢\u0006\u0002\u0010\"JW\u0010#\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010\u00062\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016¢\u0006\u0002\u0010&JM\u0010#\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016¢\u0006\u0002\u0010'J.\u0010(\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00062\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016J\u001e\u0010(\u001a\u00020\u00042\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016J&\u0010)\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\r2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016¨\u0006*"}, mo20735d2 = {"Lcom/datadog/android/rum/NoOpRumMonitor;", "Lcom/datadog/android/rum/RumMonitor;", "()V", "addError", "", "message", "", "source", "Lcom/datadog/android/rum/RumErrorSource;", "throwable", "", "attributes", "", "", "addErrorWithStacktrace", "stacktrace", "addTiming", "name", "addUserAction", "type", "Lcom/datadog/android/rum/RumActionType;", "startResource", "key", "method", "url", "startUserAction", "startView", "stopResource", "statusCode", "", "size", "", "kind", "Lcom/datadog/android/rum/RumResourceKind;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Lcom/datadog/android/rum/RumResourceKind;Ljava/util/Map;)V", "stopResourceWithError", "stackTrace", "errorType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/Throwable;Ljava/util/Map;)V", "stopUserAction", "stopView", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpRumMonitor.kt */
public final class NoOpRumMonitor implements RumMonitor {
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

    public void addTiming(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
    }

    public void addUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "attributes");
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
}
