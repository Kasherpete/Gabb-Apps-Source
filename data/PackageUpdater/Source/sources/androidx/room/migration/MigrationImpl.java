package androidx.room.migration;

import androidx.sqlite.p004db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0007H\u0016R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, mo20735d2 = {"Landroidx/room/migration/MigrationImpl;", "Landroidx/room/migration/Migration;", "startVersion", "", "endVersion", "migrateCallback", "Lkotlin/Function1;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "", "(IILkotlin/jvm/functions/Function1;)V", "getMigrateCallback", "()Lkotlin/jvm/functions/Function1;", "migrate", "database", "room-ktx_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Migration.kt */
final class MigrationImpl extends Migration {
    private final Function1<SupportSQLiteDatabase, Unit> migrateCallback;

    public final Function1<SupportSQLiteDatabase, Unit> getMigrateCallback() {
        return this.migrateCallback;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MigrationImpl(int i, int i2, Function1<? super SupportSQLiteDatabase, Unit> function1) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(function1, "migrateCallback");
        this.migrateCallback = function1;
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
        this.migrateCallback.invoke(supportSQLiteDatabase);
    }
}
