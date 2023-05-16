package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okio.Utf8;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\f\u0010\u0017\u001a\u00020\b*\u00020\bH\u0002J\f\u0010\u0018\u001a\u00020\u000e*\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, mo20735d2 = {"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "()V", "ALT_DNS_NAME", "", "ALT_IPA_NAME", "allSubjectAltNames", "", "", "certificate", "Ljava/security/cert/X509Certificate;", "getSubjectAltNames", "type", "verify", "", "host", "session", "Ljavax/net/ssl/SSLSession;", "verifyHostname", "hostname", "pattern", "verifyIpAddress", "ipAddress", "asciiToLowercase", "isAscii", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: OkHostnameVerifier.kt */
public final class OkHostnameVerifier implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();

    private OkHostnameVerifier() {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(sSLSession, "session");
        if (!isAscii(str)) {
            return false;
        }
        try {
            Certificate certificate = sSLSession.getPeerCertificates()[0];
            if (certificate != null) {
                return verify(str, (X509Certificate) certificate);
            }
            throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
        } catch (SSLException unused) {
            return false;
        }
    }

    public final boolean verify(String str, X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(x509Certificate, "certificate");
        if (Util.canParseAsIpAddress(str)) {
            return verifyIpAddress(str, x509Certificate);
        }
        return verifyHostname(str, x509Certificate);
    }

    private final boolean verifyIpAddress(String str, X509Certificate x509Certificate) {
        String canonicalHost = HostnamesKt.toCanonicalHost(str);
        Iterable<String> subjectAltNames = getSubjectAltNames(x509Certificate, 7);
        if ((subjectAltNames instanceof Collection) && ((Collection) subjectAltNames).isEmpty()) {
            return false;
        }
        for (String canonicalHost2 : subjectAltNames) {
            if (Intrinsics.areEqual((Object) canonicalHost, (Object) HostnamesKt.toCanonicalHost(canonicalHost2))) {
                return true;
            }
        }
        return false;
    }

    private final boolean verifyHostname(String str, X509Certificate x509Certificate) {
        String asciiToLowercase = asciiToLowercase(str);
        Iterable<String> subjectAltNames = getSubjectAltNames(x509Certificate, 2);
        if ((subjectAltNames instanceof Collection) && ((Collection) subjectAltNames).isEmpty()) {
            return false;
        }
        for (String verifyHostname : subjectAltNames) {
            if (INSTANCE.verifyHostname(asciiToLowercase, verifyHostname)) {
                return true;
            }
        }
        return false;
    }

    private final String asciiToLowercase(String str) {
        if (!isAscii(str)) {
            return str;
        }
        Locale locale = Locale.US;
        Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
        Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    private final boolean isAscii(String str) {
        return str.length() == ((int) Utf8.size$default(str, 0, 0, 3, (Object) null));
    }

    private final boolean verifyHostname(String str, String str2) {
        CharSequence charSequence = str;
        if (!(charSequence == null || charSequence.length() == 0) && !StringsKt.startsWith$default(str, ".", false, 2, (Object) null) && !StringsKt.endsWith$default(str, "..", false, 2, (Object) null)) {
            CharSequence charSequence2 = str2;
            if (!(charSequence2 == null || charSequence2.length() == 0) && !StringsKt.startsWith$default(str2, ".", false, 2, (Object) null) && !StringsKt.endsWith$default(str2, "..", false, 2, (Object) null)) {
                if (!StringsKt.endsWith$default(str, ".", false, 2, (Object) null)) {
                    str = str + ".";
                }
                if (!StringsKt.endsWith$default(str2, ".", false, 2, (Object) null)) {
                    str2 = str2 + ".";
                }
                String asciiToLowercase = asciiToLowercase(str2);
                CharSequence charSequence3 = asciiToLowercase;
                if (!StringsKt.contains$default(charSequence3, (CharSequence) "*", false, 2, (Object) null)) {
                    return Intrinsics.areEqual((Object) str, (Object) asciiToLowercase);
                }
                if (!StringsKt.startsWith$default(asciiToLowercase, "*.", false, 2, (Object) null) || StringsKt.indexOf$default(charSequence3, '*', 1, false, 4, (Object) null) != -1 || str.length() < asciiToLowercase.length() || Intrinsics.areEqual((Object) "*.", (Object) asciiToLowercase)) {
                    return false;
                }
                Objects.requireNonNull(asciiToLowercase, "null cannot be cast to non-null type java.lang.String");
                String substring = asciiToLowercase.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
                if (!StringsKt.endsWith$default(str, substring, false, 2, (Object) null)) {
                    return false;
                }
                int length = str.length() - substring.length();
                return length <= 0 || StringsKt.lastIndexOf$default((CharSequence) str, '.', length + -1, false, 4, (Object) null) == -1;
            }
        }
        return false;
    }

    public final List<String> allSubjectAltNames(X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(x509Certificate, "certificate");
        return CollectionsKt.plus(getSubjectAltNames(x509Certificate, 7), getSubjectAltNames(x509Certificate, 2));
    }

    private final List<String> getSubjectAltNames(X509Certificate x509Certificate, int i) {
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return CollectionsKt.emptyList();
            }
            List<String> arrayList = new ArrayList<>();
            for (List next : subjectAlternativeNames) {
                if (next != null) {
                    if (next.size() >= 2) {
                        if (!(!Intrinsics.areEqual(next.get(0), (Object) Integer.valueOf(i)))) {
                            Object obj = next.get(1);
                            if (obj == null) {
                                continue;
                            } else if (obj != null) {
                                arrayList.add((String) obj);
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                        }
                    }
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return CollectionsKt.emptyList();
        }
    }
}
