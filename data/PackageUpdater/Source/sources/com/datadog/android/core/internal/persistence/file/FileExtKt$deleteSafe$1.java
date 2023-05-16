package com.datadog.android.core.internal.persistence.file;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, mo20735d2 = {"<anonymous>", "", "Ljava/io/File;", "invoke", "(Ljava/io/File;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FileExt.kt */
final class FileExtKt$deleteSafe$1 extends Lambda implements Function1<File, Boolean> {
    public static final FileExtKt$deleteSafe$1 INSTANCE = new FileExtKt$deleteSafe$1();

    FileExtKt$deleteSafe$1() {
        super(1);
    }

    public final Boolean invoke(File file) {
        Intrinsics.checkNotNullParameter(file, "$this$safeCall");
        return Boolean.valueOf(file.delete());
    }
}
