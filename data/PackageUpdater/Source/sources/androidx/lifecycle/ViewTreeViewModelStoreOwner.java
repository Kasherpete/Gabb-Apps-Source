package androidx.lifecycle;

import android.view.View;
import android.view.ViewParent;
import androidx.lifecycle.viewmodel.C0438R;

public class ViewTreeViewModelStoreOwner {
    private ViewTreeViewModelStoreOwner() {
    }

    public static void set(View view, ViewModelStoreOwner viewModelStoreOwner) {
        view.setTag(C0438R.C0439id.view_tree_view_model_store_owner, viewModelStoreOwner);
    }

    public static ViewModelStoreOwner get(View view) {
        ViewModelStoreOwner viewModelStoreOwner = (ViewModelStoreOwner) view.getTag(C0438R.C0439id.view_tree_view_model_store_owner);
        if (viewModelStoreOwner != null) {
            return viewModelStoreOwner;
        }
        ViewParent parent = view.getParent();
        while (viewModelStoreOwner == null && (parent instanceof View)) {
            View view2 = (View) parent;
            viewModelStoreOwner = (ViewModelStoreOwner) view2.getTag(C0438R.C0439id.view_tree_view_model_store_owner);
            parent = view2.getParent();
        }
        return viewModelStoreOwner;
    }
}
