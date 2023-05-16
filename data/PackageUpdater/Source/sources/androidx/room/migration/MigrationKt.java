package androidx.room.migration;

import androidx.sqlite.p004db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¨\u0006\t"}, mo20735d2 = {"Migration", "Landroidx/room/migration/Migration;", "startVersion", "", "endVersion", "migrate", "Lkotlin/Function1;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "", "room-ktx_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Migration.kt */
public final class MigrationKt {
    public static final Migration Migration(int i, int i2, Function1<? super SupportSQLiteDatabase, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "migrate");
        return new MigrationImpl(i, i2, function1);
    }
}
