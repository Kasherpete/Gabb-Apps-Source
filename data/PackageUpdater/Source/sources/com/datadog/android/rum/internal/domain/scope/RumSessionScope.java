package com.datadog.android.rum.internal.domain.scope;

import android.os.Process;
import android.os.SystemClock;
import com.datadog.android.Datadog;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.net.FirstPartyHostDetector;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.persistence.NoOpDataWriter;
import com.datadog.android.core.internal.system.BuildSdkVersionProvider;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumSessionListener;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.rum.internal.domain.event.RumEventSourceProvider;
import com.datadog.android.rum.internal.domain.scope.RumRawEvent;
import com.datadog.android.rum.internal.domain.scope.RumViewScope;
import com.datadog.android.rum.internal.vitals.NoOpVitalMonitor;
import com.datadog.android.rum.internal.vitals.VitalMonitor;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 W2\u00020\u0001:\u0001WBu\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\u0018J\u0015\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020BH\u0000¢\u0006\u0002\bCJ\u0015\u0010D\u001a\u00020@2\u0006\u0010A\u001a\u00020BH\u0000¢\u0006\u0002\bEJ\b\u0010F\u001a\u00020GH\u0016J\u001e\u0010H\u001a\u00020I2\u0006\u0010A\u001a\u00020B2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020.0KH\u0002J\u001e\u0010L\u001a\u00020I2\u0006\u0010A\u001a\u00020B2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020.0KH\u0002J\u001e\u0010N\u001a\u00020\u00012\u0006\u0010A\u001a\u00020B2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020.0KH\u0016J\u001e\u0010O\u001a\u00020I2\u0006\u0010A\u001a\u00020B2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020.0KH\u0002J\b\u0010P\u001a\u00020\u0006H\u0016J+\u0010Q\u001a\u00020I2\u0006\u0010A\u001a\u00020R2\u0006\u0010S\u001a\u00020@2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020.0KH\u0000¢\u0006\u0002\bTJ\b\u0010U\u001a\u00020\u0016H\u0003J\b\u0010V\u001a\u00020IH\u0002R\u001a\u0010\u0019\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010 X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\f\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001b\"\u0004\b'\u0010\u001dR\u0014\u0010(\u001a\u00020)X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u000e\u0010\u000b\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0004¢\u0006\u0002\n\u0000R\u0012\u00101\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0004\n\u0002\u00102R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u001a\u00105\u001a\u000206X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u000e\u0010\u0017\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010=\u001a\u00020)X\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010+R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumSessionScope;", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "parentScope", "samplingRate", "", "backgroundTrackingEnabled", "", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "cpuVitalMonitor", "Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "memoryVitalMonitor", "frameRateVitalMonitor", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "sessionListener", "Lcom/datadog/android/rum/RumSessionListener;", "rumEventSourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "buildSdkVersionProvider", "Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;", "sessionInactivityNanos", "", "sessionMaxDurationNanos", "(Lcom/datadog/android/rum/internal/domain/scope/RumScope;FZLcom/datadog/android/core/internal/net/FirstPartyHostDetector;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/core/internal/time/TimeProvider;Lcom/datadog/android/rum/RumSessionListener;Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;JJ)V", "applicationDisplayed", "getApplicationDisplayed$dd_sdk_android_release", "()Z", "setApplicationDisplayed$dd_sdk_android_release", "(Z)V", "getBackgroundTrackingEnabled$dd_sdk_android_release", "childrenScopes", "", "getChildrenScopes$dd_sdk_android_release", "()Ljava/util/List;", "getFirstPartyHostDetector$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "keepSession", "getKeepSession$dd_sdk_android_release", "setKeepSession$dd_sdk_android_release", "lastUserInteractionNs", "Ljava/util/concurrent/atomic/AtomicLong;", "getLastUserInteractionNs$dd_sdk_android_release", "()Ljava/util/concurrent/atomic/AtomicLong;", "noOpWriter", "Lcom/datadog/android/core/internal/persistence/NoOpDataWriter;", "", "random", "Ljava/security/SecureRandom;", "resetSessionTime", "Ljava/lang/Long;", "getSamplingRate$dd_sdk_android_release", "()F", "sessionId", "", "getSessionId$dd_sdk_android_release", "()Ljava/lang/String;", "setSessionId$dd_sdk_android_release", "(Ljava/lang/String;)V", "getSessionListener$dd_sdk_android_release", "()Lcom/datadog/android/rum/RumSessionListener;", "sessionStartNs", "getSessionStartNs$dd_sdk_android_release", "createAppLaunchViewScope", "Lcom/datadog/android/rum/internal/domain/scope/RumViewScope;", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "createAppLaunchViewScope$dd_sdk_android_release", "createBackgroundViewScope", "createBackgroundViewScope$dd_sdk_android_release", "getRumContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "handleAppLaunchEvent", "", "actualWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "handleBackgroundEvent", "writer", "handleEvent", "handleOrphanEvent", "isActive", "onViewDisplayed", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartView;", "viewScope", "onViewDisplayed$dd_sdk_android_release", "resolveStartupTimeNs", "updateSessionIdIfNeeded", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumSessionScope.kt */
public final class RumSessionScope implements RumScope {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final long DEFAULT_SESSION_INACTIVITY_NS = TimeUnit.MINUTES.toNanos(15);
    /* access modifiers changed from: private */
    public static final long DEFAULT_SESSION_MAX_DURATION_NS = TimeUnit.HOURS.toNanos(4);
    public static final String MESSAGE_MISSING_VIEW = "A RUM event was detected, but no view is active. To track views automatically, try calling the Configuration.Builder.useViewTrackingStrategy() method.\nYou can also track views manually using the RumMonitor.startView() and RumMonitor.stopView() methods.";
    public static final String RUM_APP_LAUNCH_VIEW_NAME = "ApplicationLaunch";
    public static final String RUM_APP_LAUNCH_VIEW_URL = "com/datadog/application-launch/view";
    public static final String RUM_BACKGROUND_VIEW_NAME = "Background";
    public static final String RUM_BACKGROUND_VIEW_URL = "com/datadog/background/view";
    /* access modifiers changed from: private */
    public static final Class<?>[] silentOrphanEventTypes = {RumRawEvent.ApplicationStarted.class, RumRawEvent.KeepAlive.class, RumRawEvent.ResetSession.class, RumRawEvent.StopView.class, RumRawEvent.ActionDropped.class, RumRawEvent.ActionSent.class, RumRawEvent.ErrorDropped.class, RumRawEvent.ErrorSent.class, RumRawEvent.LongTaskDropped.class, RumRawEvent.LongTaskSent.class, RumRawEvent.ResourceDropped.class, RumRawEvent.ResourceSent.class};
    /* access modifiers changed from: private */
    public static final Class<?>[] validAppLaunchEventTypes = {RumRawEvent.AddError.class, RumRawEvent.AddLongTask.class, RumRawEvent.StartAction.class, RumRawEvent.StartResource.class};
    /* access modifiers changed from: private */
    public static final Class<?>[] validBackgroundEventTypes = {RumRawEvent.AddError.class, RumRawEvent.StartAction.class, RumRawEvent.StartResource.class};
    private boolean applicationDisplayed;
    private final boolean backgroundTrackingEnabled;
    private final BuildSdkVersionProvider buildSdkVersionProvider;
    private final List<RumScope> childrenScopes;
    private final VitalMonitor cpuVitalMonitor;
    private final FirstPartyHostDetector firstPartyHostDetector;
    private final VitalMonitor frameRateVitalMonitor;
    private boolean keepSession;
    private final AtomicLong lastUserInteractionNs;
    private final VitalMonitor memoryVitalMonitor;
    private final NoOpDataWriter<Object> noOpWriter;
    private final RumScope parentScope;
    private final SecureRandom random;
    private Long resetSessionTime;
    private final RumEventSourceProvider rumEventSourceProvider;
    private final float samplingRate;
    private String sessionId;
    private final long sessionInactivityNanos;
    private final RumSessionListener sessionListener;
    private final long sessionMaxDurationNanos;
    private final AtomicLong sessionStartNs;
    private final TimeProvider timeProvider;

