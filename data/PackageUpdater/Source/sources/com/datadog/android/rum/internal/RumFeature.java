package com.datadog.android.rum.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import com.datadog.android.core.configuration.Configuration;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.SdkFeature;
import com.datadog.android.core.internal.event.NoOpEventMapper;
import com.datadog.android.core.internal.net.DataUploader;
import com.datadog.android.core.internal.persistence.PersistenceStrategy;
import com.datadog.android.core.internal.system.StaticAndroidInfoProvider;
import com.datadog.android.core.internal.utils.ConcurrencyExtKt;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.event.EventMapper;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.internal.anr.ANRDetectorRunnable;
import com.datadog.android.rum.internal.debug.UiRumDebugListener;
import com.datadog.android.rum.internal.domain.RumFilePersistenceStrategy;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.rum.internal.ndk.DatadogNdkCrashHandler;
import com.datadog.android.rum.internal.net.RumOkHttpUploaderV2;
import com.datadog.android.rum.internal.tracking.NoOpUserActionTrackingStrategy;
import com.datadog.android.rum.internal.tracking.UserActionTrackingStrategy;
import com.datadog.android.rum.internal.vitals.AggregatingVitalMonitor;
import com.datadog.android.rum.internal.vitals.CPUVitalReader;
import com.datadog.android.rum.internal.vitals.MemoryVitalReader;
import com.datadog.android.rum.internal.vitals.NoOpVitalMonitor;
import com.datadog.android.rum.internal.vitals.VitalFrameCallback;
import com.datadog.android.rum.internal.vitals.VitalMonitor;
import com.datadog.android.rum.internal.vitals.VitalObserver;
import com.datadog.android.rum.internal.vitals.VitalReader;
import com.datadog.android.rum.internal.vitals.VitalReaderRunnable;
import com.datadog.android.rum.tracking.NoOpTrackingStrategy;
import com.datadog.android.rum.tracking.NoOpViewTrackingStrategy;
import com.datadog.android.rum.tracking.TrackingStrategy;
import com.datadog.android.rum.tracking.ViewTrackingStrategy;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u001e\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00020a2\u0006\u0010b\u001a\u00020\"2\u0006\u0010c\u001a\u00020\u0003H\u0016J\u0010\u0010d\u001a\u00020e2\u0006\u0010c\u001a\u00020\u0003H\u0016J\r\u0010f\u001a\u00020gH\u0000¢\u0006\u0002\bhJ\r\u0010i\u001a\u00020gH\u0000¢\u0006\u0002\bjJ\b\u0010k\u001a\u00020gH\u0002J\u0018\u0010l\u001a\u00020g2\u0006\u0010m\u001a\u00020n2\u0006\u0010o\u001a\u00020pH\u0002J\b\u0010q\u001a\u00020gH\u0002J\u0018\u0010r\u001a\u00020g2\u0006\u0010b\u001a\u00020\"2\u0006\u0010c\u001a\u00020\u0003H\u0016J\u0010\u0010s\u001a\u00020g2\u0006\u0010b\u001a\u00020\"H\u0016J\b\u0010t\u001a\u00020gH\u0016J\u0010\u0010u\u001a\u00020g2\u0006\u0010!\u001a\u00020\"H\u0002J\u0012\u0010v\u001a\u00020g2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020.X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001c\u00103\u001a\u0004\u0018\u000104X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020.X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00100\"\u0004\b;\u00102R\u001a\u0010<\u001a\u00020=X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001a\u0010B\u001a\u00020.X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u00100\"\u0004\bD\u00102R \u0010E\u001a\b\u0012\u0004\u0012\u00020\u00020FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001a\u0010K\u001a\u00020LX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001a\u0010Q\u001a\u00020LX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010N\"\u0004\bS\u0010PR\u001a\u0010T\u001a\u00020UX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001a\u0010Z\u001a\u00020[X.¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_¨\u0006w"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/RumFeature;", "Lcom/datadog/android/core/internal/SdkFeature;", "", "Lcom/datadog/android/core/configuration/Configuration$Feature$RUM;", "()V", "RUM_FEATURE_NAME", "", "VITAL_UPDATE_PERIOD_MS", "", "actionTrackingStrategy", "Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;", "getActionTrackingStrategy$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;", "setActionTrackingStrategy$dd_sdk_android_release", "(Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;)V", "anrDetectorExecutorService", "Ljava/util/concurrent/ExecutorService;", "getAnrDetectorExecutorService$dd_sdk_android_release", "()Ljava/util/concurrent/ExecutorService;", "setAnrDetectorExecutorService$dd_sdk_android_release", "(Ljava/util/concurrent/ExecutorService;)V", "anrDetectorHandler", "Landroid/os/Handler;", "getAnrDetectorHandler$dd_sdk_android_release", "()Landroid/os/Handler;", "setAnrDetectorHandler$dd_sdk_android_release", "(Landroid/os/Handler;)V", "anrDetectorRunnable", "Lcom/datadog/android/rum/internal/anr/ANRDetectorRunnable;", "getAnrDetectorRunnable$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/anr/ANRDetectorRunnable;", "setAnrDetectorRunnable$dd_sdk_android_release", "(Lcom/datadog/android/rum/internal/anr/ANRDetectorRunnable;)V", "appContext", "Landroid/content/Context;", "getAppContext$dd_sdk_android_release", "()Landroid/content/Context;", "setAppContext$dd_sdk_android_release", "(Landroid/content/Context;)V", "backgroundEventTracking", "", "getBackgroundEventTracking$dd_sdk_android_release", "()Z", "setBackgroundEventTracking$dd_sdk_android_release", "(Z)V", "cpuVitalMonitor", "Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "getCpuVitalMonitor$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "setCpuVitalMonitor$dd_sdk_android_release", "(Lcom/datadog/android/rum/internal/vitals/VitalMonitor;)V", "debugActivityLifecycleListener", "Landroid/app/Application$ActivityLifecycleCallbacks;", "getDebugActivityLifecycleListener$dd_sdk_android_release", "()Landroid/app/Application$ActivityLifecycleCallbacks;", "setDebugActivityLifecycleListener$dd_sdk_android_release", "(Landroid/app/Application$ActivityLifecycleCallbacks;)V", "frameRateVitalMonitor", "getFrameRateVitalMonitor$dd_sdk_android_release", "setFrameRateVitalMonitor$dd_sdk_android_release", "longTaskTrackingStrategy", "Lcom/datadog/android/rum/tracking/TrackingStrategy;", "getLongTaskTrackingStrategy$dd_sdk_android_release", "()Lcom/datadog/android/rum/tracking/TrackingStrategy;", "setLongTaskTrackingStrategy$dd_sdk_android_release", "(Lcom/datadog/android/rum/tracking/TrackingStrategy;)V", "memoryVitalMonitor", "getMemoryVitalMonitor$dd_sdk_android_release", "setMemoryVitalMonitor$dd_sdk_android_release", "rumEventMapper", "Lcom/datadog/android/event/EventMapper;", "getRumEventMapper$dd_sdk_android_release", "()Lcom/datadog/android/event/EventMapper;", "setRumEventMapper$dd_sdk_android_release", "(Lcom/datadog/android/event/EventMapper;)V", "samplingRate", "", "getSamplingRate$dd_sdk_android_release", "()F", "setSamplingRate$dd_sdk_android_release", "(F)V", "telemetrySamplingRate", "getTelemetrySamplingRate$dd_sdk_android_release", "setTelemetrySamplingRate$dd_sdk_android_release", "viewTrackingStrategy", "Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;", "getViewTrackingStrategy$dd_sdk_android_release", "()Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;", "setViewTrackingStrategy$dd_sdk_android_release", "(Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;)V", "vitalExecutorService", "Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "getVitalExecutorService$dd_sdk_android_release", "()Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "setVitalExecutorService$dd_sdk_android_release", "(Ljava/util/concurrent/ScheduledThreadPoolExecutor;)V", "createPersistenceStrategy", "Lcom/datadog/android/core/internal/persistence/PersistenceStrategy;", "context", "configuration", "createUploader", "Lcom/datadog/android/core/internal/net/DataUploader;", "disableDebugging", "", "disableDebugging$dd_sdk_android_release", "enableDebugging", "enableDebugging$dd_sdk_android_release", "initializeANRDetector", "initializeVitalMonitor", "vitalReader", "Lcom/datadog/android/rum/internal/vitals/VitalReader;", "vitalObserver", "Lcom/datadog/android/rum/internal/vitals/VitalObserver;", "initializeVitalMonitors", "onInitialize", "onPostInitialized", "onStop", "registerTrackingStrategies", "unregisterTrackingStrategies", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumFeature.kt */
public final class RumFeature extends SdkFeature<Object, Configuration.Feature.RUM> {
    public static final RumFeature INSTANCE = new RumFeature();
    public static final String RUM_FEATURE_NAME = "rum";
    private static final long VITAL_UPDATE_PERIOD_MS = 100;
    private static UserActionTrackingStrategy actionTrackingStrategy = new NoOpUserActionTrackingStrategy();
    public static ExecutorService anrDetectorExecutorService;
    public static Handler anrDetectorHandler;
    public static ANRDetectorRunnable anrDetectorRunnable;
    public static Context appContext;
    private static boolean backgroundEventTracking;
    private static VitalMonitor cpuVitalMonitor = new NoOpVitalMonitor();
    private static Application.ActivityLifecycleCallbacks debugActivityLifecycleListener;
    private static VitalMonitor frameRateVitalMonitor = new NoOpVitalMonitor();
    private static TrackingStrategy longTaskTrackingStrategy = new NoOpTrackingStrategy();
    private static VitalMonitor memoryVitalMonitor = new NoOpVitalMonitor();
    private static EventMapper<Object> rumEventMapper = new NoOpEventMapper();
    private static float samplingRate;
    private static float telemetrySamplingRate;
    private static ViewTrackingStrategy viewTrackingStrategy = new NoOpViewTrackingStrategy();
    public static ScheduledThreadPoolExecutor vitalExecutorService;

