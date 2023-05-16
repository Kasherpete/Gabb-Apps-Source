package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.rum.RumActionType;
import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumResourceKind;
import com.datadog.android.rum.internal.RumErrorSourceType;
import com.datadog.android.rum.internal.domain.Time;
import com.datadog.android.rum.internal.domain.event.ResourceTiming;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.rum.model.ViewEvent;
import com.datadog.android.telemetry.internal.TelemetryType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u001c\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"B\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0001\u001c#$%&'()*+,-./0123456789:;<=>¨\u0006?"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "", "()V", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "ActionDropped", "ActionSent", "AddCustomTiming", "AddError", "AddLongTask", "AddResourceTiming", "ApplicationStarted", "ErrorDropped", "ErrorSent", "KeepAlive", "LongTaskDropped", "LongTaskSent", "ResetSession", "ResourceDropped", "ResourceSent", "SendCustomActionNow", "SendTelemetry", "StartAction", "StartResource", "StartView", "StopAction", "StopResource", "StopResourceWithError", "StopResourceWithStackTrace", "StopView", "UpdateViewLoadingTime", "WaitForResourceTiming", "WebViewEvent", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartView;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopView;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartAction;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopAction;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartResource;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$WaitForResourceTiming;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddResourceTiming;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResource;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResourceWithError;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResourceWithStackTrace;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddError;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$UpdateViewLoadingTime;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ResourceSent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ActionSent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ErrorSent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$LongTaskSent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ResourceDropped;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ActionDropped;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ErrorDropped;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$LongTaskDropped;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ResetSession;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$KeepAlive;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ApplicationStarted;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddCustomTiming;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddLongTask;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$SendCustomActionNow;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$WebViewEvent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$SendTelemetry;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumRawEvent.kt */
public abstract class RumRawEvent {
    public /* synthetic */ RumRawEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Time getEventTime();

    private RumRawEvent() {
    }

    @Metadata(mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u0017\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J?\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartView;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "key", "", "name", "", "attributes", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/Object;Ljava/lang/String;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)V", "getAttributes", "()Ljava/util/Map;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getKey", "()Ljava/lang/Object;", "getName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class StartView extends RumRawEvent {
        private final Map<String, Object> attributes;
        private final Time eventTime;
        private final Object key;
        private final String name;

        public static /* synthetic */ StartView copy$default(StartView startView, Object obj, String str, Map<String, Object> map, Time time, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = startView.key;
            }
            if ((i & 2) != 0) {
                str = startView.name;
            }
            if ((i & 4) != 0) {
                map = startView.attributes;
            }
            if ((i & 8) != 0) {
                time = startView.getEventTime();
            }
            return startView.copy(obj, str, map, time);
        }

        public final Object component1() {
            return this.key;
        }

        public final String component2() {
            return this.name;
        }

        public final Map<String, Object> component3() {
            return this.attributes;
        }

        public final Time component4() {
            return getEventTime();
        }

