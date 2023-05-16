package okhttp3.internal.publicsuffix;

import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\fJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0016\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000f\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, mo20735d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "()V", "listRead", "Ljava/util/concurrent/atomic/AtomicBoolean;", "publicSuffixExceptionListBytes", "", "publicSuffixListBytes", "readCompleteLatch", "Ljava/util/concurrent/CountDownLatch;", "findMatchingRule", "", "", "domainLabels", "getEffectiveTldPlusOne", "domain", "readTheList", "", "readTheListUninterruptibly", "setListBytes", "splitDomain", "Companion", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: PublicSuffixDatabase.kt */
public final class PublicSuffixDatabase {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final char EXCEPTION_MARKER = '!';
    private static final List<String> PREVAILING_RULE = CollectionsKt.listOf("*");
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private static final byte[] WILDCARD_LABEL = {(byte) 42};
    /* access modifiers changed from: private */
    public static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private byte[] publicSuffixExceptionListBytes;
    /* access modifiers changed from: private */
    public byte[] publicSuffixListBytes;
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    public static final /* synthetic */ byte[] access$getPublicSuffixListBytes$p(PublicSuffixDatabase publicSuffixDatabase) {
        byte[] bArr = publicSuffixDatabase.publicSuffixListBytes;
        if (bArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
        }
        return bArr;
    }

