package okhttp3;

import java.security.cert.Certificate;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "", "Ljava/security/cert/Certificate;", "invoke"}, mo20736k = 3, mo20737mv = {1, 4, 0})
/* compiled from: Handshake.kt */
final class Handshake$Companion$handshake$1 extends Lambda implements Function0<List<? extends Certificate>> {
    final /* synthetic */ List $peerCertificatesCopy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Handshake$Companion$handshake$1(List list) {
        super(0);
        this.$peerCertificatesCopy = list;
    }

    public final List<Certificate> invoke() {
        return this.$peerCertificatesCopy;
    }
}
