package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.net.FirstPartyHostDetector;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.system.BuildSdkVersionProvider;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.rum.RumSessionListener;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.rum.internal.domain.event.RumEventSourceProvider;
import com.datadog.android.rum.internal.domain.scope.RumViewScope;
import com.datadog.android.rum.internal.vitals.VitalMonitor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\u001e\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0016J\b\u0010%\u001a\u00020\u0007H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006&"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumApplicationScope;", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "applicationId", "", "samplingRate", "", "backgroundTrackingEnabled", "", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "cpuVitalMonitor", "Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "memoryVitalMonitor", "frameRateVitalMonitor", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "sessionListener", "Lcom/datadog/android/rum/RumSessionListener;", "(Ljava/lang/String;FZLcom/datadog/android/core/internal/net/FirstPartyHostDetector;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/core/internal/time/TimeProvider;Lcom/datadog/android/rum/RumSessionListener;)V", "getBackgroundTrackingEnabled$dd_sdk_android_release", "()Z", "childScope", "getChildScope$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "rumContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "rumEventSourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "getSamplingRate$dd_sdk_android_release", "()F", "getRumContext", "handleEvent", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "", "isActive", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumApplicationScope.kt */
public final class RumApplicationScope implements RumScope {
    private final boolean backgroundTrackingEnabled;
    private final RumScope childScope;
    private final RumContext rumContext;
    private final RumEventSourceProvider rumEventSourceProvider;
    private final float samplingRate;

    public boolean isActive() {
        return true;
    }

    public RumApplicationScope(String str, float f, boolean z, FirstPartyHostDetector firstPartyHostDetector, VitalMonitor vitalMonitor, VitalMonitor vitalMonitor2, VitalMonitor vitalMonitor3, TimeProvider timeProvider, RumSessionListener rumSessionListener) {
        Intrinsics.checkNotNullParameter(str, "applicationId");
        Intrinsics.checkNotNullParameter(firstPartyHostDetector, "firstPartyHostDetector");
        Intrinsics.checkNotNullParameter(vitalMonitor, "cpuVitalMonitor");
        Intrinsics.checkNotNullParameter(vitalMonitor2, "memoryVitalMonitor");
        Intrinsics.checkNotNullParameter(vitalMonitor3, "frameRateVitalMonitor");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        this.samplingRate = f;
        this.backgroundTrackingEnabled = z;
        RumEventSourceProvider rumEventSourceProvider2 = r1;
        RumEventSourceProvider rumEventSourceProvider3 = new RumEventSourceProvider(CoreFeature.INSTANCE.getSourceName$dd_sdk_android_release());
        this.rumEventSourceProvider = rumEventSourceProvider3;
        this.rumContext = new RumContext(str, (String) null, (String) null, (String) null, (String) null, (String) null, (RumViewScope.RumViewType) null, 126, (DefaultConstructorMarker) null);
        this.childScope = new RumSessionScope(this, f, z, firstPartyHostDetector, vitalMonitor, vitalMonitor2, vitalMonitor3, timeProvider, rumSessionListener, rumEventSourceProvider2, (BuildSdkVersionProvider) null, 0, 0, 7168, (DefaultConstructorMarker) null);
    }

    public final float getSamplingRate$dd_sdk_android_release() {
        return this.samplingRate;
    }

    public final boolean getBackgroundTrackingEnabled$dd_sdk_android_release() {
        return this.backgroundTrackingEnabled;
    }

    public final RumScope getChildScope$dd_sdk_android_release() {
        return this.childScope;
    }

    public RumScope handleEvent(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        Intrinsics.checkNotNullParameter(rumRawEvent, "event");
        Intrinsics.checkNotNullParameter(dataWriter, "writer");
        this.childScope.handleEvent(rumRawEvent, dataWriter);
        return this;
    }

    public RumContext getRumContext() {
        return this.rumContext;
    }
}
