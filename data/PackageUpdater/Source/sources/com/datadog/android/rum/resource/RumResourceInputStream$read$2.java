package com.datadog.android.rum.resource;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, mo20735d2 = {"<anonymous>", "", "Ljava/io/InputStream;", "invoke", "(Ljava/io/InputStream;)Ljava/lang/Integer;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumResourceInputStream.kt */
final class RumResourceInputStream$read$2 extends Lambda implements Function1<InputStream, Integer> {

    /* renamed from: $b */
    final /* synthetic */ byte[] f136$b;
    final /* synthetic */ RumResourceInputStream this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RumResourceInputStream$read$2(byte[] bArr, RumResourceInputStream rumResourceInputStream) {
        super(1);
        this.f136$b = bArr;
        this.this$0 = rumResourceInputStream;
    }

    public final Integer invoke(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "$this$callWithErrorTracking");
        Integer valueOf = Integer.valueOf(inputStream.read(this.f136$b));
        RumResourceInputStream rumResourceInputStream = this.this$0;
        int intValue = valueOf.intValue();
        if (intValue >= 0) {
            rumResourceInputStream.setSize$dd_sdk_android_release(rumResourceInputStream.getSize$dd_sdk_android_release() + ((long) intValue));
        }
        rumResourceInputStream.lastByte = System.nanoTime();
        return valueOf;
    }
}
