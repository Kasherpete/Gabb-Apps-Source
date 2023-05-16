package androidx.room.migration;

import androidx.sqlite.p004db.SupportSQLiteDatabase;

public interface AutoMigrationSpec {
    void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
    }
}
