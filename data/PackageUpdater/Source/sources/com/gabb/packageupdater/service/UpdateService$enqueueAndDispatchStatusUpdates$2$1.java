package com.gabb.packageupdater.service;

import androidx.lifecycle.LiveData;
import androidx.work.WorkInfo;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "com.gabb.packageupdater.service.UpdateService$enqueueAndDispatchStatusUpdates$2$1", mo21449f = "UpdateService.kt", mo21450i = {}, mo21451l = {}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: UpdateService.kt */
final class UpdateService$enqueueAndDispatchStatusUpdates$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LiveData<WorkInfo> $liveData;
    final /* synthetic */ UpdateService$workInfoObserver$1 $observer;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UpdateService$enqueueAndDispatchStatusUpdates$2$1(LiveData<WorkInfo> liveData, UpdateService$workInfoObserver$1 updateService$workInfoObserver$1, Continuation<? super UpdateService$enqueueAndDispatchStatusUpdates$2$1> continuation) {
        super(2, continuation);
        this.$liveData = liveData;
        this.$observer = updateService$workInfoObserver$1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdateService$enqueueAndDispatchStatusUpdates$2$1(this.$liveData, this.$observer, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateService$enqueueAndDispatchStatusUpdates$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$liveData.removeObserver(this.$observer);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
