package com.datadog.android.core.internal.data.upload;

import com.datadog.android.core.internal.net.DataUploader;
import com.datadog.android.core.internal.persistence.PayloadDecoration;
import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.utils.ByteArrayExtKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/core/internal/data/upload/DataFlusher;", "Lcom/datadog/android/core/internal/data/upload/Flusher;", "fileOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "decoration", "Lcom/datadog/android/core/internal/persistence/PayloadDecoration;", "handler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "(Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Lcom/datadog/android/core/internal/persistence/PayloadDecoration;Lcom/datadog/android/core/internal/persistence/file/FileHandler;)V", "getDecoration$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/PayloadDecoration;", "getFileOrchestrator$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "getHandler$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "flush", "", "uploader", "Lcom/datadog/android/core/internal/net/DataUploader;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DataFlusher.kt */
public final class DataFlusher implements Flusher {
    private final PayloadDecoration decoration;
    private final FileOrchestrator fileOrchestrator;
    private final FileHandler handler;

    public DataFlusher(FileOrchestrator fileOrchestrator2, PayloadDecoration payloadDecoration, FileHandler fileHandler) {
        Intrinsics.checkNotNullParameter(fileOrchestrator2, "fileOrchestrator");
        Intrinsics.checkNotNullParameter(payloadDecoration, "decoration");
        Intrinsics.checkNotNullParameter(fileHandler, "handler");
        this.fileOrchestrator = fileOrchestrator2;
        this.decoration = payloadDecoration;
        this.handler = fileHandler;
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

    public void flush(DataUploader dataUploader) {
        Intrinsics.checkNotNullParameter(dataUploader, "uploader");
        for (File file : this.fileOrchestrator.getFlushableFiles()) {
            dataUploader.upload(ByteArrayExtKt.join(getHandler$dd_sdk_android_release().readData(file), getDecoration$dd_sdk_android_release().getSeparatorBytes(), getDecoration$dd_sdk_android_release().getPrefixBytes(), getDecoration$dd_sdk_android_release().getSuffixBytes()));
            getHandler$dd_sdk_android_release().delete(file);
        }
    }
}
