package kotlinx.coroutines.test;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0007\u001a\u00020\bH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, mo20735d2 = {"Lkotlinx/coroutines/test/UncaughtExceptionCaptor;", "", "uncaughtExceptions", "", "", "getUncaughtExceptions", "()Ljava/util/List;", "cleanupTestCoroutines", "", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated for removal without a replacement. Consider whether the default mechanism of handling uncaught exceptions is sufficient. If not, try writing your own `CoroutineExceptionHandler` and please report your use case at https://github.com/Kotlin/kotlinx.coroutines/issues.")
/* compiled from: TestCoroutineExceptionHandler.kt */
public interface UncaughtExceptionCaptor {
    void cleanupTestCoroutines();

    List<Throwable> getUncaughtExceptions();
}
