package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda6 */
public final /* synthetic */ class C0615x9c579010 implements Function {
    public static final /* synthetic */ C0615x9c579010 INSTANCE = new C0615x9c579010();

    private /* synthetic */ C0615x9c579010() {
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteDatabase) obj).getPath();
    }
}
