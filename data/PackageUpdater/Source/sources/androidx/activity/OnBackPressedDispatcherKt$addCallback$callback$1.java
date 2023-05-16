package androidx.activity;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo20735d2 = {"androidx/activity/OnBackPressedDispatcherKt$addCallback$callback$1", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "activity-ktx_release"}, mo20736k = 1, mo20737mv = {1, 4, 1})
/* compiled from: OnBackPressedDispatcher.kt */
public final class OnBackPressedDispatcherKt$addCallback$callback$1 extends OnBackPressedCallback {
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Function1 $onBackPressed;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OnBackPressedDispatcherKt$addCallback$callback$1(Function1 function1, boolean z, boolean z2) {
        super(z2);
        this.$onBackPressed = function1;
        this.$enabled = z;
    }

    public void handleOnBackPressed() {
        this.$onBackPressed.invoke(this);
    }
}
