package com.datadog.android.core.internal.persistence.file.batch;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.persistence.PayloadDecoration;
import com.datadog.android.core.internal.persistence.Serializer;
import com.datadog.android.core.internal.persistence.SerializerKt;
import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.log.Logger;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\u0010¢\u0006\u0004\b\u001e\u0010\u001cJ\u001f\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010 \u001a\u00020!H\u0010¢\u0006\u0004\b\"\u0010#J\u0015\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\u0016\u0010$\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020!H\u0002R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006*"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileDataWriter;", "T", "", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "fileOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "serializer", "Lcom/datadog/android/core/internal/persistence/Serializer;", "decoration", "Lcom/datadog/android/core/internal/persistence/PayloadDecoration;", "handler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Lcom/datadog/android/core/internal/persistence/Serializer;Lcom/datadog/android/core/internal/persistence/PayloadDecoration;Lcom/datadog/android/core/internal/persistence/file/FileHandler;Lcom/datadog/android/log/Logger;)V", "getDecoration$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/PayloadDecoration;", "getFileOrchestrator$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "getHandler$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "getInternalLogger$dd_sdk_android_release", "()Lcom/datadog/android/log/Logger;", "getSerializer$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/Serializer;", "consume", "", "data", "(Ljava/lang/Object;)V", "onDataWriteFailed", "onDataWriteFailed$dd_sdk_android_release", "onDataWritten", "rawData", "", "onDataWritten$dd_sdk_android_release", "(Ljava/lang/Object;[B)V", "write", "element", "", "writeData", "", "byteArray", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BatchFileDataWriter.kt */
public class BatchFileDataWriter<T> implements DataWriter<T> {
    private final PayloadDecoration decoration;
    private final FileOrchestrator fileOrchestrator;
    private final FileHandler handler;
    private final Logger internalLogger;
    private final Serializer<T> serializer;

    public void onDataWriteFailed$dd_sdk_android_release(T t) {
        Intrinsics.checkNotNullParameter(t, MPDbAdapter.KEY_DATA);
    }

    public void onDataWritten$dd_sdk_android_release(T t, byte[] bArr) {
        Intrinsics.checkNotNullParameter(t, MPDbAdapter.KEY_DATA);
        Intrinsics.checkNotNullParameter(bArr, "rawData");
    }

    public BatchFileDataWriter(FileOrchestrator fileOrchestrator2, Serializer<T> serializer2, PayloadDecoration payloadDecoration, FileHandler fileHandler, Logger logger) {
        Intrinsics.checkNotNullParameter(fileOrchestrator2, "fileOrchestrator");
        Intrinsics.checkNotNullParameter(serializer2, "serializer");
        Intrinsics.checkNotNullParameter(payloadDecoration, "decoration");
        Intrinsics.checkNotNullParameter(fileHandler, "handler");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        this.fileOrchestrator = fileOrchestrator2;
        this.serializer = serializer2;
        this.decoration = payloadDecoration;
        this.handler = fileHandler;
        this.internalLogger = logger;
    }

    public final FileOrchestrator getFileOrchestrator$dd_sdk_android_release() {
        return this.fileOrchestrator;
    }

    public final Serializer<T> getSerializer$dd_sdk_android_release() {
        return this.serializer;
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

    public void write(T t) {
        Intrinsics.checkNotNullParameter(t, "element");
        consume(t);
    }

    public void write(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, MPDbAdapter.KEY_DATA);
        for (Object consume : list) {
            consume(consume);
        }
    }

    private final void consume(T t) {
        byte[] serializeToByteArray = SerializerKt.serializeToByteArray(this.serializer, t, this.internalLogger);
        if (serializeToByteArray != null) {
            synchronized (this) {
                if (writeData(serializeToByteArray)) {
                    onDataWritten$dd_sdk_android_release(t, serializeToByteArray);
                } else {
                    onDataWriteFailed$dd_sdk_android_release(t);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    private final boolean writeData(byte[] bArr) {
        File writableFile = this.fileOrchestrator.getWritableFile(bArr.length);
        if (writableFile == null) {
            return false;
        }
        return this.handler.writeData(writableFile, bArr, true);
    }
}
