package com.datadog.android.rum.internal.domain.event;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.event.EventMapper;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.rum.internal.monitor.EventType;
import com.datadog.android.rum.model.ActionEvent;
import com.datadog.android.rum.model.ErrorEvent;
import com.datadog.android.rum.model.LongTaskEvent;
import com.datadog.android.rum.model.ResourceEvent;
import com.datadog.android.rum.model.ViewEvent;
import com.datadog.android.telemetry.model.TelemetryDebugEvent;
import com.datadog.android.telemetry.model.TelemetryErrorEvent;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 '2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001'BU\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0001\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001¢\u0006\u0002\u0010\rJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0001HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u0001HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0001HÆ\u0003JY\u0010\u0019\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u00022\u0006\u0010 \u001a\u00020\u0002H\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\u00022\u0006\u0010 \u001a\u00020\u0002H\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020\u0002H\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010\u00022\u0006\u0010 \u001a\u00020\u0002H\u0002J\t\u0010%\u001a\u00020&HÖ\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006("}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/event/RumEventMapper;", "Lcom/datadog/android/event/EventMapper;", "", "viewEventMapper", "Lcom/datadog/android/rum/model/ViewEvent;", "errorEventMapper", "Lcom/datadog/android/rum/model/ErrorEvent;", "resourceEventMapper", "Lcom/datadog/android/rum/model/ResourceEvent;", "actionEventMapper", "Lcom/datadog/android/rum/model/ActionEvent;", "longTaskEventMapper", "Lcom/datadog/android/rum/model/LongTaskEvent;", "(Lcom/datadog/android/event/EventMapper;Lcom/datadog/android/event/EventMapper;Lcom/datadog/android/event/EventMapper;Lcom/datadog/android/event/EventMapper;Lcom/datadog/android/event/EventMapper;)V", "getActionEventMapper", "()Lcom/datadog/android/event/EventMapper;", "getErrorEventMapper", "getLongTaskEventMapper", "getResourceEventMapper", "getViewEventMapper", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "map", "event", "mapRumEvent", "notifyEventDropped", "", "resolveEvent", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumEventMapper.kt */
public final class RumEventMapper implements EventMapper<Object> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EVENT_NULL_WARNING_MESSAGE = "RumEventMapper: the returned mapped object was null. This event will be dropped: %s";
    public static final String NOT_SAME_EVENT_INSTANCE_WARNING_MESSAGE = "RumEventMapper: the returned mapped object was not the same instance as the original object. This event will be dropped: %s";
    public static final String NO_EVENT_MAPPER_ASSIGNED_WARNING_MESSAGE = "RumEventMapper: there was no EventMapper assigned for RUM event type: %s";
    public static final String VIEW_EVENT_NULL_WARNING_MESSAGE = "RumEventMapper: the returned mapped ViewEvent was null. The original event object will be used instead: %s";
    private final EventMapper<ActionEvent> actionEventMapper;
    private final EventMapper<ErrorEvent> errorEventMapper;
    private final EventMapper<LongTaskEvent> longTaskEventMapper;
    private final EventMapper<ResourceEvent> resourceEventMapper;
    private final EventMapper<ViewEvent> viewEventMapper;

    public RumEventMapper() {
        this((EventMapper) null, (EventMapper) null, (EventMapper) null, (EventMapper) null, (EventMapper) null, 31, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ RumEventMapper copy$default(RumEventMapper rumEventMapper, EventMapper<ViewEvent> eventMapper, EventMapper<ErrorEvent> eventMapper2, EventMapper<ResourceEvent> eventMapper3, EventMapper<ActionEvent> eventMapper4, EventMapper<LongTaskEvent> eventMapper5, int i, Object obj) {
        if ((i & 1) != 0) {
            eventMapper = rumEventMapper.viewEventMapper;
        }
        if ((i & 2) != 0) {
            eventMapper2 = rumEventMapper.errorEventMapper;
        }
        EventMapper<ErrorEvent> eventMapper6 = eventMapper2;
        if ((i & 4) != 0) {
            eventMapper3 = rumEventMapper.resourceEventMapper;
        }
        EventMapper<ResourceEvent> eventMapper7 = eventMapper3;
        if ((i & 8) != 0) {
            eventMapper4 = rumEventMapper.actionEventMapper;
        }
        EventMapper<ActionEvent> eventMapper8 = eventMapper4;
        if ((i & 16) != 0) {
            eventMapper5 = rumEventMapper.longTaskEventMapper;
        }
        return rumEventMapper.copy(eventMapper, eventMapper6, eventMapper7, eventMapper8, eventMapper5);
    }

    public final EventMapper<ViewEvent> component1() {
        return this.viewEventMapper;
    }

    public final EventMapper<ErrorEvent> component2() {
        return this.errorEventMapper;
    }

    public final EventMapper<ResourceEvent> component3() {
        return this.resourceEventMapper;
    }

    public final EventMapper<ActionEvent> component4() {
        return this.actionEventMapper;
    }

    public final EventMapper<LongTaskEvent> component5() {
        return this.longTaskEventMapper;
    }

    public final RumEventMapper copy(EventMapper<ViewEvent> eventMapper, EventMapper<ErrorEvent> eventMapper2, EventMapper<ResourceEvent> eventMapper3, EventMapper<ActionEvent> eventMapper4, EventMapper<LongTaskEvent> eventMapper5) {
        Intrinsics.checkNotNullParameter(eventMapper, "viewEventMapper");
        Intrinsics.checkNotNullParameter(eventMapper2, "errorEventMapper");
        Intrinsics.checkNotNullParameter(eventMapper3, "resourceEventMapper");
        Intrinsics.checkNotNullParameter(eventMapper4, "actionEventMapper");
        Intrinsics.checkNotNullParameter(eventMapper5, "longTaskEventMapper");
        return new RumEventMapper(eventMapper, eventMapper2, eventMapper3, eventMapper4, eventMapper5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RumEventMapper)) {
            return false;
        }
        RumEventMapper rumEventMapper = (RumEventMapper) obj;
        return Intrinsics.areEqual((Object) this.viewEventMapper, (Object) rumEventMapper.viewEventMapper) && Intrinsics.areEqual((Object) this.errorEventMapper, (Object) rumEventMapper.errorEventMapper) && Intrinsics.areEqual((Object) this.resourceEventMapper, (Object) rumEventMapper.resourceEventMapper) && Intrinsics.areEqual((Object) this.actionEventMapper, (Object) rumEventMapper.actionEventMapper) && Intrinsics.areEqual((Object) this.longTaskEventMapper, (Object) rumEventMapper.longTaskEventMapper);
    }

    public int hashCode() {
        return (((((((this.viewEventMapper.hashCode() * 31) + this.errorEventMapper.hashCode()) * 31) + this.resourceEventMapper.hashCode()) * 31) + this.actionEventMapper.hashCode()) * 31) + this.longTaskEventMapper.hashCode();
    }

    public String toString() {
        EventMapper<ViewEvent> eventMapper = this.viewEventMapper;
        EventMapper<ErrorEvent> eventMapper2 = this.errorEventMapper;
        EventMapper<ResourceEvent> eventMapper3 = this.resourceEventMapper;
        EventMapper<ActionEvent> eventMapper4 = this.actionEventMapper;
        return "RumEventMapper(viewEventMapper=" + eventMapper + ", errorEventMapper=" + eventMapper2 + ", resourceEventMapper=" + eventMapper3 + ", actionEventMapper=" + eventMapper4 + ", longTaskEventMapper=" + this.longTaskEventMapper + ")";
    }

    public RumEventMapper(EventMapper<ViewEvent> eventMapper, EventMapper<ErrorEvent> eventMapper2, EventMapper<ResourceEvent> eventMapper3, EventMapper<ActionEvent> eventMapper4, EventMapper<LongTaskEvent> eventMapper5) {
        Intrinsics.checkNotNullParameter(eventMapper, "viewEventMapper");
        Intrinsics.checkNotNullParameter(eventMapper2, "errorEventMapper");
        Intrinsics.checkNotNullParameter(eventMapper3, "resourceEventMapper");
        Intrinsics.checkNotNullParameter(eventMapper4, "actionEventMapper");
        Intrinsics.checkNotNullParameter(eventMapper5, "longTaskEventMapper");
        this.viewEventMapper = eventMapper;
        this.errorEventMapper = eventMapper2;
        this.resourceEventMapper = eventMapper3;
        this.actionEventMapper = eventMapper4;
        this.longTaskEventMapper = eventMapper5;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ RumEventMapper(com.datadog.android.event.EventMapper r4, com.datadog.android.event.EventMapper r5, com.datadog.android.event.EventMapper r6, com.datadog.android.event.EventMapper r7, com.datadog.android.event.EventMapper r8, int r9, kotlin.jvm.internal.DefaultConstructorMarker r10) {
        /*
            r3 = this;
            r10 = r9 & 1
            if (r10 == 0) goto L_0x000b
            com.datadog.android.core.internal.event.NoOpEventMapper r4 = new com.datadog.android.core.internal.event.NoOpEventMapper
            r4.<init>()
            com.datadog.android.event.EventMapper r4 = (com.datadog.android.event.EventMapper) r4
        L_0x000b:
            r10 = r9 & 2
            if (r10 == 0) goto L_0x0016
            com.datadog.android.core.internal.event.NoOpEventMapper r5 = new com.datadog.android.core.internal.event.NoOpEventMapper
            r5.<init>()
            com.datadog.android.event.EventMapper r5 = (com.datadog.android.event.EventMapper) r5
        L_0x0016:
            r10 = r5
            r5 = r9 & 4
            if (r5 == 0) goto L_0x0023
            com.datadog.android.core.internal.event.NoOpEventMapper r5 = new com.datadog.android.core.internal.event.NoOpEventMapper
            r5.<init>()
            r6 = r5
            com.datadog.android.event.EventMapper r6 = (com.datadog.android.event.EventMapper) r6
        L_0x0023:
            r0 = r6
            r5 = r9 & 8
            if (r5 == 0) goto L_0x0030
            com.datadog.android.core.internal.event.NoOpEventMapper r5 = new com.datadog.android.core.internal.event.NoOpEventMapper
            r5.<init>()
            r7 = r5
            com.datadog.android.event.EventMapper r7 = (com.datadog.android.event.EventMapper) r7
        L_0x0030:
            r1 = r7
            r5 = r9 & 16
            if (r5 == 0) goto L_0x003d
            com.datadog.android.core.internal.event.NoOpEventMapper r5 = new com.datadog.android.core.internal.event.NoOpEventMapper
            r5.<init>()
            r8 = r5
            com.datadog.android.event.EventMapper r8 = (com.datadog.android.event.EventMapper) r8
        L_0x003d:
            r2 = r8
            r5 = r3
            r6 = r4
            r7 = r10
            r8 = r0
            r9 = r1
            r10 = r2
            r5.<init>(r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.domain.event.RumEventMapper.<init>(com.datadog.android.event.EventMapper, com.datadog.android.event.EventMapper, com.datadog.android.event.EventMapper, com.datadog.android.event.EventMapper, com.datadog.android.event.EventMapper, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final EventMapper<ViewEvent> getViewEventMapper() {
        return this.viewEventMapper;
    }

    public final EventMapper<ErrorEvent> getErrorEventMapper() {
        return this.errorEventMapper;
    }

    public final EventMapper<ResourceEvent> getResourceEventMapper() {
        return this.resourceEventMapper;
    }

    public final EventMapper<ActionEvent> getActionEventMapper() {
        return this.actionEventMapper;
    }

    public final EventMapper<LongTaskEvent> getLongTaskEventMapper() {
        return this.longTaskEventMapper;
    }

    public Object map(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "event");
        Object resolveEvent = resolveEvent(obj);
        if (resolveEvent == null) {
            notifyEventDropped(obj);
        }
        return resolveEvent;
    }

    private final Object mapRumEvent(Object obj) {
        if (obj instanceof ViewEvent) {
            return this.viewEventMapper.map(obj);
        }
        if (obj instanceof ActionEvent) {
            return this.actionEventMapper.map(obj);
        }
        if (obj instanceof ErrorEvent) {
            ErrorEvent errorEvent = (ErrorEvent) obj;
            return !Intrinsics.areEqual((Object) errorEvent.getError().isCrash(), (Object) true) ? this.errorEventMapper.map(obj) : errorEvent;
        } else if (obj instanceof ResourceEvent) {
            return this.resourceEventMapper.map(obj);
        } else {
            if (obj instanceof LongTaskEvent) {
                return this.longTaskEventMapper.map(obj);
            }
            if (obj instanceof TelemetryDebugEvent ? true : obj instanceof TelemetryErrorEvent) {
                return obj;
            }
            Logger sdkLogger = RuntimeUtilsKt.getSdkLogger();
            String format = String.format(Locale.US, NO_EVENT_MAPPER_ASSIGNED_WARNING_MESSAGE, Arrays.copyOf(new Object[]{obj.getClass().getSimpleName()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.warningWithTelemetry$default(sdkLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
            return obj;
        }
    }

    private final Object resolveEvent(Object obj) {
        Object obj2 = obj;
        Object mapRumEvent = mapRumEvent(obj);
        if ((obj2 instanceof ViewEvent) && (mapRumEvent == null || !Intrinsics.areEqual(mapRumEvent, obj2))) {
            Logger devLogger = RuntimeUtilsKt.getDevLogger();
            String format = String.format(Locale.US, VIEW_EVENT_NULL_WARNING_MESSAGE, Arrays.copyOf(new Object[]{obj2}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            Logger.w$default(devLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
        } else if (mapRumEvent == null) {
            Logger devLogger2 = RuntimeUtilsKt.getDevLogger();
            String format2 = String.format(Locale.US, EVENT_NULL_WARNING_MESSAGE, Arrays.copyOf(new Object[]{obj2}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
            Logger.w$default(devLogger2, format2, (Throwable) null, (Map) null, 6, (Object) null);
            return null;
        } else if (mapRumEvent != obj2) {
            Logger devLogger3 = RuntimeUtilsKt.getDevLogger();
            String format3 = String.format(Locale.US, NOT_SAME_EVENT_INSTANCE_WARNING_MESSAGE, Arrays.copyOf(new Object[]{obj2}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, this, *args)");
            Logger.w$default(devLogger3, format3, (Throwable) null, (Map) null, 6, (Object) null);
            return null;
        }
        return obj2;
    }

    private final void notifyEventDropped(Object obj) {
        RumMonitor rumMonitor = GlobalRum.get();
        AdvancedRumMonitor advancedRumMonitor = rumMonitor instanceof AdvancedRumMonitor ? (AdvancedRumMonitor) rumMonitor : null;
        if (advancedRumMonitor != null) {
            if (obj instanceof ActionEvent) {
                advancedRumMonitor.eventDropped(((ActionEvent) obj).getView().getId(), EventType.ACTION);
            } else if (obj instanceof ResourceEvent) {
                advancedRumMonitor.eventDropped(((ResourceEvent) obj).getView().getId(), EventType.RESOURCE);
            } else if (obj instanceof ErrorEvent) {
                advancedRumMonitor.eventDropped(((ErrorEvent) obj).getView().getId(), EventType.ERROR);
            } else if (obj instanceof LongTaskEvent) {
                LongTaskEvent longTaskEvent = (LongTaskEvent) obj;
                if (Intrinsics.areEqual((Object) longTaskEvent.getLongTask().isFrozenFrame(), (Object) true)) {
                    advancedRumMonitor.eventDropped(longTaskEvent.getView().getId(), EventType.FROZEN_FRAME);
                } else {
                    advancedRumMonitor.eventDropped(longTaskEvent.getView().getId(), EventType.LONG_TASK);
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/event/RumEventMapper$Companion;", "", "()V", "EVENT_NULL_WARNING_MESSAGE", "", "NOT_SAME_EVENT_INSTANCE_WARNING_MESSAGE", "NO_EVENT_MAPPER_ASSIGNED_WARNING_MESSAGE", "VIEW_EVENT_NULL_WARNING_MESSAGE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumEventMapper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
