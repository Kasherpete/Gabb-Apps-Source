package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(mo20734d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u0010062\b\u0012\u0004\u0012\u00028\u0000072\b\u0012\u0004\u0012\u00028\u0000082\b\u0012\u0004\u0012\u00028\u000009B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\t\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0014¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ-\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000!2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0019H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0004\b&\u0010'J!\u0010*\u001a\u00020\r2\b\u0010(\u001a\u0004\u0018\u00010\u00022\u0006\u0010)\u001a\u00020\u0002H\u0002¢\u0006\u0004\b*\u0010\u000fR\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000+8VX\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0016\u0010/\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b/\u00100R*\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00028\u00008V@VX\u000e¢\u0006\u0012\u0012\u0004\b4\u0010%\u001a\u0004\b1\u00102\"\u0004\b3\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u00065"}, mo20735d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", "T", "", "initialState", "<init>", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expect", "update", "", "compareAndSet", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/flow/StateFlowSlot;", "createSlot", "()Lkotlinx/coroutines/flow/StateFlowSlot;", "", "size", "", "createSlotArray", "(I)[Lkotlinx/coroutines/flow/StateFlowSlot;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "context", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlinx/coroutines/flow/Flow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)Lkotlinx/coroutines/flow/Flow;", "resetReplayCache", "()V", "tryEmit", "(Ljava/lang/Object;)Z", "expectedState", "newState", "updateState", "", "getReplayCache", "()Ljava/util/List;", "replayCache", "sequence", "I", "getValue", "()Ljava/lang/Object;", "setValue", "getValue$annotations", "kotlinx-coroutines-core", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: StateFlow.kt */
final class StateFlowImpl<T> extends AbstractSharedFlow<StateFlowSlot> implements MutableStateFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    private volatile /* synthetic */ Object _state;
    private int sequence;

    public static /* synthetic */ void getValue$annotations() {
    }

    public StateFlowImpl(Object obj) {
        this._state = obj;
    }

    public T getValue() {
        T t = NullSurrogateKt.NULL;
        T t2 = this._state;
        if (t2 == t) {
            return null;
        }
        return t2;
    }

    public void setValue(T t) {
        if (t == null) {
            t = NullSurrogateKt.NULL;
        }
        updateState((Object) null, t);
    }

    public boolean compareAndSet(T t, T t2) {
        if (t == null) {
            t = NullSurrogateKt.NULL;
        }
        if (t2 == null) {
            t2 = NullSurrogateKt.NULL;
        }
        return updateState(t, t2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002c, code lost:
        r8 = (kotlinx.coroutines.flow.StateFlowSlot[]) r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002e, code lost:
        if (r8 == null) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        r2 = r8.length;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0032, code lost:
        if (r3 >= r2) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0034, code lost:
        r4 = r8[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0036, code lost:
        if (r4 == null) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0038, code lost:
        r4.makePending();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003b, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003e, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r8 = r6.sequence;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0041, code lost:
        if (r8 != r7) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0043, code lost:
        r6.sequence = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0046, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0047, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r7 = getSlots();
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x004e, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x004f, code lost:
        r5 = r8;
        r8 = r7;
        r7 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean updateState(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            r6.getSlots()
            monitor-enter(r6)
            java.lang.Object r0 = r6._state     // Catch:{ all -> 0x005c }
            r1 = 0
            if (r7 == 0) goto L_0x0011
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r7)     // Catch:{ all -> 0x005c }
            if (r7 != 0) goto L_0x0011
            monitor-exit(r6)
            return r1
        L_0x0011:
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r8)     // Catch:{ all -> 0x005c }
            r0 = 1
            if (r7 == 0) goto L_0x001a
            monitor-exit(r6)
            return r0
        L_0x001a:
            r6._state = r8     // Catch:{ all -> 0x005c }
            int r7 = r6.sequence     // Catch:{ all -> 0x005c }
            r8 = r7 & 1
            if (r8 != 0) goto L_0x0056
            int r7 = r7 + r0
            r6.sequence = r7     // Catch:{ all -> 0x005c }
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot[] r8 = r6.getSlots()     // Catch:{ all -> 0x005c }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005c }
            monitor-exit(r6)
        L_0x002c:
            kotlinx.coroutines.flow.StateFlowSlot[] r8 = (kotlinx.coroutines.flow.StateFlowSlot[]) r8
            if (r8 == 0) goto L_0x003e
            int r2 = r8.length
            r3 = r1
        L_0x0032:
            if (r3 >= r2) goto L_0x003e
            r4 = r8[r3]
            if (r4 == 0) goto L_0x003b
            r4.makePending()
        L_0x003b:
            int r3 = r3 + 1
            goto L_0x0032
        L_0x003e:
            monitor-enter(r6)
            int r8 = r6.sequence     // Catch:{ all -> 0x0053 }
            if (r8 != r7) goto L_0x0048
            int r7 = r7 + r0
            r6.sequence = r7     // Catch:{ all -> 0x0053 }
            monitor-exit(r6)
            return r0
        L_0x0048:
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot[] r7 = r6.getSlots()     // Catch:{ all -> 0x0053 }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0053 }
            monitor-exit(r6)
            r5 = r8
            r8 = r7
            r7 = r5
            goto L_0x002c
        L_0x0053:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x0056:
            int r7 = r7 + 2
            r6.sequence = r7     // Catch:{ all -> 0x005c }
            monitor-exit(r6)
            return r0
        L_0x005c:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.updateState(java.lang.Object, java.lang.Object):boolean");
    }

    public List<T> getReplayCache() {
        return CollectionsKt.listOf(getValue());
    }

    public boolean tryEmit(T t) {
        setValue(t);
        return true;
    }

    public Object emit(T t, Continuation<? super Unit> continuation) {
        setValue(t);
        return Unit.INSTANCE;
    }

    public void resetReplayCache() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: kotlinx.coroutines.flow.StateFlowSlot} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: kotlinx.coroutines.flow.FlowCollector<? super T>} */
    /* JADX WARNING: type inference failed for: r12v14, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bd A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bf A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d2 A[Catch:{ all -> 0x00f1 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d3 A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00de A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r11, kotlin.coroutines.Continuation<?> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof kotlinx.coroutines.flow.StateFlowImpl$collect$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = (kotlinx.coroutines.flow.StateFlowImpl$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = new kotlinx.coroutines.flow.StateFlowImpl$collect$1
            r0.<init>(r10, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0079
            if (r2 == r6) goto L_0x0067
            if (r2 == r5) goto L_0x004d
            if (r2 != r4) goto L_0x0045
            java.lang.Object r10 = r0.L$4
            java.lang.Object r11 = r0.L$3
            kotlinx.coroutines.Job r11 = (kotlinx.coroutines.Job) r11
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r7 = (kotlinx.coroutines.flow.StateFlowImpl) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x0064 }
            r12 = r10
            r10 = r7
            goto L_0x00aa
        L_0x0045:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x004d:
            java.lang.Object r10 = r0.L$4
            java.lang.Object r11 = r0.L$3
            kotlinx.coroutines.Job r11 = (kotlinx.coroutines.Job) r11
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r7 = (kotlinx.coroutines.flow.StateFlowImpl) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x0064 }
            goto L_0x00d6
        L_0x0064:
            r10 = move-exception
            goto L_0x00f4
        L_0x0067:
            java.lang.Object r10 = r0.L$2
            r2 = r10
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r10 = r0.L$1
            r11 = r10
            kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
            java.lang.Object r10 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r10 = (kotlinx.coroutines.flow.StateFlowImpl) r10
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x00f1 }
            goto L_0x0099
        L_0x0079:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot r12 = r10.allocateSlot()
            r2 = r12
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            boolean r12 = r11 instanceof kotlinx.coroutines.flow.SubscribedFlowCollector     // Catch:{ all -> 0x00f1 }
            if (r12 == 0) goto L_0x0099
            r12 = r11
            kotlinx.coroutines.flow.SubscribedFlowCollector r12 = (kotlinx.coroutines.flow.SubscribedFlowCollector) r12     // Catch:{ all -> 0x00f1 }
            r0.L$0 = r10     // Catch:{ all -> 0x00f1 }
            r0.L$1 = r11     // Catch:{ all -> 0x00f1 }
            r0.L$2 = r2     // Catch:{ all -> 0x00f1 }
            r0.label = r6     // Catch:{ all -> 0x00f1 }
            java.lang.Object r12 = r12.onSubscription(r0)     // Catch:{ all -> 0x00f1 }
            if (r12 != r1) goto L_0x0099
            return r1
        L_0x0099:
            kotlin.coroutines.CoroutineContext r12 = r0.getContext()     // Catch:{ all -> 0x00f1 }
            kotlinx.coroutines.Job$Key r6 = kotlinx.coroutines.Job.Key     // Catch:{ all -> 0x00f1 }
            kotlin.coroutines.CoroutineContext$Key r6 = (kotlin.coroutines.CoroutineContext.Key) r6     // Catch:{ all -> 0x00f1 }
            kotlin.coroutines.CoroutineContext$Element r12 = r12.get(r6)     // Catch:{ all -> 0x00f1 }
            kotlinx.coroutines.Job r12 = (kotlinx.coroutines.Job) r12     // Catch:{ all -> 0x00f1 }
            r6 = r11
            r11 = r12
            r12 = r3
        L_0x00aa:
            java.lang.Object r7 = r10._state     // Catch:{ all -> 0x00f1 }
            if (r11 == 0) goto L_0x00b1
            kotlinx.coroutines.JobKt.ensureActive((kotlinx.coroutines.Job) r11)     // Catch:{ all -> 0x00f1 }
        L_0x00b1:
            if (r12 == 0) goto L_0x00b9
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r7)     // Catch:{ all -> 0x00f1 }
            if (r8 != 0) goto L_0x00d8
        L_0x00b9:
            kotlinx.coroutines.internal.Symbol r12 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL     // Catch:{ all -> 0x00f1 }
            if (r7 != r12) goto L_0x00bf
            r12 = r3
            goto L_0x00c0
        L_0x00bf:
            r12 = r7
        L_0x00c0:
            r0.L$0 = r10     // Catch:{ all -> 0x00f1 }
            r0.L$1 = r6     // Catch:{ all -> 0x00f1 }
            r0.L$2 = r2     // Catch:{ all -> 0x00f1 }
            r0.L$3 = r11     // Catch:{ all -> 0x00f1 }
            r0.L$4 = r7     // Catch:{ all -> 0x00f1 }
            r0.label = r5     // Catch:{ all -> 0x00f1 }
            java.lang.Object r12 = r6.emit(r12, r0)     // Catch:{ all -> 0x00f1 }
            if (r12 != r1) goto L_0x00d3
            return r1
        L_0x00d3:
            r9 = r7
            r7 = r10
            r10 = r9
        L_0x00d6:
            r12 = r10
            r10 = r7
        L_0x00d8:
            boolean r7 = r2.takePending()     // Catch:{ all -> 0x00f1 }
            if (r7 != 0) goto L_0x00aa
            r0.L$0 = r10     // Catch:{ all -> 0x00f1 }
            r0.L$1 = r6     // Catch:{ all -> 0x00f1 }
            r0.L$2 = r2     // Catch:{ all -> 0x00f1 }
            r0.L$3 = r11     // Catch:{ all -> 0x00f1 }
            r0.L$4 = r12     // Catch:{ all -> 0x00f1 }
            r0.label = r4     // Catch:{ all -> 0x00f1 }
            java.lang.Object r7 = r2.awaitPending(r0)     // Catch:{ all -> 0x00f1 }
            if (r7 != r1) goto L_0x00aa
            return r1
        L_0x00f1:
            r11 = move-exception
            r7 = r10
            r10 = r11
        L_0x00f4:
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot r2 = (kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot) r2
            r7.freeSlot(r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public StateFlowSlot createSlot() {
        return new StateFlowSlot();
    }

    /* access modifiers changed from: protected */
    public StateFlowSlot[] createSlotArray(int i) {
        return new StateFlowSlot[i];
    }

    public Flow<T> fuse(CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        return StateFlowKt.fuseStateFlow(this, coroutineContext, i, bufferOverflow);
    }
}
