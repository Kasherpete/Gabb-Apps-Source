package okhttp3.internal.tls;

import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\t0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo20735d2 = {"Lokhttp3/internal/tls/BasicTrustRootIndex;", "Lokhttp3/internal/tls/TrustRootIndex;", "caCerts", "", "Ljava/security/cert/X509Certificate;", "([Ljava/security/cert/X509Certificate;)V", "subjectToCaCerts", "", "Ljavax/security/auth/x500/X500Principal;", "", "equals", "", "other", "", "findByIssuerAndSignature", "cert", "hashCode", "", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: BasicTrustRootIndex.kt */
public final class BasicTrustRootIndex implements TrustRootIndex {
    private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts;

    public BasicTrustRootIndex(X509Certificate... x509CertificateArr) {
        Intrinsics.checkNotNullParameter(x509CertificateArr, "caCerts");
        Map<X500Principal, Set<X509Certificate>> linkedHashMap = new LinkedHashMap<>();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            Intrinsics.checkNotNullExpressionValue(subjectX500Principal, "caCert.subjectX500Principal");
            Set<X509Certificate> set = linkedHashMap.get(subjectX500Principal);
            if (set == null) {
                set = new LinkedHashSet<>();
                linkedHashMap.put(subjectX500Principal, set);
            }
            set.add(x509Certificate);
        }
        this.subjectToCaCerts = linkedHashMap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.security.cert.X509Certificate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.security.cert.X509Certificate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.security.cert.X509Certificate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.security.cert.X509Certificate} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.cert.X509Certificate findByIssuerAndSignature(java.security.cert.X509Certificate r4) {
        /*
            r3 = this;
            java.lang.String r0 = "cert"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            javax.security.auth.x500.X500Principal r0 = r4.getIssuerX500Principal()
            java.util.Map<javax.security.auth.x500.X500Principal, java.util.Set<java.security.cert.X509Certificate>> r3 = r3.subjectToCaCerts
            java.lang.Object r3 = r3.get(r0)
            java.util.Set r3 = (java.util.Set) r3
            r0 = 0
            if (r3 == 0) goto L_0x0036
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x001a:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L_0x0034
            java.lang.Object r1 = r3.next()
            r2 = r1
            java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2
            java.security.PublicKey r2 = r2.getPublicKey()     // Catch:{ Exception -> 0x0030 }
            r4.verify(r2)     // Catch:{ Exception -> 0x0030 }
            r2 = 1
            goto L_0x0031
        L_0x0030:
            r2 = 0
        L_0x0031:
            if (r2 == 0) goto L_0x001a
            r0 = r1
        L_0x0034:
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
        L_0x0036:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.BasicTrustRootIndex.findByIssuerAndSignature(java.security.cert.X509Certificate):java.security.cert.X509Certificate");
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof BasicTrustRootIndex) && Intrinsics.areEqual((Object) ((BasicTrustRootIndex) obj).subjectToCaCerts, (Object) this.subjectToCaCerts));
    }

    public int hashCode() {
        return this.subjectToCaCerts.hashCode();
    }
}
