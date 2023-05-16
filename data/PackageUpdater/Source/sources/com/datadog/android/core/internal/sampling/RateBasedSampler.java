package com.datadog.android.core.internal.sampling;

import java.security.SecureRandom;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/sampling/RateBasedSampler;", "Lcom/datadog/android/core/internal/sampling/Sampler;", "sampleRate", "", "(F)V", "random", "Ljava/security/SecureRandom;", "getRandom", "()Ljava/security/SecureRandom;", "random$delegate", "Lkotlin/Lazy;", "getSampleRate$dd_sdk_android_release", "()F", "sample", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RateBasedSampler.kt */
public final class RateBasedSampler implements Sampler {
    private final Lazy random$delegate = LazyKt.lazy(RateBasedSampler$random$2.INSTANCE);
    private final float sampleRate;

    public RateBasedSampler(float f) {
        this.sampleRate = f;
    }

    public final float getSampleRate$dd_sdk_android_release() {
        return this.sampleRate;
    }

    private final SecureRandom getRandom() {
        return (SecureRandom) this.random$delegate.getValue();
    }

    public boolean sample() {
        float f = this.sampleRate;
        if (f == 0.0f) {
            return false;
        }
        if (!(f == 1.0f) && getRandom().nextFloat() > this.sampleRate) {
            return false;
        }
        return true;
    }
}
