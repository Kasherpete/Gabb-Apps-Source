package androidx.preference;

import android.view.View;
import androidx.activity.OnBackPressedCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\t*\u0001\u0000\b\n\u0018\u00002\u00020\u0001JP\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0016¨\u0006\u000f¸\u0006\u0010"}, mo20735d2 = {"androidx/core/view/ViewKt$doOnNextLayout$1", "Landroid/view/View$OnLayoutChangeListener;", "onLayoutChange", "", "view", "Landroid/view/View;", "left", "", "top", "right", "bottom", "oldLeft", "oldTop", "oldRight", "oldBottom", "core-ktx_release", "androidx/core/view/ViewKt$doOnLayout$$inlined$doOnNextLayout$1"}, mo20736k = 1, mo20737mv = {1, 6, 0})
/* renamed from: androidx.preference.PreferenceHeaderFragmentCompat$onViewCreated$$inlined$doOnLayout$1 */
/* compiled from: View.kt */
public final class C0524x85395cb4 implements View.OnLayoutChangeListener {
    final /* synthetic */ PreferenceHeaderFragmentCompat this$0;

    public C0524x85395cb4(PreferenceHeaderFragmentCompat preferenceHeaderFragmentCompat) {
        this.this$0 = preferenceHeaderFragmentCompat;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        view.removeOnLayoutChangeListener(this);
        OnBackPressedCallback access$getOnBackPressedCallback$p = this.this$0.onBackPressedCallback;
        Intrinsics.checkNotNull(access$getOnBackPressedCallback$p);
        access$getOnBackPressedCallback$p.setEnabled(this.this$0.getSlidingPaneLayout().isSlideable() && this.this$0.getSlidingPaneLayout().isOpen());
    }
}
