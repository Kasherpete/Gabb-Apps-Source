package com.datadog.android.webview.internal.rum;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.persistence.PayloadDecoration;
import com.datadog.android.core.internal.persistence.Serializer;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.persistence.file.advanced.ScheduledWriter;
import com.datadog.android.core.internal.persistence.file.batch.BatchFilePersistenceStrategy;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.internal.domain.RumDataWriter;
import java.io.File;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJA\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\nH\u0010¢\u0006\u0002\b\u0018R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/rum/WebViewRumFilePersistenceStrategy;", "Lcom/datadog/android/core/internal/persistence/file/batch/BatchFilePersistenceStrategy;", "", "consentProvider", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "context", "Landroid/content/Context;", "executorService", "Ljava/util/concurrent/ExecutorService;", "internalLogger", "Lcom/datadog/android/log/Logger;", "localDataEncryption", "Lcom/datadog/android/security/Encryption;", "lastViewEventFile", "Ljava/io/File;", "(Lcom/datadog/android/core/internal/privacy/ConsentProvider;Landroid/content/Context;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/log/Logger;Lcom/datadog/android/security/Encryption;Ljava/io/File;)V", "createWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "fileOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "serializer", "Lcom/datadog/android/core/internal/persistence/Serializer;", "payloadDecoration", "Lcom/datadog/android/core/internal/persistence/PayloadDecoration;", "createWriter$dd_sdk_android_release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WebViewRumFilePersistenceStrategy.kt */
public final class WebViewRumFilePersistenceStrategy extends BatchFilePersistenceStrategy<Object> {
    private final File lastViewEventFile;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebViewRumFilePersistenceStrategy(com.datadog.android.core.internal.privacy.ConsentProvider r11, android.content.Context r12, java.util.concurrent.ExecutorService r13, com.datadog.android.log.Logger r14, com.datadog.android.security.Encryption r15, java.io.File r16) {
        /*
            r10 = this;
            r6 = r14
            r7 = r16
            java.lang.String r0 = "consentProvider"
            r1 = r11
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "context"
            r2 = r12
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "executorService"
            r8 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "internalLogger"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "lastViewEventFile"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            com.datadog.android.core.internal.persistence.file.advanced.FeatureFileOrchestrator r9 = new com.datadog.android.core.internal.persistence.file.advanced.FeatureFileOrchestrator
            java.lang.String r3 = "web-rum"
            r0 = r9
            r4 = r13
            r5 = r14
            r0.<init>(r1, r2, r3, r4, r5)
            r1 = r9
            com.datadog.android.core.internal.persistence.file.FileOrchestrator r1 = (com.datadog.android.core.internal.persistence.file.FileOrchestrator) r1
            com.datadog.android.rum.internal.domain.event.RumEventSerializer r0 = new com.datadog.android.rum.internal.domain.event.RumEventSerializer
            r2 = 0
            r3 = 1
            r0.<init>(r2, r3, r2)
            r3 = r0
            com.datadog.android.core.internal.persistence.Serializer r3 = (com.datadog.android.core.internal.persistence.Serializer) r3
            com.datadog.android.core.internal.persistence.PayloadDecoration$Companion r0 = com.datadog.android.core.internal.persistence.PayloadDecoration.Companion
            com.datadog.android.core.internal.persistence.PayloadDecoration r4 = r0.getNEW_LINE_DECORATION()
            com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler$Companion r0 = com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler.Companion
            r2 = r15
            com.datadog.android.core.internal.persistence.file.FileHandler r9 = r0.create(r14, r15)
            r0 = r10
            r2 = r13
            r6 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r0.lastViewEventFile = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.webview.internal.rum.WebViewRumFilePersistenceStrategy.<init>(com.datadog.android.core.internal.privacy.ConsentProvider, android.content.Context, java.util.concurrent.ExecutorService, com.datadog.android.log.Logger, com.datadog.android.security.Encryption, java.io.File):void");
    }

    public DataWriter<Object> createWriter$dd_sdk_android_release(FileOrchestrator fileOrchestrator, ExecutorService executorService, Serializer<Object> serializer, PayloadDecoration payloadDecoration, Logger logger) {
        Intrinsics.checkNotNullParameter(fileOrchestrator, "fileOrchestrator");
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(payloadDecoration, "payloadDecoration");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        return new ScheduledWriter<>(new RumDataWriter(fileOrchestrator, serializer, payloadDecoration, getFileHandler$dd_sdk_android_release(), logger, this.lastViewEventFile), executorService, logger);
    }
}
