package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo20735d2 = {"<anonymous>", "", "T", "", "invoke", "()Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScheduler.kt */
final class TestCoroutineScheduler$registerEvent$2$event$1 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ Function1<T, Boolean> $isCancelled;
    final /* synthetic */ T $marker;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestCoroutineScheduler$registerEvent$2$event$1(Function1<? super T, Boolean> function1, T t) {
        super(0);
        this.$isCancelled = function1;
        this.$marker = t;
    }

    public final Boolean invoke() {
        return this.$isCancelled.invoke(this.$marker);
    }
}
