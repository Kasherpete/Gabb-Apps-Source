package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.datadog.android.tracing.TracingInterceptor;
import com.mixpanel.android.util.MPConstants;
import com.mixpanel.android.util.MPLog;
import com.mixpanel.android.util.OfflineMode;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class MPConfig {
    public static boolean DEBUG = false;
    private static final String LOGTAG = "MixpanelAPI.Conf";
    static final String REFERRER_PREFS_NAME = "com.mixpanel.android.mpmetrics.ReferralInfo";
    public static final String VERSION = "7.0.1";
    private static MPConfig sInstance;
    private static final Object sInstanceLock = new Object();
    private final int mBulkUploadLimit;
    private final long mDataExpiration;
    private final boolean mDisableAppOpenEvent;
    private final boolean mDisableExceptionHandler;
    private String mEventsEndpoint;
    private int mFlushBatchSize;
    private final int mFlushInterval;
    private final boolean mFlushOnBackground;
    private String mGroupsEndpoint;
    private int mMaximumDatabaseLimit;
    private final int mMinSessionDuration;
    private final int mMinimumDatabaseLimit;
    private OfflineMode mOfflineMode;
    private String mPeopleEndpoint;
    private final String mResourcePackageName;
    private SSLSocketFactory mSSLSocketFactory;
    private final int mSessionTimeoutDuration;
    private boolean mTrackAutomaticEvents = true;
    private boolean mUseIpAddressForGeolocation;

    public static MPConfig getInstance(Context context) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = readConfig(context.getApplicationContext());
            }
        }
        return sInstance;
    }

    public synchronized void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.mSSLSocketFactory = sSLSocketFactory;
    }

    public synchronized void setOfflineMode(OfflineMode offlineMode) {
        this.mOfflineMode = offlineMode;
    }

    MPConfig(Bundle bundle, Context context) {
        long floatValue;
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
            sSLSocketFactory = instance.getSocketFactory();
        } catch (GeneralSecurityException e) {
            MPLog.m64i(LOGTAG, "System has no SSL support. Built-in events editor will not be available", e);
        }
        this.mSSLSocketFactory = sSLSocketFactory;
        boolean z = bundle.getBoolean("com.mixpanel.android.MPConfig.EnableDebugLogging", false);
        DEBUG = z;
        if (z) {
            MPLog.setLevel(2);
        }
        if (bundle.containsKey("com.mixpanel.android.MPConfig.DebugFlushInterval")) {
            MPLog.m67w(LOGTAG, "We do not support com.mixpanel.android.MPConfig.DebugFlushInterval anymore. There will only be one flush interval. Please, update your AndroidManifest.xml.");
        }
        this.mBulkUploadLimit = bundle.getInt("com.mixpanel.android.MPConfig.BulkUploadLimit", 40);
        this.mFlushInterval = bundle.getInt("com.mixpanel.android.MPConfig.FlushInterval", 60000);
        this.mFlushBatchSize = bundle.getInt("com.mixpanel.android.MPConfig.FlushBatchSize", 50);
        this.mFlushOnBackground = bundle.getBoolean("com.mixpanel.android.MPConfig.FlushOnBackground", true);
        this.mMinimumDatabaseLimit = bundle.getInt("com.mixpanel.android.MPConfig.MinimumDatabaseLimit", 20971520);
        this.mMaximumDatabaseLimit = bundle.getInt("com.mixpanel.android.MPConfig.MaximumDatabaseLimit", Integer.MAX_VALUE);
        this.mResourcePackageName = bundle.getString("com.mixpanel.android.MPConfig.ResourcePackageName");
        this.mDisableAppOpenEvent = bundle.getBoolean("com.mixpanel.android.MPConfig.DisableAppOpenEvent", true);
        this.mDisableExceptionHandler = bundle.getBoolean("com.mixpanel.android.MPConfig.DisableExceptionHandler", false);
        this.mMinSessionDuration = bundle.getInt("com.mixpanel.android.MPConfig.MinimumSessionDuration", 10000);
        this.mSessionTimeoutDuration = bundle.getInt("com.mixpanel.android.MPConfig.SessionTimeoutDuration", Integer.MAX_VALUE);
        this.mUseIpAddressForGeolocation = bundle.getBoolean("com.mixpanel.android.MPConfig.UseIpAddressForGeolocation", true);
        Object obj = bundle.get("com.mixpanel.android.MPConfig.DataExpiration");
        long j = 432000000;
        if (obj != null) {
            try {
                if (obj instanceof Integer) {
                    floatValue = (long) ((Integer) obj).intValue();
                } else if (obj instanceof Float) {
                    floatValue = (long) ((Float) obj).floatValue();
                } else {
                    throw new NumberFormatException(obj.toString() + " is not a number.");
                }
                j = floatValue;
            } catch (Exception e2) {
                MPLog.m62e(LOGTAG, "Error parsing com.mixpanel.android.MPConfig.DataExpiration meta-data value", e2);
            }
        }
        this.mDataExpiration = j;
        boolean containsKey = true ^ bundle.containsKey("com.mixpanel.android.MPConfig.UseIpAddressForGeolocation");
        String string = bundle.getString("com.mixpanel.android.MPConfig.EventsEndpoint");
        if (string != null) {
            setEventsEndpoint(!containsKey ? getEndPointWithIpTrackingParam(string, getUseIpAddressForGeolocation()) : string);
        } else {
            setEventsEndpointWithBaseURL(MPConstants.URL.MIXPANEL_API);
        }
        String string2 = bundle.getString("com.mixpanel.android.MPConfig.PeopleEndpoint");
        if (string2 != null) {
            setPeopleEndpoint(!containsKey ? getEndPointWithIpTrackingParam(string2, getUseIpAddressForGeolocation()) : string2);
        } else {
            setPeopleEndpointWithBaseURL(MPConstants.URL.MIXPANEL_API);
        }
        String string3 = bundle.getString("com.mixpanel.android.MPConfig.GroupsEndpoint");
        if (string3 != null) {
            setGroupsEndpoint(!containsKey ? getEndPointWithIpTrackingParam(string3, getUseIpAddressForGeolocation()) : string3);
        } else {
            setGroupsEndpointWithBaseURL(MPConstants.URL.MIXPANEL_API);
        }
        MPLog.m65v(LOGTAG, toString());
    }

    public int getBulkUploadLimit() {
        return this.mBulkUploadLimit;
    }

    public int getFlushInterval() {
        return this.mFlushInterval;
    }

    public boolean getFlushOnBackground() {
        return this.mFlushOnBackground;
    }

    public int getFlushBatchSize() {
        return this.mFlushBatchSize;
    }

    public void setFlushBatchSize(int i) {
        this.mFlushBatchSize = i;
    }

    public long getDataExpiration() {
        return this.mDataExpiration;
    }

    public int getMinimumDatabaseLimit() {
        return this.mMinimumDatabaseLimit;
    }

    public int getMaximumDatabaseLimit() {
        return this.mMaximumDatabaseLimit;
    }

    public void setMaximumDatabaseLimit(int i) {
        this.mMaximumDatabaseLimit = i;
    }

    public boolean getDisableAppOpenEvent() {
        return this.mDisableAppOpenEvent;
    }

    public String getEventsEndpoint() {
        return this.mEventsEndpoint;
    }

    public boolean getTrackAutomaticEvents() {
        return this.mTrackAutomaticEvents;
    }

    public void setServerURL(String str) {
        setEventsEndpointWithBaseURL(str);
        setPeopleEndpointWithBaseURL(str);
        setGroupsEndpointWithBaseURL(str);
    }

    private String getEndPointWithIpTrackingParam(String str, boolean z) {
        String str2 = "1";
        if (str.contains("?ip=")) {
            StringBuilder append = new StringBuilder().append(str.substring(0, str.indexOf("?ip="))).append("?ip=");
            if (!z) {
                str2 = TracingInterceptor.DROP_SAMPLING_DECISION;
            }
            return append.append(str2).toString();
        }
        StringBuilder append2 = new StringBuilder().append(str).append("?ip=");
        if (!z) {
            str2 = TracingInterceptor.DROP_SAMPLING_DECISION;
        }
        return append2.append(str2).toString();
    }

    private void setEventsEndpointWithBaseURL(String str) {
        setEventsEndpoint(getEndPointWithIpTrackingParam(str + MPConstants.URL.EVENT, getUseIpAddressForGeolocation()));
    }

    private void setEventsEndpoint(String str) {
        this.mEventsEndpoint = str;
    }

    public String getPeopleEndpoint() {
        return this.mPeopleEndpoint;
    }

    private void setPeopleEndpointWithBaseURL(String str) {
        setPeopleEndpoint(getEndPointWithIpTrackingParam(str + MPConstants.URL.PEOPLE, getUseIpAddressForGeolocation()));
    }

    private void setPeopleEndpoint(String str) {
        this.mPeopleEndpoint = str;
    }

    public String getGroupsEndpoint() {
        return this.mGroupsEndpoint;
    }

    private void setGroupsEndpointWithBaseURL(String str) {
        setGroupsEndpoint(getEndPointWithIpTrackingParam(str + MPConstants.URL.GROUPS, getUseIpAddressForGeolocation()));
    }

    private void setGroupsEndpoint(String str) {
        this.mGroupsEndpoint = str;
    }

    public int getMinimumSessionDuration() {
        return this.mMinSessionDuration;
    }

    public int getSessionTimeoutDuration() {
        return this.mSessionTimeoutDuration;
    }

    public boolean getDisableExceptionHandler() {
        return this.mDisableExceptionHandler;
    }

    private boolean getUseIpAddressForGeolocation() {
        return this.mUseIpAddressForGeolocation;
    }

    public void setUseIpAddressForGeolocation(boolean z) {
        this.mUseIpAddressForGeolocation = z;
        setEventsEndpoint(getEndPointWithIpTrackingParam(getEventsEndpoint(), z));
        setPeopleEndpoint(getEndPointWithIpTrackingParam(getPeopleEndpoint(), z));
        setGroupsEndpoint(getEndPointWithIpTrackingParam(getGroupsEndpoint(), z));
    }

    public void setEnableLogging(boolean z) {
        DEBUG = z;
        MPLog.setLevel(z ? 2 : Integer.MAX_VALUE);
    }

    public void setTrackAutomaticEvents(boolean z) {
        this.mTrackAutomaticEvents = z;
    }

    public String getResourcePackageName() {
        return this.mResourcePackageName;
    }

    public synchronized SSLSocketFactory getSSLSocketFactory() {
        return this.mSSLSocketFactory;
    }

    public synchronized OfflineMode getOfflineMode() {
        return this.mOfflineMode;
    }

    static MPConfig readConfig(Context context) {
        String packageName = context.getPackageName();
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(packageName, 128).metaData;
            if (bundle == null) {
                bundle = new Bundle();
            }
            return new MPConfig(bundle, context);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Can't configure Mixpanel with package name " + packageName, e);
        }
    }

    public String toString() {
        return "Mixpanel (7.0.1) configured with:\n    TrackAutomaticEvents: " + getTrackAutomaticEvents() + "\n    BulkUploadLimit " + getBulkUploadLimit() + "\n    FlushInterval " + getFlushInterval() + "\n    FlushInterval " + getFlushBatchSize() + "\n    DataExpiration " + getDataExpiration() + "\n    MinimumDatabaseLimit " + getMinimumDatabaseLimit() + "\n    MaximumDatabaseLimit " + getMaximumDatabaseLimit() + "\n    DisableAppOpenEvent " + getDisableAppOpenEvent() + "\n    EnableDebugLogging " + DEBUG + "\n    EventsEndpoint " + getEventsEndpoint() + "\n    PeopleEndpoint " + getPeopleEndpoint() + "\n    MinimumSessionDuration: " + getMinimumSessionDuration() + "\n    SessionTimeoutDuration: " + getSessionTimeoutDuration() + "\n    DisableExceptionHandler: " + getDisableExceptionHandler() + "\n    FlushOnBackground: " + getFlushOnBackground();
    }
}
