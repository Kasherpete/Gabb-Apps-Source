package androidx.room;

import androidx.sqlite.p004db.SupportSQLiteOpenHelper;

interface DelegatingOpenHelper {
    SupportSQLiteOpenHelper getDelegate();
}
