package com.datadog.android.core.internal.privacy;

import com.datadog.android.privacy.TrackingConsent;
import com.datadog.android.privacy.TrackingConsentProviderCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/core/internal/privacy/NoOpConsentProvider;", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "()V", "getConsent", "Lcom/datadog/android/privacy/TrackingConsent;", "registerCallback", "", "callback", "Lcom/datadog/android/privacy/TrackingConsentProviderCallback;", "setConsent", "consent", "unregisterAllCallbacks", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpConsentProvider.kt */
public final class NoOpConsentProvider implements ConsentProvider {
    public void registerCallback(TrackingConsentProviderCallback trackingConsentProviderCallback) {
        Intrinsics.checkNotNullParameter(trackingConsentProviderCallback, "callback");
    }

    public void setConsent(TrackingConsent trackingConsent) {
        Intrinsics.checkNotNullParameter(trackingConsent, "consent");
    }

    public void unregisterAllCallbacks() {
    }

    public TrackingConsent getConsent() {
        return TrackingConsent.GRANTED;
    }
}
