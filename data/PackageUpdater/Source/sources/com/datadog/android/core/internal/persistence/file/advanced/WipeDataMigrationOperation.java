package com.datadog.android.core.internal.persistence.file.advanced;

import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.utils.MiscUtilsKt;
import com.datadog.android.log.Logger;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/WipeDataMigrationOperation;", "Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrationOperation;", "targetDir", "Ljava/io/File;", "fileHandler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Ljava/io/File;Lcom/datadog/android/core/internal/persistence/file/FileHandler;Lcom/datadog/android/log/Logger;)V", "getFileHandler$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "getInternalLogger$dd_sdk_android_release", "()Lcom/datadog/android/log/Logger;", "getTargetDir$dd_sdk_android_release", "()Ljava/io/File;", "run", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WipeDataMigrationOperation.kt */
public final class WipeDataMigrationOperation implements DataMigrationOperation {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_RETRY = 3;
    private static final long RETRY_DELAY_NS = TimeUnit.MILLISECONDS.toNanos(500);
    public static final String WARN_NULL_DIR = "Can't wipe data from a null directory";
    private final FileHandler fileHandler;
    private final Logger internalLogger;
    private final File targetDir;

    public WipeDataMigrationOperation(File file, FileHandler fileHandler2, Logger logger) {
        Intrinsics.checkNotNullParameter(fileHandler2, "fileHandler");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        this.targetDir = file;
        this.fileHandler = fileHandler2;
        this.internalLogger = logger;
    }

    public final File getTargetDir$dd_sdk_android_release() {
        return this.targetDir;
    }

    public final FileHandler getFileHandler$dd_sdk_android_release() {
        return this.fileHandler;
    }

    public final Logger getInternalLogger$dd_sdk_android_release() {
        return this.internalLogger;
    }

    public void run() {
        if (this.targetDir == null) {
            Logger.w$default(this.internalLogger, WARN_NULL_DIR, (Throwable) null, (Map) null, 6, (Object) null);
        } else {
            MiscUtilsKt.retryWithDelay(3, RETRY_DELAY_NS, (Function0<Boolean>) new WipeDataMigrationOperation$run$1(this));
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/WipeDataMigrationOperation$Companion;", "", "()V", "MAX_RETRY", "", "RETRY_DELAY_NS", "", "WARN_NULL_DIR", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: WipeDataMigrationOperation.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
