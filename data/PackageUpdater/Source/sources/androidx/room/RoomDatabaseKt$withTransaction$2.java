package androidx.room;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo20734d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@"}, mo20735d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "androidx.room.RoomDatabaseKt$withTransaction$2", mo21449f = "RoomDatabase.kt", mo21450i = {0}, mo21451l = {58}, mo21452m = "invokeSuspend", mo21453n = {"transactionElement"}, mo21454s = {"L$0"})
/* compiled from: RoomDatabase.kt */
final class RoomDatabaseKt$withTransaction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
    final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
    final /* synthetic */ RoomDatabase $this_withTransaction;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoomDatabaseKt$withTransaction$2(RoomDatabase roomDatabase, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super RoomDatabaseKt$withTransaction$2> continuation) {
        super(2, continuation);
        this.$this_withTransaction = roomDatabase;
        this.$block = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RoomDatabaseKt$withTransaction$2 roomDatabaseKt$withTransaction$2 = new RoomDatabaseKt$withTransaction$2(this.$this_withTransaction, this.$block, continuation);
        roomDatabaseKt$withTransaction$2.L$0 = obj;
        return roomDatabaseKt$withTransaction$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
        return ((RoomDatabaseKt$withTransaction$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        TransactionElement transactionElement;
        TransactionElement transactionElement2;
        Throwable th;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineContext.Element element = ((CoroutineScope) this.L$0).getCoroutineContext().get(TransactionElement.Key);
            Intrinsics.checkNotNull(element);
            transactionElement = (TransactionElement) element;
            transactionElement.acquire();
            try {
                this.$this_withTransaction.beginTransaction();
                try {
                    Function1<Continuation<? super R>, Object> function1 = this.$block;
                    this.L$0 = transactionElement;
                    this.label = 1;
                    Object invoke = function1.invoke(this);
                    if (invoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    transactionElement2 = transactionElement;
                    obj = invoke;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    transactionElement2 = transactionElement;
                    th = th3;
                    this.$this_withTransaction.endTransaction();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                transactionElement.release();
                throw th;
            }
        } else if (i == 1) {
            transactionElement2 = (TransactionElement) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th5) {
                th = th5;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$this_withTransaction.setTransactionSuccessful();
        try {
            this.$this_withTransaction.endTransaction();
            transactionElement2.release();
            return obj;
        } catch (Throwable th6) {
            th = th6;
            transactionElement = transactionElement2;
            transactionElement.release();
            throw th;
        }
    }
}
