package com.mixpanel.android.mpmetrics;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

class SystemInformation {
    private static final String LOGTAG = "MixpanelAPI.SysInfo";
    private static SystemInformation sInstance;
    private static final Object sInstanceLock = new Object();
    private final String mAppName;
    private final Integer mAppVersionCode;
    private final String mAppVersionName;
    private final Context mContext;
    private final DisplayMetrics mDisplayMetrics;
    private final Boolean mHasNFC;
    private final Boolean mHasTelephony;

    static SystemInformation getInstance(Context context) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new SystemInformation(context.getApplicationContext());
            }
        }
        return sInstance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005c A[SYNTHETIC, Splitter:B:22:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SystemInformation(android.content.Context r10) {
        /*
            r9 = this;
            java.lang.String r0 = "System version appeared to support PackageManager.hasSystemFeature, but we were unable to call it."
            java.lang.String r1 = "MixpanelAPI.SysInfo"
            r9.<init>()
            r9.mContext = r10
            android.content.pm.PackageManager r2 = r10.getPackageManager()
            r3 = 0
            r4 = 0
            java.lang.String r5 = r10.getPackageName()     // Catch:{ NameNotFoundException -> 0x0020 }
            android.content.pm.PackageInfo r5 = r2.getPackageInfo(r5, r3)     // Catch:{ NameNotFoundException -> 0x0020 }
            java.lang.String r6 = r5.versionName     // Catch:{ NameNotFoundException -> 0x0020 }
            int r5 = r5.versionCode     // Catch:{ NameNotFoundException -> 0x0021 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ NameNotFoundException -> 0x0021 }
            goto L_0x0027
        L_0x0020:
            r6 = r4
        L_0x0021:
            java.lang.String r5 = "System information constructed with a context that apparently doesn't exist."
            com.mixpanel.android.util.MPLog.m67w(r1, r5)
            r5 = r4
        L_0x0027:
            android.content.pm.ApplicationInfo r7 = r10.getApplicationInfo()
            int r8 = r7.labelRes
            r9.mAppVersionName = r6
            r9.mAppVersionCode = r5
            if (r8 != 0) goto L_0x0041
            java.lang.CharSequence r10 = r7.nonLocalizedLabel
            if (r10 != 0) goto L_0x003a
            java.lang.String r10 = "Misc"
            goto L_0x0045
        L_0x003a:
            java.lang.CharSequence r10 = r7.nonLocalizedLabel
            java.lang.String r10 = r10.toString()
            goto L_0x0045
        L_0x0041:
            java.lang.String r10 = r10.getString(r8)
        L_0x0045:
            r9.mAppName = r10
            java.lang.Class r10 = r2.getClass()
            r5 = 1
            java.lang.String r6 = "hasSystemFeature"
            java.lang.Class[] r7 = new java.lang.Class[r5]     // Catch:{ NoSuchMethodException -> 0x0059 }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r3] = r8     // Catch:{ NoSuchMethodException -> 0x0059 }
            java.lang.reflect.Method r10 = r10.getMethod(r6, r7)     // Catch:{ NoSuchMethodException -> 0x0059 }
            goto L_0x005a
        L_0x0059:
            r10 = r4
        L_0x005a:
            if (r10 == 0) goto L_0x0081
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ InvocationTargetException -> 0x007a, IllegalAccessException -> 0x0075 }
            java.lang.String r7 = "android.hardware.nfc"
            r6[r3] = r7     // Catch:{ InvocationTargetException -> 0x007a, IllegalAccessException -> 0x0075 }
            java.lang.Object r6 = r10.invoke(r2, r6)     // Catch:{ InvocationTargetException -> 0x007a, IllegalAccessException -> 0x0075 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ InvocationTargetException -> 0x007a, IllegalAccessException -> 0x0075 }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0076 }
            java.lang.String r7 = "android.hardware.telephony"
            r5[r3] = r7     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0076 }
            java.lang.Object r10 = r10.invoke(r2, r5)     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0076 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0076 }
            goto L_0x007f
        L_0x0075:
            r6 = r4
        L_0x0076:
            com.mixpanel.android.util.MPLog.m67w(r1, r0)
            goto L_0x007e
        L_0x007a:
            r6 = r4
        L_0x007b:
            com.mixpanel.android.util.MPLog.m67w(r1, r0)
        L_0x007e:
            r10 = r4
        L_0x007f:
            r4 = r6
            goto L_0x0082
        L_0x0081:
            r10 = r4
        L_0x0082:
            r9.mHasNFC = r4
            r9.mHasTelephony = r10
            android.util.DisplayMetrics r10 = new android.util.DisplayMetrics
            r10.<init>()
            r9.mDisplayMetrics = r10
            android.content.Context r9 = r9.mContext
            java.lang.String r0 = "window"
            java.lang.Object r9 = r9.getSystemService(r0)
            android.view.WindowManager r9 = (android.view.WindowManager) r9
            android.view.Display r9 = r9.getDefaultDisplay()
            r9.getMetrics(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.SystemInformation.<init>(android.content.Context):void");
    }

    public String getAppVersionName() {
        return this.mAppVersionName;
    }

    public Integer getAppVersionCode() {
        return this.mAppVersionCode;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public boolean hasNFC() {
        return this.mHasNFC.booleanValue();
    }

    public boolean hasTelephony() {
        return this.mHasTelephony.booleanValue();
    }

    public DisplayMetrics getDisplayMetrics() {
        return this.mDisplayMetrics;
    }

    public String getCurrentNetworkOperator() {
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }

    public Boolean isWifiConnected() {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return null;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = true;
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1 || !activeNetworkInfo.isConnected()) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public Boolean isBluetoothEnabled() {
        BluetoothAdapter defaultAdapter;
        try {
            if (this.mContext.getPackageManager().checkPermission("android.permission.BLUETOOTH", this.mContext.getPackageName()) != 0 || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
                return null;
            }
            return Boolean.valueOf(defaultAdapter.isEnabled());
        } catch (NoClassDefFoundError | SecurityException unused) {
            return null;
        }
    }

    public String getBluetoothVersion() {
        if (Build.VERSION.SDK_INT < 18 || !this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            return this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth") ? "classic" : "none";
        }
        return "ble";
    }
}
