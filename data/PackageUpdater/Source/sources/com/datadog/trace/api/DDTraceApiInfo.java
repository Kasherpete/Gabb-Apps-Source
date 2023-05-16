package com.datadog.trace.api;

import androidx.core.p003os.EnvironmentCompat;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DDTraceApiInfo {
    public static final String VERSION;

    static {
        String str;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(DDTraceApiInfo.class.getResourceAsStream("/dd-trace-api.version"), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = bufferedReader.read();
                if (read == -1) {
                    break;
                }
                sb.append((char) read);
            }
            str = sb.toString().trim();
            bufferedReader.close();
        } catch (Exception unused) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        VERSION = str;
        return;
        throw th;
    }

    public static void main(String... strArr) {
        System.out.println(VERSION);
    }
}
