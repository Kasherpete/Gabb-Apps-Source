package androidx.core.text;

import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import com.datadog.android.tracing.internal.domain.event.SpanEventSerializer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a%\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a3\u0010\u0007\u001a\u00020\u0004*\u00020\u00042\b\b\u0001\u0010\b\u001a\u00020\t2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a)\u0010\n\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a3\u0010\b\u001a\u00020\u0004*\u00020\u00042\b\b\u0001\u0010\b\u001a\u00020\t2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a1\u0010\u000b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001aB\u0010\u000b\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u000f\"\u00020\r2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a)\u0010\u0011\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a1\u0010\u0012\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a)\u0010\u0015\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a)\u0010\u0016\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a)\u0010\u0017\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a)\u0010\u0018\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0019"}, mo20735d2 = {"buildSpannedString", "Landroid/text/SpannedString;", "builderAction", "Lkotlin/Function1;", "Landroid/text/SpannableStringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "backgroundColor", "color", "", "bold", "inSpans", "span", "", "spans", "", "(Landroid/text/SpannableStringBuilder;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Landroid/text/SpannableStringBuilder;", "italic", "scale", "proportion", "", "strikeThrough", "subscript", "superscript", "underline", "core-ktx_release"}, mo20736k = 2, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: SpannableStringBuilder.kt */
public final class SpannableStringBuilderKt {
    public static final SpannedString buildSpannedString(Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        function1.invoke(spannableStringBuilder);
        return new SpannedString(spannableStringBuilder);
    }

    public static final SpannableStringBuilder inSpans(SpannableStringBuilder spannableStringBuilder, Object[] objArr, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(objArr, SpanEventSerializer.TAG_SPANS);
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        int length2 = objArr.length;
        int i = 0;
        while (i < length2) {
            Object obj = objArr[i];
            i++;
            spannableStringBuilder.setSpan(obj, length, spannableStringBuilder.length(), 17);
        }
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder inSpans(SpannableStringBuilder spannableStringBuilder, Object obj, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(obj, "span");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(obj, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder bold(SpannableStringBuilder spannableStringBuilder, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        StyleSpan styleSpan = new StyleSpan(1);
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(styleSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder italic(SpannableStringBuilder spannableStringBuilder, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        StyleSpan styleSpan = new StyleSpan(2);
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(styleSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder underline(SpannableStringBuilder spannableStringBuilder, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(underlineSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder color(SpannableStringBuilder spannableStringBuilder, int i, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(i);
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(foregroundColorSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder backgroundColor(SpannableStringBuilder spannableStringBuilder, int i, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(i);
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(backgroundColorSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder strikeThrough(SpannableStringBuilder spannableStringBuilder, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(strikethroughSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder scale(SpannableStringBuilder spannableStringBuilder, float f, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(f);
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(relativeSizeSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder superscript(SpannableStringBuilder spannableStringBuilder, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(superscriptSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final SpannableStringBuilder subscript(SpannableStringBuilder spannableStringBuilder, Function1<? super SpannableStringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        int length = spannableStringBuilder.length();
        function1.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(subscriptSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }
}
