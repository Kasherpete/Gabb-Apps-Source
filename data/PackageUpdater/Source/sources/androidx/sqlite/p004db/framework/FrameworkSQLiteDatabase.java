package androidx.sqlite.p004db.framework;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.Build;
import android.os.CancellationSignal;
import android.text.TextUtils;
import android.util.Pair;
import androidx.sqlite.p004db.SimpleSQLiteQuery;
import androidx.sqlite.p004db.SupportSQLiteCompat;
import androidx.sqlite.p004db.SupportSQLiteDatabase;
import androidx.sqlite.p004db.SupportSQLiteQuery;
import androidx.sqlite.p004db.SupportSQLiteStatement;
import com.datadog.android.webview.internal.log.WebViewLogEventConsumer;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* renamed from: androidx.sqlite.db.framework.FrameworkSQLiteDatabase */
class FrameworkSQLiteDatabase implements SupportSQLiteDatabase {
    private static final String[] CONFLICT_VALUES = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final SQLiteDatabase mDelegate;

    FrameworkSQLiteDatabase(SQLiteDatabase sQLiteDatabase) {
        this.mDelegate = sQLiteDatabase;
    }

    public SupportSQLiteStatement compileStatement(String str) {
        return new FrameworkSQLiteStatement(this.mDelegate.compileStatement(str));
    }

    public void beginTransaction() {
        this.mDelegate.beginTransaction();
    }

