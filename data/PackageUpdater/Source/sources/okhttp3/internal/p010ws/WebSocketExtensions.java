package okhttp3.internal.p010ws;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.internal.Util;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBE\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003JN\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\u000e\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\u0006\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo20735d2 = {"Lokhttp3/internal/ws/WebSocketExtensions;", "", "perMessageDeflate", "", "clientMaxWindowBits", "", "clientNoContextTakeover", "serverMaxWindowBits", "serverNoContextTakeover", "unknownValues", "(ZLjava/lang/Integer;ZLjava/lang/Integer;ZZ)V", "Ljava/lang/Integer;", "component1", "component2", "()Ljava/lang/Integer;", "component3", "component4", "component5", "component6", "copy", "(ZLjava/lang/Integer;ZLjava/lang/Integer;ZZ)Lokhttp3/internal/ws/WebSocketExtensions;", "equals", "other", "hashCode", "noContextTakeover", "clientOriginated", "toString", "", "Companion", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* renamed from: okhttp3.internal.ws.WebSocketExtensions */
/* compiled from: WebSocketExtensions.kt */
public final class WebSocketExtensions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String HEADER_WEB_SOCKET_EXTENSION = "Sec-WebSocket-Extensions";
    public final Integer clientMaxWindowBits;
    public final boolean clientNoContextTakeover;
    public final boolean perMessageDeflate;
    public final Integer serverMaxWindowBits;
    public final boolean serverNoContextTakeover;
    public final boolean unknownValues;

    public WebSocketExtensions() {
        this(false, (Integer) null, false, (Integer) null, false, false, 63, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ WebSocketExtensions copy$default(WebSocketExtensions webSocketExtensions, boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 1) != 0) {
            z = webSocketExtensions.perMessageDeflate;
        }
        if ((i & 2) != 0) {
            num = webSocketExtensions.clientMaxWindowBits;
        }
        Integer num3 = num;
        if ((i & 4) != 0) {
            z2 = webSocketExtensions.clientNoContextTakeover;
        }
        boolean z5 = z2;
        if ((i & 8) != 0) {
            num2 = webSocketExtensions.serverMaxWindowBits;
        }
        Integer num4 = num2;
        if ((i & 16) != 0) {
            z3 = webSocketExtensions.serverNoContextTakeover;
        }
        boolean z6 = z3;
        if ((i & 32) != 0) {
            z4 = webSocketExtensions.unknownValues;
        }
        return webSocketExtensions.copy(z, num3, z5, num4, z6, z4);
    }

    public final boolean component1() {
        return this.perMessageDeflate;
    }

    public final Integer component2() {
        return this.clientMaxWindowBits;
    }

    public final boolean component3() {
        return this.clientNoContextTakeover;
    }

    public final Integer component4() {
        return this.serverMaxWindowBits;
    }

    public final boolean component5() {
        return this.serverNoContextTakeover;
    }

    public final boolean component6() {
        return this.unknownValues;
    }

    public final WebSocketExtensions copy(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4) {
        return new WebSocketExtensions(z, num, z2, num2, z3, z4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebSocketExtensions)) {
            return false;
        }
        WebSocketExtensions webSocketExtensions = (WebSocketExtensions) obj;
        return this.perMessageDeflate == webSocketExtensions.perMessageDeflate && Intrinsics.areEqual((Object) this.clientMaxWindowBits, (Object) webSocketExtensions.clientMaxWindowBits) && this.clientNoContextTakeover == webSocketExtensions.clientNoContextTakeover && Intrinsics.areEqual((Object) this.serverMaxWindowBits, (Object) webSocketExtensions.serverMaxWindowBits) && this.serverNoContextTakeover == webSocketExtensions.serverNoContextTakeover && this.unknownValues == webSocketExtensions.unknownValues;
    }

    public int hashCode() {
        boolean z = this.perMessageDeflate;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        Integer num = this.clientMaxWindowBits;
        int i2 = 0;
        int hashCode = (i + (num != null ? num.hashCode() : 0)) * 31;
        boolean z3 = this.clientNoContextTakeover;
        if (z3) {
            z3 = true;
        }
        int i3 = (hashCode + (z3 ? 1 : 0)) * 31;
        Integer num2 = this.serverMaxWindowBits;
        if (num2 != null) {
            i2 = num2.hashCode();
        }
        int i4 = (i3 + i2) * 31;
        boolean z4 = this.serverNoContextTakeover;
        if (z4) {
            z4 = true;
        }
        int i5 = (i4 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.unknownValues;
        if (!z5) {
            z2 = z5;
        }
        return i5 + (z2 ? 1 : 0);
    }

    public String toString() {
        return "WebSocketExtensions(perMessageDeflate=" + this.perMessageDeflate + ", clientMaxWindowBits=" + this.clientMaxWindowBits + ", clientNoContextTakeover=" + this.clientNoContextTakeover + ", serverMaxWindowBits=" + this.serverMaxWindowBits + ", serverNoContextTakeover=" + this.serverNoContextTakeover + ", unknownValues=" + this.unknownValues + ")";
    }

    public WebSocketExtensions(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4) {
        this.perMessageDeflate = z;
        this.clientMaxWindowBits = num;
        this.clientNoContextTakeover = z2;
        this.serverMaxWindowBits = num2;
        this.serverNoContextTakeover = z3;
        this.unknownValues = z4;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WebSocketExtensions(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : num, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? false : z3, (i & 32) != 0 ? false : z4);
    }

    public final boolean noContextTakeover(boolean z) {
        if (z) {
            return this.clientNoContextTakeover;
        }
        return this.serverNoContextTakeover;
    }

    @Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, mo20735d2 = {"Lokhttp3/internal/ws/WebSocketExtensions$Companion;", "", "()V", "HEADER_WEB_SOCKET_EXTENSION", "", "parse", "Lokhttp3/internal/ws/WebSocketExtensions;", "responseHeaders", "Lokhttp3/Headers;", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
    /* renamed from: okhttp3.internal.ws.WebSocketExtensions$Companion */
    /* compiled from: WebSocketExtensions.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WebSocketExtensions parse(Headers headers) throws IOException {
            Headers headers2 = headers;
            Intrinsics.checkNotNullParameter(headers2, "responseHeaders");
            int size = headers.size();
            Integer num = null;
            Integer num2 = num;
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            for (int i = 0; i < size; i++) {
                if (StringsKt.equals(headers2.name(i), WebSocketExtensions.HEADER_WEB_SOCKET_EXTENSION, true)) {
                    String value = headers2.value(i);
                    int i2 = 0;
                    while (i2 < value.length()) {
                        int delimiterOffset$default = Util.delimiterOffset$default(value, ',', i2, 0, 4, (Object) null);
                        int delimiterOffset = Util.delimiterOffset(value, ';', i2, delimiterOffset$default);
                        String trimSubstring = Util.trimSubstring(value, i2, delimiterOffset);
                        int i3 = delimiterOffset + 1;
                        if (StringsKt.equals(trimSubstring, "permessage-deflate", true)) {
                            if (z) {
                                z4 = true;
                            }
                            while (i3 < delimiterOffset$default) {
                                int delimiterOffset2 = Util.delimiterOffset(value, ';', i3, delimiterOffset$default);
                                int delimiterOffset3 = Util.delimiterOffset(value, '=', i3, delimiterOffset2);
                                String trimSubstring2 = Util.trimSubstring(value, i3, delimiterOffset3);
                                String removeSurrounding = delimiterOffset3 < delimiterOffset2 ? StringsKt.removeSurrounding(Util.trimSubstring(value, delimiterOffset3 + 1, delimiterOffset2), (CharSequence) "\"") : null;
                                int i4 = delimiterOffset2 + 1;
                                if (StringsKt.equals(trimSubstring2, "client_max_window_bits", true)) {
                                    if (num != null) {
                                        z4 = true;
                                    }
                                    Integer intOrNull = removeSurrounding != null ? StringsKt.toIntOrNull(removeSurrounding) : null;
                                    num = intOrNull;
                                    if (intOrNull != null) {
                                        i3 = i4;
                                    }
                                } else {
                                    if (StringsKt.equals(trimSubstring2, "client_no_context_takeover", true)) {
                                        if (z2) {
                                            z4 = true;
                                        }
                                        if (removeSurrounding != null) {
                                            z4 = true;
                                        }
                                        z2 = true;
                                    } else if (StringsKt.equals(trimSubstring2, "server_max_window_bits", true)) {
                                        if (num2 != null) {
                                            z4 = true;
                                        }
                                        Integer intOrNull2 = removeSurrounding != null ? StringsKt.toIntOrNull(removeSurrounding) : null;
                                        num2 = intOrNull2;
                                        if (intOrNull2 != null) {
                                        }
                                    } else if (StringsKt.equals(trimSubstring2, "server_no_context_takeover", true)) {
                                        if (z3) {
                                            z4 = true;
                                        }
                                        if (removeSurrounding != null) {
                                            z4 = true;
                                        }
                                        z3 = true;
                                    }
                                    i3 = i4;
                                }
                                z4 = true;
                                i3 = i4;
                            }
                            i2 = i3;
                            z = true;
                        } else {
                            i2 = i3;
                            z4 = true;
                        }
                    }
                }
            }
            return new WebSocketExtensions(z, num, z2, num2, z3, z4);
        }
    }
}
