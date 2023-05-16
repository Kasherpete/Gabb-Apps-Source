package com.datadog.android.rum.internal.domain.scope;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo20735d2 = {"<anonymous>", "", "it", "Ljava/lang/ref/WeakReference;", "", "invoke", "(Ljava/lang/ref/WeakReference;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumActionScope.kt */
final class RumActionScope$handleEvent$1 extends Lambda implements Function1<WeakReference<Object>, Boolean> {
    public static final RumActionScope$handleEvent$1 INSTANCE = new RumActionScope$handleEvent$1();

    RumActionScope$handleEvent$1() {
        super(1);
    }

    public final Boolean invoke(WeakReference<Object> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "it");
        return Boolean.valueOf(weakReference.get() == null);
    }
}
