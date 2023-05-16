package com.datadog.android.core.internal.constraints;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.CharRange;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "", "it", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogDataConstraints.kt */
final class DatadogDataConstraints$tagTransforms$2 extends Lambda implements Function1<String, String> {
    public static final DatadogDataConstraints$tagTransforms$2 INSTANCE = new DatadogDataConstraints$tagTransforms$2();

    DatadogDataConstraints$tagTransforms$2() {
        super(1);
    }

    public final String invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        CharRange charRange = new CharRange('a', 'z');
        boolean z = false;
        Character orNull = StringsKt.getOrNull(str, 0);
        if (orNull != null && charRange.contains(orNull.charValue())) {
            z = true;
        }
        if (!z) {
            return null;
        }
        return str;
    }
}
