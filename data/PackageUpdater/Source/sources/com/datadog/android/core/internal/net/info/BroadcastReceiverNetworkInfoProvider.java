package com.datadog.android.core.internal.net.info;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.receiver.ThreadSafeReceiver;
import com.datadog.android.core.internal.system.BuildSdkVersionProvider;
import com.datadog.android.core.internal.system.DefaultBuildSdkVersionProvider;
import com.datadog.android.core.model.NetworkInfo;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u001e2\u00020\u00012\u00020\u0002:\u0001\u001eB\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001a\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u000b\u0010\f¨\u0006\u001f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/info/BroadcastReceiverNetworkInfoProvider;", "Lcom/datadog/android/core/internal/receiver/ThreadSafeReceiver;", "Lcom/datadog/android/core/internal/net/info/NetworkInfoProvider;", "dataWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "Lcom/datadog/android/core/model/NetworkInfo;", "buildSdkVersionProvider", "Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;", "(Lcom/datadog/android/core/internal/persistence/DataWriter;Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;)V", "value", "networkInfo", "setNetworkInfo", "(Lcom/datadog/android/core/model/NetworkInfo;)V", "buildMobileNetworkInfo", "context", "Landroid/content/Context;", "subtype", "", "buildNetworkInfo", "activeNetworkInfo", "Landroid/net/NetworkInfo;", "getCellularTechnology", "", "getLatestNetworkInfo", "onReceive", "", "intent", "Landroid/content/Intent;", "register", "unregister", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BroadcastReceiverNetworkInfoProvider.kt */
public final class BroadcastReceiverNetworkInfoProvider extends ThreadSafeReceiver implements NetworkInfoProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String UNKNOWN_CARRIER_NAME = "Unknown Carrier Name";
    private static final Set<Integer> known2GSubtypes = SetsKt.setOf(1, 2, 4, 7, 11, 16);
    private static final Set<Integer> known3GSubtypes = SetsKt.setOf(3, 5, 6, 8, 9, 10, 12, 14, 15, 17);
    private static final Set<Integer> known4GSubtypes = SetsKt.setOf(13, 18, 19);
    private static final Set<Integer> known5GSubtypes = SetsKt.setOf(20);
    private static final Set<Integer> knownMobileTypes = SetsKt.setOf(0, 4, 5, 2, 3);
    private final BuildSdkVersionProvider buildSdkVersionProvider;
    private final DataWriter<NetworkInfo> dataWriter;
    private NetworkInfo networkInfo;

    private final String getCellularTechnology(int i) {
        switch (i) {
            case 1:
                return "GPRS";
            case 2:
                return "Edge";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "CDMAEVDORev0";
            case 6:
                return "CDMAEVDORevA";
            case 7:
                return "CDMA1x";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "iDen";
            case 12:
                return "CDMAEVDORevB";
            case 13:
                return "LTE";
            case 14:
                return "eHRPD";
            case 15:
                return "HSPA+";
            case 16:
                return "GSM";
            case 17:
                return "TD_SCDMA";
            case 18:
                return "IWLAN";
            case 19:
                return "LTE_CA";
            case 20:
                return "New Radio";
            default:
                return null;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BroadcastReceiverNetworkInfoProvider(DataWriter dataWriter2, BuildSdkVersionProvider buildSdkVersionProvider2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(dataWriter2, (i & 2) != 0 ? new DefaultBuildSdkVersionProvider() : buildSdkVersionProvider2);
    }

    public BroadcastReceiverNetworkInfoProvider(DataWriter<NetworkInfo> dataWriter2, BuildSdkVersionProvider buildSdkVersionProvider2) {
        Intrinsics.checkNotNullParameter(dataWriter2, "dataWriter");
        Intrinsics.checkNotNullParameter(buildSdkVersionProvider2, "buildSdkVersionProvider");
        this.dataWriter = dataWriter2;
        this.buildSdkVersionProvider = buildSdkVersionProvider2;
        this.networkInfo = new NetworkInfo((NetworkInfo.Connectivity) null, (String) null, (Long) null, (Long) null, (Long) null, (Long) null, (String) null, 127, (DefaultConstructorMarker) null);
    }

    private final void setNetworkInfo(NetworkInfo networkInfo2) {
        this.networkInfo = networkInfo2;
        this.dataWriter.write(networkInfo2);
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Object systemService = context.getSystemService("connectivity");
        android.net.NetworkInfo networkInfo2 = null;
        ConnectivityManager connectivityManager = systemService instanceof ConnectivityManager ? (ConnectivityManager) systemService : null;
        if (connectivityManager != null) {
            networkInfo2 = connectivityManager.getActiveNetworkInfo();
        }
        setNetworkInfo(buildNetworkInfo(context, networkInfo2));
    }

    public void register(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        onReceive(context, registerReceiver(context, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")));
    }

    public void unregister(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        unregisterReceiver(context);
    }

    public NetworkInfo getLatestNetworkInfo() {
        return this.networkInfo;
    }

    private final NetworkInfo buildNetworkInfo(Context context, android.net.NetworkInfo networkInfo2) {
        if (networkInfo2 == null || !networkInfo2.isConnected()) {
            return new NetworkInfo(NetworkInfo.Connectivity.NETWORK_NOT_CONNECTED, (String) null, (Long) null, (Long) null, (Long) null, (Long) null, (String) null, 126, (DefaultConstructorMarker) null);
        }
        if (networkInfo2.getType() == 1) {
            return new NetworkInfo(NetworkInfo.Connectivity.NETWORK_WIFI, (String) null, (Long) null, (Long) null, (Long) null, (Long) null, (String) null, 126, (DefaultConstructorMarker) null);
        }
        if (networkInfo2.getType() == 9) {
            return new NetworkInfo(NetworkInfo.Connectivity.NETWORK_ETHERNET, (String) null, (Long) null, (Long) null, (Long) null, (Long) null, (String) null, 126, (DefaultConstructorMarker) null);
        }
        if (!knownMobileTypes.contains(Integer.valueOf(networkInfo2.getType()))) {
            return new NetworkInfo(NetworkInfo.Connectivity.NETWORK_OTHER, (String) null, (Long) null, (Long) null, (Long) null, (Long) null, (String) null, 126, (DefaultConstructorMarker) null);
        }
        return buildMobileNetworkInfo(context, networkInfo2.getSubtype());
    }

    private final NetworkInfo buildMobileNetworkInfo(Context context, int i) {
        NetworkInfo.Connectivity connectivity;
        Long l;
        CharSequence simCarrierIdName;
        if (known2GSubtypes.contains(Integer.valueOf(i))) {
            connectivity = NetworkInfo.Connectivity.NETWORK_2G;
        } else if (known3GSubtypes.contains(Integer.valueOf(i))) {
            connectivity = NetworkInfo.Connectivity.NETWORK_3G;
        } else if (known4GSubtypes.contains(Integer.valueOf(i))) {
            connectivity = NetworkInfo.Connectivity.NETWORK_4G;
        } else if (known5GSubtypes.contains(Integer.valueOf(i))) {
            connectivity = NetworkInfo.Connectivity.NETWORK_5G;
        } else {
            connectivity = NetworkInfo.Connectivity.NETWORK_MOBILE_OTHER;
        }
        NetworkInfo.Connectivity connectivity2 = connectivity;
        String cellularTechnology = getCellularTechnology(i);
        if (this.buildSdkVersionProvider.version() < 28) {
            return new NetworkInfo(connectivity2, (String) null, (Long) null, (Long) null, (Long) null, (Long) null, cellularTechnology, 62, (DefaultConstructorMarker) null);
        }
        Object systemService = context.getSystemService("phone");
        TelephonyManager telephonyManager = systemService instanceof TelephonyManager ? (TelephonyManager) systemService : null;
        CharSequence charSequence = (telephonyManager == null || (simCarrierIdName = telephonyManager.getSimCarrierIdName()) == null) ? UNKNOWN_CARRIER_NAME : simCarrierIdName;
        if (telephonyManager == null) {
            l = null;
        } else {
            l = Long.valueOf((long) telephonyManager.getSimCarrierId());
        }
        return new NetworkInfo(connectivity2, charSequence.toString(), l, (Long) null, (Long) null, (Long) null, cellularTechnology, 56, (DefaultConstructorMarker) null);
    }

    @Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/info/BroadcastReceiverNetworkInfoProvider$Companion;", "", "()V", "UNKNOWN_CARRIER_NAME", "", "known2GSubtypes", "", "", "known3GSubtypes", "known4GSubtypes", "known5GSubtypes", "knownMobileTypes", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: BroadcastReceiverNetworkInfoProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
