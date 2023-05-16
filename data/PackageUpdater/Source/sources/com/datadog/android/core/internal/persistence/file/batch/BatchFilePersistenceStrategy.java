package com.datadog.android.core.internal.persistence.file.batch;

import com.datadog.android.core.internal.data.upload.DataFlusher;
import com.datadog.android.core.internal.data.upload.Flusher;
import com.datadog.android.core.internal.persistence.DataReader;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.persistence.PayloadDecoration;
import com.datadog.android.core.internal.persistence.PersistenceStrategy;
import com.datadog.android.core.internal.persistence.Serializer;
import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.persistence.file.advanced.ScheduledWriter;
import com.datadog.android.log.Logger;
import java.util.concurrent.ExecutorService;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B;\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010JA\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u001cJ\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFilePersistenceStrategy;", "T", "", "Lcom/datadog/android/core/internal/persistence/PersistenceStrategy;", "fileOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "executorService", "Ljava/util/concurrent/ExecutorService;", "serializer", "Lcom/datadog/android/core/internal/persistence/Serializer;", "payloadDecoration", "Lcom/datadog/android/core/internal/persistence/PayloadDecoration;", "internalLogger", "Lcom/datadog/android/log/Logger;", "fileHandler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "(Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/core/internal/persistence/Serializer;Lcom/datadog/android/core/internal/persistence/PayloadDecoration;Lcom/datadog/android/log/Logger;Lcom/datadog/android/core/internal/persistence/file/FileHandler;)V", "getFileHandler$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "fileReader", "Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileDataReader;", "fileWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "getFileWriter", "()Lcom/datadog/android/core/internal/persistence/DataWriter;", "fileWriter$delegate", "Lkotlin/Lazy;", "createWriter", "createWriter$dd_sdk_android_release", "getFlusher", "Lcom/datadog/android/core/internal/data/upload/Flusher;", "getReader", "Lcom/datadog/android/core/internal/persistence/DataReader;", "getWriter", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BatchFilePersistenceStrategy.kt */
public class BatchFilePersistenceStrategy<T> implements PersistenceStrategy<T> {
    /* access modifiers changed from: private */
    public final ExecutorService executorService;
    private final FileHandler fileHandler;
    /* access modifiers changed from: private */
    public final FileOrchestrator fileOrchestrator;
    private final BatchFileDataReader fileReader;
    private final Lazy fileWriter$delegate;
    /* access modifiers changed from: private */
    public final PayloadDecoration payloadDecoration;

    public BatchFilePersistenceStrategy(FileOrchestrator fileOrchestrator2, ExecutorService executorService2, Serializer<T> serializer, PayloadDecoration payloadDecoration2, Logger logger, FileHandler fileHandler2) {
        Intrinsics.checkNotNullParameter(fileOrchestrator2, "fileOrchestrator");
        Intrinsics.checkNotNullParameter(executorService2, "executorService");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(payloadDecoration2, "payloadDecoration");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        Intrinsics.checkNotNullParameter(fileHandler2, "fileHandler");
        this.fileOrchestrator = fileOrchestrator2;
        this.executorService = executorService2;
        this.payloadDecoration = payloadDecoration2;
        this.fileHandler = fileHandler2;
        this.fileWriter$delegate = LazyKt.lazy(new BatchFilePersistenceStrategy$fileWriter$2(this, serializer, logger));
        this.fileReader = new BatchFileDataReader(fileOrchestrator2, payloadDecoration2, fileHandler2, logger);
    }

    public final FileHandler getFileHandler$dd_sdk_android_release() {
        return this.fileHandler;
    }

    private final DataWriter<T> getFileWriter() {
        return (DataWriter) this.fileWriter$delegate.getValue();
    }

    public DataWriter<T> getWriter() {
        return getFileWriter();
    }

    public DataReader getReader() {
        return this.fileReader;
    }

    public Flusher getFlusher() {
        return new DataFlusher(this.fileOrchestrator, this.payloadDecoration, this.fileHandler);
    }

    public DataWriter<T> createWriter$dd_sdk_android_release(FileOrchestrator fileOrchestrator2, ExecutorService executorService2, Serializer<T> serializer, PayloadDecoration payloadDecoration2, Logger logger) {
        Intrinsics.checkNotNullParameter(fileOrchestrator2, "fileOrchestrator");
        Intrinsics.checkNotNullParameter(executorService2, "executorService");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(payloadDecoration2, "payloadDecoration");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        return new ScheduledWriter<>(new BatchFileDataWriter(fileOrchestrator2, serializer, payloadDecoration2, this.fileHandler, logger), executorService2, logger);
    }
}
