package com.datadog.android.tracing;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.net.FirstPartyHostDetector;
import com.datadog.android.core.internal.sampling.RateBasedSampler;
import com.datadog.android.core.internal.sampling.Sampler;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.core.internal.utils.ThrowableExtKt;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.tracing.internal.TracingFeature;
import com.datadog.opentracing.DDTracer;
import com.datadog.trace.api.DDTags;
import com.datadog.trace.api.interceptor.MutableSpan;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import p006io.opentracing.Span;
import p006io.opentracing.SpanContext;
import p006io.opentracing.Tracer;
import p006io.opentracing.propagation.Format;
import p006io.opentracing.propagation.TextMapExtract;
import p006io.opentracing.propagation.TextMapExtractAdapter;
import p006io.opentracing.tag.Tags;
import p006io.opentracing.util.GlobalTracer;

@Metadata(mo20734d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 A2\u00020\u0001:\u0001AB)\b\u0017\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0003\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u001b\b\u0017\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0003\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\nBE\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\u0010\u0013J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0002J\r\u0010(\u001a\u00020)H\u0010¢\u0006\u0002\b*J\u001a\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0002J\u0017\u0010-\u001a\u0004\u0018\u00010)2\u0006\u0010&\u001a\u00020'H\u0002¢\u0006\u0002\u0010.J\"\u0010/\u001a\u0002002\u0006\u0010&\u001a\u00020'2\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010$H\u0002J\"\u00104\u001a\u0002002\u0006\u0010&\u001a\u00020'2\u0006\u00105\u001a\u0002062\b\u00103\u001a\u0004\u0018\u00010$H\u0002J\u0010\u00107\u001a\u0002022\u0006\u00108\u001a\u000209H\u0016J\u0018\u00107\u001a\u0002022\u0006\u00108\u001a\u0002092\u0006\u0010&\u001a\u00020'H\u0002J \u0010:\u001a\u0002022\u0006\u00108\u001a\u0002092\u0006\u0010&\u001a\u00020'2\u0006\u0010%\u001a\u00020\u0012H\u0002J\u0010\u0010;\u001a\u00020)2\u0006\u0010&\u001a\u00020'H\u0002J.\u0010<\u001a\u0002002\u0006\u0010&\u001a\u00020'2\b\u00103\u001a\u0004\u0018\u00010$2\b\u00101\u001a\u0004\u0018\u0001022\b\u00105\u001a\u0004\u0018\u000106H\u0014J\b\u0010=\u001a\u00020\u0012H\u0002J\n\u0010>\u001a\u0004\u0018\u00010\u0012H\u0002J\"\u0010?\u001a\u00020@2\u0006\u0010&\u001a\u00020'2\u0006\u0010%\u001a\u00020\u00122\b\u00103\u001a\u0004\u0018\u00010$H\u0002R\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u001aX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006B"}, mo20735d2 = {"Lcom/datadog/android/tracing/TracingInterceptor;", "Lokhttp3/Interceptor;", "tracedHosts", "", "", "tracedRequestListener", "Lcom/datadog/android/tracing/TracedRequestListener;", "traceSamplingRate", "", "(Ljava/util/List;Lcom/datadog/android/tracing/TracedRequestListener;F)V", "(Lcom/datadog/android/tracing/TracedRequestListener;F)V", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "traceOrigin", "traceSampler", "Lcom/datadog/android/core/internal/sampling/Sampler;", "localTracerFactory", "Lkotlin/Function0;", "Lio/opentracing/Tracer;", "(Ljava/util/List;Lcom/datadog/android/tracing/TracedRequestListener;Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;Ljava/lang/String;Lcom/datadog/android/core/internal/sampling/Sampler;Lkotlin/jvm/functions/Function0;)V", "getFirstPartyHostDetector$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "localFirstPartyHostDetector", "getLocalTracerFactory$dd_sdk_android_release", "()Lkotlin/jvm/functions/Function0;", "localTracerReference", "Ljava/util/concurrent/atomic/AtomicReference;", "getTraceOrigin$dd_sdk_android_release", "()Ljava/lang/String;", "getTraceSampler$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/sampling/Sampler;", "getTracedHosts$dd_sdk_android_release", "()Ljava/util/List;", "getTracedRequestListener$dd_sdk_android_release", "()Lcom/datadog/android/tracing/TracedRequestListener;", "buildSpan", "Lio/opentracing/Span;", "tracer", "request", "Lokhttp3/Request;", "canSendSpan", "", "canSendSpan$dd_sdk_android_release", "extractParentContext", "Lio/opentracing/SpanContext;", "extractSamplingDecision", "(Lokhttp3/Request;)Ljava/lang/Boolean;", "handleResponse", "", "response", "Lokhttp3/Response;", "span", "handleThrowable", "throwable", "", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "interceptAndTrace", "isRequestTraceable", "onRequestIntercepted", "resolveLocalTracer", "resolveTracer", "updateRequest", "Lokhttp3/Request$Builder;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TracingInterceptor.kt */
public class TracingInterceptor implements Interceptor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final float DEFAULT_TRACE_SAMPLING_RATE = 20.0f;
    public static final String DROP_SAMPLING_DECISION = "0";
    public static final String HEADER_CT = "Content-Type";
    public static final String RESOURCE_NAME_404 = "404";
    public static final String SAMPLING_PRIORITY_HEADER = "x-datadog-sampling-priority";
    public static final String SPAN_ID_HEADER = "x-datadog-parent-id";
    public static final String SPAN_NAME = "okhttp.request";
    public static final String TRACE_ID_HEADER = "x-datadog-trace-id";
    public static final char URL_QUERY_PARAMS_BLOCK_SEPARATOR = '?';
    public static final String WARNING_DEFAULT_TRACER = "You added a TracingInterceptor to your OkHttpClient, but you didn't register any Tracer. We automatically created a local tracer for you.";
    public static final String WARNING_TRACING_DISABLED = "You added a TracingInterceptor to your OkHttpClient, but you did not enable the TracingFeature. Your requests won't be traced.";
    public static final String WARNING_TRACING_NO_HOSTS = "You added a TracingInterceptor to your OkHttpClient, but you did not specify any first party hosts. Your requests won't be traced.\nTo set a list of known hosts, you can use the Configuration.Builder::setFirstPartyHosts() method.";
    private final FirstPartyHostDetector firstPartyHostDetector;
    private final FirstPartyHostDetector localFirstPartyHostDetector;
    private final Function0<Tracer> localTracerFactory;
    private final AtomicReference<Tracer> localTracerReference;
    private final String traceOrigin;
    private final Sampler traceSampler;
    private final List<String> tracedHosts;
    private final TracedRequestListener tracedRequestListener;

    public TracingInterceptor() {
        this((TracedRequestListener) null, 0.0f, 3, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TracingInterceptor(TracedRequestListener tracedRequestListener2) {
        this(tracedRequestListener2, 0.0f, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(tracedRequestListener2, "tracedRequestListener");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TracingInterceptor(List<String> list) {
        this(list, (TracedRequestListener) null, 0.0f, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "tracedHosts");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TracingInterceptor(List<String> list, TracedRequestListener tracedRequestListener2) {
        this(list, tracedRequestListener2, 0.0f, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "tracedHosts");
        Intrinsics.checkNotNullParameter(tracedRequestListener2, "tracedRequestListener");
    }

    public boolean canSendSpan$dd_sdk_android_release() {
        return true;
    }

    public TracingInterceptor(List<String> list, TracedRequestListener tracedRequestListener2, FirstPartyHostDetector firstPartyHostDetector2, String str, Sampler sampler, Function0<? extends Tracer> function0) {
        Intrinsics.checkNotNullParameter(list, "tracedHosts");
        Intrinsics.checkNotNullParameter(tracedRequestListener2, "tracedRequestListener");
        Intrinsics.checkNotNullParameter(firstPartyHostDetector2, "firstPartyHostDetector");
        Intrinsics.checkNotNullParameter(sampler, "traceSampler");
        Intrinsics.checkNotNullParameter(function0, "localTracerFactory");
        this.tracedHosts = list;
        this.tracedRequestListener = tracedRequestListener2;
        this.firstPartyHostDetector = firstPartyHostDetector2;
        this.traceOrigin = str;
        this.traceSampler = sampler;
        this.localTracerFactory = function0;
        this.localTracerReference = new AtomicReference<>();
        FirstPartyHostDetector firstPartyHostDetector3 = new FirstPartyHostDetector(list);
        this.localFirstPartyHostDetector = firstPartyHostDetector3;
        if (firstPartyHostDetector3.isEmpty() && firstPartyHostDetector2.isEmpty()) {
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), WARNING_TRACING_NO_HOSTS, (Throwable) null, (Map) null, 6, (Object) null);
        }
    }

    public final List<String> getTracedHosts$dd_sdk_android_release() {
        return this.tracedHosts;
    }

    public final TracedRequestListener getTracedRequestListener$dd_sdk_android_release() {
        return this.tracedRequestListener;
    }

    public final FirstPartyHostDetector getFirstPartyHostDetector$dd_sdk_android_release() {
        return this.firstPartyHostDetector;
    }

    public final String getTraceOrigin$dd_sdk_android_release() {
        return this.traceOrigin;
    }

    public final Sampler getTraceSampler$dd_sdk_android_release() {
        return this.traceSampler;
    }

    public final Function0<Tracer> getLocalTracerFactory$dd_sdk_android_release() {
        return this.localTracerFactory;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TracingInterceptor(List list, TracedRequestListener tracedRequestListener2, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? new NoOpTracedRequestListener() : tracedRequestListener2, (i & 4) != 0 ? 20.0f : f);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TracingInterceptor(List<String> list, TracedRequestListener tracedRequestListener2, float f) {
        this(list, tracedRequestListener2, CoreFeature.INSTANCE.getFirstPartyHostDetector$dd_sdk_android_release(), (String) null, new RateBasedSampler(f / ((float) 100)), C08731.INSTANCE);
        Intrinsics.checkNotNullParameter(list, "tracedHosts");
        Intrinsics.checkNotNullParameter(tracedRequestListener2, "tracedRequestListener");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TracingInterceptor(TracedRequestListener tracedRequestListener2, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new NoOpTracedRequestListener() : tracedRequestListener2, (i & 2) != 0 ? 20.0f : f);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TracingInterceptor(TracedRequestListener tracedRequestListener2, float f) {
        this(CollectionsKt.emptyList(), tracedRequestListener2, CoreFeature.INSTANCE.getFirstPartyHostDetector$dd_sdk_android_release(), (String) null, new RateBasedSampler(f / ((float) 100)), C08742.INSTANCE);
        Intrinsics.checkNotNullParameter(tracedRequestListener2, "tracedRequestListener");
    }

    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Tracer resolveTracer = resolveTracer();
        Request request = chain.request();
        if (resolveTracer != null) {
            Intrinsics.checkNotNullExpressionValue(request, "request");
            if (isRequestTraceable(request)) {
                return interceptAndTrace(chain, request, resolveTracer);
            }
        }
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return intercept(chain, request);
    }

    /* access modifiers changed from: protected */
    public void onRequestIntercepted(Request request, Span span, Response response, Throwable th) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (span != null) {
            this.tracedRequestListener.onRequestIntercepted(request, span, response, th);
        }
    }

    private final boolean isRequestTraceable(Request request) {
        HttpUrl url = request.url();
        FirstPartyHostDetector firstPartyHostDetector2 = this.firstPartyHostDetector;
        Intrinsics.checkNotNullExpressionValue(url, "url");
        return firstPartyHostDetector2.isFirstPartyUrl(url) || this.localFirstPartyHostDetector.isFirstPartyUrl(url);
    }

    private final Response interceptAndTrace(Interceptor.Chain chain, Request request, Tracer tracer) {
        Span span;
        Request request2;
        Boolean extractSamplingDecision = extractSamplingDecision(request);
        if (extractSamplingDecision == null ? this.traceSampler.sample() : extractSamplingDecision.booleanValue()) {
            span = buildSpan(tracer, request);
        } else {
            span = null;
        }
        try {
            request2 = updateRequest(request, tracer, span).build();
        } catch (IllegalStateException e) {
            LogUtilsKt.warningWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Failed to update intercepted OkHttp request", e, (Map) null, 4, (Object) null);
            request2 = request;
        }
        try {
            Response proceed = chain.proceed(request2);
            Intrinsics.checkNotNullExpressionValue(proceed, "response");
            handleResponse(request, proceed, span);
            return proceed;
        } catch (Throwable th) {
            handleThrowable(request, th, span);
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    private final Response intercept(Interceptor.Chain chain, Request request) {
        try {
            Response proceed = chain.proceed(request);
            onRequestIntercepted(request, (Span) null, proceed, (Throwable) null);
            Intrinsics.checkNotNullExpressionValue(proceed, "response");
            return proceed;
        } catch (Throwable th) {
            onRequestIntercepted(request, (Span) null, (Response) null, th);
            throw th;
        }
    }

    private final synchronized Tracer resolveTracer() {
        Tracer tracer;
        if (!TracingFeature.INSTANCE.getInitialized$dd_sdk_android_release().get()) {
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), WARNING_TRACING_DISABLED, (Throwable) null, (Map) null, 6, (Object) null);
            tracer = null;
        } else if (GlobalTracer.isRegistered()) {
            this.localTracerReference.set((Object) null);
            tracer = GlobalTracer.get();
        } else {
            tracer = resolveLocalTracer();
        }
        return tracer;
    }

    private final Tracer resolveLocalTracer() {
        if (this.localTracerReference.get() == null) {
            this.localTracerReference.compareAndSet((Object) null, this.localTracerFactory.invoke());
            Logger.w$default(RuntimeUtilsKt.getDevLogger(), WARNING_DEFAULT_TRACER, (Throwable) null, (Map) null, 6, (Object) null);
        }
        Tracer tracer = this.localTracerReference.get();
        Intrinsics.checkNotNullExpressionValue(tracer, "localTracerReference.get()");
        return tracer;
    }

    private final Span buildSpan(Tracer tracer, Request request) {
        SpanContext extractParentContext = extractParentContext(tracer, request);
        String httpUrl = request.url().toString();
        Intrinsics.checkNotNullExpressionValue(httpUrl, "request.url().toString()");
        Tracer.SpanBuilder buildSpan = tracer.buildSpan(SPAN_NAME);
        DDTracer.DDSpanBuilder dDSpanBuilder = buildSpan instanceof DDTracer.DDSpanBuilder ? (DDTracer.DDSpanBuilder) buildSpan : null;
        if (dDSpanBuilder != null) {
            dDSpanBuilder.withOrigin(this.traceOrigin);
        }
        Span start = buildSpan.asChildOf(extractParentContext).start();
        MutableSpan mutableSpan = start instanceof MutableSpan ? (MutableSpan) start : null;
        if (mutableSpan != null) {
            mutableSpan.setResourceName(StringsKt.substringBefore$default(httpUrl, (char) URL_QUERY_PARAMS_BLOCK_SEPARATOR, (String) null, 2, (Object) null));
        }
        start.setTag(Tags.HTTP_URL.getKey(), httpUrl);
        start.setTag(Tags.HTTP_METHOD.getKey(), request.method());
        Intrinsics.checkNotNullExpressionValue(start, "span");
        return start;
    }

    private final Boolean extractSamplingDecision(Request request) {
        int intValue;
        String header = request.header(SAMPLING_PRIORITY_HEADER);
        Integer intOrNull = header == null ? null : StringsKt.toIntOrNull(header);
        if (intOrNull == null || (intValue = intOrNull.intValue()) == Integer.MIN_VALUE) {
            return null;
        }
        boolean z = true;
        if (!(intValue == 2 || intValue == 1)) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private final SpanContext extractParentContext(Tracer tracer, Request request) {
        Span span = (Span) request.tag(Span.class);
        SpanContext context = span == null ? null : span.context();
        Format<TextMapExtract> format = Format.Builtin.TEXT_MAP_EXTRACT;
        Map<String, List<String>> multimap = request.headers().toMultimap();
        Intrinsics.checkNotNullExpressionValue(multimap, "request.headers().toMultimap()");
        Collection arrayList = new ArrayList(multimap.size());
        for (Map.Entry next : multimap.entrySet()) {
            Object key = next.getKey();
            Object value = next.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "it.value");
            arrayList.add(TuplesKt.m78to(key, CollectionsKt.joinToString$default((Iterable) value, ";", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)));
        }
        SpanContext extract = tracer.extract(format, new TextMapExtractAdapter(MapsKt.toMap((List) arrayList)));
        return extract == null ? context : extract;
    }

    private final Request.Builder updateRequest(Request request, Tracer tracer, Span span) {
        Request.Builder newBuilder = request.newBuilder();
        if (span == null) {
            for (String removeHeader : CollectionsKt.listOf(SAMPLING_PRIORITY_HEADER, TRACE_ID_HEADER, SPAN_ID_HEADER)) {
                newBuilder.removeHeader(removeHeader);
            }
            newBuilder.addHeader(SAMPLING_PRIORITY_HEADER, DROP_SAMPLING_DECISION);
        } else {
            tracer.inject(span.context(), Format.Builtin.TEXT_MAP_INJECT, new TracingInterceptor$$ExternalSyntheticLambda0(newBuilder));
        }
        Intrinsics.checkNotNullExpressionValue(newBuilder, "tracedRequestBuilder");
        return newBuilder;
    }

    /* access modifiers changed from: private */
    /* renamed from: updateRequest$lambda-2  reason: not valid java name */
    public static final void m153updateRequest$lambda2(Request.Builder builder, String str, String str2) {
        builder.removeHeader(str);
        builder.addHeader(str, str2);
    }

    private final void handleResponse(Request request, Response response, Span span) {
        MutableSpan mutableSpan = null;
        if (span == null) {
            onRequestIntercepted(request, (Span) null, response, (Throwable) null);
            return;
        }
        int code = response.code();
        span.setTag(Tags.HTTP_STATUS.getKey(), (Number) Integer.valueOf(code));
        boolean z = false;
        if (400 <= code && code < 500) {
            z = true;
        }
        if (z) {
            MutableSpan mutableSpan2 = span instanceof MutableSpan ? (MutableSpan) span : null;
            if (mutableSpan2 != null) {
                mutableSpan2.setError(true);
            }
        }
        if (code == 404) {
            MutableSpan mutableSpan3 = span instanceof MutableSpan ? (MutableSpan) span : null;
            if (mutableSpan3 != null) {
                mutableSpan3.setResourceName(RESOURCE_NAME_404);
            }
        }
        onRequestIntercepted(request, span, response, (Throwable) null);
        if (canSendSpan$dd_sdk_android_release()) {
            span.finish();
            return;
        }
        if (span instanceof MutableSpan) {
            mutableSpan = (MutableSpan) span;
        }
        if (mutableSpan != null) {
            mutableSpan.drop();
        }
    }

    private final void handleThrowable(Request request, Throwable th, Span span) {
        MutableSpan mutableSpan = null;
        if (span == null) {
            onRequestIntercepted(request, (Span) null, (Response) null, th);
            return;
        }
        boolean z = span instanceof MutableSpan;
        MutableSpan mutableSpan2 = z ? (MutableSpan) span : null;
        if (mutableSpan2 != null) {
            mutableSpan2.setError(true);
        }
        span.setTag(DDTags.ERROR_MSG, th.getMessage());
        span.setTag(DDTags.ERROR_TYPE, th.getClass().getName());
        span.setTag("error.stack", ThrowableExtKt.loggableStackTrace(th));
        onRequestIntercepted(request, span, (Response) null, th);
        if (canSendSpan$dd_sdk_android_release()) {
            span.finish();
            return;
        }
        if (z) {
            mutableSpan = (MutableSpan) span;
        }
        if (mutableSpan != null) {
            mutableSpan.drop();
        }
    }

    @Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\f\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/tracing/TracingInterceptor$Companion;", "", "()V", "DEFAULT_TRACE_SAMPLING_RATE", "", "DROP_SAMPLING_DECISION", "", "HEADER_CT", "RESOURCE_NAME_404", "SAMPLING_PRIORITY_HEADER", "SPAN_ID_HEADER", "SPAN_NAME", "TRACE_ID_HEADER", "URL_QUERY_PARAMS_BLOCK_SEPARATOR", "", "WARNING_DEFAULT_TRACER", "WARNING_TRACING_DISABLED", "WARNING_TRACING_NO_HOSTS", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TracingInterceptor.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
