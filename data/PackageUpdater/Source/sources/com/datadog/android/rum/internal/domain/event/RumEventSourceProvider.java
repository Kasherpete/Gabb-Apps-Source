package com.datadog.android.rum.internal.domain.event;

import com.datadog.android.rum.model.ActionEvent;
import com.datadog.android.rum.model.ErrorEvent;
import com.datadog.android.rum.model.LongTaskEvent;
import com.datadog.android.rum.model.ResourceEvent;
import com.datadog.android.rum.model.ViewEvent;
import com.datadog.android.telemetry.model.TelemetryDebugEvent;
import com.datadog.android.telemetry.model.TelemetryErrorEvent;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0010\u001a\u0004\u0018\u00010\u00118FX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0015\u001a\u0004\u0018\u00010\u00168FX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u001a\u001a\u0004\u0018\u00010\u001b8FX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u001f\u001a\u0004\u0018\u00010 8FX\u0002¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b!\u0010\"R\u001d\u0010$\u001a\u0004\u0018\u00010%8FX\u0002¢\u0006\f\n\u0004\b(\u0010\n\u001a\u0004\b&\u0010'¨\u0006*"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "", "source", "", "(Ljava/lang/String;)V", "actionEventSource", "Lcom/datadog/android/rum/model/ActionEvent$Source;", "getActionEventSource", "()Lcom/datadog/android/rum/model/ActionEvent$Source;", "actionEventSource$delegate", "Lkotlin/Lazy;", "errorEventSource", "Lcom/datadog/android/rum/model/ErrorEvent$ErrorEventSource;", "getErrorEventSource", "()Lcom/datadog/android/rum/model/ErrorEvent$ErrorEventSource;", "errorEventSource$delegate", "longTaskEventSource", "Lcom/datadog/android/rum/model/LongTaskEvent$Source;", "getLongTaskEventSource", "()Lcom/datadog/android/rum/model/LongTaskEvent$Source;", "longTaskEventSource$delegate", "resourceEventSource", "Lcom/datadog/android/rum/model/ResourceEvent$Source;", "getResourceEventSource", "()Lcom/datadog/android/rum/model/ResourceEvent$Source;", "resourceEventSource$delegate", "telemetryDebugEventSource", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Source;", "getTelemetryDebugEventSource", "()Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Source;", "telemetryDebugEventSource$delegate", "telemetryErrorEventSource", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Source;", "getTelemetryErrorEventSource", "()Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Source;", "telemetryErrorEventSource$delegate", "viewEventSource", "Lcom/datadog/android/rum/model/ViewEvent$Source;", "getViewEventSource", "()Lcom/datadog/android/rum/model/ViewEvent$Source;", "viewEventSource$delegate", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumEventSourceProvider.kt */
public final class RumEventSourceProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String UNKNOWN_SOURCE_WARNING_MESSAGE_FORMAT = "You are using an unknown source %s for your events";
    private final Lazy actionEventSource$delegate;
    private final Lazy errorEventSource$delegate;
    private final Lazy longTaskEventSource$delegate;
    private final Lazy resourceEventSource$delegate;
    private final Lazy telemetryDebugEventSource$delegate;
    private final Lazy telemetryErrorEventSource$delegate;
    private final Lazy viewEventSource$delegate;

    public RumEventSourceProvider(String str) {
        Intrinsics.checkNotNullParameter(str, "source");
        this.viewEventSource$delegate = LazyKt.lazy(new RumEventSourceProvider$viewEventSource$2(str));
        this.longTaskEventSource$delegate = LazyKt.lazy(new RumEventSourceProvider$longTaskEventSource$2(str));
        this.errorEventSource$delegate = LazyKt.lazy(new RumEventSourceProvider$errorEventSource$2(str));
        this.actionEventSource$delegate = LazyKt.lazy(new RumEventSourceProvider$actionEventSource$2(str));
        this.resourceEventSource$delegate = LazyKt.lazy(new RumEventSourceProvider$resourceEventSource$2(str));
        this.telemetryDebugEventSource$delegate = LazyKt.lazy(new RumEventSourceProvider$telemetryDebugEventSource$2(str));
        this.telemetryErrorEventSource$delegate = LazyKt.lazy(new RumEventSourceProvider$telemetryErrorEventSource$2(str));
    }

    public final ViewEvent.Source getViewEventSource() {
        return (ViewEvent.Source) this.viewEventSource$delegate.getValue();
    }

    public final LongTaskEvent.Source getLongTaskEventSource() {
        return (LongTaskEvent.Source) this.longTaskEventSource$delegate.getValue();
    }

    public final ErrorEvent.ErrorEventSource getErrorEventSource() {
        return (ErrorEvent.ErrorEventSource) this.errorEventSource$delegate.getValue();
    }

    public final ActionEvent.Source getActionEventSource() {
        return (ActionEvent.Source) this.actionEventSource$delegate.getValue();
    }

    public final ResourceEvent.Source getResourceEventSource() {
        return (ResourceEvent.Source) this.resourceEventSource$delegate.getValue();
    }

    public final TelemetryDebugEvent.Source getTelemetryDebugEventSource() {
        return (TelemetryDebugEvent.Source) this.telemetryDebugEventSource$delegate.getValue();
    }

    public final TelemetryErrorEvent.Source getTelemetryErrorEventSource() {
        return (TelemetryErrorEvent.Source) this.telemetryErrorEventSource$delegate.getValue();
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider$Companion;", "", "()V", "UNKNOWN_SOURCE_WARNING_MESSAGE_FORMAT", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumEventSourceProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
