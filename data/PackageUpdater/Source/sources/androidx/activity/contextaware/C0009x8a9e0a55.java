package androidx.activity.contextaware;

import android.content.Context;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006¸\u0006\u0000"}, mo20735d2 = {"androidx/activity/contextaware/ContextAwareKt$withContextAvailable$2$listener$1", "Landroidx/activity/contextaware/OnContextAvailableListener;", "onContextAvailable", "", "context", "Landroid/content/Context;", "activity-ktx_release"}, mo20736k = 1, mo20737mv = {1, 4, 1})
/* renamed from: androidx.activity.contextaware.ContextAwareKt$withContextAvailable$$inlined$suspendCancellableCoroutine$lambda$1 */
/* compiled from: ContextAware.kt */
public final class C0009x8a9e0a55 implements OnContextAvailableListener {
    final /* synthetic */ CancellableContinuation $co;
    final /* synthetic */ Function1 $onContextAvailable$inlined;
    final /* synthetic */ ContextAware $this_withContextAvailable$inlined;

    public C0009x8a9e0a55(CancellableContinuation cancellableContinuation, ContextAware contextAware, Function1 function1) {
        this.$co = cancellableContinuation;
        this.$this_withContextAvailable$inlined = contextAware;
        this.$onContextAvailable$inlined = function1;
    }

    public void onContextAvailable(Context context) {
        Object obj;
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        CancellableContinuation cancellableContinuation = this.$co;
        try {
            Result.Companion companion = Result.Companion;
            C0009x8a9e0a55 contextAwareKt$withContextAvailable$$inlined$suspendCancellableCoroutine$lambda$1 = this;
            obj = Result.m176constructorimpl(this.$onContextAvailable$inlined.invoke(context));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m176constructorimpl(ResultKt.createFailure(th));
        }
        cancellableContinuation.resumeWith(obj);
    }
}
