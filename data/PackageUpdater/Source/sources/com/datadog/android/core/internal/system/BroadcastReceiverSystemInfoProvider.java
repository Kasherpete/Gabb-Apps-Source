package com.datadog.android.core.internal.system;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import com.datadog.android.core.internal.receiver.ThreadSafeReceiver;
import com.datadog.android.core.internal.system.SystemInfo;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00162\u00020\u00012\u00020\u0002:\u0001\u0016B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0003J\u001a\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0017J\u0018\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo20735d2 = {"Lcom/datadog/android/core/internal/system/BroadcastReceiverSystemInfoProvider;", "Lcom/datadog/android/core/internal/receiver/ThreadSafeReceiver;", "Lcom/datadog/android/core/internal/system/SystemInfoProvider;", "buildSdkVersionProvider", "Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;", "(Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;)V", "systemInfo", "Lcom/datadog/android/core/internal/system/SystemInfo;", "getLatestSystemInfo", "handleBatteryIntent", "", "intent", "Landroid/content/Intent;", "handlePowerSaveIntent", "context", "Landroid/content/Context;", "onReceive", "register", "registerIntentFilter", "action", "", "unregister", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BroadcastReceiverSystemInfoProvider.kt */
public final class BroadcastReceiverSystemInfoProvider extends ThreadSafeReceiver implements SystemInfoProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Set<Integer> PLUGGED_IN_STATUS_VALUES = SetsKt.setOf(1, 4, 2);
    private static final Set<SystemInfo.BatteryStatus> batteryFullOrChargingStatus = SetsKt.setOf(SystemInfo.BatteryStatus.CHARGING, SystemInfo.BatteryStatus.FULL);
    private final BuildSdkVersionProvider buildSdkVersionProvider;
    private SystemInfo systemInfo;

    public BroadcastReceiverSystemInfoProvider() {
        this((BuildSdkVersionProvider) null, 1, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BroadcastReceiverSystemInfoProvider(BuildSdkVersionProvider buildSdkVersionProvider2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DefaultBuildSdkVersionProvider() : buildSdkVersionProvider2);
    }

    public BroadcastReceiverSystemInfoProvider(BuildSdkVersionProvider buildSdkVersionProvider2) {
        Intrinsics.checkNotNullParameter(buildSdkVersionProvider2, "buildSdkVersionProvider");
        this.buildSdkVersionProvider = buildSdkVersionProvider2;
        this.systemInfo = new SystemInfo(false, 0, false, false, 15, (DefaultConstructorMarker) null);
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        String action = intent == null ? null : intent.getAction();
        if (Intrinsics.areEqual((Object) action, (Object) "android.intent.action.BATTERY_CHANGED")) {
            handleBatteryIntent(intent);
        } else if (Intrinsics.areEqual((Object) action, (Object) "android.os.action.POWER_SAVE_MODE_CHANGED")) {
            handlePowerSaveIntent(context);
        } else {
            LogUtilsKt.debugWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Received unknown broadcast intent: [" + action + "]", (Throwable) null, (Map) null, 6, (Object) null);
        }
    }

    public void register(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        registerIntentFilter(context, "android.intent.action.BATTERY_CHANGED");
        if (this.buildSdkVersionProvider.version() >= 21) {
            registerIntentFilter(context, "android.os.action.POWER_SAVE_MODE_CHANGED");
        }
    }

    public void unregister(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        unregisterReceiver(context);
    }

    public SystemInfo getLatestSystemInfo() {
        return this.systemInfo;
    }

    private final void registerIntentFilter(Context context, String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(str);
        Intent registerReceiver = registerReceiver(context, intentFilter);
        if (registerReceiver != null) {
            onReceive(context, registerReceiver);
        }
    }

    private final void handleBatteryIntent(Intent intent) {
        int intExtra = intent.getIntExtra("status", 1);
        int intExtra2 = intent.getIntExtra("level", -1);
        int intExtra3 = intent.getIntExtra("scale", 100);
        int intExtra4 = intent.getIntExtra("plugged", -1);
        SystemInfo.BatteryStatus fromAndroidStatus = SystemInfo.BatteryStatus.Companion.fromAndroidStatus(intExtra);
        boolean contains = PLUGGED_IN_STATUS_VALUES.contains(Integer.valueOf(intExtra4));
        boolean contains2 = batteryFullOrChargingStatus.contains(fromAndroidStatus);
        this.systemInfo = SystemInfo.copy$default(this.systemInfo, contains2, (intExtra2 * 100) / intExtra3, false, contains, 4, (Object) null);
    }

    private final void handlePowerSaveIntent(Context context) {
        boolean z;
        if (this.buildSdkVersionProvider.version() >= 21) {
            Object systemService = context.getSystemService("power");
            PowerManager powerManager = systemService instanceof PowerManager ? (PowerManager) systemService : null;
            if (powerManager == null) {
                z = false;
            } else {
                z = powerManager.isPowerSaveMode();
            }
            this.systemInfo = SystemInfo.copy$default(this.systemInfo, false, 0, z, false, 11, (Object) null);
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, mo20735d2 = {"Lcom/datadog/android/core/internal/system/BroadcastReceiverSystemInfoProvider$Companion;", "", "()V", "PLUGGED_IN_STATUS_VALUES", "", "", "batteryFullOrChargingStatus", "Lcom/datadog/android/core/internal/system/SystemInfo$BatteryStatus;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: BroadcastReceiverSystemInfoProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
