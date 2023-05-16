package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteStatement;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda5 */
public final /* synthetic */ class C0624xd7aa8003 implements Function {
    public static final /* synthetic */ C0624xd7aa8003 INSTANCE = new C0624xd7aa8003();

    private /* synthetic */ C0624xd7aa8003() {
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((SupportSQLiteStatement) obj).simpleQueryForLong());
    }
}
