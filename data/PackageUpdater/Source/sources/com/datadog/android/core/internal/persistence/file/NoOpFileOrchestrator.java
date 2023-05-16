package com.datadog.android.core.internal.persistence.file;

import java.io.File;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/NoOpFileOrchestrator;", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "()V", "getAllFiles", "", "Ljava/io/File;", "getFlushableFiles", "getReadableFile", "excludeFiles", "", "getRootDir", "getWritableFile", "dataSize", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpFileOrchestrator.kt */
public final class NoOpFileOrchestrator implements FileOrchestrator {
    public File getReadableFile(Set<? extends File> set) {
        Intrinsics.checkNotNullParameter(set, "excludeFiles");
        return null;
    }

    public File getRootDir() {
        return null;
    }

    public File getWritableFile(int i) {
        return null;
    }

    public List<File> getFlushableFiles() {
        return CollectionsKt.emptyList();
    }

    public List<File> getAllFiles() {
        return CollectionsKt.emptyList();
    }
}
