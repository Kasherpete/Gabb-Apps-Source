package androidx.sqlite.p004db.framework;

import androidx.sqlite.p004db.SupportSQLiteOpenHelper;

/* renamed from: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory */
public final class FrameworkSQLiteOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new FrameworkSQLiteOpenHelper(configuration.context, configuration.name, configuration.callback, configuration.useNoBackupDirectory);
    }
}
