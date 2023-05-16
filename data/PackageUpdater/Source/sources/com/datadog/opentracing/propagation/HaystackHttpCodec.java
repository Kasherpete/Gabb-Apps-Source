package com.datadog.opentracing.propagation;

import com.datadog.opentracing.DDSpanContext;
import com.datadog.opentracing.propagation.HttpCodec;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import p006io.opentracing.SpanContext;
import p006io.opentracing.propagation.TextMapExtract;
import p006io.opentracing.propagation.TextMapInject;

public class HaystackHttpCodec {
    private static final String OT_BAGGAGE_PREFIX = "Baggage-";
    private static final String PARENT_ID_KEY = "Parent_ID";
    private static final String SPAN_ID_KEY = "Span-ID";
    private static final String TRACE_ID_KEY = "Trace-ID";

    private HaystackHttpCodec() {
    }

    public static class Injector implements HttpCodec.Injector {
        public void inject(DDSpanContext dDSpanContext, TextMapInject textMapInject) {
            textMapInject.put(HaystackHttpCodec.TRACE_ID_KEY, dDSpanContext.getTraceId().toString());
            textMapInject.put(HaystackHttpCodec.SPAN_ID_KEY, dDSpanContext.getSpanId().toString());
            textMapInject.put(HaystackHttpCodec.PARENT_ID_KEY, dDSpanContext.getParentId().toString());
            for (Map.Entry next : dDSpanContext.baggageItems()) {
                textMapInject.put(HaystackHttpCodec.OT_BAGGAGE_PREFIX + ((String) next.getKey()), HttpCodec.encode((String) next.getValue()));
            }
        }
    }

    public static class Extractor implements HttpCodec.Extractor {
        private final Map<String, String> taggedHeaders = new HashMap();

        public Extractor(Map<String, String> map) {
            for (Map.Entry next : map.entrySet()) {
                this.taggedHeaders.put(((String) next.getKey()).trim().toLowerCase(Locale.US), (String) next.getValue());
            }
        }

        public SpanContext extract(TextMapExtract textMapExtract) {
            try {
                Map emptyMap = Collections.emptyMap();
                Map emptyMap2 = Collections.emptyMap();
                BigInteger bigInteger = BigInteger.ZERO;
                BigInteger bigInteger2 = BigInteger.ZERO;
                Map map = emptyMap;
                Map map2 = emptyMap2;
                BigInteger bigInteger3 = bigInteger;
                BigInteger bigInteger4 = bigInteger2;
                for (Map.Entry next : textMapExtract) {
                    String lowerCase = ((String) next.getKey()).toLowerCase(Locale.US);
                    String str = (String) next.getValue();
                    if (str != null) {
                        if (HaystackHttpCodec.TRACE_ID_KEY.equalsIgnoreCase(lowerCase)) {
                            bigInteger3 = HttpCodec.validateUInt64BitsID(str, 10);
                        } else if (HaystackHttpCodec.SPAN_ID_KEY.equalsIgnoreCase(lowerCase)) {
                            bigInteger4 = HttpCodec.validateUInt64BitsID(str, 10);
                        } else if (lowerCase.startsWith(HaystackHttpCodec.OT_BAGGAGE_PREFIX.toLowerCase(Locale.US))) {
                            if (map.isEmpty()) {
                                map = new HashMap();
                            }
                            map.put(lowerCase.replace(HaystackHttpCodec.OT_BAGGAGE_PREFIX.toLowerCase(Locale.US), ""), HttpCodec.decode(str));
                        }
                        if (this.taggedHeaders.containsKey(lowerCase)) {
                            if (map2.isEmpty()) {
                                map2 = new HashMap();
                            }
                            map2.put(this.taggedHeaders.get(lowerCase), HttpCodec.decode(str));
                        }
                    }
                }
                if (!BigInteger.ZERO.equals(bigInteger3)) {
                    ExtractedContext extractedContext = new ExtractedContext(bigInteger3, bigInteger4, 1, (String) null, map, map2);
                    extractedContext.lockSamplingPriority();
                    return extractedContext;
                }
                if (!map2.isEmpty()) {
                    return new TagContext((String) null, map2);
                }
                return null;
            } catch (RuntimeException unused) {
            }
        }
    }
}
