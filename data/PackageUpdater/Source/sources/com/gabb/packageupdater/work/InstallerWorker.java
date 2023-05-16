package com.gabb.packageupdater.work;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.data.entities.App;
import com.gabb.packageupdater.domain.packagemanagement.PackageHandler;
import com.gabb.packageupdater.model.UpdatedApps;
import com.gabb.packageupdater.notifications.Notifier;
import com.gabb.packageupdater.repository.AppRepository;
import com.gabb.packageupdater.sdk.UpdateCallbacks;
import com.gabb.packageupdater.util.MixpanelExtensionsKt;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u00010BE\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0011\u0010 \u001a\u00020\u001dH@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H¦@ø\u0001\u0000¢\u0006\u0002\u0010!J\"\u0010$\u001a\b\u0012\u0004\u0012\u00020%0#2\u0006\u0010\u001a\u001a\u00020\u001b2\n\u0010&\u001a\u00060'j\u0002`(H\u0002J)\u0010)\u001a\b\u0012\u0004\u0012\u00020%0#2\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010*\u001a\u00020%H@ø\u0001\u0000¢\u0006\u0002\u0010+J\b\u0010,\u001a\u00020%H\u0004J\u001f\u0010-\u001a\b\u0012\u0004\u0012\u00020%0#2\u0006\u0010\u001a\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010.J\u001f\u0010/\u001a\b\u0012\u0004\u0012\u00020%0#2\u0006\u0010\u001a\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010.R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u00061"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/InstallerWorker;", "T", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "connectivity", "Landroid/net/ConnectivityManager;", "repository", "Lcom/gabb/packageupdater/repository/AppRepository;", "pacman", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageHandler;", "notifier", "Lcom/gabb/packageupdater/notifications/Notifier;", "updatedApps", "Lcom/gabb/packageupdater/model/UpdatedApps;", "mixpanelAPI", "Lcom/mixpanel/android/mpmetrics/MixpanelAPI;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Landroid/net/ConnectivityManager;Lcom/gabb/packageupdater/repository/AppRepository;Lcom/gabb/packageupdater/domain/packagemanagement/PackageHandler;Lcom/gabb/packageupdater/notifications/Notifier;Lcom/gabb/packageupdater/model/UpdatedApps;Lcom/mixpanel/android/mpmetrics/MixpanelAPI;)V", "callbacks", "Lcom/gabb/packageupdater/sdk/UpdateCallbacks;", "eventName", "", "addToUpdatedApps", "", "app", "Lcom/gabb/data/entities/App;", "attemptWork", "Landroidx/work/ListenableWorker$Result;", "withData", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doWork", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadData", "Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult;", "finishWithError", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleApp", "installIfUninstalled", "(Lcom/gabb/data/entities/App;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasWifiConnection", "removeApp", "(Lcom/gabb/data/entities/App;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateApp", "TaskResult", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: InstallerWorker.kt */
public abstract class InstallerWorker<T> extends CoroutineWorker {
    private UpdateCallbacks callbacks;
    private final ConnectivityManager connectivity;
    private final String eventName = "App updates";
    private final MixpanelAPI mixpanelAPI;
    /* access modifiers changed from: private */
    public final Notifier notifier;
    private final PackageHandler pacman;
    private final WorkerParameters params;
    private final AppRepository repository;
    private final UpdatedApps updatedApps;

    public abstract Object attemptWork(T t, Continuation<? super ListenableWorker.Result> continuation);

    public Object doWork(Continuation<? super ListenableWorker.Result> continuation) {
        return doWork$suspendImpl(this, continuation);
    }

    public abstract Object downloadData(Continuation<? super TaskResult<T>> continuation);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InstallerWorker(Context context, WorkerParameters workerParameters, ConnectivityManager connectivityManager, AppRepository appRepository, PackageHandler packageHandler, Notifier notifier2, UpdatedApps updatedApps2, MixpanelAPI mixpanelAPI2) {
        super(context, workerParameters);
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(workerParameters, "params");
        Intrinsics.checkNotNullParameter(connectivityManager, "connectivity");
        Intrinsics.checkNotNullParameter(appRepository, "repository");
        Intrinsics.checkNotNullParameter(packageHandler, "pacman");
        Intrinsics.checkNotNullParameter(notifier2, "notifier");
        Intrinsics.checkNotNullParameter(updatedApps2, "updatedApps");
        Intrinsics.checkNotNullParameter(mixpanelAPI2, "mixpanelAPI");
        this.params = workerParameters;
        this.connectivity = connectivityManager;
        this.repository = appRepository;
        this.pacman = packageHandler;
        this.notifier = notifier2;
        this.updatedApps = updatedApps2;
        this.mixpanelAPI = mixpanelAPI2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0088 A[Catch:{ Exception -> 0x015f }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0095 A[Catch:{ Exception -> 0x015f }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x009c A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a4 A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00e1 A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00e3 A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e8 A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00e9 A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00fd A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0115 A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0126 A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x013c A[Catch:{ Exception -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object doWork$suspendImpl(com.gabb.packageupdater.work.InstallerWorker r9, kotlin.coroutines.Continuation r10) {
        /*
            java.lang.String r0 = "install worker"
            boolean r1 = r10 instanceof com.gabb.packageupdater.work.InstallerWorker$doWork$1
            if (r1 == 0) goto L_0x0016
            r1 = r10
            com.gabb.packageupdater.work.InstallerWorker$doWork$1 r1 = (com.gabb.packageupdater.work.InstallerWorker$doWork$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r10 = r1.label
            int r10 = r10 - r3
            r1.label = r10
            goto L_0x001b
        L_0x0016:
            com.gabb.packageupdater.work.InstallerWorker$doWork$1 r1 = new com.gabb.packageupdater.work.InstallerWorker$doWork$1
            r1.<init>(r9, r10)
        L_0x001b:
            java.lang.Object r10 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 5
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r3 == 0) goto L_0x006c
            if (r3 == r8) goto L_0x0064
            if (r3 == r7) goto L_0x005c
            if (r3 == r6) goto L_0x004e
            if (r3 == r5) goto L_0x0045
            if (r3 != r4) goto L_0x003d
            java.lang.Object r9 = r1.L$0
            com.gabb.packageupdater.work.InstallerWorker r9 = (com.gabb.packageupdater.work.InstallerWorker) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x015f }
            goto L_0x0151
        L_0x003d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0045:
            java.lang.Object r9 = r1.L$0
            com.gabb.packageupdater.work.InstallerWorker r9 = (com.gabb.packageupdater.work.InstallerWorker) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x015f }
            goto L_0x0120
        L_0x004e:
            java.lang.Object r9 = r1.L$0
            com.gabb.packageupdater.work.InstallerWorker r9 = (com.gabb.packageupdater.work.InstallerWorker) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x015f }
            kotlin.Result r10 = (kotlin.Result) r10     // Catch:{ Exception -> 0x015f }
            java.lang.Object r10 = r10.m185unboximpl()     // Catch:{ Exception -> 0x015f }
            goto L_0x0096
        L_0x005c:
            java.lang.Object r9 = r1.L$0
            com.gabb.packageupdater.work.InstallerWorker r9 = (com.gabb.packageupdater.work.InstallerWorker) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x015f }
            goto L_0x0089
        L_0x0064:
            java.lang.Object r9 = r1.L$0
            com.gabb.packageupdater.work.InstallerWorker r9 = (com.gabb.packageupdater.work.InstallerWorker) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x015f }
            goto L_0x007c
        L_0x006c:
            kotlin.ResultKt.throwOnFailure(r10)
            com.gabb.packageupdater.repository.AppRepository r10 = r9.repository     // Catch:{ Exception -> 0x015f }
            r1.L$0 = r9     // Catch:{ Exception -> 0x015f }
            r1.label = r8     // Catch:{ Exception -> 0x015f }
            java.lang.Object r10 = r10.clear(r1)     // Catch:{ Exception -> 0x015f }
            if (r10 != r2) goto L_0x007c
            return r2
        L_0x007c:
            com.gabb.packageupdater.repository.AppRepository r10 = r9.repository     // Catch:{ Exception -> 0x015f }
            r1.L$0 = r9     // Catch:{ Exception -> 0x015f }
            r1.label = r7     // Catch:{ Exception -> 0x015f }
            java.lang.Object r10 = r10.removeThirdPartyAppsIfNecessary(r1)     // Catch:{ Exception -> 0x015f }
            if (r10 != r2) goto L_0x0089
            return r2
        L_0x0089:
            com.gabb.packageupdater.repository.AppRepository r10 = r9.repository     // Catch:{ Exception -> 0x015f }
            r1.L$0 = r9     // Catch:{ Exception -> 0x015f }
            r1.label = r6     // Catch:{ Exception -> 0x015f }
            java.lang.Object r10 = r10.m169checkAppsNeedUpdateIoAF18A(r1)     // Catch:{ Exception -> 0x015f }
            if (r10 != r2) goto L_0x0096
            return r2
        L_0x0096:
            boolean r3 = kotlin.Result.m182isFailureimpl(r10)     // Catch:{ Exception -> 0x015f }
            if (r3 == 0) goto L_0x009d
            r10 = 0
        L_0x009d:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x015f }
            r3 = 0
            if (r10 != 0) goto L_0x00a4
        L_0x00a2:
            r10 = r3
            goto L_0x00df
        L_0x00a4:
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x015f }
            boolean r6 = r10 instanceof java.util.Collection     // Catch:{ Exception -> 0x015f }
            if (r6 == 0) goto L_0x00b4
            r6 = r10
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ Exception -> 0x015f }
            boolean r6 = r6.isEmpty()     // Catch:{ Exception -> 0x015f }
            if (r6 == 0) goto L_0x00b4
            goto L_0x00a2
        L_0x00b4:
            java.util.Iterator r10 = r10.iterator()     // Catch:{ Exception -> 0x015f }
        L_0x00b8:
            boolean r6 = r10.hasNext()     // Catch:{ Exception -> 0x015f }
            if (r6 == 0) goto L_0x00a2
            java.lang.Object r6 = r10.next()     // Catch:{ Exception -> 0x015f }
            com.gabb.data.entities.App r6 = (com.gabb.data.entities.App) r6     // Catch:{ Exception -> 0x015f }
            java.lang.String r6 = r6.getReleasedAt()     // Catch:{ Exception -> 0x015f }
            java.time.LocalDateTime r6 = com.gabb.base.extensions.DateKt.fromString(r6)     // Catch:{ Exception -> 0x015f }
            java.lang.String r7 = "fromString(it.releasedAt)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch:{ Exception -> 0x015f }
            int r6 = com.gabb.base.extensions.DateKt.daysFromToday(r6)     // Catch:{ Exception -> 0x015f }
            r7 = 10
            if (r6 <= r7) goto L_0x00db
            r6 = r8
            goto L_0x00dc
        L_0x00db:
            r6 = r3
        L_0x00dc:
            if (r6 == 0) goto L_0x00b8
            r10 = r8
        L_0x00df:
            if (r10 != 0) goto L_0x00e3
            r10 = r8
            goto L_0x00e4
        L_0x00e3:
            r10 = r3
        L_0x00e4:
            java.lang.String r6 = "wifi required is: "
            if (r10 == 0) goto L_0x00e9
            goto L_0x00ea
        L_0x00e9:
            r8 = r3
        L_0x00ea:
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)     // Catch:{ Exception -> 0x015f }
            java.lang.String r3 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r3)     // Catch:{ Exception -> 0x015f }
            android.util.Log.d(r0, r3)     // Catch:{ Exception -> 0x015f }
            if (r10 == 0) goto L_0x0115
            boolean r10 = r9.hasWifiConnection()     // Catch:{ Exception -> 0x015f }
            if (r10 != 0) goto L_0x0115
            com.mixpanel.android.mpmetrics.MixpanelAPI r10 = r9.mixpanelAPI     // Catch:{ Exception -> 0x015f }
            java.lang.String r1 = r9.eventName     // Catch:{ Exception -> 0x015f }
            java.lang.String r2 = "No Wifi"
            com.gabb.packageupdater.util.MixpanelExtensionsKt.trackFailure(r10, r1, r2)     // Catch:{ Exception -> 0x015f }
            java.lang.String r10 = "Retry. Wifi required"
            android.util.Log.d(r0, r10)     // Catch:{ Exception -> 0x015f }
            androidx.work.ListenableWorker$Result r10 = androidx.work.ListenableWorker.Result.retry()     // Catch:{ Exception -> 0x015f }
            java.lang.String r0 = "retry()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)     // Catch:{ Exception -> 0x015f }
            return r10
        L_0x0115:
            r1.L$0 = r9     // Catch:{ Exception -> 0x015f }
            r1.label = r5     // Catch:{ Exception -> 0x015f }
            java.lang.Object r10 = r9.downloadData(r1)     // Catch:{ Exception -> 0x015f }
            if (r10 != r2) goto L_0x0120
            return r2
        L_0x0120:
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r10 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r10     // Catch:{ Exception -> 0x015f }
            boolean r0 = r10 instanceof com.gabb.packageupdater.work.InstallerWorker.TaskResult.Failed     // Catch:{ Exception -> 0x015f }
            if (r0 == 0) goto L_0x013c
            com.mixpanel.android.mpmetrics.MixpanelAPI r0 = r9.mixpanelAPI     // Catch:{ Exception -> 0x015f }
            java.lang.String r1 = r9.eventName     // Catch:{ Exception -> 0x015f }
            com.gabb.packageupdater.work.InstallerWorker$TaskResult$Failed r10 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult.Failed) r10     // Catch:{ Exception -> 0x015f }
            java.lang.Throwable r10 = r10.getThrowable()     // Catch:{ Exception -> 0x015f }
            java.lang.String r10 = r10.getMessage()     // Catch:{ Exception -> 0x015f }
            com.gabb.packageupdater.util.MixpanelExtensionsKt.trackFailure(r0, r1, r10)     // Catch:{ Exception -> 0x015f }
            androidx.work.ListenableWorker$Result r10 = androidx.work.ListenableWorker.Result.failure()     // Catch:{ Exception -> 0x015f }
            goto L_0x0153
        L_0x013c:
            boolean r0 = r10 instanceof com.gabb.packageupdater.work.InstallerWorker.TaskResult.Success     // Catch:{ Exception -> 0x015f }
            if (r0 == 0) goto L_0x0159
            com.gabb.packageupdater.work.InstallerWorker$TaskResult$Success r10 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult.Success) r10     // Catch:{ Exception -> 0x015f }
            java.lang.Object r10 = r10.getData()     // Catch:{ Exception -> 0x015f }
            r1.L$0 = r9     // Catch:{ Exception -> 0x015f }
            r1.label = r4     // Catch:{ Exception -> 0x015f }
            java.lang.Object r10 = r9.attemptWork(r10, r1)     // Catch:{ Exception -> 0x015f }
            if (r10 != r2) goto L_0x0151
            return r2
        L_0x0151:
            androidx.work.ListenableWorker$Result r10 = (androidx.work.ListenableWorker.Result) r10     // Catch:{ Exception -> 0x015f }
        L_0x0153:
            java.lang.String r0 = "{\n            repository…}\n            }\n        }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)     // Catch:{ Exception -> 0x015f }
            goto L_0x0174
        L_0x0159:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException     // Catch:{ Exception -> 0x015f }
            r10.<init>()     // Catch:{ Exception -> 0x015f }
            throw r10     // Catch:{ Exception -> 0x015f }
        L_0x015f:
            r10 = move-exception
            com.mixpanel.android.mpmetrics.MixpanelAPI r0 = r9.mixpanelAPI
            java.lang.String r9 = r9.eventName
            java.lang.String r10 = r10.getMessage()
            com.gabb.packageupdater.util.MixpanelExtensionsKt.trackFailure(r0, r9, r10)
            androidx.work.ListenableWorker$Result r10 = androidx.work.ListenableWorker.Result.failure()
            java.lang.String r9 = "{\n            mixpanelAP…esult.failure()\n        }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r9)
        L_0x0174:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.InstallerWorker.doWork$suspendImpl(com.gabb.packageupdater.work.InstallerWorker, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object handleApp$default(InstallerWorker installerWorker, App app, boolean z, Continuation continuation, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            return installerWorker.handleApp(app, z, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleApp");
    }

    public final Object handleApp(App app, boolean z, Continuation<? super TaskResult<Boolean>> continuation) {
        boolean isInstalled = this.pacman.isInstalled(app.getPackageName());
        this.callbacks = this.repository.getCallbacks().get(app.getPackageName());
        if (!isInstalled && z) {
            return updateApp(app, continuation);
        }
        if (!isInstalled) {
            return new TaskResult.Success(Boxing.boxBoolean(true));
        }
        if (this.pacman.needsChangeState(app)) {
            this.pacman.handleDesiredPackageState(app);
            addToUpdatedApps(app);
            MixpanelExtensionsKt.trackSuccess(this.mixpanelAPI, this.eventName, app.getAppName(), Intrinsics.stringPlus("Enabled set to ", Boxing.boxBoolean(app.getEnabled())));
            return new TaskResult.Success(Boxing.boxBoolean(true));
        } else if (this.pacman.requiresUpdate(app.getPackageName(), app.getVersionCode())) {
            return updateApp(app, continuation);
        } else {
            Log.d("updater", Intrinsics.stringPlus(app.getPackageName(), " does not require update"));
            return new TaskResult.Success(Boxing.boxBoolean(true));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: com.gabb.data.entities.App} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007a A[Catch:{ Exception -> 0x0031 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007c A[Catch:{ Exception -> 0x0031 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008c A[Catch:{ Exception -> 0x0031 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008d A[Catch:{ Exception -> 0x0031 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object removeApp(com.gabb.data.entities.App r6, kotlin.coroutines.Continuation<? super com.gabb.packageupdater.work.InstallerWorker.TaskResult<java.lang.Boolean>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.gabb.packageupdater.work.InstallerWorker$removeApp$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.gabb.packageupdater.work.InstallerWorker$removeApp$1 r0 = (com.gabb.packageupdater.work.InstallerWorker$removeApp$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.work.InstallerWorker$removeApp$1 r0 = new com.gabb.packageupdater.work.InstallerWorker$removeApp$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "updater"
            r4 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r4) goto L_0x0033
            java.lang.Object r5 = r0.L$0
            r6 = r5
            com.gabb.data.entities.App r6 = (com.gabb.data.entities.App) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ Exception -> 0x0031 }
            goto L_0x0058
        L_0x0031:
            r5 = move-exception
            goto L_0x0098
        L_0x0033:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.String r7 = "attempting uninstall of "
            java.lang.String r2 = r6.getPackageName()     // Catch:{ Exception -> 0x0031 }
            java.lang.String r7 = kotlin.jvm.internal.Intrinsics.stringPlus(r7, r2)     // Catch:{ Exception -> 0x0031 }
            android.util.Log.d(r3, r7)     // Catch:{ Exception -> 0x0031 }
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler r5 = r5.pacman     // Catch:{ Exception -> 0x0031 }
            r0.L$0 = r6     // Catch:{ Exception -> 0x0031 }
            r0.label = r4     // Catch:{ Exception -> 0x0031 }
            java.lang.Object r7 = r5.remove(r6, r0)     // Catch:{ Exception -> 0x0031 }
            if (r7 != r1) goto L_0x0058
            return r1
        L_0x0058:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ Exception -> 0x0031 }
            boolean r5 = r7.booleanValue()     // Catch:{ Exception -> 0x0031 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0031 }
            r7.<init>()     // Catch:{ Exception -> 0x0031 }
            java.lang.String r0 = "uninstall of "
            java.lang.StringBuilder r7 = r7.append(r0)     // Catch:{ Exception -> 0x0031 }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ Exception -> 0x0031 }
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ Exception -> 0x0031 }
            java.lang.String r7 = " success="
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x0031 }
            r7 = 0
            if (r5 == 0) goto L_0x007c
            r0 = r4
            goto L_0x007d
        L_0x007c:
            r0 = r7
        L_0x007d:
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch:{ Exception -> 0x0031 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0031 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x0031 }
            com.gabb.packageupdater.work.InstallerWorker$TaskResult$Success r6 = new com.gabb.packageupdater.work.InstallerWorker$TaskResult$Success     // Catch:{ Exception -> 0x0031 }
            if (r5 == 0) goto L_0x008d
            goto L_0x008e
        L_0x008d:
            r4 = r7
        L_0x008e:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch:{ Exception -> 0x0031 }
            r6.<init>(r5)     // Catch:{ Exception -> 0x0031 }
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r6 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r6     // Catch:{ Exception -> 0x0031 }
            goto L_0x00a4
        L_0x0098:
            r5.printStackTrace()
            com.gabb.packageupdater.work.InstallerWorker$TaskResult$Failed r6 = new com.gabb.packageupdater.work.InstallerWorker$TaskResult$Failed
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r6.<init>(r5)
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r6 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r6
        L_0x00a4:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.InstallerWorker.removeApp(com.gabb.data.entities.App, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v15, resolved type: com.gabb.data.entities.App} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v17, resolved type: com.gabb.data.entities.App} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0139 A[Catch:{ Exception -> 0x005f, all -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x013b A[Catch:{ Exception -> 0x005f, all -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x015b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0162 A[Catch:{ Exception -> 0x0041 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0163 A[Catch:{ Exception -> 0x0041 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0168 A[Catch:{ Exception -> 0x0041 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x017d A[Catch:{ Exception -> 0x0041 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object updateApp(com.gabb.data.entities.App r14, kotlin.coroutines.Continuation<? super com.gabb.packageupdater.work.InstallerWorker.TaskResult<java.lang.Boolean>> r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof com.gabb.packageupdater.work.InstallerWorker$updateApp$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            com.gabb.packageupdater.work.InstallerWorker$updateApp$1 r0 = (com.gabb.packageupdater.work.InstallerWorker$updateApp$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.work.InstallerWorker$updateApp$1 r0 = new com.gabb.packageupdater.work.InstallerWorker$updateApp$1
            r0.<init>(r13, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r8 = 0
            r9 = 3
            r10 = 2
            java.lang.String r11 = "updater"
            r12 = 1
            if (r1 == 0) goto L_0x0077
            if (r1 == r12) goto L_0x0064
            if (r1 == r10) goto L_0x004c
            if (r1 != r9) goto L_0x0044
            boolean r13 = r0.Z$0
            java.lang.Object r14 = r0.L$1
            com.gabb.data.entities.App r14 = (com.gabb.data.entities.App) r14
            java.lang.Object r0 = r0.L$0
            com.gabb.packageupdater.work.InstallerWorker r0 = (com.gabb.packageupdater.work.InstallerWorker) r0
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Exception -> 0x0041 }
            goto L_0x015e
        L_0x003e:
            r13 = move-exception
            goto L_0x0196
        L_0x0041:
            r13 = move-exception
            goto L_0x0188
        L_0x0044:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x004c:
            java.lang.Object r13 = r0.L$1
            r14 = r13
            com.gabb.data.entities.App r14 = (com.gabb.data.entities.App) r14
            java.lang.Object r13 = r0.L$0
            com.gabb.packageupdater.work.InstallerWorker r13 = (com.gabb.packageupdater.work.InstallerWorker) r13
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            goto L_0x0118
        L_0x005a:
            r15 = move-exception
            r0 = r13
            r13 = r15
            goto L_0x0196
        L_0x005f:
            r15 = move-exception
            r0 = r13
            r13 = r15
            goto L_0x0188
        L_0x0064:
            java.lang.Object r13 = r0.L$1
            r14 = r13
            com.gabb.data.entities.App r14 = (com.gabb.data.entities.App) r14
            java.lang.Object r13 = r0.L$0
            com.gabb.packageupdater.work.InstallerWorker r13 = (com.gabb.packageupdater.work.InstallerWorker) r13
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Exception -> 0x0074 }
            goto L_0x00b5
        L_0x0071:
            r15 = move-exception
            goto L_0x01ae
        L_0x0074:
            r15 = move-exception
            goto L_0x01a0
        L_0x0077:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.String r15 = "downloading apk for "
            java.lang.String r1 = r14.getPackageName()     // Catch:{ Exception -> 0x0074 }
            java.lang.String r15 = kotlin.jvm.internal.Intrinsics.stringPlus(r15, r1)     // Catch:{ Exception -> 0x0074 }
            android.util.Log.d(r11, r15)     // Catch:{ Exception -> 0x0074 }
            com.gabb.packageupdater.notifications.Notifier r15 = r13.notifier     // Catch:{ Exception -> 0x0074 }
            java.lang.String r1 = r14.getPackageName()     // Catch:{ Exception -> 0x0074 }
            r2 = 0
            com.gabb.packageupdater.notifications.Notifier.notifyUserOfDownloadingUpdates$default(r15, r1, r2, r10, r2)     // Catch:{ Exception -> 0x0074 }
            com.gabb.packageupdater.repository.AppRepository r1 = r13.repository     // Catch:{ Exception -> 0x0074 }
            java.lang.String r2 = r14.getPackageName()     // Catch:{ Exception -> 0x0074 }
            java.lang.String r3 = r14.getVersion()     // Catch:{ Exception -> 0x0074 }
            java.lang.String r4 = r14.getDownloadUrl()     // Catch:{ Exception -> 0x0074 }
            com.gabb.packageupdater.work.InstallerWorker$updateApp$installUri$1 r15 = new com.gabb.packageupdater.work.InstallerWorker$updateApp$installUri$1     // Catch:{ Exception -> 0x0074 }
            r15.<init>(r13, r14)     // Catch:{ Exception -> 0x0074 }
            r5 = r15
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5     // Catch:{ Exception -> 0x0074 }
            r0.L$0 = r13     // Catch:{ Exception -> 0x0074 }
            r0.L$1 = r14     // Catch:{ Exception -> 0x0074 }
            r0.label = r12     // Catch:{ Exception -> 0x0074 }
            r6 = r0
            java.lang.Object r15 = r1.requestSingleDownload(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0074 }
            if (r15 != r7) goto L_0x00b5
            return r7
        L_0x00b5:
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ Exception -> 0x0074 }
            com.gabb.packageupdater.notifications.Notifier r1 = r13.notifier
            java.lang.String r2 = r14.getPackageName()
            r1.cancelNotification(r2)
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler r1 = r13.pacman
            boolean r1 = r1.hasValidSignature(r15)
            if (r1 != 0) goto L_0x00d9
            java.lang.String r15 = "signature is invalid. Not installing."
            android.util.Log.d(r11, r15)
            com.gabb.packageupdater.domain.packagemanagement.InvalidSignatureException r15 = new com.gabb.packageupdater.domain.packagemanagement.InvalidSignatureException
            r15.<init>()
            java.lang.Exception r15 = (java.lang.Exception) r15
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r13 = r13.finishWithError(r14, r15)
            return r13
        L_0x00d9:
            java.lang.String r1 = "attempting install of "
            java.lang.String r2 = r14.getPackageName()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.String r1 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r2)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            android.util.Log.d(r11, r1)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            com.gabb.packageupdater.notifications.Notifier r1 = r13.notifier     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.String r2 = r14.getPackageName()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r1.notifyUserOfInstallingUpdates(r2)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            com.gabb.packageupdater.sdk.UpdateCallbacks r1 = r13.callbacks     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            if (r1 != 0) goto L_0x00f4
            goto L_0x00f7
        L_0x00f4:
            r1.onInstalling()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
        L_0x00f7:
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler r1 = r13.pacman     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            com.gabb.packageupdater.data.resultentities.AppInstallInfo r2 = new com.gabb.packageupdater.data.resultentities.AppInstallInfo     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.String r3 = r14.getPackageName()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            android.net.Uri r15 = android.net.Uri.parse(r15)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.String r4 = "parse(this)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r4)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r2.<init>(r3, r15)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r0.L$0 = r13     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r0.L$1 = r14     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r0.label = r10     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.Object r15 = r1.install(r2, r0)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            if (r15 != r7) goto L_0x0118
            return r7
        L_0x0118:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            boolean r15 = r15.booleanValue()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r1.<init>()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.String r2 = "install of "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.String r2 = r14.getPackageName()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.String r2 = " success="
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            if (r15 == 0) goto L_0x013b
            r2 = r12
            goto L_0x013c
        L_0x013b:
            r2 = r8
        L_0x013c:
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            android.util.Log.d(r11, r1)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            com.gabb.packageupdater.repository.AppRepository r1 = r13.repository     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.String r2 = r14.getPackageName()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r0.L$0 = r13     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r0.L$1 = r14     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r0.Z$0 = r15     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            r0.label = r9     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            java.lang.Object r0 = r1.removeApp(r2, r0)     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            if (r0 != r7) goto L_0x015c
            return r7
        L_0x015c:
            r0 = r13
            r13 = r15
        L_0x015e:
            com.gabb.packageupdater.sdk.UpdateCallbacks r15 = r0.callbacks     // Catch:{ Exception -> 0x0041 }
            if (r15 != 0) goto L_0x0163
            goto L_0x0166
        L_0x0163:
            r15.onSuccess()     // Catch:{ Exception -> 0x0041 }
        L_0x0166:
            if (r13 == 0) goto L_0x016b
            r0.addToUpdatedApps(r14)     // Catch:{ Exception -> 0x0041 }
        L_0x016b:
            com.mixpanel.android.mpmetrics.MixpanelAPI r1 = r0.mixpanelAPI     // Catch:{ Exception -> 0x0041 }
            java.lang.String r2 = r0.eventName     // Catch:{ Exception -> 0x0041 }
            java.lang.String r3 = r14.getAppName()     // Catch:{ Exception -> 0x0041 }
            r4 = 0
            r5 = 4
            r6 = 0
            com.gabb.packageupdater.util.MixpanelExtensionsKt.trackSuccess$default(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0041 }
            com.gabb.packageupdater.work.InstallerWorker$TaskResult$Success r15 = new com.gabb.packageupdater.work.InstallerWorker$TaskResult$Success     // Catch:{ Exception -> 0x0041 }
            if (r13 == 0) goto L_0x017e
            r8 = r12
        L_0x017e:
            java.lang.Boolean r13 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)     // Catch:{ Exception -> 0x0041 }
            r15.<init>(r13)     // Catch:{ Exception -> 0x0041 }
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r15 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r15     // Catch:{ Exception -> 0x0041 }
            goto L_0x018c
        L_0x0188:
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r15 = r0.finishWithError(r14, r13)     // Catch:{ all -> 0x003e }
        L_0x018c:
            com.gabb.packageupdater.notifications.Notifier r13 = r0.notifier
            java.lang.String r14 = r14.getPackageName()
            r13.cancelNotification(r14)
            return r15
        L_0x0196:
            com.gabb.packageupdater.notifications.Notifier r15 = r0.notifier
            java.lang.String r14 = r14.getPackageName()
            r15.cancelNotification(r14)
            throw r13
        L_0x01a0:
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r15 = r13.finishWithError(r14, r15)     // Catch:{ all -> 0x0071 }
            com.gabb.packageupdater.notifications.Notifier r13 = r13.notifier
            java.lang.String r14 = r14.getPackageName()
            r13.cancelNotification(r14)
            return r15
        L_0x01ae:
            com.gabb.packageupdater.notifications.Notifier r13 = r13.notifier
            java.lang.String r14 = r14.getPackageName()
            r13.cancelNotification(r14)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.InstallerWorker.updateApp(com.gabb.data.entities.App, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void addToUpdatedApps(App app) {
        com.gabb.packageupdater.api.App app2 = new com.gabb.packageupdater.api.App(app.getAppName(), app.getVersionCode(), app.getVersion(), app.getReleasedAt(), app.getEnabled());
        this.updatedApps.getApps().removeIf(new InstallerWorker$$ExternalSyntheticLambda0(app2));
        this.updatedApps.getApps().add(app2);
    }

    /* access modifiers changed from: private */
    /* renamed from: addToUpdatedApps$lambda-1  reason: not valid java name */
    public static final boolean m171addToUpdatedApps$lambda1(com.gabb.packageupdater.api.App app, com.gabb.packageupdater.api.App app2) {
        Intrinsics.checkNotNullParameter(app, "$updatedApp");
        Intrinsics.checkNotNullParameter(app2, "it");
        return Intrinsics.areEqual((Object) app2.getAppName(), (Object) app.getAppName());
    }

    private final TaskResult<Boolean> finishWithError(App app, Exception exc) {
        Log.d("updater", Intrinsics.stringPlus("finishWithError ", exc.getMessage()));
        this.notifier.cancelNotification(app.getPackageName());
        MixpanelExtensionsKt.trackFailure(this.mixpanelAPI, this.eventName, app.getAppName(), exc.getMessage());
        UpdateCallbacks updateCallbacks = this.callbacks;
        if (updateCallbacks != null) {
            updateCallbacks.onError(exc);
        }
        return new TaskResult.Failed<>(exc);
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002:\u0002\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult;", "T", "", "()V", "Failed", "Success", "Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult$Success;", "Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult$Failed;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: InstallerWorker.kt */
    public static abstract class TaskResult<T> {
        public /* synthetic */ TaskResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0002HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult$Success;", "T", "Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: InstallerWorker.kt */
        public static final class Success<T> extends TaskResult<T> {
            private final T data;

            public static /* synthetic */ Success copy$default(Success success, T t, int i, Object obj) {
                if ((i & 1) != 0) {
                    t = success.data;
                }
                return success.copy(t);
            }

            public final T component1() {
                return this.data;
            }

            public final Success<T> copy(T t) {
                return new Success<>(t);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Success) && Intrinsics.areEqual((Object) this.data, (Object) ((Success) obj).data);
            }

            public int hashCode() {
                T t = this.data;
                if (t == null) {
                    return 0;
                }
                return t.hashCode();
            }

            public String toString() {
                return "Success(data=" + this.data + ')';
            }

            public Success(T t) {
                super((DefaultConstructorMarker) null);
                this.data = t;
            }

            public final T getData() {
                return this.data;
            }
        }

        private TaskResult() {
        }

        @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult$Failed;", "Nothing", "Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult;", "throwable", "", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: InstallerWorker.kt */
        public static final class Failed<Nothing> extends TaskResult<Nothing> {
            private final Throwable throwable;

            public static /* synthetic */ Failed copy$default(Failed failed, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    th = failed.throwable;
                }
                return failed.copy(th);
            }

            public final Throwable component1() {
                return this.throwable;
            }

            public final Failed<Nothing> copy(Throwable th) {
                Intrinsics.checkNotNullParameter(th, "throwable");
                return new Failed<>(th);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Failed) && Intrinsics.areEqual((Object) this.throwable, (Object) ((Failed) obj).throwable);
            }

            public int hashCode() {
                return this.throwable.hashCode();
            }

            public String toString() {
                return "Failed(throwable=" + this.throwable + ')';
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Failed(Throwable th) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(th, "throwable");
                this.throwable = th;
            }

            public final Throwable getThrowable() {
                return this.throwable;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean hasWifiConnection() {
        NetworkCapabilities networkCapabilities;
        Network activeNetwork = this.connectivity.getActiveNetwork();
        if (activeNetwork == null || (networkCapabilities = this.connectivity.getNetworkCapabilities(activeNetwork)) == null || !networkCapabilities.hasCapability(12)) {
            return false;
        }
        return networkCapabilities.hasTransport(1);
    }
}
