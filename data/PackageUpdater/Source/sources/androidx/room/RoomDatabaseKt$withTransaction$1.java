package androidx.room;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@DebugMetadata(mo21448c = "androidx.room.RoomDatabaseKt", mo21449f = "RoomDatabase.kt", mo21450i = {0, 0}, mo21451l = {50, 51}, mo21452m = "withTransaction", mo21453n = {"$this$withTransaction", "block"}, mo21454s = {"L$0", "L$1"})
/* compiled from: RoomDatabase.kt */
final class RoomDatabaseKt$withTransaction$1<R> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    RoomDatabaseKt$withTransaction$1(Continuation<? super RoomDatabaseKt$withTransaction$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RoomDatabaseKt.withTransaction((RoomDatabase) null, (Function1) null, this);
    }
}
