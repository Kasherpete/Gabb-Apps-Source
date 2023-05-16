package com.datadog.android.core.internal.utils;

import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, mo20735d2 = {"toHexString", "", "Ljava/math/BigInteger;", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BigIntegerExt.kt */
public final class BigIntegerExtKt {
    public static final String toHexString(BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "<this>");
        String l = Long.toString(bigInteger.longValue(), CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(l, "toString(this, checkRadix(radix))");
        return l;
    }
}
