package com.datadog.android.core.internal.persistence.file.batch;

import com.datadog.android.core.internal.persistence.Batch;
import com.datadog.android.core.internal.persistence.DataReader;
import com.datadog.android.core.internal.persistence.PayloadDecoration;
import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.utils.ByteArrayExtKt;
import com.datadog.android.log.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 %2\u00020\u0001:\u0001%B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0015H\u0002J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010 \u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u0010 \u001a\u00020\u00172\u0006\u0010#\u001a\u00020$2\u0006\u0010!\u001a\u00020\"H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileDataReader;", "Lcom/datadog/android/core/internal/persistence/DataReader;", "fileOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "decoration", "Lcom/datadog/android/core/internal/persistence/PayloadDecoration;", "handler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Lcom/datadog/android/core/internal/persistence/PayloadDecoration;Lcom/datadog/android/core/internal/persistence/file/FileHandler;Lcom/datadog/android/log/Logger;)V", "getDecoration$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/PayloadDecoration;", "getFileOrchestrator$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "getHandler$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "getInternalLogger$dd_sdk_android_release", "()Lcom/datadog/android/log/Logger;", "lockedFiles", "", "Ljava/io/File;", "deleteFile", "", "file", "drop", "data", "Lcom/datadog/android/core/internal/persistence/Batch;", "dropAll", "getAndLockReadableFile", "lockAndReadNext", "release", "releaseFile", "delete", "", "fileName", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BatchFileDataReader.kt */
public final class BatchFileDataReader implements DataReader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String WARNING_DELETE_FAILED = "Unable to delete file: %s";
    public static final String WARNING_UNKNOWN_BATCH_ID = "Attempting to unlock or delete an unknown file: %s";
    private final PayloadDecoration decoration;
    private final FileOrchestrator fileOrchestrator;
    private final FileHandler handler;
    private final Logger internalLogger;
    private final List<File> lockedFiles = new ArrayList();

    public BatchFileDataReader(FileOrchestrator fileOrchestrator2, PayloadDecoration payloadDecoration, FileHandler fileHandler, Logger logger) {
        Intrinsics.checkNotNullParameter(fileOrchestrator2, "fileOrchestrator");
        Intrinsics.checkNotNullParameter(payloadDecoration, "decoration");
        Intrinsics.checkNotNullParameter(fileHandler, "handler");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        this.fileOrchestrator = fileOrchestrator2;
        this.decoration = payloadDecoration;
        this.handler = fileHandler;
        this.internalLogger = logger;
    }

    public final FileOrchestrator getFileOrchestrator$dd_sdk_android_release() {
        return this.fileOrchestrator;
    }

    public final PayloadDecoration getDecoration$dd_sdk_android_release() {
        return this.decoration;
    }

    public final FileHandler getHandler$dd_sdk_android_release() {
        return this.handler;
    }

    public final Logger getInternalLogger$dd_sdk_android_release() {
        return this.internalLogger;
    }

    public Batch lockAndReadNext() {
        File andLockReadableFile = getAndLockReadableFile();
        if (andLockReadableFile == null) {
            return null;
        }
        byte[] join = ByteArrayExtKt.join(this.handler.readData(andLockReadableFile), this.decoration.getSeparatorBytes(), this.decoration.getPrefixBytes(), this.decoration.getSuffixBytes());
        String name = andLockReadableFile.getName();
        Intrinsics.checkNotNullExpressionValue(name, "file.name");
        return new Batch(name, join);
    }

    public void release(Batch batch) {
        Intrinsics.checkNotNullParameter(batch, MPDbAdapter.KEY_DATA);
        releaseFile(batch.getId(), false);
    }

    public void drop(Batch batch) {
        Intrinsics.checkNotNullParameter(batch, MPDbAdapter.KEY_DATA);
        releaseFile(batch.getId(), true);
    }

    public void dropAll() {
        synchronized (this.lockedFiles) {
            int i = 0;
            Object[] array = this.lockedFiles.toArray(new File[0]);
            if (array != null) {
                int length = array.length;
                while (i < length) {
                    Object obj = array[i];
                    i++;
                    releaseFile((File) obj, true);
                }
                Unit unit = Unit.INSTANCE;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
        }
        for (File deleteFile : this.fileOrchestrator.getAllFiles()) {
            deleteFile(deleteFile);
        }
    }

    private final File getAndLockReadableFile() {
        File readableFile;
        synchronized (this.lockedFiles) {
            readableFile = getFileOrchestrator$dd_sdk_android_release().getReadableFile(CollectionsKt.toSet(this.lockedFiles));
            if (readableFile != null) {
                this.lockedFiles.add(readableFile);
            }
        }
        return readableFile;
    }

    private final void releaseFile(String str, boolean z) {
        Object obj;
        File file;
        synchronized (this.lockedFiles) {
            Iterator it = this.lockedFiles.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((File) obj).getName(), (Object) str)) {
                    break;
                }
            }
            file = (File) obj;
        }
        if (file != null) {
            releaseFile(file, z);
            return;
        }
        Logger logger = this.internalLogger;
        String format = String.format(Locale.US, WARNING_UNKNOWN_BATCH_ID, Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
        Logger.w$default(logger, format, (Throwable) null, (Map) null, 6, (Object) null);
    }

    private final void releaseFile(File file, boolean z) {
        if (z) {
            deleteFile(file);
        }
        synchronized (this.lockedFiles) {
            this.lockedFiles.remove(file);
        }
    }

    private final void deleteFile(File file) {
        if (!this.handler.delete(file)) {
            Logger logger = this.internalLogger;
            String format = String.format(Locale.US, "Unable to delete file: %s", Arrays.copyOf(new Object[]{file.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            Logger.w$default(logger, format, (Throwable) null, (Map) null, 6, (Object) null);
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileDataReader$Companion;", "", "()V", "WARNING_DELETE_FAILED", "", "WARNING_UNKNOWN_BATCH_ID", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: BatchFileDataReader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
