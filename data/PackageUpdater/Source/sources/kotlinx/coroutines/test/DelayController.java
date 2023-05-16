package kotlinx.coroutines.test;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H'J\b\u0010\n\u001a\u00020\u0003H'J\b\u0010\u000b\u001a\u00020\fH'J\b\u0010\r\u001a\u00020\fH'J/\u0010\r\u001a\u00020\f2\u001c\u0010\u000e\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000fH§@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\fH'J\b\u0010\u0013\u001a\u00020\fH'R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, mo20735d2 = {"Lkotlinx/coroutines/test/DelayController;", "", "currentTime", "", "getCurrentTime$annotations", "()V", "getCurrentTime", "()J", "advanceTimeBy", "delayTimeMillis", "advanceUntilIdle", "cleanupTestCoroutines", "", "pauseDispatcher", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resumeDispatcher", "runCurrent", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Deprecated(level = DeprecationLevel.WARNING, message = "Use `TestCoroutineScheduler` to control virtual time.")
/* compiled from: DelayController.kt */
public interface DelayController {

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DelayController.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void getCurrentTime$annotations() {
        }
    }

    long advanceTimeBy(long j);

    long advanceUntilIdle();

    void cleanupTestCoroutines() throws AssertionError;

    long getCurrentTime();

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use a dispatcher that is paused by default, like `StandardTestDispatcher`.")
    Object pauseDispatcher(Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super Unit> continuation);

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use a dispatcher that is paused by default, like `StandardTestDispatcher`.")
    void pauseDispatcher();

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use a dispatcher that is paused by default, like `StandardTestDispatcher`.")
    void resumeDispatcher();

    void runCurrent();
}
