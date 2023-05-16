package okhttp3.internal.p010ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.Metadata;
import okio.Buffer;
import okio.ByteString;
import okio.DeflaterSink;
import okio.Sink;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0006J\u0014\u0010\u000f\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo20735d2 = {"Lokhttp3/internal/ws/MessageDeflater;", "Ljava/io/Closeable;", "noContextTakeover", "", "(Z)V", "deflatedBytes", "Lokio/Buffer;", "deflater", "Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "close", "", "deflate", "buffer", "endsWith", "suffix", "Lokio/ByteString;", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* renamed from: okhttp3.internal.ws.MessageDeflater */
/* compiled from: MessageDeflater.kt */
public final class MessageDeflater implements Closeable {
    private final Buffer deflatedBytes;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final boolean noContextTakeover;

    public MessageDeflater(boolean z) {
        this.noContextTakeover = z;
        Buffer buffer = new Buffer();
        this.deflatedBytes = buffer;
        Deflater deflater2 = new Deflater(-1, true);
        this.deflater = deflater2;
        this.deflaterSink = new DeflaterSink((Sink) buffer, deflater2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005c, code lost:
        kotlin.p007io.CloseableKt.closeFinally(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void deflate(okio.Buffer r7) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = "buffer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            okio.Buffer r0 = r6.deflatedBytes
            long r0 = r0.size()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0015
            r0 = r1
            goto L_0x0016
        L_0x0015:
            r0 = r2
        L_0x0016:
            if (r0 == 0) goto L_0x006f
            boolean r0 = r6.noContextTakeover
            if (r0 == 0) goto L_0x0021
            java.util.zip.Deflater r0 = r6.deflater
            r0.reset()
        L_0x0021:
            okio.DeflaterSink r0 = r6.deflaterSink
            long r3 = r7.size()
            r0.write(r7, r3)
            okio.DeflaterSink r0 = r6.deflaterSink
            r0.flush()
            okio.Buffer r0 = r6.deflatedBytes
            okio.ByteString r3 = okhttp3.internal.p010ws.MessageDeflaterKt.EMPTY_DEFLATE_BLOCK
            boolean r0 = r6.endsWith(r0, r3)
            if (r0 == 0) goto L_0x0060
            okio.Buffer r0 = r6.deflatedBytes
            long r2 = r0.size()
            r0 = 4
            long r4 = (long) r0
            long r2 = r2 - r4
            okio.Buffer r0 = r6.deflatedBytes
            r4 = 0
            okio.Buffer$UnsafeCursor r0 = okio.Buffer.readAndWriteUnsafe$default(r0, r4, r1, r4)
            java.io.Closeable r0 = (java.io.Closeable) r0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            r1 = r0
            okio.Buffer$UnsafeCursor r1 = (okio.Buffer.UnsafeCursor) r1     // Catch:{ all -> 0x0059 }
            r1.resizeBuffer(r2)     // Catch:{ all -> 0x0059 }
            kotlin.p007io.CloseableKt.closeFinally(r0, r4)
            goto L_0x0065
        L_0x0059:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x005b }
        L_0x005b:
            r7 = move-exception
            kotlin.p007io.CloseableKt.closeFinally(r0, r6)
            throw r7
        L_0x0060:
            okio.Buffer r0 = r6.deflatedBytes
            r0.writeByte((int) r2)
        L_0x0065:
            okio.Buffer r6 = r6.deflatedBytes
            long r0 = r6.size()
            r7.write((okio.Buffer) r6, (long) r0)
            return
        L_0x006f:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Failed requirement."
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p010ws.MessageDeflater.deflate(okio.Buffer):void");
    }

    public void close() throws IOException {
        this.deflaterSink.close();
    }

    private final boolean endsWith(Buffer buffer, ByteString byteString) {
        return buffer.rangeEquals(buffer.size() - ((long) byteString.size()), byteString);
    }
}
