package com.datadog.android.core.configuration;

import android.os.Build;
import com.datadog.android.DatadogSite;
import com.datadog.android.core.internal.event.NoOpEventMapper;
import com.datadog.android.core.internal.net.DataOkHttpUploaderV2;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.event.EventMapper;
import com.datadog.android.event.NoOpSpanEventMapper;
import com.datadog.android.event.SpanEventMapper;
import com.datadog.android.event.ViewEventMapper;
import com.datadog.android.log.Logger;
import com.datadog.android.log.model.LogEvent;
import com.datadog.android.plugin.DatadogPlugin;
import com.datadog.android.rum.internal.domain.event.RumEventMapper;
import com.datadog.android.rum.internal.instrumentation.MainLooperLongTaskStrategy;
import com.datadog.android.rum.internal.instrumentation.UserActionTrackingStrategyApi29;
import com.datadog.android.rum.internal.instrumentation.UserActionTrackingStrategyLegacy;
import com.datadog.android.rum.internal.instrumentation.gestures.DatadogGesturesTracker;
import com.datadog.android.rum.internal.tracking.JetpackViewAttributesProvider;
import com.datadog.android.rum.internal.tracking.UserActionTrackingStrategy;
import com.datadog.android.rum.model.ActionEvent;
import com.datadog.android.rum.model.ErrorEvent;
import com.datadog.android.rum.model.LongTaskEvent;
import com.datadog.android.rum.model.ResourceEvent;
import com.datadog.android.rum.tracking.ActivityViewTrackingStrategy;
import com.datadog.android.rum.tracking.ComponentPredicate;
import com.datadog.android.rum.tracking.InteractionPredicate;
import com.datadog.android.rum.tracking.NoOpInteractionPredicate;
import com.datadog.android.rum.tracking.TrackingStrategy;
import com.datadog.android.rum.tracking.ViewAttributesProvider;
import com.datadog.android.rum.tracking.ViewTrackingStrategy;
import com.datadog.trace.api.Config;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Authenticator;

