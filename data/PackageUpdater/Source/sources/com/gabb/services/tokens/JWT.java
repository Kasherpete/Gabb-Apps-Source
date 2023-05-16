package com.gabb.services.tokens;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, mo20735d2 = {"Lcom/gabb/services/tokens/JWT;", "", "token", "", "(Ljava/lang/String;)V", "bearer", "getBearer", "()Ljava/lang/String;", "getToken", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: JWT.kt */
public final class JWT {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final JWT Empty = new JWT("no-token");
    @SerializedName("token")
    private final String token;

    public static /* synthetic */ JWT copy$default(JWT jwt, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jwt.token;
        }
        return jwt.copy(str);
    }

    public final String component1() {
        return this.token;
    }

    public final JWT copy(String str) {
        Intrinsics.checkNotNullParameter(str, MPDbAdapter.KEY_TOKEN);
        return new JWT(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof JWT) && Intrinsics.areEqual((Object) this.token, (Object) ((JWT) obj).token);
    }

    public int hashCode() {
        return this.token.hashCode();
    }

    public String toString() {
        return "JWT(token=" + this.token + ')';
    }

    public JWT(String str) {
        Intrinsics.checkNotNullParameter(str, MPDbAdapter.KEY_TOKEN);
        this.token = str;
    }

    public final String getToken() {
        return this.token;
    }

    public final String getBearer() {
        return Intrinsics.stringPlus("Bearer ", this.token);
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo20735d2 = {"Lcom/gabb/services/tokens/JWT$Companion;", "", "()V", "Empty", "Lcom/gabb/services/tokens/JWT;", "getEmpty", "()Lcom/gabb/services/tokens/JWT;", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
    /* compiled from: JWT.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JWT getEmpty() {
            return JWT.Empty;
        }
    }
}
