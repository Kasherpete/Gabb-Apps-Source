package com.datadog.android.error.internal;

import android.content.Context;
import com.datadog.android.core.configuration.Configuration;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.SdkFeature;
import com.datadog.android.core.internal.net.DataUploader;
import com.datadog.android.core.internal.persistence.PersistenceStrategy;
import com.datadog.android.core.internal.system.StaticAndroidInfoProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.domain.LogGenerator;
import com.datadog.android.log.internal.net.LogsOkHttpUploaderV2;
import com.datadog.android.log.model.LogEvent;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import java.lang.Thread;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0011H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u001c"}, mo20735d2 = {"Lcom/datadog/android/error/internal/CrashReportsFeature;", "Lcom/datadog/android/core/internal/SdkFeature;", "Lcom/datadog/android/log/model/LogEvent;", "Lcom/datadog/android/core/configuration/Configuration$Feature$CrashReport;", "()V", "CRASH_FEATURE_NAME", "", "originalUncaughtExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;", "kotlin.jvm.PlatformType", "getOriginalUncaughtExceptionHandler$dd_sdk_android_release", "()Ljava/lang/Thread$UncaughtExceptionHandler;", "setOriginalUncaughtExceptionHandler$dd_sdk_android_release", "(Ljava/lang/Thread$UncaughtExceptionHandler;)V", "createPersistenceStrategy", "Lcom/datadog/android/core/internal/persistence/PersistenceStrategy;", "context", "Landroid/content/Context;", "configuration", "createUploader", "Lcom/datadog/android/core/internal/net/DataUploader;", "onInitialize", "", "onPostInitialized", "onStop", "resetOriginalExceptionHandler", "setupExceptionHandler", "appContext", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CrashReportsFeature.kt */
public final class CrashReportsFeature extends SdkFeature<LogEvent, Configuration.Feature.CrashReport> {
    public static final String CRASH_FEATURE_NAME = "crash";
    public static final CrashReportsFeature INSTANCE = new CrashReportsFeature();
    private static Thread.UncaughtExceptionHandler originalUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    private CrashReportsFeature() {
    }

    public final Thread.UncaughtExceptionHandler getOriginalUncaughtExceptionHandler$dd_sdk_android_release() {
        return originalUncaughtExceptionHandler;
    }

    public final void setOriginalUncaughtExceptionHandler$dd_sdk_android_release(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        originalUncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public void onInitialize(Context context, Configuration.Feature.CrashReport crashReport) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(crashReport, "configuration");
        setupExceptionHandler(context);
    }

    public void onStop() {
        resetOriginalExceptionHandler();
    }

    public PersistenceStrategy<LogEvent> createPersistenceStrategy(Context context, Configuration.Feature.CrashReport crashReport) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(crashReport, "configuration");
        return new CrashReportFilePersistenceStrategy(CoreFeature.INSTANCE.getTrackingConsentProvider$dd_sdk_android_release(), context, CoreFeature.INSTANCE.getPersistenceExecutorService$dd_sdk_android_release(), RuntimeUtilsKt.getSdkLogger(), CoreFeature.INSTANCE.getLocalDataEncryption$dd_sdk_android_release());
    }

    public DataUploader createUploader(Configuration.Feature.CrashReport crashReport) {
        Intrinsics.checkNotNullParameter(crashReport, "configuration");
        return new LogsOkHttpUploaderV2(crashReport.getEndpointUrl(), CoreFeature.INSTANCE.getClientToken$dd_sdk_android_release(), CoreFeature.INSTANCE.getSourceName$dd_sdk_android_release(), CoreFeature.INSTANCE.getSdkVersion$dd_sdk_android_release(), CoreFeature.INSTANCE.getOkHttpClient$dd_sdk_android_release(), StaticAndroidInfoProvider.INSTANCE, RuntimeUtilsKt.getSdkLogger());
    }

    public void onPostInitialized(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        migrateToCacheDir(context, "crash", RuntimeUtilsKt.getSdkLogger());
    }

    private final void setupExceptionHandler(Context context) {
        originalUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        new DatadogExceptionHandler(new LogGenerator(CoreFeature.INSTANCE.getServiceName$dd_sdk_android_release(), "crash", CoreFeature.INSTANCE.getNetworkInfoProvider$dd_sdk_android_release(), CoreFeature.INSTANCE.getUserInfoProvider$dd_sdk_android_release(), CoreFeature.INSTANCE.getTimeProvider$dd_sdk_android_release(), CoreFeature.INSTANCE.getSdkVersion$dd_sdk_android_release(), CoreFeature.INSTANCE.getEnvName$dd_sdk_android_release(), CoreFeature.INSTANCE.getPackageVersion$dd_sdk_android_release()), getPersistenceStrategy$dd_sdk_android_release().getWriter(), context).register();
    }

    private final void resetOriginalExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(originalUncaughtExceptionHandler);
    }
}
