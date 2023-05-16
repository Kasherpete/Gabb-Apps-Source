package okhttp3.internal.tls;

import java.security.cert.X509Certificate;
import kotlin.Metadata;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, mo20735d2 = {"Lokhttp3/internal/tls/TrustRootIndex;", "", "findByIssuerAndSignature", "Ljava/security/cert/X509Certificate;", "cert", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: TrustRootIndex.kt */
public interface TrustRootIndex {
    X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate);
}
