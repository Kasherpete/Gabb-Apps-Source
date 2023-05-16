package com.datadog.opentracing.propagation;

import com.datadog.opentracing.DDSpanContext;
import com.datadog.opentracing.DDTracer;
import com.datadog.opentracing.StringCachingBigInteger;
import com.datadog.opentracing.propagation.B3HttpCodec;
import com.datadog.opentracing.propagation.DatadogHttpCodec;
import com.datadog.opentracing.propagation.HaystackHttpCodec;
import com.datadog.trace.api.Config;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p006io.opentracing.SpanContext;
import p006io.opentracing.propagation.TextMapExtract;
import p006io.opentracing.propagation.TextMapInject;

public class HttpCodec {

    public interface Extractor {
        SpanContext extract(TextMapExtract textMapExtract);
    }

    public interface Injector {
        void inject(DDSpanContext dDSpanContext, TextMapInject textMapInject);
    }

    public static Injector createInjector(Config config) {
        ArrayList arrayList = new ArrayList();
        for (Config.PropagationStyle next : config.getPropagationStylesToInject()) {
            if (next == Config.PropagationStyle.DATADOG) {
                arrayList.add(new DatadogHttpCodec.Injector());
            } else if (next == Config.PropagationStyle.B3) {
                arrayList.add(new B3HttpCodec.Injector());
            } else if (next == Config.PropagationStyle.HAYSTACK) {
                arrayList.add(new HaystackHttpCodec.Injector());
            }
        }
        return new CompoundInjector(arrayList);
    }

    public static Extractor createExtractor(Config config, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        for (Config.PropagationStyle next : config.getPropagationStylesToExtract()) {
            if (next == Config.PropagationStyle.DATADOG) {
                arrayList.add(new DatadogHttpCodec.Extractor(map));
            } else if (next == Config.PropagationStyle.B3) {
                arrayList.add(new B3HttpCodec.Extractor(map));
            } else if (next == Config.PropagationStyle.HAYSTACK) {
                arrayList.add(new HaystackHttpCodec.Extractor(map));
            }
        }
        return new CompoundExtractor(arrayList);
    }

    public static class CompoundInjector implements Injector {
        private final List<Injector> injectors;

        public CompoundInjector(List<Injector> list) {
            this.injectors = list;
        }

        public void inject(DDSpanContext dDSpanContext, TextMapInject textMapInject) {
            for (Injector inject : this.injectors) {
                inject.inject(dDSpanContext, textMapInject);
            }
        }
    }

    public static class CompoundExtractor implements Extractor {
        private final List<Extractor> extractors;

        public CompoundExtractor(List<Extractor> list) {
            this.extractors = list;
        }

        public SpanContext extract(TextMapExtract textMapExtract) {
            Iterator<Extractor> it = this.extractors.iterator();
            SpanContext spanContext = null;
            while (it.hasNext() && ((spanContext = it.next().extract(textMapExtract)) == null || !(spanContext instanceof ExtractedContext))) {
            }
            return spanContext;
        }
    }

    static BigInteger validateUInt64BitsID(String str, int i) throws IllegalArgumentException {
        StringCachingBigInteger stringCachingBigInteger = new StringCachingBigInteger(str, i);
        if (stringCachingBigInteger.compareTo(DDTracer.TRACE_ID_MIN) >= 0 && stringCachingBigInteger.compareTo(DDTracer.TRACE_ID_MAX) <= 0) {
            return stringCachingBigInteger;
        }
        throw new IllegalArgumentException("ID out of range, must be between 0 and 2^64-1, got: " + str);
    }

    static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    static String decode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }
}
