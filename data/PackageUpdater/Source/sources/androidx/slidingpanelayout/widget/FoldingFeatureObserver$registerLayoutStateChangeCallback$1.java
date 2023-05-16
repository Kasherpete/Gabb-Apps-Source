package androidx.slidingpanelayout.widget;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1", mo21449f = "FoldingFeatureObserver.kt", mo21450i = {}, mo21451l = {97}, mo21452m = "invokeSuspend", mo21453n = {}, mo21454s = {})
/* compiled from: FoldingFeatureObserver.kt */
final class FoldingFeatureObserver$registerLayoutStateChangeCallback$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    int label;
    final /* synthetic */ FoldingFeatureObserver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FoldingFeatureObserver$registerLayoutStateChangeCallback$1(FoldingFeatureObserver foldingFeatureObserver, Activity activity, Continuation<? super FoldingFeatureObserver$registerLayoutStateChangeCallback$1> continuation) {
        super(2, continuation);
        this.this$0 = foldingFeatureObserver;
        this.$activity = activity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FoldingFeatureObserver$registerLayoutStateChangeCallback$1(this.this$0, this.$activity, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FoldingFeatureObserver$registerLayoutStateChangeCallback$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (FlowKt.distinctUntilChanged(new C0653x48a494f1(this.this$0.windowInfoTracker.windowLayoutInfo(this.$activity), this.this$0)).collect(new C0652x96081b11(this.this$0), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
