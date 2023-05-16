package com.datadog.android.rum.internal.vitals;

import com.datadog.android.core.internal.persistence.file.FileExtKt;
import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000f\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/CPUVitalReader;", "Lcom/datadog/android/rum/internal/vitals/VitalReader;", "statFile", "Ljava/io/File;", "(Ljava/io/File;)V", "getStatFile$dd_sdk_android_release", "()Ljava/io/File;", "readVitalData", "", "()Ljava/lang/Double;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CPUVitalReader.kt */
public final class CPUVitalReader implements VitalReader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final File STAT_FILE = new File(STAT_PATH);
    private static final String STAT_PATH = "/proc/self/stat";
    private static final int UTIME_IDX = 13;
    private final File statFile;

    public CPUVitalReader() {
        this((File) null, 1, (DefaultConstructorMarker) null);
    }

    public CPUVitalReader(File file) {
        Intrinsics.checkNotNullParameter(file, "statFile");
        this.statFile = file;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CPUVitalReader(File file, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? STAT_FILE : file);
    }

    public final File getStatFile$dd_sdk_android_release() {
        return this.statFile;
    }

    public Double readVitalData() {
        String readTextSafe$default;
        if (!FileExtKt.existsSafe(this.statFile) || !FileExtKt.canReadSafe(this.statFile) || (readTextSafe$default = FileExtKt.readTextSafe$default(this.statFile, (Charset) null, 1, (Object) null)) == null) {
            return null;
        }
        List split$default = StringsKt.split$default((CharSequence) readTextSafe$default, new char[]{' '}, false, 0, 6, (Object) null);
        if (split$default.size() > 13) {
            return StringsKt.toDoubleOrNull((String) split$default.get(13));
        }
        return null;
    }

    @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nXT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/vitals/CPUVitalReader$Companion;", "", "()V", "STAT_FILE", "Ljava/io/File;", "getSTAT_FILE$dd_sdk_android_release", "()Ljava/io/File;", "STAT_PATH", "", "UTIME_IDX", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: CPUVitalReader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final File getSTAT_FILE$dd_sdk_android_release() {
            return CPUVitalReader.STAT_FILE;
        }
    }
}
