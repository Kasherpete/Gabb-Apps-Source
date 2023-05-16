package com.lyft.kronos.internal.ntp;

import com.lyft.kronos.Clock;
import java.io.IOException;
import kotlin.jvm.internal.ByteCompanionObject;

public class SntpClient {
    private static final long MAX_BOOT_MISMATCH_MS = 1000;
    private static final int NTP_LEAP_NOSYNC = 3;
    private static final int NTP_MODE_BROADCAST = 5;
    private static final int NTP_MODE_CLIENT = 3;
    private static final int NTP_MODE_SERVER = 4;
    private static final int NTP_PACKET_SIZE = 48;
    private static final int NTP_PORT = 123;
    private static final int NTP_STRATUM_DEATH = 0;
    private static final int NTP_STRATUM_MAX = 15;
    private static final int NTP_VERSION = 3;
    private static final long OFFSET_1900_TO_1970 = 2208988800L;
    private static final int ORIGINATE_TIME_OFFSET = 24;
    private static final int RECEIVE_TIME_OFFSET = 32;
    private static final int TRANSMIT_TIME_OFFSET = 40;
    private final DatagramFactory datagramFactory;
    private final Clock deviceClock;
    private final DnsResolver dnsResolver;

    private static class InvalidServerReplyException extends IOException {
        public InvalidServerReplyException(String str) {
            super(str);
        }
    }

