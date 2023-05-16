package com.datadog.android;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.net.FirstPartyHostDetector;
import com.datadog.android.core.internal.net.RequestUniqueIdentifierKt;
import com.datadog.android.core.internal.sampling.RateBasedSampler;
import com.datadog.android.core.internal.sampling.Sampler;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.NoOpRumResourceAttributesProvider;
import com.datadog.android.rum.RumAttributes;
import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.RumResourceAttributesProvider;
import com.datadog.android.rum.RumResourceKind;
import com.datadog.android.rum.internal.RumFeature;
import com.datadog.android.tracing.NoOpTracedRequestListener;
import com.datadog.android.tracing.TracedRequestListener;
import com.datadog.android.tracing.TracingInterceptor;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import p006io.opentracing.Span;
import p006io.opentracing.Tracer;

@Metadata(mo20734d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 -2\u00020\u0001:\u0001-B3\b\u0017\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0003\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB%\b\u0017\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0003\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\fBC\b\u0000\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0002\u0010\u0015J\r\u0010\u0018\u001a\u00020\u0019H\u0010¢\u0006\u0002\b\u001aJ\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001fJ\"\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\u0018\u0010&\u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020+H\u0016J.\u0010,\u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006."}, mo20735d2 = {"Lcom/datadog/android/DatadogInterceptor;", "Lcom/datadog/android/tracing/TracingInterceptor;", "firstPartyHosts", "", "", "tracedRequestListener", "Lcom/datadog/android/tracing/TracedRequestListener;", "rumResourceAttributesProvider", "Lcom/datadog/android/rum/RumResourceAttributesProvider;", "traceSamplingRate", "", "(Ljava/util/List;Lcom/datadog/android/tracing/TracedRequestListener;Lcom/datadog/android/rum/RumResourceAttributesProvider;F)V", "(Lcom/datadog/android/tracing/TracedRequestListener;Lcom/datadog/android/rum/RumResourceAttributesProvider;F)V", "tracedHosts", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "traceSampler", "Lcom/datadog/android/core/internal/sampling/Sampler;", "localTracerFactory", "Lkotlin/Function0;", "Lio/opentracing/Tracer;", "(Ljava/util/List;Lcom/datadog/android/tracing/TracedRequestListener;Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;Lcom/datadog/android/rum/RumResourceAttributesProvider;Lcom/datadog/android/core/internal/sampling/Sampler;Lkotlin/jvm/functions/Function0;)V", "getRumResourceAttributesProvider$dd_sdk_android_release", "()Lcom/datadog/android/rum/RumResourceAttributesProvider;", "canSendSpan", "", "canSendSpan$dd_sdk_android_release", "getBodyLength", "", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)Ljava/lang/Long;", "handleResponse", "", "request", "Lokhttp3/Request;", "span", "Lio/opentracing/Span;", "handleThrowable", "throwable", "", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "onRequestIntercepted", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogInterceptor.kt */
public class DatadogInterceptor extends TracingInterceptor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ERROR_MSG_FORMAT = "OkHttp request error %s %s";
    public static final String ERROR_NO_RESPONSE = "The request ended with no response nor any exception.";
    private static final long MAX_BODY_PEEK = 33554432;
    public static final String ORIGIN_RUM = "rum";
    public static final String WARN_RUM_DISABLED = "You set up a DatadogInterceptor, but RUM features are disabled.Make sure you initialized the Datadog SDK with a valid Application Id, and that RUM features are enabled.";
    private final RumResourceAttributesProvider rumResourceAttributesProvider;

    public DatadogInterceptor() {
        this((TracedRequestListener) null, (RumResourceAttributesProvider) null, 0.0f, 7, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DatadogInterceptor(TracedRequestListener tracedRequestListener) {
        this(tracedRequestListener, (RumResourceAttributesProvider) null, 0.0f, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(tracedRequestListener, "tracedRequestListener");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DatadogInterceptor(TracedRequestListener tracedRequestListener, RumResourceAttributesProvider rumResourceAttributesProvider2) {
        this(tracedRequestListener, rumResourceAttributesProvider2, 0.0f, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(tracedRequestListener, "tracedRequestListener");
        Intrinsics.checkNotNullParameter(rumResourceAttributesProvider2, "rumResourceAttributesProvider");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DatadogInterceptor(List<String> list) {
        this((List) list, (TracedRequestListener) null, (RumResourceAttributesProvider) null, 0.0f, 14, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "firstPartyHosts");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DatadogInterceptor(List<String> list, TracedRequestListener tracedRequestListener) {
        this((List) list, tracedRequestListener, (RumResourceAttributesProvider) null, 0.0f, 12, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "firstPartyHosts");
        Intrinsics.checkNotNullParameter(tracedRequestListener, "tracedRequestListener");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DatadogInterceptor(List<String> list, TracedRequestListener tracedRequestListener, RumResourceAttributesProvider rumResourceAttributesProvider2) {
        this((List) list, tracedRequestListener, rumResourceAttributesProvider2, 0.0f, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "firstPartyHosts");
        Intrinsics.checkNotNullParameter(tracedRequestListener, "tracedRequestListener");
        Intrinsics.checkNotNullParameter(rumResourceAttributesProvider2, "rumResourceAttributesProvider");
    }

    public final RumResourceAttributesProvider getRumResourceAttributesProvider$dd_sdk_android_release() {
        return this.rumResourceAttributesProvider;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DatadogInterceptor(List<String> list, TracedRequestListener tracedRequestListener, FirstPartyHostDetector firstPartyHostDetector, RumResourceAttributesProvider rumResourceAttributesProvider2, Sampler sampler, Function0<? extends Tracer> function0) {
        super(list, tracedRequestListener, firstPartyHostDetector, "rum", sampler, function0);
        Intrinsics.checkNotNullParameter(list, "tracedHosts");
        Intrinsics.checkNotNullParameter(tracedRequestListener, "tracedRequestListener");
        Intrinsics.checkNotNullParameter(firstPartyHostDetector, "firstPartyHostDetector");
        Intrinsics.checkNotNullParameter(rumResourceAttributesProvider2, "rumResourceAttributesProvider");
        Intrinsics.checkNotNullParameter(sampler, "traceSampler");
        Intrinsics.checkNotNullParameter(function0, "localTracerFactory");
        this.rumResourceAttributesProvider = rumResourceAttributesProvider2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DatadogInterceptor(List list, TracedRequestListener tracedRequestListener, RumResourceAttributesProvider rumResourceAttributesProvider2, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? new NoOpTracedRequestListener() : tracedRequestListener, (i & 4) != 0 ? new NoOpRumResourceAttributesProvider() : rumResourceAttributesProvider2, (i & 8) != 0 ? 20.0f : f);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DatadogInterceptor(List<String> list, TracedRequestListener tracedRequestListener, RumResourceAttributesProvider rumResourceAttributesProvider2, float f) {
        this(list, tracedRequestListener, CoreFeature.INSTANCE.getFirstPartyHostDetector$dd_sdk_android_release(), rumResourceAttributesProvider2, (Sampler) new RateBasedSampler(f / ((float) 100)), (Function0<? extends Tracer>) C08521.INSTANCE);
        Intrinsics.checkNotNullParameter(list, "firstPartyHosts");
        Intrinsics.checkNotNullParameter(tracedRequestListener, "tracedRequestListener");
        Intrinsics.checkNotNullParameter(rumResourceAttributesProvider2, "rumResourceAttributesProvider");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DatadogInterceptor(TracedRequestListener tracedRequestListener, RumResourceAttributesProvider rumResourceAttributesProvider2, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new NoOpTracedRequestListener() : tracedRequestListener, (i & 2) != 0 ? new NoOpRumResourceAttributesProvider() : rumResourceAttributesProvider2, (i & 4) != 0 ? 20.0f : f);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DatadogInterceptor(TracedRequestListener tracedRequestListener, RumResourceAttributesProvider rumResourceAttributesProvider2, float f) {
        this((List<String>) CollectionsKt.emptyList(), tracedRequestListener, CoreFeature.INSTANCE.getFirstPartyHostDetector$dd_sdk_android_release(), rumResourceAttributesProvider2, (Sampler) new RateBasedSampler(f / ((float) 100)), (Function0<? extends Tracer>) C08532.INSTANCE);
        Intrinsics.checkNotNullParameter(tracedRequestListener, "tracedRequestListener");
        Intrinsics.checkNotNullParameter(rumResourceAttributesProvider2, "rumResourceAttributesProvider");
    }

    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        if (RumFeature.INSTANCE.isInitialized()) {
            Request request = chain.request();
            String httpUrl = request.url().toString();
            Intrinsics.checkNotNullExpressionValue(httpUrl, "request.url().toString()");
            String method = request.method();
            Intrinsics.checkNotNullExpressionValue(request, "request");
            String identifyRequest = RequestUniqueIdentifierKt.identifyRequest(request);
            RumMonitor rumMonitor = GlobalRum.get();
            Intrinsics.checkNotNullExpressionValue(method, "method");
            RumMonitor.DefaultImpls.startResource$default(rumMonitor, identifyRequest, method, httpUrl, (Map) null, 8, (Object) null);
        } else {
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), WARN_RUM_DISABLED, (Throwable) null, (Map) null, 6, (Object) null);
        }
        return super.intercept(chain);
    }

    /* access modifiers changed from: protected */
    public void onRequestIntercepted(Request request, Span span, Response response, Throwable th) {
        Intrinsics.checkNotNullParameter(request, "request");
        super.onRequestIntercepted(request, span, response, th);
        if (!RumFeature.INSTANCE.isInitialized()) {
            return;
        }
        if (response != null) {
            handleResponse(request, response, span);
            return;
        }
        if (th == null) {
            th = new IllegalStateException(ERROR_NO_RESPONSE);
        }
        handleThrowable(request, th);
    }

    public boolean canSendSpan$dd_sdk_android_release() {
        return !RumFeature.INSTANCE.isInitialized();
    }

    private final void handleResponse(Request request, Response response, Span span) {
        RumResourceKind rumResourceKind;
        Map map;
        String identifyRequest = RequestUniqueIdentifierKt.identifyRequest(request);
        int code = response.code();
        String header = response.header("Content-Type");
        if (header == null) {
            rumResourceKind = RumResourceKind.NATIVE;
        } else {
            rumResourceKind = RumResourceKind.Companion.fromMimeType$dd_sdk_android_release(header);
        }
        RumResourceKind rumResourceKind2 = rumResourceKind;
        if (span == null) {
            map = MapsKt.emptyMap();
        } else {
            map = MapsKt.mapOf(TuplesKt.m78to(RumAttributes.TRACE_ID, span.context().toTraceId()), TuplesKt.m78to(RumAttributes.SPAN_ID, span.context().toSpanId()));
        }
        GlobalRum.get().stopResource(identifyRequest, Integer.valueOf(code), getBodyLength(response), rumResourceKind2, MapsKt.plus(map, (Map) this.rumResourceAttributesProvider.onProvideAttributes(request, response, (Throwable) null)));
    }

    private final void handleThrowable(Request request, Throwable th) {
        String identifyRequest = RequestUniqueIdentifierKt.identifyRequest(request);
        String method = request.method();
        String httpUrl = request.url().toString();
        Intrinsics.checkNotNullExpressionValue(httpUrl, "request.url().toString()");
        RumMonitor rumMonitor = GlobalRum.get();
        String format = String.format(Locale.US, ERROR_MSG_FORMAT, Arrays.copyOf(new Object[]{method, httpUrl}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
        rumMonitor.stopResourceWithError(identifyRequest, (Integer) null, format, RumErrorSource.NETWORK, th, this.rumResourceAttributesProvider.onProvideAttributes(request, (Response) null, th));
    }

    private final Long getBodyLength(Response response) {
        try {
            long contentLength = response.peekBody(MAX_BODY_PEEK).contentLength();
            if (contentLength == 0) {
                return null;
            }
            return Long.valueOf(contentLength);
        } catch (IOException e) {
            Logger.e$default(RuntimeUtilsKt.getSdkLogger(), "Unable to peek response body", e, (Map) null, 4, (Object) null);
            return null;
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/DatadogInterceptor$Companion;", "", "()V", "ERROR_MSG_FORMAT", "", "ERROR_NO_RESPONSE", "MAX_BODY_PEEK", "", "ORIGIN_RUM", "WARN_RUM_DISABLED", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DatadogInterceptor.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
