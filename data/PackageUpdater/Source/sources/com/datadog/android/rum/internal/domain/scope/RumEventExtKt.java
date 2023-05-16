package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.core.model.NetworkInfo;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.RumActionType;
import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumResourceKind;
import com.datadog.android.rum.internal.RumErrorSourceType;
import com.datadog.android.rum.internal.domain.event.ResourceTiming;
import com.datadog.android.rum.model.ActionEvent;
import com.datadog.android.rum.model.ErrorEvent;
import com.datadog.android.rum.model.LongTaskEvent;
import com.datadog.android.rum.model.ResourceEvent;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0002H\u0000\u001a\u000e\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u0002H\u0000\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0002H\u0000\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0000\u001a\u000e\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u0002H\u0000\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u000bH\u0000\u001a\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0012H\u0000\u001a\f\u0010\u0013\u001a\u00020\u0014*\u00020\u000bH\u0000\u001a\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0012H\u0000\u001a\f\u0010\u0017\u001a\u00020\u0018*\u00020\u000bH\u0000\u001a\f\u0010\u0019\u001a\u00020\u001a*\u00020\u001bH\u0000\u001a\f\u0010\u001c\u001a\u00020\u001d*\u00020\u001eH\u0000\u001a\f\u0010\u001f\u001a\u00020 *\u00020!H\u0000\u001a\f\u0010\u001f\u001a\u00020\"*\u00020#H\u0000¨\u0006$"}, mo20735d2 = {"connect", "Lcom/datadog/android/rum/model/ResourceEvent$Connect;", "Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "dns", "Lcom/datadog/android/rum/model/ResourceEvent$Dns;", "download", "Lcom/datadog/android/rum/model/ResourceEvent$Download;", "firstByte", "Lcom/datadog/android/rum/model/ResourceEvent$FirstByte;", "isConnected", "", "Lcom/datadog/android/core/model/NetworkInfo;", "ssl", "Lcom/datadog/android/rum/model/ResourceEvent$Ssl;", "toErrorConnectivity", "Lcom/datadog/android/rum/model/ErrorEvent$Connectivity;", "toErrorMethod", "Lcom/datadog/android/rum/model/ErrorEvent$Method;", "", "toLongTaskConnectivity", "Lcom/datadog/android/rum/model/LongTaskEvent$Connectivity;", "toMethod", "Lcom/datadog/android/rum/model/ResourceEvent$Method;", "toResourceConnectivity", "Lcom/datadog/android/rum/model/ResourceEvent$Connectivity;", "toSchemaSource", "Lcom/datadog/android/rum/model/ErrorEvent$ErrorSource;", "Lcom/datadog/android/rum/RumErrorSource;", "toSchemaSourceType", "Lcom/datadog/android/rum/model/ErrorEvent$SourceType;", "Lcom/datadog/android/rum/internal/RumErrorSourceType;", "toSchemaType", "Lcom/datadog/android/rum/model/ActionEvent$ActionType;", "Lcom/datadog/android/rum/RumActionType;", "Lcom/datadog/android/rum/model/ResourceEvent$ResourceType;", "Lcom/datadog/android/rum/RumResourceKind;", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumEventExt.kt */
public final class RumEventExtKt {

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumEventExt.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            int[] iArr = new int[RumResourceKind.values().length];
            iArr[RumResourceKind.BEACON.ordinal()] = 1;
            iArr[RumResourceKind.FETCH.ordinal()] = 2;
            iArr[RumResourceKind.XHR.ordinal()] = 3;
            iArr[RumResourceKind.DOCUMENT.ordinal()] = 4;
            iArr[RumResourceKind.IMAGE.ordinal()] = 5;
            iArr[RumResourceKind.JS.ordinal()] = 6;
            iArr[RumResourceKind.FONT.ordinal()] = 7;
            iArr[RumResourceKind.CSS.ordinal()] = 8;
            iArr[RumResourceKind.MEDIA.ordinal()] = 9;
            iArr[RumResourceKind.NATIVE.ordinal()] = 10;
            iArr[RumResourceKind.UNKNOWN.ordinal()] = 11;
            iArr[RumResourceKind.OTHER.ordinal()] = 12;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[RumErrorSource.values().length];
            iArr2[RumErrorSource.NETWORK.ordinal()] = 1;
            iArr2[RumErrorSource.SOURCE.ordinal()] = 2;
            iArr2[RumErrorSource.CONSOLE.ordinal()] = 3;
            iArr2[RumErrorSource.LOGGER.ordinal()] = 4;
            iArr2[RumErrorSource.AGENT.ordinal()] = 5;
            iArr2[RumErrorSource.WEBVIEW.ordinal()] = 6;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[RumErrorSourceType.values().length];
            iArr3[RumErrorSourceType.ANDROID.ordinal()] = 1;
            iArr3[RumErrorSourceType.BROWSER.ordinal()] = 2;
            iArr3[RumErrorSourceType.REACT_NATIVE.ordinal()] = 3;
            iArr3[RumErrorSourceType.FLUTTER.ordinal()] = 4;
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[RumActionType.values().length];
            iArr4[RumActionType.TAP.ordinal()] = 1;
            iArr4[RumActionType.SCROLL.ordinal()] = 2;
            iArr4[RumActionType.SWIPE.ordinal()] = 3;
            iArr4[RumActionType.CLICK.ordinal()] = 4;
            iArr4[RumActionType.CUSTOM.ordinal()] = 5;
            $EnumSwitchMapping$3 = iArr4;
            int[] iArr5 = new int[NetworkInfo.Connectivity.values().length];
            iArr5[NetworkInfo.Connectivity.NETWORK_ETHERNET.ordinal()] = 1;
            iArr5[NetworkInfo.Connectivity.NETWORK_WIFI.ordinal()] = 2;
            iArr5[NetworkInfo.Connectivity.NETWORK_WIMAX.ordinal()] = 3;
            iArr5[NetworkInfo.Connectivity.NETWORK_BLUETOOTH.ordinal()] = 4;
            iArr5[NetworkInfo.Connectivity.NETWORK_2G.ordinal()] = 5;
            iArr5[NetworkInfo.Connectivity.NETWORK_3G.ordinal()] = 6;
            iArr5[NetworkInfo.Connectivity.NETWORK_4G.ordinal()] = 7;
            iArr5[NetworkInfo.Connectivity.NETWORK_5G.ordinal()] = 8;
            iArr5[NetworkInfo.Connectivity.NETWORK_MOBILE_OTHER.ordinal()] = 9;
            iArr5[NetworkInfo.Connectivity.NETWORK_CELLULAR.ordinal()] = 10;
            iArr5[NetworkInfo.Connectivity.NETWORK_OTHER.ordinal()] = 11;
            iArr5[NetworkInfo.Connectivity.NETWORK_NOT_CONNECTED.ordinal()] = 12;
            $EnumSwitchMapping$4 = iArr5;
        }
    }

    public static final ResourceEvent.Method toMethod(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String upperCase = str.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            return ResourceEvent.Method.valueOf(upperCase);
        } catch (IllegalArgumentException e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Unable to convert [" + str + "] to a valid http method", e, (Map) null, 4, (Object) null);
            return ResourceEvent.Method.GET;
        }
    }

    public static final ErrorEvent.Method toErrorMethod(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String upperCase = str.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            return ErrorEvent.Method.valueOf(upperCase);
        } catch (IllegalArgumentException e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Unable to convert [" + str + "] to a valid http method", e, (Map) null, 4, (Object) null);
            return ErrorEvent.Method.GET;
        }
    }

    public static final ResourceEvent.ResourceType toSchemaType(RumResourceKind rumResourceKind) {
        Intrinsics.checkNotNullParameter(rumResourceKind, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[rumResourceKind.ordinal()]) {
            case 1:
                return ResourceEvent.ResourceType.BEACON;
            case 2:
                return ResourceEvent.ResourceType.FETCH;
            case 3:
                return ResourceEvent.ResourceType.XHR;
            case 4:
                return ResourceEvent.ResourceType.DOCUMENT;
            case 5:
                return ResourceEvent.ResourceType.IMAGE;
            case 6:
                return ResourceEvent.ResourceType.JS;
            case 7:
                return ResourceEvent.ResourceType.FONT;
            case 8:
                return ResourceEvent.ResourceType.CSS;
            case 9:
                return ResourceEvent.ResourceType.MEDIA;
            case 10:
                return ResourceEvent.ResourceType.NATIVE;
            case 11:
            case 12:
                return ResourceEvent.ResourceType.OTHER;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final ErrorEvent.ErrorSource toSchemaSource(RumErrorSource rumErrorSource) {
        Intrinsics.checkNotNullParameter(rumErrorSource, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$1[rumErrorSource.ordinal()]) {
            case 1:
                return ErrorEvent.ErrorSource.NETWORK;
            case 2:
                return ErrorEvent.ErrorSource.SOURCE;
            case 3:
                return ErrorEvent.ErrorSource.CONSOLE;
            case 4:
                return ErrorEvent.ErrorSource.LOGGER;
            case 5:
                return ErrorEvent.ErrorSource.AGENT;
            case 6:
                return ErrorEvent.ErrorSource.WEBVIEW;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final ErrorEvent.SourceType toSchemaSourceType(RumErrorSourceType rumErrorSourceType) {
        Intrinsics.checkNotNullParameter(rumErrorSourceType, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$2[rumErrorSourceType.ordinal()];
        if (i == 1) {
            return ErrorEvent.SourceType.ANDROID;
        }
        if (i == 2) {
            return ErrorEvent.SourceType.BROWSER;
        }
        if (i == 3) {
            return ErrorEvent.SourceType.REACT_NATIVE;
        }
        if (i == 4) {
            return ErrorEvent.SourceType.FLUTTER;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final ResourceEvent.Dns dns(ResourceTiming resourceTiming) {
        Intrinsics.checkNotNullParameter(resourceTiming, "<this>");
        if (resourceTiming.getDnsStart() > 0) {
            return new ResourceEvent.Dns(resourceTiming.getDnsDuration(), resourceTiming.getDnsStart());
        }
        return null;
    }

    public static final ResourceEvent.Connect connect(ResourceTiming resourceTiming) {
        Intrinsics.checkNotNullParameter(resourceTiming, "<this>");
        if (resourceTiming.getConnectStart() > 0) {
            return new ResourceEvent.Connect(resourceTiming.getConnectDuration(), resourceTiming.getConnectStart());
        }
        return null;
    }

    public static final ResourceEvent.Ssl ssl(ResourceTiming resourceTiming) {
        Intrinsics.checkNotNullParameter(resourceTiming, "<this>");
        if (resourceTiming.getSslStart() > 0) {
            return new ResourceEvent.Ssl(resourceTiming.getSslDuration(), resourceTiming.getSslStart());
        }
        return null;
    }

    public static final ResourceEvent.FirstByte firstByte(ResourceTiming resourceTiming) {
        Intrinsics.checkNotNullParameter(resourceTiming, "<this>");
        if (resourceTiming.getFirstByteStart() < 0 || resourceTiming.getFirstByteDuration() <= 0) {
            return null;
        }
        return new ResourceEvent.FirstByte(resourceTiming.getFirstByteDuration(), resourceTiming.getFirstByteStart());
    }

    public static final ResourceEvent.Download download(ResourceTiming resourceTiming) {
        Intrinsics.checkNotNullParameter(resourceTiming, "<this>");
        if (resourceTiming.getDownloadStart() > 0) {
            return new ResourceEvent.Download(resourceTiming.getDownloadDuration(), resourceTiming.getDownloadStart());
        }
        return null;
    }

    public static final ActionEvent.ActionType toSchemaType(RumActionType rumActionType) {
        Intrinsics.checkNotNullParameter(rumActionType, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$3[rumActionType.ordinal()];
        if (i == 1) {
            return ActionEvent.ActionType.TAP;
        }
        if (i == 2) {
            return ActionEvent.ActionType.SCROLL;
        }
        if (i == 3) {
            return ActionEvent.ActionType.SWIPE;
        }
        if (i == 4) {
            return ActionEvent.ActionType.CLICK;
        }
        if (i == 5) {
            return ActionEvent.ActionType.CUSTOM;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final ResourceEvent.Connectivity toResourceConnectivity(NetworkInfo networkInfo) {
        ResourceEvent.Status status;
        List list;
        ResourceEvent.Cellular cellular;
        Intrinsics.checkNotNullParameter(networkInfo, "<this>");
        if (isConnected(networkInfo)) {
            status = ResourceEvent.Status.CONNECTED;
        } else {
            status = ResourceEvent.Status.NOT_CONNECTED;
        }
        switch (WhenMappings.$EnumSwitchMapping$4[networkInfo.getConnectivity().ordinal()]) {
            case 1:
                list = CollectionsKt.listOf(ResourceEvent.Interface.ETHERNET);
                break;
            case 2:
                list = CollectionsKt.listOf(ResourceEvent.Interface.WIFI);
                break;
            case 3:
                list = CollectionsKt.listOf(ResourceEvent.Interface.WIMAX);
                break;
            case 4:
                list = CollectionsKt.listOf(ResourceEvent.Interface.BLUETOOTH);
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                list = CollectionsKt.listOf(ResourceEvent.Interface.CELLULAR);
                break;
            case 11:
                list = CollectionsKt.listOf(ResourceEvent.Interface.OTHER);
                break;
            case 12:
                list = CollectionsKt.emptyList();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (networkInfo.getCellularTechnology() == null && networkInfo.getCarrierName() == null) {
            cellular = null;
        } else {
            cellular = new ResourceEvent.Cellular(networkInfo.getCellularTechnology(), networkInfo.getCarrierName());
        }
        return new ResourceEvent.Connectivity(status, list, cellular);
    }

    public static final ErrorEvent.Connectivity toErrorConnectivity(NetworkInfo networkInfo) {
        ErrorEvent.Status status;
        List list;
        ErrorEvent.Cellular cellular;
        Intrinsics.checkNotNullParameter(networkInfo, "<this>");
        if (isConnected(networkInfo)) {
            status = ErrorEvent.Status.CONNECTED;
        } else {
            status = ErrorEvent.Status.NOT_CONNECTED;
        }
        switch (WhenMappings.$EnumSwitchMapping$4[networkInfo.getConnectivity().ordinal()]) {
            case 1:
                list = CollectionsKt.listOf(ErrorEvent.Interface.ETHERNET);
                break;
            case 2:
                list = CollectionsKt.listOf(ErrorEvent.Interface.WIFI);
                break;
            case 3:
                list = CollectionsKt.listOf(ErrorEvent.Interface.WIMAX);
                break;
            case 4:
                list = CollectionsKt.listOf(ErrorEvent.Interface.BLUETOOTH);
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                list = CollectionsKt.listOf(ErrorEvent.Interface.CELLULAR);
                break;
            case 11:
                list = CollectionsKt.listOf(ErrorEvent.Interface.OTHER);
                break;
            case 12:
                list = CollectionsKt.emptyList();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (networkInfo.getCellularTechnology() == null && networkInfo.getCarrierName() == null) {
            cellular = null;
        } else {
            cellular = new ErrorEvent.Cellular(networkInfo.getCellularTechnology(), networkInfo.getCarrierName());
        }
        return new ErrorEvent.Connectivity(status, list, cellular);
    }

    public static final LongTaskEvent.Connectivity toLongTaskConnectivity(NetworkInfo networkInfo) {
        LongTaskEvent.Status status;
        List list;
        LongTaskEvent.Cellular cellular;
        Intrinsics.checkNotNullParameter(networkInfo, "<this>");
        if (isConnected(networkInfo)) {
            status = LongTaskEvent.Status.CONNECTED;
        } else {
            status = LongTaskEvent.Status.NOT_CONNECTED;
        }
        switch (WhenMappings.$EnumSwitchMapping$4[networkInfo.getConnectivity().ordinal()]) {
            case 1:
                list = CollectionsKt.listOf(LongTaskEvent.Interface.ETHERNET);
                break;
            case 2:
                list = CollectionsKt.listOf(LongTaskEvent.Interface.WIFI);
                break;
            case 3:
                list = CollectionsKt.listOf(LongTaskEvent.Interface.WIMAX);
                break;
            case 4:
                list = CollectionsKt.listOf(LongTaskEvent.Interface.BLUETOOTH);
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                list = CollectionsKt.listOf(LongTaskEvent.Interface.CELLULAR);
                break;
            case 11:
                list = CollectionsKt.listOf(LongTaskEvent.Interface.OTHER);
                break;
            case 12:
                list = CollectionsKt.emptyList();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (networkInfo.getCellularTechnology() == null && networkInfo.getCarrierName() == null) {
            cellular = null;
        } else {
            cellular = new LongTaskEvent.Cellular(networkInfo.getCellularTechnology(), networkInfo.getCarrierName());
        }
        return new LongTaskEvent.Connectivity(status, list, cellular);
    }

    public static final boolean isConnected(NetworkInfo networkInfo) {
        Intrinsics.checkNotNullParameter(networkInfo, "<this>");
        return networkInfo.getConnectivity() != NetworkInfo.Connectivity.NETWORK_NOT_CONNECTED;
    }
}
