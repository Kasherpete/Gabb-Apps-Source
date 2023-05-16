package com.datadog.android.rum.tracking;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u001c2\u00020\u00012\u00020\u0002:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0004J \u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\r2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/ActivityLifecycleTrackingStrategy;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "Lcom/datadog/android/rum/tracking/TrackingStrategy;", "()V", "convertToRumAttributes", "", "", "", "intent", "Landroid/content/Intent;", "bundle", "Landroid/os/Bundle;", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "register", "context", "Landroid/content/Context;", "unregister", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ActivityLifecycleTrackingStrategy.kt */
public abstract class ActivityLifecycleTrackingStrategy implements Application.ActivityLifecycleCallbacks, TrackingStrategy {
    public static final String ARGUMENT_TAG = "view.arguments";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String INTENT_ACTION_TAG = "view.intent.action";
    public static final String INTENT_URI_TAG = "view.intent.uri";

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(bundle, "outState");
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void register(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(this);
        } else {
            Logger.e$default(RuntimeUtilsKt.getDevLogger(), "In order to use the RUM automatic tracking feature you will haveto use the Application context when initializing the SDK", (Throwable) null, (Map) null, 6, (Object) null);
        }
    }

    public void unregister(Context context) {
        if (context instanceof Application) {
            ((Application) context).unregisterActivityLifecycleCallbacks(this);
        }
    }

    /* access modifiers changed from: protected */
    public final Map<String, Object> convertToRumAttributes(Intent intent) {
        if (intent == null) {
            return MapsKt.emptyMap();
        }
        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        String action = intent.getAction();
        if (action != null) {
            linkedHashMap.put(INTENT_ACTION_TAG, action);
        }
        String dataString = intent.getDataString();
        if (dataString != null) {
            linkedHashMap.put(INTENT_URI_TAG, dataString);
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Set<String> keySet = extras.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "bundle.keySet()");
            for (String str : keySet) {
                linkedHashMap.put("view.arguments." + str, extras.get(str));
            }
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: protected */
    public final Map<String, Object> convertToRumAttributes(Bundle bundle) {
        if (bundle == null) {
            return MapsKt.emptyMap();
        }
        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        Set<String> keySet = bundle.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "bundle.keySet()");
        for (String str : keySet) {
            linkedHashMap.put("view.arguments." + str, bundle.get(str));
        }
        return linkedHashMap;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/ActivityLifecycleTrackingStrategy$Companion;", "", "()V", "ARGUMENT_TAG", "", "INTENT_ACTION_TAG", "INTENT_URI_TAG", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActivityLifecycleTrackingStrategy.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