    public final String getEffectiveTldPlusOne(String str) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(str, "domain");
        String unicode = IDN.toUnicode(str);
        Intrinsics.checkNotNullExpressionValue(unicode, "unicodeDomain");
        List<String> splitDomain = splitDomain(unicode);
        List<String> findMatchingRule = findMatchingRule(splitDomain);
        if (splitDomain.size() == findMatchingRule.size() && findMatchingRule.get(0).charAt(0) != '!') {
            return null;
        }
        if (findMatchingRule.get(0).charAt(0) == '!') {
            i2 = splitDomain.size();
            i = findMatchingRule.size();
        } else {
            i2 = splitDomain.size();
            i = findMatchingRule.size() + 1;
        }
        return SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence(splitDomain(str)), i2 - i), ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    private final List<String> splitDomain(String str) {
        List<String> split$default = StringsKt.split$default((CharSequence) str, new char[]{'.'}, false, 0, 6, (Object) null);
        return Intrinsics.areEqual((Object) (String) CollectionsKt.last(split$default), (Object) "") ? CollectionsKt.dropLast(split$default, 1) : split$default;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.String> findMatchingRule(java.util.List<java.lang.String> r12) {
        /*
            r11 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r11.listRead
            boolean r0 = r0.get()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0016
            java.util.concurrent.atomic.AtomicBoolean r0 = r11.listRead
            boolean r0 = r0.compareAndSet(r1, r2)
            if (r0 == 0) goto L_0x0016
            r11.readTheListUninterruptibly()
            goto L_0x0023
        L_0x0016:
            java.util.concurrent.CountDownLatch r0 = r11.readCompleteLatch     // Catch:{ InterruptedException -> 0x001c }
            r0.await()     // Catch:{ InterruptedException -> 0x001c }
            goto L_0x0023
        L_0x001c:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
        L_0x0023:
            r0 = r11
            okhttp3.internal.publicsuffix.PublicSuffixDatabase r0 = (okhttp3.internal.publicsuffix.PublicSuffixDatabase) r0
            byte[] r0 = r0.publicSuffixListBytes
            if (r0 == 0) goto L_0x002c
            r0 = r2
            goto L_0x002d
        L_0x002c:
            r0 = r1
        L_0x002d:
            if (r0 == 0) goto L_0x012b
            int r0 = r12.size()
            byte[][] r3 = new byte[r0][]
            r4 = r1
        L_0x0036:
            if (r4 >= r0) goto L_0x0058
            java.lang.Object r5 = r12.get(r4)
            java.lang.String r5 = (java.lang.String) r5
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8
            java.lang.String r7 = "UTF_8"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            java.lang.String r7 = "null cannot be cast to non-null type java.lang.String"
            java.util.Objects.requireNonNull(r5, r7)
            byte[] r5 = r5.getBytes(r6)
            java.lang.String r6 = "(this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r3[r4] = r5
            int r4 = r4 + 1
            goto L_0x0036
        L_0x0058:
            byte[][] r3 = (byte[][]) r3
            r12 = 0
            java.lang.String r12 = (java.lang.String) r12
            int r0 = r3.length
            r4 = r1
        L_0x005f:
            java.lang.String r5 = "publicSuffixListBytes"
            if (r4 >= r0) goto L_0x0076
            okhttp3.internal.publicsuffix.PublicSuffixDatabase$Companion r6 = Companion
            byte[] r7 = r11.publicSuffixListBytes
            if (r7 != 0) goto L_0x006c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x006c:
            java.lang.String r6 = r6.binarySearch(r7, r3, r4)
            if (r6 == 0) goto L_0x0073
            goto L_0x0077
        L_0x0073:
            int r4 = r4 + 1
            goto L_0x005f
        L_0x0076:
            r6 = r12
        L_0x0077:
            r0 = r3
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            int r4 = r0.length
            if (r4 <= r2) goto L_0x00a2
            java.lang.Object r4 = r0.clone()
            byte[][] r4 = (byte[][]) r4
            r7 = r4
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            int r7 = r7.length
            int r7 = r7 - r2
            r8 = r1
        L_0x0089:
            if (r8 >= r7) goto L_0x00a2
            byte[] r9 = WILDCARD_LABEL
            r4[r8] = r9
            okhttp3.internal.publicsuffix.PublicSuffixDatabase$Companion r9 = Companion
            byte[] r10 = r11.publicSuffixListBytes
            if (r10 != 0) goto L_0x0098
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x0098:
            java.lang.String r9 = r9.binarySearch(r10, r4, r8)
            if (r9 == 0) goto L_0x009f
            goto L_0x00a3
        L_0x009f:
            int r8 = r8 + 1
            goto L_0x0089
        L_0x00a2:
            r9 = r12
        L_0x00a3:
            if (r9 == 0) goto L_0x00c0
            int r0 = r0.length
            int r0 = r0 - r2
            r4 = r1
        L_0x00a8:
            if (r4 >= r0) goto L_0x00c0
            okhttp3.internal.publicsuffix.PublicSuffixDatabase$Companion r5 = Companion
            byte[] r7 = r11.publicSuffixExceptionListBytes
            if (r7 != 0) goto L_0x00b5
            java.lang.String r8 = "publicSuffixExceptionListBytes"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
        L_0x00b5:
            java.lang.String r5 = r5.binarySearch(r7, r3, r4)
            if (r5 == 0) goto L_0x00bd
            r12 = r5
            goto L_0x00c0
        L_0x00bd:
            int r4 = r4 + 1
            goto L_0x00a8
        L_0x00c0:
            r11 = 46
            if (r12 == 0) goto L_0x00e7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r3 = 33
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r12 = r0.append(r12)
            java.lang.String r12 = r12.toString()
            r3 = r12
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            char[] r4 = new char[r2]
            r4[r1] = r11
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            java.util.List r11 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r3, (char[]) r4, (boolean) r5, (int) r6, (int) r7, (java.lang.Object) r8)
            return r11
        L_0x00e7:
            if (r6 != 0) goto L_0x00ee
            if (r9 != 0) goto L_0x00ee
            java.util.List<java.lang.String> r11 = PREVAILING_RULE
            return r11
        L_0x00ee:
            if (r6 == 0) goto L_0x0102
            r3 = r6
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            char[] r4 = new char[r2]
            r4[r1] = r11
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            java.util.List r12 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r3, (char[]) r4, (boolean) r5, (int) r6, (int) r7, (java.lang.Object) r8)
            if (r12 == 0) goto L_0x0102
            goto L_0x0106
        L_0x0102:
            java.util.List r12 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0106:
            if (r9 == 0) goto L_0x011a
            r3 = r9
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            char[] r4 = new char[r2]
            r4[r1] = r11
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            java.util.List r11 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r3, (char[]) r4, (boolean) r5, (int) r6, (int) r7, (java.lang.Object) r8)
            if (r11 == 0) goto L_0x011a
            goto L_0x011e
        L_0x011a:
            java.util.List r11 = kotlin.collections.CollectionsKt.emptyList()
        L_0x011e:
            int r0 = r12.size()
            int r1 = r11.size()
            if (r0 <= r1) goto L_0x0129
            goto L_0x012a
        L_0x0129:
            r12 = r11
        L_0x012a:
            return r12
        L_0x012b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "Unable to load publicsuffixes.gz resource from the classpath."
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.findMatchingRule(java.util.List):java.util.List");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0029 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void readTheListUninterruptibly() {
        /*
            r4 = this;
            r0 = 0
        L_0x0001:
            r4.readTheList()     // Catch:{ InterruptedIOException -> 0x0029, IOException -> 0x0010 }
            if (r0 == 0) goto L_0x000d
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            r4.interrupt()
        L_0x000d:
            return
        L_0x000e:
            r4 = move-exception
            goto L_0x002e
        L_0x0010:
            r4 = move-exception
            okhttp3.internal.platform.Platform$Companion r1 = okhttp3.internal.platform.Platform.Companion     // Catch:{ all -> 0x000e }
            okhttp3.internal.platform.Platform r1 = r1.get()     // Catch:{ all -> 0x000e }
            java.lang.String r2 = "Failed to read public suffix list"
            r3 = 5
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ all -> 0x000e }
            r1.log(r2, r3, r4)     // Catch:{ all -> 0x000e }
            if (r0 == 0) goto L_0x0028
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            r4.interrupt()
        L_0x0028:
            return
        L_0x0029:
            java.lang.Thread.interrupted()     // Catch:{ all -> 0x000e }
            r0 = 1
            goto L_0x0001
        L_0x002e:
            if (r0 == 0) goto L_0x0037
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
        L_0x0037:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.readTheListUninterruptibly():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        kotlin.p007io.CloseableKt.closeFinally(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void readTheList() throws java.io.IOException {
        /*
            r6 = this;
            java.lang.Class<okhttp3.internal.publicsuffix.PublicSuffixDatabase> r0 = okhttp3.internal.publicsuffix.PublicSuffixDatabase.class
            java.lang.String r1 = "publicsuffixes.gz"
            java.io.InputStream r0 = r0.getResourceAsStream(r1)
            if (r0 == 0) goto L_0x0056
            okio.GzipSource r1 = new okio.GzipSource
            okio.Source r0 = okio.Okio.source((java.io.InputStream) r0)
            r1.<init>(r0)
            okio.Source r1 = (okio.Source) r1
            okio.BufferedSource r0 = okio.Okio.buffer((okio.Source) r1)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = r0
            okio.BufferedSource r2 = (okio.BufferedSource) r2     // Catch:{ all -> 0x004f }
            int r3 = r2.readInt()     // Catch:{ all -> 0x004f }
            long r3 = (long) r3     // Catch:{ all -> 0x004f }
            byte[] r3 = r2.readByteArray(r3)     // Catch:{ all -> 0x004f }
            int r4 = r2.readInt()     // Catch:{ all -> 0x004f }
            long r4 = (long) r4     // Catch:{ all -> 0x004f }
            byte[] r2 = r2.readByteArray(r4)     // Catch:{ all -> 0x004f }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004f }
            kotlin.p007io.CloseableKt.closeFinally(r0, r1)
            monitor-enter(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ all -> 0x004c }
            r6.publicSuffixListBytes = r3     // Catch:{ all -> 0x004c }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ all -> 0x004c }
            r6.publicSuffixExceptionListBytes = r2     // Catch:{ all -> 0x004c }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004c }
            monitor-exit(r6)
            java.util.concurrent.CountDownLatch r6 = r6.readCompleteLatch
            r6.countDown()
            return
        L_0x004c:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x004f:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r1 = move-exception
            kotlin.p007io.CloseableKt.closeFinally(r0, r6)
            throw r1
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.readTheList():void");
    }

    public final void setListBytes(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNullParameter(bArr, "publicSuffixListBytes");
        Intrinsics.checkNotNullParameter(bArr2, "publicSuffixExceptionListBytes");
        this.publicSuffixListBytes = bArr;
        this.publicSuffixExceptionListBytes = bArr2;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }

    @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\fJ)\u0010\u000e\u001a\u0004\u0018\u00010\u0007*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo20735d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;", "", "()V", "EXCEPTION_MARKER", "", "PREVAILING_RULE", "", "", "PUBLIC_SUFFIX_RESOURCE", "WILDCARD_LABEL", "", "instance", "Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "get", "binarySearch", "labels", "", "labelIndex", "", "([B[[BI)Ljava/lang/String;", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
    /* compiled from: PublicSuffixDatabase.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PublicSuffixDatabase get() {
            return PublicSuffixDatabase.instance;
        }

        /* access modifiers changed from: private */
        public final String binarySearch(byte[] bArr, byte[][] bArr2, int i) {
            int i2;
            boolean z;
            int i3;
            int and;
            byte[] bArr3 = bArr;
            int length = bArr3.length;
            String str = null;
            int i4 = 0;
            while (i4 < length) {
                int i5 = (i4 + length) / 2;
                while (i5 > -1 && bArr3[i5] != ((byte) 10)) {
                    i5--;
                }
                int i6 = i5 + 1;
                int i7 = 1;
                while (true) {
                    i2 = i6 + i7;
                    if (bArr3[i2] == ((byte) 10)) {
                        break;
                    }
                    i7++;
                }
                int i8 = i2 - i6;
                int i9 = i;
                boolean z2 = false;
                int i10 = 0;
                int i11 = 0;
                while (true) {
                    if (z2) {
                        i3 = 46;
                        z = false;
                    } else {
                        z = z2;
                        i3 = Util.and(bArr2[i9][i10], 255);
                    }
                    and = i3 - Util.and(bArr3[i6 + i11], 255);
                    if (and == 0) {
                        i11++;
                        i10++;
                        if (i11 == i8) {
                            break;
                        } else if (bArr2[i9].length != i10) {
                            z2 = z;
                        } else if (i9 == ((Object[]) bArr2).length - 1) {
                            break;
                        } else {
                            i9++;
                            i10 = -1;
                            z2 = true;
                        }
                    } else {
                        break;
                    }
                }
                if (and >= 0) {
                    if (and <= 0) {
                        int i12 = i8 - i11;
                        int length2 = bArr2[i9].length - i10;
                        int length3 = ((Object[]) bArr2).length;
                        for (int i13 = i9 + 1; i13 < length3; i13++) {
                            length2 += bArr2[i13].length;
                        }
                        if (length2 >= i12) {
                            if (length2 <= i12) {
                                Charset charset = StandardCharsets.UTF_8;
                                Intrinsics.checkNotNullExpressionValue(charset, "UTF_8");
                                return new String(bArr3, i6, i8, charset);
                            }
                        }
                    }
                    i4 = i2 + 1;
                }
                length = i6 - 1;
            }
            return str;
        }
    }
}
