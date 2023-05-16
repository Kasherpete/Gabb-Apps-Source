package com.datadog.android.core.internal.persistence.file;

import java.io.File;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\ba\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH&J\n\u0010\t\u001a\u0004\u0018\u00010\u0004H&J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "", "getAllFiles", "", "Ljava/io/File;", "getFlushableFiles", "getReadableFile", "excludeFiles", "", "getRootDir", "getWritableFile", "dataSize", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FileOrchestrator.kt */
public interface FileOrchestrator {
    List<File> getAllFiles();

    List<File> getFlushableFiles();

    File getReadableFile(Set<? extends File> set);

    File getRootDir();

    File getWritableFile(int i);
}
