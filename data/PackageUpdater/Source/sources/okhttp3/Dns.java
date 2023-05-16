package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\b"}, mo20735d2 = {"Lokhttp3/Dns;", "", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "Companion", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: Dns.kt */
public interface Dns {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final Dns SYSTEM = new Companion.DnsSystem();

    List<InetAddress> lookup(String str) throws UnknownHostException;

    @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000¨\u0006\u0001\u0002\u0007\n\u0005\bF0\u0001¨\u0006\u0006"}, mo20735d2 = {"Lokhttp3/Dns$Companion;", "", "()V", "SYSTEM", "Lokhttp3/Dns;", "DnsSystem", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
    /* compiled from: Dns.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, mo20735d2 = {"Lokhttp3/Dns$Companion$DnsSystem;", "Lokhttp3/Dns;", "()V", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
        /* compiled from: Dns.kt */
        private static final class DnsSystem implements Dns {
            public List<InetAddress> lookup(String str) {
                Intrinsics.checkNotNullParameter(str, "hostname");
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(str);
                    Intrinsics.checkNotNullExpressionValue(allByName, "InetAddress.getAllByName(hostname)");
                    return ArraysKt.toList((T[]) allByName);
                } catch (NullPointerException e) {
                    UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + str);
                    unknownHostException.initCause(e);
                    throw unknownHostException;
                }
            }
        }
    }
}
