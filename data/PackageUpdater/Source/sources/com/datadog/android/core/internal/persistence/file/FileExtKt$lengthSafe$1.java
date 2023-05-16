package com.datadog.android.core.internal.persistence.file;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, mo20735d2 = {"<anonymous>", "", "Ljava/io/File;", "invoke", "(Ljava/io/File;)Ljava/lang/Long;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FileExt.kt */
final class FileExtKt$lengthSafe$1 extends Lambda implements Function1<File, Long> {
    public static final FileExtKt$lengthSafe$1 INSTANCE = new FileExtKt$lengthSafe$1();

    FileExtKt$lengthSafe$1() {
        super(1);
    }

    public final Long invoke(File file) {
        Intrinsics.checkNotNullParameter(file, "$this$safeCall");
        return Long.valueOf(file.length());
    }
}
