package com.gabb.packageupdater.network.interceptors;

import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BaseUrlInterceptor.kt */
final class BaseUrlInterceptor$sharedPrefs$2 extends Lambda implements Function0<SharedPreferences> {
    final /* synthetic */ BaseUrlInterceptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseUrlInterceptor$sharedPrefs$2(BaseUrlInterceptor baseUrlInterceptor) {
        super(0);
        this.this$0 = baseUrlInterceptor;
    }

    public final SharedPreferences invoke() {
        return PreferenceManager.getDefaultSharedPreferences(this.this$0.context);
    }
}
