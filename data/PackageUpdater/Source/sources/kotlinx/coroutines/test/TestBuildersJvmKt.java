package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a9\u0010\u0000\u001a\u00020\u00012'\u0010\u0002\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\b*\n\u0010\t\"\u00020\u00012\u00020\u0001\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, mo20735d2 = {"createTestResult", "", "testProcedure", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)V", "TestResult", "kotlinx-coroutines-test"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestBuildersJvm.kt */
public final class TestBuildersJvmKt {
    public static /* synthetic */ void TestResult$annotations() {
    }

    public static final void createTestResult(Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Object unused = BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new TestBuildersJvmKt$createTestResult$1(function2, (Continuation<? super TestBuildersJvmKt$createTestResult$1>) null), 1, (Object) null);
    }
}