@Metadata(mo20734d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\b\u0018\u0000 22\u00020\u0001:\u00041234BK\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u001e\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\u001fJ\u0010\u0010 \u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b!J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0007HÀ\u0003¢\u0006\u0002\b#J\u0010\u0010$\u001a\u0004\u0018\u00010\tHÀ\u0003¢\u0006\u0002\b%J\u0010\u0010&\u001a\u0004\u0018\u00010\u000bHÀ\u0003¢\u0006\u0002\b'J\u001a\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\rHÀ\u0003¢\u0006\u0002\b)JY\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\rHÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020/HÖ\u0001J\t\u00100\u001a\u00020\u000eHÖ\u0001R \u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u00065"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Configuration;", "", "coreConfig", "Lcom/datadog/android/core/configuration/Configuration$Core;", "logsConfig", "Lcom/datadog/android/core/configuration/Configuration$Feature$Logs;", "tracesConfig", "Lcom/datadog/android/core/configuration/Configuration$Feature$Tracing;", "crashReportConfig", "Lcom/datadog/android/core/configuration/Configuration$Feature$CrashReport;", "rumConfig", "Lcom/datadog/android/core/configuration/Configuration$Feature$RUM;", "additionalConfig", "", "", "(Lcom/datadog/android/core/configuration/Configuration$Core;Lcom/datadog/android/core/configuration/Configuration$Feature$Logs;Lcom/datadog/android/core/configuration/Configuration$Feature$Tracing;Lcom/datadog/android/core/configuration/Configuration$Feature$CrashReport;Lcom/datadog/android/core/configuration/Configuration$Feature$RUM;Ljava/util/Map;)V", "getAdditionalConfig$dd_sdk_android_release", "()Ljava/util/Map;", "getCoreConfig$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Core;", "setCoreConfig$dd_sdk_android_release", "(Lcom/datadog/android/core/configuration/Configuration$Core;)V", "getCrashReportConfig$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Feature$CrashReport;", "getLogsConfig$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Feature$Logs;", "getRumConfig$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Feature$RUM;", "getTracesConfig$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Feature$Tracing;", "component1", "component1$dd_sdk_android_release", "component2", "component2$dd_sdk_android_release", "component3", "component3$dd_sdk_android_release", "component4", "component4$dd_sdk_android_release", "component5", "component5$dd_sdk_android_release", "component6", "component6$dd_sdk_android_release", "copy", "equals", "", "other", "hashCode", "", "toString", "Builder", "Companion", "Core", "Feature", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Configuration.kt */
public final class Configuration {
    public static final Companion Companion;
    /* access modifiers changed from: private */
    public static final Core DEFAULT_CORE_CONFIG;
    /* access modifiers changed from: private */
    public static final Feature.CrashReport DEFAULT_CRASH_CONFIG = new Feature.CrashReport("https://logs.browser-intake-datadoghq.com", CollectionsKt.emptyList());
    /* access modifiers changed from: private */
    public static final Feature.Logs DEFAULT_LOGS_CONFIG = new Feature.Logs("https://logs.browser-intake-datadoghq.com", CollectionsKt.emptyList(), new NoOpEventMapper());
    public static final long DEFAULT_LONG_TASK_THRESHOLD_MS = 100;
    /* access modifiers changed from: private */
    public static final Feature.RUM DEFAULT_RUM_CONFIG;
    public static final float DEFAULT_SAMPLING_RATE = 100.0f;
    public static final float DEFAULT_TELEMETRY_SAMPLING_RATE = 20.0f;
    /* access modifiers changed from: private */
    public static final Feature.Tracing DEFAULT_TRACING_CONFIG = new Feature.Tracing("https://trace.browser-intake-datadoghq.com", CollectionsKt.emptyList(), new NoOpSpanEventMapper());
    public static final String ERROR_FEATURE_DISABLED = "The %s feature has been disabled in your Configuration.Builder, but you're trying to edit the RUM configuration with the %s() method.";
    public static final String NETWORK_REQUESTS_TRACKING_FEATURE_NAME = "Network requests";
    public static final String WEB_VIEW_TRACKING_FEATURE_NAME = "WebView";
    private final Map<String, Object> additionalConfig;
    private Core coreConfig;
    private final Feature.CrashReport crashReportConfig;
    private final Feature.Logs logsConfig;
    private final Feature.RUM rumConfig;
    private final Feature.Tracing tracesConfig;

    public static /* synthetic */ Configuration copy$default(Configuration configuration, Core core, Feature.Logs logs, Feature.Tracing tracing, Feature.CrashReport crashReport, Feature.RUM rum, Map<String, Object> map, int i, Object obj) {
        if ((i & 1) != 0) {
            core = configuration.coreConfig;
        }
        if ((i & 2) != 0) {
            logs = configuration.logsConfig;
        }
        Feature.Logs logs2 = logs;
        if ((i & 4) != 0) {
            tracing = configuration.tracesConfig;
        }
        Feature.Tracing tracing2 = tracing;
        if ((i & 8) != 0) {
            crashReport = configuration.crashReportConfig;
        }
        Feature.CrashReport crashReport2 = crashReport;
        if ((i & 16) != 0) {
            rum = configuration.rumConfig;
        }
        Feature.RUM rum2 = rum;
        if ((i & 32) != 0) {
            map = configuration.additionalConfig;
        }
        return configuration.copy(core, logs2, tracing2, crashReport2, rum2, map);
    }

    public final Core component1$dd_sdk_android_release() {
        return this.coreConfig;
    }

    public final Feature.Logs component2$dd_sdk_android_release() {
        return this.logsConfig;
    }

    public final Feature.Tracing component3$dd_sdk_android_release() {
        return this.tracesConfig;
    }

    public final Feature.CrashReport component4$dd_sdk_android_release() {
        return this.crashReportConfig;
    }

    public final Feature.RUM component5$dd_sdk_android_release() {
        return this.rumConfig;
    }

    public final Map<String, Object> component6$dd_sdk_android_release() {
        return this.additionalConfig;
    }

    public final Configuration copy(Core core, Feature.Logs logs, Feature.Tracing tracing, Feature.CrashReport crashReport, Feature.RUM rum, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(core, "coreConfig");
        Intrinsics.checkNotNullParameter(map, "additionalConfig");
        return new Configuration(core, logs, tracing, crashReport, rum, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Configuration)) {
            return false;
        }
        Configuration configuration = (Configuration) obj;
        return Intrinsics.areEqual((Object) this.coreConfig, (Object) configuration.coreConfig) && Intrinsics.areEqual((Object) this.logsConfig, (Object) configuration.logsConfig) && Intrinsics.areEqual((Object) this.tracesConfig, (Object) configuration.tracesConfig) && Intrinsics.areEqual((Object) this.crashReportConfig, (Object) configuration.crashReportConfig) && Intrinsics.areEqual((Object) this.rumConfig, (Object) configuration.rumConfig) && Intrinsics.areEqual((Object) this.additionalConfig, (Object) configuration.additionalConfig);
    }

    public int hashCode() {
        int hashCode = this.coreConfig.hashCode() * 31;
        Feature.Logs logs = this.logsConfig;
        int i = 0;
        int hashCode2 = (hashCode + (logs == null ? 0 : logs.hashCode())) * 31;
        Feature.Tracing tracing = this.tracesConfig;
        int hashCode3 = (hashCode2 + (tracing == null ? 0 : tracing.hashCode())) * 31;
        Feature.CrashReport crashReport = this.crashReportConfig;
        int hashCode4 = (hashCode3 + (crashReport == null ? 0 : crashReport.hashCode())) * 31;
        Feature.RUM rum = this.rumConfig;
        if (rum != null) {
            i = rum.hashCode();
        }
        return ((hashCode4 + i) * 31) + this.additionalConfig.hashCode();
    }

    public String toString() {
        Core core = this.coreConfig;
        Feature.Logs logs = this.logsConfig;
        Feature.Tracing tracing = this.tracesConfig;
        Feature.CrashReport crashReport = this.crashReportConfig;
        Feature.RUM rum = this.rumConfig;
        return "Configuration(coreConfig=" + core + ", logsConfig=" + logs + ", tracesConfig=" + tracing + ", crashReportConfig=" + crashReport + ", rumConfig=" + rum + ", additionalConfig=" + this.additionalConfig + ")";
    }

    public Configuration(Core core, Feature.Logs logs, Feature.Tracing tracing, Feature.CrashReport crashReport, Feature.RUM rum, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(core, "coreConfig");
        Intrinsics.checkNotNullParameter(map, "additionalConfig");
        this.coreConfig = core;
        this.logsConfig = logs;
        this.tracesConfig = tracing;
        this.crashReportConfig = crashReport;
        this.rumConfig = rum;
        this.additionalConfig = map;
    }

    public final Core getCoreConfig$dd_sdk_android_release() {
        return this.coreConfig;
    }

    public final void setCoreConfig$dd_sdk_android_release(Core core) {
        Intrinsics.checkNotNullParameter(core, "<set-?>");
        this.coreConfig = core;
    }

    public final Feature.Logs getLogsConfig$dd_sdk_android_release() {
        return this.logsConfig;
    }

    public final Feature.Tracing getTracesConfig$dd_sdk_android_release() {
        return this.tracesConfig;
    }

    public final Feature.CrashReport getCrashReportConfig$dd_sdk_android_release() {
        return this.crashReportConfig;
    }

    public final Feature.RUM getRumConfig$dd_sdk_android_release() {
        return this.rumConfig;
    }

    public final Map<String, Object> getAdditionalConfig$dd_sdk_android_release() {
        return this.additionalConfig;
    }

    @Metadata(mo20734d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\u0013J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J\t\u0010*\u001a\u00020\u000bHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010,\u001a\u00020\u000fHÆ\u0003J\t\u0010-\u001a\u00020\u0011HÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003Jq\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u00100\u001a\u00020\u00032\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u001cR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019¨\u00065"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Configuration$Core;", "", "needsClearTextHttp", "", "enableDeveloperModeWhenDebuggable", "firstPartyHosts", "", "", "batchSize", "Lcom/datadog/android/core/configuration/BatchSize;", "uploadFrequency", "Lcom/datadog/android/core/configuration/UploadFrequency;", "proxy", "Ljava/net/Proxy;", "proxyAuth", "Lokhttp3/Authenticator;", "securityConfig", "Lcom/datadog/android/core/configuration/SecurityConfig;", "webViewTrackingHosts", "(ZZLjava/util/List;Lcom/datadog/android/core/configuration/BatchSize;Lcom/datadog/android/core/configuration/UploadFrequency;Ljava/net/Proxy;Lokhttp3/Authenticator;Lcom/datadog/android/core/configuration/SecurityConfig;Ljava/util/List;)V", "getBatchSize", "()Lcom/datadog/android/core/configuration/BatchSize;", "getEnableDeveloperModeWhenDebuggable", "()Z", "getFirstPartyHosts", "()Ljava/util/List;", "getNeedsClearTextHttp", "setNeedsClearTextHttp", "(Z)V", "getProxy", "()Ljava/net/Proxy;", "getProxyAuth", "()Lokhttp3/Authenticator;", "getSecurityConfig", "()Lcom/datadog/android/core/configuration/SecurityConfig;", "getUploadFrequency", "()Lcom/datadog/android/core/configuration/UploadFrequency;", "getWebViewTrackingHosts", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Configuration.kt */
    public static final class Core {
        private final BatchSize batchSize;
        private final boolean enableDeveloperModeWhenDebuggable;
        private final List<String> firstPartyHosts;
        private boolean needsClearTextHttp;
        private final Proxy proxy;
        private final Authenticator proxyAuth;
        private final SecurityConfig securityConfig;
        private final UploadFrequency uploadFrequency;
        private final List<String> webViewTrackingHosts;

        public static /* synthetic */ Core copy$default(Core core, boolean z, boolean z2, List list, BatchSize batchSize2, UploadFrequency uploadFrequency2, Proxy proxy2, Authenticator authenticator, SecurityConfig securityConfig2, List list2, int i, Object obj) {
            Core core2 = core;
            int i2 = i;
            return core.copy((i2 & 1) != 0 ? core2.needsClearTextHttp : z, (i2 & 2) != 0 ? core2.enableDeveloperModeWhenDebuggable : z2, (i2 & 4) != 0 ? core2.firstPartyHosts : list, (i2 & 8) != 0 ? core2.batchSize : batchSize2, (i2 & 16) != 0 ? core2.uploadFrequency : uploadFrequency2, (i2 & 32) != 0 ? core2.proxy : proxy2, (i2 & 64) != 0 ? core2.proxyAuth : authenticator, (i2 & 128) != 0 ? core2.securityConfig : securityConfig2, (i2 & 256) != 0 ? core2.webViewTrackingHosts : list2);
        }

        public final boolean component1() {
            return this.needsClearTextHttp;
        }

        public final boolean component2() {
            return this.enableDeveloperModeWhenDebuggable;
        }

        public final List<String> component3() {
            return this.firstPartyHosts;
        }

        public final BatchSize component4() {
            return this.batchSize;
        }

        public final UploadFrequency component5() {
            return this.uploadFrequency;
        }

        public final Proxy component6() {
            return this.proxy;
        }

        public final Authenticator component7() {
            return this.proxyAuth;
        }

        public final SecurityConfig component8() {
            return this.securityConfig;
        }

        public final List<String> component9() {
            return this.webViewTrackingHosts;
        }

        public final Core copy(boolean z, boolean z2, List<String> list, BatchSize batchSize2, UploadFrequency uploadFrequency2, Proxy proxy2, Authenticator authenticator, SecurityConfig securityConfig2, List<String> list2) {
            Intrinsics.checkNotNullParameter(list, "firstPartyHosts");
            Intrinsics.checkNotNullParameter(batchSize2, "batchSize");
            UploadFrequency uploadFrequency3 = uploadFrequency2;
            Intrinsics.checkNotNullParameter(uploadFrequency3, "uploadFrequency");
            Authenticator authenticator2 = authenticator;
            Intrinsics.checkNotNullParameter(authenticator2, "proxyAuth");
            SecurityConfig securityConfig3 = securityConfig2;
            Intrinsics.checkNotNullParameter(securityConfig3, "securityConfig");
            List<String> list3 = list2;
            Intrinsics.checkNotNullParameter(list3, "webViewTrackingHosts");
            return new Core(z, z2, list, batchSize2, uploadFrequency3, proxy2, authenticator2, securityConfig3, list3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Core)) {
                return false;
            }
            Core core = (Core) obj;
            return this.needsClearTextHttp == core.needsClearTextHttp && this.enableDeveloperModeWhenDebuggable == core.enableDeveloperModeWhenDebuggable && Intrinsics.areEqual((Object) this.firstPartyHosts, (Object) core.firstPartyHosts) && this.batchSize == core.batchSize && this.uploadFrequency == core.uploadFrequency && Intrinsics.areEqual((Object) this.proxy, (Object) core.proxy) && Intrinsics.areEqual((Object) this.proxyAuth, (Object) core.proxyAuth) && Intrinsics.areEqual((Object) this.securityConfig, (Object) core.securityConfig) && Intrinsics.areEqual((Object) this.webViewTrackingHosts, (Object) core.webViewTrackingHosts);
        }

        public int hashCode() {
            boolean z = this.needsClearTextHttp;
            boolean z2 = true;
            if (z) {
                z = true;
            }
            int i = (z ? 1 : 0) * true;
            boolean z3 = this.enableDeveloperModeWhenDebuggable;
            if (!z3) {
                z2 = z3;
            }
            int hashCode = (((((((i + (z2 ? 1 : 0)) * 31) + this.firstPartyHosts.hashCode()) * 31) + this.batchSize.hashCode()) * 31) + this.uploadFrequency.hashCode()) * 31;
            Proxy proxy2 = this.proxy;
            return ((((((hashCode + (proxy2 == null ? 0 : proxy2.hashCode())) * 31) + this.proxyAuth.hashCode()) * 31) + this.securityConfig.hashCode()) * 31) + this.webViewTrackingHosts.hashCode();
        }

        public String toString() {
            boolean z = this.needsClearTextHttp;
            boolean z2 = this.enableDeveloperModeWhenDebuggable;
            List<String> list = this.firstPartyHosts;
            BatchSize batchSize2 = this.batchSize;
            UploadFrequency uploadFrequency2 = this.uploadFrequency;
            Proxy proxy2 = this.proxy;
            Authenticator authenticator = this.proxyAuth;
            SecurityConfig securityConfig2 = this.securityConfig;
            return "Core(needsClearTextHttp=" + z + ", enableDeveloperModeWhenDebuggable=" + z2 + ", firstPartyHosts=" + list + ", batchSize=" + batchSize2 + ", uploadFrequency=" + uploadFrequency2 + ", proxy=" + proxy2 + ", proxyAuth=" + authenticator + ", securityConfig=" + securityConfig2 + ", webViewTrackingHosts=" + this.webViewTrackingHosts + ")";
        }

        public Core(boolean z, boolean z2, List<String> list, BatchSize batchSize2, UploadFrequency uploadFrequency2, Proxy proxy2, Authenticator authenticator, SecurityConfig securityConfig2, List<String> list2) {
            Intrinsics.checkNotNullParameter(list, "firstPartyHosts");
            Intrinsics.checkNotNullParameter(batchSize2, "batchSize");
            Intrinsics.checkNotNullParameter(uploadFrequency2, "uploadFrequency");
            Intrinsics.checkNotNullParameter(authenticator, "proxyAuth");
            Intrinsics.checkNotNullParameter(securityConfig2, "securityConfig");
            Intrinsics.checkNotNullParameter(list2, "webViewTrackingHosts");
            this.needsClearTextHttp = z;
            this.enableDeveloperModeWhenDebuggable = z2;
            this.firstPartyHosts = list;
            this.batchSize = batchSize2;
            this.uploadFrequency = uploadFrequency2;
            this.proxy = proxy2;
            this.proxyAuth = authenticator;
            this.securityConfig = securityConfig2;
            this.webViewTrackingHosts = list2;
        }

        public final boolean getNeedsClearTextHttp() {
            return this.needsClearTextHttp;
        }

        public final void setNeedsClearTextHttp(boolean z) {
            this.needsClearTextHttp = z;
        }

        public final boolean getEnableDeveloperModeWhenDebuggable() {
            return this.enableDeveloperModeWhenDebuggable;
        }

        public final List<String> getFirstPartyHosts() {
            return this.firstPartyHosts;
        }

        public final BatchSize getBatchSize() {
            return this.batchSize;
        }

        public final UploadFrequency getUploadFrequency() {
            return this.uploadFrequency;
        }

        public final Proxy getProxy() {
            return this.proxy;
        }

        public final Authenticator getProxyAuth() {
            return this.proxyAuth;
        }

        public final SecurityConfig getSecurityConfig() {
            return this.securityConfig;
        }

        public final List<String> getWebViewTrackingHosts() {
            return this.webViewTrackingHosts;
        }
    }

    @Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0004\f\r\u000e\u000fB\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0001\u0004\u0010\u0011\u0012\u0013¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Configuration$Feature;", "", "()V", "endpointUrl", "", "getEndpointUrl", "()Ljava/lang/String;", "plugins", "", "Lcom/datadog/android/plugin/DatadogPlugin;", "getPlugins", "()Ljava/util/List;", "CrashReport", "Logs", "RUM", "Tracing", "Lcom/datadog/android/core/configuration/Configuration$Feature$Logs;", "Lcom/datadog/android/core/configuration/Configuration$Feature$CrashReport;", "Lcom/datadog/android/core/configuration/Configuration$Feature$Tracing;", "Lcom/datadog/android/core/configuration/Configuration$Feature$RUM;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Configuration.kt */
    public static abstract class Feature {
        public /* synthetic */ Feature(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract String getEndpointUrl();

        public abstract List<DatadogPlugin> getPlugins();

        private Feature() {
        }

        @Metadata(mo20734d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Configuration$Feature$Logs;", "Lcom/datadog/android/core/configuration/Configuration$Feature;", "endpointUrl", "", "plugins", "", "Lcom/datadog/android/plugin/DatadogPlugin;", "logsEventMapper", "Lcom/datadog/android/event/EventMapper;", "Lcom/datadog/android/log/model/LogEvent;", "(Ljava/lang/String;Ljava/util/List;Lcom/datadog/android/event/EventMapper;)V", "getEndpointUrl", "()Ljava/lang/String;", "getLogsEventMapper", "()Lcom/datadog/android/event/EventMapper;", "getPlugins", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: Configuration.kt */
        public static final class Logs extends Feature {
            private final String endpointUrl;
            private final EventMapper<LogEvent> logsEventMapper;
            private final List<DatadogPlugin> plugins;

            public static /* synthetic */ Logs copy$default(Logs logs, String str, List<DatadogPlugin> list, EventMapper<LogEvent> eventMapper, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = logs.getEndpointUrl();
                }
                if ((i & 2) != 0) {
                    list = logs.getPlugins();
                }
                if ((i & 4) != 0) {
                    eventMapper = logs.logsEventMapper;
                }
                return logs.copy(str, list, eventMapper);
            }

            public final String component1() {
                return getEndpointUrl();
            }

            public final List<DatadogPlugin> component2() {
                return getPlugins();
            }

            public final EventMapper<LogEvent> component3() {
                return this.logsEventMapper;
            }

            public final Logs copy(String str, List<? extends DatadogPlugin> list, EventMapper<LogEvent> eventMapper) {
                Intrinsics.checkNotNullParameter(str, "endpointUrl");
                Intrinsics.checkNotNullParameter(list, "plugins");
                Intrinsics.checkNotNullParameter(eventMapper, "logsEventMapper");
                return new Logs(str, list, eventMapper);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Logs)) {
                    return false;
                }
                Logs logs = (Logs) obj;
                return Intrinsics.areEqual((Object) getEndpointUrl(), (Object) logs.getEndpointUrl()) && Intrinsics.areEqual((Object) getPlugins(), (Object) logs.getPlugins()) && Intrinsics.areEqual((Object) this.logsEventMapper, (Object) logs.logsEventMapper);
            }

            public int hashCode() {
                return (((getEndpointUrl().hashCode() * 31) + getPlugins().hashCode()) * 31) + this.logsEventMapper.hashCode();
            }

            public String toString() {
                String endpointUrl2 = getEndpointUrl();
                List<DatadogPlugin> plugins2 = getPlugins();
                return "Logs(endpointUrl=" + endpointUrl2 + ", plugins=" + plugins2 + ", logsEventMapper=" + this.logsEventMapper + ")";
            }

            public String getEndpointUrl() {
                return this.endpointUrl;
            }

            public List<DatadogPlugin> getPlugins() {
                return this.plugins;
            }

            public final EventMapper<LogEvent> getLogsEventMapper() {
                return this.logsEventMapper;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Logs(String str, List<? extends DatadogPlugin> list, EventMapper<LogEvent> eventMapper) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "endpointUrl");
                Intrinsics.checkNotNullParameter(list, "plugins");
                Intrinsics.checkNotNullParameter(eventMapper, "logsEventMapper");
                this.endpointUrl = str;
                this.plugins = list;
                this.logsEventMapper = eventMapper;
            }
        }

        @Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Configuration$Feature$CrashReport;", "Lcom/datadog/android/core/configuration/Configuration$Feature;", "endpointUrl", "", "plugins", "", "Lcom/datadog/android/plugin/DatadogPlugin;", "(Ljava/lang/String;Ljava/util/List;)V", "getEndpointUrl", "()Ljava/lang/String;", "getPlugins", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: Configuration.kt */
        public static final class CrashReport extends Feature {
            private final String endpointUrl;
            private final List<DatadogPlugin> plugins;

            public static /* synthetic */ CrashReport copy$default(CrashReport crashReport, String str, List<DatadogPlugin> list, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = crashReport.getEndpointUrl();
                }
                if ((i & 2) != 0) {
                    list = crashReport.getPlugins();
                }
                return crashReport.copy(str, list);
            }

            public final String component1() {
                return getEndpointUrl();
            }

            public final List<DatadogPlugin> component2() {
                return getPlugins();
            }

            public final CrashReport copy(String str, List<? extends DatadogPlugin> list) {
                Intrinsics.checkNotNullParameter(str, "endpointUrl");
                Intrinsics.checkNotNullParameter(list, "plugins");
                return new CrashReport(str, list);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof CrashReport)) {
                    return false;
                }
                CrashReport crashReport = (CrashReport) obj;
                return Intrinsics.areEqual((Object) getEndpointUrl(), (Object) crashReport.getEndpointUrl()) && Intrinsics.areEqual((Object) getPlugins(), (Object) crashReport.getPlugins());
            }

            public int hashCode() {
                return (getEndpointUrl().hashCode() * 31) + getPlugins().hashCode();
            }

            public String toString() {
                String endpointUrl2 = getEndpointUrl();
                return "CrashReport(endpointUrl=" + endpointUrl2 + ", plugins=" + getPlugins() + ")";
            }

            public String getEndpointUrl() {
                return this.endpointUrl;
            }

            public List<DatadogPlugin> getPlugins() {
                return this.plugins;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public CrashReport(String str, List<? extends DatadogPlugin> list) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "endpointUrl");
                Intrinsics.checkNotNullParameter(list, "plugins");
                this.endpointUrl = str;
                this.plugins = list;
            }
        }

        @Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\bHÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Configuration$Feature$Tracing;", "Lcom/datadog/android/core/configuration/Configuration$Feature;", "endpointUrl", "", "plugins", "", "Lcom/datadog/android/plugin/DatadogPlugin;", "spanEventMapper", "Lcom/datadog/android/event/SpanEventMapper;", "(Ljava/lang/String;Ljava/util/List;Lcom/datadog/android/event/SpanEventMapper;)V", "getEndpointUrl", "()Ljava/lang/String;", "getPlugins", "()Ljava/util/List;", "getSpanEventMapper", "()Lcom/datadog/android/event/SpanEventMapper;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: Configuration.kt */
        public static final class Tracing extends Feature {
            private final String endpointUrl;
            private final List<DatadogPlugin> plugins;
            private final SpanEventMapper spanEventMapper;

            public static /* synthetic */ Tracing copy$default(Tracing tracing, String str, List<DatadogPlugin> list, SpanEventMapper spanEventMapper2, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = tracing.getEndpointUrl();
                }
                if ((i & 2) != 0) {
                    list = tracing.getPlugins();
                }
                if ((i & 4) != 0) {
                    spanEventMapper2 = tracing.spanEventMapper;
                }
                return tracing.copy(str, list, spanEventMapper2);
            }

            public final String component1() {
                return getEndpointUrl();
            }

            public final List<DatadogPlugin> component2() {
                return getPlugins();
            }

            public final SpanEventMapper component3() {
                return this.spanEventMapper;
            }

            public final Tracing copy(String str, List<? extends DatadogPlugin> list, SpanEventMapper spanEventMapper2) {
                Intrinsics.checkNotNullParameter(str, "endpointUrl");
                Intrinsics.checkNotNullParameter(list, "plugins");
                Intrinsics.checkNotNullParameter(spanEventMapper2, "spanEventMapper");
                return new Tracing(str, list, spanEventMapper2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Tracing)) {
                    return false;
                }
                Tracing tracing = (Tracing) obj;
                return Intrinsics.areEqual((Object) getEndpointUrl(), (Object) tracing.getEndpointUrl()) && Intrinsics.areEqual((Object) getPlugins(), (Object) tracing.getPlugins()) && Intrinsics.areEqual((Object) this.spanEventMapper, (Object) tracing.spanEventMapper);
            }

            public int hashCode() {
                return (((getEndpointUrl().hashCode() * 31) + getPlugins().hashCode()) * 31) + this.spanEventMapper.hashCode();
            }

            public String toString() {
                String endpointUrl2 = getEndpointUrl();
                List<DatadogPlugin> plugins2 = getPlugins();
                return "Tracing(endpointUrl=" + endpointUrl2 + ", plugins=" + plugins2 + ", spanEventMapper=" + this.spanEventMapper + ")";
            }

            public String getEndpointUrl() {
                return this.endpointUrl;
            }

            public List<DatadogPlugin> getPlugins() {
                return this.plugins;
            }

            public final SpanEventMapper getSpanEventMapper() {
                return this.spanEventMapper;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Tracing(String str, List<? extends DatadogPlugin> list, SpanEventMapper spanEventMapper2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "endpointUrl");
                Intrinsics.checkNotNullParameter(list, "plugins");
                Intrinsics.checkNotNullParameter(spanEventMapper2, "spanEventMapper");
                this.endpointUrl = str;
                this.plugins = list;
                this.spanEventMapper = spanEventMapper2;
            }
        }

        @Metadata(mo20734d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010)\u001a\u00020\bHÆ\u0003J\t\u0010*\u001a\u00020\bHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0003J\t\u0010/\u001a\u00020\u0014HÆ\u0003Ju\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001J\u0013\u00101\u001a\u00020\u00142\b\u00102\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u00066"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Configuration$Feature$RUM;", "Lcom/datadog/android/core/configuration/Configuration$Feature;", "endpointUrl", "", "plugins", "", "Lcom/datadog/android/plugin/DatadogPlugin;", "samplingRate", "", "telemetrySamplingRate", "userActionTrackingStrategy", "Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;", "viewTrackingStrategy", "Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;", "longTaskTrackingStrategy", "Lcom/datadog/android/rum/tracking/TrackingStrategy;", "rumEventMapper", "Lcom/datadog/android/event/EventMapper;", "", "backgroundEventTracking", "", "(Ljava/lang/String;Ljava/util/List;FFLcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;Lcom/datadog/android/rum/tracking/TrackingStrategy;Lcom/datadog/android/event/EventMapper;Z)V", "getBackgroundEventTracking", "()Z", "getEndpointUrl", "()Ljava/lang/String;", "getLongTaskTrackingStrategy", "()Lcom/datadog/android/rum/tracking/TrackingStrategy;", "getPlugins", "()Ljava/util/List;", "getRumEventMapper", "()Lcom/datadog/android/event/EventMapper;", "getSamplingRate", "()F", "getTelemetrySamplingRate", "getUserActionTrackingStrategy", "()Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;", "getViewTrackingStrategy", "()Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: Configuration.kt */
        public static final class RUM extends Feature {
            private final boolean backgroundEventTracking;
            private final String endpointUrl;
            private final TrackingStrategy longTaskTrackingStrategy;
            private final List<DatadogPlugin> plugins;
            private final EventMapper<Object> rumEventMapper;
            private final float samplingRate;
            private final float telemetrySamplingRate;
            private final UserActionTrackingStrategy userActionTrackingStrategy;
            private final ViewTrackingStrategy viewTrackingStrategy;

            public static /* synthetic */ RUM copy$default(RUM rum, String str, List list, float f, float f2, UserActionTrackingStrategy userActionTrackingStrategy2, ViewTrackingStrategy viewTrackingStrategy2, TrackingStrategy trackingStrategy, EventMapper eventMapper, boolean z, int i, Object obj) {
                RUM rum2 = rum;
                int i2 = i;
                return rum.copy((i2 & 1) != 0 ? rum.getEndpointUrl() : str, (i2 & 2) != 0 ? rum.getPlugins() : list, (i2 & 4) != 0 ? rum2.samplingRate : f, (i2 & 8) != 0 ? rum2.telemetrySamplingRate : f2, (i2 & 16) != 0 ? rum2.userActionTrackingStrategy : userActionTrackingStrategy2, (i2 & 32) != 0 ? rum2.viewTrackingStrategy : viewTrackingStrategy2, (i2 & 64) != 0 ? rum2.longTaskTrackingStrategy : trackingStrategy, (i2 & 128) != 0 ? rum2.rumEventMapper : eventMapper, (i2 & 256) != 0 ? rum2.backgroundEventTracking : z);
            }

            public final String component1() {
                return getEndpointUrl();
            }

            public final List<DatadogPlugin> component2() {
                return getPlugins();
            }

            public final float component3() {
                return this.samplingRate;
            }

            public final float component4() {
                return this.telemetrySamplingRate;
            }

            public final UserActionTrackingStrategy component5() {
                return this.userActionTrackingStrategy;
            }

            public final ViewTrackingStrategy component6() {
                return this.viewTrackingStrategy;
            }

            public final TrackingStrategy component7() {
                return this.longTaskTrackingStrategy;
            }

            public final EventMapper<Object> component8() {
                return this.rumEventMapper;
            }

            public final boolean component9() {
                return this.backgroundEventTracking;
            }

            public final RUM copy(String str, List<? extends DatadogPlugin> list, float f, float f2, UserActionTrackingStrategy userActionTrackingStrategy2, ViewTrackingStrategy viewTrackingStrategy2, TrackingStrategy trackingStrategy, EventMapper<Object> eventMapper, boolean z) {
                Intrinsics.checkNotNullParameter(str, "endpointUrl");
                Intrinsics.checkNotNullParameter(list, "plugins");
                EventMapper<Object> eventMapper2 = eventMapper;
                Intrinsics.checkNotNullParameter(eventMapper2, "rumEventMapper");
                return new RUM(str, list, f, f2, userActionTrackingStrategy2, viewTrackingStrategy2, trackingStrategy, eventMapper2, z);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof RUM)) {
                    return false;
                }
                RUM rum = (RUM) obj;
                return Intrinsics.areEqual((Object) getEndpointUrl(), (Object) rum.getEndpointUrl()) && Intrinsics.areEqual((Object) getPlugins(), (Object) rum.getPlugins()) && Intrinsics.areEqual((Object) Float.valueOf(this.samplingRate), (Object) Float.valueOf(rum.samplingRate)) && Intrinsics.areEqual((Object) Float.valueOf(this.telemetrySamplingRate), (Object) Float.valueOf(rum.telemetrySamplingRate)) && Intrinsics.areEqual((Object) this.userActionTrackingStrategy, (Object) rum.userActionTrackingStrategy) && Intrinsics.areEqual((Object) this.viewTrackingStrategy, (Object) rum.viewTrackingStrategy) && Intrinsics.areEqual((Object) this.longTaskTrackingStrategy, (Object) rum.longTaskTrackingStrategy) && Intrinsics.areEqual((Object) this.rumEventMapper, (Object) rum.rumEventMapper) && this.backgroundEventTracking == rum.backgroundEventTracking;
            }

            public int hashCode() {
                int hashCode = ((((((getEndpointUrl().hashCode() * 31) + getPlugins().hashCode()) * 31) + Float.hashCode(this.samplingRate)) * 31) + Float.hashCode(this.telemetrySamplingRate)) * 31;
                UserActionTrackingStrategy userActionTrackingStrategy2 = this.userActionTrackingStrategy;
                int i = 0;
                int hashCode2 = (hashCode + (userActionTrackingStrategy2 == null ? 0 : userActionTrackingStrategy2.hashCode())) * 31;
                ViewTrackingStrategy viewTrackingStrategy2 = this.viewTrackingStrategy;
                int hashCode3 = (hashCode2 + (viewTrackingStrategy2 == null ? 0 : viewTrackingStrategy2.hashCode())) * 31;
                TrackingStrategy trackingStrategy = this.longTaskTrackingStrategy;
                if (trackingStrategy != null) {
                    i = trackingStrategy.hashCode();
                }
                int hashCode4 = (((hashCode3 + i) * 31) + this.rumEventMapper.hashCode()) * 31;
                boolean z = this.backgroundEventTracking;
                if (z) {
                    z = true;
                }
                return hashCode4 + (z ? 1 : 0);
            }

            public String toString() {
                String endpointUrl2 = getEndpointUrl();
                List<DatadogPlugin> plugins2 = getPlugins();
                float f = this.samplingRate;
                float f2 = this.telemetrySamplingRate;
                UserActionTrackingStrategy userActionTrackingStrategy2 = this.userActionTrackingStrategy;
                ViewTrackingStrategy viewTrackingStrategy2 = this.viewTrackingStrategy;
                TrackingStrategy trackingStrategy = this.longTaskTrackingStrategy;
                EventMapper<Object> eventMapper = this.rumEventMapper;
                return "RUM(endpointUrl=" + endpointUrl2 + ", plugins=" + plugins2 + ", samplingRate=" + f + ", telemetrySamplingRate=" + f2 + ", userActionTrackingStrategy=" + userActionTrackingStrategy2 + ", viewTrackingStrategy=" + viewTrackingStrategy2 + ", longTaskTrackingStrategy=" + trackingStrategy + ", rumEventMapper=" + eventMapper + ", backgroundEventTracking=" + this.backgroundEventTracking + ")";
            }

            public String getEndpointUrl() {
                return this.endpointUrl;
            }

            public List<DatadogPlugin> getPlugins() {
                return this.plugins;
            }

            public final float getSamplingRate() {
                return this.samplingRate;
            }

            public final float getTelemetrySamplingRate() {
                return this.telemetrySamplingRate;
            }

            public final UserActionTrackingStrategy getUserActionTrackingStrategy() {
                return this.userActionTrackingStrategy;
            }

            public final ViewTrackingStrategy getViewTrackingStrategy() {
                return this.viewTrackingStrategy;
            }

            public final TrackingStrategy getLongTaskTrackingStrategy() {
                return this.longTaskTrackingStrategy;
            }

            public final EventMapper<Object> getRumEventMapper() {
                return this.rumEventMapper;
            }

            public final boolean getBackgroundEventTracking() {
                return this.backgroundEventTracking;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public RUM(String str, List<? extends DatadogPlugin> list, float f, float f2, UserActionTrackingStrategy userActionTrackingStrategy2, ViewTrackingStrategy viewTrackingStrategy2, TrackingStrategy trackingStrategy, EventMapper<Object> eventMapper, boolean z) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "endpointUrl");
                Intrinsics.checkNotNullParameter(list, "plugins");
                Intrinsics.checkNotNullParameter(eventMapper, "rumEventMapper");
                this.endpointUrl = str;
                this.plugins = list;
                this.samplingRate = f;
                this.telemetrySamplingRate = f2;
                this.userActionTrackingStrategy = userActionTrackingStrategy2;
                this.viewTrackingStrategy = viewTrackingStrategy2;
                this.longTaskTrackingStrategy = trackingStrategy;
                this.rumEventMapper = eventMapper;
                this.backgroundEventTracking = z;
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0016\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J&\u0010%\u001a\u00020&2\u0006\u0010#\u001a\u00020$2\u0006\u0010'\u001a\u00020\n2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020&0)H\u0002J\u0006\u0010*\u001a\u00020+J\u0010\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020\nH\u0002J\b\u0010.\u001a\u00020/H\u0002J\u0010\u00100\u001a\u00020\u00002\b\b\u0001\u00101\u001a\u000202J\u0010\u00103\u001a\u00020\u00002\b\b\u0001\u00101\u001a\u000202J\u001a\u00104\u001a\u00020\u00002\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\tJ\u000e\u00105\u001a\u00020\u00002\u0006\u00106\u001a\u000207J\u0014\u00108\u001a\u00020\u00002\f\u00109\u001a\b\u0012\u0004\u0012\u00020\n0:J\u0018\u0010;\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u00020\nH\u0007J\u0014\u0010>\u001a\u00020\u00002\f\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@J\u0018\u0010B\u001a\u00020\u00002\u0006\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010FJ\u0014\u0010G\u001a\u00020\u00002\f\u0010?\u001a\b\u0012\u0004\u0012\u00020H0@J\u0014\u0010I\u001a\u00020\u00002\f\u0010?\u001a\b\u0012\u0004\u0012\u00020J0@J\u0014\u0010K\u001a\u00020\u00002\f\u0010?\u001a\b\u0012\u0004\u0012\u00020L0@J\u0014\u0010M\u001a\u00020\u00002\f\u0010?\u001a\b\u0012\u0004\u0012\u00020N0@J\u000e\u0010O\u001a\u00020\u00002\u0006\u0010?\u001a\u00020PJ\u0010\u0010Q\u001a\u00020\u00002\u0006\u0010R\u001a\u00020SH\u0002J\u000e\u0010T\u001a\u00020\u00002\u0006\u0010?\u001a\u00020UJ\u000e\u0010V\u001a\u00020\u00002\u0006\u0010W\u001a\u00020XJ\u000e\u0010Y\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020\u0003J\u0014\u0010[\u001a\u00020\u00002\f\u00109\u001a\b\u0012\u0004\u0012\u00020\n0:J\u000e\u0010\\\u001a\u00020\u00002\u0006\u0010]\u001a\u00020\u0003J'\u0010^\u001a\u00020\u00002\u000e\b\u0002\u0010_\u001a\b\u0012\u0004\u0012\u00020a0`2\b\b\u0002\u0010b\u001a\u00020cH\u0007¢\u0006\u0002\u0010dJ\u0012\u0010e\u001a\u00020\u00002\b\b\u0002\u0010f\u001a\u00020gH\u0007J\u000e\u0010h\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\nJ\u000e\u0010i\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\nJ\u000e\u0010j\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\nJ\u000e\u0010k\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\nJ\b\u0010l\u001a\u00020\u0000H\u0007J\b\u0010m\u001a\u00020\u0000H\u0007J\u000e\u0010n\u001a\u00020\u00002\u0006\u0010o\u001a\u00020pJ\b\u0010q\u001a\u00020\u0000H\u0007J\u000e\u0010r\u001a\u00020\u00002\u0006\u0010s\u001a\u00020tR\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u000e\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0010¨\u0006u"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Configuration$Builder;", "", "logsEnabled", "", "tracesEnabled", "crashReportsEnabled", "rumEnabled", "(ZZZZ)V", "additionalConfig", "", "", "coreConfig", "Lcom/datadog/android/core/configuration/Configuration$Core;", "crashReportConfig", "Lcom/datadog/android/core/configuration/Configuration$Feature$CrashReport;", "getCrashReportsEnabled", "()Z", "hostsSanitizer", "Lcom/datadog/android/core/configuration/HostsSanitizer;", "getHostsSanitizer$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/HostsSanitizer;", "setHostsSanitizer$dd_sdk_android_release", "(Lcom/datadog/android/core/configuration/HostsSanitizer;)V", "logsConfig", "Lcom/datadog/android/core/configuration/Configuration$Feature$Logs;", "getLogsEnabled", "rumConfig", "Lcom/datadog/android/core/configuration/Configuration$Feature$RUM;", "getRumEnabled", "tracesConfig", "Lcom/datadog/android/core/configuration/Configuration$Feature$Tracing;", "getTracesEnabled", "addPlugin", "plugin", "Lcom/datadog/android/plugin/DatadogPlugin;", "feature", "Lcom/datadog/android/plugin/Feature;", "applyIfFeatureEnabled", "", "method", "block", "Lkotlin/Function0;", "build", "Lcom/datadog/android/core/configuration/Configuration;", "checkCustomEndpoint", "endpoint", "getRumEventMapper", "Lcom/datadog/android/rum/internal/domain/event/RumEventMapper;", "sampleRumSessions", "samplingRate", "", "sampleTelemetry", "setAdditionalConfiguration", "setBatchSize", "batchSize", "Lcom/datadog/android/core/configuration/BatchSize;", "setFirstPartyHosts", "hosts", "", "setInternalLogsEnabled", "clientToken", "endpointUrl", "setLogEventMapper", "eventMapper", "Lcom/datadog/android/event/EventMapper;", "Lcom/datadog/android/log/model/LogEvent;", "setProxy", "proxy", "Ljava/net/Proxy;", "authenticator", "Lokhttp3/Authenticator;", "setRumActionEventMapper", "Lcom/datadog/android/rum/model/ActionEvent;", "setRumErrorEventMapper", "Lcom/datadog/android/rum/model/ErrorEvent;", "setRumLongTaskEventMapper", "Lcom/datadog/android/rum/model/LongTaskEvent;", "setRumResourceEventMapper", "Lcom/datadog/android/rum/model/ResourceEvent;", "setRumViewEventMapper", "Lcom/datadog/android/event/ViewEventMapper;", "setSecurityConfig", "config", "Lcom/datadog/android/core/configuration/SecurityConfig;", "setSpanEventMapper", "Lcom/datadog/android/event/SpanEventMapper;", "setUploadFrequency", "uploadFrequency", "Lcom/datadog/android/core/configuration/UploadFrequency;", "setUseDeveloperModeWhenDebuggable", "developerModeEnabled", "setWebViewTrackingHosts", "trackBackgroundRumEvents", "enabled", "trackInteractions", "touchTargetExtraAttributesProviders", "", "Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "interactionPredicate", "Lcom/datadog/android/rum/tracking/InteractionPredicate;", "([Lcom/datadog/android/rum/tracking/ViewAttributesProvider;Lcom/datadog/android/rum/tracking/InteractionPredicate;)Lcom/datadog/android/core/configuration/Configuration$Builder;", "trackLongTasks", "longTaskThresholdMs", "", "useCustomCrashReportsEndpoint", "useCustomLogsEndpoint", "useCustomRumEndpoint", "useCustomTracesEndpoint", "useEUEndpoints", "useGovEndpoints", "useSite", "site", "Lcom/datadog/android/DatadogSite;", "useUSEndpoints", "useViewTrackingStrategy", "strategy", "Lcom/datadog/android/rum/tracking/ViewTrackingStrategy;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Configuration.kt */
    public static final class Builder {
        private Map<String, ? extends Object> additionalConfig = MapsKt.emptyMap();
        private Core coreConfig = Configuration.Companion.getDEFAULT_CORE_CONFIG$dd_sdk_android_release();
        /* access modifiers changed from: private */
        public Feature.CrashReport crashReportConfig = Configuration.Companion.getDEFAULT_CRASH_CONFIG$dd_sdk_android_release();
        private final boolean crashReportsEnabled;
        private HostsSanitizer hostsSanitizer = new HostsSanitizer();
        /* access modifiers changed from: private */
        public Feature.Logs logsConfig = Configuration.Companion.getDEFAULT_LOGS_CONFIG$dd_sdk_android_release();
        private final boolean logsEnabled;
        /* access modifiers changed from: private */
        public Feature.RUM rumConfig = Configuration.Companion.getDEFAULT_RUM_CONFIG$dd_sdk_android_release();
        private final boolean rumEnabled;
        /* access modifiers changed from: private */
        public Feature.Tracing tracesConfig = Configuration.Companion.getDEFAULT_TRACING_CONFIG$dd_sdk_android_release();
        private final boolean tracesEnabled;

        @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: Configuration.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[com.datadog.android.plugin.Feature.values().length];
                iArr[com.datadog.android.plugin.Feature.LOG.ordinal()] = 1;
                iArr[com.datadog.android.plugin.Feature.TRACE.ordinal()] = 2;
                iArr[com.datadog.android.plugin.Feature.CRASH.ordinal()] = 3;
                iArr[com.datadog.android.plugin.Feature.RUM.ordinal()] = 4;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public final Builder trackInteractions() {
            return trackInteractions$default(this, (ViewAttributesProvider[]) null, (InteractionPredicate) null, 3, (Object) null);
        }

        public final Builder trackInteractions(ViewAttributesProvider[] viewAttributesProviderArr) {
            Intrinsics.checkNotNullParameter(viewAttributesProviderArr, "touchTargetExtraAttributesProviders");
            return trackInteractions$default(this, viewAttributesProviderArr, (InteractionPredicate) null, 2, (Object) null);
        }

        public final Builder trackLongTasks() {
            return trackLongTasks$default(this, 0, 1, (Object) null);
        }

        public Builder(boolean z, boolean z2, boolean z3, boolean z4) {
            this.logsEnabled = z;
            this.tracesEnabled = z2;
            this.crashReportsEnabled = z3;
            this.rumEnabled = z4;
        }

        public final boolean getLogsEnabled() {
            return this.logsEnabled;
        }

        public final boolean getTracesEnabled() {
            return this.tracesEnabled;
        }

        public final boolean getCrashReportsEnabled() {
            return this.crashReportsEnabled;
        }

        public final boolean getRumEnabled() {
            return this.rumEnabled;
        }

        public final HostsSanitizer getHostsSanitizer$dd_sdk_android_release() {
            return this.hostsSanitizer;
        }

        public final void setHostsSanitizer$dd_sdk_android_release(HostsSanitizer hostsSanitizer2) {
            Intrinsics.checkNotNullParameter(hostsSanitizer2, "<set-?>");
            this.hostsSanitizer = hostsSanitizer2;
        }

        public final Configuration build() {
            return new Configuration(this.coreConfig, this.logsEnabled ? this.logsConfig : null, this.tracesEnabled ? this.tracesConfig : null, this.crashReportsEnabled ? this.crashReportConfig : null, this.rumEnabled ? this.rumConfig : null, this.additionalConfig);
        }

        public final Builder setUseDeveloperModeWhenDebuggable(boolean z) {
            this.coreConfig = Core.copy$default(this.coreConfig, false, z, (List) null, (BatchSize) null, (UploadFrequency) null, (Proxy) null, (Authenticator) null, (SecurityConfig) null, (List) null, 509, (Object) null);
            return this;
        }

        public final Builder setFirstPartyHosts(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "hosts");
            this.coreConfig = Core.copy$default(this.coreConfig, false, false, this.hostsSanitizer.sanitizeHosts$dd_sdk_android_release(list, Configuration.NETWORK_REQUESTS_TRACKING_FEATURE_NAME), (BatchSize) null, (UploadFrequency) null, (Proxy) null, (Authenticator) null, (SecurityConfig) null, (List) null, 507, (Object) null);
            return this;
        }

        public final Builder setWebViewTrackingHosts(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "hosts");
            this.coreConfig = Core.copy$default(this.coreConfig, false, false, (List) null, (BatchSize) null, (UploadFrequency) null, (Proxy) null, (Authenticator) null, (SecurityConfig) null, this.hostsSanitizer.sanitizeHosts$dd_sdk_android_release(list, Configuration.WEB_VIEW_TRACKING_FEATURE_NAME), 255, (Object) null);
            return this;
        }

        public final Builder useSite(DatadogSite datadogSite) {
            Intrinsics.checkNotNullParameter(datadogSite, Config.SITE);
            this.logsConfig = Feature.Logs.copy$default(this.logsConfig, datadogSite.logsEndpoint(), (List) null, (EventMapper) null, 6, (Object) null);
            this.tracesConfig = Feature.Tracing.copy$default(this.tracesConfig, datadogSite.tracesEndpoint(), (List) null, (SpanEventMapper) null, 6, (Object) null);
            this.crashReportConfig = Feature.CrashReport.copy$default(this.crashReportConfig, datadogSite.logsEndpoint(), (List) null, 2, (Object) null);
            this.rumConfig = Feature.RUM.copy$default(this.rumConfig, datadogSite.rumEndpoint(), (List) null, 0.0f, 0.0f, (UserActionTrackingStrategy) null, (ViewTrackingStrategy) null, (TrackingStrategy) null, (EventMapper) null, false, 510, (Object) null);
            this.coreConfig = Core.copy$default(this.coreConfig, false, false, (List) null, (BatchSize) null, (UploadFrequency) null, (Proxy) null, (Authenticator) null, (SecurityConfig) null, (List) null, 510, (Object) null);
            return this;
        }

        @Deprecated(message = "Use the `useSite()` method instead.", replaceWith = @ReplaceWith(expression = "useSite(DatadogSite.EU1)", imports = {"com.datadog.android.DatadogSite"}))
        public final Builder useEUEndpoints() {
            RuntimeUtilsKt.warnDeprecated("Configuration.Builder.useEUEndpoints()", "1.10.0", "1.12.0", "Configuration.Builder.useSite(DatadogSite.EU1)");
            this.logsConfig = Feature.Logs.copy$default(this.logsConfig, "https://mobile-http-intake.logs.datadoghq.eu", (List) null, (EventMapper) null, 6, (Object) null);
            this.tracesConfig = Feature.Tracing.copy$default(this.tracesConfig, "https:/public-trace-http-intake.logs.datadoghq.eu", (List) null, (SpanEventMapper) null, 6, (Object) null);
            this.crashReportConfig = Feature.CrashReport.copy$default(this.crashReportConfig, "https://mobile-http-intake.logs.datadoghq.eu", (List) null, 2, (Object) null);
            this.rumConfig = Feature.RUM.copy$default(this.rumConfig, "https://rum-http-intake.logs.datadoghq.eu", (List) null, 0.0f, 0.0f, (UserActionTrackingStrategy) null, (ViewTrackingStrategy) null, (TrackingStrategy) null, (EventMapper) null, false, 510, (Object) null);
            this.coreConfig = Core.copy$default(this.coreConfig, false, false, (List) null, (BatchSize) null, (UploadFrequency) null, (Proxy) null, (Authenticator) null, (SecurityConfig) null, (List) null, 510, (Object) null);
            return this;
        }

        @Deprecated(message = "Use the `useSite()` method instead.", replaceWith = @ReplaceWith(expression = "useSite(DatadogSite.US1)", imports = {"com.datadog.android.DatadogSite"}))
        public final Builder useUSEndpoints() {
            RuntimeUtilsKt.warnDeprecated("Configuration.Builder.useUSEndpoints()", "1.10.0", "1.12.0", "Configuration.Builder.useSite(DatadogSite.US1)");
            this.logsConfig = Feature.Logs.copy$default(this.logsConfig, "https://logs.browser-intake-datadoghq.com", (List) null, (EventMapper) null, 6, (Object) null);
            this.tracesConfig = Feature.Tracing.copy$default(this.tracesConfig, "https://trace.browser-intake-datadoghq.com", (List) null, (SpanEventMapper) null, 6, (Object) null);
            this.crashReportConfig = Feature.CrashReport.copy$default(this.crashReportConfig, "https://logs.browser-intake-datadoghq.com", (List) null, 2, (Object) null);
            this.rumConfig = Feature.RUM.copy$default(this.rumConfig, "https://rum.browser-intake-datadoghq.com", (List) null, 0.0f, 0.0f, (UserActionTrackingStrategy) null, (ViewTrackingStrategy) null, (TrackingStrategy) null, (EventMapper) null, false, 510, (Object) null);
            this.coreConfig = Core.copy$default(this.coreConfig, false, false, (List) null, (BatchSize) null, (UploadFrequency) null, (Proxy) null, (Authenticator) null, (SecurityConfig) null, (List) null, 510, (Object) null);
            return this;
        }

        @Deprecated(message = "Use the `useSite()` method instead.", replaceWith = @ReplaceWith(expression = "useSite(DatadogSite.US1_FED)", imports = {"com.datadog.android.DatadogSite"}))
        public final Builder useGovEndpoints() {
            RuntimeUtilsKt.warnDeprecated("Configuration.Builder.useGovEndpoints()", "1.10.0", "1.12.0", "Configuration.Builder.useSite(DatadogSite.US1_FED)");
            this.logsConfig = Feature.Logs.copy$default(this.logsConfig, "https://logs.browser-intake-ddog-gov.com", (List) null, (EventMapper) null, 6, (Object) null);
            this.tracesConfig = Feature.Tracing.copy$default(this.tracesConfig, "https://trace.browser-intake-ddog-gov.com", (List) null, (SpanEventMapper) null, 6, (Object) null);
            this.crashReportConfig = Feature.CrashReport.copy$default(this.crashReportConfig, "https://logs.browser-intake-ddog-gov.com", (List) null, 2, (Object) null);
            this.rumConfig = Feature.RUM.copy$default(this.rumConfig, "https://rum.browser-intake-ddog-gov.com", (List) null, 0.0f, 0.0f, (UserActionTrackingStrategy) null, (ViewTrackingStrategy) null, (TrackingStrategy) null, (EventMapper) null, false, 510, (Object) null);
            this.coreConfig = Core.copy$default(this.coreConfig, false, false, (List) null, (BatchSize) null, (UploadFrequency) null, (Proxy) null, (Authenticator) null, (SecurityConfig) null, (List) null, 510, (Object) null);
            return this;
        }

        public final Builder useCustomLogsEndpoint(String str) {
            Intrinsics.checkNotNullParameter(str, "endpoint");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.LOG, "useCustomLogsEndpoint", new Configuration$Builder$useCustomLogsEndpoint$1(this, str));
            return this;
        }

        public final Builder useCustomTracesEndpoint(String str) {
            Intrinsics.checkNotNullParameter(str, "endpoint");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.TRACE, "useCustomTracesEndpoint", new Configuration$Builder$useCustomTracesEndpoint$1(this, str));
            return this;
        }

        public final Builder useCustomCrashReportsEndpoint(String str) {
            Intrinsics.checkNotNullParameter(str, "endpoint");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.CRASH, "useCustomCrashReportsEndpoint", new Configuration$Builder$useCustomCrashReportsEndpoint$1(this, str));
            return this;
        }

        public final Builder useCustomRumEndpoint(String str) {
            Intrinsics.checkNotNullParameter(str, "endpoint");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "useCustomRumEndpoint", new Configuration$Builder$useCustomRumEndpoint$1(this, str));
            return this;
        }

        public static /* synthetic */ Builder trackInteractions$default(Builder builder, ViewAttributesProvider[] viewAttributesProviderArr, InteractionPredicate interactionPredicate, int i, Object obj) {
            if ((i & 1) != 0) {
                viewAttributesProviderArr = new ViewAttributesProvider[0];
            }
            if ((i & 2) != 0) {
                interactionPredicate = new NoOpInteractionPredicate();
            }
            return builder.trackInteractions(viewAttributesProviderArr, interactionPredicate);
        }

        public final Builder trackInteractions(ViewAttributesProvider[] viewAttributesProviderArr, InteractionPredicate interactionPredicate) {
            Intrinsics.checkNotNullParameter(viewAttributesProviderArr, "touchTargetExtraAttributesProviders");
            Intrinsics.checkNotNullParameter(interactionPredicate, "interactionPredicate");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "trackInteractions", new Configuration$Builder$trackInteractions$1(this, Configuration.Companion.provideUserTrackingStrategy(viewAttributesProviderArr, interactionPredicate)));
            return this;
        }

        public static /* synthetic */ Builder trackLongTasks$default(Builder builder, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = 100;
            }
            return builder.trackLongTasks(j);
        }

        public final Builder trackLongTasks(long j) {
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "trackLongTasks", new Configuration$Builder$trackLongTasks$1(this, j));
            return this;
        }

        public final Builder useViewTrackingStrategy(ViewTrackingStrategy viewTrackingStrategy) {
            Intrinsics.checkNotNullParameter(viewTrackingStrategy, "strategy");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "useViewTrackingStrategy", new Configuration$Builder$useViewTrackingStrategy$1(this, viewTrackingStrategy));
            return this;
        }

        public final Builder addPlugin(DatadogPlugin datadogPlugin, com.datadog.android.plugin.Feature feature) {
            Intrinsics.checkNotNullParameter(datadogPlugin, "plugin");
            Intrinsics.checkNotNullParameter(feature, "feature");
            applyIfFeatureEnabled(feature, "addPlugin", new Configuration$Builder$addPlugin$1(feature, this, datadogPlugin));
            return this;
        }

        public final Builder setBatchSize(BatchSize batchSize) {
            Intrinsics.checkNotNullParameter(batchSize, "batchSize");
            this.coreConfig = Core.copy$default(this.coreConfig, false, false, (List) null, batchSize, (UploadFrequency) null, (Proxy) null, (Authenticator) null, (SecurityConfig) null, (List) null, DataOkHttpUploaderV2.HTTP_UNAVAILABLE, (Object) null);
            return this;
        }

        public final Builder setUploadFrequency(UploadFrequency uploadFrequency) {
            Intrinsics.checkNotNullParameter(uploadFrequency, "uploadFrequency");
            this.coreConfig = Core.copy$default(this.coreConfig, false, false, (List) null, (BatchSize) null, uploadFrequency, (Proxy) null, (Authenticator) null, (SecurityConfig) null, (List) null, 495, (Object) null);
            return this;
        }

        public final Builder sampleRumSessions(float f) {
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "sampleRumSessions", new Configuration$Builder$sampleRumSessions$1(this, f));
            return this;
        }

        public final Builder sampleTelemetry(float f) {
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "sampleTelemetry", new Configuration$Builder$sampleTelemetry$1(this, f));
            return this;
        }

        public final Builder trackBackgroundRumEvents(boolean z) {
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "trackBackgroundRumEvents", new Configuration$Builder$trackBackgroundRumEvents$1(this, z));
            return this;
        }

        public final Builder setRumViewEventMapper(ViewEventMapper viewEventMapper) {
            Intrinsics.checkNotNullParameter(viewEventMapper, "eventMapper");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "setRumViewEventMapper", new Configuration$Builder$setRumViewEventMapper$1(this, viewEventMapper));
            return this;
        }

        public final Builder setRumResourceEventMapper(EventMapper<ResourceEvent> eventMapper) {
            Intrinsics.checkNotNullParameter(eventMapper, "eventMapper");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "setRumResourceEventMapper", new Configuration$Builder$setRumResourceEventMapper$1(this, eventMapper));
            return this;
        }

        public final Builder setRumActionEventMapper(EventMapper<ActionEvent> eventMapper) {
            Intrinsics.checkNotNullParameter(eventMapper, "eventMapper");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "setRumActionEventMapper", new Configuration$Builder$setRumActionEventMapper$1(this, eventMapper));
            return this;
        }

        public final Builder setRumErrorEventMapper(EventMapper<ErrorEvent> eventMapper) {
            Intrinsics.checkNotNullParameter(eventMapper, "eventMapper");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "setRumErrorEventMapper", new Configuration$Builder$setRumErrorEventMapper$1(this, eventMapper));
            return this;
        }

        public final Builder setRumLongTaskEventMapper(EventMapper<LongTaskEvent> eventMapper) {
            Intrinsics.checkNotNullParameter(eventMapper, "eventMapper");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.RUM, "setRumLongTaskEventMapper", new Configuration$Builder$setRumLongTaskEventMapper$1(this, eventMapper));
            return this;
        }

        public final Builder setSpanEventMapper(SpanEventMapper spanEventMapper) {
            Intrinsics.checkNotNullParameter(spanEventMapper, "eventMapper");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.TRACE, "setSpanEventMapper", new Configuration$Builder$setSpanEventMapper$1(this, spanEventMapper));
            return this;
        }

        public final Builder setLogEventMapper(EventMapper<LogEvent> eventMapper) {
            Intrinsics.checkNotNullParameter(eventMapper, "eventMapper");
            applyIfFeatureEnabled(com.datadog.android.plugin.Feature.LOG, "setLogEventMapper", new Configuration$Builder$setLogEventMapper$1(this, eventMapper));
            return this;
        }

        @Deprecated(message = "This method has no effect and will be removed in the next version.")
        public final Builder setInternalLogsEnabled(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "clientToken");
            Intrinsics.checkNotNullParameter(str2, "endpointUrl");
            RuntimeUtilsKt.warnDeprecated$default("Configuration.Builder.setInternalLogsEnabled()", "1.13.0", "1.14.0", (String) null, 8, (Object) null);
            return this;
        }

        public final Builder setAdditionalConfiguration(Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalConfig");
            Builder builder = this;
            builder.additionalConfig = map;
            return builder;
        }

        public final Builder setProxy(Proxy proxy, Authenticator authenticator) {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Core core = this.coreConfig;
            if (authenticator == null) {
                authenticator = Authenticator.NONE;
            }
            Authenticator authenticator2 = authenticator;
            Intrinsics.checkNotNullExpressionValue(authenticator2, "authenticator ?: Authenticator.NONE");
            this.coreConfig = Core.copy$default(core, false, false, (List) null, (BatchSize) null, (UploadFrequency) null, proxy, authenticator2, (SecurityConfig) null, (List) null, 415, (Object) null);
            return this;
        }

        private final Builder setSecurityConfig(SecurityConfig securityConfig) {
            this.coreConfig = Core.copy$default(this.coreConfig, false, false, (List) null, (BatchSize) null, (UploadFrequency) null, (Proxy) null, (Authenticator) null, securityConfig, (List) null, 383, (Object) null);
            return this;
        }

        /* access modifiers changed from: private */
        public final void checkCustomEndpoint(String str) {
            if (StringsKt.startsWith$default(str, "http://", false, 2, (Object) null)) {
                this.coreConfig = Core.copy$default(this.coreConfig, true, false, (List) null, (BatchSize) null, (UploadFrequency) null, (Proxy) null, (Authenticator) null, (SecurityConfig) null, (List) null, 510, (Object) null);
            }
        }

        /* access modifiers changed from: private */
        public final RumEventMapper getRumEventMapper() {
            EventMapper<Object> rumEventMapper = this.rumConfig.getRumEventMapper();
            if (rumEventMapper instanceof RumEventMapper) {
                return (RumEventMapper) rumEventMapper;
            }
            return new RumEventMapper((EventMapper) null, (EventMapper) null, (EventMapper) null, (EventMapper) null, (EventMapper) null, 31, (DefaultConstructorMarker) null);
        }

        private final void applyIfFeatureEnabled(com.datadog.android.plugin.Feature feature, String str, Function0<Unit> function0) {
            boolean z;
            int i = WhenMappings.$EnumSwitchMapping$0[feature.ordinal()];
            if (i == 1) {
                z = this.logsEnabled;
            } else if (i == 2) {
                z = this.tracesEnabled;
            } else if (i == 3) {
                z = this.crashReportsEnabled;
            } else if (i == 4) {
                z = this.rumEnabled;
            } else {
                throw new NoWhenBranchMatchedException();
            }
            if (z) {
                function0.invoke();
                return;
            }
            Logger devLogger = RuntimeUtilsKt.getDevLogger();
            String format = String.format(Locale.US, Configuration.ERROR_FEATURE_DISABLED, Arrays.copyOf(new Object[]{feature.getFeatureName$dd_sdk_android_release(), str}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            Logger.e$default(devLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
        }
    }

    @Metadata(mo20734d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010%\u001a\u00020&H\u0002¢\u0006\u0002\u0010'J#\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010%\u001a\u00020&H\u0002¢\u0006\u0002\u0010+R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001dXT¢\u0006\u0002\n\u0000¨\u0006,"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/Configuration$Companion;", "", "()V", "DEFAULT_CORE_CONFIG", "Lcom/datadog/android/core/configuration/Configuration$Core;", "getDEFAULT_CORE_CONFIG$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Core;", "DEFAULT_CRASH_CONFIG", "Lcom/datadog/android/core/configuration/Configuration$Feature$CrashReport;", "getDEFAULT_CRASH_CONFIG$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Feature$CrashReport;", "DEFAULT_LOGS_CONFIG", "Lcom/datadog/android/core/configuration/Configuration$Feature$Logs;", "getDEFAULT_LOGS_CONFIG$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Feature$Logs;", "DEFAULT_LONG_TASK_THRESHOLD_MS", "", "DEFAULT_RUM_CONFIG", "Lcom/datadog/android/core/configuration/Configuration$Feature$RUM;", "getDEFAULT_RUM_CONFIG$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Feature$RUM;", "DEFAULT_SAMPLING_RATE", "", "DEFAULT_TELEMETRY_SAMPLING_RATE", "DEFAULT_TRACING_CONFIG", "Lcom/datadog/android/core/configuration/Configuration$Feature$Tracing;", "getDEFAULT_TRACING_CONFIG$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/Configuration$Feature$Tracing;", "ERROR_FEATURE_DISABLED", "", "NETWORK_REQUESTS_TRACKING_FEATURE_NAME", "WEB_VIEW_TRACKING_FEATURE_NAME", "provideGestureTracker", "Lcom/datadog/android/rum/internal/instrumentation/gestures/DatadogGesturesTracker;", "customProviders", "", "Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "interactionPredicate", "Lcom/datadog/android/rum/tracking/InteractionPredicate;", "([Lcom/datadog/android/rum/tracking/ViewAttributesProvider;Lcom/datadog/android/rum/tracking/InteractionPredicate;)Lcom/datadog/android/rum/internal/instrumentation/gestures/DatadogGesturesTracker;", "provideUserTrackingStrategy", "Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;", "touchTargetExtraAttributesProviders", "([Lcom/datadog/android/rum/tracking/ViewAttributesProvider;Lcom/datadog/android/rum/tracking/InteractionPredicate;)Lcom/datadog/android/rum/internal/tracking/UserActionTrackingStrategy;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Configuration.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Core getDEFAULT_CORE_CONFIG$dd_sdk_android_release() {
            return Configuration.DEFAULT_CORE_CONFIG;
        }

        public final Feature.Logs getDEFAULT_LOGS_CONFIG$dd_sdk_android_release() {
            return Configuration.DEFAULT_LOGS_CONFIG;
        }

        public final Feature.CrashReport getDEFAULT_CRASH_CONFIG$dd_sdk_android_release() {
            return Configuration.DEFAULT_CRASH_CONFIG;
        }

        public final Feature.Tracing getDEFAULT_TRACING_CONFIG$dd_sdk_android_release() {
            return Configuration.DEFAULT_TRACING_CONFIG;
        }

        public final Feature.RUM getDEFAULT_RUM_CONFIG$dd_sdk_android_release() {
            return Configuration.DEFAULT_RUM_CONFIG;
        }

        /* access modifiers changed from: private */
        public final UserActionTrackingStrategy provideUserTrackingStrategy(ViewAttributesProvider[] viewAttributesProviderArr, InteractionPredicate interactionPredicate) {
            DatadogGesturesTracker provideGestureTracker = provideGestureTracker(viewAttributesProviderArr, interactionPredicate);
            if (Build.VERSION.SDK_INT >= 29) {
                return new UserActionTrackingStrategyApi29(provideGestureTracker);
            }
            return new UserActionTrackingStrategyLegacy(provideGestureTracker);
        }

        private final DatadogGesturesTracker provideGestureTracker(ViewAttributesProvider[] viewAttributesProviderArr, InteractionPredicate interactionPredicate) {
            return new DatadogGesturesTracker((ViewAttributesProvider[]) ArraysKt.plus((T[]) viewAttributesProviderArr, (T[]) new JetpackViewAttributesProvider[]{new JetpackViewAttributesProvider()}), interactionPredicate);
        }
    }

    static {
        Companion companion = new Companion((DefaultConstructorMarker) null);
        Companion = companion;
        List emptyList = CollectionsKt.emptyList();
        BatchSize batchSize = BatchSize.MEDIUM;
        UploadFrequency uploadFrequency = UploadFrequency.AVERAGE;
        Authenticator authenticator = Authenticator.NONE;
        Intrinsics.checkNotNullExpressionValue(authenticator, "NONE");
        DEFAULT_CORE_CONFIG = new Core(false, false, emptyList, batchSize, uploadFrequency, (Proxy) null, authenticator, SecurityConfig.Companion.getDEFAULT$dd_sdk_android_release(), CollectionsKt.emptyList());
        DEFAULT_RUM_CONFIG = new Feature.RUM("https://rum.browser-intake-datadoghq.com", CollectionsKt.emptyList(), 100.0f, 20.0f, companion.provideUserTrackingStrategy(new ViewAttributesProvider[0], new NoOpInteractionPredicate()), new ActivityViewTrackingStrategy(false, (ComponentPredicate) null, 2, (DefaultConstructorMarker) null), new MainLooperLongTaskStrategy(100), new NoOpEventMapper(), false);
    }
}
