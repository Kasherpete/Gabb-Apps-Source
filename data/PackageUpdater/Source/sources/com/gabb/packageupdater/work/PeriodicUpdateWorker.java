package com.gabb.packageupdater.work;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import androidx.work.WorkerParameters;
import com.gabb.data.entities.App;
import com.gabb.packageupdater.domain.packagemanagement.PackageHandler;
import com.gabb.packageupdater.model.UpdatedApps;
import com.gabb.packageupdater.notifications.Notifier;
import com.gabb.packageupdater.repository.AppRepository;
import com.gabb.packageupdater.work.InstallerWorker;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u0000 #2\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001#BK\b\u0007\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\"R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/PeriodicUpdateWorker;", "Lcom/gabb/packageupdater/work/InstallerWorker;", "", "Lcom/gabb/data/entities/App;", "appContext", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "connectivity", "Landroid/net/ConnectivityManager;", "repository", "Lcom/gabb/packageupdater/repository/AppRepository;", "pacman", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageHandler;", "notifier", "Lcom/gabb/packageupdater/notifications/Notifier;", "updatedApps", "Lcom/gabb/packageupdater/model/UpdatedApps;", "mixpanelAPI", "Lcom/mixpanel/android/mpmetrics/MixpanelAPI;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Landroid/net/ConnectivityManager;Lcom/gabb/packageupdater/repository/AppRepository;Lcom/gabb/packageupdater/domain/packagemanagement/PackageHandler;Lcom/gabb/packageupdater/notifications/Notifier;Lcom/gabb/packageupdater/model/UpdatedApps;Lcom/mixpanel/android/mpmetrics/MixpanelAPI;)V", "attemptWork", "Landroidx/work/ListenableWorker$Result;", "withData", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertResult", "taskResult", "Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult;", "", "downloadData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateApps", "updateSelf", "app", "(Lcom/gabb/data/entities/App;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PeriodicUpdateWorker.kt */
public final class PeriodicUpdateWorker extends InstallerWorker<List<? extends App>> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String UNIQUE_NAME = "periodic-update-check";
    private final Context appContext;
    private final Notifier notifier;
    private final WorkerParameters params;
    private final AppRepository repository;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @AssistedInject
    public PeriodicUpdateWorker(@Assisted Context context, @Assisted WorkerParameters workerParameters, ConnectivityManager connectivityManager, AppRepository appRepository, PackageHandler packageHandler, Notifier notifier2, UpdatedApps updatedApps, MixpanelAPI mixpanelAPI) {
        super(context, workerParameters, connectivityManager, appRepository, packageHandler, notifier2, updatedApps, mixpanelAPI);
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(workerParameters, "params");
        Intrinsics.checkNotNullParameter(connectivityManager, "connectivity");
        Intrinsics.checkNotNullParameter(appRepository, "repository");
        Intrinsics.checkNotNullParameter(packageHandler, "pacman");
        Intrinsics.checkNotNullParameter(notifier2, "notifier");
        Intrinsics.checkNotNullParameter(updatedApps, "updatedApps");
        Intrinsics.checkNotNullParameter(mixpanelAPI, "mixpanelAPI");
        this.appContext = context;
        this.params = workerParameters;
        this.repository = appRepository;
        this.notifier = notifier2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.util.List<com.gabb.data.entities.App>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0091 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object attemptWork(java.util.List<com.gabb.data.entities.App> r9, kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.gabb.packageupdater.work.PeriodicUpdateWorker$attemptWork$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            com.gabb.packageupdater.work.PeriodicUpdateWorker$attemptWork$1 r0 = (com.gabb.packageupdater.work.PeriodicUpdateWorker$attemptWork$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.work.PeriodicUpdateWorker$attemptWork$1 r0 = new com.gabb.packageupdater.work.PeriodicUpdateWorker$attemptWork$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "com.gabb.packageupdater"
            r4 = 2
            r5 = 0
            r6 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r6) goto L_0x003d
            if (r2 != r4) goto L_0x0035
            java.lang.Object r8 = r0.L$0
            com.gabb.packageupdater.work.PeriodicUpdateWorker r8 = (com.gabb.packageupdater.work.PeriodicUpdateWorker) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00d7
        L_0x0035:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003d:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r8 = r0.L$0
            com.gabb.packageupdater.work.PeriodicUpdateWorker r8 = (com.gabb.packageupdater.work.PeriodicUpdateWorker) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0080
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r9
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.Iterator r10 = r10.iterator()
        L_0x0054:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x006c
            java.lang.Object r2 = r10.next()
            r7 = r2
            com.gabb.data.entities.App r7 = (com.gabb.data.entities.App) r7
            java.lang.String r7 = r7.getPackageName()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r3)
            if (r7 == 0) goto L_0x0054
            goto L_0x006d
        L_0x006c:
            r2 = r5
        L_0x006d:
            com.gabb.data.entities.App r2 = (com.gabb.data.entities.App) r2
            if (r2 != 0) goto L_0x0073
            r10 = r5
            goto L_0x0082
        L_0x0073:
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r6
            java.lang.Object r10 = r8.updateSelf(r2, r0)
            if (r10 != r1) goto L_0x0080
            return r1
        L_0x0080:
            androidx.work.ListenableWorker$Result r10 = (androidx.work.ListenableWorker.Result) r10
        L_0x0082:
            if (r10 != 0) goto L_0x008d
            androidx.work.ListenableWorker$Result r10 = androidx.work.ListenableWorker.Result.success()
            java.lang.String r2 = "success()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)
        L_0x008d:
            boolean r2 = r10 instanceof androidx.work.ListenableWorker.Result.Failure
            if (r2 == 0) goto L_0x0092
            return r10
        L_0x0092:
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Collection r10 = (java.util.Collection) r10
            java.util.Iterator r9 = r9.iterator()
        L_0x009f:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L_0x00bb
            java.lang.Object r2 = r9.next()
            r7 = r2
            com.gabb.data.entities.App r7 = (com.gabb.data.entities.App) r7
            java.lang.String r7 = r7.getPackageName()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r3)
            r7 = r7 ^ r6
            if (r7 == 0) goto L_0x009f
            r10.add(r2)
            goto L_0x009f
        L_0x00bb:
            java.util.List r10 = (java.util.List) r10
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            com.gabb.packageupdater.work.PeriodicUpdateWorker$attemptWork$$inlined$sortedBy$1 r9 = new com.gabb.packageupdater.work.PeriodicUpdateWorker$attemptWork$$inlined$sortedBy$1
            r9.<init>()
            java.util.Comparator r9 = (java.util.Comparator) r9
            java.util.List r9 = kotlin.collections.CollectionsKt.sortedWith(r10, r9)
            r0.L$0 = r8
            r0.L$1 = r5
            r0.label = r4
            java.lang.Object r10 = r8.updateApps(r9, r0)
            if (r10 != r1) goto L_0x00d7
            return r1
        L_0x00d7:
            androidx.work.ListenableWorker$Result r10 = (androidx.work.ListenableWorker.Result) r10
            androidx.work.ListenableWorker$Result r9 = androidx.work.ListenableWorker.Result.success()
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r9)
            if (r9 == 0) goto L_0x00e8
            com.gabb.packageupdater.notifications.Notifier r8 = r8.notifier
            r8.cancelAllNotifications()
        L_0x00e8:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.PeriodicUpdateWorker.attemptWork(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f A[Catch:{ Exception -> 0x008b }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object downloadData(kotlin.coroutines.Continuation<? super com.gabb.packageupdater.work.InstallerWorker.TaskResult<java.util.List<com.gabb.data.entities.App>>> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.gabb.packageupdater.work.PeriodicUpdateWorker$downloadData$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.gabb.packageupdater.work.PeriodicUpdateWorker$downloadData$1 r0 = (com.gabb.packageupdater.work.PeriodicUpdateWorker$downloadData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.work.PeriodicUpdateWorker$downloadData$1 r0 = new com.gabb.packageupdater.work.PeriodicUpdateWorker$downloadData$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "updater"
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0043
            if (r2 == r5) goto L_0x003b
            if (r2 != r4) goto L_0x0033
            java.lang.Object r7 = r0.L$0
            java.util.List r7 = (java.util.List) r7
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x008b }
            goto L_0x006f
        L_0x0033:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003b:
            java.lang.Object r7 = r0.L$0
            com.gabb.packageupdater.work.PeriodicUpdateWorker r7 = (com.gabb.packageupdater.work.PeriodicUpdateWorker) r7
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x008b }
            goto L_0x0053
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r8)
            com.gabb.packageupdater.repository.AppRepository r8 = r7.repository     // Catch:{ Exception -> 0x008b }
            r0.L$0 = r7     // Catch:{ Exception -> 0x008b }
            r0.label = r5     // Catch:{ Exception -> 0x008b }
            java.lang.Object r8 = r8.syncAppData(r0)     // Catch:{ Exception -> 0x008b }
            if (r8 != r1) goto L_0x0053
            return r1
        L_0x0053:
            java.util.Collection r8 = (java.util.Collection) r8     // Catch:{ Exception -> 0x008b }
            java.util.List r8 = kotlin.collections.CollectionsKt.toMutableList(r8)     // Catch:{ Exception -> 0x008b }
            boolean r2 = r7.hasWifiConnection()     // Catch:{ Exception -> 0x008b }
            if (r2 == 0) goto L_0x007a
            com.gabb.packageupdater.repository.AppRepository r7 = r7.repository     // Catch:{ Exception -> 0x008b }
            r0.L$0 = r8     // Catch:{ Exception -> 0x008b }
            r0.label = r4     // Catch:{ Exception -> 0x008b }
            java.lang.Object r7 = r7.syncThirdPartyAppData(r0)     // Catch:{ Exception -> 0x008b }
            if (r7 != r1) goto L_0x006c
            return r1
        L_0x006c:
            r6 = r8
            r8 = r7
            r7 = r6
        L_0x006f:
            java.util.List r8 = (java.util.List) r8     // Catch:{ Exception -> 0x008b }
            r0 = r7
            java.util.Collection r0 = (java.util.Collection) r0     // Catch:{ Exception -> 0x008b }
            java.lang.Iterable r8 = (java.lang.Iterable) r8     // Catch:{ Exception -> 0x008b }
            kotlin.collections.CollectionsKt.addAll(r0, r8)     // Catch:{ Exception -> 0x008b }
            r8 = r7
        L_0x007a:
            java.lang.String r7 = "apps="
            java.lang.String r7 = kotlin.jvm.internal.Intrinsics.stringPlus(r7, r8)     // Catch:{ Exception -> 0x008b }
            android.util.Log.d(r3, r7)     // Catch:{ Exception -> 0x008b }
            com.gabb.packageupdater.work.InstallerWorker$TaskResult$Success r7 = new com.gabb.packageupdater.work.InstallerWorker$TaskResult$Success     // Catch:{ Exception -> 0x008b }
            r7.<init>(r8)     // Catch:{ Exception -> 0x008b }
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r7 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r7     // Catch:{ Exception -> 0x008b }
            goto L_0x00a3
        L_0x008b:
            r7 = move-exception
            java.lang.String r8 = r7.getMessage()
            java.lang.String r0 = "downloadData Exception "
            java.lang.String r8 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r8)
            android.util.Log.d(r3, r8)
            com.gabb.packageupdater.work.InstallerWorker$TaskResult$Failed r8 = new com.gabb.packageupdater.work.InstallerWorker$TaskResult$Failed
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            r8.<init>(r7)
            r7 = r8
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r7 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r7
        L_0x00a3:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.PeriodicUpdateWorker.downloadData(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object updateSelf(com.gabb.data.entities.App r5, kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.gabb.packageupdater.work.PeriodicUpdateWorker$updateSelf$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.gabb.packageupdater.work.PeriodicUpdateWorker$updateSelf$1 r0 = (com.gabb.packageupdater.work.PeriodicUpdateWorker$updateSelf$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.work.PeriodicUpdateWorker$updateSelf$1 r0 = new com.gabb.packageupdater.work.PeriodicUpdateWorker$updateSelf$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            com.gabb.packageupdater.work.PeriodicUpdateWorker r4 = (com.gabb.packageupdater.work.PeriodicUpdateWorker) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0044
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r4.handleApp(r5, r3, r0)
            if (r6 != r1) goto L_0x0044
            return r1
        L_0x0044:
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r6 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r6
            androidx.work.ListenableWorker$Result r4 = r4.convertResult(r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.PeriodicUpdateWorker.updateSelf(com.gabb.data.entities.App, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object updateApps(java.util.List<com.gabb.data.entities.App> r5, kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$1 r0 = (com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$1 r0 = new com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0046
        L_0x002a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$2 r6 = new com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$2
            r2 = 0
            r6.<init>(r5, r4, r2)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r6, r0)
            if (r6 != r1) goto L_0x0046
            return r1
        L_0x0046:
            java.lang.String r4 = "private suspend fun upda…?: Result.success()\n    }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.PeriodicUpdateWorker.updateApps(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final ListenableWorker.Result convertResult(InstallerWorker.TaskResult<Boolean> taskResult) {
        if (taskResult instanceof InstallerWorker.TaskResult.Failed) {
            ListenableWorker.Result failure = ListenableWorker.Result.failure();
            Intrinsics.checkNotNullExpressionValue(failure, "failure()");
            return failure;
        }
        ListenableWorker.Result success = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(success, "success()");
        return success;
    }

    @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/PeriodicUpdateWorker$Companion;", "", "()V", "UNIQUE_NAME", "", "build", "Landroidx/work/OneTimeWorkRequest;", "tag", "buildPeriodic", "Landroidx/work/PeriodicWorkRequest;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: PeriodicUpdateWorker.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PeriodicWorkRequest buildPeriodic() {
            WorkRequest build = ((PeriodicWorkRequest.Builder) ((PeriodicWorkRequest.Builder) new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) PeriodicUpdateWorker.class, 4, TimeUnit.HOURS).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 1, TimeUnit.HOURS)).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(PeriodicUpdateWo…\n                .build()");
            return (PeriodicWorkRequest) build;
        }

        public final OneTimeWorkRequest build(String str) {
            Intrinsics.checkNotNullParameter(str, "tag");
            WorkRequest build = ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(PeriodicUpdateWorker.class).addTag(str)).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(PeriodicUpdateWo…\n                .build()");
            return (OneTimeWorkRequest) build;
        }
    }
}
