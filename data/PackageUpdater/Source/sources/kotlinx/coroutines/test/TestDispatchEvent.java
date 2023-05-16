package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;

@Metadata(mo20734d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u00022\u00020\u0003B;\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00028\u0000\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u001c\u001a\u00020\u00162\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00028\u00008\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\b\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, mo20735d2 = {"Lkotlinx/coroutines/test/TestDispatchEvent;", "T", "", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "dispatcher", "Lkotlinx/coroutines/test/TestDispatcher;", "count", "", "time", "marker", "isForeground", "", "isCancelled", "Lkotlin/Function0;", "(Lkotlinx/coroutines/test/TestDispatcher;JJLjava/lang/Object;ZLkotlin/jvm/functions/Function0;)V", "heap", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "Ljava/lang/Object;", "compareTo", "other", "toString", "", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScheduler.kt */
final class TestDispatchEvent<T> implements Comparable<TestDispatchEvent<?>>, ThreadSafeHeapNode {
    /* access modifiers changed from: private */
    public final long count;
    public final TestDispatcher dispatcher;
    private ThreadSafeHeap<?> heap;
    private int index;
    public final Function0<Boolean> isCancelled;
    public final boolean isForeground;
    public final T marker;
    public final long time;

    public TestDispatchEvent(TestDispatcher testDispatcher, long j, long j2, T t, boolean z, Function0<Boolean> function0) {
        this.dispatcher = testDispatcher;
        this.count = j;
        this.time = j2;
        this.marker = t;
        this.isForeground = z;
        this.isCancelled = function0;
    }

    public ThreadSafeHeap<?> getHeap() {
        return this.heap;
    }

    public void setHeap(ThreadSafeHeap<?> threadSafeHeap) {
        this.heap = threadSafeHeap;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int compareTo(TestDispatchEvent<?> testDispatchEvent) {
        return ComparisonsKt.compareValuesBy(this, testDispatchEvent, (Function1<? super T, ? extends Comparable<?>>[]) new Function1[]{TestDispatchEvent$compareTo$1.INSTANCE, TestDispatchEvent$compareTo$2.INSTANCE});
    }

    public String toString() {
        return "TestDispatchEvent(time=" + this.time + ", dispatcher=" + this.dispatcher + (this.isForeground ? "" : ", background") + ')';
    }
}
