package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo20735d2 = {"<anonymous>", "", "it", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "invoke", "(Ljava/lang/Runnable;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineDispatcher.kt */
final class TestCoroutineDispatcher$post$1 extends Lambda implements Function1<Runnable, Boolean> {
    public static final TestCoroutineDispatcher$post$1 INSTANCE = new TestCoroutineDispatcher$post$1();

    TestCoroutineDispatcher$post$1() {
        super(1);
    }

    public final Boolean invoke(Runnable runnable) {
        return false;
    }
}
