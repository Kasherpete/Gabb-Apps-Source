package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.net.FirstPartyHostDetector;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.core.internal.utils.ThrowableExtKt;
import com.datadog.android.core.model.NetworkInfo;
import com.datadog.android.core.model.UserInfo;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumAttributes;
import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumResourceKind;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.rum.internal.domain.Time;
import com.datadog.android.rum.internal.domain.event.ResourceTiming;
import com.datadog.android.rum.internal.domain.event.RumEventSourceProvider;
import com.datadog.android.rum.internal.domain.scope.RumRawEvent;
import com.datadog.android.rum.model.ErrorEvent;
import com.datadog.android.rum.model.ResourceEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 W2\u00020\u0001:\u0001WB[\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\b\u00108\u001a\u00020\u001dH\u0016J \u00109\u001a\u0004\u0018\u00010\u00012\u0006\u0010:\u001a\u00020;2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000b0=H\u0016J\b\u0010>\u001a\u00020*H\u0016J\u001e\u0010?\u001a\u00020@2\u0006\u0010:\u001a\u00020A2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000b0=H\u0002J\u001e\u0010B\u001a\u00020@2\u0006\u0010:\u001a\u00020C2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000b0=H\u0002J\u001e\u0010D\u001a\u00020@2\u0006\u0010:\u001a\u00020E2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000b0=H\u0002J\u001e\u0010F\u001a\u00020@2\u0006\u0010:\u001a\u00020G2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000b0=H\u0002J\u0010\u0010H\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\n\u0010I\u001a\u0004\u0018\u00010JH\u0002J\u0010\u0010K\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\n\u0010L\u001a\u0004\u0018\u00010MH\u0002JI\u0010N\u001a\u00020@2\u0006\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020Q2\b\u0010.\u001a\u0004\u0018\u00010\r2\b\u0010R\u001a\u0004\u0018\u00010\u00042\b\u0010S\u001a\u0004\u0018\u00010\u00042\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000b0=H\u0002¢\u0006\u0002\u0010TJ?\u0010U\u001a\u00020@2\u0006\u0010 \u001a\u00020!2\b\u0010.\u001a\u0004\u0018\u00010\r2\b\u0010+\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000b0=H\u0002¢\u0006\u0002\u0010VR\"\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u000e\u0010#\u001a\u00020$X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010+\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0004\n\u0002\u0010,R\u000e\u0010-\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010.\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0004\n\u0002\u0010,R\u001a\u0010/\u001a\u00020*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0010\u00104\u001a\u0004\u0018\u000105X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001fR\u000e\u00107\u001a\u00020*X\u000e¢\u0006\u0002\n\u0000¨\u0006X"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumResourceScope;", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "parentScope", "url", "", "method", "key", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "initialAttributes", "", "", "serverTimeOffsetInMs", "", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "rumEventSourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "(Lcom/datadog/android/rum/internal/domain/scope/RumScope;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;Ljava/util/Map;JLcom/datadog/android/core/internal/net/FirstPartyHostDetector;Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;)V", "attributes", "", "getAttributes$dd_sdk_android_release", "()Ljava/util/Map;", "eventTimestamp", "getEventTimestamp$dd_sdk_android_release", "()J", "getFirstPartyHostDetector$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "initialContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "getKey$dd_sdk_android_release", "()Ljava/lang/String;", "kind", "Lcom/datadog/android/rum/RumResourceKind;", "getMethod$dd_sdk_android_release", "networkInfo", "Lcom/datadog/android/core/model/NetworkInfo;", "getParentScope$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "resourceId", "getResourceId$dd_sdk_android_release", "sent", "", "size", "Ljava/lang/Long;", "startedNanos", "statusCode", "stopped", "getStopped$dd_sdk_android_release", "()Z", "setStopped$dd_sdk_android_release", "(Z)V", "timing", "Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "getUrl$dd_sdk_android_release", "waitForTiming", "getRumContext", "handleEvent", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "isActive", "onAddResourceTiming", "", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddResourceTiming;", "onStopResource", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResource;", "onStopResourceWithError", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResourceWithError;", "onStopResourceWithStackTrace", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopResourceWithStackTrace;", "resolveDomain", "resolveErrorProvider", "Lcom/datadog/android/rum/model/ErrorEvent$Provider;", "resolveResourceDuration", "resolveResourceProvider", "Lcom/datadog/android/rum/model/ResourceEvent$Provider;", "sendError", "message", "source", "Lcom/datadog/android/rum/RumErrorSource;", "stackTrace", "errorType", "(Ljava/lang/String;Lcom/datadog/android/rum/RumErrorSource;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lcom/datadog/android/core/internal/persistence/DataWriter;)V", "sendResource", "(Lcom/datadog/android/rum/RumResourceKind;Ljava/lang/Long;Ljava/lang/Long;Lcom/datadog/android/rum/internal/domain/Time;Lcom/datadog/android/core/internal/persistence/DataWriter;)V", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumResourceScope.kt */
public final class RumResourceScope implements RumScope {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NEGATIVE_DURATION_WARNING_MESSAGE = "The computed duration for your resource: %s was 0 or negative. In order to keep the resource event we forced it to 1ns.";
    private final Map<String, Object> attributes;
    private final long eventTimestamp;
    private final FirstPartyHostDetector firstPartyHostDetector;
    private final RumContext initialContext;
    private final String key;
    private RumResourceKind kind = RumResourceKind.UNKNOWN;
    private final String method;
    private final NetworkInfo networkInfo = CoreFeature.INSTANCE.getNetworkInfoProvider$dd_sdk_android_release().getLatestNetworkInfo();
    private final RumScope parentScope;
    private final String resourceId;
    private final RumEventSourceProvider rumEventSourceProvider;
    private boolean sent;
    private Long size;
    private final long startedNanos;
    private Long statusCode;
    private boolean stopped;
    private ResourceTiming timing;
    private final String url;
    private boolean waitForTiming;

    public RumResourceScope(RumScope rumScope, String str, String str2, String str3, Time time, Map<String, ? extends Object> map, long j, FirstPartyHostDetector firstPartyHostDetector2, RumEventSourceProvider rumEventSourceProvider2) {
        Intrinsics.checkNotNullParameter(rumScope, "parentScope");
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(str2, "method");
        Intrinsics.checkNotNullParameter(str3, "key");
        Intrinsics.checkNotNullParameter(time, "eventTime");
        Intrinsics.checkNotNullParameter(map, "initialAttributes");
        Intrinsics.checkNotNullParameter(firstPartyHostDetector2, "firstPartyHostDetector");
        Intrinsics.checkNotNullParameter(rumEventSourceProvider2, "rumEventSourceProvider");
        this.parentScope = rumScope;
        this.url = str;
        this.method = str2;
        this.key = str3;
        this.firstPartyHostDetector = firstPartyHostDetector2;
        this.rumEventSourceProvider = rumEventSourceProvider2;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        this.resourceId = uuid;
        Map<String, Object> mutableMap = MapsKt.toMutableMap(map);
        mutableMap.putAll(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
        this.attributes = mutableMap;
        this.initialContext = rumScope.getRumContext();
        this.eventTimestamp = time.getTimestamp() + j;
        this.startedNanos = time.getNanoTime();
    }

    public final RumScope getParentScope$dd_sdk_android_release() {
        return this.parentScope;
    }

    public final String getUrl$dd_sdk_android_release() {
        return this.url;
    }

    public final String getMethod$dd_sdk_android_release() {
        return this.method;
    }

    public final String getKey$dd_sdk_android_release() {
        return this.key;
    }

    public final FirstPartyHostDetector getFirstPartyHostDetector$dd_sdk_android_release() {
        return this.firstPartyHostDetector;
    }

    public final String getResourceId$dd_sdk_android_release() {
        return this.resourceId;
    }

    public final Map<String, Object> getAttributes$dd_sdk_android_release() {
        return this.attributes;
    }

    public final long getEventTimestamp$dd_sdk_android_release() {
        return this.eventTimestamp;
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
        if (rumRawEvent instanceof RumRawEvent.WaitForResourceTiming) {
            if (Intrinsics.areEqual((Object) this.key, (Object) ((RumRawEvent.WaitForResourceTiming) rumRawEvent).getKey())) {
                this.waitForTiming = true;
            }
        } else if (rumRawEvent instanceof RumRawEvent.AddResourceTiming) {
            onAddResourceTiming((RumRawEvent.AddResourceTiming) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StopResource) {
            onStopResource((RumRawEvent.StopResource) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StopResourceWithError) {
            onStopResourceWithError((RumRawEvent.StopResourceWithError) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StopResourceWithStackTrace) {
            onStopResourceWithStackTrace((RumRawEvent.StopResourceWithStackTrace) rumRawEvent, dataWriter);
        }
        if (this.sent) {
            return null;
        }
        return this;
    }

    public RumContext getRumContext() {
        return this.initialContext;
    }

    public boolean isActive() {
        return !this.stopped;
    }

    private final void onStopResource(RumRawEvent.StopResource stopResource, DataWriter<Object> dataWriter) {
        if (Intrinsics.areEqual((Object) this.key, (Object) stopResource.getKey())) {
            this.stopped = true;
            this.attributes.putAll(stopResource.getAttributes());
            this.kind = stopResource.getKind();
            this.statusCode = stopResource.getStatusCode();
            this.size = stopResource.getSize();
            if (!this.waitForTiming || this.timing != null) {
                sendResource(this.kind, stopResource.getStatusCode(), stopResource.getSize(), stopResource.getEventTime(), dataWriter);
            }
        }
    }

    private final void onAddResourceTiming(RumRawEvent.AddResourceTiming addResourceTiming, DataWriter<Object> dataWriter) {
        if (Intrinsics.areEqual((Object) this.key, (Object) addResourceTiming.getKey())) {
            this.timing = addResourceTiming.getTiming();
            if (this.stopped && !this.sent) {
                sendResource(this.kind, this.statusCode, this.size, addResourceTiming.getEventTime(), dataWriter);
            }
        }
    }

    private final void onStopResourceWithError(RumRawEvent.StopResourceWithError stopResourceWithError, DataWriter<Object> dataWriter) {
        if (Intrinsics.areEqual((Object) this.key, (Object) stopResourceWithError.getKey())) {
            this.attributes.putAll(stopResourceWithError.getAttributes());
            sendError(stopResourceWithError.getMessage(), stopResourceWithError.getSource(), stopResourceWithError.getStatusCode(), ThrowableExtKt.loggableStackTrace(stopResourceWithError.getThrowable()), stopResourceWithError.getThrowable().getClass().getCanonicalName(), dataWriter);
        }
    }

    private final void onStopResourceWithStackTrace(RumRawEvent.StopResourceWithStackTrace stopResourceWithStackTrace, DataWriter<Object> dataWriter) {
        if (Intrinsics.areEqual((Object) this.key, (Object) stopResourceWithStackTrace.getKey())) {
            this.attributes.putAll(stopResourceWithStackTrace.getAttributes());
            sendError(stopResourceWithStackTrace.getMessage(), stopResourceWithStackTrace.getSource(), stopResourceWithStackTrace.getStatusCode(), stopResourceWithStackTrace.getStackTrace(), stopResourceWithStackTrace.getErrorType(), dataWriter);
        }
    }

    private final void sendResource(RumResourceKind rumResourceKind, Long l, Long l2, Time time, DataWriter<Object> dataWriter) {
        ResourceEvent.Dns dns;
        ResourceEvent.Connect connect;
        ResourceEvent.Ssl ssl;
        ResourceEvent.FirstByte firstByte;
        ResourceEvent.Download download;
        String str;
        this.attributes.putAll(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
        Object remove = this.attributes.remove(RumAttributes.TRACE_ID);
        ResourceEvent.Action action = null;
        String obj = remove == null ? null : remove.toString();
        Object remove2 = this.attributes.remove(RumAttributes.SPAN_ID);
        String obj2 = remove2 == null ? null : remove2.toString();
        RumContext rumContext = getRumContext();
        UserInfo userInfo = CoreFeature.INSTANCE.getUserInfoProvider$dd_sdk_android_release().getUserInfo();
        ResourceTiming resourceTiming = this.timing;
        if (resourceTiming == null) {
            Object remove3 = this.attributes.remove(RumAttributes.RESOURCE_TIMINGS);
            resourceTiming = ExternalResourceTimingsKt.extractResourceTiming(remove3 instanceof Map ? (Map) remove3 : null);
        }
        long resolveResourceDuration = resolveResourceDuration(time);
        long j = this.eventTimestamp;
        String str2 = this.resourceId;
        ResourceEvent.ResourceType schemaType = RumEventExtKt.toSchemaType(rumResourceKind);
        String str3 = this.url;
        ResourceEvent.Method method2 = RumEventExtKt.toMethod(this.method);
        if (resourceTiming == null) {
            dns = null;
        } else {
            dns = RumEventExtKt.dns(resourceTiming);
        }
        if (resourceTiming == null) {
            connect = null;
        } else {
            connect = RumEventExtKt.connect(resourceTiming);
        }
        if (resourceTiming == null) {
            ssl = null;
        } else {
            ssl = RumEventExtKt.ssl(resourceTiming);
        }
        if (resourceTiming == null) {
            firstByte = null;
        } else {
            firstByte = RumEventExtKt.firstByte(resourceTiming);
        }
        if (resourceTiming == null) {
            download = null;
        } else {
            download = RumEventExtKt.download(resourceTiming);
        }
        long j2 = j;
        ResourceEvent.Resource resource = new ResourceEvent.Resource(str2, schemaType, method2, str3, l, resolveResourceDuration, l2, (ResourceEvent.Redirect) null, dns, connect, ssl, firstByte, download, resolveResourceProvider(), 128, (DefaultConstructorMarker) null);
        String actionId = rumContext.getActionId();
        if (actionId != null) {
            action = new ResourceEvent.Action(actionId);
        }
        ResourceEvent.Action action2 = action;
        String viewId = rumContext.getViewId();
        String str4 = viewId == null ? "" : viewId;
        String viewName = rumContext.getViewName();
        String viewUrl = rumContext.getViewUrl();
        if (viewUrl == null) {
            str = "";
        } else {
            str = viewUrl;
        }
        ResourceEvent.View view = r8;
        ResourceEvent.View view2 = new ResourceEvent.View(str4, (String) null, str, viewName, 2, (DefaultConstructorMarker) null);
        ResourceEvent.Usr usr = r2;
        ResourceEvent.Usr usr2 = new ResourceEvent.Usr(userInfo.getId(), userInfo.getName(), userInfo.getEmail(), userInfo.getAdditionalProperties());
        ResourceEvent.Connectivity resourceConnectivity = RumEventExtKt.toResourceConnectivity(this.networkInfo);
        ResourceEvent.Application application = r2;
        ResourceEvent.Application application2 = new ResourceEvent.Application(rumContext.getApplicationId());
        ResourceEvent.ResourceEventSession resourceEventSession = r8;
        ResourceEvent.ResourceEventSession resourceEventSession2 = new ResourceEvent.ResourceEventSession(rumContext.getSessionId(), ResourceEvent.ResourceEventSessionType.USER, (Boolean) null, 4, (DefaultConstructorMarker) null);
        ResourceEvent.Source resourceEventSource = this.rumEventSourceProvider.getResourceEventSource();
        ResourceEvent.Context context = r1;
        ResourceEvent.Context context2 = new ResourceEvent.Context(this.attributes);
        ResourceEvent.C0866Dd dd = r3;
        ResourceEvent.C0866Dd dd2 = new ResourceEvent.C0866Dd(new ResourceEvent.DdSession(ResourceEvent.Plan.PLAN_1), (String) null, obj2, obj, 2, (DefaultConstructorMarker) null);
        dataWriter.write(new ResourceEvent(j2, application, (String) null, resourceEventSession, resourceEventSource, view, usr, resourceConnectivity, (ResourceEvent.Synthetics) null, (ResourceEvent.CiTest) null, dd, context, resource, action2, 772, (DefaultConstructorMarker) null));
        this.sent = true;
    }

    private final long resolveResourceDuration(Time time) {
        long nanoTime = time.getNanoTime() - this.startedNanos;
        if (nanoTime > 0) {
            return nanoTime;
        }
        Logger devLogger = RuntimeUtilsKt.getDevLogger();
        String format = String.format(Locale.US, NEGATIVE_DURATION_WARNING_MESSAGE, Arrays.copyOf(new Object[]{this.url}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
        Logger.w$default(devLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
        return 1;
    }

    private final ResourceEvent.Provider resolveResourceProvider() {
        if (this.firstPartyHostDetector.isFirstPartyUrl(this.url)) {
            return new ResourceEvent.Provider(resolveDomain(this.url), (String) null, ResourceEvent.ProviderType.FIRST_PARTY, 2, (DefaultConstructorMarker) null);
        }
        return null;
    }

    private final void sendError(String str, RumErrorSource rumErrorSource, Long l, String str2, String str3, DataWriter<Object> dataWriter) {
        long j;
        String str4;
        this.attributes.putAll(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
        RumContext rumContext = getRumContext();
        UserInfo userInfo = CoreFeature.INSTANCE.getUserInfoProvider$dd_sdk_android_release().getUserInfo();
        long j2 = this.eventTimestamp;
        ErrorEvent.ErrorSource schemaSource = RumEventExtKt.toSchemaSource(rumErrorSource);
        String str5 = this.url;
        ErrorEvent.Method errorMethod = RumEventExtKt.toErrorMethod(this.method);
        if (l == null) {
            j = 0;
        } else {
            j = l.longValue();
        }
        ErrorEvent.Error error = new ErrorEvent.Error((String) null, str, schemaSource, str2, false, str3, (ErrorEvent.Handling) null, (String) null, ErrorEvent.SourceType.ANDROID, new ErrorEvent.Resource(errorMethod, j, str5, resolveErrorProvider()), 193, (DefaultConstructorMarker) null);
        String actionId = rumContext.getActionId();
        ErrorEvent.Action action = actionId == null ? null : new ErrorEvent.Action(actionId);
        String viewId = rumContext.getViewId();
        String str6 = viewId == null ? "" : viewId;
        String viewName = rumContext.getViewName();
        String viewUrl = rumContext.getViewUrl();
        if (viewUrl == null) {
            str4 = "";
        } else {
            str4 = viewUrl;
        }
        ErrorEvent.View view = r22;
        ErrorEvent.View view2 = new ErrorEvent.View(str6, (String) null, str4, viewName, (Boolean) null, 18, (DefaultConstructorMarker) null);
        ErrorEvent.Usr usr = r3;
        ErrorEvent.Usr usr2 = new ErrorEvent.Usr(userInfo.getId(), userInfo.getName(), userInfo.getEmail(), userInfo.getAdditionalProperties());
        ErrorEvent.Connectivity errorConnectivity = RumEventExtKt.toErrorConnectivity(this.networkInfo);
        ErrorEvent.Application application = r2;
        ErrorEvent.Application application2 = new ErrorEvent.Application(rumContext.getApplicationId());
        ErrorEvent.ErrorEventSession errorEventSession = r22;
        ErrorEvent.ErrorEventSession errorEventSession2 = new ErrorEvent.ErrorEventSession(rumContext.getSessionId(), ErrorEvent.ErrorEventSessionType.USER, (Boolean) null, 4, (DefaultConstructorMarker) null);
        ErrorEvent.ErrorEventSource errorEventSource = this.rumEventSourceProvider.getErrorEventSource();
        ErrorEvent.Context context = r1;
        ErrorEvent.Context context2 = new ErrorEvent.Context(this.attributes);
        ErrorEvent.C0864Dd dd = r1;
        ErrorEvent.C0864Dd dd2 = new ErrorEvent.C0864Dd(new ErrorEvent.DdSession(ErrorEvent.Plan.PLAN_1), (String) null, 2, (DefaultConstructorMarker) null);
        dataWriter.write(new ErrorEvent(j2, application, (String) null, errorEventSession, errorEventSource, view, usr, errorConnectivity, (ErrorEvent.Synthetics) null, (ErrorEvent.CiTest) null, dd, context, error, action, 772, (DefaultConstructorMarker) null));
        this.sent = true;
    }

    private final ErrorEvent.Provider resolveErrorProvider() {
        if (this.firstPartyHostDetector.isFirstPartyUrl(this.url)) {
            return new ErrorEvent.Provider(resolveDomain(this.url), (String) null, ErrorEvent.ProviderType.FIRST_PARTY, 2, (DefaultConstructorMarker) null);
        }
        return null;
    }

    private final String resolveDomain(String str) {
        try {
            String host = new URL(str).getHost();
            Intrinsics.checkNotNullExpressionValue(host, "{\n            URL(url).host\n        }");
            return host;
        } catch (MalformedURLException unused) {
            return str;
        }
    }

    @Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumResourceScope$Companion;", "", "()V", "NEGATIVE_DURATION_WARNING_MESSAGE", "", "fromEvent", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "parentScope", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartResource;", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "timestampOffset", "", "rumEventSourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumResourceScope.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RumScope fromEvent(RumScope rumScope, RumRawEvent.StartResource startResource, FirstPartyHostDetector firstPartyHostDetector, long j, RumEventSourceProvider rumEventSourceProvider) {
            Intrinsics.checkNotNullParameter(rumScope, "parentScope");
            RumRawEvent.StartResource startResource2 = startResource;
            Intrinsics.checkNotNullParameter(startResource, "event");
            Intrinsics.checkNotNullParameter(firstPartyHostDetector, "firstPartyHostDetector");
            RumEventSourceProvider rumEventSourceProvider2 = rumEventSourceProvider;
            Intrinsics.checkNotNullParameter(rumEventSourceProvider2, "rumEventSourceProvider");
            return new RumResourceScope(rumScope, startResource.getUrl(), startResource.getMethod(), startResource.getKey(), startResource.getEventTime(), startResource.getAttributes(), j, firstPartyHostDetector, rumEventSourceProvider2);
        }
    }
}
