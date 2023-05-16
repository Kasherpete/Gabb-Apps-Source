package com.datadog.android.core.internal.net;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DataOkHttpUploaderV2.kt */
final class DataOkHttpUploaderV2$userAgent$2 extends Lambda implements Function0<String> {
    final /* synthetic */ DataOkHttpUploaderV2 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataOkHttpUploaderV2$userAgent$2(DataOkHttpUploaderV2 dataOkHttpUploaderV2) {
        super(0);
        this.this$0 = dataOkHttpUploaderV2;
    }

    public final String invoke() {
        String property = System.getProperty(DataOkHttpUploader.SYSTEM_UA);
        DataOkHttpUploaderV2 dataOkHttpUploaderV2 = this.this$0;
        CharSequence charSequence = property;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            String sdkVersion$dd_sdk_android_release = dataOkHttpUploaderV2.getSdkVersion$dd_sdk_android_release();
            String deviceVersion = dataOkHttpUploaderV2.getAndroidInfoProvider$dd_sdk_android_release().getDeviceVersion();
            return "Datadog/" + sdkVersion$dd_sdk_android_release + " (Linux; U; Android " + deviceVersion + "; " + dataOkHttpUploaderV2.getAndroidInfoProvider$dd_sdk_android_release().getDeviceModel() + " Build/" + dataOkHttpUploaderV2.getAndroidInfoProvider$dd_sdk_android_release().getDeviceBuildId() + ")";
        }
        Intrinsics.checkNotNullExpressionValue(property, "{\n                it\n            }");
        return property;
    }
}
