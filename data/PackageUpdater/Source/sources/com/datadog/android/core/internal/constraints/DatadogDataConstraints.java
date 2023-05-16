package com.datadog.android.core.internal.constraints;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.trace.api.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0014\u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\u001a\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0014\u001a\u00020\fH\u0002JP\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00170\u0016\"\u0004\b\u0000\u0010\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00170\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001bH\u0016J\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0004H\u0016J(\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001f0\u00162\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001f0\u0016H\u0016R,\u0010\u0003\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\u0002`\u00070\u0004X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\""}, mo20735d2 = {"Lcom/datadog/android/core/internal/constraints/DatadogDataConstraints;", "Lcom/datadog/android/core/internal/constraints/DataConstraints;", "()V", "tagTransforms", "", "Lkotlin/Function1;", "", "Lcom/datadog/android/core/internal/constraints/StringTransform;", "getTagTransforms$annotations", "convertAttributeKey", "rawKey", "prefixDotCount", "", "convertTag", "rawTag", "isKeyReserved", "", "tag", "resolveDiscardedAttrsWarning", "attributesGroupName", "discardedCount", "validateAttributes", "", "T", "attributes", "keyPrefix", "reservedKeys", "", "validateTags", "tags", "validateTimings", "", "timings", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogDataConstraints.kt */
public final class DatadogDataConstraints implements DataConstraints {
    public static final String CUSTOM_TIMING_KEY_REPLACED_WARNING = "Invalid timing name: %s, sanitized to: %s";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_ATTR_COUNT = 128;
    private static final int MAX_DEPTH_LEVEL = 9;
    private static final int MAX_TAG_COUNT = 100;
    private static final int MAX_TAG_LENGTH = 200;
    private static final Set<String> reservedTagKeys = SetsKt.setOf("host", "device", "source", "service");
    private final List<Function1<String, String>> tagTransforms = CollectionsKt.listOf(DatadogDataConstraints$tagTransforms$1.INSTANCE, DatadogDataConstraints$tagTransforms$2.INSTANCE, DatadogDataConstraints$tagTransforms$3.INSTANCE, DatadogDataConstraints$tagTransforms$4.INSTANCE, DatadogDataConstraints$tagTransforms$5.INSTANCE, new DatadogDataConstraints$tagTransforms$6(this));

    private static /* synthetic */ void getTagTransforms$annotations() {
    }

    public List<String> validateTags(List<String> list) {
        Intrinsics.checkNotNullParameter(list, Config.TAGS);
        Collection arrayList = new ArrayList();
        for (String str : list) {
            String convertTag = convertTag(str);
            if (convertTag == null) {
                Logger.e$default(RuntimeUtilsKt.getDevLogger(), "\"" + str + "\" is an invalid tag, and was ignored.", (Throwable) null, (Map) null, 6, (Object) null);
            } else if (!Intrinsics.areEqual((Object) convertTag, (Object) str)) {
                Logger.w$default(RuntimeUtilsKt.getDevLogger(), "tag \"" + str + "\" was modified to \"" + convertTag + "\" to match our constraints.", (Throwable) null, (Map) null, 6, (Object) null);
            }
            if (convertTag != null) {
                arrayList.add(convertTag);
            }
        }
        List list2 = (List) arrayList;
        int size = list2.size() - 100;
        if (size > 0) {
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), "too many tags were added, " + size + " had to be discarded.", (Throwable) null, (Map) null, 6, (Object) null);
        }
        return CollectionsKt.take(list2, 100);
    }

    public <T> Map<String, T> validateAttributes(Map<String, ? extends T> map, String str, String str2, Set<String> set) {
        Pair pair;
        Intrinsics.checkNotNullParameter(map, "attributes");
        Intrinsics.checkNotNullParameter(set, "reservedKeys");
        int i = 0;
        if (str != null) {
            CharSequence charSequence = str;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                boolean z = true;
                if (i2 >= charSequence.length()) {
                    break;
                }
                char charAt = charSequence.charAt(i2);
                i2++;
                if (charAt != '.') {
                    z = false;
                }
                if (z) {
                    i3++;
                }
            }
            i = i3 + 1;
        }
        Collection arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            if (next.getKey() == null) {
                Logger.e$default(RuntimeUtilsKt.getDevLogger(), "\"" + next + "\" is an invalid attribute, and was ignored.", (Throwable) null, (Map) null, 6, (Object) null);
                pair = null;
            } else if (set.contains(next.getKey())) {
                Logger.e$default(RuntimeUtilsKt.getDevLogger(), "\"" + next + "\" key was in the reservedKeys set, and was dropped.", (Throwable) null, (Map) null, 6, (Object) null);
                pair = null;
            } else {
                String convertAttributeKey = convertAttributeKey((String) next.getKey(), i);
                if (!Intrinsics.areEqual((Object) convertAttributeKey, next.getKey())) {
                    Logger.w$default(RuntimeUtilsKt.getDevLogger(), "Key \"" + next.getKey() + "\" was modified to \"" + convertAttributeKey + "\" to match our constraints.", (Throwable) null, (Map) null, 6, (Object) null);
                }
                pair = TuplesKt.m78to(convertAttributeKey, next.getValue());
            }
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        List list = (List) arrayList;
        int size = list.size() - 128;
        if (size > 0) {
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), resolveDiscardedAttrsWarning(str2, size), (Throwable) null, (Map) null, 6, (Object) null);
        }
        return MapsKt.toMap(CollectionsKt.take(list, 128));
    }

    private final String resolveDiscardedAttrsWarning(String str, int i) {
        if (str != null) {
            return "Too many attributes were added for [" + str + "], " + i + " had to be discarded.";
        }
        return "Too many attributes were added, " + i + " had to be discarded.";
    }

    private final String convertTag(String str) {
        for (Function1 invoke : this.tagTransforms) {
            str = str == null ? null : (String) invoke.invoke(str);
        }
        return str;
    }

    /* access modifiers changed from: private */
    public final boolean isKeyReserved(String str) {
        int indexOf$default = StringsKt.indexOf$default((CharSequence) str, ':', 0, false, 6, (Object) null);
        if (indexOf$default <= 0) {
            return false;
        }
        String substring = str.substring(0, indexOf$default);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return reservedTagKeys.contains(substring);
    }

    private final String convertAttributeKey(String str, int i) {
        CharSequence charSequence = str;
        Collection arrayList = new ArrayList(charSequence.length());
        int i2 = 0;
        while (i2 < charSequence.length()) {
            char charAt = charSequence.charAt(i2);
            i2++;
            if (charAt == '.' && (i = i + 1) > 9) {
                charAt = '_';
            }
            arrayList.add(Character.valueOf(charAt));
        }
        return new String(CollectionsKt.toCharArray((List) arrayList));
    }

    @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/constraints/DatadogDataConstraints$Companion;", "", "()V", "CUSTOM_TIMING_KEY_REPLACED_WARNING", "", "MAX_ATTR_COUNT", "", "MAX_DEPTH_LEVEL", "MAX_TAG_COUNT", "MAX_TAG_LENGTH", "reservedTagKeys", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DatadogDataConstraints.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public Map<String, Long> validateTimings(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "timings");
        Map<String, Long> linkedHashMap = new LinkedHashMap<>(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            String replace = new Regex("[^a-zA-Z0-9\\-_.@$]").replace((CharSequence) entry.getKey(), "_");
            if (!Intrinsics.areEqual((Object) replace, entry.getKey())) {
                Logger devLogger = RuntimeUtilsKt.getDevLogger();
                String format = String.format(Locale.US, CUSTOM_TIMING_KEY_REPLACED_WARNING, Arrays.copyOf(new Object[]{entry.getKey(), replace}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
                Logger.w$default(devLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
            }
            linkedHashMap.put(replace, entry.getValue());
        }
        return linkedHashMap;
    }
}
