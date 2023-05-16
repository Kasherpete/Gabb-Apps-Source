package com.mixpanel.android.mpmetrics;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.mixpanel.android.util.MPLog;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class MPDbAdapter {
    /* access modifiers changed from: private */
    public static final String ANONYMOUS_PEOPLE_TIME_INDEX = ("CREATE INDEX IF NOT EXISTS time_idx ON " + Table.ANONYMOUS_PEOPLE.getName() + " (" + KEY_CREATED_AT + ");");
    public static final int AUTOMATIC_DATA_COLUMN_INDEX = 3;
    public static final int CREATED_AT_COLUMN_INDEX = 2;
    /* access modifiers changed from: private */
    public static final String CREATE_ANONYMOUS_PEOPLE_TABLE = ("CREATE TABLE " + Table.ANONYMOUS_PEOPLE.getName() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DATA + " STRING NOT NULL, " + KEY_CREATED_AT + " INTEGER NOT NULL, " + KEY_AUTOMATIC_DATA + " INTEGER DEFAULT 0, " + KEY_TOKEN + " STRING NOT NULL DEFAULT '')");
    /* access modifiers changed from: private */
    public static final String CREATE_EVENTS_TABLE = ("CREATE TABLE " + Table.EVENTS.getName() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DATA + " STRING NOT NULL, " + KEY_CREATED_AT + " INTEGER NOT NULL, " + KEY_AUTOMATIC_DATA + " INTEGER DEFAULT 0, " + KEY_TOKEN + " STRING NOT NULL DEFAULT '')");
    /* access modifiers changed from: private */
    public static final String CREATE_GROUPS_TABLE = ("CREATE TABLE " + Table.GROUPS.getName() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DATA + " STRING NOT NULL, " + KEY_CREATED_AT + " INTEGER NOT NULL, " + KEY_AUTOMATIC_DATA + " INTEGER DEFAULT 0, " + KEY_TOKEN + " STRING NOT NULL DEFAULT '')");
    /* access modifiers changed from: private */
    public static final String CREATE_PEOPLE_TABLE = ("CREATE TABLE " + Table.PEOPLE.getName() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DATA + " STRING NOT NULL, " + KEY_CREATED_AT + " INTEGER NOT NULL, " + KEY_AUTOMATIC_DATA + " INTEGER DEFAULT 0, " + KEY_TOKEN + " STRING NOT NULL DEFAULT '')");
    private static final String DATABASE_NAME = "mixpanel";
    private static final int DATABASE_VERSION = 7;
    public static final int DATA_COLUMN_INDEX = 1;
    public static final int DB_OUT_OF_MEMORY_ERROR = -2;
    public static final int DB_UNDEFINED_CODE = -3;
    public static final int DB_UPDATE_ERROR = -1;
    /* access modifiers changed from: private */
    public static final String EVENTS_TIME_INDEX = ("CREATE INDEX IF NOT EXISTS time_idx ON " + Table.EVENTS.getName() + " (" + KEY_CREATED_AT + ");");
    /* access modifiers changed from: private */
    public static final String GROUPS_TIME_INDEX = ("CREATE INDEX IF NOT EXISTS time_idx ON " + Table.GROUPS.getName() + " (" + KEY_CREATED_AT + ");");
    public static final int ID_COLUMN_INDEX = 0;
    public static final String KEY_AUTOMATIC_DATA = "automatic_data";
    public static final String KEY_CREATED_AT = "created_at";
    public static final String KEY_DATA = "data";
    public static final String KEY_TOKEN = "token";
    private static final String LOGTAG = "MixpanelAPI.Database";
    private static final int MAX_DB_VERSION = 7;
    private static final int MIN_DB_VERSION = 4;
    /* access modifiers changed from: private */
    public static final String PEOPLE_TIME_INDEX = ("CREATE INDEX IF NOT EXISTS time_idx ON " + Table.PEOPLE.getName() + " (" + KEY_CREATED_AT + ");");
    public static final int TOKEN_COLUMN_INDEX = 4;
    private static final Map<Context, MPDbAdapter> sInstances = new HashMap();
    private final MPDatabaseHelper mDb;

    public enum Table {
        EVENTS("events"),
        PEOPLE("people"),
        ANONYMOUS_PEOPLE("anonymous_people"),
        GROUPS("groups");
        
        private final String mTableName;

        private Table(String str) {
            this.mTableName = str;
        }

        public String getName() {
            return this.mTableName;
        }
    }

    private static class MPDatabaseHelper extends SQLiteOpenHelper {
        /* access modifiers changed from: private */
        public final MPConfig mConfig;
        private final Context mContext;
        /* access modifiers changed from: private */
        public final File mDatabaseFile;

        MPDatabaseHelper(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 7);
            this.mDatabaseFile = context.getDatabasePath(str);
            this.mConfig = MPConfig.getInstance(context);
            this.mContext = context;
        }

        public void deleteDatabase() {
            close();
            this.mDatabaseFile.delete();
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            MPLog.m65v(MPDbAdapter.LOGTAG, "Creating a new Mixpanel events DB");
            sQLiteDatabase.execSQL(MPDbAdapter.CREATE_EVENTS_TABLE);
            sQLiteDatabase.execSQL(MPDbAdapter.CREATE_PEOPLE_TABLE);
            sQLiteDatabase.execSQL(MPDbAdapter.CREATE_GROUPS_TABLE);
            sQLiteDatabase.execSQL(MPDbAdapter.CREATE_ANONYMOUS_PEOPLE_TABLE);
            sQLiteDatabase.execSQL(MPDbAdapter.EVENTS_TIME_INDEX);
            sQLiteDatabase.execSQL(MPDbAdapter.PEOPLE_TIME_INDEX);
            sQLiteDatabase.execSQL(MPDbAdapter.GROUPS_TIME_INDEX);
            sQLiteDatabase.execSQL(MPDbAdapter.ANONYMOUS_PEOPLE_TIME_INDEX);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            MPLog.m65v(MPDbAdapter.LOGTAG, "Upgrading app, replacing Mixpanel events DB");
            if (i < 4 || i2 > 7) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.EVENTS.getName());
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.PEOPLE.getName());
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.GROUPS.getName());
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.ANONYMOUS_PEOPLE.getName());
                sQLiteDatabase.execSQL(MPDbAdapter.CREATE_EVENTS_TABLE);
                sQLiteDatabase.execSQL(MPDbAdapter.CREATE_PEOPLE_TABLE);
                sQLiteDatabase.execSQL(MPDbAdapter.CREATE_GROUPS_TABLE);
                sQLiteDatabase.execSQL(MPDbAdapter.CREATE_ANONYMOUS_PEOPLE_TABLE);
                sQLiteDatabase.execSQL(MPDbAdapter.EVENTS_TIME_INDEX);
                sQLiteDatabase.execSQL(MPDbAdapter.PEOPLE_TIME_INDEX);
                sQLiteDatabase.execSQL(MPDbAdapter.GROUPS_TIME_INDEX);
                sQLiteDatabase.execSQL(MPDbAdapter.ANONYMOUS_PEOPLE_TIME_INDEX);
                return;
            }
            if (i == 4) {
                migrateTableFrom4To5(sQLiteDatabase);
                migrateTableFrom5To6(sQLiteDatabase);
                migrateTableFrom6To7(sQLiteDatabase);
            }
            if (i == 5) {
                migrateTableFrom5To6(sQLiteDatabase);
                migrateTableFrom6To7(sQLiteDatabase);
            }
            if (i == 6) {
                migrateTableFrom6To7(sQLiteDatabase);
            }
        }

        public boolean aboveMemThreshold() {
            if (!this.mDatabaseFile.exists()) {
                return false;
            }
            if (this.mDatabaseFile.length() > Math.max(this.mDatabaseFile.getUsableSpace(), (long) this.mConfig.getMinimumDatabaseLimit()) || this.mDatabaseFile.length() > ((long) this.mConfig.getMaximumDatabaseLimit())) {
                return true;
            }
            return false;
        }

        private void migrateTableFrom4To5(SQLiteDatabase sQLiteDatabase) {
            int i;
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            sQLiteDatabase2.execSQL("ALTER TABLE " + Table.EVENTS.getName() + " ADD COLUMN " + MPDbAdapter.KEY_AUTOMATIC_DATA + " INTEGER DEFAULT 0");
            sQLiteDatabase2.execSQL("ALTER TABLE " + Table.PEOPLE.getName() + " ADD COLUMN " + MPDbAdapter.KEY_AUTOMATIC_DATA + " INTEGER DEFAULT 0");
            sQLiteDatabase2.execSQL("ALTER TABLE " + Table.EVENTS.getName() + " ADD COLUMN " + MPDbAdapter.KEY_TOKEN + " STRING NOT NULL DEFAULT ''");
            sQLiteDatabase2.execSQL("ALTER TABLE " + Table.PEOPLE.getName() + " ADD COLUMN " + MPDbAdapter.KEY_TOKEN + " STRING NOT NULL DEFAULT ''");
            Cursor rawQuery = sQLiteDatabase2.rawQuery("SELECT * FROM " + Table.EVENTS.getName(), (String[]) null);
            while (rawQuery.moveToNext()) {
                try {
                    sQLiteDatabase2.execSQL("UPDATE " + Table.EVENTS.getName() + " SET " + MPDbAdapter.KEY_TOKEN + " = '" + new JSONObject(rawQuery.getString(rawQuery.getColumnIndex(MPDbAdapter.KEY_DATA) >= 0 ? rawQuery.getColumnIndex(MPDbAdapter.KEY_DATA) : 1)).getJSONObject("properties").getString(MPDbAdapter.KEY_TOKEN) + "' WHERE _id = " + rawQuery.getInt(rawQuery.getColumnIndex("_id") >= 0 ? rawQuery.getColumnIndex("_id") : 0));
                } catch (JSONException unused) {
                    sQLiteDatabase2.delete(Table.EVENTS.getName(), "_id = " + 0, (String[]) null);
                }
            }
            Cursor rawQuery2 = sQLiteDatabase2.rawQuery("SELECT * FROM " + Table.PEOPLE.getName(), (String[]) null);
            while (rawQuery2.moveToNext()) {
                try {
                    String string = new JSONObject(rawQuery2.getString(rawQuery2.getColumnIndex(MPDbAdapter.KEY_DATA) >= 0 ? rawQuery2.getColumnIndex(MPDbAdapter.KEY_DATA) : 1)).getString("$token");
                    i = rawQuery2.getInt(rawQuery2.getColumnIndex("_id") >= 0 ? rawQuery2.getColumnIndex("_id") : 0);
                    try {
                        sQLiteDatabase2.execSQL("UPDATE " + Table.PEOPLE.getName() + " SET " + MPDbAdapter.KEY_TOKEN + " = '" + string + "' WHERE _id = " + i);
                    } catch (JSONException unused2) {
                    }
                } catch (JSONException unused3) {
                    i = 0;
                    sQLiteDatabase2.delete(Table.PEOPLE.getName(), "_id = " + i, (String[]) null);
                }
            }
        }

        private void migrateTableFrom5To6(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(MPDbAdapter.CREATE_GROUPS_TABLE);
            sQLiteDatabase.execSQL(MPDbAdapter.GROUPS_TIME_INDEX);
        }

        private void migrateTableFrom6To7(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(MPDbAdapter.CREATE_ANONYMOUS_PEOPLE_TABLE);
            sQLiteDatabase.execSQL(MPDbAdapter.ANONYMOUS_PEOPLE_TIME_INDEX);
            File file = new File(this.mContext.getApplicationInfo().dataDir, "shared_prefs");
            if (file.exists() && file.isDirectory()) {
                for (String split : file.list(new FilenameFilter() {
                    public boolean accept(File file, String str) {
                        return str.startsWith("com.mixpanel.android.mpmetrics.MixpanelAPI_");
                    }
                })) {
                    SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(split.split("\\.xml")[0], 0);
                    String string = sharedPreferences.getString("waiting_array", (String) null);
                    if (string != null) {
                        try {
                            JSONArray jSONArray = new JSONArray(string);
                            sQLiteDatabase.beginTransaction();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                try {
                                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                                    String string2 = jSONObject.getString("$token");
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put(MPDbAdapter.KEY_DATA, jSONObject.toString());
                                    contentValues.put(MPDbAdapter.KEY_CREATED_AT, Long.valueOf(System.currentTimeMillis()));
                                    contentValues.put(MPDbAdapter.KEY_AUTOMATIC_DATA, false);
                                    contentValues.put(MPDbAdapter.KEY_TOKEN, string2);
                                    sQLiteDatabase.insert(Table.ANONYMOUS_PEOPLE.getName(), (String) null, contentValues);
                                } catch (JSONException unused) {
                                }
                            }
                            sQLiteDatabase.setTransactionSuccessful();
                            sQLiteDatabase.endTransaction();
                        } catch (JSONException unused2) {
                        } catch (Throwable th) {
                            sQLiteDatabase.endTransaction();
                            throw th;
                        }
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.remove("waiting_array");
                        edit.apply();
                    }
                }
            }
        }
    }

    public MPDbAdapter(Context context) {
        this(context, DATABASE_NAME);
    }

    public MPDbAdapter(Context context, String str) {
        this.mDb = new MPDatabaseHelper(context, str);
    }

    public static MPDbAdapter getInstance(Context context) {
        MPDbAdapter mPDbAdapter;
        Map<Context, MPDbAdapter> map = sInstances;
        synchronized (map) {
            Context applicationContext = context.getApplicationContext();
            if (!map.containsKey(applicationContext)) {
                mPDbAdapter = new MPDbAdapter(applicationContext);
                map.put(applicationContext, mPDbAdapter);
            } else {
                mPDbAdapter = map.get(applicationContext);
            }
        }
        return mPDbAdapter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0081, code lost:
        if (r2 != null) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0083, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0099, code lost:
        if (r2 != null) goto L_0x0083;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008f A[Catch:{ all -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int addJSON(org.json.JSONObject r8, java.lang.String r9, com.mixpanel.android.mpmetrics.MPDbAdapter.Table r10) {
        /*
            r7 = this;
            boolean r0 = r7.aboveMemThreshold()
            java.lang.String r1 = "MixpanelAPI.Database"
            if (r0 == 0) goto L_0x000f
            java.lang.String r7 = "There is not enough space left on the device or the data was over the maximum size limit so it was discarded"
            com.mixpanel.android.util.MPLog.m61e(r1, r7)
            r7 = -2
            return r7
        L_0x000f:
            java.lang.String r10 = r10.getName()
            r0 = -1
            r2 = 0
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r3 = r7.mDb     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            android.database.sqlite.SQLiteDatabase r3 = r3.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.String r5 = "data"
            java.lang.String r8 = r8.toString()     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            r4.put(r5, r8)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.String r8 = "created_at"
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            r4.put(r8, r5)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.String r8 = "token"
            r4.put(r8, r9)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            r3.insert(r10, r2, r4)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            r8.<init>()     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.String r4 = "SELECT COUNT(*) FROM "
            java.lang.StringBuilder r8 = r8.append(r4)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.StringBuilder r8 = r8.append(r10)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.String r10 = " WHERE token='"
            java.lang.StringBuilder r8 = r8.append(r10)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.String r9 = "'"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            java.lang.String r8 = r8.toString()     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            android.database.Cursor r8 = r3.rawQuery(r8, r2)     // Catch:{ SQLiteException -> 0x0087, OutOfMemoryError -> 0x007c }
            r8.moveToFirst()     // Catch:{ SQLiteException -> 0x0088, OutOfMemoryError -> 0x0078 }
            r9 = 0
            int r0 = r8.getInt(r9)     // Catch:{ SQLiteException -> 0x0088, OutOfMemoryError -> 0x0078 }
            if (r8 == 0) goto L_0x0072
            r8.close()
        L_0x0072:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r7 = r7.mDb
            r7.close()
            goto L_0x009c
        L_0x0078:
            r2 = r8
            goto L_0x007c
        L_0x007a:
            r9 = move-exception
            goto L_0x009f
        L_0x007c:
            java.lang.String r8 = "Out of memory when adding Mixpanel data to table"
            com.mixpanel.android.util.MPLog.m61e(r1, r8)     // Catch:{ all -> 0x007a }
            if (r2 == 0) goto L_0x0072
        L_0x0083:
            r2.close()
            goto L_0x0072
        L_0x0087:
            r8 = r2
        L_0x0088:
            java.lang.String r9 = "Could not add Mixpanel data to table"
            com.mixpanel.android.util.MPLog.m61e(r1, r9)     // Catch:{ all -> 0x009d }
            if (r8 == 0) goto L_0x0093
            r8.close()     // Catch:{ all -> 0x009d }
            goto L_0x0094
        L_0x0093:
            r2 = r8
        L_0x0094:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r8 = r7.mDb     // Catch:{ all -> 0x007a }
            r8.deleteDatabase()     // Catch:{ all -> 0x007a }
            if (r2 == 0) goto L_0x0072
            goto L_0x0083
        L_0x009c:
            return r0
        L_0x009d:
            r9 = move-exception
            r2 = r8
        L_0x009f:
            if (r2 == 0) goto L_0x00a4
            r2.close()
        L_0x00a4:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r7 = r7.mDb
            r7.close()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.MPDbAdapter.addJSON(org.json.JSONObject, java.lang.String, com.mixpanel.android.mpmetrics.MPDbAdapter$Table):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(17:12|13|(1:15)(1:16)|17|(1:19)(1:20)|21|(1:23)(1:24)|25|(1:27)(1:28)|29|(1:31)(1:32)|33|34|67|66|10|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0064 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x006a A[SYNTHETIC, Splitter:B:12:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0145 A[Catch:{ all -> 0x015a }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x015e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int pushAnonymousUpdatesToPeopleDb(java.lang.String r14, java.lang.String r15) {
        /*
            r13 = this;
            java.lang.String r0 = "_id"
            java.lang.String r1 = "data"
            java.lang.String r2 = "automatic_data"
            java.lang.String r3 = "created_at"
            java.lang.String r4 = "token"
            boolean r5 = r13.aboveMemThreshold()
            java.lang.String r6 = "MixpanelAPI.Database"
            if (r5 == 0) goto L_0x0019
            java.lang.String r13 = "There is not enough space left on the device or the data was over the maximum size limit so it was discarded"
            com.mixpanel.android.util.MPLog.m61e(r6, r13)
            r13 = -2
            return r13
        L_0x0019:
            r5 = -1
            r7 = 0
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r8 = r13.mDb     // Catch:{ SQLiteException -> 0x011f }
            android.database.sqlite.SQLiteDatabase r8 = r8.getWritableDatabase()     // Catch:{ SQLiteException -> 0x011f }
            java.lang.StringBuffer r9 = new java.lang.StringBuffer     // Catch:{ SQLiteException -> 0x011f }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x011f }
            r10.<init>()     // Catch:{ SQLiteException -> 0x011f }
            java.lang.String r11 = "SELECT * FROM "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ SQLiteException -> 0x011f }
            com.mixpanel.android.mpmetrics.MPDbAdapter$Table r11 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.ANONYMOUS_PEOPLE     // Catch:{ SQLiteException -> 0x011f }
            java.lang.String r11 = r11.getName()     // Catch:{ SQLiteException -> 0x011f }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ SQLiteException -> 0x011f }
            java.lang.String r11 = " WHERE "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ SQLiteException -> 0x011f }
            java.lang.StringBuilder r10 = r10.append(r4)     // Catch:{ SQLiteException -> 0x011f }
            java.lang.String r11 = " = '"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ SQLiteException -> 0x011f }
            java.lang.StringBuilder r14 = r10.append(r14)     // Catch:{ SQLiteException -> 0x011f }
            java.lang.String r10 = "'"
            java.lang.StringBuilder r14 = r14.append(r10)     // Catch:{ SQLiteException -> 0x011f }
            java.lang.String r14 = r14.toString()     // Catch:{ SQLiteException -> 0x011f }
            r9.<init>(r14)     // Catch:{ SQLiteException -> 0x011f }
            java.lang.String r14 = r9.toString()     // Catch:{ SQLiteException -> 0x011f }
            android.database.Cursor r14 = r8.rawQuery(r14, r7)     // Catch:{ SQLiteException -> 0x011f }
            r8.beginTransaction()     // Catch:{ SQLiteException -> 0x011b }
        L_0x0064:
            boolean r9 = r14.moveToNext()     // Catch:{ all -> 0x0116 }
            if (r9 == 0) goto L_0x010a
            android.content.ContentValues r9 = new android.content.ContentValues     // Catch:{ JSONException -> 0x0064 }
            r9.<init>()     // Catch:{ JSONException -> 0x0064 }
            int r10 = r14.getColumnIndex(r3)     // Catch:{ JSONException -> 0x0064 }
            if (r10 < 0) goto L_0x007a
            int r10 = r14.getColumnIndex(r3)     // Catch:{ JSONException -> 0x0064 }
            goto L_0x007b
        L_0x007a:
            r10 = 2
        L_0x007b:
            long r10 = r14.getLong(r10)     // Catch:{ JSONException -> 0x0064 }
            java.lang.Long r10 = java.lang.Long.valueOf(r10)     // Catch:{ JSONException -> 0x0064 }
            r9.put(r3, r10)     // Catch:{ JSONException -> 0x0064 }
            int r10 = r14.getColumnIndex(r2)     // Catch:{ JSONException -> 0x0064 }
            if (r10 < 0) goto L_0x0091
            int r10 = r14.getColumnIndex(r2)     // Catch:{ JSONException -> 0x0064 }
            goto L_0x0092
        L_0x0091:
            r10 = 3
        L_0x0092:
            int r10 = r14.getInt(r10)     // Catch:{ JSONException -> 0x0064 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ JSONException -> 0x0064 }
            r9.put(r2, r10)     // Catch:{ JSONException -> 0x0064 }
            int r10 = r14.getColumnIndex(r4)     // Catch:{ JSONException -> 0x0064 }
            if (r10 < 0) goto L_0x00a8
            int r10 = r14.getColumnIndex(r4)     // Catch:{ JSONException -> 0x0064 }
            goto L_0x00a9
        L_0x00a8:
            r10 = 4
        L_0x00a9:
            java.lang.String r10 = r14.getString(r10)     // Catch:{ JSONException -> 0x0064 }
            r9.put(r4, r10)     // Catch:{ JSONException -> 0x0064 }
            int r10 = r14.getColumnIndex(r1)     // Catch:{ JSONException -> 0x0064 }
            if (r10 < 0) goto L_0x00bb
            int r10 = r14.getColumnIndex(r1)     // Catch:{ JSONException -> 0x0064 }
            goto L_0x00bc
        L_0x00bb:
            r10 = 1
        L_0x00bc:
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0064 }
            java.lang.String r10 = r14.getString(r10)     // Catch:{ JSONException -> 0x0064 }
            r11.<init>(r10)     // Catch:{ JSONException -> 0x0064 }
            java.lang.String r10 = "$distinct_id"
            r11.put(r10, r15)     // Catch:{ JSONException -> 0x0064 }
            java.lang.String r10 = r11.toString()     // Catch:{ JSONException -> 0x0064 }
            r9.put(r1, r10)     // Catch:{ JSONException -> 0x0064 }
            com.mixpanel.android.mpmetrics.MPDbAdapter$Table r10 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.PEOPLE     // Catch:{ JSONException -> 0x0064 }
            java.lang.String r10 = r10.getName()     // Catch:{ JSONException -> 0x0064 }
            r8.insert(r10, r7, r9)     // Catch:{ JSONException -> 0x0064 }
            int r9 = r14.getColumnIndex(r0)     // Catch:{ JSONException -> 0x0064 }
            if (r9 < 0) goto L_0x00e5
            int r9 = r14.getColumnIndex(r0)     // Catch:{ JSONException -> 0x0064 }
            goto L_0x00e6
        L_0x00e5:
            r9 = 0
        L_0x00e6:
            int r9 = r14.getInt(r9)     // Catch:{ JSONException -> 0x0064 }
            com.mixpanel.android.mpmetrics.MPDbAdapter$Table r10 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.ANONYMOUS_PEOPLE     // Catch:{ JSONException -> 0x0064 }
            java.lang.String r10 = r10.getName()     // Catch:{ JSONException -> 0x0064 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0064 }
            r11.<init>()     // Catch:{ JSONException -> 0x0064 }
            java.lang.String r12 = "_id = "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ JSONException -> 0x0064 }
            java.lang.StringBuilder r9 = r11.append(r9)     // Catch:{ JSONException -> 0x0064 }
            java.lang.String r9 = r9.toString()     // Catch:{ JSONException -> 0x0064 }
            r8.delete(r10, r9, r7)     // Catch:{ JSONException -> 0x0064 }
            int r5 = r5 + 1
            goto L_0x0064
        L_0x010a:
            r8.setTransactionSuccessful()     // Catch:{ all -> 0x0116 }
            r8.endTransaction()     // Catch:{ SQLiteException -> 0x011b }
            if (r14 == 0) goto L_0x0154
            r14.close()
            goto L_0x0154
        L_0x0116:
            r15 = move-exception
            r8.endTransaction()     // Catch:{ SQLiteException -> 0x011b }
            throw r15     // Catch:{ SQLiteException -> 0x011b }
        L_0x011b:
            r15 = move-exception
            goto L_0x0121
        L_0x011d:
            r15 = move-exception
            goto L_0x015c
        L_0x011f:
            r15 = move-exception
            r14 = r7
        L_0x0121:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x015a }
            r0.<init>()     // Catch:{ all -> 0x015a }
            java.lang.String r1 = "Could not push anonymous updates records from "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x015a }
            com.mixpanel.android.mpmetrics.MPDbAdapter$Table r1 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.ANONYMOUS_PEOPLE     // Catch:{ all -> 0x015a }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x015a }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x015a }
            java.lang.String r1 = ". Re-initializing database."
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x015a }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x015a }
            com.mixpanel.android.util.MPLog.m62e(r6, r0, r15)     // Catch:{ all -> 0x015a }
            if (r14 == 0) goto L_0x0149
            r14.close()     // Catch:{ all -> 0x015a }
            goto L_0x014a
        L_0x0149:
            r7 = r14
        L_0x014a:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r14 = r13.mDb     // Catch:{ all -> 0x011d }
            r14.deleteDatabase()     // Catch:{ all -> 0x011d }
            if (r7 == 0) goto L_0x0154
            r7.close()
        L_0x0154:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r13 = r13.mDb
            r13.close()
            return r5
        L_0x015a:
            r15 = move-exception
            r7 = r14
        L_0x015c:
            if (r7 == 0) goto L_0x0161
            r7.close()
        L_0x0161:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r13 = r13.mDb
            r13.close()
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.MPDbAdapter.pushAnonymousUpdatesToPeopleDb(java.lang.String, java.lang.String):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0067 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x006d A[SYNTHETIC, Splitter:B:13:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x010f A[Catch:{ all -> 0x0125 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0129  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int rewriteEventDataWithProperties(java.util.Map<java.lang.String, java.lang.String> r17, java.lang.String r18) {
        /*
            r16 = this;
            r1 = r16
            java.lang.String r0 = "_id"
            java.lang.String r2 = "properties"
            java.lang.String r3 = "data"
            boolean r4 = r16.aboveMemThreshold()
            java.lang.String r5 = "MixpanelAPI.Database"
            if (r4 == 0) goto L_0x0017
            java.lang.String r0 = "There is not enough space left on the device or the data was over the maximum size limit so it was discarded"
            com.mixpanel.android.util.MPLog.m61e(r5, r0)
            r0 = -2
            return r0
        L_0x0017:
            r4 = 0
            r6 = 0
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r7 = r1.mDb     // Catch:{ SQLiteException -> 0x0106 }
            android.database.sqlite.SQLiteDatabase r7 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.StringBuffer r8 = new java.lang.StringBuffer     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0106 }
            r9.<init>()     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r10 = "SELECT * FROM "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ SQLiteException -> 0x0106 }
            com.mixpanel.android.mpmetrics.MPDbAdapter$Table r10 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.EVENTS     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r10 = r10.getName()     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r10 = " WHERE "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r10 = "token"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r10 = " = '"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ SQLiteException -> 0x0106 }
            r10 = r18
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r10 = "'"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r9 = r9.toString()     // Catch:{ SQLiteException -> 0x0106 }
            r8.<init>(r9)     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r8 = r8.toString()     // Catch:{ SQLiteException -> 0x0106 }
            android.database.Cursor r8 = r7.rawQuery(r8, r6)     // Catch:{ SQLiteException -> 0x0106 }
            r7.beginTransaction()     // Catch:{ SQLiteException -> 0x0102 }
            r9 = r4
        L_0x0067:
            boolean r10 = r8.moveToNext()     // Catch:{ all -> 0x00fa }
            if (r10 == 0) goto L_0x00e9
            android.content.ContentValues r10 = new android.content.ContentValues     // Catch:{ JSONException -> 0x0067 }
            r10.<init>()     // Catch:{ JSONException -> 0x0067 }
            int r11 = r8.getColumnIndex(r3)     // Catch:{ JSONException -> 0x0067 }
            if (r11 < 0) goto L_0x007d
            int r11 = r8.getColumnIndex(r3)     // Catch:{ JSONException -> 0x0067 }
            goto L_0x007e
        L_0x007d:
            r11 = 1
        L_0x007e:
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0067 }
            java.lang.String r11 = r8.getString(r11)     // Catch:{ JSONException -> 0x0067 }
            r12.<init>(r11)     // Catch:{ JSONException -> 0x0067 }
            org.json.JSONObject r11 = r12.getJSONObject(r2)     // Catch:{ JSONException -> 0x0067 }
            java.util.Set r13 = r17.entrySet()     // Catch:{ JSONException -> 0x0067 }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ JSONException -> 0x0067 }
        L_0x0093:
            boolean r14 = r13.hasNext()     // Catch:{ JSONException -> 0x0067 }
            if (r14 == 0) goto L_0x00af
            java.lang.Object r14 = r13.next()     // Catch:{ JSONException -> 0x0067 }
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14     // Catch:{ JSONException -> 0x0067 }
            java.lang.Object r15 = r14.getKey()     // Catch:{ JSONException -> 0x0067 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ JSONException -> 0x0067 }
            java.lang.Object r14 = r14.getValue()     // Catch:{ JSONException -> 0x0067 }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ JSONException -> 0x0067 }
            r11.put(r15, r14)     // Catch:{ JSONException -> 0x0067 }
            goto L_0x0093
        L_0x00af:
            r12.put(r2, r11)     // Catch:{ JSONException -> 0x0067 }
            java.lang.String r11 = r12.toString()     // Catch:{ JSONException -> 0x0067 }
            r10.put(r3, r11)     // Catch:{ JSONException -> 0x0067 }
            int r11 = r8.getColumnIndex(r0)     // Catch:{ JSONException -> 0x0067 }
            if (r11 < 0) goto L_0x00c4
            int r11 = r8.getColumnIndex(r0)     // Catch:{ JSONException -> 0x0067 }
            goto L_0x00c5
        L_0x00c4:
            r11 = r4
        L_0x00c5:
            int r11 = r8.getInt(r11)     // Catch:{ JSONException -> 0x0067 }
            com.mixpanel.android.mpmetrics.MPDbAdapter$Table r12 = com.mixpanel.android.mpmetrics.MPDbAdapter.Table.EVENTS     // Catch:{ JSONException -> 0x0067 }
            java.lang.String r12 = r12.getName()     // Catch:{ JSONException -> 0x0067 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0067 }
            r13.<init>()     // Catch:{ JSONException -> 0x0067 }
            java.lang.String r14 = "_id = "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ JSONException -> 0x0067 }
            java.lang.StringBuilder r11 = r13.append(r11)     // Catch:{ JSONException -> 0x0067 }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0067 }
            r7.update(r12, r10, r11, r6)     // Catch:{ JSONException -> 0x0067 }
            int r9 = r9 + 1
            goto L_0x0067
        L_0x00e9:
            r7.setTransactionSuccessful()     // Catch:{ all -> 0x00fa }
            r7.endTransaction()     // Catch:{ SQLiteException -> 0x00ff }
            if (r8 == 0) goto L_0x00f4
            r8.close()
        L_0x00f4:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r0 = r1.mDb
            r0.close()
            goto L_0x0124
        L_0x00fa:
            r0 = move-exception
            r7.endTransaction()     // Catch:{ SQLiteException -> 0x00ff }
            throw r0     // Catch:{ SQLiteException -> 0x00ff }
        L_0x00ff:
            r0 = move-exception
            r4 = r9
            goto L_0x0108
        L_0x0102:
            r0 = move-exception
            goto L_0x0108
        L_0x0104:
            r0 = move-exception
            goto L_0x0127
        L_0x0106:
            r0 = move-exception
            r8 = r6
        L_0x0108:
            java.lang.String r2 = "Could not re-write events history. Re-initializing database."
            com.mixpanel.android.util.MPLog.m62e(r5, r2, r0)     // Catch:{ all -> 0x0125 }
            if (r8 == 0) goto L_0x0113
            r8.close()     // Catch:{ all -> 0x0125 }
            goto L_0x0114
        L_0x0113:
            r6 = r8
        L_0x0114:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r0 = r1.mDb     // Catch:{ all -> 0x0104 }
            r0.deleteDatabase()     // Catch:{ all -> 0x0104 }
            if (r6 == 0) goto L_0x011e
            r6.close()
        L_0x011e:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r0 = r1.mDb
            r0.close()
            r9 = r4
        L_0x0124:
            return r9
        L_0x0125:
            r0 = move-exception
            r6 = r8
        L_0x0127:
            if (r6 == 0) goto L_0x012c
            r6.close()
        L_0x012c:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r1 = r1.mDb
            r1.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.MPDbAdapter.rewriteEventDataWithProperties(java.util.Map, java.lang.String):int");
    }

    public void cleanupEvents(String str, Table table, String str2) {
        String name = table.getName();
        try {
            this.mDb.getWritableDatabase().delete(name, new StringBuffer("_id <= " + str + " AND " + KEY_TOKEN + " = '" + str2 + "'").toString(), (String[]) null);
        } catch (SQLiteException e) {
            MPLog.m62e(LOGTAG, "Could not clean sent Mixpanel records from " + name + ". Re-initializing database.", e);
            this.mDb.deleteDatabase();
        } catch (Exception e2) {
            MPLog.m62e(LOGTAG, "Unknown exception. Could not clean sent Mixpanel records from " + name + ".Re-initializing database.", e2);
            this.mDb.deleteDatabase();
        } catch (Throwable th) {
            this.mDb.close();
            throw th;
        }
        this.mDb.close();
    }

    public void cleanupEvents(long j, Table table) {
        String name = table.getName();
        try {
            this.mDb.getWritableDatabase().delete(name, "created_at <= " + j, (String[]) null);
        } catch (SQLiteException e) {
            MPLog.m62e(LOGTAG, "Could not clean timed-out Mixpanel records from " + name + ". Re-initializing database.", e);
            this.mDb.deleteDatabase();
        } catch (Throwable th) {
            this.mDb.close();
            throw th;
        }
        this.mDb.close();
    }

    public void cleanupAllEvents(Table table, String str) {
        String name = table.getName();
        try {
            this.mDb.getWritableDatabase().delete(name, "token = '" + str + "'", (String[]) null);
        } catch (SQLiteException e) {
            MPLog.m62e(LOGTAG, "Could not clean timed-out Mixpanel records from " + name + ". Re-initializing database.", e);
            this.mDb.deleteDatabase();
        } catch (Throwable th) {
            this.mDb.close();
            throw th;
        }
        this.mDb.close();
    }

    public void deleteDB() {
        this.mDb.deleteDatabase();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.String[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x014d A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x016a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] generateDataString(com.mixpanel.android.mpmetrics.MPDbAdapter.Table r17, java.lang.String r18) {
        /*
            r16 = this;
            r1 = r16
            r0 = r18
            java.lang.String r2 = "_id"
            java.lang.String r3 = "data"
            java.lang.String r4 = "' "
            java.lang.String r5 = " = '"
            java.lang.String r6 = "token"
            java.lang.String r7 = " WHERE "
            java.lang.String r8 = r17.getName()
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r9 = r1.mDb
            android.database.sqlite.SQLiteDatabase r9 = r9.getReadableDatabase()
            r11 = 0
            r12 = 0
            java.lang.StringBuffer r13 = new java.lang.StringBuffer     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            r14.<init>()     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.String r15 = "SELECT * FROM "
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r14 = r14.append(r8)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r14 = r14.append(r7)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r14 = r14.append(r6)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r14 = r14.append(r5)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r14 = r14.append(r0)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r14 = r14.append(r4)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.String r14 = r14.toString()     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            r13.<init>(r14)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuffer r14 = new java.lang.StringBuffer     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            r15.<init>()     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.String r10 = "SELECT COUNT(*) FROM "
            java.lang.StringBuilder r10 = r15.append(r10)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r10 = r10.append(r8)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r7 = r10.append(r7)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r5 = r6.append(r5)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.String r0 = r0.toString()     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            r14.<init>(r0)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            r0.<init>()     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.String r4 = "ORDER BY created_at ASC LIMIT "
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r4 = r1.mDb     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            com.mixpanel.android.mpmetrics.MPConfig r4 = r4.mConfig     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            int r4 = r4.getFlushBatchSize()     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.String r0 = r0.toString()     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            r13.append(r0)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.String r0 = r13.toString()     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            android.database.Cursor r4 = r9.rawQuery(r0, r12)     // Catch:{ SQLiteException -> 0x0118, all -> 0x0115 }
            java.lang.String r0 = r14.toString()     // Catch:{ SQLiteException -> 0x0112, all -> 0x010f }
            android.database.Cursor r5 = r9.rawQuery(r0, r12)     // Catch:{ SQLiteException -> 0x0112, all -> 0x010f }
            r5.moveToFirst()     // Catch:{ SQLiteException -> 0x010c }
            int r0 = r5.getInt(r11)     // Catch:{ SQLiteException -> 0x010c }
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ SQLiteException -> 0x010c }
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ SQLiteException -> 0x010a }
            r0.<init>()     // Catch:{ SQLiteException -> 0x010a }
            r7 = r12
        L_0x00b9:
            boolean r9 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x010a }
            if (r9 == 0) goto L_0x00ee
            boolean r9 = r4.isLast()     // Catch:{ SQLiteException -> 0x010a }
            if (r9 == 0) goto L_0x00d5
            int r7 = r4.getColumnIndex(r2)     // Catch:{ SQLiteException -> 0x010a }
            if (r7 < 0) goto L_0x00d0
            int r7 = r4.getColumnIndex(r2)     // Catch:{ SQLiteException -> 0x010a }
            goto L_0x00d1
        L_0x00d0:
            r7 = r11
        L_0x00d1:
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x010a }
        L_0x00d5:
            int r9 = r4.getColumnIndex(r3)     // Catch:{ JSONException -> 0x00b9 }
            if (r9 < 0) goto L_0x00e0
            int r9 = r4.getColumnIndex(r3)     // Catch:{ JSONException -> 0x00b9 }
            goto L_0x00e1
        L_0x00e0:
            r9 = 1
        L_0x00e1:
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00b9 }
            java.lang.String r9 = r4.getString(r9)     // Catch:{ JSONException -> 0x00b9 }
            r10.<init>(r9)     // Catch:{ JSONException -> 0x00b9 }
            r0.put(r10)     // Catch:{ JSONException -> 0x00b9 }
            goto L_0x00b9
        L_0x00ee:
            int r2 = r0.length()     // Catch:{ SQLiteException -> 0x010a }
            if (r2 <= 0) goto L_0x00f9
            java.lang.String r0 = r0.toString()     // Catch:{ SQLiteException -> 0x010a }
            goto L_0x00fa
        L_0x00f9:
            r0 = r12
        L_0x00fa:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r1 = r1.mDb
            r1.close()
            if (r4 == 0) goto L_0x0104
            r4.close()
        L_0x0104:
            if (r5 == 0) goto L_0x014b
            r5.close()
            goto L_0x014b
        L_0x010a:
            r0 = move-exception
            goto L_0x011c
        L_0x010c:
            r0 = move-exception
            r6 = r12
            goto L_0x011c
        L_0x010f:
            r0 = move-exception
            r5 = r12
            goto L_0x015d
        L_0x0112:
            r0 = move-exception
            r5 = r12
            goto L_0x011b
        L_0x0115:
            r0 = move-exception
            r5 = r12
            goto L_0x015e
        L_0x0118:
            r0 = move-exception
            r4 = r12
            r5 = r4
        L_0x011b:
            r6 = r5
        L_0x011c:
            java.lang.String r2 = "MixpanelAPI.Database"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x015c }
            r3.<init>()     // Catch:{ all -> 0x015c }
            java.lang.String r7 = "Could not pull records for Mixpanel out of database "
            java.lang.StringBuilder r3 = r3.append(r7)     // Catch:{ all -> 0x015c }
            java.lang.StringBuilder r3 = r3.append(r8)     // Catch:{ all -> 0x015c }
            java.lang.String r7 = ". Waiting to send."
            java.lang.StringBuilder r3 = r3.append(r7)     // Catch:{ all -> 0x015c }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x015c }
            com.mixpanel.android.util.MPLog.m62e(r2, r3, r0)     // Catch:{ all -> 0x015c }
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r0 = r1.mDb
            r0.close()
            if (r4 == 0) goto L_0x0144
            r4.close()
        L_0x0144:
            if (r5 == 0) goto L_0x0149
            r5.close()
        L_0x0149:
            r0 = r12
            r7 = r0
        L_0x014b:
            if (r7 == 0) goto L_0x015b
            if (r0 == 0) goto L_0x015b
            r1 = 3
            java.lang.String[] r1 = new java.lang.String[r1]
            r1[r11] = r7
            r2 = 1
            r1[r2] = r0
            r0 = 2
            r1[r0] = r6
            return r1
        L_0x015b:
            return r12
        L_0x015c:
            r0 = move-exception
        L_0x015d:
            r12 = r4
        L_0x015e:
            com.mixpanel.android.mpmetrics.MPDbAdapter$MPDatabaseHelper r1 = r1.mDb
            r1.close()
            if (r12 == 0) goto L_0x0168
            r12.close()
        L_0x0168:
            if (r5 == 0) goto L_0x016d
            r5.close()
        L_0x016d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.MPDbAdapter.generateDataString(com.mixpanel.android.mpmetrics.MPDbAdapter$Table, java.lang.String):java.lang.String[]");
    }

    public File getDatabaseFile() {
        return this.mDb.mDatabaseFile;
    }

    /* access modifiers changed from: protected */
    public boolean aboveMemThreshold() {
        return this.mDb.aboveMemThreshold();
    }
}