    private RumFeature() {
    }

    public final float getSamplingRate$dd_sdk_android_release() {
        return samplingRate;
    }

    public final void setSamplingRate$dd_sdk_android_release(float f) {
        samplingRate = f;
    }

    public final float getTelemetrySamplingRate$dd_sdk_android_release() {
        return telemetrySamplingRate;
    }

    public final void setTelemetrySamplingRate$dd_sdk_android_release(float f) {
        telemetrySamplingRate = f;
    }

    public final boolean getBackgroundEventTracking$dd_sdk_android_release() {
        return backgroundEventTracking;
    }

    public final void setBackgroundEventTracking$dd_sdk_android_release(boolean z) {
        backgroundEventTracking = z;
    }

    public final ViewTrackingStrategy getViewTrackingStrategy$dd_sdk_android_release() {
        return viewTrackingStrategy;
    }

    public final void setViewTrackingStrategy$dd_sdk_android_release(ViewTrackingStrategy viewTrackingStrategy2) {
        Intrinsics.checkNotNullParameter(viewTrackingStrategy2, "<set-?>");
        viewTrackingStrategy = viewTrackingStrategy2;
    }

    public final UserActionTrackingStrategy getActionTrackingStrategy$dd_sdk_android_release() {
        return actionTrackingStrategy;
    }

