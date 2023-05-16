package androidx.fragment.app;

import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo20735d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelProvider$Factory;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, mo20736k = 3, mo20737mv = {1, 4, 1})
/* compiled from: FragmentViewModelLazy.kt */
final class FragmentViewModelLazyKt$createViewModelLazy$factoryPromise$1 extends Lambda implements Function0<ViewModelProvider.Factory> {
    final /* synthetic */ Fragment $this_createViewModelLazy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FragmentViewModelLazyKt$createViewModelLazy$factoryPromise$1(Fragment fragment) {
        super(0);
        this.$this_createViewModelLazy = fragment;
    }

    public final ViewModelProvider.Factory invoke() {
        return this.$this_createViewModelLazy.getDefaultViewModelProviderFactory();
    }
}
