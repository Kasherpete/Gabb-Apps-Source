package com.datadog.android.rum.resource;

import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumErrorSource;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0007\u0018\u0000 12\u00020\u0001:\u00011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u001b\u001a\u00020\u001cH\u0016J4\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e2\u0006\u0010\u001f\u001a\u00020\u00042\u0017\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u001e0!¢\u0006\u0002\b\"H\u0002¢\u0006\u0002\u0010#J\b\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\u001cH\u0016J\b\u0010(\u001a\u00020\u000bH\u0016J\b\u0010)\u001a\u00020\u001cH\u0016J\u0010\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+H\u0016J \u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\u001cH\u0016J\b\u0010.\u001a\u00020%H\u0016J\u0010\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0007H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013¨\u00062"}, mo20735d2 = {"Lcom/datadog/android/rum/resource/RumResourceInputStream;", "Ljava/io/InputStream;", "delegate", "url", "", "(Ljava/io/InputStream;Ljava/lang/String;)V", "callStart", "", "getDelegate", "()Ljava/io/InputStream;", "failed", "", "getFailed$dd_sdk_android_release", "()Z", "setFailed$dd_sdk_android_release", "(Z)V", "firstByte", "key", "getKey$dd_sdk_android_release", "()Ljava/lang/String;", "lastByte", "size", "getSize$dd_sdk_android_release", "()J", "setSize$dd_sdk_android_release", "(J)V", "getUrl", "available", "", "callWithErrorTracking", "T", "errorMessage", "operation", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "close", "", "mark", "readlimit", "markSupported", "read", "b", "", "off", "len", "reset", "skip", "n", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumResourceInputStream.kt */
public final class RumResourceInputStream extends InputStream {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ERROR_CLOSE = "Error closing input stream";
    public static final String ERROR_MARK = "Error marking input stream";
    public static final String ERROR_READ = "Error reading from input stream";
    public static final String ERROR_RESET = "Error resetting input stream";
    public static final String ERROR_SKIP = "Error skipping bytes from input stream";
    public static final String METHOD = "GET";
    /* access modifiers changed from: private */
    public long callStart = System.nanoTime();
    private final InputStream delegate;
    private boolean failed;
    /* access modifiers changed from: private */
    public long firstByte;
    private final String key;
    /* access modifiers changed from: private */
    public long lastByte;
    private long size;
    private final String url;

    public final InputStream getDelegate() {
        return this.delegate;
    }

    public final String getUrl() {
        return this.url;
    }

    public RumResourceInputStream(InputStream inputStream, String str) {
        Intrinsics.checkNotNullParameter(inputStream, "delegate");
        Intrinsics.checkNotNullParameter(str, "url");
        this.delegate = inputStream;
        this.url = str;
        String str2 = inputStream.getClass().getSimpleName() + "@" + System.identityHashCode(inputStream);
        this.key = str2;
        RumMonitor rumMonitor = GlobalRum.get();
        rumMonitor.startResource(str2, "GET", str, MapsKt.emptyMap());
        if (rumMonitor instanceof AdvancedRumMonitor) {
            ((AdvancedRumMonitor) rumMonitor).waitForResourceTiming(str2);
        }
    }

    public final String getKey$dd_sdk_android_release() {
        return this.key;
    }

    public final long getSize$dd_sdk_android_release() {
        return this.size;
    }

    public final void setSize$dd_sdk_android_release(long j) {
        this.size = j;
    }

    public final boolean getFailed$dd_sdk_android_release() {
        return this.failed;
    }

    public final void setFailed$dd_sdk_android_release(boolean z) {
        this.failed = z;
    }

    public int read() {
        if (this.firstByte == 0) {
            this.firstByte = System.nanoTime();
        }
        return ((Number) callWithErrorTracking(ERROR_READ, new RumResourceInputStream$read$1(this))).intValue();
    }

    public int read(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "b");
        if (this.firstByte == 0) {
            this.firstByte = System.nanoTime();
        }
        return ((Number) callWithErrorTracking(ERROR_READ, new RumResourceInputStream$read$2(bArr, this))).intValue();
    }

    public int read(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "b");
        if (this.firstByte == 0) {
            this.firstByte = System.nanoTime();
        }
        return ((Number) callWithErrorTracking(ERROR_READ, new RumResourceInputStream$read$3(bArr, i, i2, this))).intValue();
    }

    public int available() {
        return ((Number) callWithErrorTracking(ERROR_READ, RumResourceInputStream$available$1.INSTANCE)).intValue();
    }

    public long skip(long j) {
        return ((Number) callWithErrorTracking(ERROR_SKIP, new RumResourceInputStream$skip$1(j))).longValue();
    }

    public boolean markSupported() {
        return ((Boolean) callWithErrorTracking(ERROR_READ, RumResourceInputStream$markSupported$1.INSTANCE)).booleanValue();
    }

    public void mark(int i) {
        callWithErrorTracking(ERROR_MARK, new RumResourceInputStream$mark$1(i));
    }

    public void reset() {
        callWithErrorTracking(ERROR_RESET, RumResourceInputStream$reset$1.INSTANCE);
    }

    public void close() {
        callWithErrorTracking(ERROR_CLOSE, new RumResourceInputStream$close$1(this));
    }

    private final <T> T callWithErrorTracking(String str, Function1<? super InputStream, ? extends T> function1) {
        try {
            return function1.invoke(this.delegate);
        } catch (Throwable th) {
            if (!this.failed) {
                this.failed = true;
                RumMonitor.DefaultImpls.stopResourceWithError$default(GlobalRum.get(), this.key, (Integer) null, str, RumErrorSource.SOURCE, th, (Map) null, 32, (Object) null);
            }
            throw th;
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/rum/resource/RumResourceInputStream$Companion;", "", "()V", "ERROR_CLOSE", "", "ERROR_MARK", "ERROR_READ", "ERROR_RESET", "ERROR_SKIP", "METHOD", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumResourceInputStream.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
