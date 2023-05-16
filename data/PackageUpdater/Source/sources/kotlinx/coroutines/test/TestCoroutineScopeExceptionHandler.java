package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineExceptionHandler;

@Metadata(mo20734d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bb\u0018\u00002\u00020\u0001¨\u0006\u0002"}, mo20735d2 = {"Lkotlinx/coroutines/test/TestCoroutineScopeExceptionHandler;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScope.kt */
interface TestCoroutineScopeExceptionHandler extends CoroutineExceptionHandler {

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TestCoroutineScope.kt */
    public static final class DefaultImpls {
        public static <R> R fold(TestCoroutineScopeExceptionHandler testCoroutineScopeExceptionHandler, R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return CoroutineExceptionHandler.DefaultImpls.fold(testCoroutineScopeExceptionHandler, r, function2);
        }

        public static <E extends CoroutineContext.Element> E get(TestCoroutineScopeExceptionHandler testCoroutineScopeExceptionHandler, CoroutineContext.Key<E> key) {
            return CoroutineExceptionHandler.DefaultImpls.get(testCoroutineScopeExceptionHandler, key);
        }

        public static CoroutineContext minusKey(TestCoroutineScopeExceptionHandler testCoroutineScopeExceptionHandler, CoroutineContext.Key<?> key) {
            return CoroutineExceptionHandler.DefaultImpls.minusKey(testCoroutineScopeExceptionHandler, key);
        }

        public static CoroutineContext plus(TestCoroutineScopeExceptionHandler testCoroutineScopeExceptionHandler, CoroutineContext coroutineContext) {
            return CoroutineExceptionHandler.DefaultImpls.plus(testCoroutineScopeExceptionHandler, coroutineContext);
        }
    }
}
