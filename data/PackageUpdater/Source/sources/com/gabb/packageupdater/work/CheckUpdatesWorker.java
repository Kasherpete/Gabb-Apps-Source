package com.gabb.packageupdater.work;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import androidx.work.WorkerParameters;
import com.gabb.packageupdater.notifications.Notifier;
import com.gabb.packageupdater.repository.AppRepository;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB+\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0011\u0010\u000b\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/CheckUpdatesWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "notifier", "Lcom/gabb/packageupdater/notifications/Notifier;", "appRepository", "Lcom/gabb/packageupdater/repository/AppRepository;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lcom/gabb/packageupdater/notifications/Notifier;Lcom/gabb/packageupdater/repository/AppRepository;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CheckUpdatesWorker.kt */
public final class CheckUpdatesWorker extends CoroutineWorker {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "check updates";
    public static final String UNIQUE_NAME = "updates-required-check";
    private final Context appContext;
    private final AppRepository appRepository;
    private final Notifier notifier;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @AssistedInject
    public CheckUpdatesWorker(@Assisted Context context, @Assisted WorkerParameters workerParameters, Notifier notifier2, AppRepository appRepository2) {
        super(context, workerParameters);
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(workerParameters, "workerParams");
        Intrinsics.checkNotNullParameter(notifier2, "notifier");
        Intrinsics.checkNotNullParameter(appRepository2, "appRepository");
        this.appContext = context;
        this.notifier = notifier2;
        this.appRepository = appRepository2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doWork(kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.gabb.packageupdater.work.CheckUpdatesWorker$doWork$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.gabb.packageupdater.work.CheckUpdatesWorker$doWork$1 r0 = (com.gabb.packageupdater.work.CheckUpdatesWorker$doWork$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.work.CheckUpdatesWorker$doWork$1 r0 = new com.gabb.packageupdater.work.CheckUpdatesWorker$doWork$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            java.lang.String r4 = "check updates"
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r5 = r0.L$0
            com.gabb.packageupdater.work.CheckUpdatesWorker r5 = (com.gabb.packageupdater.work.CheckUpdatesWorker) r5
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.Result r6 = (kotlin.Result) r6
            java.lang.Object r6 = r6.m185unboximpl()
            goto L_0x0053
        L_0x0036:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r6 = "CheckUpdates doWork() called"
            android.util.Log.i(r4, r6)
            com.gabb.packageupdater.repository.AppRepository r6 = r5.appRepository
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r6.m169checkAppsNeedUpdateIoAF18A(r0)
            if (r6 != r1) goto L_0x0053
            return r1
        L_0x0053:
            boolean r0 = kotlin.Result.m182isFailureimpl(r6)
            if (r0 == 0) goto L_0x0063
            androidx.work.ListenableWorker$Result r5 = androidx.work.ListenableWorker.Result.failure()
            java.lang.String r6 = "failure()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            return r5
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r6)
            java.util.List r6 = (java.util.List) r6
            boolean r6 = r6.isEmpty()
            java.lang.String r0 = "success()"
            if (r6 == 0) goto L_0x0082
            java.lang.String r6 = "apps up to date"
            android.util.Log.i(r4, r6)
            com.gabb.packageupdater.notifications.Notifier r5 = r5.notifier
            r5.cancelAllNotifications()
            androidx.work.ListenableWorker$Result r5 = androidx.work.ListenableWorker.Result.success()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            return r5
        L_0x0082:
            java.lang.String r6 = "apps need to be updated"
            android.util.Log.i(r4, r6)
            com.gabb.packageupdater.notifications.Notifier r5 = r5.notifier
            r5.nudgeUserToDownloadUpdates()
            androidx.work.ListenableWorker$Result r5 = androidx.work.ListenableWorker.Result.success()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.CheckUpdatesWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo20735d2 = {"Lcom/gabb/packageupdater/work/CheckUpdatesWorker$Companion;", "", "()V", "TAG", "", "UNIQUE_NAME", "buildPeriodic", "Landroidx/work/PeriodicWorkRequest;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: CheckUpdatesWorker.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PeriodicWorkRequest buildPeriodic() {
            WorkRequest build = ((PeriodicWorkRequest.Builder) ((PeriodicWorkRequest.Builder) new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) CheckUpdatesWorker.class, 7, TimeUnit.DAYS).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())).setInitialDelay(15, TimeUnit.MINUTES)).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(CheckUpdatesWork…\n                .build()");
            return (PeriodicWorkRequest) build;
        }
    }
}
