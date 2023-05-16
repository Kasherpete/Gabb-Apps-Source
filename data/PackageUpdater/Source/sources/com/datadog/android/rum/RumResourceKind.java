package com.datadog.android.rum;

import androidx.core.p003os.EnvironmentCompat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0001\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/rum/RumResourceKind;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue$dd_sdk_android_release", "()Ljava/lang/String;", "BEACON", "FETCH", "XHR", "DOCUMENT", "NATIVE", "UNKNOWN", "IMAGE", "JS", "FONT", "CSS", "MEDIA", "OTHER", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumResourceKind.kt */
public enum RumResourceKind {
    BEACON("beacon"),
    FETCH("fetch"),
    XHR("xhr"),
    DOCUMENT("document"),
    NATIVE("native"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN),
    IMAGE("image"),
    JS("js"),
    FONT("font"),
    CSS("css"),
    MEDIA("media"),
    OTHER("other");
    
    public static final Companion Companion = null;
    private final String value;

    @JvmStatic
    public static final RumResourceKind fromMimeType$dd_sdk_android_release(String str) {
        return Companion.fromMimeType$dd_sdk_android_release(str);
    }

    private RumResourceKind(String str) {
        this.value = str;
    }

    public final String getValue$dd_sdk_android_release() {
        return this.value;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¢\u0006\u0002\b\u0007¨\u0006\b"}, mo20735d2 = {"Lcom/datadog/android/rum/RumResourceKind$Companion;", "", "()V", "fromMimeType", "Lcom/datadog/android/rum/RumResourceKind;", "mimeType", "", "fromMimeType$dd_sdk_android_release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumResourceKind.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final RumResourceKind fromMimeType$dd_sdk_android_release(String str) {
            Intrinsics.checkNotNullParameter(str, "mimeType");
            String substringBefore$default = StringsKt.substringBefore$default(str, '/', (String) null, 2, (Object) null);
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String lowerCase = substringBefore$default.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            String substringBefore$default2 = StringsKt.substringBefore$default(StringsKt.substringAfter$default(str, '/', (String) null, 2, (Object) null), ';', (String) null, 2, (Object) null);
            Locale locale2 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale2, "US");
            String lowerCase2 = substringBefore$default2.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            if (Intrinsics.areEqual((Object) lowerCase, (Object) "image")) {
                return RumResourceKind.IMAGE;
            }
            if (Intrinsics.areEqual((Object) lowerCase, (Object) "video") || Intrinsics.areEqual((Object) lowerCase, (Object) "audio")) {
                return RumResourceKind.MEDIA;
            }
            if (Intrinsics.areEqual((Object) lowerCase, (Object) "font")) {
                return RumResourceKind.FONT;
            }
            if (Intrinsics.areEqual((Object) lowerCase, (Object) "text") && Intrinsics.areEqual((Object) lowerCase2, (Object) "css")) {
                return RumResourceKind.CSS;
            }
            if (!Intrinsics.areEqual((Object) lowerCase, (Object) "text") || !Intrinsics.areEqual((Object) lowerCase2, (Object) "javascript")) {
                return RumResourceKind.NATIVE;
            }
            return RumResourceKind.JS;
        }
    }
}
