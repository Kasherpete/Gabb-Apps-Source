package androidx.core.text;

import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\b¨\u0006\u0005"}, mo20735d2 = {"isDigitsOnly", "", "", "trimmedLength", "", "core-ktx_release"}, mo20736k = 2, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: CharSequence.kt */
public final class CharSequenceKt {
    public static final boolean isDigitsOnly(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return TextUtils.isDigitsOnly(charSequence);
    }

    public static final int trimmedLength(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return TextUtils.getTrimmedLength(charSequence);
    }
}
