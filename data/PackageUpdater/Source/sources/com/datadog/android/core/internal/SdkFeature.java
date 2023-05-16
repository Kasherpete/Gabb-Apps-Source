package com.datadog.android.core.internal;

import android.content.Context;
import com.datadog.android.core.configuration.Configuration;
import com.datadog.android.core.configuration.Configuration.Feature;
import com.datadog.android.core.internal.data.upload.DataUploadScheduler;
import com.datadog.android.core.internal.data.upload.NoOpUploadScheduler;
import com.datadog.android.core.internal.data.upload.UploadScheduler;
import com.datadog.android.core.internal.net.DataUploader;
import com.datadog.android.core.internal.net.NoOpDataUploader;
import com.datadog.android.core.internal.persistence.NoOpPersistenceStrategy;
import com.datadog.android.core.internal.persistence.PersistenceStrategy;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.persistence.file.FilePersistenceConfig;
import com.datadog.android.core.internal.persistence.file.advanced.CacheFileMigrator;
import com.datadog.android.core.internal.persistence.file.advanced.FeatureFileOrchestrator;
import com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler;
import com.datadog.android.core.internal.persistence.file.batch.BatchFileOrchestrator;
import com.datadog.android.core.internal.privacy.ConsentProvider;
import com.datadog.android.log.Logger;
import com.datadog.android.plugin.DatadogPlugin;
import com.datadog.android.plugin.DatadogPluginConfig;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0005J\u0006\u0010\u001f\u001a\u00020 J#\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00028\u0001H&¢\u0006\u0002\u0010%J\u0015\u0010&\u001a\u00020\u001a2\u0006\u0010$\u001a\u00028\u0001H&¢\u0006\u0002\u0010'J\r\u0010(\u001a\u00020 H\u0000¢\u0006\u0002\b)J\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0+J\u001b\u0010,\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00028\u0001¢\u0006\u0002\u0010-J\u0006\u0010.\u001a\u00020/J \u00100\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0004J\u001d\u00105\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010-J\u0010\u00106\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u00107\u001a\u00020 H\u0016J\b\u00108\u001a\u00020 H\u0016J&\u00109\u001a\u00020 2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\b0+2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0002J\u0015\u0010?\u001a\u00020 2\u0006\u0010$\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010@J\u0006\u0010A\u001a\u00020 J\b\u0010B\u001a\u00020 H\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006C"}, mo20735d2 = {"Lcom/datadog/android/core/internal/SdkFeature;", "T", "", "C", "Lcom/datadog/android/core/configuration/Configuration$Feature;", "()V", "featurePlugins", "", "Lcom/datadog/android/plugin/DatadogPlugin;", "initialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getInitialized$dd_sdk_android_release", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "persistenceStrategy", "Lcom/datadog/android/core/internal/persistence/PersistenceStrategy;", "getPersistenceStrategy$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/PersistenceStrategy;", "setPersistenceStrategy$dd_sdk_android_release", "(Lcom/datadog/android/core/internal/persistence/PersistenceStrategy;)V", "uploadScheduler", "Lcom/datadog/android/core/internal/data/upload/UploadScheduler;", "getUploadScheduler$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/data/upload/UploadScheduler;", "setUploadScheduler$dd_sdk_android_release", "(Lcom/datadog/android/core/internal/data/upload/UploadScheduler;)V", "uploader", "Lcom/datadog/android/core/internal/net/DataUploader;", "getUploader$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/net/DataUploader;", "setUploader$dd_sdk_android_release", "(Lcom/datadog/android/core/internal/net/DataUploader;)V", "clearAllData", "", "createPersistenceStrategy", "context", "Landroid/content/Context;", "configuration", "(Landroid/content/Context;Lcom/datadog/android/core/configuration/Configuration$Feature;)Lcom/datadog/android/core/internal/persistence/PersistenceStrategy;", "createUploader", "(Lcom/datadog/android/core/configuration/Configuration$Feature;)Lcom/datadog/android/core/internal/net/DataUploader;", "flushStoredData", "flushStoredData$dd_sdk_android_release", "getPlugins", "", "initialize", "(Landroid/content/Context;Lcom/datadog/android/core/configuration/Configuration$Feature;)V", "isInitialized", "", "migrateToCacheDir", "featureName", "", "internalLogger", "Lcom/datadog/android/log/Logger;", "onInitialize", "onPostInitialized", "onPostStopped", "onStop", "registerPlugins", "plugins", "config", "Lcom/datadog/android/plugin/DatadogPluginConfig;", "trackingConsentProvider", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "setupUploader", "(Lcom/datadog/android/core/configuration/Configuration$Feature;)V", "stop", "unregisterPlugins", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SdkFeature.kt */
public abstract class SdkFeature<T, C extends Configuration.Feature> {
    private final List<DatadogPlugin> featurePlugins = new ArrayList();
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private PersistenceStrategy<T> persistenceStrategy = new NoOpPersistenceStrategy();
    private UploadScheduler uploadScheduler = new NoOpUploadScheduler();
    private DataUploader uploader = new NoOpDataUploader();

    public abstract PersistenceStrategy<T> createPersistenceStrategy(Context context, C c);

    public abstract DataUploader createUploader(C c);

