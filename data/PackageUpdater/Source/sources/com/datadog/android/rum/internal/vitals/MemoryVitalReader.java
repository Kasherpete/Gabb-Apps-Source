package com.datadog.android.rum.internal.vitals;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000f\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/MemoryVitalReader;", "Lcom/datadog/android/rum/internal/vitals/VitalReader;", "statusFile", "Ljava/io/File;", "(Ljava/io/File;)V", "getStatusFile$dd_sdk_android_release", "()Ljava/io/File;", "readVitalData", "", "()Ljava/lang/Double;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: MemoryVitalReader.kt */
public final class MemoryVitalReader implements VitalReader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final File STATUS_FILE = new File(STATUS_PATH);
    private static final String STATUS_PATH = "/proc/self/status";
    private static final String VM_RSS_PATTERN = "VmRSS:\\s+(\\d+) kB";
    private static final Regex VM_RSS_REGEX = new Regex(VM_RSS_PATTERN);
    private final File statusFile;

    public MemoryVitalReader() {
        this((File) null, 1, (DefaultConstructorMarker) null);
    }

    public MemoryVitalReader(File file) {
        Intrinsics.checkNotNullParameter(file, "statusFile");
        this.statusFile = file;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MemoryVitalReader(File file, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? STATUS_FILE : file);
    }

    public final File getStatusFile$dd_sdk_android_release() {
        return this.statusFile;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        r3 = r3.getGroupValues();
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Double readVitalData() {
        /*
            r5 = this;
            java.io.File r0 = r5.statusFile
            boolean r0 = com.datadog.android.core.internal.persistence.file.FileExtKt.existsSafe(r0)
            r1 = 0
            if (r0 == 0) goto L_0x0077
            java.io.File r0 = r5.statusFile
            boolean r0 = com.datadog.android.core.internal.persistence.file.FileExtKt.canReadSafe(r0)
            if (r0 != 0) goto L_0x0013
            goto L_0x0077
        L_0x0013:
            java.io.File r5 = r5.statusFile
            r0 = 1
            java.util.List r5 = com.datadog.android.core.internal.persistence.file.FileExtKt.readLinesSafe$default(r5, r1, r0, r1)
            if (r5 != 0) goto L_0x001e
        L_0x001c:
            r5 = r1
            goto L_0x0066
        L_0x001e:
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r5 = r5.iterator()
        L_0x002b:
            boolean r3 = r5.hasNext()
            if (r3 == 0) goto L_0x0057
            java.lang.Object r3 = r5.next()
            java.lang.String r3 = (java.lang.String) r3
            kotlin.text.Regex r4 = VM_RSS_REGEX
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            kotlin.text.MatchResult r3 = r4.matchEntire(r3)
            if (r3 != 0) goto L_0x0043
        L_0x0041:
            r3 = r1
            goto L_0x0050
        L_0x0043:
            java.util.List r3 = r3.getGroupValues()
            if (r3 != 0) goto L_0x004a
            goto L_0x0041
        L_0x004a:
            java.lang.Object r3 = kotlin.collections.CollectionsKt.getOrNull(r3, r0)
            java.lang.String r3 = (java.lang.String) r3
        L_0x0050:
            if (r3 != 0) goto L_0x0053
            goto L_0x002b
        L_0x0053:
            r2.add(r3)
            goto L_0x002b
        L_0x0057:
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r5 = kotlin.collections.CollectionsKt.firstOrNull(r2)
            java.lang.String r5 = (java.lang.String) r5
            if (r5 != 0) goto L_0x0062
            goto L_0x001c
        L_0x0062:
            java.lang.Double r5 = kotlin.text.StringsKt.toDoubleOrNull(r5)
        L_0x0066:
            if (r5 != 0) goto L_0x006b
            java.lang.Double r1 = (java.lang.Double) r1
            goto L_0x0077
        L_0x006b:
            double r0 = r5.doubleValue()
            r5 = 1000(0x3e8, float:1.401E-42)
            double r2 = (double) r5
            double r0 = r0 * r2
            java.lang.Double r1 = java.lang.Double.valueOf(r0)
        L_0x0077:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.vitals.MemoryVitalReader.readVitalData():java.lang.Double");
    }

    @Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/MemoryVitalReader$Companion;", "", "()V", "STATUS_FILE", "Ljava/io/File;", "getSTATUS_FILE$dd_sdk_android_release", "()Ljava/io/File;", "STATUS_PATH", "", "VM_RSS_PATTERN", "VM_RSS_REGEX", "Lkotlin/text/Regex;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: MemoryVitalReader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final File getSTATUS_FILE$dd_sdk_android_release() {
            return MemoryVitalReader.STATUS_FILE;
        }
    }
}
