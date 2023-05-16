package com.datadog.android.core.internal.persistence.file;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.security.Encryption;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0001J\u0019\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0001J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\rH\u0016J \u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u000bH\u0016R\u0014\u0010\u0004\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0019"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/EncryptedFileHandler;", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "encryption", "Lcom/datadog/android/security/Encryption;", "delegate", "(Lcom/datadog/android/security/Encryption;Lcom/datadog/android/core/internal/persistence/file/FileHandler;)V", "getDelegate$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "getEncryption$dd_sdk_android_release", "()Lcom/datadog/android/security/Encryption;", "delete", "", "target", "Ljava/io/File;", "moveFiles", "srcDir", "destDir", "readData", "", "", "file", "writeData", "data", "append", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: EncryptedFileHandler.kt */
public final class EncryptedFileHandler implements FileHandler {
    public static final String BAD_ENCRYPTION_RESULT_MESSAGE = "Encryption of non-empty data produced empty result, aborting write operation.";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final FileHandler delegate;
    private final Encryption encryption;

    public boolean delete(File file) {
        Intrinsics.checkNotNullParameter(file, "target");
        return this.delegate.delete(file);
    }

    public boolean moveFiles(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "srcDir");
        Intrinsics.checkNotNullParameter(file2, "destDir");
        return this.delegate.moveFiles(file, file2);
    }

    public EncryptedFileHandler(Encryption encryption2, FileHandler fileHandler) {
        Intrinsics.checkNotNullParameter(encryption2, "encryption");
        Intrinsics.checkNotNullParameter(fileHandler, "delegate");
        this.encryption = encryption2;
        this.delegate = fileHandler;
    }

    public final Encryption getEncryption$dd_sdk_android_release() {
        return this.encryption;
    }

    public final FileHandler getDelegate$dd_sdk_android_release() {
        return this.delegate;
    }

    public boolean writeData(File file, byte[] bArr, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(bArr, MPDbAdapter.KEY_DATA);
        byte[] encrypt = this.encryption.encrypt(bArr);
        boolean z2 = true;
        if (!(bArr.length == 0)) {
            if (encrypt.length != 0) {
                z2 = false;
            }
            if (z2) {
                Logger.e$default(RuntimeUtilsKt.getDevLogger(), BAD_ENCRYPTION_RESULT_MESSAGE, (Throwable) null, (Map) null, 6, (Object) null);
                return false;
            }
        }
        return this.delegate.writeData(file, encrypt, z);
    }

    public List<byte[]> readData(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        Iterable<byte[]> readData = this.delegate.readData(file);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(readData, 10));
        for (byte[] decrypt : readData) {
            arrayList.add(getEncryption$dd_sdk_android_release().decrypt(decrypt));
        }
        return (List) arrayList;
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/EncryptedFileHandler$Companion;", "", "()V", "BAD_ENCRYPTION_RESULT_MESSAGE", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: EncryptedFileHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
