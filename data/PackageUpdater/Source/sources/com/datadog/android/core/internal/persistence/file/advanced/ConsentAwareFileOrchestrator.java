package com.datadog.android.core.internal.persistence.file.advanced;

import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.persistence.file.NoOpFileOrchestrator;
import com.datadog.android.core.internal.privacy.ConsentProvider;
import com.datadog.android.privacy.TrackingConsent;
import com.datadog.android.privacy.TrackingConsentProviderCallback;
import java.io.File;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0010\u0018\u0000 #2\u00020\u00012\u00020\u0002:\u0001#B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0001\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0017H\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001f\u001a\u00020\tH\u0002J\u0018\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0012\u0010!\u001a\u00020\u00012\b\u0010\"\u001a\u0004\u0018\u00010\tH\u0002R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0001X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006$"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/ConsentAwareFileOrchestrator;", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "Lcom/datadog/android/privacy/TrackingConsentProviderCallback;", "consentProvider", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "pendingOrchestrator", "grantedOrchestrator", "dataMigrator", "Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrator;", "Lcom/datadog/android/privacy/TrackingConsent;", "(Lcom/datadog/android/core/internal/privacy/ConsentProvider;Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrator;)V", "getDataMigrator$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/advanced/DataMigrator;", "delegateOrchestrator", "getGrantedOrchestrator$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "getPendingOrchestrator$dd_sdk_android_release", "getAllFiles", "", "Ljava/io/File;", "getFlushableFiles", "getReadableFile", "excludeFiles", "", "getRootDir", "getWritableFile", "dataSize", "", "handleConsentChange", "", "previousConsent", "newConsent", "onConsentUpdated", "resolveDelegateOrchestrator", "consent", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ConsentAwareFileOrchestrator.kt */
public class ConsentAwareFileOrchestrator implements FileOrchestrator, TrackingConsentProviderCallback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final FileOrchestrator NO_OP_ORCHESTRATOR = new NoOpFileOrchestrator();
    private final DataMigrator<TrackingConsent> dataMigrator;
    private FileOrchestrator delegateOrchestrator;
    private final FileOrchestrator grantedOrchestrator;
    private final FileOrchestrator pendingOrchestrator;

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ConsentAwareFileOrchestrator.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TrackingConsent.values().length];
            iArr[TrackingConsent.PENDING.ordinal()] = 1;
            iArr[TrackingConsent.GRANTED.ordinal()] = 2;
            iArr[TrackingConsent.NOT_GRANTED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public File getRootDir() {
        return null;
    }

    public ConsentAwareFileOrchestrator(ConsentProvider consentProvider, FileOrchestrator fileOrchestrator, FileOrchestrator fileOrchestrator2, DataMigrator<TrackingConsent> dataMigrator2) {
        Intrinsics.checkNotNullParameter(consentProvider, "consentProvider");
        Intrinsics.checkNotNullParameter(fileOrchestrator, "pendingOrchestrator");
        Intrinsics.checkNotNullParameter(fileOrchestrator2, "grantedOrchestrator");
        Intrinsics.checkNotNullParameter(dataMigrator2, "dataMigrator");
        this.pendingOrchestrator = fileOrchestrator;
        this.grantedOrchestrator = fileOrchestrator2;
        this.dataMigrator = dataMigrator2;
        handleConsentChange((TrackingConsent) null, consentProvider.getConsent());
        consentProvider.registerCallback(this);
    }

    public final FileOrchestrator getPendingOrchestrator$dd_sdk_android_release() {
        return this.pendingOrchestrator;
    }

    public final FileOrchestrator getGrantedOrchestrator$dd_sdk_android_release() {
        return this.grantedOrchestrator;
    }

    public final DataMigrator<TrackingConsent> getDataMigrator$dd_sdk_android_release() {
        return this.dataMigrator;
    }

    public File getWritableFile(int i) {
        FileOrchestrator fileOrchestrator = this.delegateOrchestrator;
        if (fileOrchestrator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delegateOrchestrator");
            fileOrchestrator = null;
        }
        return fileOrchestrator.getWritableFile(i);
    }

    public File getReadableFile(Set<? extends File> set) {
        Intrinsics.checkNotNullParameter(set, "excludeFiles");
        return this.grantedOrchestrator.getReadableFile(set);
    }

    public List<File> getAllFiles() {
        return CollectionsKt.plus(this.pendingOrchestrator.getAllFiles(), this.grantedOrchestrator.getAllFiles());
    }

    public List<File> getFlushableFiles() {
        return this.grantedOrchestrator.getFlushableFiles();
    }

    public void onConsentUpdated(TrackingConsent trackingConsent, TrackingConsent trackingConsent2) {
        Intrinsics.checkNotNullParameter(trackingConsent, "previousConsent");
        Intrinsics.checkNotNullParameter(trackingConsent2, "newConsent");
        handleConsentChange(trackingConsent, trackingConsent2);
    }

    private final void handleConsentChange(TrackingConsent trackingConsent, TrackingConsent trackingConsent2) {
        FileOrchestrator resolveDelegateOrchestrator = resolveDelegateOrchestrator(trackingConsent);
        FileOrchestrator resolveDelegateOrchestrator2 = resolveDelegateOrchestrator(trackingConsent2);
        this.dataMigrator.migrateData(trackingConsent, resolveDelegateOrchestrator, trackingConsent2, resolveDelegateOrchestrator2);
        this.delegateOrchestrator = resolveDelegateOrchestrator2;
    }

    private final FileOrchestrator resolveDelegateOrchestrator(TrackingConsent trackingConsent) {
        int i = trackingConsent == null ? -1 : WhenMappings.$EnumSwitchMapping$0[trackingConsent.ordinal()];
        if (i == -1 || i == 1) {
            return this.pendingOrchestrator;
        }
        if (i == 2) {
            return this.grantedOrchestrator;
        }
        if (i == 3) {
            return NO_OP_ORCHESTRATOR;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/advanced/ConsentAwareFileOrchestrator$Companion;", "", "()V", "NO_OP_ORCHESTRATOR", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "getNO_OP_ORCHESTRATOR$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ConsentAwareFileOrchestrator.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FileOrchestrator getNO_OP_ORCHESTRATOR$dd_sdk_android_release() {
            return ConsentAwareFileOrchestrator.NO_OP_ORCHESTRATOR;
        }
    }
}
