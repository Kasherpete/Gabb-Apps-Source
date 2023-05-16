package com.datadog.android.log.internal.domain;

import com.datadog.android.core.internal.net.info.NetworkInfoProvider;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.core.model.NetworkInfo;
import com.datadog.android.core.model.UserInfo;
import com.datadog.android.log.LogAttributes;
import com.datadog.android.log.internal.user.UserInfoProvider;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.log.model.LogEvent;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.webview.internal.log.WebViewLogEventConsumer;
import com.datadog.trace.api.Config;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import p006io.opentracing.Span;
import p006io.opentracing.SpanContext;
import p006io.opentracing.util.GlobalTracer;

@Metadata(mo20734d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 ?2\u00020\u0001:\u0001?BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\u0001\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010$2\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030(2\u0006\u0010)\u001a\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020-2\n\b\u0002\u0010/\u001a\u0004\u0018\u0001002\n\b\u0002\u00101\u001a\u0004\u0018\u000102J<\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0001042\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010&2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0002J\u0010\u00105\u001a\u0002062\u0006\u0010 \u001a\u00020!H\u0002J\u0014\u00107\u001a\u0004\u0018\u0001082\b\u00101\u001a\u0004\u0018\u000102H\u0002J\u0012\u00109\u001a\u0004\u0018\u00010:2\u0006\u00101\u001a\u000202H\u0002J\u001c\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00030<2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030(H\u0002J\u0012\u0010=\u001a\u00020>2\b\u0010/\u001a\u0004\u0018\u000100H\u0002R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u000b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006@"}, mo20735d2 = {"Lcom/datadog/android/log/internal/domain/LogGenerator;", "", "serviceName", "", "loggerName", "networkInfoProvider", "Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;", "userInfoProvider", "Lcom/datadog/android/log/internal/user/UserInfoProvider;", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "sdkVersion", "envName", "appVersion", "(Ljava/lang/String;Ljava/lang/String;Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;Lcom/datadog/android/log/internal/user/UserInfoProvider;Lcom/datadog/android/core/internal/time/TimeProvider;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "appVersionTag", "envTag", "getEnvTag$dd_sdk_android_release", "()Ljava/lang/String;", "getLoggerName$dd_sdk_android_release", "getNetworkInfoProvider$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;", "getSdkVersion$dd_sdk_android_release", "getServiceName$dd_sdk_android_release", "simpleDateFormat", "Ljava/text/SimpleDateFormat;", "getTimeProvider$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/time/TimeProvider;", "getUserInfoProvider$dd_sdk_android_release", "()Lcom/datadog/android/log/internal/user/UserInfoProvider;", "generateLog", "Lcom/datadog/android/log/model/LogEvent;", "level", "", "message", "throwable", "", "attributes", "", "tags", "", "timestamp", "", "threadName", "bundleWithTraces", "", "bundleWithRum", "userInfo", "Lcom/datadog/android/core/model/UserInfo;", "networkInfo", "Lcom/datadog/android/core/model/NetworkInfo;", "resolveAttributes", "", "resolveLogLevelStatus", "Lcom/datadog/android/log/model/LogEvent$Status;", "resolveNetworkInfo", "Lcom/datadog/android/log/model/LogEvent$Network;", "resolveSimCarrier", "Lcom/datadog/android/log/model/LogEvent$SimCarrier;", "resolveTags", "", "resolveUserInfo", "Lcom/datadog/android/log/model/LogEvent$Usr;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LogGenerator.kt */
public final class LogGenerator {
    public static final int CRASH = 9;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private final String appVersionTag;
    private final String envTag;
    private final String loggerName;
    private final NetworkInfoProvider networkInfoProvider;
    private final String sdkVersion;
    private final String serviceName;
    private final SimpleDateFormat simpleDateFormat = LogUtilsKt.buildLogDateFormat();
    private final TimeProvider timeProvider;
    private final UserInfoProvider userInfoProvider;

    public LogGenerator(String str, String str2, NetworkInfoProvider networkInfoProvider2, UserInfoProvider userInfoProvider2, TimeProvider timeProvider2, String str3, String str4, String str5) {
        String str6;
        String str7;
        Intrinsics.checkNotNullParameter(str, "serviceName");
        Intrinsics.checkNotNullParameter(str2, "loggerName");
        Intrinsics.checkNotNullParameter(userInfoProvider2, "userInfoProvider");
        Intrinsics.checkNotNullParameter(timeProvider2, "timeProvider");
        Intrinsics.checkNotNullParameter(str3, "sdkVersion");
        Intrinsics.checkNotNullParameter(str4, "envName");
        Intrinsics.checkNotNullParameter(str5, "appVersion");
        this.serviceName = str;
        this.loggerName = str2;
        this.networkInfoProvider = networkInfoProvider2;
        this.userInfoProvider = userInfoProvider2;
        this.timeProvider = timeProvider2;
        this.sdkVersion = str3;
        boolean z = true;
        if (str4.length() > 0) {
            str6 = "env:" + str4;
        } else {
            str6 = null;
        }
        this.envTag = str6;
        if (str5.length() <= 0 ? false : z) {
            str7 = "version:" + str5;
        } else {
            str7 = null;
        }
        this.appVersionTag = str7;
    }

    public final String getServiceName$dd_sdk_android_release() {
        return this.serviceName;
    }

    public final String getLoggerName$dd_sdk_android_release() {
        return this.loggerName;
    }

    public final NetworkInfoProvider getNetworkInfoProvider$dd_sdk_android_release() {
        return this.networkInfoProvider;
    }

    public final UserInfoProvider getUserInfoProvider$dd_sdk_android_release() {
        return this.userInfoProvider;
    }

    public final TimeProvider getTimeProvider$dd_sdk_android_release() {
        return this.timeProvider;
    }

    public final String getSdkVersion$dd_sdk_android_release() {
        return this.sdkVersion;
    }

    public final String getEnvTag$dd_sdk_android_release() {
        return this.envTag;
    }

    public static /* synthetic */ LogEvent generateLog$default(LogGenerator logGenerator, int i, String str, Throwable th, Map map, Set set, long j, String str2, boolean z, boolean z2, UserInfo userInfo, NetworkInfo networkInfo, int i2, Object obj) {
        int i3 = i2;
        return logGenerator.generateLog(i, str, th, map, set, j, (i3 & 64) != 0 ? null : str2, (i3 & 128) != 0 ? true : z, (i3 & 256) != 0 ? true : z2, (i3 & 512) != 0 ? null : userInfo, (i3 & 1024) != 0 ? null : networkInfo);
    }

    public final LogEvent generateLog(int i, String str, Throwable th, Map<String, ? extends Object> map, Set<String> set, long j, String str2, boolean z, boolean z2, UserInfo userInfo, NetworkInfo networkInfo) {
        String format;
        LogEvent.Error error;
        UserInfo userInfo2;
        Map<String, ? extends Object> map2 = map;
        Set<String> set2 = set;
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map2, "attributes");
        Intrinsics.checkNotNullParameter(set2, Config.TAGS);
        long serverOffsetMillis = j + this.timeProvider.getServerOffsetMillis();
        Map<String, Object> resolveAttributes = resolveAttributes(map2, z, z2);
        synchronized (this.simpleDateFormat) {
            format = this.simpleDateFormat.format(new Date(serverOffsetMillis));
        }
        Set<String> resolveTags = resolveTags(set2);
        if (th == null) {
            error = null;
            userInfo2 = userInfo;
        } else {
            String canonicalName = th.getClass().getCanonicalName();
            if (canonicalName == null) {
                canonicalName = th.getClass().getSimpleName();
            }
            LogEvent.Error error2 = new LogEvent.Error(canonicalName, th.getMessage(), ExceptionsKt.stackTraceToString(th));
            userInfo2 = userInfo;
            error = error2;
        }
        LogEvent.Usr resolveUserInfo = resolveUserInfo(userInfo2);
        LogEvent.Network resolveNetworkInfo = resolveNetworkInfo(networkInfo);
        LogEvent.Logger logger = new LogEvent.Logger(this.loggerName, str2 == null ? Thread.currentThread().getName() : str2, this.sdkVersion);
        String str3 = this.serviceName;
        LogEvent.Status resolveLogLevelStatus = resolveLogLevelStatus(i);
        String joinToString$default = CollectionsKt.joinToString$default(resolveTags, WebViewLogEventConsumer.DDTAGS_SEPARATOR, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        Intrinsics.checkNotNullExpressionValue(format, "formattedDate");
        return new LogEvent(resolveLogLevelStatus, str3, str, format, logger, resolveUserInfo, resolveNetworkInfo, error, joinToString$default, resolveAttributes);
    }

    private final LogEvent.Network resolveNetworkInfo(NetworkInfo networkInfo) {
        String str = null;
        if (networkInfo == null) {
            NetworkInfoProvider networkInfoProvider2 = this.networkInfoProvider;
            networkInfo = networkInfoProvider2 == null ? null : networkInfoProvider2.getLatestNetworkInfo();
        }
        if (networkInfo == null) {
            return null;
        }
        LogEvent.SimCarrier resolveSimCarrier = resolveSimCarrier(networkInfo);
        Long strength = networkInfo.getStrength();
        String l = strength == null ? null : strength.toString();
        Long downKbps = networkInfo.getDownKbps();
        String l2 = downKbps == null ? null : downKbps.toString();
        Long upKbps = networkInfo.getUpKbps();
        if (upKbps != null) {
            str = upKbps.toString();
        }
        return new LogEvent.Network(new LogEvent.Client(resolveSimCarrier, l, l2, str, networkInfo.getConnectivity().toString()));
    }

    private final LogEvent.Usr resolveUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            userInfo = this.userInfoProvider.getUserInfo();
        }
        return new LogEvent.Usr(userInfo.getId(), userInfo.getName(), userInfo.getEmail(), userInfo.getAdditionalProperties());
    }

    private final Set<String> resolveTags(Set<String> set) {
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(set);
        String str = this.envTag;
        if (str != null) {
            linkedHashSet.add(str);
        }
        String str2 = this.appVersionTag;
        if (str2 != null) {
            linkedHashSet.add(str2);
        }
        return linkedHashSet;
    }

    private final Map<String, Object> resolveAttributes(Map<String, ? extends Object> map, boolean z, boolean z2) {
        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.putAll(map);
        if (z && GlobalTracer.isRegistered()) {
            Span activeSpan = GlobalTracer.get().activeSpan();
            SpanContext context = activeSpan == null ? null : activeSpan.context();
            if (context != null) {
                linkedHashMap.put(LogAttributes.DD_TRACE_ID, context.toTraceId());
                linkedHashMap.put(LogAttributes.DD_SPAN_ID, context.toSpanId());
            }
        }
        if (z2 && GlobalRum.isRegistered()) {
            RumContext rumContext$dd_sdk_android_release = GlobalRum.INSTANCE.getRumContext$dd_sdk_android_release();
            linkedHashMap.put(LogAttributes.RUM_APPLICATION_ID, rumContext$dd_sdk_android_release.getApplicationId());
            linkedHashMap.put(LogAttributes.RUM_SESSION_ID, rumContext$dd_sdk_android_release.getSessionId());
            linkedHashMap.put(LogAttributes.RUM_VIEW_ID, rumContext$dd_sdk_android_release.getViewId());
            linkedHashMap.put(LogAttributes.RUM_ACTION_ID, rumContext$dd_sdk_android_release.getActionId());
        }
        return linkedHashMap;
    }

    private final LogEvent.Status resolveLogLevelStatus(int i) {
        switch (i) {
            case 2:
                return LogEvent.Status.TRACE;
            case 3:
                return LogEvent.Status.DEBUG;
            case 4:
                return LogEvent.Status.INFO;
            case 5:
                return LogEvent.Status.WARN;
            case 6:
                return LogEvent.Status.ERROR;
            case 7:
                return LogEvent.Status.CRITICAL;
            case 9:
                return LogEvent.Status.EMERGENCY;
            default:
                return LogEvent.Status.DEBUG;
        }
    }

    private final LogEvent.SimCarrier resolveSimCarrier(NetworkInfo networkInfo) {
        String str = null;
        if (networkInfo.getCarrierId() == null && networkInfo.getCarrierName() == null) {
            return null;
        }
        Long carrierId = networkInfo.getCarrierId();
        if (carrierId != null) {
            str = carrierId.toString();
        }
        return new LogEvent.SimCarrier(str, networkInfo.getCarrierName());
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/log/internal/domain/LogGenerator$Companion;", "", "()V", "CRASH", "", "ISO_8601", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LogGenerator.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
