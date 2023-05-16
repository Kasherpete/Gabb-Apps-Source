package com.gabb.packageupdater.repository;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.data.dao.AppDao;
import com.gabb.data.entities.App;
import com.gabb.packageupdater.domain.packagemanagement.PackageHandler;
import com.gabb.packageupdater.domain.vendor.VendorManager;
import com.gabb.packageupdater.network.interfaces.StoreApi;
import com.gabb.packageupdater.network.model.Release;
import com.gabb.packageupdater.sdk.UpdateCallbacks;
import com.gabb.services.tokens.TokenManager;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Singleton
@Metadata(mo20734d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u0000 62\u00020\u0001:\u00016BA\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J(\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u001aH@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010\u001f\u001a\u00020 H@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ&\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001b2\b\b\u0002\u0010$\u001a\u00020%H\u0002J\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u0010\u0010'\u001a\u00020%2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0019\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010*J\u0011\u0010+\u001a\u00020 H@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJC\u0010,\u001a\u00020\u00132\n\u0010)\u001a\u00060\u0013j\u0002`-2\u0006\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u00132\u0014\b\u0002\u00100\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020 01H@ø\u0001\u0000¢\u0006\u0002\u00103J\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001eR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00067"}, mo20735d2 = {"Lcom/gabb/packageupdater/repository/AppRepository;", "", "context", "Landroid/content/Context;", "storeApi", "Lcom/gabb/packageupdater/network/interfaces/StoreApi;", "appDAO", "Lcom/gabb/data/dao/AppDao;", "apkDownloader", "Lcom/gabb/packageupdater/repository/AppDownloader;", "vendorManager", "Lcom/gabb/packageupdater/domain/vendor/VendorManager;", "pacman", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageHandler;", "tokenManager", "Lcom/gabb/services/tokens/TokenManager;", "(Landroid/content/Context;Lcom/gabb/packageupdater/network/interfaces/StoreApi;Lcom/gabb/data/dao/AppDao;Lcom/gabb/packageupdater/repository/AppDownloader;Lcom/gabb/packageupdater/domain/vendor/VendorManager;Lcom/gabb/packageupdater/domain/packagemanagement/PackageHandler;Lcom/gabb/services/tokens/TokenManager;)V", "callbacks", "", "", "Lcom/gabb/packageupdater/sdk/UpdateCallbacks;", "getCallbacks", "()Ljava/util/Map;", "preferences", "Landroid/content/SharedPreferences;", "checkAppsNeedUpdate", "Lkotlin/Result;", "", "Lcom/gabb/data/entities/App;", "checkAppsNeedUpdate-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clear", "", "getAppsFromReleases", "releases", "Lcom/gabb/packageupdater/network/model/Release;", "autoEnabled", "", "getInternalReleases", "isUserSetupComplete", "removeApp", "packageName", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeThirdPartyAppsIfNecessary", "requestSingleDownload", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageName;", "versionName", "apkUrl", "onDownloading", "Lkotlin/Function1;", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncAppData", "syncThirdPartyAppData", "Companion", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: AppRepository.kt */
public final class AppRepository {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String hasDeletedUserApps = "HasDeletedUserApps";
    public static final String updaterStoragePrefs = "UpdaterStoragePrefs";
    /* access modifiers changed from: private */
    public final AppDownloader apkDownloader;
    private final AppDao appDAO;
    private final Map<String, UpdateCallbacks> callbacks = new LinkedHashMap();
    private final Context context;
    private final PackageHandler pacman;
    private final SharedPreferences preferences;
    private final StoreApi storeApi;
    private final TokenManager tokenManager;
    private final VendorManager vendorManager;

    @Inject
    public AppRepository(@ApplicationContext Context context2, StoreApi storeApi2, AppDao appDao, AppDownloader appDownloader, VendorManager vendorManager2, PackageHandler packageHandler, TokenManager tokenManager2) {
        Intrinsics.checkNotNullParameter(context2, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(storeApi2, "storeApi");
        Intrinsics.checkNotNullParameter(appDao, "appDAO");
        Intrinsics.checkNotNullParameter(appDownloader, "apkDownloader");
        Intrinsics.checkNotNullParameter(vendorManager2, "vendorManager");
        Intrinsics.checkNotNullParameter(packageHandler, "pacman");
        Intrinsics.checkNotNullParameter(tokenManager2, "tokenManager");
        this.context = context2;
        this.storeApi = storeApi2;
        this.appDAO = appDao;
        this.apkDownloader = appDownloader;
        this.vendorManager = vendorManager2;
        this.pacman = packageHandler;
        this.tokenManager = tokenManager2;
        SharedPreferences sharedPreferences = context2.getSharedPreferences(updaterStoragePrefs, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…fs, Context.MODE_PRIVATE)");
        this.preferences = sharedPreferences;
    }

    public final Map<String, UpdateCallbacks> getCallbacks() {
        return this.callbacks;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object requestSingleDownload(java.lang.String r16, java.lang.String r17, java.lang.String r18, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r19, kotlin.coroutines.Continuation<? super java.lang.String> r20) {
        /*
            r15 = this;
            r7 = r15
            r0 = r20
            boolean r1 = r0 instanceof com.gabb.packageupdater.repository.AppRepository$requestSingleDownload$1
            if (r1 == 0) goto L_0x0017
            r1 = r0
            com.gabb.packageupdater.repository.AppRepository$requestSingleDownload$1 r1 = (com.gabb.packageupdater.repository.AppRepository$requestSingleDownload$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0017
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001c
        L_0x0017:
            com.gabb.packageupdater.repository.AppRepository$requestSingleDownload$1 r1 = new com.gabb.packageupdater.repository.AppRepository$requestSingleDownload$1
            r1.<init>(r15, r0)
        L_0x001c:
            r8 = r1
            java.lang.Object r0 = r8.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r10 = 2
            r11 = 1
            if (r1 == 0) goto L_0x004c
            if (r1 == r11) goto L_0x003d
            if (r1 != r10) goto L_0x0035
            java.lang.Object r1 = r8.L$0
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x008c
        L_0x0035:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003d:
            java.lang.Object r1 = r8.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r8.L$0
            com.gabb.packageupdater.repository.AppRepository r2 = (com.gabb.packageupdater.repository.AppRepository) r2
            kotlin.ResultKt.throwOnFailure(r0)
            r14 = r1
            r1 = r0
            r0 = r14
            goto L_0x0078
        L_0x004c:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()
            r12 = r0
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12
            com.gabb.packageupdater.repository.AppRepository$requestSingleDownload$installUri$1 r13 = new com.gabb.packageupdater.repository.AppRepository$requestSingleDownload$installUri$1
            r6 = 0
            r0 = r13
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6)
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            r8.L$0 = r7
            r0 = r16
            r8.L$1 = r0
            r8.label = r11
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r12, r13, r8)
            if (r1 != r9) goto L_0x0077
            return r9
        L_0x0077:
            r2 = r7
        L_0x0078:
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L_0x008d
            com.gabb.data.dao.AppDao r2 = r2.appDAO
            r8.L$0 = r1
            r3 = 0
            r8.L$1 = r3
            r8.label = r10
            java.lang.Object r0 = r2.updateWithUri(r0, r1, r8)
            if (r0 != r9) goto L_0x008c
            return r9
        L_0x008c:
            return r1
        L_0x008d:
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppRepository.requestSingleDownload(java.lang.String, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object requestSingleDownload$default(AppRepository appRepository, String str, String str2, String str3, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = AppRepository$requestSingleDownload$2.INSTANCE;
        }
        return appRepository.requestSingleDownload(str, str2, str3, function1, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object removeThirdPartyAppsIfNecessary(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.gabb.packageupdater.repository.AppRepository$removeThirdPartyAppsIfNecessary$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.gabb.packageupdater.repository.AppRepository$removeThirdPartyAppsIfNecessary$1 r0 = (com.gabb.packageupdater.repository.AppRepository$removeThirdPartyAppsIfNecessary$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.repository.AppRepository$removeThirdPartyAppsIfNecessary$1 r0 = new com.gabb.packageupdater.repository.AppRepository$removeThirdPartyAppsIfNecessary$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "HasDeletedUserApps"
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 == r6) goto L_0x003f
            if (r2 == r5) goto L_0x0037
            if (r2 != r4) goto L_0x002f
            goto L_0x0037
        L_0x002f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0037:
            java.lang.Object r7 = r0.L$0
            com.gabb.packageupdater.repository.AppRepository r7 = (com.gabb.packageupdater.repository.AppRepository) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0086
        L_0x003f:
            java.lang.Object r7 = r0.L$0
            com.gabb.packageupdater.repository.AppRepository r7 = (com.gabb.packageupdater.repository.AppRepository) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0060
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r8)
            android.content.SharedPreferences r8 = r7.preferences
            r2 = 0
            boolean r8 = r8.getBoolean(r3, r2)
            if (r8 != 0) goto L_0x0097
            com.gabb.services.tokens.TokenManager r8 = r7.tokenManager
            r0.L$0 = r7
            r0.label = r6
            java.lang.Object r8 = r8.getAccessToken(r0)
            if (r8 != r1) goto L_0x0060
            return r1
        L_0x0060:
            com.gabb.services.tokens.JWT r8 = (com.gabb.services.tokens.JWT) r8
            if (r8 != 0) goto L_0x0066
            r8 = 0
            goto L_0x006a
        L_0x0066:
            java.lang.String r8 = r8.getBearer()
        L_0x006a:
            if (r8 != 0) goto L_0x0079
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler r8 = r7.pacman
            r0.L$0 = r7
            r0.label = r5
            java.lang.Object r8 = r8.removeAllUserApps(r0)
            if (r8 != r1) goto L_0x0086
            return r1
        L_0x0079:
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler r8 = r7.pacman
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = r8.removeDisabledUserApps(r0)
            if (r8 != r1) goto L_0x0086
            return r1
        L_0x0086:
            android.content.SharedPreferences r7 = r7.preferences
            android.content.SharedPreferences$Editor r7 = r7.edit()
            java.lang.String r8 = "editor"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            r7.putBoolean(r3, r6)
            r7.apply()
        L_0x0097:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppRepository.removeThirdPartyAppsIfNecessary(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0075 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object syncAppData(kotlin.coroutines.Continuation<? super java.util.List<com.gabb.data.entities.App>> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.gabb.packageupdater.repository.AppRepository$syncAppData$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.gabb.packageupdater.repository.AppRepository$syncAppData$1 r0 = (com.gabb.packageupdater.repository.AppRepository$syncAppData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.repository.AppRepository$syncAppData$1 r0 = new com.gabb.packageupdater.repository.AppRepository$syncAppData$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r5 = r0.L$0
            java.util.List r5 = (java.util.List) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0077
        L_0x0031:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0039:
            java.lang.Object r5 = r0.L$0
            com.gabb.packageupdater.repository.AppRepository r5 = (com.gabb.packageupdater.repository.AppRepository) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004f
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.getInternalReleases(r0)
            if (r6 != r1) goto L_0x004f
            return r1
        L_0x004f:
            java.util.List r6 = (java.util.List) r6
            com.gabb.data.dao.AppDao r5 = r5.appDAO
            r2 = r6
            java.util.Collection r2 = (java.util.Collection) r2
            r4 = 0
            com.gabb.data.entities.App[] r4 = new com.gabb.data.entities.App[r4]
            java.lang.Object[] r2 = r2.toArray(r4)
            java.lang.String r4 = "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>"
            java.util.Objects.requireNonNull(r2, r4)
            com.gabb.data.entities.App[] r2 = (com.gabb.data.entities.App[]) r2
            int r4 = r2.length
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r4)
            com.gabb.data.entities.App[] r2 = (com.gabb.data.entities.App[]) r2
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r5 = r5.upsert(r2, r0)
            if (r5 != r1) goto L_0x0076
            return r1
        L_0x0076:
            r5 = r6
        L_0x0077:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppRepository.syncAppData(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object syncThirdPartyAppData(kotlin.coroutines.Continuation<? super java.util.List<com.gabb.data.entities.App>> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.gabb.packageupdater.repository.AppRepository$syncThirdPartyAppData$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.gabb.packageupdater.repository.AppRepository$syncThirdPartyAppData$1 r0 = (com.gabb.packageupdater.repository.AppRepository$syncThirdPartyAppData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.repository.AppRepository$syncThirdPartyAppData$1 r0 = new com.gabb.packageupdater.repository.AppRepository$syncThirdPartyAppData$1
            r0.<init>(r8, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 1
            r5 = 0
            r6 = 2
            if (r2 == 0) goto L_0x0056
            if (r2 == r4) goto L_0x0046
            if (r2 == r6) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r8 = r0.L$0
            java.util.List r8 = (java.util.List) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00c5
        L_0x0036:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003e:
            java.lang.Object r8 = r0.L$0
            com.gabb.packageupdater.repository.AppRepository r8 = (com.gabb.packageupdater.repository.AppRepository) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x008e
        L_0x0046:
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r2 = r0.L$0
            com.gabb.packageupdater.repository.AppRepository r2 = (com.gabb.packageupdater.repository.AppRepository) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r7 = r9
            r9 = r8
            r8 = r2
            r2 = r7
            goto L_0x006e
        L_0x0056:
            kotlin.ResultKt.throwOnFailure(r9)
            com.gabb.packageupdater.domain.vendor.VendorManager r9 = r8.vendorManager
            java.lang.String r9 = r9.getVendorType()
            com.gabb.services.tokens.TokenManager r2 = r8.tokenManager
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r2 = r2.getAccessToken(r0)
            if (r2 != r1) goto L_0x006e
            return r1
        L_0x006e:
            com.gabb.services.tokens.JWT r2 = (com.gabb.services.tokens.JWT) r2
            if (r2 != 0) goto L_0x0074
            r2 = r5
            goto L_0x0078
        L_0x0074:
            java.lang.String r2 = r2.getBearer()
        L_0x0078:
            if (r2 != 0) goto L_0x007f
            java.util.List r8 = kotlin.collections.CollectionsKt.emptyList()
            return r8
        L_0x007f:
            com.gabb.packageupdater.network.interfaces.StoreApi r4 = r8.storeApi
            r0.L$0 = r8
            r0.L$1 = r5
            r0.label = r6
            java.lang.Object r9 = r4.getThirdPartyApps(r2, r9, r0)
            if (r9 != r1) goto L_0x008e
            return r1
        L_0x008e:
            java.util.List r9 = (java.util.List) r9
            boolean r2 = r9.isEmpty()
            if (r2 == 0) goto L_0x009b
            java.util.List r8 = kotlin.collections.CollectionsKt.emptyList()
            return r8
        L_0x009b:
            r2 = 0
            java.util.List r9 = getAppsFromReleases$default(r8, r9, r2, r6, r5)
            com.gabb.data.dao.AppDao r8 = r8.appDAO
            r4 = r9
            java.util.Collection r4 = (java.util.Collection) r4
            com.gabb.data.entities.App[] r2 = new com.gabb.data.entities.App[r2]
            java.lang.Object[] r2 = r4.toArray(r2)
            java.lang.String r4 = "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>"
            java.util.Objects.requireNonNull(r2, r4)
            com.gabb.data.entities.App[] r2 = (com.gabb.data.entities.App[]) r2
            int r4 = r2.length
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r4)
            com.gabb.data.entities.App[] r2 = (com.gabb.data.entities.App[]) r2
            r0.L$0 = r9
            r0.label = r3
            java.lang.Object r8 = r8.upsert(r2, r0)
            if (r8 != r1) goto L_0x00c4
            return r1
        L_0x00c4:
            r8 = r9
        L_0x00c5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppRepository.syncThirdPartyAppData(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: checkAppsNeedUpdate-IoAF18A  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m169checkAppsNeedUpdateIoAF18A(kotlin.coroutines.Continuation<? super kotlin.Result<? extends java.util.List<com.gabb.data.entities.App>>> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.gabb.packageupdater.repository.AppRepository$checkAppsNeedUpdate$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.gabb.packageupdater.repository.AppRepository$checkAppsNeedUpdate$1 r0 = (com.gabb.packageupdater.repository.AppRepository$checkAppsNeedUpdate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.repository.AppRepository$checkAppsNeedUpdate$1 r0 = new com.gabb.packageupdater.repository.AppRepository$checkAppsNeedUpdate$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r7 = r0.L$0
            com.gabb.packageupdater.repository.AppRepository r7 = (com.gabb.packageupdater.repository.AppRepository) r7
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0050 }
            goto L_0x0049
        L_0x002e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.Result$Companion r8 = kotlin.Result.Companion     // Catch:{ all -> 0x0050 }
            r8 = r7
            com.gabb.packageupdater.repository.AppRepository r8 = (com.gabb.packageupdater.repository.AppRepository) r8     // Catch:{ all -> 0x0050 }
            r0.L$0 = r7     // Catch:{ all -> 0x0050 }
            r0.label = r3     // Catch:{ all -> 0x0050 }
            java.lang.Object r8 = r8.getInternalReleases(r0)     // Catch:{ all -> 0x0050 }
            if (r8 != r1) goto L_0x0049
            return r1
        L_0x0049:
            java.util.List r8 = (java.util.List) r8     // Catch:{ all -> 0x0050 }
            java.lang.Object r8 = kotlin.Result.m176constructorimpl(r8)     // Catch:{ all -> 0x0050 }
            goto L_0x005b
        L_0x0050:
            r8 = move-exception
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
            java.lang.Object r8 = kotlin.Result.m176constructorimpl(r8)
        L_0x005b:
            java.lang.Throwable r0 = kotlin.Result.m179exceptionOrNullimpl(r8)
            if (r0 == 0) goto L_0x006c
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r7 = kotlin.Result.m176constructorimpl(r7)
            return r7
        L_0x006c:
            java.lang.Throwable r0 = kotlin.Result.m179exceptionOrNullimpl(r8)
            if (r0 != 0) goto L_0x00ab
            java.util.List r8 = (java.util.List) r8
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r8 = r8.iterator()
        L_0x0081:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x00a2
            java.lang.Object r1 = r8.next()
            r2 = r1
            com.gabb.data.entities.App r2 = (com.gabb.data.entities.App) r2
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler r3 = r7.pacman
            java.lang.String r4 = r2.getPackageName()
            long r5 = r2.getVersionCode()
            boolean r2 = r3.requiresUpdate(r4, r5)
            if (r2 == 0) goto L_0x0081
            r0.add(r1)
            goto L_0x0081
        L_0x00a2:
            java.util.List r0 = (java.util.List) r0
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.Result.m176constructorimpl(r0)
            return r7
        L_0x00ab:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r7 = kotlin.Result.m176constructorimpl(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppRepository.m169checkAppsNeedUpdateIoAF18A(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getInternalReleases(kotlin.coroutines.Continuation<? super java.util.List<com.gabb.data.entities.App>> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.gabb.packageupdater.repository.AppRepository$getInternalReleases$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.gabb.packageupdater.repository.AppRepository$getInternalReleases$1 r0 = (com.gabb.packageupdater.repository.AppRepository$getInternalReleases$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.repository.AppRepository$getInternalReleases$1 r0 = new com.gabb.packageupdater.repository.AppRepository$getInternalReleases$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            com.gabb.packageupdater.repository.AppRepository r5 = (com.gabb.packageupdater.repository.AppRepository) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004e
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            com.gabb.packageupdater.domain.vendor.VendorManager r6 = r5.vendorManager
            java.lang.String r6 = r6.getVendorType()
            com.gabb.packageupdater.network.interfaces.StoreApi r2 = r5.storeApi
            r0.L$0 = r5
            r0.label = r3
            java.lang.String r4 = "Token bd22cd9209a4a04040244fd24605e9a6aa21c527"
            java.lang.Object r6 = r2.getReleasesByDevice(r4, r6, r0)
            if (r6 != r1) goto L_0x004e
            return r1
        L_0x004e:
            java.util.List r6 = (java.util.List) r6
            java.util.List r5 = r5.getAppsFromReleases(r6, r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppRepository.getInternalReleases(kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ List getAppsFromReleases$default(AppRepository appRepository, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return appRepository.getAppsFromReleases(list, z);
    }

    private final List<App> getAppsFromReleases(List<Release> list, boolean z) {
        Object obj;
        Map linkedHashMap = new LinkedHashMap();
        for (Object next : list) {
            String packageName = ((Release) next).getPackageName();
            Object obj2 = linkedHashMap.get(packageName);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(packageName, obj2);
            }
            ((List) obj2).add(next);
        }
        Collection arrayList = new ArrayList();
        for (List it : linkedHashMap.values()) {
            Iterator it2 = it.iterator();
            if (!it2.hasNext()) {
                obj = null;
            } else {
                Object next2 = it2.next();
                if (!it2.hasNext()) {
                    obj = next2;
                } else {
                    long parseLong = Long.parseLong(((Release) next2).getVersionCode());
                    do {
                        Object next3 = it2.next();
                        long parseLong2 = Long.parseLong(((Release) next3).getVersionCode());
                        if (parseLong < parseLong2) {
                            next2 = next3;
                            parseLong = parseLong2;
                        }
                    } while (it2.hasNext());
                }
                obj = next2;
            }
            CollectionsKt.addAll(arrayList, CollectionsKt.listOf(obj));
        }
        List mutableList = CollectionsKt.toMutableList(CollectionsKt.filterNotNull((List) arrayList));
        if (isUserSetupComplete(this.context)) {
            mutableList.removeIf(AppRepository$$ExternalSyntheticLambda0.INSTANCE);
        }
        Iterable<Release> iterable = mutableList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Release release : iterable) {
            arrayList2.add(new App(release.getPackageName(), release.getAppName(), Long.parseLong(release.getVersionCode()), release.getVersionName(), z || release.getEnabled(), (String) null, release.getFileUrl(), release.getReleasedAt()));
        }
        return (List) arrayList2;
    }

    /* access modifiers changed from: private */
    /* renamed from: getAppsFromReleases$lambda-8  reason: not valid java name */
    public static final boolean m168getAppsFromReleases$lambda8(Release release) {
        Intrinsics.checkNotNullParameter(release, "it");
        return StringsKt.contains$default((CharSequence) release.getPackageName(), (CharSequence) "wizard", false, 2, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object removeApp(java.lang.String r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.gabb.packageupdater.repository.AppRepository$removeApp$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.gabb.packageupdater.repository.AppRepository$removeApp$1 r0 = (com.gabb.packageupdater.repository.AppRepository$removeApp$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.repository.AppRepository$removeApp$1 r0 = new com.gabb.packageupdater.repository.AppRepository$removeApp$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r4 = r0.L$1
            r5 = r4
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r4 = r0.L$0
            com.gabb.packageupdater.repository.AppRepository r4 = (com.gabb.packageupdater.repository.AppRepository) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004d
        L_0x0033:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r6)
            com.gabb.data.dao.AppDao r6 = r4.appDAO
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.deletePackage(r5, r0)
            if (r6 != r1) goto L_0x004d
            return r1
        L_0x004d:
            com.gabb.packageupdater.repository.AppDownloader r4 = r4.apkDownloader
            r4.clearCache(r5)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppRepository.removeApp(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object clear(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.gabb.packageupdater.repository.AppRepository$clear$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            com.gabb.packageupdater.repository.AppRepository$clear$1 r0 = (com.gabb.packageupdater.repository.AppRepository$clear$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.repository.AppRepository$clear$1 r0 = new com.gabb.packageupdater.repository.AppRepository$clear$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            com.gabb.packageupdater.repository.AppRepository r4 = (com.gabb.packageupdater.repository.AppRepository) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0046
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            com.gabb.data.dao.AppDao r5 = r4.appDAO
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.nuke(r0)
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            com.gabb.packageupdater.repository.AppDownloader r4 = r4.apkDownloader
            r5 = 0
            com.gabb.packageupdater.repository.AppDownloader.clearCache$default(r4, r5, r3, r5)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.repository.AppRepository.clear(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean isUserSetupComplete(Context context2) {
        int i;
        try {
            Result.Companion companion = Result.Companion;
            AppRepository appRepository = this;
            ContentResolver contentResolver = context2.getContentResolver();
            Intrinsics.checkNotNullExpressionValue(contentResolver, "context.contentResolver");
            i = Result.m176constructorimpl(Integer.valueOf(Settings.Secure.getInt(contentResolver, "gabb_setup_complete", 0)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            i = Result.m176constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m179exceptionOrNullimpl(i) != null) {
            i = 0;
        }
        if (((Number) i).intValue() == 1) {
            return true;
        }
        return false;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo20735d2 = {"Lcom/gabb/packageupdater/repository/AppRepository$Companion;", "", "()V", "hasDeletedUserApps", "", "updaterStoragePrefs", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: AppRepository.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
