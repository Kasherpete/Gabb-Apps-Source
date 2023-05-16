package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda17 */
public final /* synthetic */ class C0604xee9a718c implements Function {
    public final /* synthetic */ long f$0;

    public /* synthetic */ C0604xee9a718c(long j) {
        this.f$0 = j;
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((SupportSQLiteDatabase) obj).setMaximumSize(this.f$0));
    }
}
