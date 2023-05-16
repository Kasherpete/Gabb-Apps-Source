package com.datadog.android.error.internal;

import com.datadog.android.core.internal.persistence.file.batch.BatchFilePersistenceStrategy;
import com.datadog.android.log.model.LogEvent;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\r¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/error/internal/CrashReportFilePersistenceStrategy;", "Lcom/datadog/android/core/internal/persistence/file/batch/BatchFilePersistenceStrategy;", "Lcom/datadog/android/log/model/LogEvent;", "consentProvider", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "context", "Landroid/content/Context;", "executorService", "Ljava/util/concurrent/ExecutorService;", "internalLogger", "Lcom/datadog/android/log/Logger;", "localDataEncryption", "Lcom/datadog/android/security/Encryption;", "(Lcom/datadog/android/core/internal/privacy/ConsentProvider;Landroid/content/Context;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/log/Logger;Lcom/datadog/android/security/Encryption;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CrashReportFilePersistenceStrategy.kt */
public final class CrashReportFilePersistenceStrategy extends BatchFilePersistenceStrategy<LogEvent> {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CrashReportFilePersistenceStrategy(com.datadog.android.core.internal.privacy.ConsentProvider r9, android.content.Context r10, java.util.concurrent.ExecutorService r11, com.datadog.android.log.Logger r12, com.datadog.android.security.Encryption r13) {
        /*
            r8 = this;
            java.lang.String r0 = "consentProvider"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "executorService"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "internalLogger"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            com.datadog.android.core.internal.persistence.file.advanced.FeatureFileOrchestrator r0 = new com.datadog.android.core.internal.persistence.file.advanced.FeatureFileOrchestrator
            java.lang.String r4 = "crash"
            r1 = r0
            r2 = r9
            r3 = r10
            r5 = r11
            r6 = r12
            r1.<init>(r2, r3, r4, r5, r6)
            r2 = r0
            com.datadog.android.core.internal.persistence.file.FileOrchestrator r2 = (com.datadog.android.core.internal.persistence.file.FileOrchestrator) r2
            com.datadog.android.log.internal.domain.event.LogEventSerializer r0 = new com.datadog.android.log.internal.domain.event.LogEventSerializer
            r1 = 0
            r3 = 1
            r0.<init>(r1, r3, r1)
            r4 = r0
            com.datadog.android.core.internal.persistence.Serializer r4 = (com.datadog.android.core.internal.persistence.Serializer) r4
            com.datadog.android.core.internal.persistence.PayloadDecoration$Companion r0 = com.datadog.android.core.internal.persistence.PayloadDecoration.Companion
            com.datadog.android.core.internal.persistence.PayloadDecoration r5 = r0.getJSON_ARRAY_DECORATION()
            com.datadog.android.log.Logger r6 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler$Companion r0 = com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler.Companion
            com.datadog.android.log.Logger r1 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            com.datadog.android.core.internal.persistence.file.FileHandler r0 = r0.create(r1, r13)
            r1 = r8
            r3 = r11
            r7 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.error.internal.CrashReportFilePersistenceStrategy.<init>(com.datadog.android.core.internal.privacy.ConsentProvider, android.content.Context, java.util.concurrent.ExecutorService, com.datadog.android.log.Logger, com.datadog.android.security.Encryption):void");
    }
}
