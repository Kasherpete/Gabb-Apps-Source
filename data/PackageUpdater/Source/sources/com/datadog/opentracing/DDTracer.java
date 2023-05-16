package com.datadog.opentracing;

import com.datadog.android.tracing.TracingInterceptor;
import com.datadog.opentracing.decorators.AbstractDecorator;
import com.datadog.opentracing.decorators.DDDecoratorsFactory;
import com.datadog.opentracing.jfr.DDNoopScopeEventFactory;
import com.datadog.opentracing.jfr.DDScopeEventFactory;
import com.datadog.opentracing.propagation.ExtractedContext;
import com.datadog.opentracing.propagation.HttpCodec;
import com.datadog.opentracing.propagation.TagContext;
import com.datadog.opentracing.scopemanager.ContextualScopeManager;
import com.datadog.opentracing.scopemanager.ScopeContext;
import com.datadog.trace.api.Config;
import com.datadog.trace.api.interceptor.TraceInterceptor;
import com.datadog.trace.common.sampling.PrioritySampler;
import com.datadog.trace.common.sampling.Sampler;
import com.datadog.trace.common.writer.LoggingWriter;
import com.datadog.trace.common.writer.Writer;
import com.datadog.trace.context.ScopeListener;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import p006io.opentracing.References;
import p006io.opentracing.Scope;
import p006io.opentracing.ScopeManager;
import p006io.opentracing.Span;
import p006io.opentracing.SpanContext;
import p006io.opentracing.Tracer;
import p006io.opentracing.propagation.Format;
import p006io.opentracing.propagation.TextMapExtract;
import p006io.opentracing.propagation.TextMapInject;
import p006io.opentracing.tag.Tag;

