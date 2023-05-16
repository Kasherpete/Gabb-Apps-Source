package com.lyft.kronos.internal.ntp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import kotlin.Metadata;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, mo20735d2 = {"Lcom/lyft/kronos/internal/ntp/DnsResolver;", "", "resolve", "Ljava/net/InetAddress;", "host", "", "kronos-java"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: DnsResolver.kt */
public interface DnsResolver {
    InetAddress resolve(String str) throws UnknownHostException;
}
