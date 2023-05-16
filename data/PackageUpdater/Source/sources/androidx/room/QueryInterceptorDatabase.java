package androidx.room;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.room.RoomDatabase;
import androidx.sqlite.p004db.SupportSQLiteDatabase;
import androidx.sqlite.p004db.SupportSQLiteQuery;
import androidx.sqlite.p004db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

final class QueryInterceptorDatabase implements SupportSQLiteDatabase {
    private final SupportSQLiteDatabase mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;

    QueryInterceptorDatabase(SupportSQLiteDatabase supportSQLiteDatabase, RoomDatabase.QueryCallback queryCallback, Executor executor) {
        this.mDelegate = supportSQLiteDatabase;
        this.mQueryCallback = queryCallback;
        this.mQueryCallbackExecutor = executor;
    }

    public SupportSQLiteStatement compileStatement(String str) {
        return new QueryInterceptorStatement(this.mDelegate.compileStatement(str), this.mQueryCallback, str, this.mQueryCallbackExecutor);
    }

    public void beginTransaction() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda0(this));
        this.mDelegate.beginTransaction();
    }

    /* renamed from: lambda$beginTransaction$0$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m116lambda$beginTransaction$0$androidxroomQueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    public void beginTransactionNonExclusive() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda3(this));
        this.mDelegate.beginTransactionNonExclusive();
    }

    /* renamed from: lambda$beginTransactionNonExclusive$1$androidx-room-QueryInterceptorDatabase */
    public /* synthetic */ void mo10455x9d2bf0fa() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda4(this));
        this.mDelegate.beginTransactionWithListener(sQLiteTransactionListener);
    }

    /* renamed from: lambda$beginTransactionWithListener$2$androidx-room-QueryInterceptorDatabase */
    public /* synthetic */ void mo10456xe4527eb0() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda5(this));
        this.mDelegate.beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
    }

    /* renamed from: lambda$beginTransactionWithListenerNonExclusive$3$androidx-room-QueryInterceptorDatabase */
    public /* synthetic */ void mo10457xa78c463e() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    public void endTransaction() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda6(this));
        this.mDelegate.endTransaction();
    }

    /* renamed from: lambda$endTransaction$4$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m117lambda$endTransaction$4$androidxroomQueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("END TRANSACTION", Collections.emptyList());
    }

    /* renamed from: lambda$setTransactionSuccessful$5$androidx-room-QueryInterceptorDatabase */
    public /* synthetic */ void mo10465x7d646086() {
        this.mQueryCallback.onQuery("TRANSACTION SUCCESSFUL", Collections.emptyList());
    }

    public void setTransactionSuccessful() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda7(this));
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

    /* renamed from: lambda$query$6$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m120lambda$query$6$androidxroomQueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, Collections.emptyList());
    }

    public Cursor query(String str) {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda11(this, str));
        return this.mDelegate.query(str);
    }

    public Cursor query(String str, Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(objArr));
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda2(this, str, arrayList));
        return this.mDelegate.query(str, objArr);
    }

    /* renamed from: lambda$query$7$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m121lambda$query$7$androidxroomQueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
        QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        supportSQLiteQuery.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda8(this, supportSQLiteQuery, queryInterceptorProgram));
        return this.mDelegate.query(supportSQLiteQuery);
    }

    /* renamed from: lambda$query$8$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m122lambda$query$8$androidxroomQueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        supportSQLiteQuery.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda9(this, supportSQLiteQuery, queryInterceptorProgram));
        return this.mDelegate.query(supportSQLiteQuery);
    }

    /* renamed from: lambda$query$9$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m123lambda$query$9$androidxroomQueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    public long insert(String str, int i, ContentValues contentValues) throws SQLException {
        return this.mDelegate.insert(str, i, contentValues);
    }

    public int delete(String str, String str2, Object[] objArr) {
        return this.mDelegate.delete(str, str2, objArr);
    }

    public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        return this.mDelegate.update(str, i, contentValues, str2, objArr);
    }

    public void execSQL(String str) throws SQLException {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda10(this, str));
        this.mDelegate.execSQL(str);
    }

    /* renamed from: lambda$execSQL$10$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m118lambda$execSQL$10$androidxroomQueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, new ArrayList(0));
    }

    public void execSQL(String str, Object[] objArr) throws SQLException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(objArr));
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda1(this, str, arrayList));
        this.mDelegate.execSQL(str, arrayList.toArray());
    }

    /* renamed from: lambda$execSQL$11$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m119lambda$execSQL$11$androidxroomQueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
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
        this.mDelegate.setForeignKeyConstraintsEnabled(z);
    }

    public boolean enableWriteAheadLogging() {
        return this.mDelegate.enableWriteAheadLogging();
    }

    public void disableWriteAheadLogging() {
        this.mDelegate.disableWriteAheadLogging();
    }

    public boolean isWriteAheadLoggingEnabled() {
        return this.mDelegate.isWriteAheadLoggingEnabled();
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
}
