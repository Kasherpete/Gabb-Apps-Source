package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteStatement;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda4 */
public final /* synthetic */ class C0623xd7aa8002 implements Function {
    public static final /* synthetic */ C0623xd7aa8002 INSTANCE = new C0623xd7aa8002();

    private /* synthetic */ C0623xd7aa8002() {
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((SupportSQLiteStatement) obj).executeInsert());
    }
}
