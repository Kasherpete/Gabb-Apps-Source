package retrofit2;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, mo20735d2 = {"<anonymous>", "", "T", "it", "", "invoke", "retrofit2/KotlinExtensions$awaitResponse$2$1"}, mo20736k = 3, mo20737mv = {1, 1, 15})
/* renamed from: retrofit2.KotlinExtensions$awaitResponse$$inlined$suspendCancellableCoroutine$lambda$1 */
/* compiled from: KotlinExtensions.kt */
final class C1475xc95e9eb1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ Call $this_awaitResponse$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1475xc95e9eb1(Call call) {
        super(1);
        this.$this_awaitResponse$inlined = call;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.$this_awaitResponse$inlined.cancel();
    }
}