    public void beginTransactionNonExclusive() {
        this.mDelegate.beginTransactionNonExclusive();
    }

    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mDelegate.beginTransactionWithListener(sQLiteTransactionListener);
    }

    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mDelegate.beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
    }

    public void endTransaction() {
        this.mDelegate.endTransaction();
    }

    public void setTransactionSuccessful() {
        this.mDelegate.setTransactionSuccessful();
    }

    public boolean inTransaction() {
        return this.mDelegate.inTransaction();
    }

    public boolean isDbLockedByCurrentThread() {
        return this.mDelegate.isDbLockedByCurrentThread();
    }

    public boolean yieldIfContendedSafely() {
        return this.mDelegate.yieldIfContendedSafely();
    }

    public boolean yieldIfContendedSafely(long j) {
        return this.mDelegate.yieldIfContendedSafely(j);
    }

    public boolean isExecPerConnectionSQLSupported() {
        return Build.VERSION.SDK_INT >= 30;
    }

    public void execPerConnectionSQL(String str, Object[] objArr) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mDelegate.execPerConnectionSQL(str, objArr);
            return;
        }
        throw new UnsupportedOperationException("execPerConnectionSQL is not supported on a SDK version lower than 30, current version is: " + Build.VERSION.SDK_INT);
    }

    public int getVersion() {
        return this.mDelegate.getVersion();
    }

    public void setVersion(int i) {
        this.mDelegate.setVersion(i);
    }

    public long getMaximumSize() {
        return this.mDelegate.getMaximumSize();
    }

    public long setMaximumSize(long j) {
        return this.mDelegate.setMaximumSize(j);
    }

    public long getPageSize() {
        return this.mDelegate.getPageSize();
    }

    public void setPageSize(long j) {
        this.mDelegate.setPageSize(j);
    }

    public Cursor query(String str) {
        return query((SupportSQLiteQuery) new SimpleSQLiteQuery(str));
    }

    public Cursor query(String str, Object[] objArr) {
        return query((SupportSQLiteQuery) new SimpleSQLiteQuery(str, objArr));
    }

    public Cursor query(final SupportSQLiteQuery supportSQLiteQuery) {
        return this.mDelegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() {
            public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                supportSQLiteQuery.bindTo(new FrameworkSQLiteProgram(sQLiteQuery));
                return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
            }
        }, supportSQLiteQuery.getSql(), EMPTY_STRING_ARRAY, (String) null);
    }

    public Cursor query(final SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        return SupportSQLiteCompat.Api16Impl.rawQueryWithFactory(this.mDelegate, supportSQLiteQuery.getSql(), EMPTY_STRING_ARRAY, (String) null, cancellationSignal, new SQLiteDatabase.CursorFactory() {
            public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                supportSQLiteQuery.bindTo(new FrameworkSQLiteProgram(sQLiteQuery));
                return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
            }
        });
    }

    public long insert(String str, int i, ContentValues contentValues) throws SQLException {
        return this.mDelegate.insertWithOnConflict(str, (String) null, contentValues, i);
    }

    public int delete(String str, String str2, Object[] objArr) {
        SupportSQLiteStatement compileStatement = compileStatement("DELETE FROM " + str + (TextUtils.isEmpty(str2) ? "" : " WHERE " + str2));
        SimpleSQLiteQuery.bind(compileStatement, objArr);
        return compileStatement.executeUpdateDelete();
    }

    public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        int i2;
        if (contentValues == null || contentValues.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        StringBuilder sb = new StringBuilder(120);
        sb.append("UPDATE ");
        sb.append(CONFLICT_VALUES[i]);
        sb.append(str);
        sb.append(" SET ");
        int size = contentValues.size();
        if (objArr == null) {
            i2 = size;
        } else {
            i2 = objArr.length + size;
        }
        Object[] objArr2 = new Object[i2];
        int i3 = 0;
        for (String next : contentValues.keySet()) {
            sb.append(i3 > 0 ? WebViewLogEventConsumer.DDTAGS_SEPARATOR : "");
            sb.append(next);
            objArr2[i3] = contentValues.get(next);
            sb.append("=?");
            i3++;
        }
        if (objArr != null) {
            for (int i4 = size; i4 < i2; i4++) {
                objArr2[i4] = objArr[i4 - size];
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(" WHERE ");
            sb.append(str2);
        }
        SupportSQLiteStatement compileStatement = compileStatement(sb.toString());
        SimpleSQLiteQuery.bind(compileStatement, objArr2);
        return compileStatement.executeUpdateDelete();
    }

    public void execSQL(String str) throws SQLException {
        this.mDelegate.execSQL(str);
    }

    public void execSQL(String str, Object[] objArr) throws SQLException {
        this.mDelegate.execSQL(str, objArr);
    }

    public boolean isReadOnly() {
        return this.mDelegate.isReadOnly();
    }

    public boolean isOpen() {
        return this.mDelegate.isOpen();
    }

    public boolean needUpgrade(int i) {
        return this.mDelegate.needUpgrade(i);
    }

    public String getPath() {
        return this.mDelegate.getPath();
    }

    public void setLocale(Locale locale) {
        this.mDelegate.setLocale(locale);
    }

    public void setMaxSqlCacheSize(int i) {
        this.mDelegate.setMaxSqlCacheSize(i);
    }

    public void setForeignKeyConstraintsEnabled(boolean z) {
        SupportSQLiteCompat.Api16Impl.setForeignKeyConstraintsEnabled(this.mDelegate, z);
    }

    public boolean enableWriteAheadLogging() {
        return this.mDelegate.enableWriteAheadLogging();
    }

    public void disableWriteAheadLogging() {
        SupportSQLiteCompat.Api16Impl.disableWriteAheadLogging(this.mDelegate);
    }

    public boolean isWriteAheadLoggingEnabled() {
        return SupportSQLiteCompat.Api16Impl.isWriteAheadLoggingEnabled(this.mDelegate);
    }

    public List<Pair<String, String>> getAttachedDbs() {
        return this.mDelegate.getAttachedDbs();
    }

    public boolean isDatabaseIntegrityOk() {
        return this.mDelegate.isDatabaseIntegrityOk();
    }

    public void close() throws IOException {
        this.mDelegate.close();
    }

    /* access modifiers changed from: package-private */
    public boolean isDelegate(SQLiteDatabase sQLiteDatabase) {
        return this.mDelegate == sQLiteDatabase;
    }
}
