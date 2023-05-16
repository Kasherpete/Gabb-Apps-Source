package okhttp3.internal.connection;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.Handshake;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "", "Ljava/security/cert/X509Certificate;", "invoke"}, mo20736k = 3, mo20737mv = {1, 4, 0})
/* compiled from: RealConnection.kt */
final class RealConnection$connectTls$2 extends Lambda implements Function0<List<? extends X509Certificate>> {
    final /* synthetic */ RealConnection this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RealConnection$connectTls$2(RealConnection realConnection) {
        super(0);
        this.this$0 = realConnection;
    }

    public final List<X509Certificate> invoke() {
        Handshake access$getHandshake$p = this.this$0.handshake;
        Intrinsics.checkNotNull(access$getHandshake$p);
        Iterable<Certificate> peerCertificates = access$getHandshake$p.peerCertificates();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(peerCertificates, 10));
        for (Certificate certificate : peerCertificates) {
            Objects.requireNonNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            arrayList.add((X509Certificate) certificate);
        }
        return (List) arrayList;
    }
}
