package com.datadog.android.core.internal.utils;

import android.content.ComponentName;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.fragment.DialogFragmentNavigator;
import androidx.navigation.fragment.FragmentNavigator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0003H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0004H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"UNKNOWN_DESTINATION_URL", "", "resolveViewUrl", "Landroid/content/ComponentName;", "", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ViewUtils.kt */
public final class ViewUtilsKt {
    public static final String UNKNOWN_DESTINATION_URL = "Unknown";

    public static final String resolveViewUrl(Object obj) {
        String resolveViewUrl;
        Intrinsics.checkNotNullParameter(obj, "<this>");
        if (obj instanceof FragmentNavigator.Destination) {
            String className = ((FragmentNavigator.Destination) obj).getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "className");
            return className;
        } else if (obj instanceof DialogFragmentNavigator.Destination) {
            String className2 = ((DialogFragmentNavigator.Destination) obj).getClassName();
            Intrinsics.checkNotNullExpressionValue(className2, "className");
            return className2;
        } else if (obj instanceof ActivityNavigator.Destination) {
            ComponentName component = ((ActivityNavigator.Destination) obj).getComponent();
            if (component == null || (resolveViewUrl = resolveViewUrl(component)) == null) {
                return UNKNOWN_DESTINATION_URL;
            }
            return resolveViewUrl;
        } else if (obj instanceof String) {
            return (String) obj;
        } else {
            String canonicalName = obj.getClass().getCanonicalName();
            String simpleName = canonicalName == null ? obj.getClass().getSimpleName() : canonicalName;
            Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.canonicalName ?: javaClass.simpleName");
            return simpleName;
        }
    }

    public static final String resolveViewUrl(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "<this>");
        String packageName = componentName.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        if (packageName.length() == 0) {
            String className = componentName.getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "className");
            return className;
        }
        String className2 = componentName.getClassName();
        Intrinsics.checkNotNullExpressionValue(className2, "className");
        if (StringsKt.startsWith$default(className2, componentName.getPackageName() + ".", false, 2, (Object) null)) {
            String className3 = componentName.getClassName();
            Intrinsics.checkNotNullExpressionValue(className3, "className");
            return className3;
        }
        String className4 = componentName.getClassName();
        Intrinsics.checkNotNullExpressionValue(className4, "className");
        if (StringsKt.contains$default((CharSequence) className4, '.', false, 2, (Object) null)) {
            String className5 = componentName.getClassName();
            Intrinsics.checkNotNullExpressionValue(className5, "className");
            return className5;
        }
        return componentName.getPackageName() + "." + componentName.getClassName();
    }
}
