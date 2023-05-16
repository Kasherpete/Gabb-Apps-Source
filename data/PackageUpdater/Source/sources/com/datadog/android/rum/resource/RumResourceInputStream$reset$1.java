package com.datadog.android.rum.resource;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "", "Ljava/io/InputStream;", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumResourceInputStream.kt */
final class RumResourceInputStream$reset$1 extends Lambda implements Function1<InputStream, Unit> {
    public static final RumResourceInputStream$reset$1 INSTANCE = new RumResourceInputStream$reset$1();

    RumResourceInputStream$reset$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((InputStream) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "$this$callWithErrorTracking");
        inputStream.reset();
    }
}