    public boolean isActive() {
        return true;
    }

    public RumSessionScope(RumScope rumScope, float f, boolean z, FirstPartyHostDetector firstPartyHostDetector2, VitalMonitor vitalMonitor, VitalMonitor vitalMonitor2, VitalMonitor vitalMonitor3, TimeProvider timeProvider2, RumSessionListener rumSessionListener, RumEventSourceProvider rumEventSourceProvider2, BuildSdkVersionProvider buildSdkVersionProvider2, long j, long j2) {
        VitalMonitor vitalMonitor4 = vitalMonitor2;
        VitalMonitor vitalMonitor5 = vitalMonitor3;
        TimeProvider timeProvider3 = timeProvider2;
        RumEventSourceProvider rumEventSourceProvider3 = rumEventSourceProvider2;
        BuildSdkVersionProvider buildSdkVersionProvider3 = buildSdkVersionProvider2;
        Intrinsics.checkNotNullParameter(rumScope, "parentScope");
        Intrinsics.checkNotNullParameter(firstPartyHostDetector2, "firstPartyHostDetector");
        Intrinsics.checkNotNullParameter(vitalMonitor, "cpuVitalMonitor");
        Intrinsics.checkNotNullParameter(vitalMonitor4, "memoryVitalMonitor");
        Intrinsics.checkNotNullParameter(vitalMonitor5, "frameRateVitalMonitor");
        Intrinsics.checkNotNullParameter(timeProvider3, "timeProvider");
        Intrinsics.checkNotNullParameter(rumEventSourceProvider3, "rumEventSourceProvider");
        Intrinsics.checkNotNullParameter(buildSdkVersionProvider3, "buildSdkVersionProvider");
        this.parentScope = rumScope;
        this.samplingRate = f;
        this.backgroundTrackingEnabled = z;
        this.firstPartyHostDetector = firstPartyHostDetector2;
        this.cpuVitalMonitor = vitalMonitor;
        this.memoryVitalMonitor = vitalMonitor4;
        this.frameRateVitalMonitor = vitalMonitor5;
        this.timeProvider = timeProvider3;
        this.sessionListener = rumSessionListener;
        this.rumEventSourceProvider = rumEventSourceProvider3;
        this.buildSdkVersionProvider = buildSdkVersionProvider3;
        this.sessionInactivityNanos = j;
        this.sessionMaxDurationNanos = j2;
        this.childrenScopes = new ArrayList();
        this.sessionId = RumContext.Companion.getNULL_UUID();
        this.sessionStartNs = new AtomicLong(System.nanoTime());
        this.lastUserInteractionNs = new AtomicLong(0);
        this.random = new SecureRandom();
        this.noOpWriter = new NoOpDataWriter<>();
        GlobalRum.updateRumContext$dd_sdk_android_release$default(GlobalRum.INSTANCE, getRumContext(), (Function1) null, 2, (Object) null);
    }

