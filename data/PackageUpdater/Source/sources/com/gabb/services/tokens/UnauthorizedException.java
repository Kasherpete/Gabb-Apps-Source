package com.gabb.services.tokens;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, mo20735d2 = {"Lcom/gabb/services/tokens/UnauthorizedException;", "", "()V", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: errors.kt */
public final class UnauthorizedException extends Throwable {
    public UnauthorizedException() {
        super("You've been signed out.");
    }
}
