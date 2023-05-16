package com.gabb.packageupdater.p005di;

import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import androidx.work.WorkManager;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.packageupdater.BuildConfig;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.Objects;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0011"}, mo20735d2 = {"Lcom/gabb/packageupdater/di/AppModule;", "", "()V", "providesConnectivityManager", "Landroid/net/ConnectivityManager;", "context", "Landroid/content/Context;", "providesContentResolver", "Landroid/content/ContentResolver;", "providesMixpanelApi", "Lcom/mixpanel/android/mpmetrics/MixpanelAPI;", "providesNotificationManager", "Landroid/app/NotificationManager;", "providesPackageManager", "Landroid/content/pm/PackageManager;", "providesWorkManager", "Landroidx/work/WorkManager;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Module
/* renamed from: com.gabb.packageupdater.di.AppModule */
/* compiled from: AppModule.kt */
public final class AppModule {
    public static final AppModule INSTANCE = new AppModule();

    private AppModule() {
    }

    @Provides
    public final NotificationManager providesNotificationManager(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Object systemService = context.getApplicationContext().getSystemService("notification");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        return (NotificationManager) systemService;
    }

    @Singleton
    @Provides
    public final PackageManager providesPackageManager(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        return packageManager;
    }

    @Provides
    public final WorkManager providesWorkManager(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        WorkManager instance = WorkManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(context)");
        return instance;
    }

    @Singleton
    @Provides
    public final ContentResolver providesContentResolver(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        ContentResolver contentResolver = context.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "context.contentResolver");
        return contentResolver;
    }

    @Singleton
    @Provides
    public final ConnectivityManager providesConnectivityManager(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Object systemService = context.getSystemService(ConnectivityManager.class);
        Intrinsics.checkNotNullExpressionValue(systemService, "context.getSystemService…ivityManager::class.java)");
        return (ConnectivityManager) systemService;
    }

    @Singleton
    @Provides
    public final MixpanelAPI providesMixpanelApi(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        MixpanelAPI instance = MixpanelAPI.getInstance(context, BuildConfig.MIXPANEL_PROJECT_TOKEN, true);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(context, Bui…ANEL_PROJECT_TOKEN, true)");
        return instance;
    }
}
