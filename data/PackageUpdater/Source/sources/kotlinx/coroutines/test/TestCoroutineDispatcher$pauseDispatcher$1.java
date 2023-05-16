package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.test.TestCoroutineDispatcher", mo21449f = "TestCoroutineDispatcher.kt", mo21450i = {0, 0}, mo21451l = {68}, mo21452m = "pauseDispatcher", mo21453n = {"this", "previous"}, mo21454s = {"L$0", "Z$0"})
/* compiled from: TestCoroutineDispatcher.kt */
final class TestCoroutineDispatcher$pauseDispatcher$1 extends ContinuationImpl {
    Object L$0;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TestCoroutineDispatcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestCoroutineDispatcher$pauseDispatcher$1(TestCoroutineDispatcher testCoroutineDispatcher, Continuation<? super TestCoroutineDispatcher$pauseDispatcher$1> continuation) {
        super(continuation);
        this.this$0 = testCoroutineDispatcher;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.pauseDispatcher((Function1<? super Continuation<? super Unit>, ? extends Object>) null, this);
    }
}
