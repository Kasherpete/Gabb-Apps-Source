package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0004J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J@\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u0002H\u00110\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u00170\u0014H\b¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH&J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH&J\b\u0010\u001d\u001a\u00020\u0015H&J\u0006\u0010\u001e\u001a\u00020\u001fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, mo20735d2 = {"Lokhttp3/ResponseBody;", "Ljava/io/Closeable;", "()V", "reader", "Ljava/io/Reader;", "byteStream", "Ljava/io/InputStream;", "byteString", "Lokio/ByteString;", "bytes", "", "charStream", "charset", "Ljava/nio/charset/Charset;", "close", "", "consumeSource", "T", "", "consumer", "Lkotlin/Function1;", "Lokio/BufferedSource;", "sizeMapper", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "source", "string", "", "BomAwareReader", "Companion", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: ResponseBody.kt */
public abstract class ResponseBody implements Closeable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private Reader reader;

    @JvmStatic
    public static final ResponseBody create(String str, MediaType mediaType) {
        return Companion.create(str, mediaType);
    }

    @JvmStatic
    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}))
    public static final ResponseBody create(MediaType mediaType, long j, BufferedSource bufferedSource) {
        return Companion.create(mediaType, j, bufferedSource);
    }

    @JvmStatic
    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    public static final ResponseBody create(MediaType mediaType, String str) {
        return Companion.create(mediaType, str);
    }

    @JvmStatic
    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    public static final ResponseBody create(MediaType mediaType, ByteString byteString) {
        return Companion.create(mediaType, byteString);
    }

    @JvmStatic
    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    public static final ResponseBody create(MediaType mediaType, byte[] bArr) {
        return Companion.create(mediaType, bArr);
    }

    @JvmStatic
    public static final ResponseBody create(BufferedSource bufferedSource, MediaType mediaType, long j) {
        return Companion.create(bufferedSource, mediaType, j);
    }

    @JvmStatic
    public static final ResponseBody create(ByteString byteString, MediaType mediaType) {
        return Companion.create(byteString, mediaType);
    }

    @JvmStatic
    public static final ResponseBody create(byte[] bArr, MediaType mediaType) {
        return Companion.create(bArr, mediaType);
    }

    public abstract long contentLength();

    public abstract MediaType contentType();

    public abstract BufferedSource source();

    public final InputStream byteStream() {
        return source().inputStream();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0067, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0068, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.p007io.CloseableKt.closeFinally(r4, r5);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0071, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T> T consumeSource(kotlin.jvm.functions.Function1<? super okio.BufferedSource, ? extends T> r5, kotlin.jvm.functions.Function1<? super T, java.lang.Integer> r6) {
        /*
            r4 = this;
            long r0 = r4.contentLength()
            r2 = 2147483647(0x7fffffff, float:NaN)
            long r2 = (long) r2
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x0072
            okio.BufferedSource r4 = r4.source()
            java.io.Closeable r4 = (java.io.Closeable) r4
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = 1
            java.lang.Object r5 = r5.invoke(r4)     // Catch:{ all -> 0x0065 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlin.p007io.CloseableKt.closeFinally(r4, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            java.lang.Object r4 = r6.invoke(r5)
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            r2 = -1
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0064
            long r2 = (long) r4
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x0039
            goto L_0x0064
        L_0x0039:
            java.io.IOException r5 = new java.io.IOException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r2 = "Content-Length ("
            java.lang.StringBuilder r6 = r6.append(r2)
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r0 = ") and stream length ("
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.StringBuilder r4 = r6.append(r4)
            java.lang.String r6 = ") disagree"
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            throw r5
        L_0x0064:
            return r5
        L_0x0065:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0067 }
        L_0x0067:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlin.p007io.CloseableKt.closeFinally(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r6
        L_0x0072:
            java.io.IOException r4 = new java.io.IOException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Cannot buffer entire body for content length: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.ResponseBody.consumeSource(kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    public final Reader charStream() {
        Reader reader2 = this.reader;
        if (reader2 != null) {
            return reader2;
        }
        Reader bomAwareReader = new BomAwareReader(source(), charset());
        this.reader = bomAwareReader;
        return bomAwareReader;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        kotlin.p007io.CloseableKt.closeFinally(r0, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String string() throws java.io.IOException {
        /*
            r3 = this;
            okio.BufferedSource r0 = r3.source()
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = r0
            okio.BufferedSource r2 = (okio.BufferedSource) r2     // Catch:{ all -> 0x001c }
            java.nio.charset.Charset r3 = r3.charset()     // Catch:{ all -> 0x001c }
            java.nio.charset.Charset r3 = okhttp3.internal.Util.readBomAsCharset(r2, r3)     // Catch:{ all -> 0x001c }
            java.lang.String r3 = r2.readString(r3)     // Catch:{ all -> 0x001c }
            kotlin.p007io.CloseableKt.closeFinally(r0, r1)
            return r3
        L_0x001c:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001e }
        L_0x001e:
            r1 = move-exception
            kotlin.p007io.CloseableKt.closeFinally(r0, r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.ResponseBody.string():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r1.charset(kotlin.text.Charsets.UTF_8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.nio.charset.Charset charset() {
        /*
            r1 = this;
            okhttp3.MediaType r1 = r1.contentType()
            if (r1 == 0) goto L_0x000f
            java.nio.charset.Charset r0 = kotlin.text.Charsets.UTF_8
            java.nio.charset.Charset r1 = r1.charset(r0)
            if (r1 == 0) goto L_0x000f
            goto L_0x0011
        L_0x000f:
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
        L_0x0011:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.ResponseBody.charset():java.nio.charset.Charset");
    }

    public void close() {
        Util.closeQuietly((Closeable) source());
    }

    @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo20735d2 = {"Lokhttp3/ResponseBody$BomAwareReader;", "Ljava/io/Reader;", "source", "Lokio/BufferedSource;", "charset", "Ljava/nio/charset/Charset;", "(Lokio/BufferedSource;Ljava/nio/charset/Charset;)V", "closed", "", "delegate", "close", "", "read", "", "cbuf", "", "off", "len", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
    /* compiled from: ResponseBody.kt */
    public static final class BomAwareReader extends Reader {
        private final Charset charset;
        private boolean closed;
        private Reader delegate;
        private final BufferedSource source;

        public BomAwareReader(BufferedSource bufferedSource, Charset charset2) {
            Intrinsics.checkNotNullParameter(bufferedSource, "source");
            Intrinsics.checkNotNullParameter(charset2, "charset");
            this.source = bufferedSource;
            this.charset = charset2;
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            Intrinsics.checkNotNullParameter(cArr, "cbuf");
            if (!this.closed) {
                Reader reader = this.delegate;
                if (reader == null) {
                    reader = new InputStreamReader(this.source.inputStream(), Util.readBomAsCharset(this.source, this.charset));
                    this.delegate = reader;
                }
                return reader.read(cArr, i, i2);
            }
            throw new IOException("Stream closed");
        }

        public void close() throws IOException {
            this.closed = true;
            Reader reader = this.delegate;
            if (reader != null) {
                reader.close();
            } else {
                this.source.close();
            }
        }
    }

    @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u000bH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\fH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\rH\u0007J'\u0010\u000e\u001a\u00020\u0004*\u00020\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\b\u0003J\u001d\u0010\u000f\u001a\u00020\u0004*\u00020\b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J\u001d\u0010\u000f\u001a\u00020\u0004*\u00020\f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J\u001d\u0010\u000f\u001a\u00020\u0004*\u00020\r2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003¨\u0006\u0010"}, mo20735d2 = {"Lokhttp3/ResponseBody$Companion;", "", "()V", "create", "Lokhttp3/ResponseBody;", "contentType", "Lokhttp3/MediaType;", "content", "", "contentLength", "", "Lokio/BufferedSource;", "", "Lokio/ByteString;", "asResponseBody", "toResponseBody", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
    /* compiled from: ResponseBody.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, String str, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(str, mediaType);
        }

        @JvmStatic
        public final ResponseBody create(String str, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(str, "$this$toResponseBody");
            Charset charset = Charsets.UTF_8;
            if (mediaType != null && (charset = MediaType.charset$default(mediaType, (Charset) null, 1, (Object) null)) == null) {
                charset = Charsets.UTF_8;
                mediaType = MediaType.Companion.parse(mediaType + "; charset=utf-8");
            }
            Buffer writeString = new Buffer().writeString(str, charset);
            return create((BufferedSource) writeString, mediaType, writeString.size());
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, byte[] bArr, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(bArr, mediaType);
        }

        @JvmStatic
        public final ResponseBody create(byte[] bArr, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(bArr, "$this$toResponseBody");
            return create((BufferedSource) new Buffer().write(bArr), mediaType, (long) bArr.length);
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, ByteString byteString, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(byteString, mediaType);
        }

        @JvmStatic
        public final ResponseBody create(ByteString byteString, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(byteString, "$this$toResponseBody");
            return create((BufferedSource) new Buffer().write(byteString), mediaType, (long) byteString.size());
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, BufferedSource bufferedSource, MediaType mediaType, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            if ((i & 2) != 0) {
                j = -1;
            }
            return companion.create(bufferedSource, mediaType, j);
        }

        @JvmStatic
        public final ResponseBody create(BufferedSource bufferedSource, MediaType mediaType, long j) {
            Intrinsics.checkNotNullParameter(bufferedSource, "$this$asResponseBody");
            return new ResponseBody$Companion$asResponseBody$1(bufferedSource, mediaType, j);
        }

        @JvmStatic
        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        public final ResponseBody create(MediaType mediaType, String str) {
            Intrinsics.checkNotNullParameter(str, "content");
            return create(str, mediaType);
        }

        @JvmStatic
        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        public final ResponseBody create(MediaType mediaType, byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "content");
            return create(bArr, mediaType);
        }

        @JvmStatic
        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        public final ResponseBody create(MediaType mediaType, ByteString byteString) {
            Intrinsics.checkNotNullParameter(byteString, "content");
            return create(byteString, mediaType);
        }

        @JvmStatic
        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}))
        public final ResponseBody create(MediaType mediaType, long j, BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(bufferedSource, "content");
            return create(bufferedSource, mediaType, j);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005b, code lost:
        kotlin.p007io.CloseableKt.closeFinally(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005e, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] bytes() throws java.io.IOException {
        /*
            r6 = this;
            long r0 = r6.contentLength()
            r2 = 2147483647(0x7fffffff, float:NaN)
            long r2 = (long) r2
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x005f
            okio.BufferedSource r6 = r6.source()
            java.io.Closeable r6 = (java.io.Closeable) r6
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = r6
            okio.BufferedSource r3 = (okio.BufferedSource) r3     // Catch:{ all -> 0x0058 }
            byte[] r3 = r3.readByteArray()     // Catch:{ all -> 0x0058 }
            kotlin.p007io.CloseableKt.closeFinally(r6, r2)
            int r6 = r3.length
            r4 = -1
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x0057
            long r4 = (long) r6
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x002c
            goto L_0x0057
        L_0x002c:
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Content-Length ("
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r1 = ") and stream length ("
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r6 = r0.append(r6)
            java.lang.String r0 = ") disagree"
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            r2.<init>(r6)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0057:
            return r3
        L_0x0058:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x005a }
        L_0x005a:
            r1 = move-exception
            kotlin.p007io.CloseableKt.closeFinally(r6, r0)
            throw r1
        L_0x005f:
            java.io.IOException r6 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Cannot buffer entire body for content length: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.ResponseBody.bytes():byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005e, code lost:
        kotlin.p007io.CloseableKt.closeFinally(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0061, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final okio.ByteString byteString() throws java.io.IOException {
        /*
            r6 = this;
            long r0 = r6.contentLength()
            r2 = 2147483647(0x7fffffff, float:NaN)
            long r2 = (long) r2
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x0062
            okio.BufferedSource r6 = r6.source()
            java.io.Closeable r6 = (java.io.Closeable) r6
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = r6
            okio.BufferedSource r3 = (okio.BufferedSource) r3     // Catch:{ all -> 0x005b }
            okio.ByteString r3 = r3.readByteString()     // Catch:{ all -> 0x005b }
            kotlin.p007io.CloseableKt.closeFinally(r6, r2)
            int r6 = r3.size()
            r4 = -1
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x005a
            long r4 = (long) r6
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x002f
            goto L_0x005a
        L_0x002f:
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Content-Length ("
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r1 = ") and stream length ("
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r6 = r0.append(r6)
            java.lang.String r0 = ") disagree"
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            r2.<init>(r6)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x005a:
            return r3
        L_0x005b:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x005d }
        L_0x005d:
            r1 = move-exception
            kotlin.p007io.CloseableKt.closeFinally(r6, r0)
            throw r1
        L_0x0062:
            java.io.IOException r6 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Cannot buffer entire body for content length: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.ResponseBody.byteString():okio.ByteString");
    }
}