    public final void setActionTrackingStrategy$dd_sdk_android_release(UserActionTrackingStrategy userActionTrackingStrategy) {
        Intrinsics.checkNotNullParameter(userActionTrackingStrategy, "<set-?>");
        actionTrackingStrategy = userActionTrackingStrategy;
    }

    public final EventMapper<Object> getRumEventMapper$dd_sdk_android_release() {
        return rumEventMapper;
    }

    public final void setRumEventMapper$dd_sdk_android_release(EventMapper<Object> eventMapper) {
        Intrinsics.checkNotNullParameter(eventMapper, "<set-?>");
        rumEventMapper = eventMapper;
    }

    public final TrackingStrategy getLongTaskTrackingStrategy$dd_sdk_android_release() {
        return longTaskTrackingStrategy;
    }

    public final void setLongTaskTrackingStrategy$dd_sdk_android_release(TrackingStrategy trackingStrategy) {
        Intrinsics.checkNotNullParameter(trackingStrategy, "<set-?>");
        longTaskTrackingStrategy = trackingStrategy;
    }

    public final VitalMonitor getCpuVitalMonitor$dd_sdk_android_release() {
        return cpuVitalMonitor;
    }

    public final void setCpuVitalMonitor$dd_sdk_android_release(VitalMonitor vitalMonitor) {
        Intrinsics.checkNotNullParameter(vitalMonitor, "<set-?>");
        cpuVitalMonitor = vitalMonitor;
    }

