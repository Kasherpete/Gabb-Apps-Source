package androidx.room;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a9\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b*\u00020\b2\u001c\u0010\f\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, mo20735d2 = {"acquireTransactionThread", "Lkotlin/coroutines/ContinuationInterceptor;", "Ljava/util/concurrent/Executor;", "controlJob", "Lkotlinx/coroutines/Job;", "(Ljava/util/concurrent/Executor;Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createTransactionContext", "Lkotlin/coroutines/CoroutineContext;", "Landroidx/room/RoomDatabase;", "(Landroidx/room/RoomDatabase;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTransaction", "R", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "room-ktx_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RoomDatabase.kt */
public final class RoomDatabaseKt {
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0082 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0083 A[PHI: r9 
      PHI: (r9v2 java.lang.Object) = (r9v5 java.lang.Object), (r9v1 java.lang.Object) binds: [B:23:0x0080, B:10:0x002a] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <R> java.lang.Object withTransaction(androidx.room.RoomDatabase r7, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super R> r9) {
        /*
            boolean r0 = r9 instanceof androidx.room.RoomDatabaseKt$withTransaction$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            androidx.room.RoomDatabaseKt$withTransaction$1 r0 = (androidx.room.RoomDatabaseKt$withTransaction$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            androidx.room.RoomDatabaseKt$withTransaction$1 r0 = new androidx.room.RoomDatabaseKt$withTransaction$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0045
            if (r2 == r4) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0083
        L_0x002e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0036:
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            androidx.room.RoomDatabase r8 = (androidx.room.RoomDatabase) r8
            kotlin.ResultKt.throwOnFailure(r9)
            r6 = r8
            r8 = r7
            r7 = r6
            goto L_0x006d
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.coroutines.CoroutineContext r9 = r0.getContext()
            androidx.room.TransactionElement$Key r2 = androidx.room.TransactionElement.Key
            kotlin.coroutines.CoroutineContext$Key r2 = (kotlin.coroutines.CoroutineContext.Key) r2
            kotlin.coroutines.CoroutineContext$Element r9 = r9.get(r2)
            androidx.room.TransactionElement r9 = (androidx.room.TransactionElement) r9
            if (r9 != 0) goto L_0x005a
            r9 = r5
            goto L_0x005e
        L_0x005a:
            kotlin.coroutines.ContinuationInterceptor r9 = r9.getTransactionDispatcher$room_ktx_release()
        L_0x005e:
            if (r9 != 0) goto L_0x006d
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r9 = createTransactionContext(r7, r0)
            if (r9 != r1) goto L_0x006d
            return r1
        L_0x006d:
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            androidx.room.RoomDatabaseKt$withTransaction$2 r2 = new androidx.room.RoomDatabaseKt$withTransaction$2
            r2.<init>(r7, r8, r5)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r2, r0)
            if (r9 != r1) goto L_0x0083
            return r1
        L_0x0083:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabaseKt.withTransaction(androidx.room.RoomDatabase, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object createTransactionContext(androidx.room.RoomDatabase r6, kotlin.coroutines.Continuation<? super kotlin.coroutines.CoroutineContext> r7) {
        /*
            boolean r0 = r7 instanceof androidx.room.RoomDatabaseKt$createTransactionContext$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            androidx.room.RoomDatabaseKt$createTransactionContext$1 r0 = (androidx.room.RoomDatabaseKt$createTransactionContext$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            androidx.room.RoomDatabaseKt$createTransactionContext$1 r0 = new androidx.room.RoomDatabaseKt$createTransactionContext$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.CompletableJob r6 = (kotlinx.coroutines.CompletableJob) r6
            java.lang.Object r0 = r0.L$0
            androidx.room.RoomDatabase r0 = (androidx.room.RoomDatabase) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x007a
        L_0x0032:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = 0
            kotlinx.coroutines.CompletableJob r7 = kotlinx.coroutines.JobKt.Job$default((kotlinx.coroutines.Job) r7, (int) r3, (java.lang.Object) r7)
            kotlin.coroutines.CoroutineContext r2 = r0.getContext()
            kotlinx.coroutines.Job$Key r4 = kotlinx.coroutines.Job.Key
            kotlin.coroutines.CoroutineContext$Key r4 = (kotlin.coroutines.CoroutineContext.Key) r4
            kotlin.coroutines.CoroutineContext$Element r2 = r2.get(r4)
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
            if (r2 != 0) goto L_0x0053
            goto L_0x005d
        L_0x0053:
            androidx.room.RoomDatabaseKt$createTransactionContext$2 r4 = new androidx.room.RoomDatabaseKt$createTransactionContext$2
            r4.<init>(r7)
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            r2.invokeOnCompletion(r4)
        L_0x005d:
            java.util.concurrent.Executor r2 = r6.getTransactionExecutor()
            java.lang.String r4 = "transactionExecutor"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r4 = r7
            kotlinx.coroutines.Job r4 = (kotlinx.coroutines.Job) r4
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r0 = acquireTransactionThread(r2, r4, r0)
            if (r0 != r1) goto L_0x0076
            return r1
        L_0x0076:
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
        L_0x007a:
            kotlin.coroutines.ContinuationInterceptor r7 = (kotlin.coroutines.ContinuationInterceptor) r7
            androidx.room.TransactionElement r1 = new androidx.room.TransactionElement
            r2 = r6
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
            r1.<init>(r2, r7)
            java.lang.ThreadLocal r0 = r0.getSuspendingTransactionId()
            java.lang.String r2 = "suspendingTransactionId"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            int r6 = java.lang.System.identityHashCode(r6)
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            kotlinx.coroutines.ThreadContextElement r6 = kotlinx.coroutines.ThreadContextElementKt.asContextElement(r0, r6)
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            kotlin.coroutines.CoroutineContext r7 = r7.plus(r1)
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            kotlin.coroutines.CoroutineContext r6 = r7.plus(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabaseKt.createTransactionContext(androidx.room.RoomDatabase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Object acquireTransactionThread(Executor executor, Job job, Continuation<? super ContinuationInterceptor> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new RoomDatabaseKt$acquireTransactionThread$2$1(job));
        try {
            executor.execute(new RoomDatabaseKt$acquireTransactionThread$2$2(cancellableContinuation, job));
        } catch (RejectedExecutionException e) {
            cancellableContinuation.cancel(new IllegalStateException("Unable to acquire a thread to perform the database transaction.", e));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
