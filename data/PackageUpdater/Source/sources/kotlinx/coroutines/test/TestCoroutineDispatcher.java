package kotlinx.coroutines.test;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.test.SchedulerAsDelayController;

@Metadata(mo20734d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014H\u0016J\u001c\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u000fH\u0016J/\u0010\u0016\u001a\u00020\u000f2\u001c\u0010\u0012\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u001c\u0010\u001b\u001a\u00020\u001c2\n\u0010\u0012\u001a\u00060\u0013j\u0002`\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, mo20735d2 = {"Lkotlinx/coroutines/test/TestCoroutineDispatcher;", "Lkotlinx/coroutines/test/TestDispatcher;", "Lkotlinx/coroutines/Delay;", "Lkotlinx/coroutines/test/SchedulerAsDelayController;", "scheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "(Lkotlinx/coroutines/test/TestCoroutineScheduler;)V", "value", "", "dispatchImmediately", "setDispatchImmediately", "(Z)V", "getScheduler", "()Lkotlinx/coroutines/test/TestCoroutineScheduler;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchYield", "pauseDispatcher", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "post", "Lkotlinx/coroutines/DisposableHandle;", "resumeDispatcher", "toString", "", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Deprecated(level = DeprecationLevel.WARNING, message = "The execution order of `TestCoroutineDispatcher` can be confusing, and the mechanism of pausing is typically misunderstood. Please use `StandardTestDispatcher` or `UnconfinedTestDispatcher` instead.")
/* compiled from: TestCoroutineDispatcher.kt */
public final class TestCoroutineDispatcher extends TestDispatcher implements Delay, SchedulerAsDelayController {
    private boolean dispatchImmediately;
    private final TestCoroutineScheduler scheduler;

    public TestCoroutineDispatcher() {
        this((TestCoroutineScheduler) null, 1, (DefaultConstructorMarker) null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This function delegates to the test scheduler, which may cause confusing behavior unless made explicit.", replaceWith = @ReplaceWith(expression = "this.scheduler.apply { advanceTimeBy(delayTimeMillis); runCurrent() }", imports = {}))
    public long advanceTimeBy(long j) {
        return SchedulerAsDelayController.DefaultImpls.advanceTimeBy(this, j);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This function delegates to the test scheduler, which may cause confusing behavior unless made explicit.", replaceWith = @ReplaceWith(expression = "this.scheduler.advanceUntilIdle()", imports = {}))
    public long advanceUntilIdle() {
        return SchedulerAsDelayController.DefaultImpls.advanceUntilIdle(this);
    }

    public void cleanupTestCoroutines() {
        SchedulerAsDelayController.DefaultImpls.cleanupTestCoroutines(this);
    }

    public long getCurrentTime() {
        return SchedulerAsDelayController.DefaultImpls.getCurrentTime(this);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This function delegates to the test scheduler, which may cause confusing behavior unless made explicit.", replaceWith = @ReplaceWith(expression = "this.scheduler.runCurrent()", imports = {}))
    public void runCurrent() {
        SchedulerAsDelayController.DefaultImpls.runCurrent(this);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TestCoroutineDispatcher(TestCoroutineScheduler testCoroutineScheduler, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new TestCoroutineScheduler() : testCoroutineScheduler);
    }

    public TestCoroutineScheduler getScheduler() {
        return this.scheduler;
    }

    public TestCoroutineDispatcher(TestCoroutineScheduler testCoroutineScheduler) {
        this.scheduler = testCoroutineScheduler;
        this.dispatchImmediately = true;
    }

    private final void setDispatchImmediately(boolean z) {
        this.dispatchImmediately = z;
        if (z) {
            getScheduler().advanceUntilIdle();
        }
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        TestCoroutineSchedulerKt.checkSchedulerInContext(getScheduler(), coroutineContext);
        if (this.dispatchImmediately) {
            getScheduler().sendDispatchEvent$kotlinx_coroutines_test(coroutineContext);
            runnable.run();
            return;
        }
        post(runnable, coroutineContext);
    }

    public void dispatchYield(CoroutineContext coroutineContext, Runnable runnable) {
        TestCoroutineSchedulerKt.checkSchedulerInContext(getScheduler(), coroutineContext);
        post(runnable, coroutineContext);
    }

    public String toString() {
        return "TestCoroutineDispatcher[scheduler=" + getScheduler() + ']';
    }

    private final DisposableHandle post(Runnable runnable, CoroutineContext coroutineContext) {
        return getScheduler().registerEvent$kotlinx_coroutines_test(this, 0, runnable, coroutineContext, TestCoroutineDispatcher$post$1.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object pauseDispatcher(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.test.TestCoroutineDispatcher$pauseDispatcher$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            kotlinx.coroutines.test.TestCoroutineDispatcher$pauseDispatcher$1 r0 = (kotlinx.coroutines.test.TestCoroutineDispatcher$pauseDispatcher$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.test.TestCoroutineDispatcher$pauseDispatcher$1 r0 = new kotlinx.coroutines.test.TestCoroutineDispatcher$pauseDispatcher$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            boolean r5 = r0.Z$0
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.test.TestCoroutineDispatcher r6 = (kotlinx.coroutines.test.TestCoroutineDispatcher) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0032 }
            r7 = r5
            r5 = r6
            goto L_0x0056
        L_0x0032:
            r7 = move-exception
            r4 = r7
            r7 = r5
            r5 = r6
            r6 = r4
            goto L_0x005d
        L_0x0038:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r7)
            boolean r7 = r5.dispatchImmediately
            r2 = 0
            r5.setDispatchImmediately(r2)
            r0.L$0 = r5     // Catch:{ all -> 0x005c }
            r0.Z$0 = r7     // Catch:{ all -> 0x005c }
            r0.label = r3     // Catch:{ all -> 0x005c }
            java.lang.Object r6 = r6.invoke(r0)     // Catch:{ all -> 0x005c }
            if (r6 != r1) goto L_0x0056
            return r1
        L_0x0056:
            r5.setDispatchImmediately(r7)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x005c:
            r6 = move-exception
        L_0x005d:
            r5.setDispatchImmediately(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.test.TestCoroutineDispatcher.pauseDispatcher(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void pauseDispatcher() {
        setDispatchImmediately(false);
    }

    public void resumeDispatcher() {
        setDispatchImmediately(true);
    }
}
