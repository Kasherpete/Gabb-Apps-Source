package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.rum.internal.domain.event.ResourceTiming;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;

@Metadata(mo20734d1 = {"\u0000(\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0002\u001a\"\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\rH\u0000\u001a(\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0013\u001a\u00020\u00022\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00110\rH\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo20735d2 = {"ALL_TIMINGS", "", "", "CONNECT_TIMING", "DNS_TIMING", "DOWNLOAD_TIMING", "DURATION_KEY", "FIRST_BYTE_TIMING", "SSL_TIMING", "START_TIME_KEY", "createResourceTiming", "Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "timings", "", "Lcom/datadog/android/rum/internal/domain/scope/Timing;", "extractResourceTiming", "timingsPayload", "", "extractTiming", "name", "source", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ExternalResourceTimings.kt */
public final class ExternalResourceTimingsKt {
    private static final List<String> ALL_TIMINGS = CollectionsKt.listOf(FIRST_BYTE_TIMING, DOWNLOAD_TIMING, SSL_TIMING, CONNECT_TIMING, DNS_TIMING);
    private static final String CONNECT_TIMING = "connect";
    private static final String DNS_TIMING = "dns";
    private static final String DOWNLOAD_TIMING = "download";
    private static final String DURATION_KEY = "duration";
    private static final String FIRST_BYTE_TIMING = "firstByte";
    private static final String SSL_TIMING = "ssl";
    private static final String START_TIME_KEY = "startTime";

    public static final ResourceTiming extractResourceTiming(Map<String, ? extends Object> map) {
        if (map == null) {
            return null;
        }
        Iterable iterable = ALL_TIMINGS;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
        for (Object next : iterable) {
            linkedHashMap.put(next, extractTiming((String) next, map));
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            if (((Timing) entry.getValue()) == null) {
                z = false;
            }
            if (z) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        Map map2 = linkedHashMap2;
        if (!map2.isEmpty()) {
            return createResourceTiming(map2);
        }
        return null;
    }

    private static final ResourceTiming createResourceTiming(Map<String, Timing> map) {
        Map<String, Timing> map2 = map;
        Timing timing = map2.get(FIRST_BYTE_TIMING);
        long j = 0;
        long startTime = timing == null ? 0 : timing.getStartTime();
        Timing timing2 = map2.get(FIRST_BYTE_TIMING);
        long duration = timing2 == null ? 0 : timing2.getDuration();
        Timing timing3 = map2.get(DOWNLOAD_TIMING);
        long startTime2 = timing3 == null ? 0 : timing3.getStartTime();
        Timing timing4 = map2.get(DOWNLOAD_TIMING);
        long duration2 = timing4 == null ? 0 : timing4.getDuration();
        Timing timing5 = map2.get(DNS_TIMING);
        long startTime3 = timing5 == null ? 0 : timing5.getStartTime();
        Timing timing6 = map2.get(DNS_TIMING);
        long duration3 = timing6 == null ? 0 : timing6.getDuration();
        Timing timing7 = map2.get(CONNECT_TIMING);
        long startTime4 = timing7 == null ? 0 : timing7.getStartTime();
        Timing timing8 = map2.get(CONNECT_TIMING);
        long duration4 = timing8 == null ? 0 : timing8.getDuration();
        Timing timing9 = map2.get(SSL_TIMING);
        long startTime5 = timing9 == null ? 0 : timing9.getStartTime();
        Timing timing10 = map2.get(SSL_TIMING);
        if (timing10 != null) {
            j = timing10.getDuration();
        }
        return new ResourceTiming(startTime3, duration3, startTime4, duration4, startTime5, j, startTime, duration, startTime2, duration2);
    }

    private static final Timing extractTiming(String str, Map<String, ? extends Object> map) {
        Object obj = map.get(str);
        if (obj == null || !(obj instanceof Map)) {
            return null;
        }
        Map map2 = (Map) obj;
        Object obj2 = map2.get(START_TIME_KEY);
        Number number = obj2 instanceof Number ? (Number) obj2 : null;
        Long valueOf = number == null ? null : Long.valueOf(number.longValue());
        Object obj3 = map2.get("duration");
        Number number2 = obj3 instanceof Number ? (Number) obj3 : null;
        Long valueOf2 = number2 == null ? null : Long.valueOf(number2.longValue());
        if (valueOf == null || valueOf2 == null) {
            return null;
        }
        return new Timing(valueOf.longValue(), valueOf2.longValue());
    }
}
