package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda9 */
public final /* synthetic */ class C0618x9c579013 implements Function {
    public static final /* synthetic */ C0618x9c579013 INSTANCE = new C0618x9c579013();

    private /* synthetic */ C0618x9c579013() {
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).isDatabaseIntegrityOk());
    }
}
