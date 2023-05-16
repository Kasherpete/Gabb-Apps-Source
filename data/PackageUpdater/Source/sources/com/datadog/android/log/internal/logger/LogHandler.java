package com.datadog.android.log.internal.logger;

import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;

@Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001J]\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/log/internal/logger/LogHandler;", "", "handleLog", "", "level", "", "message", "", "throwable", "", "attributes", "", "tags", "", "timestamp", "", "(ILjava/lang/String;Ljava/lang/Throwable;Ljava/util/Map;Ljava/util/Set;Ljava/lang/Long;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LogHandler.kt */
public interface LogHandler {
    void handleLog(int i, String str, Throwable th, Map<String, ? extends Object> map, Set<String> set, Long l);

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LogHandler.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void handleLog$default(LogHandler logHandler, int i, String str, Throwable th, Map map, Set set, Long l, int i2, Object obj) {
            Set set2;
            if (obj == null) {
                Throwable th2 = (i2 & 4) != 0 ? null : th;
                Map emptyMap = (i2 & 8) != 0 ? MapsKt.emptyMap() : map;
                if ((i2 & 16) != 0) {
                    set2 = SetsKt.emptySet();
                } else {
                    set2 = set;
                }
                logHandler.handleLog(i, str, th2, emptyMap, set2, (i2 & 32) != 0 ? null : l);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleLog");
        }
    }
}
