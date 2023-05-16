package kotlinx.coroutines.test.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DefaultExecutorKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.test.TestCoroutineScheduler;
import kotlinx.coroutines.test.TestDispatcher;

@Metadata(mo20734d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \"2\u00020\u00012\u00020\u0002:\u0002\"#B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001c\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014H\u0016J\u001c\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014H\u0016J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u0012\u001a\u00060\u0013j\u0002`\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0006\u0010\u001c\u001a\u00020\u000fJ\u001e\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001fH\u0016J\u000e\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u0004R\u0014\u0010\u0006\u001a\u00020\u00028BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u00018VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, mo20735d2 = {"Lkotlinx/coroutines/test/internal/TestMainDispatcher;", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "delegate", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "delay", "getDelay", "()Lkotlinx/coroutines/Delay;", "Lkotlinx/coroutines/test/internal/TestMainDispatcher$NonConcurrentlyModifiable;", "immediate", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "mainDispatcher", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchYield", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "isDispatchNeeded", "", "resetDispatcher", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "setDispatcher", "dispatcher", "Companion", "NonConcurrentlyModifiable", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestMainDispatcher.kt */
public final class TestMainDispatcher extends MainCoroutineDispatcher implements Delay {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public NonConcurrentlyModifiable<CoroutineDispatcher> delegate;
    private final CoroutineDispatcher mainDispatcher;

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long j, Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, j, continuation);
    }

    public TestMainDispatcher(CoroutineDispatcher coroutineDispatcher) {
        this.mainDispatcher = coroutineDispatcher;
        this.delegate = new NonConcurrentlyModifiable<>(coroutineDispatcher, "Dispatchers.Main");
    }

    private final Delay getDelay() {
        CoroutineDispatcher value = this.delegate.getValue();
        Delay delay = value instanceof Delay ? (Delay) value : null;
        return delay == null ? DefaultExecutorKt.getDefaultDelay() : delay;
    }

    public MainCoroutineDispatcher getImmediate() {
        MainCoroutineDispatcher immediate;
        CoroutineDispatcher value = this.delegate.getValue();
        MainCoroutineDispatcher mainCoroutineDispatcher = value instanceof MainCoroutineDispatcher ? (MainCoroutineDispatcher) value : null;
        return (mainCoroutineDispatcher == null || (immediate = mainCoroutineDispatcher.getImmediate()) == null) ? this : immediate;
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        this.delegate.getValue().dispatch(coroutineContext, runnable);
    }

    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        return this.delegate.getValue().isDispatchNeeded(coroutineContext);
    }

    public void dispatchYield(CoroutineContext coroutineContext, Runnable runnable) {
        this.delegate.getValue().dispatchYield(coroutineContext, runnable);
    }

    public final void setDispatcher(CoroutineDispatcher coroutineDispatcher) {
        this.delegate.setValue(coroutineDispatcher);
    }

    public final void resetDispatcher() {
        this.delegate.setValue(this.mainDispatcher);
    }

    public void scheduleResumeAfterDelay(long j, CancellableContinuation<? super Unit> cancellableContinuation) {
        getDelay().scheduleResumeAfterDelay(j, cancellableContinuation);
    }

    public DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        return getDelay().invokeOnTimeout(j, runnable, coroutineContext);
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048@X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8@X\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, mo20735d2 = {"Lkotlinx/coroutines/test/internal/TestMainDispatcher$Companion;", "", "()V", "currentTestDispatcher", "Lkotlinx/coroutines/test/TestDispatcher;", "getCurrentTestDispatcher$kotlinx_coroutines_test", "()Lkotlinx/coroutines/test/TestDispatcher;", "currentTestScheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "getCurrentTestScheduler$kotlinx_coroutines_test", "()Lkotlinx/coroutines/test/TestCoroutineScheduler;", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TestMainDispatcher.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TestDispatcher getCurrentTestDispatcher$kotlinx_coroutines_test() {
            NonConcurrentlyModifiable access$getDelegate$p;
            MainCoroutineDispatcher main = Dispatchers.getMain();
            TestMainDispatcher testMainDispatcher = main instanceof TestMainDispatcher ? (TestMainDispatcher) main : null;
            AbstractCoroutineContextElement abstractCoroutineContextElement = (testMainDispatcher == null || (access$getDelegate$p = testMainDispatcher.delegate) == null) ? null : (CoroutineDispatcher) access$getDelegate$p.getValue();
            if (abstractCoroutineContextElement instanceof TestDispatcher) {
                return (TestDispatcher) abstractCoroutineContextElement;
            }
            return null;
        }

        public final TestCoroutineScheduler getCurrentTestScheduler$kotlinx_coroutines_test() {
            TestDispatcher currentTestDispatcher$kotlinx_coroutines_test = getCurrentTestDispatcher$kotlinx_coroutines_test();
            if (currentTestDispatcher$kotlinx_coroutines_test != null) {
                return currentTestDispatcher$kotlinx_coroutines_test.getScheduler();
            }
            return null;
        }
    }

    @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\t\u001a\u00060\u0007j\u0002`\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00060\u0007j\u0002`\bH\u0002¢\u0006\u0004\b\u000b\u0010\nR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\fR$\u0010\r\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u00008F@FX\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, mo20735d2 = {"Lkotlinx/coroutines/test/internal/TestMainDispatcher$NonConcurrentlyModifiable;", "T", "initialValue", "", "name", "<init>", "(Ljava/lang/Object;Ljava/lang/String;)V", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "concurrentRW", "()Ljava/lang/IllegalStateException;", "concurrentWW", "Ljava/lang/String;", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "kotlinx-coroutines-test", ""}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TestMainDispatcher.kt */
    private static final class NonConcurrentlyModifiable<T> {
        private static final /* synthetic */ AtomicReferenceFieldUpdater exceptionWhenReading$FU;
        private static final /* synthetic */ AtomicIntegerFieldUpdater isWriting$FU;
        private static final /* synthetic */ AtomicIntegerFieldUpdater readers$FU;
        private volatile /* synthetic */ Object _value;
        private volatile /* synthetic */ Object exceptionWhenReading = null;
        private volatile /* synthetic */ int isWriting = 0;
        private final String name;
        private volatile /* synthetic */ int readers = 0;

        static {
            Class<NonConcurrentlyModifiable> cls = NonConcurrentlyModifiable.class;
            readers$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "readers");
            isWriting$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "isWriting");
            exceptionWhenReading$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "exceptionWhenReading");
        }

        public NonConcurrentlyModifiable(T t, String str) {
            this.name = str;
            this._value = t;
        }

        private final IllegalStateException concurrentWW() {
            return new IllegalStateException(this.name + " is modified concurrently");
        }

        private final IllegalStateException concurrentRW() {
            return new IllegalStateException(this.name + " is used concurrently with setting it");
        }

        public final T getValue() {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = readers$FU;
            atomicIntegerFieldUpdater.incrementAndGet(this);
            if (this.isWriting != 0) {
                this.exceptionWhenReading = concurrentRW();
            }
            T t = this._value;
            atomicIntegerFieldUpdater.decrementAndGet(this);
            return t;
        }

        public final void setValue(T t) {
            Throwable th = (Throwable) exceptionWhenReading$FU.getAndSet(this, (Object) null);
            if (th != null) {
                throw th;
            } else if (this.readers != 0) {
                throw concurrentRW();
            } else if (isWriting$FU.compareAndSet(this, 0, 1)) {
                this._value = t;
                this.isWriting = 0;
                if (this.readers != 0) {
                    throw concurrentRW();
                }
            } else {
                throw concurrentWW();
            }
        }
    }
}
