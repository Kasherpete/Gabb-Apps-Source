package okhttp3;

import java.io.File;
import kotlin.Metadata;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, mo20735d2 = {"okhttp3/RequestBody$Companion$asRequestBody$1", "Lokhttp3/RequestBody;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "writeTo", "", "sink", "Lokio/BufferedSink;", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: RequestBody.kt */
public final class RequestBody$Companion$asRequestBody$1 extends RequestBody {
    final /* synthetic */ MediaType $contentType;
    final /* synthetic */ File $this_asRequestBody;

    RequestBody$Companion$asRequestBody$1(File file, MediaType mediaType) {
        this.$this_asRequestBody = file;
        this.$contentType = mediaType;
    }

    public MediaType contentType() {
        return this.$contentType;
    }

    public long contentLength() {
        return this.$this_asRequestBody.length();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        kotlin.p007io.CloseableKt.closeFinally(r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeTo(okio.BufferedSink r3) {
        /*
            r2 = this;
            java.lang.String r0 = "sink"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.io.File r2 = r2.$this_asRequestBody
            okio.Source r2 = okio.Okio.source((java.io.File) r2)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = r2
            okio.Source r1 = (okio.Source) r1     // Catch:{ all -> 0x001a }
            r3.writeAll(r1)     // Catch:{ all -> 0x001a }
            kotlin.p007io.CloseableKt.closeFinally(r2, r0)
            return
        L_0x001a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001c }
        L_0x001c:
            r0 = move-exception
            kotlin.p007io.CloseableKt.closeFinally(r2, r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.RequestBody$Companion$asRequestBody$1.writeTo(okio.BufferedSink):void");
    }
}
