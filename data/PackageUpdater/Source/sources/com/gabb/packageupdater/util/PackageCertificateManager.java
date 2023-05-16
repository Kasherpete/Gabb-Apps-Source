package com.gabb.packageupdater.util;

import android.content.ContentResolver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.gabb.base.certs.CertificateManager;
import com.gabb.base.certs.model.PackageSignature;
import com.gabb.packageupdater.BuildConfig;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\bH\u0002J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0010\u001a\u00020\nH\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0012\u001a\u00020\nH\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0016\u0010\u0016\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J \u0010\u0018\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, mo20735d2 = {"Lcom/gabb/packageupdater/util/PackageCertificateManager;", "Lcom/gabb/base/certs/CertificateManager;", "packageManager", "Landroid/content/pm/PackageManager;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/content/pm/PackageManager;Landroid/content/ContentResolver;)V", "getPackageInfo", "Landroid/content/pm/PackageInfo;", "archiveFilePath", "", "getSignatures", "", "Lcom/gabb/base/certs/model/PackageSignature;", "packageInfo", "getSignaturesFromPackageName", "packageName", "getSignaturesFromUri", "uri", "verifyApkSignatureWithPackageName", "", "verifyApkSignatureWithUri", "verifySignatures", "appSignatures", "containsAnyIgnoreCase", "elements", "Companion", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PackageCertificateManager.kt */
public final class PackageCertificateManager implements CertificateManager {
    /* access modifiers changed from: private */
    public static final List<PackageSignature> ALLOWED_FINGERPRINTS;
    private static final List<PackageSignature> APK_FINGERPRINT;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ContentResolver contentResolver;
    private final PackageManager packageManager;

    @Inject
    public PackageCertificateManager(PackageManager packageManager2, ContentResolver contentResolver2) {
        Intrinsics.checkNotNullParameter(packageManager2, "packageManager");
        Intrinsics.checkNotNullParameter(contentResolver2, "contentResolver");
        this.packageManager = packageManager2;
        this.contentResolver = contentResolver2;
    }

    public String toHex(byte[] bArr) {
        return CertificateManager.DefaultImpls.toHex(this, bArr);
    }

    public boolean verifyApkSignatureWithUri(String str) {
        Intrinsics.checkNotNullParameter(str, "uri");
        return verifySignatures(getSignaturesFromUri(str));
    }

    public boolean verifyApkSignatureWithPackageName(String str) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        return verifySignatures(getSignaturesFromPackageName(str));
    }

    private final boolean verifySignatures(List<PackageSignature> list) {
        Log.d("updater", Intrinsics.stringPlus("certs=", list));
        List listOf = CollectionsKt.listOf(new PackageSignature("*"));
        if (list.isEmpty()) {
            return false;
        }
        List<PackageSignature> list2 = ALLOWED_FINGERPRINTS;
        if (containsAnyIgnoreCase(list2, listOf)) {
            return true;
        }
        return containsAnyIgnoreCase(list, list2);
    }

    private final List<PackageSignature> getSignaturesFromUri(String str) {
        ContentResolver contentResolver2 = this.contentResolver;
        Uri parse = Uri.parse(str);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(this)");
        ParcelFileDescriptor openFileDescriptor = contentResolver2.openFileDescriptor(parse, "r");
        if (openFileDescriptor == null) {
            return CollectionsKt.emptyList();
        }
        return getSignatures(getPackageInfo(Intrinsics.stringPlus("/proc/self/fd/", Integer.valueOf(openFileDescriptor.getFd()))));
    }

    public List<PackageSignature> getSignaturesFromPackageName(String str) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        PackageInfo packageInfo = this.packageManager.getPackageInfo(str, 134217728);
        if (packageInfo == null) {
            return CollectionsKt.emptyList();
        }
        return getSignatures(packageInfo);
    }

    private final List<PackageSignature> getSignatures(PackageInfo packageInfo) {
        SigningInfo signingInfo = packageInfo.signingInfo;
        Object[] signingCertificateHistory = signingInfo == null ? null : signingInfo.getSigningCertificateHistory();
        if (signingCertificateHistory == null) {
            signingCertificateHistory = packageInfo.signatures;
        }
        if (signingCertificateHistory == null) {
            signingCertificateHistory = (Object[]) new Signature[0];
        }
        Collection arrayList = new ArrayList(signingCertificateHistory.length);
        for (Object obj : signingCertificateHistory) {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(((Signature) obj).toByteArray());
            byte[] digest = instance.digest();
            Intrinsics.checkNotNullExpressionValue(digest, "digest.digest()");
            arrayList.add(toHex(digest));
        }
        Iterable<String> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String packageSignature : iterable) {
            arrayList2.add(new PackageSignature(packageSignature));
        }
        return (List) arrayList2;
    }

    private final PackageInfo getPackageInfo(String str) {
        PackageInfo packageArchiveInfo = this.packageManager.getPackageArchiveInfo(str, 134217792);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo;
        }
        throw new IllegalStateException();
    }

    private final boolean containsAnyIgnoreCase(List<PackageSignature> list, List<PackageSignature> list2) {
        Object obj;
        boolean z;
        Iterable<PackageSignature> iterable = list;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            for (PackageSignature packageSignature : iterable) {
                Iterator it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (StringsKt.equals(((PackageSignature) obj).getSignature(), packageSignature.getSignature(), true)) {
                        break;
                    }
                }
                if (obj != null) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    @Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, mo20735d2 = {"Lcom/gabb/packageupdater/util/PackageCertificateManager$Companion;", "", "()V", "ALLOWED_FINGERPRINTS", "", "Lcom/gabb/base/certs/model/PackageSignature;", "getALLOWED_FINGERPRINTS", "()Ljava/util/List;", "APK_FINGERPRINT", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: PackageCertificateManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<PackageSignature> getALLOWED_FINGERPRINTS() {
            return PackageCertificateManager.ALLOWED_FINGERPRINTS;
        }
    }

    static {
        String[] strArr = BuildConfig.APK_SHA256_FINGERPRINT;
        Intrinsics.checkNotNullExpressionValue(strArr, "APK_SHA256_FINGERPRINT");
        Object[] objArr = (Object[]) strArr;
        Collection arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            String str = (String) obj;
            Intrinsics.checkNotNullExpressionValue(str, "it");
            arrayList.add(new PackageSignature(str));
        }
        List<PackageSignature> list = (List) arrayList;
        APK_FINGERPRINT = list;
        List<PackageSignature> arrayList2 = new ArrayList<>();
        arrayList2.addAll(list);
        ALLOWED_FINGERPRINTS = arrayList2;
    }
}
