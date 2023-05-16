package androidx.room;

import androidx.room.RoomDatabase;
import androidx.sqlite.p004db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

final class QueryInterceptorStatement implements SupportSQLiteStatement {
    private final List<Object> mBindArgsCache = new ArrayList();
    private final SupportSQLiteStatement mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;
    private final String mSqlStatement;

    QueryInterceptorStatement(SupportSQLiteStatement supportSQLiteStatement, RoomDatabase.QueryCallback queryCallback, String str, Executor executor) {
        this.mDelegate = supportSQLiteStatement;
        this.mQueryCallback = queryCallback;
        this.mSqlStatement = str;
        this.mQueryCallbackExecutor = executor;
    }

    public void execute() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorStatement$$ExternalSyntheticLambda0(this));
        this.mDelegate.execute();
    }

    /* renamed from: lambda$execute$0$androidx-room-QueryInterceptorStatement  reason: not valid java name */
    public /* synthetic */ void m124lambda$execute$0$androidxroomQueryInterceptorStatement() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    public int executeUpdateDelete() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorStatement$$ExternalSyntheticLambda2(this));
        return this.mDelegate.executeUpdateDelete();
    }

    /* renamed from: lambda$executeUpdateDelete$1$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void mo10483x7bfa4cf9() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    public long executeInsert() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorStatement$$ExternalSyntheticLambda1(this));
        return this.mDelegate.executeInsert();
    }

    /* renamed from: lambda$executeInsert$2$androidx-room-QueryInterceptorStatement  reason: not valid java name */
    public /* synthetic */ void m125lambda$executeInsert$2$androidxroomQueryInterceptorStatement() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    /* renamed from: lambda$simpleQueryForLong$3$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void mo10484xa983133b() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    public long simpleQueryForLong() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorStatement$$ExternalSyntheticLambda3(this));
        return this.mDelegate.simpleQueryForLong();
    }

    /* renamed from: lambda$simpleQueryForString$4$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void mo10485x12aaf991() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    public String simpleQueryForString() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorStatement$$ExternalSyntheticLambda4(this));
        return this.mDelegate.simpleQueryForString();
    }

    public void bindNull(int i) {
        saveArgsToCache(i, this.mBindArgsCache.toArray());
        this.mDelegate.bindNull(i);
    }

    public void bindLong(int i, long j) {
        saveArgsToCache(i, Long.valueOf(j));
        this.mDelegate.bindLong(i, j);
    }

    public void bindDouble(int i, double d) {
        saveArgsToCache(i, Double.valueOf(d));
        this.mDelegate.bindDouble(i, d);
    }

    public void bindString(int i, String str) {
        saveArgsToCache(i, str);
        this.mDelegate.bindString(i, str);
    }

    public void bindBlob(int i, byte[] bArr) {
        saveArgsToCache(i, bArr);
        this.mDelegate.bindBlob(i, bArr);
    }

    public void clearBindings() {
        this.mBindArgsCache.clear();
        this.mDelegate.clearBindings();
    }

    public void close() throws IOException {
        this.mDelegate.close();
    }

    private void saveArgsToCache(int i, Object obj) {
        int i2 = i - 1;
        if (i2 >= this.mBindArgsCache.size()) {
            for (int size = this.mBindArgsCache.size(); size <= i2; size++) {
                this.mBindArgsCache.add((Object) null);
            }
        }
        this.mBindArgsCache.set(i2, obj);
    }
}
