package com.mixpanel.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpService implements RemoteService {
    private static final String LOGTAG = "MixpanelAPI.Message";
    private static final int MAX_UNAVAILABLE_HTTP_RESPONSE_CODE = 599;
    private static final int MIN_UNAVAILABLE_HTTP_RESPONSE_CODE = 500;
    /* access modifiers changed from: private */
    public static boolean sIsMixpanelBlocked;

    public void checkIsMixpanelBlocked() {
        new Thread(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:11:0x001f A[Catch:{ Exception -> 0x0026 }] */
            /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r1 = this;
                    java.lang.String r1 = "api.mixpanel.com"
                    java.net.InetAddress r1 = java.net.InetAddress.getByName(r1)     // Catch:{ Exception -> 0x0026 }
                    boolean r0 = r1.isLoopbackAddress()     // Catch:{ Exception -> 0x0026 }
                    if (r0 != 0) goto L_0x0015
                    boolean r1 = r1.isAnyLocalAddress()     // Catch:{ Exception -> 0x0026 }
                    if (r1 == 0) goto L_0x0013
                    goto L_0x0015
                L_0x0013:
                    r1 = 0
                    goto L_0x0016
                L_0x0015:
                    r1 = 1
                L_0x0016:
                    boolean unused = com.mixpanel.android.util.HttpService.sIsMixpanelBlocked = r1     // Catch:{ Exception -> 0x0026 }
                    boolean r1 = com.mixpanel.android.util.HttpService.sIsMixpanelBlocked     // Catch:{ Exception -> 0x0026 }
                    if (r1 == 0) goto L_0x0026
                    java.lang.String r1 = "MixpanelAPI.Message"
                    java.lang.String r0 = "AdBlocker is enabled. Won't be able to use Mixpanel services."
                    com.mixpanel.android.util.MPLog.m65v(r1, r0)     // Catch:{ Exception -> 0x0026 }
                L_0x0026:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.util.HttpService.C13151.run():void");
            }
        }).start();
    }

    public boolean isOnline(Context context, OfflineMode offlineMode) {
        if (sIsMixpanelBlocked || onOfflineMode(offlineMode)) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                MPLog.m65v(LOGTAG, "A default network has not been set so we cannot be certain whether we are offline");
                return true;
            }
            boolean isConnectedOrConnecting = activeNetworkInfo.isConnectedOrConnecting();
            MPLog.m65v(LOGTAG, "ConnectivityManager says we " + (isConnectedOrConnecting ? "are" : "are not") + " online");
            return isConnectedOrConnecting;
        } catch (SecurityException unused) {
            MPLog.m65v(LOGTAG, "Don't have permission to check connectivity, will assume we are online");
            return true;
        }
    }

    private boolean onOfflineMode(OfflineMode offlineMode) {
        if (offlineMode == null) {
            return false;
        }
        try {
            return offlineMode.isOffline();
        } catch (Exception e) {
            MPLog.m66v(LOGTAG, "Client State should not throw exception, will assume is not on offline mode", e);
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v19, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v34, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v39, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v41, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: java.io.BufferedOutputStream} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r6v33 */
    /* JADX WARNING: type inference failed for: r6v36 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0133 A[SYNTHETIC, Splitter:B:100:0x0133] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0138 A[SYNTHETIC, Splitter:B:104:0x0138] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x013d A[SYNTHETIC, Splitter:B:108:0x013d] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x001c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00ec A[SYNTHETIC, Splitter:B:65:0x00ec] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x011b A[SYNTHETIC, Splitter:B:84:0x011b] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0120 A[SYNTHETIC, Splitter:B:88:0x0120] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0125 A[SYNTHETIC, Splitter:B:92:0x0125] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x012a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] performRequest(java.lang.String r11, java.util.Map<java.lang.String, java.lang.Object> r12, javax.net.ssl.SSLSocketFactory r13) throws com.mixpanel.android.util.RemoteService.ServiceUnavailableException, java.io.IOException {
        /*
            r10 = this;
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "Attempting request to "
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            java.lang.String r0 = "MixpanelAPI.Message"
            com.mixpanel.android.util.MPLog.m65v(r0, r10)
            r10 = 0
            r1 = 0
            r2 = r10
            r3 = r1
        L_0x001c:
            r4 = 3
            if (r10 >= r4) goto L_0x0146
            if (r2 != 0) goto L_0x0146
            r4 = 1
            java.net.URL r5 = new java.net.URL     // Catch:{ EOFException -> 0x010e, IOException -> 0x00e6, all -> 0x00e1 }
            r5.<init>(r11)     // Catch:{ EOFException -> 0x010e, IOException -> 0x00e6, all -> 0x00e1 }
            java.net.URLConnection r5 = r5.openConnection()     // Catch:{ EOFException -> 0x010e, IOException -> 0x00e6, all -> 0x00e1 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ EOFException -> 0x010e, IOException -> 0x00e6, all -> 0x00e1 }
            if (r13 == 0) goto L_0x0039
            boolean r6 = r5 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            if (r6 == 0) goto L_0x0039
            r6 = r5
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            r6.setSSLSocketFactory(r13)     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
        L_0x0039:
            r6 = 2000(0x7d0, float:2.803E-42)
            r5.setConnectTimeout(r6)     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            r6 = 30000(0x7530, float:4.2039E-41)
            r5.setReadTimeout(r6)     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            if (r12 == 0) goto L_0x00ba
            android.net.Uri$Builder r6 = new android.net.Uri$Builder     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            r6.<init>()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.util.Set r7 = r12.entrySet()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
        L_0x0052:
            boolean r8 = r7.hasNext()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            if (r8 == 0) goto L_0x0070
            java.lang.Object r8 = r7.next()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.lang.Object r9 = r8.getKey()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.lang.Object r8 = r8.getValue()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.lang.String r8 = r8.toString()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            r6.appendQueryParameter(r9, r8)     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            goto L_0x0052
        L_0x0070:
            android.net.Uri r6 = r6.build()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.lang.String r6 = r6.getEncodedQuery()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            byte[] r7 = r6.getBytes()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            int r7 = r7.length     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            r5.setFixedLengthStreamingMode(r7)     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            r5.setDoOutput(r4)     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.lang.String r7 = "POST"
            r5.setRequestMethod(r7)     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.io.OutputStream r7 = r5.getOutputStream()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            java.io.BufferedOutputStream r8 = new java.io.BufferedOutputStream     // Catch:{ EOFException -> 0x00b6, IOException -> 0x00b2, all -> 0x00ae }
            r8.<init>(r7)     // Catch:{ EOFException -> 0x00b6, IOException -> 0x00b2, all -> 0x00ae }
            java.lang.String r9 = "UTF-8"
            byte[] r6 = r6.getBytes(r9)     // Catch:{ EOFException -> 0x00ab, IOException -> 0x00a8, all -> 0x00a4 }
            r8.write(r6)     // Catch:{ EOFException -> 0x00ab, IOException -> 0x00a8, all -> 0x00a4 }
            r8.flush()     // Catch:{ EOFException -> 0x00ab, IOException -> 0x00a8, all -> 0x00a4 }
            r8.close()     // Catch:{ EOFException -> 0x00ab, IOException -> 0x00a8, all -> 0x00a4 }
            r7.close()     // Catch:{ EOFException -> 0x00b6, IOException -> 0x00b2, all -> 0x00ae }
            goto L_0x00ba
        L_0x00a4:
            r10 = move-exception
            r6 = r1
            goto L_0x0130
        L_0x00a8:
            r10 = move-exception
            r6 = r1
            goto L_0x00dd
        L_0x00ab:
            r6 = r1
            goto L_0x0112
        L_0x00ae:
            r10 = move-exception
            r6 = r1
            goto L_0x0131
        L_0x00b2:
            r10 = move-exception
            r6 = r1
            r8 = r6
            goto L_0x00dd
        L_0x00b6:
            r6 = r1
            r8 = r6
            goto L_0x0112
        L_0x00ba:
            java.io.InputStream r6 = r5.getInputStream()     // Catch:{ EOFException -> 0x00df, IOException -> 0x00d9, all -> 0x00d6 }
            byte[] r3 = slurp(r6)     // Catch:{ EOFException -> 0x00d4, IOException -> 0x00d1, all -> 0x00cd }
            r6.close()     // Catch:{ EOFException -> 0x00d4, IOException -> 0x00d1, all -> 0x00cd }
            if (r5 == 0) goto L_0x00ca
            r5.disconnect()
        L_0x00ca:
            r2 = r4
            goto L_0x001c
        L_0x00cd:
            r10 = move-exception
            r7 = r1
            goto L_0x0131
        L_0x00d1:
            r10 = move-exception
            r7 = r1
            goto L_0x00dc
        L_0x00d4:
            r7 = r1
            goto L_0x0111
        L_0x00d6:
            r10 = move-exception
            r6 = r1
            goto L_0x00e4
        L_0x00d9:
            r10 = move-exception
            r6 = r1
            r7 = r6
        L_0x00dc:
            r8 = r7
        L_0x00dd:
            r1 = r5
            goto L_0x00ea
        L_0x00df:
            r6 = r1
            goto L_0x0110
        L_0x00e1:
            r10 = move-exception
            r5 = r1
            r6 = r5
        L_0x00e4:
            r7 = r6
            goto L_0x0131
        L_0x00e6:
            r10 = move-exception
            r6 = r1
            r7 = r6
            r8 = r7
        L_0x00ea:
            if (r1 == 0) goto L_0x010a
            int r11 = r1.getResponseCode()     // Catch:{ all -> 0x010b }
            r12 = 500(0x1f4, float:7.0E-43)
            if (r11 < r12) goto L_0x010a
            int r11 = r1.getResponseCode()     // Catch:{ all -> 0x010b }
            r12 = 599(0x257, float:8.4E-43)
            if (r11 > r12) goto L_0x010a
            com.mixpanel.android.util.RemoteService$ServiceUnavailableException r10 = new com.mixpanel.android.util.RemoteService$ServiceUnavailableException     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "Service Unavailable"
            java.lang.String r12 = "Retry-After"
            java.lang.String r12 = r1.getHeaderField(r12)     // Catch:{ all -> 0x010b }
            r10.<init>(r11, r12)     // Catch:{ all -> 0x010b }
            throw r10     // Catch:{ all -> 0x010b }
        L_0x010a:
            throw r10     // Catch:{ all -> 0x010b }
        L_0x010b:
            r10 = move-exception
            r5 = r1
            goto L_0x0130
        L_0x010e:
            r5 = r1
            r6 = r5
        L_0x0110:
            r7 = r6
        L_0x0111:
            r8 = r7
        L_0x0112:
            java.lang.String r4 = "Failure to connect, likely caused by a known issue with Android lib. Retrying."
            com.mixpanel.android.util.MPLog.m59d(r0, r4)     // Catch:{ all -> 0x012f }
            int r10 = r10 + 1
            if (r8 == 0) goto L_0x011e
            r8.close()     // Catch:{ IOException -> 0x011e }
        L_0x011e:
            if (r7 == 0) goto L_0x0123
            r7.close()     // Catch:{ IOException -> 0x0123 }
        L_0x0123:
            if (r6 == 0) goto L_0x0128
            r6.close()     // Catch:{ IOException -> 0x0128 }
        L_0x0128:
            if (r5 == 0) goto L_0x001c
            r5.disconnect()
            goto L_0x001c
        L_0x012f:
            r10 = move-exception
        L_0x0130:
            r1 = r8
        L_0x0131:
            if (r1 == 0) goto L_0x0136
            r1.close()     // Catch:{ IOException -> 0x0136 }
        L_0x0136:
            if (r7 == 0) goto L_0x013b
            r7.close()     // Catch:{ IOException -> 0x013b }
        L_0x013b:
            if (r6 == 0) goto L_0x0140
            r6.close()     // Catch:{ IOException -> 0x0140 }
        L_0x0140:
            if (r5 == 0) goto L_0x0145
            r5.disconnect()
        L_0x0145:
            throw r10
        L_0x0146:
            if (r10 < r4) goto L_0x014d
            java.lang.String r10 = "Could not connect to Mixpanel service after three retries."
            com.mixpanel.android.util.MPLog.m65v(r0, r10)
        L_0x014d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.util.HttpService.performRequest(java.lang.String, java.util.Map, javax.net.ssl.SSLSocketFactory):byte[]");
    }

    private static byte[] slurp(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr, 0, 8192);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
