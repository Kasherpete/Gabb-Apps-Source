package com.datadog.android.webview;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.webview.internal.MixedWebViewEventConsumer;
import com.datadog.android.webview.internal.WebViewEventConsumer;
import com.datadog.android.webview.internal.log.WebViewLogEventConsumer;
import com.datadog.android.webview.internal.log.WebViewLogsFeature;
import com.datadog.android.webview.internal.rum.WebViewRumEventConsumer;
import com.datadog.android.webview.internal.rum.WebViewRumEventContextProvider;
import com.datadog.android.webview.internal.rum.WebViewRumEventMapper;
import com.datadog.android.webview.internal.rum.WebViewRumFeature;
import com.google.gson.JsonArray;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0015\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006B#\b\u0000\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\u0005H\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/webview/DatadogEventBridge;", "", "()V", "allowedHosts", "", "", "(Ljava/util/List;)V", "webViewEventConsumer", "Lcom/datadog/android/webview/internal/WebViewEventConsumer;", "(Lcom/datadog/android/webview/internal/WebViewEventConsumer;Ljava/util/List;)V", "getWebViewEventConsumer$dd_sdk_android_release", "()Lcom/datadog/android/webview/internal/WebViewEventConsumer;", "getAllowedWebViewHosts", "send", "", "event", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogEventBridge.kt */
public final class DatadogEventBridge {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DATADOG_EVENT_BRIDGE_NAME = "DatadogEventBridge";
    public static final String JAVA_SCRIPT_NOT_ENABLED_WARNING_MESSAGE = "You are trying to enable the WebViewtracking but the java script capability was not enabled for the given WebView.";
    private final List<String> allowedHosts;
    private final WebViewEventConsumer<String> webViewEventConsumer;

    public DatadogEventBridge(WebViewEventConsumer<String> webViewEventConsumer2, List<String> list) {
        Intrinsics.checkNotNullParameter(webViewEventConsumer2, "webViewEventConsumer");
        Intrinsics.checkNotNullParameter(list, "allowedHosts");
        this.webViewEventConsumer = webViewEventConsumer2;
        this.allowedHosts = list;
    }

    public final WebViewEventConsumer<String> getWebViewEventConsumer$dd_sdk_android_release() {
        return this.webViewEventConsumer;
    }

    public DatadogEventBridge() {
        this(Companion.buildWebViewEventConsumer(), CollectionsKt.emptyList());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DatadogEventBridge(List<String> list) {
        this(Companion.buildWebViewEventConsumer(), list);
        Intrinsics.checkNotNullParameter(list, "allowedHosts");
    }

    @JavascriptInterface
    public final void send(String str) {
        Intrinsics.checkNotNullParameter(str, "event");
        this.webViewEventConsumer.consume(str);
    }

    @JavascriptInterface
    public final String getAllowedWebViewHosts() {
        JsonArray jsonArray = new JsonArray();
        for (String add : this.allowedHosts) {
            jsonArray.add(add);
        }
        for (String add2 : CoreFeature.INSTANCE.getWebViewTrackingHosts$dd_sdk_android_release()) {
            jsonArray.add(add2);
        }
        String jsonArray2 = jsonArray.toString();
        Intrinsics.checkNotNullExpressionValue(jsonArray2, "origins.toString()");
        return jsonArray2;
    }

    @Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/webview/DatadogEventBridge$Companion;", "", "()V", "DATADOG_EVENT_BRIDGE_NAME", "", "JAVA_SCRIPT_NOT_ENABLED_WARNING_MESSAGE", "buildWebViewEventConsumer", "Lcom/datadog/android/webview/internal/WebViewEventConsumer;", "setup", "", "webView", "Landroid/webkit/WebView;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DatadogEventBridge.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void setup(WebView webView) {
            Intrinsics.checkNotNullParameter(webView, "webView");
            if (!webView.getSettings().getJavaScriptEnabled()) {
                Logger.w$default(RuntimeUtilsKt.getDevLogger(), DatadogEventBridge.JAVA_SCRIPT_NOT_ENABLED_WARNING_MESSAGE, (Throwable) null, (Map) null, 6, (Object) null);
            }
            webView.addJavascriptInterface(new DatadogEventBridge(), DatadogEventBridge.DATADOG_EVENT_BRIDGE_NAME);
        }

        /* access modifiers changed from: private */
        public final WebViewEventConsumer<String> buildWebViewEventConsumer() {
            WebViewRumEventContextProvider webViewRumEventContextProvider = new WebViewRumEventContextProvider();
            return new MixedWebViewEventConsumer(new WebViewRumEventConsumer(WebViewRumFeature.INSTANCE.getPersistenceStrategy$dd_sdk_android_release().getWriter(), CoreFeature.INSTANCE.getTimeProvider$dd_sdk_android_release(), (WebViewRumEventMapper) null, webViewRumEventContextProvider, 4, (DefaultConstructorMarker) null), new WebViewLogEventConsumer(WebViewLogsFeature.INSTANCE.getPersistenceStrategy$dd_sdk_android_release().getWriter(), webViewRumEventContextProvider, CoreFeature.INSTANCE.getTimeProvider$dd_sdk_android_release()));
        }
    }
}
