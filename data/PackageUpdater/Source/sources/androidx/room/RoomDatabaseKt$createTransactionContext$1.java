package androidx.room;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "androidx.room.RoomDatabaseKt", mo21449f = "RoomDatabase.kt", mo21450i = {0, 0}, mo21451l = {99}, mo21452m = "createTransactionContext", mo21453n = {"$this$createTransactionContext", "controlJob"}, mo21454s = {"L$0", "L$1"})
/* compiled from: RoomDatabase.kt */
final class RoomDatabaseKt$createTransactionContext$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    RoomDatabaseKt$createTransactionContext$1(Continuation<? super RoomDatabaseKt$createTransactionContext$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RoomDatabaseKt.createTransactionContext((RoomDatabase) null, this);
    }
}
