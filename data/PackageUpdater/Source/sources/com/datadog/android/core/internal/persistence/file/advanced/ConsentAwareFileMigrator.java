package com.datadog.android.core.internal.persistence.file.advanced;

import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.privacy.TrackingConsent;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ*\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/ConsentAwareFileMigrator;", "Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrator;", "Lcom/datadog/android/privacy/TrackingConsent;", "fileHandler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "executorService", "Ljava/util/concurrent/ExecutorService;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Lcom/datadog/android/core/internal/persistence/file/FileHandler;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/log/Logger;)V", "migrateData", "", "previousState", "previousFileOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "newState", "newFileOrchestrator", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ConsentAwareFileMigrator.kt */
public final class ConsentAwareFileMigrator implements DataMigrator<TrackingConsent> {
    private final ExecutorService executorService;
    private final FileHandler fileHandler;
    private final Logger internalLogger;

    public ConsentAwareFileMigrator(FileHandler fileHandler2, ExecutorService executorService2, Logger logger) {
        Intrinsics.checkNotNullParameter(fileHandler2, "fileHandler");
        Intrinsics.checkNotNullParameter(executorService2, "executorService");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        this.fileHandler = fileHandler2;
        this.executorService = executorService2;
        this.internalLogger = logger;
    }

    public void migrateData(TrackingConsent trackingConsent, FileOrchestrator fileOrchestrator, TrackingConsent trackingConsent2, FileOrchestrator fileOrchestrator2) {
        boolean z;
        boolean z2;
        boolean z3;
        DataMigrationOperation dataMigrationOperation;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        Intrinsics.checkNotNullParameter(fileOrchestrator, "previousFileOrchestrator");
        Intrinsics.checkNotNullParameter(trackingConsent2, "newState");
        Intrinsics.checkNotNullParameter(fileOrchestrator2, "newFileOrchestrator");
        Pair pair = TuplesKt.m78to(trackingConsent, trackingConsent2);
        boolean z8 = true;
        if (Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(null, TrackingConsent.PENDING))) {
            z = true;
        } else {
            z = Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(null, TrackingConsent.GRANTED));
        }
        if (z) {
            z2 = true;
        } else {
            z2 = Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(null, TrackingConsent.NOT_GRANTED));
        }
        if (z2) {
            z3 = true;
        } else {
            z3 = Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(TrackingConsent.PENDING, TrackingConsent.NOT_GRANTED));
        }
        if (z3) {
            dataMigrationOperation = new WipeDataMigrationOperation(fileOrchestrator.getRootDir(), this.fileHandler, this.internalLogger);
        } else {
            if (Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(TrackingConsent.GRANTED, TrackingConsent.PENDING))) {
                z4 = true;
            } else {
                z4 = Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(TrackingConsent.NOT_GRANTED, TrackingConsent.PENDING));
            }
            if (z4) {
                dataMigrationOperation = new WipeDataMigrationOperation(fileOrchestrator2.getRootDir(), this.fileHandler, this.internalLogger);
            } else if (Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(TrackingConsent.PENDING, TrackingConsent.GRANTED))) {
                dataMigrationOperation = new MoveDataMigrationOperation(fileOrchestrator.getRootDir(), fileOrchestrator2.getRootDir(), this.fileHandler, this.internalLogger);
            } else {
                if (Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(TrackingConsent.PENDING, TrackingConsent.PENDING))) {
                    z5 = true;
                } else {
                    z5 = Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(TrackingConsent.GRANTED, TrackingConsent.GRANTED));
                }
                if (z5) {
                    z6 = true;
                } else {
                    z6 = Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(TrackingConsent.GRANTED, TrackingConsent.NOT_GRANTED));
                }
                if (z6) {
                    z7 = true;
                } else {
                    z7 = Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(TrackingConsent.NOT_GRANTED, TrackingConsent.NOT_GRANTED));
                }
                if (!z7) {
                    z8 = Intrinsics.areEqual((Object) pair, (Object) TuplesKt.m78to(TrackingConsent.NOT_GRANTED, TrackingConsent.GRANTED));
                }
                if (z8) {
                    dataMigrationOperation = new NoOpDataMigrationOperation();
                } else {
                    LogUtilsKt.warningWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Unexpected consent migration from " + trackingConsent + " to " + trackingConsent2, (Throwable) null, (Map) null, 6, (Object) null);
                    dataMigrationOperation = new NoOpDataMigrationOperation();
                }
            }
        }
        try {
            this.executorService.submit(dataMigrationOperation);
        } catch (RejectedExecutionException e) {
            Logger.e$default(this.internalLogger, "Unable to schedule migration on the executor", e, (Map) null, 4, (Object) null);
        }
    }
}
