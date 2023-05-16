package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(mo20734d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00018VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, mo20735d2 = {"Lkotlinx/coroutines/test/BackgroundWork;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlin/coroutines/CoroutineContext$Element;", "()V", "key", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "toString", "", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScheduler.kt */
public final class BackgroundWork implements CoroutineContext.Key<BackgroundWork>, CoroutineContext.Element {
    public static final BackgroundWork INSTANCE = new BackgroundWork();

    public String toString() {
        return "BackgroundWork";
    }

    private BackgroundWork() {
    }

    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return CoroutineContext.Element.DefaultImpls.fold(this, r, function2);
    }

    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return CoroutineContext.Element.DefaultImpls.get(this, key);
    }

    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return CoroutineContext.Element.DefaultImpls.minusKey(this, key);
    }

    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.Element.DefaultImpls.plus(this, coroutineContext);
    }

    public CoroutineContext.Key<?> getKey() {
        return this;
    }
}
