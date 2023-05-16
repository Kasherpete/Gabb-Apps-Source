package com.datadog.android.rum.internal.monitor;

import android.os.Handler;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.net.FirstPartyHostDetector;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.RumActionType;
import com.datadog.android.rum.RumAttributes;
import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.RumResourceKind;
import com.datadog.android.rum.RumSessionListener;
import com.datadog.android.rum.internal.CombinedRumSessionListener;
import com.datadog.android.rum.internal.RumErrorSourceType;
import com.datadog.android.rum.internal.debug.RumDebugListener;
import com.datadog.android.rum.internal.domain.Time;
import com.datadog.android.rum.internal.domain.TimeKt;
import com.datadog.android.rum.internal.domain.event.ResourceTiming;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.rum.internal.domain.scope.RumApplicationScope;
import com.datadog.android.rum.internal.domain.scope.RumRawEvent;
import com.datadog.android.rum.internal.domain.scope.RumScope;
import com.datadog.android.rum.internal.domain.scope.RumSessionScope;
import com.datadog.android.rum.internal.domain.scope.RumViewScope;
import com.datadog.android.rum.internal.vitals.VitalMonitor;
import com.datadog.android.rum.model.ViewEvent;
import com.datadog.android.telemetry.internal.TelemetryEventHandler;
import com.datadog.android.telemetry.internal.TelemetryType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 ~2\u00020\u00012\u00020\u0002:\u0001~Bw\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b¢\u0006\u0002\u0010\u001cJ \u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0016J8\u0010<\u001a\u0002062\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;2\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016J8\u0010?\u001a\u0002062\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u0002092\b\u0010@\u001a\u0004\u0018\u00010\u00042\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016J\u0018\u0010A\u001a\u0002062\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0004H\u0016J\u0018\u0010E\u001a\u0002062\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020HH\u0016J\u0010\u0010I\u001a\u0002062\u0006\u0010J\u001a\u00020\u0004H\u0016J.\u0010K\u001a\u0002062\u0006\u0010L\u001a\u00020M2\u0006\u0010J\u001a\u00020\u00042\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016J\r\u0010N\u001a\u000206H\u0000¢\u0006\u0002\bOJ\u0018\u0010P\u001a\u0002062\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010L\u001a\u00020RH\u0016J\u0018\u0010S\u001a\u0002062\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010L\u001a\u00020RH\u0016J\u001e\u0010T\u001a\u00020U2\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0002J \u0010V\u001a\u0004\u0018\u00010\u00042\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0002J\u001e\u0010W\u001a\u00020X2\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0002J\u0015\u0010Y\u001a\u0002062\u0006\u0010Z\u001a\u00020[H\u0000¢\u0006\u0002\b\\J\r\u0010]\u001a\u000206H\u0000¢\u0006\u0002\b^J\b\u0010_\u001a\u000206H\u0016J\u0010\u0010`\u001a\u0002062\u0006\u00107\u001a\u00020\u0004H\u0016J\u001a\u0010a\u001a\u0002062\u0006\u00107\u001a\u00020\u00042\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\b\u0010b\u001a\u000206H\u0016J\u0012\u0010c\u001a\u0002062\b\u0010d\u001a\u0004\u0018\u00010 H\u0016J6\u0010e\u001a\u0002062\u0006\u0010F\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u00042\u0006\u0010g\u001a\u00020\u00042\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016J.\u0010h\u001a\u0002062\u0006\u0010L\u001a\u00020M2\u0006\u0010J\u001a\u00020\u00042\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016J.\u0010i\u001a\u0002062\u0006\u0010F\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u00042\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016J\r\u0010j\u001a\u000206H\u0000¢\u0006\u0002\bkJG\u0010l\u001a\u0002062\u0006\u0010F\u001a\u00020\u00042\b\u0010m\u001a\u0004\u0018\u00010n2\b\u0010o\u001a\u0004\u0018\u00010C2\u0006\u0010p\u001a\u00020q2\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016¢\u0006\u0002\u0010rJW\u0010s\u001a\u0002062\u0006\u0010F\u001a\u00020\u00042\b\u0010m\u001a\u0004\u0018\u00010n2\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u0002092\u0006\u0010t\u001a\u00020\u00042\b\u0010u\u001a\u0004\u0018\u00010\u00042\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016¢\u0006\u0002\u0010vJM\u0010s\u001a\u0002062\u0006\u0010F\u001a\u00020\u00042\b\u0010m\u001a\u0004\u0018\u00010n2\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016¢\u0006\u0002\u0010wJ.\u0010x\u001a\u0002062\u0006\u0010L\u001a\u00020M2\u0006\u0010J\u001a\u00020\u00042\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016J\u001e\u0010x\u001a\u0002062\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016J&\u0010y\u001a\u0002062\u0006\u0010F\u001a\u00020\u000b2\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0>H\u0016J \u0010z\u001a\u0002062\u0006\u0010F\u001a\u00020\u000b2\u0006\u0010{\u001a\u00020C2\u0006\u0010L\u001a\u00020|H\u0016J\u0010\u0010}\u001a\u0002062\u0006\u0010F\u001a\u00020\u0004H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(X\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000¨\u0006"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/monitor/DatadogRumMonitor;", "Lcom/datadog/android/rum/RumMonitor;", "Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "applicationId", "", "samplingRate", "", "backgroundTrackingEnabled", "", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "", "handler", "Landroid/os/Handler;", "telemetryEventHandler", "Lcom/datadog/android/telemetry/internal/TelemetryEventHandler;", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "cpuVitalMonitor", "Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "memoryVitalMonitor", "frameRateVitalMonitor", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "sessionListener", "Lcom/datadog/android/rum/RumSessionListener;", "executorService", "Ljava/util/concurrent/ExecutorService;", "(Ljava/lang/String;FZLcom/datadog/android/core/internal/persistence/DataWriter;Landroid/os/Handler;Lcom/datadog/android/telemetry/internal/TelemetryEventHandler;Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/core/internal/time/TimeProvider;Lcom/datadog/android/rum/RumSessionListener;Ljava/util/concurrent/ExecutorService;)V", "getBackgroundTrackingEnabled$dd_sdk_android_release", "()Z", "debugListener", "Lcom/datadog/android/rum/internal/debug/RumDebugListener;", "getDebugListener$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/debug/RumDebugListener;", "setDebugListener$dd_sdk_android_release", "(Lcom/datadog/android/rum/internal/debug/RumDebugListener;)V", "getHandler$dd_sdk_android_release", "()Landroid/os/Handler;", "keepAliveRunnable", "Ljava/lang/Runnable;", "getKeepAliveRunnable$dd_sdk_android_release", "()Ljava/lang/Runnable;", "rootScope", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "getRootScope$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "setRootScope$dd_sdk_android_release", "(Lcom/datadog/android/rum/internal/domain/scope/RumScope;)V", "getSamplingRate$dd_sdk_android_release", "()F", "getTelemetryEventHandler$dd_sdk_android_release", "()Lcom/datadog/android/telemetry/internal/TelemetryEventHandler;", "addCrash", "", "message", "source", "Lcom/datadog/android/rum/RumErrorSource;", "throwable", "", "addError", "attributes", "", "addErrorWithStacktrace", "stacktrace", "addLongTask", "durationNs", "", "target", "addResourceTiming", "key", "timing", "Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "addTiming", "name", "addUserAction", "type", "Lcom/datadog/android/rum/RumActionType;", "drainExecutorService", "drainExecutorService$dd_sdk_android_release", "eventDropped", "viewId", "Lcom/datadog/android/rum/internal/monitor/EventType;", "eventSent", "getErrorSourceType", "Lcom/datadog/android/rum/internal/RumErrorSourceType;", "getErrorType", "getEventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "handleEvent", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "handleEvent$dd_sdk_android_release", "notifyDebugListenerWithState", "notifyDebugListenerWithState$dd_sdk_android_release", "resetSession", "sendDebugTelemetryEvent", "sendErrorTelemetryEvent", "sendWebViewEvent", "setDebugListener", "listener", "startResource", "method", "url", "startUserAction", "startView", "stopKeepAliveCallback", "stopKeepAliveCallback$dd_sdk_android_release", "stopResource", "statusCode", "", "size", "kind", "Lcom/datadog/android/rum/RumResourceKind;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Lcom/datadog/android/rum/RumResourceKind;Ljava/util/Map;)V", "stopResourceWithError", "stackTrace", "errorType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/Throwable;Ljava/util/Map;)V", "stopUserAction", "stopView", "updateViewLoadingTime", "loadingTimeInNs", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "waitForResourceTiming", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogRumMonitor.kt */
public final class DatadogRumMonitor implements RumMonitor, AdvancedRumMonitor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final long KEEP_ALIVE_MS = TimeUnit.MINUTES.toMillis(5);
    private final boolean backgroundTrackingEnabled;
    private RumDebugListener debugListener;
    private final ExecutorService executorService;
    private final Handler handler;
    private final Runnable keepAliveRunnable;
    private RumScope rootScope;
    private final float samplingRate;
    private final TelemetryEventHandler telemetryEventHandler;
    private final DataWriter<Object> writer;

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DatadogRumMonitor.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EventType.values().length];
            iArr[EventType.ACTION.ordinal()] = 1;
            iArr[EventType.RESOURCE.ordinal()] = 2;
            iArr[EventType.ERROR.ordinal()] = 3;
            iArr[EventType.LONG_TASK.ordinal()] = 4;
            iArr[EventType.FROZEN_FRAME.ordinal()] = 5;
            iArr[EventType.VIEW.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DatadogRumMonitor(String str, float f, boolean z, DataWriter<Object> dataWriter, Handler handler2, TelemetryEventHandler telemetryEventHandler2, FirstPartyHostDetector firstPartyHostDetector, VitalMonitor vitalMonitor, VitalMonitor vitalMonitor2, VitalMonitor vitalMonitor3, TimeProvider timeProvider, RumSessionListener rumSessionListener, ExecutorService executorService2) {
        RumSessionListener rumSessionListener2;
        DataWriter<Object> dataWriter2 = dataWriter;
        Handler handler3 = handler2;
        TelemetryEventHandler telemetryEventHandler3 = telemetryEventHandler2;
        ExecutorService executorService3 = executorService2;
        Intrinsics.checkNotNullParameter(str, "applicationId");
        Intrinsics.checkNotNullParameter(dataWriter2, "writer");
        Intrinsics.checkNotNullParameter(handler3, "handler");
        Intrinsics.checkNotNullParameter(telemetryEventHandler3, "telemetryEventHandler");
        Intrinsics.checkNotNullParameter(firstPartyHostDetector, "firstPartyHostDetector");
        Intrinsics.checkNotNullParameter(vitalMonitor, "cpuVitalMonitor");
        Intrinsics.checkNotNullParameter(vitalMonitor2, "memoryVitalMonitor");
        Intrinsics.checkNotNullParameter(vitalMonitor3, "frameRateVitalMonitor");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        Intrinsics.checkNotNullParameter(executorService3, "executorService");
        this.samplingRate = f;
        this.backgroundTrackingEnabled = z;
        this.writer = dataWriter2;
        this.handler = handler3;
        this.telemetryEventHandler = telemetryEventHandler3;
        this.executorService = executorService3;
        if (rumSessionListener != null) {
            rumSessionListener2 = new CombinedRumSessionListener(rumSessionListener, telemetryEventHandler3);
        } else {
            rumSessionListener2 = telemetryEventHandler3;
        }
        this.rootScope = new RumApplicationScope(str, f, z, firstPartyHostDetector, vitalMonitor, vitalMonitor2, vitalMonitor3, timeProvider, rumSessionListener2);
        DatadogRumMonitor$$ExternalSyntheticLambda0 datadogRumMonitor$$ExternalSyntheticLambda0 = new DatadogRumMonitor$$ExternalSyntheticLambda0(this);
        this.keepAliveRunnable = datadogRumMonitor$$ExternalSyntheticLambda0;
        handler3.postDelayed(datadogRumMonitor$$ExternalSyntheticLambda0, KEEP_ALIVE_MS);
    }

    public final float getSamplingRate$dd_sdk_android_release() {
        return this.samplingRate;
    }

    public final boolean getBackgroundTrackingEnabled$dd_sdk_android_release() {
        return this.backgroundTrackingEnabled;
    }

    public final Handler getHandler$dd_sdk_android_release() {
        return this.handler;
    }

    public final TelemetryEventHandler getTelemetryEventHandler$dd_sdk_android_release() {
        return this.telemetryEventHandler;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ DatadogRumMonitor(java.lang.String r17, float r18, boolean r19, com.datadog.android.core.internal.persistence.DataWriter r20, android.os.Handler r21, com.datadog.android.telemetry.internal.TelemetryEventHandler r22, com.datadog.android.core.internal.net.FirstPartyHostDetector r23, com.datadog.android.rum.internal.vitals.VitalMonitor r24, com.datadog.android.rum.internal.vitals.VitalMonitor r25, com.datadog.android.rum.internal.vitals.VitalMonitor r26, com.datadog.android.core.internal.time.TimeProvider r27, com.datadog.android.rum.RumSessionListener r28, java.util.concurrent.ExecutorService r29, int r30, kotlin.jvm.internal.DefaultConstructorMarker r31) {
        /*
            r16 = this;
            r0 = r30
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x0011
            java.util.concurrent.ExecutorService r0 = java.util.concurrent.Executors.newSingleThreadExecutor()
            java.lang.String r1 = "newSingleThreadExecutor()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r15 = r0
            goto L_0x0013
        L_0x0011:
            r15 = r29
        L_0x0013:
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            r10 = r24
            r11 = r25
            r12 = r26
            r13 = r27
            r14 = r28
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.monitor.DatadogRumMonitor.<init>(java.lang.String, float, boolean, com.datadog.android.core.internal.persistence.DataWriter, android.os.Handler, com.datadog.android.telemetry.internal.TelemetryEventHandler, com.datadog.android.core.internal.net.FirstPartyHostDetector, com.datadog.android.rum.internal.vitals.VitalMonitor, com.datadog.android.rum.internal.vitals.VitalMonitor, com.datadog.android.rum.internal.vitals.VitalMonitor, com.datadog.android.core.internal.time.TimeProvider, com.datadog.android.rum.RumSessionListener, java.util.concurrent.ExecutorService, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final RumScope getRootScope$dd_sdk_android_release() {
        return this.rootScope;
    }

    public final void setRootScope$dd_sdk_android_release(RumScope rumScope) {
        Intrinsics.checkNotNullParameter(rumScope, "<set-?>");
        this.rootScope = rumScope;
    }

    public final Runnable getKeepAliveRunnable$dd_sdk_android_release() {
        return this.keepAliveRunnable;
    }

    /* access modifiers changed from: private */
    /* renamed from: keepAliveRunnable$lambda-0  reason: not valid java name */
    public static final void m149keepAliveRunnable$lambda0(DatadogRumMonitor datadogRumMonitor) {
        Intrinsics.checkNotNullParameter(datadogRumMonitor, "this$0");
        datadogRumMonitor.handleEvent$dd_sdk_android_release(new RumRawEvent.KeepAlive((Time) null, 1, (DefaultConstructorMarker) null));
    }

    public final RumDebugListener getDebugListener$dd_sdk_android_release() {
        return this.debugListener;
    }

    public final void setDebugListener$dd_sdk_android_release(RumDebugListener rumDebugListener) {
        this.debugListener = rumDebugListener;
    }

    public void startView(Object obj, String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.StartView(obj, str, map, getEventTime(map)));
    }

    public void stopView(Object obj, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(map, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.StopView(obj, map, getEventTime(map)));
    }

    public void addUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.StartAction(rumActionType, str, false, map, getEventTime(map)));
    }

    public void startUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.StartAction(rumActionType, str, true, map, getEventTime(map)));
    }

    public void stopUserAction(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.StopAction((RumActionType) null, (String) null, map, (Time) null, 8, (DefaultConstructorMarker) null));
    }

    public void stopUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.StopAction(rumActionType, str, map, getEventTime(map)));
    }

    public void startResource(String str, String str2, String str3, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "method");
        Intrinsics.checkNotNullParameter(str3, "url");
        Intrinsics.checkNotNullParameter(map, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.StartResource(str, str3, str2, map, getEventTime(map)));
    }

    public void stopResource(String str, Integer num, Long l, RumResourceKind rumResourceKind, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(rumResourceKind, "kind");
        Intrinsics.checkNotNullParameter(map, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.StopResource(str, num == null ? null : Long.valueOf((long) num.intValue()), l, rumResourceKind, map, getEventTime(map)));
    }

    public void stopResourceWithError(String str, Integer num, String str2, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map) {
        Long l;
        String str3 = str;
        Intrinsics.checkNotNullParameter(str, "key");
        String str4 = str2;
        Intrinsics.checkNotNullParameter(str2, "message");
        RumErrorSource rumErrorSource2 = rumErrorSource;
        Intrinsics.checkNotNullParameter(rumErrorSource, "source");
        Intrinsics.checkNotNullParameter(th, "throwable");
        Intrinsics.checkNotNullParameter(map, "attributes");
        if (num == null) {
            l = null;
        } else {
            l = Long.valueOf((long) num.intValue());
        }
        RumRawEvent stopResourceWithError = new RumRawEvent.StopResourceWithError(str, l, str2, rumErrorSource, th, map, (Time) null, 64, (DefaultConstructorMarker) null);
        handleEvent$dd_sdk_android_release(stopResourceWithError);
    }

    public void stopResourceWithError(String str, Integer num, String str2, RumErrorSource rumErrorSource, String str3, String str4, Map<String, ? extends Object> map) {
        Long l;
        String str5 = str;
        Intrinsics.checkNotNullParameter(str, "key");
        String str6 = str2;
        Intrinsics.checkNotNullParameter(str2, "message");
        Intrinsics.checkNotNullParameter(rumErrorSource, "source");
        Intrinsics.checkNotNullParameter(str3, "stackTrace");
        Intrinsics.checkNotNullParameter(map, "attributes");
        if (num == null) {
            l = null;
        } else {
            l = Long.valueOf((long) num.intValue());
        }
        RumRawEvent stopResourceWithStackTrace = new RumRawEvent.StopResourceWithStackTrace(str, l, str2, rumErrorSource, str3, str4, map, (Time) null, 128, (DefaultConstructorMarker) null);
        handleEvent$dd_sdk_android_release(stopResourceWithStackTrace);
    }

    public void addError(String str, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map) {
        Map<String, ? extends Object> map2 = map;
        Intrinsics.checkNotNullParameter(str, "message");
        RumErrorSource rumErrorSource2 = rumErrorSource;
        Intrinsics.checkNotNullParameter(rumErrorSource2, "source");
        Intrinsics.checkNotNullParameter(map2, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.AddError(str, rumErrorSource2, th, (String) null, false, map2, getEventTime(map2), getErrorType(map2), (RumErrorSourceType) null, 256, (DefaultConstructorMarker) null));
    }

    public void addErrorWithStacktrace(String str, RumErrorSource rumErrorSource, String str2, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(rumErrorSource, "source");
        Intrinsics.checkNotNullParameter(map, "attributes");
        handleEvent$dd_sdk_android_release(new RumRawEvent.AddError(str, rumErrorSource, (Throwable) null, str2, false, map, getEventTime(map), getErrorType(map), getErrorSourceType(map)));
    }

    public void sendWebViewEvent() {
        handleEvent$dd_sdk_android_release(new RumRawEvent.WebViewEvent((Time) null, 1, (DefaultConstructorMarker) null));
    }

    public void resetSession() {
        handleEvent$dd_sdk_android_release(new RumRawEvent.ResetSession((Time) null, 1, (DefaultConstructorMarker) null));
    }

    public void waitForResourceTiming(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        handleEvent$dd_sdk_android_release(new RumRawEvent.WaitForResourceTiming(str, (Time) null, 2, (DefaultConstructorMarker) null));
    }

    public void addResourceTiming(String str, ResourceTiming resourceTiming) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(resourceTiming, "timing");
        handleEvent$dd_sdk_android_release(new RumRawEvent.AddResourceTiming(str, resourceTiming, (Time) null, 4, (DefaultConstructorMarker) null));
    }

    public void addCrash(String str, RumErrorSource rumErrorSource, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(rumErrorSource, "source");
        Throwable th2 = th;
        Intrinsics.checkNotNullParameter(th2, "throwable");
        handleEvent$dd_sdk_android_release(new RumRawEvent.AddError(str, rumErrorSource, th2, (String) null, true, MapsKt.emptyMap(), (Time) null, (String) null, (RumErrorSourceType) null, 448, (DefaultConstructorMarker) null));
    }

    public void updateViewLoadingTime(Object obj, long j, ViewEvent.LoadingType loadingType) {
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(loadingType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        handleEvent$dd_sdk_android_release(new RumRawEvent.UpdateViewLoadingTime(obj, j, loadingType, (Time) null, 8, (DefaultConstructorMarker) null));
    }

    public void addTiming(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        handleEvent$dd_sdk_android_release(new RumRawEvent.AddCustomTiming(str, (Time) null, 2, (DefaultConstructorMarker) null));
    }

    public void addLongTask(long j, String str) {
        Intrinsics.checkNotNullParameter(str, "target");
        handleEvent$dd_sdk_android_release(new RumRawEvent.AddLongTask(j, str, (Time) null, 4, (DefaultConstructorMarker) null));
    }

    public void eventSent(String str, EventType eventType) {
        Intrinsics.checkNotNullParameter(str, "viewId");
        Intrinsics.checkNotNullParameter(eventType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        int i = WhenMappings.$EnumSwitchMapping$0[eventType.ordinal()];
        if (i == 1) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.ActionSent(str, (Time) null, 2, (DefaultConstructorMarker) null));
        } else if (i == 2) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.ResourceSent(str, (Time) null, 2, (DefaultConstructorMarker) null));
        } else if (i == 3) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.ErrorSent(str, (Time) null, 2, (DefaultConstructorMarker) null));
        } else if (i == 4) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.LongTaskSent(str, false, (Time) null, 4, (DefaultConstructorMarker) null));
        } else if (i == 5) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.LongTaskSent(str, true, (Time) null, 4, (DefaultConstructorMarker) null));
        }
    }

    public void eventDropped(String str, EventType eventType) {
        Intrinsics.checkNotNullParameter(str, "viewId");
        Intrinsics.checkNotNullParameter(eventType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        int i = WhenMappings.$EnumSwitchMapping$0[eventType.ordinal()];
        if (i == 1) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.ActionDropped(str, (Time) null, 2, (DefaultConstructorMarker) null));
        } else if (i == 2) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.ResourceDropped(str, (Time) null, 2, (DefaultConstructorMarker) null));
        } else if (i == 3) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.ErrorDropped(str, (Time) null, 2, (DefaultConstructorMarker) null));
        } else if (i == 4) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.LongTaskDropped(str, false, (Time) null, 4, (DefaultConstructorMarker) null));
        } else if (i == 5) {
            handleEvent$dd_sdk_android_release(new RumRawEvent.LongTaskDropped(str, true, (Time) null, 4, (DefaultConstructorMarker) null));
        }
    }

    public void setDebugListener(RumDebugListener rumDebugListener) {
        this.debugListener = rumDebugListener;
    }

    public void sendDebugTelemetryEvent(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        handleEvent$dd_sdk_android_release(new RumRawEvent.SendTelemetry(TelemetryType.DEBUG, str, (Throwable) null, (Time) null, 8, (DefaultConstructorMarker) null));
    }

    public void sendErrorTelemetryEvent(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        handleEvent$dd_sdk_android_release(new RumRawEvent.SendTelemetry(TelemetryType.ERROR, str, th, (Time) null, 8, (DefaultConstructorMarker) null));
    }

    public final void drainExecutorService$dd_sdk_android_release() throws UnsupportedOperationException, InterruptedException {
        BlockingQueue<Runnable> queue;
        ArrayList<Runnable> arrayList = new ArrayList<>();
        ExecutorService executorService2 = this.executorService;
        ThreadPoolExecutor threadPoolExecutor = executorService2 instanceof ThreadPoolExecutor ? (ThreadPoolExecutor) executorService2 : null;
        if (!(threadPoolExecutor == null || (queue = threadPoolExecutor.getQueue()) == null)) {
            queue.drainTo(arrayList);
        }
        this.executorService.shutdown();
        this.executorService.awaitTermination(10, TimeUnit.SECONDS);
        for (Runnable run : arrayList) {
            run.run();
        }
    }

    public final void handleEvent$dd_sdk_android_release(RumRawEvent rumRawEvent) {
        Intrinsics.checkNotNullParameter(rumRawEvent, "event");
        if ((rumRawEvent instanceof RumRawEvent.AddError) && ((RumRawEvent.AddError) rumRawEvent).isFatal()) {
            this.rootScope.handleEvent(rumRawEvent, this.writer);
        } else if (rumRawEvent instanceof RumRawEvent.SendTelemetry) {
            this.telemetryEventHandler.handleEvent((RumRawEvent.SendTelemetry) rumRawEvent, this.writer);
        } else {
            this.handler.removeCallbacks(this.keepAliveRunnable);
            if (!this.executorService.isShutdown()) {
                try {
                    this.executorService.submit(new DatadogRumMonitor$$ExternalSyntheticLambda1(this, rumRawEvent));
                } catch (RejectedExecutionException e) {
                    Logger.e$default(RuntimeUtilsKt.getDevLogger(), "Unable to handle a RUM event, the ", e, (Map) null, 4, (Object) null);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: handleEvent$lambda-3  reason: not valid java name */
    public static final void m148handleEvent$lambda3(DatadogRumMonitor datadogRumMonitor, RumRawEvent rumRawEvent) {
        Intrinsics.checkNotNullParameter(datadogRumMonitor, "this$0");
        Intrinsics.checkNotNullParameter(rumRawEvent, "$event");
        synchronized (datadogRumMonitor.rootScope) {
            datadogRumMonitor.getRootScope$dd_sdk_android_release().handleEvent(rumRawEvent, datadogRumMonitor.writer);
            datadogRumMonitor.notifyDebugListenerWithState$dd_sdk_android_release();
            Unit unit = Unit.INSTANCE;
        }
        datadogRumMonitor.handler.postDelayed(datadogRumMonitor.keepAliveRunnable, KEEP_ALIVE_MS);
    }

    public final void stopKeepAliveCallback$dd_sdk_android_release() {
        this.handler.removeCallbacks(this.keepAliveRunnable);
    }

    public final void notifyDebugListenerWithState$dd_sdk_android_release() {
        RumScope rumScope;
        RumDebugListener rumDebugListener = this.debugListener;
        if (rumDebugListener != null) {
            RumScope rootScope$dd_sdk_android_release = getRootScope$dd_sdk_android_release();
            RumSessionScope rumSessionScope = null;
            RumApplicationScope rumApplicationScope = rootScope$dd_sdk_android_release instanceof RumApplicationScope ? (RumApplicationScope) rootScope$dd_sdk_android_release : null;
            if (rumApplicationScope == null) {
                rumScope = null;
            } else {
                rumScope = rumApplicationScope.getChildScope$dd_sdk_android_release();
            }
            if (rumScope instanceof RumSessionScope) {
                rumSessionScope = (RumSessionScope) rumScope;
            }
            if (rumSessionScope != null) {
                Collection arrayList = new ArrayList();
                for (Object next : rumSessionScope.getChildrenScopes$dd_sdk_android_release()) {
                    if (next instanceof RumViewScope) {
                        arrayList.add(next);
                    }
                }
                Collection arrayList2 = new ArrayList();
                for (Object next2 : (List) arrayList) {
                    if (((RumViewScope) next2).isActive()) {
                        arrayList2.add(next2);
                    }
                }
                Collection arrayList3 = new ArrayList();
                for (RumViewScope rumContext : (List) arrayList2) {
                    String viewName = rumContext.getRumContext().getViewName();
                    if (viewName != null) {
                        arrayList3.add(viewName);
                    }
                }
                rumDebugListener.onReceiveRumActiveViews((List) arrayList3);
            }
        }
    }

    private final Time getEventTime(Map<String, ? extends Object> map) {
        Object obj = map.get(RumAttributes.INTERNAL_TIMESTAMP);
        Time time = null;
        Long l = obj instanceof Long ? (Long) obj : null;
        if (l != null) {
            time = TimeKt.asTime(l.longValue());
        }
        return time == null ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time;
    }

    private final String getErrorType(Map<String, ? extends Object> map) {
        Object obj = map.get(RumAttributes.INTERNAL_ERROR_TYPE);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    private final RumErrorSourceType getErrorSourceType(Map<String, ? extends Object> map) {
        Object obj = map.get(RumAttributes.INTERNAL_ERROR_SOURCE_TYPE);
        String str = null;
        String str2 = obj instanceof String ? (String) obj : null;
        if (str2 != null) {
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            str = str2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(locale)");
        }
        if (str != null) {
            switch (str.hashCode()) {
                case -861391249:
                    if (str.equals(CoreFeature.DEFAULT_SOURCE_NAME)) {
                        return RumErrorSourceType.ANDROID;
                    }
                    break;
                case -760334308:
                    if (str.equals("flutter")) {
                        return RumErrorSourceType.FLUTTER;
                    }
                    break;
                case 150940456:
                    if (str.equals("browser")) {
                        return RumErrorSourceType.BROWSER;
                    }
                    break;
                case 828638245:
                    if (str.equals("react-native")) {
                        return RumErrorSourceType.REACT_NATIVE;
                    }
                    break;
            }
        }
        return RumErrorSourceType.ANDROID;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/monitor/DatadogRumMonitor$Companion;", "", "()V", "KEEP_ALIVE_MS", "", "getKEEP_ALIVE_MS$dd_sdk_android_release", "()J", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DatadogRumMonitor.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long getKEEP_ALIVE_MS$dd_sdk_android_release() {
            return DatadogRumMonitor.KEEP_ALIVE_MS;
        }
    }
}
