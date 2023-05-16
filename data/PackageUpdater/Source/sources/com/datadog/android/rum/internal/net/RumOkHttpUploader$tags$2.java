package com.datadog.android.rum.internal.net;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.webview.internal.log.WebViewLogEventConsumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumOkHttpUploader.kt */
final class RumOkHttpUploader$tags$2 extends Lambda implements Function0<String> {
    public static final RumOkHttpUploader$tags$2 INSTANCE = new RumOkHttpUploader$tags$2();

    RumOkHttpUploader$tags$2() {
        super(0);
    }

    public final String invoke() {
        boolean z = false;
        List mutableListOf = CollectionsKt.mutableListOf("service:" + CoreFeature.INSTANCE.getServiceName$dd_sdk_android_release(), "version:" + CoreFeature.INSTANCE.getPackageVersion$dd_sdk_android_release(), "sdk_version:1.13.0-rc1", "env:" + CoreFeature.INSTANCE.getEnvName$dd_sdk_android_release());
        if (CoreFeature.INSTANCE.getVariant$dd_sdk_android_release().length() > 0) {
            z = true;
        }
        if (z) {
            mutableListOf.add("variant:" + CoreFeature.INSTANCE.getVariant$dd_sdk_android_release());
        }
        return CollectionsKt.joinToString$default(mutableListOf, WebViewLogEventConsumer.DDTAGS_SEPARATOR, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }
}
