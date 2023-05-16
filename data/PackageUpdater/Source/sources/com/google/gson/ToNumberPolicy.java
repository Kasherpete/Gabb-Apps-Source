package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.math.BigDecimal;

public enum ToNumberPolicy implements ToNumberStrategy {
    DOUBLE {
        public Double readNumber(JsonReader jsonReader) throws IOException {
            return Double.valueOf(jsonReader.nextDouble());
        }
    },
    LAZILY_PARSED_NUMBER {
        public Number readNumber(JsonReader jsonReader) throws IOException {
            return new LazilyParsedNumber(jsonReader.nextString());
        }
    },
    LONG_OR_DOUBLE {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x004a, code lost:
            throw new com.google.gson.stream.MalformedJsonException("JSON forbids NaN and infinities: " + r1 + "; at path " + r6.getPath());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0070, code lost:
            throw new com.google.gson.JsonParseException("Cannot parse " + r0 + "; at path " + r6.getPath(), r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
            r1 = java.lang.Double.valueOf(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
            if (r1.isInfinite() != false) goto L_0x001f;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000f */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Number readNumber(com.google.gson.stream.JsonReader r6) throws java.io.IOException, com.google.gson.JsonParseException {
            /*
                r5 = this;
                java.lang.String r5 = "; at path "
                java.lang.String r0 = r6.nextString()
                long r1 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x000f }
                java.lang.Long r5 = java.lang.Long.valueOf(r1)     // Catch:{ NumberFormatException -> 0x000f }
                return r5
            L_0x000f:
                java.lang.Double r1 = java.lang.Double.valueOf(r0)     // Catch:{ NumberFormatException -> 0x004b }
                boolean r2 = r1.isInfinite()     // Catch:{ NumberFormatException -> 0x004b }
                if (r2 != 0) goto L_0x001f
                boolean r2 = r1.isNaN()     // Catch:{ NumberFormatException -> 0x004b }
                if (r2 == 0) goto L_0x0025
            L_0x001f:
                boolean r2 = r6.isLenient()     // Catch:{ NumberFormatException -> 0x004b }
                if (r2 == 0) goto L_0x0026
            L_0x0025:
                return r1
            L_0x0026:
                com.google.gson.stream.MalformedJsonException r2 = new com.google.gson.stream.MalformedJsonException     // Catch:{ NumberFormatException -> 0x004b }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x004b }
                r3.<init>()     // Catch:{ NumberFormatException -> 0x004b }
                java.lang.String r4 = "JSON forbids NaN and infinities: "
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ NumberFormatException -> 0x004b }
                java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ NumberFormatException -> 0x004b }
                java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ NumberFormatException -> 0x004b }
                java.lang.String r3 = r6.getPath()     // Catch:{ NumberFormatException -> 0x004b }
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ NumberFormatException -> 0x004b }
                java.lang.String r1 = r1.toString()     // Catch:{ NumberFormatException -> 0x004b }
                r2.<init>((java.lang.String) r1)     // Catch:{ NumberFormatException -> 0x004b }
                throw r2     // Catch:{ NumberFormatException -> 0x004b }
            L_0x004b:
                r1 = move-exception
                com.google.gson.JsonParseException r2 = new com.google.gson.JsonParseException
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Cannot parse "
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.StringBuilder r0 = r3.append(r0)
                java.lang.StringBuilder r5 = r0.append(r5)
                java.lang.String r6 = r6.getPath()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r2.<init>(r5, r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.ToNumberPolicy.C12223.readNumber(com.google.gson.stream.JsonReader):java.lang.Number");
        }
    },
    BIG_DECIMAL {
        public BigDecimal readNumber(JsonReader jsonReader) throws IOException {
            String nextString = jsonReader.nextString();
            try {
                return new BigDecimal(nextString);
            } catch (NumberFormatException e) {
                throw new JsonParseException("Cannot parse " + nextString + "; at path " + jsonReader.getPath(), e);
            }
        }
    }
}
