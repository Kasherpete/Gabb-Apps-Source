package com.datadog.android.webview.internal.log;

import com.datadog.android.core.internal.CoreFeature;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WebViewLogEventConsumer.kt */
final class WebViewLogEventConsumer$ddTags$2 extends Lambda implements Function0<String> {
    public static final WebViewLogEventConsumer$ddTags$2 INSTANCE = new WebViewLogEventConsumer$ddTags$2();

    WebViewLogEventConsumer$ddTags$2() {
        super(0);
    }

    public final String invoke() {
        String packageVersion$dd_sdk_android_release = CoreFeature.INSTANCE.getPackageVersion$dd_sdk_android_release();
        return "version:" + packageVersion$dd_sdk_android_release + ",env:" + CoreFeature.INSTANCE.getEnvName$dd_sdk_android_release();
    }
}
