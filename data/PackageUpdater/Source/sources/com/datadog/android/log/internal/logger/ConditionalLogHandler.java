package com.datadog.android.log.internal.logger;

import com.datadog.trace.api.Config;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u001a\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0002\u0010\bJU\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u0010\u001aR(\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/log/internal/logger/ConditionalLogHandler;", "Lcom/datadog/android/log/internal/logger/LogHandler;", "delegateHandler", "condition", "Lkotlin/Function2;", "", "", "", "(Lcom/datadog/android/log/internal/logger/LogHandler;Lkotlin/jvm/functions/Function2;)V", "getCondition$dd_sdk_android_release", "()Lkotlin/jvm/functions/Function2;", "getDelegateHandler$dd_sdk_android_release", "()Lcom/datadog/android/log/internal/logger/LogHandler;", "handleLog", "", "level", "message", "", "throwable", "attributes", "", "", "tags", "", "timestamp", "", "(ILjava/lang/String;Ljava/lang/Throwable;Ljava/util/Map;Ljava/util/Set;Ljava/lang/Long;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ConditionalLogHandler.kt */
public final class ConditionalLogHandler implements LogHandler {
    private final Function2<Integer, Throwable, Boolean> condition;
    private final LogHandler delegateHandler;

    public ConditionalLogHandler(LogHandler logHandler, Function2<? super Integer, ? super Throwable, Boolean> function2) {
        Intrinsics.checkNotNullParameter(logHandler, "delegateHandler");
        Intrinsics.checkNotNullParameter(function2, "condition");
        this.delegateHandler = logHandler;
        this.condition = function2;
    }

    public final LogHandler getDelegateHandler$dd_sdk_android_release() {
        return this.delegateHandler;
    }

    public final Function2<Integer, Throwable, Boolean> getCondition$dd_sdk_android_release() {
        return this.condition;
    }

    public void handleLog(int i, String str, Throwable th, Map<String, ? extends Object> map, Set<String> set, Long l) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        Intrinsics.checkNotNullParameter(set, Config.TAGS);
        if (this.condition.invoke(Integer.valueOf(i), th).booleanValue()) {
            this.delegateHandler.handleLog(i, str, th, map, set, l);
        }
    }
}
