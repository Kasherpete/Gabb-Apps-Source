package com.datadog.android.core.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.datadog.android.DatadogEndpoint;
import com.datadog.android.core.configuration.BatchSize;
import com.datadog.android.core.configuration.Configuration;
import com.datadog.android.core.configuration.Credentials;
import com.datadog.android.core.configuration.UploadFrequency;
import com.datadog.android.core.internal.net.FirstPartyHostDetector;
import com.datadog.android.core.internal.net.GzipRequestInterceptor;
import com.datadog.android.core.internal.net.info.BroadcastReceiverNetworkInfoProvider;
import com.datadog.android.core.internal.net.info.CallbackNetworkInfoProvider;
import com.datadog.android.core.internal.net.info.NetworkInfoDeserializer;
import com.datadog.android.core.internal.net.info.NetworkInfoProvider;
import com.datadog.android.core.internal.net.info.NoOpNetworkInfoProvider;
import com.datadog.android.core.internal.persistence.file.FilePersistenceConfig;
import com.datadog.android.core.internal.persistence.file.advanced.ScheduledWriter;
import com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler;
import com.datadog.android.core.internal.privacy.ConsentProvider;
import com.datadog.android.core.internal.privacy.NoOpConsentProvider;
import com.datadog.android.core.internal.privacy.TrackingConsentProvider;
import com.datadog.android.core.internal.system.BroadcastReceiverSystemInfoProvider;
import com.datadog.android.core.internal.system.BuildSdkVersionProvider;
import com.datadog.android.core.internal.system.NoOpSystemInfoProvider;
import com.datadog.android.core.internal.system.SystemInfoProvider;
import com.datadog.android.core.internal.time.KronosTimeProvider;
import com.datadog.android.core.internal.time.LoggingSyncListener;
import com.datadog.android.core.internal.time.NoOpTimeProvider;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.domain.LogGenerator;
import com.datadog.android.log.internal.user.DatadogUserInfoProvider;
import com.datadog.android.log.internal.user.MutableUserInfoProvider;
import com.datadog.android.log.internal.user.NoOpMutableUserInfoProvider;
import com.datadog.android.log.internal.user.UserInfoDeserializer;
import com.datadog.android.privacy.TrackingConsent;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.rum.internal.domain.event.RumEventSourceProvider;
import com.datadog.android.rum.internal.ndk.DatadogNdkCrashHandler;
import com.datadog.android.rum.internal.ndk.NdkCrashHandler;
import com.datadog.android.rum.internal.ndk.NdkCrashLogDeserializer;
import com.datadog.android.rum.internal.ndk.NdkNetworkInfoDataWriter;
import com.datadog.android.rum.internal.ndk.NdkUserInfoDataWriter;
import com.datadog.android.rum.internal.ndk.NoOpNdkCrashHandler;
import com.datadog.android.security.Encryption;
import com.lyft.kronos.AndroidClockFactory;
import com.lyft.kronos.KronosClock;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.TlsVersion;

