package com.datadog.android.rum.internal.domain.event;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001Bi\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003Jm\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020*HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006+"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/event/ResourceTiming;", "", "dnsStart", "", "dnsDuration", "connectStart", "connectDuration", "sslStart", "sslDuration", "firstByteStart", "firstByteDuration", "downloadStart", "downloadDuration", "(JJJJJJJJJJ)V", "getConnectDuration", "()J", "getConnectStart", "getDnsDuration", "getDnsStart", "getDownloadDuration", "getDownloadStart", "getFirstByteDuration", "getFirstByteStart", "getSslDuration", "getSslStart", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ResourceTiming.kt */
public final class ResourceTiming {
    private final long connectDuration;
    private final long connectStart;
    private final long dnsDuration;
    private final long dnsStart;
    private final long downloadDuration;
    private final long downloadStart;
    private final long firstByteDuration;
    private final long firstByteStart;
    private final long sslDuration;
    private final long sslStart;

    public ResourceTiming() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1023, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ ResourceTiming copy$default(ResourceTiming resourceTiming, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, int i, Object obj) {
        ResourceTiming resourceTiming2 = resourceTiming;
        int i2 = i;
        return resourceTiming.copy((i2 & 1) != 0 ? resourceTiming2.dnsStart : j, (i2 & 2) != 0 ? resourceTiming2.dnsDuration : j2, (i2 & 4) != 0 ? resourceTiming2.connectStart : j3, (i2 & 8) != 0 ? resourceTiming2.connectDuration : j4, (i2 & 16) != 0 ? resourceTiming2.sslStart : j5, (i2 & 32) != 0 ? resourceTiming2.sslDuration : j6, (i2 & 64) != 0 ? resourceTiming2.firstByteStart : j7, (i2 & 128) != 0 ? resourceTiming2.firstByteDuration : j8, (i2 & 256) != 0 ? resourceTiming2.downloadStart : j9, (i2 & 512) != 0 ? resourceTiming2.downloadDuration : j10);
    }

    public final long component1() {
        return this.dnsStart;
    }

    public final long component10() {
        return this.downloadDuration;
    }

    public final long component2() {
        return this.dnsDuration;
    }

    public final long component3() {
        return this.connectStart;
    }

    public final long component4() {
        return this.connectDuration;
    }

    public final long component5() {
        return this.sslStart;
    }

    public final long component6() {
        return this.sslDuration;
    }

    public final long component7() {
        return this.firstByteStart;
    }

    public final long component8() {
        return this.firstByteDuration;
    }

    public final long component9() {
        return this.downloadStart;
    }

