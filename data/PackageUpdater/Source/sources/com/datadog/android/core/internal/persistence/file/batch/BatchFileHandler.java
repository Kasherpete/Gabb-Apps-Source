package com.datadog.android.core.internal.persistence.file.batch;

import com.datadog.android.core.internal.persistence.file.EncryptedFileHandler;
import com.datadog.android.core.internal.persistence.file.EventMeta;
import com.datadog.android.core.internal.persistence.file.FileExtKt;
import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.security.Encryption;
import com.google.gson.JsonParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p007io.FilesKt;

@Metadata(mo20734d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 (2\u00020\u0001:\u0002()BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005\u0012#\b\u0002\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\u0002\u0010\rJ \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0018\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017H\u0002J\u0018\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060!2\u0006\u0010\u001a\u001a\u00020\u0017H\u0016J\u001e\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0011\u0018\u00010#2\u0006\u0010$\u001a\u00020%H\u0002J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060!2\u0006\u0010\u001a\u001a\u00020\u0017H\u0002J \u0010'\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R)\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R)\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileHandler;", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "internalLogger", "Lcom/datadog/android/log/Logger;", "metaGenerator", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "data", "metaParser", "metaBytes", "Lcom/datadog/android/core/internal/persistence/file/EventMeta;", "(Lcom/datadog/android/log/Logger;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "checkReadSizeExpected", "", "expected", "", "actual", "operation", "", "delete", "target", "Ljava/io/File;", "lockFileAndWriteData", "", "file", "append", "moveFile", "destDir", "moveFiles", "srcDir", "readData", "", "readEventHeader", "Lkotlin/Pair;", "stream", "Ljava/io/InputStream;", "readFileData", "writeData", "Companion", "MetaTooBigException", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BatchFileHandler.kt */
public final class BatchFileHandler implements FileHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ERROR_DELETE = "Unable to delete file: %s";
    public static final String ERROR_EOF_AT_META_SIZE_BYTE = "Cannot read meta size byte, because EOF reached.";
    public static final String ERROR_EOF_AT_VERSION_BYTE = "Cannot read version byte, because EOF reached.";
    public static final String ERROR_FAILED_META_PARSE = "Failed to parse meta bytes, stopping file read.";
    public static final String ERROR_MOVE_NOT_DIR = "Unable to move files; file is not a directory: %s";
    public static final String ERROR_MOVE_NO_DST = "Unable to move files; could not create directory: %s";
    public static final String ERROR_READ = "Unable to read data from file: %s";
    public static final String ERROR_WRITE = "Unable to write data to file: %s";
    public static final byte HEADER_VERSION = 1;
    public static final String INFO_MOVE_NO_SRC = "Unable to move files; source directory does not exist: %s";
    public static final int MAX_META_SIZE_BYTES = 255;
    public static final String WARNING_NOT_ALL_DATA_READ = "File %s is probably corrupted, not all content was read.";
    private final Logger internalLogger;
    private final Function1<byte[], byte[]> metaGenerator;
    private final Function1<byte[], EventMeta> metaParser;

    public BatchFileHandler(Logger logger, Function1<? super byte[], byte[]> function1, Function1<? super byte[], EventMeta> function12) {
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        Intrinsics.checkNotNullParameter(function1, "metaGenerator");
        Intrinsics.checkNotNullParameter(function12, "metaParser");
        this.internalLogger = logger;
        this.metaGenerator = function1;
        this.metaParser = function12;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BatchFileHandler(Logger logger, Function1 function1, Function1 function12, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(logger, (i & 2) != 0 ? C08601.INSTANCE : function1, (i & 4) != 0 ? C08612.INSTANCE : function12);
    }

    public boolean writeData(File file, byte[] bArr, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(bArr, MPDbAdapter.KEY_DATA);
        try {
            lockFileAndWriteData(file, z, bArr);
            return true;
        } catch (IOException e) {
            Logger logger = this.internalLogger;
            String format = String.format(Locale.US, ERROR_WRITE, Arrays.copyOf(new Object[]{file.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger, format, e, (Map) null, 4, (Object) null);
        } catch (SecurityException e2) {
            Logger logger2 = this.internalLogger;
            String format2 = String.format(Locale.US, ERROR_WRITE, Arrays.copyOf(new Object[]{file.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger2, format2, e2, (Map) null, 4, (Object) null);
        }
        return false;
    }

    public List<byte[]> readData(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            return readFileData(file);
        } catch (IOException e) {
            Logger logger = this.internalLogger;
            String format = String.format(Locale.US, ERROR_READ, Arrays.copyOf(new Object[]{file.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger, format, e, (Map) null, 4, (Object) null);
            return CollectionsKt.emptyList();
        } catch (SecurityException e2) {
            Logger logger2 = this.internalLogger;
            String format2 = String.format(Locale.US, ERROR_READ, Arrays.copyOf(new Object[]{file.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger2, format2, e2, (Map) null, 4, (Object) null);
            return CollectionsKt.emptyList();
        }
    }

    public boolean delete(File file) {
        Intrinsics.checkNotNullParameter(file, "target");
        try {
            return FilesKt.deleteRecursively(file);
        } catch (FileNotFoundException e) {
            Logger logger = this.internalLogger;
            String format = String.format(Locale.US, "Unable to delete file: %s", Arrays.copyOf(new Object[]{file.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger, format, e, (Map) null, 4, (Object) null);
            return false;
        } catch (SecurityException e2) {
            Logger logger2 = this.internalLogger;
            String format2 = String.format(Locale.US, "Unable to delete file: %s", Arrays.copyOf(new Object[]{file.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger2, format2, e2, (Map) null, 4, (Object) null);
            return false;
        }
    }

    public boolean moveFiles(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "srcDir");
        Intrinsics.checkNotNullParameter(file2, "destDir");
        if (!FileExtKt.existsSafe(file)) {
            Logger logger = this.internalLogger;
            String format = String.format(Locale.US, INFO_MOVE_NO_SRC, Arrays.copyOf(new Object[]{file.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
            Logger.i$default(logger, format, (Throwable) null, (Map) null, 6, (Object) null);
            return true;
        } else if (!FileExtKt.isDirectorySafe(file)) {
            Logger logger2 = this.internalLogger;
            String format2 = String.format(Locale.US, ERROR_MOVE_NOT_DIR, Arrays.copyOf(new Object[]{file.getPath()}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, this, *args)");
            LogUtilsKt.errorWithTelemetry$default(logger2, format2, (Throwable) null, (Map) null, 6, (Object) null);
            return false;
        } else {
            if (!FileExtKt.existsSafe(file2)) {
                if (!FileExtKt.mkdirsSafe(file2)) {
                    Logger logger3 = this.internalLogger;
                    String format3 = String.format(Locale.US, ERROR_MOVE_NO_DST, Arrays.copyOf(new Object[]{file.getPath()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format3, "format(locale, this, *args)");
                    LogUtilsKt.errorWithTelemetry$default(logger3, format3, (Throwable) null, (Map) null, 6, (Object) null);
                    return false;
                }
            } else if (!FileExtKt.isDirectorySafe(file2)) {
                Logger logger4 = this.internalLogger;
                String format4 = String.format(Locale.US, ERROR_MOVE_NOT_DIR, Arrays.copyOf(new Object[]{file2.getPath()}, 1));
                Intrinsics.checkNotNullExpressionValue(format4, "format(locale, this, *args)");
                LogUtilsKt.errorWithTelemetry$default(logger4, format4, (Throwable) null, (Map) null, 6, (Object) null);
                return false;
            }
            File[] listFilesSafe = FileExtKt.listFilesSafe(file);
            if (listFilesSafe == null) {
                listFilesSafe = new File[0];
            }
            int length = listFilesSafe.length;
            int i = 0;
            while (i < length) {
                File file3 = listFilesSafe[i];
                i++;
                if (!moveFile(file3, file2)) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0057, code lost:
        kotlin.p007io.CloseableKt.closeFinally(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void lockFileAndWriteData(java.io.File r7, boolean r8, byte[] r9) throws java.io.IOException {
        /*
            r6 = this;
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r0.<init>(r7, r8)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r7 = r0
            java.io.FileOutputStream r7 = (java.io.FileOutputStream) r7     // Catch:{ all -> 0x0054 }
            java.nio.channels.FileChannel r8 = r7.getChannel()     // Catch:{ all -> 0x0054 }
            java.nio.channels.FileLock r8 = r8.lock()     // Catch:{ all -> 0x0054 }
            java.lang.String r1 = "outputStream.channel.lock()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)     // Catch:{ all -> 0x0054 }
            kotlin.jvm.functions.Function1<byte[], byte[]> r6 = r6.metaGenerator     // Catch:{ all -> 0x004f }
            java.lang.Object r6 = r6.invoke(r9)     // Catch:{ all -> 0x004f }
            byte[] r6 = (byte[]) r6     // Catch:{ all -> 0x004f }
            int r1 = r6.length     // Catch:{ all -> 0x004f }
            r2 = 255(0xff, float:3.57E-43)
            if (r1 > r2) goto L_0x0047
            int r1 = r6.length     // Catch:{ all -> 0x004f }
            r2 = 2
            int r1 = r1 + r2
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x004f }
            r3 = 1
            r4 = 0
            r1[r4] = r3     // Catch:{ all -> 0x004f }
            int r5 = r6.length     // Catch:{ all -> 0x004f }
            byte r5 = (byte) r5     // Catch:{ all -> 0x004f }
            r1[r3] = r5     // Catch:{ all -> 0x004f }
            int r3 = r6.length     // Catch:{ all -> 0x004f }
            com.datadog.android.core.internal.utils.ByteArrayExtKt.copyTo(r6, r4, r1, r2, r3)     // Catch:{ all -> 0x004f }
            r7.write(r1)     // Catch:{ all -> 0x004f }
            r7.write(r9)     // Catch:{ all -> 0x004f }
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004f }
            r8.release()     // Catch:{ all -> 0x0054 }
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0054 }
            r6 = 0
            kotlin.p007io.CloseableKt.closeFinally(r0, r6)
            return
        L_0x0047:
            com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler$MetaTooBigException r6 = new com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler$MetaTooBigException     // Catch:{ all -> 0x004f }
            java.lang.String r7 = "Meta size is bigger than limit of 255 bytes, cannot write data."
            r6.<init>(r7)     // Catch:{ all -> 0x004f }
            throw r6     // Catch:{ all -> 0x004f }
        L_0x004f:
            r6 = move-exception
            r8.release()     // Catch:{ all -> 0x0054 }
            throw r6     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r7 = move-exception
            kotlin.p007io.CloseableKt.closeFinally(r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler.lockFileAndWriteData(java.io.File, boolean, byte[]):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009b, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009c, code lost:
        kotlin.p007io.CloseableKt.closeFinally(r2, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009f, code lost:
        throw r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<byte[]> readFileData(java.io.File r12) throws java.io.IOException {
        /*
            r11 = this;
            long r0 = com.datadog.android.core.internal.persistence.file.FileExtKt.lengthSafe(r12)
            int r0 = (int) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r1 = (java.util.List) r1
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r2.<init>(r12)
            java.io.InputStream r2 = (java.io.InputStream) r2
            boolean r3 = r2 instanceof java.io.BufferedInputStream
            if (r3 == 0) goto L_0x001a
            java.io.BufferedInputStream r2 = (java.io.BufferedInputStream) r2
            goto L_0x0022
        L_0x001a:
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream
            r4 = 8192(0x2000, float:1.14794E-41)
            r3.<init>(r2, r4)
            r2 = r3
        L_0x0022:
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            r4 = r2
            java.io.BufferedInputStream r4 = (java.io.BufferedInputStream) r4     // Catch:{ all -> 0x0099 }
        L_0x0028:
            r5 = 0
            if (r0 <= 0) goto L_0x0066
            r6 = r4
            java.io.InputStream r6 = (java.io.InputStream) r6     // Catch:{ all -> 0x0099 }
            kotlin.Pair r6 = r11.readEventHeader(r6)     // Catch:{ all -> 0x0099 }
            if (r6 != 0) goto L_0x0035
            goto L_0x0066
        L_0x0035:
            java.lang.Object r7 = r6.component1()     // Catch:{ all -> 0x0099 }
            com.datadog.android.core.internal.persistence.file.EventMeta r7 = (com.datadog.android.core.internal.persistence.file.EventMeta) r7     // Catch:{ all -> 0x0099 }
            java.lang.Object r6 = r6.component2()     // Catch:{ all -> 0x0099 }
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ all -> 0x0099 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x0099 }
            int r8 = r7.getEventSize()     // Catch:{ all -> 0x0099 }
            byte[] r8 = new byte[r8]     // Catch:{ all -> 0x0099 }
            int r9 = r7.getEventSize()     // Catch:{ all -> 0x0099 }
            int r9 = r4.read(r8, r5, r9)     // Catch:{ all -> 0x0099 }
            int r7 = r7.getEventSize()     // Catch:{ all -> 0x0099 }
            java.lang.String r10 = "read event"
            boolean r7 = r11.checkReadSizeExpected(r7, r9, r10)     // Catch:{ all -> 0x0099 }
            if (r7 != 0) goto L_0x0060
            goto L_0x0066
        L_0x0060:
            r1.add(r8)     // Catch:{ all -> 0x0099 }
            int r6 = r6 + r9
            int r0 = r0 - r6
            goto L_0x0028
        L_0x0066:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0099 }
            kotlin.p007io.CloseableKt.closeFinally(r2, r3)
            if (r0 == 0) goto L_0x0098
            java.util.Locale r0 = java.util.Locale.US
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r12 = r12.getPath()
            r3[r5] = r12
            java.lang.Object[] r12 = java.util.Arrays.copyOf(r3, r2)
            java.lang.String r2 = "File %s is probably corrupted, not all content was read."
            java.lang.String r12 = java.lang.String.format(r0, r2, r12)
            java.lang.String r0 = "format(locale, this, *args)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            com.datadog.android.log.Logger r3 = com.datadog.android.core.internal.utils.RuntimeUtilsKt.getDevLogger()
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            r4 = r12
            com.datadog.android.log.Logger.e$default(r3, r4, r5, r6, r7, r8)
            com.datadog.android.log.Logger r3 = r11.internalLogger
            com.datadog.android.log.internal.utils.LogUtilsKt.errorWithTelemetry$default(r3, r4, r5, r6, r7, r8)
        L_0x0098:
            return r1
        L_0x0099:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x009b }
        L_0x009b:
            r12 = move-exception
            kotlin.p007io.CloseableKt.closeFinally(r2, r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler.readFileData(java.io.File):java.util.List");
    }

    private final boolean moveFile(File file, File file2) {
        return FileExtKt.renameToSafe(file, new File(file2, file.getName()));
    }

    private final Pair<EventMeta, Integer> readEventHeader(InputStream inputStream) {
        if (inputStream.read() < 0) {
            Logger.e$default(this.internalLogger, ERROR_EOF_AT_VERSION_BYTE, (Throwable) null, (Map) null, 6, (Object) null);
            return null;
        }
        int read = inputStream.read();
        if (read < 0) {
            Logger.e$default(this.internalLogger, ERROR_EOF_AT_META_SIZE_BYTE, (Throwable) null, (Map) null, 6, (Object) null);
            return null;
        }
        byte[] bArr = new byte[read];
        int read2 = inputStream.read(bArr, 0, read);
        if (!checkReadSizeExpected(read, read2, "read meta")) {
            return null;
        }
        try {
            return TuplesKt.m78to(this.metaParser.invoke(bArr), Integer.valueOf(read2 + 2));
        } catch (JsonParseException e) {
            Logger.e$default(this.internalLogger, ERROR_FAILED_META_PARSE, e, (Map) null, 4, (Object) null);
            return null;
        }
    }

    private final boolean checkReadSizeExpected(int i, int i2, String str) {
        if (i == i2) {
            return true;
        }
        Logger.e$default(this.internalLogger, "Number of bytes read for operation='" + str + "' doesn't match with expected: expected=" + i + ", actual=" + i2, (Throwable) null, (Map) null, 6, (Object) null);
        return false;
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileHandler$MetaTooBigException;", "Ljava/io/IOException;", "message", "", "(Ljava/lang/String;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: BatchFileHandler.kt */
    public static final class MetaTooBigException extends IOException {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MetaTooBigException(String str) {
            super(str);
            Intrinsics.checkNotNullParameter(str, "message");
        }
    }

    @Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileHandler$Companion;", "", "()V", "ERROR_DELETE", "", "ERROR_EOF_AT_META_SIZE_BYTE", "ERROR_EOF_AT_VERSION_BYTE", "ERROR_FAILED_META_PARSE", "ERROR_MOVE_NOT_DIR", "ERROR_MOVE_NO_DST", "ERROR_READ", "ERROR_WRITE", "HEADER_VERSION", "", "INFO_MOVE_NO_SRC", "MAX_META_SIZE_BYTES", "", "WARNING_NOT_ALL_DATA_READ", "create", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "internalLogger", "Lcom/datadog/android/log/Logger;", "encryption", "Lcom/datadog/android/security/Encryption;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: BatchFileHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FileHandler create(Logger logger, Encryption encryption) {
            Intrinsics.checkNotNullParameter(logger, "internalLogger");
            if (encryption == null) {
                return new BatchFileHandler(logger, (Function1) null, (Function1) null, 6, (DefaultConstructorMarker) null);
            }
            return new EncryptedFileHandler(encryption, new BatchFileHandler(logger, (Function1) null, (Function1) null, 6, (DefaultConstructorMarker) null));
        }
    }
}
