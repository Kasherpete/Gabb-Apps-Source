package com.gabb.packageupdater.data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.p004db.SupportSQLiteDatabase;
import androidx.sqlite.p004db.SupportSQLiteOpenHelper;
import com.gabb.data.dao.AppDao;
import com.gabb.data.dao.AppDao_Impl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class GabbRoomDatabase_Impl extends GabbRoomDatabase {
    private volatile AppDao _appDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(5) {
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `apps` (`packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `versionCode` INTEGER NOT NULL, `version` TEXT NOT NULL, `enabled` INTEGER NOT NULL, `uri` TEXT, `downloadUrl` TEXT NOT NULL, `releasedAt` TEXT NOT NULL, PRIMARY KEY(`packageName`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `contentUris` (`packageName` TEXT NOT NULL, `contentUri` TEXT NOT NULL, PRIMARY KEY(`packageName`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3b2b424282d1340cf53c9b81bb725eb2')");
            }

            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `apps`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `contentUris`");
                if (GabbRoomDatabase_Impl.this.mCallbacks != null) {
                    int size = GabbRoomDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) GabbRoomDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (GabbRoomDatabase_Impl.this.mCallbacks != null) {
                    int size = GabbRoomDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) GabbRoomDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase unused = GabbRoomDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                GabbRoomDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (GabbRoomDatabase_Impl.this.mCallbacks != null) {
                    int size = GabbRoomDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) GabbRoomDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            /* access modifiers changed from: protected */
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase supportSQLiteDatabase2 = supportSQLiteDatabase;
                HashMap hashMap = new HashMap(8);
                hashMap.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 1, (String) null, 1));
                hashMap.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, (String) null, 1));
                hashMap.put("versionCode", new TableInfo.Column("versionCode", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("version", new TableInfo.Column("version", "TEXT", true, 0, (String) null, 1));
                hashMap.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("uri", new TableInfo.Column("uri", "TEXT", false, 0, (String) null, 1));
                hashMap.put("downloadUrl", new TableInfo.Column("downloadUrl", "TEXT", true, 0, (String) null, 1));
                hashMap.put("releasedAt", new TableInfo.Column("releasedAt", "TEXT", true, 0, (String) null, 1));
                TableInfo tableInfo = new TableInfo("apps", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase2, "apps");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "apps(com.gabb.data.entities.App).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(2);
                hashMap2.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 1, (String) null, 1));
                hashMap2.put("contentUri", new TableInfo.Column("contentUri", "TEXT", true, 0, (String) null, 1));
                TableInfo tableInfo2 = new TableInfo("contentUris", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase2, "contentUris");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "contentUris(com.gabb.data.entities.AppContentUri).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                return new RoomOpenHelper.ValidationResult(true, (String) null);
            }
        }, "3b2b424282d1340cf53c9b81bb725eb2", "29e00a26dda1d61994315bd611f72653")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "apps", "contentUris");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `apps`");
            writableDatabase.execSQL("DELETE FROM `contentUris`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    /* access modifiers changed from: protected */
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(AppDao.class, AppDao_Impl.getRequiredConverters());
        return hashMap;
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return Arrays.asList(new Migration[0]);
    }

    public AppDao appDao() {
        AppDao appDao;
        if (this._appDao != null) {
            return this._appDao;
        }
        synchronized (this) {
            if (this._appDao == null) {
                this._appDao = new AppDao_Impl(this);
            }
            appDao = this._appDao;
        }
        return appDao;
    }
}
