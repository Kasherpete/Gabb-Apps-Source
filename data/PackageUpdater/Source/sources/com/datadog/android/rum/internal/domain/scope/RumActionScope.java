package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.model.UserInfo;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumActionType;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.rum.internal.domain.Time;
import com.datadog.android.rum.internal.domain.event.RumEventSourceProvider;
import com.datadog.android.rum.internal.domain.scope.RumRawEvent;
import com.datadog.android.rum.model.ActionEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0017\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 ^2\u00020\u0001:\u0001^Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\b\u0010E\u001a\u00020FH\u0016J \u0010G\u001a\u0004\u0018\u00010\u00012\u0006\u0010H\u001a\u00020I2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\r0KH\u0016J\b\u0010L\u001a\u00020\u0004H\u0016J&\u0010M\u001a\u00020N2\u0006\u0010H\u001a\u00020O2\u0006\u0010P\u001a\u00020\u000f2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\r0KH\u0002J\u0010\u0010Q\u001a\u00020N2\u0006\u0010P\u001a\u00020\u000fH\u0002J\u0018\u0010R\u001a\u00020N2\u0006\u0010S\u001a\u00020\n2\u0006\u0010P\u001a\u00020\u000fH\u0002J\u0018\u0010T\u001a\u00020N2\u0006\u0010H\u001a\u00020U2\u0006\u0010P\u001a\u00020\u000fH\u0002J\u001e\u0010V\u001a\u00020N2\u0006\u0010P\u001a\u00020\u000f2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\r0KH\u0002J\u0018\u0010W\u001a\u00020N2\u0006\u0010H\u001a\u00020X2\u0006\u0010P\u001a\u00020\u000fH\u0002J\u0018\u0010Y\u001a\u00020N2\u0006\u0010H\u001a\u00020Z2\u0006\u0010P\u001a\u00020\u000fH\u0002J\u001e\u0010[\u001a\u00020N2\u0006\u0010P\u001a\u00020\u000f2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\r0KH\u0002J\u001e\u0010\\\u001a\u00020N2\u0006\u0010]\u001a\u00020\u000f2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\r0KH\u0002R\u0014\u0010\u0015\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0019X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u0014\u0010$\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u000e\u0010&\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001e\"\u0004\b*\u0010 R\u000e\u0010+\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0017\"\u0004\b.\u0010/R\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0201X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u001a\u00105\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001e\"\u0004\b7\u0010 R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010:\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010<¨\u0006_"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumActionScope;", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "parentScope", "waitForStop", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "initialType", "Lcom/datadog/android/rum/RumActionType;", "initialName", "", "initialAttributes", "", "", "serverTimeOffsetInMs", "", "inactivityThresholdMs", "maxDurationMs", "rumEventSourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "(Lcom/datadog/android/rum/internal/domain/scope/RumScope;ZLcom/datadog/android/rum/internal/domain/Time;Lcom/datadog/android/rum/RumActionType;Ljava/lang/String;Ljava/util/Map;JJJLcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;)V", "actionId", "getActionId$dd_sdk_android_release", "()Ljava/lang/String;", "attributes", "", "getAttributes$dd_sdk_android_release", "()Ljava/util/Map;", "crashCount", "getCrashCount$dd_sdk_android_release", "()J", "setCrashCount$dd_sdk_android_release", "(J)V", "errorCount", "getErrorCount$dd_sdk_android_release", "setErrorCount$dd_sdk_android_release", "eventTimestamp", "getEventTimestamp$dd_sdk_android_release", "inactivityThresholdNs", "lastInteractionNanos", "longTaskCount", "getLongTaskCount$dd_sdk_android_release", "setLongTaskCount$dd_sdk_android_release", "maxDurationNs", "name", "getName$dd_sdk_android_release", "setName$dd_sdk_android_release", "(Ljava/lang/String;)V", "ongoingResourceKeys", "", "Ljava/lang/ref/WeakReference;", "getParentScope", "()Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "resourceCount", "getResourceCount$dd_sdk_android_release", "setResourceCount$dd_sdk_android_release", "sent", "startedNanos", "stopped", "getStopped$dd_sdk_android_release", "()Z", "setStopped$dd_sdk_android_release", "(Z)V", "type", "getType$dd_sdk_android_release", "()Lcom/datadog/android/rum/RumActionType;", "setType$dd_sdk_android_release", "(Lcom/datadog/android/rum/RumActionType;)V", "getWaitForStop", "getRumContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "handleEvent", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "isActive", "onError", "", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddError;", "now", "onLongTask", "onResourceError", "eventKey", "onStartResource", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartResource;", "onStartView", "onStopAction", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopAction;", "onStopResource", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResource;", "onStopView", "sendAction", "endNanos", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumActionScope.kt */
public final class RumActionScope implements RumScope {
    public static final long ACTION_INACTIVITY_MS = 100;
    public static final long ACTION_MAX_DURATION_MS = 5000;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String actionId;
    private final Map<String, Object> attributes;
    private long crashCount;
    private long errorCount;
    private final long eventTimestamp;
    private final long inactivityThresholdNs;
    private long lastInteractionNanos;
    private long longTaskCount;
    private final long maxDurationNs;
    private String name;
    private final List<WeakReference<Object>> ongoingResourceKeys;
    private final RumScope parentScope;
    private long resourceCount;
    private final RumEventSourceProvider rumEventSourceProvider;
    private boolean sent;
    private final long startedNanos;
    private boolean stopped;
    private RumActionType type;
    private final boolean waitForStop;

