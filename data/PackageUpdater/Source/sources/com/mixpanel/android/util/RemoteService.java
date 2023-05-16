package com.mixpanel.android.util;

import android.content.Context;
import java.io.IOException;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

public interface RemoteService {
    void checkIsMixpanelBlocked();

    boolean isOnline(Context context, OfflineMode offlineMode);

    byte[] performRequest(String str, Map<String, Object> map, SSLSocketFactory sSLSocketFactory) throws ServiceUnavailableException, IOException;

    public static class ServiceUnavailableException extends Exception {
        private final int mRetryAfter;

        public ServiceUnavailableException(String str, String str2) {
            super(str);
            int i;
            try {
                i = Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                i = 0;
            }
            this.mRetryAfter = i;
        }

        public int getRetryAfter() {
            return this.mRetryAfter;
        }
    }
}
