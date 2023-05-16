package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1", mo21449f = "Combine.kt", mo21450i = {}, mo21451l = {131}, mo21452m = "emit", mo21453n = {}, mo21454s = {})
/* compiled from: Combine.kt */
final class CombineKt$zipImpl$1$1$2$1$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CombineKt$zipImpl$1$1.C14432.C14441<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CombineKt$zipImpl$1$1$2$1$emit$1(CombineKt$zipImpl$1$1.C14432.C14441<? super T> r1, Continuation<? super CombineKt$zipImpl$1$1$2$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = r1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
