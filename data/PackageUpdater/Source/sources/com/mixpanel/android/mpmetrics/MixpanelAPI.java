package com.mixpanel.android.mpmetrics;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.mixpanel.android.mpmetrics.AnalyticsMessages;
import com.mixpanel.android.mpmetrics.SharedPreferencesLoader;
import com.mixpanel.android.util.MPLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MixpanelAPI {
    private static final String APP_LINKS_LOGTAG = "MixpanelAPI.AL";
    private static final String ENGAGE_DATE_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String LOGTAG = "MixpanelAPI.API";
    public static final String VERSION = "7.0.1";
    private static final Map<String, Map<Context, MixpanelAPI>> sInstanceMap = new HashMap();
    private static final SharedPreferencesLoader sPrefsLoader = new SharedPreferencesLoader();
    private static Future<SharedPreferences> sReferrerPrefs;
    private final MPConfig mConfig;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Map<String, String> mDeviceInfo;
    private final Map<String, Long> mEventTimings;
    /* access modifiers changed from: private */
    public final Map<String, GroupImpl> mGroups;
    private final AnalyticsMessages mMessages;
    private MixpanelActivityLifecycleCallbacks mMixpanelActivityLifecycleCallbacks;
    /* access modifiers changed from: private */
    public final PeopleImpl mPeople;
    /* access modifiers changed from: private */
    public final PersistentIdentity mPersistentIdentity;
    /* access modifiers changed from: private */
    public final SessionMetadata mSessionMetadata;
    /* access modifiers changed from: private */
    public final String mToken;
    private final Boolean mTrackAutomaticEvents;

    public interface Group {
        void deleteGroup();

        void remove(String str, Object obj);

        void set(String str, Object obj);

        void set(JSONObject jSONObject);

        void setMap(Map<String, Object> map);

        void setOnce(String str, Object obj);

        void setOnce(JSONObject jSONObject);

        void setOnceMap(Map<String, Object> map);

        void union(String str, JSONArray jSONArray);

        void unset(String str);
    }

    interface InstanceProcessor {
        void process(MixpanelAPI mixpanelAPI);
    }

    public interface People {
        void append(String str, Object obj);

        void clearCharges();

        void deleteUser();

        @Deprecated
        String getDistinctId();

        @Deprecated
        void identify(String str);

        void increment(String str, double d);

        void increment(Map<String, ? extends Number> map);

        boolean isIdentified();

        void merge(String str, JSONObject jSONObject);

        void remove(String str, Object obj);

        void set(String str, Object obj);

        void set(JSONObject jSONObject);

        void setMap(Map<String, Object> map);

        void setOnce(String str, Object obj);

        void setOnce(JSONObject jSONObject);

        void setOnceMap(Map<String, Object> map);

        void trackCharge(double d, JSONObject jSONObject);

        void union(String str, JSONArray jSONArray);

        void unset(String str);

        People withIdentity(String str);
    }

    MixpanelAPI(Context context, Future<SharedPreferences> future, String str, boolean z, JSONObject jSONObject, boolean z2) {
        this(context, future, str, MPConfig.getInstance(context), z, jSONObject, (String) null, z2);
    }

    MixpanelAPI(Context context, Future<SharedPreferences> future, String str, boolean z, JSONObject jSONObject, String str2, boolean z2) {
        this(context, future, str, MPConfig.getInstance(context), z, jSONObject, str2, z2);
    }

    MixpanelAPI(Context context, Future<SharedPreferences> future, String str, MPConfig mPConfig, boolean z, JSONObject jSONObject, String str2, boolean z2) {
        JSONObject jSONObject2 = jSONObject;
        this.mContext = context;
        this.mToken = str;
        this.mPeople = new PeopleImpl();
        this.mGroups = new HashMap();
        this.mConfig = mPConfig;
        this.mTrackAutomaticEvents = Boolean.valueOf(z2);
        HashMap hashMap = new HashMap();
        hashMap.put("$android_lib_version", "7.0.1");
        hashMap.put("$android_os", "Android");
        String str3 = "UNKNOWN";
        hashMap.put("$android_os_version", Build.VERSION.RELEASE == null ? str3 : Build.VERSION.RELEASE);
        hashMap.put("$android_manufacturer", Build.MANUFACTURER == null ? str3 : Build.MANUFACTURER);
        hashMap.put("$android_brand", Build.BRAND == null ? str3 : Build.BRAND);
        hashMap.put("$android_model", Build.MODEL != null ? Build.MODEL : str3);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            hashMap.put("$android_app_version", packageInfo.versionName);
            hashMap.put("$android_app_version_code", Integer.toString(packageInfo.versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            MPLog.m62e(LOGTAG, "Exception getting app version name", e);
        }
        this.mDeviceInfo = Collections.unmodifiableMap(hashMap);
        this.mSessionMetadata = new SessionMetadata();
        this.mMessages = getAnalyticsMessages();
        Future<SharedPreferences> future2 = future;
        PersistentIdentity persistentIdentity = getPersistentIdentity(context, future, str, str2);
        this.mPersistentIdentity = persistentIdentity;
        this.mEventTimings = persistentIdentity.getTimeEvents();
        if (z && (hasOptedOutTracking() || !persistentIdentity.hasOptOutFlag(str))) {
            optOutTracking();
        }
        if (jSONObject2 != null) {
            registerSuperProperties(jSONObject2);
        }
        boolean exists = MPDbAdapter.getInstance(this.mContext).getDatabaseFile().exists();
        registerMixpanelActivityLifecycleCallbacks();
        if (persistentIdentity.isFirstLaunch(exists, this.mToken) && this.mTrackAutomaticEvents.booleanValue()) {
            track(AutomaticEvents.FIRST_OPEN, (JSONObject) null, true);
            persistentIdentity.setHasLaunched(this.mToken);
        }
        if (sendAppOpen() && this.mTrackAutomaticEvents.booleanValue()) {
            track("$app_open", (JSONObject) null);
        }
        if (!persistentIdentity.isFirstIntegration(this.mToken)) {
            try {
                sendHttpEvent("Integration", "85053bf24bba75239b16a601d9387e17", str, (JSONObject) null, false);
                persistentIdentity.setIsIntegrated(this.mToken);
            } catch (JSONException unused) {
            }
        }
        if (this.mPersistentIdentity.isNewVersion((String) hashMap.get("$android_app_version_code")) && this.mTrackAutomaticEvents.booleanValue()) {
            try {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(AutomaticEvents.VERSION_UPDATED, hashMap.get("$android_app_version"));
                track(AutomaticEvents.APP_UPDATED, jSONObject3, true);
            } catch (JSONException unused2) {
            }
        }
        if (isDebuggingMode()) {
            try {
                if (this.mToken.length() == 32) {
                    trackMixpanelDevX();
                }
            } catch (JSONException unused3) {
            }
        }
        if (!this.mConfig.getDisableExceptionHandler()) {
            ExceptionHandler.init();
        }
    }

    private boolean isDebuggingMode() {
        return (getContext().getApplicationInfo().flags & 2) != 0;
    }

    private void trackMixpanelDevX() throws JSONException {
        trackDebugLaunch();
        trackMixpanelImplemented();
    }

    private void trackDebugLaunch() throws JSONException {
        int debugInitCount = this.mPersistentIdentity.debugInitCount(this.mToken) + 1;
        this.mPersistentIdentity.setDebugInitCount(this.mToken, debugInitCount);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("Debug Launch Count", debugInitCount);
        sendHttpEvent("SDK Debug Launch", "metrics-1", this.mToken, jSONObject, true);
    }

    private void trackMixpanelImplemented() throws JSONException {
        if (!this.mPersistentIdentity.hasImplemented(this.mToken)) {
            int i = (this.mPersistentIdentity.hasMPDebugTracked(this.mToken) ? 1 : 0) + false + (this.mPersistentIdentity.hasMPDebugIdentified(this.mToken) ? 1 : 0) + (this.mPersistentIdentity.hasMPDebugAliased(this.mToken) ? 1 : 0) + (this.mPersistentIdentity.hasMPDebugUsedPeople(this.mToken) ? 1 : 0);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Tracked", this.mPersistentIdentity.hasMPDebugTracked(this.mToken));
            jSONObject.put("Identified", this.mPersistentIdentity.hasMPDebugIdentified(this.mToken));
            jSONObject.put("Aliased", this.mPersistentIdentity.hasMPDebugAliased(this.mToken));
            jSONObject.put("Used People", this.mPersistentIdentity.hasMPDebugUsedPeople(this.mToken));
            if (i >= 3) {
                sendHttpEvent("SDK Implemented", "metrics-1", this.mToken, jSONObject, true);
                this.mPersistentIdentity.setHasImplemented(this.mToken);
            }
        }
    }

    private void sendHttpEvent(String str, String str2, String str3, JSONObject jSONObject, boolean z) throws JSONException {
        String str4;
        String str5;
        JSONObject superProperties = getSuperProperties();
        String str6 = null;
        if (superProperties != null) {
            try {
                str4 = (String) superProperties.get("mp_lib");
                try {
                    str5 = (String) superProperties.get("$lib_version");
                    str6 = str4;
                } catch (JSONException unused) {
                }
            } catch (JSONException unused2) {
                str4 = null;
            }
        } else {
            str5 = null;
        }
        str4 = str6;
        str6 = str5;
        JSONObject jSONObject2 = new JSONObject();
        if (str4 == null) {
            str4 = "Android";
        }
        jSONObject2.put("mp_lib", str4);
        jSONObject2.put("distinct_id", str3);
        if (str6 == null) {
            str6 = "7.0.1";
        }
        jSONObject2.put("$lib_version", str6);
        jSONObject2.put("DevX", true);
        jSONObject2.put("Project Token", str3);
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject2.put(next, jSONObject.get(next));
            }
        }
        this.mMessages.eventsMessage(new AnalyticsMessages.EventDescription(str, jSONObject2, str2));
        if (z) {
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put(str, 1);
            jSONObject3.put("$add", jSONObject4);
            jSONObject3.put("$token", str2);
            jSONObject3.put("$distinct_id", str3);
            this.mMessages.peopleMessage(new AnalyticsMessages.PeopleDescription(jSONObject3, str2));
        }
        this.mMessages.postToServer(new AnalyticsMessages.MixpanelDescription(str2));
    }

    public static MixpanelAPI getInstance(Context context, String str, boolean z) {
        return getInstance(context, str, false, (JSONObject) null, (String) null, z);
    }

    public static MixpanelAPI getInstance(Context context, String str, String str2, boolean z) {
        return getInstance(context, str, false, (JSONObject) null, str2, z);
    }

    public static MixpanelAPI getInstance(Context context, String str, boolean z, boolean z2) {
        return getInstance(context, str, z, (JSONObject) null, (String) null, z2);
    }

    public static MixpanelAPI getInstance(Context context, String str, boolean z, String str2, boolean z2) {
        return getInstance(context, str, z, (JSONObject) null, str2, z2);
    }

    public static MixpanelAPI getInstance(Context context, String str, JSONObject jSONObject, boolean z) {
        return getInstance(context, str, false, jSONObject, (String) null, z);
    }

    public static MixpanelAPI getInstance(Context context, String str, JSONObject jSONObject, String str2, boolean z) {
        return getInstance(context, str, false, jSONObject, str2, z);
    }

    public static MixpanelAPI getInstance(Context context, String str, boolean z, JSONObject jSONObject, String str2, boolean z2) {
        MixpanelAPI mixpanelAPI;
        Context context2 = context;
        if (str == null || context2 == null) {
            return null;
        }
        Map<String, Map<Context, MixpanelAPI>> map = sInstanceMap;
        synchronized (map) {
            Context applicationContext = context.getApplicationContext();
            if (sReferrerPrefs == null) {
                sReferrerPrefs = sPrefsLoader.loadPreferences(context, "com.mixpanel.android.mpmetrics.ReferralInfo", (SharedPreferencesLoader.OnPrefsLoadedListener) null);
            }
            String str3 = str2 != null ? str2 : str;
            Map map2 = map.get(str3);
            if (map2 == null) {
                map2 = new HashMap();
                map.put(str3, map2);
            }
            Map map3 = map2;
            mixpanelAPI = (MixpanelAPI) map3.get(applicationContext);
            if (mixpanelAPI == null && ConfigurationChecker.checkBasicConfiguration(applicationContext)) {
                MixpanelAPI mixpanelAPI2 = new MixpanelAPI(applicationContext, sReferrerPrefs, str, z, jSONObject, str2, z2);
                registerAppLinksListeners(context, mixpanelAPI2);
                map3.put(applicationContext, mixpanelAPI2);
                mixpanelAPI = mixpanelAPI2;
            }
            checkIntentForInboundAppLink(context);
        }
        return mixpanelAPI;
    }

    public void setUseIpAddressForGeolocation(boolean z) {
        this.mConfig.setUseIpAddressForGeolocation(z);
    }

    public void setEnableLogging(boolean z) {
        this.mConfig.setEnableLogging(z);
        JSONObject jSONObject = new JSONObject();
        if (isDebuggingMode()) {
            try {
                jSONObject.put("Logging Enabled", z);
                sendHttpEvent("Toggle SDK Logging", "metrics-1", this.mToken, jSONObject, true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFlushBatchSize(int i) {
        this.mConfig.setFlushBatchSize(i);
    }

    public int getFlushBatchSize() {
        return this.mConfig.getFlushBatchSize();
    }

    public void setMaximumDatabaseLimit(int i) {
        this.mConfig.setMaximumDatabaseLimit(i);
    }

    public int getMaximumDatabaseLimit() {
        return this.mConfig.getMaximumDatabaseLimit();
    }

    public void setServerURL(String str) {
        this.mConfig.setServerURL(str);
    }

    public Boolean getTrackAutomaticEvents() {
        return this.mTrackAutomaticEvents;
    }

    public void alias(String str, String str2) {
        if (!hasOptedOutTracking()) {
            if (str2 == null) {
                str2 = getDistinctId();
            }
            if (str.equals(str2)) {
                MPLog.m67w(LOGTAG, "Attempted to alias identical distinct_ids " + str + ". Alias message will not be sent.");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("alias", str);
                jSONObject.put("original", str2);
                track("$create_alias", jSONObject);
                if (isDebuggingMode()) {
                    this.mPersistentIdentity.setHasMPDebugAliased(this.mToken);
                }
            } catch (JSONException e) {
                MPLog.m62e(LOGTAG, "Failed to alias", e);
            }
            flush();
        }
    }

    public void identify(String str) {
        identify(str, true, true);
    }

    public void identify(String str, boolean z) {
        identify(str, true, z);
    }

    private void identify(String str, boolean z, boolean z2) {
        if (!hasOptedOutTracking()) {
            if (str == null) {
                MPLog.m61e(LOGTAG, "Can't identify with null distinct_id.");
                return;
            }
            synchronized (this.mPersistentIdentity) {
                String eventsDistinctId = this.mPersistentIdentity.getEventsDistinctId();
                this.mPersistentIdentity.setAnonymousIdIfAbsent(eventsDistinctId);
                this.mPersistentIdentity.setEventsDistinctId(str);
                if (z) {
                    this.mPersistentIdentity.markEventsUserIdPresent();
                }
                if (!str.equals(eventsDistinctId)) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("$anon_distinct_id", eventsDistinctId);
                        track("$identify", jSONObject);
                        if (isDebuggingMode()) {
                            this.mPersistentIdentity.setHasMPDebugIdentified(this.mToken);
                        }
                    } catch (JSONException unused) {
                        MPLog.m61e(LOGTAG, "Could not track $identify event");
                    }
                }
                if (z2) {
                    this.mPeople.identify_people(str);
                }
            }
        }
    }

    public void timeEvent(String str) {
        if (!hasOptedOutTracking()) {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this.mEventTimings) {
                this.mEventTimings.put(str, Long.valueOf(currentTimeMillis));
                this.mPersistentIdentity.addTimeEvent(str, Long.valueOf(currentTimeMillis));
            }
        }
    }

    public void clearTimedEvents() {
        synchronized (this.mEventTimings) {
            this.mEventTimings.clear();
            this.mPersistentIdentity.clearTimedEvents();
        }
    }

    public void clearTimedEvent(String str) {
        synchronized (this.mEventTimings) {
            this.mEventTimings.remove(str);
            this.mPersistentIdentity.removeTimedEvent(str);
        }
    }

    public double eventElapsedTime(String str) {
        Long l;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.mEventTimings) {
            l = this.mEventTimings.get(str);
        }
        if (l == null) {
            return 0.0d;
        }
        return (double) ((currentTimeMillis - l.longValue()) / 1000);
    }

    public void trackMap(String str, Map<String, Object> map) {
        if (!hasOptedOutTracking()) {
            if (map == null) {
                track(str, (JSONObject) null);
                return;
            }
            try {
                track(str, new JSONObject(map));
            } catch (NullPointerException unused) {
                MPLog.m67w(LOGTAG, "Can't have null keys in the properties of trackMap!");
            }
        }
    }

    public void trackWithGroups(String str, Map<String, Object> map, Map<String, Object> map2) {
        if (!hasOptedOutTracking()) {
            if (map2 == null) {
                trackMap(str, map);
            } else if (map == null) {
                trackMap(str, map2);
            } else {
                for (Map.Entry next : map2.entrySet()) {
                    if (next.getValue() != null) {
                        map.put(next.getKey(), next.getValue());
                    }
                }
                trackMap(str, map);
            }
        }
    }

    public void track(String str, JSONObject jSONObject) {
        if (!hasOptedOutTracking()) {
            track(str, jSONObject, false);
        }
    }

    public void track(String str) {
        if (!hasOptedOutTracking()) {
            track(str, (JSONObject) null);
        }
    }

    public void flush() {
        if (!hasOptedOutTracking()) {
            this.mMessages.postToServer(new AnalyticsMessages.MixpanelDescription(this.mToken));
        }
    }

    public JSONObject getSuperProperties() {
        JSONObject jSONObject = new JSONObject();
        this.mPersistentIdentity.addSuperPropertiesToObject(jSONObject);
        return jSONObject;
    }

    public String getDistinctId() {
        return this.mPersistentIdentity.getEventsDistinctId();
    }

    public String getAnonymousId() {
        return this.mPersistentIdentity.getAnonymousId();
    }

    /* access modifiers changed from: protected */
    public String getUserId() {
        return this.mPersistentIdentity.getEventsUserId();
    }

    public void registerSuperPropertiesMap(Map<String, Object> map) {
        if (!hasOptedOutTracking()) {
            if (map == null) {
                MPLog.m61e(LOGTAG, "registerSuperPropertiesMap does not accept null properties");
                return;
            }
            try {
                registerSuperProperties(new JSONObject(map));
            } catch (NullPointerException unused) {
                MPLog.m67w(LOGTAG, "Can't have null keys in the properties of registerSuperPropertiesMap");
            }
        }
    }

    public void registerSuperProperties(JSONObject jSONObject) {
        if (!hasOptedOutTracking()) {
            this.mPersistentIdentity.registerSuperProperties(jSONObject);
        }
    }

    public void unregisterSuperProperty(String str) {
        if (!hasOptedOutTracking()) {
            this.mPersistentIdentity.unregisterSuperProperty(str);
        }
    }

    public void registerSuperPropertiesOnceMap(Map<String, Object> map) {
        if (!hasOptedOutTracking()) {
            if (map == null) {
                MPLog.m61e(LOGTAG, "registerSuperPropertiesOnceMap does not accept null properties");
                return;
            }
            try {
                registerSuperPropertiesOnce(new JSONObject(map));
            } catch (NullPointerException unused) {
                MPLog.m67w(LOGTAG, "Can't have null keys in the properties of registerSuperPropertiesOnce!");
            }
        }
    }

    public void registerSuperPropertiesOnce(JSONObject jSONObject) {
        if (!hasOptedOutTracking()) {
            this.mPersistentIdentity.registerSuperPropertiesOnce(jSONObject);
        }
    }

    public void clearSuperProperties() {
        this.mPersistentIdentity.clearSuperProperties();
    }

    public void updateSuperProperties(SuperPropertyUpdate superPropertyUpdate) {
        if (!hasOptedOutTracking()) {
            this.mPersistentIdentity.updateSuperProperties(superPropertyUpdate);
        }
    }

    public void setGroup(String str, Object obj) {
        if (!hasOptedOutTracking()) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(obj);
            setGroup(str, (List<Object>) arrayList);
        }
    }

    public void setGroup(String str, List<Object> list) {
        if (!hasOptedOutTracking()) {
            JSONArray jSONArray = new JSONArray();
            for (Object next : list) {
                if (next == null) {
                    MPLog.m67w(LOGTAG, "groupID must be non-null");
                } else {
                    jSONArray.put(next);
                }
            }
            try {
                registerSuperProperties(new JSONObject().put(str, jSONArray));
                this.mPeople.set(str, jSONArray);
            } catch (JSONException unused) {
                MPLog.m67w(LOGTAG, "groupKey must be non-null");
            }
        }
    }

    public void addGroup(final String str, final Object obj) {
        if (!hasOptedOutTracking()) {
            updateSuperProperties(new SuperPropertyUpdate() {
                public JSONObject update(JSONObject jSONObject) {
                    try {
                        jSONObject.accumulate(str, obj);
                    } catch (JSONException e) {
                        MPLog.m62e(MixpanelAPI.LOGTAG, "Failed to add groups superProperty", e);
                    }
                    return jSONObject;
                }
            });
            this.mPeople.union(str, new JSONArray().put(obj));
        }
    }

    public void removeGroup(final String str, final Object obj) {
        if (!hasOptedOutTracking()) {
            updateSuperProperties(new SuperPropertyUpdate() {
                public JSONObject update(JSONObject jSONObject) {
                    try {
                        JSONArray jSONArray = jSONObject.getJSONArray(str);
                        JSONArray jSONArray2 = new JSONArray();
                        if (jSONArray.length() <= 1) {
                            jSONObject.remove(str);
                            MixpanelAPI.this.mPeople.unset(str);
                        } else {
                            for (int i = 0; i < jSONArray.length(); i++) {
                                if (!jSONArray.get(i).equals(obj)) {
                                    jSONArray2.put(jSONArray.get(i));
                                }
                            }
                            jSONObject.put(str, jSONArray2);
                            MixpanelAPI.this.mPeople.remove(str, obj);
                        }
                    } catch (JSONException unused) {
                        jSONObject.remove(str);
                        MixpanelAPI.this.mPeople.unset(str);
                    }
                    return jSONObject;
                }
            });
        }
    }

    public People getPeople() {
        return this.mPeople;
    }

    public Group getGroup(String str, Object obj) {
        String makeMapKey = makeMapKey(str, obj);
        GroupImpl groupImpl = this.mGroups.get(makeMapKey);
        if (groupImpl == null) {
            groupImpl = new GroupImpl(str, obj);
            this.mGroups.put(makeMapKey, groupImpl);
        }
        if (groupImpl.mGroupKey.equals(str) && groupImpl.mGroupID.equals(obj)) {
            return groupImpl;
        }
        MPLog.m63i(LOGTAG, "groups map key collision " + makeMapKey);
        GroupImpl groupImpl2 = new GroupImpl(str, obj);
        this.mGroups.put(makeMapKey, groupImpl2);
        return groupImpl2;
    }

    /* access modifiers changed from: private */
    public String makeMapKey(String str, Object obj) {
        return str + '_' + obj;
    }

    public void reset() {
        this.mPersistentIdentity.clearPreferences();
        getAnalyticsMessages().clearAnonymousUpdatesMessage(new AnalyticsMessages.MixpanelDescription(this.mToken));
        identify(getDistinctId(), false);
        flush();
    }

    public Map<String, String> getDeviceInfo() {
        return this.mDeviceInfo;
    }

    public void optOutTracking() {
        getAnalyticsMessages().emptyTrackingQueues(new AnalyticsMessages.MixpanelDescription(this.mToken));
        if (getPeople().isIdentified()) {
            getPeople().deleteUser();
            getPeople().clearCharges();
        }
        this.mPersistentIdentity.clearPreferences();
        synchronized (this.mEventTimings) {
            this.mEventTimings.clear();
            this.mPersistentIdentity.clearTimedEvents();
        }
        this.mPersistentIdentity.clearReferrerProperties();
        this.mPersistentIdentity.setOptOutTracking(true, this.mToken);
    }

    public void optInTracking() {
        optInTracking((String) null, (JSONObject) null);
    }

    public void optInTracking(String str) {
        optInTracking(str, (JSONObject) null);
    }

    public void optInTracking(String str, JSONObject jSONObject) {
        this.mPersistentIdentity.setOptOutTracking(false, this.mToken);
        if (str != null) {
            identify(str);
        }
        track("$opt_in", jSONObject);
    }

    public boolean hasOptedOutTracking() {
        return this.mPersistentIdentity.getOptOutTracking(this.mToken);
    }

    /* access modifiers changed from: package-private */
    public void registerMixpanelActivityLifecycleCallbacks() {
        if (Build.VERSION.SDK_INT < 14) {
            return;
        }
        if (this.mContext.getApplicationContext() instanceof Application) {
            MixpanelActivityLifecycleCallbacks mixpanelActivityLifecycleCallbacks = new MixpanelActivityLifecycleCallbacks(this, this.mConfig);
            this.mMixpanelActivityLifecycleCallbacks = mixpanelActivityLifecycleCallbacks;
            ((Application) this.mContext.getApplicationContext()).registerActivityLifecycleCallbacks(mixpanelActivityLifecycleCallbacks);
            return;
        }
        MPLog.m63i(LOGTAG, "Context is not an Application, Mixpanel won't be able to automatically flush on an app background.");
    }

    public boolean isAppInForeground() {
        if (Build.VERSION.SDK_INT >= 14) {
            MixpanelActivityLifecycleCallbacks mixpanelActivityLifecycleCallbacks = this.mMixpanelActivityLifecycleCallbacks;
            if (mixpanelActivityLifecycleCallbacks != null) {
                return mixpanelActivityLifecycleCallbacks.isInForeground();
            }
            return false;
        }
        MPLog.m61e(LOGTAG, "Your build version is below 14. This method will always return false.");
        return false;
    }

    /* access modifiers changed from: package-private */
    public void onBackground() {
        if (this.mConfig.getFlushOnBackground()) {
            flush();
        }
    }

    /* access modifiers changed from: package-private */
    public void onForeground() {
        this.mSessionMetadata.initSession();
    }

    static void allInstances(InstanceProcessor instanceProcessor) {
        Map<String, Map<Context, MixpanelAPI>> map = sInstanceMap;
        synchronized (map) {
            for (Map<Context, MixpanelAPI> values : map.values()) {
                for (MixpanelAPI process : values.values()) {
                    instanceProcessor.process(process);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public AnalyticsMessages getAnalyticsMessages() {
        return AnalyticsMessages.getInstance(this.mContext);
    }

    /* access modifiers changed from: package-private */
    public PersistentIdentity getPersistentIdentity(Context context, Future<SharedPreferences> future, String str) {
        return getPersistentIdentity(context, future, str, (String) null);
    }

    /* access modifiers changed from: package-private */
    public PersistentIdentity getPersistentIdentity(Context context, Future<SharedPreferences> future, String str, String str2) {
        C13103 r0 = new SharedPreferencesLoader.OnPrefsLoadedListener() {
            public void onPrefsLoaded(SharedPreferences sharedPreferences) {
                String peopleDistinctId = PersistentIdentity.getPeopleDistinctId(sharedPreferences);
                if (peopleDistinctId != null) {
                    MixpanelAPI.this.pushWaitingPeopleRecord(peopleDistinctId);
                }
            }
        };
        if (str2 != null) {
            str = str2;
        }
        String str3 = "com.mixpanel.android.mpmetrics.MixpanelAPI_" + str;
        SharedPreferencesLoader sharedPreferencesLoader = sPrefsLoader;
        return new PersistentIdentity(future, sharedPreferencesLoader.loadPreferences(context, str3, r0), sharedPreferencesLoader.loadPreferences(context, "com.mixpanel.android.mpmetrics.MixpanelAPI.TimeEvents_" + str, (SharedPreferencesLoader.OnPrefsLoadedListener) null), sharedPreferencesLoader.loadPreferences(context, "com.mixpanel.android.mpmetrics.Mixpanel", (SharedPreferencesLoader.OnPrefsLoadedListener) null));
    }

    /* access modifiers changed from: package-private */
    public boolean sendAppOpen() {
        return !this.mConfig.getDisableAppOpenEvent();
    }

    private class PeopleImpl implements People {
        private PeopleImpl() {
        }

        public void identify(String str) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                MPLog.m67w(MixpanelAPI.LOGTAG, "People.identify() is deprecated and calling it is no longer necessary, please use MixpanelAPI.identify() and set 'usePeople' to true instead");
                if (str == null) {
                    MPLog.m61e(MixpanelAPI.LOGTAG, "Can't identify with null distinct_id.");
                } else if (str != MixpanelAPI.this.mPersistentIdentity.getEventsDistinctId()) {
                    MPLog.m67w(MixpanelAPI.LOGTAG, "Identifying with a distinct_id different from the one being set by MixpanelAPI.identify() is not supported.");
                } else {
                    identify_people(str);
                }
            }
        }

        /* access modifiers changed from: private */
        public void identify_people(String str) {
            synchronized (MixpanelAPI.this.mPersistentIdentity) {
                MixpanelAPI.this.mPersistentIdentity.setPeopleDistinctId(str);
            }
            MixpanelAPI.this.pushWaitingPeopleRecord(str);
        }

        public void setMap(Map<String, Object> map) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                if (map == null) {
                    MPLog.m61e(MixpanelAPI.LOGTAG, "setMap does not accept null properties");
                    return;
                }
                try {
                    set(new JSONObject(map));
                } catch (NullPointerException unused) {
                    MPLog.m67w(MixpanelAPI.LOGTAG, "Can't have null keys in the properties of setMap!");
                }
            }
        }

        public void set(JSONObject jSONObject) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    JSONObject jSONObject2 = new JSONObject(MixpanelAPI.this.mDeviceInfo);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject2.put(next, jSONObject.get(next));
                    }
                    MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$set", jSONObject2));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception setting people properties", e);
                }
            }
        }

        public void set(String str, Object obj) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    set(new JSONObject().put(str, obj));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "set", e);
                }
            }
        }

        public void setOnceMap(Map<String, Object> map) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                if (map == null) {
                    MPLog.m61e(MixpanelAPI.LOGTAG, "setOnceMap does not accept null properties");
                    return;
                }
                try {
                    setOnce(new JSONObject(map));
                } catch (NullPointerException unused) {
                    MPLog.m67w(MixpanelAPI.LOGTAG, "Can't have null keys in the properties setOnceMap!");
                }
            }
        }

        public void setOnce(JSONObject jSONObject) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$set_once", jSONObject));
                } catch (JSONException unused) {
                    MPLog.m61e(MixpanelAPI.LOGTAG, "Exception setting people properties");
                }
            }
        }

        public void setOnce(String str, Object obj) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    setOnce(new JSONObject().put(str, obj));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "set", e);
                }
            }
        }

        public void increment(Map<String, ? extends Number> map) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$add", new JSONObject(map)));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception incrementing properties", e);
                }
            }
        }

        public void merge(String str, JSONObject jSONObject) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put(str, jSONObject);
                    MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$merge", jSONObject2));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception merging a property", e);
                }
            }
        }

        public void increment(String str, double d) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                HashMap hashMap = new HashMap();
                hashMap.put(str, Double.valueOf(d));
                increment(hashMap);
            }
        }

        public void append(String str, Object obj) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(str, obj);
                    MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$append", jSONObject));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception appending a property", e);
                }
            }
        }

        public void union(String str, JSONArray jSONArray) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(str, jSONArray);
                    MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$union", jSONObject));
                } catch (JSONException unused) {
                    MPLog.m61e(MixpanelAPI.LOGTAG, "Exception unioning a property");
                }
            }
        }

        public void remove(String str, Object obj) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(str, obj);
                    MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$remove", jSONObject));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception appending a property", e);
                }
            }
        }

        public void unset(String str) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(str);
                    MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$unset", jSONArray));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception unsetting a property", e);
                }
            }
        }

        public void trackCharge(double d, JSONObject jSONObject) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MixpanelAPI.ENGAGE_DATE_FORMAT_STRING, Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("$amount", d);
                    jSONObject2.put("$time", simpleDateFormat.format(date));
                    if (jSONObject != null) {
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            jSONObject2.put(next, jSONObject.get(next));
                        }
                    }
                    append("$transactions", jSONObject2);
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception creating new charge", e);
                }
            }
        }

        public void clearCharges() {
            unset("$transactions");
        }

        public void deleteUser() {
            try {
                MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$delete", JSONObject.NULL));
            } catch (JSONException unused) {
                MPLog.m61e(MixpanelAPI.LOGTAG, "Exception deleting a user");
            }
        }

        public String getDistinctId() {
            return MixpanelAPI.this.mPersistentIdentity.getPeopleDistinctId();
        }

        public People withIdentity(final String str) {
            if (str == null) {
                return null;
            }
            return new PeopleImpl() {
                public String getDistinctId() {
                    return str;
                }

                public void identify(String str) {
                    throw new RuntimeException("This MixpanelPeople object has a fixed, constant distinctId");
                }
            };
        }

        private JSONObject stdPeopleMessage(String str, Object obj) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            String distinctId = getDistinctId();
            String anonymousId = MixpanelAPI.this.getAnonymousId();
            jSONObject.put(str, obj);
            jSONObject.put("$token", MixpanelAPI.this.mToken);
            jSONObject.put("$time", System.currentTimeMillis());
            jSONObject.put("$had_persisted_distinct_id", MixpanelAPI.this.mPersistentIdentity.getHadPersistedDistinctId());
            if (anonymousId != null) {
                jSONObject.put("$device_id", anonymousId);
            }
            if (distinctId != null) {
                jSONObject.put("$distinct_id", distinctId);
                jSONObject.put("$user_id", distinctId);
            }
            jSONObject.put("$mp_metadata", MixpanelAPI.this.mSessionMetadata.getMetadataForPeople());
            if ((MixpanelAPI.this.getContext().getApplicationInfo().flags & 2) != 0 && (obj instanceof JSONObject)) {
                JSONObject jSONObject2 = (JSONObject) obj;
                if (jSONObject2.keys().hasNext() && !jSONObject2.keys().next().startsWith("$ae_")) {
                    MixpanelAPI.this.mPersistentIdentity.setHasMPDebugUsedPeople(MixpanelAPI.this.mToken);
                }
            }
            return jSONObject;
        }

        public boolean isIdentified() {
            return getDistinctId() != null;
        }
    }

    private class GroupImpl implements Group {
        /* access modifiers changed from: private */
        public final Object mGroupID;
        /* access modifiers changed from: private */
        public final String mGroupKey;

        public GroupImpl(String str, Object obj) {
            this.mGroupKey = str;
            this.mGroupID = obj;
        }

        public void setMap(Map<String, Object> map) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                if (map == null) {
                    MPLog.m61e(MixpanelAPI.LOGTAG, "setMap does not accept null properties");
                } else {
                    set(new JSONObject(map));
                }
            }
        }

        public void set(JSONObject jSONObject) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject2.put(next, jSONObject.get(next));
                    }
                    MixpanelAPI.this.recordGroupMessage(stdGroupMessage("$set", jSONObject2));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception setting group properties", e);
                }
            }
        }

        public void set(String str, Object obj) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    set(new JSONObject().put(str, obj));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "set", e);
                }
            }
        }

        public void setOnceMap(Map<String, Object> map) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                if (map == null) {
                    MPLog.m61e(MixpanelAPI.LOGTAG, "setOnceMap does not accept null properties");
                    return;
                }
                try {
                    setOnce(new JSONObject(map));
                } catch (NullPointerException unused) {
                    MPLog.m67w(MixpanelAPI.LOGTAG, "Can't have null keys in the properties for setOnceMap!");
                }
            }
        }

        public void setOnce(JSONObject jSONObject) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    MixpanelAPI.this.recordGroupMessage(stdGroupMessage("$set_once", jSONObject));
                } catch (JSONException unused) {
                    MPLog.m61e(MixpanelAPI.LOGTAG, "Exception setting group properties");
                }
            }
        }

        public void setOnce(String str, Object obj) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    setOnce(new JSONObject().put(str, obj));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Property name cannot be null", e);
                }
            }
        }

        public void union(String str, JSONArray jSONArray) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(str, jSONArray);
                    MixpanelAPI.this.recordGroupMessage(stdGroupMessage("$union", jSONObject));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception unioning a property", e);
                }
            }
        }

        public void remove(String str, Object obj) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(str, obj);
                    MixpanelAPI.this.recordGroupMessage(stdGroupMessage("$remove", jSONObject));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception removing a property", e);
                }
            }
        }

        public void unset(String str) {
            if (!MixpanelAPI.this.hasOptedOutTracking()) {
                try {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(str);
                    MixpanelAPI.this.recordGroupMessage(stdGroupMessage("$unset", jSONArray));
                } catch (JSONException e) {
                    MPLog.m62e(MixpanelAPI.LOGTAG, "Exception unsetting a property", e);
                }
            }
        }

        public void deleteGroup() {
            try {
                MixpanelAPI.this.recordGroupMessage(stdGroupMessage("$delete", JSONObject.NULL));
                MixpanelAPI.this.mGroups.remove(MixpanelAPI.this.makeMapKey(this.mGroupKey, this.mGroupID));
            } catch (JSONException e) {
                MPLog.m62e(MixpanelAPI.LOGTAG, "Exception deleting a group", e);
            }
        }

        private JSONObject stdGroupMessage(String str, Object obj) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str, obj);
            jSONObject.put("$token", MixpanelAPI.this.mToken);
            jSONObject.put("$time", System.currentTimeMillis());
            jSONObject.put("$group_key", this.mGroupKey);
            jSONObject.put("$group_id", this.mGroupID);
            jSONObject.put("$mp_metadata", MixpanelAPI.this.mSessionMetadata.getMetadataForPeople());
            return jSONObject;
        }
    }

    /* access modifiers changed from: protected */
    public void track(String str, JSONObject jSONObject, boolean z) {
        Long l;
        if (hasOptedOutTracking()) {
            return;
        }
        if (!z || this.mTrackAutomaticEvents.booleanValue()) {
            synchronized (this.mEventTimings) {
                l = this.mEventTimings.get(str);
                this.mEventTimings.remove(str);
                this.mPersistentIdentity.removeTimedEvent(str);
            }
            try {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry next : this.mPersistentIdentity.getReferrerProperties().entrySet()) {
                    jSONObject2.put((String) next.getKey(), (String) next.getValue());
                }
                this.mPersistentIdentity.addSuperPropertiesToObject(jSONObject2);
                double currentTimeMillis = ((double) System.currentTimeMillis()) / 1000.0d;
                String distinctId = getDistinctId();
                String anonymousId = getAnonymousId();
                String userId = getUserId();
                jSONObject2.put("time", System.currentTimeMillis());
                jSONObject2.put("distinct_id", distinctId);
                jSONObject2.put("$had_persisted_distinct_id", this.mPersistentIdentity.getHadPersistedDistinctId());
                if (anonymousId != null) {
                    jSONObject2.put("$device_id", anonymousId);
                }
                if (userId != null) {
                    jSONObject2.put("$user_id", userId);
                }
                if (l != null) {
                    jSONObject2.put("$duration", currentTimeMillis - (((double) l.longValue()) / 1000.0d));
                }
                if (jSONObject != null) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next2 = keys.next();
                        jSONObject2.put(next2, jSONObject.opt(next2));
                    }
                }
                this.mMessages.eventsMessage(new AnalyticsMessages.EventDescription(str, jSONObject2, this.mToken, z, this.mSessionMetadata.getMetadataForEvent()));
                if (isDebuggingMode() && !str.startsWith("$")) {
                    this.mPersistentIdentity.setHasMPDebugTracked(this.mToken);
                }
            } catch (JSONException e) {
                MPLog.m62e(LOGTAG, "Exception tracking event " + str, e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void recordPeopleMessage(JSONObject jSONObject) {
        if (!hasOptedOutTracking()) {
            this.mMessages.peopleMessage(new AnalyticsMessages.PeopleDescription(jSONObject, this.mToken));
        }
    }

    /* access modifiers changed from: private */
    public void recordGroupMessage(JSONObject jSONObject) {
        if (!hasOptedOutTracking()) {
            if (!jSONObject.has("$group_key") || !jSONObject.has("$group_id")) {
                MPLog.m61e(LOGTAG, "Attempt to update group without key and value--this should not happen.");
            } else {
                this.mMessages.groupMessage(new AnalyticsMessages.GroupDescription(jSONObject, this.mToken));
            }
        }
    }

    /* access modifiers changed from: private */
    public void pushWaitingPeopleRecord(String str) {
        this.mMessages.pushAnonymousPeopleMessage(new AnalyticsMessages.PushAnonymousPeopleDescription(str, this.mToken));
    }

    private static void registerAppLinksListeners(Context context, MixpanelAPI mixpanelAPI) {
        try {
            Class<?> cls = Class.forName("androidx.localbroadcastmanager.content.LocalBroadcastManager");
            Method method = cls.getMethod("getInstance", new Class[]{Context.class});
            cls.getMethod("registerReceiver", new Class[]{BroadcastReceiver.class, IntentFilter.class}).invoke(method.invoke((Object) null, new Object[]{context}), new Object[]{new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    JSONObject jSONObject = new JSONObject();
                    Bundle bundleExtra = intent.getBundleExtra("event_args");
                    if (bundleExtra != null) {
                        for (String str : bundleExtra.keySet()) {
                            try {
                                jSONObject.put(str, bundleExtra.get(str));
                            } catch (JSONException e) {
                                MPLog.m62e(MixpanelAPI.APP_LINKS_LOGTAG, "failed to add key \"" + str + "\" to properties for tracking bolts event", e);
                            }
                        }
                    }
                    MixpanelAPI.this.track("$" + intent.getStringExtra("event_name"), jSONObject);
                }
            }, new IntentFilter("com.parse.bolts.measurement_event")});
        } catch (InvocationTargetException e) {
            MPLog.m60d(APP_LINKS_LOGTAG, "Failed to invoke LocalBroadcastManager.registerReceiver() -- App Links tracking will not be enabled due to this exception", e);
        } catch (ClassNotFoundException e2) {
            MPLog.m59d(APP_LINKS_LOGTAG, "To enable App Links tracking, add implementation 'androidx.localbroadcastmanager:localbroadcastmanager:1.0.0': " + e2.getMessage());
        } catch (NoSuchMethodException e3) {
            MPLog.m59d(APP_LINKS_LOGTAG, "To enable App Links tracking, add implementation 'androidx.localbroadcastmanager:localbroadcastmanager:1.0.0': " + e3.getMessage());
        } catch (IllegalAccessException e4) {
            MPLog.m59d(APP_LINKS_LOGTAG, "App Links tracking will not be enabled due to this exception: " + e4.getMessage());
        }
    }

    private static void checkIntentForInboundAppLink(Context context) {
        if (context instanceof Activity) {
            try {
                Class<?> cls = Class.forName("bolts.AppLinks");
                Intent intent = ((Activity) context).getIntent();
                cls.getMethod("getTargetUrlFromInboundIntent", new Class[]{Context.class, Intent.class}).invoke((Object) null, new Object[]{context, intent});
            } catch (InvocationTargetException e) {
                MPLog.m60d(APP_LINKS_LOGTAG, "Failed to invoke bolts.AppLinks.getTargetUrlFromInboundIntent() -- Unable to detect inbound App Links", e);
            } catch (ClassNotFoundException e2) {
                MPLog.m59d(APP_LINKS_LOGTAG, "Please install the Bolts library >= 1.1.2 to track App Links: " + e2.getMessage());
            } catch (NoSuchMethodException e3) {
                MPLog.m59d(APP_LINKS_LOGTAG, "Please install the Bolts library >= 1.1.2 to track App Links: " + e3.getMessage());
            } catch (IllegalAccessException e4) {
                MPLog.m59d(APP_LINKS_LOGTAG, "Unable to detect inbound App Links: " + e4.getMessage());
            }
        } else {
            MPLog.m59d(APP_LINKS_LOGTAG, "Context is not an instance of Activity. To detect inbound App Links, pass an instance of an Activity to getInstance.");
        }
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }
}