    public RumActionScope(RumScope rumScope, boolean z, Time time, RumActionType rumActionType, String str, Map<String, ? extends Object> map, long j, long j2, long j3, RumEventSourceProvider rumEventSourceProvider2) {
        Intrinsics.checkNotNullParameter(rumScope, "parentScope");
        Intrinsics.checkNotNullParameter(time, "eventTime");
        Intrinsics.checkNotNullParameter(rumActionType, "initialType");
        Intrinsics.checkNotNullParameter(str, "initialName");
        Intrinsics.checkNotNullParameter(map, "initialAttributes");
        Intrinsics.checkNotNullParameter(rumEventSourceProvider2, "rumEventSourceProvider");
        this.parentScope = rumScope;
        this.waitForStop = z;
        this.rumEventSourceProvider = rumEventSourceProvider2;
        this.inactivityThresholdNs = TimeUnit.MILLISECONDS.toNanos(j2);
        this.maxDurationNs = TimeUnit.MILLISECONDS.toNanos(j3);
        this.eventTimestamp = time.getTimestamp() + j;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        this.actionId = uuid;
        this.type = rumActionType;
        this.name = str;
        long nanoTime = time.getNanoTime();
        this.startedNanos = nanoTime;
        this.lastInteractionNanos = nanoTime;
        Map<String, Object> mutableMap = MapsKt.toMutableMap(map);
        mutableMap.putAll(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
        this.attributes = mutableMap;
        this.ongoingResourceKeys = new ArrayList();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ RumActionScope(com.datadog.android.rum.internal.domain.scope.RumScope r18, boolean r19, com.datadog.android.rum.internal.domain.Time r20, com.datadog.android.rum.RumActionType r21, java.lang.String r22, java.util.Map r23, long r24, long r26, long r28, com.datadog.android.rum.internal.domain.event.RumEventSourceProvider r30, int r31, kotlin.jvm.internal.DefaultConstructorMarker r32) {
        /*
            r17 = this;
            r0 = r31
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x000a
            r1 = 100
            r12 = r1
            goto L_0x000c
        L_0x000a:
            r12 = r26
        L_0x000c:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x0014
            r0 = 5000(0x1388, double:2.4703E-320)
            r14 = r0
            goto L_0x0016
        L_0x0014:
            r14 = r28
        L_0x0016:
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            r10 = r24
            r16 = r30
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r12, r14, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.domain.scope.RumActionScope.<init>(com.datadog.android.rum.internal.domain.scope.RumScope, boolean, com.datadog.android.rum.internal.domain.Time, com.datadog.android.rum.RumActionType, java.lang.String, java.util.Map, long, long, long, com.datadog.android.rum.internal.domain.event.RumEventSourceProvider, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final RumScope getParentScope() {
        return this.parentScope;
    }

    public final boolean getWaitForStop() {
        return this.waitForStop;
    }

    public final long getEventTimestamp$dd_sdk_android_release() {
        return this.eventTimestamp;
    }

    public final String getActionId$dd_sdk_android_release() {
        return this.actionId;
    }

    public final RumActionType getType$dd_sdk_android_release() {
        return this.type;
    }

    public final void setType$dd_sdk_android_release(RumActionType rumActionType) {
        Intrinsics.checkNotNullParameter(rumActionType, "<set-?>");
        this.type = rumActionType;
    }

    public final String getName$dd_sdk_android_release() {
        return this.name;
    }

    public final void setName$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final Map<String, Object> getAttributes$dd_sdk_android_release() {
        return this.attributes;
    }

    public final long getResourceCount$dd_sdk_android_release() {
        return this.resourceCount;
    }

    public final void setResourceCount$dd_sdk_android_release(long j) {
        this.resourceCount = j;
    }

    public final long getErrorCount$dd_sdk_android_release() {
        return this.errorCount;
    }

    public final void setErrorCount$dd_sdk_android_release(long j) {
        this.errorCount = j;
    }

    public final long getCrashCount$dd_sdk_android_release() {
        return this.crashCount;
    }

    public final void setCrashCount$dd_sdk_android_release(long j) {
        this.crashCount = j;
    }

    public final long getLongTaskCount$dd_sdk_android_release() {
        return this.longTaskCount;
    }

    public final void setLongTaskCount$dd_sdk_android_release(long j) {
        this.longTaskCount = j;
    }

    public final boolean getStopped$dd_sdk_android_release() {
        return this.stopped;
    }

    public final void setStopped$dd_sdk_android_release(boolean z) {
        this.stopped = z;
    }

    public RumScope handleEvent(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        Intrinsics.checkNotNullParameter(rumRawEvent, "event");
        Intrinsics.checkNotNullParameter(dataWriter, "writer");
        long nanoTime = rumRawEvent.getEventTime().getNanoTime();
        boolean z = true;
        boolean z2 = nanoTime - this.lastInteractionNanos > this.inactivityThresholdNs;
        boolean z3 = nanoTime - this.startedNanos > this.maxDurationNs;
        CollectionsKt.removeAll(this.ongoingResourceKeys, RumActionScope$handleEvent$1.INSTANCE);
        boolean z4 = this.waitForStop && !this.stopped;
        if (!z2 || !this.ongoingResourceKeys.isEmpty() || z4) {
            z = false;
        }
        if (z) {
            sendAction(this.lastInteractionNanos, dataWriter);
        } else if (z3) {
            sendAction(nanoTime, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.SendCustomActionNow) {
            sendAction(this.lastInteractionNanos, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StartView) {
            onStartView(nanoTime, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StopView) {
            onStopView(nanoTime, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StopAction) {
            onStopAction((RumRawEvent.StopAction) rumRawEvent, nanoTime);
        } else if (rumRawEvent instanceof RumRawEvent.StartResource) {
            onStartResource((RumRawEvent.StartResource) rumRawEvent, nanoTime);
        } else if (rumRawEvent instanceof RumRawEvent.StopResource) {
            onStopResource((RumRawEvent.StopResource) rumRawEvent, nanoTime);
        } else if (rumRawEvent instanceof RumRawEvent.AddError) {
            onError((RumRawEvent.AddError) rumRawEvent, nanoTime, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StopResourceWithError) {
            onResourceError(((RumRawEvent.StopResourceWithError) rumRawEvent).getKey(), nanoTime);
        } else if (rumRawEvent instanceof RumRawEvent.StopResourceWithStackTrace) {
            onResourceError(((RumRawEvent.StopResourceWithStackTrace) rumRawEvent).getKey(), nanoTime);
        } else if (rumRawEvent instanceof RumRawEvent.AddLongTask) {
            onLongTask(nanoTime);
        }
        if (this.sent) {
            return null;
        }
        return this;
    }

    public RumContext getRumContext() {
        return this.parentScope.getRumContext();
    }

    public boolean isActive() {
        return !this.stopped;
    }

    private final void onStartView(long j, DataWriter<Object> dataWriter) {
        this.ongoingResourceKeys.clear();
        sendAction(j, dataWriter);
    }

    private final void onStopView(long j, DataWriter<Object> dataWriter) {
        this.ongoingResourceKeys.clear();
        sendAction(j, dataWriter);
    }

    private final void onStopAction(RumRawEvent.StopAction stopAction, long j) {
        RumActionType type2 = stopAction.getType();
        if (type2 != null) {
            setType$dd_sdk_android_release(type2);
        }
        String name2 = stopAction.getName();
        if (name2 != null) {
            setName$dd_sdk_android_release(name2);
        }
        this.attributes.putAll(stopAction.getAttributes());
        this.stopped = true;
        this.lastInteractionNanos = j;
    }

    private final void onStartResource(RumRawEvent.StartResource startResource, long j) {
        this.lastInteractionNanos = j;
        this.resourceCount++;
        this.ongoingResourceKeys.add(new WeakReference(startResource.getKey()));
    }

    private final void onStopResource(RumRawEvent.StopResource stopResource, long j) {
        Object obj;
        Iterator it = this.ongoingResourceKeys.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((WeakReference) obj).get(), (Object) stopResource.getKey())) {
                break;
            }
        }
        WeakReference weakReference = (WeakReference) obj;
        if (weakReference != null) {
            this.ongoingResourceKeys.remove(weakReference);
            this.lastInteractionNanos = j;
        }
    }

    private final void onError(RumRawEvent.AddError addError, long j, DataWriter<Object> dataWriter) {
        this.lastInteractionNanos = j;
        this.errorCount++;
        if (addError.isFatal()) {
            this.crashCount++;
            sendAction(j, dataWriter);
        }
    }

    private final void onResourceError(String str, long j) {
        Object obj;
        Iterator it = this.ongoingResourceKeys.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((WeakReference) obj).get(), (Object) str)) {
                break;
            }
        }
        WeakReference weakReference = (WeakReference) obj;
        if (weakReference != null) {
            this.ongoingResourceKeys.remove(weakReference);
            this.lastInteractionNanos = j;
            this.resourceCount--;
            this.errorCount++;
        }
    }

    private final void onLongTask(long j) {
        this.lastInteractionNanos = j;
        this.longTaskCount++;
    }

    private final void sendAction(long j, DataWriter<Object> dataWriter) {
        String str;
        if (!this.sent) {
            RumActionType rumActionType = this.type;
            this.attributes.putAll(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
            RumContext rumContext = getRumContext();
            UserInfo userInfo = CoreFeature.INSTANCE.getUserInfoProvider$dd_sdk_android_release().getUserInfo();
            long j2 = this.eventTimestamp;
            ActionEvent.Action action = new ActionEvent.Action(RumEventExtKt.toSchemaType(rumActionType), this.actionId, Long.valueOf(Math.max(j - this.startedNanos, 1)), new ActionEvent.Target(this.name), new ActionEvent.Error(this.errorCount), new ActionEvent.Crash(this.crashCount), new ActionEvent.LongTask(this.longTaskCount), new ActionEvent.Resource(this.resourceCount));
            String viewId = rumContext.getViewId();
            String str2 = viewId == null ? "" : viewId;
            String viewName = rumContext.getViewName();
            String viewUrl = rumContext.getViewUrl();
            if (viewUrl == null) {
                str = "";
            } else {
                str = viewUrl;
            }
            ActionEvent.View view = r23;
            ActionEvent.View view2 = new ActionEvent.View(str2, (String) null, str, viewName, (Boolean) null, 18, (DefaultConstructorMarker) null);
            ActionEvent.Application application = r1;
            ActionEvent.Application application2 = new ActionEvent.Application(rumContext.getApplicationId());
            ActionEvent.ActionEventSession actionEventSession = r12;
            ActionEvent.ActionEventSession actionEventSession2 = new ActionEvent.ActionEventSession(rumContext.getSessionId(), ActionEvent.ActionEventSessionType.USER, (Boolean) null, 4, (DefaultConstructorMarker) null);
            ActionEvent.Source actionEventSource = this.rumEventSourceProvider.getActionEventSource();
            ActionEvent.Usr usr = r1;
            ActionEvent.Usr usr2 = new ActionEvent.Usr(userInfo.getId(), userInfo.getName(), userInfo.getEmail(), userInfo.getAdditionalProperties());
            ActionEvent.Context context = r1;
            ActionEvent.Context context2 = new ActionEvent.Context(this.attributes);
            ActionEvent.C0863Dd dd = r1;
            ActionEvent.C0863Dd dd2 = new ActionEvent.C0863Dd(new ActionEvent.DdSession(ActionEvent.Plan.PLAN_1), (String) null, 2, (DefaultConstructorMarker) null);
            dataWriter.write(new ActionEvent(j2, application, (String) null, actionEventSession, actionEventSource, view, usr, (ActionEvent.Connectivity) null, (ActionEvent.Synthetics) null, (ActionEvent.CiTest) null, dd, context, action, 900, (DefaultConstructorMarker) null));
            this.sent = true;
        }
    }

    @Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumActionScope$Companion;", "", "()V", "ACTION_INACTIVITY_MS", "", "ACTION_MAX_DURATION_MS", "fromEvent", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "parentScope", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartAction;", "timestampOffset", "eventSourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumActionScope.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RumScope fromEvent(RumScope rumScope, RumRawEvent.StartAction startAction, long j, RumEventSourceProvider rumEventSourceProvider) {
            RumScope rumScope2 = rumScope;
            Intrinsics.checkNotNullParameter(rumScope2, "parentScope");
            Intrinsics.checkNotNullParameter(startAction, "event");
            RumEventSourceProvider rumEventSourceProvider2 = rumEventSourceProvider;
            Intrinsics.checkNotNullParameter(rumEventSourceProvider2, "eventSourceProvider");
            return new RumActionScope(rumScope2, startAction.getWaitForStop(), startAction.getEventTime(), startAction.getType(), startAction.getName(), startAction.getAttributes(), j, 0, 0, rumEventSourceProvider2, 384, (DefaultConstructorMarker) null);
        }
    }
}
