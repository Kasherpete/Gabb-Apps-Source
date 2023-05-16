package com.datadog.android.core.internal.persistence.file.advanced;

import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b`\u0018\u0000 \u000b*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001\u000bJ/\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\u0007H&¢\u0006\u0002\u0010\n¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrator;", "S", "", "migrateData", "", "previousState", "previousFileOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "newState", "newFileOrchestrator", "(Ljava/lang/Object;Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Ljava/lang/Object;Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;)V", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DataMigrator.kt */
public interface DataMigrator<S> {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String ERROR_REJECTED = "Unable to schedule migration on the executor";

    void migrateData(S s, FileOrchestrator fileOrchestrator, S s2, FileOrchestrator fileOrchestrator2);

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrator$Companion;", "", "()V", "ERROR_REJECTED", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DataMigrator.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String ERROR_REJECTED = "Unable to schedule migration on the executor";

        private Companion() {
        }
    }
}
