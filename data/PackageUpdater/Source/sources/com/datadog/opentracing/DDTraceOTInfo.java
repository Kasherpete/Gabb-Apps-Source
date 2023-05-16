package com.datadog.opentracing;

import androidx.core.p003os.EnvironmentCompat;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DDTraceOTInfo {
    public static final String JAVA_VERSION;
    public static final String JAVA_VM_NAME;
    public static final String JAVA_VM_VENDOR;
    public static final String VERSION;

    static {
        BufferedReader bufferedReader;
        String str = EnvironmentCompat.MEDIA_UNKNOWN;
        JAVA_VERSION = System.getProperty("java.version", str);
        JAVA_VM_NAME = System.getProperty("java.vm.name", str);
        JAVA_VM_VENDOR = System.getProperty("java.vm.vendor", str);
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(DDTraceOTInfo.class.getResourceAsStream("/dd-trace-ot.version"), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = bufferedReader.read();
                if (read == -1) {
                    break;
                }
                sb.append((char) read);
            }
            String trim = sb.toString().trim();
            bufferedReader.close();
            str = trim;
        } catch (Exception unused) {
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
