package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.pm.PackageManager;
import com.mixpanel.android.util.MPLog;

class ConfigurationChecker {
    public static String LOGTAG = "MixpanelAPI.ConfigurationChecker";

    ConfigurationChecker() {
    }

    public static boolean checkBasicConfiguration(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        if (packageManager == null || packageName == null) {
            MPLog.m67w(LOGTAG, "Can't check configuration when using a Context with null packageManager or packageName");
            return false;
        } else if (packageManager.checkPermission("android.permission.INTERNET", packageName) == 0) {
            return true;
        } else {
            MPLog.m67w(LOGTAG, "Package does not have permission android.permission.INTERNET - Mixpanel will not work at all!");
            MPLog.m63i(LOGTAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"android.permission.INTERNET\" />");
            return false;
        }
    }
}
