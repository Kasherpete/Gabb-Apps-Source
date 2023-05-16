package com.lyft.kronos.internal.ntp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, mo20735d2 = {"Lcom/lyft/kronos/internal/ntp/DatagramFactoryImpl;", "Lcom/lyft/kronos/internal/ntp/DatagramFactory;", "()V", "createPacket", "Ljava/net/DatagramPacket;", "buffer", "", "address", "Ljava/net/InetAddress;", "port", "", "createSocket", "Ljava/net/DatagramSocket;", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: DatagramFactory.kt */
public final class DatagramFactoryImpl implements DatagramFactory {
    public DatagramSocket createSocket() throws SocketException {
        return new DatagramSocket();
    }

    public DatagramPacket createPacket(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        return new DatagramPacket(bArr, bArr.length);
    }

    public DatagramPacket createPacket(byte[] bArr, InetAddress inetAddress, int i) {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        Intrinsics.checkNotNullParameter(inetAddress, "address");
        return new DatagramPacket(bArr, bArr.length, inetAddress, i);
    }
}