@Metadata(mo20734d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010¥\u0001\u001a\u00030¦\u0001J\n\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\n\u0010©\u0001\u001a\u00030¨\u0001H\u0002J\b\u0010ª\u0001\u001a\u00030¨\u0001J/\u0010«\u0001\u001a\u00030¨\u00012\u0007\u0010¬\u0001\u001a\u00020\"2\b\u0010­\u0001\u001a\u00030®\u00012\b\u0010¯\u0001\u001a\u00030°\u00012\b\u0010±\u0001\u001a\u00030²\u0001J\u0013\u0010³\u0001\u001a\u00030¨\u00012\u0007\u0010¬\u0001\u001a\u00020\"H\u0002J\u0013\u0010´\u0001\u001a\u00030¨\u00012\u0007\u0010¬\u0001\u001a\u00020\"H\u0002J\u001d\u0010µ\u0001\u001a\u00030¨\u00012\u0007\u0010¬\u0001\u001a\u00020\"2\b\u0010­\u0001\u001a\u00030®\u0001H\u0002J\u0014\u0010¶\u0001\u001a\u00030¨\u00012\b\u0010¯\u0001\u001a\u00030°\u0001H\u0002J\u0013\u0010·\u0001\u001a\u00030¨\u00012\u0007\u0010¬\u0001\u001a\u00020\"H\u0002J\n\u0010¸\u0001\u001a\u00030¨\u0001H\u0002J\u001d\u0010¹\u0001\u001a\u00030¨\u00012\u0007\u0010¬\u0001\u001a\u00020\"2\b\u0010±\u0001\u001a\u00030²\u0001H\u0002J\u0013\u0010º\u0001\u001a\u00030¨\u00012\u0007\u0010¬\u0001\u001a\u00020\"H\u0002J\u0014\u0010»\u0001\u001a\u00030¨\u00012\b\u0010¯\u0001\u001a\u00030°\u0001H\u0002J\u0013\u0010¼\u0001\u001a\u00030¨\u00012\u0007\u0010¬\u0001\u001a\u00020\"H\u0002J\n\u0010½\u0001\u001a\u00030¨\u0001H\u0002J\b\u0010¾\u0001\u001a\u00030¨\u0001R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\u000eX\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001d\"\u0004\b/\u0010\u001fR\u001a\u00100\u001a\u000201X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0014\u00106\u001a\u000207X\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u001a\u0010:\u001a\u00020(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010*\"\u0004\b<\u0010,R\u001a\u0010=\u001a\u00020>X.¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001c\u0010C\u001a\u0004\u0018\u00010DX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001a\u0010I\u001a\u00020JX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001a\u0010O\u001a\u00020PX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001a\u0010U\u001a\u00020VX.¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001a\u0010[\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u001d\"\u0004\b]\u0010\u001fR\u001a\u0010^\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u001d\"\u0004\b`\u0010\u001fR\u001a\u0010a\u001a\u00020bX.¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u001a\u0010g\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010i\"\u0004\bj\u0010kR\u001c\u0010l\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u001d\"\u0004\bn\u0010\u001fR\u001a\u0010o\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010\u001d\"\u0004\bq\u0010\u001fR\u001a\u0010r\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010\u001d\"\u0004\bt\u0010\u001fR\u001a\u0010u\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010\u001d\"\u0004\bw\u0010\u001fR\u001a\u0010x\u001a\u00020yX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R\u001e\u0010~\u001a\u00020X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R \u0010\u0001\u001a\u00030\u0001X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R \u0010\u0001\u001a\u00030\u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R \u0010\u0001\u001a\u00030\u0001X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R \u0010\u0001\u001a\u00030\u0001X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R\u001d\u0010\u0001\u001a\u00020\u0006X\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010\u001d\"\u0005\b\u0001\u0010\u001fR&\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020\u00060 \u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\b¡\u0001\u0010¢\u0001\"\u0006\b£\u0001\u0010¤\u0001¨\u0006¿\u0001"}, mo20735d2 = {"Lcom/datadog/android/core/internal/CoreFeature;", "", "()V", "CORE_DEFAULT_POOL_SIZE", "", "DEFAULT_APP_VERSION", "", "DEFAULT_SDK_VERSION", "DEFAULT_SOURCE_NAME", "NETWORK_TIMEOUT_MS", "", "getNETWORK_TIMEOUT_MS$dd_sdk_android_release", "()J", "RESTRICTED_CIPHER_SUITES", "", "Lokhttp3/CipherSuite;", "kotlin.jvm.PlatformType", "getRESTRICTED_CIPHER_SUITES$dd_sdk_android_release", "()[Lokhttp3/CipherSuite;", "[Lokhttp3/CipherSuite;", "THREAD_POOL_MAX_KEEP_ALIVE_MS", "batchSize", "Lcom/datadog/android/core/configuration/BatchSize;", "getBatchSize$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/BatchSize;", "setBatchSize$dd_sdk_android_release", "(Lcom/datadog/android/core/configuration/BatchSize;)V", "clientToken", "getClientToken$dd_sdk_android_release", "()Ljava/lang/String;", "setClientToken$dd_sdk_android_release", "(Ljava/lang/String;)V", "contextRef", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "getContextRef$dd_sdk_android_release", "()Ljava/lang/ref/WeakReference;", "setContextRef$dd_sdk_android_release", "(Ljava/lang/ref/WeakReference;)V", "disableKronosBackgroundSync", "", "getDisableKronosBackgroundSync$dd_sdk_android_release", "()Z", "setDisableKronosBackgroundSync$dd_sdk_android_release", "(Z)V", "envName", "getEnvName$dd_sdk_android_release", "setEnvName$dd_sdk_android_release", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "getFirstPartyHostDetector$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "setFirstPartyHostDetector$dd_sdk_android_release", "(Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;)V", "initialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getInitialized$dd_sdk_android_release", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "isMainProcess", "isMainProcess$dd_sdk_android_release", "setMainProcess$dd_sdk_android_release", "kronosClock", "Lcom/lyft/kronos/KronosClock;", "getKronosClock$dd_sdk_android_release", "()Lcom/lyft/kronos/KronosClock;", "setKronosClock$dd_sdk_android_release", "(Lcom/lyft/kronos/KronosClock;)V", "localDataEncryption", "Lcom/datadog/android/security/Encryption;", "getLocalDataEncryption$dd_sdk_android_release", "()Lcom/datadog/android/security/Encryption;", "setLocalDataEncryption$dd_sdk_android_release", "(Lcom/datadog/android/security/Encryption;)V", "ndkCrashHandler", "Lcom/datadog/android/rum/internal/ndk/NdkCrashHandler;", "getNdkCrashHandler$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/ndk/NdkCrashHandler;", "setNdkCrashHandler$dd_sdk_android_release", "(Lcom/datadog/android/rum/internal/ndk/NdkCrashHandler;)V", "networkInfoProvider", "Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;", "getNetworkInfoProvider$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;", "setNetworkInfoProvider$dd_sdk_android_release", "(Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;)V", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient$dd_sdk_android_release", "()Lokhttp3/OkHttpClient;", "setOkHttpClient$dd_sdk_android_release", "(Lokhttp3/OkHttpClient;)V", "packageName", "getPackageName$dd_sdk_android_release", "setPackageName$dd_sdk_android_release", "packageVersion", "getPackageVersion$dd_sdk_android_release", "setPackageVersion$dd_sdk_android_release", "persistenceExecutorService", "Ljava/util/concurrent/ExecutorService;", "getPersistenceExecutorService$dd_sdk_android_release", "()Ljava/util/concurrent/ExecutorService;", "setPersistenceExecutorService$dd_sdk_android_release", "(Ljava/util/concurrent/ExecutorService;)V", "processImportance", "getProcessImportance$dd_sdk_android_release", "()I", "setProcessImportance$dd_sdk_android_release", "(I)V", "rumApplicationId", "getRumApplicationId$dd_sdk_android_release", "setRumApplicationId$dd_sdk_android_release", "sdkVersion", "getSdkVersion$dd_sdk_android_release", "setSdkVersion$dd_sdk_android_release", "serviceName", "getServiceName$dd_sdk_android_release", "setServiceName$dd_sdk_android_release", "sourceName", "getSourceName$dd_sdk_android_release", "setSourceName$dd_sdk_android_release", "systemInfoProvider", "Lcom/datadog/android/core/internal/system/SystemInfoProvider;", "getSystemInfoProvider$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/system/SystemInfoProvider;", "setSystemInfoProvider$dd_sdk_android_release", "(Lcom/datadog/android/core/internal/system/SystemInfoProvider;)V", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "getTimeProvider$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/time/TimeProvider;", "setTimeProvider$dd_sdk_android_release", "(Lcom/datadog/android/core/internal/time/TimeProvider;)V", "trackingConsentProvider", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "getTrackingConsentProvider$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "setTrackingConsentProvider$dd_sdk_android_release", "(Lcom/datadog/android/core/internal/privacy/ConsentProvider;)V", "uploadExecutorService", "Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "getUploadExecutorService$dd_sdk_android_release", "()Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "setUploadExecutorService$dd_sdk_android_release", "(Ljava/util/concurrent/ScheduledThreadPoolExecutor;)V", "uploadFrequency", "Lcom/datadog/android/core/configuration/UploadFrequency;", "getUploadFrequency$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/UploadFrequency;", "setUploadFrequency$dd_sdk_android_release", "(Lcom/datadog/android/core/configuration/UploadFrequency;)V", "userInfoProvider", "Lcom/datadog/android/log/internal/user/MutableUserInfoProvider;", "getUserInfoProvider$dd_sdk_android_release", "()Lcom/datadog/android/log/internal/user/MutableUserInfoProvider;", "setUserInfoProvider$dd_sdk_android_release", "(Lcom/datadog/android/log/internal/user/MutableUserInfoProvider;)V", "variant", "getVariant$dd_sdk_android_release", "setVariant$dd_sdk_android_release", "webViewTrackingHosts", "", "getWebViewTrackingHosts$dd_sdk_android_release", "()Ljava/util/List;", "setWebViewTrackingHosts$dd_sdk_android_release", "(Ljava/util/List;)V", "buildFilePersistenceConfig", "Lcom/datadog/android/core/internal/persistence/file/FilePersistenceConfig;", "cleanupApplicationInfo", "", "cleanupProviders", "drainAndShutdownExecutors", "initialize", "appContext", "credentials", "Lcom/datadog/android/core/configuration/Credentials;", "configuration", "Lcom/datadog/android/core/configuration/Configuration$Core;", "consent", "Lcom/datadog/android/privacy/TrackingConsent;", "initializeClockSync", "prepareNdkCrashData", "readApplicationInformation", "readConfigurationSettings", "resolveProcessInfo", "setupExecutors", "setupInfoProviders", "setupNetworkInfoProviders", "setupOkHttpClient", "setupUserInfoProvider", "shutDownExecutors", "stop", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CoreFeature.kt */
public final class CoreFeature {
    private static final int CORE_DEFAULT_POOL_SIZE = 1;
    public static final String DEFAULT_APP_VERSION = "?";
    public static final String DEFAULT_SDK_VERSION = "1.13.0-rc1";
    public static final String DEFAULT_SOURCE_NAME = "android";
    public static final CoreFeature INSTANCE = new CoreFeature();
    private static final long NETWORK_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(45);
    private static final CipherSuite[] RESTRICTED_CIPHER_SUITES = {CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256};
    private static final long THREAD_POOL_MAX_KEEP_ALIVE_MS = TimeUnit.SECONDS.toMillis(5);
    private static BatchSize batchSize = BatchSize.MEDIUM;
    private static String clientToken = "";
    private static WeakReference<Context> contextRef = new WeakReference<>((Object) null);
    private static boolean disableKronosBackgroundSync;
    private static String envName = "";
    private static FirstPartyHostDetector firstPartyHostDetector = new FirstPartyHostDetector(CollectionsKt.emptyList());
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static boolean isMainProcess = true;
    public static KronosClock kronosClock;
    private static Encryption localDataEncryption;
    private static NdkCrashHandler ndkCrashHandler = new NoOpNdkCrashHandler();
    private static NetworkInfoProvider networkInfoProvider = new NoOpNetworkInfoProvider();
    public static OkHttpClient okHttpClient;
    private static String packageName = "";
    private static String packageVersion = "";
    public static ExecutorService persistenceExecutorService;
    private static int processImportance = 100;
    private static String rumApplicationId;
    private static String sdkVersion = "1.13.0-rc1";
    private static String serviceName = "";
    private static String sourceName = DEFAULT_SOURCE_NAME;
    private static SystemInfoProvider systemInfoProvider = new NoOpSystemInfoProvider();
    private static TimeProvider timeProvider = new NoOpTimeProvider();
    private static ConsentProvider trackingConsentProvider = new NoOpConsentProvider();
    public static ScheduledThreadPoolExecutor uploadExecutorService;
    private static UploadFrequency uploadFrequency = UploadFrequency.AVERAGE;
    private static MutableUserInfoProvider userInfoProvider = new NoOpMutableUserInfoProvider();
    private static String variant = "";
    public static List<String> webViewTrackingHosts;

    private CoreFeature() {
    }

    public final long getNETWORK_TIMEOUT_MS$dd_sdk_android_release() {
        return NETWORK_TIMEOUT_MS;
    }

    public final CipherSuite[] getRESTRICTED_CIPHER_SUITES$dd_sdk_android_release() {
        return RESTRICTED_CIPHER_SUITES;
    }

    public final AtomicBoolean getInitialized$dd_sdk_android_release() {
        return initialized;
    }

    public final WeakReference<Context> getContextRef$dd_sdk_android_release() {
        return contextRef;
    }

    public final void setContextRef$dd_sdk_android_release(WeakReference<Context> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
        contextRef = weakReference;
    }

    public final FirstPartyHostDetector getFirstPartyHostDetector$dd_sdk_android_release() {
        return firstPartyHostDetector;
    }

    public final void setFirstPartyHostDetector$dd_sdk_android_release(FirstPartyHostDetector firstPartyHostDetector2) {
        Intrinsics.checkNotNullParameter(firstPartyHostDetector2, "<set-?>");
        firstPartyHostDetector = firstPartyHostDetector2;
    }

    public final NetworkInfoProvider getNetworkInfoProvider$dd_sdk_android_release() {
        return networkInfoProvider;
    }

    public final void setNetworkInfoProvider$dd_sdk_android_release(NetworkInfoProvider networkInfoProvider2) {
        Intrinsics.checkNotNullParameter(networkInfoProvider2, "<set-?>");
        networkInfoProvider = networkInfoProvider2;
    }

    public final SystemInfoProvider getSystemInfoProvider$dd_sdk_android_release() {
        return systemInfoProvider;
    }

    public final void setSystemInfoProvider$dd_sdk_android_release(SystemInfoProvider systemInfoProvider2) {
        Intrinsics.checkNotNullParameter(systemInfoProvider2, "<set-?>");
        systemInfoProvider = systemInfoProvider2;
    }

    public final TimeProvider getTimeProvider$dd_sdk_android_release() {
        return timeProvider;
    }

    public final void setTimeProvider$dd_sdk_android_release(TimeProvider timeProvider2) {
        Intrinsics.checkNotNullParameter(timeProvider2, "<set-?>");
        timeProvider = timeProvider2;
    }

    public final ConsentProvider getTrackingConsentProvider$dd_sdk_android_release() {
        return trackingConsentProvider;
    }

    public final void setTrackingConsentProvider$dd_sdk_android_release(ConsentProvider consentProvider) {
        Intrinsics.checkNotNullParameter(consentProvider, "<set-?>");
        trackingConsentProvider = consentProvider;
    }

    public final MutableUserInfoProvider getUserInfoProvider$dd_sdk_android_release() {
        return userInfoProvider;
    }

    public final void setUserInfoProvider$dd_sdk_android_release(MutableUserInfoProvider mutableUserInfoProvider) {
        Intrinsics.checkNotNullParameter(mutableUserInfoProvider, "<set-?>");
        userInfoProvider = mutableUserInfoProvider;
    }

    public final OkHttpClient getOkHttpClient$dd_sdk_android_release() {
        OkHttpClient okHttpClient2 = okHttpClient;
        if (okHttpClient2 != null) {
            return okHttpClient2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("okHttpClient");
        return null;
    }

    public final void setOkHttpClient$dd_sdk_android_release(OkHttpClient okHttpClient2) {
        Intrinsics.checkNotNullParameter(okHttpClient2, "<set-?>");
        okHttpClient = okHttpClient2;
    }

    public final KronosClock getKronosClock$dd_sdk_android_release() {
        KronosClock kronosClock2 = kronosClock;
        if (kronosClock2 != null) {
            return kronosClock2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("kronosClock");
        return null;
    }

    public final void setKronosClock$dd_sdk_android_release(KronosClock kronosClock2) {
        Intrinsics.checkNotNullParameter(kronosClock2, "<set-?>");
        kronosClock = kronosClock2;
    }

    public final String getClientToken$dd_sdk_android_release() {
        return clientToken;
    }

    public final void setClientToken$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        clientToken = str;
    }

    public final String getPackageName$dd_sdk_android_release() {
        return packageName;
    }

    public final void setPackageName$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        packageName = str;
    }

    public final String getPackageVersion$dd_sdk_android_release() {
        return packageVersion;
    }

    public final void setPackageVersion$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        packageVersion = str;
    }

    public final String getServiceName$dd_sdk_android_release() {
        return serviceName;
    }

    public final void setServiceName$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        serviceName = str;
    }

    public final String getSourceName$dd_sdk_android_release() {
        return sourceName;
    }

    public final void setSourceName$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        sourceName = str;
    }

    public final String getSdkVersion$dd_sdk_android_release() {
        return sdkVersion;
    }

    public final void setSdkVersion$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        sdkVersion = str;
    }

    public final String getRumApplicationId$dd_sdk_android_release() {
        return rumApplicationId;
    }

    public final void setRumApplicationId$dd_sdk_android_release(String str) {
        rumApplicationId = str;
    }

    public final boolean isMainProcess$dd_sdk_android_release() {
        return isMainProcess;
    }

    public final void setMainProcess$dd_sdk_android_release(boolean z) {
        isMainProcess = z;
    }

    public final int getProcessImportance$dd_sdk_android_release() {
        return processImportance;
    }

    public final void setProcessImportance$dd_sdk_android_release(int i) {
        processImportance = i;
    }

    public final String getEnvName$dd_sdk_android_release() {
        return envName;
    }

    public final void setEnvName$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        envName = str;
    }

    public final String getVariant$dd_sdk_android_release() {
        return variant;
    }

    public final void setVariant$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        variant = str;
    }

    public final BatchSize getBatchSize$dd_sdk_android_release() {
        return batchSize;
    }

    public final void setBatchSize$dd_sdk_android_release(BatchSize batchSize2) {
        Intrinsics.checkNotNullParameter(batchSize2, "<set-?>");
        batchSize = batchSize2;
    }

    public final UploadFrequency getUploadFrequency$dd_sdk_android_release() {
        return uploadFrequency;
    }

    public final void setUploadFrequency$dd_sdk_android_release(UploadFrequency uploadFrequency2) {
        Intrinsics.checkNotNullParameter(uploadFrequency2, "<set-?>");
        uploadFrequency = uploadFrequency2;
    }

    public final NdkCrashHandler getNdkCrashHandler$dd_sdk_android_release() {
        return ndkCrashHandler;
    }

    public final void setNdkCrashHandler$dd_sdk_android_release(NdkCrashHandler ndkCrashHandler2) {
        Intrinsics.checkNotNullParameter(ndkCrashHandler2, "<set-?>");
        ndkCrashHandler = ndkCrashHandler2;
    }

    public final ScheduledThreadPoolExecutor getUploadExecutorService$dd_sdk_android_release() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = uploadExecutorService;
        if (scheduledThreadPoolExecutor != null) {
            return scheduledThreadPoolExecutor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("uploadExecutorService");
        return null;
    }

    public final void setUploadExecutorService$dd_sdk_android_release(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        Intrinsics.checkNotNullParameter(scheduledThreadPoolExecutor, "<set-?>");
        uploadExecutorService = scheduledThreadPoolExecutor;
    }

    public final ExecutorService getPersistenceExecutorService$dd_sdk_android_release() {
        ExecutorService executorService = persistenceExecutorService;
        if (executorService != null) {
            return executorService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("persistenceExecutorService");
        return null;
    }

    public final void setPersistenceExecutorService$dd_sdk_android_release(ExecutorService executorService) {
        Intrinsics.checkNotNullParameter(executorService, "<set-?>");
        persistenceExecutorService = executorService;
    }

    public final Encryption getLocalDataEncryption$dd_sdk_android_release() {
        return localDataEncryption;
    }

    public final void setLocalDataEncryption$dd_sdk_android_release(Encryption encryption) {
        localDataEncryption = encryption;
    }

    public final List<String> getWebViewTrackingHosts$dd_sdk_android_release() {
        List<String> list = webViewTrackingHosts;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("webViewTrackingHosts");
        return null;
    }

    public final void setWebViewTrackingHosts$dd_sdk_android_release(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        webViewTrackingHosts = list;
    }

    public final boolean getDisableKronosBackgroundSync$dd_sdk_android_release() {
        return disableKronosBackgroundSync;
    }

    public final void setDisableKronosBackgroundSync$dd_sdk_android_release(boolean z) {
        disableKronosBackgroundSync = z;
    }

    public final void initialize(Context context, Credentials credentials, Configuration.Core core, TrackingConsent trackingConsent) {
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(credentials, "credentials");
        Intrinsics.checkNotNullParameter(core, "configuration");
        Intrinsics.checkNotNullParameter(trackingConsent, "consent");
        AtomicBoolean atomicBoolean = initialized;
        if (!atomicBoolean.get()) {
            readConfigurationSettings(core);
            readApplicationInformation(context, credentials);
            resolveProcessInfo(context);
            initializeClockSync(context);
            setupOkHttpClient(core);
            firstPartyHostDetector.addKnownHosts(core.getFirstPartyHosts());
            setWebViewTrackingHosts$dd_sdk_android_release(core.getWebViewTrackingHosts());
            setupExecutors();
            timeProvider = new KronosTimeProvider(getKronosClock$dd_sdk_android_release());
            prepareNdkCrashData(context);
            setupInfoProviders(context, trackingConsent);
            atomicBoolean.set(true);
        }
    }

    public final void stop() {
        if (initialized.get()) {
            Context context = (Context) contextRef.get();
            if (context != null) {
                CoreFeature coreFeature = INSTANCE;
                coreFeature.getNetworkInfoProvider$dd_sdk_android_release().unregister(context);
                coreFeature.getSystemInfoProvider$dd_sdk_android_release().unregister(context);
            }
            contextRef.clear();
            trackingConsentProvider.unregisterAllCallbacks();
            try {
                getKronosClock$dd_sdk_android_release().shutdown();
            } catch (IllegalStateException e) {
                Logger.e$default(RuntimeUtilsKt.getSdkLogger(), "Trying to shut down Kronos when it is already not running", e, (Map) null, 4, (Object) null);
            }
            cleanupApplicationInfo();
            cleanupProviders();
            shutDownExecutors();
            initialized.set(false);
            ndkCrashHandler = new NoOpNdkCrashHandler();
            trackingConsentProvider = new NoOpConsentProvider();
        }
    }

    public final FilePersistenceConfig buildFilePersistenceConfig() {
        return new FilePersistenceConfig(batchSize.getWindowDurationMs$dd_sdk_android_release(), 0, 0, 0, 0, 0, 62, (DefaultConstructorMarker) null);
    }

    public final void drainAndShutdownExecutors() throws UnsupportedOperationException, InterruptedException {
        BlockingQueue<Runnable> queue;
        ArrayList<Runnable> arrayList = new ArrayList<>();
        ExecutorService persistenceExecutorService$dd_sdk_android_release = getPersistenceExecutorService$dd_sdk_android_release();
        ThreadPoolExecutor threadPoolExecutor = persistenceExecutorService$dd_sdk_android_release instanceof ThreadPoolExecutor ? (ThreadPoolExecutor) persistenceExecutorService$dd_sdk_android_release : null;
        if (!(threadPoolExecutor == null || (queue = threadPoolExecutor.getQueue()) == null)) {
            queue.drainTo(arrayList);
        }
        getUploadExecutorService$dd_sdk_android_release().getQueue().drainTo(arrayList);
        getPersistenceExecutorService$dd_sdk_android_release().shutdown();
        getUploadExecutorService$dd_sdk_android_release().shutdown();
        getPersistenceExecutorService$dd_sdk_android_release().awaitTermination(10, TimeUnit.SECONDS);
        getUploadExecutorService$dd_sdk_android_release().awaitTermination(10, TimeUnit.SECONDS);
        for (Runnable run : arrayList) {
            run.run();
        }
    }

    private final void prepareNdkCrashData(Context context) {
        if (isMainProcess) {
            ExecutorService persistenceExecutorService$dd_sdk_android_release = getPersistenceExecutorService$dd_sdk_android_release();
            LogGenerator logGenerator = new LogGenerator(serviceName, DatadogNdkCrashHandler.LOGGER_NAME, networkInfoProvider, userInfoProvider, timeProvider, sdkVersion, envName, packageVersion);
            Context context2 = context;
            LogGenerator logGenerator2 = logGenerator;
            NdkCrashHandler datadogNdkCrashHandler = new DatadogNdkCrashHandler(context2, persistenceExecutorService$dd_sdk_android_release, logGenerator2, new NdkCrashLogDeserializer(RuntimeUtilsKt.getSdkLogger()), new RumEventDeserializer(), new NetworkInfoDeserializer(RuntimeUtilsKt.getSdkLogger()), new UserInfoDeserializer(RuntimeUtilsKt.getSdkLogger()), RuntimeUtilsKt.getSdkLogger(), timeProvider, BatchFileHandler.Companion.create(RuntimeUtilsKt.getSdkLogger(), localDataEncryption), (RumEventSourceProvider) null, 1024, (DefaultConstructorMarker) null);
            ndkCrashHandler = datadogNdkCrashHandler;
            datadogNdkCrashHandler.prepareData();
        }
    }

    private final void initializeClockSync(Context context) {
        AndroidClockFactory androidClockFactory = AndroidClockFactory.INSTANCE;
        List listOf = CollectionsKt.listOf(DatadogEndpoint.NTP_0, DatadogEndpoint.NTP_1, DatadogEndpoint.NTP_2, DatadogEndpoint.NTP_3);
        long millis = TimeUnit.MINUTES.toMillis(30);
        Context context2 = context;
        KronosClock createKronosClock$default = AndroidClockFactory.createKronosClock$default(context2, new LoggingSyncListener(), listOf, 0, TimeUnit.MINUTES.toMillis(5), millis, 0, 72, (Object) null);
        if (!INSTANCE.getDisableKronosBackgroundSync$dd_sdk_android_release()) {
            createKronosClock$default.syncInBackground();
        }
        setKronosClock$dd_sdk_android_release(createKronosClock$default);
    }

    private final void readApplicationInformation(Context context, Credentials credentials) {
        PackageInfo packageInfo;
        String packageName2 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName2, "appContext.packageName");
        packageName = packageName2;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e$default(RuntimeUtilsKt.getDevLogger(), "Unable to read your application's version name", e, (Map) null, 4, (Object) null);
            packageInfo = null;
        }
        String str = DEFAULT_APP_VERSION;
        if (packageInfo != null) {
            String str2 = packageInfo.versionName;
            if (str2 == null) {
                str2 = String.valueOf(packageInfo.versionCode);
            }
            if (str2 != null) {
                str = str2;
            }
        }
        packageVersion = str;
        clientToken = credentials.getClientToken();
        String serviceName2 = credentials.getServiceName();
        if (serviceName2 == null) {
            serviceName2 = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(serviceName2, "appContext.packageName");
        }
        serviceName = serviceName2;
        rumApplicationId = credentials.getRumApplicationId();
        envName = credentials.getEnvName();
        variant = credentials.getVariant();
        contextRef = new WeakReference<>(context);
    }

    private final void readConfigurationSettings(Configuration.Core core) {
        batchSize = core.getBatchSize();
        uploadFrequency = core.getUploadFrequency();
        localDataEncryption = core.getSecurityConfig().getLocalDataEncryption();
    }

    private final void setupInfoProviders(Context context, TrackingConsent trackingConsent) {
        trackingConsentProvider = new TrackingConsentProvider(trackingConsent);
        SystemInfoProvider broadcastReceiverSystemInfoProvider = new BroadcastReceiverSystemInfoProvider((BuildSdkVersionProvider) null, 1, (DefaultConstructorMarker) null);
        systemInfoProvider = broadcastReceiverSystemInfoProvider;
        broadcastReceiverSystemInfoProvider.register(context);
        setupNetworkInfoProviders(context);
        setupUserInfoProvider(context);
    }

    private final void setupUserInfoProvider(Context context) {
        userInfoProvider = new DatadogUserInfoProvider(new ScheduledWriter(new NdkUserInfoDataWriter(context, trackingConsentProvider, getPersistenceExecutorService$dd_sdk_android_release(), BatchFileHandler.Companion.create(RuntimeUtilsKt.getSdkLogger(), localDataEncryption), RuntimeUtilsKt.getSdkLogger()), getPersistenceExecutorService$dd_sdk_android_release(), RuntimeUtilsKt.getSdkLogger()));
    }

    private final void setupNetworkInfoProviders(Context context) {
        NetworkInfoProvider networkInfoProvider2;
        ScheduledWriter scheduledWriter = new ScheduledWriter(new NdkNetworkInfoDataWriter(context, trackingConsentProvider, getPersistenceExecutorService$dd_sdk_android_release(), BatchFileHandler.Companion.create(RuntimeUtilsKt.getSdkLogger(), localDataEncryption), RuntimeUtilsKt.getSdkLogger()), getPersistenceExecutorService$dd_sdk_android_release(), RuntimeUtilsKt.getSdkLogger());
        if (Build.VERSION.SDK_INT >= 24) {
            networkInfoProvider2 = new CallbackNetworkInfoProvider(scheduledWriter, (BuildSdkVersionProvider) null, 2, (DefaultConstructorMarker) null);
        } else {
            networkInfoProvider2 = new BroadcastReceiverNetworkInfoProvider(scheduledWriter, (BuildSdkVersionProvider) null, 2, (DefaultConstructorMarker) null);
        }
        networkInfoProvider = networkInfoProvider2;
        networkInfoProvider2.register(context);
    }

    private final void setupOkHttpClient(Configuration.Core core) {
        ConnectionSpec connectionSpec;
        if (core.getNeedsClearTextHttp()) {
            connectionSpec = ConnectionSpec.CLEARTEXT;
        } else {
            ConnectionSpec.Builder supportsTlsExtensions = new ConnectionSpec.Builder(ConnectionSpec.RESTRICTED_TLS).tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_3).supportsTlsExtensions(true);
            CipherSuite[] cipherSuiteArr = RESTRICTED_CIPHER_SUITES;
            connectionSpec = supportsTlsExtensions.cipherSuites((CipherSuite[]) Arrays.copyOf(cipherSuiteArr, cipherSuiteArr.length)).build();
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        long j = NETWORK_TIMEOUT_MS;
        builder.callTimeout(j, TimeUnit.MILLISECONDS).writeTimeout(j, TimeUnit.MILLISECONDS).protocols(CollectionsKt.listOf(Protocol.HTTP_2, Protocol.HTTP_1_1)).connectionSpecs(CollectionsKt.listOf(connectionSpec));
        builder.addInterceptor(new GzipRequestInterceptor());
        if (core.getProxy() != null) {
            builder.proxy(core.getProxy());
            builder.proxyAuthenticator(core.getProxyAuth());
        }
        OkHttpClient build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        setOkHttpClient$dd_sdk_android_release(build);
    }

    private final void setupExecutors() {
        setUploadExecutorService$dd_sdk_android_release(new ScheduledThreadPoolExecutor(1));
        setPersistenceExecutorService$dd_sdk_android_release(new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors(), THREAD_POOL_MAX_KEEP_ALIVE_MS, TimeUnit.MILLISECONDS, new LinkedBlockingDeque()));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: android.app.ActivityManager$RunningAppProcessInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.app.ActivityManager$RunningAppProcessInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.app.ActivityManager$RunningAppProcessInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: android.app.ActivityManager$RunningAppProcessInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: android.app.ActivityManager$RunningAppProcessInfo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void resolveProcessInfo(android.content.Context r6) {
        /*
            r5 = this;
            int r5 = android.os.Process.myPid()
            java.lang.String r0 = "activity"
            java.lang.Object r0 = r6.getSystemService(r0)
            boolean r1 = r0 instanceof android.app.ActivityManager
            r2 = 0
            if (r1 == 0) goto L_0x0012
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            goto L_0x0013
        L_0x0012:
            r0 = r2
        L_0x0013:
            r1 = 1
            if (r0 != 0) goto L_0x0017
            goto L_0x003d
        L_0x0017:
            java.util.List r0 = r0.getRunningAppProcesses()
            if (r0 != 0) goto L_0x001e
            goto L_0x003d
        L_0x001e:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0024:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x003b
            java.lang.Object r3 = r0.next()
            r4 = r3
            android.app.ActivityManager$RunningAppProcessInfo r4 = (android.app.ActivityManager.RunningAppProcessInfo) r4
            int r4 = r4.pid
            if (r4 != r5) goto L_0x0037
            r4 = r1
            goto L_0x0038
        L_0x0037:
            r4 = 0
        L_0x0038:
            if (r4 == 0) goto L_0x0024
            r2 = r3
        L_0x003b:
            android.app.ActivityManager$RunningAppProcessInfo r2 = (android.app.ActivityManager.RunningAppProcessInfo) r2
        L_0x003d:
            if (r2 != 0) goto L_0x0046
            isMainProcess = r1
            r5 = 100
            processImportance = r5
            goto L_0x0056
        L_0x0046:
            java.lang.String r5 = r6.getPackageName()
            java.lang.String r6 = r2.processName
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            isMainProcess = r5
            int r5 = r2.importance
            processImportance = r5
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.internal.CoreFeature.resolveProcessInfo(android.content.Context):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0023 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void shutDownExecutors() {
        /*
            r6 = this;
            java.util.concurrent.ScheduledThreadPoolExecutor r0 = r6.getUploadExecutorService$dd_sdk_android_release()
            r0.shutdownNow()
            java.util.concurrent.ExecutorService r0 = r6.getPersistenceExecutorService$dd_sdk_android_release()
            r0.shutdownNow()
            java.util.concurrent.ScheduledThreadPoolExecutor r0 = r6.getUploadExecutorService$dd_sdk_android_release()     // Catch:{ InterruptedException -> 0x0023 }
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0023 }
            r2 = 1
            r0.awaitTermination(r2, r1)     // Catch:{ InterruptedException -> 0x0023 }
            java.util.concurrent.ExecutorService r6 = r6.getPersistenceExecutorService$dd_sdk_android_release()     // Catch:{ InterruptedException -> 0x0023 }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0023 }
            r6.awaitTermination(r2, r0)     // Catch:{ InterruptedException -> 0x0023 }
            goto L_0x003b
        L_0x0023:
            java.lang.Thread r6 = java.lang.Thread.currentThread()     // Catch:{ SecurityException -> 0x002b }
            r6.interrupt()     // Catch:{ SecurityException -> 0x002b }
            goto L_0x003b
        L_0x002b:
            r6 = move-exception
            com.datadog.android.log.Logger r0 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getSdkLogger()
            r2 = r6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = 0
            r4 = 4
            r5 = 0
            java.lang.String r1 = "Thread was unable to set its own interrupted state"
            com.datadog.android.log.Logger.e$default(r0, r1, r2, r3, r4, r5)
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.internal.CoreFeature.shutDownExecutors():void");
    }

    private final void cleanupApplicationInfo() {
        clientToken = "";
        packageName = "";
        packageVersion = "";
        serviceName = "";
        sourceName = DEFAULT_SOURCE_NAME;
        rumApplicationId = null;
        isMainProcess = true;
        envName = "";
        variant = "";
    }

    private final void cleanupProviders() {
        firstPartyHostDetector = new FirstPartyHostDetector(CollectionsKt.emptyList());
        networkInfoProvider = new NoOpNetworkInfoProvider();
        systemInfoProvider = new NoOpSystemInfoProvider();
        timeProvider = new NoOpTimeProvider();
        trackingConsentProvider = new NoOpConsentProvider();
        userInfoProvider = new NoOpMutableUserInfoProvider();
    }
}
