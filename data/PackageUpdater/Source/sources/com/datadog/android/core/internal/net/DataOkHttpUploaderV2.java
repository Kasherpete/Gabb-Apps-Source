package com.datadog.android.core.internal.net;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.system.AndroidInfoProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.LogsFeature;
import com.datadog.android.tracing.internal.domain.event.SpanEventSerializer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Metadata(mo20734d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b \u0018\u0000 62\u00020\u0001:\u000267BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0003H\u0002J\u0014\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020*0)H\u0014J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010'\u001a\u00020\u0003H\u0002J\b\u0010/\u001a\u00020\u0003H\u0002J\u0018\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020.2\u0006\u0010'\u001a\u00020\u0003H\u0002J\u0010\u00102\u001a\u0002012\u0006\u00103\u001a\u000204H\u0002J\u0010\u00105\u001a\u0002012\u0006\u0010-\u001a\u00020.H\u0016R\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\t\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0014\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0006\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0016\u0010\u001d\u001a\n \u001e*\u0004\u0018\u00010\u00030\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020\u00038BX\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b \u0010\u0014¨\u00068"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/DataOkHttpUploaderV2;", "Lcom/datadog/android/core/internal/net/DataUploader;", "intakeUrl", "", "clientToken", "source", "sdkVersion", "callFactory", "Lokhttp3/Call$Factory;", "contentType", "androidInfoProvider", "Lcom/datadog/android/core/internal/system/AndroidInfoProvider;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lokhttp3/Call$Factory;Ljava/lang/String;Lcom/datadog/android/core/internal/system/AndroidInfoProvider;Lcom/datadog/android/log/Logger;)V", "getAndroidInfoProvider$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/system/AndroidInfoProvider;", "getCallFactory$dd_sdk_android_release", "()Lokhttp3/Call$Factory;", "getClientToken$dd_sdk_android_release", "()Ljava/lang/String;", "getContentType$dd_sdk_android_release", "getIntakeUrl$dd_sdk_android_release", "setIntakeUrl$dd_sdk_android_release", "(Ljava/lang/String;)V", "getInternalLogger$dd_sdk_android_release", "()Lcom/datadog/android/log/Logger;", "getSdkVersion$dd_sdk_android_release", "getSource$dd_sdk_android_release", "uploaderName", "kotlin.jvm.PlatformType", "userAgent", "getUserAgent", "userAgent$delegate", "Lkotlin/Lazy;", "buildHeaders", "", "builder", "Lokhttp3/Request$Builder;", "requestId", "buildQueryParameters", "", "", "buildRequest", "Lokhttp3/Request;", "data", "", "buildUrl", "executeUploadRequest", "Lcom/datadog/android/core/internal/net/UploadStatus;", "responseCodeToUploadStatus", "code", "", "upload", "Companion", "TrackType", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DataOkHttpUploaderV2.kt */
public abstract class DataOkHttpUploaderV2 implements DataUploader {
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_TEXT_UTF8 = "text/plain;charset=UTF-8";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String HEADER_API_KEY = "DD-API-KEY";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_EVP_ORIGIN = "DD-EVP-ORIGIN";
    public static final String HEADER_EVP_ORIGIN_VERSION = "DD-EVP-ORIGIN-VERSION";
    public static final String HEADER_REQUEST_ID = "DD-REQUEST-ID";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final int HTTP_ACCEPTED = 202;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_CLIENT_TIMEOUT = 408;
    public static final int HTTP_ENTITY_TOO_LARGE = 413;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_INTERNAL_ERROR = 500;
    public static final int HTTP_TOO_MANY_REQUESTS = 429;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_UNAVAILABLE = 503;
    public static final String QUERY_PARAM_SOURCE = "ddsource";
    public static final String QUERY_PARAM_TAGS = "ddtags";
    private static final String UPLOAD_URL = "%s/api/v2/%s";
    private final AndroidInfoProvider androidInfoProvider;
    private final Call.Factory callFactory;
    private final String clientToken;
    private final String contentType;
    private String intakeUrl;
    private final Logger internalLogger;
    private final String sdkVersion;
    private final String source;
    private final String uploaderName = getClass().getSimpleName();
    private final Lazy userAgent$delegate = LazyKt.lazy(new DataOkHttpUploaderV2$userAgent$2(this));

