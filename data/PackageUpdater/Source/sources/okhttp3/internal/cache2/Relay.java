package okhttp3.internal.cache2;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 :2\u00020\u0001:\u0002:;B3\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\u000e\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tJ\b\u00105\u001a\u0004\u0018\u00010\u0005J \u00106\u001a\u0002032\u0006\u00107\u001a\u00020\t2\u0006\u00104\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u0007H\u0002J\u0010\u00109\u001a\u0002032\u0006\u00104\u001a\u00020\u0007H\u0002R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0011\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u0006<"}, mo20735d2 = {"Lokhttp3/internal/cache2/Relay;", "", "file", "Ljava/io/RandomAccessFile;", "upstream", "Lokio/Source;", "upstreamPos", "", "metadata", "Lokio/ByteString;", "bufferMaxSize", "(Ljava/io/RandomAccessFile;Lokio/Source;JLokio/ByteString;J)V", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "getBufferMaxSize", "()J", "complete", "", "getComplete", "()Z", "setComplete", "(Z)V", "getFile", "()Ljava/io/RandomAccessFile;", "setFile", "(Ljava/io/RandomAccessFile;)V", "isClosed", "sourceCount", "", "getSourceCount", "()I", "setSourceCount", "(I)V", "getUpstream", "()Lokio/Source;", "setUpstream", "(Lokio/Source;)V", "upstreamBuffer", "getUpstreamBuffer", "getUpstreamPos", "setUpstreamPos", "(J)V", "upstreamReader", "Ljava/lang/Thread;", "getUpstreamReader", "()Ljava/lang/Thread;", "setUpstreamReader", "(Ljava/lang/Thread;)V", "commit", "", "upstreamSize", "newSource", "writeHeader", "prefix", "metadataSize", "writeMetadata", "Companion", "RelaySource", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: Relay.kt */
public final class Relay {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long FILE_HEADER_SIZE = 32;
    public static final ByteString PREFIX_CLEAN = ByteString.Companion.encodeUtf8("OkHttp cache v1\n");
    public static final ByteString PREFIX_DIRTY = ByteString.Companion.encodeUtf8("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    private final Buffer buffer;
    private final long bufferMaxSize;
    private boolean complete;
    private RandomAccessFile file;
    private final ByteString metadata;
    private int sourceCount;
    private Source upstream;
    private final Buffer upstreamBuffer;
    private long upstreamPos;
    private Thread upstreamReader;

    private Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2) {
        this.file = randomAccessFile;
        this.upstream = source;
        this.upstreamPos = j;
        this.metadata = byteString;
        this.bufferMaxSize = j2;
        this.upstreamBuffer = new Buffer();
        this.complete = this.upstream == null;
        this.buffer = new Buffer();
    }

