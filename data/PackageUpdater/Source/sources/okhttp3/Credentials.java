package okhttp3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, mo20735d2 = {"Lokhttp3/Credentials;", "", "()V", "basic", "", "username", "password", "charset", "Ljava/nio/charset/Charset;", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: Credentials.kt */
public final class Credentials {
    public static final Credentials INSTANCE = new Credentials();

    @JvmStatic
    public static final String basic(String str, String str2) {
        return basic$default(str, str2, (Charset) null, 4, (Object) null);
    }

    private Credentials() {
    }

    public static /* synthetic */ String basic$default(String str, String str2, Charset charset, int i, Object obj) {
        if ((i & 4) != 0) {
            charset = StandardCharsets.ISO_8859_1;
            Intrinsics.checkNotNullExpressionValue(charset, "ISO_8859_1");
        }
        return basic(str, str2, charset);
    }

    @JvmStatic
    public static final String basic(String str, String str2, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "username");
        Intrinsics.checkNotNullParameter(str2, "password");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return "Basic " + ByteString.Companion.encodeString(str + ':' + str2, charset).base64();
    }
}
