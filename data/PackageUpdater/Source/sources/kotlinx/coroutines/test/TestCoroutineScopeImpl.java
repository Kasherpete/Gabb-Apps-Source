package kotlinx.coroutines.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, mo20735d2 = {"Lkotlinx/coroutines/test/TestCoroutineScopeImpl;", "Lkotlinx/coroutines/test/TestCoroutineScope;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "cleanedUp", "", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "exceptions", "", "", "initialJobs", "", "Lkotlinx/coroutines/Job;", "lock", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "testScheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "getTestScheduler", "()Lkotlinx/coroutines/test/TestCoroutineScheduler;", "cleanupTestCoroutines", "", "reportException", "throwable", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScope.kt */
final class TestCoroutineScopeImpl implements TestCoroutineScope {
    private boolean cleanedUp;
    private final CoroutineContext coroutineContext;
    private List<Throwable> exceptions = new ArrayList();
    private final Set<Job> initialJobs = TestCoroutineScopeKt.activeJobs(getCoroutineContext());
    private final Object lock = new Object();

    public TestCoroutineScopeImpl(CoroutineContext coroutineContext2) {
        this.coroutineContext = coroutineContext2;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final boolean reportException(Throwable th) {
        boolean z;
        synchronized (this.lock) {
            if (this.cleanedUp) {
                z = false;
            } else {
                this.exceptions.add(th);
                z = true;
            }
        }
        return z;
    }

    public TestCoroutineScheduler getTestScheduler() {
        CoroutineContext.Element element = getCoroutineContext().get(TestCoroutineScheduler.Key);
        Intrinsics.checkNotNull(element);
        return (TestCoroutineScheduler) element;
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [kotlin.coroutines.CoroutineContext$Element] */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processHandlersOutBlocks(RegionMaker.java:1008)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:978)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0046  */
    public void cleanupTestCoroutines() {
        /*
            r5 = this;
            kotlin.coroutines.CoroutineContext r0 = r5.getCoroutineContext()
            kotlin.coroutines.ContinuationInterceptor$Key r1 = kotlin.coroutines.ContinuationInterceptor.Key
            kotlin.coroutines.CoroutineContext$Key r1 = (kotlin.coroutines.CoroutineContext.Key) r1
            kotlin.coroutines.CoroutineContext$Element r0 = r0.get(r1)
            kotlin.coroutines.ContinuationInterceptor r0 = (kotlin.coroutines.ContinuationInterceptor) r0
            boolean r1 = r0 instanceof kotlinx.coroutines.test.DelayController
            r2 = 0
            if (r1 == 0) goto L_0x0016
            kotlinx.coroutines.test.DelayController r0 = (kotlinx.coroutines.test.DelayController) r0
            goto L_0x0017
        L_0x0016:
            r0 = r2
        L_0x0017:
            r1 = 0
            r3 = 1
            if (r0 == 0) goto L_0x001f
            r0.cleanupTestCoroutines()     // Catch:{ UncompletedCoroutinesError -> 0x0030 }
            goto L_0x0031
        L_0x001f:
            kotlinx.coroutines.test.TestCoroutineScheduler r0 = r5.getTestScheduler()
            r0.runCurrent()
            kotlinx.coroutines.test.TestCoroutineScheduler r0 = r5.getTestScheduler()
            boolean r0 = r0.isIdle$kotlinx_coroutines_test(r1)
            if (r0 != 0) goto L_0x0031
        L_0x0030:
            r1 = r3
        L_0x0031:
            kotlin.coroutines.CoroutineContext r0 = r5.getCoroutineContext()
            kotlinx.coroutines.CoroutineExceptionHandler$Key r4 = kotlinx.coroutines.CoroutineExceptionHandler.Key
            kotlin.coroutines.CoroutineContext$Key r4 = (kotlin.coroutines.CoroutineContext.Key) r4
            kotlin.coroutines.CoroutineContext$Element r0 = r0.get(r4)
            boolean r4 = r0 instanceof kotlinx.coroutines.test.UncaughtExceptionCaptor
            if (r4 == 0) goto L_0x0044
            r2 = r0
            kotlinx.coroutines.test.UncaughtExceptionCaptor r2 = (kotlinx.coroutines.test.UncaughtExceptionCaptor) r2
        L_0x0044:
            if (r2 == 0) goto L_0x0049
            r2.cleanupTestCoroutines()
        L_0x0049:
            java.lang.Object r0 = r5.lock
            monitor-enter(r0)
            boolean r2 = r5.cleanedUp     // Catch:{ all -> 0x00c3 }
            if (r2 != 0) goto L_0x00bb
            r5.cleanedUp = r3     // Catch:{ all -> 0x00c3 }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00c3 }
            monitor-exit(r0)
            java.util.List<java.lang.Throwable> r0 = r5.exceptions
            java.lang.Object r0 = kotlin.collections.CollectionsKt.firstOrNull(r0)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            if (r0 == 0) goto L_0x007e
            java.util.List<java.lang.Throwable> r5 = r5.exceptions
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r5 = kotlin.collections.CollectionsKt.drop(r5, r3)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x006d:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x007d
            java.lang.Object r1 = r5.next()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            kotlin.ExceptionsKt.addSuppressed(r0, r1)
            goto L_0x006d
        L_0x007d:
            throw r0
        L_0x007e:
            if (r1 != 0) goto L_0x00b3
            kotlin.coroutines.CoroutineContext r0 = r5.getCoroutineContext()
            java.util.Set r0 = kotlinx.coroutines.test.TestCoroutineScopeKt.activeJobs(r0)
            java.util.Set<kotlinx.coroutines.Job> r5 = r5.initialJobs
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Set r5 = kotlin.collections.SetsKt.minus(r0, r5)
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            r5 = r5 ^ r3
            if (r5 != 0) goto L_0x009a
            return
        L_0x009a:
            kotlinx.coroutines.test.UncompletedCoroutinesError r5 = new kotlinx.coroutines.test.UncompletedCoroutinesError
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Test finished with active jobs: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L_0x00b3:
            kotlinx.coroutines.test.UncompletedCoroutinesError r5 = new kotlinx.coroutines.test.UncompletedCoroutinesError
            java.lang.String r0 = "Unfinished coroutines during teardown. Ensure all coroutines are completed or cancelled by your test."
            r5.<init>(r0)
            throw r5
        L_0x00bb:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00c3 }
            java.lang.String r1 = "Attempting to clean up a test coroutine scope more than once."
            r5.<init>(r1)     // Catch:{ all -> 0x00c3 }
            throw r5     // Catch:{ all -> 0x00c3 }
        L_0x00c3:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.test.TestCoroutineScopeImpl.cleanupTestCoroutines():void");
    }
}
