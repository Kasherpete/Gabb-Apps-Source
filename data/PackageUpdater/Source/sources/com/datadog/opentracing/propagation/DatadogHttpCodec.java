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

class DatadogHttpCodec {
    private static final String ORIGIN_KEY = "x-datadog-origin";
    private static final String OT_BAGGAGE_PREFIX = "ot-baggage-";
    private static final String SAMPLING_PRIORITY_KEY = "x-datadog-sampling-priority";
    private static final String SPAN_ID_KEY = "x-datadog-parent-id";
    private static final String TRACE_ID_KEY = "x-datadog-trace-id";

    private DatadogHttpCodec() {
    }

    public static class Injector implements HttpCodec.Injector {
        public void inject(DDSpanContext dDSpanContext, TextMapInject textMapInject) {
            textMapInject.put("x-datadog-trace-id", dDSpanContext.getTraceId().toString());
            textMapInject.put("x-datadog-parent-id", dDSpanContext.getSpanId().toString());
            String origin = dDSpanContext.getOrigin();
            if (origin != null) {
                textMapInject.put(DatadogHttpCodec.ORIGIN_KEY, origin);
            }
            for (Map.Entry next : dDSpanContext.baggageItems()) {
                textMapInject.put(DatadogHttpCodec.OT_BAGGAGE_PREFIX + ((String) next.getKey()), HttpCodec.encode((String) next.getValue()));
            }
            textMapInject.put("x-datadog-sampling-priority", "1");
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
                String str = null;
                Map map = emptyMap;
                Map map2 = emptyMap2;
                BigInteger bigInteger3 = bigInteger;
                BigInteger bigInteger4 = bigInteger2;
                int i = Integer.MIN_VALUE;
                for (Map.Entry next : textMapExtract) {
                    String lowerCase = ((String) next.getKey()).toLowerCase(Locale.US);
                    String str2 = (String) next.getValue();
                    if (str2 != null) {
                        if ("x-datadog-trace-id".equalsIgnoreCase(lowerCase)) {
                            bigInteger3 = HttpCodec.validateUInt64BitsID(str2, 10);
                        } else if ("x-datadog-parent-id".equalsIgnoreCase(lowerCase)) {
                            bigInteger4 = HttpCodec.validateUInt64BitsID(str2, 10);
                        } else if ("x-datadog-sampling-priority".equalsIgnoreCase(lowerCase)) {
                            i = Integer.parseInt(str2);
                        } else if (DatadogHttpCodec.ORIGIN_KEY.equalsIgnoreCase(lowerCase)) {
                            str = str2;
                        } else if (lowerCase.startsWith(DatadogHttpCodec.OT_BAGGAGE_PREFIX)) {
                            if (map.isEmpty()) {
                                map = new HashMap();
                            }
                            map.put(lowerCase.replace(DatadogHttpCodec.OT_BAGGAGE_PREFIX, ""), HttpCodec.decode(str2));
                        }
                        if (this.taggedHeaders.containsKey(lowerCase)) {
                            if (map2.isEmpty()) {
                                map2 = new HashMap();
                            }
                            map2.put(this.taggedHeaders.get(lowerCase), HttpCodec.decode(str2));
                        }
                    }
                }
                if (!BigInteger.ZERO.equals(bigInteger3)) {
                    ExtractedContext extractedContext = new ExtractedContext(bigInteger3, bigInteger4, i, str, map, map2);
                    extractedContext.lockSamplingPriority();
                    return extractedContext;
                }
                if (str != null || !map2.isEmpty()) {
                    return new TagContext(str, map2);
                }
                return null;
            } catch (RuntimeException unused) {
            }
        }
    }
}