    public final VitalMonitor getMemoryVitalMonitor$dd_sdk_android_release() {
        return memoryVitalMonitor;
    }

    public final void setMemoryVitalMonitor$dd_sdk_android_release(VitalMonitor vitalMonitor) {
        Intrinsics.checkNotNullParameter(vitalMonitor, "<set-?>");
        memoryVitalMonitor = vitalMonitor;
    }

    public final VitalMonitor getFrameRateVitalMonitor$dd_sdk_android_release() {
        return frameRateVitalMonitor;
    }

    public final void setFrameRateVitalMonitor$dd_sdk_android_release(VitalMonitor vitalMonitor) {
        Intrinsics.checkNotNullParameter(vitalMonitor, "<set-?>");
        frameRateVitalMonitor = vitalMonitor;
    }

    public final Application.ActivityLifecycleCallbacks getDebugActivityLifecycleListener$dd_sdk_android_release() {
        return debugActivityLifecycleListener;
    }

    public final void setDebugActivityLifecycleListener$dd_sdk_android_release(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        debugActivityLifecycleListener = activityLifecycleCallbacks;
    }

    public final ScheduledThreadPoolExecutor getVitalExecutorService$dd_sdk_android_release() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = vitalExecutorService;
        if (scheduledThreadPoolExecutor != null) {
            return scheduledThreadPoolExecutor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("vitalExecutorService");
        return null;
    }

    public final void setVitalExecutorService$dd_sdk_android_release(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        Intrinsics.checkNotNullParameter(scheduledThreadPoolExecutor, "<set-?>");
        vitalExecutorService = scheduledThreadPoolExecutor;
    }

    public final ExecutorService getAnrDetectorExecutorService$dd_sdk_android_release() {
        ExecutorService executorService = anrDetectorExecutorService;
        if (executorService != null) {
            return executorService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("anrDetectorExecutorService");
        return null;
    }

    public final void setAnrDetectorExecutorService$dd_sdk_android_release(ExecutorService executorService) {
        Intrinsics.checkNotNullParameter(executorService, "<set-?>");
        anrDetectorExecutorService = executorService;
    }

    public final ANRDetectorRunnable getAnrDetectorRunnable$dd_sdk_android_release() {
        ANRDetectorRunnable aNRDetectorRunnable = anrDetectorRunnable;
        if (aNRDetectorRunnable != null) {
            return aNRDetectorRunnable;
        }
        Intrinsics.throwUninitializedPropertyAccessException("anrDetectorRunnable");
        return null;
    }

    public final void setAnrDetectorRunnable$dd_sdk_android_release(ANRDetectorRunnable aNRDetectorRunnable) {
        Intrinsics.checkNotNullParameter(aNRDetectorRunnable, "<set-?>");
        anrDetectorRunnable = aNRDetectorRunnable;
    }

    public final Handler getAnrDetectorHandler$dd_sdk_android_release() {
        Handler handler = anrDetectorHandler;
        if (handler != null) {
            return handler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("anrDetectorHandler");
        return null;
    }

    public final void setAnrDetectorHandler$dd_sdk_android_release(Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "<set-?>");
        anrDetectorHandler = handler;
    }

    public final Context getAppContext$dd_sdk_android_release() {
        Context context = appContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appContext");
        return null;
    }

    public final void setAppContext$dd_sdk_android_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        appContext = context;
    }

