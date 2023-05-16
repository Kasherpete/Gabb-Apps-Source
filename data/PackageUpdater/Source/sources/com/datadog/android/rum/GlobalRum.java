package com.datadog.android.rum;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.error.internal.CrashReportsFeature;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.LogsFeature;
import com.datadog.android.plugin.DatadogContext;
import com.datadog.android.plugin.DatadogPlugin;
import com.datadog.android.plugin.DatadogRumContext;
import com.datadog.android.rum.internal.RumFeature;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.rum.internal.domain.scope.RumViewScope;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.tracing.internal.TracingFeature;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0007J\b\u0010\u0019\u001a\u00020\u0010H\u0007J\r\u0010\u001a\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001bJ\b\u0010\u000b\u001a\u00020\u001cH\u0007J\r\u0010\u001d\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u001eJ\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0016\u0010\u001f\u001a\u00020\u001c2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100!H\u0007J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0007J\b\u0010#\u001a\u00020\u0016H\u0003J\u001e\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(H\u0002J:\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u00052#\b\u0002\u0010,\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u001c0-H\u0000¢\u0006\u0002\b1R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u00062"}, mo20735d2 = {"Lcom/datadog/android/rum/GlobalRum;", "", "()V", "activeContext", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/datadog/android/rum/internal/domain/RumContext;", "globalAttributes", "", "", "getGlobalAttributes$dd_sdk_android_release", "()Ljava/util/Map;", "isRegistered", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isRegistered$dd_sdk_android_release", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "monitor", "Lcom/datadog/android/rum/RumMonitor;", "getMonitor$dd_sdk_android_release", "()Lcom/datadog/android/rum/RumMonitor;", "setMonitor$dd_sdk_android_release", "(Lcom/datadog/android/rum/RumMonitor;)V", "addAttribute", "", "key", "value", "get", "getRumContext", "getRumContext$dd_sdk_android_release", "", "notifyIngestedWebViewEvent", "notifyIngestedWebViewEvent$dd_sdk_android_release", "registerIfAbsent", "provider", "Ljava/util/concurrent/Callable;", "removeAttribute", "resetSession", "updateContextInPlugins", "pluginContext", "Lcom/datadog/android/plugin/DatadogContext;", "plugins", "", "Lcom/datadog/android/plugin/DatadogPlugin;", "updateRumContext", "newContext", "applyOnlyIf", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "currentContext", "updateRumContext$dd_sdk_android_release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: GlobalRum.kt */
public final class GlobalRum {
    public static final GlobalRum INSTANCE = new GlobalRum();
    private static AtomicReference<RumContext> activeContext = new AtomicReference<>(new RumContext((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (RumViewScope.RumViewType) null, 127, (DefaultConstructorMarker) null));
    private static final Map<String, Object> globalAttributes = new ConcurrentHashMap();
    private static final AtomicBoolean isRegistered = new AtomicBoolean(false);
    private static RumMonitor monitor = new NoOpRumMonitor();

    /* access modifiers changed from: private */
    /* renamed from: registerIfAbsent$lambda-0  reason: not valid java name */
    public static final RumMonitor m145registerIfAbsent$lambda0(RumMonitor rumMonitor) {
        Intrinsics.checkNotNullParameter(rumMonitor, "$monitor");
        return rumMonitor;
    }

    private GlobalRum() {
    }

    public final Map<String, Object> getGlobalAttributes$dd_sdk_android_release() {
        return globalAttributes;
    }

    public final AtomicBoolean isRegistered$dd_sdk_android_release() {
        return isRegistered;
    }

    public final RumMonitor getMonitor$dd_sdk_android_release() {
        return monitor;
    }

    public final void setMonitor$dd_sdk_android_release(RumMonitor rumMonitor) {
        Intrinsics.checkNotNullParameter(rumMonitor, "<set-?>");
        monitor = rumMonitor;
    }

    @JvmStatic
    public static final boolean isRegistered() {
        return isRegistered.get();
    }

    @JvmStatic
    public static final boolean registerIfAbsent(RumMonitor rumMonitor) {
        Intrinsics.checkNotNullParameter(rumMonitor, "monitor");
        return registerIfAbsent((Callable<RumMonitor>) new GlobalRum$$ExternalSyntheticLambda0(rumMonitor));
    }

    @JvmStatic
    public static final boolean registerIfAbsent(Callable<RumMonitor> callable) {
        Intrinsics.checkNotNullParameter(callable, "provider");
        AtomicBoolean atomicBoolean = isRegistered;
        if (atomicBoolean.get()) {
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), "RumMonitor has already been registered", (Throwable) null, (Map) null, 6, (Object) null);
            return false;
        } else if (!atomicBoolean.compareAndSet(false, true)) {
            return false;
        } else {
            RumMonitor call = callable.call();
            Intrinsics.checkNotNullExpressionValue(call, "provider.call()");
            monitor = call;
            return true;
        }
    }

    @JvmStatic
    public static final RumMonitor get() {
        return monitor;
    }

    @JvmStatic
    public static final void addAttribute(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "key");
        if (obj == null) {
            globalAttributes.remove(str);
        } else {
            globalAttributes.put(str, obj);
        }
    }

    @JvmStatic
    public static final void removeAttribute(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        globalAttributes.remove(str);
    }

    public final void notifyIngestedWebViewEvent$dd_sdk_android_release() {
        RumMonitor rumMonitor = monitor;
        AdvancedRumMonitor advancedRumMonitor = rumMonitor instanceof AdvancedRumMonitor ? (AdvancedRumMonitor) rumMonitor : null;
        if (advancedRumMonitor != null) {
            advancedRumMonitor.sendWebViewEvent();
        }
    }

    public final RumContext getRumContext$dd_sdk_android_release() {
        RumContext rumContext = activeContext.get();
        Intrinsics.checkNotNullExpressionValue(rumContext, "activeContext.get()");
        return rumContext;
    }

    public static /* synthetic */ void updateRumContext$dd_sdk_android_release$default(GlobalRum globalRum, RumContext rumContext, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = GlobalRum$updateRumContext$1.INSTANCE;
        }
        globalRum.updateRumContext$dd_sdk_android_release(rumContext, function1);
    }

    public final void updateRumContext$dd_sdk_android_release(RumContext rumContext, Function1<? super RumContext, Boolean> function1) {
        Intrinsics.checkNotNullParameter(rumContext, "newContext");
        Intrinsics.checkNotNullParameter(function1, "applyOnlyIf");
        RumContext rumContext2 = activeContext.get();
        Intrinsics.checkNotNullExpressionValue(rumContext2, "activeContext.get()");
        if (function1.invoke(rumContext2).booleanValue()) {
            activeContext.set(rumContext);
            DatadogContext datadogContext = new DatadogContext(new DatadogRumContext(rumContext.getApplicationId(), rumContext.getSessionId(), rumContext.getViewId()));
            updateContextInPlugins(datadogContext, RumFeature.INSTANCE.getPlugins());
            updateContextInPlugins(datadogContext, CrashReportsFeature.INSTANCE.getPlugins());
            updateContextInPlugins(datadogContext, LogsFeature.INSTANCE.getPlugins());
            updateContextInPlugins(datadogContext, TracingFeature.INSTANCE.getPlugins());
        }
    }

    private final void updateContextInPlugins(DatadogContext datadogContext, List<? extends DatadogPlugin> list) {
        for (DatadogPlugin onContextChanged : list) {
            onContextChanged.onContextChanged(datadogContext);
        }
    }

    @JvmStatic
    private static final void resetSession() {
        RumMonitor rumMonitor = monitor;
        AdvancedRumMonitor advancedRumMonitor = rumMonitor instanceof AdvancedRumMonitor ? (AdvancedRumMonitor) rumMonitor : null;
        if (advancedRumMonitor != null) {
            advancedRumMonitor.resetSession();
        }
    }
}
