package com.datadog.android.core.configuration;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.trace.api.Config;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\b¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/HostsSanitizer;", "", "()V", "sanitizeHosts", "", "", "hosts", "feature", "sanitizeHosts$dd_sdk_android_release", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: HostsSanitizer.kt */
public final class HostsSanitizer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ERROR_MALFORMED_HOST_IP_ADDRESS = "You are using a malformed host or ip address \"%s\" to setup %s tracking. It will be dropped.";
    public static final String ERROR_MALFORMED_URL = "You are using a malformed url \"%s\" to setup %s tracking. It will be dropped. Please try using a host name instead, e.g.: \"example.com\"";
    private static final String URL_REGEX = "^(http|https)://(.*)";
    private static final String VALID_DOMAIN_REGEX = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)+([A-Za-z]|[A-Za-z][A-Za-z0-9-]*[A-Za-z0-9])$";
    private static final String VALID_HOSTNAME_REGEX = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$|^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)+([A-Za-z]|[A-Za-z][A-Za-z0-9-]*[A-Za-z0-9])$";
    private static final String VALID_IP_REGEX = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
    public static final String WARNING_USING_URL = "You are using a url \"%s\" instead of a host to setup %s tracking. You should use instead a valid host name: \"%s\"";

    public final List<String> sanitizeHosts$dd_sdk_android_release(List<String> list, String str) {
        List<String> list2 = list;
        String str2 = str;
        Intrinsics.checkNotNullParameter(list2, "hosts");
        Intrinsics.checkNotNullParameter(str2, "feature");
        Regex regex = new Regex(VALID_HOSTNAME_REGEX);
        Regex regex2 = new Regex(URL_REGEX);
        Collection arrayList = new ArrayList();
        for (String str3 : list2) {
            CharSequence charSequence = str3;
            if (regex2.matches(charSequence)) {
                try {
                    URL url = new URL(str3);
                    Logger devLogger = RuntimeUtilsKt.getDevLogger();
                    String format = String.format(Locale.US, WARNING_USING_URL, Arrays.copyOf(new Object[]{str3, str2, url.getHost()}, 3));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
                    Logger.w$default(devLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
                    str3 = url.getHost();
                } catch (MalformedURLException e) {
                    Logger devLogger2 = RuntimeUtilsKt.getDevLogger();
                    String format2 = String.format(Locale.US, ERROR_MALFORMED_URL, Arrays.copyOf(new Object[]{str3, str2}, 2));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
                    Logger.e$default(devLogger2, format2, e, (Map) null, 4, (Object) null);
                    str3 = null;
                }
            } else if (!regex.matches(charSequence)) {
                Locale locale = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale, "US");
                String lowerCase = str3.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (!Intrinsics.areEqual((Object) lowerCase, (Object) Config.DEFAULT_AGENT_HOST)) {
                    Logger devLogger3 = RuntimeUtilsKt.getDevLogger();
                    String format3 = String.format(Locale.US, ERROR_MALFORMED_HOST_IP_ADDRESS, Arrays.copyOf(new Object[]{str3, str2}, 2));
                    Intrinsics.checkNotNullExpressionValue(format3, "format(locale, this, *args)");
                    Logger.e$default(devLogger3, format3, (Throwable) null, (Map) null, 6, (Object) null);
                    str3 = null;
                }
            }
            if (str3 != null) {
                arrayList.add(str3);
            }
        }
        return (List) arrayList;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/HostsSanitizer$Companion;", "", "()V", "ERROR_MALFORMED_HOST_IP_ADDRESS", "", "ERROR_MALFORMED_URL", "URL_REGEX", "VALID_DOMAIN_REGEX", "VALID_HOSTNAME_REGEX", "VALID_IP_REGEX", "WARNING_USING_URL", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: HostsSanitizer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
