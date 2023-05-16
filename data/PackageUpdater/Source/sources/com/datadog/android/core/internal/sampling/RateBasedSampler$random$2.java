package com.datadog.android.core.internal.sampling;

import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo20735d2 = {"<anonymous>", "Ljava/security/SecureRandom;", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RateBasedSampler.kt */
final class RateBasedSampler$random$2 extends Lambda implements Function0<SecureRandom> {
    public static final RateBasedSampler$random$2 INSTANCE = new RateBasedSampler$random$2();

    RateBasedSampler$random$2() {
        super(0);
    }

    public final SecureRandom invoke() {
        return new SecureRandom();
    }
}