        public final StartView copy(Object obj, String str, Map<String, ? extends Object> map, Time time) {
            Intrinsics.checkNotNullParameter(obj, "key");
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new StartView(obj, str, map, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StartView)) {
                return false;
            }
            StartView startView = (StartView) obj;
            return Intrinsics.areEqual(this.key, startView.key) && Intrinsics.areEqual((Object) this.name, (Object) startView.name) && Intrinsics.areEqual((Object) this.attributes, (Object) startView.attributes) && Intrinsics.areEqual((Object) getEventTime(), (Object) startView.getEventTime());
        }

        public int hashCode() {
            return (((((this.key.hashCode() * 31) + this.name.hashCode()) * 31) + this.attributes.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            Object obj = this.key;
            String str = this.name;
            Map<String, Object> map = this.attributes;
            return "StartView(key=" + obj + ", name=" + str + ", attributes=" + map + ", eventTime=" + getEventTime() + ")";
        }

        public final Object getKey() {
            return this.key;
        }

        public final String getName() {
            return this.name;
        }

        public final Map<String, Object> getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StartView(Object obj, String str, Map map, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, str, map, (i & 8) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StartView(Object obj, String str, Map<String, ? extends Object> map, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(obj, "key");
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.key = obj;
            this.name = str;
            this.attributes = map;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0017\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\bHÆ\u0003J5\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001R\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopView;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "key", "", "attributes", "", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/Object;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)V", "getAttributes", "()Ljava/util/Map;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getKey", "()Ljava/lang/Object;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class StopView extends RumRawEvent {
        private final Map<String, Object> attributes;
        private final Time eventTime;
        private final Object key;

        public static /* synthetic */ StopView copy$default(StopView stopView, Object obj, Map<String, Object> map, Time time, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = stopView.key;
            }
            if ((i & 2) != 0) {
                map = stopView.attributes;
            }
            if ((i & 4) != 0) {
                time = stopView.getEventTime();
            }
            return stopView.copy(obj, map, time);
        }

        public final Object component1() {
            return this.key;
        }

        public final Map<String, Object> component2() {
            return this.attributes;
        }

        public final Time component3() {
            return getEventTime();
        }

        public final StopView copy(Object obj, Map<String, ? extends Object> map, Time time) {
            Intrinsics.checkNotNullParameter(obj, "key");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new StopView(obj, map, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StopView)) {
                return false;
            }
            StopView stopView = (StopView) obj;
            return Intrinsics.areEqual(this.key, stopView.key) && Intrinsics.areEqual((Object) this.attributes, (Object) stopView.attributes) && Intrinsics.areEqual((Object) getEventTime(), (Object) stopView.getEventTime());
        }

        public int hashCode() {
            return (((this.key.hashCode() * 31) + this.attributes.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            Object obj = this.key;
            Map<String, Object> map = this.attributes;
            return "StopView(key=" + obj + ", attributes=" + map + ", eventTime=" + getEventTime() + ")";
        }

        public final Object getKey() {
            return this.key;
        }

        public final Map<String, Object> getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StopView(Object obj, Map map, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, map, (i & 4) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StopView(Object obj, Map<String, ? extends Object> map, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(obj, "key");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.key = obj;
            this.attributes = map;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u0017\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\fHÆ\u0003JI\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00072\b\u0010\u001f\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006#"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartAction;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "type", "Lcom/datadog/android/rum/RumActionType;", "name", "", "waitForStop", "", "attributes", "", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Lcom/datadog/android/rum/RumActionType;Ljava/lang/String;ZLjava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)V", "getAttributes", "()Ljava/util/Map;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getName", "()Ljava/lang/String;", "getType", "()Lcom/datadog/android/rum/RumActionType;", "getWaitForStop", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class StartAction extends RumRawEvent {
        private final Map<String, Object> attributes;
        private final Time eventTime;
        private final String name;
        private final RumActionType type;
        private final boolean waitForStop;

        public static /* synthetic */ StartAction copy$default(StartAction startAction, RumActionType rumActionType, String str, boolean z, Map<String, Object> map, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                rumActionType = startAction.type;
            }
            if ((i & 2) != 0) {
                str = startAction.name;
            }
            String str2 = str;
            if ((i & 4) != 0) {
                z = startAction.waitForStop;
            }
            boolean z2 = z;
            if ((i & 8) != 0) {
                map = startAction.attributes;
            }
            Map<String, Object> map2 = map;
            if ((i & 16) != 0) {
                time = startAction.getEventTime();
            }
            return startAction.copy(rumActionType, str2, z2, map2, time);
        }

        public final RumActionType component1() {
            return this.type;
        }

        public final String component2() {
            return this.name;
        }

        public final boolean component3() {
            return this.waitForStop;
        }

        public final Map<String, Object> component4() {
            return this.attributes;
        }

        public final Time component5() {
            return getEventTime();
        }

        public final StartAction copy(RumActionType rumActionType, String str, boolean z, Map<String, ? extends Object> map, Time time) {
            Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new StartAction(rumActionType, str, z, map, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StartAction)) {
                return false;
            }
            StartAction startAction = (StartAction) obj;
            return this.type == startAction.type && Intrinsics.areEqual((Object) this.name, (Object) startAction.name) && this.waitForStop == startAction.waitForStop && Intrinsics.areEqual((Object) this.attributes, (Object) startAction.attributes) && Intrinsics.areEqual((Object) getEventTime(), (Object) startAction.getEventTime());
        }

        public int hashCode() {
            int hashCode = ((this.type.hashCode() * 31) + this.name.hashCode()) * 31;
            boolean z = this.waitForStop;
            if (z) {
                z = true;
            }
            return ((((hashCode + (z ? 1 : 0)) * 31) + this.attributes.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            RumActionType rumActionType = this.type;
            String str = this.name;
            boolean z = this.waitForStop;
            Map<String, Object> map = this.attributes;
            return "StartAction(type=" + rumActionType + ", name=" + str + ", waitForStop=" + z + ", attributes=" + map + ", eventTime=" + getEventTime() + ")";
        }

        public final RumActionType getType() {
            return this.type;
        }

        public final String getName() {
            return this.name;
        }

        public final boolean getWaitForStop() {
            return this.waitForStop;
        }

        public final Map<String, Object> getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StartAction(RumActionType rumActionType, String str, boolean z, Map map, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(rumActionType, str, z, map, (i & 16) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StartAction(RumActionType rumActionType, String str, boolean z, Map<String, ? extends Object> map, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(rumActionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.type = rumActionType;
            this.name = str;
            this.waitForStop = z;
            this.attributes = map;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B9\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\nHÆ\u0003JC\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopAction;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "type", "Lcom/datadog/android/rum/RumActionType;", "name", "", "attributes", "", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Lcom/datadog/android/rum/RumActionType;Ljava/lang/String;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)V", "getAttributes", "()Ljava/util/Map;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getName", "()Ljava/lang/String;", "getType", "()Lcom/datadog/android/rum/RumActionType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class StopAction extends RumRawEvent {
        private final Map<String, Object> attributes;
        private final Time eventTime;
        private final String name;
        private final RumActionType type;

        public static /* synthetic */ StopAction copy$default(StopAction stopAction, RumActionType rumActionType, String str, Map<String, Object> map, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                rumActionType = stopAction.type;
            }
            if ((i & 2) != 0) {
                str = stopAction.name;
            }
            if ((i & 4) != 0) {
                map = stopAction.attributes;
            }
            if ((i & 8) != 0) {
                time = stopAction.getEventTime();
            }
            return stopAction.copy(rumActionType, str, map, time);
        }

        public final RumActionType component1() {
            return this.type;
        }

        public final String component2() {
            return this.name;
        }

        public final Map<String, Object> component3() {
            return this.attributes;
        }

        public final Time component4() {
            return getEventTime();
        }

        public final StopAction copy(RumActionType rumActionType, String str, Map<String, ? extends Object> map, Time time) {
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new StopAction(rumActionType, str, map, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StopAction)) {
                return false;
            }
            StopAction stopAction = (StopAction) obj;
            return this.type == stopAction.type && Intrinsics.areEqual((Object) this.name, (Object) stopAction.name) && Intrinsics.areEqual((Object) this.attributes, (Object) stopAction.attributes) && Intrinsics.areEqual((Object) getEventTime(), (Object) stopAction.getEventTime());
        }

        public int hashCode() {
            RumActionType rumActionType = this.type;
            int i = 0;
            int hashCode = (rumActionType == null ? 0 : rumActionType.hashCode()) * 31;
            String str = this.name;
            if (str != null) {
                i = str.hashCode();
            }
            return ((((hashCode + i) * 31) + this.attributes.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            RumActionType rumActionType = this.type;
            String str = this.name;
            Map<String, Object> map = this.attributes;
            return "StopAction(type=" + rumActionType + ", name=" + str + ", attributes=" + map + ", eventTime=" + getEventTime() + ")";
        }

        public final RumActionType getType() {
            return this.type;
        }

        public final String getName() {
            return this.name;
        }

        public final Map<String, Object> getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StopAction(RumActionType rumActionType, String str, Map map, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(rumActionType, str, map, (i & 8) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StopAction(RumActionType rumActionType, String str, Map<String, ? extends Object> map, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.type = rumActionType;
            this.name = str;
            this.attributes = map;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0017\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\nHÆ\u0003JI\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011¨\u0006 "}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartResource;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "key", "", "url", "method", "attributes", "", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)V", "getAttributes", "()Ljava/util/Map;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getKey", "()Ljava/lang/String;", "getMethod", "getUrl", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class StartResource extends RumRawEvent {
        private final Map<String, Object> attributes;
        private final Time eventTime;
        private final String key;
        private final String method;
        private final String url;

        public static /* synthetic */ StartResource copy$default(StartResource startResource, String str, String str2, String str3, Map<String, Object> map, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = startResource.key;
            }
            if ((i & 2) != 0) {
                str2 = startResource.url;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                str3 = startResource.method;
            }
            String str5 = str3;
            if ((i & 8) != 0) {
                map = startResource.attributes;
            }
            Map<String, Object> map2 = map;
            if ((i & 16) != 0) {
                time = startResource.getEventTime();
            }
            return startResource.copy(str, str4, str5, map2, time);
        }

        public final String component1() {
            return this.key;
        }

        public final String component2() {
            return this.url;
        }

        public final String component3() {
            return this.method;
        }

        public final Map<String, Object> component4() {
            return this.attributes;
        }

        public final Time component5() {
            return getEventTime();
        }

        public final StartResource copy(String str, String str2, String str3, Map<String, ? extends Object> map, Time time) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "url");
            Intrinsics.checkNotNullParameter(str3, "method");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new StartResource(str, str2, str3, map, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StartResource)) {
                return false;
            }
            StartResource startResource = (StartResource) obj;
            return Intrinsics.areEqual((Object) this.key, (Object) startResource.key) && Intrinsics.areEqual((Object) this.url, (Object) startResource.url) && Intrinsics.areEqual((Object) this.method, (Object) startResource.method) && Intrinsics.areEqual((Object) this.attributes, (Object) startResource.attributes) && Intrinsics.areEqual((Object) getEventTime(), (Object) startResource.getEventTime());
        }

        public int hashCode() {
            return (((((((this.key.hashCode() * 31) + this.url.hashCode()) * 31) + this.method.hashCode()) * 31) + this.attributes.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.key;
            String str2 = this.url;
            String str3 = this.method;
            Map<String, Object> map = this.attributes;
            return "StartResource(key=" + str + ", url=" + str2 + ", method=" + str3 + ", attributes=" + map + ", eventTime=" + getEventTime() + ")";
        }

        public final String getKey() {
            return this.key;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getMethod() {
            return this.method;
        }

        public final Map<String, Object> getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StartResource(String str, String str2, String str3, Map map, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, map, (i & 16) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StartResource(String str, String str2, String str3, Map<String, ? extends Object> map, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "url");
            Intrinsics.checkNotNullParameter(str3, "method");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.key = str;
            this.url = str2;
            this.method = str3;
            this.attributes = map;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$WaitForResourceTiming;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "key", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getKey", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class WaitForResourceTiming extends RumRawEvent {
        private final Time eventTime;
        private final String key;

        public static /* synthetic */ WaitForResourceTiming copy$default(WaitForResourceTiming waitForResourceTiming, String str, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = waitForResourceTiming.key;
            }
            if ((i & 2) != 0) {
                time = waitForResourceTiming.getEventTime();
            }
            return waitForResourceTiming.copy(str, time);
        }

        public final String component1() {
            return this.key;
        }

        public final Time component2() {
            return getEventTime();
        }

        public final WaitForResourceTiming copy(String str, Time time) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new WaitForResourceTiming(str, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WaitForResourceTiming)) {
                return false;
            }
            WaitForResourceTiming waitForResourceTiming = (WaitForResourceTiming) obj;
            return Intrinsics.areEqual((Object) this.key, (Object) waitForResourceTiming.key) && Intrinsics.areEqual((Object) getEventTime(), (Object) waitForResourceTiming.getEventTime());
        }

        public int hashCode() {
            return (this.key.hashCode() * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.key;
            return "WaitForResourceTiming(key=" + str + ", eventTime=" + getEventTime() + ")";
        }

        public final String getKey() {
            return this.key;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WaitForResourceTiming(String str, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public WaitForResourceTiming(String str, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.key = str;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddResourceTiming;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "key", "", "timing", "Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getKey", "()Ljava/lang/String;", "getTiming", "()Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class AddResourceTiming extends RumRawEvent {
        private final Time eventTime;
        private final String key;
        private final ResourceTiming timing;

        public static /* synthetic */ AddResourceTiming copy$default(AddResourceTiming addResourceTiming, String str, ResourceTiming resourceTiming, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = addResourceTiming.key;
            }
            if ((i & 2) != 0) {
                resourceTiming = addResourceTiming.timing;
            }
            if ((i & 4) != 0) {
                time = addResourceTiming.getEventTime();
            }
            return addResourceTiming.copy(str, resourceTiming, time);
        }

        public final String component1() {
            return this.key;
        }

        public final ResourceTiming component2() {
            return this.timing;
        }

        public final Time component3() {
            return getEventTime();
        }

        public final AddResourceTiming copy(String str, ResourceTiming resourceTiming, Time time) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(resourceTiming, "timing");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new AddResourceTiming(str, resourceTiming, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AddResourceTiming)) {
                return false;
            }
            AddResourceTiming addResourceTiming = (AddResourceTiming) obj;
            return Intrinsics.areEqual((Object) this.key, (Object) addResourceTiming.key) && Intrinsics.areEqual((Object) this.timing, (Object) addResourceTiming.timing) && Intrinsics.areEqual((Object) getEventTime(), (Object) addResourceTiming.getEventTime());
        }

        public int hashCode() {
            return (((this.key.hashCode() * 31) + this.timing.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.key;
            ResourceTiming resourceTiming = this.timing;
            return "AddResourceTiming(key=" + str + ", timing=" + resourceTiming + ", eventTime=" + getEventTime() + ")";
        }

        public final String getKey() {
            return this.key;
        }

        public final ResourceTiming getTiming() {
            return this.timing;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AddResourceTiming(String str, ResourceTiming resourceTiming, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, resourceTiming, (i & 4) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AddResourceTiming(String str, ResourceTiming resourceTiming, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(resourceTiming, "timing");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.key = str;
            this.timing = resourceTiming;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\u0017\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nHÆ\u0003J\t\u0010 \u001a\u00020\rHÆ\u0003J\\\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001a\u0010\u0018¨\u0006)"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResource;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "key", "", "statusCode", "", "size", "kind", "Lcom/datadog/android/rum/RumResourceKind;", "attributes", "", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Lcom/datadog/android/rum/RumResourceKind;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)V", "getAttributes", "()Ljava/util/Map;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getKey", "()Ljava/lang/String;", "getKind", "()Lcom/datadog/android/rum/RumResourceKind;", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStatusCode", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Lcom/datadog/android/rum/RumResourceKind;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResource;", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class StopResource extends RumRawEvent {
        private final Map<String, Object> attributes;
        private final Time eventTime;
        private final String key;
        private final RumResourceKind kind;
        private final Long size;
        private final Long statusCode;

        public static /* synthetic */ StopResource copy$default(StopResource stopResource, String str, Long l, Long l2, RumResourceKind rumResourceKind, Map<String, Object> map, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = stopResource.key;
            }
            if ((i & 2) != 0) {
                l = stopResource.statusCode;
            }
            Long l3 = l;
            if ((i & 4) != 0) {
                l2 = stopResource.size;
            }
            Long l4 = l2;
            if ((i & 8) != 0) {
                rumResourceKind = stopResource.kind;
            }
            RumResourceKind rumResourceKind2 = rumResourceKind;
            if ((i & 16) != 0) {
                map = stopResource.attributes;
            }
            Map<String, Object> map2 = map;
            if ((i & 32) != 0) {
                time = stopResource.getEventTime();
            }
            return stopResource.copy(str, l3, l4, rumResourceKind2, map2, time);
        }

        public final String component1() {
            return this.key;
        }

        public final Long component2() {
            return this.statusCode;
        }

        public final Long component3() {
            return this.size;
        }

        public final RumResourceKind component4() {
            return this.kind;
        }

        public final Map<String, Object> component5() {
            return this.attributes;
        }

        public final Time component6() {
            return getEventTime();
        }

        public final StopResource copy(String str, Long l, Long l2, RumResourceKind rumResourceKind, Map<String, ? extends Object> map, Time time) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(rumResourceKind, "kind");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new StopResource(str, l, l2, rumResourceKind, map, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StopResource)) {
                return false;
            }
            StopResource stopResource = (StopResource) obj;
            return Intrinsics.areEqual((Object) this.key, (Object) stopResource.key) && Intrinsics.areEqual((Object) this.statusCode, (Object) stopResource.statusCode) && Intrinsics.areEqual((Object) this.size, (Object) stopResource.size) && this.kind == stopResource.kind && Intrinsics.areEqual((Object) this.attributes, (Object) stopResource.attributes) && Intrinsics.areEqual((Object) getEventTime(), (Object) stopResource.getEventTime());
        }

        public int hashCode() {
            int hashCode = this.key.hashCode() * 31;
            Long l = this.statusCode;
            int i = 0;
            int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.size;
            if (l2 != null) {
                i = l2.hashCode();
            }
            return ((((((hashCode2 + i) * 31) + this.kind.hashCode()) * 31) + this.attributes.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.key;
            Long l = this.statusCode;
            Long l2 = this.size;
            RumResourceKind rumResourceKind = this.kind;
            Map<String, Object> map = this.attributes;
            return "StopResource(key=" + str + ", statusCode=" + l + ", size=" + l2 + ", kind=" + rumResourceKind + ", attributes=" + map + ", eventTime=" + getEventTime() + ")";
        }

        public final String getKey() {
            return this.key;
        }

        public final Long getStatusCode() {
            return this.statusCode;
        }

        public final Long getSize() {
            return this.size;
        }

        public final RumResourceKind getKind() {
            return this.kind;
        }

        public final Map<String, Object> getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StopResource(String str, Long l, Long l2, RumResourceKind rumResourceKind, Map map, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, l, l2, rumResourceKind, map, (i & 32) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StopResource(String str, Long l, Long l2, RumResourceKind rumResourceKind, Map<String, ? extends Object> map, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(rumResourceKind, "kind");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.key = str;
            this.statusCode = l;
            this.size = l2;
            this.kind = rumResourceKind;
            this.attributes = map;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001bJ\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J\u0017\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\fHÆ\u0003J\t\u0010%\u001a\u00020\u000fHÆ\u0003Jd\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006."}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResourceWithError;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "key", "", "statusCode", "", "message", "source", "Lcom/datadog/android/rum/RumErrorSource;", "throwable", "", "attributes", "", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/Throwable;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)V", "getAttributes", "()Ljava/util/Map;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getKey", "()Ljava/lang/String;", "getMessage", "getSource", "()Lcom/datadog/android/rum/RumErrorSource;", "getStatusCode", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getThrowable", "()Ljava/lang/Throwable;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/Throwable;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResourceWithError;", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class StopResourceWithError extends RumRawEvent {
        private final Map<String, Object> attributes;
        private final Time eventTime;
        private final String key;
        private final String message;
        private final RumErrorSource source;
        private final Long statusCode;
        private final Throwable throwable;

        public static /* synthetic */ StopResourceWithError copy$default(StopResourceWithError stopResourceWithError, String str, Long l, String str2, RumErrorSource rumErrorSource, Throwable th, Map<String, Object> map, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = stopResourceWithError.key;
            }
            if ((i & 2) != 0) {
                l = stopResourceWithError.statusCode;
            }
            Long l2 = l;
            if ((i & 4) != 0) {
                str2 = stopResourceWithError.message;
            }
            String str3 = str2;
            if ((i & 8) != 0) {
                rumErrorSource = stopResourceWithError.source;
            }
            RumErrorSource rumErrorSource2 = rumErrorSource;
            if ((i & 16) != 0) {
                th = stopResourceWithError.throwable;
            }
            Throwable th2 = th;
            if ((i & 32) != 0) {
                map = stopResourceWithError.attributes;
            }
            Map<String, Object> map2 = map;
            if ((i & 64) != 0) {
                time = stopResourceWithError.getEventTime();
            }
            return stopResourceWithError.copy(str, l2, str3, rumErrorSource2, th2, map2, time);
        }

        public final String component1() {
            return this.key;
        }

        public final Long component2() {
            return this.statusCode;
        }

        public final String component3() {
            return this.message;
        }

        public final RumErrorSource component4() {
            return this.source;
        }

        public final Throwable component5() {
            return this.throwable;
        }

        public final Map<String, Object> component6() {
            return this.attributes;
        }

        public final Time component7() {
            return getEventTime();
        }

        public final StopResourceWithError copy(String str, Long l, String str2, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map, Time time) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(th, "throwable");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new StopResourceWithError(str, l, str2, rumErrorSource, th, map, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StopResourceWithError)) {
                return false;
            }
            StopResourceWithError stopResourceWithError = (StopResourceWithError) obj;
            return Intrinsics.areEqual((Object) this.key, (Object) stopResourceWithError.key) && Intrinsics.areEqual((Object) this.statusCode, (Object) stopResourceWithError.statusCode) && Intrinsics.areEqual((Object) this.message, (Object) stopResourceWithError.message) && this.source == stopResourceWithError.source && Intrinsics.areEqual((Object) this.throwable, (Object) stopResourceWithError.throwable) && Intrinsics.areEqual((Object) this.attributes, (Object) stopResourceWithError.attributes) && Intrinsics.areEqual((Object) getEventTime(), (Object) stopResourceWithError.getEventTime());
        }

        public int hashCode() {
            int hashCode = this.key.hashCode() * 31;
            Long l = this.statusCode;
            return ((((((((((hashCode + (l == null ? 0 : l.hashCode())) * 31) + this.message.hashCode()) * 31) + this.source.hashCode()) * 31) + this.throwable.hashCode()) * 31) + this.attributes.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.key;
            Long l = this.statusCode;
            String str2 = this.message;
            RumErrorSource rumErrorSource = this.source;
            Throwable th = this.throwable;
            Map<String, Object> map = this.attributes;
            return "StopResourceWithError(key=" + str + ", statusCode=" + l + ", message=" + str2 + ", source=" + rumErrorSource + ", throwable=" + th + ", attributes=" + map + ", eventTime=" + getEventTime() + ")";
        }

        public final String getKey() {
            return this.key;
        }

        public final Long getStatusCode() {
            return this.statusCode;
        }

        public final String getMessage() {
            return this.message;
        }

        public final RumErrorSource getSource() {
            return this.source;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }

        public final Map<String, Object> getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StopResourceWithError(String str, Long l, String str2, RumErrorSource rumErrorSource, Throwable th, Map map, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, l, str2, rumErrorSource, th, map, (i & 64) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StopResourceWithError(String str, Long l, String str2, RumErrorSource rumErrorSource, Throwable th, Map<String, ? extends Object> map, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(th, "throwable");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.key = str;
            this.statusCode = l;
            this.message = str2;
            this.source = rumErrorSource;
            this.throwable = th;
            this.attributes = map;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\fHÆ\u0003J\t\u0010&\u001a\u00020\u000fHÆ\u0003Jp\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001¢\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001d¨\u0006/"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResourceWithStackTrace;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "key", "", "statusCode", "", "message", "source", "Lcom/datadog/android/rum/RumErrorSource;", "stackTrace", "errorType", "attributes", "", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)V", "getAttributes", "()Ljava/util/Map;", "getErrorType", "()Ljava/lang/String;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getKey", "getMessage", "getSource", "()Lcom/datadog/android/rum/RumErrorSource;", "getStackTrace", "getStatusCode", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;)Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResourceWithStackTrace;", "equals", "", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class StopResourceWithStackTrace extends RumRawEvent {
        private final Map<String, Object> attributes;
        private final String errorType;
        private final Time eventTime;
        private final String key;
        private final String message;
        private final RumErrorSource source;
        private final String stackTrace;
        private final Long statusCode;

        public static /* synthetic */ StopResourceWithStackTrace copy$default(StopResourceWithStackTrace stopResourceWithStackTrace, String str, Long l, String str2, RumErrorSource rumErrorSource, String str3, String str4, Map map, Time time, int i, Object obj) {
            StopResourceWithStackTrace stopResourceWithStackTrace2 = stopResourceWithStackTrace;
            int i2 = i;
            return stopResourceWithStackTrace.copy((i2 & 1) != 0 ? stopResourceWithStackTrace2.key : str, (i2 & 2) != 0 ? stopResourceWithStackTrace2.statusCode : l, (i2 & 4) != 0 ? stopResourceWithStackTrace2.message : str2, (i2 & 8) != 0 ? stopResourceWithStackTrace2.source : rumErrorSource, (i2 & 16) != 0 ? stopResourceWithStackTrace2.stackTrace : str3, (i2 & 32) != 0 ? stopResourceWithStackTrace2.errorType : str4, (i2 & 64) != 0 ? stopResourceWithStackTrace2.attributes : map, (i2 & 128) != 0 ? stopResourceWithStackTrace.getEventTime() : time);
        }

        public final String component1() {
            return this.key;
        }

        public final Long component2() {
            return this.statusCode;
        }

        public final String component3() {
            return this.message;
        }

        public final RumErrorSource component4() {
            return this.source;
        }

        public final String component5() {
            return this.stackTrace;
        }

        public final String component6() {
            return this.errorType;
        }

        public final Map<String, Object> component7() {
            return this.attributes;
        }

        public final Time component8() {
            return getEventTime();
        }

        public final StopResourceWithStackTrace copy(String str, Long l, String str2, RumErrorSource rumErrorSource, String str3, String str4, Map<String, ? extends Object> map, Time time) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(str3, "stackTrace");
            Map<String, ? extends Object> map2 = map;
            Intrinsics.checkNotNullParameter(map2, "attributes");
            Time time2 = time;
            Intrinsics.checkNotNullParameter(time2, "eventTime");
            return new StopResourceWithStackTrace(str, l, str2, rumErrorSource, str3, str4, map2, time2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StopResourceWithStackTrace)) {
                return false;
            }
            StopResourceWithStackTrace stopResourceWithStackTrace = (StopResourceWithStackTrace) obj;
            return Intrinsics.areEqual((Object) this.key, (Object) stopResourceWithStackTrace.key) && Intrinsics.areEqual((Object) this.statusCode, (Object) stopResourceWithStackTrace.statusCode) && Intrinsics.areEqual((Object) this.message, (Object) stopResourceWithStackTrace.message) && this.source == stopResourceWithStackTrace.source && Intrinsics.areEqual((Object) this.stackTrace, (Object) stopResourceWithStackTrace.stackTrace) && Intrinsics.areEqual((Object) this.errorType, (Object) stopResourceWithStackTrace.errorType) && Intrinsics.areEqual((Object) this.attributes, (Object) stopResourceWithStackTrace.attributes) && Intrinsics.areEqual((Object) getEventTime(), (Object) stopResourceWithStackTrace.getEventTime());
        }

        public int hashCode() {
            int hashCode = this.key.hashCode() * 31;
            Long l = this.statusCode;
            int i = 0;
            int hashCode2 = (((((((hashCode + (l == null ? 0 : l.hashCode())) * 31) + this.message.hashCode()) * 31) + this.source.hashCode()) * 31) + this.stackTrace.hashCode()) * 31;
            String str = this.errorType;
            if (str != null) {
                i = str.hashCode();
            }
            return ((((hashCode2 + i) * 31) + this.attributes.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.key;
            Long l = this.statusCode;
            String str2 = this.message;
            RumErrorSource rumErrorSource = this.source;
            String str3 = this.stackTrace;
            String str4 = this.errorType;
            Map<String, Object> map = this.attributes;
            return "StopResourceWithStackTrace(key=" + str + ", statusCode=" + l + ", message=" + str2 + ", source=" + rumErrorSource + ", stackTrace=" + str3 + ", errorType=" + str4 + ", attributes=" + map + ", eventTime=" + getEventTime() + ")";
        }

        public final String getKey() {
            return this.key;
        }

        public final Long getStatusCode() {
            return this.statusCode;
        }

        public final String getMessage() {
            return this.message;
        }

        public final RumErrorSource getSource() {
            return this.source;
        }

        public final String getStackTrace() {
            return this.stackTrace;
        }

        public final String getErrorType() {
            return this.errorType;
        }

        public final Map<String, Object> getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StopResourceWithStackTrace(String str, Long l, String str2, RumErrorSource rumErrorSource, String str3, String str4, Map map, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, l, str2, rumErrorSource, str3, str4, map, (i & 128) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StopResourceWithStackTrace(String str, Long l, String str2, RumErrorSource rumErrorSource, String str3, String str4, Map<String, ? extends Object> map, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(str3, "stackTrace");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.key = str;
            this.statusCode = l;
            this.message = str2;
            this.source = rumErrorSource;
            this.stackTrace = str3;
            this.errorType = str4;
            this.attributes = map;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010'\u001a\u00020\nHÆ\u0003J\u0017\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\fHÆ\u0003J\t\u0010)\u001a\u00020\u000fHÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0012HÆ\u0003Jw\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001J\u0013\u0010-\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001a¨\u00062"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddError;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "message", "", "source", "Lcom/datadog/android/rum/RumErrorSource;", "throwable", "", "stacktrace", "isFatal", "", "attributes", "", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "type", "sourceType", "Lcom/datadog/android/rum/internal/RumErrorSourceType;", "(Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/Throwable;Ljava/lang/String;ZLjava/util/Map;Lcom/datadog/android/rum/internal/domain/Time;Ljava/lang/String;Lcom/datadog/android/rum/internal/RumErrorSourceType;)V", "getAttributes", "()Ljava/util/Map;", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "()Z", "getMessage", "()Ljava/lang/String;", "getSource", "()Lcom/datadog/android/rum/RumErrorSource;", "getSourceType", "()Lcom/datadog/android/rum/internal/RumErrorSourceType;", "getStacktrace", "getThrowable", "()Ljava/lang/Throwable;", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class AddError extends RumRawEvent {
        private final Map<String, Object> attributes;
        private final Time eventTime;
        private final boolean isFatal;
        private final String message;
        private final RumErrorSource source;
        private final RumErrorSourceType sourceType;
        private final String stacktrace;
        private final Throwable throwable;
        private final String type;

        public static /* synthetic */ AddError copy$default(AddError addError, String str, RumErrorSource rumErrorSource, Throwable th, String str2, boolean z, Map map, Time time, String str3, RumErrorSourceType rumErrorSourceType, int i, Object obj) {
            AddError addError2 = addError;
            int i2 = i;
            return addError.copy((i2 & 1) != 0 ? addError2.message : str, (i2 & 2) != 0 ? addError2.source : rumErrorSource, (i2 & 4) != 0 ? addError2.throwable : th, (i2 & 8) != 0 ? addError2.stacktrace : str2, (i2 & 16) != 0 ? addError2.isFatal : z, (i2 & 32) != 0 ? addError2.attributes : map, (i2 & 64) != 0 ? addError.getEventTime() : time, (i2 & 128) != 0 ? addError2.type : str3, (i2 & 256) != 0 ? addError2.sourceType : rumErrorSourceType);
        }

        public final String component1() {
            return this.message;
        }

        public final RumErrorSource component2() {
            return this.source;
        }

        public final Throwable component3() {
            return this.throwable;
        }

        public final String component4() {
            return this.stacktrace;
        }

        public final boolean component5() {
            return this.isFatal;
        }

        public final Map<String, Object> component6() {
            return this.attributes;
        }

        public final Time component7() {
            return getEventTime();
        }

        public final String component8() {
            return this.type;
        }

        public final RumErrorSourceType component9() {
            return this.sourceType;
        }

        public final AddError copy(String str, RumErrorSource rumErrorSource, Throwable th, String str2, boolean z, Map<String, ? extends Object> map, Time time, String str3, RumErrorSourceType rumErrorSourceType) {
            Intrinsics.checkNotNullParameter(str, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Map<String, ? extends Object> map2 = map;
            Intrinsics.checkNotNullParameter(map2, "attributes");
            Time time2 = time;
            Intrinsics.checkNotNullParameter(time2, "eventTime");
            RumErrorSourceType rumErrorSourceType2 = rumErrorSourceType;
            Intrinsics.checkNotNullParameter(rumErrorSourceType2, "sourceType");
            return new AddError(str, rumErrorSource, th, str2, z, map2, time2, str3, rumErrorSourceType2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AddError)) {
                return false;
            }
            AddError addError = (AddError) obj;
            return Intrinsics.areEqual((Object) this.message, (Object) addError.message) && this.source == addError.source && Intrinsics.areEqual((Object) this.throwable, (Object) addError.throwable) && Intrinsics.areEqual((Object) this.stacktrace, (Object) addError.stacktrace) && this.isFatal == addError.isFatal && Intrinsics.areEqual((Object) this.attributes, (Object) addError.attributes) && Intrinsics.areEqual((Object) getEventTime(), (Object) addError.getEventTime()) && Intrinsics.areEqual((Object) this.type, (Object) addError.type) && this.sourceType == addError.sourceType;
        }

        public int hashCode() {
            int hashCode = ((this.message.hashCode() * 31) + this.source.hashCode()) * 31;
            Throwable th = this.throwable;
            int i = 0;
            int hashCode2 = (hashCode + (th == null ? 0 : th.hashCode())) * 31;
            String str = this.stacktrace;
            int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            boolean z = this.isFatal;
            if (z) {
                z = true;
            }
            int hashCode4 = (((((hashCode3 + (z ? 1 : 0)) * 31) + this.attributes.hashCode()) * 31) + getEventTime().hashCode()) * 31;
            String str2 = this.type;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return ((hashCode4 + i) * 31) + this.sourceType.hashCode();
        }

        public String toString() {
            String str = this.message;
            RumErrorSource rumErrorSource = this.source;
            Throwable th = this.throwable;
            String str2 = this.stacktrace;
            boolean z = this.isFatal;
            Map<String, Object> map = this.attributes;
            Time eventTime2 = getEventTime();
            String str3 = this.type;
            return "AddError(message=" + str + ", source=" + rumErrorSource + ", throwable=" + th + ", stacktrace=" + str2 + ", isFatal=" + z + ", attributes=" + map + ", eventTime=" + eventTime2 + ", type=" + str3 + ", sourceType=" + this.sourceType + ")";
        }

        public final String getMessage() {
            return this.message;
        }

        public final RumErrorSource getSource() {
            return this.source;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }

        public final String getStacktrace() {
            return this.stacktrace;
        }

        public final boolean isFatal() {
            return this.isFatal;
        }

        public final Map<String, Object> getAttributes() {
            return this.attributes;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ AddError(java.lang.String r20, com.datadog.android.rum.RumErrorSource r21, java.lang.Throwable r22, java.lang.String r23, boolean r24, java.util.Map r25, com.datadog.android.rum.internal.domain.Time r26, java.lang.String r27, com.datadog.android.rum.internal.RumErrorSourceType r28, int r29, kotlin.jvm.internal.DefaultConstructorMarker r30) {
            /*
                r19 = this;
                r0 = r29
                r1 = r0 & 64
                if (r1 == 0) goto L_0x0015
                com.datadog.android.rum.internal.domain.Time r1 = new com.datadog.android.rum.internal.domain.Time
                r3 = 0
                r5 = 0
                r7 = 3
                r8 = 0
                r2 = r1
                r2.<init>(r3, r5, r7, r8)
                r16 = r1
                goto L_0x0017
            L_0x0015:
                r16 = r26
            L_0x0017:
                r1 = r0 & 128(0x80, float:1.794E-43)
                if (r1 == 0) goto L_0x001f
                r1 = 0
                r17 = r1
                goto L_0x0021
            L_0x001f:
                r17 = r27
            L_0x0021:
                r0 = r0 & 256(0x100, float:3.59E-43)
                if (r0 == 0) goto L_0x002a
                com.datadog.android.rum.internal.RumErrorSourceType r0 = com.datadog.android.rum.internal.RumErrorSourceType.ANDROID
                r18 = r0
                goto L_0x002c
            L_0x002a:
                r18 = r28
            L_0x002c:
                r9 = r19
                r10 = r20
                r11 = r21
                r12 = r22
                r13 = r23
                r14 = r24
                r15 = r25
                r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.domain.scope.RumRawEvent.AddError.<init>(java.lang.String, com.datadog.android.rum.RumErrorSource, java.lang.Throwable, java.lang.String, boolean, java.util.Map, com.datadog.android.rum.internal.domain.Time, java.lang.String, com.datadog.android.rum.internal.RumErrorSourceType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        public final String getType() {
            return this.type;
        }

        public final RumErrorSourceType getSourceType() {
            return this.sourceType;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AddError(String str, RumErrorSource rumErrorSource, Throwable th, String str2, boolean z, Map<String, ? extends Object> map, Time time, String str3, RumErrorSourceType rumErrorSourceType) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "message");
            Intrinsics.checkNotNullParameter(rumErrorSource, "source");
            Intrinsics.checkNotNullParameter(map, "attributes");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            Intrinsics.checkNotNullParameter(rumErrorSourceType, "sourceType");
            this.message = str;
            this.source = rumErrorSource;
            this.throwable = th;
            this.stacktrace = str2;
            this.isFatal = z;
            this.attributes = map;
            this.eventTime = time;
            this.type = str3;
            this.sourceType = rumErrorSourceType;
        }
    }

    @Metadata(mo20734d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$UpdateViewLoadingTime;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "key", "", "loadingTime", "", "loadingType", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/Object;JLcom/datadog/android/rum/model/ViewEvent$LoadingType;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getKey", "()Ljava/lang/Object;", "getLoadingTime", "()J", "getLoadingType", "()Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class UpdateViewLoadingTime extends RumRawEvent {
        private final Time eventTime;
        private final Object key;
        private final long loadingTime;
        private final ViewEvent.LoadingType loadingType;

        public static /* synthetic */ UpdateViewLoadingTime copy$default(UpdateViewLoadingTime updateViewLoadingTime, Object obj, long j, ViewEvent.LoadingType loadingType2, Time time, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = updateViewLoadingTime.key;
            }
            if ((i & 2) != 0) {
                j = updateViewLoadingTime.loadingTime;
            }
            long j2 = j;
            if ((i & 4) != 0) {
                loadingType2 = updateViewLoadingTime.loadingType;
            }
            ViewEvent.LoadingType loadingType3 = loadingType2;
            if ((i & 8) != 0) {
                time = updateViewLoadingTime.getEventTime();
            }
            return updateViewLoadingTime.copy(obj, j2, loadingType3, time);
        }

        public final Object component1() {
            return this.key;
        }

        public final long component2() {
            return this.loadingTime;
        }

        public final ViewEvent.LoadingType component3() {
            return this.loadingType;
        }

        public final Time component4() {
            return getEventTime();
        }

        public final UpdateViewLoadingTime copy(Object obj, long j, ViewEvent.LoadingType loadingType2, Time time) {
            Intrinsics.checkNotNullParameter(obj, "key");
            Intrinsics.checkNotNullParameter(loadingType2, "loadingType");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new UpdateViewLoadingTime(obj, j, loadingType2, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UpdateViewLoadingTime)) {
                return false;
            }
            UpdateViewLoadingTime updateViewLoadingTime = (UpdateViewLoadingTime) obj;
            return Intrinsics.areEqual(this.key, updateViewLoadingTime.key) && this.loadingTime == updateViewLoadingTime.loadingTime && this.loadingType == updateViewLoadingTime.loadingType && Intrinsics.areEqual((Object) getEventTime(), (Object) updateViewLoadingTime.getEventTime());
        }

        public int hashCode() {
            return (((((this.key.hashCode() * 31) + Long.hashCode(this.loadingTime)) * 31) + this.loadingType.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            Object obj = this.key;
            long j = this.loadingTime;
            ViewEvent.LoadingType loadingType2 = this.loadingType;
            return "UpdateViewLoadingTime(key=" + obj + ", loadingTime=" + j + ", loadingType=" + loadingType2 + ", eventTime=" + getEventTime() + ")";
        }

        public final Object getKey() {
            return this.key;
        }

        public final long getLoadingTime() {
            return this.loadingTime;
        }

        public final ViewEvent.LoadingType getLoadingType() {
            return this.loadingType;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ UpdateViewLoadingTime(Object obj, long j, ViewEvent.LoadingType loadingType2, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, j, loadingType2, (i & 8) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UpdateViewLoadingTime(Object obj, long j, ViewEvent.LoadingType loadingType2, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(obj, "key");
            Intrinsics.checkNotNullParameter(loadingType2, "loadingType");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.key = obj;
            this.loadingTime = j;
            this.loadingType = loadingType2;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ResourceSent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "viewId", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getViewId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class ResourceSent extends RumRawEvent {
        private final Time eventTime;
        private final String viewId;

        public static /* synthetic */ ResourceSent copy$default(ResourceSent resourceSent, String str, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = resourceSent.viewId;
            }
            if ((i & 2) != 0) {
                time = resourceSent.getEventTime();
            }
            return resourceSent.copy(str, time);
        }

        public final String component1() {
            return this.viewId;
        }

        public final Time component2() {
            return getEventTime();
        }

        public final ResourceSent copy(String str, Time time) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new ResourceSent(str, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ResourceSent)) {
                return false;
            }
            ResourceSent resourceSent = (ResourceSent) obj;
            return Intrinsics.areEqual((Object) this.viewId, (Object) resourceSent.viewId) && Intrinsics.areEqual((Object) getEventTime(), (Object) resourceSent.getEventTime());
        }

        public int hashCode() {
            return (this.viewId.hashCode() * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.viewId;
            return "ResourceSent(viewId=" + str + ", eventTime=" + getEventTime() + ")";
        }

        public final String getViewId() {
            return this.viewId;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ResourceSent(String str, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ResourceSent(String str, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.viewId = str;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ActionSent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "viewId", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getViewId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class ActionSent extends RumRawEvent {
        private final Time eventTime;
        private final String viewId;

        public static /* synthetic */ ActionSent copy$default(ActionSent actionSent, String str, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionSent.viewId;
            }
            if ((i & 2) != 0) {
                time = actionSent.getEventTime();
            }
            return actionSent.copy(str, time);
        }

        public final String component1() {
            return this.viewId;
        }

        public final Time component2() {
            return getEventTime();
        }

        public final ActionSent copy(String str, Time time) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new ActionSent(str, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ActionSent)) {
                return false;
            }
            ActionSent actionSent = (ActionSent) obj;
            return Intrinsics.areEqual((Object) this.viewId, (Object) actionSent.viewId) && Intrinsics.areEqual((Object) getEventTime(), (Object) actionSent.getEventTime());
        }

        public int hashCode() {
            return (this.viewId.hashCode() * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.viewId;
            return "ActionSent(viewId=" + str + ", eventTime=" + getEventTime() + ")";
        }

        public final String getViewId() {
            return this.viewId;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ActionSent(String str, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ActionSent(String str, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.viewId = str;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ErrorSent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "viewId", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getViewId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class ErrorSent extends RumRawEvent {
        private final Time eventTime;
        private final String viewId;

        public static /* synthetic */ ErrorSent copy$default(ErrorSent errorSent, String str, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = errorSent.viewId;
            }
            if ((i & 2) != 0) {
                time = errorSent.getEventTime();
            }
            return errorSent.copy(str, time);
        }

        public final String component1() {
            return this.viewId;
        }

        public final Time component2() {
            return getEventTime();
        }

        public final ErrorSent copy(String str, Time time) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new ErrorSent(str, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ErrorSent)) {
                return false;
            }
            ErrorSent errorSent = (ErrorSent) obj;
            return Intrinsics.areEqual((Object) this.viewId, (Object) errorSent.viewId) && Intrinsics.areEqual((Object) getEventTime(), (Object) errorSent.getEventTime());
        }

        public int hashCode() {
            return (this.viewId.hashCode() * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.viewId;
            return "ErrorSent(viewId=" + str + ", eventTime=" + getEventTime() + ")";
        }

        public final String getViewId() {
            return this.viewId;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ErrorSent(String str, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ErrorSent(String str, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.viewId = str;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$LongTaskSent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "viewId", "", "isFrozenFrame", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;ZLcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "()Z", "getViewId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class LongTaskSent extends RumRawEvent {
        private final Time eventTime;
        private final boolean isFrozenFrame;
        private final String viewId;

        public static /* synthetic */ LongTaskSent copy$default(LongTaskSent longTaskSent, String str, boolean z, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = longTaskSent.viewId;
            }
            if ((i & 2) != 0) {
                z = longTaskSent.isFrozenFrame;
            }
            if ((i & 4) != 0) {
                time = longTaskSent.getEventTime();
            }
            return longTaskSent.copy(str, z, time);
        }

        public final String component1() {
            return this.viewId;
        }

        public final boolean component2() {
            return this.isFrozenFrame;
        }

        public final Time component3() {
            return getEventTime();
        }

        public final LongTaskSent copy(String str, boolean z, Time time) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new LongTaskSent(str, z, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LongTaskSent)) {
                return false;
            }
            LongTaskSent longTaskSent = (LongTaskSent) obj;
            return Intrinsics.areEqual((Object) this.viewId, (Object) longTaskSent.viewId) && this.isFrozenFrame == longTaskSent.isFrozenFrame && Intrinsics.areEqual((Object) getEventTime(), (Object) longTaskSent.getEventTime());
        }

        public int hashCode() {
            int hashCode = this.viewId.hashCode() * 31;
            boolean z = this.isFrozenFrame;
            if (z) {
                z = true;
            }
            return ((hashCode + (z ? 1 : 0)) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.viewId;
            boolean z = this.isFrozenFrame;
            return "LongTaskSent(viewId=" + str + ", isFrozenFrame=" + z + ", eventTime=" + getEventTime() + ")";
        }

        public final String getViewId() {
            return this.viewId;
        }

        public final boolean isFrozenFrame() {
            return this.isFrozenFrame;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LongTaskSent(String str, boolean z, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LongTaskSent(String str, boolean z, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.viewId = str;
            this.isFrozenFrame = z;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ResourceDropped;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "viewId", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getViewId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class ResourceDropped extends RumRawEvent {
        private final Time eventTime;
        private final String viewId;

        public static /* synthetic */ ResourceDropped copy$default(ResourceDropped resourceDropped, String str, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = resourceDropped.viewId;
            }
            if ((i & 2) != 0) {
                time = resourceDropped.getEventTime();
            }
            return resourceDropped.copy(str, time);
        }

        public final String component1() {
            return this.viewId;
        }

        public final Time component2() {
            return getEventTime();
        }

        public final ResourceDropped copy(String str, Time time) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new ResourceDropped(str, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ResourceDropped)) {
                return false;
            }
            ResourceDropped resourceDropped = (ResourceDropped) obj;
            return Intrinsics.areEqual((Object) this.viewId, (Object) resourceDropped.viewId) && Intrinsics.areEqual((Object) getEventTime(), (Object) resourceDropped.getEventTime());
        }

        public int hashCode() {
            return (this.viewId.hashCode() * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.viewId;
            return "ResourceDropped(viewId=" + str + ", eventTime=" + getEventTime() + ")";
        }

        public final String getViewId() {
            return this.viewId;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ResourceDropped(String str, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ResourceDropped(String str, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.viewId = str;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ActionDropped;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "viewId", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getViewId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class ActionDropped extends RumRawEvent {
        private final Time eventTime;
        private final String viewId;

        public static /* synthetic */ ActionDropped copy$default(ActionDropped actionDropped, String str, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionDropped.viewId;
            }
            if ((i & 2) != 0) {
                time = actionDropped.getEventTime();
            }
            return actionDropped.copy(str, time);
        }

        public final String component1() {
            return this.viewId;
        }

        public final Time component2() {
            return getEventTime();
        }

        public final ActionDropped copy(String str, Time time) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new ActionDropped(str, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ActionDropped)) {
                return false;
            }
            ActionDropped actionDropped = (ActionDropped) obj;
            return Intrinsics.areEqual((Object) this.viewId, (Object) actionDropped.viewId) && Intrinsics.areEqual((Object) getEventTime(), (Object) actionDropped.getEventTime());
        }

        public int hashCode() {
            return (this.viewId.hashCode() * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.viewId;
            return "ActionDropped(viewId=" + str + ", eventTime=" + getEventTime() + ")";
        }

        public final String getViewId() {
            return this.viewId;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ActionDropped(String str, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ActionDropped(String str, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.viewId = str;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ErrorDropped;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "viewId", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getViewId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class ErrorDropped extends RumRawEvent {
        private final Time eventTime;
        private final String viewId;

        public static /* synthetic */ ErrorDropped copy$default(ErrorDropped errorDropped, String str, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = errorDropped.viewId;
            }
            if ((i & 2) != 0) {
                time = errorDropped.getEventTime();
            }
            return errorDropped.copy(str, time);
        }

        public final String component1() {
            return this.viewId;
        }

        public final Time component2() {
            return getEventTime();
        }

        public final ErrorDropped copy(String str, Time time) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new ErrorDropped(str, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ErrorDropped)) {
                return false;
            }
            ErrorDropped errorDropped = (ErrorDropped) obj;
            return Intrinsics.areEqual((Object) this.viewId, (Object) errorDropped.viewId) && Intrinsics.areEqual((Object) getEventTime(), (Object) errorDropped.getEventTime());
        }

        public int hashCode() {
            return (this.viewId.hashCode() * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.viewId;
            return "ErrorDropped(viewId=" + str + ", eventTime=" + getEventTime() + ")";
        }

        public final String getViewId() {
            return this.viewId;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ErrorDropped(String str, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ErrorDropped(String str, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.viewId = str;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$LongTaskDropped;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "viewId", "", "isFrozenFrame", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;ZLcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "()Z", "getViewId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class LongTaskDropped extends RumRawEvent {
        private final Time eventTime;
        private final boolean isFrozenFrame;
        private final String viewId;

        public static /* synthetic */ LongTaskDropped copy$default(LongTaskDropped longTaskDropped, String str, boolean z, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = longTaskDropped.viewId;
            }
            if ((i & 2) != 0) {
                z = longTaskDropped.isFrozenFrame;
            }
            if ((i & 4) != 0) {
                time = longTaskDropped.getEventTime();
            }
            return longTaskDropped.copy(str, z, time);
        }

        public final String component1() {
            return this.viewId;
        }

        public final boolean component2() {
            return this.isFrozenFrame;
        }

        public final Time component3() {
            return getEventTime();
        }

        public final LongTaskDropped copy(String str, boolean z, Time time) {
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new LongTaskDropped(str, z, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LongTaskDropped)) {
                return false;
            }
            LongTaskDropped longTaskDropped = (LongTaskDropped) obj;
            return Intrinsics.areEqual((Object) this.viewId, (Object) longTaskDropped.viewId) && this.isFrozenFrame == longTaskDropped.isFrozenFrame && Intrinsics.areEqual((Object) getEventTime(), (Object) longTaskDropped.getEventTime());
        }

        public int hashCode() {
            int hashCode = this.viewId.hashCode() * 31;
            boolean z = this.isFrozenFrame;
            if (z) {
                z = true;
            }
            return ((hashCode + (z ? 1 : 0)) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.viewId;
            boolean z = this.isFrozenFrame;
            return "LongTaskDropped(viewId=" + str + ", isFrozenFrame=" + z + ", eventTime=" + getEventTime() + ")";
        }

        public final String getViewId() {
            return this.viewId;
        }

        public final boolean isFrozenFrame() {
            return this.isFrozenFrame;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LongTaskDropped(String str, boolean z, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LongTaskDropped(String str, boolean z, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "viewId");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.viewId = str;
            this.isFrozenFrame = z;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ResetSession;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class ResetSession extends RumRawEvent {
        private final Time eventTime;

        public ResetSession() {
            this((Time) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ ResetSession copy$default(ResetSession resetSession, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                time = resetSession.getEventTime();
            }
            return resetSession.copy(time);
        }

        public final Time component1() {
            return getEventTime();
        }

        public final ResetSession copy(Time time) {
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new ResetSession(time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ResetSession) && Intrinsics.areEqual((Object) getEventTime(), (Object) ((ResetSession) obj).getEventTime());
        }

        public int hashCode() {
            return getEventTime().hashCode();
        }

        public String toString() {
            return "ResetSession(eventTime=" + getEventTime() + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ResetSession(Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ResetSession(Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$KeepAlive;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class KeepAlive extends RumRawEvent {
        private final Time eventTime;

        public KeepAlive() {
            this((Time) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ KeepAlive copy$default(KeepAlive keepAlive, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                time = keepAlive.getEventTime();
            }
            return keepAlive.copy(time);
        }

        public final Time component1() {
            return getEventTime();
        }

        public final KeepAlive copy(Time time) {
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new KeepAlive(time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof KeepAlive) && Intrinsics.areEqual((Object) getEventTime(), (Object) ((KeepAlive) obj).getEventTime());
        }

        public int hashCode() {
            return getEventTime().hashCode();
        }

        public String toString() {
            return "KeepAlive(eventTime=" + getEventTime() + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ KeepAlive(Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public KeepAlive(Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ApplicationStarted;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "applicationStartupNanos", "", "(Lcom/datadog/android/rum/internal/domain/Time;J)V", "getApplicationStartupNanos", "()J", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class ApplicationStarted extends RumRawEvent {
        private final long applicationStartupNanos;
        private final Time eventTime;

        public static /* synthetic */ ApplicationStarted copy$default(ApplicationStarted applicationStarted, Time time, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                time = applicationStarted.getEventTime();
            }
            if ((i & 2) != 0) {
                j = applicationStarted.applicationStartupNanos;
            }
            return applicationStarted.copy(time, j);
        }

        public final Time component1() {
            return getEventTime();
        }

        public final long component2() {
            return this.applicationStartupNanos;
        }

        public final ApplicationStarted copy(Time time, long j) {
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new ApplicationStarted(time, j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ApplicationStarted)) {
                return false;
            }
            ApplicationStarted applicationStarted = (ApplicationStarted) obj;
            return Intrinsics.areEqual((Object) getEventTime(), (Object) applicationStarted.getEventTime()) && this.applicationStartupNanos == applicationStarted.applicationStartupNanos;
        }

        public int hashCode() {
            return (getEventTime().hashCode() * 31) + Long.hashCode(this.applicationStartupNanos);
        }

        public String toString() {
            Time eventTime2 = getEventTime();
            return "ApplicationStarted(eventTime=" + eventTime2 + ", applicationStartupNanos=" + this.applicationStartupNanos + ")";
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        public final long getApplicationStartupNanos() {
            return this.applicationStartupNanos;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ApplicationStarted(Time time, long j) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.eventTime = time;
            this.applicationStartupNanos = j;
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddCustomTiming;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "name", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class AddCustomTiming extends RumRawEvent {
        private final Time eventTime;
        private final String name;

        public static /* synthetic */ AddCustomTiming copy$default(AddCustomTiming addCustomTiming, String str, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                str = addCustomTiming.name;
            }
            if ((i & 2) != 0) {
                time = addCustomTiming.getEventTime();
            }
            return addCustomTiming.copy(str, time);
        }

        public final String component1() {
            return this.name;
        }

        public final Time component2() {
            return getEventTime();
        }

        public final AddCustomTiming copy(String str, Time time) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new AddCustomTiming(str, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AddCustomTiming)) {
                return false;
            }
            AddCustomTiming addCustomTiming = (AddCustomTiming) obj;
            return Intrinsics.areEqual((Object) this.name, (Object) addCustomTiming.name) && Intrinsics.areEqual((Object) getEventTime(), (Object) addCustomTiming.getEventTime());
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + getEventTime().hashCode();
        }

        public String toString() {
            String str = this.name;
            return "AddCustomTiming(name=" + str + ", eventTime=" + getEventTime() + ")";
        }

        public final String getName() {
            return this.name;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AddCustomTiming(String str, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AddCustomTiming(String str, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.name = str;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddLongTask;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "durationNs", "", "target", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(JLjava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;)V", "getDurationNs", "()J", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getTarget", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class AddLongTask extends RumRawEvent {
        private final long durationNs;
        private final Time eventTime;
        private final String target;

        public static /* synthetic */ AddLongTask copy$default(AddLongTask addLongTask, long j, String str, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                j = addLongTask.durationNs;
            }
            if ((i & 2) != 0) {
                str = addLongTask.target;
            }
            if ((i & 4) != 0) {
                time = addLongTask.getEventTime();
            }
            return addLongTask.copy(j, str, time);
        }

        public final long component1() {
            return this.durationNs;
        }

        public final String component2() {
            return this.target;
        }

        public final Time component3() {
            return getEventTime();
        }

        public final AddLongTask copy(long j, String str, Time time) {
            Intrinsics.checkNotNullParameter(str, "target");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new AddLongTask(j, str, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AddLongTask)) {
                return false;
            }
            AddLongTask addLongTask = (AddLongTask) obj;
            return this.durationNs == addLongTask.durationNs && Intrinsics.areEqual((Object) this.target, (Object) addLongTask.target) && Intrinsics.areEqual((Object) getEventTime(), (Object) addLongTask.getEventTime());
        }

        public int hashCode() {
            return (((Long.hashCode(this.durationNs) * 31) + this.target.hashCode()) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            long j = this.durationNs;
            String str = this.target;
            return "AddLongTask(durationNs=" + j + ", target=" + str + ", eventTime=" + getEventTime() + ")";
        }

        public final long getDurationNs() {
            return this.durationNs;
        }

        public final String getTarget() {
            return this.target;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AddLongTask(long j, String str, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, str, (i & 4) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AddLongTask(long j, String str, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "target");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.durationNs = j;
            this.target = str;
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$SendCustomActionNow;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class SendCustomActionNow extends RumRawEvent {
        private final Time eventTime;

        public SendCustomActionNow() {
            this((Time) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ SendCustomActionNow copy$default(SendCustomActionNow sendCustomActionNow, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                time = sendCustomActionNow.getEventTime();
            }
            return sendCustomActionNow.copy(time);
        }

        public final Time component1() {
            return getEventTime();
        }

        public final SendCustomActionNow copy(Time time) {
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new SendCustomActionNow(time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SendCustomActionNow) && Intrinsics.areEqual((Object) getEventTime(), (Object) ((SendCustomActionNow) obj).getEventTime());
        }

        public int hashCode() {
            return getEventTime().hashCode();
        }

        public String toString() {
            return "SendCustomActionNow(eventTime=" + getEventTime() + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SendCustomActionNow(Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SendCustomActionNow(Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.eventTime = time;
        }
    }

    @Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$WebViewEvent;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class WebViewEvent extends RumRawEvent {
        private final Time eventTime;

        public WebViewEvent() {
            this((Time) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ WebViewEvent copy$default(WebViewEvent webViewEvent, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                time = webViewEvent.getEventTime();
            }
            return webViewEvent.copy(time);
        }

        public final Time component1() {
            return getEventTime();
        }

        public final WebViewEvent copy(Time time) {
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new WebViewEvent(time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof WebViewEvent) && Intrinsics.areEqual((Object) getEventTime(), (Object) ((WebViewEvent) obj).getEventTime());
        }

        public int hashCode() {
            return getEventTime().hashCode();
        }

        public String toString() {
            return "WebViewEvent(eventTime=" + getEventTime() + ")";
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public WebViewEvent(Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.eventTime = time;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WebViewEvent(Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }
    }

    @Metadata(mo20734d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J3\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$SendTelemetry;", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "type", "Lcom/datadog/android/telemetry/internal/TelemetryType;", "message", "", "throwable", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "(Lcom/datadog/android/telemetry/internal/TelemetryType;Ljava/lang/String;Ljava/lang/Throwable;Lcom/datadog/android/rum/internal/domain/Time;)V", "getEventTime", "()Lcom/datadog/android/rum/internal/domain/Time;", "getMessage", "()Ljava/lang/String;", "getThrowable", "()Ljava/lang/Throwable;", "getType", "()Lcom/datadog/android/telemetry/internal/TelemetryType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumRawEvent.kt */
    public static final class SendTelemetry extends RumRawEvent {
        private final Time eventTime;
        private final String message;
        private final Throwable throwable;
        private final TelemetryType type;

        public static /* synthetic */ SendTelemetry copy$default(SendTelemetry sendTelemetry, TelemetryType telemetryType, String str, Throwable th, Time time, int i, Object obj) {
            if ((i & 1) != 0) {
                telemetryType = sendTelemetry.type;
            }
            if ((i & 2) != 0) {
                str = sendTelemetry.message;
            }
            if ((i & 4) != 0) {
                th = sendTelemetry.throwable;
            }
            if ((i & 8) != 0) {
                time = sendTelemetry.getEventTime();
            }
            return sendTelemetry.copy(telemetryType, str, th, time);
        }

        public final TelemetryType component1() {
            return this.type;
        }

        public final String component2() {
            return this.message;
        }

        public final Throwable component3() {
            return this.throwable;
        }

        public final Time component4() {
            return getEventTime();
        }

        public final SendTelemetry copy(TelemetryType telemetryType, String str, Throwable th, Time time) {
            Intrinsics.checkNotNullParameter(telemetryType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            Intrinsics.checkNotNullParameter(str, "message");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            return new SendTelemetry(telemetryType, str, th, time);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SendTelemetry)) {
                return false;
            }
            SendTelemetry sendTelemetry = (SendTelemetry) obj;
            return this.type == sendTelemetry.type && Intrinsics.areEqual((Object) this.message, (Object) sendTelemetry.message) && Intrinsics.areEqual((Object) this.throwable, (Object) sendTelemetry.throwable) && Intrinsics.areEqual((Object) getEventTime(), (Object) sendTelemetry.getEventTime());
        }

        public int hashCode() {
            int hashCode = ((this.type.hashCode() * 31) + this.message.hashCode()) * 31;
            Throwable th = this.throwable;
            return ((hashCode + (th == null ? 0 : th.hashCode())) * 31) + getEventTime().hashCode();
        }

        public String toString() {
            TelemetryType telemetryType = this.type;
            String str = this.message;
            Throwable th = this.throwable;
            return "SendTelemetry(type=" + telemetryType + ", message=" + str + ", throwable=" + th + ", eventTime=" + getEventTime() + ")";
        }

        public final TelemetryType getType() {
            return this.type;
        }

        public final String getMessage() {
            return this.message;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SendTelemetry(TelemetryType telemetryType, String str, Throwable th, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(telemetryType, str, th, (i & 8) != 0 ? new Time(0, 0, 3, (DefaultConstructorMarker) null) : time);
        }

        public Time getEventTime() {
            return this.eventTime;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SendTelemetry(TelemetryType telemetryType, String str, Throwable th, Time time) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(telemetryType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            Intrinsics.checkNotNullParameter(str, "message");
            Intrinsics.checkNotNullParameter(time, "eventTime");
            this.type = telemetryType;
            this.message = str;
            this.throwable = th;
            this.eventTime = time;
        }
    }
}
