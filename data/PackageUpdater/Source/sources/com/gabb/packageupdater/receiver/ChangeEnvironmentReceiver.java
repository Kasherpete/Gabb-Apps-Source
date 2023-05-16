package com.gabb.packageupdater.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, mo20735d2 = {"Lcom/gabb/packageupdater/receiver/ChangeEnvironmentReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ChangeEnvironmentReceiver.kt */
public final class ChangeEnvironmentReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(intent, "intent");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (Intrinsics.areEqual((Object) intent.getAction(), (Object) "com.gabb.packageupdater.intent.action.CHANGE_ENV_DEV")) {
            Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "sharedPrefs");
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            Intrinsics.checkNotNullExpressionValue(edit, "editor");
            edit.putString(SharedPrefsKey.ENVIRONMENT, Environment.DEV);
            edit.commit();
        }
    }
}