    public SntpClient(Clock clock, DnsResolver dnsResolver2, DatagramFactory datagramFactory2) {
        this.deviceClock = clock;
        this.dnsResolver = dnsResolver2;
        this.datagramFactory = datagramFactory2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x009c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.lyft.kronos.internal.ntp.SntpClient.Response requestTime(java.lang.String r20, java.lang.Long r21) throws java.io.IOException {
        /*
            r19 = this;
            r0 = r19
            r1 = 0
            com.lyft.kronos.internal.ntp.DnsResolver r2 = r0.dnsResolver     // Catch:{ all -> 0x0099 }
            r3 = r20
            java.net.InetAddress r2 = r2.resolve(r3)     // Catch:{ all -> 0x0099 }
            com.lyft.kronos.internal.ntp.DatagramFactory r3 = r0.datagramFactory     // Catch:{ all -> 0x0099 }
            java.net.DatagramSocket r1 = r3.createSocket()     // Catch:{ all -> 0x0099 }
            int r3 = r21.intValue()     // Catch:{ all -> 0x0095 }
            r1.setSoTimeout(r3)     // Catch:{ all -> 0x0095 }
            r3 = 48
            byte[] r4 = new byte[r3]     // Catch:{ all -> 0x0095 }
            com.lyft.kronos.internal.ntp.DatagramFactory r5 = r0.datagramFactory     // Catch:{ all -> 0x0095 }
            r6 = 123(0x7b, float:1.72E-43)
            java.net.DatagramPacket r2 = r5.createPacket(r4, r2, r6)     // Catch:{ all -> 0x0095 }
            r5 = 27
            r6 = 0
            r4[r6] = r5     // Catch:{ all -> 0x0095 }
            com.lyft.kronos.Clock r5 = r0.deviceClock     // Catch:{ all -> 0x0095 }
            long r7 = r5.getCurrentTimeMs()     // Catch:{ all -> 0x0095 }
            com.lyft.kronos.Clock r5 = r0.deviceClock     // Catch:{ all -> 0x0095 }
            long r9 = r5.getElapsedTimeMs()     // Catch:{ all -> 0x0095 }
            r5 = 40
            writeTimeStamp(r4, r5, r7)     // Catch:{ all -> 0x0095 }
            r1.send(r2)     // Catch:{ all -> 0x0095 }
            byte[] r2 = java.util.Arrays.copyOf(r4, r3)     // Catch:{ all -> 0x0095 }
            com.lyft.kronos.internal.ntp.DatagramFactory r3 = r0.datagramFactory     // Catch:{ all -> 0x0095 }
            java.net.DatagramPacket r3 = r3.createPacket(r2)     // Catch:{ all -> 0x0095 }
            r1.receive(r3)     // Catch:{ all -> 0x0095 }
            com.lyft.kronos.Clock r3 = r0.deviceClock     // Catch:{ all -> 0x0095 }
            long r14 = r3.getElapsedTimeMs()     // Catch:{ all -> 0x0095 }
            long r3 = r14 - r9
            long r12 = r7 + r3
            byte r3 = r2[r6]     // Catch:{ all -> 0x0095 }
            int r3 = r3 >> 6
            r3 = r3 & 3
            byte r3 = (byte) r3     // Catch:{ all -> 0x0095 }
            byte r4 = r2[r6]     // Catch:{ all -> 0x0095 }
            r4 = r4 & 7
            byte r4 = (byte) r4     // Catch:{ all -> 0x0095 }
            r6 = 1
            byte r6 = r2[r6]     // Catch:{ all -> 0x0095 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r7 = 24
            long r7 = readTimeStamp(r2, r7)     // Catch:{ all -> 0x0095 }
            r9 = 32
            long r9 = readTimeStamp(r2, r9)     // Catch:{ all -> 0x0095 }
            r20 = r1
            long r1 = readTimeStamp(r2, r5)     // Catch:{ all -> 0x0091 }
            checkValidServerReply(r3, r4, r6, r1)     // Catch:{ all -> 0x0091 }
            long r9 = r9 - r7
            long r1 = r1 - r12
            long r9 = r9 + r1
            r1 = 2
            long r16 = r9 / r1
            com.lyft.kronos.internal.ntp.SntpClient$Response r1 = new com.lyft.kronos.internal.ntp.SntpClient$Response     // Catch:{ all -> 0x0091 }
            com.lyft.kronos.Clock r0 = r0.deviceClock     // Catch:{ all -> 0x0091 }
            r11 = r1
            r18 = r0
            r11.<init>(r12, r14, r16, r18)     // Catch:{ all -> 0x0091 }
            if (r20 == 0) goto L_0x0090
            r20.close()
        L_0x0090:
            return r1
        L_0x0091:
            r0 = move-exception
            r1 = r20
            goto L_0x009a
        L_0x0095:
            r0 = move-exception
            r20 = r1
            goto L_0x009a
        L_0x0099:
            r0 = move-exception
        L_0x009a:
            if (r1 == 0) goto L_0x009f
            r1.close()
        L_0x009f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lyft.kronos.internal.ntp.SntpClient.requestTime(java.lang.String, java.lang.Long):com.lyft.kronos.internal.ntp.SntpClient$Response");
    }

    private static void checkValidServerReply(byte b, byte b2, int i, long j) throws InvalidServerReplyException {
        if (b == 3) {
            throw new InvalidServerReplyException("unsynchronized server");
        } else if (b2 != 4 && b2 != 5) {
            throw new InvalidServerReplyException("untrusted mode: " + b2);
        } else if (i == 0 || i > 15) {
            throw new InvalidServerReplyException("untrusted stratum: " + i);
        } else if (j == 0) {
            throw new InvalidServerReplyException("zero transmitTime");
        }
    }

    private static long read32(byte[] bArr, int i) {
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        byte b5 = b & true;
        int i2 = b;
        if (b5 == true) {
            i2 = (b & ByteCompanionObject.MAX_VALUE) + ByteCompanionObject.MIN_VALUE;
        }
        byte b6 = b2 & true;
        int i3 = b2;
        if (b6 == true) {
            i3 = (b2 & ByteCompanionObject.MAX_VALUE) + ByteCompanionObject.MIN_VALUE;
        }
        byte b7 = b3 & true;
        int i4 = b3;
        if (b7 == true) {
            i4 = (b3 & ByteCompanionObject.MAX_VALUE) + ByteCompanionObject.MIN_VALUE;
        }
        byte b8 = b4 & true;
        int i5 = b4;
        if (b8 == true) {
            i5 = (b4 & ByteCompanionObject.MAX_VALUE) + ByteCompanionObject.MIN_VALUE;
        }
        return (((long) i2) << 24) + (((long) i3) << 16) + (((long) i4) << 8) + ((long) i5);
    }

    static long readTimeStamp(byte[] bArr, int i) {
        return ((read32(bArr, i) - OFFSET_1900_TO_1970) * MAX_BOOT_MISMATCH_MS) + ((read32(bArr, i + 4) * MAX_BOOT_MISMATCH_MS) / 4294967296L);
    }

    private static void writeTimeStamp(byte[] bArr, int i, long j) {
        long j2 = j / MAX_BOOT_MISMATCH_MS;
        long j3 = j - (j2 * MAX_BOOT_MISMATCH_MS);
        long j4 = j2 + OFFSET_1900_TO_1970;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) (j4 >> 24));
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) (j4 >> 16));
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((int) (j4 >> 8));
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((int) (j4 >> 0));
        long j5 = (j3 * 4294967296L) / MAX_BOOT_MISMATCH_MS;
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((int) (j5 >> 24));
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((int) (j5 >> 16));
        bArr[i7] = (byte) ((int) (j5 >> 8));
        bArr[i7 + 1] = (byte) ((int) (Math.random() * 255.0d));
    }

    public static final class Response {
        private final Clock deviceClock;
        private final long deviceCurrentTimestampMs;
        private final long deviceElapsedTimestampMs;
        private final long offsetMs;

        Response(long j, long j2, long j3, Clock clock) {
            this.deviceCurrentTimestampMs = j;
            this.deviceElapsedTimestampMs = j2;
            this.offsetMs = j3;
            this.deviceClock = clock;
        }

        /* access modifiers changed from: package-private */
        public long getDeviceCurrentTimestampMs() {
            return this.deviceCurrentTimestampMs;
        }

        /* access modifiers changed from: package-private */
        public long getDeviceElapsedTimestampMs() {
            return this.deviceElapsedTimestampMs;
        }

        public long getCurrentTimeMs() {
            return this.deviceCurrentTimestampMs + this.offsetMs + getResponseAge();
        }

        public long getOffsetMs() {
            return this.offsetMs;
        }

        public long getResponseAge() {
            return this.deviceClock.getElapsedTimeMs() - this.deviceElapsedTimestampMs;
        }

        /* access modifiers changed from: package-private */
        public boolean isFromSameBoot() {
            return Math.abs((this.deviceCurrentTimestampMs - this.deviceElapsedTimestampMs) - (this.deviceClock.getCurrentTimeMs() - this.deviceClock.getElapsedTimeMs())) < SntpClient.MAX_BOOT_MISMATCH_MS;
        }
    }
}
