package com.datadog.android.core.internal.utils;

import com.datadog.android.Datadog;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, mo20735d2 = {"<anonymous>", "", "i", "", "<anonymous parameter 1>", "", "invoke", "(ILjava/lang/Throwable;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RuntimeUtils.kt */
final class RuntimeUtilsKt$buildDevLogHandler$1 extends Lambda implements Function2<Integer, Throwable, Boolean> {
    public static final RuntimeUtilsKt$buildDevLogHandler$1 INSTANCE = new RuntimeUtilsKt$buildDevLogHandler$1();

    RuntimeUtilsKt$buildDevLogHandler$1() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj).intValue(), (Throwable) obj2);
    }

    public final Boolean invoke(int i, Throwable th) {
        return Boolean.valueOf(i >= Datadog.INSTANCE.getLibraryVerbosity$dd_sdk_android_release());
    }
}
