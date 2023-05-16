package com.datadog.android.core.internal.persistence.file;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.p007io.FilesKt;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "", "Ljava/io/File;", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FileExt.kt */
final class FileExtKt$readBytesSafe$1 extends Lambda implements Function1<File, byte[]> {
    public static final FileExtKt$readBytesSafe$1 INSTANCE = new FileExtKt$readBytesSafe$1();

    FileExtKt$readBytesSafe$1() {
        super(1);
    }

    public final byte[] invoke(File file) {
        Intrinsics.checkNotNullParameter(file, "$this$safeCall");
        return FilesKt.readBytes(file);
    }
}