public class DDTracer implements Tracer, Closeable, com.datadog.trace.api.Tracer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final BigInteger TRACE_ID_MAX = BigInteger.valueOf(2).pow(64).subtract(BigInteger.ONE);
    public static final BigInteger TRACE_ID_MIN = BigInteger.ZERO;
    /* access modifiers changed from: private */
    public final Map<String, String> defaultSpanTags;
    private final HttpCodec.Extractor extractor;
    private final HttpCodec.Injector injector;
    private final SortedSet<TraceInterceptor> interceptors;
    /* access modifiers changed from: private */
    public final Map<String, String> localRootSpanTags;
    private final int partialFlushMinSpans;
    /* access modifiers changed from: private */
    public final Random random;
    final Sampler sampler;
    final ScopeManager scopeManager;
    final String serviceName;
    /* access modifiers changed from: private */
    public final Map<String, String> serviceNameMappings;
    private final Thread shutdownCallback;
    private final Map<String, List<AbstractDecorator>> spanContextDecorators;
    final Writer writer;

    protected DDTracer(Config config, Writer writer2, Random random2) {
        this(config.getServiceName(), writer2, Sampler.Builder.forConfig(config), HttpCodec.createInjector(Config.get()), HttpCodec.createExtractor(Config.get(), config.getHeaderTags()), new ContextualScopeManager(Config.get().getScopeDepthLimit().intValue(), createScopeEventFactory()), random2, config.getLocalRootSpanTags(), config.getMergedSpanTags(), config.getServiceMapping(), config.getHeaderTags(), config.getPartialFlushMinSpans().intValue());
    }

    private DDTracer(String str, Writer writer2, Sampler sampler2, HttpCodec.Injector injector2, HttpCodec.Extractor extractor2, ScopeManager scopeManager2, Random random2, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, int i) {
        this.spanContextDecorators = new ConcurrentHashMap();
        this.interceptors = new ConcurrentSkipListSet(new Comparator<TraceInterceptor>() {
            public int compare(TraceInterceptor traceInterceptor, TraceInterceptor traceInterceptor2) {
                return Integer.compare(traceInterceptor.priority(), traceInterceptor2.priority());
            }
        });
        this.random = random2;
        this.serviceName = str;
        if (writer2 == null) {
            this.writer = new LoggingWriter();
        } else {
            this.writer = writer2;
        }
        this.sampler = sampler2;
        this.injector = injector2;
        this.extractor = extractor2;
        this.scopeManager = scopeManager2;
        this.localRootSpanTags = map;
        this.defaultSpanTags = map2;
        this.serviceNameMappings = map3;
        this.partialFlushMinSpans = i;
        this.writer.start();
        ShutdownHook shutdownHook = new ShutdownHook();
        this.shutdownCallback = shutdownHook;
        try {
            Runtime.getRuntime().addShutdownHook(shutdownHook);
        } catch (IllegalStateException unused) {
        }
        for (AbstractDecorator addDecorator : DDDecoratorsFactory.createBuiltinDecorators()) {
            addDecorator(addDecorator);
        }
        registerClassLoader(ClassLoader.getSystemClassLoader());
        PendingTrace.initialize();
    }

    public void finalize() {
        try {
            Runtime.getRuntime().removeShutdownHook(this.shutdownCallback);
            this.shutdownCallback.run();
        } catch (Exception unused) {
        }
    }

    public List<AbstractDecorator> getSpanContextDecorators(String str) {
        return this.spanContextDecorators.get(str);
    }

    public void addDecorator(AbstractDecorator abstractDecorator) {
        List list = this.spanContextDecorators.get(abstractDecorator.getMatchingTag());
        if (list == null) {
            list = new ArrayList();
        }
        list.add(abstractDecorator);
        this.spanContextDecorators.put(abstractDecorator.getMatchingTag(), list);
    }

    @Deprecated
    public void addScopeContext(ScopeContext scopeContext) {
        ScopeManager scopeManager2 = this.scopeManager;
        if (scopeManager2 instanceof ContextualScopeManager) {
            ((ContextualScopeManager) scopeManager2).addScopeContext(scopeContext);
        }
    }

    public void registerClassLoader(ClassLoader classLoader) {
        try {
            Iterator<S> it = ServiceLoader.load(TraceInterceptor.class, classLoader).iterator();
            while (it.hasNext()) {
                addTraceInterceptor((TraceInterceptor) it.next());
            }
        } catch (ServiceConfigurationError unused) {
        }
    }

    public ScopeManager scopeManager() {
        return this.scopeManager;
    }

    public Span activeSpan() {
        return this.scopeManager.activeSpan();
    }

    public Scope activateSpan(Span span) {
        return this.scopeManager.activate(span);
    }

    public Tracer.SpanBuilder buildSpan(String str) {
        return new DDSpanBuilder(str, this.scopeManager);
    }

    public <T> void inject(SpanContext spanContext, Format<T> format, T t) {
        if (t instanceof TextMapInject) {
            DDSpanContext dDSpanContext = (DDSpanContext) spanContext;
            setSamplingPriorityIfNecessary(dDSpanContext.getTrace().getRootSpan());
            this.injector.inject(dDSpanContext, (TextMapInject) t);
        }
    }

    public <T> SpanContext extract(Format<T> format, T t) {
        if (t instanceof TextMapExtract) {
            return this.extractor.extract((TextMapExtract) t);
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: com.datadog.opentracing.DDSpan} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(java.util.Collection<com.datadog.opentracing.DDSpan> r4) {
        /*
            r3 = this;
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            java.util.SortedSet<com.datadog.trace.api.interceptor.TraceInterceptor> r0 = r3.interceptors
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0015
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r4)
            goto L_0x0055
        L_0x0015:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r4)
            java.util.SortedSet<com.datadog.trace.api.interceptor.TraceInterceptor> r4 = r3.interceptors
            java.util.Iterator r4 = r4.iterator()
        L_0x0020:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0031
            java.lang.Object r1 = r4.next()
            com.datadog.trace.api.interceptor.TraceInterceptor r1 = (com.datadog.trace.api.interceptor.TraceInterceptor) r1
            java.util.Collection r0 = r1.onTraceComplete(r0)
            goto L_0x0020
        L_0x0031:
            java.util.ArrayList r4 = new java.util.ArrayList
            int r1 = r0.size()
            r4.<init>(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x003e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0054
            java.lang.Object r1 = r0.next()
            com.datadog.trace.api.interceptor.MutableSpan r1 = (com.datadog.trace.api.interceptor.MutableSpan) r1
            boolean r2 = r1 instanceof com.datadog.opentracing.DDSpan
            if (r2 == 0) goto L_0x003e
            com.datadog.opentracing.DDSpan r1 = (com.datadog.opentracing.DDSpan) r1
            r4.add(r1)
            goto L_0x003e
        L_0x0054:
            r0 = r4
        L_0x0055:
            r3.incrementTraceCount()
            boolean r4 = r0.isEmpty()
            if (r4 != 0) goto L_0x0084
            r4 = 0
            java.lang.Object r1 = r0.get(r4)
            com.datadog.opentracing.DDSpan r1 = (com.datadog.opentracing.DDSpan) r1
            com.datadog.trace.api.interceptor.MutableSpan r1 = r1.getLocalRootSpan()
            com.datadog.opentracing.DDSpan r1 = (com.datadog.opentracing.DDSpan) r1
            r3.setSamplingPriorityIfNecessary(r1)
            if (r1 != 0) goto L_0x0077
            java.lang.Object r4 = r0.get(r4)
            r1 = r4
            com.datadog.opentracing.DDSpan r1 = (com.datadog.opentracing.DDSpan) r1
        L_0x0077:
            com.datadog.trace.common.sampling.Sampler r4 = r3.sampler
            boolean r4 = r4.sample(r1)
            if (r4 == 0) goto L_0x0084
            com.datadog.trace.common.writer.Writer r3 = r3.writer
            r3.write(r0)
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.opentracing.DDTracer.write(java.util.Collection):void");
    }

    /* access modifiers changed from: package-private */
    public void setSamplingPriorityIfNecessary(DDSpan dDSpan) {
        if ((this.sampler instanceof PrioritySampler) && dDSpan != null && dDSpan.context().getSamplingPriority() == Integer.MIN_VALUE) {
            ((PrioritySampler) this.sampler).setSamplingPriority(dDSpan);
        }
    }

    /* access modifiers changed from: package-private */
    public void incrementTraceCount() {
        this.writer.incrementTraceCount();
    }

    public String getTraceId() {
        Span activeSpan = activeSpan();
        return activeSpan instanceof DDSpan ? ((DDSpan) activeSpan).getTraceId().toString() : TracingInterceptor.DROP_SAMPLING_DECISION;
    }

    public String getSpanId() {
        Span activeSpan = activeSpan();
        return activeSpan instanceof DDSpan ? ((DDSpan) activeSpan).getSpanId().toString() : TracingInterceptor.DROP_SAMPLING_DECISION;
    }

    public boolean addTraceInterceptor(TraceInterceptor traceInterceptor) {
        return this.interceptors.add(traceInterceptor);
    }

    public void addScopeListener(ScopeListener scopeListener) {
        ScopeManager scopeManager2 = this.scopeManager;
        if (scopeManager2 instanceof ContextualScopeManager) {
            ((ContextualScopeManager) scopeManager2).addScopeListener(scopeListener);
        }
    }

    public void close() {
        PendingTrace.close();
        this.writer.close();
    }

    public String toString() {
        return "DDTracer-" + Integer.toHexString(hashCode()) + "{ serviceName=" + this.serviceName + ", writer=" + this.writer + ", sampler=" + this.sampler + ", defaultSpanTags=" + this.defaultSpanTags + '}';
    }

    @Deprecated
    private static Map<String, String> customRuntimeTags(String str, Map<String, String> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put(Config.RUNTIME_ID_TAG, str);
        return Collections.unmodifiableMap(hashMap);
    }

    private static DDScopeEventFactory createScopeEventFactory() {
        try {
            return (DDScopeEventFactory) Class.forName("com.datadog.opentracing.jfr.openjdk.ScopeEventFactory").newInstance();
        } catch (ClassFormatError | NoClassDefFoundError | ReflectiveOperationException unused) {
            return new DDNoopScopeEventFactory();
        }
    }

    public class DDSpanBuilder implements Tracer.SpanBuilder {
        private boolean errorFlag;
        private boolean ignoreScope = false;
        private LogHandler logHandler = new DefaultLogHandler();
        private final String operationName;
        private String origin;
        private SpanContext parent;
        private String resourceName;
        private final ScopeManager scopeManager;
        private String serviceName;
        private String spanType;
        private final Map<String, Object> tags;
        private long timestampMicro;

        public DDSpanBuilder(String str, ScopeManager scopeManager2) {
            this.tags = new LinkedHashMap(DDTracer.this.defaultSpanTags);
            this.operationName = str;
            this.scopeManager = scopeManager2;
        }

        public Tracer.SpanBuilder ignoreActiveSpan() {
            this.ignoreScope = true;
            return this;
        }

        private Span startSpan() {
            return new DDSpan(this.timestampMicro, buildSpanContext(), this.logHandler);
        }

        public Scope startActive(boolean z) {
            return this.scopeManager.activate(startSpan(), z);
        }

        @Deprecated
        public Span startManual() {
            return start();
        }

        public Span start() {
            return startSpan();
        }

        public DDSpanBuilder withTag(String str, Number number) {
            return withTag(str, (Object) number);
        }

        public DDSpanBuilder withTag(String str, String str2) {
            return withTag(str, (Object) str2);
        }

        public DDSpanBuilder withTag(String str, boolean z) {
            return withTag(str, (Object) Boolean.valueOf(z));
        }

        public <T> Tracer.SpanBuilder withTag(Tag<T> tag, T t) {
            return withTag(tag.getKey(), (Object) t);
        }

        public DDSpanBuilder withStartTimestamp(long j) {
            this.timestampMicro = j;
            return this;
        }

        public DDSpanBuilder withServiceName(String str) {
            this.serviceName = str;
            return this;
        }

        public DDSpanBuilder withResourceName(String str) {
            this.resourceName = str;
            return this;
        }

        public DDSpanBuilder withErrorFlag() {
            this.errorFlag = true;
            return this;
        }

        public DDSpanBuilder withSpanType(String str) {
            this.spanType = str;
            return this;
        }

        public Iterable<Map.Entry<String, String>> baggageItems() {
            SpanContext spanContext = this.parent;
            if (spanContext == null) {
                return Collections.emptyList();
            }
            return spanContext.baggageItems();
        }

        public DDSpanBuilder withLogHandler(LogHandler logHandler2) {
            if (logHandler2 != null) {
                this.logHandler = logHandler2;
            }
            return this;
        }

        public DDSpanBuilder asChildOf(Span span) {
            return asChildOf(span == null ? null : span.context());
        }

        public DDSpanBuilder asChildOf(SpanContext spanContext) {
            this.parent = spanContext;
            return this;
        }

        public DDSpanBuilder addReference(String str, SpanContext spanContext) {
            if (spanContext == null) {
                return this;
            }
            if (!(spanContext instanceof ExtractedContext) && !(spanContext instanceof DDSpanContext)) {
                return this;
            }
            if (References.CHILD_OF.equals(str) || References.FOLLOWS_FROM.equals(str)) {
                return asChildOf(spanContext);
            }
            return this;
        }

        public DDSpanBuilder withOrigin(String str) {
            this.origin = str;
            return this;
        }

        private DDSpanBuilder withTag(String str, Object obj) {
            if (obj == null || ((obj instanceof String) && ((String) obj).isEmpty())) {
                this.tags.remove(str);
            } else {
                this.tags.put(str, obj);
            }
            return this;
        }

        private BigInteger generateNewId() {
            StringCachingBigInteger stringCachingBigInteger;
            do {
                synchronized (DDTracer.this.random) {
                    stringCachingBigInteger = new StringCachingBigInteger(63, DDTracer.this.random);
                }
            } while (stringCachingBigInteger.signum() == 0);
            return stringCachingBigInteger;
        }

        private DDSpanContext buildSpanContext() {
            PendingTrace pendingTrace;
            Map<String, String> map;
            String str;
            int i;
            BigInteger bigInteger;
            BigInteger bigInteger2;
            int i2;
            BigInteger bigInteger3;
            BigInteger bigInteger4;
            Map<String, String> map2;
            String str2;
            Span activeSpan;
            BigInteger generateNewId = generateNewId();
            SpanContext spanContext = this.parent;
            if (spanContext == null && !this.ignoreScope && (activeSpan = this.scopeManager.activeSpan()) != null) {
                spanContext = activeSpan.context();
            }
            if (spanContext instanceof DDSpanContext) {
                DDSpanContext dDSpanContext = (DDSpanContext) spanContext;
                bigInteger2 = dDSpanContext.getTraceId();
                BigInteger spanId = dDSpanContext.getSpanId();
                Map<String, String> baggageItems = dDSpanContext.getBaggageItems();
                PendingTrace trace = dDSpanContext.getTrace();
                if (this.serviceName == null) {
                    this.serviceName = dDSpanContext.getServiceName();
                }
                i = Integer.MIN_VALUE;
                bigInteger = spanId;
                map = baggageItems;
                pendingTrace = trace;
                str = null;
            } else {
                if (spanContext instanceof ExtractedContext) {
                    ExtractedContext extractedContext = (ExtractedContext) spanContext;
                    bigInteger4 = extractedContext.getTraceId();
                    bigInteger3 = extractedContext.getSpanId();
                    i2 = extractedContext.getSamplingPriority();
                    map2 = extractedContext.getBaggage();
                } else {
                    BigInteger generateNewId2 = generateNewId();
                    bigInteger3 = BigInteger.ZERO;
                    i2 = Integer.MIN_VALUE;
                    bigInteger4 = generateNewId2;
                    map2 = null;
                }
                if (spanContext instanceof TagContext) {
                    TagContext tagContext = (TagContext) spanContext;
                    this.tags.putAll(tagContext.getTags());
                    str2 = tagContext.getOrigin();
                } else {
                    str2 = this.origin;
                }
                this.tags.putAll(DDTracer.this.localRootSpanTags);
                PendingTrace pendingTrace2 = new PendingTrace(DDTracer.this, bigInteger4);
                str = str2;
                map = map2;
                bigInteger2 = bigInteger4;
                bigInteger = bigInteger3;
                i = i2;
                pendingTrace = pendingTrace2;
            }
            if (this.serviceName == null) {
                this.serviceName = DDTracer.this.serviceName;
            }
            String str3 = this.operationName;
            if (str3 == null) {
                str3 = this.resourceName;
            }
            String str4 = str3;
            String str5 = this.serviceName;
            String str6 = this.resourceName;
            boolean z = this.errorFlag;
            String str7 = this.spanType;
            Map<String, Object> map3 = this.tags;
            DDTracer dDTracer = DDTracer.this;
            DDSpanContext dDSpanContext2 = r1;
            DDSpanContext dDSpanContext3 = new DDSpanContext(bigInteger2, generateNewId, bigInteger, str5, str4, str6, i, str, map, z, str7, map3, pendingTrace, dDTracer, dDTracer.serviceNameMappings);
            for (Map.Entry next : this.tags.entrySet()) {
                if (next.getValue() == null) {
                    dDSpanContext2.setTag((String) next.getKey(), (Object) null);
                } else {
                    DDSpanContext dDSpanContext4 = dDSpanContext2;
                    boolean z2 = true;
                    List<AbstractDecorator> spanContextDecorators = DDTracer.this.getSpanContextDecorators((String) next.getKey());
                    if (spanContextDecorators != null) {
                        for (AbstractDecorator shouldSetTag : spanContextDecorators) {
                            try {
                                z2 &= shouldSetTag.shouldSetTag(dDSpanContext4, (String) next.getKey(), next.getValue());
                            } catch (Throwable unused) {
                            }
                        }
                    }
                    if (!z2) {
                        dDSpanContext4.setTag((String) next.getKey(), (Object) null);
                    }
                    dDSpanContext2 = dDSpanContext4;
                }
            }
            return dDSpanContext2;
        }
    }

    private static class ShutdownHook extends Thread {
        private final WeakReference<DDTracer> reference;

        private ShutdownHook(DDTracer dDTracer) {
            super("dd-tracer-shutdown-hook");
            this.reference = new WeakReference<>(dDTracer);
        }

        public void run() {
            DDTracer dDTracer = (DDTracer) this.reference.get();
            if (dDTracer != null) {
                dDTracer.close();
            }
        }
    }

    public int getPartialFlushMinSpans() {
        return this.partialFlushMinSpans;
    }
}
