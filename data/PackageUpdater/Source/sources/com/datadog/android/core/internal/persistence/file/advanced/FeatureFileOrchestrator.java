package com.datadog.android.core.internal.persistence.file.advanced;

import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.persistence.file.FilePersistenceConfig;
import com.datadog.android.core.internal.privacy.ConsentProvider;
import com.datadog.android.privacy.TrackingConsent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\u0010\u0013¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/FeatureFileOrchestrator;", "Lcom/datadog/android/core/internal/persistence/file/advanced/ConsentAwareFileOrchestrator;", "consentProvider", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "context", "Landroid/content/Context;", "featureName", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Lcom/datadog/android/core/internal/privacy/ConsentProvider;Landroid/content/Context;Ljava/lang/String;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/log/Logger;)V", "pendingOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "grantedOrchestrator", "dataMigrator", "Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrator;", "Lcom/datadog/android/privacy/TrackingConsent;", "(Lcom/datadog/android/core/internal/privacy/ConsentProvider;Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrator;)V", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FeatureFileOrchestrator.kt */
public final class FeatureFileOrchestrator extends ConsentAwareFileOrchestrator {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String GRANTED_DIR = "dd-%s-v2";
    public static final String PENDING_DIR = "dd-%s-pending-v2";
    private static final FilePersistenceConfig PERSISTENCE_CONFIG = new FilePersistenceConfig(0, 0, 0, 0, 0, 0, 63, (DefaultConstructorMarker) null);
    public static final int VERSION = 2;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeatureFileOrchestrator(ConsentProvider consentProvider, FileOrchestrator fileOrchestrator, FileOrchestrator fileOrchestrator2, DataMigrator<TrackingConsent> dataMigrator) {
        super(consentProvider, fileOrchestrator, fileOrchestrator2, dataMigrator);
        Intrinsics.checkNotNullParameter(consentProvider, "consentProvider");
        Intrinsics.checkNotNullParameter(fileOrchestrator, "pendingOrchestrator");
        Intrinsics.checkNotNullParameter(fileOrchestrator2, "grantedOrchestrator");
        Intrinsics.checkNotNullParameter(dataMigrator, "dataMigrator");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FeatureFileOrchestrator(com.datadog.android.core.internal.privacy.ConsentProvider r10, android.content.Context r11, java.lang.String r12, java.util.concurrent.ExecutorService r13, com.datadog.android.log.Logger r14) {
        /*
            r9 = this;
            java.lang.String r0 = "consentProvider"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "featureName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "executorService"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "internalLogger"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            com.datadog.android.core.internal.persistence.file.batch.BatchFileOrchestrator r0 = new com.datadog.android.core.internal.persistence.file.batch.BatchFileOrchestrator
            java.io.File r1 = new java.io.File
            java.io.File r2 = r11.getCacheDir()
            java.util.Locale r3 = java.util.Locale.US
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r6 = 0
            r5[r6] = r12
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r4)
            java.lang.String r7 = "dd-%s-pending-v2"
            java.lang.String r3 = java.lang.String.format(r3, r7, r5)
            java.lang.String r5 = "format(locale, this, *args)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
            r1.<init>(r2, r3)
            com.datadog.android.core.internal.persistence.file.FilePersistenceConfig r2 = PERSISTENCE_CONFIG
            r0.<init>(r1, r2, r14)
            com.datadog.android.core.internal.persistence.file.FileOrchestrator r0 = (com.datadog.android.core.internal.persistence.file.FileOrchestrator) r0
            com.datadog.android.core.internal.persistence.file.batch.BatchFileOrchestrator r1 = new com.datadog.android.core.internal.persistence.file.batch.BatchFileOrchestrator
            java.io.File r3 = new java.io.File
            java.io.File r11 = r11.getCacheDir()
            java.util.Locale r7 = java.util.Locale.US
            java.lang.Object[] r8 = new java.lang.Object[r4]
            r8[r6] = r12
            java.lang.Object[] r12 = java.util.Arrays.copyOf(r8, r4)
            java.lang.String r4 = "dd-%s-v2"
            java.lang.String r12 = java.lang.String.format(r7, r4, r12)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r5)
            r3.<init>(r11, r12)
            r1.<init>(r3, r2, r14)
            com.datadog.android.core.internal.persistence.file.FileOrchestrator r1 = (com.datadog.android.core.internal.persistence.file.FileOrchestrator) r1
            com.datadog.android.core.internal.persistence.file.advanced.ConsentAwareFileMigrator r11 = new com.datadog.android.core.internal.persistence.file.advanced.ConsentAwareFileMigrator
            com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler r12 = new com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler
            r4 = 0
            r5 = 0
            r6 = 6
            r7 = 0
            r2 = r12
            r3 = r14
            r2.<init>(r3, r4, r5, r6, r7)
            com.datadog.android.core.internal.persistence.file.FileHandler r12 = (com.datadog.android.core.internal.persistence.file.FileHandler) r12
            r11.<init>(r12, r13, r14)
            com.datadog.android.core.internal.persistence.file.advanced.DataMigrator r11 = (com.datadog.android.core.internal.persistence.file.advanced.DataMigrator) r11
            r9.<init>(r10, r0, r1, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.internal.persistence.file.advanced.FeatureFileOrchestrator.<init>(com.datadog.android.core.internal.privacy.ConsentProvider, android.content.Context, java.lang.String, java.util.concurrent.ExecutorService, com.datadog.android.log.Logger):void");
    }

    @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/FeatureFileOrchestrator$Companion;", "", "()V", "GRANTED_DIR", "", "PENDING_DIR", "PERSISTENCE_CONFIG", "Lcom/datadog/android/core/internal/persistence/file/FilePersistenceConfig;", "VERSION", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: FeatureFileOrchestrator.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
