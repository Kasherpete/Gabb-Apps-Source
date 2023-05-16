package com.gabb.packageupdater.work;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkRequest;
import androidx.work.WorkerParameters;
import com.gabb.data.entities.App;
import com.gabb.packageupdater.domain.packagemanagement.PackageHandler;
import com.gabb.packageupdater.model.UpdatedApps;
import com.gabb.packageupdater.notifications.Notifier;
import com.gabb.packageupdater.repository.AppRepository;
import com.gabb.packageupdater.work.InstallerWorker;
import com.google.gson.Gson;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bBK\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/SingleShotInstallWorker;", "Lcom/gabb/packageupdater/work/InstallerWorker;", "Lcom/gabb/data/entities/App;", "appContext", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "connectivity", "Landroid/net/ConnectivityManager;", "repository", "Lcom/gabb/packageupdater/repository/AppRepository;", "pacman", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageHandler;", "notifier", "Lcom/gabb/packageupdater/notifications/Notifier;", "updatedApps", "Lcom/gabb/packageupdater/model/UpdatedApps;", "mixpanelAPI", "Lcom/mixpanel/android/mpmetrics/MixpanelAPI;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Landroid/net/ConnectivityManager;Lcom/gabb/packageupdater/repository/AppRepository;Lcom/gabb/packageupdater/domain/packagemanagement/PackageHandler;Lcom/gabb/packageupdater/notifications/Notifier;Lcom/gabb/packageupdater/model/UpdatedApps;Lcom/mixpanel/android/mpmetrics/MixpanelAPI;)V", "attemptWork", "Landroidx/work/ListenableWorker$Result;", "withData", "(Lcom/gabb/data/entities/App;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadData", "Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SingleShotInstallWorker.kt */
public final class SingleShotInstallWorker extends InstallerWorker<App> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String UNIQUE_NAME = "single-shot-install";
    private final Context appContext;
    private final WorkerParameters params;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @AssistedInject
    public SingleShotInstallWorker(@Assisted Context context, @Assisted WorkerParameters workerParameters, ConnectivityManager connectivityManager, AppRepository appRepository, PackageHandler packageHandler, Notifier notifier, UpdatedApps updatedApps, MixpanelAPI mixpanelAPI) {
        super(context, workerParameters, connectivityManager, appRepository, packageHandler, notifier, updatedApps, mixpanelAPI);
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(workerParameters, "params");
        Intrinsics.checkNotNullParameter(connectivityManager, "connectivity");
        Intrinsics.checkNotNullParameter(appRepository, "repository");
        Intrinsics.checkNotNullParameter(packageHandler, "pacman");
        Intrinsics.checkNotNullParameter(notifier, "notifier");
        Intrinsics.checkNotNullParameter(updatedApps, "updatedApps");
        Intrinsics.checkNotNullParameter(mixpanelAPI, "mixpanelAPI");
        this.appContext = context;
        this.params = workerParameters;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object attemptWork(com.gabb.data.entities.App r5, kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.gabb.packageupdater.work.SingleShotInstallWorker$attemptWork$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.gabb.packageupdater.work.SingleShotInstallWorker$attemptWork$1 r0 = (com.gabb.packageupdater.work.SingleShotInstallWorker$attemptWork$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.work.SingleShotInstallWorker$attemptWork$1 r0 = new com.gabb.packageupdater.work.SingleShotInstallWorker$attemptWork$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x003e
        L_0x002a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.label = r3
            java.lang.Object r6 = r4.handleApp(r5, r3, r0)
            if (r6 != r1) goto L_0x003e
            return r1
        L_0x003e:
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r6 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r6
            boolean r4 = r6 instanceof com.gabb.packageupdater.work.InstallerWorker.TaskResult.Failed
            if (r4 == 0) goto L_0x004e
            androidx.work.ListenableWorker$Result r4 = androidx.work.ListenableWorker.Result.failure()
            java.lang.String r5 = "failure()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            return r4
        L_0x004e:
            androidx.work.ListenableWorker$Result r4 = androidx.work.ListenableWorker.Result.success()
            java.lang.String r5 = "success()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.SingleShotInstallWorker.attemptWork(com.gabb.data.entities.App, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object downloadData(Continuation<? super InstallerWorker.TaskResult<App>> continuation) {
        String string = this.params.getInputData().getString("downloadInfo");
        if (string == null) {
            return new InstallerWorker.TaskResult.Failed(new Throwable("No download info found. Bailing out"));
        }
        return new InstallerWorker.TaskResult.Success(((AppDownloadData) new Gson().fromJson(string, AppDownloadData.class)).toApp());
    }

    @Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/SingleShotInstallWorker$Companion;", "", "()V", "UNIQUE_NAME", "", "build", "Landroidx/work/OneTimeWorkRequest;", "downloadData", "Lcom/gabb/packageupdater/work/AppDownloadData;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SingleShotInstallWorker.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OneTimeWorkRequest build(AppDownloadData appDownloadData) {
            Intrinsics.checkNotNullParameter(appDownloadData, "downloadData");
            OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(SingleShotInstallWorker.class);
            Data.Builder builder2 = new Data.Builder();
            builder2.putString("downloadInfo", new Gson().toJson((Object) appDownloadData));
            WorkRequest build = ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) builder.setInputData(builder2.build())).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(SingleShotInstal…\n                .build()");
            return (OneTimeWorkRequest) build;
        }
    }
}
