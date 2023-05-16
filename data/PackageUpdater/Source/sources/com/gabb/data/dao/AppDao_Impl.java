package com.gabb.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p004db.SupportSQLiteStatement;
import com.gabb.data.entities.App;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class AppDao_Impl implements AppDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter<App> __insertionAdapterOfApp;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeletePackage;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfNuke;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfUpdateWithUri;

    public AppDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfApp = new EntityInsertionAdapter<App>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `apps` (`packageName`,`appName`,`versionCode`,`version`,`enabled`,`uri`,`downloadUrl`,`releasedAt`) VALUES (?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, App app) {
                if (app.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, app.getPackageName());
                }
                if (app.getAppName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, app.getAppName());
                }
                supportSQLiteStatement.bindLong(3, app.getVersionCode());
                if (app.getVersion() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, app.getVersion());
                }
                supportSQLiteStatement.bindLong(5, app.getEnabled() ? 1 : 0);
                if (app.getUri() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, app.getUri());
                }
                if (app.getDownloadUrl() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, app.getDownloadUrl());
                }
                if (app.getReleasedAt() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, app.getReleasedAt());
                }
            }
        };
        this.__preparedStmtOfUpdateWithUri = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE apps SET uri = ? WHERE packageName = ?";
            }
        };
        this.__preparedStmtOfDeletePackage = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM apps WHERE packageName = ?";
            }
        };
        this.__preparedStmtOfNuke = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM apps";
            }
        };
    }

    public Object upsert(final App[] appArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                AppDao_Impl.this.__db.beginTransaction();
                try {
                    AppDao_Impl.this.__insertionAdapterOfApp.insert((T[]) appArr);
                    AppDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    AppDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object updateWithUri(final String str, final String str2, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = AppDao_Impl.this.__preparedStmtOfUpdateWithUri.acquire();
                String str = str2;
                if (str == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, str);
                }
                String str2 = str;
                if (str2 == null) {
                    acquire.bindNull(2);
                } else {
                    acquire.bindString(2, str2);
                }
                AppDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    AppDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    AppDao_Impl.this.__db.endTransaction();
                    AppDao_Impl.this.__preparedStmtOfUpdateWithUri.release(acquire);
                }
            }
        }, continuation);
    }

    public Object deletePackage(final String str, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = AppDao_Impl.this.__preparedStmtOfDeletePackage.acquire();
                String str = str;
                if (str == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, str);
                }
                AppDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    AppDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    AppDao_Impl.this.__db.endTransaction();
                    AppDao_Impl.this.__preparedStmtOfDeletePackage.release(acquire);
                }
            }
        }, continuation);
    }

    public Object nuke(Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = AppDao_Impl.this.__preparedStmtOfNuke.acquire();
                AppDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    AppDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    AppDao_Impl.this.__db.endTransaction();
                    AppDao_Impl.this.__preparedStmtOfNuke.release(acquire);
                }
            }
        }, continuation);
    }

    public Object getAllApps(Continuation<? super List<App>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM apps", 0);
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<List<App>>() {
            public List<App> call() throws Exception {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                String str6;
                Cursor query = DBUtil.query(AppDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "packageName");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "appName");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "versionCode");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "version");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "enabled");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "uri");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "downloadUrl");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "releasedAt");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow);
                        }
                        if (query.isNull(columnIndexOrThrow2)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow2);
                        }
                        long j = query.getLong(columnIndexOrThrow3);
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        boolean z = query.getInt(columnIndexOrThrow5) != 0;
                        if (query.isNull(columnIndexOrThrow6)) {
                            str4 = null;
                        } else {
                            str4 = query.getString(columnIndexOrThrow6);
                        }
                        if (query.isNull(columnIndexOrThrow7)) {
                            str5 = null;
                        } else {
                            str5 = query.getString(columnIndexOrThrow7);
                        }
                        if (query.isNull(columnIndexOrThrow8)) {
                            str6 = null;
                        } else {
                            str6 = query.getString(columnIndexOrThrow8);
                        }
                        arrayList.add(new App(str, str2, j, str3, z, str4, str5, str6));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object getAppsWithoutUri(Continuation<? super List<App>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM apps WHERE uri == null", 0);
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<List<App>>() {
            public List<App> call() throws Exception {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                String str6;
                Cursor query = DBUtil.query(AppDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "packageName");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "appName");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "versionCode");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "version");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "enabled");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "uri");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "downloadUrl");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "releasedAt");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow);
                        }
                        if (query.isNull(columnIndexOrThrow2)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow2);
                        }
                        long j = query.getLong(columnIndexOrThrow3);
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        boolean z = query.getInt(columnIndexOrThrow5) != 0;
                        if (query.isNull(columnIndexOrThrow6)) {
                            str4 = null;
                        } else {
                            str4 = query.getString(columnIndexOrThrow6);
                        }
                        if (query.isNull(columnIndexOrThrow7)) {
                            str5 = null;
                        } else {
                            str5 = query.getString(columnIndexOrThrow7);
                        }
                        if (query.isNull(columnIndexOrThrow8)) {
                            str6 = null;
                        } else {
                            str6 = query.getString(columnIndexOrThrow8);
                        }
                        arrayList.add(new App(str, str2, j, str3, z, str4, str5, str6));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object getAppsWithUri(Continuation<? super List<App>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM apps WHERE uri != null", 0);
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<List<App>>() {
            public List<App> call() throws Exception {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                String str6;
                Cursor query = DBUtil.query(AppDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "packageName");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "appName");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "versionCode");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "version");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "enabled");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "uri");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "downloadUrl");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "releasedAt");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow);
                        }
                        if (query.isNull(columnIndexOrThrow2)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow2);
                        }
                        long j = query.getLong(columnIndexOrThrow3);
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        boolean z = query.getInt(columnIndexOrThrow5) != 0;
                        if (query.isNull(columnIndexOrThrow6)) {
                            str4 = null;
                        } else {
                            str4 = query.getString(columnIndexOrThrow6);
                        }
                        if (query.isNull(columnIndexOrThrow7)) {
                            str5 = null;
                        } else {
                            str5 = query.getString(columnIndexOrThrow7);
                        }
                        if (query.isNull(columnIndexOrThrow8)) {
                            str6 = null;
                        } else {
                            str6 = query.getString(columnIndexOrThrow8);
                        }
                        arrayList.add(new App(str, str2, j, str3, z, str4, str5, str6));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
