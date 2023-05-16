package com.gabb.packageupdater.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.packageupdater.C0893R;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\nJ\b\u0010\u0019\u001a\u00020\u0015H\u0002J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH\u0002J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH\u0002J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\nH\u0002J\u001f\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\n2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u001fJ\u000e\u0010 \u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\nJ\u0006\u0010!\u001a\u00020\u0015J\b\u0010\"\u001a\u00020#H\u0002R\u000e\u0010\t\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00100\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, mo20735d2 = {"Lcom/gabb/packageupdater/notifications/Notifier;", "", "context", "Landroid/content/Context;", "notificationManager", "Landroid/app/NotificationManager;", "packageManager", "Landroid/content/pm/PackageManager;", "(Landroid/content/Context;Landroid/app/NotificationManager;Landroid/content/pm/PackageManager;)V", "CHANNEL_ID", "", "CHANNEL_NAME", "UPDATES_REQUIRED_NOTIFICATION_CHANNEL_ID", "UPDATES_REQUIRED_NOTIFICATION_CHANNEL_ID_OLD", "UPDATES_REQUIRED_NOTIFICATION_CHANNEL_NAME", "UPDATES_REQUIRED_NOTIFICATION_ID", "", "UPDATES_REQUIRED_NOTIFICATION_ID_OLD", "notificationIds", "", "cancelAllNotifications", "", "cancelCheckUpdatesNotification", "cancelNotification", "packageName", "deleteOldCheckUpdatesNotification", "getApplicationNameForPackage", "getNameLocally", "getNotificationIdForPackage", "notifyUserOfDownloadingUpdates", "percent", "(Ljava/lang/String;Ljava/lang/Integer;)V", "notifyUserOfInstallingUpdates", "nudgeUserToDownloadUpdates", "openGabbId", "Landroid/app/PendingIntent;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Notifier.kt */
public final class Notifier {
    private final String CHANNEL_ID = "gabb_updater";
    private final String CHANNEL_NAME = "Gabb Updater";
    private final String UPDATES_REQUIRED_NOTIFICATION_CHANNEL_ID = "updates_required_silent";
    private final String UPDATES_REQUIRED_NOTIFICATION_CHANNEL_ID_OLD = "updates_required";
    private final String UPDATES_REQUIRED_NOTIFICATION_CHANNEL_NAME = "App Updates Ready";
    private final int UPDATES_REQUIRED_NOTIFICATION_ID = 1443;
    private final int UPDATES_REQUIRED_NOTIFICATION_ID_OLD = 1442;
    private final Context context;
    private Map<String, Integer> notificationIds = new LinkedHashMap();
    private final NotificationManager notificationManager;
    private final PackageManager packageManager;

    @Inject
    public Notifier(@ApplicationContext Context context2, NotificationManager notificationManager2, PackageManager packageManager2) {
        Intrinsics.checkNotNullParameter(context2, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(notificationManager2, "notificationManager");
        Intrinsics.checkNotNullParameter(packageManager2, "packageManager");
        this.context = context2;
        this.notificationManager = notificationManager2;
        this.packageManager = packageManager2;
        notificationManager2.createNotificationChannel(new NotificationChannel("gabb_updater", "Gabb Updater", 2));
    }

    public final void cancelAllNotifications() {
        this.notificationManager.cancelAll();
    }

    public final void cancelNotification(String str) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        this.notificationManager.cancel(getNotificationIdForPackage(str));
    }

    public final void cancelCheckUpdatesNotification() {
        this.notificationManager.cancel(this.UPDATES_REQUIRED_NOTIFICATION_ID);
    }

    private final void deleteOldCheckUpdatesNotification() {
        this.notificationManager.deleteNotificationChannel(this.UPDATES_REQUIRED_NOTIFICATION_CHANNEL_ID_OLD);
    }

