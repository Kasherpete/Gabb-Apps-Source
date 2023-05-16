package kotlinx.coroutines.test;

import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u0000X\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a_\u0010\u0002\u001a\u00020\u0003\"\u000e\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\u0006\u001a\u0002H\u00042\u0006\u0010\u0007\u001a\u00020\u00012\u0019\u0010\b\u001a\u0015\u0012\u0004\u0012\u0002H\u0004\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\u0002\b\u000b2\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\rH\b¢\u0006\u0004\b\u000f\u0010\u0010\u001aQ\u0010\u0011\u001a\u00060\u0003j\u0002`\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0007\u001a\u00020\u00012'\u0010\u0015\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0016¢\u0006\u0002\b\u000bH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001aK\u0010\u0011\u001a\u00060\u0003j\u0002`\u0012*\u00020\u00172\b\b\u0002\u0010\u0007\u001a\u00020\u00012'\u0010\u0015\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0016¢\u0006\u0002\b\u000bH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a\u0001\u0010\u001c\u001a\u00020\u0003\"\u000e\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u00020\u00030\u0005*\u00020\u001d2\u0006\u0010\u0006\u001a\u0002H\u00042\u0006\u0010\u0007\u001a\u00020\u00012\u0019\u0010\b\u001a\u0015\u0012\u0004\u0012\u0002H\u0004\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\u0002\b\u000b2'\u0010\u0015\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0016¢\u0006\u0002\b\u000b2\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0012\u0010\u001f\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, mo20735d2 = {"DEFAULT_DISPATCH_TIMEOUT_MS", "", "handleTimeout", "", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "coroutine", "dispatchTimeoutMs", "tryGetCompletionCause", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "cleanup", "Lkotlin/Function0;", "", "handleTimeout$TestBuildersKt__TestBuildersKt", "(Lkotlinx/coroutines/AbstractCoroutine;JLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "runTest", "Lkotlinx/coroutines/test/TestResult;", "context", "Lkotlin/coroutines/CoroutineContext;", "testBody", "Lkotlin/Function2;", "Lkotlinx/coroutines/test/TestScope;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/coroutines/CoroutineContext;JLkotlin/jvm/functions/Function2;)V", "(Lkotlinx/coroutines/test/TestScope;JLkotlin/jvm/functions/Function2;)V", "runTestCoroutine", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/AbstractCoroutine;JLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "throwAll", "kotlinx-coroutines-test"}, mo20736k = 5, mo20737mv = {1, 6, 0}, mo20739xi = 48, mo20740xs = "kotlinx/coroutines/test/TestBuildersKt")
/* compiled from: TestBuilders.kt */
final /* synthetic */ class TestBuildersKt__TestBuildersKt {
    public static /* synthetic */ void runTest$default(CoroutineContext coroutineContext, long j, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            j = TestBuildersKt.DEFAULT_DISPATCH_TIMEOUT_MS;
        }
        TestBuildersKt.runTest(coroutineContext, j, (Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    public static final void runTest(CoroutineContext coroutineContext, long j, Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (coroutineContext.get(RunningInRunTest.INSTANCE) == null) {
            TestBuildersKt.runTest(TestScopeKt.TestScope(coroutineContext.plus(RunningInRunTest.INSTANCE)), j, function2);
            return;
        }
        throw new IllegalStateException("Calls to `runTest` can't be nested. Please read the docs on `TestResult` for details.");
    }

    public static /* synthetic */ void runTest$default(TestScope testScope, long j, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = TestBuildersKt.DEFAULT_DISPATCH_TIMEOUT_MS;
        }
        TestBuildersKt.runTest(testScope, j, (Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    public static final void runTest(TestScope testScope, long j, Function2<? super TestScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        TestScopeImpl asSpecificImplementation = TestScopeKt.asSpecificImplementation(testScope);
        asSpecificImplementation.enter();
        TestBuildersJvmKt.createTestResult(new TestBuildersKt__TestBuildersKt$runTest$1$1(asSpecificImplementation, j, function2, testScope, (Continuation<? super TestBuildersKt__TestBuildersKt$runTest$1$1>) null));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00de, code lost:
        r4 = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r1, new kotlinx.coroutines.CoroutineName("background work runner"), (kotlinx.coroutines.CoroutineStart) null, new kotlinx.coroutines.test.C1461x40016ec7(r11, (kotlin.coroutines.Continuation<? super kotlinx.coroutines.test.C1461x40016ec7>) null), 2, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r12.L$0 = r1;
        r12.L$1 = r9;
        r12.L$2 = r8;
        r12.L$3 = r10;
        r12.L$4 = r11;
        r12.L$5 = r14;
        r12.L$6 = r4;
        r12.J$0 = r2;
        r12.label = r6 ? 1 : 0;
        r15 = new kotlinx.coroutines.selects.SelectBuilderImpl(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r0 = r15;
        r0.invoke(r9.getOnJoin(), new kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$1(r14, (kotlin.coroutines.Continuation<? super kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$1>) null));
        r0.invoke(r11.getOnDispatchEvent$kotlinx_coroutines_test(), new kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2((kotlin.coroutines.Continuation<? super kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2>) null));
        r0.onTimeout(r2, new kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3(r9, r2, r8, r10, (kotlin.coroutines.Continuation<? super kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3>) null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0151, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r15.handleBuilderException(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x019d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x018a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x018b  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01b8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x01c0 A[SYNTHETIC, Splitter:B:54:0x01c0] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T extends kotlinx.coroutines.AbstractCoroutine<? super kotlin.Unit>> java.lang.Object runTestCoroutine(kotlinx.coroutines.CoroutineScope r21, T r22, long r23, kotlin.jvm.functions.Function1<? super T, ? extends java.lang.Throwable> r25, kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r26, kotlin.jvm.functions.Function0<? extends java.util.List<? extends java.lang.Throwable>> r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) {
        /*
            r0 = r28
            boolean r1 = r0 instanceof kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$1 r1 = (kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$1 r1 = new kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r3 == 0) goto L_0x0099
            if (r3 == r6) goto L_0x0071
            if (r3 == r5) goto L_0x0040
            if (r3 == r4) goto L_0x0037
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0037:
            java.lang.Object r1 = r1.L$0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x01b9
        L_0x0040:
            long r8 = r1.J$0
            java.lang.Object r3 = r1.L$5
            kotlin.jvm.internal.Ref$BooleanRef r3 = (kotlin.jvm.internal.Ref.BooleanRef) r3
            java.lang.Object r10 = r1.L$4
            kotlinx.coroutines.test.TestCoroutineScheduler r10 = (kotlinx.coroutines.test.TestCoroutineScheduler) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.functions.Function0 r11 = (kotlin.jvm.functions.Function0) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.AbstractCoroutine r13 = (kotlinx.coroutines.AbstractCoroutine) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.CoroutineScope r14 = (kotlinx.coroutines.CoroutineScope) r14
            kotlin.ResultKt.throwOnFailure(r0)
            r18 = r12
            r12 = r1
            r1 = r14
            r14 = r3
            r19 = r13
            r13 = r2
            r2 = r8
            r8 = r18
            r9 = r19
            r20 = r11
            r11 = r10
            r10 = r20
            goto L_0x0199
        L_0x0071:
            long r8 = r1.J$0
            java.lang.Object r3 = r1.L$6
            kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3
            java.lang.Object r10 = r1.L$5
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$4
            kotlinx.coroutines.test.TestCoroutineScheduler r11 = (kotlinx.coroutines.test.TestCoroutineScheduler) r11
            java.lang.Object r12 = r1.L$3
            kotlin.jvm.functions.Function0 r12 = (kotlin.jvm.functions.Function0) r12
            java.lang.Object r13 = r1.L$2
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r1.L$1
            kotlinx.coroutines.AbstractCoroutine r14 = (kotlinx.coroutines.AbstractCoroutine) r14
            java.lang.Object r15 = r1.L$0
            kotlinx.coroutines.CoroutineScope r15 = (kotlinx.coroutines.CoroutineScope) r15
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0094 }
            goto L_0x0171
        L_0x0094:
            r0 = move-exception
            r12 = r1
            r1 = r0
            goto L_0x01a1
        L_0x0099:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.coroutines.CoroutineContext r0 = r22.getCoroutineContext()
            kotlinx.coroutines.test.TestCoroutineScheduler$Key r3 = kotlinx.coroutines.test.TestCoroutineScheduler.Key
            kotlin.coroutines.CoroutineContext$Key r3 = (kotlin.coroutines.CoroutineContext.Key) r3
            kotlin.coroutines.CoroutineContext$Element r0 = r0.get(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            kotlinx.coroutines.test.TestCoroutineScheduler r0 = (kotlinx.coroutines.test.TestCoroutineScheduler) r0
            kotlinx.coroutines.CoroutineStart r3 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
            kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$2 r8 = new kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$2
            r9 = r26
            r8.<init>(r9, r7)
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = r22
            r9.start(r3, r9, r8)
            kotlin.jvm.internal.Ref$BooleanRef r3 = new kotlin.jvm.internal.Ref$BooleanRef
            r3.<init>()
            r8 = r25
            r10 = r27
            r11 = r0
            r12 = r1
            r13 = r2
            r14 = r3
            r1 = r21
            r2 = r23
        L_0x00ce:
            boolean r0 = r14.element
            if (r0 != 0) goto L_0x01ba
            r11.advanceUntilIdle()
            boolean r0 = r9.isCompleted()
            if (r0 == 0) goto L_0x00de
            r14.element = r6
            goto L_0x00ce
        L_0x00de:
            kotlinx.coroutines.CoroutineName r0 = new kotlinx.coroutines.CoroutineName
            java.lang.String r15 = "background work runner"
            r0.<init>(r15)
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
            r15 = 0
            kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$backgroundWorkRunner$1 r4 = new kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$backgroundWorkRunner$1
            r4.<init>(r11, r7)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r16 = 2
            r17 = 0
            r21 = r1
            r22 = r0
            r23 = r15
            r24 = r4
            r25 = r16
            r26 = r17
            kotlinx.coroutines.Job r4 = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r21, r22, r23, r24, r25, r26)
            r12.L$0 = r1     // Catch:{ all -> 0x019d }
            r12.L$1 = r9     // Catch:{ all -> 0x019d }
            r12.L$2 = r8     // Catch:{ all -> 0x019d }
            r12.L$3 = r10     // Catch:{ all -> 0x019d }
            r12.L$4 = r11     // Catch:{ all -> 0x019d }
            r12.L$5 = r14     // Catch:{ all -> 0x019d }
            r12.L$6 = r4     // Catch:{ all -> 0x019d }
            r12.J$0 = r2     // Catch:{ all -> 0x019d }
            r12.label = r6     // Catch:{ all -> 0x019d }
            kotlinx.coroutines.selects.SelectBuilderImpl r15 = new kotlinx.coroutines.selects.SelectBuilderImpl     // Catch:{ all -> 0x019d }
            r15.<init>(r12)     // Catch:{ all -> 0x019d }
            r0 = r15
            kotlinx.coroutines.selects.SelectBuilder r0 = (kotlinx.coroutines.selects.SelectBuilder) r0     // Catch:{ all -> 0x0151 }
            kotlinx.coroutines.selects.SelectClause0 r6 = r9.getOnJoin()     // Catch:{ all -> 0x0151 }
            kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$1 r5 = new kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$1     // Catch:{ all -> 0x0151 }
            r5.<init>(r14, r7)     // Catch:{ all -> 0x0151 }
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5     // Catch:{ all -> 0x0151 }
            r0.invoke((kotlinx.coroutines.selects.SelectClause0) r6, r5)     // Catch:{ all -> 0x0151 }
            kotlinx.coroutines.selects.SelectClause1 r5 = r11.getOnDispatchEvent$kotlinx_coroutines_test()     // Catch:{ all -> 0x0151 }
            kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2 r6 = new kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$2     // Catch:{ all -> 0x0151 }
            r6.<init>(r7)     // Catch:{ all -> 0x0151 }
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6     // Catch:{ all -> 0x0151 }
            r0.invoke(r5, r6)     // Catch:{ all -> 0x0151 }
            kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3 r5 = new kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt$runTestCoroutine$3$3     // Catch:{ all -> 0x0151 }
            r6 = 0
            r21 = r5
            r22 = r9
            r23 = r2
            r25 = r8
            r26 = r10
            r27 = r6
            r21.<init>(r22, r23, r25, r26, r27)     // Catch:{ all -> 0x0151 }
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5     // Catch:{ all -> 0x0151 }
            r0.onTimeout(r2, r5)     // Catch:{ all -> 0x0151 }
            goto L_0x0155
        L_0x0151:
            r0 = move-exception
            r15.handleBuilderException(r0)     // Catch:{ all -> 0x019d }
        L_0x0155:
            java.lang.Object r0 = r15.getResult()     // Catch:{ all -> 0x019d }
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch:{ all -> 0x019d }
            if (r0 != r5) goto L_0x0162
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r12)     // Catch:{ all -> 0x019d }
        L_0x0162:
            if (r0 != r13) goto L_0x0165
            return r13
        L_0x0165:
            r15 = r1
            r1 = r12
            r12 = r10
            r10 = r14
            r14 = r9
            r18 = r2
            r3 = r4
            r2 = r13
            r13 = r8
            r8 = r18
        L_0x0171:
            r1.L$0 = r15
            r1.L$1 = r14
            r1.L$2 = r13
            r1.L$3 = r12
            r1.L$4 = r11
            r1.L$5 = r10
            r1.L$6 = r7
            r1.J$0 = r8
            r5 = 2
            r1.label = r5
            java.lang.Object r0 = kotlinx.coroutines.JobKt.cancelAndJoin(r3, r1)
            if (r0 != r2) goto L_0x018b
            return r2
        L_0x018b:
            r18 = r12
            r12 = r1
            r1 = r15
            r19 = r13
            r13 = r2
            r2 = r8
            r8 = r19
            r9 = r14
            r14 = r10
            r10 = r18
        L_0x0199:
            r4 = 3
            r6 = 1
            goto L_0x00ce
        L_0x019d:
            r0 = move-exception
            r1 = r0
            r3 = r4
            r2 = r13
        L_0x01a1:
            r12.L$0 = r1
            r12.L$1 = r7
            r12.L$2 = r7
            r12.L$3 = r7
            r12.L$4 = r7
            r12.L$5 = r7
            r12.L$6 = r7
            r4 = 3
            r12.label = r4
            java.lang.Object r0 = kotlinx.coroutines.JobKt.cancelAndJoin(r3, r12)
            if (r0 != r2) goto L_0x01b9
            return r2
        L_0x01b9:
            throw r1
        L_0x01ba:
            java.lang.Throwable r0 = r9.getCompletionExceptionOrNull()
            if (r0 == 0) goto L_0x01da
            java.lang.Object r1 = r10.invoke()     // Catch:{ UncompletedCoroutinesError -> 0x01c7 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ UncompletedCoroutinesError -> 0x01c7 }
            goto L_0x01cb
        L_0x01c7:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x01cb:
            java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r0)
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.List r0 = kotlin.collections.CollectionsKt.plus(r0, r1)
            kotlinx.coroutines.test.TestBuildersKt.throwAll(r0)
        L_0x01da:
            java.lang.Object r0 = r10.invoke()
            java.util.List r0 = (java.util.List) r0
            kotlinx.coroutines.test.TestBuildersKt.throwAll(r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.test.TestBuildersKt__TestBuildersKt.runTestCoroutine(kotlinx.coroutines.CoroutineScope, kotlinx.coroutines.AbstractCoroutine, long, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final <T extends AbstractCoroutine<? super Unit>> void handleTimeout$TestBuildersKt__TestBuildersKt(T t, long j, Function1<? super T, ? extends Throwable> function1, Function0<? extends List<? extends Throwable>> function0) {
        List<Throwable> list;
        try {
            list = (List) function0.invoke();
        } catch (UncompletedCoroutinesError unused) {
            list = CollectionsKt.emptyList();
        }
        List<Job> list2 = SequencesKt.toList(SequencesKt.filter(t.getChildren(), TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1.INSTANCE));
        Throwable th = t.isCancelled() ? (Throwable) function1.invoke(t) : null;
        String str = "After waiting for " + j + " ms";
        if (th == null) {
            str = str + ", the test coroutine is not completing";
        }
        if (!list2.isEmpty()) {
            str = str + ", there were active child jobs: " + list2;
        }
        if (th != null && list2.isEmpty()) {
            if (!t.isCompleted()) {
                str = str + ", the test coroutine was not completed";
            } else {
                return;
            }
        }
        UncompletedCoroutinesError uncompletedCoroutinesError = new UncompletedCoroutinesError(str);
        if (th != null) {
            ExceptionsKt.addSuppressed(uncompletedCoroutinesError, th);
        }
        for (Throwable addSuppressed : list) {
            ExceptionsKt.addSuppressed(uncompletedCoroutinesError, addSuppressed);
        }
        throw uncompletedCoroutinesError;
    }

    public static final void throwAll(List<? extends Throwable> list) {
        Throwable th = (Throwable) CollectionsKt.firstOrNull(list);
        if (th != null) {
            for (Throwable addSuppressed : CollectionsKt.drop(list, 1)) {
                ExceptionsKt.addSuppressed(th, addSuppressed);
            }
            throw th;
        }
    }
}
