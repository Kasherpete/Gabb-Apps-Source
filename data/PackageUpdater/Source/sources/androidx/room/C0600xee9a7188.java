package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p004db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda13 */
public final /* synthetic */ class C0600xee9a7188 implements Function {
    public static final /* synthetic */ C0600xee9a7188 INSTANCE = new C0600xee9a7188();

    private /* synthetic */ C0600xee9a7188() {
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).yieldIfContendedSafely());
    }
}
