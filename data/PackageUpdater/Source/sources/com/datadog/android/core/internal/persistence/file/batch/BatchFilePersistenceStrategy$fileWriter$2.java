package com.datadog.android.core.internal.persistence.file.batch;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.persistence.Serializer;
import com.datadog.android.log.Logger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo20735d2 = {"<anonymous>", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "T", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BatchFilePersistenceStrategy.kt */
final class BatchFilePersistenceStrategy$fileWriter$2 extends Lambda implements Function0<DataWriter<T>> {
    final /* synthetic */ Logger $internalLogger;
    final /* synthetic */ Serializer<T> $serializer;
    final /* synthetic */ BatchFilePersistenceStrategy<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BatchFilePersistenceStrategy$fileWriter$2(BatchFilePersistenceStrategy<T> batchFilePersistenceStrategy, Serializer<T> serializer, Logger logger) {
        super(0);
        this.this$0 = batchFilePersistenceStrategy;
        this.$serializer = serializer;
        this.$internalLogger = logger;
    }

    public final DataWriter<T> invoke() {
        BatchFilePersistenceStrategy<T> batchFilePersistenceStrategy = this.this$0;
        return batchFilePersistenceStrategy.createWriter$dd_sdk_android_release(batchFilePersistenceStrategy.fileOrchestrator, this.this$0.executorService, this.$serializer, this.this$0.payloadDecoration, this.$internalLogger);
    }
}
