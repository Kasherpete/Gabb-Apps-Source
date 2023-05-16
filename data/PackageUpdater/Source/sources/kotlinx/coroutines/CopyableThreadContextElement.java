package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.ThreadContextElement;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, mo20735d2 = {"Lkotlinx/coroutines/CopyableThreadContextElement;", "S", "Lkotlinx/coroutines/ThreadContextElement;", "copyForChild", "mergeForChild", "Lkotlin/coroutines/CoroutineContext;", "overwritingElement", "Lkotlin/coroutines/CoroutineContext$Element;", "kotlinx-coroutines-core"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ThreadContextElement.kt */
public interface CopyableThreadContextElement<S> extends ThreadContextElement<S> {
    CopyableThreadContextElement<S> copyForChild();

    CoroutineContext mergeForChild(CoroutineContext.Element element);

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ThreadContextElement.kt */
    public static final class DefaultImpls {
        public static <S, R> R fold(CopyableThreadContextElement<S> copyableThreadContextElement, R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return ThreadContextElement.DefaultImpls.fold(copyableThreadContextElement, r, function2);
        }

        public static <S, E extends CoroutineContext.Element> E get(CopyableThreadContextElement<S> copyableThreadContextElement, CoroutineContext.Key<E> key) {
            return ThreadContextElement.DefaultImpls.get(copyableThreadContextElement, key);
        }

        public static <S> CoroutineContext minusKey(CopyableThreadContextElement<S> copyableThreadContextElement, CoroutineContext.Key<?> key) {
            return ThreadContextElement.DefaultImpls.minusKey(copyableThreadContextElement, key);
        }

        public static <S> CoroutineContext plus(CopyableThreadContextElement<S> copyableThreadContextElement, CoroutineContext coroutineContext) {
            return ThreadContextElement.DefaultImpls.plus(copyableThreadContextElement, coroutineContext);
        }
    }
}
