package com.datadog.android.log;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.net.info.NetworkInfoProvider;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.sampling.RateBasedSampler;
import com.datadog.android.core.internal.utils.MapUtilsKt;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.LogsFeature;
import com.datadog.android.log.internal.domain.LogGenerator;
import com.datadog.android.log.internal.logger.CombinedLogHandler;
import com.datadog.android.log.internal.logger.DatadogLogHandler;
import com.datadog.android.log.internal.logger.LogHandler;
import com.datadog.android.log.internal.logger.LogcatLogHandler;
import com.datadog.android.log.internal.logger.NoOpLogHandler;
import com.datadog.android.log.model.LogEvent;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(mo20734d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\u0010$\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 92\u00020\u0001:\u000289B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0012J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0013J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0014J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0015J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0016J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0017J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0018J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0019J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u001aJ\u000e\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0007J\u0016\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J4\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0007J4\u0010#\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0007J4\u0010$\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0007JK\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010!2\u0014\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0018H\u0000¢\u0006\u0004\b)\u0010*J<\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0007J\u000e\u0010-\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007J\u000e\u0010.\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0007J\u0010\u0010/\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J\u000e\u00100\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007J\u001a\u00101\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0002J\u001c\u00102\u001a\u00020\u000e2\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001404H\u0002J4\u00105\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0007J4\u00106\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0007J4\u00107\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0007R\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0004R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\fX\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, mo20735d2 = {"Lcom/datadog/android/log/Logger;", "", "handler", "Lcom/datadog/android/log/internal/logger/LogHandler;", "(Lcom/datadog/android/log/internal/logger/LogHandler;)V", "attributes", "Ljava/util/concurrent/ConcurrentHashMap;", "", "getHandler$dd_sdk_android_release", "()Lcom/datadog/android/log/internal/logger/LogHandler;", "setHandler$dd_sdk_android_release", "tags", "Ljava/util/concurrent/CopyOnWriteArraySet;", "addAttribute", "", "key", "value", "Lcom/google/gson/JsonArray;", "Lcom/google/gson/JsonObject;", "Ljava/util/Date;", "", "", "", "", "", "Lorg/json/JSONArray;", "Lorg/json/JSONObject;", "addTag", "tag", "addTagInternal", "d", "message", "throwable", "", "", "e", "i", "internalLog", "level", "localAttributes", "timestamp", "internalLog$dd_sdk_android_release", "(ILjava/lang/String;Ljava/lang/Throwable;Ljava/util/Map;Ljava/lang/Long;)V", "log", "priority", "removeAttribute", "removeTag", "removeTagInternal", "removeTagsWithKey", "safelyAddAttribute", "safelyRemoveTagsWithKey", "keyFilter", "Lkotlin/Function1;", "v", "w", "wtf", "Builder", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Logger.kt */
public final class Logger {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String SDK_NOT_INITIALIZED_WARNING_MESSAGE = "You're trying to create a Logger instance, but the SDK was not yet initialized. This Logger will not be able to send any messages. Please initialize the Datadog SDK first before creating a new Logger instance.";
    private final ConcurrentHashMap<String, Object> attributes = new ConcurrentHashMap<>();
    private LogHandler handler;
    private final CopyOnWriteArraySet<String> tags = new CopyOnWriteArraySet<>();

    /* renamed from: d */
    public final void mo13080d(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        d$default(this, str, (Throwable) null, (Map) null, 6, (Object) null);
    }

    /* renamed from: d */
    public final void mo13081d(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        d$default(this, str, th, (Map) null, 4, (Object) null);
    }

    /* renamed from: e */
    public final void mo13083e(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        e$default(this, str, (Throwable) null, (Map) null, 6, (Object) null);
    }

    /* renamed from: e */
    public final void mo13084e(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        e$default(this, str, th, (Map) null, 4, (Object) null);
    }

    /* renamed from: i */
    public final void mo13087i(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        i$default(this, str, (Throwable) null, (Map) null, 6, (Object) null);
    }

    /* renamed from: i */
    public final void mo13088i(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        i$default(this, str, th, (Map) null, 4, (Object) null);
    }

    public final void log(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        log$default(this, i, str, (Throwable) null, (Map) null, 12, (Object) null);
    }

    public final void log(int i, String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        log$default(this, i, str, th, (Map) null, 8, (Object) null);
    }

    /* renamed from: v */
    public final void mo13098v(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        v$default(this, str, (Throwable) null, (Map) null, 6, (Object) null);
    }

    /* renamed from: v */
    public final void mo13099v(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        v$default(this, str, th, (Map) null, 4, (Object) null);
    }

    /* renamed from: w */
    public final void mo13101w(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        w$default(this, str, (Throwable) null, (Map) null, 6, (Object) null);
    }

    /* renamed from: w */
    public final void mo13102w(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        w$default(this, str, th, (Map) null, 4, (Object) null);
    }

    public final void wtf(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        wtf$default(this, str, (Throwable) null, (Map) null, 6, (Object) null);
    }

    public final void wtf(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "message");
        wtf$default(this, str, th, (Map) null, 4, (Object) null);
    }

    public Logger(LogHandler logHandler) {
        Intrinsics.checkNotNullParameter(logHandler, "handler");
        this.handler = logHandler;
    }

    public final LogHandler getHandler$dd_sdk_android_release() {
        return this.handler;
    }

    public final void setHandler$dd_sdk_android_release(LogHandler logHandler) {
        Intrinsics.checkNotNullParameter(logHandler, "<set-?>");
        this.handler = logHandler;
    }

    public static /* synthetic */ void v$default(Logger logger, String str, Throwable th, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        logger.mo13100v(str, th, map);
    }

    /* renamed from: v */
    public final void mo13100v(String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        internalLog$dd_sdk_android_release$default(this, 2, str, th, map, (Long) null, 16, (Object) null);
    }

    public static /* synthetic */ void d$default(Logger logger, String str, Throwable th, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        logger.mo13082d(str, th, map);
    }

    /* renamed from: d */
    public final void mo13082d(String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        internalLog$dd_sdk_android_release$default(this, 3, str, th, map, (Long) null, 16, (Object) null);
    }

    public static /* synthetic */ void i$default(Logger logger, String str, Throwable th, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        logger.mo13089i(str, th, map);
    }

    /* renamed from: i */
    public final void mo13089i(String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        internalLog$dd_sdk_android_release$default(this, 4, str, th, map, (Long) null, 16, (Object) null);
    }

    public static /* synthetic */ void w$default(Logger logger, String str, Throwable th, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        logger.mo13103w(str, th, map);
    }

    /* renamed from: w */
    public final void mo13103w(String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        internalLog$dd_sdk_android_release$default(this, 5, str, th, map, (Long) null, 16, (Object) null);
    }

    public static /* synthetic */ void e$default(Logger logger, String str, Throwable th, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        logger.mo13085e(str, th, map);
    }

    /* renamed from: e */
    public final void mo13085e(String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        internalLog$dd_sdk_android_release$default(this, 6, str, th, map, (Long) null, 16, (Object) null);
    }

    public static /* synthetic */ void wtf$default(Logger logger, String str, Throwable th, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        logger.wtf(str, th, map);
    }

    public final void wtf(String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        internalLog$dd_sdk_android_release$default(this, 7, str, th, map, (Long) null, 16, (Object) null);
    }

    public static /* synthetic */ void log$default(Logger logger, int i, String str, Throwable th, Map map, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            th = null;
        }
        if ((i2 & 8) != 0) {
            map = MapsKt.emptyMap();
        }
        logger.log(i, str, th, map);
    }

    public final void log(int i, String str, Throwable th, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        internalLog$dd_sdk_android_release$default(this, i, str, th, map, (Long) null, 16, (Object) null);
    }

    @Metadata(mo20734d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0002J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\tJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0004J\u0010\u0010 \u001a\u00020\u00002\b\b\u0001\u0010!\u001a\u00020\fJ\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, mo20735d2 = {"Lcom/datadog/android/log/Logger$Builder;", "", "()V", "bundleWithRumEnabled", "", "bundleWithTraceEnabled", "datadogLogsEnabled", "logcatLogsEnabled", "loggerName", "", "networkInfoEnabled", "sampleRate", "", "serviceName", "build", "Lcom/datadog/android/log/Logger;", "buildDatadogHandler", "Lcom/datadog/android/log/internal/logger/LogHandler;", "buildLogGenerator", "Lcom/datadog/android/log/internal/domain/LogGenerator;", "buildLogWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "Lcom/datadog/android/log/model/LogEvent;", "buildLogcatHandler", "setBundleWithRumEnabled", "enabled", "setBundleWithTraceEnabled", "setDatadogLogsEnabled", "setLogcatLogsEnabled", "setLoggerName", "name", "setNetworkInfoEnabled", "setSampleRate", "rate", "setServiceName", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Logger.kt */
    public static final class Builder {
        private boolean bundleWithRumEnabled = true;
        private boolean bundleWithTraceEnabled = true;
        private boolean datadogLogsEnabled = true;
        private boolean logcatLogsEnabled;
        private String loggerName = CoreFeature.INSTANCE.getPackageName$dd_sdk_android_release();
        private boolean networkInfoEnabled;
        private float sampleRate = 1.0f;
        private String serviceName = CoreFeature.INSTANCE.getServiceName$dd_sdk_android_release();

        public final Logger build() {
            LogHandler logHandler;
            boolean z = this.datadogLogsEnabled;
            if (z && this.logcatLogsEnabled) {
                logHandler = new CombinedLogHandler(buildDatadogHandler(), buildLogcatHandler());
            } else if (z) {
                logHandler = buildDatadogHandler();
            } else if (this.logcatLogsEnabled) {
                logHandler = buildLogcatHandler();
            } else {
                logHandler = new NoOpLogHandler();
            }
            return new Logger(logHandler);
        }

        public final Builder setServiceName(String str) {
            Intrinsics.checkNotNullParameter(str, "serviceName");
            this.serviceName = str;
            return this;
        }

        public final Builder setDatadogLogsEnabled(boolean z) {
            this.datadogLogsEnabled = z;
            return this;
        }

        public final Builder setLogcatLogsEnabled(boolean z) {
            this.logcatLogsEnabled = z;
            return this;
        }

        public final Builder setNetworkInfoEnabled(boolean z) {
            this.networkInfoEnabled = z;
            return this;
        }

        public final Builder setLoggerName(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.loggerName = str;
            return this;
        }

        public final Builder setBundleWithTraceEnabled(boolean z) {
            this.bundleWithTraceEnabled = z;
            return this;
        }

        public final Builder setBundleWithRumEnabled(boolean z) {
            this.bundleWithRumEnabled = z;
            return this;
        }

        public final Builder setSampleRate(float f) {
            this.sampleRate = f;
            return this;
        }

        private final LogHandler buildLogcatHandler() {
            return new LogcatLogHandler(this.serviceName, true);
        }

        private final LogHandler buildDatadogHandler() {
            DataWriter<LogEvent> buildLogWriter = buildLogWriter();
            if (buildLogWriter == null) {
                return new NoOpLogHandler();
            }
            return new DatadogLogHandler(buildLogGenerator(), buildLogWriter, this.bundleWithTraceEnabled, this.bundleWithRumEnabled, new RateBasedSampler(this.sampleRate));
        }

        private final DataWriter<LogEvent> buildLogWriter() {
            if (LogsFeature.INSTANCE.isInitialized()) {
                return LogsFeature.INSTANCE.getPersistenceStrategy$dd_sdk_android_release().getWriter();
            }
            Logger.e$default(RuntimeUtilsKt.getDevLogger(), "You're trying to create a Logger instance, but the SDK was not yet initialized. This Logger will not be able to send any messages. Please initialize the Datadog SDK first before creating a new Logger instance.\nPlease add the following code in your application's onCreate() method:\nval credentials = Credentials(\"<CLIENT_TOKEN>\", \"<ENVIRONMENT>\", \"<VARIANT>\", \"<APPLICATION_ID>\")\nDatadog.initialize(context, credentials, configuration, trackingConsent);", (Throwable) null, (Map) null, 6, (Object) null);
            return null;
        }

        private final LogGenerator buildLogGenerator() {
            NetworkInfoProvider networkInfoProvider;
            if (this.networkInfoEnabled) {
                networkInfoProvider = CoreFeature.INSTANCE.getNetworkInfoProvider$dd_sdk_android_release();
            } else {
                networkInfoProvider = null;
            }
            return new LogGenerator(this.serviceName, this.loggerName, networkInfoProvider, CoreFeature.INSTANCE.getUserInfoProvider$dd_sdk_android_release(), CoreFeature.INSTANCE.getTimeProvider$dd_sdk_android_release(), CoreFeature.INSTANCE.getSdkVersion$dd_sdk_android_release(), CoreFeature.INSTANCE.getEnvName$dd_sdk_android_release(), CoreFeature.INSTANCE.getPackageVersion$dd_sdk_android_release());
        }
    }

    public final void addAttribute(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.attributes.put(str, Boolean.valueOf(z));
    }

    public final void addAttribute(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.attributes.put(str, Integer.valueOf(i));
    }

    public final void addAttribute(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.attributes.put(str, Long.valueOf(j));
    }

    public final void addAttribute(String str, float f) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.attributes.put(str, Float.valueOf(f));
    }

    public final void addAttribute(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.attributes.put(str, Double.valueOf(d));
    }

    public final void addAttribute(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        safelyAddAttribute(str, str2);
    }

    public final void addAttribute(String str, Date date) {
        Intrinsics.checkNotNullParameter(str, "key");
        safelyAddAttribute(str, date);
    }

    public final void addAttribute(String str, JsonObject jsonObject) {
        Intrinsics.checkNotNullParameter(str, "key");
        safelyAddAttribute(str, jsonObject);
    }

    public final void addAttribute(String str, JsonArray jsonArray) {
        Intrinsics.checkNotNullParameter(str, "key");
        safelyAddAttribute(str, jsonArray);
    }

    public final void addAttribute(String str, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(str, "key");
        safelyAddAttribute(str, jSONObject);
    }

    public final void addAttribute(String str, JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(str, "key");
        safelyAddAttribute(str, jSONArray);
    }

    public final void removeAttribute(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.attributes.remove(str);
    }

    public final void addTag(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "value");
        addTagInternal(str + ":" + str2);
    }

    public final void addTag(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        addTagInternal(str);
    }

    public final void removeTag(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        removeTagInternal(str);
    }

    public final void removeTagsWithKey(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        safelyRemoveTagsWithKey(new Logger$removeTagsWithKey$1(str + ":"));
    }

    public static /* synthetic */ void internalLog$dd_sdk_android_release$default(Logger logger, int i, String str, Throwable th, Map map, Long l, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            l = null;
        }
        logger.internalLog$dd_sdk_android_release(i, str, th, map, l);
    }

    public final void internalLog$dd_sdk_android_release(int i, String str, Throwable th, Map<String, ? extends Object> map, Long l) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "localAttributes");
        Map linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(this.attributes);
        linkedHashMap.putAll(map);
        this.handler.handleLog(i, str, th, linkedHashMap, this.tags, l);
    }

    private final void addTagInternal(String str) {
        this.tags.add(str);
    }

    private final void removeTagInternal(String str) {
        this.tags.remove(str);
    }

    private final void safelyAddAttribute(String str, Object obj) {
        if (obj == null) {
            obj = MapUtilsKt.getNULL_MAP_VALUE();
        }
        this.attributes.put(str, obj);
    }

    private final void safelyRemoveTagsWithKey(Function1<? super String, Boolean> function1) {
        int i = 0;
        Object[] array = this.tags.toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        Collection arrayList = new ArrayList();
        int length = array.length;
        while (i < length) {
            Object obj = array[i];
            i++;
            if (function1.invoke(obj).booleanValue()) {
                arrayList.add(obj);
            }
        }
        this.tags.removeAll((List) arrayList);
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/log/Logger$Companion;", "", "()V", "SDK_NOT_INITIALIZED_WARNING_MESSAGE", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Logger.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
