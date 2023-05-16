package com.datadog.android.rum;

import android.os.Handler;
import android.os.Looper;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.sampling.RateBasedSampler;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.internal.RumFeature;
import com.datadog.android.rum.internal.domain.event.RumEventSourceProvider;
import com.datadog.android.rum.internal.monitor.DatadogRumMonitor;
import com.datadog.android.telemetry.internal.TelemetryEventHandler;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001:\u0001(J8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&J8\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0005H&J.\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00052\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&J8\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&J.\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00052\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&J0\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00052\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&JG\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&¢\u0006\u0002\u0010 JY\u0010!\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u00052\b\u0010#\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&¢\u0006\u0002\u0010$JO\u0010!\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&¢\u0006\u0002\u0010%J0\u0010&\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00052\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&J \u0010&\u001a\u00020\u00032\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH'J(\u0010'\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH&¨\u0006)"}, mo20735d2 = {"Lcom/datadog/android/rum/RumMonitor;", "", "addError", "", "message", "", "source", "Lcom/datadog/android/rum/RumErrorSource;", "throwable", "", "attributes", "", "addErrorWithStacktrace", "stacktrace", "addTiming", "name", "addUserAction", "type", "Lcom/datadog/android/rum/RumActionType;", "startResource", "key", "method", "url", "startUserAction", "startView", "stopResource", "statusCode", "", "size", "", "kind", "Lcom/datadog/android/rum/RumResourceKind;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Lcom/datadog/android/rum/RumResourceKind;Ljava/util/Map;)V", "stopResourceWithError", "stackTrace", "errorType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/Throwable;Ljava/util/Map;)V", "stopUserAction", "stopView", "Builder", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumMonitor.kt */
public interface RumMonitor {
    void addError(String str, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map);

    void addErrorWithStacktrace(String str, RumErrorSource rumErrorSource, String str2, Map<String, ? extends Object> map);

    void addTiming(String str);

    void addUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map);

    void startResource(String str, String str2, String str3, Map<String, ? extends Object> map);

    void startUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map);

    void startView(Object obj, String str, Map<String, ? extends Object> map);

    void stopResource(String str, Integer num, Long l, RumResourceKind rumResourceKind, Map<String, ? extends Object> map);

    void stopResourceWithError(String str, Integer num, String str2, RumErrorSource rumErrorSource, String str3, String str4, Map<String, ? extends Object> map);

    void stopResourceWithError(String str, Integer num, String str2, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map);

    void stopUserAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map);

    @Deprecated(message = "This method is deprecated. Please use RumMonitor#stopUserAction(type, name, attributes) instead")
    void stopUserAction(Map<String, ? extends Object> map);

    void stopView(Object obj, Map<String, ? extends Object> map);

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumMonitor.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void startView$default(RumMonitor rumMonitor, Object obj, String str, Map map, int i, Object obj2) {
            if (obj2 == null) {
                if ((i & 4) != 0) {
                    map = MapsKt.emptyMap();
                }
                rumMonitor.startView(obj, str, map);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startView");
        }

        public static /* synthetic */ void stopView$default(RumMonitor rumMonitor, Object obj, Map map, int i, Object obj2) {
            if (obj2 == null) {
                if ((i & 2) != 0) {
                    map = MapsKt.emptyMap();
                }
                rumMonitor.stopView(obj, map);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stopView");
        }

        public static /* synthetic */ void stopUserAction$default(RumMonitor rumMonitor, Map map, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    map = MapsKt.emptyMap();
                }
                rumMonitor.stopUserAction(map);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stopUserAction");
        }

        public static /* synthetic */ void stopUserAction$default(RumMonitor rumMonitor, RumActionType rumActionType, String str, Map map, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    map = MapsKt.emptyMap();
                }
                rumMonitor.stopUserAction(rumActionType, str, map);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stopUserAction");
        }

        public static /* synthetic */ void startResource$default(RumMonitor rumMonitor, String str, String str2, String str3, Map map, int i, Object obj) {
            if (obj == null) {
                if ((i & 8) != 0) {
                    map = MapsKt.emptyMap();
                }
                rumMonitor.startResource(str, str2, str3, map);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startResource");
        }

        public static /* synthetic */ void stopResourceWithError$default(RumMonitor rumMonitor, String str, Integer num, String str2, RumErrorSource rumErrorSource, Throwable th, Map map, int i, Object obj) {
            if (obj == null) {
                if ((i & 32) != 0) {
                    map = MapsKt.emptyMap();
                }
                rumMonitor.stopResourceWithError(str, num, str2, rumErrorSource, th, map);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stopResourceWithError");
        }

        public static /* synthetic */ void stopResourceWithError$default(RumMonitor rumMonitor, String str, Integer num, String str2, RumErrorSource rumErrorSource, String str3, String str4, Map map, int i, Object obj) {
            Map map2;
            if (obj == null) {
                if ((i & 64) != 0) {
                    map2 = MapsKt.emptyMap();
                } else {
                    map2 = map;
                }
                rumMonitor.stopResourceWithError(str, num, str2, rumErrorSource, str3, str4, map2);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stopResourceWithError");
        }
    }

    @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\u00002\b\b\u0001\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/rum/RumMonitor$Builder;", "", "()V", "samplingRate", "", "sessionListener", "Lcom/datadog/android/rum/RumSessionListener;", "build", "Lcom/datadog/android/rum/RumMonitor;", "sampleRumSessions", "setSessionListener", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumMonitor.kt */
    public static final class Builder {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public static final String INVALID_APPLICATION_ID_ERROR_MESSAGE = "You're trying to create a RumMonitor instance, but the RUM application id was null or empty. No RUM data will be sent.";
        public static final String RUM_NOT_ENABLED_ERROR_MESSAGE = "You're trying to create a RumMonitor instance, but the SDK was not initialized or RUM feature was disabled in your Configuration. No RUM data will be sent.";
        private float samplingRate = RumFeature.INSTANCE.getSamplingRate$dd_sdk_android_release();
        private RumSessionListener sessionListener;

        public final Builder sampleRumSessions(float f) {
            this.samplingRate = f;
            return this;
        }

        public final Builder setSessionListener(RumSessionListener rumSessionListener) {
            Intrinsics.checkNotNullParameter(rumSessionListener, "sessionListener");
            this.sessionListener = rumSessionListener;
            return this;
        }

        public final RumMonitor build() {
            String rumApplicationId$dd_sdk_android_release = CoreFeature.INSTANCE.getRumApplicationId$dd_sdk_android_release();
            if (!RumFeature.INSTANCE.isInitialized()) {
                Logger.e$default(RuntimeUtilsKt.getDevLogger(), "You're trying to create a RumMonitor instance, but the SDK was not initialized or RUM feature was disabled in your Configuration. No RUM data will be sent.\nPlease add the following code in your application's onCreate() method:\nval credentials = Credentials(\"<CLIENT_TOKEN>\", \"<ENVIRONMENT>\", \"<VARIANT>\", \"<APPLICATION_ID>\")\nDatadog.initialize(context, credentials, configuration, trackingConsent);", (Throwable) null, (Map) null, 6, (Object) null);
                return new NoOpRumMonitor();
            }
            CharSequence charSequence = rumApplicationId$dd_sdk_android_release;
            if (charSequence == null || StringsKt.isBlank(charSequence)) {
                Logger.e$default(RuntimeUtilsKt.getDevLogger(), INVALID_APPLICATION_ID_ERROR_MESSAGE, (Throwable) null, (Map) null, 6, (Object) null);
                return new NoOpRumMonitor();
            }
            return new DatadogRumMonitor(rumApplicationId$dd_sdk_android_release, this.samplingRate, RumFeature.INSTANCE.getBackgroundEventTracking$dd_sdk_android_release(), RumFeature.INSTANCE.getPersistenceStrategy$dd_sdk_android_release().getWriter(), new Handler(Looper.getMainLooper()), new TelemetryEventHandler(CoreFeature.INSTANCE.getServiceName$dd_sdk_android_release(), CoreFeature.INSTANCE.getSdkVersion$dd_sdk_android_release(), new RumEventSourceProvider(CoreFeature.INSTANCE.getSourceName$dd_sdk_android_release()), CoreFeature.INSTANCE.getTimeProvider$dd_sdk_android_release(), new RateBasedSampler(RumFeature.INSTANCE.getTelemetrySamplingRate$dd_sdk_android_release() / ((float) 100))), CoreFeature.INSTANCE.getFirstPartyHostDetector$dd_sdk_android_release(), RumFeature.INSTANCE.getCpuVitalMonitor$dd_sdk_android_release(), RumFeature.INSTANCE.getMemoryVitalMonitor$dd_sdk_android_release(), RumFeature.INSTANCE.getFrameRateVitalMonitor$dd_sdk_android_release(), CoreFeature.INSTANCE.getTimeProvider$dd_sdk_android_release(), this.sessionListener, (ExecutorService) null, 4096, (DefaultConstructorMarker) null);
        }

        @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo20735d2 = {"Lcom/datadog/android/rum/RumMonitor$Builder$Companion;", "", "()V", "INVALID_APPLICATION_ID_ERROR_MESSAGE", "", "RUM_NOT_ENABLED_ERROR_MESSAGE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: RumMonitor.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