    public DataOkHttpUploaderV2(String str, String str2, String str3, String str4, Call.Factory factory, String str5, AndroidInfoProvider androidInfoProvider2, Logger logger) {
        Intrinsics.checkNotNullParameter(str, "intakeUrl");
        Intrinsics.checkNotNullParameter(str2, "clientToken");
        Intrinsics.checkNotNullParameter(str3, "source");
        Intrinsics.checkNotNullParameter(str4, "sdkVersion");
        Intrinsics.checkNotNullParameter(factory, "callFactory");
        Intrinsics.checkNotNullParameter(str5, "contentType");
        Intrinsics.checkNotNullParameter(androidInfoProvider2, "androidInfoProvider");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        this.intakeUrl = str;
        this.clientToken = str2;
        this.source = str3;
        this.sdkVersion = str4;
        this.callFactory = factory;
        this.contentType = str5;
        this.androidInfoProvider = androidInfoProvider2;
        this.internalLogger = logger;
    }

    public final String getIntakeUrl$dd_sdk_android_release() {
        return this.intakeUrl;
    }

    public final void setIntakeUrl$dd_sdk_android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.intakeUrl = str;
    }

    public final String getClientToken$dd_sdk_android_release() {
        return this.clientToken;
    }

    public final String getSource$dd_sdk_android_release() {
        return this.source;
    }

    public final String getSdkVersion$dd_sdk_android_release() {
        return this.sdkVersion;
    }

    public final Call.Factory getCallFactory$dd_sdk_android_release() {
        return this.callFactory;
    }

    public final String getContentType$dd_sdk_android_release() {
        return this.contentType;
    }

    public final AndroidInfoProvider getAndroidInfoProvider$dd_sdk_android_release() {
        return this.androidInfoProvider;
    }

    public final Logger getInternalLogger$dd_sdk_android_release() {
        return this.internalLogger;
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/DataOkHttpUploaderV2$TrackType;", "", "trackName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTrackName", "()Ljava/lang/String;", "LOGS", "RUM", "SPANS", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DataOkHttpUploaderV2.kt */
    public enum TrackType {
        LOGS(LogsFeature.LOGS_FEATURE_NAME),
        RUM("rum"),
        SPANS(SpanEventSerializer.TAG_SPANS);
        
        private final String trackName;

        private TrackType(String str) {
            this.trackName = str;
        }

        public final String getTrackName() {
            return this.trackName;
        }
    }

    private final String getUserAgent() {
        return (String) this.userAgent$delegate.getValue();
    }

    public UploadStatus upload(byte[] bArr) {
        UploadStatus uploadStatus;
        Intrinsics.checkNotNullParameter(bArr, MPDbAdapter.KEY_DATA);
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        try {
            uploadStatus = executeUploadRequest(bArr, uuid);
        } catch (Throwable th) {
            Logger.e$default(this.internalLogger, "Unable to upload batch data.", th, (Map) null, 4, (Object) null);
            uploadStatus = UploadStatus.NETWORK_ERROR;
        }
        UploadStatus uploadStatus2 = uploadStatus;
        String str = this.uploaderName;
        Intrinsics.checkNotNullExpressionValue(str, "uploaderName");
        UploadStatus uploadStatus3 = uploadStatus2;
        String str2 = uuid;
        uploadStatus3.logStatus(str, bArr.length, RuntimeUtilsKt.getDevLogger(), false, false, str2);
        String str3 = this.uploaderName;
        Intrinsics.checkNotNullExpressionValue(str3, "uploaderName");
        uploadStatus3.logStatus(str3, bArr.length, this.internalLogger, true, true, str2);
        return uploadStatus2;
    }

    private final UploadStatus executeUploadRequest(byte[] bArr, String str) {
        Response execute = this.callFactory.newCall(buildRequest(bArr, str)).execute();
        execute.close();
        return responseCodeToUploadStatus(execute.code());
    }

    private final Request buildRequest(byte[] bArr, String str) {
        Request.Builder post = new Request.Builder().url(buildUrl()).post(RequestBody.create((MediaType) null, bArr));
        Intrinsics.checkNotNullExpressionValue(post, "builder");
        buildHeaders(post, str);
        Request build = post.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    private final String buildUrl() {
        Map<String, Object> buildQueryParameters = buildQueryParameters();
        if (buildQueryParameters.isEmpty()) {
            return this.intakeUrl;
        }
        String str = this.intakeUrl;
        Collection arrayList = new ArrayList(buildQueryParameters.size());
        for (Map.Entry next : buildQueryParameters.entrySet()) {
            Object key = next.getKey();
            arrayList.add(key + "=" + next.getValue());
        }
        return str + CollectionsKt.joinToString$default((List) arrayList, "&", CoreFeature.DEFAULT_APP_VERSION, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 60, (Object) null);
    }

    private final void buildHeaders(Request.Builder builder, String str) {
        builder.addHeader(HEADER_API_KEY, this.clientToken);
        builder.addHeader(HEADER_EVP_ORIGIN, this.source);
        builder.addHeader(HEADER_EVP_ORIGIN_VERSION, this.sdkVersion);
        builder.addHeader(HEADER_USER_AGENT, getUserAgent());
        builder.addHeader("Content-Type", this.contentType);
        builder.addHeader(HEADER_REQUEST_ID, str);
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> buildQueryParameters() {
        return MapsKt.emptyMap();
    }

    private final UploadStatus responseCodeToUploadStatus(int i) {
        if (i == 202) {
            return UploadStatus.SUCCESS;
        }
        if (i == 403) {
            return UploadStatus.INVALID_TOKEN_ERROR;
        }
        if (i == 408) {
            return UploadStatus.HTTP_CLIENT_RATE_LIMITING;
        }
        if (i == 413) {
            return UploadStatus.HTTP_CLIENT_ERROR;
        }
        if (i == 429) {
            return UploadStatus.HTTP_CLIENT_RATE_LIMITING;
        }
        if (i == 500) {
            return UploadStatus.HTTP_SERVER_ERROR;
        }
        if (i == 503) {
            return UploadStatus.HTTP_SERVER_ERROR;
        }
        if (i == 400) {
            return UploadStatus.HTTP_CLIENT_ERROR;
        }
        if (i != 401) {
            return UploadStatus.UNKNOWN_ERROR;
        }
        return UploadStatus.INVALID_TOKEN_ERROR;
    }

    @Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001e"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/DataOkHttpUploaderV2$Companion;", "", "()V", "CONTENT_TYPE_JSON", "", "CONTENT_TYPE_TEXT_UTF8", "HEADER_API_KEY", "HEADER_CONTENT_TYPE", "HEADER_EVP_ORIGIN", "HEADER_EVP_ORIGIN_VERSION", "HEADER_REQUEST_ID", "HEADER_USER_AGENT", "HTTP_ACCEPTED", "", "HTTP_BAD_REQUEST", "HTTP_CLIENT_TIMEOUT", "HTTP_ENTITY_TOO_LARGE", "HTTP_FORBIDDEN", "HTTP_INTERNAL_ERROR", "HTTP_TOO_MANY_REQUESTS", "HTTP_UNAUTHORIZED", "HTTP_UNAVAILABLE", "QUERY_PARAM_SOURCE", "QUERY_PARAM_TAGS", "UPLOAD_URL", "buildUrl", "endpoint", "trackType", "Lcom/datadog/android/core/internal/net/DataOkHttpUploaderV2$TrackType;", "buildUrl$dd_sdk_android_release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DataOkHttpUploaderV2.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String buildUrl$dd_sdk_android_release(String str, TrackType trackType) {
            Intrinsics.checkNotNullParameter(str, "endpoint");
            Intrinsics.checkNotNullParameter(trackType, "trackType");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.US, DataOkHttpUploaderV2.UPLOAD_URL, Arrays.copyOf(new Object[]{str, trackType.getTrackName()}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            return format;
        }
    }
}