    public void onInitialize(Context context, Configuration.Feature.RUM rum) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(rum, "configuration");
        samplingRate = rum.getSamplingRate();
        telemetrySamplingRate = rum.getTelemetrySamplingRate();
        backgroundEventTracking = rum.getBackgroundEventTracking();
        rumEventMapper = rum.getRumEventMapper();
        ViewTrackingStrategy viewTrackingStrategy2 = rum.getViewTrackingStrategy();
        if (viewTrackingStrategy2 != null) {
            INSTANCE.setViewTrackingStrategy$dd_sdk_android_release(viewTrackingStrategy2);
        }
        UserActionTrackingStrategy userActionTrackingStrategy = rum.getUserActionTrackingStrategy();
        if (userActionTrackingStrategy != null) {
            INSTANCE.setActionTrackingStrategy$dd_sdk_android_release(userActionTrackingStrategy);
        }
        TrackingStrategy longTaskTrackingStrategy2 = rum.getLongTaskTrackingStrategy();
        if (longTaskTrackingStrategy2 != null) {
            INSTANCE.setLongTaskTrackingStrategy$dd_sdk_android_release(longTaskTrackingStrategy2);
        }
        initializeVitalMonitors();
        initializeANRDetector();
        registerTrackingStrategies(context);
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        setAppContext$dd_sdk_android_release(applicationContext);
    }

    public void onStop() {
        unregisterTrackingStrategies((Context) CoreFeature.INSTANCE.getContextRef$dd_sdk_android_release().get());
        viewTrackingStrategy = new NoOpViewTrackingStrategy();
        actionTrackingStrategy = new NoOpUserActionTrackingStrategy();
        longTaskTrackingStrategy = new NoOpTrackingStrategy();
        rumEventMapper = new NoOpEventMapper();
        cpuVitalMonitor = new NoOpVitalMonitor();
        memoryVitalMonitor = new NoOpVitalMonitor();
        frameRateVitalMonitor = new NoOpVitalMonitor();
        getVitalExecutorService$dd_sdk_android_release().shutdownNow();
        getAnrDetectorExecutorService$dd_sdk_android_release().shutdownNow();
        getAnrDetectorRunnable$dd_sdk_android_release().stop();
    }

    public PersistenceStrategy<Object> createPersistenceStrategy(Context context, Configuration.Feature.RUM rum) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(rum, "configuration");
        return new RumFilePersistenceStrategy(CoreFeature.INSTANCE.getTrackingConsentProvider$dd_sdk_android_release(), context, rum.getRumEventMapper(), CoreFeature.INSTANCE.getPersistenceExecutorService$dd_sdk_android_release(), RuntimeUtilsKt.getSdkLogger(), CoreFeature.INSTANCE.getLocalDataEncryption$dd_sdk_android_release(), DatadogNdkCrashHandler.Companion.getLastViewEventFile$dd_sdk_android_release(context));
    }

    public DataUploader createUploader(Configuration.Feature.RUM rum) {
        Intrinsics.checkNotNullParameter(rum, "configuration");
        return new RumOkHttpUploaderV2(rum.getEndpointUrl(), CoreFeature.INSTANCE.getClientToken$dd_sdk_android_release(), CoreFeature.INSTANCE.getSourceName$dd_sdk_android_release(), CoreFeature.INSTANCE.getSdkVersion$dd_sdk_android_release(), CoreFeature.INSTANCE.getOkHttpClient$dd_sdk_android_release(), StaticAndroidInfoProvider.INSTANCE);
    }

    public void onPostInitialized(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        migrateToCacheDir(context, "rum", RuntimeUtilsKt.getSdkLogger());
    }

    public final void enableDebugging$dd_sdk_android_release() {
        Context appContext$dd_sdk_android_release = getAppContext$dd_sdk_android_release();
        if (appContext$dd_sdk_android_release instanceof Application) {
            Application.ActivityLifecycleCallbacks uiRumDebugListener = new UiRumDebugListener();
            debugActivityLifecycleListener = uiRumDebugListener;
            ((Application) appContext$dd_sdk_android_release).registerActivityLifecycleCallbacks(uiRumDebugListener);
        }
    }

    public final void disableDebugging$dd_sdk_android_release() {
        Context appContext$dd_sdk_android_release = getAppContext$dd_sdk_android_release();
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = debugActivityLifecycleListener;
        if (activityLifecycleCallbacks != null && (appContext$dd_sdk_android_release instanceof Application)) {
            ((Application) appContext$dd_sdk_android_release).unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
            debugActivityLifecycleListener = null;
        }
    }

    private final void registerTrackingStrategies(Context context) {
        actionTrackingStrategy.register(context);
        viewTrackingStrategy.register(context);
        longTaskTrackingStrategy.register(context);
    }

    private final void unregisterTrackingStrategies(Context context) {
        actionTrackingStrategy.unregister(context);
        viewTrackingStrategy.unregister(context);
        longTaskTrackingStrategy.unregister(context);
    }

    private final void initializeVitalMonitors() {
        cpuVitalMonitor = new AggregatingVitalMonitor();
        memoryVitalMonitor = new AggregatingVitalMonitor();
        frameRateVitalMonitor = new AggregatingVitalMonitor();
        setVitalExecutorService$dd_sdk_android_release(new ScheduledThreadPoolExecutor(1));
        initializeVitalMonitor(new CPUVitalReader((File) null, 1, (DefaultConstructorMarker) null), cpuVitalMonitor);
        initializeVitalMonitor(new MemoryVitalReader((File) null, 1, (DefaultConstructorMarker) null), memoryVitalMonitor);
        try {
            Choreographer.getInstance().postFrameCallback(new VitalFrameCallback(frameRateVitalMonitor, RumFeature$initializeVitalMonitors$vitalFrameCallback$1.INSTANCE));
        } catch (IllegalStateException e) {
            Logger.e$default(RuntimeUtilsKt.getSdkLogger(), "Unable to initialize the Choreographer FrameCallback", e, (Map) null, 4, (Object) null);
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), "It seems you initialized the SDK on a thread without a Looper: we won't be able to track your Views' refresh rate.", (Throwable) null, (Map) null, 6, (Object) null);
        }
    }

    private final void initializeVitalMonitor(VitalReader vitalReader, VitalObserver vitalObserver) {
        ConcurrencyExtKt.scheduleSafe(getVitalExecutorService$dd_sdk_android_release(), "Vitals monitoring", 100, TimeUnit.MILLISECONDS, new VitalReaderRunnable(vitalReader, vitalObserver, getVitalExecutorService$dd_sdk_android_release(), 100));
    }

    private final void initializeANRDetector() {
        setAnrDetectorHandler$dd_sdk_android_release(new Handler(Looper.getMainLooper()));
        setAnrDetectorRunnable$dd_sdk_android_release(new ANRDetectorRunnable(getAnrDetectorHandler$dd_sdk_android_release(), 0, 0, 6, (DefaultConstructorMarker) null));
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "newSingleThreadExecutor()");
        setAnrDetectorExecutorService$dd_sdk_android_release(newSingleThreadExecutor);
        ConcurrencyExtKt.executeSafe(getAnrDetectorExecutorService$dd_sdk_android_release(), "ANR detection", getAnrDetectorRunnable$dd_sdk_android_release());
    }
}