    public static /* synthetic */ void notifyUserOfDownloadingUpdates$default(Notifier notifier, String str, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = 0;
        }
        notifier.notifyUserOfDownloadingUpdates(str, num);
    }

    public final void notifyUserOfDownloadingUpdates(String str, Integer num) {
        int i;
        Intrinsics.checkNotNullParameter(str, "packageName");
        int notificationIdForPackage = getNotificationIdForPackage(str);
        String applicationNameForPackage = getApplicationNameForPackage(str);
        NotificationManager notificationManager2 = this.notificationManager;
        NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(this.context, this.CHANNEL_ID).setContentTitle(applicationNameForPackage).setSubText(this.context.getString(C0893R.string.downloading_updates)).setSmallIcon((int) C0893R.C0895drawable.ic_installing_updates);
        if (num == null) {
            i = 0;
        } else {
            i = num.intValue();
        }
        notificationManager2.notify(notificationIdForPackage, smallIcon.setProgress(100, i, false).setOnlyAlertOnce(true).setOngoing(true).build());
    }

    public final void notifyUserOfInstallingUpdates(String str) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        this.notificationManager.notify(getNotificationIdForPackage(str), new NotificationCompat.Builder(this.context, this.CHANNEL_ID).setContentTitle(getApplicationNameForPackage(str)).setSubText(this.context.getString(C0893R.string.installing_updates)).setSmallIcon((int) C0893R.C0895drawable.ic_installing_updates).setProgress(0, 0, true).setOnlyAlertOnce(true).setOngoing(true).build());
    }

    private final int getNotificationIdForPackage(String str) {
        Integer num = this.notificationIds.get(str);
        if (num == null) {
            num = Integer.valueOf(Random.Default.nextInt());
            this.notificationIds.put(str, num);
        }
        return num.intValue();
    }

    private final String getApplicationNameForPackage(String str) {
        CharSequence charSequence;
        try {
            ApplicationInfo applicationInfo = this.packageManager.getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                charSequence = this.packageManager.getApplicationLabel(applicationInfo);
            } else {
                charSequence = getNameLocally(str);
            }
            return (String) charSequence;
        } catch (Exception unused) {
            return getNameLocally(str);
        }
    }

    private final String getNameLocally(String str) {
        String str2;
        CharSequence charSequence = str;
        if (StringsKt.contains(charSequence, (CharSequence) "updater", true)) {
            str2 = this.context.getString(C0893R.string.updater);
        } else if (StringsKt.contains(charSequence, (CharSequence) "wizard", true)) {
            str2 = this.context.getString(C0893R.string.wizard);
        } else if (StringsKt.contains(charSequence, (CharSequence) "messenger", true)) {
            str2 = this.context.getString(C0893R.string.messenger);
        } else if (StringsKt.contains(charSequence, (CharSequence) "music", true)) {
            str2 = this.context.getString(C0893R.string.music);
        } else if (StringsKt.contains(charSequence, (CharSequence) "services", true)) {
            str2 = this.context.getString(C0893R.string.services);
        } else if (StringsKt.contains(charSequence, (CharSequence) "gabbid", true)) {
            str2 = this.context.getString(C0893R.string.gabbid);
        } else if (StringsKt.contains(charSequence, (CharSequence) "cloud", true)) {
            str2 = this.context.getString(C0893R.string.cloud);
        } else {
            str2 = this.context.getString(C0893R.string.gabb);
        }
        Intrinsics.checkNotNullExpressionValue(str2, "with(packageName) {\n    …)\n            }\n        }");
        return str2;
    }

    public final void nudgeUserToDownloadUpdates() {
        NotificationChannel notificationChannel = new NotificationChannel(this.UPDATES_REQUIRED_NOTIFICATION_CHANNEL_ID, this.UPDATES_REQUIRED_NOTIFICATION_CHANNEL_NAME, 4);
        notificationChannel.setSound((Uri) null, (AudioAttributes) null);
        this.notificationManager.createNotificationChannel(notificationChannel);
        deleteOldCheckUpdatesNotification();
        this.notificationManager.notify(this.UPDATES_REQUIRED_NOTIFICATION_ID, new NotificationCompat.Builder(this.context, this.UPDATES_REQUIRED_NOTIFICATION_CHANNEL_ID).setContentTitle(this.context.getString(C0893R.string.updates_ready)).setSmallIcon((int) C0893R.C0895drawable.ic_installing_updates).setOngoing(true).setSound((Uri) null).setContentIntent(openGabbId()).build());
    }

    private final PendingIntent openGabbId() {
        PendingIntent activity = PendingIntent.getActivity(this.context, 0, this.packageManager.getLaunchIntentForPackage("com.gabb.gabbid"), 67108864);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(context, 0, …ingIntent.FLAG_IMMUTABLE)");
        return activity;
    }
}
