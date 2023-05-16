package com.datadog.android.core.internal.persistence.file.batch;

import com.datadog.android.core.internal.persistence.file.FileExtKt;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.persistence.file.FilePersistenceConfig;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0003H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016H\u0016J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u001aH\u0016J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001c\u001a\u00020\rH\u0002J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001c\u001a\u00020\rH\u0016J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u000fH\u0002J\b\u0010#\u001a\u00020 H\u0002J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileOrchestrator;", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "rootDir", "Ljava/io/File;", "config", "Lcom/datadog/android/core/internal/persistence/file/FilePersistenceConfig;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Ljava/io/File;Lcom/datadog/android/core/internal/persistence/file/FilePersistenceConfig;Lcom/datadog/android/log/Logger;)V", "fileFilter", "Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileOrchestrator$BatchFileFilter;", "previousFile", "previousFileItemCount", "", "recentReadDelayMs", "", "recentWriteDelayMs", "createNewFile", "deleteObsoleteFiles", "", "freeSpaceIfNeeded", "getAllFiles", "", "getFlushableFiles", "getReadableFile", "excludeFiles", "", "getReusableWritableFile", "dataSize", "getRootDir", "getWritableFile", "isFileRecent", "", "file", "delayMs", "isRootDirValid", "listSortedBatchFiles", "BatchFileFilter", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BatchFileOrchestrator.kt */
public final class BatchFileOrchestrator implements FileOrchestrator {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ERROR_CANT_CREATE_ROOT = "The provided root file can't be created: %s";
    public static final String ERROR_DISK_FULL = "Too much disk space used (%d/%d): cleaning up to free %d bytes…";
    public static final String ERROR_LARGE_DATA = "Can't write data with size %d (max item size is %d)";
    public static final String ERROR_ROOT_NOT_DIR = "The provided root file is not a directory: %s";
    public static final String ERROR_ROOT_NOT_WRITABLE = "The provided root dir is not writable: %s";
    /* access modifiers changed from: private */
    public static final Regex batchFileNameRegex = new Regex("\\d+");
    private final FilePersistenceConfig config;
    private final BatchFileFilter fileFilter = new BatchFileFilter();
    private final Logger internalLogger;
    private File previousFile;
    private int previousFileItemCount;
    private final long recentReadDelayMs;
    private final long recentWriteDelayMs;
    private final File rootDir;

    public BatchFileOrchestrator(File file, FilePersistenceConfig filePersistenceConfig, Logger logger) {
        Intrinsics.checkNotNullParameter(file, "rootDir");
        Intrinsics.checkNotNullParameter(filePersistenceConfig, "config");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        this.rootDir = file;
        this.config = filePersistenceConfig;
        this.internalLogger = logger;
        this.recentReadDelayMs = (long) (((double) filePersistenceConfig.getRecentDelayMs()) * 1.05d);
        this.recentWriteDelayMs = (long) (((double) filePersistenceConfig.getRecentDelayMs()) * 0.95d);
    }

