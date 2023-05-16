package com.datadog.android.core.internal.persistence.file;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import java.io.File;
import java.io.FileFilter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(mo20734d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0002H\u0000\u001a\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000b*\u00020\u0002H\u0000¢\u0006\u0002\u0010\f\u001a!\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\u0010\u000f\u001a\f\u0010\u0010\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0002H\u0000\u001a\u001e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014*\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0000\u001a\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u0015*\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0000\u001a\u0014\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0000\u001a8\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0006\u0010\u001d\u001a\u0002H\u001c2\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u001c0\u001f¢\u0006\u0002\b H\u0002¢\u0006\u0002\u0010!¨\u0006\""}, mo20735d2 = {"canReadSafe", "", "Ljava/io/File;", "canWriteSafe", "deleteSafe", "existsSafe", "isDirectorySafe", "isFileSafe", "lengthSafe", "", "listFilesSafe", "", "(Ljava/io/File;)[Ljava/io/File;", "filter", "Ljava/io/FileFilter;", "(Ljava/io/File;Ljava/io/FileFilter;)[Ljava/io/File;", "mkdirsSafe", "readBytesSafe", "", "readLinesSafe", "", "", "charset", "Ljava/nio/charset/Charset;", "readTextSafe", "renameToSafe", "dest", "safeCall", "T", "default", "lambda", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/io/File;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FileExt.kt */
public final class FileExtKt {
    private static final <T> T safeCall(File file, T t, Function1<? super File, ? extends T> function1) {
        try {
            return function1.invoke(file);
        } catch (SecurityException e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Security exception was thrown for file " + file.getPath(), e, (Map) null, 4, (Object) null);
            return t;
        } catch (Exception e2) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Unexpected exception was thrown for file " + file.getPath(), e2, (Map) null, 4, (Object) null);
            return t;
        }
    }

    public static final boolean canWriteSafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return ((Boolean) safeCall(file, false, FileExtKt$canWriteSafe$1.INSTANCE)).booleanValue();
    }

    public static final boolean canReadSafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return ((Boolean) safeCall(file, false, FileExtKt$canReadSafe$1.INSTANCE)).booleanValue();
    }

    public static final boolean deleteSafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return ((Boolean) safeCall(file, false, FileExtKt$deleteSafe$1.INSTANCE)).booleanValue();
    }

    public static final boolean existsSafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return ((Boolean) safeCall(file, false, FileExtKt$existsSafe$1.INSTANCE)).booleanValue();
    }

    public static final boolean isFileSafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return ((Boolean) safeCall(file, false, FileExtKt$isFileSafe$1.INSTANCE)).booleanValue();
    }

    public static final boolean isDirectorySafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return ((Boolean) safeCall(file, false, FileExtKt$isDirectorySafe$1.INSTANCE)).booleanValue();
    }

    public static final File[] listFilesSafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return (File[]) safeCall(file, (Object) null, FileExtKt$listFilesSafe$1.INSTANCE);
    }

    public static final File[] listFilesSafe(File file, FileFilter fileFilter) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(fileFilter, "filter");
        return (File[]) safeCall(file, (Object) null, new FileExtKt$listFilesSafe$2(fileFilter));
    }

    public static final long lengthSafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return ((Number) safeCall(file, 0L, FileExtKt$lengthSafe$1.INSTANCE)).longValue();
    }

    public static final boolean mkdirsSafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return ((Boolean) safeCall(file, false, FileExtKt$mkdirsSafe$1.INSTANCE)).booleanValue();
    }

    public static final boolean renameToSafe(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, "dest");
        return ((Boolean) safeCall(file, false, new FileExtKt$renameToSafe$1(file2))).booleanValue();
    }

    public static /* synthetic */ String readTextSafe$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextSafe(file, charset);
    }

    public static final String readTextSafe(File file, Charset charset) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (!existsSafe(file) || !canReadSafe(file)) {
            return null;
        }
        return (String) safeCall(file, (Object) null, new FileExtKt$readTextSafe$1(charset));
    }

    public static final byte[] readBytesSafe(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        if (!existsSafe(file) || !canReadSafe(file)) {
            return null;
        }
        return (byte[]) safeCall(file, (Object) null, FileExtKt$readBytesSafe$1.INSTANCE);
    }

    public static /* synthetic */ List readLinesSafe$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readLinesSafe(file, charset);
    }

    public static final List<String> readLinesSafe(File file, Charset charset) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (!existsSafe(file) || !canReadSafe(file)) {
            return null;
        }
        return (List) safeCall(file, (Object) null, new FileExtKt$readLinesSafe$1(charset));
    }
}
