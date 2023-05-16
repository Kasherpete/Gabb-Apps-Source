package com.datadog.android.core.internal.net;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.system.AndroidInfoProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Metadata(mo20734d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b \u0018\u0000 &2\u00020\u0001:\u0001&B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\u0014\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00190\u0018H&J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0014\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001fH\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010%\u001a\u00020\u0003H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000f\"\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00038BX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0014\u0010\u000f¨\u0006'"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/DataOkHttpUploader;", "Lcom/datadog/android/core/internal/net/DataUploader;", "url", "", "callFactory", "Lokhttp3/Call$Factory;", "androidInfoProvider", "Lcom/datadog/android/core/internal/system/AndroidInfoProvider;", "contentType", "(Ljava/lang/String;Lokhttp3/Call$Factory;Lcom/datadog/android/core/internal/system/AndroidInfoProvider;Ljava/lang/String;)V", "getAndroidInfoProvider$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/system/AndroidInfoProvider;", "getCallFactory$dd_sdk_android_release", "()Lokhttp3/Call$Factory;", "getContentType$dd_sdk_android_release", "()Ljava/lang/String;", "getUrl$dd_sdk_android_release", "setUrl$dd_sdk_android_release", "(Ljava/lang/String;)V", "userAgent", "getUserAgent", "userAgent$delegate", "Lkotlin/Lazy;", "buildQueryParams", "", "", "buildRequest", "Lokhttp3/Request;", "data", "", "headers", "", "responseCodeToUploadStatus", "Lcom/datadog/android/core/internal/net/UploadStatus;", "code", "", "upload", "urlWithQueryParams", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DataOkHttpUploader.kt */
public abstract class DataOkHttpUploader implements DataUploader {
    private static final String ACTIVE_THREADS_LOG_ATTRIBUTE_KEY = "active_threads";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_TEXT_UTF8 = "text/plain;charset=UTF-8";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String HEADER_CT = "Content-Type";
    private static final String HEADER_UA = "User-Agent";
    public static final String QP_BATCH_TIME = "batch_time";
    public static final String QP_SOURCE = "ddsource";
    public static final String SYSTEM_UA = "http.agent";
    private final AndroidInfoProvider androidInfoProvider;
    private final Call.Factory callFactory;
    private final String contentType;
    private String url;
    private final Lazy userAgent$delegate;

    public abstract Map<String, Object> buildQueryParams();

    public DataOkHttpUploader(String str, Call.Factory factory, AndroidInfoProvider androidInfoProvider2, String str2) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(factory, "callFactory");
        Intrinsics.checkNotNullParameter(androidInfoProvider2, "androidInfoProvider");
        Intrinsics.checkNotNullParameter(str2, "contentType");
        this.url = str;
        this.callFactory = factory;
        this.androidInfoProvider = androidInfoProvider2;
        this.contentType = str2;
        this.userAgent$delegate = LazyKt.lazy(new DataOkHttpUploader$userAgent$2(this));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DataOkHttpUploader(String str, Call.Factory factory, AndroidInfoProvider androidInfoProvider2, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, factory, androidInfoProvider2, (i & 8) != 0 ? "application/json" : str2);
    }

    public final String getUrl$dd_sdk_android_release() {
        return this.url;
    }

    public final void setUrl$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public final Call.Factory getCallFactory$dd_sdk_android_release() {
        return this.callFactory;
    }

    public final AndroidInfoProvider getAndroidInfoProvider$dd_sdk_android_release() {
        return this.androidInfoProvider;
    }

    public final String getContentType$dd_sdk_android_release() {
        return this.contentType;
    }

    public UploadStatus upload(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, MPDbAdapter.KEY_DATA);
        try {
            Response execute = this.callFactory.newCall(buildRequest(bArr)).execute();
            execute.close();
            return responseCodeToUploadStatus(execute.code());
        } catch (Throwable th) {
            RuntimeUtilsKt.getSdkLogger().mo13085e("Unable to upload batch data.", th, MapsKt.mapOf(TuplesKt.m78to(ACTIVE_THREADS_LOG_ATTRIBUTE_KEY, Integer.valueOf(CoreFeature.INSTANCE.getUploadExecutorService$dd_sdk_android_release().getActiveCount()))));
            return UploadStatus.NETWORK_ERROR;
        }
    }

    private final Map<String, String> headers() {
        return MapsKt.mutableMapOf(TuplesKt.m78to("User-Agent", getUserAgent()), TuplesKt.m78to("Content-Type", this.contentType));
    }

    private final String getUserAgent() {
        return (String) this.userAgent$delegate.getValue();
    }

    private final Request buildRequest(byte[] bArr) {
        Request.Builder post = new Request.Builder().url(urlWithQueryParams()).post(RequestBody.create((MediaType) null, bArr));
        for (Map.Entry next : headers().entrySet()) {
            post.addHeader((String) next.getKey(), (String) next.getValue());
        }
        Request build = post.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    private final String urlWithQueryParams() {
        Map<String, Object> buildQueryParams = buildQueryParams();
        if (buildQueryParams.isEmpty()) {
            return this.url;
        }
        String str = this.url;
        Collection arrayList = new ArrayList(buildQueryParams.size());
        for (Map.Entry next : buildQueryParams.entrySet()) {
            Object key = next.getKey();
            arrayList.add(key + "=" + next.getValue());
        }
        return str + CollectionsKt.joinToString$default((List) arrayList, "&", CoreFeature.DEFAULT_APP_VERSION, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 60, (Object) null);
    }

    private final UploadStatus responseCodeToUploadStatus(int i) {
        if (i == 403) {
            return UploadStatus.INVALID_TOKEN_ERROR;
        }
        boolean z = true;
        if (200 <= i && i < 300) {
            return UploadStatus.SUCCESS;
        }
        if (300 <= i && i < 400) {
            return UploadStatus.HTTP_REDIRECTION;
        }
        if (400 <= i && i < 500) {
            return UploadStatus.HTTP_CLIENT_ERROR;
        }
        if (500 > i || i >= 600) {
            z = false;
        }
        if (z) {
            return UploadStatus.HTTP_SERVER_ERROR;
        }
        return UploadStatus.UNKNOWN_ERROR;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/DataOkHttpUploader$Companion;", "", "()V", "ACTIVE_THREADS_LOG_ATTRIBUTE_KEY", "", "CONTENT_TYPE_JSON", "CONTENT_TYPE_TEXT_UTF8", "HEADER_CT", "HEADER_UA", "QP_BATCH_TIME", "QP_SOURCE", "SYSTEM_UA", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DataOkHttpUploader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
