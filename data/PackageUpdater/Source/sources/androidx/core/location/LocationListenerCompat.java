package androidx.core.location;

import android.location.LocationListener;
import android.os.Bundle;

public interface LocationListenerCompat extends LocationListener {
    void onProviderDisabled(String str) {
    }

    void onProviderEnabled(String str) {
    }

    void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
