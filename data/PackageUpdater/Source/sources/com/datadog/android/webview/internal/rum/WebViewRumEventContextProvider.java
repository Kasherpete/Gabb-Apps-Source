package com.datadog.android.webview.internal.rum;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.internal.domain.RumContext;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/rum/WebViewRumEventContextProvider;", "", "()V", "rumFeatureDisabled", "", "getRumContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WebViewRumEventContextProvider.kt */
public final class WebViewRumEventContextProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String RUM_NOT_INITIALIZED_ERROR_MESSAGE = "Trying to consume a bundled rum event but the RUM feature was not yet initialized. Missing the RUM context.";
    public static final String RUM_NOT_INITIALIZED_WARNING_MESSAGE = "You are trying to use the WebView tracking API but the RUM feature was not properly initialized.";
    private boolean rumFeatureDisabled;

    public final RumContext getRumContext() {
        if (this.rumFeatureDisabled) {
            return null;
        }
        RumContext rumContext$dd_sdk_android_release = GlobalRum.INSTANCE.getRumContext$dd_sdk_android_release();
        if (!Intrinsics.areEqual((Object) rumContext$dd_sdk_android_release.getApplicationId(), (Object) RumContext.Companion.getNULL_UUID()) && !Intrinsics.areEqual((Object) rumContext$dd_sdk_android_release.getSessionId(), (Object) RumContext.Companion.getNULL_UUID())) {
            return rumContext$dd_sdk_android_release;
        }
        this.rumFeatureDisabled = true;
        Logger.w$default(RuntimeUtilsKt.getDevLogger(), RUM_NOT_INITIALIZED_WARNING_MESSAGE, (Throwable) null, (Map) null, 6, (Object) null);
        Logger.e$default(RuntimeUtilsKt.getSdkLogger(), RUM_NOT_INITIALIZED_ERROR_MESSAGE, (Throwable) null, (Map) null, 6, (Object) null);
        return null;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo20735d2 = {"Lcom/datadog/android/webview/internal/rum/WebViewRumEventContextProvider$Companion;", "", "()V", "RUM_NOT_INITIALIZED_ERROR_MESSAGE", "", "RUM_NOT_INITIALIZED_WARNING_MESSAGE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: WebViewRumEventContextProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
