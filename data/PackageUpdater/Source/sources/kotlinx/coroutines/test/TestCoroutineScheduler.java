package kotlinx.coroutines.test;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.time.DurationUnit;
import kotlin.time.TimeSource;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(mo20734d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0007\u0018\u0000 B2\u00020D2\u00020E:\u0001BB\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\u0002J\u001d\u0010\u000e\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0010\u0010\u0011JM\u0010 \u001a\u00020\u001d\"\b\b\u0000\u0010\u0014*\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u00192\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\u001bH\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010!\u001a\u00020\u0005H\u0007¢\u0006\u0004\b!\u0010\u0002J\u0017\u0010$\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\"\u0010#J\u001d\u0010'\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0004\b%\u0010&R*\u0010)\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u00038F@BX\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u0012\u0004\b-\u0010\u0002\u001a\u0004\b+\u0010,R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00050.8\u0002X\u0004¢\u0006\u0006\n\u0004\b/\u00100R \u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001302018\u0002X\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0018\u00106\u001a\u00060\u0013j\u0002`58\u0002X\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u001a\u0010;\u001a\b\u0012\u0004\u0012\u00020\u0005088@X\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R \u0010=\u001a\u00020<8\u0006X\u0004¢\u0006\u0012\n\u0004\b=\u0010>\u0012\u0004\bA\u0010\u0002\u001a\u0004\b?\u0010@¨\u0006C"}, mo20735d2 = {"Lkotlinx/coroutines/test/TestCoroutineScheduler;", "<init>", "()V", "", "delayTimeMillis", "", "advanceTimeBy", "(J)V", "advanceUntilIdle", "Lkotlin/Function0;", "", "condition", "advanceUntilIdleOr$kotlinx_coroutines_test", "(Lkotlin/jvm/functions/Function0;)V", "advanceUntilIdleOr", "strict", "isIdle$kotlinx_coroutines_test", "(Z)Z", "isIdle", "", "T", "Lkotlinx/coroutines/test/TestDispatcher;", "dispatcher", "timeDeltaMillis", "marker", "Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlin/Function1;", "isCancelled", "Lkotlinx/coroutines/DisposableHandle;", "registerEvent$kotlinx_coroutines_test", "(Lkotlinx/coroutines/test/TestDispatcher;JLjava/lang/Object;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "registerEvent", "runCurrent", "sendDispatchEvent$kotlinx_coroutines_test", "(Lkotlin/coroutines/CoroutineContext;)V", "sendDispatchEvent", "tryRunNextTaskUnless$kotlinx_coroutines_test", "(Lkotlin/jvm/functions/Function0;)Z", "tryRunNextTaskUnless", "<set-?>", "currentTime", "J", "getCurrentTime", "()J", "getCurrentTime$annotations", "Lkotlinx/coroutines/channels/Channel;", "dispatchEvents", "Lkotlinx/coroutines/channels/Channel;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/test/TestDispatchEvent;", "events", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/internal/SynchronizedObject;", "lock", "Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnDispatchEvent$kotlinx_coroutines_test", "()Lkotlinx/coroutines/selects/SelectClause1;", "onDispatchEvent", "Lkotlin/time/TimeSource;", "timeSource", "Lkotlin/time/TimeSource;", "getTimeSource", "()Lkotlin/time/TimeSource;", "getTimeSource$annotations", "Key", "kotlinx-coroutines-test", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlin/coroutines/CoroutineContext$Element;"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScheduler.kt */
public final class TestCoroutineScheduler extends AbstractCoroutineContextElement implements CoroutineContext.Element {
    public static final Key Key = new Key((DefaultConstructorMarker) null);
    private static final /* synthetic */ AtomicLongFieldUpdater count$FU = AtomicLongFieldUpdater.newUpdater(TestCoroutineScheduler.class, "count");
    private volatile /* synthetic */ long count = 0;
    private long currentTime;
    private final Channel<Unit> dispatchEvents = ChannelKt.Channel$default(-1, (BufferOverflow) null, (Function1) null, 6, (Object) null);
    /* access modifiers changed from: private */
    public final ThreadSafeHeap<TestDispatchEvent<Object>> events = new ThreadSafeHeap<>();
    private final Object lock = new Object();
    private final TimeSource timeSource = new TestCoroutineScheduler$timeSource$1(this, DurationUnit.MILLISECONDS);

    public static /* synthetic */ void getCurrentTime$annotations() {
    }

    public static /* synthetic */ void getTimeSource$annotations() {
    }

    public TestCoroutineScheduler() {
        super(Key);
    }

    @Metadata(mo20734d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, mo20735d2 = {"Lkotlinx/coroutines/test/TestCoroutineScheduler$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "()V", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TestCoroutineScheduler.kt */
    public static final class Key implements CoroutineContext.Key<TestCoroutineScheduler> {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
        }
    }

    public final long getCurrentTime() {
        long j;
        synchronized (this.lock) {
            j = this.currentTime;
        }
        return j;
    }

    public final <T> DisposableHandle registerEvent$kotlinx_coroutines_test(TestDispatcher testDispatcher, long j, T t, CoroutineContext coroutineContext, Function1<? super T, Boolean> function1) {
        TestCoroutineScheduler$$ExternalSyntheticLambda0 testCoroutineScheduler$$ExternalSyntheticLambda0;
        long j2 = j;
        CoroutineContext coroutineContext2 = coroutineContext;
        if (j2 >= 0) {
            TestCoroutineSchedulerKt.checkSchedulerInContext(this, coroutineContext2);
            long andIncrement = count$FU.getAndIncrement(this);
            boolean z = coroutineContext2.get(BackgroundWork.INSTANCE) == null;
            synchronized (this.lock) {
                TestDispatchEvent testDispatchEvent = new TestDispatchEvent(testDispatcher, andIncrement, TestCoroutineSchedulerKt.addClamping(getCurrentTime(), j2), t, z, new TestCoroutineScheduler$registerEvent$2$event$1(function1, t));
                this.events.addLast(testDispatchEvent);
                sendDispatchEvent$kotlinx_coroutines_test(coroutineContext2);
                testCoroutineScheduler$$ExternalSyntheticLambda0 = new TestCoroutineScheduler$$ExternalSyntheticLambda0(this, testDispatchEvent);
            }
            return testCoroutineScheduler$$ExternalSyntheticLambda0;
        }
        throw new IllegalArgumentException(("Attempted scheduling an event earlier in time (with the time delta " + j2 + ')').toString());
    }

    /* access modifiers changed from: private */
    /* renamed from: registerEvent$lambda-4$lambda-3  reason: not valid java name */
    public static final void m1677registerEvent$lambda4$lambda3(TestCoroutineScheduler testCoroutineScheduler, TestDispatchEvent testDispatchEvent) {
        synchronized (testCoroutineScheduler.lock) {
            testCoroutineScheduler.events.remove(testDispatchEvent);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean tryRunNextTaskUnless$kotlinx_coroutines_test(Function0<Boolean> function0) {
        synchronized (this.lock) {
            if (function0.invoke().booleanValue()) {
                return false;
            }
            TestDispatchEvent removeFirstOrNull = this.events.removeFirstOrNull();
            if (removeFirstOrNull == null) {
                return false;
            }
            if (getCurrentTime() <= removeFirstOrNull.time) {
                this.currentTime = removeFirstOrNull.time;
                removeFirstOrNull.dispatcher.processEvent$kotlinx_coroutines_test(removeFirstOrNull.time, removeFirstOrNull.marker);
                return true;
            }
            Void unused = TestCoroutineSchedulerKt.currentTimeAheadOfEvents();
            throw new KotlinNothingValueException();
        }
    }

    public final void advanceUntilIdle() {
        advanceUntilIdleOr$kotlinx_coroutines_test(new TestCoroutineScheduler$advanceUntilIdle$1(this));
    }

    public final void advanceUntilIdleOr$kotlinx_coroutines_test(Function0<Boolean> function0) {
        do {
        } while (tryRunNextTaskUnless$kotlinx_coroutines_test(function0));
    }

    public final void runCurrent() {
        long currentTime2;
        ThreadSafeHeapNode threadSafeHeapNode;
        TestDispatchEvent testDispatchEvent;
        synchronized (this.lock) {
            currentTime2 = getCurrentTime();
        }
        while (true) {
            synchronized (this.lock) {
                ThreadSafeHeap<TestDispatchEvent<Object>> threadSafeHeap = this.events;
                synchronized (threadSafeHeap) {
                    TestDispatchEvent<Object> firstImpl = threadSafeHeap.firstImpl();
                    threadSafeHeapNode = null;
                    if (firstImpl != null) {
                        if (firstImpl.time <= currentTime2) {
                            threadSafeHeapNode = threadSafeHeap.removeAtImpl(0);
                        } else {
                            threadSafeHeapNode = null;
                        }
                    }
                }
                testDispatchEvent = (TestDispatchEvent) threadSafeHeapNode;
            }
            if (testDispatchEvent != null) {
                testDispatchEvent.dispatcher.processEvent$kotlinx_coroutines_test(testDispatchEvent.time, testDispatchEvent.marker);
            } else {
                return;
            }
        }
    }

    public final void advanceTimeBy(long j) {
        ThreadSafeHeapNode threadSafeHeapNode;
        TestDispatchEvent testDispatchEvent;
        if (j >= 0) {
            long access$addClamping = TestCoroutineSchedulerKt.addClamping(getCurrentTime(), j);
            while (true) {
                synchronized (this.lock) {
                    long currentTime2 = getCurrentTime();
                    ThreadSafeHeap<TestDispatchEvent<Object>> threadSafeHeap = this.events;
                    synchronized (threadSafeHeap) {
                        TestDispatchEvent<Object> firstImpl = threadSafeHeap.firstImpl();
                        threadSafeHeapNode = null;
                        if (firstImpl != null) {
                            if (access$addClamping > firstImpl.time) {
                                threadSafeHeapNode = threadSafeHeap.removeAtImpl(0);
                            } else {
                                threadSafeHeapNode = null;
                            }
                        }
                    }
                    testDispatchEvent = (TestDispatchEvent) threadSafeHeapNode;
                    if (testDispatchEvent == null) {
                        this.currentTime = access$addClamping;
                        return;
                    } else if (currentTime2 <= testDispatchEvent.time) {
                        this.currentTime = testDispatchEvent.time;
                    } else {
                        Void unused = TestCoroutineSchedulerKt.currentTimeAheadOfEvents();
                        throw new KotlinNothingValueException();
                    }
                }
                testDispatchEvent.dispatcher.processEvent$kotlinx_coroutines_test(testDispatchEvent.time, testDispatchEvent.marker);
            }
        } else {
            throw new IllegalArgumentException(("Can not advance time by a negative delay: " + j).toString());
        }
    }

    public static /* synthetic */ boolean isIdle$kotlinx_coroutines_test$default(TestCoroutineScheduler testCoroutineScheduler, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return testCoroutineScheduler.isIdle$kotlinx_coroutines_test(z);
    }

    public final boolean isIdle$kotlinx_coroutines_test(boolean z) {
        boolean isEmpty;
        synchronized (this.lock) {
            isEmpty = z ? this.events.isEmpty() : TestCoroutineSchedulerKt.none(this.events, TestCoroutineScheduler$isIdle$1$1.INSTANCE);
        }
        return isEmpty;
    }

    public final void sendDispatchEvent$kotlinx_coroutines_test(CoroutineContext coroutineContext) {
        if (coroutineContext.get(BackgroundWork.INSTANCE) != BackgroundWork.INSTANCE) {
            this.dispatchEvents.m1643trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    public final SelectClause1<Unit> getOnDispatchEvent$kotlinx_coroutines_test() {
        return this.dispatchEvents.getOnReceive();
    }

    public final TimeSource getTimeSource() {
        return this.timeSource;
    }
}
