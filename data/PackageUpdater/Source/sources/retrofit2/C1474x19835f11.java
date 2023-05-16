package retrofit2;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, mo20735d2 = {"<anonymous>", "", "T", "", "it", "", "invoke", "retrofit2/KotlinExtensions$await$4$1"}, mo20736k = 3, mo20737mv = {1, 1, 15})
/* renamed from: retrofit2.KotlinExtensions$await$$inlined$suspendCancellableCoroutine$lambda$2 */
/* compiled from: KotlinExtensions.kt */
final class C1474x19835f11 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ Call $this_await$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1474x19835f11(Call call) {
        super(1);
        this.$this_await$inlined = call;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.$this_await$inlined.cancel();
    }
}
