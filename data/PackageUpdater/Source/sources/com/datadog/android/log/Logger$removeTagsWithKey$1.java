package com.datadog.android.log;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo20735d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/String;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Logger.kt */
final class Logger$removeTagsWithKey$1 extends Lambda implements Function1<String, Boolean> {
    final /* synthetic */ String $prefix;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Logger$removeTagsWithKey$1(String str) {
        super(1);
        this.$prefix = str;
    }

    public final Boolean invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return Boolean.valueOf(StringsKt.startsWith$default(str, this.$prefix, false, 2, (Object) null));
    }
}
