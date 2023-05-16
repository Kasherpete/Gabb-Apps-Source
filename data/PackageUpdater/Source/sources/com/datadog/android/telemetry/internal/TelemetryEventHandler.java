package com.datadog.android.telemetry.internal;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.sampling.Sampler;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.core.internal.utils.ThrowableExtKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumSessionListener;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.rum.internal.domain.event.RumEventSourceProvider;
import com.datadog.android.rum.internal.domain.scope.RumRawEvent;
import com.datadog.android.telemetry.model.TelemetryDebugEvent;
import com.datadog.android.telemetry.model.TelemetryErrorEvent;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0000\u0018\u0000 .2\u00020\u0001:\u0002./B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0015H\u0002J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0003H\u0002J*\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00032\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\u001c\u0010&\u001a\u00020'2\u0006\u0010\u001a\u001a\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)J\u0018\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0019H\u0016R\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\u00020\u0012*\u00020\u00158BX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u00060"}, mo20735d2 = {"Lcom/datadog/android/telemetry/internal/TelemetryEventHandler;", "Lcom/datadog/android/rum/RumSessionListener;", "serviceName", "", "sdkVersion", "sourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "eventSampler", "Lcom/datadog/android/core/internal/sampling/Sampler;", "(Ljava/lang/String;Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;Lcom/datadog/android/core/internal/time/TimeProvider;Lcom/datadog/android/core/internal/sampling/Sampler;)V", "getEventSampler$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/sampling/Sampler;", "getSdkVersion$dd_sdk_android_release", "()Ljava/lang/String;", "seenInCurrentSession", "", "Lcom/datadog/android/telemetry/internal/TelemetryEventHandler$EventIdentity;", "getServiceName$dd_sdk_android_release", "identity", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$SendTelemetry;", "getIdentity", "(Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$SendTelemetry;)Lcom/datadog/android/telemetry/internal/TelemetryEventHandler$EventIdentity;", "canWrite", "", "event", "createDebugEvent", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent;", "timestamp", "", "rumContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "message", "createErrorEvent", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent;", "throwable", "", "handleEvent", "", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "", "onSessionStarted", "sessionId", "isDiscarded", "Companion", "EventIdentity", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TelemetryEventHandler.kt */
public final class TelemetryEventHandler implements RumSessionListener {
    public static final String ALREADY_SEEN_EVENT_MESSAGE = "Already seen telemetry event with identity=%s, rejecting.";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int MAX_EVENTS_PER_SESSION = 100;
    public static final String MAX_EVENT_NUMBER_REACHED_MESSAGE = "Max number of telemetry events per session reached, rejecting.";
    private final Sampler eventSampler;
    private final String sdkVersion;
    private final Set<EventIdentity> seenInCurrentSession = new LinkedHashSet();
    private final String serviceName;
    private final RumEventSourceProvider sourceProvider;
    private final TimeProvider timeProvider;

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryEventHandler.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TelemetryType.values().length];
            iArr[TelemetryType.DEBUG.ordinal()] = 1;
            iArr[TelemetryType.ERROR.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TelemetryEventHandler(String str, String str2, RumEventSourceProvider rumEventSourceProvider, TimeProvider timeProvider2, Sampler sampler) {
        Intrinsics.checkNotNullParameter(str, "serviceName");
        Intrinsics.checkNotNullParameter(str2, "sdkVersion");
        Intrinsics.checkNotNullParameter(rumEventSourceProvider, "sourceProvider");
        Intrinsics.checkNotNullParameter(timeProvider2, "timeProvider");
        Intrinsics.checkNotNullParameter(sampler, "eventSampler");
        this.serviceName = str;
        this.sdkVersion = str2;
        this.sourceProvider = rumEventSourceProvider;
        this.timeProvider = timeProvider2;
        this.eventSampler = sampler;
    }

    public final String getServiceName$dd_sdk_android_release() {
        return this.serviceName;
    }

    public final String getSdkVersion$dd_sdk_android_release() {
        return this.sdkVersion;
    }

    public final Sampler getEventSampler$dd_sdk_android_release() {
        return this.eventSampler;
    }

    public final void handleEvent(RumRawEvent.SendTelemetry sendTelemetry, DataWriter<Object> dataWriter) {
        Object obj;
        Intrinsics.checkNotNullParameter(sendTelemetry, "event");
        Intrinsics.checkNotNullParameter(dataWriter, "writer");
        if (canWrite(sendTelemetry)) {
            this.seenInCurrentSession.add(getIdentity(sendTelemetry));
            long timestamp = sendTelemetry.getEventTime().getTimestamp() + this.timeProvider.getServerOffsetMillis();
            RumContext rumContext$dd_sdk_android_release = GlobalRum.INSTANCE.getRumContext$dd_sdk_android_release();
            int i = WhenMappings.$EnumSwitchMapping$0[sendTelemetry.getType().ordinal()];
            if (i == 1) {
                obj = createDebugEvent(timestamp, rumContext$dd_sdk_android_release, sendTelemetry.getMessage());
            } else if (i == 2) {
                obj = createErrorEvent(timestamp, rumContext$dd_sdk_android_release, sendTelemetry.getMessage(), sendTelemetry.getThrowable());
            } else {
                throw new NoWhenBranchMatchedException();
            }
            dataWriter.write(obj);
        }
    }

    public void onSessionStarted(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        this.seenInCurrentSession.clear();
    }

    private final boolean canWrite(RumRawEvent.SendTelemetry sendTelemetry) {
        if (!this.eventSampler.sample()) {
            return false;
        }
        EventIdentity identity = getIdentity(sendTelemetry);
        if (this.seenInCurrentSession.contains(identity)) {
            Logger sdkLogger = RuntimeUtilsKt.getSdkLogger();
            String format = String.format(Locale.US, ALREADY_SEEN_EVENT_MESSAGE, Arrays.copyOf(new Object[]{identity}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            Logger.i$default(sdkLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
            return false;
        } else if (this.seenInCurrentSession.size() != 100) {
            return true;
        } else {
            Logger.i$default(RuntimeUtilsKt.getSdkLogger(), MAX_EVENT_NUMBER_REACHED_MESSAGE, (Throwable) null, (Map) null, 6, (Object) null);
            return false;
        }
    }

    private final TelemetryDebugEvent createDebugEvent(long j, RumContext rumContext, String str) {
        TelemetryDebugEvent.C0871Dd dd = new TelemetryDebugEvent.C0871Dd();
        TelemetryDebugEvent.Source telemetryDebugEventSource = this.sourceProvider.getTelemetryDebugEventSource();
        if (telemetryDebugEventSource == null) {
            telemetryDebugEventSource = TelemetryDebugEvent.Source.ANDROID;
        }
        TelemetryDebugEvent.Source source = telemetryDebugEventSource;
        String str2 = this.serviceName;
        String str3 = this.sdkVersion;
        TelemetryDebugEvent.Application application = new TelemetryDebugEvent.Application(rumContext.getApplicationId());
        TelemetryDebugEvent.Session session = new TelemetryDebugEvent.Session(rumContext.getSessionId());
        String viewId = rumContext.getViewId();
        TelemetryDebugEvent.Action action = null;
        TelemetryDebugEvent.View view = viewId == null ? null : new TelemetryDebugEvent.View(viewId);
        String actionId = rumContext.getActionId();
        if (actionId != null) {
            action = new TelemetryDebugEvent.Action(actionId);
        }
        return new TelemetryDebugEvent(dd, j, str2, source, str3, application, session, view, action, new TelemetryDebugEvent.Telemetry(str));
    }

    private final TelemetryErrorEvent createErrorEvent(long j, RumContext rumContext, String str, Throwable th) {
        TelemetryErrorEvent.C0872Dd dd = new TelemetryErrorEvent.C0872Dd();
        TelemetryErrorEvent.Source telemetryErrorEventSource = this.sourceProvider.getTelemetryErrorEventSource();
        if (telemetryErrorEventSource == null) {
            telemetryErrorEventSource = TelemetryErrorEvent.Source.ANDROID;
        }
        TelemetryErrorEvent.Source source = telemetryErrorEventSource;
        String str2 = this.serviceName;
        String str3 = this.sdkVersion;
        TelemetryErrorEvent.Application application = new TelemetryErrorEvent.Application(rumContext.getApplicationId());
        TelemetryErrorEvent.Session session = new TelemetryErrorEvent.Session(rumContext.getSessionId());
        String viewId = rumContext.getViewId();
        TelemetryErrorEvent.Error error = null;
        TelemetryErrorEvent.View view = viewId == null ? null : new TelemetryErrorEvent.View(viewId);
        String actionId = rumContext.getActionId();
        TelemetryErrorEvent.Action action = actionId == null ? null : new TelemetryErrorEvent.Action(actionId);
        if (th != null) {
            String loggableStackTrace = ThrowableExtKt.loggableStackTrace(th);
            String canonicalName = th.getClass().getCanonicalName();
            if (canonicalName == null) {
                canonicalName = th.getClass().getSimpleName();
            }
            error = new TelemetryErrorEvent.Error(loggableStackTrace, canonicalName);
        }
        return new TelemetryErrorEvent(dd, j, str2, source, str3, application, session, view, action, new TelemetryErrorEvent.Telemetry(str, error));
    }

    private final EventIdentity getIdentity(RumRawEvent.SendTelemetry sendTelemetry) {
        return new EventIdentity(sendTelemetry.getMessage(), sendTelemetry.getThrowable() != null ? sendTelemetry.getThrowable().getClass() : null);
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/telemetry/internal/TelemetryEventHandler$EventIdentity;", "", "message", "", "throwableClass", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Class;)V", "getMessage", "()Ljava/lang/String;", "getThrowableClass", "()Ljava/lang/Class;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryEventHandler.kt */
    public static final class EventIdentity {
        private final String message;
        private final Class<?> throwableClass;

        public static /* synthetic */ EventIdentity copy$default(EventIdentity eventIdentity, String str, Class<?> cls, int i, Object obj) {
            if ((i & 1) != 0) {
                str = eventIdentity.message;
            }
            if ((i & 2) != 0) {
                cls = eventIdentity.throwableClass;
            }
            return eventIdentity.copy(str, cls);
        }

        public final String component1() {
            return this.message;
        }

        public final Class<?> component2() {
            return this.throwableClass;
        }

        public final EventIdentity copy(String str, Class<?> cls) {
            Intrinsics.checkNotNullParameter(str, "message");
            return new EventIdentity(str, cls);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EventIdentity)) {
                return false;
            }
            EventIdentity eventIdentity = (EventIdentity) obj;
            return Intrinsics.areEqual((Object) this.message, (Object) eventIdentity.message) && Intrinsics.areEqual((Object) this.throwableClass, (Object) eventIdentity.throwableClass);
        }

        public int hashCode() {
            int hashCode = this.message.hashCode() * 31;
            Class<?> cls = this.throwableClass;
            return hashCode + (cls == null ? 0 : cls.hashCode());
        }

        public String toString() {
            String str = this.message;
            return "EventIdentity(message=" + str + ", throwableClass=" + this.throwableClass + ")";
        }

        public EventIdentity(String str, Class<?> cls) {
            Intrinsics.checkNotNullParameter(str, "message");
            this.message = str;
            this.throwableClass = cls;
        }

        public final String getMessage() {
            return this.message;
        }

        public final Class<?> getThrowableClass() {
            return this.throwableClass;
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo20735d2 = {"Lcom/datadog/android/telemetry/internal/TelemetryEventHandler$Companion;", "", "()V", "ALREADY_SEEN_EVENT_MESSAGE", "", "MAX_EVENTS_PER_SESSION", "", "MAX_EVENT_NUMBER_REACHED_MESSAGE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryEventHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
