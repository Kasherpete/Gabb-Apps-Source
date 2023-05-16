package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda15 */
public final /* synthetic */ class C0602xee9a718a implements Function {
    public static final /* synthetic */ C0602xee9a718a INSTANCE = new C0602xee9a718a();

    private /* synthetic */ C0602xee9a718a() {
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((SupportSQLiteDatabase) obj).getPageSize());
    }
}
