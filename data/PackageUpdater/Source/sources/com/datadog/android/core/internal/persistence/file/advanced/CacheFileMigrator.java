package com.datadog.android.core.internal.persistence.file.advanced;

import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.log.Logger;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ/\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/CacheFileMigrator;", "Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrator;", "", "fileHandler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "executorService", "Ljava/util/concurrent/ExecutorService;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Lcom/datadog/android/core/internal/persistence/file/FileHandler;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/log/Logger;)V", "migrateData", "", "previousState", "previousFileOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "newState", "newFileOrchestrator", "(Ljava/lang/Boolean;Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;ZLcom/datadog/android/core/internal/persistence/file/FileOrchestrator;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CacheFileMigrator.kt */
public final class CacheFileMigrator implements DataMigrator<Boolean> {
    private final ExecutorService executorService;
    private final FileHandler fileHandler;
    private final Logger internalLogger;

    public CacheFileMigrator(FileHandler fileHandler2, ExecutorService executorService2, Logger logger) {
        Intrinsics.checkNotNullParameter(fileHandler2, "fileHandler");
        Intrinsics.checkNotNullParameter(executorService2, "executorService");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        this.fileHandler = fileHandler2;
        this.executorService = executorService2;
        this.internalLogger = logger;
    }

    public /* bridge */ /* synthetic */ void migrateData(Object obj, FileOrchestrator fileOrchestrator, Object obj2, FileOrchestrator fileOrchestrator2) {
        migrateData((Boolean) obj, fileOrchestrator, ((Boolean) obj2).booleanValue(), fileOrchestrator2);
    }

    public void migrateData(Boolean bool, FileOrchestrator fileOrchestrator, boolean z, FileOrchestrator fileOrchestrator2) {
        Intrinsics.checkNotNullParameter(fileOrchestrator, "previousFileOrchestrator");
        Intrinsics.checkNotNullParameter(fileOrchestrator2, "newFileOrchestrator");
        if (z) {
            File rootDir = fileOrchestrator.getRootDir();
            MoveDataMigrationOperation moveDataMigrationOperation = new MoveDataMigrationOperation(rootDir, fileOrchestrator2.getRootDir(), this.fileHandler, this.internalLogger);
            WipeDataMigrationOperation wipeDataMigrationOperation = new WipeDataMigrationOperation(rootDir, this.fileHandler, this.internalLogger);
            try {
                this.executorService.submit(moveDataMigrationOperation);
            } catch (RejectedExecutionException e) {
                Logger.e$default(this.internalLogger, "Unable to schedule migration on the executor", e, (Map) null, 4, (Object) null);
            }
            try {
                this.executorService.submit(wipeDataMigrationOperation);
            } catch (RejectedExecutionException e2) {
                Logger.e$default(this.internalLogger, "Unable to schedule migration on the executor", e2, (Map) null, 4, (Object) null);
            }
        }
    }
}
