package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.SharedPreferences;
import com.mixpanel.android.util.MPLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

class PersistentIdentity {
    private static final String DELIMITER = ",";
    private static final String LOGTAG = "MixpanelAPI.PIdentity";
    private static final String MP_DEBUG_INIT_COUNT_KEY = "mpDebugInitCount";
    private static final String MP_HAS_DEBUG_ALIASED_KEY = "mpHasDebugAliased";
    private static final String MP_HAS_DEBUG_IDENTIFIED_KEY = "mpHasDebugIdentified";
    private static final String MP_HAS_DEBUG_TRACKED_KEY = "mpHasDebugTracked";
    private static final String MP_HAS_DEBUG_USED_PEOPLE_KEY = "mpHasDebugUsedPeople";
    private static final String MP_HAS_IMPLEMENTED_KEY = "mpHasImplemented";
    private static Boolean sIsFirstAppLaunch = null;
    private static Integer sPreviousVersionCode = null;
    /* access modifiers changed from: private */
    public static boolean sReferrerPrefsDirty = true;
    /* access modifiers changed from: private */
    public static final Object sReferrerPrefsLock = new Object();
    private String mAnonymousId;
    private String mEventsDistinctId;
    private boolean mEventsUserIdPresent;
    private boolean mHadPersistedDistinctId;
    private boolean mIdentitiesLoaded;
    private Boolean mIsUserOptOut;
    private final Future<SharedPreferences> mLoadReferrerPreferences;
    private final Future<SharedPreferences> mLoadStoredPreferences;
    private final Future<SharedPreferences> mMixpanelPreferences;
    private String mPeopleDistinctId;
    private final SharedPreferences.OnSharedPreferenceChangeListener mReferrerChangeListener;
    private Map<String, String> mReferrerPropertiesCache;
    private JSONObject mSuperPropertiesCache;
    private final Object mSuperPropsLock = new Object();
    private final Future<SharedPreferences> mTimeEventsPreferences;

    public static String getPeopleDistinctId(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("people_distinct_id", (String) null);
    }

    public static void writeReferrerPrefs(Context context, String str, Map<String, String> map) {
        synchronized (sReferrerPrefsLock) {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.clear();
            for (Map.Entry next : map.entrySet()) {
                edit.putString((String) next.getKey(), (String) next.getValue());
            }
            writeEdits(edit);
            sReferrerPrefsDirty = true;
        }
    }

