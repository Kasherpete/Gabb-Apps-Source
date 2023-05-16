package com.datadog.android.core.internal.persistence.file.single;

import com.datadog.android.core.internal.persistence.file.FileExtKt;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import java.io.File;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u0016J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u0016J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/single/SingleFileOrchestrator;", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "file", "Ljava/io/File;", "(Ljava/io/File;)V", "getAllFiles", "", "getFlushableFiles", "getReadableFile", "excludeFiles", "", "getRootDir", "getWritableFile", "dataSize", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SingleFileOrchestrator.kt */
public final class SingleFileOrchestrator implements FileOrchestrator {
    private final File file;

    public File getRootDir() {
        return null;
    }

    public SingleFileOrchestrator(File file2) {
        Intrinsics.checkNotNullParameter(file2, "file");
        this.file = file2;
    }

    public File getWritableFile(int i) {
        File parentFile = this.file.getParentFile();
        if (parentFile != null) {
            FileExtKt.mkdirsSafe(parentFile);
        }
        return this.file;
    }

    public File getReadableFile(Set<? extends File> set) {
        Intrinsics.checkNotNullParameter(set, "excludeFiles");
        File parentFile = this.file.getParentFile();
        if (parentFile != null) {
            FileExtKt.mkdirsSafe(parentFile);
        }
        if (set.contains(this.file)) {
            return null;
        }
        return this.file;
    }

    public List<File> getAllFiles() {
        File parentFile = this.file.getParentFile();
        if (parentFile != null) {
            FileExtKt.mkdirsSafe(parentFile);
        }
        return CollectionsKt.listOf(this.file);
    }

    public List<File> getFlushableFiles() {
        return getAllFiles();
    }
}
