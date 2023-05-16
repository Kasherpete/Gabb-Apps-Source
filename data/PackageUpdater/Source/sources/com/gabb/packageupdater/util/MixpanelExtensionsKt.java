package com.gabb.packageupdater.util;

import com.mixpanel.android.mpmetrics.MixpanelAPI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u001a$\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004¨\u0006\t"}, mo20735d2 = {"trackFailure", "", "Lcom/mixpanel/android/mpmetrics/MixpanelAPI;", "eventName", "", "appName", "errorMessage", "trackSuccess", "message", "app_productionRelease"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: MixpanelExtensions.kt */
public final class MixpanelExtensionsKt {
    public static /* synthetic */ void trackSuccess$default(MixpanelAPI mixpanelAPI, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        trackSuccess(mixpanelAPI, str, str2, str3);
    }

    public static final void trackSuccess(MixpanelAPI mixpanelAPI, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(mixpanelAPI, "<this>");
        Intrinsics.checkNotNullParameter(str, "eventName");
        Intrinsics.checkNotNullParameter(str2, "appName");
        Intrinsics.checkNotNullParameter(str3, "message");
        boolean z = true;
        JSONObject put = new JSONObject().put("Succeeded", true);
        put.put("App name", str2);
        if (str3.length() <= 0) {
            z = false;
        }
        if (z) {
            put.put("Message", str3);
        }
        mixpanelAPI.track(str, put);
    }

    public static final void trackFailure(MixpanelAPI mixpanelAPI, String str, String str2) {
        Intrinsics.checkNotNullParameter(mixpanelAPI, "<this>");
        Intrinsics.checkNotNullParameter(str, "eventName");
        JSONObject put = new JSONObject().put("Succeeded", false);
        put.put("Error message", str2);
        mixpanelAPI.track(str, put);
    }

    public static final void trackFailure(MixpanelAPI mixpanelAPI, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(mixpanelAPI, "<this>");
        Intrinsics.checkNotNullParameter(str, "eventName");
        Intrinsics.checkNotNullParameter(str2, "appName");
        JSONObject put = new JSONObject().put("Succeeded", false);
        put.put("App name", str2);
        put.put("Error message", str3);
        mixpanelAPI.track(str, put);
    }
}