    public /* synthetic */ Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(randomAccessFile, source, j, byteString, j2);
    }

    public final RandomAccessFile getFile() {
        return this.file;
    }

    public final void setFile(RandomAccessFile randomAccessFile) {
        this.file = randomAccessFile;
    }

    public final Source getUpstream() {
        return this.upstream;
    }

    public final void setUpstream(Source source) {
        this.upstream = source;
    }

    public final long getUpstreamPos() {
        return this.upstreamPos;
    }

    public final void setUpstreamPos(long j) {
        this.upstreamPos = j;
    }

    public final long getBufferMaxSize() {
        return this.bufferMaxSize;
    }

    public final Thread getUpstreamReader() {
        return this.upstreamReader;
    }

    public final void setUpstreamReader(Thread thread) {
        this.upstreamReader = thread;
    }

    public final Buffer getUpstreamBuffer() {
        return this.upstreamBuffer;
    }

    public final boolean getComplete() {
        return this.complete;
    }

    public final void setComplete(boolean z) {
        this.complete = z;
    }

    public final Buffer getBuffer() {
        return this.buffer;
    }

    public final int getSourceCount() {
        return this.sourceCount;
    }

    public final void setSourceCount(int i) {
        this.sourceCount = i;
    }

    public final boolean isClosed() {
        return this.file == null;
    }

    /* access modifiers changed from: private */
    public final void writeHeader(ByteString byteString, long j, long j2) throws IOException {
        Buffer buffer2 = new Buffer();
        buffer2.write(byteString);
        buffer2.writeLong(j);
        buffer2.writeLong(j2);
        if (buffer2.size() == FILE_HEADER_SIZE) {
            RandomAccessFile randomAccessFile = this.file;
            Intrinsics.checkNotNull(randomAccessFile);
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "file!!.channel");
            new FileOperator(channel).write(0, buffer2, FILE_HEADER_SIZE);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final void writeMetadata(long j) throws IOException {
        Buffer buffer2 = new Buffer();
        buffer2.write(this.metadata);
        RandomAccessFile randomAccessFile = this.file;
        Intrinsics.checkNotNull(randomAccessFile);
        FileChannel channel = randomAccessFile.getChannel();
        Intrinsics.checkNotNullExpressionValue(channel, "file!!.channel");
        new FileOperator(channel).write(FILE_HEADER_SIZE + j, buffer2, (long) this.metadata.size());
    }

    public final void commit(long j) throws IOException {
        writeMetadata(j);
        RandomAccessFile randomAccessFile = this.file;
        Intrinsics.checkNotNull(randomAccessFile);
        randomAccessFile.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, j, (long) this.metadata.size());
        RandomAccessFile randomAccessFile2 = this.file;
        Intrinsics.checkNotNull(randomAccessFile2);
        randomAccessFile2.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
            Unit unit = Unit.INSTANCE;
        }
        Source source = this.upstream;
        if (source != null) {
            Util.closeQuietly((Closeable) source);
        }
        this.upstream = null;
    }

    public final ByteString metadata() {
        return this.metadata;
    }

    public final Source newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }

    @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo20735d2 = {"Lokhttp3/internal/cache2/Relay$RelaySource;", "Lokio/Source;", "(Lokhttp3/internal/cache2/Relay;)V", "fileOperator", "Lokhttp3/internal/cache2/FileOperator;", "sourcePos", "", "timeout", "Lokio/Timeout;", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
    /* compiled from: Relay.kt */
    public final class RelaySource implements Source {
        private FileOperator fileOperator;
        private long sourcePos;
        private final Timeout timeout = new Timeout();

        public RelaySource() {
            RandomAccessFile file = Relay.this.getFile();
            Intrinsics.checkNotNull(file);
            FileChannel channel = file.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "file!!.channel");
            this.fileOperator = new FileOperator(channel);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x008b, code lost:
            if (r4 != true) goto L_0x00ae;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x008d, code lost:
            r8 = java.lang.Math.min(r2, r1.this$0.getUpstreamPos() - r1.sourcePos);
            r2 = r1.fileOperator;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2);
            r2.read(r1.sourcePos + okhttp3.internal.cache2.Relay.FILE_HEADER_SIZE, r20, r8);
            r1.sourcePos += r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ad, code lost:
            return r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            r0 = r1.this$0.getUpstream();
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
            r14 = r0.read(r1.this$0.getUpstreamBuffer(), r1.this$0.getBufferMaxSize());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ca, code lost:
            if (r14 != -1) goto L_0x00f7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00cc, code lost:
            r0 = r1.this$0;
            r0.commit(r0.getUpstreamPos());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d5, code lost:
            r2 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d7, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            r1.this$0.setUpstreamReader((java.lang.Thread) null);
            r0 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e1, code lost:
            if (r0 == null) goto L_0x00ec;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e3, code lost:
            r0.notifyAll();
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ea, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00eb, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f3, code lost:
            throw new java.lang.NullPointerException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
            r9 = java.lang.Math.min(r14, r2);
            r1.this$0.getUpstreamBuffer().copyTo(r20, 0, r9);
            r1.sourcePos += r9;
            r13 = r1.fileOperator;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13);
            r4 = r14;
            r13.write(r1.this$0.getUpstreamPos() + okhttp3.internal.cache2.Relay.FILE_HEADER_SIZE, r1.this$0.getUpstreamBuffer().clone(), r4);
            r2 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x012e, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
            r1.this$0.getBuffer().write(r1.this$0.getUpstreamBuffer(), r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0150, code lost:
            if (r1.this$0.getBuffer().size() <= r1.this$0.getBufferMaxSize()) goto L_0x016c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0152, code lost:
            r1.this$0.getBuffer().skip(r1.this$0.getBuffer().size() - r1.this$0.getBufferMaxSize());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x016c, code lost:
            r0 = r1.this$0;
            r0.setUpstreamPos(r0.getUpstreamPos() + r4);
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0179, code lost:
            r2 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x017b, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
            r1.this$0.setUpstreamReader((java.lang.Thread) null);
            r0 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x0185, code lost:
            if (r0 == null) goto L_0x0190;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x0187, code lost:
            r0.notifyAll();
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x018e, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x018f, code lost:
            return r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x0197, code lost:
            throw new java.lang.NullPointerException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x019e, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x01a1, code lost:
            monitor-enter(r1.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
            r1.this$0.setUpstreamReader((java.lang.Thread) null);
            r1 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:0x01ab, code lost:
            if (r1 == null) goto L_0x01ad;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x01b4, code lost:
            throw new java.lang.NullPointerException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x01b5, code lost:
            r1.notifyAll();
            r1 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x01bd, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(okio.Buffer r20, long r21) throws java.io.IOException {
            /*
                r19 = this;
                r1 = r19
                r2 = r21
                java.lang.String r0 = "sink"
                r5 = r20
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                okhttp3.internal.cache2.FileOperator r0 = r1.fileOperator
                r4 = 1
                if (r0 == 0) goto L_0x0012
                r0 = r4
                goto L_0x0013
            L_0x0012:
                r0 = 0
            L_0x0013:
                if (r0 == 0) goto L_0x01c4
                okhttp3.internal.cache2.Relay r8 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r8)
            L_0x0018:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                long r6 = r0.getUpstreamPos()     // Catch:{ all -> 0x01c1 }
                long r9 = r1.sourcePos     // Catch:{ all -> 0x01c1 }
                int r0 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
                r6 = 2
                r9 = -1
                if (r0 == 0) goto L_0x0065
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                long r11 = r0.getUpstreamPos()     // Catch:{ all -> 0x01c1 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                okio.Buffer r0 = r0.getBuffer()     // Catch:{ all -> 0x01c1 }
                long r13 = r0.size()     // Catch:{ all -> 0x01c1 }
                long r11 = r11 - r13
                long r13 = r1.sourcePos     // Catch:{ all -> 0x01c1 }
                int r0 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
                if (r0 >= 0) goto L_0x0040
                r4 = r6
                goto L_0x0088
            L_0x0040:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                long r6 = r0.getUpstreamPos()     // Catch:{ all -> 0x01c1 }
                long r9 = r1.sourcePos     // Catch:{ all -> 0x01c1 }
                long r6 = r6 - r9
                long r9 = java.lang.Math.min(r2, r6)     // Catch:{ all -> 0x01c1 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                okio.Buffer r2 = r0.getBuffer()     // Catch:{ all -> 0x01c1 }
                long r3 = r1.sourcePos     // Catch:{ all -> 0x01c1 }
                long r6 = r3 - r11
                r3 = r20
                r4 = r6
                r6 = r9
                r2.copyTo((okio.Buffer) r3, (long) r4, (long) r6)     // Catch:{ all -> 0x01c1 }
                long r2 = r1.sourcePos     // Catch:{ all -> 0x01c1 }
                long r2 = r2 + r9
                r1.sourcePos = r2     // Catch:{ all -> 0x01c1 }
                monitor-exit(r8)
                return r9
            L_0x0065:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                boolean r0 = r0.getComplete()     // Catch:{ all -> 0x01c1 }
                if (r0 == 0) goto L_0x006f
                monitor-exit(r8)
                return r9
            L_0x006f:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                java.lang.Thread r0 = r0.getUpstreamReader()     // Catch:{ all -> 0x01c1 }
                if (r0 == 0) goto L_0x007f
                okio.Timeout r0 = r1.timeout     // Catch:{ all -> 0x01c1 }
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                r0.waitUntilNotified(r6)     // Catch:{ all -> 0x01c1 }
                goto L_0x0018
            L_0x007f:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x01c1 }
                r0.setUpstreamReader(r7)     // Catch:{ all -> 0x01c1 }
            L_0x0088:
                monitor-exit(r8)
                r11 = 32
                if (r4 != r6) goto L_0x00ae
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this
                long r6 = r0.getUpstreamPos()
                long r8 = r1.sourcePos
                long r6 = r6 - r8
                long r8 = java.lang.Math.min(r2, r6)
                okhttp3.internal.cache2.FileOperator r2 = r1.fileOperator
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                long r3 = r1.sourcePos
                long r3 = r3 + r11
                r5 = r20
                r6 = r8
                r2.read(r3, r5, r6)
                long r2 = r1.sourcePos
                long r2 = r2 + r8
                r1.sourcePos = r2
                return r8
            L_0x00ae:
                r8 = 0
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019e }
                okio.Source r0 = r0.getUpstream()     // Catch:{ all -> 0x019e }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x019e }
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019e }
                okio.Buffer r4 = r4.getUpstreamBuffer()     // Catch:{ all -> 0x019e }
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019e }
                long r6 = r6.getBufferMaxSize()     // Catch:{ all -> 0x019e }
                long r14 = r0.read(r4, r6)     // Catch:{ all -> 0x019e }
                int r0 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
                if (r0 != 0) goto L_0x00f7
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019e }
                long r2 = r0.getUpstreamPos()     // Catch:{ all -> 0x019e }
                r0.commit(r2)     // Catch:{ all -> 0x019e }
                okhttp3.internal.cache2.Relay r2 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r2)
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f4 }
                java.lang.Thread r8 = (java.lang.Thread) r8     // Catch:{ all -> 0x00f4 }
                r0.setUpstreamReader(r8)     // Catch:{ all -> 0x00f4 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f4 }
                if (r0 == 0) goto L_0x00ec
                java.lang.Object r0 = (java.lang.Object) r0     // Catch:{ all -> 0x00f4 }
                r0.notifyAll()     // Catch:{ all -> 0x00f4 }
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00f4 }
                monitor-exit(r2)
                return r9
            L_0x00ec:
                java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x00f4 }
                java.lang.String r1 = "null cannot be cast to non-null type java.lang.Object"
                r0.<init>(r1)     // Catch:{ all -> 0x00f4 }
                throw r0     // Catch:{ all -> 0x00f4 }
            L_0x00f4:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            L_0x00f7:
                long r9 = java.lang.Math.min(r14, r2)     // Catch:{ all -> 0x019e }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019e }
                okio.Buffer r2 = r0.getUpstreamBuffer()     // Catch:{ all -> 0x019e }
                r6 = 0
                r3 = r20
                r4 = r6
                r6 = r9
                r2.copyTo((okio.Buffer) r3, (long) r4, (long) r6)     // Catch:{ all -> 0x019e }
                long r2 = r1.sourcePos     // Catch:{ all -> 0x019e }
                long r2 = r2 + r9
                r1.sourcePos = r2     // Catch:{ all -> 0x019e }
                okhttp3.internal.cache2.FileOperator r13 = r1.fileOperator     // Catch:{ all -> 0x019e }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)     // Catch:{ all -> 0x019e }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019e }
                long r2 = r0.getUpstreamPos()     // Catch:{ all -> 0x019e }
                long r2 = r2 + r11
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019e }
                okio.Buffer r0 = r0.getUpstreamBuffer()     // Catch:{ all -> 0x019e }
                okio.Buffer r16 = r0.clone()     // Catch:{ all -> 0x019e }
                r4 = r14
                r14 = r2
                r17 = r4
                r13.write(r14, r16, r17)     // Catch:{ all -> 0x019e }
                okhttp3.internal.cache2.Relay r2 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019e }
                monitor-enter(r2)     // Catch:{ all -> 0x019e }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019b }
                okio.Buffer r0 = r0.getBuffer()     // Catch:{ all -> 0x019b }
                okhttp3.internal.cache2.Relay r3 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019b }
                okio.Buffer r3 = r3.getUpstreamBuffer()     // Catch:{ all -> 0x019b }
                r0.write((okio.Buffer) r3, (long) r4)     // Catch:{ all -> 0x019b }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019b }
                okio.Buffer r0 = r0.getBuffer()     // Catch:{ all -> 0x019b }
                long r6 = r0.size()     // Catch:{ all -> 0x019b }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019b }
                long r11 = r0.getBufferMaxSize()     // Catch:{ all -> 0x019b }
                int r0 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                if (r0 <= 0) goto L_0x016c
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019b }
                okio.Buffer r0 = r0.getBuffer()     // Catch:{ all -> 0x019b }
                okhttp3.internal.cache2.Relay r3 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019b }
                okio.Buffer r3 = r3.getBuffer()     // Catch:{ all -> 0x019b }
                long r6 = r3.size()     // Catch:{ all -> 0x019b }
                okhttp3.internal.cache2.Relay r3 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019b }
                long r11 = r3.getBufferMaxSize()     // Catch:{ all -> 0x019b }
                long r6 = r6 - r11
                r0.skip(r6)     // Catch:{ all -> 0x019b }
            L_0x016c:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x019b }
                long r6 = r0.getUpstreamPos()     // Catch:{ all -> 0x019b }
                long r6 = r6 + r4
                r0.setUpstreamPos(r6)     // Catch:{ all -> 0x019b }
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x019b }
                monitor-exit(r2)     // Catch:{ all -> 0x019e }
                okhttp3.internal.cache2.Relay r2 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r2)
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0198 }
                java.lang.Thread r8 = (java.lang.Thread) r8     // Catch:{ all -> 0x0198 }
                r0.setUpstreamReader(r8)     // Catch:{ all -> 0x0198 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0198 }
                if (r0 == 0) goto L_0x0190
                java.lang.Object r0 = (java.lang.Object) r0     // Catch:{ all -> 0x0198 }
                r0.notifyAll()     // Catch:{ all -> 0x0198 }
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0198 }
                monitor-exit(r2)
                return r9
            L_0x0190:
                java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x0198 }
                java.lang.String r1 = "null cannot be cast to non-null type java.lang.Object"
                r0.<init>(r1)     // Catch:{ all -> 0x0198 }
                throw r0     // Catch:{ all -> 0x0198 }
            L_0x0198:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            L_0x019b:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x019e }
                throw r0     // Catch:{ all -> 0x019e }
            L_0x019e:
                r0 = move-exception
                okhttp3.internal.cache2.Relay r2 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r2)
                okhttp3.internal.cache2.Relay r3 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01be }
                java.lang.Thread r8 = (java.lang.Thread) r8     // Catch:{ all -> 0x01be }
                r3.setUpstreamReader(r8)     // Catch:{ all -> 0x01be }
                okhttp3.internal.cache2.Relay r1 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01be }
                if (r1 != 0) goto L_0x01b5
                java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x01be }
                java.lang.String r1 = "null cannot be cast to non-null type java.lang.Object"
                r0.<init>(r1)     // Catch:{ all -> 0x01be }
                throw r0     // Catch:{ all -> 0x01be }
            L_0x01b5:
                java.lang.Object r1 = (java.lang.Object) r1     // Catch:{ all -> 0x01be }
                r1.notifyAll()     // Catch:{ all -> 0x01be }
                kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01be }
                monitor-exit(r2)
                throw r0
            L_0x01be:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            L_0x01c1:
                r0 = move-exception
                monitor-exit(r8)
                throw r0
            L_0x01c4:
                java.lang.String r0 = "Check failed."
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(okio.Buffer, long):long");
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public void close() throws IOException {
            if (this.fileOperator != null) {
                this.fileOperator = null;
                RandomAccessFile randomAccessFile = null;
                synchronized (Relay.this) {
                    Relay relay = Relay.this;
                    relay.setSourceCount(relay.getSourceCount() - 1);
                    if (Relay.this.getSourceCount() == 0) {
                        randomAccessFile = Relay.this.getFile();
                        Relay.this.setFile((RandomAccessFile) null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                if (randomAccessFile != null) {
                    Util.closeQuietly((Closeable) randomAccessFile);
                }
            }
        }
    }

    @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo20735d2 = {"Lokhttp3/internal/cache2/Relay$Companion;", "", "()V", "FILE_HEADER_SIZE", "", "PREFIX_CLEAN", "Lokio/ByteString;", "PREFIX_DIRTY", "SOURCE_FILE", "", "SOURCE_UPSTREAM", "edit", "Lokhttp3/internal/cache2/Relay;", "file", "Ljava/io/File;", "upstream", "Lokio/Source;", "metadata", "bufferMaxSize", "read", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
    /* compiled from: Relay.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Relay edit(File file, Source source, ByteString byteString, long j) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(source, "upstream");
            Intrinsics.checkNotNullParameter(byteString, "metadata");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            Relay relay = new Relay(randomAccessFile, source, 0, byteString, j, (DefaultConstructorMarker) null);
            randomAccessFile.setLength(0);
            relay.writeHeader(Relay.PREFIX_DIRTY, -1, -1);
            return relay;
        }

        public final Relay read(File file) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "randomAccessFile.channel");
            FileOperator fileOperator = new FileOperator(channel);
            Buffer buffer = new Buffer();
            fileOperator.read(0, buffer, Relay.FILE_HEADER_SIZE);
            if (!(!Intrinsics.areEqual((Object) buffer.readByteString((long) Relay.PREFIX_CLEAN.size()), (Object) Relay.PREFIX_CLEAN))) {
                long readLong = buffer.readLong();
                long readLong2 = buffer.readLong();
                Buffer buffer2 = new Buffer();
                fileOperator.read(readLong + Relay.FILE_HEADER_SIZE, buffer2, readLong2);
                return new Relay(randomAccessFile, (Source) null, readLong, buffer2.readByteString(), 0, (DefaultConstructorMarker) null);
            }
            throw new IOException("unreadable cache file");
        }
    }
}
