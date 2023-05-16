package kotlinx.coroutines.test;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt", mo21449f = "TestBuilders.kt", mo21450i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, mo21451l = {326, 266, 266}, mo21452m = "runTestCoroutine", mo21453n = {"$this$runTestCoroutine", "coroutine", "tryGetCompletionCause", "cleanup", "scheduler", "completed", "backgroundWorkRunner", "dispatchTimeoutMs", "$this$runTestCoroutine", "coroutine", "tryGetCompletionCause", "cleanup", "scheduler", "completed", "dispatchTimeoutMs"}, mo21454s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0"})
/* compiled from: TestBuilders.kt */
final class TestBuildersKt__TestBuildersKt$runTestCoroutine$1<T extends AbstractCoroutine<? super Unit>> extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;

    TestBuildersKt__TestBuildersKt$runTestCoroutine$1(Continuation<? super TestBuildersKt__TestBuildersKt$runTestCoroutine$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TestBuildersKt.runTestCoroutine((CoroutineScope) null, null, 0, (Function1) null, (Function2) null, (Function0<? extends List<? extends Throwable>>) null, this);
    }
}
