package com.mixpanel.android.mpmetrics;

import android.R;
import android.content.Context;
import android.util.SparseArray;
import com.datadog.android.core.internal.CoreFeature;
import com.mixpanel.android.util.MPLog;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public abstract class ResourceReader implements ResourceIds {
    private static final String LOGTAG = "MixpanelAPI.RsrcReader";
    private final Context mContext;
    private final Map<String, Integer> mIdNameToId = new HashMap();
    private final SparseArray<String> mIdToIdName = new SparseArray<>();

    /* access modifiers changed from: protected */
    public abstract String getLocalClassName(Context context);

    /* access modifiers changed from: protected */
    public abstract Class<?> getSystemClass();

    public static class Ids extends ResourceReader {
        private final String mResourcePackageName;

        public Ids(String str, Context context) {
            super(context);
            this.mResourcePackageName = str;
            initialize();
        }

        /* access modifiers changed from: protected */
        public Class<?> getSystemClass() {
            return R.id.class;
        }

        /* access modifiers changed from: protected */
        public String getLocalClassName(Context context) {
            return this.mResourcePackageName + ".R$id";
        }
    }

    public static class Drawables extends ResourceReader {
        private final String mResourcePackageName;

        protected Drawables(String str, Context context) {
            super(context);
            this.mResourcePackageName = str;
            initialize();
        }

        /* access modifiers changed from: protected */
        public Class<?> getSystemClass() {
            return R.drawable.class;
        }

        /* access modifiers changed from: protected */
        public String getLocalClassName(Context context) {
            return this.mResourcePackageName + ".R$drawable";
        }
    }

    protected ResourceReader(Context context) {
        this.mContext = context;
    }

    public boolean knownIdName(String str) {
        return this.mIdNameToId.containsKey(str);
    }

    public int idFromName(String str) {
        return this.mIdNameToId.get(str).intValue();
    }

    public String nameForId(int i) {
        return this.mIdToIdName.get(i);
    }

    private static void readClassIds(Class<?> cls, String str, Map<String, Integer> map) {
        try {
            Field[] fields = cls.getFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers()) && field.getType() == Integer.TYPE) {
                    try {
                        String name = field.getName();
                        int i = field.getInt((Object) null);
                        if (str != null) {
                            name = str + ":" + name;
                        }
                        map.put(name, Integer.valueOf(i));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        MPLog.m62e(LOGTAG, "Can't read built-in id name from " + cls.getName(), e);
                    }
                }
            }
        } catch (IllegalAccessException e2) {
            MPLog.m62e(LOGTAG, "Can't read built-in id names from " + cls.getName(), e2);
        }
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mIdNameToId.clear();
        this.mIdToIdName.clear();
        readClassIds(getSystemClass(), CoreFeature.DEFAULT_SOURCE_NAME, this.mIdNameToId);
        String localClassName = getLocalClassName(this.mContext);
        try {
            readClassIds(Class.forName(localClassName), (String) null, this.mIdNameToId);
        } catch (ClassNotFoundException unused) {
            MPLog.m67w(LOGTAG, "Can't load names for Android view ids from '" + localClassName + "', ids by name will not be available in the events editor.");
            MPLog.m63i(LOGTAG, "You may be missing a Resources class for your package due to your proguard configuration, or you may be using an applicationId in your build that isn't the same as the package declared in your AndroidManifest.xml file.\nIf you're using proguard, you can fix this issue by adding the following to your proguard configuration:\n\n-keep class **.R$* {\n    <fields>;\n}\n\nIf you're not using proguard, or if your proguard configuration already contains the directive above, you can add the following to your AndroidManifest.xml file to explicitly point the Mixpanel library to the appropriate library for your resources class:\n\n<meta-data android:name=\"com.mixpanel.android.MPConfig.ResourcePackageName\" android:value=\"YOUR_PACKAGE_NAME\" />\n\nwhere YOUR_PACKAGE_NAME is the same string you use for the \"package\" attribute in your <manifest> tag.");
        }
        for (Map.Entry next : this.mIdNameToId.entrySet()) {
            this.mIdToIdName.put(((Integer) next.getValue()).intValue(), next.getKey());
        }
    }
}
