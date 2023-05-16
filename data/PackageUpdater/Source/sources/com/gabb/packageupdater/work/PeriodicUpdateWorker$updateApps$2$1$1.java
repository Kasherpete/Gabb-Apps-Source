package com.gabb.packageupdater.work;

import com.gabb.data.entities.App;
import com.gabb.packageupdater.work.InstallerWorker;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, mo20735d2 = {"<anonymous>", "Lcom/gabb/packageupdater/work/InstallerWorker$TaskResult;", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.work.PeriodicUpdateWorker$updateApps$2$1$1", mo21449f = "PeriodicUpdateWorker.kt", mo21450i = {}, mo21451l = {83}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: PeriodicUpdateWorker.kt */
final class PeriodicUpdateWorker$updateApps$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super InstallerWorker.TaskResult<Boolean>>, Object> {
    final /* synthetic */ App $it;
    int label;
    final /* synthetic */ PeriodicUpdateWorker this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PeriodicUpdateWorker$updateApps$2$1$1(PeriodicUpdateWorker periodicUpdateWorker, App app, Continuation<? super PeriodicUpdateWorker$updateApps$2$1$1> continuation) {
        super(2, continuation);
        this.this$0 = periodicUpdateWorker;
        this.$it = app;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PeriodicUpdateWorker$updateApps$2$1$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super InstallerWorker.TaskResult<Boolean>> continuation) {
        return ((PeriodicUpdateWorker$updateApps$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PeriodicUpdateWorker periodicUpdateWorker = this.this$0;
            App app = this.$it;
            this.label = 1;
            obj = periodicUpdateWorker.handleApp(app, app.getEnabled(), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
