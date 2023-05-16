package com.datadog.android.webview.internal.log;

import com.datadog.android.core.internal.persistence.file.batch.BatchFilePersistenceStrategy;
import com.google.gson.JsonObject;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\r¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/log/WebViewLogFilePersistenceStrategy;", "Lcom/datadog/android/core/internal/persistence/file/batch/BatchFilePersistenceStrategy;", "Lcom/google/gson/JsonObject;", "consentProvider", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "context", "Landroid/content/Context;", "executorService", "Ljava/util/concurrent/ExecutorService;", "internalLogger", "Lcom/datadog/android/log/Logger;", "localDataEncryption", "Lcom/datadog/android/security/Encryption;", "(Lcom/datadog/android/core/internal/privacy/ConsentProvider;Landroid/content/Context;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/log/Logger;Lcom/datadog/android/security/Encryption;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WebViewLogFilePersistenceStrategy.kt */
public final class WebViewLogFilePersistenceStrategy extends BatchFilePersistenceStrategy<JsonObject> {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebViewLogFilePersistenceStrategy(com.datadog.android.core.internal.privacy.ConsentProvider r10, android.content.Context r11, java.util.concurrent.ExecutorService r12, com.datadog.android.log.Logger r13, com.datadog.android.security.Encryption r14) {
        /*
            r9 = this;
            java.lang.String r0 = "consentProvider"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "executorService"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "internalLogger"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            com.datadog.android.core.internal.persistence.file.advanced.FeatureFileOrchestrator r8 = new com.datadog.android.core.internal.persistence.file.advanced.FeatureFileOrchestrator
            java.lang.String r3 = "web-logs"
            r0 = r8
            r1 = r10
            r2 = r11
            r4 = r12
            r5 = r13
            r0.<init>(r1, r2, r3, r4, r5)
            r2 = r8
            com.datadog.android.core.internal.persistence.file.FileOrchestrator r2 = (com.datadog.android.core.internal.persistence.file.FileOrchestrator) r2
            com.datadog.android.log.internal.domain.event.WebViewLogEventSerializer r0 = new com.datadog.android.log.internal.domain.event.WebViewLogEventSerializer
            r0.<init>()
            r4 = r0
            com.datadog.android.core.internal.persistence.Serializer r4 = (com.datadog.android.core.internal.persistence.Serializer) r4
            com.datadog.android.core.internal.persistence.PayloadDecoration$Companion r0 = com.datadog.android.core.internal.persistence.PayloadDecoration.Companion
            com.datadog.android.core.internal.persistence.PayloadDecoration r5 = r0.getJSON_ARRAY_DECORATION()
            com.datadog.android.log.Logger r0 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler$Companion r1 = com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler.Companion
            com.datadog.android.core.internal.persistence.file.FileHandler r8 = r1.create(r13, r14)
            r1 = r9
            r3 = r12
            r6 = r0
            r7 = r8
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.webview.internal.log.WebViewLogFilePersistenceStrategy.<init>(com.datadog.android.core.internal.privacy.ConsentProvider, android.content.Context, java.util.concurrent.ExecutorService, com.datadog.android.log.Logger, com.datadog.android.security.Encryption):void");
    }
}
