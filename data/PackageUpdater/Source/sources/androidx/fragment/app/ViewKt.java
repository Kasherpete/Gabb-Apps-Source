package androidx.fragment.app;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0019\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo20735d2 = {"findFragment", "F", "Landroidx/fragment/app/Fragment;", "Landroid/view/View;", "(Landroid/view/View;)Landroidx/fragment/app/Fragment;", "fragment-ktx_release"}, mo20736k = 2, mo20737mv = {1, 4, 1})
/* compiled from: View.kt */
public final class ViewKt {
    public static final <F extends Fragment> F findFragment(View view) {
        Intrinsics.checkNotNullParameter(view, "$this$findFragment");
        F findFragment = FragmentManager.findFragment(view);
        Intrinsics.checkNotNullExpressionValue(findFragment, "FragmentManager.findFragment(this)");
        return findFragment;
    }
}
