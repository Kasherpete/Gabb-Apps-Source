package com.datadog.android.rum;

import java.util.Map;
import kotlin.Metadata;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bg\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH&¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/RumResourceAttributesProvider;", "", "onProvideAttributes", "", "", "request", "Lokhttp3/Request;", "response", "Lokhttp3/Response;", "throwable", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumResourceAttributesProvider.kt */
public interface RumResourceAttributesProvider {
    Map<String, Object> onProvideAttributes(Request request, Response response, Throwable th);
}
