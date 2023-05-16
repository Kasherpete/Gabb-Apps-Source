package com.gabb.base.certs;

import com.gabb.base.certs.model.PackageSignature;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0006H&J\f\u0010\u000b\u001a\u00020\u0006*\u00020\fH\u0016¨\u0006\r"}, mo20735d2 = {"Lcom/gabb/base/certs/CertificateManager;", "", "getSignaturesFromPackageName", "", "Lcom/gabb/base/certs/model/PackageSignature;", "packageName", "", "verifyApkSignatureWithPackageName", "", "verifyApkSignatureWithUri", "uri", "toHex", "", "base"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CertificateManager.kt */
public interface CertificateManager {
    List<PackageSignature> getSignaturesFromPackageName(String str);

    String toHex(byte[] bArr);

    boolean verifyApkSignatureWithPackageName(String str);

    boolean verifyApkSignatureWithUri(String str);

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: CertificateManager.kt */
    public static final class DefaultImpls {
        public static String toHex(CertificateManager certificateManager, byte[] bArr) {
            Intrinsics.checkNotNullParameter(certificateManager, "this");
            Intrinsics.checkNotNullParameter(bArr, "receiver");
            return ArraysKt.joinToString$default(bArr, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) CertificateManager$toHex$1.INSTANCE, 30, (Object) null);
        }
    }
}