    public File getWritableFile(int i) {
        if (!isRootDirValid()) {
            return null;
        }
        if (((long) i) > this.config.getMaxItemSize()) {
            Logger logger = this.internalLogger;
            String format = String.format(Locale.US, ERROR_LARGE_DATA, Arrays.copyOf(new Object[]{Integer.valueOf(i), Long.valueOf(this.config.getMaxItemSize())}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger, format, (Throwable) null, (Map) null, 6, (Object) null);
            return null;
        }
        deleteObsoleteFiles();
        freeSpaceIfNeeded();
        File reusableWritableFile = getReusableWritableFile(i);
        return reusableWritableFile == null ? createNewFile() : reusableWritableFile;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.io.File} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File getReadableFile(java.util.Set<? extends java.io.File> r7) {
        /*
            r6 = this;
            java.lang.String r0 = "excludeFiles"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            boolean r0 = r6.isRootDirValid()
            r1 = 0
            if (r0 != 0) goto L_0x000d
            return r1
        L_0x000d:
            r6.deleteObsoleteFiles()
            java.util.List r0 = r6.listSortedBatchFiles()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x001a:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x003b
            java.lang.Object r2 = r0.next()
            r3 = r2
            java.io.File r3 = (java.io.File) r3
            boolean r4 = r7.contains(r3)
            if (r4 != 0) goto L_0x0037
            long r4 = r6.recentReadDelayMs
            boolean r3 = r6.isFileRecent(r3, r4)
            if (r3 != 0) goto L_0x0037
            r3 = 1
            goto L_0x0038
        L_0x0037:
            r3 = 0
        L_0x0038:
            if (r3 == 0) goto L_0x001a
            r1 = r2
        L_0x003b:
            java.io.File r1 = (java.io.File) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.internal.persistence.file.batch.BatchFileOrchestrator.getReadableFile(java.util.Set):java.io.File");
    }

    public List<File> getAllFiles() {
        if (!isRootDirValid()) {
            return CollectionsKt.emptyList();
        }
        return listSortedBatchFiles();
    }

    public List<File> getFlushableFiles() {
        return getAllFiles();
    }

    public File getRootDir() {
        if (!isRootDirValid()) {
            return null;
        }
        return this.rootDir;
    }

    private final boolean isRootDirValid() {
        if (!FileExtKt.existsSafe(this.rootDir)) {
            synchronized (this.rootDir) {
                if (FileExtKt.existsSafe(this.rootDir)) {
                    return true;
                }
                if (FileExtKt.mkdirsSafe(this.rootDir)) {
                    return true;
                }
                Logger logger = this.internalLogger;
                String format = String.format(Locale.US, ERROR_CANT_CREATE_ROOT, Arrays.copyOf(new Object[]{this.rootDir.getPath()}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
                LogUtilsKt.errorWithTelemetry$default(logger, format, (Throwable) null, (Map) null, 6, (Object) null);
                return false;
            }
        } else if (!this.rootDir.isDirectory()) {
            Logger logger2 = this.internalLogger;
            String format2 = String.format(Locale.US, ERROR_ROOT_NOT_DIR, Arrays.copyOf(new Object[]{this.rootDir.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger2, format2, (Throwable) null, (Map) null, 6, (Object) null);
            return false;
        } else if (FileExtKt.canWriteSafe(this.rootDir)) {
            return true;
        } else {
            Logger logger3 = this.internalLogger;
            String format3 = String.format(Locale.US, ERROR_ROOT_NOT_WRITABLE, Arrays.copyOf(new Object[]{this.rootDir.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger3, format3, (Throwable) null, (Map) null, 6, (Object) null);
            return false;
        }
    }

    private final File createNewFile() {
        File file = new File(this.rootDir, String.valueOf(System.currentTimeMillis()));
        this.previousFile = file;
        this.previousFileItemCount = 1;
        return file;
    }

    private final File getReusableWritableFile(int i) {
        File file = (File) CollectionsKt.lastOrNull(listSortedBatchFiles());
        if (file == null) {
            return null;
        }
        File file2 = this.previousFile;
        int i2 = this.previousFileItemCount;
        if (!Intrinsics.areEqual((Object) file2, (Object) file)) {
            return null;
        }
        boolean isFileRecent = isFileRecent(file, this.recentWriteDelayMs);
        boolean z = false;
        boolean z2 = FileExtKt.lengthSafe(file) + ((long) i) < this.config.getMaxBatchSize();
        if (i2 < this.config.getMaxItemsPerBatch()) {
            z = true;
        }
        if (!isFileRecent || !z2 || !z) {
            return null;
        }
        this.previousFileItemCount = i2 + 1;
        return file;
    }

    private final boolean isFileRecent(File file, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "file.name");
        Long longOrNull = StringsKt.toLongOrNull(name);
        return (longOrNull == null ? 0 : longOrNull.longValue()) >= currentTimeMillis - j;
    }

    private final void deleteObsoleteFiles() {
        for (File deleteSafe : SequencesKt.filter(CollectionsKt.asSequence(listSortedBatchFiles()), new BatchFileOrchestrator$deleteObsoleteFiles$1(System.currentTimeMillis() - this.config.getOldFileThreshold()))) {
            FileExtKt.deleteSafe(deleteSafe);
        }
    }

    private final void freeSpaceIfNeeded() {
        Iterable<File> listSortedBatchFiles = listSortedBatchFiles();
        long j = 0;
        for (File lengthSafe : listSortedBatchFiles) {
            j += FileExtKt.lengthSafe(lengthSafe);
        }
        long maxDiskSpace = this.config.getMaxDiskSpace();
        long j2 = j - maxDiskSpace;
        if (j2 > 0) {
            Logger logger = this.internalLogger;
            String format = String.format(Locale.US, ERROR_DISK_FULL, Arrays.copyOf(new Object[]{Long.valueOf(j), Long.valueOf(maxDiskSpace), Long.valueOf(j2)}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger, format, (Throwable) null, (Map) null, 6, (Object) null);
            for (File file : listSortedBatchFiles) {
                if (j2 > 0) {
                    long lengthSafe2 = FileExtKt.lengthSafe(file);
                    if (FileExtKt.deleteSafe(file)) {
                        j2 -= lengthSafe2;
                    }
                }
            }
        }
    }

    private final List<File> listSortedBatchFiles() {
        File[] listFilesSafe = FileExtKt.listFilesSafe(this.rootDir, this.fileFilter);
        if (listFilesSafe == null) {
            listFilesSafe = new File[0];
        }
        return ArraysKt.sorted((T[]) (Comparable[]) listFilesSafe);
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileOrchestrator$BatchFileFilter;", "Ljava/io/FileFilter;", "()V", "accept", "", "file", "Ljava/io/File;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: BatchFileOrchestrator.kt */
    public static final class BatchFileFilter implements FileFilter {
        public boolean accept(File file) {
            if (file != null && FileExtKt.isFileSafe(file)) {
                String name = file.getName();
                Intrinsics.checkNotNullExpressionValue(name, "file.name");
                if (BatchFileOrchestrator.batchFileNameRegex.matches(name)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileOrchestrator$Companion;", "", "()V", "ERROR_CANT_CREATE_ROOT", "", "ERROR_DISK_FULL", "ERROR_LARGE_DATA", "ERROR_ROOT_NOT_DIR", "ERROR_ROOT_NOT_WRITABLE", "batchFileNameRegex", "Lkotlin/text/Regex;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: BatchFileOrchestrator.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
