package com.datadog.android.core.internal.persistence.file;

import java.io.File;
import java.io.FileFilter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a*\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u0001 \u0003*\u0014\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo20735d2 = {"<anonymous>", "", "Ljava/io/File;", "kotlin.jvm.PlatformType", "invoke", "(Ljava/io/File;)[Ljava/io/File;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FileExt.kt */
final class FileExtKt$listFilesSafe$2 extends Lambda implements Function1<File, File[]> {
    final /* synthetic */ FileFilter $filter;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileExtKt$listFilesSafe$2(FileFilter fileFilter) {
        super(1);
        this.$filter = fileFilter;
    }

    public final File[] invoke(File file) {
        Intrinsics.checkNotNullParameter(file, "$this$safeCall");
        return file.listFiles(this.$filter);
    }
}
