package com.datadog.android.core.internal.persistence.file;

import java.io.File;
import java.util.List;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0012\n\u0002\b\u0005\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H&J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u0005H&J \u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0003H&¨\u0006\u0010"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "", "delete", "", "target", "Ljava/io/File;", "moveFiles", "srcDir", "destDir", "readData", "", "", "file", "writeData", "data", "append", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FileHandler.kt */
public interface FileHandler {
    boolean delete(File file);

    boolean moveFiles(File file, File file2);

    List<byte[]> readData(File file);

    boolean writeData(File file, byte[] bArr, boolean z);
}
