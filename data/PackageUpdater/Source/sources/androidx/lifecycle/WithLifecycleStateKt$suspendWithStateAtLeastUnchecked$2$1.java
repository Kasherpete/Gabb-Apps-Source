package androidx.lifecycle;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n"}, mo20735d2 = {"<anonymous>", "", "R"}, mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: WithLifecycleState.kt */
final class WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$1 implements Runnable {
    final /* synthetic */ C0429xfdb59cc4 $observer;
    final /* synthetic */ Lifecycle $this_suspendWithStateAtLeastUnchecked;

    WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$1(Lifecycle lifecycle, C0429xfdb59cc4 withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1) {
        this.$this_suspendWithStateAtLeastUnchecked = lifecycle;
        this.$observer = withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1;
    }

    public final void run() {
        this.$this_suspendWithStateAtLeastUnchecked.addObserver(this.$observer);
    }
}