    public void onInitialize(Context context, C c) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(c, "configuration");
    }

    public void onPostInitialized(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
    }

    public void onPostStopped() {
    }

    public void onStop() {
    }

    public final AtomicBoolean getInitialized$dd_sdk_android_release() {
        return this.initialized;
    }

    public final PersistenceStrategy<T> getPersistenceStrategy$dd_sdk_android_release() {
        return this.persistenceStrategy;
    }

    public final void setPersistenceStrategy$dd_sdk_android_release(PersistenceStrategy<T> persistenceStrategy2) {
        Intrinsics.checkNotNullParameter(persistenceStrategy2, "<set-?>");
        this.persistenceStrategy = persistenceStrategy2;
    }

    public final DataUploader getUploader$dd_sdk_android_release() {
        return this.uploader;
    }

    public final void setUploader$dd_sdk_android_release(DataUploader dataUploader) {
        Intrinsics.checkNotNullParameter(dataUploader, "<set-?>");
        this.uploader = dataUploader;
    }

    public final UploadScheduler getUploadScheduler$dd_sdk_android_release() {
        return this.uploadScheduler;
    }

    public final void setUploadScheduler$dd_sdk_android_release(UploadScheduler uploadScheduler2) {
        Intrinsics.checkNotNullParameter(uploadScheduler2, "<set-?>");
        this.uploadScheduler = uploadScheduler2;
    }

    public final void initialize(Context context, C c) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(c, "configuration");
        if (!this.initialized.get()) {
            this.persistenceStrategy = createPersistenceStrategy(context, c);
            setupUploader(c);
            registerPlugins(c.getPlugins(), new DatadogPluginConfig(context, CoreFeature.INSTANCE.getEnvName$dd_sdk_android_release(), CoreFeature.INSTANCE.getServiceName$dd_sdk_android_release(), CoreFeature.INSTANCE.getTrackingConsentProvider$dd_sdk_android_release().getConsent()), CoreFeature.INSTANCE.getTrackingConsentProvider$dd_sdk_android_release());
            onInitialize(context, c);
            this.initialized.set(true);
            onPostInitialized(context);
        }
    }

    public final boolean isInitialized() {
        return this.initialized.get();
    }

    public final void clearAllData() {
        this.persistenceStrategy.getReader().dropAll();
    }

    public final void stop() {
        if (this.initialized.get()) {
            unregisterPlugins();
            this.uploadScheduler.stopScheduling();
            this.persistenceStrategy = new NoOpPersistenceStrategy();
            this.uploadScheduler = new NoOpUploadScheduler();
            onStop();
            this.initialized.set(false);
            onPostStopped();
        }
    }

    public final List<DatadogPlugin> getPlugins() {
        return this.featurePlugins;
    }

    private final void registerPlugins(List<? extends DatadogPlugin> list, DatadogPluginConfig datadogPluginConfig, ConsentProvider consentProvider) {
        for (DatadogPlugin datadogPlugin : list) {
            this.featurePlugins.add(datadogPlugin);
            datadogPlugin.register(datadogPluginConfig);
            consentProvider.registerCallback(datadogPlugin);
        }
    }

    private final void unregisterPlugins() {
        for (DatadogPlugin unregister : this.featurePlugins) {
            unregister.unregister();
        }
        this.featurePlugins.clear();
    }

    private final void setupUploader(C c) {
        UploadScheduler uploadScheduler2;
        if (CoreFeature.INSTANCE.isMainProcess$dd_sdk_android_release()) {
            this.uploader = createUploader(c);
            uploadScheduler2 = new DataUploadScheduler(this.persistenceStrategy.getReader(), this.uploader, CoreFeature.INSTANCE.getNetworkInfoProvider$dd_sdk_android_release(), CoreFeature.INSTANCE.getSystemInfoProvider$dd_sdk_android_release(), CoreFeature.INSTANCE.getUploadFrequency$dd_sdk_android_release(), CoreFeature.INSTANCE.getUploadExecutorService$dd_sdk_android_release());
        } else {
            uploadScheduler2 = new NoOpUploadScheduler();
        }
        this.uploadScheduler = uploadScheduler2;
        uploadScheduler2.startScheduling();
    }

    /* access modifiers changed from: protected */
    public final void migrateToCacheDir(Context context, String str, Logger logger) {
        String str2 = str;
        Logger logger2 = logger;
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(str2, "featureName");
        Intrinsics.checkNotNullParameter(logger2, "internalLogger");
        BatchFileHandler batchFileHandler = new BatchFileHandler(logger, (Function1) null, (Function1) null, 6, (DefaultConstructorMarker) null);
        FilePersistenceConfig filePersistenceConfig = new FilePersistenceConfig(0, 0, 0, 0, 0, 0, 63, (DefaultConstructorMarker) null);
        CacheFileMigrator cacheFileMigrator = new CacheFileMigrator(batchFileHandler, CoreFeature.INSTANCE.getPersistenceExecutorService$dd_sdk_android_release(), logger2);
        File filesDir = context.getFilesDir();
        String format = String.format(Locale.US, FeatureFileOrchestrator.GRANTED_DIR, Arrays.copyOf(new Object[]{str2}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
        BatchFileOrchestrator batchFileOrchestrator = new BatchFileOrchestrator(new File(filesDir, format), filePersistenceConfig, logger2);
        File cacheDir = context.getCacheDir();
        String format2 = String.format(Locale.US, FeatureFileOrchestrator.GRANTED_DIR, Arrays.copyOf(new Object[]{str2}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
        cacheFileMigrator.migrateData((Boolean) null, (FileOrchestrator) batchFileOrchestrator, true, (FileOrchestrator) new BatchFileOrchestrator(new File(cacheDir, format2), filePersistenceConfig, logger2));
    }

    public final void flushStoredData$dd_sdk_android_release() {
        this.persistenceStrategy.getFlusher().flush(this.uploader);
    }
}
