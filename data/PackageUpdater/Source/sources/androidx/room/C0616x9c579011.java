package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda7 */
public final /* synthetic */ class C0616x9c579011 implements Function {
    public static final /* synthetic */ C0616x9c579011 INSTANCE = new C0616x9c579011();

    private /* synthetic */ C0616x9c579011() {
    }

    public final Object apply(Object obj) {
        return Integer.valueOf(((SupportSQLiteDatabase) obj).getVersion());
    }
}
