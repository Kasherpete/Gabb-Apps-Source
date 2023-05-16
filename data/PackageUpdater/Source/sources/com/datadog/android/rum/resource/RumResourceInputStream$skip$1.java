package com.datadog.android.rum.resource;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, mo20735d2 = {"<anonymous>", "", "Ljava/io/InputStream;", "invoke", "(Ljava/io/InputStream;)Ljava/lang/Long;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumResourceInputStream.kt */
final class RumResourceInputStream$skip$1 extends Lambda implements Function1<InputStream, Long> {

    /* renamed from: $n */
    final /* synthetic */ long f138$n;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RumResourceInputStream$skip$1(long j) {
        super(1);
        this.f138$n = j;
    }

    public final Long invoke(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "$this$callWithErrorTracking");
        return Long.valueOf(inputStream.skip(this.f138$n));
    }
}
