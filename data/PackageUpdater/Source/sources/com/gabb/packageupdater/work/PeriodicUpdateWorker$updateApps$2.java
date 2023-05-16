package com.gabb.packageupdater.work;

import androidx.work.ListenableWorker;
import com.gabb.data.entities.App;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$2", mo21449f = "PeriodicUpdateWorker.kt", mo21450i = {0}, mo21451l = {82}, mo21452m = "invokeSuspend", mo21453n = {"destination$iv$iv"}, mo21454s = {"L$1"})
/* compiled from: PeriodicUpdateWorker.kt */
final class PeriodicUpdateWorker$updateApps$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
    final /* synthetic */ List<App> $withData;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ PeriodicUpdateWorker this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PeriodicUpdateWorker$updateApps$2(List<App> list, PeriodicUpdateWorker periodicUpdateWorker, Continuation<? super PeriodicUpdateWorker$updateApps$2> continuation) {
        super(2, continuation);
        this.$withData = list;
        this.this$0 = periodicUpdateWorker;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PeriodicUpdateWorker$updateApps$2(this.$withData, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
        return ((PeriodicUpdateWorker$updateApps$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 != r2) goto L_0x001f
            java.lang.Object r1 = r8.L$3
            java.util.Collection r1 = (java.util.Collection) r1
            java.lang.Object r3 = r8.L$2
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r8.L$1
            java.util.Collection r4 = (java.util.Collection) r4
            java.lang.Object r5 = r8.L$0
            com.gabb.packageupdater.work.PeriodicUpdateWorker r5 = (com.gabb.packageupdater.work.PeriodicUpdateWorker) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0070
        L_0x001f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r9)
            java.util.List<com.gabb.data.entities.App> r9 = r8.$withData
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            com.gabb.packageupdater.work.PeriodicUpdateWorker r1 = r8.this$0
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r9, r4)
            r3.<init>(r4)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r9 = r9.iterator()
            r5 = r1
            r1 = r3
            r3 = r9
        L_0x0044:
            boolean r9 = r3.hasNext()
            r4 = 0
            if (r9 == 0) goto L_0x0077
            java.lang.Object r9 = r3.next()
            com.gabb.data.entities.App r9 = (com.gabb.data.entities.App) r9
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$2$1$1 r7 = new com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$2$1$1
            r7.<init>(r5, r9, r4)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8.L$0 = r5
            r8.L$1 = r1
            r8.L$2 = r3
            r8.L$3 = r1
            r8.label = r2
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r8)
            if (r9 != r0) goto L_0x006f
            return r0
        L_0x006f:
            r4 = r1
        L_0x0070:
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r9 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r9
            r1.add(r9)
            r1 = r4
            goto L_0x0044
        L_0x0077:
            java.util.List r1 = (java.util.List) r1
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r8 = r1.iterator()
        L_0x007f:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0091
            java.lang.Object r9 = r8.next()
            r0 = r9
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r0 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r0
            boolean r0 = r0 instanceof com.gabb.packageupdater.work.InstallerWorker.TaskResult.Failed
            if (r0 == 0) goto L_0x007f
            goto L_0x0092
        L_0x0091:
            r9 = r4
        L_0x0092:
            com.gabb.packageupdater.work.InstallerWorker$TaskResult r9 = (com.gabb.packageupdater.work.InstallerWorker.TaskResult) r9
            if (r9 != 0) goto L_0x0097
            goto L_0x009b
        L_0x0097:
            androidx.work.ListenableWorker$Result r4 = androidx.work.ListenableWorker.Result.failure()
        L_0x009b:
            if (r4 != 0) goto L_0x00a1
            androidx.work.ListenableWorker$Result r4 = androidx.work.ListenableWorker.Result.success()
        L_0x00a1:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
