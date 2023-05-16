package com.mixpanel.android.util;

import android.util.Log;

public class MPLog {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int NONE = Integer.MAX_VALUE;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static int sMinLevel = 5;

    public static void setLevel(int i) {
        sMinLevel = i;
    }

    public static int getLevel() {
        return sMinLevel;
    }

    /* renamed from: v */
    public static void m65v(String str, String str2) {
        if (shouldLog(2)) {
            Log.v(str, str2);
        }
    }

    /* renamed from: v */
    public static void m66v(String str, String str2, Throwable th) {
        if (shouldLog(2)) {
            Log.v(str, str2, th);
        }
    }

    /* renamed from: d */
    public static void m59d(String str, String str2) {
        if (shouldLog(3)) {
            Log.d(str, str2);
        }
    }

    /* renamed from: d */
    public static void m60d(String str, String str2, Throwable th) {
        if (shouldLog(3)) {
            Log.d(str, str2, th);
        }
    }

    /* renamed from: i */
    public static void m63i(String str, String str2) {
        if (shouldLog(4)) {
            Log.i(str, str2);
        }
    }

    /* renamed from: i */
    public static void m64i(String str, String str2, Throwable th) {
        if (shouldLog(4)) {
            Log.i(str, str2, th);
        }
    }

    /* renamed from: w */
    public static void m67w(String str, String str2) {
        if (shouldLog(5)) {
            Log.w(str, str2);
        }
    }

    /* renamed from: w */
    public static void m68w(String str, String str2, Throwable th) {
        if (shouldLog(5)) {
            Log.w(str, str2, th);
        }
    }

    /* renamed from: e */
    public static void m61e(String str, String str2) {
        if (shouldLog(6)) {
            Log.e(str, str2);
        }
    }

    /* renamed from: e */
    public static void m62e(String str, String str2, Throwable th) {
        if (shouldLog(6)) {
            Log.e(str, str2, th);
        }
    }

    private static boolean shouldLog(int i) {
        return sMinLevel <= i;
    }
}
