package com.datadog.android.core.internal.constraints;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001JV\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nH&J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\fH&J(\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u00032\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u0003H&¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/core/internal/constraints/DataConstraints;", "", "validateAttributes", "", "", "T", "attributes", "keyPrefix", "attributesGroupName", "reservedKeys", "", "validateTags", "", "tags", "validateTimings", "", "timings", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DataConstraints.kt */
public interface DataConstraints {
    <T> Map<String, T> validateAttributes(Map<String, ? extends T> map, String str, String str2, Set<String> set);

    List<String> validateTags(List<String> list);

    Map<String, Long> validateTimings(Map<String, Long> map);

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DataConstraints.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ Map validateAttributes$default(DataConstraints dataConstraints, Map map, String str, String str2, Set set, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str = null;
                }
                if ((i & 4) != 0) {
                    str2 = null;
                }
                if ((i & 8) != 0) {
                    set = SetsKt.emptySet();
                }
                return dataConstraints.validateAttributes(map, str, str2, set);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: validateAttributes");
        }
    }
}
