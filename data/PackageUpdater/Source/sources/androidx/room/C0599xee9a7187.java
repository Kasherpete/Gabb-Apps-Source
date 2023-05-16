package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda12 */
public final /* synthetic */ class C0599xee9a7187 implements Function {
    public static final /* synthetic */ C0599xee9a7187 INSTANCE = new C0599xee9a7187();

    private /* synthetic */ C0599xee9a7187() {
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).isReadOnly());
    }
}
