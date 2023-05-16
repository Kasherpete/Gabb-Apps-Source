package com.gabb.packageupdater.domain.vendor;

import android.os.Build;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/vendor/VendorManager;", "", "()V", "getVendorType", "", "Companion", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: VendorManager.kt */
public final class VendorManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    /* renamed from: S1 */
    private static final String f180S1 = "S1";

    /* renamed from: Z2 */
    private static final String f181Z2 = "Z2";

    public final String getVendorType() {
        if (StringsKt.equals(Build.MANUFACTURER, "samsung", true)) {
            Log.i("Vendor", "Samsung Vendor");
            return f180S1;
        } else if (StringsKt.equals(Build.MANUFACTURER, "zte", true)) {
            Log.i("Vendor", "zte Vendor");
            return f181Z2;
        } else {
            throw new IllegalStateException();
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/vendor/VendorManager$Companion;", "", "()V", "S1", "", "Z2", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: VendorManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
