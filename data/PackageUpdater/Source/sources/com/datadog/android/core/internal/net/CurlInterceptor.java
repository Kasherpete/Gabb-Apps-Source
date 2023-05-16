package com.datadog.android.core.internal.net;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

@Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/CurlInterceptor;", "Lokhttp3/Interceptor;", "printBody", "", "output", "Lkotlin/Function1;", "", "", "(ZLkotlin/jvm/functions/Function1;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "CurlBuilder", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CurlInterceptor.kt */
public final class CurlInterceptor implements Interceptor {
    private static final String CONTENT_TYPE = "Content-Type";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String FORMAT_BODY = "-d '%1$s'";
    private static final String FORMAT_HEADER = "-H \"%1$s:%2$s\"";
    private static final String FORMAT_METHOD = "-X %1$s";
    private static final String FORMAT_URL = "\"%1$s\"";
    private final Function1<String, Unit> output;
    private final boolean printBody;

    public CurlInterceptor() {
        this(false, (Function1) null, 3, (DefaultConstructorMarker) null);
    }

    public CurlInterceptor(boolean z, Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "output");
        this.printBody = z;
        this.output = function1;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CurlInterceptor(boolean z, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? C08591.INSTANCE : function1);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Request build = request.newBuilder().build();
        Intrinsics.checkNotNullExpressionValue(build, "copy");
        this.output.invoke(new CurlBuilder(build, this.printBody).toCommand());
        Response proceed = chain.proceed(request);
        Intrinsics.checkNotNullExpressionValue(proceed, "chain.proceed(request)");
        return proceed;
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006BI\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\u001a\b\u0002\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000e0\r¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u0017\u001a\u00020\bR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R#\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011¨\u0006\u0018"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/CurlInterceptor$CurlBuilder;", "", "request", "Lokhttp3/Request;", "printBody", "", "(Lokhttp3/Request;Z)V", "url", "", "method", "contentType", "body", "headers", "", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getBody", "()Ljava/lang/String;", "getContentType", "getHeaders", "()Ljava/util/Map;", "getMethod", "getUrl", "toCommand", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: CurlInterceptor.kt */
    public static final class CurlBuilder {
        private final String body;
        private final String contentType;
        private final Map<String, List<String>> headers;
        private final String method;
        private final String url;

        public CurlBuilder(String str, String str2, String str3, String str4, Map<String, ? extends List<String>> map) {
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(str2, "method");
            Intrinsics.checkNotNullParameter(map, "headers");
            this.url = str;
            this.method = str2;
            this.contentType = str3;
            this.body = str4;
            this.headers = map;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getMethod() {
            return this.method;
        }

        public final String getContentType() {
            return this.contentType;
        }

        public final String getBody() {
            return this.body;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ CurlBuilder(String str, String str2, String str3, String str4, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, List<String>> getHeaders() {
            return this.headers;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0024, code lost:
            r0 = r0.contentType();
         */
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public CurlBuilder(okhttp3.Request r8, boolean r9) {
            /*
                r7 = this;
                java.lang.String r0 = "request"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                okhttp3.HttpUrl r0 = r8.url()
                java.lang.String r2 = r0.toString()
                java.lang.String r0 = "request.url().toString()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
                java.lang.String r3 = r8.method()
                java.lang.String r0 = "request.method()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
                okhttp3.RequestBody r0 = r8.body()
                r1 = 0
                if (r0 != 0) goto L_0x0024
            L_0x0022:
                r4 = r1
                goto L_0x0030
            L_0x0024:
                okhttp3.MediaType r0 = r0.contentType()
                if (r0 != 0) goto L_0x002b
                goto L_0x0022
            L_0x002b:
                java.lang.String r0 = r0.toString()
                r4 = r0
            L_0x0030:
                if (r9 == 0) goto L_0x003e
                com.datadog.android.core.internal.net.CurlInterceptor$Companion r9 = com.datadog.android.core.internal.net.CurlInterceptor.Companion
                okhttp3.RequestBody r0 = r8.body()
                java.lang.String r9 = r9.peekBody(r0)
                r5 = r9
                goto L_0x003f
            L_0x003e:
                r5 = r1
            L_0x003f:
                okhttp3.Headers r8 = r8.headers()
                java.util.Map r6 = r8.toMultimap()
                java.lang.String r8 = "request.headers().toMultimap()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r8)
                r1 = r7
                r1.<init>(r2, r3, r4, r5, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.internal.net.CurlInterceptor.CurlBuilder.<init>(okhttp3.Request, boolean):void");
        }

        public final String toCommand() {
            List arrayList = new ArrayList();
            arrayList.add("curl");
            Locale locale = Locale.US;
            String str = this.method;
            Locale locale2 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale2, "US");
            String upperCase = str.toUpperCase(locale2);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            String format = String.format(locale, CurlInterceptor.FORMAT_METHOD, Arrays.copyOf(new Object[]{upperCase}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            arrayList.add(format);
            for (Map.Entry next : this.headers.entrySet()) {
                String str2 = (String) next.getKey();
                for (String str3 : (List) next.getValue()) {
                    String format2 = String.format(Locale.US, CurlInterceptor.FORMAT_HEADER, Arrays.copyOf(new Object[]{str2, str3}, 2));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
                    arrayList.add(format2);
                }
            }
            if (this.contentType != null && !this.headers.containsKey("Content-Type")) {
                String format3 = String.format(Locale.US, CurlInterceptor.FORMAT_HEADER, Arrays.copyOf(new Object[]{"Content-Type", this.contentType}, 2));
                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, this, *args)");
                arrayList.add(format3);
            }
            if (this.body != null) {
                String format4 = String.format(Locale.US, CurlInterceptor.FORMAT_BODY, Arrays.copyOf(new Object[]{this.body}, 1));
                Intrinsics.checkNotNullExpressionValue(format4, "format(locale, this, *args)");
                arrayList.add(format4);
            }
            String format5 = String.format(Locale.US, CurlInterceptor.FORMAT_URL, Arrays.copyOf(new Object[]{this.url}, 1));
            Intrinsics.checkNotNullExpressionValue(format5, "format(locale, this, *args)");
            arrayList.add(format5);
            return CollectionsKt.joinToString$default(arrayList, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/CurlInterceptor$Companion;", "", "()V", "CONTENT_TYPE", "", "FORMAT_BODY", "FORMAT_HEADER", "FORMAT_METHOD", "FORMAT_URL", "peekBody", "body", "Lokhttp3/RequestBody;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: CurlInterceptor.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final String peekBody(RequestBody requestBody) {
            if (requestBody == null) {
                return null;
            }
            try {
                Buffer buffer = new Buffer();
                Charset defaultCharset = Charset.defaultCharset();
                Intrinsics.checkNotNullExpressionValue(defaultCharset, "defaultCharset()");
                requestBody.writeTo(buffer);
                return buffer.readString(defaultCharset);
            } catch (IOException e) {
                return "Error while reading body: " + e;
            } catch (IllegalArgumentException e2) {
                return "Error while reading body: " + e2;
            }
        }
    }
}
