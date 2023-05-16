package com.datadog.android.core.internal.constraints;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "", "it", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogDataConstraints.kt */
final class DatadogDataConstraints$tagTransforms$4 extends Lambda implements Function1<String, String> {
    public static final DatadogDataConstraints$tagTransforms$4 INSTANCE = new DatadogDataConstraints$tagTransforms$4();

    DatadogDataConstraints$tagTransforms$4() {
        super(1);
    }

    public final String invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        CharSequence charSequence = str;
        if (!StringsKt.endsWith$default(charSequence, ':', false, 2, (Object) null)) {
            return str;
        }
        String substring = str.substring(0, StringsKt.getLastIndex(charSequence));
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }
}