    public PersistentIdentity(Future<SharedPreferences> future, Future<SharedPreferences> future2, Future<SharedPreferences> future3, Future<SharedPreferences> future4) {
        this.mLoadReferrerPreferences = future;
        this.mLoadStoredPreferences = future2;
        this.mTimeEventsPreferences = future3;
        this.mMixpanelPreferences = future4;
        this.mSuperPropertiesCache = null;
        this.mReferrerPropertiesCache = null;
        this.mIdentitiesLoaded = false;
        this.mReferrerChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                synchronized (PersistentIdentity.sReferrerPrefsLock) {
                    PersistentIdentity.this.readReferrerProperties();
                    boolean unused = PersistentIdentity.sReferrerPrefsDirty = false;
                }
            }
        };
    }

    public void addSuperPropertiesToObject(JSONObject jSONObject) {
        synchronized (this.mSuperPropsLock) {
            JSONObject superPropertiesCache = getSuperPropertiesCache();
            Iterator<String> keys = superPropertiesCache.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    jSONObject.put(next, superPropertiesCache.get(next));
                } catch (JSONException e) {
                    MPLog.m62e(LOGTAG, "Object read from one JSON Object cannot be written to another", e);
                }
            }
        }
    }

    public void updateSuperProperties(SuperPropertyUpdate superPropertyUpdate) {
        synchronized (this.mSuperPropsLock) {
            JSONObject superPropertiesCache = getSuperPropertiesCache();
            JSONObject jSONObject = new JSONObject();
            try {
                Iterator<String> keys = superPropertiesCache.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject.put(next, superPropertiesCache.get(next));
                }
                JSONObject update = superPropertyUpdate.update(jSONObject);
                if (update == null) {
                    MPLog.m67w(LOGTAG, "An update to Mixpanel's super properties returned null, and will have no effect.");
                    return;
                }
                this.mSuperPropertiesCache = update;
                storeSuperProperties();
            } catch (JSONException e) {
                MPLog.m62e(LOGTAG, "Can't copy from one JSONObject to another", e);
            }
        }
    }

    public void registerSuperProperties(JSONObject jSONObject) {
        synchronized (this.mSuperPropsLock) {
            JSONObject superPropertiesCache = getSuperPropertiesCache();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    superPropertiesCache.put(next, jSONObject.get(next));
                } catch (JSONException e) {
                    MPLog.m62e(LOGTAG, "Exception registering super property.", e);
                }
            }
            storeSuperProperties();
        }
    }

    public void unregisterSuperProperty(String str) {
        synchronized (this.mSuperPropsLock) {
            getSuperPropertiesCache().remove(str);
            storeSuperProperties();
        }
    }

    public void registerSuperPropertiesOnce(JSONObject jSONObject) {
        synchronized (this.mSuperPropsLock) {
            JSONObject superPropertiesCache = getSuperPropertiesCache();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!superPropertiesCache.has(next)) {
                    try {
                        superPropertiesCache.put(next, jSONObject.get(next));
                    } catch (JSONException e) {
                        MPLog.m62e(LOGTAG, "Exception registering super property.", e);
                    }
                }
            }
            storeSuperProperties();
        }
    }

    public void clearSuperProperties() {
        synchronized (this.mSuperPropsLock) {
            this.mSuperPropertiesCache = new JSONObject();
            storeSuperProperties();
        }
    }

    public Map<String, String> getReferrerProperties() {
        synchronized (sReferrerPrefsLock) {
            if (sReferrerPrefsDirty || this.mReferrerPropertiesCache == null) {
                readReferrerProperties();
                sReferrerPrefsDirty = false;
            }
        }
        return this.mReferrerPropertiesCache;
    }

    public void clearReferrerProperties() {
        synchronized (sReferrerPrefsLock) {
            try {
                SharedPreferences.Editor edit = this.mLoadReferrerPreferences.get().edit();
                edit.clear();
                writeEdits(edit);
            } catch (ExecutionException e) {
                MPLog.m62e(LOGTAG, "Cannot load referrer properties from shared preferences.", e.getCause());
            } catch (InterruptedException e2) {
                MPLog.m62e(LOGTAG, "Cannot load referrer properties from shared preferences.", e2);
            }
        }
    }

    public synchronized String getAnonymousId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mAnonymousId;
    }

    public synchronized boolean getHadPersistedDistinctId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mHadPersistedDistinctId;
    }

    public synchronized String getEventsDistinctId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mEventsDistinctId;
    }

    public synchronized String getEventsUserId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        if (!this.mEventsUserIdPresent) {
            return null;
        }
        return this.mEventsDistinctId;
    }

    public synchronized void setAnonymousIdIfAbsent(String str) {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        if (this.mAnonymousId == null) {
            this.mAnonymousId = str;
            this.mHadPersistedDistinctId = true;
            writeIdentities();
        }
    }

    public synchronized void setEventsDistinctId(String str) {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        this.mEventsDistinctId = str;
        writeIdentities();
    }

    public synchronized void markEventsUserIdPresent() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        this.mEventsUserIdPresent = true;
        writeIdentities();
    }

    public synchronized String getPeopleDistinctId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mPeopleDistinctId;
    }

    public synchronized void setPeopleDistinctId(String str) {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        this.mPeopleDistinctId = str;
        writeIdentities();
    }

    public synchronized void clearPreferences() {
        try {
            SharedPreferences.Editor edit = this.mLoadStoredPreferences.get().edit();
            edit.clear();
            writeEdits(edit);
            readSuperProperties();
            readIdentities();
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (InterruptedException e2) {
            throw new RuntimeException(e2.getCause());
        }
    }

    public void clearTimedEvents() {
        try {
            SharedPreferences.Editor edit = this.mTimeEventsPreferences.get().edit();
            edit.clear();
            writeEdits(edit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }

    public Map<String, Long> getTimeEvents() {
        HashMap hashMap = new HashMap();
        try {
            for (Map.Entry next : this.mTimeEventsPreferences.get().getAll().entrySet()) {
                hashMap.put(next.getKey(), Long.valueOf(next.getValue().toString()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }

    public void removeTimedEvent(String str) {
        try {
            SharedPreferences.Editor edit = this.mTimeEventsPreferences.get().edit();
            edit.remove(str);
            writeEdits(edit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }

    public void addTimeEvent(String str, Long l) {
        try {
            SharedPreferences.Editor edit = this.mTimeEventsPreferences.get().edit();
            edit.putLong(str, l.longValue());
            writeEdits(edit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }

    private synchronized int getIntFlagValue(String str, String str2) {
        int i;
        i = 0;
        try {
            i = this.mMixpanelPreferences.get().getInt(str + str2, 0);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Couldn't read internal Mixpanel shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Couldn't read internal Mixpanel from shared preferences.", e2);
        }
        return i;
    }

    private synchronized void setIntFlagValue(String str, String str2, int i) {
        try {
            SharedPreferences.Editor edit = this.mMixpanelPreferences.get().edit();
            edit.putInt(str + str2, i);
            writeEdits(edit);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel from shared preferences.", e2);
        }
    }

    private synchronized boolean getBoolFlagValue(String str, String str2) {
        boolean z;
        z = false;
        try {
            z = this.mMixpanelPreferences.get().getBoolean(str + str2, false);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Couldn't read internal Mixpanel shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Couldn't read internal Mixpanel from shared preferences.", e2);
        }
        return z;
    }

    private synchronized void setBooleanFlagValue(String str, String str2, boolean z) {
        try {
            SharedPreferences.Editor edit = this.mMixpanelPreferences.get().edit();
            edit.putBoolean(str + str2, z);
            writeEdits(edit);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel from shared preferences.", e2);
        }
    }

    public synchronized int debugInitCount(String str) {
        return getIntFlagValue(str, MP_DEBUG_INIT_COUNT_KEY);
    }

    public synchronized void setDebugInitCount(String str, int i) {
        setIntFlagValue(str, MP_DEBUG_INIT_COUNT_KEY, i);
    }

    public synchronized boolean hasImplemented(String str) {
        return getBoolFlagValue(str, MP_HAS_IMPLEMENTED_KEY);
    }

    public synchronized void setHasImplemented(String str) {
        setBooleanFlagValue(str, MP_HAS_IMPLEMENTED_KEY, true);
    }

    public synchronized boolean hasMPDebugTracked(String str) {
        return getBoolFlagValue(str, MP_HAS_DEBUG_TRACKED_KEY);
    }

    public synchronized void setHasMPDebugTracked(String str) {
        setBooleanFlagValue(str, MP_HAS_DEBUG_TRACKED_KEY, true);
    }

    public synchronized boolean hasMPDebugIdentified(String str) {
        return getBoolFlagValue(str, MP_HAS_DEBUG_IDENTIFIED_KEY);
    }

    public synchronized void setHasMPDebugIdentified(String str) {
        setBooleanFlagValue(str, MP_HAS_DEBUG_IDENTIFIED_KEY, true);
    }

    public synchronized boolean hasMPDebugAliased(String str) {
        return getBoolFlagValue(str, MP_HAS_DEBUG_ALIASED_KEY);
    }

    public synchronized void setHasMPDebugAliased(String str) {
        setBooleanFlagValue(str, MP_HAS_DEBUG_ALIASED_KEY, true);
    }

    public synchronized boolean hasMPDebugUsedPeople(String str) {
        return getBoolFlagValue(str, MP_HAS_DEBUG_USED_PEOPLE_KEY);
    }

    public synchronized void setHasMPDebugUsedPeople(String str) {
        setBooleanFlagValue(str, MP_HAS_DEBUG_USED_PEOPLE_KEY, true);
    }

    public synchronized boolean isFirstIntegration(String str) {
        boolean z;
        z = false;
        try {
            z = this.mMixpanelPreferences.get().getBoolean(str, false);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Couldn't read internal Mixpanel shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Couldn't read internal Mixpanel from shared preferences.", e2);
        }
        return z;
    }

    public synchronized void setIsIntegrated(String str) {
        try {
            SharedPreferences.Editor edit = this.mMixpanelPreferences.get().edit();
            edit.putBoolean(str, true);
            writeEdits(edit);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel from shared preferences.", e2);
        }
    }

    public synchronized boolean isNewVersion(String str) {
        if (str == null) {
            return false;
        }
        Integer valueOf = Integer.valueOf(str);
        try {
            if (sPreviousVersionCode == null) {
                Integer valueOf2 = Integer.valueOf(this.mMixpanelPreferences.get().getInt("latest_version_code", -1));
                sPreviousVersionCode = valueOf2;
                if (valueOf2.intValue() == -1) {
                    sPreviousVersionCode = valueOf;
                    SharedPreferences.Editor edit = this.mMixpanelPreferences.get().edit();
                    edit.putInt("latest_version_code", valueOf.intValue());
                    writeEdits(edit);
                }
            }
            if (sPreviousVersionCode.intValue() < valueOf.intValue()) {
                SharedPreferences.Editor edit2 = this.mMixpanelPreferences.get().edit();
                edit2.putInt("latest_version_code", valueOf.intValue());
                writeEdits(edit2);
                return true;
            }
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel from shared preferences.", e2);
        }
        return false;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0043 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean isFirstLaunch(boolean r5, java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.Boolean r0 = sIsFirstAppLaunch     // Catch:{ all -> 0x0058 }
            if (r0 != 0) goto L_0x0050
            r0 = 0
            java.util.concurrent.Future<android.content.SharedPreferences> r1 = r4.mMixpanelPreferences     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            java.lang.Object r1 = r1.get()     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            android.content.SharedPreferences r1 = (android.content.SharedPreferences) r1     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            r2.<init>()     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            java.lang.String r3 = "has_launched_"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            java.lang.StringBuilder r2 = r2.append(r6)     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            java.lang.String r2 = r2.toString()     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            boolean r1 = r1.getBoolean(r2, r0)     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            if (r1 == 0) goto L_0x002e
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r0)     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            sIsFirstAppLaunch = r5     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            goto L_0x0050
        L_0x002e:
            if (r5 != 0) goto L_0x0032
            r5 = 1
            goto L_0x0033
        L_0x0032:
            r5 = r0
        L_0x0033:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            sIsFirstAppLaunch = r5     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            boolean r5 = r5.booleanValue()     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            if (r5 != 0) goto L_0x0050
            r4.setHasLaunched(r6)     // Catch:{ ExecutionException -> 0x004a, InterruptedException -> 0x0043 }
            goto L_0x0050
        L_0x0043:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0058 }
            sIsFirstAppLaunch = r5     // Catch:{ all -> 0x0058 }
            goto L_0x0050
        L_0x004a:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0058 }
            sIsFirstAppLaunch = r5     // Catch:{ all -> 0x0058 }
        L_0x0050:
            java.lang.Boolean r5 = sIsFirstAppLaunch     // Catch:{ all -> 0x0058 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0058 }
            monitor-exit(r4)
            return r5
        L_0x0058:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.PersistentIdentity.isFirstLaunch(boolean, java.lang.String):boolean");
    }

    public synchronized void setHasLaunched(String str) {
        try {
            SharedPreferences.Editor edit = this.mMixpanelPreferences.get().edit();
            edit.putBoolean("has_launched_" + str, true);
            writeEdits(edit);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Couldn't write internal Mixpanel shared preferences.", e2);
        }
    }

    public synchronized void setOptOutTracking(boolean z, String str) {
        this.mIsUserOptOut = Boolean.valueOf(z);
        writeOptOutFlag(str);
    }

    public synchronized boolean getOptOutTracking(String str) {
        if (this.mIsUserOptOut == null) {
            readOptOutFlag(str);
        }
        return this.mIsUserOptOut.booleanValue();
    }

    private JSONObject getSuperPropertiesCache() {
        if (this.mSuperPropertiesCache == null) {
            readSuperProperties();
        }
        return this.mSuperPropertiesCache;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        r5.mSuperPropertiesCache = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readSuperProperties() {
        /*
            r5 = this;
            java.lang.String r0 = "Cannot load superProperties from SharedPreferences."
            java.lang.String r1 = "MixpanelAPI.PIdentity"
            java.util.concurrent.Future<android.content.SharedPreferences> r2 = r5.mLoadStoredPreferences     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            java.lang.Object r2 = r2.get()     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            android.content.SharedPreferences r2 = (android.content.SharedPreferences) r2     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            java.lang.String r3 = "super_properties"
            java.lang.String r4 = "{}"
            java.lang.String r2 = r2.getString(r3, r4)     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            r3.<init>()     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            java.lang.String r4 = "Loading Super Properties "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            java.lang.StringBuilder r3 = r3.append(r2)     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            java.lang.String r3 = r3.toString()     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            com.mixpanel.android.util.MPLog.m65v(r1, r3)     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            r3.<init>(r2)     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            r5.mSuperPropertiesCache = r3     // Catch:{ ExecutionException -> 0x0056, InterruptedException -> 0x0046, JSONException -> 0x0034 }
            goto L_0x0068
        L_0x0032:
            r0 = move-exception
            goto L_0x0069
        L_0x0034:
            java.lang.String r0 = "Cannot parse stored superProperties"
            com.mixpanel.android.util.MPLog.m61e(r1, r0)     // Catch:{ all -> 0x0032 }
            r5.storeSuperProperties()     // Catch:{ all -> 0x0032 }
            org.json.JSONObject r0 = r5.mSuperPropertiesCache
            if (r0 != 0) goto L_0x0068
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            goto L_0x0053
        L_0x0046:
            r2 = move-exception
            com.mixpanel.android.util.MPLog.m62e(r1, r0, r2)     // Catch:{ all -> 0x0032 }
            org.json.JSONObject r0 = r5.mSuperPropertiesCache
            if (r0 != 0) goto L_0x0068
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
        L_0x0053:
            r5.mSuperPropertiesCache = r0
            goto L_0x0068
        L_0x0056:
            r2 = move-exception
            java.lang.Throwable r2 = r2.getCause()     // Catch:{ all -> 0x0032 }
            com.mixpanel.android.util.MPLog.m62e(r1, r0, r2)     // Catch:{ all -> 0x0032 }
            org.json.JSONObject r0 = r5.mSuperPropertiesCache
            if (r0 != 0) goto L_0x0068
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            goto L_0x0053
        L_0x0068:
            return
        L_0x0069:
            org.json.JSONObject r1 = r5.mSuperPropertiesCache
            if (r1 != 0) goto L_0x0074
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            r5.mSuperPropertiesCache = r1
        L_0x0074:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.PersistentIdentity.readSuperProperties():void");
    }

    /* access modifiers changed from: private */
    public void readReferrerProperties() {
        this.mReferrerPropertiesCache = new HashMap();
        try {
            SharedPreferences sharedPreferences = this.mLoadReferrerPreferences.get();
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this.mReferrerChangeListener);
            sharedPreferences.registerOnSharedPreferenceChangeListener(this.mReferrerChangeListener);
            for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
                Object value = next.getValue();
                this.mReferrerPropertiesCache.put((String) next.getKey(), value.toString());
            }
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Cannot load referrer properties from shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Cannot load referrer properties from shared preferences.", e2);
        }
    }

    private void storeSuperProperties() {
        JSONObject jSONObject = this.mSuperPropertiesCache;
        if (jSONObject == null) {
            MPLog.m61e(LOGTAG, "storeSuperProperties should not be called with uninitialized superPropertiesCache.");
            return;
        }
        String jSONObject2 = jSONObject.toString();
        MPLog.m65v(LOGTAG, "Storing Super Properties " + jSONObject2);
        try {
            SharedPreferences.Editor edit = this.mLoadStoredPreferences.get().edit();
            edit.putString("super_properties", jSONObject2);
            writeEdits(edit);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Cannot store superProperties in shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Cannot store superProperties in shared preferences.", e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readIdentities() {
        /*
            r4 = this;
            java.lang.String r0 = "Cannot read distinct ids from sharedPreferences."
            java.lang.String r1 = "MixpanelAPI.PIdentity"
            r2 = 0
            java.util.concurrent.Future<android.content.SharedPreferences> r3 = r4.mLoadStoredPreferences     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000e }
            java.lang.Object r3 = r3.get()     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000e }
            android.content.SharedPreferences r3 = (android.content.SharedPreferences) r3     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000e }
            goto L_0x001c
        L_0x000e:
            r3 = move-exception
            com.mixpanel.android.util.MPLog.m62e(r1, r0, r3)
            goto L_0x001b
        L_0x0013:
            r3 = move-exception
            java.lang.Throwable r3 = r3.getCause()
            com.mixpanel.android.util.MPLog.m62e(r1, r0, r3)
        L_0x001b:
            r3 = r2
        L_0x001c:
            if (r3 != 0) goto L_0x001f
            return
        L_0x001f:
            java.lang.String r0 = "events_distinct_id"
            java.lang.String r0 = r3.getString(r0, r2)
            r4.mEventsDistinctId = r0
            java.lang.String r0 = "events_user_id_present"
            r1 = 0
            boolean r0 = r3.getBoolean(r0, r1)
            r4.mEventsUserIdPresent = r0
            java.lang.String r0 = "people_distinct_id"
            java.lang.String r0 = r3.getString(r0, r2)
            r4.mPeopleDistinctId = r0
            java.lang.String r0 = "anonymous_id"
            java.lang.String r0 = r3.getString(r0, r2)
            r4.mAnonymousId = r0
            java.lang.String r0 = "had_persisted_distinct_id"
            boolean r0 = r3.getBoolean(r0, r1)
            r4.mHadPersistedDistinctId = r0
            java.lang.String r0 = r4.mEventsDistinctId
            if (r0 != 0) goto L_0x005d
            java.util.UUID r0 = java.util.UUID.randomUUID()
            java.lang.String r0 = r0.toString()
            r4.mAnonymousId = r0
            r4.mEventsDistinctId = r0
            r4.mEventsUserIdPresent = r1
            r4.writeIdentities()
        L_0x005d:
            r0 = 1
            r4.mIdentitiesLoaded = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.PersistentIdentity.readIdentities():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readOptOutFlag(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = "Cannot read opt out flag from sharedPreferences."
            java.lang.String r1 = "MixpanelAPI.PIdentity"
            java.util.concurrent.Future<android.content.SharedPreferences> r2 = r3.mMixpanelPreferences     // Catch:{ ExecutionException -> 0x0012, InterruptedException -> 0x000d }
            java.lang.Object r2 = r2.get()     // Catch:{ ExecutionException -> 0x0012, InterruptedException -> 0x000d }
            android.content.SharedPreferences r2 = (android.content.SharedPreferences) r2     // Catch:{ ExecutionException -> 0x0012, InterruptedException -> 0x000d }
            goto L_0x001b
        L_0x000d:
            r2 = move-exception
            com.mixpanel.android.util.MPLog.m62e(r1, r0, r2)
            goto L_0x001a
        L_0x0012:
            r2 = move-exception
            java.lang.Throwable r2 = r2.getCause()
            com.mixpanel.android.util.MPLog.m62e(r1, r0, r2)
        L_0x001a:
            r2 = 0
        L_0x001b:
            if (r2 != 0) goto L_0x001e
            return
        L_0x001e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "opt_out_"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r4 = r4.toString()
            r0 = 0
            boolean r4 = r2.getBoolean(r4, r0)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r3.mIsUserOptOut = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.PersistentIdentity.readOptOutFlag(java.lang.String):void");
    }

    private void writeOptOutFlag(String str) {
        try {
            SharedPreferences.Editor edit = this.mMixpanelPreferences.get().edit();
            edit.putBoolean("opt_out_" + str, this.mIsUserOptOut.booleanValue());
            writeEdits(edit);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Can't write opt-out shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Can't write opt-out shared preferences.", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void removeOptOutFlag(String str) {
        try {
            SharedPreferences.Editor edit = this.mMixpanelPreferences.get().edit();
            edit.clear();
            writeEdits(edit);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Can't remove opt-out shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Can't remove opt-out shared preferences.", e2);
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasOptOutFlag(String str) {
        try {
            return this.mMixpanelPreferences.get().contains("opt_out_" + str);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Can't read opt-out shared preferences.", e.getCause());
            return false;
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Can't read opt-out shared preferences.", e2);
            return false;
        }
    }

    private void writeIdentities() {
        try {
            SharedPreferences.Editor edit = this.mLoadStoredPreferences.get().edit();
            edit.putString("events_distinct_id", this.mEventsDistinctId);
            edit.putBoolean("events_user_id_present", this.mEventsUserIdPresent);
            edit.putString("people_distinct_id", this.mPeopleDistinctId);
            edit.putString("anonymous_id", this.mAnonymousId);
            edit.putBoolean("had_persisted_distinct_id", this.mHadPersistedDistinctId);
            writeEdits(edit);
        } catch (ExecutionException e) {
            MPLog.m62e(LOGTAG, "Can't write distinct ids to shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            MPLog.m62e(LOGTAG, "Can't write distinct ids to shared preferences.", e2);
        }
    }

    private static void writeEdits(SharedPreferences.Editor editor) {
        editor.apply();
    }
}
