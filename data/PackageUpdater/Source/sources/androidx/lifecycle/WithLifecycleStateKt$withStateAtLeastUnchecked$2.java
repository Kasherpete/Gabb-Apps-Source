package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\n"}, mo20735d2 = {"<anonymous>", "R"}, mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: WithLifecycleState.kt */
public final class WithLifecycleStateKt$withStateAtLeastUnchecked$2 extends Lambda implements Function0<R> {
    final /* synthetic */ Function0<R> $block;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WithLifecycleStateKt$withStateAtLeastUnchecked$2(Function0<? extends R> function0) {
        super(0);
        this.$block = function0;
    }

    public final R invoke() {
        return this.$block.invoke();
    }
}