    public final ResourceTiming copy(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10) {
        return new ResourceTiming(j, j2, j3, j4, j5, j6, j7, j8, j9, j10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResourceTiming)) {
            return false;
        }
        ResourceTiming resourceTiming = (ResourceTiming) obj;
        return this.dnsStart == resourceTiming.dnsStart && this.dnsDuration == resourceTiming.dnsDuration && this.connectStart == resourceTiming.connectStart && this.connectDuration == resourceTiming.connectDuration && this.sslStart == resourceTiming.sslStart && this.sslDuration == resourceTiming.sslDuration && this.firstByteStart == resourceTiming.firstByteStart && this.firstByteDuration == resourceTiming.firstByteDuration && this.downloadStart == resourceTiming.downloadStart && this.downloadDuration == resourceTiming.downloadDuration;
    }

    public int hashCode() {
        return (((((((((((((((((Long.hashCode(this.dnsStart) * 31) + Long.hashCode(this.dnsDuration)) * 31) + Long.hashCode(this.connectStart)) * 31) + Long.hashCode(this.connectDuration)) * 31) + Long.hashCode(this.sslStart)) * 31) + Long.hashCode(this.sslDuration)) * 31) + Long.hashCode(this.firstByteStart)) * 31) + Long.hashCode(this.firstByteDuration)) * 31) + Long.hashCode(this.downloadStart)) * 31) + Long.hashCode(this.downloadDuration);
    }

    public String toString() {
        long j = this.dnsStart;
        long j2 = this.dnsDuration;
        long j3 = this.connectStart;
        long j4 = this.connectDuration;
        long j5 = this.sslStart;
        long j6 = this.sslDuration;
        long j7 = this.firstByteStart;
        long j8 = this.firstByteDuration;
        long j9 = this.downloadStart;
        return "ResourceTiming(dnsStart=" + j + ", dnsDuration=" + j2 + ", connectStart=" + j3 + ", connectDuration=" + j4 + ", sslStart=" + j5 + ", sslDuration=" + j6 + ", firstByteStart=" + j7 + ", firstByteDuration=" + j8 + ", downloadStart=" + j9 + ", downloadDuration=" + this.downloadDuration + ")";
    }

    public ResourceTiming(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10) {
        this.dnsStart = j;
        this.dnsDuration = j2;
        this.connectStart = j3;
        this.connectDuration = j4;
        this.sslStart = j5;
        this.sslDuration = j6;
        this.firstByteStart = j7;
        this.firstByteDuration = j8;
        this.downloadStart = j9;
        this.downloadDuration = j10;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ResourceTiming(long r23, long r25, long r27, long r29, long r31, long r33, long r35, long r37, long r39, long r41, int r43, kotlin.jvm.internal.DefaultConstructorMarker r44) {
        /*
            r22 = this;
            r0 = r43
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x000a
            r4 = r2
            goto L_0x000c
        L_0x000a:
            r4 = r23
        L_0x000c:
            r1 = r0 & 2
            if (r1 == 0) goto L_0x0012
            r6 = r2
            goto L_0x0014
        L_0x0012:
            r6 = r25
        L_0x0014:
            r1 = r0 & 4
            if (r1 == 0) goto L_0x001a
            r8 = r2
            goto L_0x001c
        L_0x001a:
            r8 = r27
        L_0x001c:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0022
            r10 = r2
            goto L_0x0024
        L_0x0022:
            r10 = r29
        L_0x0024:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x002a
            r12 = r2
            goto L_0x002c
        L_0x002a:
            r12 = r31
        L_0x002c:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0032
            r14 = r2
            goto L_0x0034
        L_0x0032:
            r14 = r33
        L_0x0034:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x003b
            r16 = r2
            goto L_0x003d
        L_0x003b:
            r16 = r35
        L_0x003d:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0044
            r18 = r2
            goto L_0x0046
        L_0x0044:
            r18 = r37
        L_0x0046:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x004d
            r20 = r2
            goto L_0x004f
        L_0x004d:
            r20 = r39
        L_0x004f:
            r0 = r0 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L_0x0054
            goto L_0x0056
        L_0x0054:
            r2 = r41
        L_0x0056:
            r23 = r4
            r25 = r6
            r27 = r8
            r29 = r10
            r31 = r12
            r33 = r14
            r35 = r16
            r37 = r18
            r39 = r20
            r41 = r2
            r22.<init>(r23, r25, r27, r29, r31, r33, r35, r37, r39, r41)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.domain.event.ResourceTiming.<init>(long, long, long, long, long, long, long, long, long, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final long getDnsStart() {
        return this.dnsStart;
    }

    public final long getDnsDuration() {
        return this.dnsDuration;
    }

    public final long getConnectStart() {
        return this.connectStart;
    }

    public final long getConnectDuration() {
        return this.connectDuration;
    }

    public final long getSslStart() {
        return this.sslStart;
    }

    public final long getSslDuration() {
        return this.sslDuration;
    }

    public final long getFirstByteStart() {
        return this.firstByteStart;
    }

    public final long getFirstByteDuration() {
        return this.firstByteDuration;
    }

    public final long getDownloadStart() {
        return this.downloadStart;
    }

    public final long getDownloadDuration() {
        return this.downloadDuration;
    }
}
