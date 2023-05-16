package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteStatement;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda1 */
public final /* synthetic */ class C0620xd7aa7fff implements Function {
    public static final /* synthetic */ C0620xd7aa7fff INSTANCE = new C0620xd7aa7fff();

    private /* synthetic */ C0620xd7aa7fff() {
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteStatement) obj).execute();
    }
}
