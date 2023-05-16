package com.datadog.android.core.internal.privacy;

import com.datadog.android.privacy.TrackingConsent;
import com.datadog.android.privacy.TrackingConsentProviderCallback;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\u0003H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/core/internal/privacy/TrackingConsentProvider;", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "consent", "Lcom/datadog/android/privacy/TrackingConsent;", "(Lcom/datadog/android/privacy/TrackingConsent;)V", "callbacks", "Ljava/util/LinkedList;", "Lcom/datadog/android/privacy/TrackingConsentProviderCallback;", "getConsent", "notifyCallbacks", "", "previous", "new", "registerCallback", "callback", "setConsent", "unregisterAllCallbacks", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TrackingConsentProvider.kt */
public final class TrackingConsentProvider implements ConsentProvider {
    private final LinkedList<TrackingConsentProviderCallback> callbacks = new LinkedList<>();
    private volatile TrackingConsent consent;

    public TrackingConsentProvider(TrackingConsent trackingConsent) {
        Intrinsics.checkNotNullParameter(trackingConsent, "consent");
        this.consent = trackingConsent;
    }

    public TrackingConsent getConsent() {
        return this.consent;
    }

    public synchronized void setConsent(TrackingConsent trackingConsent) {
        Intrinsics.checkNotNullParameter(trackingConsent, "consent");
        if (trackingConsent != this.consent) {
            TrackingConsent trackingConsent2 = this.consent;
            this.consent = trackingConsent;
            notifyCallbacks(trackingConsent2, trackingConsent);
        }
    }

    public synchronized void registerCallback(TrackingConsentProviderCallback trackingConsentProviderCallback) {
        Intrinsics.checkNotNullParameter(trackingConsentProviderCallback, "callback");
        this.callbacks.add(trackingConsentProviderCallback);
    }

    public synchronized void unregisterAllCallbacks() {
        this.callbacks.clear();
    }

    private final void notifyCallbacks(TrackingConsent trackingConsent, TrackingConsent trackingConsent2) {
        for (TrackingConsentProviderCallback onConsentUpdated : this.callbacks) {
            onConsentUpdated.onConsentUpdated(trackingConsent, trackingConsent2);
        }
    }
}