    public final float getSamplingRate$dd_sdk_android_release() {
        return this.samplingRate;
    }

    public final boolean getBackgroundTrackingEnabled$dd_sdk_android_release() {
        return this.backgroundTrackingEnabled;
    }

    public final FirstPartyHostDetector getFirstPartyHostDetector$dd_sdk_android_release() {
        return this.firstPartyHostDetector;
    }

    public final RumSessionListener getSessionListener$dd_sdk_android_release() {
        return this.sessionListener;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ RumSessionScope(com.datadog.android.rum.internal.domain.scope.RumScope r19, float r20, boolean r21, com.datadog.android.core.internal.net.FirstPartyHostDetector r22, com.datadog.android.rum.internal.vitals.VitalMonitor r23, com.datadog.android.rum.internal.vitals.VitalMonitor r24, com.datadog.android.rum.internal.vitals.VitalMonitor r25, com.datadog.android.core.internal.time.TimeProvider r26, com.datadog.android.rum.RumSessionListener r27, com.datadog.android.rum.internal.domain.event.RumEventSourceProvider r28, com.datadog.android.core.internal.system.BuildSdkVersionProvider r29, long r30, long r32, int r34, kotlin.jvm.internal.DefaultConstructorMarker r35) {
        /*
            r18 = this;
            r0 = r34
            r1 = r0 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L_0x000f
            com.datadog.android.core.internal.system.DefaultBuildSdkVersionProvider r1 = new com.datadog.android.core.internal.system.DefaultBuildSdkVersionProvider
            r1.<init>()
            com.datadog.android.core.internal.system.BuildSdkVersionProvider r1 = (com.datadog.android.core.internal.system.BuildSdkVersionProvider) r1
            r13 = r1
            goto L_0x0011
        L_0x000f:
            r13 = r29
        L_0x0011:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0019
            long r1 = DEFAULT_SESSION_INACTIVITY_NS
            r14 = r1
            goto L_0x001b
        L_0x0019:
            r14 = r30
        L_0x001b:
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x0024
            long r0 = DEFAULT_SESSION_MAX_DURATION_NS
            r16 = r0
            goto L_0x0026
        L_0x0024:
            r16 = r32
        L_0x0026:
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            r6 = r22
            r7 = r23
            r8 = r24
            r9 = r25
            r10 = r26
            r11 = r27
            r12 = r28
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.domain.scope.RumSessionScope.<init>(com.datadog.android.rum.internal.domain.scope.RumScope, float, boolean, com.datadog.android.core.internal.net.FirstPartyHostDetector, com.datadog.android.rum.internal.vitals.VitalMonitor, com.datadog.android.rum.internal.vitals.VitalMonitor, com.datadog.android.rum.internal.vitals.VitalMonitor, com.datadog.android.core.internal.time.TimeProvider, com.datadog.android.rum.RumSessionListener, com.datadog.android.rum.internal.domain.event.RumEventSourceProvider, com.datadog.android.core.internal.system.BuildSdkVersionProvider, long, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final List<RumScope> getChildrenScopes$dd_sdk_android_release() {
        return this.childrenScopes;
    }

    public final boolean getKeepSession$dd_sdk_android_release() {
        return this.keepSession;
    }

    public final void setKeepSession$dd_sdk_android_release(boolean z) {
        this.keepSession = z;
    }

    public final String getSessionId$dd_sdk_android_release() {
        return this.sessionId;
    }

    public final void setSessionId$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sessionId = str;
    }

    public final AtomicLong getSessionStartNs$dd_sdk_android_release() {
        return this.sessionStartNs;
    }

    public final AtomicLong getLastUserInteractionNs$dd_sdk_android_release() {
        return this.lastUserInteractionNs;
    }

    public final boolean getApplicationDisplayed$dd_sdk_android_release() {
        return this.applicationDisplayed;
    }

    public final void setApplicationDisplayed$dd_sdk_android_release(boolean z) {
        this.applicationDisplayed = z;
    }

    public RumScope handleEvent(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        Intrinsics.checkNotNullParameter(rumRawEvent, "event");
        Intrinsics.checkNotNullParameter(dataWriter, "writer");
        int i = 0;
        if (rumRawEvent instanceof RumRawEvent.ResetSession) {
            this.sessionId = RumContext.Companion.getNULL_UUID();
            this.resetSessionTime = Long.valueOf(System.nanoTime());
            this.applicationDisplayed = false;
        }
        updateSessionIdIfNeeded();
        if (!this.keepSession) {
            dataWriter = this.noOpWriter;
        }
        Iterator<RumScope> it = this.childrenScopes.iterator();
        while (it.hasNext()) {
            if (it.next().handleEvent(rumRawEvent, dataWriter) == null) {
                it.remove();
            }
        }
        if (rumRawEvent instanceof RumRawEvent.StartView) {
            RumRawEvent.StartView startView = (RumRawEvent.StartView) rumRawEvent;
            RumViewScope fromEvent$dd_sdk_android_release = RumViewScope.Companion.fromEvent$dd_sdk_android_release(this, startView, this.firstPartyHostDetector, this.cpuVitalMonitor, this.memoryVitalMonitor, this.frameRateVitalMonitor, this.timeProvider, this.rumEventSourceProvider);
            onViewDisplayed$dd_sdk_android_release(startView, fromEvent$dd_sdk_android_release, dataWriter);
            this.childrenScopes.add(fromEvent$dd_sdk_android_release);
        } else {
            Iterable<RumScope> iterable = this.childrenScopes;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                for (RumScope isActive : iterable) {
                    if (isActive.isActive() && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            if (i == 0) {
                handleOrphanEvent(rumRawEvent, dataWriter);
            }
        }
        return this;
    }

    public RumContext getRumContext() {
        updateSessionIdIfNeeded();
        if (this.keepSession) {
            return RumContext.copy$default(this.parentScope.getRumContext(), (String) null, this.sessionId, (String) null, (String) null, (String) null, (String) null, (RumViewScope.RumViewType) null, 125, (Object) null);
        }
        return new RumContext((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (RumViewScope.RumViewType) null, 127, (DefaultConstructorMarker) null);
    }

    private final void handleOrphanEvent(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        boolean z = CoreFeature.INSTANCE.getProcessImportance$dd_sdk_android_release() == 100;
        if (this.applicationDisplayed || !z) {
            handleBackgroundEvent(rumRawEvent, dataWriter);
        } else {
            handleAppLaunchEvent(rumRawEvent, dataWriter);
        }
    }

    private final void handleAppLaunchEvent(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        boolean contains = ArraysKt.contains((T[]) validAppLaunchEventTypes, rumRawEvent.getClass());
        boolean contains2 = ArraysKt.contains((T[]) silentOrphanEventTypes, rumRawEvent.getClass());
        if (contains) {
            RumViewScope createAppLaunchViewScope$dd_sdk_android_release = createAppLaunchViewScope$dd_sdk_android_release(rumRawEvent);
            createAppLaunchViewScope$dd_sdk_android_release.handleEvent(rumRawEvent, dataWriter);
            this.childrenScopes.add(createAppLaunchViewScope$dd_sdk_android_release);
        } else if (!contains2) {
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), MESSAGE_MISSING_VIEW, (Throwable) null, (Map) null, 6, (Object) null);
        }
    }

    private final void handleBackgroundEvent(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        boolean contains = ArraysKt.contains((T[]) validBackgroundEventTypes, rumRawEvent.getClass());
        boolean contains2 = ArraysKt.contains((T[]) silentOrphanEventTypes, rumRawEvent.getClass());
        if (contains && this.backgroundTrackingEnabled) {
            RumViewScope createBackgroundViewScope$dd_sdk_android_release = createBackgroundViewScope$dd_sdk_android_release(rumRawEvent);
            createBackgroundViewScope$dd_sdk_android_release.handleEvent(rumRawEvent, dataWriter);
            this.childrenScopes.add(createBackgroundViewScope$dd_sdk_android_release);
        } else if (!contains2) {
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), MESSAGE_MISSING_VIEW, (Throwable) null, (Map) null, 6, (Object) null);
        }
    }

    public final RumViewScope createBackgroundViewScope$dd_sdk_android_release(RumRawEvent rumRawEvent) {
        Intrinsics.checkNotNullParameter(rumRawEvent, "event");
        return new RumViewScope(this, RUM_BACKGROUND_VIEW_URL, RUM_BACKGROUND_VIEW_NAME, rumRawEvent.getEventTime(), MapsKt.emptyMap(), this.firstPartyHostDetector, new NoOpVitalMonitor(), new NoOpVitalMonitor(), new NoOpVitalMonitor(), this.timeProvider, this.rumEventSourceProvider, (BuildSdkVersionProvider) null, (ViewUpdatePredicate) null, RumViewScope.RumViewType.BACKGROUND, 6144, (DefaultConstructorMarker) null);
    }

    public final RumViewScope createAppLaunchViewScope$dd_sdk_android_release(RumRawEvent rumRawEvent) {
        Intrinsics.checkNotNullParameter(rumRawEvent, "event");
        return new RumViewScope(this, RUM_APP_LAUNCH_VIEW_URL, RUM_APP_LAUNCH_VIEW_NAME, rumRawEvent.getEventTime(), MapsKt.emptyMap(), this.firstPartyHostDetector, new NoOpVitalMonitor(), new NoOpVitalMonitor(), new NoOpVitalMonitor(), this.timeProvider, this.rumEventSourceProvider, (BuildSdkVersionProvider) null, (ViewUpdatePredicate) null, RumViewScope.RumViewType.APPLICATION_LAUNCH, 6144, (DefaultConstructorMarker) null);
    }

    public final void onViewDisplayed$dd_sdk_android_release(RumRawEvent.StartView startView, RumViewScope rumViewScope, DataWriter<Object> dataWriter) {
        Intrinsics.checkNotNullParameter(startView, "event");
        Intrinsics.checkNotNullParameter(rumViewScope, "viewScope");
        Intrinsics.checkNotNullParameter(dataWriter, "writer");
        if (!this.applicationDisplayed) {
            boolean z = true;
            this.applicationDisplayed = true;
            if (CoreFeature.INSTANCE.getProcessImportance$dd_sdk_android_release() != 100) {
                z = false;
            }
            if (z) {
                rumViewScope.handleEvent(new RumRawEvent.ApplicationStarted(startView.getEventTime(), resolveStartupTimeNs()), dataWriter);
            }
        }
    }

    private final long resolveStartupTimeNs() {
        Long l = this.resetSessionTime;
        if (l != null) {
            return l.longValue();
        }
        if (this.buildSdkVersionProvider.version() < 24) {
            return Datadog.INSTANCE.getStartupTimeNs$dd_sdk_android_release();
        }
        return System.nanoTime() - TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime() - Process.getStartElapsedRealtime());
    }

    private final synchronized void updateSessionIdIfNeeded() {
        long nanoTime = System.nanoTime();
        boolean areEqual = Intrinsics.areEqual((Object) this.sessionId, (Object) RumContext.Companion.getNULL_UUID());
        long j = nanoTime - this.sessionStartNs.get();
        boolean z = true;
        boolean z2 = nanoTime - this.lastUserInteractionNs.get() >= this.sessionInactivityNanos;
        boolean z3 = j >= this.sessionMaxDurationNanos;
        if (areEqual || z2 || z3) {
            this.keepSession = this.random.nextFloat() * 100.0f < this.samplingRate;
            this.sessionStartNs.set(nanoTime);
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
            this.sessionId = uuid;
            RumSessionListener rumSessionListener = this.sessionListener;
            if (rumSessionListener != null) {
                if (this.keepSession) {
                    z = false;
                }
                rumSessionListener.onSessionStarted(uuid, z);
            }
        }
        this.lastUserInteractionNs.set(nanoTime);
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u000e\u0010\t\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nXT¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010X\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010X\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0016\u0010\u0013R \u0010\u0017\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010X\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0018\u0010\u0013¨\u0006\u0019"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumSessionScope$Companion;", "", "()V", "DEFAULT_SESSION_INACTIVITY_NS", "", "getDEFAULT_SESSION_INACTIVITY_NS$dd_sdk_android_release", "()J", "DEFAULT_SESSION_MAX_DURATION_NS", "getDEFAULT_SESSION_MAX_DURATION_NS$dd_sdk_android_release", "MESSAGE_MISSING_VIEW", "", "RUM_APP_LAUNCH_VIEW_NAME", "RUM_APP_LAUNCH_VIEW_URL", "RUM_BACKGROUND_VIEW_NAME", "RUM_BACKGROUND_VIEW_URL", "silentOrphanEventTypes", "", "Ljava/lang/Class;", "getSilentOrphanEventTypes$dd_sdk_android_release", "()[Ljava/lang/Class;", "[Ljava/lang/Class;", "validAppLaunchEventTypes", "getValidAppLaunchEventTypes$dd_sdk_android_release", "validBackgroundEventTypes", "getValidBackgroundEventTypes$dd_sdk_android_release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumSessionScope.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Class<?>[] getValidBackgroundEventTypes$dd_sdk_android_release() {
            return RumSessionScope.validBackgroundEventTypes;
        }

        public final Class<?>[] getValidAppLaunchEventTypes$dd_sdk_android_release() {
            return RumSessionScope.validAppLaunchEventTypes;
        }

        public final Class<?>[] getSilentOrphanEventTypes$dd_sdk_android_release() {
            return RumSessionScope.silentOrphanEventTypes;
        }

        public final long getDEFAULT_SESSION_INACTIVITY_NS$dd_sdk_android_release() {
            return RumSessionScope.DEFAULT_SESSION_INACTIVITY_NS;
        }

        public final long getDEFAULT_SESSION_MAX_DURATION_NS$dd_sdk_android_release() {
            return RumSessionScope.DEFAULT_SESSION_MAX_DURATION_NS;
        }
    }
}
