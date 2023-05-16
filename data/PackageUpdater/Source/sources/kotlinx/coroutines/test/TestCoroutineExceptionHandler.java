package kotlinx.coroutines.test;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineExceptionHandlerImplKt;

@Metadata(mo20734d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bj\u0002`\fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, mo20735d2 = {"Lkotlinx/coroutines/test/TestCoroutineExceptionHandler;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlinx/coroutines/test/UncaughtExceptionCaptor;", "()V", "_coroutinesCleanedUp", "", "_exceptions", "", "", "_lock", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "uncaughtExceptions", "", "getUncaughtExceptions", "()Ljava/util/List;", "cleanupTestCoroutines", "", "handleException", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated for removal without a replacement. It may be to define one's own `CoroutineExceptionHandler` if you just need to handle 'uncaught exceptions without a special `TestCoroutineScope` integration.")
/* compiled from: TestCoroutineExceptionHandler.kt */
public final class TestCoroutineExceptionHandler extends AbstractCoroutineContextElement implements CoroutineExceptionHandler, UncaughtExceptionCaptor {
    private boolean _coroutinesCleanedUp;
    private final List<Throwable> _exceptions = new ArrayList();
    private final Object _lock = new Object();

    public TestCoroutineExceptionHandler() {
        super(CoroutineExceptionHandler.Key);
    }

    public void handleException(CoroutineContext coroutineContext, Throwable th) {
        synchronized (this._lock) {
            if (this._coroutinesCleanedUp) {
                CoroutineExceptionHandlerImplKt.handleCoroutineExceptionImpl(coroutineContext, th);
            }
            this._exceptions.add(th);
            Unit unit = Unit.INSTANCE;
        }
    }

    public List<Throwable> getUncaughtExceptions() {
        List<Throwable> list;
        synchronized (this._lock) {
            list = CollectionsKt.toList(this._exceptions);
        }
        return list;
    }

    public void cleanupTestCoroutines() {
        synchronized (this._lock) {
            this._coroutinesCleanedUp = true;
            Throwable th = (Throwable) CollectionsKt.firstOrNull(this._exceptions);
            if (th != null) {
                for (Throwable printStackTrace : CollectionsKt.drop(this._exceptions, 1)) {
                    printStackTrace.printStackTrace();
                }
                throw th;
            }
        }
    }
}
