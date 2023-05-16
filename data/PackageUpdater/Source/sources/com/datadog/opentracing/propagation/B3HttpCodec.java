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

class B3HttpCodec {
    private static final int HEX_RADIX = 16;
    /* access modifiers changed from: private */
    public static final String SAMPLING_PRIORITY_ACCEPT = String.valueOf(1);
    /* access modifiers changed from: private */
    public static final String SAMPLING_PRIORITY_DROP = String.valueOf(0);
    private static final String SAMPLING_PRIORITY_KEY = "X-B3-Sampled";
    private static final String SPAN_ID_KEY = "X-B3-SpanId";
    private static final String TRACE_ID_KEY = "X-B3-TraceId";

    private B3HttpCodec() {
    }

    public static class Injector implements HttpCodec.Injector {
        public void inject(DDSpanContext dDSpanContext, TextMapInject textMapInject) {
            try {
                textMapInject.put(B3HttpCodec.TRACE_ID_KEY, dDSpanContext.getTraceId().toString(16).toLowerCase(Locale.US));
                textMapInject.put(B3HttpCodec.SPAN_ID_KEY, dDSpanContext.getSpanId().toString(16).toLowerCase(Locale.US));
                if (dDSpanContext.lockSamplingPriority()) {
                    textMapInject.put(B3HttpCodec.SAMPLING_PRIORITY_KEY, convertSamplingPriority(dDSpanContext.getSamplingPriority()));
                }
            } catch (NumberFormatException unused) {
            }
        }

        private String convertSamplingPriority(int i) {
            return i > 0 ? B3HttpCodec.SAMPLING_PRIORITY_ACCEPT : B3HttpCodec.SAMPLING_PRIORITY_DROP;
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
                BigInteger bigInteger = BigInteger.ZERO;
                BigInteger bigInteger2 = BigInteger.ZERO;
                Map map = emptyMap;
                BigInteger bigInteger3 = bigInteger;
                BigInteger bigInteger4 = bigInteger2;
                int i = Integer.MIN_VALUE;
                for (Map.Entry next : textMapExtract) {
                    String lowerCase = ((String) next.getKey()).toLowerCase(Locale.US);
                    String str = (String) next.getValue();
                    if (str != null) {
                        if (B3HttpCodec.TRACE_ID_KEY.equalsIgnoreCase(lowerCase)) {
                            int length = str.length();
                            if (length > 32) {
                                bigInteger3 = BigInteger.ZERO;
                            } else {
                                bigInteger3 = HttpCodec.validateUInt64BitsID(length > 16 ? str.substring(length - 16) : str, 16);
                            }
                        } else if (B3HttpCodec.SPAN_ID_KEY.equalsIgnoreCase(lowerCase)) {
                            bigInteger4 = HttpCodec.validateUInt64BitsID(str, 16);
                        } else if (B3HttpCodec.SAMPLING_PRIORITY_KEY.equalsIgnoreCase(lowerCase)) {
                            i = convertSamplingPriority(str);
                        }
                        if (this.taggedHeaders.containsKey(lowerCase)) {
                            if (map.isEmpty()) {
                                map = new HashMap();
                            }
                            map.put(this.taggedHeaders.get(lowerCase), HttpCodec.decode(str));
                        }
                    }
                }
                if (!BigInteger.ZERO.equals(bigInteger3)) {
                    ExtractedContext extractedContext = new ExtractedContext(bigInteger3, bigInteger4, i, (String) null, Collections.emptyMap(), map);
                    extractedContext.lockSamplingPriority();
                    return extractedContext;
                }
                if (!map.isEmpty()) {
                    return new TagContext((String) null, map);
                }
                return null;
            } catch (RuntimeException unused) {
            }
        }

        private int convertSamplingPriority(String str) {
            return Integer.parseInt(str) == 1 ? 1 : 0;
        }
    }
}
